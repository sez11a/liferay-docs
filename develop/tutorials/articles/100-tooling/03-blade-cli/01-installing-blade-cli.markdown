# Installing Blade CLI [](id=installing-blade-cli)

The best way to obtain Blade CLI is to download its JAR file and install it
using the [Java Package Manager](http://jpm4j.org) (JPM). Blade CLI is
packaged using [JPM](http://jpm4j.org/#!/md/install). Make sure to install JPM,
since it's the best way to download, install, and update your Blade CLI
environment. The exact JPM installation and update syntax depends on your
operating system, and will be covered in greater detail next.

## Installing Blade CLI Using JPM [](id=installing-blade-cli-using-jpm)

After you've installed JPM, install the Blade CLI using the following command: 

    (sudo) jpm install com.liferay.blade.cli

If you have proxy server requirements and want to configure your http(s) proxy
to work with the Blade CLI, follow the instructions below. If not, continue on
to the next section.

For Mac and Linux users, run the following:
		
    (sudo) jpm install -f --jvmargs "-Dhttp(s).proxyHost=[your proxy host] -Dhttp(s).proxyPort=[your proxy port]" com.liferay.blade.cli
	
Alternatively, for Windows users, there is a bug preventing JVM arguments from
passing into JPM. Therefore, install the Blade CLI the same way that was
instructed for non-proxy users. Then go to your JPM installation path (e.g.,
`USER_HOME/.jpm/windows/bin`) and open the `blade.ini`. Add the following
lines to the end of the file.

    vmarg.1=-Dhttp(s).proxyHost=[your proxy host]
    vmarg.2=-Dhttp(s).proxyPort=[your proxy port]

Now that Blade CLI is installed on your machine, you'll learn how to verify and
update your installation.

## Verifying Your Blade CLI Installation [](id=verifying-your-blade-cli-installation)

To check that Blade CLI is installed, make sure that the `blade` executable is
available on your system path. Test its usage by entering `blade version` into a
terminal. If Blade CLI is installed correctly, you'll see the current version
for the installed tools:

If your version is outdated, you can run `blade update` to automatically
download and install the latest version of Blade CLI. Blade CLI is updated
frequently, so it's recommended to update your Blade CLI environment for new
features.

+$$$

**Note:** For Windows users, the `blade update` command does not work. This is
because Windows cannot update a file that is currently in use. To bypass this
issue, you can use JPM to update your version of Blade CLI:

    jpm install -f com.liferay.blade.cli

$$$

You've successfully downloaded and installed Blade CLI using JPM and verified
your installation using a basic `blade` command. Blade CLI offers many `create`
templates to help build @product@ 7.0 applications, and also offers various ways
to deploy those apps and interact with your Liferay server. Be sure to explore
more Blade CLI tutorials to learn how.
