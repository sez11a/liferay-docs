# Converting Legacy Applications to Modules [](id=converting-legacy-applications-to-modules)

You've probably heard the term *modularity* discussed frequently in relation to
Liferay 7.0. With Liferay 7.0 transforming into a modular platform, Liferay
applications will be composed of one or more
[modules](https://dev.liferay.com/participate/liferaypedia/-/wiki/Main/Module).

A module doesn't use the traditional Liferay application structure from previous
versions. Liferay is following a different structure for modules that you'll
learn about in this tutorial. You, however, are not required to follow
Liferay's standard application structure; you have the freedom to structure your
application's modules however you wish.

A module in Liferay is very similar to a standard Java application with some
additional metadata and annotations. The annotations replace the need for XML
file descriptors in the application and are easier to manage since everything is
in one place.

The options you have for upgrading your application to Liferay 7.0 are
discussed briefly in the
[Migrating Legacy Applications to New Plugins SDK](/develop/tutorials/-/knowledge_base/7-0/migrating-legacy-applications-to-new-plugins-sdk)
tutorial. Because Liferay 7.0 maintains backwards compatibility, you have two
options:

1. You can keep your legacy application as a WAR-style application.
2. You can convert your application into modules. Before you begin following
   the steps in this tutorial to convert your application into modules,
   consider the recommendations regarding if and when to convert your legacy
   application.

Before you begin converting your legacy application into modules, we recommend
that you first migrate your legacy plugin to a Liferay 7.0 compatible WAR-style
application. It is much easier to convert a legacy application to modules when
the API and breaking changes have already been updated. If you jump from a
legacy 6.2 application to 7.0 modules, it will be much more difficult to debug
and figure out which issues are related to API changes and which are related to
the migration process.

Converting your legacy application to modules might not always be the best
choice. In some scenarios, it could make sense to stick with your traditional
WAR model. Below, you'll learn some important tips to guide your decision on
whether to convert your legacy application to modules.

**When to convert?**

- If you have a very large application with many lines of code. For example, if
  there are many developers that are collaborating on an application
  concurrently and making changes frequently, separating the code into modules
  will increase productivity and provide the agility to release more
  frequently.
- If your plugin has reusable parts that you'd like to consume from elsewhere.
  For instance, suppose you have business logic that you're reusing in multiple
  different projects. Instead of copying that code into several different WARs
  and deploying those WARs to different customers, you can convert your
  application to modules and consume the services provided by those modules
  from other modules.

**When not to convert?**

- You have a portlet that's JSR-168/286 compatible and you still want to be
  able to deploy it into another portlet container. If you want to retain that
  compatibility, it is recommended to stay with the traditional WAR model.
- You're using a complex legacy web framework that is heavily tied to the Java
  EE programming model, and the amount of work necessary to make that work with
  OSGi is more than you feel is necessary or warranted.
- If your plugin interacts with the app server. Module-based applications are
  not as portable when they directly interact with the app server.
- If your legacy application's original intent is to have a limited-lifetime.

Your decision to convert to modules ultimately comes down to benefits vs.
costs. Obviously, the time to convert your legacy application is a cost.
Likewise, there are many benefits to managing your legacy application as
modules.

A large application can be split into many small independent, easy to manage
modules. These small modules also allow for incremental release cycles. In
multi-module projects, this also means that a certain module can be updated
independently. For instance, if a JSP is changed due to a security issue, the
web (client) module can be updated while the persistence modules can remain
unchanged. This could mean applications that usually had to wait for new
Liferay releases could see independent releases between Liferay versions.

Module dependencies are explicitly listed within a module. A module will refuse
to run unless all dependencies are met, thus eliminating obscure run time
errors. Another common deployment issue for legacy applications was multiple
versions of the same library in an environment. The class loader would merge
classes from multiple versions of a library, leading to very hard to
troubleshoot and obscure problems. Module versions can be stated explicitly in
the dependency, eliminating these types of issues.

Now that you have some ammunition to make an informed decision on what's best
for your particular situation, you'll learn how to convert your legacy WAR
application into modules. Before getting started, it's important to reiterate
that the module structure shown in this tutorial is just one of many ways for
structuring your application's modules. It's also important to remember that
applications come in all different shapes and sizes. There may be special
actions that some applications require. This tutorial will provide the general
process for converting to modules using Liferay's module structure.

The first thing you'll do is create your application's web (client) module.

## Converting Your Legacy Application's Portlet Classes and UI [](id=converting-your-legacy-applications-portlet-classes-and-ui)

The first thing you'll do is create your application's parent directory and the
directory structure for your application's *web* client module. This module
holds your application's portlet classes and is responsible for its UI. Before
you start creating a skeleton structure for your application's modules, you
should determine which modules which will comprise the 7.0 version of your
application. If your application provides service and API classes (which is the
case for all Liferay Service Builder applications), you should create separate
modules for your service implementation and service API modules. This tutorial
assumes the Maven project model, although any build tools or directory setup is
permissible.

1.  Create the parent directory for your application. This parent directory is
    home for your application's independent modules and configuration files. For
    example, if your application's name is *Tasks*, then your parent directory
    could be *tasks*.

2.  Create the directory structure skeleton for your web client module. You can
    do this automatically by using Blade Tools. You can learn how to install
    Blade Tools by visiting the
    [Introduction to Blade Tools](/develop/tutorials/-/knowledge_base/7-0/introduction-to-blade-tools)
    tutorial and you can view examples of
    [Creating Liferay Applications](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-applications)
    and
    [Creating Liferay Components](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-components)
    by visiting the respective tutorials.

    Navigate to your parent directory (e.g., `tasks`) and run the following
    Blade Tools command to generate a generic web client module structure:

        blade create -t mvcportlet [APPLICATION_NAME]-web

    If your application uses Liferay Service Builder, you'll need to run the
    following Blade Tools command instead. This command generates the service
    implementation and service API modules along with the aforementioned web
    client module:

        blade create -t servicebuilder -p [ROOT_PACKAGE] [APPLICATION_NAME]

    If you used the `blade create servicebuilder` command to generate
    implementation and API modules, ignore the `*-service` and `*-api` folders
    for now; you'll learn about them later in this tutorial.

3.  Replace the `/src/main/java/[APPLICATION_NAME]-web` folder with your root
    package. For instance, if your application's root package name is
    `com.liferay.tasks.web`, your class's directory should be
    `/src/main/java/com/liferay/tasks/web`. Also, remove the `init.jsp` and
    `view.jsp` files located in the `src/main/resources/META-INF/resources`
    folder. You'll insert your legacy application's Java code and JSPs, so the
    generated default code is not necessary.


4.  Verify that your current directory structure for your application's `*-web`
    module matches the structure listed below:

    - `tasks`
        - `tasks-web`
            - `src`
                - `main`
                    - `java`
                        - `[ROOT_PACKAGE]`
                    - `resources`
                        - `content`
                            - `Language.properties`
                        - `META-INF`
                            - `resources`
            - `bnd.bnd`
            - `build.gradle`

    The instructions in the rest of this sub-section only affect your
    application's web client module.

5.  Open the `bnd.bnd` file. This is used to generate your module's
    `MANIFEST.MF` file that is generated when you build your project. Edit your
    module's `bnd.bnd` file to fit your application. For more information about
    `bnd.bnd`, visit
    [http://www.aqute.biz/Bnd/Bnd](http://www.aqute.biz/Bnd/Bnd). You can view
    the `dictionary-web` module's
    [`bnd.bnd`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/dictionary/dictionary-web/bnd.bnd)
    for a simple example, or the `journal-web` module's
    [`bnd.bnd`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/journal/journal-web/bnd.bnd)
    for a more advanced example.

6.  Open the `build.gradle` file. This is used to specify all your module's
    dependencies. The `build.gradle` file that was generated for you is
    pre-populated with content and default dependencies related to OSGi and
    Liferay Portal. In the `dependencies {...}` block, you need to add the web
    client module's dependencies. When deploying your module into the OSGi
    container, OSGi checks if the dependencies are available in the container.
    If the dependencies are not available in the container, your module will be
    unavailable. Therefore, your dependencies are not bundled with your module.
    Instead, they're available from Liferay's OSGi container.

7.  Copy your legacy application's JSP files into the
    `/src/main/resources/META-INF/resources` directory. In most cases, all of
    your application's JSP files should reside in the web client module.

8.  Your next task is to add your portlet classes, non-service classes, and
    non-implementation classes into your client module. Copy your portlet
    classes into their respective directories and ensure their package names
    within the class are specified correctly. Your client module can hold one
    class or many classes, depending on how large your application is. It's a
    good practice to organize your classes into sub-packages of the main
    package, to easily manage your Java classes. You can view the
    [journal-web](https://github.com/liferay/liferay-portal/tree/master/modules/apps/journal/journal-web/src/main/java/com/liferay/journal/web)
    module for an example of a client module holding many different Java
    classes.

    +$$$

    **Note:** Many applications have service and API classes. These classes
    need to live in separate implementation and API modules. You'll learn more
    about creating these later in this tutorial.

    $$$

9.  Now that you have the necessary classes in your client module, you need to
    edit these classes to be compliant with OSGi. First, you need to choose a
    component framework to work with. Using a component framework lets you
    easily harness the power of OSGi. Liferay uses the
    [Declarative Services](http://wiki.osgi.org/wiki/Declarative_Services)
    component framework and recommends that Liferay developers use it too. This
    tutorial assumes that you're using Declarative Services. You can, however,
    use any other OSGi component framework in Liferay.

    Review your legacy application's XML files and migrate the configuration
    and metadata information to the portlet class as properties. You can do
    this by adding the `@Component` annotation to your portlet class and adding
    the necessary properties to that annotation. The end result should look
    similar to the following example:

        @Component(
            immediate = true,
            property = {
                "com.liferay.portlet.display-category=category.sample",
                "com.liferay.portlet.icon=/icon.png",
                "javax.portlet.name=1",
                "javax.portlet.display-name=Tasks Portlet",
                "javax.portlet.security-role-ref=administrator,guest,power-user",
                "javax.portlet.init-param.clear-request-parameters=true",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.expiration-cache=0",
                "javax.portlet.supports.mime-type=text/html",
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.info.title=Tasks Portlet",
                "javax.portlet.info.short-title=Tasks",
                "javax.portlet.info.keywords=Tasks",
            },
            service = Portlet.class
        )
        public class TasksPortlet extends MVCPortlet {

10.  Convert all references of the `portletId` (e.g., `58_INSTANCE_4gtH`) to the
    class name of the portlet, replacing all periods with underscores (e.g.,
    `com_liferay_web_proxy_portlet_WebProxyPortlet`).

11.  If your legacy application has resource actions, you'll need to migrate
    those into your client module. Create the
    `/src/main/resources/resource-actions/default.xml` file, and copy your
    resource actions there. Make sure to create the `src/portlet.properties`
    file and add the following property:

        resource.actions.configs=resource-actions/default.xml

    As an example, you can view the Directory application's
    [`default.xml`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/directory/directory-web/src/main/resources/resource-actions/default.xml)
    file.

12.  Add any language keys that your application uses to the
    `src/main/resources/content/Language.properties` file. You should only
    include the language keys that are unique to your application. Your
    application will use the default language keys in Liferay when it is
    deployed.

Awesome! You've successfully created your application's web client module! If
you'd like to learn more about client modules, and how to create one from
scratch, visit the
[Creating a Client Module](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-components#creating-a-client-module)
section.
<!-- The Creating a Client Module link above refers to instructions on creating generic OSGi client modules. Once the Creating a Service Builder application tutorial has been written, the link above should be replaced to the section in the Creating a Service Builder application tutorial on creating a web client module. -Jesse -->

The table below serves as a quick reference guide. It summarizes the migration
process for many of your legacy application's directories, packages, and files.
This is a sample table for a fictitious *tasks* applications.

| Legacy Package | Module Package |
|----------------|----------------|
| `tasks-portlet/docroot/WEB-INF/src/com.liferay.tasks.asset` | `tasks-web/src/main/java/com.liferay.tasks.asset` |
| `tasks-portlet/docroot/WEB-INF/src/com.liferay.tasks.portlet` | `tasks-web/src/main/java/com.liferay.tasks.portlet` |
| `tasks-portlet/docroot/WEB-INF/src/content` | `tasks-web/src/main/resources/content` |
| `tasks-portlet/docroot/WEB-INF/src/resource-actions` | `tasks-web/src/main/resources/resource-actions` |
| `tasks-portlet/docroot/WEB-INF/src/portlet.properties` | `tasks-web/src/main/resources/portlet.properties` |
| `tasks-portlet/docroot/init.jsp` | `tasks-web/src/main/resources/META-INF/resources/init.jsp` |
| `tasks-portlet/docroot/tasks` | `tasks-web/src/main/resources/META-INF/resources/tasks` |
| `tasks-portlet/docroot/upcoming_tasks` | `tasks-web/src/main/resources/META-INF/resources/upcoming_tasks` |

Many applications only have a web client module. Larger, more complex
applications, such as Liferay Service Builder applications, require additional
modules to hold their service API and service implementation logic. You'll
learn how to create these modules next.

## Converting Your Legacy Application's Service Builder API and Implementation [](id=converting-your-legacy-applications-api-and-service-builder-implementation)

In this section, you'll learn about converting a legacy Service Builder
application to a Liferay 7 style application. In the previous section, you
learned how to generate your implementation and API modules. If you haven't yet
run the `servicebuilder` blade command outlined in step 2 of the previous
section, run it now. The API module holds your application's Service Builder
generated API and the implementation module holds your application's Service
Builder implementation.

Before you begin editing the API and implementation modules, you'll need to
configure your root project (e.g., `tasks`) to recognize the multiple modules
residing there. A multi-module Gradle project must have a `settings.gradle`
file in the root project for building purposes. Luckily, when you generated
your project using blade, the `settings.gradle` file was inserted and
pre-configured for your application's modules.

Your root project directory should now be in good shape. Next, you'll learn how
to use Service Builder to generate your application's service API and service
implementation code.

1.  Copy your legacy application's `service.xml` file and paste it into the
    implementation module's root directory (e.g., `tasks/tasks-service`).

2.  Blade generated a `bnd.bnd` file for your service implementation module.
    Make sure to edit this `bnd.bnd` file to fit your application. For examples
    of service implementation module BND files, see the `export-import-service`
    module's
    [`bnd.bnd`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/export-import/export-import-service/bnd.bnd)
    and the `wiki-service` module's
    [`bnd.bnd`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/wiki/wiki-service/bnd.bnd).

3.  Blade Tools also generated your service implementation module's
    `build.gradle` file. In this file, Service Builder is already configured to
    generate code both in this module and in your service API module. When you
    run Service Builder, Java classes, interfaces, and related files will be
    generated in your `*api` and `*service` modules. Open your service
    implementation module's `build.gradle` file to view the default
    configuration.

    As you've learned already, you don't have to accept the generated build
    files' defaults. Blade Tools simply generated some standard OSGi and
    Liferay configurations. Notice that Service Builder is already available
    for you by default. The Service Builder dependency was added as follows:

        dependencies {
            classpath group: "com.liferay", name: "com.liferay.gradle.plugins.service.builder", version: "1.0.5"
        }

    Also, the Service Builder plugin is applied as follows:

        apply plugin: "com.liferay.portal.tools.service.builder"

    With the Service Builder plugin already available, you don't have to worry
    about configuring it in your project.

5.  Another important part of your service implementation module's
    `build.gradle` file is the `buildService{...}` block. This block configures
    how Service Builder runs for your project. The current configuration will
    generate your API module successfully, but extra configuration might be
    necessary in certain cases.

6.  Open a terminal and navigate to your root project folder. Then run `gradle
    buildService`.

    Your `service.xml` file's configuration is used to generate your
    application's service API and service implementation classes in their
    respective modules. You've also generated other custom files (related to
    SQL, Hibernate, Spring, etc.), depending on your `buildService {...)`
    block's configuration.

7.  Now that you've run Service Builder, continue copying custom classes into
    your implementation module. The table below highlights popular legacy
    classes and packages and where they should be placed in your application.
    This table is intended to aid in the organization of your classes and
    configuration files; however, remember to follow the organizational
    methodologies that make the most sense for your application. One size does
    not fit all with your modules' directory schemes.

    | Legacy Package | Module Package |
    |----------------|----------------|
    | `tasks-portlet/docroot/WEB-INF/src/com.liferay.tasks.model.impl` | `tasks-service/src/main/java/com.liferay.tasks.model.impl` | 
    | `tasks-portlet/docroot/WEB-INF/src/com.liferay.tasks.service.impl` | `tasks-service/src/main/java/com.liferay.tasks.service.impl` |
    | `tasks-portlet/docroot/WEB-INF/src/com.liferay.tasks.service.permission` | `tasks-service/src/main/java/com.liferay.tasks.service.permission` |
    | `tasks-portlet/docroot/WEB-INF/src/com.liferay.tasks.service.persistence.impl` | `tasks-service/src/main/java/com.liferay.tasks.service.persistence.impl` |
    | `tasks-portlet/docroot/WEB-INF/src/com.liferay.tasks.social` | `tasks-service/src/main/java/com.liferay.tasks.social` |
    | `tasks-portlet/docroot/WEB-INF/src/com.liferay.tasks.util` | `tasks-service/src/main/java/com.liferay.tasks.util` |
    | `tasks-portlet/docroot/WEB-INF/src/custom-sql` | `tasks-service/src/main/resources/META-INF/custom-sql` |

8.  Once you've copied all of your custom classes over, run `gradle
    buildService` again to generate the remaining services.

Now that your services are generated, you'll need to wire up your modules so
they can reference each other when deployed to Liferay's OSGi container. Blade
Tools has already completed this task. It assumes that the client module
depends on the service implementation and service API modules and that the
service implementation module depends on the service API module. For example,
if you open your web client module's `build.gradle` file, you'll spot similar
dependencies to these:

    dependencies {
        compile  project(':tasks-api')
        compile  project(':tasks-service')
    }

Excellent! You've successfully generated your application's services using
Service Builder. They now reside in modules, and can be deployed to Liferay 7.0.
If you'd like to learn more information about creating implementation and API
modules from scratch, visit the
[Creating an Implementation Module](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-components#creating-an-implementation-module)
and
[Creating an API Module](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-components#creating-an-api-module),
respectively.
<!-- The links above refer to instructions on creating generic OSGi implementation and API modules. Once the Creating a Service Builder application tutorial has been written, the links above should be replaced to the sections in the Creating a Service Builder application tutorial on creating service implementation and service API modules. -Jesse -->

## Building Your Module JARs for Deployment [](id=building-your-module-jars-for-deployment)

Now it's time to build your modules and deploy them to your Liferay 7.0
instance. To build your project, run `gradle build` from your project's root
directory.

+$$$

**Note:** At the time of this writing, there are some compile errors you'll
receive when running `gradlew build`. These compilation errors are caused from
the `portal-service-7.0.0-SNAPSHOT` JAR being out-of-date. To work around this
issue, build [liferay-portal](https://github.com/liferay/liferay-portal) from
source (i.e., run `ant all` in the `liferay-portal` root directory) and then
copy the `liferay-portal/portal-service/portal-service.jar` file to your
project's `lib` folder (e.g., `tasks/lib`). You must create this `lib` folder.
Then replace the following line in all your module's `build.gradle` files:

    compile 'com.liferay.portal:portal-service:7.0.0-SNAPSHOT'

with this one:

    compile files('../lib/portal-service.jar')

Also, in your API module's `build.gradle` file, you'll need to add the following
line within your `dependencies {}` block:

    compile 'com.liferay:com.liferay.osgi.util:2.0.4'

$$$

Once your project successfully builds, check all of your modules' `/build/libs`
directories. There should be a newly generated JAR file in each, which is the
file you'll need to deploy to Liferay. You can deploy each JAR by running `blade
deploy` from each module's root directory.

+$$$

**Note:** If you deploy your modules out of order, you might receive error
messages. For instance, if you try deploying your web client module first,
you'll receive errors if it relies on the service implementation and service
API modules. Once each module's dependencies are met, they will successfully be
deployed for use in Liferay. For more information on checking each module's
dependencies, see the
[Using the Felix Gogo Shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell)
article.

$$$

Once you've successfully deployed your modules, you can list them from the Gogo
shell as shown below.

![Figure 1: Once you've connected to your Liferay instance in your Gogo shell prompt, run *lb* to list your new converted modules.](../../images/deploy-converted-modules.png)

This tutorial explained how to convert your legacy application into the modular
format of a Liferay 7 style applicaton. You first created a web client
(`*-web`) module that holds your application's portlet classes and UI. Then you
learned how to create a service implementation module and a service API module.
Your learned how to run Service Builder to generate code for your application's
service and API modules. Next, you learned how to wire your modules together by
declaring their dependencies on each another. Lastly, you built your modules
and deployed them to your Liferay instance. Great job!

## Related Topics [](id=related-topics)

[Creating Liferay Applications](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-applications)

[Creating Liferay Components](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-components)

[Consuming Liferay Services](/develop/tutorials/-/knowledge_base/7-0/consuming-liferay-services)
