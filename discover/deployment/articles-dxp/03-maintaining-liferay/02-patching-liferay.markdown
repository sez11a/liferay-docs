# Patching Liferay [](id=patching-liferay)

While we strive for perfection with every release of @product@, the reality
of the human condition dictates that releases of the product may not be as
perfect as originally intended. But we've planned for that. Included with every
Liferay bundle is a Patching Tool that can handle the installation of two types
of patches: fix packs and hotfixes.

On a regular schedule, the latest fixes that patch the core are bundled together
into fix packs, which are provided to all of Liferay's customers. Fix packs
include fixes for both the core and the application suites that ship with the
product. 

A hotfix is provided to a customer when a customer contacts Liferay about an
emergency situation, and Liferay's support team--working with the customer--
determines that the problem is indeed an issue with the product that needs to be
fixed very quickly. Support fixes the bug and provides a hotfix to the customer
immediately. This is a short-term fix that solves the issue for the customer as
quickly as possible. Hotfixes can patch both the core and the application
suites.

## Installing the Patching Tool [](id=installing-the-patching-tool)

If you're using a Liferay bundle, the Patching Tool is already
installed. When an update is necessary to install a patch, the patching tool will automatically ask for an update.

You follow the same procedure whether you're installing or upgrading the
Patching Tool. Once you've obtained it from the Customer Portal, unzip it anywhere in the filesystem. Generally it's a good idea to keep it together with the @product@ installation.

Upgradeing is easy: override the previous Patching Tool with newest one by overriding the files.

## Executables [](id=executables)

The Patching Tool is a Java based application. The distribution contains shell / .bat scripts to make it easier to use. On Unix systems you can simply run:

    ./patching-tool.sh parameters

On Windows you should run:

    patching-tool parameters

This document will continue using the latter method, on Unix, please replace the name of the executable before running the scripts.

## Basic configuration [](id=basic-configuration)

The Patching Tool configuration can be prepared two ways:
1) Auto configuration by executing the `auto-discovery` command
2) Preparing the configuration file manually

The automatic configuration is a simple helper which prepares the configuration files by looking for files in the filesystem. By default the Patching Tool will start looking for the @product@ files from the parent folder. To start the process, you's simply need to run:

    patching-tool auto-discovery

If you are running the patching tool from a different location, you can additionally specify the folder as a parameter:

    patching-tool auto-discovery /opt/Liferay/tomcat-8.0.32

