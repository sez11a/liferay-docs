# Actionable Dynamic Queries [](id=actionable-dynamic-queries)

Suppose your portal has over a million users, and you want to perform
some kind of mass update to a large portion of them. One approach might be
to use a dynamic query to retrieve the list of users in question. Once loaded
into memory, you could loop through the list and update each user. However,
with over a million users, the memory cost of such an operation would be too
great. In general, if you have very large numbers of Service Builder entities,
it can be too expensive in terms of memory and speed to run a dynamic query to
retrieve a list of such entities in order to do some processing on them. 

Liferay provides actionable dynamic queries to solve this kind of situation.
An actionable dynamic query does not return a list of Service Builder entities
like a regular dynamic query. Instead, it uses a pagination strategy to
load only small numbers of Service Builder entities into memory at a time and
performs some processing (i.e., performs an *action*) on each entity. So
instead of trying to use a dynamic query to load a million users into memory
and then perform some processing on each of them, a much better strategy is to
use an actionable dynamic query to process them. This way, only small numbers of
users are loaded into memory at a time, but you still process all the users.

When you run Service Builder, it includes actionable dynamic query support in
the generated API and service modules. For example, consider the
[Basic Liferay Service Builder sample project](/develop/reference/-/knowledge_base/7-0/service-builder-samples). 
The `FooLocalService` interface in the API module declares these methods:

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public ActionableDynamicQuery getActionableDynamicQuery();

    public DynamicQuery dynamicQuery();

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public ExportActionableDynamicQuery getExportActionableDynamicQuery(
        PortletDataContext portletDataContext);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

The service module's `FooLocalServiceBaseImpl` class implements each of these
methods. 

The `FooLocalService.dynamicQuery()` implementation uses
`DynamicQueryFactoryUtil` to obtain a new (regular) dynamic query instance for
the `Foo` entity--this is the pattern shown in the
[Dynamic Query tutorial](/develop/tutorials/-/knowledge_base/7-1/dynamic-query).

    @Override
    public DynamicQuery dynamicQuery() {
        Class<?> clazz = getClass();

        return DynamicQueryFactoryUtil.forClass(Foo.class,
            clazz.getClassLoader());
    }

Use `*LocalService.getActionableDynamicQuery()` to obtain a new
[`ActionableDynamicQuery` instance](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/orm/ActionableDynamicQuery.html)
for the service entity. Once you have the `ActionableDynamicQuery` instance, you
can use chaining to build up the query using any of the techniques described for
[regular dynamic queries](/develop/tutorials/-/knowledge_base/7-1/dynamic-query),
such as restrictions or projections. But the point of using an *actionable*
dynamic query is to specify an action to perform on each entity that results
from the query. To specify such an action, use the
`ActionableDynamicQuery.setPerformActionMethod<PerformActionMethod<?>` method.
Once the query has been defined and an action has been specified, use the
`ActionableDynamicQuery.performActions()` method to perform the action on each
entity that results from the query.

