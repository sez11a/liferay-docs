# Advanced Web Content Management

## Advanced Content Creation [](id=lp-6-1-ugen03-advanced-content-creation-0)

If you've ever launched a web site, you know that as it grows, you can experience growing pains. This is the case especially if you've given lots of people access to the site to make whatever changes they need to make. Without preset limitations, users can display content in any order and in any manner they desire (think huge, flashing letters in a font nobody can read). Content can get stale, especially if those responsible for it don't maintain it like they should. And sometimes, content is published that should never have seen the light of day. 

Thankfully, Liferay WCM helps you handle all of those situations. You can use *Structures* to define which fields are available to users when they create content. These are coupled with *Templates* that define how to display that content. Content won't get stale, because you can take advantage of the *Scheduling* feature to determine when content is displayed and when it's removed. Additionally, you can configure Liferay's built-in *Workflow* system to set up a review and publishing process so that only what you want winds up on the live site. Liferay Portal gives you the management tools you need to run everything from a simple, one-page web site to an enormous, content-rich site.

All of this starts with structures. 

### Using structures [](id=lp-6-1-ugen03-using-structures-0)

Structures are the foundation for web content. They determine which fields are available to users as they create new items for display. Structures not only improve manageability for you the administrator, but also make it much easier for users to quickly add content.

For example, say you're managing an online news magazine. All your articles need to contain the same types of information: a title, a subtitle, an author, and one or more pages of text and images that comprise the body of the article. If Liferay only supported simple content as has been described above, you'd have no way to make sure that your users entered a title, subtitle, and author. You might also get articles that don't match the look and feel of your site. If titles are supposed to be navy blue and they come in from your writers manually set to light blue, you need to spend time reformatting them before they are published.

Structures give you the ability to provide a format for your content so that your users know what needs to be entered to have a complete article. Using structures, you can provide a form for your users which spells out exactly what is required and can be formatted automatically using a template.

To create a structure, you add form controls such as text fields, text boxes, text areas (HTML), check boxes, select boxes, and multi-selection lists, as well as specialized, Liferay-specific application fields such as Image Uploader and Documents and Media right onto the structure. Furthermore, you can move the elements around by dragging them where you want them. This makes it easy for you to prototype different orders for your input fields. Additionally, elements can be grouped together into blocks which can then be repeatable. Template writers can then write a template which loops through these blocks and presents your content in innovative ways, such as in sliding navigation bars, content which scrolls with the user, and more.

Let's look at how we edit a structure.

### Editing a Structure [](id=lp-6-1-ugen03-editing-a-structure-0)

Go back to the control panel and select *Web Content* from the Site section. Click *Add* from the Web Content page to add another piece of content to your portal. Instead of going right for the content this time, however, we'll create a structure instead. To edit a structure, simply click on the *Edit* icon next to the *Structure* heading near the top of the page.

It's very easy to edit structures: all you have to do is drag elements into the structure and then give them names. For instance, select the *Checkbox* element under the *Form Controls* tab and drag it onto the structure. You can do the same with any of the elements. To remove it from the structure, simply select the *Delete* icon (black circle with X) in the upper right corner of the element. Take a moment to add, delete, and rearrange different elements.

![Figure 3.10: Structure Elements](../../images/04-web-content-structure-editor.png)

Liferay supports the following elements in structures:

**FORM FIELDS**

**Text Field:** Used for items such a titles and headings.
**Text Box:** Used for the body of your content or long descriptions.
**Text Area (HTML):** An area that uses a WYSIWYG editor to enhance the content.
**Checkbox:** Allows you to add a checkbox onto your structure. Template developers can use this as a display rule.
**Selection List:** Allows you to add a select box onto your structure.
**Multi-selection List:** Allows you to add a multi-selection list onto your structure.

**APPLICATION FIELDS**

**Image Uploader:** Allows you to add the upload image application into your structure.
**Documents and Media:** Allows you to add the Documents and Media folder hierarchy to your structure.

**MISCELLANEOUS**

**Link to Page:** Inserts a link to another page in the same site.
**Selection Break:** Inserts a break in the content.

These form elements provide all you need to model any information type that you would want to use as web content. Liferay customers have used structures to model everything from articles, to video metadata, to databases of wildlife. You're limited only by your imagination. To fire that imagination, let's look more closely at the form elements. 

### Editing form elements [](id=lp-6-1-ugen03-editing-form-elements-0)

When creating a new structure it is essential that you set variable names that template writers can use to refer to elements on your form. If you don't do this, Liferay generates random variable names, and these can be difficult for a template writer to follow. For example, consider a field called *Author*. You might create this field in your form, but the underlying variable name in the structure might look something like `TextField4882`. The template writer needs to create markup for your structure and place the Author field in a certain spot in the markup. How will he or she know which field is Author, when they're all named randomly? 

To solve this problem, all you need to do is set a variable name for each field as you add it to your structure. Let's do this now. In your structure, add an element *Text Area (HTML)* which has the Field Label *Instructions*. If we wanted to give it the variable name `Steps`, we can do it very easily: at the bottom of every form element is a **Variable Name** field. Replace the generated name with the name you want to use. Now your template writer has a variable by which he or she can refer to this field. 

Below each field is a button labeled *Edit Options*. This contains several other ways to configure your fields: 

**Field Type:** changes the field type, in case you dragged the wrong field type to this location in the form. 
**Field Label:** changes the displayed label for the field. 
**Index Type:** Choose how you want Liferay to index your field for search. You can have it index by keyword, which filters out common words such as *and*, *but*, *the*, and so on, or you can have it index the full text of the field. By default, indexing is turned off. 
**Predefined Value:** If there's a common default value for this field, type it here. 
**Instructions for the User:** You can type instructions in the **Instructions for the User** field and display them as a tooltip by checking the box. 
**Repeatable:** If you want this field to be a repeatable element, check this box. Your users will then be able to add as many copies of this field as they like. For example, if you're creating a structure for articles, you might want a repeatable Author field in case you have multiple authors for a particular article. 
**Required:** Check the box to mark the field required. If a field is required, users cannot submit content using this structure without filling out this field. 

For the Nose-ster structure, type something in the *Instructions for the User* field that helps users know what to put into the Body element (example: *this is an HTML Text area for the body of your content*). Also enable the *Display as Tooltip* box. Now, when users hover over the Help icon near your title, your instructions are displayed. 

As with everything else in Liferay, you can set permissions on structures. Let's see how you'd do that. 

### Assigning Permissions [](id=lp-6-1-ugen03-assigning-permissions-1)

Setting permissions on structures is done using the same procedure as permissions everywhere else in Liferay. Most users should not have the ability to edit structures. Structures are coupled with templates, which require some web development knowledge to create. For this reason, only trusted developers should be able to create structures and templates. Users, of course, should be able to view structures. The View permission enables them to make use of the structures to create content.

![Figure 3.11: View Permission for a Structure](../../images/04-web-content-structure-permissions.png)

You can grant or deny permissions based on Roles, and this is the recommended way to handle permissions for structures.  

Now that you understand what structures are used for, you need to understand the other half of Liferay's web content management system: templates. 

## Advanced Publishing Options [](id=lp-6-1-ugen03-advanced-publishing-options-0)

As we discussed above, as your site becomes larger and more complex, management of the content becomes more challenging. We've gone over Liferay management tools that help you create content quickly and in an orderly fashion. You created a simple announcement with Liferay's structure editor that allows you to quickly design a structure and prepare it for the template designers. Then you applied a template to the structure. You know how to display content using the Web Content Display portlet. Now, you're ready to take advantage of Liferay's advanced publishing options.

If a web site isn't properly managed, it can quickly become out of date, and that drives viewers away. If people are finding your site because of search engines, you don't want them presented with outdated (and now inaccurate) web content.

You also want your content to be found easily by your users. This is done through tags and categories. 

Additionally, you may want to create content and send it through a review and approve process weeks before you want it displayed on the web site. Liferay gives you this flexibility with the *Schedule* and *Workflow* features.  

### Scheduling Web Content [](id=lp-6-1-ugen03-scheduling-web-content-0)

