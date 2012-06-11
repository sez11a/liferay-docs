# Using Liferay Web Content Management [](id=using-liferay-web-content-management)

Web Content Management is a system which allows non-technical users to publish content to the web without having advanced knowledge of web technology or programming of any sort. Liferay WCM empowers you to publish your content with a simple point and click interface, and it helps you to keep your site fresh. You'll find yourself easily creating, editing, and publishing content within just a few minutes of being exposed to its features. But Liferay WCM doesn't sacrifice power for simplicity. If need be, you can use your developer skills to create complex presentation layer templates that make your content "pop" with dynamic elements. Once these templates have been deployed into the portal, your non-technical users can manage content using these templates as easily as they would manage static content. All of this makes Liferay WCM an appropriate choice for sites with only a few pages or sites with gigabytes of content. 

In this chapter, we'll cover the following topics: 

- Features of Liferay WCM 
- Simple content creation 
- Publishing content 
- Structures and Templates 
- Permissions 
- Tags and Categories 
- Staging and Workflow 

As you'll see, Liferay's WCM is a full-featured solution for managing your web site. We'll start with an overview of what it has to offer, and then we'll dive down into its features. 

## How can Liferay's WCM help you? [](id=how-can-liferay-s-wcm-help-you-)

With Liferay's WCM you have the ability to create, edit, stage, publish, and approve content with easy to learn yet powerful tools. Liferay's WCM streamlines site changes for the end user versus doing a site in HTML. Some ways Liferay WCM makes this possible include:

-   Once set up, non-technical users can manage the site.
-   Liferay's fine-grained permissions system ensures your content gets to the right users.
-   To manage the site, no programming is required.
-   Content can be staged.
-   Content can be passed through a workflow.
-   Content can be published on a schedule.
-   WCM is integrated with Liferay's services, so advanced template developers can use them to query for data stored elsewhere in Liferay.

Once you get familiar with Liferay WCM, you'll wonder how you ever got along without it.  

### What Features Does Liferay WCM Have? [](id=lp-6-1-ugen03-what-features-does-liferay-wcm-have-0)

Liferay's WCM has a host of features the makes managing the content of your site easier.

-   **WYSIWYG Editor:** A complete HTML editor that allow you to modify fonts, add color, insert images and much more.

-   **Structure Editor:** Easily add and remove fields you want available to content creators and then dynamically move them around. This editor includes an entire suite of form controls you can drag and drop onto your structure.

-   **Template Editor:** Import template script files that inform the system how to display the content within the fields determined by the structure.

-   **Web Content Display:** A portlet that allows you place web content on a page in your portal.

-   **Asset Publisher:** A portlet which can aggregate different types of content together in one view.

-   **Scheduler:** Lets you schedule when content is reviewed, displayed, and removed.

-   **Workflow Integration:** Run your content through an approval or review process.

-   **Staging:** Use a separate staging server or stage your content locally so you can keep your changes separate from the live site. 

Liferay's Web Content Management is a powerful and robust tool for creating and organizing content on your web site. Now that you've seen the basics of what you can do with Liferay's WCM, let's apply some of these concepts and create some content.

## Creating sites and managing pages [](id=building-a-site-with-liferay-s-w-3)

You've just been assigned the task to build a web site for an innovative new social networking site called Nose-ster. You've decided to take advantage of Liferay Portal and its rapid deployment features as well as its ability to get a fully functional, content-rich web site with integrated social features up and running in little time.

We'll walk through the creation of Nose-ster's web site, starting by creating and publishing some simple content using Liferay's built-in WYSIWYG editor. We'll then take advantage of Liferay's robust structure editor. We'll use templates to display the content and then explore some of the advanced publishing features such as the built-in workflow and Asset Publisher.

### Creating content the simple way [](id=lp-6-1-ugen03-creating-content-the-simple-way-0)

As we've stated above, content is the reason web sites exist. Liferay Portal has made it easier than ever to get content published to your site. Because Liferay Portal is so flexible, you can use basic authoring tools right away or take advantage of the more advanced features. It's adaptable to your needs.

We'll begin by creating simple content using Liferay's WYSIWYG Editor and then we'll publish it to the home page of Nose-ster's web site. This a fast and straightforward process that demonstrates how easy it is to create and publish content onto your Liferay Portal. So let's get familiar with the Web Content section of the control panel so we can create and publish our first pieces of content.

![Figure 3.1: Choosing a Site in the Content Section](../../images/04-web-content-context-dropdown.png)

When you manage web content from the control panel, you can select the location where the content resides. For instance, you can add content that's available to a specific site or globally across the portal. The Content section of the control panel displays as its heading the name of the site you're currently working on. This heading is clickable: you can change where you're working by using the drop down attached to the heading. We will add our first piece of content to the *Nose-ster* site, which we defined earlier in the chapter as the default site.

#### Rich, WYSIWYG Editing [](id=lp-6-1-ugen03-rich-wysiwyg-editing-0)

Once you have the Nose-ster site selected, click on the *Web Content* link in the control panel. Next, click the *Add* button under the *Web Content* tab. This is a highly customizable form that by default has two fields: a title and a powerful WYSIWYG editor. We could customize this form to contain whatever fields our content needs, but let's keep things simple for now. We'll cover more advanced features such as structures, templates, and content scheduling later in this chapter.

For now, type the words *Welcome to Nose-ster* in the *Name* field. Notice that content can be localized in whatever language you want. If you click the *localize* check box, two select boxes appear which allow you to pick the language you're working in and the language that is the default. You can enter translations of your content for any language in the list. The screenshot below shows this interface but for now, we won't be using it, so you can leave it unchecked. In the content field, add a short sentence announcing that the web site is up and running.

![Figure 3.2: The Web Content Editor](../../images/04-web-content-wysiwyg.png)

Getting a new web site up and running is an exciting step for anyone, whether it is a large corporation or a small non-profit charity. To celebrate this momentous achievement at Nose-ster, let's give our announcement some of the pomp and circumstance we think it deserves!

Using the editor, select all the text and then change the style to Heading 1 and the color to dark blue.

You could insert an image here as well as more text with a different style, as demonstrated in the screenshot below. You can also add bullets, numbering, links to another site, or custom images. You can even add an emoticon. Let's add a smiley face at the end of our announcement.

![Figure 3.3: Customizing Content](../../images/04-web-content-example2.png)

The WYSIWYG editor is a flexible tool that gives you the ability to add text, images, tables, links, and more. Additionally, you can modify the display to match the purpose of the content. Plus it's integrated with the rest of Liferay Portal: for example, when you upload an image to be added to a page, that image can be viewed and manipulated in the Documents and Media portlet.

If you're HTML savvy, Liferay WCM doesn't leave you out in the cold. You can click the *Source* button and write your own HTML if you wish.

###Managing Pages in Liferay Portal [](id=managing-pages-in-liferay-portal)

With most products, you would learn what the software can do in terms of setting up your users and security model, and then start building your system. You'd design your infrastructure and get your server environment up and running while your developers write the applications that live on your web site. With Liferay Portal, however, you start farther ahead. Liferay Portal is more than just a *container* for applications with a robust security model. It already includes many of the applications you'll need, out of the box, ready to go, and integrated with all the user management and security features you've already learned about.

Perhaps the key application that ships with Liferay is Liferay's Web Content Management system (WCM). We call it the key application because it's the one that almost everybody uses, all web sites have content. Liferay's WCM empowers you to manage all the content on your site quickly, easily, and in the browser. Beyond managing existing content, Liferay WCM lets users easily create and manage everything from a simple article containing text and images to fully functional web sites. Web publishing works alongside Liferay Portal's larger collection of applications, which means that you can add shopping cart functionality, visitor polls, web forms, site collaboration tools, and more. Everything is done with our collection of easy-to-use tools with familiar rich-text editors and intuitive interface.

This chapter covers all aspects of Liferay WCM, including:

- Page types 
- Layouts 
- Page and content permissions
- Importing and exporting content 
- Content creation and editing 
- Staging 
- Content publishing 
- Structures and templates 
- WCM Workflow 
- Asset publisher

