# Customizing Page Options [](id=customizing-page-options)

The next level of configuration under Sites is pages. When it comes to pages, 
you can handle configuration at the individual page level or configure a set of 
public or private pages all at once.

One example of how this hierarchy flows down is themes. You can configure a 
theme at the Site level, which applies to all pages within a Site. You could 
then configure a different theme for the Private Pages set which is different 
than what's specified for the whole site. On top of that, you could also 
configure a different theme for a specific page that is different than the 
master site configuration or the configuration for the page set that it belongs 
to.

Many configuration options are like this, so, when configuring pages, it's 
important to be aware of the specific context you're in, so that you don't 
configure an option that you intended for the whole site just on one page --- 
or change the whole site's configuration, when you meant to change it for only 
the particular page set.

## Configuring Page Sets

To configure options for the entire page set, select *Configure* from the menu 
in the top right corner of *Site Pages*. This page defaults to *Look and 
Feel*. On this tab appears an interface for choosing a theme for the current
site. Themes can transform the entire look of the portal. They are created by
developers and are easily installed using the Liferay Marketplace. Since you
don't have any themes beyond the default one installed yet, you'll use the
default theme for your pages.

![Figure 1: The Look and Feel interface allows you to choose a theme for the current site.](../../../../images/look-and-feel-pages.png)

You can apply themes to individual pages as well by selecting *Configure*
&rarr; *Define a specific look and feel for this page* option under the page's
*Look and Feel* category. 

![Figure 2: You can define a specific look and feel for a page.](../../../../images/define-a-specific-look-and-feel.png)

Many themes include more than one color scheme, which keeps the existing look
and feel while giving the site a different flavor. The Color Schemes option
is not available for the default theme.

There are a few more configurable settings for your theme. You can switch the
bullet style between dots and arrows and you can choose whether or not to show
maximize/minimize application links by default. The *CSS* section has an editor
for custom CSS for tweaking your theme. 

## Using a Custom Logo for a Site [](id=using-a-custom-logo-for-a-site)

By default, the Liferay logo is used for your site pages' logo. If you want to
use your own logo for a specific site, use the *Logo* tab. Adding a custom logo
is easy: select the *Logo* tab from the *Configure* interface and browse
to the location of your logo. Make sure your logo fits the space in the top left
corner of the theme you're using for your web site. If you don't, you could wind
up with a site that's difficult to navigate, as other page elements are pushed
aside to make way for the logo.

In the logo tab, you can also choose whether or not to display the site name on
the site. If you check the box labeled *Show Site Name*, the site name appears
next to the logo. This option is enabled by default and cannot be disabled if
the *Allow Site Administrators to set their own logo* option is disabled in
*Instance Settings*. Removing the site name is not available for the default
site--you can configure this only for new sites and user pages. 


## Personalizing Pages [](id=personalizing-pages)

Administrators can designate pages or sections of Widget Pages to be customizable. When
a user visits such a page, a notification appears stating that the user can
customize the page. Users can make customizations only in the sections of pages
designated by administrators. Customizations are based on the rows and columns
of a page layout. Page customizations are only visible to the user who made the
customizations. By default, site members can make page customizations but
non-site members and guests can't.

To enable page customizations as an administrator, first click *Configure Page*
from the *Options* button next to the Page you'd like to let site members
modify. Then select the *Advanced* tab at the top of the page, expand the
*Customization Settings* area, and click on the *Customizable* selector button. 

![Figure 2: To enable page customizations, click on the *Configure Page* button next to the page, expand the *Customization Settings* area, and click on the *Customizable* button.](../../../../images/page-customizations.png)

Once you've enabled the *Customizable* selector, select the sections of
the page you'd like to enable customization for, depending on the layout
template of your page. Enable one or more of the *Customizable* sections to
allow site members to customize certain sections of the page. Regions that
you've designated as customizable are colored blue.

When site members visit your customizable page, they'll see an extended Control
Menu with a notification saying *You can customize this page*. Site members can
toggle whether to view or hide the customizable regions. If you toggle the
selector to view customizable regions, the regions on the page are color-coded
to help distinguish customizable vs. non-customizable sections of the page.

![Figure 3: Customizable regions are colored green and non-customizable regions are colored red.](../../../../images/color-coded-customizable-regions.png)

Site members can also choose between viewing their customized page and viewing
the default page by selecting the *Options* button
(![Options](../../../../images/icon-options.png)) from the Control Menu and
clicking the *View Page without my customizations* or *View My Customized Page*.

There's also a *Reset My Customizations* option available from the *Options*
button that restores a user's customized page to match the default page. This
allows users to discard one set of customizations and start a new set without
having to undo each customization that they'd previously made manually. 

Note that non-administrator site members can access the Add menu from the top
right side of the screen when viewing their customizable page even if they don't
ordinarily have permission to view this menu. This allows them to add apps to
the sections of the page that they're allowed to customize. If they click *View
Page without my customizations*, the Add menu disappears from the menu since
they're not allowed to modify the default page.

Administrators of customizable pages have the same two views as site members:
the *default page* view and the *customized page* view. Changes made to the
default page affect all users, whereas changes made to the customized page
affect only the administrator who made the changes. Changes made by
administrators to non-customizable sections in the default view are immediately
applied for all users. Changes made by administrators to customizable sections,
however, do *not* overwrite users' customizations.

Users can make two kinds of customizations to customizable regions. First, they
can configure applications within the customizable regions. Second, they can add
apps to or remove apps from the customizable regions.

