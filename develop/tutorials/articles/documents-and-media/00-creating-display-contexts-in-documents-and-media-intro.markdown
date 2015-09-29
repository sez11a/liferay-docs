# Creating Display Contexts in Documents and Media

Display contexts are an ongoing effort (currently applied to Documents & Media
portlet) to modularize the user interface (UI) in Liferay Portal. This
modularization will lead to a better source code structure and the possibility
to customize Liferay Portal's UI by deploying OSGi modules without any need to
develop hooks or ext plugins.

Let's look at the anatomy of a Display Context (from now on DC) by looking at
one specific type of DC. We will use `DLViewFileVersionDisplayContext`.

## What is DLViewFileVersionDisplayContext for?

First of all, DCs are always related to one action (page, window) of the
portlet. For instance, the `DLViewFileVersionDisplayContext` is the responsible
for the D&M page showing the details of a specific version of a document (a
`FileVersion`).

Nevertheless, a DC may be used in a different context until every view is moved
to its own DC. For example, the `DLViewFileVersionDisplayContext` is used to
render the context menus associated to each file in the *Files List View* of
D&M. This is because the *Files List View* has no DC assigned yet. Once the
*Files List View* has a DC, it will be used instead of
`DLViewFileVersionDisplayContext`, and the shared logic (that deciding which
actions should be rendered) will be placed in a common class used by both DCs.

To finish with, a DC is always defined by an interface which, usually, has a
default implementation in Liferay's core. The default implementation does the
basic functionality everybody expects from Liferay, but it can be easily
extended or replaced by deploying an OSGi bundle.

## What kind of things does DLViewFileVersionDisplayContext decide?

A look at the methods in `DLViewFileVersionDisplayContext` shows that it is
responsible for deciding what actions are available to the user in the *File
Version View*. For example, the methods:

- `List<MenuItem> getMenuItems()`
- `List<ToolbarItem> getToolbarItems()`

return the list of menu or toolbar items to show for the `FileVersion` currently
shown to the user. The default implementation in the portal returns the *Edit*,
*Checkout*, *Delete*, etc. actions you are used to, but it would be pretty easy
to deploy an OSGi bundle which filters such list of items and, for example,
removes the *Delete* one so that it is not shown to the user.

Some other methods like:

- `List<DDMStructure> getDDMStructures()`
- `Fields getFields(DDMStructure ddmStructure)`

return values already present in the `FileVersion` model object, but filtered
according to the UI needs. An overriding implementation could, for instance,
remove some fields from a metadata section of the UI simply implementing the
`getFields()` method. It could also simulate fields which are not stored in the
database, or modify existing fields.

Finally, another type of method sometimes found in DCs is:

- `void renderPreview(HttpServletRequest request, HttpServletResponse response)`

which is a method called by Liferay whenever it needs to render a preview of a
`FileVersion`. This method renders its HTML output directly instead of returning
a model object (like, say, `MenuItem`). Thus, it is less extensible than the
previous types of method and less maintainable. It's somewhat between a hook and
a proper DC method. Unfortunately for such an unstructured content as a preview,
it is not possible to define a proper model object like `MenuItem` or
`ToolbarItem`.

## How do I extend DLViewFileVersionDisplayContext?

Overriding the default implementation of a DC is easy and straightforward. You
can see a sample customization of `DLViewFileVersionDisplayContext` inside
Liferay's source code, under
[`modules/apps/document-library/document-library-google-docs`](https://github.com/liferay/liferay-portal/tree/master/modules/apps/document-library/document-library-google-docs).

Whenever you want to extend a DC, you must create an OSGi module that will be
deployed inside Liferay. How to create such modules is beyond the scope of this
article, so we will assume that you know how to develop and deploy OSGi modules
for Liferay.

The first step is implementing a DC factory for the DC you want to customize. In
our example, we will implement `DLViewFileVersionDisplayContextFactory` and
declare it as an OSGi component providing such service
(`DLViewFileVersionDisplayContextFactory`). For that, we just need to declare a
class as:

    @Component(
       service = DLViewFileVersionDisplayContextFactory.class
    )
    public class CustomizedDLViewFileVersionDisplayContextFactory
       implements DLViewFileVersionDisplayContextFactory {

        ...

    }

Then, we implement the method in `DLViewFileVersionDisplayContextFactory`: 

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

The DC factory methods receive a parent DC, the request and response of the view
being rendered, and one or more business object associated with the view (in
this case a `FileVersion`). With that information, the DC factory needs to
decide whether or not its DC applies to the caller view. For example, we could
implement a customized DC that only applies to image files. We could check the
extension of the `FileVersion` in the `shouldBeCustomized()` method and only
return true if it is PNG, JPG or GIF.

The parent DC is provided for two reasons:

1. If the factory is not willing to customize the default DC it should return
the parent DC as it was provided.

2. If the factory decides to customize the DC, it is handy to pass the parent DC
to the customized implementation so that it can call it and then operate on the
results of the parent DC. Of course, this is not mandatory, and you can
implement your customized DC from scratch, but sometimes you just want to
slightly modify the default behaviour, rather than changing it totally.

You may be wondering what happens if you register several factories. The answer
is that you can deploy as many as you want and they will be chained according to
their [service ranking](http://www.osgi.org/javadoc/r4v42/org/osgi/framework/Constants.html#SERVICE%5FRANKING).

## A real world example: how to remove the Delete menu item in our example DC

Say you want to remove the *Delete* action from the *File Version View* menus.
To do that you must first implement the DC factory as shown in the previous
section and then override the `List<MenuItem> getMenuItems()` method in your
`CustomizedDLViewFileVersionDisplayContext` as shown below:

    public class CustomizedDLViewFileVersionDisplayContext extends
    BaseDLViewFileVersionDisplayContext {

        ...

        @Override public List<MenuItem> getMenuItems() throws PortalException {
        List<MenuItem> menuItems = super.getMenuItems();

            for (MenuItem menuItem : menuItems) { if
            (DLUIItemKeys.DELETE.equals(menuItem.getKey())) {
            menuItems.remove(menuItem); break; } }

            return menuItems; }

        ...

    }

As you can see, what we are doing is:

1.  Calling the parent DC to obtain the default list of menu items.

2.  Traversing the list to find the *Delete* menu item and remove it (note that
    D&M provides the `DLUIItemKeys` API so that overriders can identify
    `MenuItems` programatically without any black magic).

3.  Return the modified list of menu items.

Note that `CustomizedDLViewFileVersionDisplayContext` extends from
`BaseDLViewFileVersionDisplayContext` instead of directly implementing
`DLViewFileVersionDisplayContext`. This is not mandatory, but provides a better
migration path to deploy current DCs in future versions of Liferay.

Imagine what would happen if the next version of Liferay is shipped with a new
method in the `DLViewFileVersionDisplayContext` interface: if your customized DC
directly implements the interface it will not compile, but if you extend
`BaseDLViewFileVersionDisplayContext` your customized DC will compile without
trouble and that functionality will be provided by Liferay's default
implementation. That way, you won't need any effort to migrate your
customizations to future versions of Liferay.

## Conclusion

Display contexts will hopefully help to modularize Liferay's UI layer and make
it, at the same time, extensible by third parties in a robust way which will
need less future maintenance than a hook or an ext plugin. They will also make
Liferay's APIs explicit and compilable which will, in the end, lead to an easier
to maintain and reuse source code both inside Liferay and in third party code
outside of it.

Remember that you can see a real life example inside Liferay's source code,
under
[`modules/apps/document-library/document-library-google-docs`](https://github.com/liferay/liferay-portal/tree/master/modules/apps/document-library/document-library-google-docs).
