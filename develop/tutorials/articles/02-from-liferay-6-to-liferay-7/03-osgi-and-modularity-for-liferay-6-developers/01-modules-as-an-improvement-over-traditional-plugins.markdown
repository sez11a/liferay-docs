# Modules as an Improvement over Traditional Plugins [](id=modules-as-an-improvement-over-traditional-plugins)

In Liferay 7, you can develop applications using OSGi modules or using
traditional Liferay plugins (WAR-style portlets, hooks, EXT, and web
applications). Liferay's Plugin Compatibility Layer (explained later) makes it
possible to deploy traditional plugins to the OSGi runtime framework. To gain
from all Liferay 7 and OSGi offer, however, you should use OSGi modules.

Here are some important things you gain from using modules:

- **Better Encapsulation** - The only classes a module exposes publicly are
those it exports explicitly. This lets the developer define internal public
classes transparent to external clients.

- **Dependencies by Package** - Dependencies are specified by Java package, not
by JAR file. In traditional plugins, developers had to add *all* of a JAR file's
classes to the classpath in order to use *any* of its classes. With OSGi,
developers need only import packages of the classes they need. Only the classes
in those packages are added to the module's class path.

- **Lightweight** - A module can be as small as the developer wants it to be. In
contrast to a traditional plugin, which may require several descriptor files, a
module requires only a single descriptor file--a standard JAR manifest. Also,
traditional plugins are typically larger than modules and are deployed on app
server startup, which can slow down that process considerably. Modules deploy
more quickly and require minimal overhead cost.

- **Easier to Reuse** - OSGi modules lend themselves well to developing small,
highly cohesive chunks of code. They can be combined to create applications that
are easier to test and maintain. Modules can be distributed publicly (e.g., on
Maven Central) or privately. And since modules are versioned, developers can
specify precisely the modules they want to use.

- **In-Context Descriptors** - Where plugins use descriptor files (e.g.,
`web.xml`, `portlet.xml`, etc.) to describe classes, module classes uses OSGi
annotations to describe themselves. For example, a module portlet class can use
[OSGi Service annotation
properties](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/portlet-descriptor-to-osgi-service-property-map)
to specify its name, display name, resource bundle, public render parameters,
and much more. Instead of specifying that information in descriptor files
separate from the code, they're specified in context in the code.

These are just a few ways modules outshine traditional plugins. Note, however,
developers experienced with Liferay plugins have the best of both worlds.
Liferay 7 supports traditional plugins *and* modules. Existing Liferay
developers can find comfort in the simplicity of modules and their similarities
with plugins.
Here are some fundamental characterics modules share with plugins:

- Developers use them to create applications (portlets for Liferay)

- They're zipped up packages of classes and resources

- They're packaged as a standard Java JARs

Now that we've compared and contrasted modules with plugins, it's time to take a
tour of the module structure. 

## Module Structure: A JAR File with a Manifest [](id=module-structure-a-jar-file-with-a-manifest)

A module's structure is extremely simple. It has one mandatory file:
`META-INF/MANIFEST.MF`. Developers add code and resources to the module and
organize them as desired. 

Here's the essential structure of a module JAR file:

    |
    |- [Module's files]
    |_ META-INF
        |_ MANIFEST.MF

The `MANIFEST.MF` file describes the module to the system. The manifest's [OSGi headers](https://www.osgi.org/bundle-headers-reference/)
identify the module and its relationship to other modules. 

Here are some of the most commonly used headers:

- `Bundle-Name`: User friendly name of the module.

- `Bundle-SymbolicName`: Globally unique identifier for the module. Java package
conventions (e.g., `com.liferay.journal.api`) are commonly used.

- `Bundle-Version`: Version of the module.

- `Export-Package`: Packages from this module to make accessible to other
modules.

- `Import-Package`: Packages this module requires that other modules provide.

Additional headers can be used to specify more characteristics, such as how the
module was built, development tools used, etc. 

As an example, here are some headers from the Liferay Journal Web module manifest:

    Manifest-Version: 1.0
    Bundle-ManifestVersion: 2
    Bundle-Name: Liferay Journal Web
    Bundle-SymbolicName: com.liferay.journal.web
    Bundle-Vendor: Liferay, Inc.
    Bundle-Version: 1.1.2
    Export-Package:\
        com.liferay.journal.web.asset,\
        com.liferay.dynamic.data.mapping.util,\
        com.liferay.journal.model,
        com.liferay.journal.service,com.liferay.journal.util, [..]
    Import-Package:\
        aQute.bnd.annotation.metatype,\
        com.liferay.announcements.kernel.model,
        com.liferay.application.list,\
        com.liferay.asset.kernel,\
        com.liferay.asset.kernel.exception, [..]

Note: to remove unnecessary "noise" from this example, some headers have been
abbreviated (`[...]`) and some have been removed.

As for a module's Java code and resources, you can organize them and build them
however you like. You're free to use any directory structure conventions, such
as those used in Maven or by your development team. And you can use any build
tool, such as Gradle or Maven, to manage dependencies.

Liferay Workspace is an environment for managing module projects (and theme
projects). It provides Gradle build scripts for developing on Liferay. It can be
used from the command line or from within Liferay IDE and Developer Studio. Note
also, these IDE/Studio provide plugins for Gradle, Maven, and BndTools. Tooling
details are covered later in this series.

Now that you're familiar with the module structure and manifest, it's time to
explore how to build modules.

## Building Modules with Bnd [](id=building-modules-with-bnd)

The most common way to build modules is with a little tool called [Bnd](http://bnd.bndtools.org/).
It's an engine that, among other things, simplifies generating manifest
metadata. Instead of manually creating a `MANIFEST.MF` file, developers use Bnd
to generate it. Bnd can be used on its own or along with other build tools, such
as Gradle or Maven. Liferay Workspace uses Gradle and Bnd together.

One of Bnd's best features is that it automatically transverses a module's code,
to identify external classes the module uses and add them to the manifest's list
of packages to import. Bnd also provides several OSGi-specific operations that
simplify module development.

Bnd generates the manifest based on a file called `bnd.bnd` in the project root.
This file's header list is similar to (but shorter than) that of the
`MANIFEST.MF`. Compare the Liferay Journal Web module's `bnd.bnd` file content
(simplified a bit) below to its `MANIFEST.MF` file content that was listed
earlier:

    Bundle-Name: Liferay Journal Web
    Bundle-SymbolicName: com.liferay.journal.web
    Bundle-Version: 1.1.2
    Export-Package:\
        com.liferay.journal.web.asset,\
        com.liferay.journal.web.dynamic.data.mapping.util,\
        com.liferay.journal.web.social,\
        com.liferay.journal.web.util

The main difference is that the `bnd.bnd` file doesn't specify an
`Import-Package` header. It's unnecessary because Bnd generates it in the
`MANIFEST.MF` file automatically. It's metadata made easy!

Bnd plugins are available to use with Gradle and Maven. And since Liferay
Workspace includes Bnd, developers can use Bnd from the command line and from
Liferay IDE / Developer Studio.

Now that you're familiar with Bnd and the `Export-Package` and `Import-Package`
manifest headers, let's explore how leverage dependencies using them.
