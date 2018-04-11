# Creating Local Services [](id=creating-local-services)

The heart of your service is its `*LocalServiceImpl` class. This class is your
entity's local service extension point. Local services can be invoked within
your application or by other Liferay applications running on the same Liferay
instance as your application. Remote services differ from local services in that
remote services can be invoked from any application that can access your Liferay
instance (e.g., over the Internet) and has permission to do so. All of your
application's core business logic for working with your entity model (or models)
should be added as methods of your `*LocalServiceImpl` class. Before adding any
custom service methods, however, you should review the
[initial service classes](/develop/tutorials/-/knowledge_base/7-1/running-service-builder-and-understanding-the-generated-code#understanding-the-code-generated-by-service-builder)
that Service Builder generated during its initial run. 

<!--
This best practice also appears in creating-remote-services.markdown. If you
edit it, please update that location, as well.
-->

**Best Practice:** If your application needs both local and remote services, follow these steps:

1. Determine the service methods that your application needs for working with 
your entity model and add these service methods to your `*LocalServiceImpl`. 
2. Create corresponding remote services methods in your `*ServiceImpl`.
3. Add permission checks to the remote service methods and make the remote 
service methods invoke the local service methods. The remote service methods can
have the same names as the local service methods that they call.
4. Within your application, only call the remote services. This ensures that 
your service methods are secured and that you don't have to duplicate
permissions code.

Note that Service Builder creates a `*LocalService` class which is the interface
for the local service. It contains the signatures of every method in
`*LocalServiceBaseImpl` and `*LocalServiceImpl`. `*LocalServiceBaseImpl`
contains some automatically generated methods that provide functionality that's
common to all local services. Since the `*LocalService` class is generated,
never modify it. If you do, your changes will be overwritten the next time you
run Service Builder. Place all custom code in your `*LocalServiceImpl`, where it
will not be overwritten.

For example, the Bookmarks application's
[BookmarksEntryLocalServiceImpl](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/bookmarks/bookmarks-service/src/main/java/com/liferay/bookmarks/service/impl/BookmarksEntryLocalServiceImpl.java)
class demonstrates the kinds of service methods that applications commonly need
for working with an entity model. Click on the class's link to view some of its
local service methods.

Adding an entity to the database requires an ID for the entity. Liferay provides
a counter service which obtains a unique ID for each new entity. It's possible
to use the `increment` method of Liferay's
[`CounterLocalService` class](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/counter/kernel/service/CounterLocalService.html),
but Service Builder already makes a `CounterLocalService` instance available to
your app's `*LocalServiceBaseImpl`. The `CounterLocalService` instance is
injected into a module as an OSGi service:

    @ServiceReference(type=com.liferay.counter.kernel.service.CounterLocalService.class)
    protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;

If you're creating local services in Liferay's core, the `CounterLocalService`
instance is injected as a Spring bean:

    @BeanReference(type=com.liferay.counter.kernel.service.CounterLocalService.class)
    protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;

Since your `*LocalServiceImpl` class extends `*LocalServiceBaseImpl`, you can
access this `CounterLocalService` instance. See your app's
`*LocalServiceBaseImpl` for a list of all the Spring beans/OSGi services you
have available for use.

You can use either the injected class's `increment` method or you can call
Liferay's `CounterLocalService`'s `increment` method directly. For example, a
bookmarks entry is assigned a unique ID like this:

    long entryId = counterLocalService.increment();

The Bookmarks application uses the generated ID as the ID for the new
`BookmarksEntry`:

    BookmarksEntry entry = bookmarksEntryPersistence.create(entryId);

`bookmarksEntryPersistence` is one of the OSGi services Service Builder injects 
into `EventLocalServiceBaseImpl`. 

Next, the Bookmarks application sets the attribute fields that were specified
for the BookmarksEntry entity in the `service.xml`. These attributes include the
`groupId`, `userId`, `name`, `url`, `serviceContext`, etc. Lastly, a Bookmarks
folder ID must be associated to the entry.

It's also important to assign values to the audit fields. In the Bookmarks
application, the group of the entity is set first. An entity's group determines
its scope. In this example, the group is the site. The company and user are
specified after the group is set. The company represents the portal instance and
the user is the user who created the bookmark. The Bookmarks application sets
the `createDate` and `modifiedDate` of the Event to the current time. After
that, the generated `addEntry` method of `BookmarksEntryLocalServiceBaseImpl` is
called to add the bookmark to the database. Lastly, the bookmark is added as a
resource so that permissions can be applied to it later. To view the `addEntry`
method in its entirety, see the
[BookmarksEntryLocalServiceImpl](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/bookmarks/bookmarks-service/src/main/java/com/liferay/bookmarks/service/impl/BookmarksEntryLocalServiceImpl.java)
class. For more information about adding resources, please see the
[Assets: Integrating with Liferay's Framework](/develop/tutorials/-/knowledge_base/7-1/assets-integrating-with-liferays-framework)
learning path. 

The Bookmarks application creates local services for `BookmarksFolder` entities
and `BookmarksEntry` entities. Look at the custom service methods available in
the
[BookmarksFolderLocalServiceImpl](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/bookmarks/bookmarks-service/src/main/java/com/liferay/bookmarks/service/impl/BookmarksFolderLocalServiceImpl.java)
class for a better understanding of services available for bookmark folders.

Before you can use any custom methods that you added to your `*LocalServiceImpl`
class, you must
[run Service Builder](/develop/tutorials/-/knowledge_base/7-1/running-service-builder-and-understanding-the-generated-code)
again. Running Service Builder again adds the method signatures of your custom
service methods to your `*LocalService` interface. 

Service Builder looks through your `*LocalServiceImpl` class and automatically
copies the signatures of each method into the corresponding `*LocalService`
interface. After running Service Builder, you can test that your services are
working as intended by
[invoking one of the methods](/develop/tutorials/-/knowledge_base/7-1/invoking-local-services)
that Service Builder added to your `*LocalService` class. For example, if you
were developing the Bookmarks application, you could make the following service
invocation to make sure that your service works as intended:

    BookmarksEntryLocalService.addBookmarksEntry(bookmarksEntry);

In addition to all of the Java classes and interfaces, Service Builder also
generates a
[`service.properties` file](/develop/tutorials/-/knowledge_base/7-1/configuring-service-properties) 
to track the service builds.
[Invoking local services](/develop/tutorials/-/knowledge_base/7-1/invoking-local-services)
is up next. 

## Related Topics [](id=related-topics)

[Running Service Builder and Understanding the Generated Code](/develop/tutorials/-/knowledge_base/7-1/running-service-builder-and-understanding-the-generated-code)

[Invoking Local Services](/develop/tutorials/-/knowledge_base/7-1/invoking-local-services)

[Creating Remote Services](/develop/tutorials/-/knowledge_base/7-1/creating-remote-services)
