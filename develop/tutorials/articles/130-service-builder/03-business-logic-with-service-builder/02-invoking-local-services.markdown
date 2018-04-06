# Invoking Local Services [](id=invoking-local-services)

Application business logic typically involves operating on the application's
entities (models). In applications whose entities are defined using Service
Builder, local services are the implementation that operates on the models.
Service Builder generates local  service (and remote service) classes as OSGi
Declarative Services (DS) components. The components are accessible to other
components in your application. This tutorial demonstrates accessing and
invoking local services. Here's what's involved:

1. [Access the local service component.](#step-1-access-the-local-service-component) 
2. [Call the service component methods.](step-2-call-the-service-component-methods)

The
[Basic Service Builder Liferay Blade sample project](/develop/reference/-/knowledge_base/7-1/service-builder-samples)
has examples of invoking local services. Start with accessing the local service
component object.

## Step 1: Access the Local Service Component [](id=step-1-access-the-local-service-component)

Service Builder-generated local services are DS components that you can inject
into your app's other DS components
[using the `@Reference` annotation](/develop/tutorials/-/knowledge_base/7-1/osgi-services-and-dependency-injection-with-declarative-services).
The `JSPPortlet` class in the sample project's `basic-web` module injects
`FooLocalService`. 

    @Reference
	private volatile FooLocalService _fooLocalService;

As a convenience, the sample portlet's `getFooLocalService()` method returns the
`FooLocalService` object.   

    public FooLocalService getFooLocalService() {
        return _fooLocalService;
    }

To make the local service object available to the sample application's JSPs, the
portlet's `render` method associates the `FooLocalService` object with a request
attribute. 

    @Override
    public void render(RenderRequest request, RenderResponse response)
        throws IOException, PortletException {

        //set service bean
        request.setAttribute("fooLocalService", getFooLocalService());

        super.render(request, response);
    }

The portlet's `init.jsp` file accesses the local service object. 

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

The `init.jsp` retrieves the `FooLocalService` from the request attribute.  You
can invoke the service component's methods. The next step demonstrates using the
local service object in other JSPs. 

## Step 2: Call the Service Component Methods [](id=step-2-call-the-service-component-methods)

Now that you have the service component object, you can invoke its methods as
you would any Java object's methods. 

The `basic-web` sample module's `view.jsp` and `edit_foo.jsp` files include
`init.jsp` and, therefore, have access to the `fooLocalService` variable,
previously assigned the local service object. The `view.jsp` file uses the local
service in a Liferay Search Container to list `Foo` instances. 

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

You can access your application's Service Builder-generated local service
objects anywhere in the application. Your app's OSGi Declarative Service
component classes can use the `@Reference` annotation to inject themselves with
the local service objects and, as this tutorial demonstrates, you can provide
your JSPs access to the local service objects via request attributes. You can
use your local services anywhere and everywhere you want in your application. 

## Related Topics [](id=related-topics)

[Creating Local Services](/develop/tutorials/-/knowledge_base/7-1/creating-local-services)

[Creating Remote Services](/develop/tutorials/-/knowledge_base/7-1/creating-remote-services)

[Invoking Remote Services](/develop/tutorials/-/knowledge_base/7-1/invoking-remote-services)

[Service Security Layers](/develop/tutorials/-/knowledge_base/7-1/service-security-layers)

[Finding and Invoking Liferay Services](/develop/tutorials/-/knowledge_base/7-1/finding-and-invoking-liferay-services)

[OSGi Services and Dependency Injection with Declarative Services](/develop/tutorials/-/knowledge_base/7-1/osgi-services-and-dependency-injection-with-declarative-services)
