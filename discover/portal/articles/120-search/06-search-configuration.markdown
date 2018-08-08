# Configuring Search

_Configuring Search_ could mean lots of different things: 

- System scoped search configuration
- Reindexing to make sure the search indexes are current with the database
- Tweaking the search widgets added to pages
- Creating new search pages
- Configuring the connectors that let @product@ and the search engine
  communicate

In fact, _Configuring Search_ means all those things. This is a high level
overview of what search behavior is configurable out of the box, and
importantly, _where_ to find search configuration options.

## System Scoped Search Configuration

System scoped search configurations are primarily found in 
[System Settings](/discover/portal/-/knowledge_base/7-1/system-settings).

1.  Go to *Control Panel* &rarr; *Configuration* &rarr; *System Settings*.

2.  Click the *Search* category under the Platform section.

    Alternatively, search for *Search*.

![Figure x: There are numerous system scoped entries for search in System Settings.](../../images/search-category-system-settings.png)

These system scoped configurations are available in System Settings:

**Default Keyword Query**
: `DefaultKeywordQueryContributor` automatically adds `description`, `userName`,
and `title` fields to the keyword search query. Specify the entry class names
`DefaultKeywordQueryContributor` should ignore.

**Default Search Result Permission Filter**
: Configure *post-filtering permission checking* (database permission checking
that occurs after the results are returned from the search index). Read 
[here](/discover/portal/-/knowledge_base/7-1/search-results-behavior#final-permissions-checking) 
for more information.

**Index Status Manager**
: Enable *Index Read Only* to suspend all indexing operations and writes to the
search engine. Searches return only the documents already indexed. This is
useful for speeding up large data imports, but it should be disabled and a full
re-index executed once the import is finished.

**Indexer Writer Helper**
: When Index Commit Immediately is *true* (the default), each write request
forces the search engine to refresh the index reader, potentially flushing
transactions to disk. This may negatively impact search engine performance. The
default behavior is to commit immediately for index writing on individual assets
(e.g. add blog, update blog) but delay commits for bulk index writing
operations (e.g.  index all users, index all form entries) until all entries
have been sent to the search engine. Setting this to false changes the behavior
for individual index operations, and may cause applications like Asset Publisher
to exhibit a delayed response when showing newly added content. See
https://www.elastic.co/guide/en/elasticsearch/guide/current/near-real-time.html
for more information.

**Index Registry**
: Disable or configure the buffering of indexing requests. To stop the buffering
of index requests, set the Buffered property to *Disabled*. If buffering is
enabled, set the Maximum Buffer Size so that additional indexing requests are
executed immediately. Minimum Buffer Availability Percentage sets a different
threshold: when the capacity of the buffer has only a certain percent of space
left, the existing requests in the buffer are executed in one batch and removed
from the buffer.

**Index Query Preprocessor**
: Fields with names matching the patterns set here are treated as non-analyzed
keyword fields. Instead of scored full text queries, matching is performed by
non-scored wildcard queries. This is a resource intensive operation that
degrades search engine performance as indexes grow larger. For substring
matching, relying on the
[NGram Tokenizer](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/analysis-ngram-tokenizer.html) 
usually performs better.

**Reindex**
: Use the Indexing Batch Sizes property to set the number of documents indexed
per batch for model types that support batch indexing. Defaults to 10000. For
models with large documents, decreasing this value may improve stability when
executing a full re-index.

**Engine Helper**
: Specify Excluded Entry Class Names to exclude an asset type from being
searched in the catchall query constructed for the Search application. For
example, fields of the Organization asset must be indexed to be searchable from
the Users and Organizations application, but should not be searched in the
Search application. Thus, Organizations are added to `excludedEntryClassNames`.

**Permission Checker**
: Configure *pre-filtering permission checking* (permission checking on the
search index) behavior. See 
[here](/discover/portal/-/knowledge_base/7-1/search-results-behavior#initial-permissions-checking) 
for more information.

**Elasticsearch 6**
: Configure the connection between @product@ and Elasticsearch 6. See
[here](/discover/deployment/-/knowledge_base/7-1/configuring-the-liferay-elasticsearch-connector) 
for more information.

**Search Web**
: Revert the default search experience from using the new Search Widgets to the
classic Search Portlet that was standard in past releases. See 
[here](/discover/portal/-/knowledge_base/7-1/configuring-search-pages#legacy-search-experience)
for more information.

In addition to the System Settings for Search, the action of recreating the
search indexes is a system scoped action. 

1.  Go to *Control Panel* &rarr; *Configuration* &rarr; *Search*.

2.  Re-index one of these:

    - All indexable assets
    - An individual indexable asset
    - All spell check indexes

Portal properties are system scoped configurations as well. The 
[Lucene Search](https://docs.liferay.com/portal/7.1-latest/propertiesdoc/portal.properties.html#Lucene%20Search)
portal properties configure low level search behavior. Review the properties and
their descriptions and determine if they apply to your search requirements.

## Site Scoped Search Configuration

Search isn't configurable at the Site Scope by the strict definition of 
[Site Scoped Configuration](/discover/portal/-/knowledge_base/7-1/setting-up#configuratino-scope).
However, the use of 
[Search Pages](/discover/portal/-/knowledge_base/7-1/configuring-search-pages) 
is a way to influence Site-specific search behavior. Search pages contain the
search widgets used to search for content within a particular Site.

Because of the modularity of Search, there are some important configuration
nuances to be aware of. 

1.  The classic Search application is not instanceable. Only one can be added to
    any page.

2.  The new Search widgets, (for example, the Search Bar) are instanceable. Add
    as many to a page as you need, and configure them however you'd like.

3.  Configure the Search bar that's embedded in every site page by default
    (including the default Search page) just by configuring one of these Search
    Bar's. The configuration of this Search Bar overrides the Search Page's
    Search Bar, if there's a conflict. However, Search Bar widgets manually
    placed on a page can be configured differently.

Note that 
[configuration of a Search Bar](/discover/portal/-/knowledge_base/7-1/searching-for-assets#configuring-the-search-bar)
widget added to a page or configuration of the classic Search Application,
(still available for adding to a page in @product-ver@) is page-scoped
configuration.

## Widget Scoped Search Configuration

Several search widgets are available, and each one has its own configuration
options:

**Search Results**
:  Configure how search results are displayed. Read 
[here](/discover/portal/-/knowledge_base/7-1/search-results) for more
information.

**Search Bar**
: Configure the behavior of how search keywords are processed. See
[here](/discover/portal/-/knowledge_base/7-1/searching-for-assets#configuring-the-search-bar) 
for more information.

**Search Facets**
: Configure each facet's behavior and URL parameters. See
[here](/discover/portal/-/knowledge_base/7-1/facets) for more information.

**Search Options**
: This is a special case, where configuring this widget defines page scoped
behavior. Add the Search Option widget to a page and define two booleans for
the Search Page: 

- Allow Empty Searches: By default, failure to enter a keyword returns no
  results. Enabling this ensure that _all_ results are returned when no
  keyword is entered in the Search Bar.

- Basic Facet Selection: By default, facet counts are recalculated after each
  facet selection. Enable this to turn off facet recounting.

**Search Suggestions**
: Suggest better queries and spell check queries. See
[here](/discover/portal/-/knowledge_base/7-1/searching-for-assets#search-suggestions) 
for more information.

**Search Insights**
: Add this to the search page to inspect the full query string that's
constructed by the back-end search code when the User enters a keyword. Only
useful for testing and development.

