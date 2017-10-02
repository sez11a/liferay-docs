# Using a SharePoint Repository [](id=using-a-sharepoint-repository)

With the help of Liferay's Sharepoint Connector app, users can access SharePoint 
2013 and SharePoint 2010 libraries from within a @product@ Documents and Media 
Library. The app installs a SharePoint repository type that you can select on 
adding a new *Repository* to Documents and Media. The SharePoint Connector 
integrates sites with existing SharePoint libraries, so you can access all of 
your organization's files.

The [Liferay Sharepoint Connector](https://web.liferay.com/marketplace/-/mp/application/15188537)
app is available to EE subscribers on Liferay's
[Marketplace](http://marketplace.liferay.com/). Refer to [Managing Apps](/discover/portal/-/knowledge_base/7-0/managing-apps)
for details on installing and activating apps.

Users gain these benefits by accessing the SharePoint repository through the
Liferay Sharepoint Connector: 

- Reading/writing documents and folders
- Document check-in, check-out, and undo check-out
- Downloading documents
- Moving folders and documents within the repository
- Getting revision history
- Reverting to a revision

The Sharepoint Connector uses SharePoint's API, which has some limitations: 

- Version history is lost when moving or renaming a file without first 
  checking it out.
- You can't change file extensions, you can only change file names.
- A file's current name propagates to all previous versions.
- The user who checks out a file is the only one who can see its working copy 
  version number.
- Queries for suffixes or intermediate wildcards convert to queries for 
  containment.  
- Ratings are unsupported. 

The Liferay Sharepoint Connector uses OAuth 2 for authorization against a 
SharePoint server, using the Azure ACS. This means that **client applications 
must use @product@ through HTTPS.** For more information on configuring 
@product@ to work with HTTPS see Apache's docs [here](https://tomcat.apache.org/tomcat-8.0-doc/ssl-howto.html)
<!-- is this only for apache Tomcat? If not we shouldn't point to only Tomcat's docs. -->

To use a SharePoint repository inside Documents and Media, you must first
configure the SharePoint and @product@ environments to support authentication 
with SharePoint and then add a Documents and Media Repository that connects to
SharePoint. Start by configuring the environments.

## Environment Setup [](id=environment-setup)

Here's an overview of what you must do to configure your host, @product@, and
SharePoint to use SharePoint from @product@'s Documents and Media Library:

1. Enable Basic Authentication on the SharePoint host
2. Enable Versioning Support on the SharePoint library
3. Configure Authentication on @product@
4. Synchronize user credentials between @product@ and SharePoint
5. Create a New SharePoint Repository Configuration

Note, these instructions are geared to @product@ and SharePoint system
administrators.

Before you can use SharePoint as an external repository with @product@, you must
set up a few things on the SharePoint host and in the SharePoint server 
application.

### Step 1: Enable Basic Authentication on the SharePoint Host [](id=step-1-enable-basic-authentication-on-the-sharepoint-host)

So that Liferay's Sharepoint Connector can authenticate against the SharePoint
web services, you must enable Basic Authentication on the SharePoint host. As
you do this, make sure to empty Basic Authentication's default domain and realm
fields of all values. 

Authentication setup steps differ between Windows versions. But as an
example, here are steps for enabling Basic Authentication on *Windows Server
2008*: 

1.  Sign in to the Windows server as a member of the Administrators group. 
2.  Open *Administrative Tools*, and then click *Internet Information Services
    (IIS) Manager* to launch the IIS Manager console. 
3.  In the Connections navigation panel, navigate to the SharePoint web site
    options by clicking on the server's name, then *Sites*, and then the name of
    the SharePoint site. The site's Features View is available in the main
    viewing area of the IIS Manager console.
4.  Select the *Features View* tab and then double-click on the *Authentication* icon
    in the IIS section of the Features View. The Authentication panel appears.
    
    ![Figure 1: The Features View for the site shows the Authentication icon.](../../../images-dxp/sharepoint-site-iis-authentication.png)
    
5.  In the Authentication panel, select the row named *Basic Authentication*.
    The Actions panel appears next to the main panel.
6.  In the Actions panel, click *Enable* to activate Basic Authentication. 
7.  Also in the Actions panel, click *Edit*. An Edit Basic Authentication Settings
    dialog box appears.
   ![Figure 2: Clicking the *Edit...* action brings up the a dialog for setting the Default domain and Realm.](../../../images-dxp/sharepoint-host-edit-basic-auth-settings.png)
8.  In the dialog box, empty the *Default domain* and *Realm* fields of any
    values and click *OK*.

You've configured Basic Authentication on the SharePoint host.

Next, you should enable versioning support in your SharePoint library so that
users can leverage file versioning between @product@ and SharePoint. 

### Step 2: Enable Versioning Support in the SharePoint Library [](id=step-2-enable-versioning-support-in-the-sharepoint-library)

You must enable versioning in SharePoint library for @product@'s check-in/out
features to work with SharePoint. To enable it, follow these steps: 

1.  Open a browser to the SharePoint library's URL.
2.  Click on the SharePoint library's name.
3.  At the top of the toolbar, click on *Liferay Tools* and then on *Library*,
    underneath *Liferay Tools*. 
4.  In the toolbar, click on *Library Settings*.
5.  Under *General Settings*, click on *Versioning settings*.
6.  In *Document Version History*, select *Create major and minor (draft)
    versions*. 
7.  In *Require Check Out* select *Yes*.

You've set SharePoint to accept versioning requests from @product@.

Next, you'll configure authentication for @product@. 

### Step 3: Authentication on Liferay [](id=step-3-authentication-on-liferay)

To authenticate with the SharePoint repository, you must configure an 
authentication type that supports storing passwords for the user sessions.

**Important**: Since authentication with single sign-on (SSO) does not store
encrypted passwords in the user sessions, SSO can't be used with the Sharepoint
Connector app.

Follow these steps:

1.  In your [Liferay Home](/discover/deployment/-/knowledge_base/7-0/installing-liferay-portal#liferay-home),
    create a `portal-ext.properties` file, if one doesn't already exist.

2.  Add a [`session.store.password`](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Session)
    portal property set to `true`:

        session.store.password=true

3.  Authenticate the same way on both @product@ and the external repository. To 
    do so, authenticate by screen name. Add the following  
    [`company.security.auth.type`](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Company)
    portal property to your `portal-ext.properties` file: 

        company.security.auth.type=screenName

    Alternatively, you can configure this property in the Control Panel under 
    *Configuration* &rarr; *Instance Settings* &rarr; *Authentication*.

Next, you must give @product@ users access to the SharePoint server.

### Step 4: Synchronize Credentials [](id=step-4-synchronize-credentials)

As a @product@ system administrator, you must ensure that the same credentials
and authentication are used in @product@ and in SharePoint.
[LDAP](/discover/deployment/-/knowledge_base/7-0/ldap) is a typical mechanism
for synchronizing them. If you don't have LDAP, however, you must manually
synchronize the credentials and authentication. 
 
For @product@ users to access the external repository, their screen names and
passwords must be the same in @product@ and in SharePoint. For details on adding
and managing @product@ users, refer to [User Management](/discover/portal/-/knowledge_base/7-0/user-management).

Next, you must create a SharePoint repository configuration. 

### Step 5: Creating a New SharePoint Repository Configuration

To connect to a remote SharePoint server you must create a repository 
configuration.

Follow these steps:

1.  Open the Control Panel and go to *Configuration* &rarr; *System Settings* 
    &rarr; *Collaboration* &rarr; *Sharepoint OAuth2*

2.  Click the *Add* icon (![Add](../../../images-dxp/icon-portlet-add-control.png)) 
    to create a new configuration.

3.  In the new Repository form specify values for the following fields 
    (your SharePoint administrator can provide you with this information):

    **Name:** The configuration's name.

    **Authorization grant endpoint:** The URL used to request OAuth2 
    authorization grants. If you're using SharePoint Online, this should look 
    similar to this URL: `https://[site name]/sharepoint.com/_layouts/oauthauthorize.aspx`.
    
    **Authorization token endpoint:** The ACS URL. If you're using SharePoint 
    Online with Azure ACS, the URL has the following pattern: 
    `https://accounts.accesscontrol.windows.net/[App ID]/tokens/OAuth/2`.

    **Client ID:**  The client ID.

    **Client Secret:** The client secret.

    **Scope:** The permission set required for your tokens. Valid scopes are 
    configured during your app's SharePoint registration.

    **Tenant ID:** The Tenant ID. If you're using SharePoint Online, you can use 
    the same App ID you embedded in the *Authorization token endpoint*.

    **Site domain:** The site domain registered for your application.

    **Resource:** This value depends on the ACS service you use. If you're using 
    SharePoint Online with Azure ACS, the value is similar to this: 
    `00000003-0000-0ff1-ce00-000000000000/[site name].sharepoint.com@[tenant ID]`.
    
4.  Click *Save*.

Now that your SharePoint repository is configured, you can mount it into 
@product@'s Documents and Media Library. 

## Add SharePoint as a Liferay Documents and Media repository [](id=add-sharepoint-as-a-liferay-documents-and-media-repository)

It's time to add a SharePoint Library repository type to Documents and Media so 
users can work with SharePoint in @product@. 

Follow these steps:

1.  Add the Documents and Media application to a page, if you haven't done so
    already.

2.  From the home location in the Documents and Media application, click the
    *Add* icon (![Add](../../../images-dxp/icon-portlet-add-control.png)) and 
    select *Repository*. The  New Repository screen appears. 

3.  Select the repository type for the SharePoint OAuth2 configuration you 
    created. For example, if your configuration is nammed *Foo*, the repository 
    type is listed as *SharePoint (Foo)*.

4.  Specify values for the following fields:

    **Site Absolute URL**: Resolves relative URLs. If you're using SharePoint 
    Online the value is similar to this: `https://[site name].sharepoint.com`.

    **Library Path**: A relative path from the *Site Absolute URL* that points 
    to the SharePoint Document Library you want to mount in Documents and Media 
    (for example, *Shared Documents*).

    ![Figure 3: The Repostiory Configuration form is where you specify access to the SharePoint Library you want to use.](../../../images-dxp/sharepoint-repo-configuration-form.png)

5.  After you've finished entering any additional options, click *Save*.

Your Documents and Media Library is now connected to the SharePoint repository.
The new external repository is now listed in the Documents and Media home. 

+$$$

**Note:** The first time you access a mounted SharePoint repository, it 
redirects you to your SharePoint instance, where you must log in and grant 
permissions to @product@ to access the repository.

$$$

Now that you've added a SharePoint Repository to Documents and Media, you can
access and modify SharePoint Library files from within @product@'s Documents and
Media Library!

## Related Articles [](id=related-articles)

[Publishing Files](/discover/portal/-/knowledge_base/7-0/publishing-files)

[Administering Liferay Sync](/discover/portal/-/knowledge_base/7-0/administering-liferay-sync)

[Repository Types](/discover/portal/-/knowledge_base/7-0/repository-types)
