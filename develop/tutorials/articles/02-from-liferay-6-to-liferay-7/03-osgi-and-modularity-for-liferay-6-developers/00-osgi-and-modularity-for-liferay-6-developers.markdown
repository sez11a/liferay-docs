# OSGi and Modularity for Liferay 6 Developers [](id=osgi-and-modularity-for-liferay-6-developers)

To create a powerful reliable platform for developing modular applications,
Liferay sought out a best-of-breed standards-based frameworks and technologies.
It was imperative to not only meet demands for enterprise digital experiences
but to also offer developers, both experienced with Liferay and new to Liferay,
a clear and elegant way to create apps.

Here were some of the key goals:

- Allow breaking down a large system into smaller pieces of code, whose
boundaries and relationships could be clearly defined.

- Explicitly differentiate public APIs from private APIs.

- Facilitate extensibility of existing code.

- Modernize the development environment, leveraging more state-of-the-art tools
to provide a great developer experience.

It wasn't long before Liferay discovered that OSGi, and its supporting
tools/technologies, fit the the bill!

In this tutorial, you'll learn how Liferay 7 uses OSGi to meet these objectives.
And equally important, you'll find out how easy and fun modular development can
be.

Here are the topics you'll dig into:

1. **Modules as an Improvement over Traditional Plugins**: Development and
customization of applications for Liferay has traditionally been done in plugins
(Portlet, Hook, Ext and Web). In Liferay 7, plugins are replaced with (and can
be automatically converted to) modules. This section compares and contrasts
plugins and modules, and explains the benefits of using modules.

2. **Leveraging Dependencies**: In Liferay 7, developers can declare
dependencies among modules and combine modules to create applications. Since
leveraging dependencies provides huge benefits, it warrants its own section.

3. **OSGi Services and Dependency Injection**: OSGi provides a very powerful
concept called OSGi Services (also known as microservices). Together with the
Declarative Services standard, it provides a clean way to inject dependencies
(similar to Spring DI) in a dynamic environment. It also offers an elegant
extensibility model that Liferay 7 leverages extensively.

4. **Dynamic Deployment**: Module deployment is managed by Liferay 7 (not the
application server). This section demonstrates how to use dynamic deployment to
allow for more dynamicity and speed.

Let's start with learning how modules are better than traditional plugins.
