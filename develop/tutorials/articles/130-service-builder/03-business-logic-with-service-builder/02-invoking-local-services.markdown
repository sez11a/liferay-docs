# Invoking Local Services [](id=invoking-local-services)

Application business logic involves operating on the application's entity model.
The application's local services implement those operations. Service Builder
generates local service (and remote service) classes as OSGi Declarative
Services (DS) components. These components are accessible to other DS components
in your application. Here's what's involved:

1. [Reference the local service component.](#step-1-reference-the-local-service-component) 
2. [Call the service component methods.](step-2-call-the-service-component-methods)

The
[Basic Service Builder Liferay Blade sample project](/develop/reference/-/knowledge_base/7-1/service-builder-samples)
has examples of referencing local services in its `Portlet` service component and invoking them in its JSPs. Start with accessing the local service
component object.

## Step 1: Reference the Local Service Component [](id=step-1-reference-the-local-service-component)

Your application's Service Builder-generated local services are DS components that you can inject
into your application's other DS components
[using the `@Reference` annotation](/develop/tutorials/-/knowledge_base/7-1/osgi-services-and-dependency-injection-with-declarative-services).
The `JSPPortlet` class in the sample project's `basic-web` module injects
`FooLocalService`. 

    @Reference
	private volatile FooLocalService _fooLocalService;

The sample declares the `_fooLocalService` field to be volatile--making a field
volatile is completely optional. 

+$$$

**Note**: Service Builder generates `*LocalServiceImpl`, `*ServiceImpl`, and 
`[ENTITY]Impl` classes for your entities as Service Builder Spring Beans--not
OSGi Declarative Services.
[Service Builder Spring Beans must use means other than the `@Reference` annotation to reference Liferay services and OSGi services](/develop/reference/-/knowledge_base/7-1/invoking-services-from-service builder-code).

$$$

**Important:** You should never invoke `*LocalServiceImpl` objects directly. You
should only invoke them indirectly through their `*LocalService` service interface. The OSGi service registry wires the service implementation object to your component. 

As a convenience, the sample portlet's `getFooLocalService()` method returns the
`FooLocalService` object.   

    public FooLocalService getFooLocalService() {
        return _fooLocalService;
    }

To make the local service object available to the sample application's JSPs, the
portlet's `render` method associates the `FooLocalService` object with a request
attribute `"fooLocalService"`. 

    @Override
    public void render(RenderRequest request, RenderResponse response)
        throws IOException, PortletException {

        //set service bean
        request.setAttribute("fooLocalService", getFooLocalService());

        super.render(request, response);
    }

The portlet's `init.jsp` file retrieves the `FooLocalService` object from the
request attribute. 

    ...
    <<%@
    page import="com.liferay.blade.samples.servicebuilder.service.FooLocalService" %>
    ...

    <liferay-theme:defineObjects />

    <portlet:defineObjects />

    <%
    ...

    //get service bean
    FooLocalService fooLocalService = (FooLocalService)request.getAttribute("fooLocalService");
    %>

You
can invoke the service component's methods. The next step demonstrates using the
local service object in JSPs. 

## Step 2: Call the Service Component Methods [](id=step-2-call-the-service-component-methods)

Now that you have the service component object, you can invoke its methods as
you would any Java object's methods. 

The `basic-web` sample module's `view.jsp` and `edit_foo.jsp` files include
`init.jsp` and, therefore, have access to the `fooLocalService` variable,
previously assigned the local service object. The `view.jsp` file uses the local
service's `getFoosCount` method and `getFoos` method in a Liferay Search
Container to list `Foo` instances. 

    <liferay-ui:search-container
    	total="<%= fooLocalService.getFoosCount() %>"
    >
    	<liferay-ui:search-container-results
    		results="<%= fooLocalService.getFoos(searchContainer.getStart(), searchContainer.getEnd()) %>"
    	/>
        ...
    </liferay-ui:search-container>

The `edit_foo.jsp` file calls `getFoo(long id)` to retrieve `Foo` entities based
IDs.  

    long fooId = ParamUtil.getLong(request, "fooId");
    Foo foo = null;
    if (fooId > 0) {
    	foo = fooLocalService.getFoo(fooId);
    }

Using the `@Reference` annotation, you can inject your application's OSGi DS
components with instances of your application's Service Builder-generated local
services. Also you can provide your JSPs access to the local service instances
via request attributes. 

## Related Topics [](id=related-topics)

[Creating Local Services](/develop/tutorials/-/knowledge_base/7-1/creating-local-services)

[Creating Remote Services](/develop/tutorials/-/knowledge_base/7-1/creating-remote-services)

[Invoking Remote Services](/develop/tutorials/-/knowledge_base/7-1/invoking-remote-services)

[Service Security Layers](/develop/tutorials/-/knowledge_base/7-1/service-security-layers)

[Invoking Services from Service Builder Code](/develop/reference/-/knowledge_base/7-1/invoking-services-from-service builder-code)

[OSGi Services and Dependency Injection with Declarative Services](/develop/tutorials/-/knowledge_base/7-1/osgi-services-and-dependency-injection-with-declarative-services)
