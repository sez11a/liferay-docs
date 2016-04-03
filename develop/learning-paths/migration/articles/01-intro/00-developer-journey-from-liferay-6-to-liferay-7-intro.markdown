# Developer Journey from Liferay 6 to Liferay 7

When you were looking for Liferay 7 migration documentation, were you expecting
a title such as *Migrating to Liferay 7* or *12 Easy Steps for App Migration*?
Whatever title you were expecting, I bet it didn't have the word "journey" in
it. You might be wondering whether you took a wrong turn. Or perhaps we're using
*journey* as a euphemism for the long and arduous effort required to get Liferay
6 plugins running on Liferay 7. Would we really do that? No; we're not
sugarcoating anything. Migrating to Liferay 7 is no easy task and it will take
considerable effort. But the journey's worth it. We'll explain.

Liferay 7 has much to offer you as a software developer, integrator, and
maintainer. In fact, we had you in mind as we transformed Liferay's architecture
and applications into a robust, flexible, and dynamic ecosystem. That's almost
starting to sound exciting, right--like a *journey*.

At this point, you've decided to upgrade to Liferay 7 or you're still deciding.
Wherever you're at in your decision process, you want to understand Liferay 7
and what's involved with migrating your plugins. What are the architectural
differences between Liferay 6 and 7? What new concepts are involved with
developing and maintaining plugins? And what were Liferay's reasons for all the
changes?

We'll start by highlighting some of the key issues that drove us to make
considerable changes in Liferay 7 and the reasons for resolving the issues the
way we did. Let's start by going over a few issues with the Liferay Platform
prior to Liferay 7.

It's often desirable for different applications to use different versions of a
dependency. For example, you want to keep version 1.1 of an app running for some
of your applications to use and also run version 1.2 of the app for your other
application to use. Prior to Liferay 7, you couldn't do this. Now you can.

Liferay 7 lets you specify in each application the precise version of software
on which it depends. And it let's you use different versions of that software
for other applications.

Another issue we overcame was the size of the Liferay platform. Sure it had
plenty of useful powerful applications, but you had to take all of them. This
was due in part to tight coupling between Liferay applications. So we sought a
way to maximize loose coupling between the apps, by adopting modularity and
implementing a framework to support it. We wanted to refine the platform's
essentials to what we now call the Liferay Community Edition Core (CE Core).
Now, you just run CE Core and need only keep Liferay applications you want.

Adding and removing applications brought up another issue, or key need. We
wanted a dynamic system that would allow modules to come and go, safely and
easily. For example, if a dependency went down, we wanted the system to be savvy
enough to swap in another available module to satisfy the dependency. We
developed this capability into Liferay's Module Framework.

## Liferay's Module Framework

Liferay has adopted the OSGi module standard. It's an open source industry
standard for modularity, developed and maintained by modularity
experts from various technology companies, including Liferay, as part of the
OSGi Alliance. Projects such as Eclipse, IntelliJ, JBoss, and more use OSGi. You
can read more about modularity and OSGi in our [introductory tutorials](/develop/tutorials/-/knowledge_base/7-0/osgi-and-modularity).
We'll focus on some key aspects of the Module Framework here. 

Liferay's Module Framework leverages OSGi to deploy plugins as modules to the
Liferay Platform. The modules use Semantic Versioning to uniquely distinguish
themselves in the system. Not only does a module distinguish itself from other
modules, but it also distinguishes itself from different versions of itself.
Each version of each module uniquely registers with the Module Framework. This
enables applications in Liferay to use different versions of a named module. 

Modules are the single plugin type used in Liferay 7. But Liferay 7 is remains
compliant with the JSR-286 portal standards. In Liferay 7, even though OSGi
modules are packaged as Web Application Bundles (WABs), you can still deploy
legacy WAR-style plugins. Liferay's Module Framework converts the plugin WAR to
a WAB. The generated WAB is a `.jar` file that essentially consists of the WAR
file's contents and an OSGi bundle manifest--more on manifests later.

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

<!-- new -->

## Services and Components

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

<!-- Skipping Tooling for now. -->

