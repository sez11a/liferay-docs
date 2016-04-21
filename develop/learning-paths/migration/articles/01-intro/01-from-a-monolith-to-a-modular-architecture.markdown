# From a Monolith to a Modular Architecture

For years, Liferay Portal's delivered built-in apps and extensible frameworks
that deploy on various devices. The apps leverage Portal's frameworks (e.g.,
asset and comment frameworks) and in some cases they call on each other (e.g.,
the Blogs and Wiki apps store attachments in the Documents and Media Library).
And undoubtedly you've used Liferay Portal's apps and frameworks via their APIs.
Yes, Liferay Portal's enabled developers to build, customize, and maintain
excellent sites. 

But as Liferay Portal's grown, it's become a monolith of sorts. We wanted to
make Liferay 7 not only better for users but also particularly better for
developers. So we streamlined the codebase, made Portal more tunable, and split
it into independent parts.

These smaller parts are like building blocks. Their size allows us to focus more
easily on adding features and improving quality and performance. They're
compact, composable, and reusable, making them better for you to work with. As a
result of extracting them Liferay's core is also considerably smaller. On
Liferay 7, you'll see how much easier it is to build your own components and
customize Liferay. 

As we developed Liferay 7, we drew inspiration from the concept of
microservices, which we'll discuss next. 

## The Microservices Concept

The goal of a microservices is to do one (and only one) thing very well. Each
microservice is small and stripped down of all but what it requires to meet its
purpose. They're independent too. You can separately deploy each microservice
and use it. But in addition, each microservice is composable, meaning it can be
used with other microservices to do more complex things. 

![Figure 1: Liferay drew inspiration from the concept of microservices. Their size, self reliance, and composability, make them ideal for a modular architecture.](../../images/microservice.png)

In essence, Liferay's been rebuilt as microservices, to provide the ability to
deploy new versions of the services in a few weeks, or even days, as compared
previously to months. These microservices are encapsulated in OSGi modules,
which are standards-based frameworks for deploying to dynamic Java systems.

All of Liferay 7's modules can be developed and iterated on independently. Each
module can have its own release cycle. Developers can innovate rapidly over
their modules. As with their modules, they can focus on providing the single
purpose of their modules. 

We'll go into details of OSGi modules later. Next, let's consider how modules
are delivered to you as a Liferay consumer. 

## Core, Apps, and Suites

Liferay Portal the monolith, has been broken down to its parts and re-engineered
into a much more manageable and agile system. Before going into details of how
its modularity is implemented, look at how Liferay 7's modules are arranged. 

![Figure 2: Liferay 7 is composed of the Liferay Core and several suites, each with their own set of application and framework modules.](../../images/core-suites-apps.png)

What does all this mean? Has Liferay Portal simply been repackaged? No;
it's been redeveloped into independent robust modules that work together to
bring you a full-fledged portal. And on top of that, the modules are
accessible in aggregations that are easier to release. You'll learn about them
now. 

All of the suites run on Liferay Core. The core bootstraps the system, manages
modules, and registers services. In addition to relying on the core, the suites
rely on the Liferay Foundation suite for the building blocks familiar to Liferay
users and developers, such as roles and permissions, users, groups, and user
groups, sessions, login, and licensing, upgrades, clustering, DAO, and front-end
mainstays for themes, CSS, taglibs, and JavaScript. The Foundation suite's
modules deploy on Liferay Core, as do all of the non-core modules.

And more apps, frameworks, and APIs you've come to know and love have been
reorganized into suites. 

**Liferay Web Experience**: Contains apps such as Web Content Display, Asset
Publisher, and Breadcrumbs and features and frameworks such as Application
Display Templates, Tags, and Recycle Bin. 

**Liferay Collaboration**: Comprises the Documents and Media Library, Liferay's
social apps, and collaboration apps such as Message Boards, Wiki, and Blogs. 

**Liferay Forms and Workflow**: Provides the Dynamic Data Mapping framework
and apps such as Calendar, Forms, and Workflow. 

Now, you could take the core and its suites to be a simple shuffling around of
things. But it's much more than that. For one, the core and each of the suites
have their own release cycles. That means, you can grab the latest Liferay Core
release and not burden yourself with updating any of the other suites. Even at
the module aggregation level, you start to see the freedom. 

But wait a minute! How does an app in one suite use an app in another? For
example, the Web Content Display in Web Experience uses the Comments framework
that's in Collaboration. How does that work? Is there tight coupling between
them? To answer these questions, you need a deeper understanding of how Liferay
7 embraces modularity. You'll learn how modularity has been implemented and
compare Liferay 7 modules with Liferay 6 plugins. 
