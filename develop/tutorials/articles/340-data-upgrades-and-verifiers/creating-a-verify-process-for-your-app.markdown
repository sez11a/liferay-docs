# Creating A Verify Process for Your App [](id=creating-a-verify-process-for-your-app)

During the cycle of development, you'll want to verify the data your app is 
producing is consistent and accurate. A verify process is a class that will run 
on portal startup to verify that your app is functioning properly, according to 
your app's business logic, and notify you of any business logic data integrity 
problems found in the database. You should be aware that this may make 
modifications directly to the database.

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
[VerifyProcessTrackerOSGiCommands.java](https://github.com/liferay/liferay-portal/blob/master/modules/apps/foundation/portal/portal-verify-extender/src/main/java/com/liferay/portal/verify/extender/internal/VerifyProcessTrackerOSGiCommands.java#L161-L162)
for more information. To learn how to execute them see 
[VerifyProcessTrackerOSGiCommands.java](https://github.com/liferay/liferay-portal/blob/master/modules/apps/foundation/portal/portal-verify-extender/src/main/java/com/liferay/portal/verify/extender/internal/VerifyProcessTrackerOSGiCommands.java.java#L390-L407)
for more information.

It is recommended that you use the name of the service package of the app as the
value for the `verify.process.name` property, as shown in the Dynamic Data
Mapping app verify process above:

    com.liferay.dynamic.data.mapping.service

You can learn how to write your verifiers in more detail next.

## Writing Verifiers [](id=writing-verifiers)

Verifiers are executed during the verify process, as you saw in the previous 
section, via the `doVerify()` method. They are responsible for the business 
logic data integrity checks that take place during the release of a specific 
version of your app:

    @Override
    protected void doVerify() throws Exception {
        verifyStructures();
        verifyStructureLinks();
        verifyTemplateLinks();

        verifyContents();
    }

You could write all of your code within this class, however for clarity, you may
want to separate the logic into different classes.
    
For example, the verifier methods are called in this class, however the main 
logic for the verifiers is located inside of another class.
    
Take a look at the `verifyStructures()` method for example:

    protected void verifyStructures() throws Exception {
                    try (LoggingTimer loggingTimer = new LoggingTimer()) {
                            ActionableDynamicQuery actionableDynamicQuery =
                                    _ddmStructureLocalService.getActionableDynamicQuery();
    
                            actionableDynamicQuery.setPerformActionMethod(
                                    new 
                                    ActionableDynamicQuery.PerformActionMethod() 
                                    {
    
                                            @Override
                                            public void performAction(Object 
                                            object)
                                                    throws PortalException {
    
                                                    DDMStructure structure = 
                                                    (DDMStructure)object;
    
                                                    verifyStructure(structure);
                                            }
    
                                    });
    
                            actionableDynamicQuery.performActions();
                    }
    }

This verifier method throws a portal exception, calling another method,
`verifyStructure(structure)`, called further down:

    protected void verifyStructure(DDMStructure structure)
                    throws PortalException {
    
                    verifyDDMForm(structure.getDDMForm());
                    verifyDDMFormLayout(structure.getDDMFormLayout());
    }

This method throws a portal exception as well and calls a couple methods. Take a 
closer look at the `verifyDDMForm(structure.getDDMForm());` method for example. 
Examining the code you can see that it uses  this method for validation:

    protected void verifyDDMForm(DDMForm ddmForm) throws PortalException {
                    _ddmFormValidator.validate(ddmForm);
    }

`-ddmFormValidator` is a reference to `ddmFormValidator` as shown here:

    @Reference(unbind = "-")
            protected void setDDMFormValidator(DDMFormValidator 
            ddmFormValidator) {
                    _ddmFormValidator = ddmFormValidator;
    }

The `ddmFormValidator` class is imported at the top of this class:

    import com.liferay.dynamic.data.mapping.validator.DDMFormValidator;

and implemented in the following class: [DDMFormValidatorImpl.java](https://github.com/liferay/liferay-portal/blob/35970e7e4516281127d37c60a45877d88a180b88/modules/apps/forms-and-workflow/dynamic-data-mapping/dynamic-data-mapping-validator/src/main/java/com/liferay/dynamic/data/mapping/validator/internal/DDMFormValidatorImpl.java)

The `ddmFormValidator` `validate()` method that is called in the 
`verifyDDMForm()` method is defined at the top of the `DDMFOrmValidatorImpl` 
class. The `verifyDDMForm()` method internally checks that the default language 
of each DDMForm is present as an available locale in the DDMForm. It also checks 
to make sure that all the form fields are compliant with the app's business 
logic:

    @Component(immediate = true)
    public class DDMFormValidatorImpl implements DDMFormValidator {
    
            @Override
            public void validate(DDMForm ddmForm) throws 
            DDMFormValidationException {
                    validateDDMFormLocales(ddmForm);
    
                    List<DDMFormField> ddmFormFields = 
                    ddmForm.getDDMFormFields();
    
                    if (ddmFormFields.isEmpty()) {
                            throw new MustSetFieldsForForm();
                    }
    
                    validateDDMFormFields(
                            ddmFormFields, new HashSet<String>(), 
                            ddmForm.getAvailableLocales(),
                            ddmForm.getDefaultLocale());
    }

You can review the full [`DDMFormValidatorImpl.java`](https://github.com/liferay/liferay-portal/blob/35970e7e4516281127d37c60a45877d88a180b88/modules/apps/forms-and-workflow/dynamic-data-mapping/dynamic-data-mapping-validator/src/main/java/com/liferay/dynamic/data/mapping/validator/internal/DDMFormValidatorImpl.java) 
class for the entire method to learn more.

As another example of what a verifier typically does, you can take a look at the
[DDMLayoutValidatorImpl.java](https://github.com/liferay/liferay-portal/blob/2960360870ae69360861a720136e082a06c5548f/modules/apps/forms-and-workflow/dynamic-data-mapping/dynamic-data-mapping-validator/src/main/java/com/liferay/dynamic/data/mapping/validator/internal/DDMFormLayoutValidatorImpl.java) 
class.

the `verifyDDMFormLayout()` method internally checks that, for all pages, rows, 
and columns, in a DDMForm, there are no duplicated field names. It also checks 
if the title of all pages is the same as the DDMFormLayout language. Finally, it 
checks that all columns in the rows of each page does not have a size greater 
than 12.

Your verifiers will depend on what your app's business logic requires, but You 
can use the same general approach to write your verifiers. 

Once your verifiers are written, you can learn how to address any data issues
that come up next.

## Addressing Business Logic Data Integrity Issues

When issues occur with your app's business logic, you'll need to handle them.
You could write a log, or correct the data with code, or even throw an exception 
if it's a critical issue that portal should not, or can not, start without 
explaining how to correct it manually.

To give an example of logging an error, take another look at the 
`validateDDMFormLocales(DDMFORM ddmForm)` method:

    protected void validateDDMFormLocales(DDMForm ddmForm)
            throws DDMFormValidationException {
    
            Locale defaultLocale = ddmForm.getDefaultLocale();
    
            if (defaultLocale == null) {
                throw new MustSetDefaultLocale();
            }
    
            validateDDMFormAvailableLocales(
                ddmForm.getAvailableLocales(), defaultLocale);
        }

In this example, if there is not a default locale specified, a 
`MustSetDefaultLocale()` error is thrown. This is defined in the 
[DDMFormLayoutValidationException.java](https://github.com/liferay/liferay-portal/blob/2960360870ae69360861a720136e082a06c5548f/modules/apps/forms-and-workflow/dynamic-data-mapping/dynamic-data-mapping-validator/src/main/java/com/liferay/dynamic/data/mapping/validator/DDMFormLayoutValidationException.java)
class:

    public static class MustSetDefaultLocale
                    extends DDMFormLayoutValidationException {
    
                    public MustSetDefaultLocale() {
                            super("DDM form layout does not have a default 
                            locale");
                    }
    
    }
    
This exception extends `DDMFormLayoutValidationException`. If you look at the 
top of the `DDMFormLayoutValidationException.java` class, you can see that it
extends `PortalException.java`:

    public class DDMFormLayoutValidationException extends PortalException

If you take a look at the `DBUpgrader.java` class(the class that handles the 
verify suite), you can see how the verify process works:

    try {
                StartupHelperUtil.verifyProcess(
                    newBuildNumber, release.isVerified());
            }
            catch (Exception e) {
                _updateReleaseState(ReleaseConstants.STATE_VERIFY_FAILURE);
    
                _log.error(
                    "Unable to execute verify process: " + e.getMessage(), e);
    
                throw e;
            }
            finally {
                if (PropsValues.VERIFY_DATABASE_TRANSACTIONS_DISABLED) {
                    TransactionsUtil.enableTransactions();
                }
            }

In the case of any exceptions, it catches it and stops the server startup 
process. This will ensure that if there are any major issues that need addressed 
immediately, the server won't startup until they are fixed, as shown in the 
`processStartupEvents()` method of the try catch block for the 
[MainServlet.java](https://github.com/liferay/liferay-portal/blob/4c72e50d806acbcf130a120e809d5051013efb44/portal-impl/src/com/liferay/portal/servlet/MainServlet.java) 
class:

    if (_log.isDebugEnabled()) {
                            _log.debug("Process startup events");
                    }
    
                    try {
                            processStartupEvents();
                    }
                    catch (Exception e) {
                            _log.error(e, e);
    
                            System.out.println(
                                    "Stopping the server due to unexpected 
                                    startup errors");
    
                            System.exit(0);
    }
    
Now that you know how data integrity issues are handled, you can learn how to 
declare dependencies on Liferay services for your verify processes next.

## Using Liferay Services in Your Verify Process [](id=using-liferay-services-in-your-verify-process)

Verify processes execute on the startup of a specific release. It's important
to be aware of this while running upgrade processes because your database starts
in a previous schema version, then gets upgraded to the new schema version.

This is an important distinction between upgrade processes and verify processes.
An upgrade process of the portal core cannot use Liferay services to access the 
database, as those Liferay services are using the upgraded definition of the
database, but since verify processes are already in the upgraded schema version, 
they can use release Liferay services. You can learn more about upgrade
processes in the [Creating an Upgrade Process for Your App](/develop/tutorials/-/knowledge_base/7-0/creating-an-upgrade-process-for-your-app) 
tutorial.

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