Liferay's WCM lets you define when your content goes live. You can determine when the content is displayed, expired, and/or reviewed. This is an excellent to way to keep your site current and free from outdated (and perhaps incorrect) information. The scheduler is built right into the form that your users make use of to add web content, in the same column as the structure and template selectors.

![Figure 3.15: Schedule for Publishing Content](../../images/04-web-content-schedule.png)

**Display Date:** Sets (within a minute) when content will be displayed.
**Expiration Date:** Sets a date to expire the content. The default is one year.
**Never Auto Expire:** Sets your content to never expire.
**Review Date:** Sets a content review date. 
**Never Review:** Sets the content to never be reviewed.

As you can see, the scheduling feature in Liferay Portal gives you great control in managing when, and for how long, your web content is displayed on your web site. Additionally, you have the ability to determine when your content should be reviewed for accuracy and/or relevance. This makes it possible to manage your growing inventory of content. 

In addition to controlling when content displays, you can use the Display Page feature to control where content displays.

### Display Page [](id=lp-6-1-ugen03-display-page-0)

If you've been using Liferay for a while, or you've just spent a little bit of time with this guide, you might have noticed something about how Liferay handles web content--content is never tied directly to a page. While this can be useful (because it means that you don't have to recreate content if you want to display the same thing on multiple pages), it also means that you don't have a static URL for any web content, which is bad for search engine optimization.

To fix this issue, Liferay has introduced the concept of Display Pages and Canonical URLs. Each web content entry on the portal has a canonical URL, which is the official location of the content that is referenced any time the content is displayed. A Display Page can be any page with an asset publisher set to its default settings. You can create the page yourself, or use the *Content Display Page* page template included with Liferay.

So right now you're thinking "Wait, you just told me that each Web Content item has its own URL, and that this is somehow related to pages where we display a whole bunch of content on the same page?" Yes. That's exactly what I said. Just watch--create a display page somewhere on your portal, using the Content Display Page template. Now click on *Display Page*, and select the page you just created as the display page for the content. 

![Figure 3.16: Selecting a Display Page](../../images/04-web-content-display-page.png)

You can now click the link to display the content, and the canonical URL for content shows in your browser's address bar. You can create your own custom display page, and any additional portlets that you place on the page are displayed along with the content when you access it via the canonical URL. 

![Figure 3.17: The Canonical URL](../../images/04-web-content-canonical-url.png)

Let's move on to tags and categories. 

### Tags and Categories [](id=lp-6-1-ugen03-tags-and-categories-0)

Tags are keywords that are attached to web content in order to help users find content. Categories are hierarchical structures of content that are defined by administrators. Tags and categories make it easier for your users to find content through search or navigation.

By assigning a tag to web content, you define metadata about that content. This is then used by Liferay's search engine to score the results of a search, enabling users to find content that is most relevant to their search. Tags can be created on the fly by creators of content, and it's important to tag content whenever it is created. If you don't tag content, all the search engine has to go on is the full text of the content (if you've made your content indexable), and that might not produce the most optimal results.

Tagging also helps with navigation. Liferay Portal has two portlets specifically designed for navigating content using tags: Tag Cloud and Tag Navigation. If you add either of these to a page, you can use them to show the topics contained in your content.

Tags can be added on the fly or they can be selected from the existing library of tags. For most of the portal, users tag content, but for web content, only the content creator tags the content, because there is no user interface for regular users to tag web content.

It is important that you both tag and categorize your content when you enter it.

Categories are a little bit different, and new users generally ask a valid question when presented with tags and categories: what's the difference? Categories are defined by someone with administrative access to the content. They are hierarchical, tree-like structures that users can use to find content. Categories are different from tags in that they're never created by end users. Instead, categories define how your content is organized from the point of view of the owner of the content. A good example of categories might be the table of contents of a book: it shows the hierarchical structure and organization for all of the content within that book. This shows that the structure of the book has been planned ahead of time by the author. Categories do the same thing as the table of contents. 

By contrast, tags are like the index of a book: they show where many different topics are mentioned within the book in alphabetical order. When a search is done throughout the book, even the author might be surprised at how many times he or she mentions a particular topic outside of its category. So both ways of organizing content are important, especially if your users will be using search to find content.

Tagging and categorizing web content is easy. You can do it at the bottom of the same form you use to add content. If you open the *Categorization* section of the form, you'll be presented with an interface for adding tags and categories.

![Figure 3.18: Tagging and categorizing content can be done at the same time you create it.](../../images/04-web-content-categorization.png)

The control panel contains an interface for managing tags and categories for each site in the portal. This interface can be used to manage all your tags and categories in one place. We'll look at this interface in the next chapter. 

Next, we'll look at how to configure the way pages are accessed by mobile devices.

## Leveraging Liferay's multi-site capabilities

### Backing up and Restoring Pages [](id=lp-6-1-ugen02-backing-up-and-restoring-pages-0)

Next to the *Add Page* button in the Manage Site Pages screen are two buttons labeled *Export* and *Import*. The Export button exports the pages you create into a single file, called a LAR (Liferay Archive). You can then import this file into any server running Liferay to re-create the pages. If you have a LAR that you would like to import, use the *Import* button. Exporting and Importing LARs is a great way to take content from one environment (say, a development or QA environment) and move it all in one shot to your production server. Note that you should not make it a regular process to do this. If you want to regularly move pages from one server to another, you should use Liferay's staging environment, which is covered in chapter 3. 

LARs are also a good way to back up your site's content. You can export them to a specific location on your server which is backed up, and if you ever have to restore your site, all you need to do is import the latest LAR file. One limitation on LAR files, however, is that they are version dependent, so you can't use an export from an old version of Liferay and import it into a newer version.

Let's be good administrators and export a LAR file for backup purposes. Click on the *Export* button and then name the file `nosesterv1.lar`. Use the check boxes to determine what you'd like to export. For this initial export, select everything. Note that if you select the *More Options* link, the list expands to include data from many of Liferay's applications, including the Documents and Media Library, Message Boards, and Web Content. You can also export the theme you're using.

Once you click *Export*, your browser prompts you to save the file. Once you have the file, you can copy it to a backup location for safekeeping or import it into another installation of Liferay Portal. If you must rebuild or wish to revert back to this version of your site, you can import this file by clicking the *Import* button from the Manage Site Pages dialog box, browsing to it, and selecting it. 

Next, we'll look at the options on the right side menu, starting with Look and Feel. 

## Page Templates and Site Templates [](id=lp-6-1-ugen02-page-templates-and-site-templates-0)

*Page Templates* and *Site Templates* are invaluable tools for building similar pages on larger portals. As you continue to add pages to sites in your portal, you'll notice repeatable patterns in the designs of those pages. Page templates enable you to preconfigure a single page and then apply it to any new page that you create. Site Templates allow you to do the same thing, but on the scale of a site--if you have multiple sites that use a similar structure of pages, you can create a single site template and use it to create as many sites as desired. For a full explanation of Page Templates and Site Templates, see chapter 12. 

### Using templates [](id=lp-6-1-ugen03-using-templates-0)

Developers create templates to display the elements of the structure in the markup that they want. Content can then be styled properly using CSS, because markup is generated consistently by the template when structured content is displayed. In essence, templates are scripts that tell Liferay how to display content in the structure. Any changes to the structure require corresponding changes to the template, because new or deleted fields produce errors on the page. If users enter content into a structure, it *must* have a matching template. Without a template, the portal has no idea how to display content which has been created using a custom structure. 

Let's look more closely at the types of templates Liferay supports. 

### Template Types (VM, XSL, FTL, and CSS) [](id=lp-6-1-ugen03-template-types-vm-xsl-ftl-and-css-0)

Liferay supports templates written in four different templating languages, to support the skill sets of the largest number of developers. This increases the chances that you can jump right in and use whichever one you've already used before. If you haven't yet been exposed to any of them, your best bet is Velocity or FreeMarker, as they are less "chatty" than XSL and extremely simple to understand.

**VM** (Velocity Macro): Velocity is a scripting language that lets you mix logic with HTML. This is similar to other scripting languages, such as PHP, that you may have seen before, though Velocity is much simpler. It's been in the product the longest, so it is probably the most widely used language for templates in Liferay WCM. If you haven't used any of the template languages before, we recommend using Velocity: you'll get up to speed the fastest.

