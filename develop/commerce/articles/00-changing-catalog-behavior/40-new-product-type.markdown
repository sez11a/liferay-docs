# Creating New Product Types

@commerce@ supports three different 
[product types](/web/emporio/documentation/-/knowledge_base/1-0/product-types) 
out of the box, but additional types can be created. This tutorial covers
creating a new product type and generating admin screens for managing products
of that type.

You need four things to create a new product type:

-  An implementation of the `CPType` interface to create the type

-  An admin screen (or screens) created using @product@'s 
[Screen Navigation Framework](/develop/tutorials/-/knowledge_base/7-1/screen-navigation-framework)

-  A file---typically a JSP---containing the UI for the admin screen

-  Java class(es) handling your type's business logic

Follow these steps to create a new product type:

1.  Create a new module add a dependency on the `commerce.product.api` module
    to its `build.gradle` file.

2.  Implement the `com.liferay.commerce.product.type.CPType` interface.

3.  Create your product type's admin screen by implementing the
    `ScreenNavigationCategory` and `ScreenNavigationEntry` interfaces, and
    creating corresponding JSP files.

+$$$

This tutorial covers the first two steps in detail. For more on generating
admin screens, see 
[Screen Navigation Framework](/develop/tutorials/-/knowledge_base/7-1/screen-navigation-framework).

$$$

## Defining the New Type

First, create a module and add dependencies to its `build.gradle`. The build
script should look like this:

    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"

    dependencies {
        compileOnly group: "com.liferay.commerce", name: "com.liferay.commerce.product.api", version: "2.0.0-SNAPSHOT"
        compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "3.5.0"
        compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
        compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
    }

Then create a new Java class implementing the `CPType` interface. Insert the
following Component annotation before the class declaration:

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

![Figure 1: The `Integer=20` setting places the "sample" type effectively in
fourth position in the menu.](../../images/commerce-cptype-menu.png)

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

## Providing the Type's Functionality

At this point, you can deploy your module and create products belonging to your
new type. Other than the type's label, however, these products will have no
special characteristics distinguishing them from
[simple](/web/emporio/documentation/-/knowledge_base/1-0/product-types)
products. 

Defining your new type's functionality involves extending the catalog's UI to
give administrators the ability to configure products of that type.

![Figure 2: Admin screens are provided by extending product tab menu. The
*Virtual* tab, above, is an admin screen unique to the virtual product
type.](../../images/cptype-menu.png)

Follow these steps:

1.  Create your product type's admin screen by implementing the
    `ScreenNavigationCategory` and `ScreenNavigationEntry` interfaces.

2.  Provide the admin screen with UI, typically with a JSP.

3.  Write the Java class(es) handling your type's business logic. Map the JSP's
    tags to this code as your use case requires.

See 
[Screen Navigation Framework](/develop/tutorials/-/knowledge_base/7-1/screen-navigation-framework)
for details on how to implement the `ScreenNavigationCategory` and
`ScreenNavigationEntry` interfaces. In this case, the implementation's
`build.gradle` file dependencies should look like this:

    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"

    dependencies {
    compileOnly group: "com.liferay", name: "com.liferay.frontend.taglib", version: "3.0.0"
    compileOnly group: "com.liferay", name: "com.liferay.frontend.taglib.soy", version: "2.0.0"
    compileOnly group: "com.liferay.commerce", name: "com.liferay.commerce.product.api", version: "2.0.0"
    compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "3.5.0"
    compileOnly group: "com.liferay.portal", name: "com.liferay.util.taglib", version: "3.0.0"
    compileOnly group: "javax.portlet", name: "portlet-api", version: "3.0.0"
    compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
    compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
    }
