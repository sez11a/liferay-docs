# Improving Site Intelligence [](id=improving-site-intelligence)

Site Settings offers several miscellaneous configuration options that improve
your site's usability, overall display, and data tracking. To make your Lunar
Resort site smarter, explore and configure the options below.

## Site URL [](id=site-url)

One of the most important aspects of your site is the simplicity of your site
URLs. Long obscure site URLs are a hassle for users to deal with, and can
negatively affect your site's probability of being featured by search engines.
Having a human-readable friendly URL assists indexing bots and is critical to
good search engine optimization. For the Lunar Resort site, you can select the
*Site URL* tab to set a friendly URL and/or a virtual host. The *Friendly URL*
option lets you manage the path to your site in the instance's URL. Friendly
URLs are used for both public and private pages. For public pages, the friendly
URL is appended to http://localhost:8080/web. For private pages, the friendly
URL is appended to http://localhost:8080/group. Each friendly URL needs to be a
unique name, of course.

For example, suppose you want to set a friendly URL for your Lunar Resort site.
If you set the friendly URL of your instance's default site to /lunar-resort,
the URL of your default site's public home page would change to
http://localhost:8080/web/lunar-resort/home. If your instance's default site had
private pages, the URL of the default private home page would change to
http://localhost:8080/group/lunar-resort/home.

Note that if you're adding a friendly URL for your instance's home page, you
should update your instance's Home URL field so that page requests to
http://localhost:8080 redirect properly. To do this, navigate to the
*Configuration* &rarr; *Instance Settings* page of the Control Panel and find the
Home URL field in the Navigation section. For the Lunar Resort example, you
would enter */web/lunar-resort/home* into the Home URL field. Once you've
entered this setting, page requests to localhost:8080 will redirect to the
friendly URL of your Liferay instance's new homepage:
http://localhost:8080/web/lunar-resort/home.

The other setting you can configure under the Site URL tab is *Virtual Hosts*,
which makes web navigation much easier for your users by connecting a domain
name to a site. This allows you to define a domain name (i.e.,
www.lunar-resort.com) for your site. This can be a full domain or a subdomain.
This enables you to host a number of web sites as separate sites on one Liferay
server.

For instance, if you set this up for the Lunar Resort's development network,
users in that site could use *developers.lunar-resort.com* to get to their site,
provided that the Lunar Resort instance's network administrators created the
domain name and pointed it to the Liferay server.

To set this up, the DNS name *developers.lunar-resort.com* should point to your
instance's IP address first. Then enter *http://developers.lunar-resort.com* in
the Virtual Host tab for the Developers site. This helps users quickly access
their site without having to recall an extended URL. The *Site URL* option is
listed under the General tab.

## Analytics [](id=analytics)

Analyzing site traffic is another huge advantage for site administrators that
want to monitor what content is most popular. @product@ includes built-in
support for Google Analytics, allowing administrators to make use of Google's
tool set for analyzing site traffic data. When you sign up for Google Analytics,
a snippet of code is provided which needs to be added to your web pages to allow
Google's system to register the page hit. It can be a tedious process to add
this code to every page on a site, especially if it's a large site and there is
a lot of user-generated content.

This problem can be solved in Liferay by putting Google's code into a custom
theme written specifically for the web site on which Liferay is running. Doing
this, however, requires a theme developer to make specific changes to the theme
and it prevents users from using the many themes that are freely available for
@product@ "out of the box."

Because of this, support for Google Analytics has been built into @product@, and
can be turned on through a simple user interface. This allows Liferay
administrators to make use of Google Analytics on a site by site basis and turn
it on and off when needed. You can sign up for Google Analytics at the Google
Analytics site here:

[http://www.google.com/analytics](http://www.google.com/analytics)

To enable Google Analytics support, navigate to the *Analytics* tab in Site
Settings, which loads a very simple form, pictured below.

![Figure 5: Setting up Google Analytics for your site is very easy: sign up for Google Analytics, receive an ID, and then enter it into the Google Analytics ID field.](../../../images/maintaining-google-analytics.png)

Enter your Google Analytics ID (which should have been provided to you when you
signed up for the service) in the field and click *Save*. All the pages in the
site you selected will now have the Google Analytics code in them and will be
tracked. 

To enable a different analytics service, navigate to *Configuration* in the
Control Panel, and then go to *Instance Settings* &rarr; *Miscellaneous*. You
can enter the name of any additional service you want to add in the *Analytics*
field provided. Once you have entered the name, go to the *Site Settings*
&rarr; *Advanced* &rarr; *Analytics* page for the site where you wish to add
analytics. Copy the JavaScript tracking code provided by your analytics
platform into corresponding field for your service. Now all pages on the
selected site contain the tracking script and will send analytics data to your
analytics platform. 

This is a fairly simple procedure, and it gives you the ability to take
advantage of some great tools to help you visualize who's coming to your site
and from where. The *Analytics* option is listed under the Advanced tab.

## Maps [](id=maps)

The *Maps* options lets you configure the maps API provider used by your Liferay
instance when displaying geolocalized assets. Geolocalized assets can be
displayed for documents, web content articles, DDL records, etc. Maps is
available under the Advanced tab.

<!-- TODO: Provide link to where Maps feature is documented once it's available.

This is Jim's area. Good blog on this:
https://www.liferay.com/web/juan.fernandez/blog/-/blogs/adding-geolocation-support-to-liferay-7-0
-Cody -->

## Languages [](id=languages)

The *Languages* option lets you configure the language options for your
site. This is the leading option when opening the Languages tab. You have
options to use the default language options or define a custom default language.

Now that you know how to configure sites, you'll learn how to customize your
personal sites.