By the time we're done, you should be able to apply all these concepts to your own content. To demonstrate Liferay's Content Management features, we'll create and manage content on the portal for *Nose-ster*, a new social network where people are connected based on what their noses look like.

First, a little housekeeping. If we're going to be *Nose-ster*, our portal should also be called Nose-ster. To set general information about your portal like the name and mail domain, go to the control panel and select *Portal Settings* under the Portal heading. You could set up the configuration for Nose-ster as follows.

![Figure 2.1 Changing Portal Settings](../../images/04-web-content-changing-settings.png)

You can also customize the logo in the top left corner of every page by selecting *Display Settings* under the *Miscellaneous* tab on the panel to the right. Once you've made the changes, we can begin creating pages.

### Page Creation and Management [](id=page-creation-and-manageme-3)

You have a few options for accessing the page creation interface. To unify this, it necessitates that we cover the Dockbar's *Manage* menu slightly out of order. There are two interfaces to be aware of: *Site Pages* and *Page*. You can get to these from multiple places. Depending on what you're editing and where you are on the portal, you'll use either the *Manage* menu or the control panel to work with your pages. From the control panel, make sure that you have the correct site selected in the context menu and click the *Site Pages* link in the content section. If you're currently navigated to the site you wish to manage, click *Manage* from the Dockbar and select *Site Pages*. This is the exact same interface that you see in the control panel. To modify the single page in the site that you're on, click *Manage* and select *Page*. 

![Figure 2.1: Managing Individual Pages](../../images/04-web-content-managing-single-page.png)

For convenience, you can also navigate to the Sites page under the Portal section of the control panel and click *Actions* &rarr; *Manage Pages*. To quickly add a single page while you're browsing a site, click *Add* from the Dockbar and select *Page*, and a page is added immediately. You can then enter a name for the page and work on it immediately.

![Figure 2.2: Managing Site Pages](../../images/04-managing-site-pages.png)

*Site Pages* is an interface to view existing pages, create new pages, view pages, and export or import pages using Liferay Archive (LAR) files. Note that you can switch between managing a set of pages and managing a single page using the left-hand side navigation menu. Click on *Public Pages* or *Private Pages* to manage the group or click on an individual page to manage just that one. Switching views like this changes this list of available tabs to the right. By default, liferay.com, which we renamed nosester.com, contains a single public page called *Welcome*.

Liferay's page groups are always associated with sites. Even users' personal pages are part of their personal sites. All pages must belong to one of two sets of pages: public pages or private pages. By default, anyone, even guests, can access a site's public pages. Private pages are accessible only to users who are members of the site which owns the pages. This means that the private pages of an organization's site would be viewable only by members of the organization. 

Regardless of whether the pages are public or private, Liferay uses the same interface to manage them. Let's look at this interface more closely. 

### Managing Pages [](id=lp-6-1-ugen02-managing-pages-0)

From the Manage Site Pages dialog box, you can add a page to the site by clicking the *Add Page* button. Because *Public Pages* is selected on the left, clicking *Add Page* here adds a top level page next to the Welcome page. You can, however, nest pages as deeply as you like. To create a sub-page under the Welcome page, select the *Welcome* page first and then create your page. If you later decide you don't like the order of your pages, you can drag and drop them in the list to put them in whatever order you want. Let's go ahead and add another top level page and name it *Community*. We'll use this page for the Recent Bloggers and Wiki portlets.

![Figure 2.3: Adding Pages](../../images/04-web-content-add-page.png)

When you create a new page, you can create either a blank page or a page prepopulated with portlets from a page template. When you're entering the name of the page, you can select from a list of page templates that are currently available. To view the pages once you add them, click the *View Pages* button. This is how you'd populate your pages with content and applications. This is covered in succeeding chapters.

If you're using the Manage Pages interface to create a new page, you'll have some additional options to create different types of pages. There are **Portlet Pages**, **Panel Pages**, **Embedded Pages**, **URL Pages**, and **Link to Page**. By default all pages are created as portlet pages, but in some situations you might want to use one of the other options.

**Portlet Pages** are the pages that we're usually talking about. They have a layout which you can drag and drop portlets into. The overwhelming majority of pages that you create will be portlet pages.

**Panel Pages** can have any number of portlets on them, as selected by an administrator, but only one will be displayed at a time. The users select which portlet they want to use from a menu on the left side of the page, and the selected portlet takes up the entire page. 

![Figure 2.4: A panel page](../../images/web-content-panel-page.png)

**Embedded Pages** display content from another website inside of your portal. An administrator can set a URL from in the page management interface, and that page will appear in the context and within the navigation of your Liferay portal.

**URL Pages** are just redirects to any URL specified by an administrator. You can use this to link to pages within your portal on another community, or to an external site. These should be used with caution, as blind redirects can create a poor user experience.

A **Link to Page** is a portal page which functions as an immediate redirect to another page within the same site. You can select which page to link to from a dropdown in the page management interface. This can be useful for putting a deeply nested page in the primary navigation, or just for confusing your users.

Once you've created pages and populated them with content, Liferay provides a way for you to back them up to separate files. Let's see how that works. 

### Backing up and Restoring Pages [](id=lp-6-1-ugen02-backing-up-and-restoring-pages-0)

Next to the *Add Page* button in the Manage Site Pages screen are two buttons labeled *Export* and *Import*. The Export button exports the pages you create into a single file, called a LAR (Liferay Archive). You can then import this file into any server running Liferay to re-create the pages. If you have a LAR that you would like to import, use the *Import* button. Exporting and Importing LARs is a great way to take content from one environment (say, a development or QA environment) and move it all in one shot to your production server. Note that you should not make it a regular process to do this. If you want to regularly move pages from one server to another, you should use Liferay's staging environment, which is covered in chapter 3. 

LARs are also a good way to back up your site's content. You can export them to a specific location on your server which is backed up, and if you ever have to restore your site, all you need to do is import the latest LAR file. One limitation on LAR files, however, is that they are version dependent, so you can't use an export from an old version of Liferay and import it into a newer version.

Let's be good administrators and export a LAR file for backup purposes. Click on the *Export* button and then name the file `nosesterv1.lar`. Use the check boxes to determine what you'd like to export. For this initial export, select everything. Note that if you select the *More Options* link, the list expands to include data from many of Liferay's applications, including the Documents and Media Library, Message Boards, and Web Content. You can also export the theme you're using.

Once you click *Export*, your browser prompts you to save the file. Once you have the file, you can copy it to a backup location for safekeeping or import it into another installation of Liferay Portal. If you must rebuild or wish to revert back to this version of your site, you can import this file by clicking the *Import* button from the Manage Site Pages dialog box, browsing to it, and selecting it. 

### Site Content [](id=lp-6-1-ugen02-site-content-0)

Liferay 6.1 makes it easier to access Web Content management without using the control panel. You can now click *Manage* and then *Site Content* to access the same Web Content controls that are featured in the control panel right from your portal page. 

![Figure 2.8: Site Content](../../images/web-content-site-content.png)

You can manage the following kinds of content:

- Recent Content
- Web Content
- Documents and Media
- Bookmarks
- Calendar
- Message Boards
- Blogs
- Wiki
- Polls
- Software Catalog
- Tags
- Categories
- Social Equity
- Dynamic Data Lists

For details about Liferay's social collaboration suite, see chapter 4. 

## Authoring content [](id=creating-the-nose-ster-pages)

There are a lot of other things you can do beyond placing portlets on a page. So let's start working on the Nose-ster site. You can do this by going up to the Dockbar and clicking *Go to &rarr; Nose-ster*.

We'll use the *Community* page you created earlier in the chapter. Navigate to the *Community* page and select *Manage &rarr; Page* from the Dockbar.

This screen should now be familiar to you, but let's recap. 

The Page tab allows you to:

-   Change the name of the page
-   Enter HTML code for the title
-   Choose the page type
-   Hide the page from the theme navigation
-   Define a friendly URL to the page
-   Choose an icon to be displayed
-   Choose a frame target for the page
-   Copy an existing page

