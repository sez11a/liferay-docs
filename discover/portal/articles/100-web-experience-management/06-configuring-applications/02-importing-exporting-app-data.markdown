# Exporting/Importing App Data [](id=exporting-importing-app-data)

There are times when you want to export/import specific content created from an
application, and not deal with site data at all. There are many apps that let
you export or import their app data individually. For example, 

- Blogs
- Bookmarks
- Dynamic Data Lists
- Forms
- Knowledge Base
- Message Boards
- Web Content
- Wiki
- etc.

Exporting app data produces a `.lar` file that you can save
and import into another application of the same type. To import app
data, you must select a `.lar` file. Be careful not to confuse app-specific
`.lar` files with site-specific `.lar` files. See the
[Importing/Exporting Pages and Content](/discover/portal/-/knowledge_base/7-1/importing-exporting-pages-and-content)
article for information on importing/exporting site page data.

You can export/import app content two ways. You can navigate to the app's
administrative area located in the Product Menu, or you can visit the
corresponding widget that resides on a page. Both export/import menus work the
same, but the administrative area may hold different content than its widget
counterpart (e.g., Web Content Admin in Product Menu and Web Content Display
widget do not offer same content for export/import), so be wary of your
selection. 

To export or import app data from the app's administrative area, follow the
steps below.

1.  Navigate to the app's designated area in the Product Menu. For example, if
    you plan to export Web Content app data, navigate to *Content* &rarr; *Web
    Content*.

2.  Click the *Options* button (![Options](../../../images/icon-options.png))
    from the top right of the page and select *Export/Import*.

3.  Select the *Export* or *Import* tab to begin configuring the respective
    process.

![Figure 1: You can access an app's administrative *Export/Import* feature by selecting its Options menu.](../../../images/admin-app-export-import-feature.png)

To export or import app data from a widget, follow the steps below:

1.  Ensure the widget you're interested in exporting/importing from is available
    on a page. You can add widgets from the *Add*
    (![Add](../../../images/icon-add-app.png)) &rarr; *Widgets* menu.

2.  Select the widget's *Options* button
    (![Options](../../../images/icon-widget-options.png)) and select
    *Export/Import*.

3.  Select the *Export* or *Import* tab to begin configuring the respective
    process.

![Figure 2: You can access a widget's *Export/Import* feature by selecting its Options menu.](../../../images/widget-export-import-feature.png)

Now that you know how to navigate to the *Export/Import* menus, you'll explore
the export process next.

## Exporting App Data [](id=exporting-app-data)

To export app data, create a new export process by selecting the *New Export
Process* tab (default). You have several export options to configure.

First, you can choose to export your application's configuration settings. This
exports your customized settings that you've configured from your application's
*Options* &rarr; *Configuration* menu.

Next, you can select a *Date Range* of content that you'd like to export.
Content that has been added to your app within your specified date range is
included in the `.lar` file. The following date range choices are available:

**All:** publishes all content regardless of its creation or last modification
date.

**Date Range:** publishes content based on a specified date range. You can set a
start and end date/time window. The content created or modified within that
window of time is published.

**Last...:** publishes content based on a set amount of time since the current
time. For example, you can set the date range to the past 48 hours, starting
from the current time.

By checking the *Content* box, you can choose specific content you'd like to
export. When you check the *Content* box, more options appear, letting you
choose specific kinds of metadata to include. For example, if you have a wiki
page with referenced content that you don't wish to include, you can simply
check the *Wiki Pages* checkbox and uncheck the *Referenced Content* checkbox.
Another option in the Content section of the Export/Import window is the
selection of content types. Two familiar content types in your Liferay instance
are *Comments* and *Ratings*. If you wish to include these entities in your
`.lar` file, select *Change* and select them from the checklist. For more
information on managing content types, see the
[Managing Content Types in Staging](/discover/portal/-/knowledge_base/7-1/managing-content-types-in-staging)
article.

Next, you can choose to export individual deletions. This lets delete operations
performed for content types be exported to the LAR file.

Lastly, you can choose whether to include permissions for your exported content.
The permissions assigned for the exported app window are included if you enable
the *Export Permissions* selector.

After you've exported your app's data, switch to the *Current and Previous* tab
to view ongoing export processes and the history of past exports. You can also
download the exported `.lar` file from this tab.

## Importing App Data [](id=importing-app-data)

To import app data, you can select the LAR using your file explorer or by
dragging and dropping the file between the dotted lines.

![Figure 3: When importing app data, you can choose a LAR file using the file explorer or drag and drop the file between the dotted lines.](../../../images/import-menu.png)

Your LAR file is uploaded and displayed to you for review. Click *Continue*.

Now that you've uploaded and confirmed your LAR file, you're given a similar
screen to what you'd be offered during export. Several of these options are
covered in great detail in the
[Importing/Exporting Pages and Content](/discover/portal/-/knowledge_base/7-1/importing-exporting-pages-and-content)
section. There are a couple of additional options, however, available---*Update
Data* and *Authorship of the Content*. Here's options and descriptions
for each section:

**Update Data**

- *Mirror*: All data and content inside the imported LAR is newly created
  the first time while maintaining a reference to the source. Subsequent imports
  from the same source updates entries instead of creating new entries.
- *Mirror with overwriting*: Same behavior as the mirror strategy, but if a
  document or an image with the same name is found, it is overwritten.
- *Copy as New*: All data and content inside the imported LAR is created as new
  entries within the current site every time the LAR is imported.

**Authorship of the Content**

- *Use the Original Author*: Keep authorship of imported content whenever
  possible. Use the current user as author if the original one is not found.
- *Use the Current User as Author*: Assign the current user as the author of all
  imported content.

Once you've selected the appropriate options, select *Import*, and your app's
data is imported and ready for use.
