# Developer Journey from Liferay 6 to Liferay 7

You have been using Liferay as a developers for some time now; you have read
documentations and blogs and maybe even gone through some training. More
importantly, you have already developed some portlets and extensions. And now
you hear about how all the changes in Liferay 7 and you wonder: will I have to
relearn everything from scratch? Will I have to recode all existing
developments? Do I need to learn new tools?

This learning path will answer all these questions and more, showing all the key
differences and similarities between Liferay 7 and previous versions from a
developer experience. You will understand new concepts, learn how to make
minimal changes to make existing applications work and leverage the new
possibilities to achieve much more than what was possible until now. After
reading this, you will be able to call yourself a LIferay 7 developer.

## From a Monolith to a Modular Architecture

<!--
What is a monolith? How was Liferay previously a monolith?

What were the advantages of being a monolith?

What were the defficiencies?

What is a modular architecture? Why did we switch?

Describe our new modular architecture.

-->

<!--
Jorge's blog - Modularization
https://www.liferay.com/web/jorge.ferrer/blog/-/blogs/liferay-7-milestone-5-get-involved-while-itâ€™s-hot

Liferay 6 is a monolith
- It's a single web application
- All running in same JVM, right?
- It and it's built-in apps had to be released together
-->


## Core, Apps, and Suites

- Content coming soon.

## From plugins to modules

- (Include the Compatibility Layer)

## Liferay's Module Framework

Liferay has adopted the OSGi module standard. It's an open source industry
standard for modularity, developed and maintained by modularity
experts from various technology companies, including Liferay, as part of the
OSGi Alliance. Projects such as Eclipse, IntelliJ, JBoss, and more use OSGi. You
can read more about modularity and OSGi in our [introductory tutorials](/develop/tutorials/-/knowledge_base/7-0/osgi-and-modularity).
We'll focus on some key aspects of the Module Framework here. 

Liferay's Module Framework leverages OSGi to deploy plugins as modules to the
Liferay Platform. Not only does a module distinguish itself from other
modules, but it also distinguishes itself from different versions of itself.
Each version of each module uniquely registers with the Module Framework. This
enables applications in Liferay to use different versions of a named module. 

Modules are the single plugin type used in Liferay 7. In Liferay 7, even though
OSGi modules are packaged as Web Application Bundles (WABs), you can still
deploy legacy WAR-style plugins. Liferay's Module Framework converts the plugin
WAR to a WAB. The generated WAB is a `.jar` file that essentially consists of
the WAR file's contents and an OSGi bundle manifest--more on manifests later.

