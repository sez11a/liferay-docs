# Why Hypermedia REST APIs? [](id=why-hypermedia-rest-apis)

Before explaining how to use and create hypermedia REST APIs, you should first 
understand what *hypermedia* is. In short, 
[hypermedia](https://en.wikipedia.org/wiki/Hypermedia) 
is the ability to represent information and link or refer to related resources. 
This isn't a new concept. When a browser requests a web page it retrieves an 
HTML document that contains the page's contents. Pages typically contain links 
to resources like other web pages and files. The browser knows how to interpret 
such links and renders the page and its content. A web page is therefore a 
hypermedia document that a browser knows how to parse. 

Liferay's hypermedia REST APIs are based on hypermedia. They expose resources in 
such a way that consumers can identify and navigate relationships between 
resources via links. Consumers can also use the links to discover the operations 
available on each resource. Again, this isn't a new concept. 
[Hypermedia as the Engine of Application State (HATEOAS)](https://en.wikipedia.org/wiki/HATEOAS) 
specifies that an API uses hypermedia to provide the information that clients 
need to use the API. 

+$$$

**Note:** Hypermedia REST APIs are available starting with Liferay DXP 7.1 Fix 
Pack 1, and Liferay Portal CE 7.1 GA2. To work with web services in earlier 
releases, you must do so via 
[Service Builder Web Services](/develop/tutorials/-/knowledge_base/7-1/service-builder-web-services), 
JAX-RS, or JAX-WS. 

$$$

## Use Cases for Hypermedia REST APIs [](id=use-cases-for-hypermedia-rest-apis)

Hypermedia REST APIs will progressively offer all the features that @product@ 
has in its built-in user interface. This means that developers can develop 
clients with their tools and programming language of choice, instead of the 
usual way to develop @product@ apps. It also enables headless use of @product@. 
Developers could even create a custom client that lets users access portal 
features entirely via that client. 
<!-- Link to "Going Headless with Hypermedia REST APIs" article, once it exists. -->

@product@'s hypermedia REST APIs can be segmented into two main parts: 

1.  **Management API:** Lets clients perform administrative functions in the 
    portal. However, you should typically use the portal UI to perform such 
    functions. 

2.  **Delivery API:** Lets clients access and manipulate portal content. This is 
    the typical use case for @product@'s hypermedia REST APIs. Here are some 
    specific examples: 

    -   Retrieve portal content (e.g., blog posts, web content, media files, 
        etc.) and present it according to the client app's needs. 
    -   Access 
        [Documents and Media](/discover/portal/-/knowledge_base/7-1/managing-documents-and-media) 
        assets from a remote client, effectively using the portal as a content 
        repository. 
    -   Retrieve and modify portal content metadata, such as asset 
        [tags and categories](/discover/portal/-/knowledge_base/7-1/organizing-content-with-tags-and-categories). 
    -   Access forms created with 
        [Liferay Forms](/discover/portal/-/knowledge_base/7-1/forms). 
    -   And much more! API consumers can discover API features by navigating 
        hypermedia links in the responses. You'll see examples of this in the 
        [Consuming Web Services tutorials](/develop/tutorials/-/knowledge_base/7-1/consuming-web-services). 

## Related Topics [](id=related-topics)

[Consuming Web Services](/develop/tutorials/-/knowledge_base/7-1/consuming-web-services)
