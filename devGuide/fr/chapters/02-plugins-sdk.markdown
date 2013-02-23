# The Plugins SDK [](id=the-plugins-s-3)

Java developers have a wide variety of tools and development environments.
Liferay makes every effort to remain tool agnostic, so that you can choose the
tools that works best for you. For that reason, we provide a Plugins Software
Development Kit (SDK) which is based on Apache Ant and may be used along with
any editor or Integrated Development Environment (IDE). The chapters of this
guide will use the Plugins SDK and a text editor, but you may use whatever tool
you are comfortable with. In a later chapter we also introduce Liferay IDE, a
plugin for eclipse that simplifies development for Liferay.

---

 ![tip](../../images/tip-pen-paper.png)**Tip**: If you are an Eclipse user and
 prefer to start working with it from be very beginning, you can read that
 chapter first before reading the rest of the guide.

---

We discuss the following topics in this chapter:

- *Initial Setup:* How to install the Plugins SDK and configure it for your use
with your Liferay Portal server.

- *Structure of the SDK:* Where to develop your plugin within the SDK and what
Apache Ant targets are available.

- *Best Practices:* Suggests helpful ways for organizing your plugin projects.

Setting up the Plugins SDK is easy. Let's get to it.

## Installing the SDK [](id=installing-the-sdk)

Setting up your environment for Liferay development is straightforward. First,
you will need Liferay Portal. If you have yet to install Liferay with an
application server, follow instructions in the
[Installation and Setup](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/installing-lifer-1)
chapter of *Using Liferay Portal 6.1*. Many people use the Tomcat application server
for development, as it is small, fast, and takes up less resources than most
other servlet containers. Although you can use any application server supported
by Liferay Portal for development, our examples use Tomcat.

