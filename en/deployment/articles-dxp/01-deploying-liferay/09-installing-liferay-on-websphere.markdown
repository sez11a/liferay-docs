---
header-id: installing-liferay-dxp-on-websphere
---

# Installing @product@ on WebSphere

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/installing-liferay/installing-liferay-on-an-application-server/installing-on-websphere.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

IBM &reg; WebSphere &reg; is a trademark of International Business Machines
Corporation, registered in many jurisdictions worldwide.

| **Tip:** Throughout this installation and configuration process, WebSphere
| prompts you to click *Save* to apply changes to the Master Configuration. Do | so intermittently to save your changes.

For @product@ to work correctly, WebSphere 9 (Fix Pack 11 is the latest) must be
installed. You can find more information about this fix pack
[here](http://www-01.ibm.com/support/docview.wss?uid=swg24043005).

Please also note that @product@ doesn't support the WebSphere Application
Liberty Profile.

| **Important:** Before installing @product@, familiarize yourself with
| [preparing for install](/docs/7-2/deploy/-/knowledge_base/d/preparing-for-install).

Now, [download the @product@ WAR and Dependency
JARs](/docs/7-2/deploy/-/knowledge_base/d/obtaining-product#downloading-the-liferay-war-and-dependency-jars):

-   @product@ WAR file
-   Dependencies ZIP file
-   OSGi Dependencies ZIP file

Note that the [*Liferay Home*
folder](docs/7-2/deploy/-/knowledge_base/d/liferay-home) is important to the
operation of @product@. In Liferay Home, @product@ creates certain files and
folders that it needs to run. On WebSphere, Liferay Home is typically `[Install
Location]/WebSphere/AppServer/profiles/your-profile/liferay`.

Without any further ado, get ready to install @product@ in WebSphere!

## Preparing WebSphere for @product@

When the application server binaries have been installed, start the *Profile
Management Tool* to create a profile appropriate for @product@.

1.  Click *Create...*, choose *Application Server*, and then click *Next*.

2.  Click the *Advanced* profile creation option and then click *Next*. You need
    the advanced profile to  specify your own values for settings such as the
    location of the profile and names of the profile, node and host, to assign
    your own ports, or to optionally choose whether to deploy the administrative
    console and sample application and also add web-server definitions for IBM
    HTTP Server. See the WebSphere documentation for more information about
    these options.

    ![Figure 1: Choose the Advanced profile option to specify your own settings.](../../images-dxp/websphere-01-profile.png)

3.  Check the box *Deploy the administrative console*. This gives you a
    web-based UI for working with your application server. Skip the default
    applications. You'd only install these on a development machine. Click
    *Next*.

4.  Set the profile name and location. Ensure you specify a performance tuning
    setting other than *Development*, since you're installing a production
    server. See the WebSphere documentation for more information about
    performance tuning settings. Click *Next*.

5.  Choose node, server, and host names for your server. These are specific
    to your environment. Click *Next*.

6.  Administrative security in WebSphere is a way to restrict who has access to
    the administrative tools. You may want to have it enabled in your
    environment so that a user name and password are required to administer the
    WebSphere server. See WebSphere's documentation for more information. Click
    *Next*.

7.  Each profile needs a security certificate, which comes next in the wizard.
    If you don't have certificates already, choose the option to generate a
    personal certificate and a signing certificate and click *Next*.

8.  Once the certificates are generated, set a password for your keystore. Click
    *Next*.

9.  Now you can customize the ports this server profile uses. Be sure to choose
    ports that are open on your machine. When choosing ports, the wizard detects
    existing WebSphere installations and if it finds activity, it increments
    ports by one.

10. Choose whether to start this profile when the machine starts. Click *Next*.

11. WebSphere ships with IBM HTTP Server, which is a re-branded version of
    Apache. Choose whether you want a web server definition, so that this JVM
    receives requests forwarded from the HTTP server. See WebSphere's
    documentation for details on this. When finished, click *Next*.

12. The wizard then shows you a summary of what you selected, enabling you to
    keep your choices or go back and change something. When you're satisfied,
    click *Next*.

WebSphere then creates your profile and finishes with a message telling you the
profile was created successfully. Awesome! Your profile is complete. Now there
are a few things you need to configure in your application server.

![Figure 2: Example of the settings before creating the profile.](../../images-dxp/websphere-02-profile.png)

### Configuring the WebSphere Application Server

In this version of WebSphere, servlet filters are not initialized on web
application startup, but rather, on first access. This can cause problems when
deploying certain apps to @product@. To configure servlet filters to initialize
on application startup (i.e., deployment), set the following `webcontainer`
properties in your WebSphere application server:

```properties
com.ibm.ws.webcontainer.initFilterBeforeInitServlet = true
com.ibm.ws.webcontainer.invokeFilterInitAtStartup = true
```

To set `webcontainer` properties in the WebSphere application server, follow the
instructions  [here in WebSphere's
documentation](http://www-01.ibm.com/support/docview.wss?rss=180&uid=swg21284395).

### Setting up JVM Parameters for Liferay DXP

Next, in the WebSphere profile you created for @product@, you must set an
argument that supports @product@'s Java memory requirements. You'll modify this
file:

```
[Install Location]/WebSphere/AppServer/profiles/your-profile/config/cells/your-cell/nodes/your-node/servers/your-server/server.xml
```

Add `maximumHeapSize="2560"` inside the `jvmEntries` tag. For example:

```xml
<jvmEntries xmi:id="JavaVirtualMachine_1183122130078" ... maximumHeapSize="2560">
```

| **Note:** The JVM parameters used here are defaults intended for initial
| deployment of production systems. Administrators should change the settings to
| values that best address their specific environments. These must be tuned
| depending on need.

Administrators can set the UTF-8 properties in the `<jvmEntries
genericJvmArguments=.../>` attribute in `server.xml`. This is required or else
special characters will not be parsed correctly. Set the maximum and minimum
heap sizes to `2560m` there too. Add the following inside the `jvmEntries` tag:

```xml
<jvmEntries xmi:id="JavaVirtualMachine_1183122130078" ...genericJvmArguments="-Dfile.encoding=UTF-8 -Duser.timezone=GMT -Xms2560m -Xmx2560m">
```

| **Important:** For @product@ to work properly, the application server JVM must
| use the `GMT` time zone and `UTF-8` file encoding.

Alternately, you can set the UTF-8 properties from the WebSphere Admin Console.
(See below.)

### Removing the secureSessionCookie Tag

In the same profile, you should delete a problematic `secureSessionCookie` tag
that can cause @product@ startup errors. Note that this is just a default
setting; once @product@ is installed, you should tune it appropriately based on
your usage.

In `[Install Location]/WebSphere/AppServer/profiles/your-profile/config/cells/your-cell/cell.xml`,
Delete the `secureSessionCookie` tag containing
`xmi:id="SecureSessionCookie_1"`.

If this tag is not removed, an error similar to this may occur:

```
WSVR0501E: Error creating component com.ibm.ws.runtime.component.CompositionUnitMgrImpl@d74fa901
com.ibm.ws.exception.RuntimeWarning: com.ibm.ws.webcontainer.exception.WebAppNotLoadedException: Failed to load webapp: Failed to load webapp: SRVE8111E: The application, LiferayEAR, is trying to modify a cookie which matches a pattern in the restricted programmatic session cookies list [domain=*, name=JSESSIONID, path=/].
```

## Installing @product@'s Dependencies

You must now install @product@'s dependencies. Recall that earlier you
downloaded two ZIP files containing these dependencies. Install their contents
now:

1.  Unzip the Dependencies ZIP file and place its contents in your WebSphere
    application server's `[Install Location]/WebSphere/AppServer/lib/ext`
    folder. If you have a JDBC database driver `JAR`, copy it to this location
    as well.

    | **Note:** The [Liferay DXP Compatibility Matrix](https://web.liferay.com/documents/14/21598941/Liferay+DXP+7.2+Compatibility+Matrix/b6e0f064-db31-49b4-8317-a29d1d76abf7?) specifies supported databases and environments.

2.  From the same archive, copy `portlet.jar`into `[Install
    Location]/WebSphere/AppServer/javaext` for WebSphere 9.0.0.x. WebSphere
    already contains an older version of `portlet.jar` which must be overridden
    at the highest class loader level. The new `portlet.jar` (version 3) is
    backwards-compatible.

3.  Unzip the OSGi Dependencies ZIP file and place its contents in the
    `[Liferay Home]/osgi` folder (create this folder if it doesn't exist). This
    is typically `[Install
    Location]/WebSphere/AppServer/profiles/your-profile/liferay/osgi`.

### Ensuring that @product@'s portlet.jar is loaded first

In addition to placing the `portlet.jar` in the correct folder, you must
configure the `config.ini` file so that it is loaded first. Navigate to
`/IBM/WebSphere/AppServer/configuration/config.ini`.

1.  Find the property `com.ibm.CORBA,com.ibm`

2.  Insert the property
    `javax.portlet,javax.portlet.filter,javax.portlet.annotations`
    after `com.ibm.CORBA` and before `com.ibm`.

3.  Save the file.

Once you've installed these dependencies and configured the `config.ini` file,
start the server profile you created for @product@. Once it starts, you're ready
to configure your database.

## Database Configuration

If you want WebSphere to manage the database connections, follow the
instructions below. Note this is not necessary if you plan to use @product@'s
standard database configuration; in that case, skip this section. See the [Using
the Built-in Data
Sources](/docs/7-2/deploy/-/knowledge_base/d/preparing-for-install#using-the-built-in-data-source)
section for more article.

You'll set your database information in @product@'s setup wizard after the
install.

| **Note:** Although @product@'s embedded database is fine for testing purposes,
| you **should not** use it for production @product@ instances.

![Figure 3: WebSphere JDBC providers](../../images-dxp/websphere-jdbc-providers.png)

1.  Start WebSphere.

2.  Open the Administrative Console and log in.

3.  Click *Resources &rarr; JDBC Providers*.

4.  Select a scope and then click *New*.

5.  Select your database type, provider type, and implementation type. If you
    select a predefined database, the wizard fills in the name and description
    fields for you. If the database you want to use isn't listed, select
    *User-defined* from the *Database type* field and then fill in the
    *Implementation Class Name*. For example, if you use MySQL, select *Database
    type* &rarr; *User-defined*, and then enter
    `com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource` in
    *Implementation Class Name*. Click *Next* when you are finished.

6.  Clear any text in the class path settings. You already copied the necessary
    JARs to a location on the server's class path. Click *Next*.

7.  Review your settings and click *Finish*. The final configuration should look
    like this:

    ![Figure 4: Completed JDBC provider configurations.](../../images-dxp/websphere-03.png)

8.  Click your new provider configuration when it appears in the table, and then
    click *Data Sources* under *Additional Properties*. Click *New*.

9.  Enter *liferaydatabasesource* in the *Data source name* field and
    `jdbc/LiferayPool` in the *JNDI name* field. Click *Next*.

10. Click *Next* in the remaining screens of the wizard to accept the default
    values. Then review your changes and click *Finish*.

11. Click the data source when it appears in the table and then click *Custom
    Properties*. Now click the *Show Filter Function* button. This is the second
    from last of the small icons under the *New* and *Delete* buttons.

12. Type *user* into the search terms and click *Go*.

    ![Figure 5: Modifying data source properties in WebSphere](../../images-dxp/websphere-database-properties.png)

13. Select the *user* property and give it the value of the user name to your
    database. Click *OK* and save to master configuration.

14. Do another filter search for the *url* property. Give this property a value
    that points to your database. For example, a MySQL URL would look like this:

    ```properties
    jdbc:mysql://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT&useFastDateParsing=false
    ```

    | **Tip:** For more example URLs, see the `jdbc.default.url` values in
    | [Database Templates](/docs/7-2/deploy/-/knowledge_base/d/database-templates).

    Click *OK* and save to master configuration.

15. Do another filter search for the *password* property. Enter the password for
    the user ID you added earlier as the value for this property. Click *OK* and
    save to master configuration.

16. Go back to the data source page by clicking it in the breadcrumb trail.
    Click the *Test Connection* button. It should connect successfully.

Once you've set up your database, you can set up your mail session.

## Mail Configuration

If you want WebSphere to manage your mail sessions, use the following procedure.
If you want to use @product@'s built-in mail sessions, you can skip this
section. See the [Configuring
Mail](/docs/7-2/deploy/-/knowledge_base/d/configuring-mail) article on how to
use @product@'s built-in mail sessions.

### Creating a WebSphere-Managed Mail Session (Optional)

1.  Click *Resources &rarr; Mail &rarr; Mail Providers*.

2.  Click the Built-In Mail Provider for your node and server.

3.  Click *Mail Sessions* and then click the *New* button.

4.  Give your mail session a name of *liferaymail* and a JNDI name of
    `mail/MailSession`. Fill in the correct information for your mail server in
    the sections *Outgoing Mail Properties* and *Incoming Mail Properties*.
    Click *OK* and then save to the master configuration.

5.  Click your mail session when it appears in the table and select *Custom
    Properties* under the *Additional Properties* section. Set any other
    JavaMail properties required by your mail server, such as the protocol,
    ports, whether to use SSL, and so on.

6.  Click *Security &rarr; Global Security* and de-select *Use Java 2 security
    to restrict application access to local resources* if it is selected. Click
    *Apply*.

Note that you may also need to retrieve a SSL certificate from your mail server
and add it to WebSphere's trust store. See WebSphere's documentation for
instructions on this.

### Verifying WebSphere Mail Provider

To validate that the mail session has been configured correctly, there are a
number of ways to test this once the WAR has been deployed, the server has
started, and the user has signed in as the system administrator. One quick way
to validate is to create a new user with a valid email account. The newly
created user should receive an email notification. The logs should display that
the SMTP server has been pinged with the correct port number listed.

## Enable Cookies for HTTP Sessions

WebSphere restricts cookies to HTTPS sessions by default. If you're
using HTTP instead, this prevents users from signing in to @product@ and
displays the following error in the console:

```
20:07:14,021 WARN  [WebContainer : 1][SecurityPortletContainerWrapper:341]
User 0 is not allowed to access URL http://localhost:9081/web/guest/home and
portlet com_liferay_login_web_portlet_LoginPortlet
```

This occurs because @product@ can't use the HTTPS cookie when you use HTTP. The
end result is that new sessions are created on each page refresh. Follow these
steps to resolve this issue in WebSphere:

1.  Click *Application Servers* &rarr; *server1* &rarr; *Session Management*
   &rarr; Enable Cookies

2.  De-select *Restrict cookies to HTTPS sessions*

3.  Click *Apply*

4.  Click *Save*

## Enable UTF-8

If you did not add the `-Dfile.encoding=UTF-8` property in the `server.xml`, you
can do so in the Administrative Console.

1.  Click *Application Servers* &rarr; *server1* &rarr; *Process definition*.

2.  Click *Java Virtual Machine* under *Additional Properties*.

3.  Enter `-Dfile.encoding=UTF-8` in the *Generic JVM arguments* field.

4.  Click *Apply* and then *Save* to master configuration.

Once the changes have been saved, @product@ can parse special characters if
there is localized content.

## Deploy @product@

Now you're ready to deploy @product@!

1.  In WebSphere's administrative console, click *Applications* &rarr; *New
    Application* &rarr; *New Enterprise Application*.

2.  Browse to the @product@ `.war` file, select it, and click *Next*.

3.  Leave *Fast Path* selected and click *Next*. Ensure that *Distribute
    Application* has been checked and click *Next* again.

4.  Choose the WebSphere runtimes and/or clusters where you want @product@
    deployed. Click *Next*.

5.  Select the virtual host to deploy @product@ on and click *Next*.

6.  Map @product@ to the root context (`/`) and click *Next*.

7.  Select the *metadata-complete attribute* setting that you want to use and
    click *Next*.

8.  Ensure that you have made all the correct choices and click *Finish*. When
    @product@ has installed, click *Save to Master Configuration*.

   ![Figure 6: Review your deployment options before deploying.](../../images-dxp/websphere-deploy-dxp.png)

You've now installed @product@!

## Setting the JDK Version for Compiling JSPs

@product@ requires that its JSPs are compiled to the Java 8 bytecode format. To
ensure that WebSphere does this, shut down WebSphere after you've deployed the
@product@ `.war` file. Navigate to the `WEB_INF` folder and add the following
setting to the `ibm-web-ext.xml` or in most cases the `ibm-web-ext.xmi` file:

```xml
<jsp-attribute name="jdkSourceLevel" value="18" />
```

The exact path to the `ibm-web-ext.xmi` file depends on your WebSphere
installation location and @product@ version, but here's an example:

```bash
/opt/IBM/WebSphere/AppServer/profiles/AppSrv01/config/cells/localhostNode01Cell/applications/liferayXX.ear/deployments/liferayXX/liferayXX.war/WEB-INF/ibm-web-ext.xmi
```

Note that the @product@ `.war` comes pre-packaged with the `ibm-web-ext.xmi`
file; this format is functionally the same as `.xml` and WebSphere recognizes
both formats. For more general information on how WebSphere compiles JSPs see
IBM's official documentation for [WebSphere Application Server
9.0.0.x](https://www.ibm.com/support/knowledgecenter/en/SSEQTP_9.0.0/com.ibm.websphere.base.doc/ae/rweb_jspengine.html).

## Start @product@

1.  If you plan to use @product@'s [setup

    wizard](/docs/7-2/deploy/-/knowledge_base/d/installing-product#using-the-setup-wizard),
    skip to the next step. If you wish to use WebSphere's data source and mail
    session, create a file called `portal-ext.properties` in your Liferay Home
    folder. Place the following configuration in the file:

    ```properties
    jdbc.default.jndi.name=jdbc/LiferayPool
    mail.session.jndi.name=mail/MailSession
    setup.wizard.enabled=false
    ```
2.  In the WebSphere administrative console, navigate to *Enterprise
    Applications*, select the @product@ application, and click *Start*. While
    @product@ is starting, WebSphere displays a spinning graphic.

3.  In @product@'s setup wizard, select and configure your database type. Click
    *Finish* when you're done. @product@ then creates the tables it needs in the
    database.

Congratulations! You've installed @product@ on WebSphere!

| After deploying @product@, you may see excessive warnings and log messages, such
| as the ones below, involving `PhaseOptimizer`. These are benign and can be
| ignored. Make sure to adjust your app server's logging level or log filters to
| avoid excessive benign log messages.
|
|     May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
|     WARNING: Skipping pass gatherExternProperties
|     May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
|     WARNING: Skipping pass checkControlFlow
|     May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
|     INFO: pass supports: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, modules, exponent operator (**), async function, trailing comma in param list]
|     current AST contains: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, exponent operator (**), async function, trailing comma in param list, object literals with spread, object pattern rest]
