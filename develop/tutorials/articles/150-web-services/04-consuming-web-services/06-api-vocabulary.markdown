# API Vocabulary [](id=api-vocabulary)

When defining an API, the developer must decide how to expose the representation 
of its resources. This determines how the API can evolve, and its ease of use. 
Traditionally, the developer must choose between two approaches: 

-   **Contract Last:** The code is written first and features are exposed as web 
    or REST services. This approach is typically easier for developers, as they 
    only need to implement and expose the business logic. 
    [Service Builder](/develop/tutorials/-/knowledge_base/7-1/service-builder-web-services) 
    is an example of this. However, this approach also couples the service 
    contract to the business logic and internal model. Any change to the 
    internal model, even attribute renaming, can break the exposed contract and 
    could even expose attribute types that are specific to the technology that 
    implements the services. The contract-last approach can therefore result in 
    [leaky abstractions](https://en.wikipedia.org/wiki/Leaky_abstraction). 

-   **Contract First:** The structure for client-server messages is written 
    before the code that implements the services. Such messages are defined 
    independent of the code. This therefore avoids the coupling of the 
    contract-last approach and leads to a design that supports API evolution 
    without breaking clients (as much as possible). 

Liferay's hypermedia web APIs use the contract-first approach. Therefore, the 
API design effort focuses on defining how client-server messages represent the 
APIs' resources. In other words, the APIs' schemas are defined first and the 
attributes, resources, and operations are named to clearly define what they 
represent and how they should be used. 

<!-- 

Once schema is defined and schema.liferay.org is live, add a new section on the 
schema, and examples.

-->

## Related Topics [](id=related-topics)

[Service Builder Web Services](/develop/tutorials/-/knowledge_base/7-1/service-builder-web-services)

[Hypermedia Formats and Content Negotiation](/develop/tutorials/-/knowledge_base/7-1/hypermedia-formats-and-content-negotiation)