You can also enter custom meta tags or JavaScript to the page if you're a web developer. Additionally, if you click the *Permissions* button, you can define which users, groups, roles, or organizations can view or edit the page.

The Children tab lets you create child pages underneath the page you've selected. You can nest pages as deep as you like, but for every page below the top level hierarchy, you must provide navigation to it via a Navigation or Breadcrumb portlet, at least with most themes (including the default). Developers can create themes which have cascading menu bars which show the full hierarchy, and some examples of that are in Liferay's plugin repositories.

For now, click *Return to full page*. You should be able to define and manage pages in Liferay at this point, so let's look at what you'd put on a page. 

### Portlets [](id=lp-6-1-ugen02-portlets-0)

As we discussed earlier, Liferay Portal pages are composed of portlets. All of your site's functionality, from blogs to shopping, is composed of portlets.

Adding portlets to a page is simple. Let's add some to our Community page.

1.  In the Dockbar, select *Add &rarr; More*.
2.  In the menu that appears, expand the *Collaboration* category.
3.  Drag the *Blogs Aggregator* portlet off the Add Application window onto the right column of our page.
4.  Next, drag the *Wiki* portlet to the *left column*.

See how easy it is to add applications to your pages? We've gone one step further: we've got the Wiki portlet, the Blogs Aggregator portlet, and then a nested portlet with a different layout and the Alerts, Search, and Dictionary portlets in the figure below. 

