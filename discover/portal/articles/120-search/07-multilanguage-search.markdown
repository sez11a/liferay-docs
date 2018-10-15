# Searching for Localized Content [](id=searching-for-localized-content)

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

## What is Localized Search? [](id=what-is-localized-search)

In localized search, fields are indexed with a two letter locale appended (for
example, `en` for English, making a localized title field indexed as
`title_en`). It's then passed to the proper 
[language analyzer](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/analysis-lang-analyzer.html) 
in the search engine so that the 
[analysis](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/analysis.html) 
process is performed properly. There are two common approaches.

Fully localized search works like this:

1.  One or more of an asset's fields are localizable in the user interface and
    database (the locale is appended based on the asset creator's language
    selection).

2.  The fields are indexed with the appended locale and analyzed with the
    corresponding language analyzer.

3.  At search time, the user can be sure that content existing in a certain
    language was analyzed properly, and will be properly returned according to
    search engine's relevance algorithms.

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

## Assets Supporting Localized Search [](id=assets-supporting-localized-search)

Whether or not an asset supports localized search depends on how the asset was
indexed in the search engine. At this time, no cohesive pan-asset approach to
indexing assets for localized search exists. Localized search support is
currently limited, and available in the following assets:

Web Content Articles: 

- The `title`, `content`, and `description` fields support fully localized search.

- At search time, any matching results (with any locale appended) can be
    returned.

Categories:

- The `name` and `description` fields support fully localized search.

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

## Examples [](id=examples)

To see fully localized search in action,

1.  Add a Basic Web Content article:

    - Title: _What time is it?_
    - Summary: _It's soccer time!_
    - Content: _That's right, it's time for soccer. The 2018 World Cup recently
        concluded, and teams all over the U.S. are gearing up for Fall Soccer
        season. It's never too early to start practicing._

2.  Add a second article:

    - Title: _What is the best soccer team ever?_
    - Summary: _There are many good teams? Which is the best?_
    - Content: _Here are the 10 best teams in the world: 1. The Lunar Resort's
        Club Team, Waxing Crescent FC..._

3.  Add a Portuguese (_pt-BR_) translation of the article:

    - Title: _Qual time de futebol é o melhor de todos os tempos?_
    - Summary: _Existem muitas boas equipes. Qual é o melhor?_
    - Content: _Aqui estao as 10 melhores equipes do mundo: 1. Selecao
        brasileira de Futebol 2. O time do Resort Lunar, Waxing Crescent FC..._

4.  Find a search bar widget and enter _time_ as the keyword.

    The first article is returned, and so is the Portuguese translation of the
    article about soccer teams (because _time_ in Portuguese translates to the
    English word _team_).

In fully localized search, fields are appended with the proper locale, and even
fields with a locale other than the User's display context are returned if they
contain matches to the searched keyword.

To see an example of site-localized search:

1.  Create a text file named `search-test.txt` with the following contents: 

        Meu time de futebol favorito é o melhor

2. Upload it as a Basic Document to the Documents and Media application.

3.  If your site's language is currently set to English, adding this file will
    append it's content field with the _en_ locale. 

4.  Search in the site for the keyword _time_.

    ![Figure 1: Even though the content of this DM File is written in Portuguese, it was appended with the _en_ locale, so it's searchable in an English language site.](../../images/search-site-localized1.png)

    The file is returned because even though the text in the file is
    Portuguese, the locale appended to its _content_ field is for English.

5.  Now change the Site's default language to _Portuguese (Brazil)_.
    Use Site Settings &rarr; Languages to accomplish this.

6. Now search for _time_ in the site, and the document is not returned in the
   results, because the search is looking for the _pt_ locale.

   ![Figure 2: The uploaded DM File doesn't appear when the site language is changed, because only fields with the site's locale are searched.](../../images/search-site-localized2.png)

7.  Now go to Control Panel &rarr; Configuration &rarr; Search, and click
    *Execute* next to _Reindex all search indexes._

8. Search for _time_ in the site's Search Bar again, and now the document is
   returned in the results, because the content field's locale was changed
   from _en_ to _pt_ when reindexed.

   ![Figure 3: Once the field is reindexed with the site's locale, it can be returned as a search result in the site.](../../images/search-site-localized3.png)

If an asset supports site-localized search, its fields must be reindexed after
the site language is changed in order to be returned as search results.

