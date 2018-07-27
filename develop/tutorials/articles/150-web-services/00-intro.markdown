# Web Services [](id=web-services)

It's important for apps on different machines to communicate. To enable this, an 
app can expose APIs that let remote components access the app's features. The 
remote component could be any other app or device. For example, it could be a 
client app that presents information to a user, a server app that processes data 
in B2B setting, or an IoT device that needs data to do its work. Regardless, 
without exposing web APIs, an app can't communicate with any type of external 
app or device. 

The breadth of apps and features in @product@ necessitates web APIs that let 
developers access those apps and features from external apps and devices. 
@product@ exposes such APIs and lets developers extend them and create new ones. 

There are two different approaches for clients to connect to @product@'s web 
APIs: 

-   **Hypermedia REST APIs:** Services that are designed and built in an 
    opinionated way, and thus decoupled from the internal model. These APIs 
    follow well-known industry standards and are intended to allow evolution of 
    the APIs without breaking clients. This is the modern, preferred way to work 
    with web services in @product@. These services will progressively enable 
    headless operation of @product@. Hypermedia REST APIs are available starting 
    with Liferay DXP 7.1 Fix Pack 1, and Liferay Portal CE 7.1 GA2. 

-   **Plain Web/REST Services:** This is the old way to build and consume web 
    services in @product@, but is still supported. For example, you can use 
    JAX-RS, JAX-WS, or 
    [Service Builder](/develop/tutorials/-/knowledge_base/7-1/service-builder-web-services) 
    to implement plain REST or SOAP web services. 

The tutorials that follow show you how to consume and create web services in 
@product@, beginning with hypermedia REST APIs. 
