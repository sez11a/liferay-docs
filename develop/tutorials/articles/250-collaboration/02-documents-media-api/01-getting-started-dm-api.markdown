# Getting Started with the Documents and Media API [](id=getting-started-with-the-documents-and-media-api)

Before you start using the Documents and Media API to perform specific tasks, 
you should learn the basic information and techniques you'll need to use the 
API: 

-   [**Key Interfaces:**](#key-interfaces) 
    The interfaces you'll use most while using the API. 
-   [**Getting a Service Reference:**](#getting-a-service-reference) 
    How to get a service reference that lets you call the API's services. 
-   [**Specifying Repositories:**](#specifying-repositories) 
    How to specify the Documents and Media repository that you want to work 
    with. 
-   [**Specifying Folders:**](#specifying-folders) 
    How to specify a Documents and Media folder to work with. 

## Key Interfaces [](id=key-interfaces)

The Documents and Media API contains several key interfaces that you'll use: 

-   **Documents and Media Services:** These interfaces expose all the available 
    Documents and Media functionality: 

    -   [`DLAppLocalService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppLocalService.html): 
        The local service. 
    -   [`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html): 
        The remote service. This service wraps the local service 
        methods in permission checks. 

    Note that Liferay used 
    [Service Builder](/develop/tutorials/-/knowledge_base/7-1/service-builder) 
    to create these services. Because the remote service contains permission 
    checks, it's 
    [best practice](/develop/tutorials/-/knowledge_base/7-1/creating-remote-services#using-service-builder-to-generate-remote-services) 
    to call it instead of the local service. See the section below for 
    instructions on getting a service reference. 

-   **Entity Interfaces:** These interfaces represent entities in the Documents 
    and Media library. Here are the primary ones you'll work with: 

    -   `FileEntry`: Represents a file. 
    -   `Folder`: Represents a folder. 
    -   `FileShortcut`: Represents a shortcut to a file. 

## Getting a Service Reference [](id=getting-a-service-reference)

Before you can do anything with the Documents and Media API, you must get a 
service reference. Use the `@Reference` annotation to 
[get a service reference in an OSGi component via Declarative Services](/develop/tutorials/-/knowledge_base/7-1/osgi-services-and-dependency-injection-with-declarative-services). 
For example, this code gets such a reference to `DLAppService`: 

    @Reference
    private DLAppService _dlAppService;

Getting the reference this way ensures that you leverage OSGi's 
[dependency management](/develop/tutorials/-/knowledge_base/7-1/leveraging-dependencies) 
features. If you need to use the Documents and Media services outside of an OSGi 
component (e.g., in a JSP), then you can use the services' static `*Util` 
classes: 

-   [`DLAppServiceUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppServiceUtil.html)
-   [`DLAppLocalServiceUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppLocalServiceUtil.html)

## Specifying Repositories [](id=specifying-repositories)

Many methods in the Documents and Media API contain a `repositoryId` parameter 
that identifies the Documents and Media repository in which the operation will 
be performed. A site (group) can have multiple repositories, but only one can be 
accessed via the portal UI. This is called the site (group) repository, which is 
effectively a site's default repository. To access this repository via the API, 
provide the group ID as the `repositoryId`. 

You can also get the `repositoryId` via file (`FileEntry`), folder (`Folder`), 
and file shortcut (`FileShortcut`) entities. Each of these entities has a 
`getRepositoryId` method that gets the ID of the repository the entity resides 
in. For example, this code gets the repository ID of the `FileEntry` object 
`fileEntry`: 

    long repositoryId = fileEntry.getRepositoryId();

There may also be cases that require a `Repository` object. You can get one by 
creating a `RepositoryProvider` reference and passing the repository ID to its 
`getRepository` method: 

    @Reference
    private RepositoryProvider repositoryProvider;

    Repository repository = repositoryProvider.getRepository(repositoryId);

Even if you only have an entity ID (e.g., a file ID or folder ID), you can still 
use `RepositoryProvider` to get a `Repository` object. To do so, call the 
`RepositoryProvider` method for the entity type with the entity ID as its 
argument. For example, this code gets a folder's `Repository` by calling the 
`RepositoryProvider` method `getFolderRepository` with the folder's ID: 

    Repository repository = repositoryProvider.getFolderRepository(folderId);

See the 
[`RepositoryProvider` Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/repository/RepositoryProvider.html)
for a list of the methods for other entity types. 

Note that there are ways to create repositories programmatically, including 
repositories private to specific apps. For simplicity, however, the tutorials 
here access the default site repository. 

## Specifying Folders [](id=specifying-folders)

Many API methods require the ID of a folder that they perform operations in or 
on. For example, such methods may contain parameters like `folderId` or 
`parentFolderId`. Also note that you can use the constant 
`DLFolderConstants.DEFAULT_PARENT_FOLDER_ID` to specify the root folder of the 
repository you're working in. 

## Related Topics [](id=related-topics)

[Service Builder](/develop/tutorials/-/knowledge_base/7-1/service-builder)

[OSGi Services and Dependency Injection with Declarative Services](/develop/tutorials/-/knowledge_base/7-1/osgi-services-and-dependency-injection-with-declarative-services)

[Leveraging Dependencies](/develop/tutorials/-/knowledge_base/7-1/leveraging-dependencies)

[Service Trackers](/develop/tutorials/-/knowledge_base/7-1/service-trackers)
