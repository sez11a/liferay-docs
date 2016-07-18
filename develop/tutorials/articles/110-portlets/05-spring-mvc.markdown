# Spring MVC [](id=spring-mvc)

Liferay is an open platform in an ecosystem of open platforms. Just because
Liferay has its own [MVC framework](/develop/tutorials/-/knowledge_base/7-0/liferay-mvc-portlet),
therefore, doesn't mean you have to use it. It is perfectly valid to bring the
tools and experience you have from other development projects over to Liferay.
In fact, we expect you to. Liferay's development platform is standards-based,
making it an ideal choice for applications of almost any type. 

If you're already a wizard with Spring MVC, you can use it instead of Liferay's
`MVCPortlet` with no limitations whatsoever. Since Spring MVC replaces only your
application's web application layer, you can still use [Service
Builder](/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder) 
for your service layer. 

So what does it take to implement a Spring MVC application in Liferay? Start by
considering how to package a Spring MVC application for Liferay 7.

<!--Do you want to pre-configure your Spring MVC portlet project as a WAB, or do you
want to let the WAB generator in Liferay do the work for you? The benefits and
drawbacks of each approach are covered here.

+$$$

**Note:** If you're wondering what in the world a WAB is, it's a Web Application
Bundle. If that doesn't clarify things, think of it as a traditional WAR-style
plugin that also contains a `META-INF/MANIFEST.MF` file with the
`Bundle-SymbolicName` OSGi header specified. The WAB can run as an OSGi module
because of the Liferay 
[WAB Extender](https://github.com/liferay/liferay-portal/tree/master/modules/apps/foundation/portal-osgi-web/portal-osgi-web-wab-extender). 

$$$
-->

## Packaging a Spring MVC Portlet [](id=packaging-a-spring-mvc-portlet)

Developers creating portlets for Liferay 7.0 can usually deploy their portlet as
Java EE-style Web Application ARchive (WAR) artifacts or as Java ARchive (JAR)
OSGi bundle artifacts. Spring MVC portlet developers don't have that
flexibility. Spring MVC portlets must be packaged as WAR artifacts because the
Spring MVC framework is designed for Java EE. Therefore, it expects a WAR layout
and requires Java EE resources such as the `WEB-INF/web.xml` descriptor. 

Because Liferay supports the OSGi WAB standard for deployment, you can deploy
your WAR and it will run as expected in the OSGi runtime. Here are the high
points on why that works in Liferay 7:

-  The Liferay auto-deploy process runs, adding the `PortletServlet` and
`PlugincontextListener` configurations to the `WEB-INF/web.xml` file.

-  The [Liferay WAB Generator](https://github.com/liferay/liferay-portal/tree/master/modules/apps/foundation/portal-osgi-web/portal-osgi-web-wab-generator) automatically creates an OSGi-ready
`META-INF/MANIFEST.MF` file. If you want to affect the content of the manifest
file, you can place BND directives and OSGi headers directly into the
`WEB-INF/liferay-plugin-package.properties` file.

<!-- Removing mention of pre-configured WAB as per comments by Ray.

There are a couple of
ways to make your source code into a WAB:

1.  Use Liferay's WAB Generator to convert your WAR into a WAB at
    deployment time.

    The benefits of this approach:

    - Processed by the Liferay auto-deploy process, which adds
          `PortletServlet` and `PluginContextListener` to the `WEB-INF/web.xml`
          descriptor.
    - Processed by the Liferay [WAB Generator](https://github.com/liferay/liferay-portal/tree/master/modules/apps/foundation/portal-osgi-web/portal-osgi-web-wab-generator),
          which automatically creates an OSGi-ready `META-INF/MANIFEST.MF`.
    - Can affect the content of `META-INF/MANIFEST.MF` by putting BND directives and
          OSGi headers into the `WEB-INF/liferay-plugin-package.properties` file.

    The drawback:

    - Can't supply the `bnd.bnd` and can't
      use a build-time plugin such as the
[bnd-maven-plugin](http://njbartlett.name/2015/03/27/announcing-bnd-maven-plugin.html)
for generating the manifest file.

2.  Pre-configure a WAB by providing the `MANIFEST.MF` file with the 
    `Bundle-SymbolicName` OSGi header. This is accomplished by using a `bnd.bnd`
    file in the root of your project, which specifies OSGi headers that will go in
    the manifest.

    This approach has a benefit over the WAB Generator approach:

    - The `bnd.bnd` file can be processed by a build-time plugin (e.g.,
      [bnd-maven-plugin](http://njbartlett.name/2015/03/27/announcing-bnd-maven-plugin.html)) to affect the content of an OSGi-ready `META-INF/MANIFEST.MF`.

    There's also a drawback:

    - It bypasses the Liferay auto-deploy process, which means developers must
      have the `WEB-INF/web.xml` descriptor fully ready for deployment. As a
      Java EE developer, though, you should be comfortable with this. 

-->

You'll still need to provide the Liferay descriptor files `liferay-display.xml`
and `liferay-portlet.xml`, and you'll need a `portlet.xml` descriptor.

<!--Of the two approaches above, it's recommended to -->Develop a Spring MVC portlet
WAR file with the appropriate descriptor files, and let the auto-deploy process
and Liferay's WAB generator take care of converting your project to a
Liferay-ready WAB. <!--This is what each tool gets you:

-  The auto-deploy feature in Liferay will automatically configure the required
   Portlet Servlet and `PluginContextListener` in your project's `web.xml`:

        <listener>
            <listener-class>com.liferay.portal.kernel.servlet.PluginContextListener</listener-class>
        </listener>
        <servlet>
            <servlet-name>Portlet Servlet</servlet-name>
            <servlet-class>com.liferay.portal.kernel.servlet.PortletServlet</servlet-class>
            <load-on-startup>1</load-on-startup>
        </servlet>
        <servlet-mapping>
            <servlet-name>Portlet Servlet</servlet-name>
            <url-pattern>/portlet-servlet/*</url-pattern>
        </servlet-mapping>

-  The WAB generator adds the necessary OSGi headers in the required
   `MANIFEST.MF` file. You can directly configure OSGi headers in your project's
`plugin-package.properties` file.
-->

Now get into the details of configuring a Spring MVC portlet for Liferay.

## Spring MVC Portlets in Liferay [](id=spring-mvc-portlets-in-liferay)

This isn't a comprehensive guide to configuring a Spring MVC portlet. It covers
the high points, assuming you already have familiarity with Spring MVC. If you
don't, you should consider using Liferay's MVC framework. 

What does a Liferay Spring MVC portlet look like? Almost identical to any other
Spring MVC portlet. To configure a Spring MVC portlet, start with the
`<portlet-class>` element in `portlet.xml`. In it you must declare Spring's
`DispatcherPortlet`:

    <portlet-class>org.springframework.web.portlet.DispatcherPortlet</portlet-class>

The Spring front controller needs to know where the application context file is,
so specify it as an initialization parameter in `portlet.xml` (update the path
as needed):

    <init-param>
        <name>contextConfigLocation</name>
        <value>/WEB-INF/spring/portlet-context.xml</value>
    </init-param>

Provide an application context file (`portlet-context.xml` in the example
above), specified as you normally would for your Spring MVC portlet.

    <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/views/" />
        <property name="suffix" value=".jsp" />
    </bean>

If you're configuring a WAB yourself, the `web.xml` file in your Spring MVC
project needs to be fully ready for deployment. In addition to any `web.xml`
configuration for Spring MVC, you need to include a listener for
`PluginContextListener` and a `servlet` and `servlet-mapping` for
`PortletServlet`:

    <listener>
        <listener-class>com.liferay.portal.kernel.servlet.PluginContextListener</listener-class>
    </listener>
    <servlet>
        <servlet-name>Portlet Servlet</servlet-name>
        <servlet-class>com.liferay.portal.kernel.servlet.PortletServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>Portlet Servlet</servlet-name>
        <url-pattern>/portlet-servlet/*</url-pattern>
    </servlet-mapping>

If you're letting Liferay generate the WAB for you (this is the recommended
approach), the above is not necessary, as it is added automatically during
auto-deployment.

Your application must be able to convert `PortletRequest`s to `ServletRequest`s
and back again. Add this to `web.xml`:

    <servlet>
        <servlet-name>ViewRendererServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.ViewRendererServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>ViewRendererServlet</servlet-name>
        <url-pattern>/WEB-INF/servlet/view</url-pattern>
    </servlet-mapping>

To configure the Spring view resolver, add a bean in your application context
file:

    <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/views/" />
        <property name="suffix" value=".jsp" />
    </bean>

Now the front controller, `DispatcherPortlet`, can get a request from the view
layer, but there are no actual controller classes to delegate the request
handling to.

With Spring MVC, your controller is conveniently separated into classes
that handle the portlet modes (View, Edit, Help).

You'll use Spring's annotations to configure the controller and tell
`DispatcherPortlet` which mode the controller supports. 

A simple controller class supporting View mode might look like this:

    @Controller("myAppController")
    @RequestMapping("VIEW")
    public class MyAppController {

        @RenderMapping
        public String processRenderRequest(RenderRequest request,
                RenderResponse response) {

            return "defaultView";
        }
    }

The `return defaultView` statement should be understood in terms of the view
resolver bean in the application context file, which gives the String
`defaultView` a prefix of `WEB-INF/views/`, and a suffix of `.jsp`. That maps to
the path `WEB-INF/views/defaultView.jsp`, where you would place your default
view for the application. 

With Spring MVC, you can only support one portlet phase in each controller. 

An edit mode controller might contain render methods and action methods.

    @Controller("myAppEditController")
    @RequestMapping("EDIT")
    public class MyAppEditController {

        @RenderMapping
        public String processRenderRequest(RenderRequest request,
                RenderResponse response) {

            return "thisView";
        }

        @ActionMapping(params="action=doSomething")
        public void doSomething(Actionrequest request, ActionResponse response){
        
            // Do something here

        }
    }

You need to define any controller classes in your application context file by
adding a `<bean>` tag for each one:

    <bean class="com.liferay.docs.springmvc.portlet.MyAppController" />
    <bean class="com.liferay.docs.springmvc.portlet.MyAppEditController" />

Develop your controllers and your views as you normally would in a Spring MVC
portlet. You'll also need to provide some necessary descriptors for Liferay.

### Liferay Descriptors [](id=liferay-descriptors)

Liferay portlet plugins that are packaged as WAR files should include some
Liferay specific descriptors.

The descriptor `liferay-display.xml` controls the category in which your portlet
appears in @product@'s *Add* menu. Find the complete DTD
[here](https://docs.liferay.com/portal/7.0/definitions/liferay-display_7_0_0.dtd.html).

Here's a simple example that just specifies the category the application will go under
in Liferay's menu for adding applications:

    <display>
        <category name="New Category">
            <portlet id="example-portlet" />
        </category>
    </display>

The descriptor `liferay-portlet.xml` is used for specifying additional
information about the portlet (like the location of CSS and JavaScript files or
the portlet's icon. A complete list of the attributes you can set can be
found [here](https://docs.liferay.com/portal/7.0/definitions/liferay-portlet-app_7_0_0.dtd.html)

    <liferay-portlet-app>
        <portlet>
            <portlet-name>example-portlet</portlet-name>
            <instanceable>true</instanceable>
            <render-weight>0</render-weight>
            <ajaxable>true</ajaxable>
            <header-portlet-css>/css/main.css</header-portlet-css>
            <footer-portlet-javascript>/js/main.js</footer-portlet-javascript>
            <footer-portlet-javascript>/js/jquery.foundation.orbit.js</footer-portlet-javascript>
        </portlet>
        <role-mapper>
            <role-name>administrator</role-name>
            <role-link>Administrator</role-link>
        </role-mapper>
        <role-mapper>
            <role-name>guest</role-name>
            <role-link>Guest</role-link>
        </role-mapper>
        <role-mapper>
            <role-name>power-user</role-name>
            <role-link>Power User</role-link>
        </role-mapper>
        <role-mapper>
            <role-name>user</role-name>
            <role-link>User</role-link>
        </role-mapper>
    </liferay-portlet-app>

You'll also notice the `role-mapper` elements included above. They're for
defining the Liferay roles used in the portlet. 

Then there's the `liferay-plugin-package.properties`. These properties describe
the Liferay plugin, declare its resources, and specify its security related
parameters. The DTD is found
[here](https://docs.liferay.com/portal/7.0/definitions/liferay-plugin-package_7_0_0.dtd.html)

    name=example-portlet
    module-group-id=liferay
    module-incremental-version=1
    tags=
    short-description=
    change-log=
    page-url=http://www.liferay.com
    author=Liferay, Inc.
    licenses=LGPL
    version=1

In the `liferay-plugin-package.properties` file you can also add OSGi metadata,
which is properly placed in the `MANIFEST.MF` file when you deploy your
WAR file. 

Find all of Liferay's DTDs [here](https://docs.liferay.com/portal/7.0/definitions/)

## Calling Services from Spring MVC [](id=calling-services-from-spring-mvc)

To call OSGi-based Service Builder services from your Spring MVC portlet, you
need a mechanism that gives you access to the OSGi service registry.

+$$$

**Note:** If you don't already have one, create a service builder project using [Blade CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli).

    springmvc-service-builder/
        build.gradle
        springmvc-service-builder-api/
            bnd.bnd
            build.gradle
        springmvc-service-builder-service/
            bnd.bnd
            build.gradle
            service.xml

Design your model entity and write your service layer as normal (see the
tutorials on Service Builder
[here](/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder)). After
that, add your service's API JAR as a dependency in your Spring MVC project. 

$$$

Since you can't look up a reference to the services published to the OSGi
runtime using Declarative Services, how do you call Service Builder services?
One way is by calling the static utility methods.

    FooServiceUtil.getFoosByGroupId()

While very simple, that's not the best way to call OSGi services because of the
dynamic nature of the OSGi runtime. Service implementations could be removed and
added at any time, and your plugin needs to be able to account for that.
Additionally, you need a mechanism that lets your portlet plugin react
gracefully to the possibility of the service implementation becoming unavailable
entirely. That's why you should open a Service Tracker when you want to call a
service that's in the OSGi service registry.

### Service Trackers [](id=service-trackers)

Since you don't have the luxury of using Declarative Services to manage your
service dependencies, you have a little bit of work to do if you want to gain
some of the benefits OSGi gives you:

-  Accounting for multiple service implementations, using the best service
    implementation available (taking into account the service ranking property)

-  Accounting for no service implementations

The static utility classes don't let you do that, and that's sad. But be happy,
because with a little code, you can regain those benefits.

To implement a service tracker you can do this:

    Bundle bundle = org.osgi.framework.FrameworkUtil.getBundle(this.getClass());
    BundleContext bundleContext = bundle.getBundleContext();
    org.osgi.util.tracker.ServiceTracker<SomeService, SomeService> serviceTracker = new 
    org.osgi.util.tracker.ServiceTracker(bundleContext, SomeService.class, null);

That's too wordy. To minimize the service tracker code you need to add to your
controllers, use a type-safe wrapper class that extends
`org.osgi.util.tracker.ServiceTracker`. Your `ServiceTracker` takes two generic
type parameters: the type of service being tracked, and the type of object being
produced. In the present use case, both types are the same.

    public class SomeServiceTracker extends 
        ServiceTracker<SomeService, SomeService> {     

        public SomeServiceTracker(Object host) {
            super(FrameworkUtil.getBundle(host.getClass()).getBundleContext(), 
                SomeService.class, null);     
        } 
    }

After extending the `ServiceTracker`, you just need to call the constructor, and
the service tracker is ready to use in your controller layer.

In your controllers, wherever you need to call the service of interest, open the
service tracker. An `init` method is a good place to put this initialization
code:

    @PostConstruct
    public void init() {
        someServiceTracker = new SomeServiceTracker(this);
        someServiceTracker.open();
    }

When you want to call the service in your controller’s method, you can make sure
the service tracker has something in it, then get the service using the Service
Tracker API’s `getService` method. After that, use the service to do something
cool:

    if (!someServiceTracker.isEmpty()) {
        SomeService someService = someServiceTracker.getService();
        someService.doSomethingCool();
    }

Of course, where there’s an `if`, there can also be an `else`, and you can do
whatever you’d like in response to an empty service tracker.

When it’s time for the controller bean to be removed, you can close the service
tracker. Using a `destroy` method is an appropriate place to do this:

    @PreDestroy
    public void destroy() {
        someServiceTracker.close();
    }

Now you know how to use a service tracker to look up services in the service
registry, giving you a more robust way to call OSGi services. But there's more.

### Implementing a Service Tracker Customizer [](id=implementing-a-service-tracker-customizer)

If you want to employ a callback-like approach for reacting to service changes
at the time they occur,  you can implement a
`org.osgi.util.tracker.ServiceTrackerCustomizer`. To illustrate how it works,
first consider a service tracker that sends an email each time a
service happens:

	public class SomeServiceTracker extends ServiceTracker<SomeService, SomeService> {

		public SomeServiceTracker(Object host) {
			super(FrameworkUtil.getBundle(host.getClass()).getBundleContext(), SomeService.class, null);
		}

		public SomeService addingService(ServiceReference<SomeService> reference) {
			sendAddingServiceEmail(reference);
			return super.addingService(reference);
		}

		public void modifiedService(ServiceReference<SomeService> reference, SomeService service) {
			super.modifiedService(reference, service);
			sendModifiedServiceEmail(reference);
		}

		public void removedService(ServiceReference<SomeService> reference, SomeService service) {
			super.removedService(reference, service);
			sendRemovedServiceEmail(reference);
		}
	}

The code discussed earlier for creating the service tracker is the same, but now
when service events happen, you're performing some logic (like sending an email)
on top of `ServiceTracker`'s implementation of the `ServiceTrackerCustomizer`
interface. To make things a bit more object oriented, create your own
implementation of `ServiceTrackerCustomizer`. Here's what the example logic
above looks like using this approach:

    public class EmailServiceTrackerCustomizer implements ServiceTrackerCustomizer<SomeService, MailState> {

        public EmailServiceTrackerCustomizer(MailContext mc) {
            _mc = mc;
        }

        public MailState addingService(ServiceReference<SomeService> reference) {
            MailState ms = new MailState(_mc);
            ms.sendAddingServiceEmail(reference);
            return ms;
        }

        public void modifiedService(ServiceReference<SomeService> reference, MailState ms) {
            ms.sendModifiedServiceEmail(reference);
        }

        public void removedService(ServiceReference<SomeService> reference, MailState ms) {
            ms.sendRemovedServiceEmail(reference);
        }

        private MailContext _mc;
    }

A small change to your service tracker initialization code is necessary when you
implement your own service tracker customizer: 

    public class SomeServiceTracker extends ServiceTracker<SomeService, MailState> {

        public SomeServiceTracker(Object host) {
            super(FrameworkUtil.getBundle(host.getClass()).getBundleContext(), SomeService.class,
                    new EmailServiceTrackerCustomizer());
        }
    }

There's now a different second type parameter (`MailState` in this example,
which is the object being produced) in the `extends` directive. You're also
replacing the `null` parameter in the constructor with `new
EmailServiceTrackerCustomizer()`. When this is null, you're declaring that the
service tracker will call the `SerivceTrackerCustomizer` methods on itself. By
instantiating your service tracker customizer instead, your service tracker is
now relying on your service tracker customizer implementation. 

Your service tracker customizer is called when a service is being added to a
service tracker. For any service your controller layer depends on, a service
tracker customizer lets you proactively participate in the events happening on
the service. A good use case is taking your application’s own functionality out
of service gracefully when a dependency can no longer be fulfilled.

As you can see, there's some boilerplate code required in order to enjoy the
benefits of the OSGi lifecycle. If you are not required to use a Spring MVC
portlet, consider using Liferay's MVC framework to design your portlets instead.
Then you can take advantage of the Declarative Services `@Component` and
`@Reference` annotations, which let you avoid the boilerplate code associated
with service trackers.
