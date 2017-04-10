# A new extension model: Services and components

Components are the building blocks of a module. Each component is a Java class
that focuses on a specific piece of functionality. Services specify the module's
interfaces and components implement the interfaces. Modules publish the services as contracts in the
Module Framework. The Module Framework puts service consumers in touch with service providers.

Note that these services are OSGi services. They provide functionality based on
the interfaces they define. OSGi services aren't to be confused with web
services and/or services generated using Liferay's Service Builder. A services
is a Java interface specified by a component. 

The Module Framework's Service Registry manages service interactions between
components and modules. A module can request a service from the Service Registry, which in turn adds the
service from a module that provides it. 

Components can define service interfaces and components can implement them. We use [Declarative Service](http://wiki.osgi.org/wiki/Declarative_Services)
annotations to designate components, and define them as service providers and
service consumers. All components use the Declarative Services annotation
`@Component`. Components that provide a services specify those services in the
`@Component` annotation by means of a `service` attribute. For example,
`MyServiceProvider` class below describes itself as implementing the
`SomeService.class` service.

    import org.osgi.service.component.annotations.Component;
    
    import com.liferay.documentation.SomeService;

    @Component(
        service = {SomeService.class}
    )
    public class MyServiceProvider extends SomeService { 
        ...
    }

A class that uses a service is a service consumer. It declares a service bean of
the service class type and specifies an `@Reference` annotation for the bean's
setter method. 

    import org.osgi.service.component.annotations.Reference;

    import com.liferay.documentation.SomeService;

    public class MyServiceConsumer {

        private SomeService _someService;

        public Greeting getGreeting() {
            return _someService;
        }

        @Reference
        public void setGreeting(SomeService someService) {
            _someService = someService;
        }

        // Add methods that use the service component ...

    }

The `MyServiceConsumer` class above declares a bean for the class `SomeService`.
The `@Reference` annotation of the bean's setter method informs the Module
Framework to find a provider for that service and instantiate the bean with it. 

In summary, the service provider uses a `service` attribute in its `@Component`
annotation to specify the service it provides, the service consumer marks itself
with an `@Reference` annotation for the bean it needs set, and the Module
Framework connects both parties. Simple enough, right?

Services and components are the key ingredients to modularity in Liferay 7.
We'll go into more details and examples of components and services throughout
this Learning Path. Note, you can also refer to tutorial
[Creating Liferay Components](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-components).
