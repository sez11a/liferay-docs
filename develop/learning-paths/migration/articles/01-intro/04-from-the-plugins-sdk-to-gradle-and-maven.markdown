# From the Plugins SDK to Gradle or Maven

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