# Searching for Translated Content

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

If your site's default language is different than the default virtual instance
language.

These assets currently support searching for content in multi-language settings:

- Web Content Folders and Articles

- Document Library Folders and File Entries

This means that if there's a DL File Entry with Japanese Content, 


- A User uploads a Japanese Language document to a site whose language is set to
    Japanese. They search for a Japanese word found in the title of the
    document. The Japanese analyzer in the search engine is called on to return
    proper results.

- A User creates a Web Content Article and selects the language of the Title,
    Summary, and Content fields. When searched for, the language analyzer
    corresponding to the locale selected by the content creator is used to
    search for the content. 


----------------------------------------------------

Full multi-language search support=

1. The asset's fields are localizable (the locale is
appended based on the asset creator's language selection)
2. The fields are indexed with the appended locale and analyzed with the
   corresponding language analyzer.
3. At search time, the user can be sure that if content exists in their
   language, it's properly returned according to search engine's relevance
   algorithms.

Assets that support full multi-language search
Web Content Articles

Site language search support=

1.  The asset's indexed fields are appended with the locale of the site (set in
    Site Settings) and analyzed with the corresponding language analyzer.
2.  At search time, the user can be sure that if content exists matching the
    language of the site, it's properly returned according to the search
    engine's algorithms.

Assets that support single-site multi-language search
DL File Entries

Other assets are not indexed with a locale and are always analyzed with the
default analyzer. 
