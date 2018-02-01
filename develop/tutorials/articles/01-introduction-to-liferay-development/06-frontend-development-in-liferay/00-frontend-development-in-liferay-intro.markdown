# Introduction to Front-End Development [](id=introduction-to-frontend-development)

@product@ offers a wide range of tools and options to make front-end development
easy. 

## JavaScript [](id=javascript)

Longtime users of @product@ are probably familiar with Alloy UI, but other
front-end technologies are also available:

-   EcmaScript ES2015+
-   [Metal.js](https://metaljs.com/) (developed by Liferay)
-   [AlloyUI](https://alloyui.com/) (developed by Liferay)
-   jQuery (included)
-   Lodash (included)

## Loaders [](id=loaders)

Ordinarily, loading modules requires you to know when they are needed, where
they are located at build time, whether you want to bundle them together or load
them independently, and to assemble them at runtime. Since all that is a hassle,
@product@'s Loaders (YUI/AUI and AMD) handle it for you. The loaders can use
YUI/AUI modules, AMD modules, and npm modules (in AMD format). Just provide
a little information<!--do we have any more detail about this? I hate to leave
this vague-NR--> about your module and they take care of the rest.

## npm [](id=npm)

To use npm in your portlets, create an OSGi bundle with all the npm dependencies
extracted and modified to work with the Liferay AMD Loader. @product@'s
`liferay-npm-bundler` is built for this purpose, and speeds the process with
presets for common module types (AMD, React, Angular JS, etc.). 

Simply include the bundler in your project, and after configuration<!--again,I
hate to leave this vague. Do we have any information on how exactly the bundler
should be configured?-NR--> it bundles your portlet for OSGi and transpiles your
code for the Liferay AMD Loader. This lets you share JavaScript modules and take
advantage of semantic versioning among portlets that use the same npm modules on
the page.

## Lexicon and Clay [](id=lexicon)

@product@ uses its own design language, called
[Lexicon](https://lexicondesign.io/docs/lexicon/), to provide a common framework
for building consistent UIs and user experiences across the Liferay product
ecosystem. The web implementation of Lexicon (CSS, JS, and HTML) is called
[Clay](https://claycss.com/docs/clay/). It is automatically available to
application developers through a set of CSS classes, or our tag library. 

## Templates [](id=templates)

For templating, Java EE's JSP is available as well as FreeMarker, but the
platform's modularity also permits Google's Soy (aka Closure Templates), or
whatever else you like. 

## Themes [](id=themes)

A @product@ Theme provides the overall look and feel for a site. Themes are
a combination of CSS, JavaScript, HTML, and FreeMarker templates. Although the
default themes are (we don't mind saying) quite nice, you may wish differentiate
your site with your own look and feel.

A wide variety of tools and environments are available to choose from, including
the [Theme Builder Gradle
Plugin](/develop/reference/-/knowledge_base/7-1/theme-builder-gradle-plugin), 
the [Liferay Theme
Generator](/develop/tutorials/-/knowledge_base/7-1/themes-generator),
[@ide@](/develop/tutorials/-/knowledge_base/7-1/creating-themes-with-liferay-ide),
and [Blade CLI](/develop/tutorials/-/knowledge_base/7-1/blade-cli)'s [Theme
Template](/develop/reference/-/knowledge_base/7-1/theme-template), so you can
use what you're most comfortable with.

## Front-End Extensions [](id=frontend-extensions)

The availability of development customizations and extension points is another
benefit of modularity. These extensions assure the stability, conformity, and
future evolution of your applications.

Below are some of the available front-end extensions:

- [Theme Contributors](/develop/tutorials/-/knowledge_base/7-1/theme-contributors)
- [Context Contributors](/develop/tutorials/-/knowledge_base/7-1/context-contributors)
- [Portlet Decorators](/develop/tutorials/-/knowledge_base/7-1/portlet-decorators)
- [Editor Config Contributors](/develop/tutorials/-/knowledge_base/7-1/modifying-an-editors-configuration)
