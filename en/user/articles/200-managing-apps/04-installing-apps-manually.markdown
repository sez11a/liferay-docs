---
header-id: installing-apps-manually
---

# Installing Apps Manually

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/system-administration/installing-and-managing-apps/installing-apps.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

By default, apps you download from the Control Panel via Liferay Marketplace
install automatically. But what if the app you want to install isn't on
Marketplace? What if all you have is the app's file? In this case, you must
install the app manually. Here you'll learn how to install any app manually.

| **Warning:** Not all apps are designed to be "auto deployed"---deployed while
| the server is running. Deploying that way can cause instabilities, such as
| class loading leaks and memory leaks. On production systems, avoid "auto
| deploying" apps whenever possible. See the
| [best practices for managing apps in production](/docs/7-2/user/-/knowledge_base/u/managing-and-configuring-apps#managing-apps-in-production).

| **Important**: When uninstalling an app or module, make sure to use the same
| agent you used to install the app. For example, if you installed it with
| Marketplace, uninstall it with
| [Marketplace](/docs/7-2/user/-/knowledge_base/u/using-the-liferay-marketplace).
| If you installed it with the file system, use the file system to uninstall it.
| If you installed it with the App Manager, however, use
| [Blacklisting](/docs/7-2/user/-/knowledge_base/u/blacklisting-osgi-bundles-and-components)
| to uninstall it.

## Using the Control Panel to Install Apps

To install an app manually from the Control Panel, navigate to *Control Panel*
&rarr; *Apps* &rarr; *App Manager*, and select *Upload* from the options button
(![Options](../../images/icon-options.png)). In the Upload dialog, choose the
app on your machine and then click *Install*. When the install completes, close
the dialog and you're ready to roll!

## Using Your File System to Install Apps

To install an app manually on the @product@ server, put the app in the `[Liferay
Home]/deploy` folder (the [Liferay
Home](/docs/7-2/deploy/-/knowledge_base/d/liferay-home) folder is typically the
app server's parent folder). That's it. The auto deploy mechanism takes care of
the rest.

You might now be thinking, "Whoa there! What do you mean by 'the rest?' What
exactly happens here? And what if my app server doesn't support auto deploy?"
These are fantastic questions! When you put an app in the `[Liferay
Home]/deploy` folder, the OSGi container deploys the app to the appropriate
subfolder in `[Liferay Home]/osgi`. By default, the following subfolders are
used for apps matching the indicated file type:

-   `marketplace`: Marketplace LPKG packages
-   `modules`: OSGi modules
-   `war`: WAR files

You can, however, change these subfolders by setting the properties
`module.framework.base.dir` and `module.framework.auto.deploy.dirs` in a
[`portal-ext.properties`](/docs/7-2/deploy/-/knowledge_base/d/portal-properties)
file. These properties define the `[Liferay Home]/osgi`  folder and its auto
deploy subfolders, respectively. The default settings for  these properties in
the
[`portal.properties`](@platform-ref@/7.2-latest/propertiesdoc/portal.properties.html)
file are as follows:

```properties
module.framework.base.dir=${liferay.home}/osgi

module.framework.auto.deploy.dirs=\
    ${module.framework.base.dir}/configs,\
    ${module.framework.base.dir}/marketplace,\
    ${module.framework.base.dir}/modules,\
    ${module.framework.base.dir}/war
```

Note that the `configs` subfolder isn't for apps: it's for configuration files
[imported from other @product@ instances](/docs/7-2/user/-/knowledge_base/u/system-settings#exporting-and-importing-configurations).

But what happens if your app server doesn't support "hot deploy"? No problem!
@product@'s module framework (OSGi) enables auto deploy. Any app server running
@product@ therefore also supports this auto deploy mechanism.

## Manually Deploying an LPKG App

When manually installing an LPKG app, the installation may hang with a server
log message like this:

```
14:00:15,789 INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:252] Processing Liferay Push 2.1.0.lpkg
```

This happens when LPKG apps have the `restart-required=true` property in their
`liferay-marketplace.properties` file (inside the LPKG file). This property
setting specifies that a server restart is required to complete the
installation.
