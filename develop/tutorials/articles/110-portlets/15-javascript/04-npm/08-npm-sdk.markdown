# liferay-npm-sdk-assistant

This is a tool to ease management of projects using Liferay's npm SDK.

> This tool is experimental and not yet fully supported, so expect issues when
> using it. Nevertheless, don't hesitate to ask for help via the
> [Issues](https://github.com/liferay/liferay-npm-sdk-assistant/issues) section
> of Github.

Given the distributed nature of the npm SDK, which is based on several 
disconnected components of Liferay software, we have developed this tool to 
help developers in managing their npm SDK based projects.

If you want to learn the theoric foundations of this tool keep reading. 
Otherwise, jump directly to the [Usage](#usage) section to learn the syntax of 
the tool.

## Definition of npm SDK

Currently, *npm SDK* is an abstract umbrella term to refer to the coordinated 
functionalities of all these Liferay components:

* The 
[liferay-npm-bundler](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/liferay-npm-bundler)
tool.
* The plugins for Babel contained in
[liferay-npm-build-tools](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages).
* The plugins for liferay-npm-bundler contained in
[liferay-npm-build-tools](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages).
* The 
[frontend-js-loader-modules-extender](https://github.com/liferay/liferay-portal/tree/master/modules/apps/foundation/frontend-js/frontend-js-loader-modules-extender)
OSGi bundle of the Portal.
* The [Javascript AMD loader](https://github.com/liferay/liferay-amd-loader).

Additionally some external tools are used by the SDK:

* The [gradle build tool](https://gradle.org/).
* The Liferay's 
[gradle plugin for node](https://github.com/liferay/liferay-portal/tree/master/modules/sdk/gradle-plugins-node).
* The [npm](https://www.npmjs.com/) Javascript package management tool.

To finish with, there may be 
[blade samples](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/apps/npm)
and 
[templates](https://github.com/liferay/liferay-portal/tree/master/modules/sdk/project-templates) 
that rely on a certain feature level.

## Feature level concept

Given that different components of the SDK may have different release cycles, we 
have coined the term *feature level* to refer to specific cuts in time where 
a complete set of defined versions of each component give some well defined 
results.

Each feature level defines a set of features described by LPSs and GitHub 
issues which work as expected.

Feature levels are defined by the minimum versions needed for each component for
the supported features to work but, of course, you can use higher versions in 
any of the components as long as they are compatible with the one defined by the
feature level.

### Feature level example

Let's see and example for clarification: imagine that feature level 8 is defined
by the following features (invented for the example):

* All those listed under feature level 7 (because they are always inherited)
* LPS-91456
* LPS-95555
* Issue #98465 of liferay-npm-build-tools

And that it specifies the following minimum versions for each component 
(version numbers invented too):

* gradle: 12.0
* gradle plugin for node: 17.0.0
* npm: 8.0
* babel: 21.0
* liferay-npm-build-tools: 27.0.0
* frontend-js-loader-modules-extender: 15.0.0
* liferay-amd-loader: 8.0.0

> Note that the version of liferay-npm-build-tools specifies the version for the
> liferay-npm-bundler tool, the plugins for Babel, and the plugins for 
> liferay-npm-bundler. 
>
> In fact, all these components are always released together to make version 
> management easier. That means that some subcomponent may have different 
> version numbers for the same artifact, but we prefer such redundancy to having 
> disparate version numbers for each subcomponent.

That means that, as long as we have components with the minimum version numbers,
we can use the four features listed in the feature level (plus all those 
inherited from the previous feature level, of course).

Also, if we set a component to a higher version number which is compatible 
according to semantic versioning, we would still be in the same feature level 
(or maybe a higher one if we increase version numbers enough to reach a new 
level).

So, say that feature level 8 is the maximum possible, and that we have these
versions:

* gradle: 12.0
* gradle plugin for node: 17.3.0
* npm: 8.0
* babel: 21.7
* liferay-npm-build-tools: 27.0.0
* frontend-js-loader-modules-extender: 15.4.1
* liferay-amd-loader: 8.0.1

We are still in feature level 8 as all version changes have been semantical 
versioning compatible.

On the contrary, if we update to these version numbers:

* gradle: 12.0
* gradle plugin for node: 17.0.0
* npm: 8.0
* babel: 21.0
* liferay-npm-build-tools: 27.0.0
* frontend-js-loader-modules-extender: 16.0.0
* liferay-amd-loader: 8.0.0

We are no longer in feature level 8, as we have introduced the possibility of a
breaking change when updating frontend-js-loader-modules-extender from 15.0.0
to 16.0.0. 

So, where are we if we said that feature level 8 was the maximum possible? In 
this case we would be in an indeterminate feature level. Probably all features
of level 8 would work, but we cannot assure it with 100% confidence because no
feature level 9 has yet been released, so nobody has tested that the given 
versions combination works correctly. This is the typical case of using a 
development/unstable version of the SDK which has not yeet been released 
officially. Thus, we could call this indeterminate state, feature level pre-9 or
something alike.

## Usage

The tool is a CLI driven by commands given as arguments (much in the same way as
tools like git or blade). 

To see the list of all supported commands run `lnka help`. Then, to obtain help
about a specific command run `lnka <command> help`.

Detailed descriptions of each supported command follow in the next sections.

### features

The `lnka features` command can be run in a project folder that uses the npm SDK 
to determine its feature level. 

When this command is run it examines the versions of all related components and
uses them to report the supported feature level.

Keep in mind that the tool may not be able, for different reasons, to retrieve 
the version number of a component. In that case the reported feature level may 
be higher than the real one.

For example: the tool needs to contact Liferay Portal through HTTP to get the 
AMD loader version and through the GoGo console to get the 
frontend-js-loader-modules-extender version. Thus, if you don't have a Portal 
available, the tools cannot determine the version number for those components
and does not take them into account when calculating the feature level.

### man

The `lnka man <item>` command shows information about the requested `<item>`. 
Use `lnka man` (with no item) to get a list of supported items.

