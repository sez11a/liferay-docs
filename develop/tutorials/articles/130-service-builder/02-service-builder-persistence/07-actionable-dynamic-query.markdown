## Actionable Dynamic Queries [](id=actionable-dynamic-queries)

Suppose you have over a million users on your portal, and you want to perform
some kind of mass update to a large portion of them. One approach might be
to use a dynamic query to retrieve the list of users in question. Once loaded
into memory, you could loop through the list and update each user. However,
with over a million users, the memory cost of such an operation would be too
great. In general, if you have very large numbers of service builder entities,
it can be too expensive in terms of memory and speed to run a dynamic query to
retrieve a list of such entities in order to do some processing on them. 

Liferay provides actionable dynamic queries to solve this kind of situation.
An actionable dynamic query does not return a list of service builder entities
like a regular dynamic query. Instead, it uses a pagination strategy to
load only small numbers of service builder entities into memory at a time and
performs some processing (i.e., performs an *action*) on each entity. So
instead of trying to use a dynamic query to load a million users into memory
and then perform some processing on each of them, a much better strategy is to
use an actionable dynamic query to process them. This way, only small numbers of
users are loaded into memory at a time, but you still process all the users.

When you run Service Builder, it includes actionable dynamic query support in
the generated API and service modules. For example, consider the API module of
the BLADE service builder example project. 

The `FooLocalService` interface in the API module contains these methods:

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

The `FooLocalServiceBaseImpl` class in the service module implements each of
these methods. See
[here](https://github.com/liferay/liferay-blade-samples/blob/master/gradle/apps/service-builder/basic/basic-api/src/main/java/com/liferay/blade/samples/servicebuilder/service/FooLocalService.java#L156-L166)
and
[here](https://github.com/liferay/liferay-blade-samples/blob/master/gradle/apps/service-builder/basic/basic-service/src/main/java/com/liferay/blade/samples/servicebuilder/service/base/FooLocalServiceBaseImpl.java#L247-L330)
for details.

The implementation of `FooLocalService.dynamicQuery()` uses
`DynamicQueryFactoryUtil` to obtain new (regular) dynamic query instance for
the `Foo` entity. This is the pattern shown earlier.

You can use `FooLocalService.getActionableDynamicQuery()` to obtain a new
actionable dynamic query instance for the `Foo` entity. Once you have the
instance, you can use chaining to build up the query using any of the techniques
described above for regular dynamic queries, such as restrictions or
projections. But the point of using an *actionable* dynamic query is to specify
an action to perform on each entity that results from the query. To specify such an
action, use the
`ActionableDynamicQuery.setPerformActionMethod<PerformActionMethod<?>` method.
Once the query has been defined and an action has been specified, use the
`ActionableDynamicQuery.performActions` to perform the action on each entity
that results from the query.

Here's an example from a test for @product@'s Bookmarks application:

	ActionableDynamicQuery actionableDynamicQuery = BookmarksEntryLocalServiceUtil.getActionableDynamicQuery();

	actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<BookmarksEntry>() {
			@Override
			public void performAction(BookmarksEntry bookmarksEntry) {
				Assert.assertNotNull(bookmarksEntry);

				count.increment();
			}
		});

	actionableDynamicQuery.performActions();

You can see the full context [here](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/bookmarks/bookmarks-test/src/testIntegration/java/com/liferay/bookmarks/service/persistence/test/BookmarksEntryPersistenceTest.java#L483-L501).

Consider the `FooLocalService` from the BLADE service builder API project
again. For most of your actionable dynamic query use cases, the actionable
query returned by `FooLocalService.getActionableDynamicQuery` will suffice.
This actionable dynamic query is an instance of the concrete class
`DefaultActionableDynamicQuery`. However, in addition to
`FooLocalService.getActionableDynamicQuery`, there are two additional methods
related to actionable dynamic queries:
`FooLocalService.getExportActionableDynamicQuery` and
`FooLocalService.getIndexableActionableDynamicQuery`. These methods return
instances of concrete classes (either `IndexableActionableDynamicQuery` or
`ExportActionableDynamicQuery`) that extend `DefaultActionableDynamicQuery`.
`IndexableActionableDynamicQuery` contains methods designed to facilitate
processing that involves search indexing and `ExportActionableDynamicQuery`
contains methods designed to facilitate processing that involves export /
import functionality.

To see examples of configuring indexer and export actionable dynamic queries,
see see the Bookmarks application
[here](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/bookmarks/bookmarks-service/src/main/java/com/liferay/bookmarks/service/base/BookmarksEntryLocalServiceBaseImpl.java#L285-L296)
and
[here](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/bookmarks/bookmarks-service/src/main/java/com/liferay/bookmarks/service/base/BookmarksEntryLocalServiceBaseImpl.java#L307-L405).
To see an example invocation of an indexable actionable dynamic query, see the
`reindexEntries` method of the Bookmarks application's indexer
[here](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/bookmarks/bookmarks-service/src/main/java/com/liferay/bookmarks/search/BookmarksEntryIndexer.java#L155-L210).

## Related Topics [](id=related-topics)

[Service Builder Web Services](/develop/tutorials/-/knowledge_base/7-0/service-builder-web-services)

[Creating Local Service](/develop/tutorials/-/knowledge_base/7-0/creating-local-services)

[Invoking Local Services](/develop/tutorials/-/knowledge_base/7-0/invoking-local-services)
