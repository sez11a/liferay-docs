# Using a SharePoint Repository [](id=using-a-sharepoint-repository)

With the help of Liferay's Sharepoint Connector app, users can access SharePoint 
2013 and SharePoint 2016 libraries from within a @product@ Documents and Media 
Library. The app installs a SharePoint repository type that you can select when 
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
- Comments, ratings, and using a SharePoint folder as a Documents and Media root 
  folder are unsupported. 

The Liferay Sharepoint Connector uses OAuth 2 for authorization against a 
SharePoint server, using the Azure ACS. So, **client applications 
must use @product@ through HTTPS.** 

To use a SharePoint repository inside Documents and Media, you must first create 
an application in SharePoint and authorize it to access the repository.

## Creating a SharePoint Application

Follow these steps to create a SharePoint application:

1.  Go to your SharePoint installation's URL 
    (`https://[name].sharepoint.com/_layouts/15/appregenw.aspx` for example) and 
    click the two *Generate* buttons to generate a client ID and client secret.

2.  Provide the following information:

    **Title:** The name displayed in Documents and Media. This should be self 
    explanatory.

    **Domain Name:** The application's domain name along with the port 
    (e.g. `localhost:8228`)

    **redirect URL:** The application's URL. **The URL must use HTTPS.**

    An example configuration is shown below:

    - **Client ID:** `1234a56b-7890-1234-5ccc-67d8ea9b0c1c`
    - **Client secret:** `1ABCDEfGh2IJKLmNoP3QrStuvwX41YzAB+CDEFg20G3=`
    - **Title:** `My Application's Title`
    - **App Domain:** `localhost:8228`
    - **Redirect URL:** `https://localhost:8228/c/document_library/sharepoint/oauth2`

3.  Next, you must grant permissions to your new SharePoint application. You can 
    access this at your SharePoint installation's ? URL 
    (`https://[name].sharepoint.com/_layouts/15/appinv.aspx` for example).

    In the `APP ID` field, put the application's `Client ID` and click *Search*. 
    This fills in all the information except *Permission Request XML*.
    
4.  Paste the following XML into the *Permission Request XML* field and click 
    *Create*:

        <AppPermissionRequests>
            <AppPermissionRequest
                scope="http://sharepoint/content/sitecollection/web/list"
                Right="Write" 
            />
            <AppPermissionRequest
                scope="http://sharepoint/search"
                Right="QueryAsUserIgnoreAppPrincipal" 
            />
        </AppPermissionRequests>

    The XML above grants your application write and search permission over the 
    SharePoint instance.

    +$$$

    **Note:** @product@ only supports write and search permissions for SharePoint.

    $$$

5.  Next, go to *Settings* &rarr; *Site App Permissions* in your SharePoint 
    installation. You can also access permissions from your SharePoint 
    installation's ? URL 
    (`https://[name].sharepoint.com/_layouts/15/appprincipals.aspx?Scope=Web` 
    for example). Note your application's *app identifier* 
    (`i:0i.t|ms.sp.ext|6123d38d-2998-4972-9aaa-71a4da9f3a5a@b9c24ab3-ad34-4943-ab57-729d8c329053` 
    for example). You'll use this to configure the SharePoint connector. 

Now that your SharePoint application is created, you can set up the environment 
next.

## Environment Setup [](id=environment-setup)

These steps are required to configure your host, @product@, and SharePoint 
installation to use SharePoint from @product@'s Documents and Media Library:

1. Enable Versioning Support on the SharePoint library <!-- may not be needed, see googledoc -->
2. Create a New SharePoint Repository Configuration

Note, these instructions are geared towards @product@ and SharePoint system
administrators.

Before you can use SharePoint as an external repository with @product@, you must
configure the SharePoint host and the SharePoint server application. 

### Step 1: Enable Versioning Support in the SharePoint Library [](id=step-2-enable-versioning-support-in-the-sharepoint-library)

<!-- Again, note this step may not be required. Pending answer from Adolfo in googledoc -->

You must enable versioning in SharePoint library for @product@'s check-in/out
features to work with SharePoint. To enable versioning, follow these steps: 

1.  Open a browser to the SharePoint library's URL.
2.  Click on the SharePoint library's name.
3.  At the top of the toolbar, go to *Liferay Tools* &rarr; *Library*. 
4.  In the toolbar, select *Library Settings*.
5.  Under *General Settings*, click on *Versioning settings*.
6.  In *Document Version History*, select *Create major and minor (draft)
    versions*. 
7.  Select *Yes* in *Require Check Out*.

You've set SharePoint to accept versioning requests from @product@.

Next, you'll configure authentication for @product@. 

### Step 2: Creating a New SharePoint Repository Configuration

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
