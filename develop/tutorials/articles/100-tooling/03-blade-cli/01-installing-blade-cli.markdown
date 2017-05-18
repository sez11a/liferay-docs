# Installing Blade CLI [](id=installing-blade-cli)

Blade CLI can be installed to your machine using the Liferay Workspace
installer. This installs JPM and Blade CLI into your user home folder, and
optionally initializes a
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/liferay-workspace)
folder.

+$$$

**Note:** In the past, if you've installed Blade CLI globally (e.g., using
`sudo`), you should not run the installer to *update* your Blade CLI version.
Since the installer only installs Blade CLI to your user home folder, your
previous global installation would always override the installer's installation.
Therefore, always follow the
[Updating Your Blade CLI Installation](#verifying-your-blade-cli-installation)
section to update your Blade CLI instance.

$$$

If you need to configure proxy settings for Blade CLI, follow the
[Installing Blade CLI with Proxy Requirements](/develop/tutorials/-/knowledge_base/7-0/setting-blade-cli-proxy-requirments)

Follow the steps below to download and install Blade CLI:

1.  Download the
    [Liferay Workspace installer](https://sourceforge.net/projects/lportal/files/Liferay%20Workspace)
    that corresponds with your operating system (e.g., Windows, MacOS, or
    Linux).

2.  Run the installer. Click *Next* to step through the installer's
    introduction.

3.  If you'd like to initialize a Liferay Workspace, you can set the directory
    in which it should reside.

    ![Figure 1: Determine where your Liferay Workspace should reside, if you want one.](../../../images/blade-installer-workspace-init.png)

    Select the *Don't initialize Liferay Workspace directory* option if you only
    want to install Blade CLI. Then click *Next*.

4.  If you decided to initialize a Liferay Workspace folder in the previous
    step, you'll have an additional option to select the Liferay product type
    you'll use with your workspace. Choose the product type and click *Next*.

    ![Figure 2: Select the product version you'll use with your Liferay Workspace.](../../../images/installer-workspace-type.png)

5.  Click *Next* to begin installing Blade CLI/Liferay Workspace on your
    computer.

That's it! Blade CLI is installed on your machine! If you specified a location
to initialize a Liferay Workspace folder, that is also available.

## Updating Your Blade CLI Installation [](id=verifying-your-blade-cli-installation)

If your Blade CLI version is outdated, you can run the following command to
automatically download and install the latest version of Blade CLI:

    blade update

For Windows users, the `blade update` command does not work because Windows
cannot update a file that is currently in use. To bypass this issue, you can use
JPM to update your version of Blade CLI:

    jpm install -f https://releases.liferay.com/tools/blade-cli/latest/blade.jar

Blade CLI is updated frequently, so it's recommended to update your Blade CLI
environment for new features. You can check your current installed version by
running `blade version`.

You've successfully learned how to install and update Blade CLI. Blade CLI
offers many
[`create` templates](/develop/reference/-/knowledge_base/7-0/project-templates)
to help build @product-ver@ applications. It also offers various ways to deploy
those apps and interact with your Liferay server. Be sure to explore more Blade
CLI tutorials to learn how.
