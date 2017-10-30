# Using a SharePoint Repository [](id=using-a-sharepoint-repository)

Liferay's Sharepoint Connector app lets users access SharePoint 2013 and 
SharePoint 2016 libraries from @product@'s Documents and Media Library. Once 
installed, you can add a SharePoint repository type to @product@'s Documents and 
Media Library to access your SharePoint files.

+$$$

The Liferay Sharepoint Connector uses Azure ACS with OAuth 2 for SharePoint 
server authorization. So **you must enable HTTPS support in your app server to 
use the Liferay SharePoint Connector.** Please consult your app server's 
documentation for details. For instance, the required steps for 
Tomcat can be found in their [documentation](https://tomcat.apache.org/tomcat-8.0-doc/ssl-howto.html).

$$$

The [Liferay Sharepoint Connector](https://web.liferay.com/marketplace/-/mp/application/15188537)
app is available to EE subscribers on Liferay's [Marketplace](http://marketplace.liferay.com/).

The Liferay Sharepoint Connector provides these key benefits: 

- Reading/writing documents and folders
- Document check-in, check-out, and undo check-out
- Downloading documents
- Moving folders and documents within the repository
- Getting revision history
- Reverting to a revision

The Liferay SharePoint Connector uses SharePoint's API, which has the 
limitations shown below:

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

To use a SharePoint repository in Documents and Media, you must first create 
an application in SharePoint and authorize it to access the repository. The 
guides in this section walk you through this process.
