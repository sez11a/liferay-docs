# Liferay npm SDK Assistant

@product@ has several components that rely on npm build tools. *npm SDK* is an 
abstract umbrella term that refers to all these components as a whole. It can be 
hard keeping track of all the different component versions and which ones are 
required to use certain features. The npm SDK assistant is a CLI tool that 
helps manage this for you. 

+$$$

**Note:** The [Liferay npm SDK Assistant](https://github.com/liferay/liferay-npm-sdk-assistant)
is unsupported. The tool is still in development and is not guaranteed to work
on all platforms and environments. You can, however, ask for help via the
[Issues](https://github.com/liferay/liferay-npm-sdk-assistant/issues) section
of the Github repo.

$$$

The npm SDK includes the components listed below:

- The 
[liferay-npm-bundler](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/liferay-npm-bundler)
tool.
- The plugins for Babel contained in
[liferay-npm-build-tools](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages).
- The plugins for liferay-npm-bundler contained in
[liferay-npm-build-tools](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages).
- The 
[frontend-js-loader-modules-extender](https://github.com/liferay/liferay-portal/tree/master/modules/apps/foundation/frontend-js/frontend-js-loader-modules-extender)
OSGi bundle of @product@.
- The [Javascript AMD loader](https://github.com/liferay/liferay-amd-loader).

The npm SDK also uses the external tools listed below:

- The [gradle build tool](https://gradle.org/).
- @product@'s 
[gradle plugin for node](https://github.com/liferay/liferay-portal/tree/master/modules/sdk/gradle-plugins-node).
- The [npm Javascript package management tool](https://www.npmjs.com/). 

This tutorial covers how the npm SDK assistant tool works and how to use it.

## Feature Levels [](id=feature-levels)

Different components of the npm SDK may have different release cycles. The term 
*feature level* refers to a complete set of component versions that produces 
stable features(described by LPSs and GitHub issues). Feature levels are defined 
by the minimum component versions needed for the supported features to work. 
Note that you can use higher versions in any of the components, as long as they 
are compatible with the one defined by the feature level.

+$$$

**Note:** Certain 
[blade samples](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/apps/npm)
and 
[templates](https://github.com/liferay/liferay-portal/tree/master/modules/sdk/project-templates) 
may rely on a specific feature level.

<!-- How can you determine what feature level is required for the template or 
sample to work?
Running this tool will list the available feature levels and tell which one you 
have, but it doesn't tell you what feature level is required to run your project.  -->

$$$

Running the command `lnka man features` displays the available feature levels 
and their supported features:

    Feature level 2
      · Gradle: 2.5.0
      · Gradle Node plugin: 2.3.0
      · npm: 4.0.0
      · Babel: 6.26.0
      · Liferay npm bundler: 1.2.1
      · Loader modules extender: 1.1.0
      · AMD loader: 2.1.0
      · Supported features:
          - NPM resolver API (https://issues.liferay.com/browse/LPS-75257)
          - Resolve own package from NPMResolver (https://issues.liferay.com/browse/LPS-75555)
          - Source maps support (https://issues.liferay.com/browse/LPS-75339)
          - Variable aliases in require attribute of <aui:script> tag (https://issues.liferay.com/browse/LPS-75553)

    Feature level 1
      · Gradle: 2.5.0
      · Gradle Node plugin: 2.3.0
      · npm: 4.0.0
      · Babel: 6.26.0
      · Liferay npm bundler: 1.2.1
      · Loader modules extender: 1.0.14
      · AMD loader: 2.1.0
      · Supported features:
          - Basic functionality

+$$$

**Note:** The tool requires access to a running @product@ instance to get the 
AMD loader version and the Gogo console to get the 
frontend-js-loader-modules-extender version. If you don't have a @product@ 
instance available, the tools can't determine the version number for these 
components and doesn't take them into account when calculating the feature 
level. This can cause a feature level to appear higher than it actually is, 
since those components are not accounted for.

$$$

As long your project has components with the minimum version numbers, you can 
use the features listed in the feature level (plus all those inherited from the 
previous feature level, of course). If you set a component to a higher version 
number which is compatible according to semantic versioning, you are still in 
the same feature level (or maybe a higher one if you increase version numbers 
enough to reach a new level). This can lead to running an unsupported npm SDK 
version that may be broken or produce unexpected results. For example, say that 
feature level 2 is the maximum possible, and your component versions are 
slightly higher than the minimum versions required (Loader modules extender has 
increased from version 1.1.0 to 1.5.0):

- Gradle: 2.5.0
- Gradle Node plugin: 2.3.0
- npm: 4.0.0
- Babel: 6.26.0
- Liferay npm bundler: 1.2.1
- Loader modules extender: 1.5.0
- AMD loader: 2.1.0

You are still in feature level 2, as all version changes have been semantical 
versioning compatible. If, however, you update to these version numbers:

- Gradle: 2.5.0
- Gradle Node plugin: 2.3.0
- npm: 4.0.0
- Babel: 6.26.0
- Liferay npm bundler: 1.5.2
- Loader modules extender: 2.1.0
- AMD loader: 2.1.0

You are no longer in feature level 2, as you have introduced the possibility of 
a breaking change when updating Loader Modules Extender from 1.1.0 to 2.1.0. In 
this case, you would be in an indeterminate feature level (pre-3 for example) 
that has not been tested. We recommend that you use component versions that are 
semantical version compatible with the component versions listed in the feature 
level you wish to use. 

+$$$

<!-- `liferay-npm-build-tools` version only shows up in your level 8 example in the README. Running 
the tool does not list this component. Is it suppose to be listed, or has this 
changed?  -->

**Note:** The liferay-npm-build-tools version specifies the version for the 
liferay-npm-bundler tool, the plugins for Babel, and the plugins for 
liferay-npm-bundler. All these components are always released together to make 
version management easier. That means that some subcomponent may have different 
version numbers for the same artifact, but we prefer such redundancy to having 
disparate version numbers for each subcomponent.

$$$

## Using the npm SDK Assistant Tool [](id=using-npm-sdk-assistant-tool)

Follow these steps to use the npm SDK Assistant:

1.  Run `npm install -g liferay-npm-sdk-assistant` to install the npm SDK 
    assistant tool. 
    
2.  Navigate to your project's root folder and run `lnka man features` to list 
    the supported feature levels.
    
3.  Run `lnka features` to list your current feature level.

4.  Update any components that don't satisfy the minimum version requirements 
    for the feature level you want.

The available commands are listed below.

### Available Commands [](id=available-commands)
    
This tool supports the commands listed below:

- `lnka help`: Lists all of the supported commands <!-- appears to do the same 
as `lnka man` -->

- `lnka <command> help`: Obtains help about a specific command
<!-- I was unable to run all the options shown for `lnka features help`. how do 
you run them? With a sever running, `lnka features --server` for instance 
produces an error. How do you configure this? -->

- `lnka features`: Determines the feature level for a project that uses the npm 
SDK. Examines the versions of all related components for the project and reports 
the supported feature level.

- `lnka man`: Lists supported commands and options
<!-- the output reads `lnka.js man...` and `lnka.js man features...`. Shouldn't 
this just say `lnka man` and `lnka man features`? I'm not sure what the 
description `Show information or documentation about the SDK` means or why it is 
shown. Also `Doing nothing` is listed and has a description. Shouldn't this be 
removed from the output? -->

- `lnka man <item>`: Shows information about the requested `<item>`. For 
example, running `lnka man features` displays the available feature levels and 
minimum component versions.

Now you know how to use the npm SDK Assistant!

## Related Topics [](id=related-topics)

[]()

[]()
