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

In order to create a verify process you must first create a package to hold the 
verify process. Follow these steps to create the package and classes for your 
verify process:

1.  Create a package called `verify` in your project's layout. 

2.  Inside the `verify` package, create an OSGi Component of the service 
    `VerifyProcess` class that extends the interface `VerifyProcess`:

        @Component(
                immediate = true,
                property = {"verify.process.name=
                com.liferay.verify.process.name"},
                service = VerifyProcess.class
        )
        public class [YourServiceNameVerifyProcess] extends VerifyProcess {
        
        ...
        
        }
        
    You must use `VerifyProcess.class` as the `service` property value to mark 
    the class as a valid implementation for the `VerifyProcess` interface.  The
    `immediate` property specifies that the component will be available 
    immediately, rather than the first time it's used.
    
    You must also define the mandatory `verify.process.name` property, which is 
    used by the OSGi service tracker for verifiers to capture verifier 
    components. See [VerifyProcessTrackerOSGiCommands.java](https://github.com/liferay/liferay-portal/blob/master/modules/apps/foundation/portal/portal-verify-extender/src/main/java/com/liferay/portal/verify/extender/internal/VerifyProcessTrackerOSGiCommands.java#L161-L162)
    for more information.

    It is recommended that you use the name of the service package of the app as 
    the value for the `verify.process.name` property. For example, the 
    [DDMServiceVerifyProcess](https://github.com/mdelapenya/liferay-portal/blob/247aa80e752ad3864fe7fb1d56b8a80a64efc61a/modules/apps/forms-and-workflow/dynamic-data-mapping/dynamic-data-mapping-service/src/main/java/com/liferay/dynamic/data/mapping/verify/DDMServiceVerifyProcess.java) 
    uses the following configuration for the Dynamic Data Mapping app:

        @Component(
                immediate = true,
                property = {"verify.process.name=
                com.liferay.dynamic.data.mapping.service"},
                service = VerifyProcess.class
        )
        public class DDMServiceVerifyProcess extends VerifyProcess {...

    Once your `*VerifyProcess` class is setup you'll need to declare your 
    verifiers next. The `VerifyProcess` interface provides a `doVerify()` method 
    that handles the verifiers.
    
3. Add the following `doVerify()` method to your `*VerifyProcess` class:
        
        @Override
        protected void doVerify() throws Exception {
                [verifyName]();
                [verifyName2]();
                [verifyName3]();
                ...
        }
    
    For example, look at the [DDMServiceVerifyProcess](https://github.com/mdelapenya/liferay-portal/blob/247aa80e752ad3864fe7fb1d56b8a80a64efc61a/modules/apps/forms-and-workflow/dynamic-data-mapping/dynamic-data-mapping-service/src/main/java/com/liferay/dynamic/data/mapping/verify/DDMServiceVerifyProcess.java) 
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

Now that your verifiers are declared, you can learn how to write your verifiers 
in more detail next.

## Writing Verifiers [](id=writing-verifiers)

Verifiers are responsible for the business logic data integrity checks that take 
place during the release of a specific version of your app.

You could write all of your code within your `*VerifyProcess` class, but that 
can start to get clunky. You may want to separate the logic into different 
classes instead.
    
For example, the verifier methods are called within the 
`DDMServiceVerifyProcess` class, however the main logic for the verifiers is 
located inside of another class.

Take a closer look at the `DDMServiceVerifyProcess` class to see how the 
`verifyStructures()` verifier is broken down for example:

    import com.liferay.dynamic.data.mapping.validator.DDMFormValidator;
    
    ...

    public class DDMServiceVerifyProcess extends VerifyProcess {

    ...
    
        @Override
        protected void doVerify() throws Exception {
            verifyStructures();
            verifyStructureLinks();
            verifyTemplateLinks();
    
            verifyContents();
        }
        
        ...
    
        @Reference(unbind = "-")
                protected void setDDMFormValidator(DDMFormValidator 
                ddmFormValidator) {
                        _ddmFormValidator = ddmFormValidator;
        }
    
        ...
    
        protected void verifyDDMForm(DDMForm ddmForm) throws PortalException {
                    _ddmFormValidator.validate(ddmForm);
        }

        ...
    
        protected void verifyStructure(DDMStructure structure)
                    throws PortalException {
    
                    verifyDDMForm(structure.getDDMForm());
                    verifyDDMFormLayout(structure.getDDMFormLayout());
        }

        ...
    
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
    
        ...
    
    }
    
The `verifyStructures()` verifier method throws a portal exception, which calls 
the `verifyStructure(structure)` method. This method throws a portal exception 
as well and calls a couple more methods:
`verifyDDMForm(structure.getDDMForm())` and 
`verifyDDMFormLayout(structure.getDDMFormLayout())`. Taking a closer look at the 
`verifyDDMForm(structure.getDDMForm())` method shown above, you can see that it 
uses the `_ddmFormValidator.validate(ddmForm)` method for validation. The 
`ddmFormValidator` class it references is imported at the top of the 
`DDMServiceVerifyProcess` class, and implemented in the following class: 
[DDMFormValidatorImpl](https://github.com/liferay/liferay-portal/blob/35970e7e4516281127d37c60a45877d88a180b88/modules/apps/forms-and-workflow/dynamic-data-mapping/dynamic-data-mapping-validator/src/main/java/com/liferay/dynamic/data/mapping/validator/internal/DDMFormValidatorImpl.java)

The `ddmFormValidator` `validate()` method that is called in the 
`verifyDDMForm()` method of the `DDMServiceVerifyProcess` class is defined at 
the top of the `DDMFOrmValidatorImpl` class. After following the trail of code, 
you can see what the `verifyStructures()` method of the 
`DDMServiceVerifyProcess` class is actually doing.

The `verifyDDMForm()` method internally checks that the default language of each 
DDMForm is present as an available locale in the DDMForm. It also checks to make 
sure that all the form fields are compliant with the app's business logic. Below 
is the `validate()` method for the `DDMFormValidatorImpl` class:

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
[DDMLayoutValidatorImpl](https://github.com/liferay/liferay-portal/blob/2960360870ae69360861a720136e082a06c5548f/modules/apps/forms-and-workflow/dynamic-data-mapping/dynamic-data-mapping-validator/src/main/java/com/liferay/dynamic/data/mapping/validator/internal/DDMFormLayoutValidatorImpl.java) 
class, the implementation class that handles the layout validation for the
`DDMServiceVerifyProcess` class.

the `verifyDDMFormLayout()` method internally checks that, for all pages, rows, 
and columns, in a `DDMForm`, there are no duplicated field names. It also checks 
if the title of all pages is the same as the `DDMFormLayout` language. Finally, 
it checks that all columns in the rows of each page does not have a size greater 
than 12.

Your verifiers will depend on what your app's business logic requires, but You 
can use the same general approach to write your verifiers. 

Once your verifiers are written, you can learn how to address any data issues
that come up next.

## Addressing Business Logic Data Integrity Issues

You may be wondering why all these exceptions are being thrown in the example 
code. The answer to that question truly gets to the heart of the purpose of a 
verifier. Ultimately, a verifier validates your app's business logic and ensures 
that it is functioning properly. In any case that the business logic of your app 
malfunctions, or is missing data that it needs i.e. user input, the verifier is 
meant to catch the issue and notify the user.

In the `DDMServiceVerifyProcess` class example, the app throws exceptions when 
there is an issue with a verify process.

When issues occur with your app's business logic, you'll need to handle them.
You could write a log, or correct the data with code, or even throw an exception 
if it's a critical issue that should stop the startup of your portal.

A good route would be to throw an exception in most cases. This ensures that
the portal will only start when your app is in full working order.

Follow these steps to write exceptions for your app:

1.  First, you'll need to create a class for your exception that extends the 
    `PortalException` class:
    
        [ExceptionClassName] extends PortalException {
            ...
        }
        
    For example the [DDMFormValidationException](https://github.com/liferay/liferay-portal/blob/master/modules/apps/forms-and-workflow/dynamic-data-mapping/dynamic-data-mapping-validator/src/main/java/com/liferay/dynamic/data/mapping/validator/DDMFormValidationException.java) 
    class is declared with the following line:
    
        public class DDMFormValidationException extends PortalException {
            ...
        }
        
    This class can hold all the exceptions for your app.

2.  Next, you can define your exceptions as inner subclasses of the `*Exception` 
    class you just created:
    
        public class [ExceptionClassName] extends PortalException {
            public static class [ExceptionName] extends [ExceptionClassName] {
                ...
            }
        }
    
    For example, the `DDMFormValidationException` class declares its exceptions
    as subclasses within its class, using the following code:
    
        public static class MustSetDefaultLocale
                        extends DDMFormValidationException {
            ...
        }
        
3.  Next, you'll need to define the method for your exception subclass.

    For example, the `DDMFormValidationException` class defines the 
    `MustSetDefaultLocale()` method the following way:

        public static class MustSetDefaultLocale
                        extends DDMFormValidationException {
                        
            public MustSetDefaultLocale() {
                    super("The default locale property was not set 
                    for the DDM form");
            }
        
        }
        
    In this case, you can see that the method uses the `super` keyword to 
    override the superclass, `DDMFormValidationException` in this case, method, 
    setting the error message for the exception. You can use the same pattern to 
    write your error messages.

    Once your exceptions are declared, you can call the exceptions in your
    verifiers next.
    
4.  Open your `*VerifyProcess` implementation class, for example
    `DDMFormValidatorImpl`, and throw an exception, using the main `*Exception`
    class that you created in step 1.
    
    For example, the `validateDDMFormLocales(DDMFORM ddmForm)` method in the 
    `DDMFormValidatorImpl` class throws a `DDMFormValidationException` within
    its `validateDDMFormLocales()` method:

        protected void validateDDMFormLocales(DDMForm ddmForm)
                throws DDMFormValidationException {
            ...
        }
        
5.  Next, you'll need to define the conditions to call the exception subclass
    you created in step 2.

    For example, the `validateDDMFormLocales(DDMFORM ddmForm)` method sets the
    following conditions for its `DDMFormValidationException`:
        
        protected void validateDDMFormLocales(DDMForm ddmForm)
                throws DDMFormValidationException {
        
                Locale defaultLocale = ddmForm.getDefaultLocale();
        
                if (defaultLocale == null) {
                    throw new MustSetDefaultLocale();
                }
        
                validateDDMFormAvailableLocales(
                    ddmForm.getAvailableLocales(), defaultLocale);
        }

In this example, if there is not a default locale specified for the structure, a 
new instance of the `MustSetDefaultLocale()` is created and thrown, calling the 
`MustSetDefaultLocale()` class method, which sets the error text.

In the case of any exceptions, the server startup process is stopped. This will 
ensure that if there are any major issues that need addressed immediately, the 
server won't startup until they are fixed.

Now that you know how to handle your app's data integrity issues, you can learn 
how to declare dependencies on Liferay services for your verify processes next.

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

Follow these steps to declare dependencies on Liferay services for your verify 
process:

1.  Use the `@Reference` annotation to declare a dependency for your verify 
    process.

    For example, here is a reference to the `DLFileVersionLocalService`
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

Now that your dependencies are declared, you can learn how to execute your 
verifiers next.

## Executing your Verify Process [](id=executing-your-verify-process)

Once your verifiers for your app are written, executing them is a breeze.

To execute your verify process, deploy the module that contains it in the
portal, using the following step: 

1.  Copy your module's `jar` file into your app server's `deploy` folder, under 
    your `LIFERAY_HOME`, and the hot-deploy mechanisms will do the rest.

The verify process is executed immediately. See 
[VerifyProcessTrackerOSGiCommands.java](https://github.com/liferay/liferay-portal/blob/master/modules/apps/foundation/portal/portal-verify-extender/src/main/java/com/liferay/portal/verify/extender/internal/VerifyProcessTrackerOSGiCommands.java#L390-L407)
for more information.

You may wish to execute your verifiers more than just when they are deployed. 
You can learn how to execute your verifiers using the Gogo shell next. 

### Executing your Verify Process Using the Gogo Shell

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
