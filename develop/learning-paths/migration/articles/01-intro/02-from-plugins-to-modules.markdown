# From Plugins to Modules

To implement modularity, we've adopted the OSGi technology and module standards.
OSGi stands for the Open-source Gateway Initiative, which is an organization of
affiliated experts in software modularity. They've come up with the industry
standard processes, technologies, and specifications for building dynamic
component systems in Java. By using OSGi, we've turned the corner from using
traditional WAR style plugins to defining rich self describing OSGi modules. 

To engage with the outside world, each module has a well defined contract
explaining its purpose, what it depends on, and what it provides. You can use
the modules in concert to produce rich complex systems. Consider your car, for
example. It's composed of all kinds of subsystems: an engine, transmission,
suspension, computers, and each subsystem's components, such as an engine's
pistons and gaskets, a transmission's gears, and the suspension's struts. The
car's subsystems and their parts can be developed and tested separately. Each of
their manufacturers concentrates on delivering well their specific parts
(modules). The same principles apply to software. We can construct powerful
sophisticated software systems quickly by connecting and combining well-defined
well-tested modules.

Modules are plugins that are deployed to Liferay's Module Framework. A module
contains software that can consist of components: small well-defined objects
that perform specific functionality. A module can also contain resources, such
as JSPs, text files, templates, image files, and more. 

### Liferay's Module Framework

<!--
Liferay has adopted the OSGi module standard. It's an open source industry
standard for modularity, developed and maintained by modularity
experts from various technology companies, including Liferay, as part of the
OSGi Alliance. Projects such as Eclipse, IntelliJ, JBoss, and more use OSGi. You
can read more about modularity and OSGi in our [introductory tutorials](/develop/tutorials/-/knowledge_base/7-0/osgi-and-modularity).
We'll focus on some key aspects of the Module Framework here. 
-->

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

## Liferay 7 Modules

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
modules are the preferred structure for new Liferay 7 plugins. 

TODO continue to talk about the compatibility layer. Jim

## Understanding Dependencies

<!--
A key ingredient to Liferay 7's success is the *glue* we used to connect the
modules. As with a car's parts, everything is connected in a well defined
manner. All of the parts are built to specification and can be connected based
on compatiblity. We use Semantic Versioning to fit the modules together.
-->

TODO more to come.

<!--
Next, let's consider approaches to getting your legacy plugins running on
Liferay 7. 
-->