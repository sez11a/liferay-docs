# Searching for Localized Content

@product@ supports setting a virtual instance-wide 
[default language](/discover/portal/-/knowledge_base/7-1/miscellaneous-settings#miscellaneous-display-settings) 
and setting a 
[site-specific language](/discover/portal/-/knowledge_base/7-1/social-settings-and-languages#languages).
In addition, many out of the box assets 
[support translation](/discover/portal/-/knowledge_base/7-1/other-content-options#localizing-content).

How an asset's fields are indexed in the search engine plays an important role
in the search behavior experienced by the end User. Not all assets are indexed
in a way that supports searching in a language other than the default language.
Even assets that are translatable might not support searching for the content in
that language.

## What is Localized Search?

In localized search, fields are indexed with a two letter locale appended (for
example, `en` for English, making a localized title field indexed as
`title_en`). It's then passed to the proper 
[language analyzer](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/analysis-lang-analyzer.html) 
in the search engine so that the 
[analysis](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/analysis.html) 
process is performed properly. There are two common approaches.

Fully localized search works like this:

1. One or more of an asset's fields are localizable in the user interface and
   database (the locale is appended based on the asset creator's language
   selection).

2. The fields are indexed with the appended locale and analyzed with the
   corresponding language analyzer.

3.  At search time, the user can be sure that content existing in a certain
    language was analyzed properly and is their language, it's properly returned
    according to search engine's relevance algorithms.

Site-localized search works like this:

1.  The asset's indexed fields are appended with the locale of the site (set in
    Site Settings) and analyzed with the corresponding language analyzer.

2.  If the site language is changed, reindexing is required to append the proper
    site locale to the indexed fields and analyze with the corresponding
    language analyzer.

2.  At search time, the user can be sure that if content exists matching the
    language of the site, it's properly returned according to the search
    engine's algorithms.

Not all assets support localized search, however.

## Assets Supporting Localized Search

Whether or not an asset supports localized search depends on how the asset was
indexed in the search engine. At this time, no cohesive pan-asset approach to
indexing assets for localized search exists. Localized search support is
currently limited, and available in the following assets:

Web Content Articles: 

- The `title`, `content`, and `description` fields support fully localized search.

- At search time, any matching results (with any locale appended) can be
    returned.

Document Library File Entries:

- The `content` field (which contains the content of an uploaded file) supports
    site-localized search.

- No other fields are indexed with a locale. This means they're always analyzed
    using the default language analyzer.

DDM Fields:

- DDM Fields include all form fields created in the Forms application and all
    fields created in Dynamic Data List Data Definitions and Web Content
    Structures. 

- DDM Fields support fully localized search, with the exception that results can
    only be returned in the current display locale where the search is taking
    place.

