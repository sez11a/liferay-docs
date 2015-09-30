# Creating Display Contexts in Documents and Media

Display Contexts (DCs) are classes that model portlet screens. They're a part of
an ongoing effort to modularize Liferay Portal's UI. This modularization results
in better source code structure and facilitates customizing Liferay dynamically
using OSGi modules. The first DCs created in Liferay Portal apply to the
Documents and Media portlet:

- [`DLEditFileEntryDisplayContext`](http://docs.liferay.com/portal/7.0-a1/javadocs/com/liferay/portlet/documentlibrary/display/context/DLEditFileEntryDisplayContext.html): models the edit file entry/version screen
- [`DLViewFileVersionDisplayContext`](http://docs.liferay.com/portal/7.0-a1/javadocs/com/liferay/portlet/documentlibrary/display/context/DLViewFileVersionDisplayContext.html): models the view file entry/version screen

To learn the anatomy of Display Contexts and how to create one, let's consider
the `DLViewFileVersionDisplayContext` interface. 

## What is a DLViewFileVersionDisplayContext for?

DCs should relate to one entire view, or whole screen, of a portlet. The
`DLViewFileVersionDisplayContext` bends the rule, as it relates only to *part*
of the Documents and Media portlet's Files List view. In this view, the DC
renders only the context menu--the part of the view that shows the details of a
specific version of a document (a `FileVersion`). The
`DLViewFileVersionDisplayContext`, however, exemplifies important aspects of
Display Contexts.

A DC, furthermore, is always defined by an interface which has a default
implementation, often one in Liferay's core. Liferay's default
implementations provide basic functionality that Liferay users expect. These
implementations can, however, be easily extended or replaced by another
implementation deployed in an OSGi bundle.

## What Kind of Things Does DLViewFileVersionDisplayContext Decide?

A look at the methods in `DLViewFileVersionDisplayContext` shows that it decides
what actions the File Version View makes available to a user. For example, the
following methods respectively return the menu and the list of toolbar
items to show the user for a
[`FileVersion`](http://docs.liferay.com/portal/7.0-a1/javadocs/com/liferay/portal/kernel/repository/model/FileVersion.html). 

- `Menu getMenu()`
- `List<ToolbarItem> getToolbarItems()`

The default implementation in Portal returns a menu with typical actions
*Edit*, *Checkout*, *Delete*, etc. But it would be pretty easy to deploy an OSGi
bundle that filters out the *Delete* action, for example, to hide it from the
user. 

The following `DLViewFileVersionDisplayContext` methods return values already
present in the `FileVersion` model object, but that are filtered according to
what's needed in the UI. 

- `List<DDMStructure> getDDMStructures()`
- `DDMFormValues getDDMFormValues(DDMStructure ddmStructure)`

An extension implementation could, for example, override the `getDDMFormValues`
method to remove some form values from a metadata section of the UI. Or an
implementation could modify existing form values or simulate form values which
aren't stored in the database. 

Lastly, consider the following `DLViewFileVersionDisplayContext` method that
renders a preview of a `FileVersion` instance. 

    void renderPreview(HttpServletRequest request, HttpServletResponse response)

This method renders HTML output directly instead of returning a model object
(e.g., a `MenuItem`). The method is, therefore, less extensible and less
maintainable than the previously mentioned types of methods. It's a cross
between a hook and a proper DC method. For unstructured content, such as preview
content, it's unfortunately impossible to define a proper model object like
`MenuItem` or `ToolbarItem`. 

## How do I Extend a DLViewFileVersionDisplayContext?

Overriding the default implementation of a DC is straightforward. You can see a
sample customization of `DLViewFileVersionDisplayContext` in Liferay's source
code in GitHub repository folder 
[`modules/apps/document-library/document-library-google-docs`](https://github.com/liferay/liferay-portal/tree/master/modules/apps/document-library/document-library-google-docs).

To extend the DC, first implement a DC factory for the type of DC you want to
customize. Here's an example DC factory that implements
`DLViewFileVersionDisplayContextFactory` and that declares itself an OSGi
component that provides a service. 

    @Component(
       service = DLViewFileVersionDisplayContextFactory.class
    )
    public class CustomizedDLViewFileVersionDisplayContextFactory
       implements DLViewFileVersionDisplayContextFactory {

        // Methods and fields go here ...

    }

Then you could, for example, implement the method
`getDLFileVersionActionsDisplayContext` like this: 

    @Override
    public DLViewFileVersionDisplayContext getDLFileVersionActionsDisplayContext(
        DLViewFileVersionDisplayContext parentDLFileEntryActionsDisplayContext,
        HttpServletRequest request, HttpServletResponse response,
        FileVersion fileVersion) {

        if (shouldBeCustomized(fileVersion)) {
            return new CustomizedDLViewFileVersionDisplayContext(
                parentDLFileEntryActionsDisplayContext, request, response,
                fileVersion);
        }

        return parentDLFileEntryActionsDisplayContext;
    }

DC factory methods typically take as parameters a parent DC, the request and
response of the view being rendered, and one or more business objects (in this
case a `FileVersion`) associated with the view. The DC factory uses this
information to decide whether to apply its DC to the calling view. 

You could, for example, implement a customized DC that only applies to image
files. Its factory's `getDLFileVersionActionsDisplayContext` method could
inspect the `FileVersion`'s extension in a helper method called
`shouldBeCustomized`, that only returns true if the extension is for a PNG, JPG,
or GIF file. 

The `getDLFileVersionActionsDisplayContext` method uses the parent DC for the
following reasons: 

1.  If the factory doesn't customize the default DC, it can return the parent DC.

2.  If the factory customizes the DC, it can optionally apply methods of the
    parent DC. If you want only to modify slightly the default behavior of the
    parent DC, you'll probably want to access the parent DC. 

You may be wondering what happens if you register several factories. You can
deploy as many as you want and they'll be chained in descending order
according to their [service ranking](http://www.osgi.org/javadoc/r4v42/org/osgi/framework/Constants.html#SERVICE%5FRANKING).

## Example: How to Remove the Delete Menu Item Using a Display Context

Say you want to remove the *Delete* action from Document and Media's *File
Version View* menus. You must first implement the applicable DC factory, as
demonstrated previously. Then you override the `List<MenuItem> getMenuItems()`
method in your `CustomizedDLViewFileVersionDisplayContext`, as shown in the
paraphrased code below: 

    public class CustomizedDLViewFileVersionDisplayContext extends
    BaseDLViewFileVersionDisplayContext {

        // Other methods ...

        @Override public Menu getMenu() throws PortalException {
            Menu menu = super.getMenu();
            List<MenuItem> menuItems = menu.getMenuItems();

            for (MenuItem menuItem : menuItems) { 
                if (DLUIItemKeys.DELETE.equals(menuItem.getKey())) {
                    menuItems.remove(menuItem); 
                    break; 
                } 
          }

          return menu; 
        }

        // More methods ...

    }

Here's the logic it implements:

1.  Call the parent DC to obtain the default list of menu items.

2.  Traverse the list to find the *Delete* menu item and remove it. Note:
    Documents and Media provides the
    [`DLUIItemKeys`](http://docs.liferay.com/portal/7.0-a1/javadocs/com/liferay/portlet/documentlibrary/display/context/DLUIItemKeys.html)
    API for implementations to programmatically identify menu items.

3.  Return the modified list of menu items.

The `CustomizedDLViewFileVersionDisplayContext` class extends
`BaseDLViewFileVersionDisplayContext`, instead of directly implementing
`DLViewFileVersionDisplayContext`. Extending an existing Liferay base DC isn't
mandatory, but it can provide a better migration path for deploying DCs in
future versions of Liferay. Imagine what would happen if the next version of
Liferay ships with a new method in the `DLViewFileVersionDisplayContext`
interface: if your customized DC directly implements the interface, it won't
compile; but if your DC extends `BaseDLViewFileVersionDisplayContext`, it will
compile without trouble and the new method will already be implemented by
Liferay's default implementation. Extending a Liferay base implementation
facilitates migrating your DC customizations to future versions of Liferay.

As you've learned, display contexts help modularize Liferay's UI layer and make
it more extensible by using a robust approach to alleviate maintenance. DC's
help make Liferay's APIs explicit and compilable to facilitate maintenance and
source code reuse, both inside Liferay and in third party code. 

**Related Topics**

[Understandting Liferay's Module Framework](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/understanding-liferays-module-framework)

[Creating a Simple Bundle](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/creating-a-simple-bundle)

[Creating Liferay Plugins/Bundles in the Blade Environment](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/creating-liferay-plugins-bundles-in-the-blade-environment)
