# Creating A Verify Process for Your App [](id=creating-a-verify-process-for-your-app)

During the cycle of development, you'll want to verify the data your app is 
producing is consistent. A verify process is a class that will run on portal 
startup to verify and fix any integrity problems found in the database. You 
should be aware that this may make modifications directly to the database.

This tutorial demonstrates how to:

- Create a verify process for your app using new development patterns
- Execute a verify process 

Get started by writing your verify package next.

## Writing the Verify Package [](id=writing-the-verify-package)

In order to create a verify process you must first create a package called 
`verify` in your project's layout. Inside the `verify` package, create an OSGi 
Component of the service `VerifyProcess` class that extends the interface
`VerifyProcess`.

This interface provides a `doVerify` method that handles the verifiers. For
example, look at the [DDMServiceVerifyProcess.java](https://github.com/mdelapenya/liferay-portal/blob/247aa80e752ad3864fe7fb1d56b8a80a64efc61a/modules/apps/forms-and-workflow/dynamic-data-mapping/dynamic-data-mapping-service/src/main/java/com/liferay/dynamic/data/mapping/verify/DDMServiceVerifyProcess.java) 
class for the Dynamic Data Mapping app:

    @Component(
            immediate = true,
            property = {"verify.process.name=
            com.liferay.dynamic.data.mapping.service"},
            service = VerifyProcess.class
    )
    public class DDMServiceVerifyProcess extends VerifyProcess {
    
            @Override
            protected void doVerify() throws Exception {
                    verifyStructures();
                    verifyStructureLinks();
                    verifyTemplateLinks();

                    verifyContents();
            }

    }

You must use `VerifyProcess.class` as the `service` property value to mark the
class as a valid implementation for the `VerifyProcess` interface.  The
`immediate` property specifies that the component will be available immediately,
rather than the first time it's used.

You must also define the mandatory `verify.process.name` property, which is used 
by the OSGi service tracker for verifiers to capture verifier components. See 
[VerifyProcessTracker.java](https://github.com/liferay/liferay-portal/blob/master/modules/apps/foundation/portal/portal-verify-extender/src/main/java/com/liferay/portal/verify/extender/internal/VerifyProcessTracker.java#L152-L153)
for more information. And to execute them see [VerifyProcessTracker.java](https://github.com/liferay/liferay-portal/blob/master/modules/apps/foundation/portal/portal-verify-extender/src/main/java/com/liferay/portal/verify/extender/internal/VerifyProcessTracker.java#L344-L361)
for more information.

It is recommended that you use the name of the service package of the app as the
value for the `verify.process.name` property, as shown in the Dynamic Data
Mapping app verify process above:

    com.liferay.dynamic.data.mapping.service

Verify processes are written within this class as well. For example, look at
`VerifyUser`:

    @Override
    protected void doVerify() throws Exception {
        verifyStructures();
        verifyStructureLinks();
        verifyTemplateLinks();

        verifyContents();
    }

You can see that the `verifyStructures()` and `verifyStructure(DDMStructure)`
methods are defined in this same class:

    protected void verifyStructures() throws Exception {
        try (LoggingTimer loggingTimer = new LoggingTimer()) {
            ActionableDynamicQuery actionableDynamicQuery =
                _ddmStructureLocalService.getActionableDynamicQuery();

            actionableDynamicQuery.setPerformActionMethod(
                new ActionableDynamicQuery.PerformActionMethod() {

                    @Override
                    public void performAction(Object object)
                        throws PortalException {

                        DDMStructure structure = (DDMStructure)object;

                        verifyStructure(structure);
                    }

                });

            actionableDynamicQuery.performActions();
        }
    }

    protected void verifyStructure(DDMStructure structure)
        throws PortalException {

        verifyDDMForm(structure.getDDMForm());
        verifyDDMFormLayout(structure.getDDMFormLayout());
    }

Now that your verify process class is written, you can learn how to declare
dependencies on Liferay services for your verify processes next.

## Using Liferay Services in Your Verify Process [](id=using-liferay-services-in-your-verify-process)

Verify processes execute on the startup of a specific release. It's important
to be aware of this while running upgrade processes because your database starts
in a previous schema version, then gets upgraded to the new schema version.

This is an important distinction between upgrade processes and verify processes.
An upgrade process of the portal core cannot use Liferay services to access the 
database, as those Liferay services are using the upgraded definition of the
database, but since verify processes are already in the upgraded schema version, 
they can use release Liferay services.

If you choose to use Liferay services in your verify process, you'll need to
declare dependencies on those services. Because the services are not available 
until the Portal, or the modules they rely on, are started, **your verify
process must depend on them to load**, before your processes can execute.
You can declare a dependency for your verify process, using the `@Reference`
annotation. For example, here is a reference to the `DLFileVersionLocalService`
for the [Document Library app](https://github.com/liferay/liferay-portal/blob/2960360870ae69360861a720136e082a06c5548f/modules/apps/collaboration/document-library/document-library-service/src/main/java/com/liferay/document/library/workflow/DLFileEntryWorkflowHandler.java):

    @Reference(unbind = "-")
    protected void setDLFileVersionLocalService(
      DLFileVersionLocalService dlFileVersionLocalService) {

      _dlFileVersionLocalService = dlFileVersionLocalService;
    }
    ...
    private DLFileVersionLocalService _dlFileVersionLocalService;

This lets the verify process know that the OSGi framework will provide a valid 
implementation of the proper service (in this example, a 
`DLFileVersionLocalService`). Whenever some other module provides that 
implementation, the verify process can use it.

As you may have noticed, the `ServiceUtil` classes are no longer being used.
Instead, the OSGi framework is providing the Liferay services, so you can
directly use the components.

Now that your verify process is written, you'll need to execute it next.

## Executing your very process [](id=executing-your-verify-process)

To execute your verify process, deploy the module that contains it in the
portal, using the following step: 

1.  Copy your module's `jar` file into your app server's `deploy` folder, under 
    your `LIFERAY_HOME`, and the hot-deploy mechanisms will do the rest.

The verify process will be executed immediately.

With the new modularity capabilities of the portal, you can now trigger a new
execution of any verify process already present in the portal. Using the
[Gogo shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell), 
the OSGi command line shell, you can perform the following useful operations 
over the existing verify processes:

 - **execute:** Executes the verify process passed as an argument. For example,
 you would run the following command from the Gogo shell to execute the 
 `com.liferay.journal.service` module's verify process: 
 `g! verify:execute com.liferay.journal.service`.
 
 - **executeAll:** Executes all verify processes present in the portal, using 
 the following command from the Gogo shell: `g! verify:executeAll`.
 
 - **list:** Lists all existing verify processes present in the portal, using
 the following command from the Gogo shell: `g! verify:list`. An example listing
 would be the following output:
 
    Registered verify process com.liferay.portal.scheduler.internal.verify
    Registered verify process com.liferay.portal.security.sso.cas
    Registered verify process com.liferay.journal.service
    Registered verify process com.liferay.portal.security.sso.openid
    Registered verify process com.liferay.dynamic.data.mapping.service
    Registered verify process com.liferay.portal.security.sso.token.siteminder
    Registered verify process com.liferay.wiki.service
    Registered verify process com.liferay.portal.security.sso.ntlm
    Registered verify process com.liferay.portal.security.sso.token.shibboleth
    Registered verify process com.liferay.polls.service
    Registered verify process com.liferay.portal.security.sso.google
    Registered verify process com.liferay.mobile.device.rules.service
    Registered verify process com.liferay.shopping.service
    Registered verify process com.liferay.portal.security.ldap
    Registered verify process com.liferay.portal.security.sso.opensso
    Registered verify process com.liferay.portal.security.service.access.policy.service
    Registered verify process com.liferay.document.library.service
    Registered verify process com.liferay.portal.security.sso.facebook.connect
    Registered verify process com.liferay.bookmarks.service
 
 - **show:** Shows information about the verify process passed as an argument.
 For example, you would run the following command in the Gogo shell to show
 information about the `com.liferay.portal.security.sso.cas` module's verify
 process: `g! verify:show com.liferay.portal.security.sso.cas`. An example
 output would be the following information:
 
    Registered verify process com.liferay.portal.security.sso.cas
    
There you go. Now you know how to create a verification process for your app!

## Related Topics [](id=related-topics)

[Creating an Upgrade Process for Your App](/develop/tutorials/-/knowledge_base/7-0/creating-an-upgrade-process-for-your-application)

[Migrating a Liferay 6 Application](/develop/tutorials/-/knowledge_base/7-0/migrating-a-liferay-6-application)

[Application Configuration](/develop/tutorials/-/knowledge_base/7-0/application-configuration)
