# Configuring Site Settings [](id=configuring-site-settings)

You can access Site Settings by navigating to the Site Administation dropdown
menu and selecting *Configuration* &rarr; *Site Settings*.

![Figure 4: The Site Settings window offers a plethora of options for your site.](../../../images/site-settings-overview.png)

You'll find options to organize site content, manage site users, and improve
your site's overall intelligence. You'll explore the Site Settings categories
available, which are available from the *General*, *Social*, *Languages*, and
*Advanced* tabs, and simulate configuring those settings for your sample Lunar
Resort site.

## Organizing Site Content [](id=organizing-site-content)

Organizing your site's content drastically improves the usability of your site
for users. The Site Settings menu offers some configuration options that aid in
your organizational goals.

### Categorization [](id=categorization)

*Categorization* helps administrators organize the site and allows for users to 
easily find your site and its content through search and navigation. For more 
information on using tags and categories, visit the
[Organizing Content with Tags and Categories](/discover/portal/-/knowledge_base/7-1/organizing-content-with-tags-and-categories) section.

### Site Template [](id=site-template)

The last tab listed under the Basic Information category is *Site Template*. If
you created your Lunar Resort site as a Blank Site, this option is not available
in Site Settings. If you did, however, create your site using a site template,
this section displays information about the link between the site template and
the site. Specifically, you can see which site template was used and whether or
not it allows modifications to the pages inherited from it by site
administrators. To learn more about site templates and how to create your own,
see the [Building Sites from Templates](/discover/portal/-/knowledge_base/7-1/building-sites-from-templates)
section.

### Content Sharing [](id=content-sharing)

If you select the *Content Sharing* tab from the Advanced tab, you can
configure whether sub-sites can display content from this site. Administrators
of this site's sub-sites can use all structures, templates, categories,
application display templates, etc. from this parent site. Even if you
initially allowed content sharing between the parent site and its sub-sites,
you're able to disable this option and immediately revoke content sharing from
all sub-sites.

### Recycle Bin [](id=recycle-bin)

The *Recycle Bin* option under the Advanced tab provides the option to
enable/disable the Recycle Bin for your site. You can also regulate the age (in
minutes) for which content is able to be stored in the Recycle Bin until it is
permanently deleted. For a full explanation of the Recycle Bin, see the
[Restoring Deleted Assets](/discover/portal/-/knowledge_base/7-0/restoring-deleted-assets)
section.

### Custom Fields [](id=custom-fields)

*Custom Fields* lets you edit the custom fields you already have configured
for the Site resource. If you don't have any custom fields configured for the
Site resource, you can navigate to the Control Panel &rarr; *Custom Fields*
located under the *Configuration* tab. The Custom Fields tab does not display in
Site Settings unless you have existing custom fields. For more information on
Custom Fields, see the
[Custom Fields](https://dev.liferay.com/discover/portal/-/knowledge_base/6-2/custom-fields)
section.

<!-- TODO: replace link above. -Cody -->

### Documents and Media [](id=documents-and-media)

The last option that relates to organizing your site's content is *Documents and
Media*, which is found under General. This lets you enable/disable Directory
Indexing, which allows site administrators to browse your site's documents and
media files and folders. For example, a site administrator of a site called
*Lunar Resort* can browse documents at
http://localhost:8080/documents/lunar-resort if this option is enabled.

Now that you can organize your site's content using Site Settings, move on to
the next section to learn how to manage site users.

### Managing Site Users [](id=managing-site-users)

An always important job for site administrators is managing site users. There
are configuration options in Site Settings that allow for easier user
management.

### Details [](id=details)

*Details* is the leading option under the General tab, which provides
the same menu you filled out when first creating your Lunar Resort site. This
allows an administrator to change the description and membership type of a site.
The membership type can be set as open, restricted, or private based on the
privacy needs of the site. Users can join and leave an open site at will. To
join a restricted site, a user has to be added by the site administrator. A user
can also request to be added through the Sites section of the Control Panel. A
private site is like a restricted site but doesn't appear in the Sites section
of the Control Panel for users who aren't members.

You also have the ability to organize sites into hierarchies. At the bottom of
the Details sub-section is the Parent Site section. This feature allows you to
select the parent site for the site you're currently on. After selecting a
parent site, you have a checkbox option to limit membership to members of the
parent site.

### Default User Associations [](id=default-user-associations)

Once you have the basic details of your site saved, you can begin assigning your
users to roles and teams. *Default User Associations*, the leading option when
opening the Advanced tab, lets you configure site roles and teams that newly
assigned site members will have by default. If you'd like to learn more about
creating roles and/or teams, visit the
[Roles and Permissions](/discover/portal/-/knowledge_base/6-2/roles-and-permissions)
and
[Creating Teams for Advanced Site Membership Management](/discover/portal/-/knowledge_base/7-0/creating-teams-for-advanced-site-membership-management)
sections, respectively.

### Ratings [](id=ratings)

To allow your site's users to rate content in your site, you can use the
*Ratings* option to select what ratings type to use for applications like
Documents and Media, Web Content, Comments, etc. Ratings types include Stars,
Likes, and Thumbs. This is the leading option when opening the Social tab.

<!-- TODO Is there a link we can point to for this? Coordinate with Jim. -Cody
-->

### Mentions [](id=mentions)

The last configuration option in Site Settings related to managing users is
*Mentions*. *Mentions* is found under the Social tab. This option allows you to
enable/disable the Mentioning functionality, which is used to *mention* (notify
and/or draw attention to) friends and colleagues by entering the "@" character
followed by their user name. You can learn more about the mentioning feature by
visiting the
[Mentioning Users](/discover/portal/-/knowledge_base/7-0/mentioning-users)
article.

Now that you're aware of your Site Settings abilities to improve User and
Content management, you'll learn how to improve your site's overall intelligence
using Site Settings.