![Figure 2.9: Yeah, we're showoffs. But as you can see, your page layout options are virtually limitless.](../../images/04-web-content-portlet-layout.png)

You'll find it's easy to make your pages look exactly the way you want them to. If the layout options provided aren't enough, you can even develop your own. More information about that can be found in Liferay's official guide to development, [*Liferay in Action*](http://manning.com/sezov).

### Page Permissions [](id=lp-6-1-ugen02-page-permissions-0)

By default, public pages are just that: public. They can be viewed by anybody, logged in or not logged in. And private pages are really only private from non-members of the site. If someone has joined your site or is a member of your organization, that person can see all the private pages. You can, however, modify the permissions on individual pages in either page group so that only certain users can view them.

Let's say we wanted to create a page only for administrators to see. We can do this with the following procedure:

1.  Go to the Dockbar and select *Manage &rarr; Control Panel*.
2.  Ensure that you've selected the default site in the context selector.
3.  Click the *Site Pages* link.
4.  Click the *Private Pages* tab to switch to the Private Pages. Remember, these pages by default are viewable only by members of the site.
5.  Create a page called *Admin Tips*.
6.  Click on the page in the tree on the left and then click *Permissions*.
7.  Uncheck the *View* and *Add Discussion* permissions next to the Site Member role.
8.  Click the *Save* button.

![Figure 2.10: Permissions for Admin Tips](../../images/04-web-content-page-permissions.png)

Congratulations! You've just changed the permissions for this page so that only site administrators can view it. Any users you add to this role can now see the page. Other users, even members of this site, won't have permission to see it.

## Publishing content [](id=lp-6-1-ugen03-publishing-content-with-the-web-content-display-portlet-0)

Now that we've created and published our first piece of web content for Nose-ster, it's time to display it. First, add the *Web Content Display* portlet to our Welcome page, by selecting *Add &rarr; Web Content Display* from the Dockbar.

![Figure 3.7: Adding the Web Content Display Portlet](../../images/portal-admin-ch4_html_m5a656d59.jpg)

Once the portlet appears, drag it to the position on the page where you want your content to appear. You can have as many Web Content Display portlets on a page as you need, which gives you the power to lay out your content exactly the way you want it. 

To add existing web content, select the *gear* icon on the lower left of the portlet. You will see the message *Please select a web content from the list below*. You have several options here.

Naturally, if your content appears in the list, you can simply select it. If there is lots of published content available, you could search for the content by name, ID, type, version, content, and site (click the *Advanced* link to see all the options). You can also show the available locales for your content. If you're working on the page for a particular language, you can select the translation of your content that goes with your locale.

![Figure 3.8: Publishing web content is a snap. At a minimum, you only have to select the content you wish to publish. You can also enable lots of optional features to let your users interact with your content.](../../images/04-web-content-choosing-web-content.png)

If you have enabled OpenOffice.org integration with your portal, you can also enable document conversion for your content. This gives your users the ability to download your content in their format of choice. This is especially handy if you are running a research or academically oriented site; users can very quickly download PDFs of your content for their research projects.

Note that you also have other options, such as enabling a Print button, enabling ratings so that users can rate the content, enabling comments, and enabling ratings on comments.

The Print button pops the content up in a separate browser window that contains just the content, without any of the web site navigation. This is handy for printing the content. Enabling ratings shows one of two ratings interfaces Liferay has: five stars or thumbs up and thumbs down. This can be set globally in the `portal-ext.properties` file. See chapter 12 for further information about this.

Enabling comments creates a discussion forum attached to your content which users can use to discuss your content. Enabling ratings on comments gives your users the ability to rate the comments. You may decide you want one, some, or none of these features, which is why they're all implemented as simple check boxes to be enabled or disabled at need.

If you click the *Supported Clients* tab, you'll see that you can choose the type of client to which you want to expose content. This lets you target the large screens of users' computers for expansive graphics and lots of special effects, or target the small screens of mobile devices with pertinent information and a lightweight page. For now, leave both checked and click the *Save* button. You can now close the configuration window.

To publish new content, select the *page and green plus icon* on the lower left of the portlet. This launches the same full-featured editor you've already seen in the control panel, which lets you add and edit content in place as you are working on your page.

This is another example of the flexibility that Liferay Portal offers. At times, you may want to add content directly into the Web Content Display portlet of the page you're managing, especially if you are in the process of building the page. At other times, you may want to use the control panel to create content, because at that moment you're more concerned with the creation of the content and not where the content will later be displayed. Liferay WCM supports both processes. 

Editing content that's already been published is just as easy as creating new content is. You'll use the same exact tools. 

#### Editing Content [](id=lp-6-1-ugen03-editing-content-0)

Once the content is displayed--whether you've selected content or created it in the Web Content Display portlet--you can edit the content directly from the Web Content Display portlet or from the control panel. To edit it from the Web Content Display portlet, select the *pencil* icon to the lower left of the portlet. This launches the WYSIWYG editor and from there you can make any necessary changes.

![Figure 3.9: Edit, Select, and Add Icons of Web Content Display Portlet](../../images/web-content-display-icons.png)

When you publish your content this way, it becomes available immediately (unless, of course, you have a workflow enabled, which we'll see below). This happens whether you edit it in place or in the control panel.

Note: if you want to view your page the way your users will see it (i.e., without all those portlet controls and icons), go up to the Dockbar and select *Toggle Edit Controls*. This makes all those extra controls you see as a portal administrator disappear. If you need to use those controls again, just select *Toggle Edit Controls* again. 

That's pretty much all there is to simple content creation. Whole sites have been created this way. But if you want to take advantage of the full power of Liferay's WCM, you'll want to use structures and templates. Next, we'll see how they work and how you can use them to make your site more dynamic. 

### Using the Asset Publisher Portlet [](id=lp-6-1-ugen03-using-the-asset-publisher-portlet-0)

As we create web content, it's important to keep in mind that to Liferay, the pieces of content are assets, just like message board entries and blog posts. This allows you to publish your web content using Liferay's Asset Publisher.

You can use the Asset Publisher to publish a mixed group of various kinds of assets such as images, documents, blogs, and of course, web content. This helps in creating a more dynamic web site: you can place user-created wiki entries, blog posts, or message board messages in context with your content. Let's look at some of its features.

#### Querying for Content [](id=lp-6-1-ugen03-querying-for-content-0)

The Asset Publisher portlet is a highly configurable application that lets you query for mixed types of content on the fly. By giving you the ability to control what and how content is displayed from one location, the Asset Publisher helps you to "bubble up" the most relevant content to your users.

To get to all the portlet's options, click the *Configuration* link in the portlet's menu (the wrench icon).

The ability to configure how content is displayed and selected by your users further demonstrates the flexibility of the Asset Publisher. You get to choose how content is displayed. You can select it manually for display in a similar way to the Web Content Display portlet, or you can set up predefined queries and filters and let the portal select the content for you, based on its type or its tags and categories.

Let's first look at how we might select content manually. You'll see that it's very similar to the Web Content Display portlet.

#### Selecting assets manually [](id=lp-6-1-ugen03-selecting-assets-manually-0)

By selecting *Manual* from the select box beneath *Asset Selection*, you tell the Asset Publisher that you want to select content manually. You can select what you want to be published within the portlet, or you can create new content from within the Asset Publisher.

![Figure 3.22: Selecting assets manually is very similar to the Web Content Display portlet, except you have many other content types to choose from.](../../images/04-web-content-asset-publisher-manual.png)

Clicking *Add New* gives you a menu of options, enabling you to create the content right where you are. You can create blogs, bookmarks, calendar entries, documents, images, and of course, web content. Anything you create here is added to the list below of assets that are displayed by the portlet.

Clicking *Select Existing* gives you a similar menu, except this time you can pick from existing content in the portal that either you or your users have created. Has someone written an excellent wiki page that you want to highlight? Select it here, and it will be displayed.

The Asset Publisher enables you to mix and match different content types in the same interface. Once you have your content selected, you can move on to the display types to configure how the content appears.

Most of the time, however, you'll likely be using the Asset Publisher to select content dynamically.

#### Selecting assets dynamically [](id=lp-6-1-ugen03-selecting-assets-dynamically-0)

Asset Publisher's default behavior is to select assets dynamically according to rules that you give it. These rules can be stacked on top of each other so that they compliment each other to create a nice, refined query for your content. You have the following options for creating these rules:

**Scope:** Choose the sites or organizations from which the content should be selected.

**Asset Type:** Choose whether you'll display any asset or only assets of a specific type, such as only web content, only wiki entries, or any combinations of multiple types.

![Figure 3.23: You can filter by tags and categories, and you can set up as many filter rules as you need.](../../images/04-web-content-asset-publisher-filter.png)

**Filter Rules:** Add as many filters on tags or categories as you like. You can choose whether the content contains or does not contain any or all categories or tags that you enter.

Once you've set up your filter rules for dynamically selecting your content, you can then decide how the content will be displayed.

#### Ordering and Grouping [](id=lp-6-1-ugen03-ordering-and-grouping-0)

You can display the content returned by the filters above in order by title, create date, modified date, view count, and more in ascending or descending order. For instance, you may have a series of "How To" articles that you want displayed in descending order based on whether the article was tagged with the *hammer* tag. Or, you may want a series of video captures to display in ascending order based on a category called *birds*. You can also group by *Asset*, *Type*, or *Vocabularies*. Vocabularies are groups of categories defined by administrators in the *Categories* section of the control panel. Again, we'll see more about categories in chapter 4.

In the *Ordering and Grouping* section of the Asset Publisher, you have great control over how content is ordered and grouped in the list, but this is only one aspect of how your content will be displayed. You can refine the display through many other display settings.

#### Display Settings [](id=lp-6-1-ugen03-display-settings-0)

The Display Settings section gives you precise control over the display of your assets. There are a multitude of options available to configure how you want your content to appear. You can configure the style, length of abstracts, behavior of the asset link, maximum items to display, pagination type, and file conversions. Additionally, you can enable printing, flags, ratings, comments, and comment ratings, and these work the same way they do in the Web Content Display portlet.

#### Display Style [](id=lp-6-1-ugen03-display-style-0)

**Abstracts:** Shows the first 200-500 characters of the content, defined by the **Abstract Length** field.

**Table:** Displays the content in an HTML table which can be styled by a theme developer.

**Title List:** The content's title as defined by the user who entered it.

**Full Content:** The entire content of the entry.

#### Other Settings [](id=lp-6-1-ugen03-other-settings-0)

**Asset Link Behavior:** When the link to the asset is clicked, it can be displayed in the Asset Publisher or in the portlet to which the asset belongs, such as the Blogs or Message Boards.

**Maximum Items to Display:** You can display 1-100 items.

**Pagination Type:** Select Simple or Regular. Simple shows previous and next navigation; regular includes a way of selecting the page to which you'd like to navigate.

**Exclude Assets with 0 Views:** If an asset has not been viewed, exclude it from the list.

**Show Available Locales:** Since content can be localized, you can have different versions of it based on locale. This will show the locales available, enabling the user to view the content in the language of his or her choice.

**Enable Conversion To:** If you have enabled Liferay Portal's OpenOffice.org integration, you can allow your users to convert the content to one of several formats, including PDF.

Below these options are the same ones in the Web Content Display portlet: enable print, enable comments, enable ratings, etc.

**Show Metadata:** Allows you to select from the available metadata types (see below).

![Figure 3.24: Show Metadata](../../images/portal-admin-ch4_html_m409b2939.jpg)

**Enable RSS Subscription:** This lets users subscribe to the content via RSS Feeds.

The Display Settings section of the Asset Publisher has numerous options to help you configure how your content selections are displayed to your users. Even though there are many choices, it's easy to go through the options and quickly adjust the ones that apply to you. You'll want to use the Asset Publisher to query for mixed assets in the portal that have relevant information for your users.

Next, we'll look at some of the most powerful features of Liferay WCM: staging and workflow. 

## Using Liferay's integrated workflow with WCM [](id=lp-6-1-ugen03-using-liferays-integrated-workflow-with-content-management-0)

Workflow is essentially a predetermined sequence of connected steps. In Liferay WCM, workflow is designed to manage the creation, modification, and publication of web content. You can set up a workflow so that content can't be published without going through an approval process that you design. In this way, content is published to the site only after it has been reviewed and approved.

Liferay's workflow engine is called Kaleo workflow, and it ships with Liferay CE. If you have uninstalled it or are using EE, it needs to be installed and configured separately. This is covered in chapter 6. Since we have somewhat of a "What came first--the chicken or the egg?" problem, for now, we'll assume it's installed and look at how you can take advantage of workflow in getting your content through any approval steps between creation and publication.

You may have noticed that something appears to be missing from the staging process discussed above. In particular, you might be asking the question, "How do I reject changes?" Starting with Liferay 6.1, Staging is integrated with Liferay's Workflow engine. In order to have a review process for staged pages, you need to make sure that you have a workflow engine configured, and that you have staging set up in the workflow. To do this, select the workflow definition desired for page revisions in the Workflow Configuration. 

When using a workflow, clicking *Submit for Publication* submits the staged pages into the workflow. Once all necessary approvals have been completed, the page status is marked as ready for publication. The *Publish to Live Now* and *Schedule for Publication* options publish the last version of the selected pages marked as ready for publication.

To enable workflow for Web Content, navigate to the control panel and select *Workflow Configuration*. From there, select a workflow that has been deployed to Liferay.

![Figure 3.30: Enabling Workflow for Content Management](../../images/04-web-content-workflow-config.png)

As you'll discover in chapter 6, you can design workflows to suit your organization's approval process. For Nose-ster's implementation we'll use the *Single Approver* workflow which ships with the product.

### Defining Workflows for Web Content [](id=lp-6-1-ugen03-defining-workflows-for-web-content-0)

Let's set up Liferay's Workflow for the Nose-ster web site.

1.  Go to the control panel and select *Workflow Configuration* from the left panel.

2.  From the select box, choose *Single Approver* for Web Content. Click *Save.* Note that you can add workflow to many of Liferay's portlets.

That's all it takes to set up workflow for web content. Now that workflow is enabled, publishing content works a little bit differently. Let's go through the process of publishing details for new class offerings at Nose-ster. Return to the home page and click the *Add Web Content* icon on the Web Content Display portlet. Call the new content *Course Offerings* and enter some content. Notice that the Publish button is now gone. In its place is a *Submit for Publication* button. Go ahead and click it.

![Figure 3.31: Pending Workflow](../../images/04-web-content-workflow-config.png)

Next, go to the *Workflow Tasks* in control panel and then select *My Workflow Tasks*. You will see the option to Review Content for Sales Goals. It shows because you are logged in as an Administrator. There is also a Content Approvers role which is defined by this workflow, and anyone in this role can approve content as well.

To approve the content, you must first take ownership of it. Click on the task. You should see the screen below.

Taking ownership of, reviewing, and approving content is very easy:

1.  Click the *Assign to Me* button. Alternatively, you could assign it to someone else in the Content Approvers role or create / update a due date for the content's approval.
2.  Once you've assigned it to yourself, buttons allowing you to approve or reject the content appear. Click *Approve*.
3.  You're asked to submit a comment. You'd have to do this for either *Approve* or *Reject*. Add a comment and click *Save*.
4.  The content is now approved.

In a real world situation, you obviously wouldn't want the person who created the content to be the one who approves it. Instead, you would have one or more roles designed for users who will be creating content, and you would have specific users assigned to one or more roles for approving content. Our example was of a very straightforward workflow, as it has only a single approver. Kaleo workflow allows you to design workflows that go through as many steps as you need to conform to your business processes. We look at Kaleo workflow in more detail in chapter 6.

## Site memberships [](id=administering-liferay-portal)

You know how all these retailers want to advertise themselves as a "one stop shop" for whatever it is that you want? The idea is that they have so much stuff that chances are that whatever you're looking for is there. Liferay's control panel is something like this. If you want to create users, sites, organizations, configure permissions and plugins, and pretty much anything else, you'll do it with the control panel. The nice thing about the control panel is that it makes all this very easy to do. This chapter takes all the concepts you learned about Liferay in chapter 1 (sites, organizations, and more) and makes them concrete. Here, you'll learn how to create and manage every aspect of Liferay's configuration. 

This chapter covers the following activities:

-   Using the control panel to manage users, organizations, sites, user groups, roles, and teams

-   Using the control panel to manage password policies, authentication settings, mail host names, email notifications, display settings, and monitoring

-   Using the control panel to manage server administration, portal instances, plugins, and updates

Let's begin our examination of Liferay's control panel by looking at how to manage and organize users in Liferay Portal.

## Managing Users, User Groups, Organizations, Sites, Teams, and Roles [](id=managing-users-user-groups-organizations-sites-teams-and-roles)

The Portal section of the control panel is used for most administrative tasks. You'll find there an interface for the creation and maintenance of

- Users, User Groups, and Organizations

- Sites and Teams

- Site Templates

- Page Templates

- Roles

Additionally, you can configure many server settings, including:

- Password Policies

- Portal Settings

- Custom Fields

- Monitoring

- Plugins Configuration

You'll use the Portal section of the control panel to create your portal structure, implement security, and administer your users. Configurable portal settings include mail host names, email notifications, and authentication options including single sign-on and LDAP integration. Note that only users with the administrator role, which is a portal scoped role, have permission to view this section of the control panel. You can, of course, grant custom roles permissions to one or more sections.

### Adding users [](id=lp-6-1-ugen12-adding-users-0)

Let's add a user account for yourself and configure this account so that it has the same administrative access as the default administrator account. Go up to the Dockbar, mouse over *Go to* and click *Control Panel*. Then open the *Users and Organizations* page under the *Portal* category. Click the *Add* button and select *User*. Fill out the Add User form using your name and email address. When you are finished, click *Save*.

![Figure 12.1: The Add User Screen](../../images/01-add-user-screen.png)

After you submit the form, the page reloads with a message saying that the save was successful. An expanded form appears that allows you to fill out a lot more information about the user. You don't have to fill anything else out right now. Just note that when the user ID was created, a password was automatically generated and, if Liferay was correctly installed (see chapter 11), an email message with the password in it was sent to the user. This, of course, requires that Liferay can properly communicate with your SMTP mail server.

![Figure 12.2: Liferay's User Account Editor](../../images/01-user-account-editor.png)

If you haven't yet set up your mail server, you'll need to use this page to change the default password for the user ID to something you can remember. You can do this by clicking on the *Password* link in the box on the right, entering the new password in the two fields, and clicking *Save*. Next, you should give your user account the same administrative rights as the default administrator's account. This allows you to perform administrative tasks with your own ID instead of having to use the default ID. It also helps to make your portal more secure by deleting or disabling the default ID.

Click the *Roles* link. The control panel's Roles page shows the roles to which your ID is currently assigned. You should have one role: Power User. By default, all users are assigned the Power User role. You can give this role certain permissions if you wish or disable it altogether. You can also define the default roles a new user receives. We'll see how to do this later.

To make yourself an Administrator, click the *Select* link. A dialog box pops up with a list of all the roles in the system. Select the Administrator role from the list. The dialog box disappears and the role is added to the list of roles associated with your account. Next, click the *Save* button, which is at the bottom of the blue bar of links on the right. You are now an administrator of the portal. Log out of the portal and then log back in with your own user ID.

We'll next look at some aspects of user management. 

### User management [](id=lp-6-1-ugen12-user-management-0)

If you click the *Users* link on the left menu of the control panel, there are now two users in the list of users. If you want to change something about a particular user, you can click the *Actions* button next to that user.

**Edit User:** takes you back to the Edit User page, where you can modify anything about the user.

**Permissions:** allows you to define which roles have permissions to edit the user.

**Manage Pages:** allows you to edit the personal pages of a user.

**Impersonate User:** opens another browser window which allows you to browse the site as if you were the user.

**Deactivate:** deactivates the user's account.

Note that most users can't perform most of the above actions. In fact, most users won't even have access to this section of the control panel. You can perform all of the above functions because you have administrative access.

Let's look next at how to manage organizations. 

### Managing organizations [](id=lp-6-1-ugen12-managing-organizations-0)

Organizations are used to represent hierarchical structures such as those of companies, non-profit organizations, churches, schools, and clubs. They have been used to represent a sports league, with various sports (soccer, baseball, basketball, etc.) and their teams as sub-organizations. If you have a collection of users that fit into a hierarchical structure, you can model that as an organization.

Your portal design might not need organizations or it might have one or several, depending on your portal's function. For example, a simple photo-sharing web site could be powered by sites only (see below for information on sites). On the other hand, organizations are useful for corporations or educational institutions since their users can be placed easily into a hierarchical structure. In fact, organizations in Liferay are designed to model any group hierarchy, from those of government agencies all the way down to those of small clubs. Of course, your portal can use both organizations and independent sites. For example, a corporation or educational institution could create a social networking site open to all portal users, even ones from separate organizations. 

Organizations and suborganizations can be created in a hierarchy to unlimited levels, and users can be members of one or many organizations. These organizations can all reside in a single hierarchy or cut across different hierarchies. Note that the rights of an organization administrator apply both to his/her organization and to any child organizations. By default, members of child organizations are members of the parent organizations. This behavior can be customized in your portal's `portal-ext.properties` configuration file.

Additionally, Organizations can be associated with roles. One application of this in a corporate setting might be an IT Security group. You could have a suborganizaton of your IT organization that handles security for all of the applications company-wide. If you grant the IT Security organization the same administrator role you just gave to your own ID, all members of the organization would have administrative access to the portal. Suppose now that a user in this organization later was hired by the Human Resources department. The simple act of removing the user from the IT Security organization also removes the user's administrative privileges, since the privilege came from the IT Security group's role. By adding the user to the HR organization, any roles the HR organization has (such as access to a benefits system in the portal) are transferred to the user. In this manner, you can design your portal to correspond with your existing organization chart, and users' permissions are granted according to their positions in the chart.

Of course, this is only one way to design it. If you have more complex requirements, you can combine organizations with teams and scoped roles to assemble the sets of permissions you wish to grant to particular users. But we'll get to that. Let's first see how to manage organizations. 

To add an organization, click the *Users and Organizations* link on the left side of the control panel. Then click the *Add* button and choose *Regular Organization*. 

Does your organization need to have its own web site? Most organizations don't, but some do, and Liferay provides this ability by attaching a site to an organization. To attach a site when you create an organization, click the *Organization Site* tab at the right, and check the *Create Site* box. If you don't know right now if your organization needs a web site, that's fine: you can always add one later if the need arises. 

![Figure 12.3: Adding an organization](../../images/01-add-organization-screen.png)

**Name:** Enter a name for the organization.

**Type:** Choose whether this is a regular organization or a location. A location cannot have any suborganizations.

**Parent Organization:** Select an organization in the system to be the direct parent of the organization you are creating. Click the *Remove* button to remove the currently configured parent.

---

![tip](../../images/01-tip.png) **Tip:** Note that you're already a member of any organizations that you create. By creating an organization, you become both a member and receive the Organization Owner role, which gives you full rights to the organization. You can, of course, add other users to this role to make them Organization Owners. 

---

Fill out the information for your organization and click *Save*. As before with users, the form reappears and you can enter more information about the organization. Organizations can have multiple email addresses, postal addresses, web sites, and phone numbers associated with them. The Services link can be used to indicate the operating hours of the organization, if any.

For now, click the *Back* button. This takes you back to the list of organizations.

Click the *Actions* button next to the new organization you created. This shows the actions you can take to manipulate this organization.

**Edit:** lets you specify details about the organization, including addresses, phone numbers, email addresses, and websites.

**Manage Site:** lets you create and manage public and private pages for the organization's site.

**Assign Organization Roles:** lets you assign organization-scoped roles to users. By default, Organizations are created with three roles: Organization Administrator, Organization User, and Organization Owner. You can assign one or more of these roles to users in the organization. All members of the organization automatically get the Organization User role so this role is hidden when you click Assign Organization Roles.

**Assign Users:** lets you search and select users in the portal to be assigned to this organization as members.

**Add User:** adds a new user in the portal and assigns the user as a member of this organization.

**Add Regular Organization:** lets you add a child organization to this organization. This is how you create hierarchies of organizations with parent-child relationships.

**Add Location:** lets you add a child Location, which is a special type of organization that cannot have any children added to it.

**Delete:** deletes this organization from the portal. You must ensure that the organization has no users in it first.

If you click the *View* button at the top of the Users and Organizations page and select *View Hierarchy* you can view both a list of users who are members of this organization and a list of all the suborganizations of this organization.

We briefly mentioned sites during this discussion. Sites are another construct within the portal, and have different properties than organizations. Let's see how you can use them. 

### Sites [](id=lp-6-1-ugen12-sites-0)

As stated in chapter 1, a site is a set of pages that can be used to publish content or applications. Sites can be independent or they can be associated to one organization and act as the website of that organization.

Liferay's sites can be used for a variety of purposes, from corporate websites to company intranets including small sites to collaborate among members of a team. To support all types of collaboration and social scenarios, Liferay's sites support three types of membership types:

- Private: Users are not allowed to become members of the site. Site administrators can still manually select users and make them members of the site.
- Restricted: Users are allowed to request members of the site and site administrators have to aprove the request. The request can be done from the *My Sites* application.
- Open: Users are allowed to become members of the site at any time. This can be done from the *My Sites* portlet.

In addition to these memberships, when a site is associated to an organization, all the users of that organization are automatically considered members of the site.

Members of a site can be given additional privileges within the site by using Liferay's permission settings. It is also possible to assign different roles within the site to different members. This can be done through *site roles* which are defined equal for all sites or *teams* which are unique for each site.

Liferay's Sites can have two hierarchies of pages: public pages and private pages. A site can have only public pages, only private pages or both. The main difference between the two hierarchies is that private pages can only be accessed by members of the site. For both of them it is possible to restrict access to them in finer detail for each page through the permission system. Public pages and private pages are accessed through a different URL and can have a different look and feel, but they share the same content.

An example of using sites could be a corporate Intranet running Liferay which might have sites for all the organizations in the company: Sales, Marketing, Information Technology, Human Resources, and so on. But what about the corporate health and fitness center? That's something that everybody in the company, regardless of organization, may want to join. This makes it a good candidate for an open and independent site. Similarly, the home page for a corporate intranet should probably be placed in an open independent site so that any member of the portal can access it.

For other kinds of web sites, you may want to use independent sites to bring people together who share a common interest. If you were building a photo sharing web site out of Liferay, you might have independent sites based on the types of photos people want to share. For example, those who enjoy taking pictures of landscapes could join a Landscapes site, and those who enjoy taking pictures of sunsets could join a Sunsets site. 

Liferay always provides one default site, which is also known as the main site of the portal. This site does not have its own name, but rather takes the name of the portal. By default the portal name is *liferay.com*, but this value can be changed through the simple configuration of the setup wizard. The portal name can also be changed at any time through the control panel within *Portal Settings*.

---

![tip](../../images/01-tip.png) **Tip:** Prior to Liferay 6.1, there were two ways of creating sites: organizations and communities. This situation has been simplified to provide more ease of use and allow for more flexibility. The main role of organizations is still or organize the users of the portal in a hierarchy but they can also have associated sites. Communities can still be created through independent sites, but the new name reflects the fact that sites can be used for many different purposes besides communities.

---

Sites can be created through the control panel, like all administration operations in Liferay. To add a site, click the *Sites* link on the left side of the control panel in the Portal section, and then click *Add* in the toolbar. If there is at least one site template available, a dropdown menu will be shown allowing you to select a *Blank Site* or one of the site templates available. *Site templates* provide a preconfigured set of pages, applications and content that can be used as the basis of the site.

The following figure shows the form that needs to be filled when creating a *Blank Site*.


![Figure 12.4: Adding a Site](../../images/01-add-site-screen.png)

**Name:** is the name of the site you wish to create.

**Description:** describes the site's intended function.

**Membership Type:** can be open, restricted, or private. An open site appears in the My Sites portlet and users can join and leave the site whenever they want. A restricted site is the same except that users can only request membership. A site administrator must then explicitly grant or deny users' requests to join. A private site does not appear in the My Sites portlet, and users must be added to it manually by a site administrator.

**Active:** determines whether a site is active or inactive. Inactive sites are inaccessible but can be activated whenever a site administrator wishes.

Once you've created a site, it appears in the Sites page of the control panel. Once the site has been created you can specify more details about the site, and these fall under three main categories: Basic Information, Search Engine Optimization, and Advanced.

![Figure 12.5: Editing a Site](../../images/01-site-editor.png)

**Details:** lets you edit the information you entered when you created the site and allows you to choose a site template for the public or private pages of your site. If you select a site template, leave the *Enable propagation of changes from the site template* box checked to automatically update your site if the associated site template changes. The update will only be done to pages which have not been changed within the specific site. If you uncheck this box but recheck it later, the template pages are then reapplied to your site, overwriting any changes that may have been made. Only users who have the permission "Unlink Site Template" will be able to disable the propagation of changes. When the propagation is enabled, the site template might prevent modification of all or certain pages to ensure that the propagation occurs.

**Categorization:** allows you to apply categories and tags to the site.

**Site URL:** lets you set friendly URLs and virtual hosts for your web site.

**Site Template:** provides additional information about the site template associated to the pages of the site (if any).

**Sitemap:** lets you use the sitemap protocol to notify search engines that your web site is available for crawling. 

**Robots:** lets you use a `robots.txt` file to specify certain pages and links that you don't want to be indexed by search engines. You need to set a virtual host before you set a `robots.txt` file.

**Staging:** lets you turn on either Local Live staging or Remote Live staging. To enable staging, the *Enable propagation of changes from the site template* box on the Details tab must be unchecked. With staging enabled, changes to the site template are automatically propagated to the staged site, not to the live site. The changes still must be approved before the site is published to live.

**Analytics:** lets you set a Google Analytics ID that is used for your site. 

When creating a site from a site template, the initial form provides a new option that lets you decide if you want to copy the pages from the template as public pages or as private pages. By default, the site is linked to the site template, and changes to the site template propagate to any site based on it. A checkbox appears that allows users to unlink the site template if the user has permission to do so.

<!-- | TODO: Add screenshot of form used to create a site from a site template | -->

Site templates are very powerful for managing many similiar sites. Let's look further at how they work. 



### User Groups [](id=lp-6-1-ugen12-user-groups-0)

User Groups are arbitrary groupings of users. These groups are created by portal administrators to group users together who don't necessarily share an obvious hierarchical attribute. Users can be assigned to multiple user groups. User Groups are most often used to achieve one of the following goals:

- Simplify the assignment of several roles to a group of users. For example, in a University portal, a user group could be created to group all teachers independently of their organization to make it easier to assign one or several roles at once to all the teachers.
- Simplify membership to one or more sites by specifying a group of users. Using the previous example, all teachers could be members of the sites *University Employees* and *Students and Teachers Collaboration Site* by adding the *Teachers* user group as a member.
- Provide predefined public or private pages to the users that belong to the user group. For example, the *Teachers* user group could be created to ensure that the home page on all teachers' personal sites has the same layout and applications.

Creating a user group is easy. Navigate to the control panel, click the *Users Groups* link, and then click the *Add* button. There are only two fields to fill out: Name and Description. Click *Save* and you will be redirected back to the *User Groups* page of the control panel.

![Figure 12.11: Creating a New User Group](../../images/server-configuration-new-user-group.png)

Note in the figure above how each user group may have a site, with public and private pages. This is a special type of site that determines the base pages on all user group members' personal sites. The user group site works in a similar way to Site Templates, except that in this case the the User Group Site pages are not copied for each user, but are rather shown dynamically along with any custom pages that the user may have on his/her personal site. For this reason, users are not allowed to make any modifications to the pages that are *inherited* from the user group. Alternatively the administrators of the user group can define certain areas as customizable, just like they can for regular sites. This allows users to decide which applications they want to place in certain areas of each page, as well as change their configuration.

![Figure 12.12: User Group Actions](../../images/01-editing-a-user-group.png)

As with the other resources in the portal, you can click the *Actions* button next to a user group to perform various operations on that group.

**Edit:** allows you to modify the name or description of the user group.

**Permissions:** lets you define which roles have permissions to view, edit, delete, assign members to the user group, etc.

**Site Permissions:** lets you define which roles have permissions to manage the user group site, to specify who can administer its pages, export and import pages and portlet content, manage archived setups, and configure its applications. 

**Manage Site Pages:** allows you to add pages to the user group site, import or export pages, organize the page hierarchy, modify the look and feel of the pages, add a logo, or access other options from the Manage Site interface.

**Assign Members:** lets you search for and select users in the portal to be assigned to this user group as well as view the users currently belonging to the user group .

**Delete:** deletes the user group.

If your user group has a site, two options named **Go to the Site's Public Pages** and **Go to the Site's Private Pages** also appear as links in your user group's Actions menu. Clicking one of these links opens the user group's site in a new browser window. Any changes you make to the site are saved automatically. You can safely close the browser window when you're done.

#### Creating and editing a User Group [](id=lp-6-1-ugen12-creating-and-editing-a-user-group-0)

A user group's site can be administered from the control panel. Select *User Groups* from the control panel to see a list of existing user groups. To edit a user group, click on its name or description. You can also click on the *Actions* button to see the full list of actions that can be performed on a user group. When editing a user group, you can view its site, if it exists, by clicking the *Open Pages* link under Public Pages or Private Pages (read below for details on user group sites).

As an example of how user group sites can be used, let's create a user group called *Bloggers* along with a simple template. We'll call the site template *Bloggers* too. It should contain a single *Blog* page with the Blogs and Recents Bloggers portlets on it. First, navigate to the User Groups page of the control panel. Then click *Add* and enter the name *Bloggers* for your user group, and optionally, a description. Click *Save* to create your user group.

Our next step is to assign an existing user to the *Bloggers* group.

#### Assigning Members to a User Group [](id=lp-6-1-ugen12-assigning-members-to-a-user-group-0)

Navigate to *Users and Organizations* and create a new user called *Joe Bloggs*. Then navigate to the User Groups page of the control panel and click *Actions* &rarr; *Assign Members* next to the Bloggers group. Click the *Available* tab to see a list of users that can be assigned to the group.

![Figure 12.13: Assigning Members to a User Group](../../images/01-adding-members-to-user-group.png)

From that list, one or more users can be selected to be assigned as members of the user group.

For example, by default, newly created users are given *Welcome* pages on the public pages portion of their personal sites. This Welcome page contains the Language, Search, and Blogs portlets. You can see the effect of the *Bloggers* site template on the public pages of Joe Bloggs's personal site in the figure above. When Joe Bloggs was added to the *Bloggers* group, he received a *Blogs* page with the *Blogs* and *Recent Bloggers* portlets.

After the user group has been created and several users have been added to it, you can add all those users at once as members of a site in one step from the *Site Memberships* UI of the site. You can also use the user group when assigning a role to users from the roles management UI.

The next section describes a more advanced usage of user groups: User Group Sites.

#### User Group Sites [](id=lp-6-1-ugen12-user-group-sites-0)

Liferay allows users to each have a personal site consisting of public and private pages. Permissions can be granted to allow users to customize their personal sites at will. Originally, the default configuration of those pages could only be determined by the portal administrator through the `portal-ext.properties` file and, optionally, by providing the configuration in a LAR file. You can still configure it like this, but it isn't very flexible or easy to use.

By using User Group Sites, portal administrators can add pages to the personal sites of all the users who belong to the site in an easy and centralized way. All the user group site's public pages are shown as part of the user's public personal site. All the user group site's private pages are shown as part of the user's private site. If a user belongs to several user groups, all of its pages are made part of his public and private site. In an educational institution's portal, for example, teachers, staff, and students could get different default pages and applications on their personal sites.

The pages that a user personal site *inherits* from a User Group still belong to the User Group and thus cannot be changed in any way by the users. What the user group administrators can do is define certain areas of the pages as customizable to allow the users to choose which applications and what configuration should be shown in those areas. If a user has permission to add custom pages to his/her personal site, besides those *inherited* from a user group, the custom pages are always shown last.

Since the *inheritance* of pages is done dynamically, this new system introduced in Liferay 6.1 can scale to hundreds of thousands of users or even millions of them without an exponential impact in performance. Previous versions of Liferay used a different technique that required that the user group pages were copied to each user's personal site. For portals upgrading from previous versions of Liferay, you can keep the old behavior, but it has been left disabled by default. You can enable it by adding the following line to your portal-ext.properties file:

	user.groups.copy.layouts.to.user.personal.site=true

When this property is set to true, once the template pages have been copied to a user's personal site, the copies may be modified by the user. Changes done to the originals in the User Group will only affect new users added to the user group. Users with administrative privileges over their personal sites can modify the pages and their content provided that the *Allow Site Administrators to Modify the Pages Associated with This Site Template* box has been checked for the template. When a user is removed from a user group, the associated pages are removed from the user's personal site. Moreover, if a user is removed from a group and is subsequently added back, the group's template pages are copied to the user's site a second time. Note that if a user group's site is based on a site template and an administrator modifies the user group's site template after users have already been added to the group, those changes only take effect if the *Enable propagation of changes from the site template* box for the user group was checked.

---

![tip](../../images/01-tip.png) **Tip:** Previous to Liferay 6.1, pages from different user groups could be combined on users' personal sites by using a naming convention. Liferay 6.1 simplifies the way user groups' sites work by disallowing page combination. Set the property *user.groups.copy.layouts.to.user.personal.site* to true if you depend on that functionality.

---

You can create a user group's site manually or base it on a site template. To create a user group's site manually, use the *Actions* menu mentioned above and choose *Manage Site Pages*. You can add a new public or private page by selecting the appropriate tab and then clicking the *Add Page* button. Once the user group has at least one public or private page in place, you can go back to the *Actions* menu and click on the *Go to the Site's Public Pages* or *Go to the Site's Private Pages* link to open the user group's site in a new browser window. In the new window, you can add more pages and portlets and configure site settings.

You can also base a user group's site on a template. When editing a user group, use the Public Pages and Private Pages drop down lists to select a site template. Leave the *Enable propagation of changes from the site template* box checked to automatically update users' personal sites if the associated site template changes. If you uncheck this box but recheck it later, the template pages are copied to the users' sites, overwriting any changes they may have made. You can allow users to make changes to the pages they receive from the user group by enabling the customization options on each page.

This flexibility lets you achieve almost any desired configuration for a user's personal site without having to modify it directly. When a user is assigned to a user group, the configured site pages are copied directly to the user's personal site.

Following with the example above, we will create a site for our sample user group. Edit the *Bloggers* group. Choose an existing Site Template from the drop down menu for the user group's public pages and click *Save*. After the page reloads you can click to see the pages and make any changes desired, add additional pages, etc.

![Figure 12.14: Selecting a Template for the User Group Site](../../images/user-group-site-template-selected.png)

Also, try visiting the public site of one of the users that belong to the user group. You will see how all of the pages in the user group appear as part of the user site, including the ones copied from the site template and the ones added afterwards.

### Roles [](id=lp-6-1-ugen12-roles-0)

Roles are groupings of users that share a particular function within the portal, according to a particular scope. Roles can be granted permissions to various functions within portlet applications. You can think of a role as a description of a function, such as Message Board Administrators. A role with that name is likely to have permissions relevant to the specific Message Board portlets delegated to it. Users who are placed in this role will inherit these permissions.

The roles page of the control panel serves as a single interface which lets you create roles, assign permissions to them, and assign users to the roles. Roles can be scoped by portal, site, or organization. To create a role, click the *Roles* link, and then click the *Add* button. You can choose a Regular, Site, or Organization role. A regular role is a portal-scoped role. Make a selection and then type a name for your role, a title, and a description. The name field is required but the title and description are optional. If you enter a name and a title, the title will be displayed in the list of roles on the Roles page of the control panel. If you do not enter a title, the name will be displayed. When you have finished, click *Save*.

![Figure 12.15: Roles Page and Role Actions Menu](../../images/01-roles-and-role-actions.png)

After you save, Liferay redirects you to the list of roles. To see what functions you can perform on your new role, click the *Actions* button.

**Edit:** lets you change the name, title, or description of the role.

**Permissions:** allows you to define which users, user groups, or roles have permissions to edit the role.

**Define Permissions:** defines what permissions this role grants. This is outlined in the next section.

**Assign Members:** lets you search and select users in the portal to be assigned to this role. These users will inherit any permissions that have been assigned to this role.

**View Users:** allows you to view the users who have been assigned to this role.

**Delete:** permanently removes a role from the portal.

Next, let's examine how to configure the permissions granted by different roles.

#### Defining Permissions on a Role [](id=lp-6-1-ugen12-defining-permissions-on-a-role-0)

Roles serve as repositories of permissions to be assigned to users who belong to them. So, to use a role, you need to assign members to it and define the permissions that you want to grant to members of the role.

![Figure 12.16: Defining Permissions on a Role](../../images/01-defining-permissions-on-a-role.png)

When you click the *Actions* button on portal-scoped role and select *Define Permissions*, you will be shown a list of all the permissions that have been defined for that role. Click the *Add Permissions* drop-down menu to see a list of the permissions that can be defined. As of Liferay version 6.1, these permissions fall into seven categories: Portal, Site Content, Site Application, Control Panel: Personal, Control Panel: Site, Control Panel: Portal, and Control Panel: Server. For non-portal scoped roles, you need to click on the *Options* link on individual portlets, then *Configuration*, then *Permissions* to assign permissions within the site or organization that owns the portlet. 

Portal permissions cover portal-wide activities that comprise several categories, such as site, organization, location, password policy, etc. This allows you to create a role that, for example, can create new sites within the portal. This would allow you to grant users that particular permission without making them overall portal administrators.

Site Content permissions cover the content that the installed portlets create. If you pick one of the portlets from this list, you'll get options for defining permissions on its content. For example, if you pick Message Boards, you'll see permissions for creating categories or threads, or deleting and moving topics.

Site Application permissions affect the application as a whole. So, using our Message Boards example, an application permission might define who can add the Message Boards portlet to a page.

The control panel permissions affect how the portlet appears to the user in the control panel. Some control panel portlets have a Configuration button, so you can define who gets to see that, as well as who gets to see an application in the control panel.

![Figure 12.17: Message Board Content Permissions](../../images/01-message-board-content-permissions.png)

Each possible action to which permissions can be granted is listed. To grant a permission, check the box next to it. If you want to limit the scope of the permission to a particular site, click the *Limit Scope* link, and then choose the site. Once you have chosen the permissions granted to this role, click *Save*. For a portal-scoped Message Boards Administrator role, you might grant content permissions for every action listed. After you click *Save*, you will see a list of all permissions that are currently granted to this role. From here, you can add more permissions or go back by clicking a link in the breadcrumb list or the *Return to Full Page* link.

Roles are very powerful, and allow portal administrators to define various permissions in whatever combinations they like. This gives you as much flexibility as possible to build the site you have designed.

#### Special Note about the Power Users Role [](id=lp-6-1-ugen12-special-note-about-the-power-users-role-0)

Prior to Liferay 6.0, the default configurations of many Liferay portlets allowed power users, but not regular users, to access them. Liferay 6.0 and subsequent versions grant the same default permissions to both power users and regular users. This way, portal administrators are not forced to use the power users role. However, Liferay encourages those who do to create their own custom permissions for the role. 

---

![tip](../../images/01-tip.png) Note: Prior to Liferay version 6.0, Power Users and Users did *not* have the same default permissions. So if are using Liferay 5.2 or a previous version, it's dangerous to remove the Power Users role from the default user associations: this could remove certain permissions that you expect to apply to all users. If you decide to remove the Power Users role from the default user associations anyway, you will probably want to modify the permissions on certain portlets to make them accessible to all users. To do this, see the section on Plugins Configuration below.

---

Liferay 6.0 introduced a new feature to Liferay's permissions system: teams. Let's examine them next.

### Teams [](id=lp-6-1-ugen12-teams-0)

Teams don't appear as a link in the control panel because they exist *within* sites. Teams allow site administrators a greater degree of flexibility than was possible using just user groups and roles. They allow site administrators to create various sets of users and permissions for site-specific functions. Teams are the preferred method for collecting permissions within a single site. 

If you create a team for one site, the permissions defined for it are not available to any other sites. In contrast, if you assigned a custom role to a user group, the role would be available portal-wide even though the specific permissions defined by it would only apply within the scope of a designated site. Furthermore, team members, unlike user group members, are guaranteed to be members of the desired site.

To create a team within a site, first naviagte to the *Control Panel &rarr; Sites* page then and then select *Actions &rarr; Manage Memberships* for the site within which you want to create a team. Finally, click *View &rarr; Teams* and click the Add Team button.

![Figure 12.18: Creating a Team within a Site](../../images/01-creating-a-team.png)

After you've clicked the *Add Team* button and entered a name and a description, click *Save*. Your new team will appear in the list. To add members, simply click on *Actions &rarr; Assign Members*.

Permission management for teams is handled at the individual portlet level, using the *Options &rarr; Configuration &rarr; Permissions* tab of the portlet itself. Remember that the portlet options link is the wrench symbol at the top of a portlet. This enables users who wouldn't have access to all of the necessary options in the control panel to manage permissions through teams.

[Assigning Portlet Permissions to a Team](../../images/01-assigning-portlet-permissions-to-teams)

To give a team access to a particular portlet function, access the *Permissions* tab of a portlet residing on a page, check the boxes corresponding to permissions you want to assign to the teams, and then click *Save*. That's it! Now your team is ready to perform their functions. Next, let's look at how to configure Liferay's portal settings.

