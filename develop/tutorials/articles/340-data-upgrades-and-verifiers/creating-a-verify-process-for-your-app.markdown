# Creating A Verify Process for Your App [](id=creating-a-verify-process-for-your-app)

During the cycle of development, you'll want to verify the data your app is 
producing is consistent. A verify process is a class that will run on portal 
startup to verify and fix any integrity problems found in the database. You 
should be aware that this may make modifications directly to the database.

This tutorial demonstrates how to:

- Create a verify process for your app using new development patterns

Get started by writing your verify package next.

## Writing the Verify Package [](id=writing-the-verify-package)

In order to create a verify process you must first create a package called 
`verify` in your project's layout. Inside of the `verify` package, create a OSGi 
Component of the service `VerifyProcess` class that extends the interface
`VerifyProcess`.

This interface provides a `doVerify` method that handles the verifiers. For
example, take a look at the [DDMServiceVerifyProcess.java](https://github.com/mdelapenya/liferay-portal/blob/247aa80e752ad3864fe7fb1d56b8a80a64efc61a/modules/apps/forms-and-workflow/dynamic-data-mapping/dynamic-data-mapping-service/src/main/java/com/liferay/dynamic/data/mapping/verify/DDMServiceVerifyProcess.java) 
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

You must use `VerifyProcess.class` as the `service` property value, to denote 
that the class is a valid implementation for the `VerifyProcess` interface.
The `immediate` property specifies that the component will be available 
immediately, rather than the first time it's used.

You must also define the mandatory `verify.process.name` property, which is used 
by the OSGi service tracker for verifiers to capture verifier components. See 
[VerifyProcessTracker.java](https://github.com/liferay/liferay-portal/blob/master/modules/apps/foundation/portal/portal-verify-extender/src/main/java/com/liferay/portal/verify/extender/internal/VerifyProcessTracker.java#L152-L153)
for more information. And to execute them see [VerifyProcessTracker.java](https://github.com/liferay/liferay-portal/blob/master/modules/apps/foundation/portal/portal-verify-extender/src/main/java/com/liferay/portal/verify/extender/internal/VerifyProcessTracker.java#L344-L361)
for more information.

It is recommended that you use the name of the service package of the app as the
value for the `verify.process.name` property, as shown in the Mobile Device
Rules app verify process above:

    com.liferay.dynamic.data.mapping.service

Verify processes are written within this class as well. Let's use the
VerifyUser as an example to understand the concepts.

    @Override
    protected void doVerify() throws Exception {
        verifyStructures();
        verifyStructureLinks();
        verifyTemplateLinks();

        verifyContents();
    }

you can see that the `verifyStructures()` and `verifyStructure(DDMStructure)`
method is defined in this same class:

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
until the Portal or the modules they rely on are started, **your verify
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

There you go. Now you know how to create a verification process for your app!

## Related Topics [](id=related-topics)

[Creating an Upgrade Process for Your App](/develop/tutorials/-/knowledge_base/7-0/creating-an-upgrade-process-for-your-application)

[Migrating a Liferay 6 Application](/develop/tutorials/-/knowledge_base/7-0/migrating-a-liferay-6-application)

[Application Configuration](/develop/tutorials/-/knowledge_base/7-0/application-configuration)