**XSL** (Extensible Style Sheet Language): XSL is used in Liferay templates to transform the underlying XML of a structure into markup suitable for the browser. While it may not be as clean and compact as Velocity or FTL, it's widely used for transforming XML into other formats, and it's very likely your developers have already been exposed to it. 

**FTL** (FreeMarker Template Language): FreeMarker is a templating language which could be considered a successor to Velocity, though it is not yet as popular. It has some advantages over Velocity for which it sacrifices some simplicity, yet it is still easy to use. 

**CSS** (Cascading Style Sheets): You can use CSS if your structure is very straightforward and modifications are simple (colors, fonts, layouts, etc). If your structure is more complex, however, you'll need to use one of the other options.

### Adding a Template [](id=lp-6-1-ugen03-adding-a-template-0)

Liferay WCM makes it easy to create structures, templates, and content from the same interface. Let's go through the entire flow of how you'd create a structure, link it to a template, and then create content using them both. We'll use Velocity for our template, and we'll lay out the structure fields systematically to go along with the format we've defined for our content.

![Figure 3.12: Adding Template Interface](../../images/04-web-content-templates-create.png)

1.  Go back to the Web Content section of the control panel and click *Add* under *Web Content*.
2.  Click the *Edit* icon for Structures.
3.  Remove the Content field and add the following fields:

Field Type		Variable Name
----------		-------------
Text				*title*
Text Box		 	*abstract*
Image			*image*
Text Area		*body*

4.  Select *Save* and give the structure a name.
5.  Go back to the main web content page and select the *Templates* tab.
6.  Select *Add Template.*
7.  Type in a name and description.
8.  De-select the box labeled *Cacheable.*
9.  Select VM as the language.
10. Click *Select* and choose a Structure that goes with the Templates.
11. If you've written the script beforehand, you can select *Browse* to upload it from your machine. Otherwise, you can click *Launch Editor* to type the script directly into the small editor window that appears.
12. Select *Save.*
13. Return to the Web Content tab and open the Company News content. You'll see the new element labeled Abstract just below the Title.

Below is the template script for this structure. It is written in Velocity:

    #set ($renderUrlMax = $request.get("render-url-maximized"))
    #set ($namespace = $request.get("portlet-namespace"))
    #set($readmore = $request.get("parameters").get("read_more"))
	<h1>$title.getData()</h1>
    #if ($readmore)
	<p>$abstract.getData()</p>
	<p>$body.getData()</p>
    #else
	<p>
	<img src="${image.getData()}" border="0" align="right">
	$abstract.getData()</p>
	<a href="${renderUrlMax}&${namespace}read_more=true">Read More</a>
    #end

This template is pretty small, but it actually does quite a bit. First, a portlet URL which maximizes the portlet is created. Once this is done, the template gets the namespace of the portlet. This is important to avoid URL collisions with other URLs that might be on the page.

After this, the template attempts to get a request parameter called `read_more`. Whether or not this was successful is the key to the rest of the script:

-   If the template got the `read_more` parameter, it displays the abstract and the body below the title (which is always displayed).

-   If the template didn't get the `read_more` parameter, it displays the image, the abstract, and the link created above, which sets the `read_more` parameter.

When this template is rendered, it looks something like this:

![Figure 3.13: Initial View](../../images/04-web-content-adv-example1.png)

![Figure 3.14: After Clicking "Read More"](../../images/04-web-content-adv-example2.png)