![Figure 4: Customizable areas are highlighted green when organizing apps on the page.](../../../../images/customizable-regions.png)

Users can't change a non-instanceable app's configuration inside a customizable
region since those kinds of apps are tied to the site where they've been added.
If this were allowed, the customization would affect all users, not just the one
who customized the region. Therefore, changes to the app configuration in
a customizable region are only possible for instanceable apps, whose app
configuration only affects that one user.

For example, suppose that you, as an administrator, selected the right column of
the Welcome page of the Lunar Resort site to be customizable. A member of the
Lunar Resort site could take the following steps to make a personal
customization of the Welcome page:

1.  Navigate to the Welcome homepage by clicking the Site Administration's *Site
    Selector* button (![Compass](../../../../images/icon-compass.png)) and
    selecting the Lunar Resort site.

2.  Add the Language Selector app to the right column of the page by clicking
    the *Add* icon, clicking on *Applications*, searching for *Language
    Selector*, and clicking *Add* next to its name.

The Language Selector application is useful to have on your home page if you
expect users who speak different languages to access your instance. Users can
select their language in the Language Selector app to view a translation of your
site into their native language. After closing the Configuration dialog box of
the Language Selector app, the customized Welcome page looks like this:

![Figure 5: In this example, the user added the Language app, and changed the display style from icons to a select box.](../../../../images/customized-portal-homepage.png)

To allow users to customize a page, administrators must grant users permission
to *Customize* pages under the Site section. This can be achieved by assigning
permission to a role, then assigning this role to the appropriate users. For
example, if you want any logged user to be able to customize your customizable
pages, you could assign the *Customize* permission to the role *User*. If you
want site members to be able to customize the customizable pages of their sites,
you would accept the default setting. By default, the *Customize* permission is
assigned to the role *Site Member*.

In addition to granting the ability to customize app configurations, the
*Customize* permission allows users to customize the look and feel of apps
and to import or export app settings. Next, you'll look at how to change page
permissions.

## Changing Page Permissions [](id=changing-page-permissions)

By default, public pages are just that: public. They can be viewed by anybody,
logged in or not logged in. And private pages are really only private from
non-members of the site. If someone has joined your site or is a member of your
organization, that person can see all the private pages. You can, however,
modify the permissions on individual pages in either page group so only certain
users can view them.

Suppose you want to create a page only for administrators to see. You can do it
this way:

1.  Go to your site's Site Administration dropdown and select *Navigation*
    &rarr; *Private Pages*. If you don't have the *Private Pages* option
    available, select the *Options* button next to Public Pages and click *Add
    Private Page*. Remember, these pages by default are viewable only by members
    of the site.

2.  Create a page called *Admin Tips*.

3.  Click *Configure Page* from the Options button dropdown for the page in the
    left menu.

4.  Select *Permissions* from the *Options* icon
    (![Options](../../../../images/icon-options.png)) in the top right corner of
    the screen.

4.  Uncheck the *View* and *Add Discussion* permissions next to the Site Member
    role.

5.  Click the *Save* button.

![Figure 12: The Permissions offer a plethora of options for each role.](../../../../images/web-content-page-permissions.png)

Congratulations! You've changed the permissions for this page so only site
administrators can view it. Any users you add to this role can now see the page.
Other users, even members of this site, won't have permission to see it.

Pages are as flexible as pages you'd create manually without a Liferay instance.
Using a point and click interface, you can define your site any way you want.
You can create and remove pages, export and import them, set their layouts,
define how they are indexed by search engines, and more.

You now understand how to manage pages. It's time to move on to further
customizing those pages for mobile devices and building standardized pages using
custom templates.

## Executing JavaScript in Site Pages [](id=executing-javascript-in-site-pages)

If you click on *Advanced* for a page set (either Public Pages or Private
Pages), a JavaScript editor appears. This code is executed at the bottom of
every page in the site. If your site's theme uses JavaScript (as is usually the
case), it's best to add custom JavaScript code to the theme and *not* here.
This way, all of your site's JavaScript code remains in one place.

This may be useful if your site's theme does *not* use JavaScript. In this
case, you can place *all* of your site's JavaScript here.

## Rendering Pages for Mobile Devices [](id=rendering-pages-for-mobile-devices)

Select *Advanced* &rarr;  *Mobile Device Rules* to configure your page set for
mobile devices. Mobile device rules are inherited from your Public Pages, but
you can define specific rules per page. You can edit the Look and Feel of
specific pages for mobile devices, including the theme. The set up for this
option is explained in the 
[Displaying Site Pages to Mobile Devices](/discover/portal/-/knowledge_base/7-0/displaying-pages-on-mobile-devices)
section.

## Configuring Rules for Virtual Hosting [](id=configuring-rules-for-virtual-hosting)

If you're using virtual hosting for this site, selecting the *Robots* option
from the *Advanced* tab configures `robots.txt` rules for the domain: both its
public and private pages. If you're not using Virtual Hosting, this tab is
rather boring.

## Notifying Search Engines of Site Pages [](id=notifying-search-engines-of-site-pages)

If you select the *Sitemap* option from the *Advanced* tab for a page set, you
can send a sitemap to some search engines so they can crawl your site. It uses
the industry standard sitemap protocol. 

Select a search engine link to send the sitemap to it. It's only necessary to
do this once per site.

If you're interested in seeing what is sent to the search engines, select the
*preview* link to see the generated XML.

Next, you'll learn how to customize individual site pages.
