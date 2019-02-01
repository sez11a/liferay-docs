# Creating New Product Types [](id=creating-new-product-types)

@commerce@ supports three 
[product types](/web/emporio/documentation/-/knowledge_base/1-0/product-types) 
out of the box, but additional types can be created. This tutorial covers
creating a new product type and generating admin screens for its products.

You need four things to create a new product type:

-  An implementation of the `CPType` interface to create the type

-  An admin screen (or screens) created using @product@'s 
[Screen Navigation Framework](/develop/tutorials/-/knowledge_base/7-1/screen-navigation-framework)

-  A file---typically a JSP---containing the UI for the admin screen

-  Java class(es) handling your type's business logic

Follow these steps:

1.  Create a new module and add a dependency on the `commerce.product.api` module
    to its `build.gradle` file.

2.  Implement the `com.liferay.commerce.product.type.CPType` interface.

3.  Create your product type's admin screen by implementing the
    `ScreenNavigationCategory` and `ScreenNavigationEntry` interfaces. 

4.  Create JSP files to populate the admin screen, and provide whatever
    additional Java classes are necessary to support them.

## Defining the New Type [](id=defining-the-new-type)

First, create a module and add dependencies to its `build.gradle`. The build
script should look like this:

    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"

    dependencies {
        compileOnly group: "com.liferay.commerce", name: "com.liferay.commerce.product.api", version: "2.0.0"
        compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "3.5.0"
        compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
        compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
    }

Then create a new Java class implementing the `CPType` interface. Insert the
following `@Component` annotation before the class declaration:

    @Component(
        immediate = true,
        property = {
            "commerce.product.type.display.order:Integer=20",
            "commerce.product.type.name=sample"
        },
        service = CPType.class
    )

The `display.order` property sets the position of your new type in the selection
box that an admin uses to add a new product to the catalog. The out-of-the-box
product types---*Simple*, *Grouped* and *Virtual*---have their `display.order`
properties set at 5, 10, and 15, respectively, leaving plenty of space for you
to disperse your types wherever you want.

![Figure 1: The `Integer=20` setting places the "sample" type in fourth position in the menu.](../images/commerce-cptype-menu.png)

Then implement the interface: 

    public class SampleCPType implements CPType {

        @Override
        public void deleteCPDefinition(long cpDefinitionId) throws PortalException {
        }

        @Override
        public String getLabel(Locale locale) {
            return LanguageUtil.get(locale, "sample");
        }

        @Override
        public String getName() {
            return "sample";
        }

    }

The interface's `getLabel` method returns the localized version of your type's
name to display in the UI, while the `getName` method returns a unique
identifier for the type to be stored in the database.

## Creating an Admin Screen[](id=providing-the-types-functionality)

At this point, you can deploy your module and create products belonging to your
new type. Other than the type's label, however, these products have no special
characteristics distinguishing them from
[simple](/web/emporio/documentation/-/knowledge_base/1-0/product-types)
products. 

Defining your new type's functionality involves extending the catalog's UI to
give administrators the ability to configure products.

![Figure 2: Admin screens are provided by extending product tab menu. The *Virtual* tab, for example, is an admin screen unique to the virtual product type.](../images/cptype-tab-menu.png)

First, add some additional dependencies to your `build.gradle` file:

    compileOnly group: "com.liferay", name: "com.liferay.frontend.taglib", version: "3.0.0"
    compileOnly group: "com.liferay", name: "com.liferay.frontend.taglib.soy", version: "2.0.0"
    compileOnly group: "com.liferay.portal", name: "com.liferay.util.taglib", version: "3.0.0"
    compileOnly group: "javax.portlet", name: "portlet-api", version: "3.0.0"

Then create a new component in the same package as your `CPType` implementation,
and implement the `ScreenNavigationCategory` and `ScreenNavigationEntry`
interfaces:

    @Component(
        property = {
            "screen.navigation.category.order:Integer=20",
            "screen.navigation.entry.order:Integer=20"
        },
        service = {ScreenNavigationCategory.class, ScreenNavigationEntry.class}
    )
    public class SampleCPTypeVirtualScreenNavigationEntry
        implements ScreenNavigationCategory, ScreenNavigationEntry<CPDefinition> {

Note that the two `order` properties locate the your admin screen in the
product's tab bar.

![Figure 3: In this case, the product type admin tab is placed immediately after the *Details* tab.](../images/cptype-tab-menu-2.png)

Include the methods required by the interfaces:

    @Override
    public String getCategoryKey() {
        return "sample";
    }

    @Override
    public String getEntryKey() {
        return "sample";
    }

    @Override
    public String getLabel(Locale locale) {
        ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
            "content.Language", locale, getClass());

        return LanguageUtil.get(resourceBundle, "sample");
    }

    @Override
    public String getScreenNavigationKey() {
        return "cp.definition.general";
    }

    @Override
    public boolean isVisible(User user, CPDefinition cpDefinition) {
        if (cpDefinition == null) {
            return false;
        }

        String productTypeName = cpDefinition.getProductTypeName();

        if (productTypeName.equals(getCategoryKey())) {
            return true;
        }

        return false;
    }

    @Override
    public void render(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse)
        throws IOException {

        try {
            _jspRenderer.renderJSP(
            _servletContext, httpServletRequest, httpServletResponse,
            "/edit_sample_product.jsp");
        } finally {
        }
    }

The `getLabel` method provides a name for the new tab on the frontend. You
must have a `Language.properties` file containing a language key for
appropriate string (`sample` in the example above). For more details on the
above methods, see
[Screen Navigation Framework](/develop/tutorials/-/knowledge_base/7-1/screen-navigation-framework).

Finally, include the following references:

        @Reference
        private JSPRenderer _jspRenderer;

        @Reference(target = "(osgi.web.symbolicname=com.liferay.commerce.sample)")
        private ServletContext _servletContext;

    }

The `osgi.web.symbolicname` should match the `Bundle-SymbolicName` from your
module's `bnd.bnd` file.

## Providing a UI

At this point if you deploy your module to the server and create a product of
the new type, you'll see that you've added a new item to the product tab menu,
but the new tab has no content.

![Figure 4: The admin screen exists, but is not much use.](../images/cptype-empty-tab.png)

Note that the `render` method calls for a `edit_sample_product.jsp` which
should be placed in your `META-INF/resources` folder.

Create the JSP. This example creates a simple text field:

    <%@ include file="/init.jsp" %>

    <aui:input name="sampleInput" type="text"/>

The example above requires one import, which---per convention---belongs in an
`init.jsp` file in the same directory.

    <%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

![Figure 4: At this point your admin screen displays with content. Well done.](../images/cptype-full-tab.png)

Depending on your use case, you need additional Java classes in your module to
handle user input from the admin screen and implement any other business logic.
If your screen looks like the image above, however, you've successfully created
a new product and created an admin screen to manage it.