Of course, there is much, much more you can do with structures and templates. Check out the Liferay Wiki ([http://wiki.liferay.com](http://wiki.liferay.com/)) for further information and examples.

### Assigning template permissions [](id=lp-6-1-ugen03-assigning-template-permissions-0)

Permissions for templates are similar to permissions for structures. As with structures, you only want specific developers editing and creating templates. You may, however, want to make the templates viewable to some content creators who understand the template scripting language, but are not directly writing the scripts. You can determine who views the template by selecting from the *Viewable By* select box beneath the *Permissions* tab. By default the *Anyone (Guest Role)* is selected.

You'll also want to determine how users can interact with the template. You can do this by selecting the *More* link.

From the *More* link, you can grant or deny permissions based on Roles. For instance, you may create a role with the ability to update the template and create a second role that can both update and delete. Liferay Portal makes it possible to assign permissions based on the roles and responsibilities within your organization.

Now that you can create all kinds of advanced content with structures and templates, you'll want to know how Liferay makes it possible to manage how that content is published. We'll cover that next. 

## Using site templates [](id=lp-6-1-ugen12-site-templates-0)

When creating a site from a site template, the initial form provides a new option that lets you decide if you want to copy the pages from the template as public pages or as private pages. By default, the site is linked to the site template, and changes to the site template propagate to any site based on it. A checkbox appears that allows users to unlink the site template if the user has permission to do so.

<!-- | TODO: Add screenshot of form used to create a site from a site template | -->

Site templates are very powerful for managing many similar sites. Let's look further at how they work. 



Site Templates can be administered in the control panel, within the portal section of the left menu.

Creating or modifying a site template is done using the same tools used to manage a site. You can use these tools to add a hierarchy of pages. Each page can have any configuration and any number of applications, just like a regular site. When you create a site using a site template, the configuration of pages and applications are copied from the template to the site. By default, all changes made to the site template are automatically copied to sites based on that template.

Site templates can also contain content just like actual sites. This allows you to use a site template to create sample content that appears in your site when it is first created. Changes to a site template's content, however, are not propagated to existing sites that are linked to the site template.

---

![tip](../../images/01-tip.png) **Tip:** If you want to publish a piece of web content to many sites and ensure that modifications are applied to all, don't use site template content for that purpose. Instead, place the content in the global scope and then reference it from a *Web Content Display* application in each site.

---

By default, the following site templates are provided:

- **Community Site:** Provides a preconfigured site for building online communities. The home of a *community site* provides message boards, search, a display of a poll and user statistics of the activity of the members of the community. The site will also be created with a page for a community calendar and a page for a wiki.

- **Intranet Site:** Provides a sample preconfigured site for an intranet. The Home page displays the activities of the members of the site, search, a language chooser and a list of the recent content created in the intranet. It also provides 3 additional pages for *Documents and Media*, *Calendar* and external *News* obtained through public feeds.

The following figure displays the form shown when editing the *Community Site* template:

![Figure 12.6: Site Templates](../../images/01-site-templates.png)

To view and manage the pages of a site template, click the *Open site template* link. This opens the template in a new browser window (or tab) and it can be navigated or managed like you would do for a regular site..

<!-- | TODO: I would change the example below to not use organizations to simplify things | -->

For example, let's suppose that we need to create sites for three suborganizations of the Nosester organization: Engineering, Marketing, and Legal. These are to be private sites designed for each organization's internal use. We could design each site separately but we can save ourselves some work if we create a site template to use instead.

To create a site template, navigate to the control panel and click *Site Templates*. Then click *Add* and enter a name for your template: we'll use *Organization Site Template* for our example. Leave the *Active* and *Allow Site Administrators to Modify the Pages Associated with This Site Template* boxes checked. The *Active* box needs to be checked in order for your template to be usable. If your template is still a work in progress, you can uncheck it so that no one uses it until it's ready. Checking *Allow Site Administrators to Modify the Pages Associated with This Site Template* allows Site Administrators to modify or remove the pages and portlets that the template introduces to their sites--if you want the templates to be completely static, you should uncheck this.

Click on the *Open site template* link to begin adding pages and portlets and configuring the layouts. For our example, we would like our template to include four pages: a Home page with the Activities,  Announcements, and Calendar portlets, a Documents and Media page with the Documents and Media portlet, a Wiki page with the Wiki portlet and a Tag Cloud portlet, and a Message Boards page with the Message Boards and Tag Cloud portlets. The changes are automatically saved as you make them, so once you're finished, return to the Site Templates page of the control panel and select *Save*.

![Figure 12.7: You can see the name of the site template you're currently editing](../../images/editing-site-template.png)

Now let's create the Engineering, Marketing, and Legal organizations whose sites we want to create with our template. Go to the control panel and click *Users and Organizations*. Then click the *Add* button and select *Regular Organization*. Enter a name for your organization, select the *Organization site* tab, and check the *Create Site* box. When you check this box, two drop-down lists appear: one for the site's Public Pages and one for its Private Pages. To use your template to create the site, select the name of your template, *Organization Site*, from the Private Pages drop-down list. Click *Save* to create your site.  You can view the new site by clicking the *Open private pages* link from the newly created organization page. The new site will have all the pages and portlets you created in the template. This feature streamlines the site creation process for administrators, making it easy to quickly create sites. Next, let's discuss how to create and apply page templates.

## Using page templates  [](id=lp-6-1-ugen12-page-templates-0)

Page templates function similarly to site templates but at the page level. Page templates provide a pre-configured page to reuse. Within a page template it is possible to set up a theme, a layout and specific applications and their configuration. Both sites and site templates can utilize page templates for creating new pages.

![Figure 12.8: Page Templates](../../images/server-configuration-page-templates.png)

The Page Templates page of the control panel shows a list of created page templates and lets you create new ones. It also allows you to edit existing templates and configure their permissions. By default three sample page templates are provided:

- Blog: provides a page with three applications related to blogging. It has two columns, the main left column contains the blogs portlet and the small right column provides two side portlets, tags cloud and recent bloggers. The tag cloud application will show the tags used within the site and will allow navigating through the blog entries shown in the main blogs portlet.
- Wiki: provides a page with three applications related to authoring a wiki. It also has two columns, the main left column with the wiki application and two right side portlets to allow navigating through pages by tags and categories.
- Content Display Page: provides a page that is preconfigured to display content. It has three auxiliary applications (tags navigation, categories navigation and search) and an Asset Publisher. The most significant aspect of this page is that the Asset Publisher is preconfigured to be display any web content that is associated with this page. Because of that when creating a web content it will be possible to select any page created from this page template and a unique (canonical) URL for the web content pointing to this page will be created for it.

To add a new page template, click the *Add* button. Then enter a name and description for your template. Leave the *Active* button checked. Click *Save* and then identify your page template in the list. Click its name or use the Actions button to edit the page template. The *Open Page Template* link opens a new browser window which you can use to configure your new page. Any changes you make are automatically saved so you can close the new browser window once you're done.

Note that after a new page template has been created the default permissions are to only allow the creator to use the page template. In order to provide access to it to other users, use the actions menu in the list of templates and choose *Permissions*. Once you see the matrix of roles and permissions, check the *View* permission for the role or roles that are needed to see the page template in the list of available page templates when creating a new page. If you want any user who can create a page to be able to use the page template, just check the *View* permission for the *User* role.

![Figure 12.9: Selecting a Page Template](../../images/control-panel-selecting-page-template.png)

To use your template to create a new page, just navigate to a page over which you have site administrator privileges and select *Add* &rarr; *Page* from the Dockbar. You'll be able to select a page template and type a name for the new page. Alternatively, you can use the control panel. First, in the context selector menu, select the site to which you'd like to add a page and then click on the *Site Pages* link. Then click the *Add Page* button, type a name, select your template from the drop down menu, and click *Add Page* to finish.

![Figure 12.10: Choosing whether or not to automatically apply page template changes to live pages](../../images/automatic-application-page-template-changes.png)

Note that by default, when a site administrator creates a page based on a page template, any future changes to the template are automatically propagated to your page. Site administrators can disable this behavior by editing the page unchecking the *Automatically apply changes done to the page template* box.

<!-- | COMMENT FOR AUTHOR: IMHO, the following paragraph does not fit here because it is of interest in the context of managing a site, not in the context of managing a site template | -->

If staging has been enabled, changes to the page template are automatically propagated to the staged page. These changes still need to be approved before the page is published to live. For this reason, the automatic propagation of page template changes to the staged page cannot be turned off and the *Automatically apply changes done to the page template* checkbox does not appear.

Now that we've looked at site and page templates, let's discuss how to set up and manage user groups.

![EE Only Feature]
### Exporting and Importing Site Templates and Page Templates

If you want to export a site that uses site or page Templates to a different environment (trough a LAR file or remote publication), the Templates must be exported and imported manually in advance, or the import will fail.

To export a Site using a Site Template, use the following process:
1. Go to Control Panel &rarr; Site Templates and click Actions &rarr; Manage Pages for the Site Template that your site is using. 
2. Click *Export* to obtain a LAR file with the content of the Site Template. Be sure to choose the applications and data you want exported. 
3. In your target environment, go to Control Panel &rarr; Site Templates, and create a new Site Template.
4. Click Actions &rarr; Manage Pages for that Site Template and then click *Import*.
5. Upload the LAR file containing your site template's content. 

Now the site can be exported and imported normally to this new environment.

For page templates, the process very similar: 
1. Go to Control Panel &rarr; Page Templates. 
2. Next to the page template you would like to export, click Actions &rarr; Export. This produces a LAR file that can you later import. 
3. On the target environment, go to Control Panel &rarr; Page Templates, and create a new Page Template
4. Next to the new template, click Actions &rarr; Import. 
5. Upload the LAR file containing the exported page template from step 3

The page template can now be imported normally to this new environment.

## Allowing users to customize site pages  [](id=lp-6-1-ugen02-page-customizations-0)

Page Customizations are a new feature in Liferay 6.1. With Page Customizations, any user with the appropriate permissions can create personalized versions of any public page that has customizations enabled. Customizations are based on the rows and columns of a page layout. Administrators can activate or deactivate customizations for any row or column on any page. When users customize a page, they have the option to use either their version or the default version of a page. Users can't see alternate versions of pages other than their own.

![Figure 2.6: Setting Customizable Columns](../../images/04-web-content-personal-customization.png)

When an administrator activates Page Customizations for a page, any portlets that are in a *Customizable* row or column can be moved around the page or removed from the page. Users can add new portlets of their own choosing to these columns of the page. If at any time users determine that they don't like their customizations, they can click *Reset My Customizations* to revert their pages back to the default.

The administrator of the "customizable" page, will have two different views: the *default page* view and the *customized page*. The changes he makes in the *default page* view will affect all users, whereas the changes he makes in the *customized page* view will only affect himself as if he were any other regular user customizing this page. Changes made by the administrator to a not customizable section in the *default view* will be effective immediately for any user. However, if changes are made to a customizable section, the changes made by the administrator will *not* overwrite the users' changes. For this reason, users can view the *default page* and *reset his customizations* from the bar at the top.

In order to allow users to customize a page, these users need to have permission to *Customize* pages (which can be fond under the Site section when assigning permissions to roles). This can be achieved assigning this permission to a role and then assigning this role to the users we want to be able to customize pages. For example, if we want any logged user to be able to customize our customizable pages, we could assign the Customize permission to the role *User* and if we want any member of a site to be able to customize the customizable pages of its sites, we would assign the Customize permission to the role *Site Member*.

Now that you know how to configure pages, let's look at the settings for the site as a whole.

### Customizing the Look and Feel [](id=lp-6-1-ugen02-customizing-the-look-and-feel-0)

When you open the Manage Site Pages dialog box it defaults to the Look and Feel tab. On this tab, you're presented with an interface that allows you to choose a theme for the current site. Themes can transform the entire look of the portal. They are created by developers and are easily installed using the Liferay Marketplace. Since we don't have any themes beyond the default one installed yet, we'll use the default theme for our pages.  

![Figure 2.5: Look and Feel Interface](../../images/04-look-and-feel.png)

Many themes include more than one color scheme. This allows you to keep the existing look and feel while giving your site a different flavor. Change the color scheme from blue to green by selecting *Green* under *Color Schemes*. If you now go back to the site (by clicking *Back to nosester.com* in the top left corner of the control panel), you'll see that some parts of the page are now tinged in a greenish hue. 

If you apply a color scheme to a set of public or private pages it is, by default, applied to each page in the set. If, however, you open the Manage Pages dialog box for a particular page, you can select *Define a specific look and feel for this page* to make the color scheme apply to this page only. You can use this feature to choose a different color scheme for a particular page than the one defined for the set of public or private pages to which it belongs. 

There are a few more configurable settings for your theme. You can switch the bullet style between dots and arrows and you can choose whether or not to show portlet borders by default.

Also notice that themes can apply to regular browsers or mobile devices. You could create another site for mobile users attached to the [http://m.nosester.com](http://m.nosester.com) address and serve up a page designed for the smaller screens on phones.

The *CSS* section allows you to enter custom CSS that will also be served up by your theme. In this way, you can tweak a theme in real time by adding new styles or overriding existing ones. 

The next option configures the logo that appears for your site. 

### Using a custom logo [](id=lp-6-1-ugen02-using-a-custom-logo-0)

If you want to use your own logo for a specific site, use the Logo tab. Adding a custom logo is easy: select the Logo tab and browse to the location of your logo. Make sure that your logo fits the space in the top left corner of the theme you're using for your web site. If you don't, you could wind up with a site that's difficult to navigate, as other page elements are pushed aside to make way for the logo.

In the logo tab, you can also choose whether or not to display the site name on the site. If you check the box labeled *Show Site Name* the site name will appear in the top right corner of the page. This option is enabled by default, and cannot be disabled if the *Allow Site Administrators to set their own logo* option is disabled in *Portal Settings*. It is also not available on the default community -- only newly created communities and user pages have the option to have the name display.

When you click on a specific page, some other options become available to you. 

### Changing options for individual pages [](id=lp-6-1-ugen02-changing-options-for-individual-pages-0)

When you select a single page, some different options appear. Let's look at what these do. 

**Details:** lets you name the page for any localizations you need. You can also set the HTML title that appears in the browser window for the page. Finally, you can set an easy to remember, friendly URL for the page. 

**SEO:** provides several means of optimizing the data the page provides to an indexer that's crawling the page. You can set the various meta tags for description, keywords, and robots. There's also a separate Robots section that lets you tell indexing robots how frequently the page is updated and how it should be prioritized. If the page is localized, you can select a box to make Liferay generate canonical links by language. If you want to set some of these settings for the entire site, you can specify them from the Sitemaps and Robots tabs of the Manage Site Settings dialog box (see below).

---

![tip](../../images/01-tip.png) In previous versions of Liferay, it was possible that a single page could be indexed multiple times. In Liferay 6.1, to improve search ratings for your pages, all URLs that direct to the same page will only create one entry in the index. So, for example while previously the simple URL *http://www.nosester.com/web/guest/blog/-/blogs/thenose* and different versions of the URL which provided additional information about the referring page and what not, like *http://www.nosester.com/web/guest/blog/-/blogs/thenose?redirect_33&...* would have different entries in the index, now there is only one. From the search engines point of view, this will make your pages rank higher, since, for example, any references to any variations of a specific URL will be considered references to the one page.] 

---

**Look and Feel:** lets you set a page-specific theme. 

**Layout:** lets you specify how portlets are arranged on a page. Choose from the available installed templates to modify the layout. It's very easy for developers to define custom layouts and add them to the list, and this is covered in both the *Liferay Developer's Guide* and in [*Liferay in Action*](http://manning.com/sezov).  

**JavaScript:** gives you the ability to paste custom JavaScript code to be executed on this page. 

**Custom fields:** If custom fields have been defined for pages (which can be done from the *Custom Fields* page of the control panel), they appear here. These are metadata about the page, and can be anything you like, such as author or creation date. 

**Advanced:** contains several optional features. You can set a query string to provide parameters to the page. This can become useful to web content templates, which you'll see in the next chapter. You can set a target for the page so that it either pops up in a particularly named window or appears in a frameset. And you can set an icon for the page that appears in the navigation menu. 

**Mobile Rule Groups:** allows you to apply rules for how this page should be rendered for various mobile devices. You can set these up in the *Mobile Device Rules* section of the control panel. 

**Customization Settings:** lets you mark specific sections of the page that you want users to be able to customize.

Note that the *Manage &rarr; Page Layout* menu directs you to the same Layout tab that's in *Manage &rarr; Page*. 

### Modifying Page Layouts [](id=lp-6-1-ugen02-modifying-page-layouts-0)

Page layouts allow you to arrange your pages so that the content appears the way you want it to. Liferay comes with many layouts already defined. Developers can create more and they can be deployed to your portal for your use.

In order to prepare for the portlets we'll soon be adding, let's change the layout of the Collaboration page. To access layouts, select *Manage &rarr; Page Layout* from the Dockbar.

Now, select the *2 Columns (70/30)* layout and click *Save*. Once saved, you'll return to the page and it'll seem as though nothing has happened. Once we start adding portlets, however, you'll notice that the page is now equally divided into two columns. You can stack portlets on top of each other in these columns. There are, of course, more complicated layouts available, and you can play around with them to get the layout that you want.

Sometimes a particular layout is *almost* what you want, but not quite. In this case, use the Nested Portlets portlet to embed a layout inside another layout. This portlet is a container for other portlets. It lets you select from any of the layouts installed in Liferay, just like the layouts for a page. This gives you virtually unlimited options for laying out your pages.

The next option in the *Manage* menu is page customizations.  

### Configuring Site Settings [](id=lp-6-1-ugen02-configuring-site-settings-0)

As with Site Pages, you can access Site Settings through the control panel or directly from the site using the Dockbar (*Manage* &rarr; *Site Settings*). 

![Figure 2.7: Site Settings](../../images/web-content-site-settings.png)

You'll find options to specify details and metadata about your site, set up friendly URLs and virtual hosts, configure search engine optimization settings, turn staging on or off, and specify a Google Analytics ID. Let's take a closer look. 

**Details:** allows an administrator to change the description and membership type of a site, and also to specify tags and categories for the site. The membership type can be set as open, restricted, or private based on the privacy needs of the site. Users can join and leave an open site at will. To join a restricted site, a user has to be added by the site administrator, but can request to be added through the Sites section of the control panel. A private site is like a restricted site, but doesn't appear in the Sites section of the control panel for users who aren't members.  

**Pages:** From Site Settings, click on *Pages* to manage some basic features of the pages on a site. If no pages have been defined yet, you can set site templates for the public or private pages. If pages already exist, links are provided to view them. You can also change the site's application adapter, which is a special type of hook plugin that customizes out of the box functionality for specific sites. 

**Site URL:** Set the virtual host for your site and/or a friendly URL here. The *Friendly URL* option lets you manage the path to your site in the portal's URL. This needs to be a unique name, of course. Having a human-readable friendly URL assists indexing bots, and is critical to good search engine optimization. 

*Virtual Hosts* make web navigation much easier for your users by connecting a domain name to a site. This tab allows you to define a domain name (i.e., www.mycompany.com) for your site. This can be a full domain or a subdomain. This enables you to host a number of web sites as separate sites on one Liferay server.

For instance, if we set this up for Nose-ster's Development Network, users in that site could use developers.nosester.com to get to their site, provided that Nose-ster's network administrators created the domain name and pointed it to the Liferay server.

To set this up, the DNS name *developers.nosester.com* should point to your portal's IP address first. Then enter http://developers.noseter.com in the Virtual Host tab for the Developers site. This helps users quickly access their site without having to recall an extended URL.

**Site Template:** If you've created the site from a site template, this section displays information about the link between the site template and the site. Specifically, you can see which site template was used and whether or not it allows modifications to the pages inherited from it by site administrators. If you're not using site templates for this site, you can safely ignore this section.

**Sitemap:** lets you send a sitemap to some search engines so they can crawl your site. It uses the sitemap protocol, which is an industry standard. You can publish your site to Yahoo or Google, and their web crawlers will use the sitemap to index your site. Liferay Portal makes this very simple for administrators by generating the sitemap XML automatically for all public web sites.

By selecting one of the search engine links, the sitemap will be sent to them. It's only necessary to do this once per site. The search engine crawler will periodically crawl the sitemap once you've made the initial request.

If you're interested in seeing what is being sent to the search engines, select the *Preview* link to see the generated XML.

**Robots:** If you're using virtual hosting for this site, you can configure `robots.txt` rules for the domain. The Robots page gives you the option to configure your `robots.txt` for both public and private pages on a site. If you don't have Virtual Hosting set up, this tab is rather boring. 

**Staging:** enables you to edit and revise a page behind the scenes, and then publish changes to your site once they have been completed and reviewed. For a full explanation of Staging, see Chapter 3: Managing Content.

**Analytics:** allows you to integrate your pages with Google Analytics. Liferay provides seamless integration with Google Analytics, allowing you to place your ID in one place, and then it will get inserted automatically on every page. This enables you to focus your efforts on building the page, rather than remembering to put the code everywhere. Google Analytics is a free service which lets you do all kinds of traffic analysis on your site, so you can see who visits, where visitors are from, and what pages they most often visit. This helps you to tweak your site so that you can provide the most relevant content to your users.

Next in the menu is Site Memberships, which is covered in chapter 12. There, you'll learn how to administer a Liferay portal and define users and permissions. 

## Advanced Publishing Options [](id=lp-6-1-ugen03-advanced-publishing-options-0)

As we discussed above, as your site becomes larger and more complex, management of the content becomes more challenging. We've gone over Liferay management tools that help you create content quickly and in an orderly fashion. You created a simple announcement with Liferay's structure editor that allows you to quickly design a structure and prepare it for the template designers. Then you applied a template to the structure. You know how to display content using the Web Content Display portlet. Now, you're ready to take advantage of Liferay's advanced publishing options.

If a web site isn't properly managed, it can quickly become out of date, and that drives viewers away. If people are finding your site because of search engines, you don't want them presented with outdated (and now inaccurate) web content.

You also want your content to be found easily by your users. This is done through tags and categories. 

Additionally, you may want to create content and send it through a review and approve process weeks before you want it displayed on the web site. Liferay gives you this flexibility with the *Schedule* and *Workflow* features.  

### Scheduling Web Content [](id=lp-6-1-ugen03-scheduling-web-content-0)

Liferay's WCM lets you define when your content goes live. You can determine when the content is displayed, expired, and/or reviewed. This is an excellent to way to keep your site current and free from outdated (and perhaps incorrect) information. The scheduler is built right into the form that your users make use of to add web content, in the same column as the structure and template selectors.

![Figure 3.15: Schedule for Publishing Content](../../images/04-web-content-schedule.png)

**Display Date:** Sets (within a minute) when content will be displayed.
**Expiration Date:** Sets a date to expire the content. The default is one year.
**Never Auto Expire:** Sets your content to never expire.
**Review Date:** Sets a content review date. 
**Never Review:** Sets the content to never be reviewed.

As you can see, the scheduling feature in Liferay Portal gives you great control in managing when, and for how long, your web content is displayed on your web site. Additionally, you have the ability to determine when your content should be reviewed for accuracy and/or relevance. This makes it possible to manage your growing inventory of content. 

In addition to controlling when content displays, you can use the Display Page feature to control where content displays.

### Display Page [](id=lp-6-1-ugen03-display-page-0)

If you've been using Liferay for a while, or you've just spent a little bit of time with this guide, you might have noticed something about how Liferay handles web content--content is never tied directly to a page. While this can be useful (because it means that you don't have to recreate content if you want to display the same thing on multiple pages), it also means that you don't have a static URL for any web content, which is bad for search engine optimization.

To fix this issue, Liferay has introduced the concept of Display Pages and Canonical URLs. Each web content entry on the portal has a canonical URL, which is the official location of the content that is referenced any time the content is displayed. A Display Page can be any page with an asset publisher set to its default settings. You can create the page yourself, or use the *Content Display Page* page template included with Liferay.

So right now you're thinking "Wait, you just told me that each Web Content item has its own URL, and that this is somehow related to pages where we display a whole bunch of content on the same page?" Yes. That's exactly what I said. Just watch--create a display page somewhere on your portal, using the Content Display Page template. Now click on *Display Page*, and select the page you just created as the display page for the content. 

![Figure 3.16: Selecting a Display Page](../../images/04-web-content-display-page.png)

You can now click the link to display the content, and the canonical URL for content shows in your browser's address bar. You can create your own custom display page, and any additional portlets that you place on the page are displayed along with the content when you access it via the canonical URL. 

![Figure 3.17: The Canonical URL](../../images/04-web-content-canonical-url.png)

Let's move on to tags and categories. 

### Tags and Categories [](id=lp-6-1-ugen03-tags-and-categories-0)

Tags are keywords that are attached to web content in order to help users find content. Categories are hierarchical structures of content that are defined by administrators. Tags and categories make it easier for your users to find content through search or navigation.

By assigning a tag to web content, you define metadata about that content. This is then used by Liferay's search engine to score the results of a search, enabling users to find content that is most relevant to their search. Tags can be created on the fly by creators of content, and it's important to tag content whenever it is created. If you don't tag content, all the search engine has to go on is the full text of the content (if you've made your content indexable), and that might not produce the most optimal results.

Tagging also helps with navigation. Liferay Portal has two portlets specifically designed for navigating content using tags: Tag Cloud and Tag Navigation. If you add either of these to a page, you can use them to show the topics contained in your content.

Tags can be added on the fly or they can be selected from the existing library of tags. For most of the portal, users tag content, but for web content, only the content creator tags the content, because there is no user interface for regular users to tag web content.

It is important that you both tag and categorize your content when you enter it.

Categories are a little bit different, and new users generally ask a valid question when presented with tags and categories: what's the difference? Categories are defined by someone with administrative access to the content. They are hierarchical, tree-like structures that users can use to find content. Categories are different from tags in that they're never created by end users. Instead, categories define how your content is organized from the point of view of the owner of the content. A good example of categories might be the table of contents of a book: it shows the hierarchical structure and organization for all of the content within that book. This shows that the structure of the book has been planned ahead of time by the author. Categories do the same thing as the table of contents. 

By contrast, tags are like the index of a book: they show where many different topics are mentioned within the book in alphabetical order. When a search is done throughout the book, even the author might be surprised at how many times he or she mentions a particular topic outside of its category. So both ways of organizing content are important, especially if your users will be using search to find content.

Tagging and categorizing web content is easy. You can do it at the bottom of the same form you use to add content. If you open the *Categorization* section of the form, you'll be presented with an interface for adding tags and categories.

![Figure 3.18: Tagging and categorizing content can be done at the same time you create it.](../../images/04-web-content-categorization.png)

The control panel contains an interface for managing tags and categories for each site in the portal. This interface can be used to manage all your tags and categories in one place. We'll look at this interface in the next chapter. 

Next, we'll look at how to configure the way pages are accessed by mobile devices.

### Assigning Permissions [](id=lp-6-1-ugen03-assigning-permissions-0)

Permissions in Liferay WCM work the same way they do everywhere else in Liferay. By default, content is viewable by Anyone (Guest Role). You can limit viewable permissions by selecting any Role from the drop-down or in the list. Additionally, Liferay Portal provides the ability to customize permissions in more detail. Select the *More Options* link next to the drop down button, and you'll find the different activities you can grant or deny to your web content.

![Figure 3.4: Permissions for Web Content](../../images/04-web-content-content-permissions.png)

For this piece of web content, we don't need to change anything. After you're finished with permissions, click *Save*. This saves the content in draft form. Once you're satisfied with your changes, select *Publish*. This makes the content available for display, but we still have some work to do to enable users to see it. In Liferay WCM, all content resides in a container, which is one of two portlets: Web Content Display or Web Content List. By far the most frequently used is the *Web Content Display* portlet. 

### Localization [](id=lp-6-1-ugen03-localization-0)

Previous versions of Liferay had the ability to create and manage different translations of your web content, but with Liferay 6.1 we've added several improvements. 

When you create a new piece of Web Content, you have the ability to choose a default language. If you click *Change* you can select your default language from a large number of languages that Liferay supports. Before you can create a translation, you must finish creating the content in your default language and save it. Once you've done that, editing the content provides you with the option to *Add Translation*.

![Figure 3.5: Adding a translation](../../images/04-web-content-content-translation.png)

After you click *Add Translation*, you can select a language, either by scrolling through the list or by entering the language you want to use in the search box. When you select a language, a lightbox opens within your browser window, enabling you to easily compare the original with the new translation. Once you are done with the translation, click *Save*, and the translation is added to the list of *Available Translations*. 

![Figure 3.6: Adding a translation](../../images/04-web-content-content-translation-2.png)

The ability to completely delete a translation in one step has also been added. Instead of simply disabling a translation or having to go through a multistep process to remove it, you can now simply open the translation that you don't want and click *Remove Translation*.

When you create a new web content structure, each field you create has a *Localizable* checkbox displayed next to it. This enables you to control what can and can't be changed in the translation process. For example, if you don't want images or content titles to be changed when the content is translated, you can make sure that those fields aren't listed as localizable. When you follow the steps above to localize content, only fields within the structure that had the *Localizable* box checked appear within the translation window.

Let's go back to the page where we want the content displayed and add the Web Content Display portlet (in this case, the Welcome page).

## Staging page publication [](id=lp-6-1-ugen03-staging-and-workflow-0)

Next, we'll look at some of the most powerful features of Liferay WCM: staging and workflow. 

Staging is an important feature of Liferay WCM. The concept of staging is a simple one: you can modify your site behind the scenes, and then publish it all in one shot. You don't want your users seeing your web site change before their eyes as you're modifying it, do you? Liferay's staging environment allows you to make changes to your site in a specialized *staging area*, and then when you're finished, publish the whole site to your users.

You can use staging in multiple ways. You can have a staging server—-a separate instance of Liferay Portal which is used just for staging. Content creators can then use this server to make their changes while the live server handles the incoming user traffic. When changes to the site are ready to be published, they are pushed over the network to the live server.

You can also use staging in the same instance of your Liferay Portal. In this configuration, you have a *local* staging environment: you host both your staging environment and your live environment on the same server. Either way, once set up, the interface is the same; the only difference comes when it's actually time to publish your content.

In addition, Liferay 6.1 adds the capability to create multiple variations of staged pages, so that you can manage several future versions of a site simultaneously. Variations can be merged and published through an intuitive UI. Let's jump in to see how to use staging.

### Enabling the staging environment [](id=lp-6-1-ugen03-enabling-the-staging-environment-0)

Staging configuration can be found in the Site Settings UI. The Staging tab allows us to make changes in a staging environment and preview our work before publishing it to the live site. Let's create a staging environment for Nose-ster's home page.

First, you'll add a new page. Click *Add &rarr; Page* from the toolbar in the default site and name the new page *News and Events*. Next, click the *View Pages* button and add the Alerts and Announcements portlets to it.

Now we're ready to activate the staging feature for this site. Go to the control panel then to *Site Settings* and select *Staging* from under the *Advanced* heading.

![Figure 3.25: You can decide to use versioning and choose what content should be staged.](../../images/04-web-content-staging.png)

We'll assume we don't have a separate staging server, and so we'll select a staging type of *Local Live*. If you want to set up a remote staging environment, it's easy. First, select *Remote Live*, and then supply the name or IP of the remote server where staged content should be published, the port (80 if Liferay is sitting behind a web server, or the port your application server is listening on if not), and the remote site or organization ID. You can find this ID by selecting *Actions &rarr; Edit* on any site in the control panel. Either way, once you make a selection (*Local Live* or *Remote Live*), more options become available.

We'll cover many of the collaboration portlets listed here when we come to chapter 4. For now you just need to be aware that the option is available to enable or disable staging for any of them, and you need to decide if you want to stage content for these portlets. In the case of the collaborative portlets, the answer is usually "no." Why? Because portlets such as the Message Boards are designed for user interaction. If their content were staged, you'd have to manually publish your site whenever somebody posted a message on the message boards in order to make that message appear on the live site.

Generally, you'll want web content to be staged, because end users aren't creating that kind of content-—web content is the stuff you publish to your site. But portlets like the message boards or the wiki would likely benefit from *not* being staged.

Enabling *Page Versioning* makes it so that you can work in parallel with other users on multiple versions of the same pages, and it gives you the flexibility to revert easily to a previous version if you encounter any issues. Check *Enabled On Public Pages* so that we can look at versioning.

### Using the staging environment [](id=lp-6-1-ugen03-using-the-staging-environment-0)

If you navigate back to the News and Events page you'll now notice some new items along the top of the screen. These will help us manage staged pages. You'll also notice that most of your page management options have been removed, because you can now no longer directly edit live pages--you'll now use the staging environment to do that. Click on *Staging* to view the staged area. Your management options are restored, and you have some new options related to staging.

![Figure 3.26: You can see the new bar that staging adds to the top of your screen.](../../images/04-web-content-staging-live-page.png)

Add the Calendar portlet and then click on *Live* from the Dockbar. Notice that the Calendar portlet isn't there. That's because you've staged a change to the page, but haven't published that change yet to the live site. Go back to the staged page, and look at the options you have available. From here you can *Undo* changes, view a *History* of changes, *Mark as Ready for Publication*, and *Manage Page Variations*.

**Undo/Redo:** allows you to step back/forward through recent changes to a page, which can save you the time of manually adding or removing portlets if you make a mistake.

**History:** shows you the list of revisions of the page, based on publication dates. You can go to any change in the revision history and see how the pages looked at that point.

**Manage Page Variations:** allows you to work in parallel on multiple versions of a staged page. We will explain this later.

After you're done making changes to the staged page, click the *Mark as Ready for Publication* button. The status of the page changes from *Draft* to *Ready for Publication* and any changes you've made can be published to the Live Site. When you publish a page to live, only the version which was *Marked as Ready for Publication* is published.

The dropdown next to the Staging link at the top gives you the option to *Publish to Live Now* or *Schedule Publication to Live*.

**Publish to Live Now:** immediately pushes any changes to the Live Site.

**Schedule Publication to Live:** lets you set a specific date to publish or to setup recurring publishing. You could use this, for example, to publish all changes made during the week every Monday morning without any further intervention.

Click on *Mark as Ready for Publication* and then *Publish to Live Now* to publish your Calendar portlet to the live site.

Content publication can be also controlled using staging. Calendar events are staged by default (this can be changed in Staging Configuration). If you create an event in the staged site, it isn't visible in the live site until you publish it to the live site following the same steps you just performed (you can select which types of content are published when you publish to the live site). If workflow is enabled for Calendar Events, the event needs to go through the workflow process before it can be published to the live site.

![Figure 3.27: Ready to publish to the live site.  ](../../images/04-web-content-staging-publish.png)

One of the most powerful features of staging is page variations. Let's see how to use them to create multiple different variations of your site's pages for different purposes.

### Site Pages Variations [](id=lp-6-1-ugen03-site-pages-variations-0)

Let's say that you're working on a product-oriented site where you'll have several major changes to a page or a set of pages over a short period of time, and you need to be working on multiple versions of the site at the same time to ensure that everything has been properly reviewed before it goes live. With staging in Liferay 6.1 you can do this with **Page Variations**.

For example, you can create several page variations, enabling the marketing team to give your site a completely different look and feel for Christmas. At the same time, the product management team can work on a different version that will be published the day after Christmas for launching of a new product. Additionally, the product management team is considering two different ideas for the home page of the site, so they can create several page variations of the home page inside their product launch site.

Variations only affect pages and not the content, which means that all the existing content in your staging site is shared by all your variations. In different site page variations you can have different logos, look and feel for your pages, different applications on these pages, different configuration of these applications, and even different pages. One page can exist in just one site page variation or in several of them.

By default, we only have one site page variation which is called **Main Variation**. In order to create a new one, use the dropdown next to the *Staging* link and click on *Manage Site Pages Variations*. This brings you to a list of the existing site page variations for your site. Click *Add Site Pages Variation* to create a new one. From the *Add Site Pages Variation* screen, you can set a Name, Description, and also set your new variation to copy the content from an existing variation. There are several options to choose in this selector. 

**Any existing Site Pages Variation:** creates a new site page variation that contains only the last version of all the pages that exist in this variation. The current variation must be marked as ready for publication. 

**All Site Pages Variation:** creates a new variation that contains the last version marked as ready for publication from any single page existing in any other variation.

**None:** creates a new, empty variation.

You are also able to rename any variation. For example, edit the Main Variation and change its name to something that makes more sense in your site, such as *Basic*, *Master*, *Regular* and create a variation for Christmas.

You can switch between different variations by clicking on them from the staging menu bar. It's also possible to set permissions on each variation, so that certain users have access to manage some, but not all variations.

You can now go to the home page of your Christmas variation and change the logo, apply a new theme, move portlets around, change the order of the pages, and configure different portlets. The other variations won't be affected. You can even delete existing pages or add new ones (remember to *Mark as Ready for Publication* when you are finished with your changes). 

When you delete a page, it is deleted only in the current variation, and the same happens when you add a new page. If you try to access a page which was deleted in the current variation, Liferay informs you that this page is not *enabled* in this variation, and you can enable it. To enable it, navigate to the *Manage* &rarr; *Site Pages* screen. Here, all the existing pages for all the variations are shown in a tree. Pages that are not enabled for the current variation are shown in a lighter color.

To publish a variation to the live site, click on *Publish to Live now* in the dropdown next to the variation name. Publications can also be scheduled independently for different variations. For example, you could have a variation called *Mondays* which is published to the live site every Monday and another one called *Day 1* which is published to the live site every first day of each month.

You can also have variations for a single page inside a site page variation, which allows you to work in parallel in different versions of a page. For example, you might work on two different proposals for the design of the home page for the Christmas variation. These page variations only exist inside a site Page variation.

To create a new page variation, click *Manage Page Variations* on the staging toolbar. This brings you to a list of existing page variations for the current page (by default, there is only one called *Main Variation*). You can create more or rename the existing one. You can switch between different page variations using the toolbar containing the page variations below the site pages variations toolbar. When you decide which page variation should be published, mark it as *Ready for Publication*. Only one page variation can be marked as ready for publication and that is the one that gets published to the live site.

![Figure 3.28: Creating a new Page Variation](../../images/04-web-content-add-site-pages-variation.png)

For example, we could create a page variation called Thanksgiving for the News and Events page inside of the Christmas variation and another one called Christmas Day to display different content on those particular days.

![Figure 3.29: The Thanksgiving Page Variation.](../../images/04-web-content-branch-thanksgiving.png)

Another powerful feature is the possibility of *merging* Site Pages Variations. In order to merge two Site Pages Variations, you need to go to the Manage Site Variations screen. From there, you should click on *Merge* on the Site Pages Variation that you want to use as the base, and you will be asked to choose the Site Pages Variation to merge on top of it. Merging works in the following way:

* New pages that don't exist in the base Variation, will be added.
* If a page exists in both Site Pages variations, and at least one version of the page was marked as ready for publication, then the latest version marked as ready will be added as a new Page Variation in the target page of the base Variation. (Note that older versions or page variations not marked as ready for publication won't be copied. However, merge can be executed as many times as needed and will create the needed pages variations in the appropriate page of the base Site Pages Variation).
* Merging does not affect content nor will overwrite anything in the base Variation, it will just add more versions, pages and page variations as needed.

Let's finish our discussion of staging by outlining a few more features. 

### Wrapping up staging [](id=lp-6-1-ugen03-wrapping-up-staging-0)

You can enable staging on an individual site basis, depending on your needs. This makes it easy to put strict controls in place for your public web site, but to open things up for individual sites that don't need such strict controls. Liferay's staging environment is extremely easy to use and makes maintaining a content-rich web site a snap. 

Let's go one step further by introducing workflow into the mix.

## Creating teams for advanced site membership management

## Creating sites for mobile devices [](id=lp-6-1-ugen03-mobile-device-rules-0)

Next, we'll look at how to configure the way pages are accessed by mobile devices.

Mobile device rules allow you to configure sets of rules and use those rules to alter the behavior of the portal based on the device being used to access Liferay. The proportion of mobile devices users browsing the web has been steadily increasing so it's important to be able to handle different kinds of devices appropriately. For instance, you can configure the look and feel of Liferay pages accessed by smartphone or tablet users differently than those accessed by PC users. 

Both sites and individual pages can be configured with any number of rule groups. A rule group is designed to describe a group of devices. It can contain one or more rules which describe a category of devices, such as all android devices or all iOS tablets. Rule groups can be prioritized to determine which one will be applied to a given page request. You can define as many rules as you need to classify all the devices you want to take actions on.

In order to configure mobile device rules, you must install the wurlf-web plugin. This plugin enables Liferay to detect which mobile device or operating system is being used for any given request. To install the plugin, navigate to the Plugins Installation section of the control panel, located under the Server heading. Then search for the wurlf-web plugin and click *Install*. 

Alternatively, if you have a working copy of Liferay's plugin repository, you can install the plugin manually. The plugin is available from Liferay's public SVN repository at [http://svn.liferay.com/repos/public/plugins/trunk/webs/wurfl-web/](http://svn.liferay.com/repos/public/plugins/trunk/webs/wurfl-web/) or from Liferay's public Github repository at [https://github.com/liferay/liferay-plugins/tree/master/webs/wurfl-web](https://github.com/liferay/liferay-plugins/tree/master/webs/wurfl-web). Once you have checked out or cloned the repository, navigate to the plugins root directory and create a `build.username.properties` file. Add the line `app.server.dir = <path-to-app-server>`, replacing `<path-to-app-server>` with the path to the application server running Liferay on your machine. Then navigate to the wurlf-web directory and execute the ant deploy target.

You can access the Mobile Device Rules administrative page from the Content section of the control panel. Select the appropriate scope using the context menu selector so that your rule groups are available where you expect them to be. The Mobile Device Rules administrative page displays a list of defined rule groups and allows you to add more. To add rules to a rule group, select *Actions* &rarr; *Manage Rules* or just click on a rule group to edit it and then click the *Manage Rules* link.

![Figure 3.19: You can manage device rules from the Mobile Device Rules administrative page.](../../images/mobile-device-rules.png)

The rules defined for a rule group, along with the priorities of the rule groups selected for a particular site or page, determine which rule will be applied to a given request. From the Manage Rules page for a specific rule set, you can add a rule by specifying a rule type. Note that, by default, only the Simple Rule type is available. However, the rules are designed to be extensible and additional rule types can be added by your developers. Once added, you can edit the rule to specify a device type and operating system.

![Figure 3.20: You need to install the wurlf-web plugin to populate the OS list.](../../images/mobile-device-editing-rule.png)

Once you've created some mobile device rule groups and added some rules to them, you'll be ready to set up some actions. The actions defined for a rule group determine what happens to a particular request when the request's device has been detected and the rule group has been found to apply. 

You can add actions to a rule group from the Site Pages page of the control panel. Select either the public or private pages and then look for the *Mobile Rule Groups* link in the right-hand menu. Use the *Select Rule Group* button to select rule groups to be applied either to a site or to a single page. If you select the page group itself from the left-hand menu, the selected rule group will apply to all the pages of the site by default. However, if you select an individual page and then click the *Select Rule Group* button, the rule groups will apply to only that page. You can select multiple rule groups for a particular site or page and order them by priority. The rule groups are checked in decreasing order of priority: the actions defined by the first rule group that applies are executed.

![Figure 3.21: You can select a mobile device rule group to apply for a site or page from the Site Pages section of the control panel.](../../images/mobile-device-selection.png)

To add actions to a selected rule group, use the *Actions* &rarr; *Manage Actions* button and then click *Add Action*. By default, there are four kinds of actions that be configured for mobile rule groups: layout template modifications, theme modifications, simple redirects, and site redirects. Layout template modifications let you change the way portlets are arranged on pages delivered to mobile devices and themes modifications let you select a specific look and feel. If it makes more sense for you to create separate mobile versions of certain sites or pages you can use a redirect to make sure that mobile devices users get to the right page. To define a simple redirect, you need to specify a URL. To define a site redirect, you only need to specify the site name and page name of the page you're redirecting to. Like mobile device rules, mobile device actions are designed to be extensible. Your developers can define custom actions in addition to the four actions provided by default. Next, let's discuss Liferay's asset publisher, which you can use to publish many different kinds of portal content.