The `reindexEntries` method from the Bookmarks
[`com.liferay.bookmarks.service` module's](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.bookmarks.service/)
`BookmarksEntryIndexer` class demonstrates performing an indexable actionable
dynamic query.

    protected void reindexEntries(
            long companyId, final long groupId, final long folderId)
        throws PortalException {

        final IndexableActionableDynamicQuery indexableActionableDynamicQuery =
            _bookmarksEntryLocalService.getIndexableActionableDynamicQuery();

        indexableActionableDynamicQuery.setAddCriteriaMethod(
            new ActionableDynamicQuery.AddCriteriaMethod() {

                @Override
                public void addCriteria(DynamicQuery dynamicQuery) {
                    Property folderIdProperty = PropertyFactoryUtil.forName(
                        "folderId");

                    dynamicQuery.add(folderIdProperty.eq(folderId));

                    Property statusProperty = PropertyFactoryUtil.forName(
                        "status");

                    Integer[] statuses = {
                        WorkflowConstants.STATUS_APPROVED,
                        WorkflowConstants.STATUS_IN_TRASH
                    };

                    dynamicQuery.add(statusProperty.in(statuses));
                }

            });
        indexableActionableDynamicQuery.setCompanyId(companyId);
        indexableActionableDynamicQuery.setGroupId(groupId);
        indexableActionableDynamicQuery.setPerformActionMethod(
            new ActionableDynamicQuery.PerformActionMethod<BookmarksEntry>() {

                @Override
                public void performAction(BookmarksEntry entry) {
                    try {
                        Document document = getDocument(entry);

                        indexableActionableDynamicQuery.addDocuments(document);
                    }
                    catch (PortalException pe) {
                        if (_log.isWarnEnabled()) {
                            _log.warn(
                                "Unable to index bookmarks entry " +
                                    entry.getEntryId(),
                                pe);
                        }
                    }
                }

            });
        indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

        indexableActionableDynamicQuery.performActions();
    }

The `reindexEntries` method gets an `IndexableActionableDynamicQuery` from its
entity's local service.  

    final IndexableActionableDynamicQuery indexableActionableDynamicQuery =
        _bookmarksEntryLocalService.getIndexableActionableDynamicQuery();

Then it adds `folderId` and `status` properties (approved and in-trash) as
criteria. The method sets the company and group ID for the query and then sets a
perform action method to add the document the bookmarks entry references to a
collection of documents to index. Lastly the search engine is assigned to the
query and the query's action is performed. 

For most actionable dynamic query use cases, the actionable query returned by
`*LocalService.getActionableDynamicQuery` suffices. This actionable dynamic
query is an instance of the
[concrete class
`DefaultActionableDynamicQuery`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/orm/DefaultActionableDynamicQuery.html). 
However, there are two more actionable dynamic queries available.

[*`IndexableActionableDynamicQuery`*
](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/orm/IndexableActionableDynamicQuery.html):
contains methods designed to facilitate processing that involves search
indexing. 

[*`ExportActionableDynamicQuery`*](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/orm/ExportActionableDynamicQuery.html):
contains methods designed to facilitate processing that involves export / import
functionality. 

They're concrete classes that extend `DefaultActionableDynamicQuery`. The
`*LocalService` methods `getExportActionableDynamicQuery` and
`getIndexableActionableDynamicQuery` return instances
`IndexableActionableDynamicQuery` or `ExportActionableDynamicQuery`,
respectively.

All `*LocalServiceBaseImpl` classes, including the
`com.liferay.bookmarks.service` module's `BookmarksEntryLocalServiceBaseImpl`
class, implement several actionable dynamic query methods, including
`getExportActionableDynamicQuery` and `getIndexableActionableDynamicQuery`.
Here's `BookmarksEntryLocalServiceBaseImpl`'s  implementation of
`getIndexableActionableDynamicQuery`: 

    @Override
    public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
        IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

        indexableActionableDynamicQuery.setBaseLocalService(bookmarksEntryLocalService);
        indexableActionableDynamicQuery.setClassLoader(getClassLoader());
        indexableActionableDynamicQuery.setModelClass(BookmarksEntry.class);

        indexableActionableDynamicQuery.setPrimaryKeyPropertyName("entryId");

        return indexableActionableDynamicQuery;
    }

Service Builder geneates the `getIndexableActionableDynamicQuery` method to
create a new `IndexableActionableDynamicQuery` and set the query's local service
instance, class loader, model class type, and primary key name. Service. 

Actionable dynamic queries let you act on large numbers of entities in smaller
groups. Its an efficient and performant way to affect entities. 

**Related Topics**

[Service Builder Web Services](/develop/tutorials/-/knowledge_base/7-0/service-builder-web-services)

[Creating Local Service](/develop/tutorials/-/knowledge_base/7-0/creating-local-services)

[Invoking Local Services](/develop/tutorials/-/knowledge_base/7-0/invoking-local-services)
