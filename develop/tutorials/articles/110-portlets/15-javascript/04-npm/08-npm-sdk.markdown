# Liferay npm SDK Assistant [](id=liferay-npm-sdk-assistant)

@product@ has several npm-dependent components--collectively known as *npm
SDK*--on which certain features rely. It can be difficult, however, to keep track of
the numerous component versions and the features that require them. Liferay npm
SDK Assistant is a CLI tool that provides a list of features and their
supported component versions, along with your current component versions, so
you can update your components to a version that supports the features you
need. This tutorial covers how the Liferay npm SDK Assistant works and how to
use it.

+$$$

**Note:** The [Liferay npm SDK Assistant](https://github.com/liferay/liferay-npm-sdk-assistant)
is unsupported. The tool is still in development and is not guaranteed to work
on all platforms and environments. You can, however, ask for help via the
[Issues](https://github.com/liferay/liferay-npm-sdk-assistant/issues) section of 
the Github repo.

$$$

The npm SDK's components are:

- The 
[liferay-npm-bundler](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/liferay-npm-bundler)
tool
- The plugins for Babel in
[liferay-npm-build-tools](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages)
- The plugins for liferay-npm-bundler in
[liferay-npm-build-tools](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages)
- The 
[frontend-js-loader-modules-extender](https://github.com/liferay/liferay-portal/tree/7.0.x/modules/apps/foundation/frontend-js/frontend-js-loader-modules-extender)
OSGi bundle 
- The [Javascript AMD loader](https://github.com/liferay/liferay-amd-loader)

+$$$

**Note:** The liferay-npm-bundler tool, its plugins, and Babel's plugins are 
always released together to make version management easier. This means that some 
subcomponents may have different version numbers for the same artifact. This is 
preferred over having different version numbers for each subcomponent.

$$$

The npm SDK also uses the external tools listed below:

- The [gradle build tool](https://gradle.org/).
- @product@'s 
[gradle plugin for node](https://github.com/liferay/liferay-portal/tree/7.0.x/modules/sdk/gradle-plugins-node).
- The [npm Javascript package management tool](https://www.npmjs.com/). 


## Feature Levels [](id=feature-levels)

Different components of the npm SDK may have different release cycles. The term 
*feature level* refers to a complete set of component versions that produces 
stable features (described by LPSs and GitHub issues). Feature levels are defined 
by the minimum component versions needed for the supported features to work. 
Note that you can use higher versions in any of the components, as long as they 
are compatible with the one defined by the feature level (see Semantic 
Versioning Compatibility section below).

+$$$

**Note:** Certain 
[blade samples](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/apps/npm)
and 
[templates](https://github.com/liferay/liferay-portal/tree/master/modules/sdk/project-templates) 
may rely on a specific feature level.

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

**Note:** The Liferay npm SDK Assistant requires access to a running @product@ 
instance to get the AMD loader version and the Gogo console to get the 
`frontend-js-loader-modules-extender` version. If you don't have a @product@ 
instance available, the tools can't determine the version number for these 
components and doesn't take them into account when calculating the feature 
level. This can cause a feature level to appear higher than it actually is, 
since those components are not taken into account.

$$$

Now that you are a little more familiar with feature levels, you can dive deeper 
and learn how semantic versioning affects them.

### Semantic Versioning Compatibility [](id=semantic-versioning-compatibility)
<!--This whole section is pretty vague. Maybe someone with a better knowledge
base than me would understand it-NR--> As long your project has components with
the minimum version numbers, you can use the features listed in the feature
level (plus all those inherited from the previous feature level). If you set
a component to a higher version number that is compatible according to semantic
versioning, you are still in the same feature level (or maybe a higher one if
you increase version numbers enough to reach a new level).<!--We need some
precision here. If we have no idea how high is too high, shouldn't we be
telling users to stick with the minimum version numbers? Is there any reason
not to do this?-NR--> This can lead to running an unsupported npm SDK version
that may be broken or produce unexpected results. For example, say that feature
level 2 is the maximum possible, and your component versions are slightly
higher than the minimum versions required (Loader modules extender has
increased from version 1.1.0 to 1.5.0 for example):

- Gradle: 2.5.0
- Gradle Node plugin: 2.3.0
- npm: 4.0.0
- Babel: 6.26.0
- Liferay npm bundler: 1.2.1
- Loader modules extender: 1.5.0
- AMD loader: 2.1.0

You are still in feature level 2, as all version changes have been semantic 
versioning compatible. If, however, you update to these version numbers:
<!--How is a user supposed to know if his version changes are compatible? As
far as I can tell, the npm SDK Assistant only tells the user the minimum
version numbers.-->
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
semantic version compatible with the component versions listed in the feature 
level you wish to use. 

Now that you know how feature levels work, you can learn how to use the Liferay 
npm SDK Assistant. 

## Using the Liferay npm SDK Assistant [](id=using-liferay-npm-sdk-assistant)

Follow these steps to use the Liferay npm SDK Assistant:

1.  Run `npm install -g liferay-npm-sdk-assistant` to install the npm SDK 
    assistant tool. 
    
2.  Navigate to your project's root folder and run `lnka man features` to list 
    the supported feature levels.
    
3.  Run `lnka features` to list your current feature level.

4.  Update any components that don't satisfy the minimum version requirements 
    for the feature level you want.

The available commands are listed below.

### Available Commands [](id=available-commands)
    
This Liferay npm SDK Assistant supports the commands listed below:

- `lnka help`: Lists all of the supported commands

- `lnka <command> help`: Obtains help about a specific command

- `lnka features`: Determines the feature level for a project that uses the npm 
SDK. Examines the versions of all related components for the project and reports 
the supported feature level.

- `lnka man`: Shows information or documentation about the SDK

- `lnka man <item>`: Shows information about the requested `<item>`. For 
example, running `lnka man features` displays the available feature levels and 
minimum component versions.

Now you know how to use the Liferay npm SDK Assistant!

## Related Topics [](id=related-topics)

[liferay-npm-bundler](/develop/tutorials/-/knowledge_base/7-0/liferay-npm-bundler)

[Using ES2015 in Your Portlets](/develop/tutorials/-/knowledge_base/7-0/using-es2015-in-your-portlets)

[The Structure of OSGi Bundles Containing NPM Packages](/develop/tutorials/-/knowledge_base/7-0/the-structure-of-osgi-bundles-containing-npm-packages)