---

 ![note](../../images/tip-pen-paper.png)**Note:** In Liferay Developer Studio,
 the SDK is already installed and ready to use. Liferay Portal Enterprise
 Edition (EE) comes with Liferay Developer Studio and much more (see [CE vs
 EE](http://www.liferay.com/downloads/liferay-portal/overview)). Download a
 [free trial](http://www.liferay.com/c/portal/register_trial_license?redirect=/products/liferay-portal/ee/30-day-trial)
 of Liferay Portal EE today.

---

Installation steps:

1. The Plugins SDK is downloadable from our web site at
[http://www.liferay.com](http://www.liferay.com). Download the Plugins SDK:

	I.	Click the *Downloads* link at the top of the page

	II. Then, within the *Liferay Portal 6.1 Community Edition* section, select
	the *Plugins SDK* option

	III.	Click *Download*

2. Unzip the archive to a folder of your choosing. Avoid using a folder name
that contains spaces because some operating systems have problems running Java
applications in folders with spaces in their name.

---

 ![tip](../../images/tip-pen-paper.png)**Tip**: By default Liferay Portal
 Community Edition comes bundled with many plugins. It's common to remove them
 to speed up the server start-up. To do this, in the
 `liferay-portal-[version]/tomcat-[tomcat-version]/webapps` directory, delete
 all the directories except for `ROOT` and `tunnel-web.`

---

Now that you have Plugins SDK installed, let's configure Apache Ant for use in
developing your plugins.

### Ant Configuration [](id=lp-6-1-dgen02-ant-configuration-0)

Building projects in the Plugins SDK requires that you have Ant (version 1.7 or
higher) installed on your machine. Download the latest version of Ant from
[http://ant.apache.org/](http://ant.apache.org/). Decompress the archive into a
folder of your choosing.

Next, set an environment variable called `ANT_HOME` which points to the folder
to which you installed Ant. Use this variable to add the binaries for Ant to
your `PATH` by adding $`ANT_HOME\bin` to your `PATH` environment variable.

You can do this on Linux or Mac OS X by modifying your `.bash_profile` file as
follows (assuming you installed Ant in `/java`):

	export ANT_HOME=/java/apache-ant-1.8.2
	export PATH=$PATH:$ANT_HOME/bin

Close and reopen your terminal window to make these settings take effect.

You can do this on Windows by going to *Start -\> Control Panel* , and
double-clicking the *System* icon. Go to *Advanced*, and then click the
*Environment Variables* button. Under *System Variables*, select *New*. Make the
Variable Name *ANT_HOME* and the Variable Value the path to which you installed
Ant (e.g. `c:\java\apache-ant-1.8.2`), and click *OK*.

Scroll down until you find the `PATH` environment variable. Select it and click
*Edit*. Add `%ANT_HOME%\bin` to the end of the *Variable Value*. Click *OK*, and
then click *OK* again. Open a command prompt and type `ant -version` and press
*Enter*. You should get output similar to this:

	Apache Ant(TM) version 1.8.2 compiled on December 20 2010

If not, check your environment variable settings and make sure the directory
where you unzipped Ant is referenced in your `PATH`.

Now that you have Apache Ant setup, let's configure the Plugins SDK for
environment - specifically your Liferay Portal and application server
environment.

### Plugins SDK Configuration [](id=lp-6-1-dgen02-plugins-sdk-configuration-0)

Now that all the proper tools are in place, we must configure the Plugins SDK to
be able to deploy into your Liferay instance. You will notice that the Plugins
SDK contains a file called `build.properties`. This file contains the default
settings about the location of your Liferay installation and your deployment
folder. You can use this file as a reference, but you should not modify it
directly (In fact, you will see the message "DO NOT EDIT THIS FILE" at the top
if you open it). In order to override the default settings, create a new file in
the same folder called `build.[user.name].properties`, where `[user.name]` is
your user ID on your machine. For example, if your user name is jsmith (for John
Smith), you would create a file called `build.jsmith.properties`.

Edit this file and add the following lines:

    #
    # Specify the paths to an unzipped Tomcat bundle.
    #

	app.server.type=tomcat
	app.server.dir=the directory containing your application server

In the case that you are using Liferay Portal bundled with Tomcat,
`app.server.dir` should be the absolute path to your directory (e.g.
`c:/liferay-portal-[version]/bundles/tomcat-[tomcat-version]`). We specify
`app.server.type` as it is a best practice to specify the application server
type along with the application server properties we are overriding.

Save the file.

Next, let's consider the structure of the Plugins SDK.

## Structure of the SDK [](id=structure-of-the-s-3)

Each folder in the Plugins SDK contains scripts for creating new plugins of that
type. Here is the directory structure of the Plugins SDK:

- `liferay-plugins-[version]/` - Plugins SDK root directory.

	-	`clients/` - Client applications directory.

	-	`dist/` - Archived plugins for distribution and deployment.

	-	`ext/` - Ext plugins directory. See chapter *Ext plugins*.

	-	`hooks/` - Hook plugins directory. See chapter *Hooks*.

	- `layouttpl/` - Layout templates directory. See chapter *Creating Liferay
	Themes*.

	-	`lib/` - Commonly referenced libraries.

	- `misc/` - Development configuration files. Example, a source code
	formatting specification file.

	- `portlets/` - Portlet plugins directory. See chapter *Portlet
	Development*.

	- `themes/` - Themes plugins directory. See chapter *Creating Liferay
	Themes*.

	-	`tools/` - Plugin templates and utilities.

	-	`webs/` - Web plugins directory.

	-	`build.properties` - Default SDK properties.

	-	`build.[user.name].properties` (optional) - Override SDK properties.

	-	`build.xml` - Contains targets to invoke for the SDK.

	- `build-common.xml` - Contains common targets and properties referenced
	throughout the SDK.

	- `build-common-plugin.xml` - Contains common targets and properties
	referenced by each plugin.

	- `build-common-plugins.xml` - Contains common targets and properties
	referenced by each plugin type.

New plugins are placed in their own subdirectory of the appropriate plugin type.
For instance, a new portlet called "greeting-portlet" would reside in
`liferay-plugins-[version]/portlets/greeting-portlet`.

As you may notice, there is an Ant build file (`build.xml`) in each of the
plugins directories. Here are some Ant targets you'll commonly use in developing
your plugins:

- `build-service` - Builds the plugin into a service using Liferay Service
Builder.

- `clean` - Cleans the residual files created by the invocations of the
compilation, archiving and deployment targets.

-	`compile` - Compiles the plugin source code.

-	`deploy` - Builds and deploys the plugin to your application server.

- `format-source` - Formats the source code per Liferay's source code
guidelines.

-	`format-javadoc` - Formats the Javadoc per Liferay's Javadoc guidelines.

Next, let's learn how to use the Plugins SDK to create a plugin that extends
another plugin.

## Creating Plugins to extend Plugins [](id=lp-6-1-dgen02-creating-plugins-to-extend-plugins-0)

For Liferay plugins, it's possible to create a new plugin that extends an
existing one. This allows you to utilize all the features of the existing plugin
in your new plugin while keeping your changes/extensions separate from the
existing plugin's source code.

In order to create a plugin which extends another, you just need to follow these
steps:

1. Create a new empty plugin in the Plugins SDK.

2. Remove all the auto-generated files except `build.xml` and the docroot folder
   which should be empty.

3. Copy the origin WAR file of the plugin you'd like to extend (for example,
   social-networking-portlet-6.1.10.1-ee-ga1.war) to the root folder of your new
   plugin.

4. Add the following line to your `build.xml` inside of the <project> tag in
   order to reference the original war file you are going to extend. 

    <property name="original.war.file" value="social-networking-portlet-6.1.10.1-ee-ga1.war" />

5. Copy any files from the original plugin that you'd like to overwrite to your
   new plugin (using the same folder structure) and run the Ant target `merge`.
   Please note that the `merge` target is called whenever the plugin is
   compiled. All you have to do is to check the Ant output: 

    dsanz@host:~/sdk/portlets/my-social-networking-portlet$ ant war
    Buildfile: /home/dsanz/sdk/portlets/my-social-networking-portlet/build.xml
    
    compile:
    
    merge:
    [mkdir] Created dir: /home/dsanz/sdk/portlets/my-social-networking-portlet/tmp
    [mkdir] Created dir: /home/dsanz/sdk/portlets/my-social-networking-portlet/tmp/WEB-INF/classes
    [mkdir] Created dir: /home/dsanz/sdk/portlets/my-social-networking-portlet/tmp/WEB-INF/lib

    merge-unzip:
    [unzip] Expanding: /home/dsanz/sdk/portlets/my-social-networking-portlet/social-networking-portlet-6.1.10.1-ee-ga1.war into /home/dsanz/sdk/portlets/my-social-networking-portlet/tmp
    [copy] Copying 2 files to /home/dsanz/sdk/portlets/my-social-networking-portlet/tmp
    [mkdir] Created dir: /home/dsanz/sdk/portlets/my-social-networking-portlet/docroot/WEB-INF/classes

    ...

This generates a plugin (you can find the WAR file in the `/dist` folder of your
plugins SDK) which combines the original one with your changes. 

Next, let's consider some best practices for developing plugins using the SDK.

## Best Practices [](id=best-practices)

The Plugins SDK can house all of your plugin projects enterprise-wide, or you
can have separate Plugins SDK projects for each plugin. For example, if you have
an internal Intranet using Liferay with some custom portlets, you could keep
those portlets and themes in their own Plugins SDK project in your source code
repository. If you also have an external instance of Liferay for your public
Internet web site, you could have a separate Plugins SDK with those projects as
well. Or you could further separate your projects by having a different Plugins
SDK project for each portlet or theme project.

It is also possible to use use the Plugins SDK as a simple cross-platform
project generator. You can create a plugin project using the Plugins SDK and
then copy the resulting project folder to your IDE of choice. This method
requires some manual modification of the Ant scripts, but it makes it possible
to conform to the strict standards some organizations have for their Java
projects.

Next, we'll discuss an alternative solution for developing and managing plugins:
Maven.

## Maven alternative [](id=maven-alternative)

As an alternative to developing plugins using the SDK, you can leverage the
Apache Maven build management framework. You'll be happy to know that we have
archetypes to help you build various types of plugins including Liferay
portlets, themes, and layout templates.

To find out more, read the *Developing plugins using Maven* chapter of this
guide. 

## Summary [](id=lp-6-1-dgen02-summary-0)

Now that you have your Plugins SDK configured, know the directory structure and
available targets, and have learned some best practices, you are ready to start
developing plugins using Liferay's Plugins SDK.

In the next chapter, we'll start portlet development.
