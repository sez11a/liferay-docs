# From a Monolith to a Modular Architecture

Since Liferay Portal's beginning it's been an amazing application full of all
kinds of capabilities. It's had powerful built-in apps and extensible frameworks
that deploy on all kinds of devices. These apps leverage Portal's powerful
frameworks (e.g., asset and comment frameworks) and in some cases the apps
leverage each other (e.g., the Blogs and Wiki apps leverage the Documents and
Media Library). And undoubtedly you've leveraged Liferay Portal's apps and
frameworks via their APIs. Yes, Liferay Portal has traditionally provided so
many things to so many users and developers--and that's been a very good thing.

But as Liferay Portal's grown, it has become a monolith of sorts. Its repository
code base was huge and it's memory footprint was much bigger than what we'd
prefer. Considering all that Portal had been providing and how it's evolved, its
size isn't surprising, but we knew we had to do better for Liferay 7. So before
we started designing Liferay 7, we took a step back to consider how to provide
even better capabilities while making Liferay Portal, and its features and
frameworks, lean, agile, and independent. To start, we took a hard look at key
capabilities to improve.

Liferay Portal's releasability was something we definitely wanted to improve.
Before Liferay 7, you couldn't download releases of new features to *parts* of
Liferay Portal--you had to wait until a new release of the *entire* portal
product. And you couldn't just take the new parts you wanted, you had to take
all of Liferay Portal--the whole monolith. Releasability of Liferay Portal's
core and its components became a top priority.

To improve releasability, we came face to face with how Portal's parts were
interacting. In many cases, they had too much knowledge of each other's
internals (i.e., they were coupled tightly). Tight coupling was occurring way too
easily in the development process, as we hadn't yet found an easy way to facilitate and enforce
loose coupling. So we also prioritized finding a framework to help Liferay
engineers and contributors develop loosely coupled components on Liferay.

One last major goal related to both releasability and loose coupling was finding
the best way to modularize Liferay's core, apps, and frameworks into independent
lightweight components. We wanted to do away with the massive monolith and
refactor it into components that would be easy to maintain and iterate on
separately and frequently. In our research, we drew inspiration from the concept
of microservices, which we'll discuss next. 

## The Microservices Concept

The goal of a microservices is to do one (and only one) thing very
well. Each microservice is small and stripped down of all but what it requires
to meet its purpose. Their independent too. You can independently deploy each
microservice and use it. But in addition to being independent, each microservice
is composable, meaning it can be used with other microservices to do more
complex things. 

![Figure 1: Liferay drew inspiration from the concept of microservices. Their size, self reliance, and composability, make them ideal for a modular architecture.](../../images/microservice.png)

In essence, we've rebuilt Liferay as microservices, to gain the ability to
deploy new versions of the services in a few weeks, or even days, as compared
previously to months. We've encapsulated these microservices in OSGi modules,
which are a standards based deployment structure for deploying to dynamic Java
systems.

All of Liferay 7's modules can be developed and iterated on independently. Each
module can have it's own release cycle. Developers can innovate rapidly over
their modules. As with their modules, they can focus on providing the single
purpose of their module.

We'll go into details of OSGi modules later. For now, take comfort that we've
chipped away at the Liferay 6.2 monolith to bring you Liferay 7 as a robust
system of modules. Next, we'll consider how their delivered to you as a Liferay
consumer. 

## Core, Apps, and Suites

We've taken Liferay Portal the monolith, broken it down to its parts, decoupled
them, and re-engineered them into a much more manageable and agile system. Before
going into details of how we implemented particulars of modularity, let's take a
look at how Liferay 7's modules are arranged.

![Figure 2: Liferay 7 is composed of the Liferay Core and several suites, each with their own set of application and framework modules.](../../images/core-suites-apps.png)

What does all this mean? Has Liferay Portal simply been repackaged? No;
it's been redeveloped into independent robust modules that work together to
bring you a full-fledged portal. And on top of that, we've made the modules
accessible in aggregations that are easier to release. We'll brief you on them
now. 

All of the suites run on Liferay Core. The core bootstraps the system, manages
modules, and registers services. In addition to relying on the core, the suites
rely on the Liferay Foundation suite for the building blocks familiar to Liferay
users and developers, such as roles and permissions, users, groups, and user
groups, sessions, login, and licensing, upgrades, clustering, and DAO, and
front-end mainstays for themes, CSS, taglibs, and JavaScript. The Foundation
suite's modules deploy on Liferay Core, as do all of the non-core modules.

And then there are Liferay 7's other new suites that provide modules for the
apps, frameworks, and APIs you've come to know and love as a Liferay platform
developer.

- **Liferay Web Experience**: Provides modules for apps such as Web Content
Display, Asset Publisher, and Breadcrumbs, and provides powerful features and
frameworks such as Application Display Templates, Tags, and Recycle Bin.
Collectively they help you create, manage, and track content.

- **Liferay Collaboration**: Users join together using the Message Boards, Wiki,
and Blogs apps to share ideas and hash out details. It's Documents and Media
Library continues to be your site's file store. And the suite's social apps and
APIs enable you to track user activities and attribute social equity.

- **Liferay Forms and Workflow**: Boosts productivity with its Calendar, Forms,
and Workflow app modules. Its Dynamic Data Mapping framework helps you bring new
meaning to your structured content.

Now, you could take the core and its suites to be a simple shuffling around of
things. But it's much more than that. For one, the core and each of the suites
have their own release cycles. That means, you can grab the latest Liferay Core
release and not burden yourself with updating any of the other suites. If a new
version of new Liferay Collaboration has a hot new feature or API improvement
you want, you can focus on upgrading to that new version of Liferay
Collaboration. Even at the module aggregation level, you start to see the
freedom.

But wait a minute! How does an app in one suite use an app in another? For
example, the Web Content Display in Web Experience uses the Comments framework
that's in Collaboration. How does that work? Is there tight coupling between
them? To answer these questions, we need to get a deeper understanding of how
Liferay 7 embraces modularity. We'll explain how we've implemented modularity
and compare Liferay 7 modules with Liferay 6 plugins. 