![Figure x: You deploy plugins via Liferay's Module Framework.](../../images/app-server-module-framework.png)

The Module Framework is a highly dynamic, flexible environment. Modules can be
installed anytime. At runtime, they can be upgraded, fixed, or improved. And
modules can be removed or stopped at will. If a module's dependency stops, the
module can request from the Module Framework a replacement for that dependency.
If an alternative module exists that satisfies the dependency, the Module
Framework connects the dependant module to it.

So far, we've gone over key issues that Liferay 7 resolves via the Module
Framework. Next we'll consider what a module is and how it differs from a
Liferay 6 plugin.

## Plugins vs. Modules

Modules are plugins that are deployed to Liferay's Module Framework. A module
contains software that can consist of components: small well-defined objects
that perform specific functionality. A module can also contain resources, such
as JSPs, text files, templates, image files, and more. 

A module's essentials are one or more Java classes and a `MANIFEST.MF` file. The
manifest describes the module, and at a minimum must specify the module's name
and version. The module's manifest file specifies Java packages it needs from
other modules. It can also expose packages to share with other modules and
explicitly hide packages from other modules. Modules can describe themselves
completely via their manifest files. Manually specifying a`MANIFEST.MF` file,
however, can be very tedious work. So we made sure there were tools available to
simplify specifying them. 

To facilitate creating and managing manifests, we use the tool
[*bnd*](http://www.aqute.biz/Bnd/Bnd). It's a very useful OSGi development tool.
It's optional, but we recommend using it to generate and update your
manifest files. As a developer, you specify the manifest in a `bnd.bnd` file,
using headers that use a simpler [format](http://www.aqute.biz/Bnd/Format).
We'll specify `bnd.bnd` files later on and you'll see plenty of them in the
Liferay 7 tutorials.

A module can be developed in any environment; you can use Eclipse, IntelliJ,
Netbeans, leverage powerful graphical plugins like Liferay IDE and BndTools, or
use command line tools like Blade CLI. And you're free to use build frameworks
such as Gradle, Maven, Ivy, and Ant. It's up to you. And you're not locked in to
any particular project structure. You can manage your project as you like. A
module is a one-size fits all deployment structure, but allows for flexible
project structure.

There are some important differences worth noting between Liferay 6 plugins and
Liferay 7 modules. Liferay 6 plugins were tightly tied to Liferay's Plugins SDK.
In certain development environments, they were difficult to set up and work
with. Liferay 6 plugins also required lots of extensive XML files, such as a
portlet's `portlet.xml` and `liferay-portlet.xml` files. Liferay 7 modules use
annotations in place of the XML files. This information is specified much more
simply and is captured in the context in the Java files themselves.

As was stated previously, Liferay 7 continues to support WAR-style plugins, but
modules are the preferred structure for new Liferay 7 plugins. An important part
of modularity is the use components and services. They're explained next. 

## Understanding dependencies 

Content coming soon.

## From the Plugins SDK to Gradle and Maven

Content coming soon.

## Portlets in Liferay 7 

Content coming soon.

## A New Extension Model: Services and Components

Components are the building blocks of a module. Each component is a Java class
that focuses on a specific piece of functionality. Services specify the module's
interfaces and components implement the interfaces. Modules publish the services as contracts in the
Module Framework. The Module Framework puts service consumers in touch with service providers.

Note that these services are OSGi services. They provide functionality based on
the interfaces they define. OSGi services aren't to be confused with web
services and/or services generated using Liferay's Service Builder. A services
is a Java interface specified by a component. 

The Module Framework's Service Registry manages service interactions between
components and modules. A module can request a service from the Service Registry, which in turn adds the
service from a module that provides it. 

Components can define service interfaces and components can implement them. We use [Declarative Service](http://wiki.osgi.org/wiki/Declarative_Services)
annotations to designate components, and define them as service providers and
service consumers. All components use the Declarative Services annotation
`@Component`. Components that provide a services specify those services in the
`@Component` annotation by means of a `service` attribute. For example,
`MyServiceProvider` class below describes itself as implementing the
`SomeService.class` service.

    import org.osgi.service.component.annotations.Component;
    
    import com.liferay.documentation.SomeService;

    @Component(
        service = {SomeService.class}
    )
    public class MyServiceProvider extends SomeService { 
        ...
    }

A class that uses a service is a service consumer. It declares a service bean of
the service class type and specifies an `@Reference` annotation for the bean's
setter method. 

    import org.osgi.service.component.annotations.Reference;

    import com.liferay.documentation.SomeService;

    public class MyServiceConsumer {

        private SomeService _someService;

        public Greeting getGreeting() {
            return _someService;
        }

        @Reference
        public void setGreeting(SomeService someService) {
            _someService = someService;
        }

        // Add methods that use the service component ...

    }

The `MyServiceConsumer` class above declares a bean for the class `SomeService`.
The `@Reference` annotation of the bean's setter method informs the Module
Framework to find a provider for that service and instantiate the bean with it. 

In summary, the service provider uses a `service` attribute in its `@Component`
annotation to specify the service it provides, the service consumer marks itself
with an `@Reference` annotation for the bean it needs set, and the Module
Framework connects both parties. Simple enough, right?

Services and components are the key ingredients to modularity in Liferay 7.
We'll go into more details and examples of components and services throughout
this Learning Path. Note, you can also refer to tutorial
[Creating Liferay Components](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-components).

Next, let's consider approaches to getting your legacy plugins running on
Liferay 7.

## From the Plugins SDK to Gradle or Maven

Prior to Liferay 7, the Plugins SDK was the recommended plugin development
infrastructure. The Plugins SDK was fine for it's time; but do to it's
limitations and the traction of Gradle and Maven, it's no longer the preferred
tool for developing and maintaining Liferay 7 plugins.

As background, the Plugins SDK is a proprietary tool. It creates and assumes a
proprietary project directory structure. In addition, the Plugins SDK's build
scripts are not intended to be tampered with. If you modify the SDK, there's no
guarantee that upgrades to the it will work properly, as they may conflict with
or overwrite your modifications. The SDK didn't integrate with a developer's
other projects.

In place of the Plugins SDK, Liferay 7 welcomes Gradle or Maven. These build
environments use widely-adopted directory structures and tasks/targets. Gradle
and Maven are the defacto project development environments. As a developer,
chances are you're familiar with one or both of them. Developing Liferay 7
plugins with them will be natural.

Gradle and Maven projects are easy to mix in with your existing project scripts.
As you develop Liferay 7 plugins, you can leverage powerful third-party Gradle
or Maven plugins for such things as unit testing, code coverage, source code
testing, and more.

Gradle is the preferred build environment for Liferay 7, but you can
alternatively use Maven. And note, we still support development with the Plugins
SDK, for the time being. But you should strongly consider using Gradle or Maven.
We've even developed a tool called Liferay Workspaces to help you create and
manage Gradle based plugin projects (more on this later).  


## A new configuration paradigm

- From portlet preferences to Configuration API

- From properties files to Configuration API

## Migrating existing code

- A portlet 

- An extension point implementation 

- A servlet

## Designing a modular application

- Modular best practices 

- Portlets as components 