If you did not use an prepackaged installation, the Patching Tool might not find the [Liferay Home](/discover/deployment/-/knowledge_base/7-0/installing-liferay-portal#liferay-home) folder automatically. In this case, the Patching Tool will give an error message with a sample configuration:

    The .liferay-home has not been detected in the given directory tree.

    Configuration:
    patching.mode=binary
    war.path=../tomcat-8.0.32/webapps/ROOT/
    global.lib.path=../tomcat-8.0.32/lib/ext/
    liferay.home=**[please enter manually]**

    The configuration hasn't been saved. Please save this to the default.properties file.

In this case you can either add the folder manually to the configuration or create the `.liferay-home` file and re-run the auto-discovery process.

The properties above (described fully [below](#using-profiles-with-the-patching-tool)) 
define the location of Liferay Home, the patching mode
(binary or source), the path to where WAR files are deployed in the app server,
and the global library path. If auto-discovery found your Liferay Home folder,
the location of Liferay's OSGi-based module framework can be calculated from
this. If, however, you customized the folder structure, you'll have to specify
manually the following properties: 

    module.framework.core.path=path_to_modules_core_dir
    module.framework.marketplace.path=path_to_modules_marketplace_dir
    module.framework.modules.path=path_to_modules_modules_dir
    module.framework.portal.path=path_to_modules_portal_dir
    module.framework.static.path=path_to_modules_static_dir

For most installations, you don't have to do this, as the `osgi` folder is in
its default location. If you've customized the location of the module framework,
however, you'll have to specify the above locations. Since you moved them, you
should know where they are. 

Now that you've installed the Patching Tool and run auto-discovery, you're ready
to download and install patches. You can install patches manually or
automatically. For automatic patch installation, you need to set up the Patching
Tool Agent. This is presented next.

+$$$

**Checkpoint:** The patching tool is configured. When you run `patching-tool info` you receive information about the product version.

$$$

### Configuring the Patching Tool Agent [](id=configuring-the-patching-tool-agent)

The Patching Tool Agent automatically installs downloaded patches on server 
startup. For the agent to start with your server, you need to set the `javaagent` 
property in the JVM options. Make sure that you specify the correct file path to 
the `patching-tool-agent.jar`. Here's an example of setting the `javaagent` 
property:

    -javaagent:../../patching-tool/lib/patching-tool-agent.jar
    
When the agent runs, it tries to find the Patching Tool's home folder. If your 
Patching Tool is installed in a location other than the Liferay Home folder, you 
must specify the path of the `patching-tool` folder as a JVM argument for the 
app server. This is done with the `patching.tool.home` property. For example:

    -Dpatching.tool.home=/opt/liferay-dxp-digital-enterprise-7.0-ga1/tools/patching-tool
    
There are also a few other things to consider when using the agent. Due to class 
loading issues, the agent starts in a separate JVM. You can specify options for 
it by using the `patching.tool.agent.jvm.opts` property. For example:

    -Dpatching.tool.agent.jvm.opts="-Xmx1024m -Xms512m"
    
You may also experience issues on Windows if the user starting the app server 
doesn't have administrator privileges. Here are some examples of the errors you 
may see:

    `java.nio.file.FileSystemException: ..\tomcat-8.0.32\webapps\ROOT\WEB-INF\lib\util-java.jar: Not a file!`
    `java.io.FileNotFoundException: java.io.IOException: Access refused`

To solve this, set the `java.io.tmpdir` system property as follows in the 
`patching.tool.agent.jvm.opts` property:

    -Dpatching.tool.agent.jvm.opts="-Xmx1024m -Xms512m -Djava.io.tmpdir=%TMP%"

The agent also has some flags you can set to control how it behaves:

- **debug**: Provides verbose output in the console.
- **nohalt**: Starts the portal even if the agent encounters an issue.

You can specify these as follows:

    -Dpatching.tool.agent.properties=debug,nohalt

Now let's see how to use the Patching Tool to get your patches installed. 

## Installing Patches [](id=installing-patches)

The absolute first thing you must do when installing one or more patches is to
shut down your server. On Windows operating systems, files that are in use are
locked by the OS, and won't be patched. On Unix-style systems, you can usually
replace files that are running, but of course that still leaves the old ones
loaded in memory. So your best bet is to shut down the application server that's
running Liferay before you install a patch. 

**Note:** Liferay Connected Services (LCS) installs patches for you. See the 
[LCS documentation](/discover/deployment/-/knowledge_base/7-0/managing-liferay-with-liferay-connected-services) 
for more information.

Liferay distributes patches as `.zip` files, whether they are hotfixes or fix
packs. When you receive one, either via a LESA ticket (hotfix) or through
downloading a fix pack from the customer portal, you'll need to place it in the
`patches` folder, which is inside the Patching Tool's home folder. Once you've
done that, it's a simple matter to install it. First, execute

    ./patching-tool.sh info
 
This shows you a list of patches you've already installed, along with a list of
patches that *can* be installed, from what's in the `patches` folder. 

There are two ways to install patches: 

- You can use the agent
- You can install patches manually

If you've set up the agent, it installs new patches that have been placed in the
`patches` folder on server restarts. To use the agent to install the patches,
therefore, restart the server. The agent takes care of the rest. 

To install the available patches manually, use the following steps. First, 
issue the following command: 

    ./patching-tool.sh install

If there are new indexes created by the patch, the Patching Tool notifies you
to update them. To get the list, run this command:

    ./patching-tool.sh index-info

As there's no database connection at patching time, the indexes need to be
created at portal startup. In order to have the indexes created automatically,
add the following line to the `portal-ext.properties` file if the server has
permissions to modify the indexes on the database:

    database.indexes.update.on.startup=true

Otherwise, you have to create the indexes manually. Check the output of the
`./patching-tool index-info` command for more details.

Once your patches have been installed, you can verify them by using the
`./patching-tool.sh info` command, which now shows your patch in the list of
installed patches. 

Next, you'll learn how to manage your patches. 

### Handling Hotfixes and Patches [](id=handling-hot-fixes-and-patches)

As stated above, hotfixes are short term fixes provided as quickly as possible
and fix packs are larger bundles of hotfixes provided to all customers at
regular intervals. If you already have a hotfix installed and the fix pack
which contains that hotfix is released, the Patching Tool will manage this for
you. Fix packs always supersede hotfixes, so when you install your fix pack,
the hotfix that it already contains is uninstalled, and the fix pack version is
installed in its place. 

Sometimes there can be a fix to a fix pack. This is also handled automatically.
If a new version of a fix pack is released, you can use the Patching Tool to
install it. The Patching Tool uninstalls the old fix pack and installs the new
version in its place. 

### Fix Pack Dependencies [](id=fix-pack-dependencies)

Some hotfix require a fix pack to be installed first. If you attempt to
install a hotfix that depends on a fix pack, the Patching Tool notifies
you of this so you can go to the customer portal and obtain the hotfix
dependency. Once all the necessary patches are available in the `patches`
folder, the Patching Tool will install them. 

The Patching Tool can also remove patches. 

## Removing or Reverting Patches [](id=removing-or-reverting-patches)

Have you noticed that the Patching Tool only seems to have an `install` command?
This is because patches are managed not by the command, but by what appears in
the `patches` folder. You manage the patches you have installed by adding or
removing patches from this folder. If you currently have a patch installed and
you don't want it installed, remove it from the `patches` folder. Then run the
`./patching-tool.sh install` command, and the patch is removed. 

If you want to remove all patches you've installed, use the `./patching-tool.sh
revert` command. This removes all patches from your installation.

What we've described so far is the simplest way to use the Patching Tool, but
you can also use the Patching Tool in the most complex, multi-VM, clustered
environments. This is done by using profiles. 

## Cleaning Up

After you've performed your patching procedure (whether you've installed or
removed patches), it's important to clean up Liferay's cache of deployed code.
This ensures that when you start the server, you're using the revision you've
just installed the patches for. This is really easy to do. 

In the Liferay Home folder is a folder called `work`. Remove the contents of
this folder to clear out the cached code. Now you're ready to start your server. 

## Using Profiles with the Patching Tool [](id=using-profiles-with-the-patching-tool)

When you ran the auto-discovery task after installing the Patching Tool, it
created a default profile that points to the application server it discovered.
This is the easiest way to use the Patching Tool, and is great for smaller,
single server installations. But many Liferay installations are sized to serve
millions of pages per day, and the Patching Tool has been designed for this as
well. So if you're running a small, medium, or large cluster of Liferay
machines, you can use the Patching Tool to manage all of them using profiles. 

The auto-discovery task creates a properties file called `default.properties`.
This file contains the detected configuration for your application server. But
you're not limited to only one server which the tool can detect. You can have it
auto-discover other runtimes, or you can manually create new profiles yourself. 

To have the Patching Tool auto-discover other runtimes, you'll need to use a few
more command line parameters: 

    ./patching-tool.sh [name of profile] auto-discovery [path/to/runtime]
 
This will run the same discovery process, but on a path you choose, and the
profile information goes into a `[your profile name].properties` file. 

Alternatively, you can manually create your profiles. Using a text editor,
create a `[profile name].properties` file in the same folder as the Patching
Tool script. You can place the following properties in the file: 

**patching.mode:** This can be `binary` (the default) or `source`, if you're
patching the source tree you're working with. Liferay patches contain both
binary and source patches. If your development team is extending Liferay, you'll
want to provide the patches you install to your development team so they can
patch their source tree. 

**patches.folder:** Specify the location where you'll copy your patches. By
default, this is `./patches`. 

**war.path:** This is a property for which you specify the
location of the Liferay installation inside your application server.
Alternatively, you can specify a .war file here, and you'll be able to patch a
Liferay .war for installation to your application server. 

**global.lib.path:** Specify the location where .jar files on the global
classpath are stored. If you're not sure, search for your `portal-kernel.jar`
file; it's on the global classpath. This property is only valid if your
`patching.mode` is `binary`. 

**liferay.home:** Specify the location where by default the `data`,
`osgi`, and `tools` folders reside.

**source.path:** Specify the location of your Liferay source tree. This property
is only valid if your `patching.mode` is `source`. 

You can have as many profiles as you want, and use the same Patching Tool to
patch all of them. This helps to keep all your installations in sync. 

Great! Now you know how to keep @product@ up to date with the latest patches.
