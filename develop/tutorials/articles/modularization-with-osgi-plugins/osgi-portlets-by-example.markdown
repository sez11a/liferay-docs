# OSGi Portlets by Example

Liferay 7.0 allows portlets to be deployed as OSGi services. What are the
benefits of doing this?

- Portlets can be deployed, redeployed, uninstalled, started, and stopped at any
  time using OSGi's management tools. This means that Liferay no longer needs to
  depend on specific application server tools which are often not as reliable or
  flexible. It most cases, this will also result in a faster startup.
- Portlet configurations can be defined via annotations on portlet classes
  instead of via XML files. Properties that were previously specified in
  standard portlet configuration files such as `portlet.xml` and
  Liferay-specific configuration files such as `liferay-portlet.xml` and
  `liferay-display.xml` can be defined via annotations. `portlet.xml` might
  still need to be used for some advanced use cases.
- Portlets can declare dependencies. When developing an OSGi module, a developer
  needs to declare (with the help of the bnd tool) the APIs that the module's
  code depends on. Over time, this helps evolve the portlet to be more
  maintainable.
- Breaking changes can be detected in portlets sooner. If one of the
  dependencies changes in an incompatible way, you're notified at compile-time
  instead of at runtime.
- Portlets are more extensible. Designing portlets as OSGi bundles makes it very
  easy to make them extensible using OSGi extension points (which are
  annotation-driven)!
- Other benefits: OSGi modules tend to be easier to test since there is a higher
  emphasis on APIs. The remoting capabilities of the API increase the
  scalability since the portlet can be distributed in several processes, etc.

The key to harnessing the power of OSGi is choosing a *component framework* to
work with. A component framework is synonymous with frameworks which provide
**DI** (dependency injection), **SoC** (separation of concerns), and **IoC**
(Inversion of Control).

Some popular (non-OSGi) component frameworks (there are others) are

- Spring
- Google Guice
- CDI

OSGi component frameworks, in addition to providing the features mentioned
above, offer awareness of the dynamic nature of the OSGi runtime. Such awareness
supports well behaved arrival and departure of bundles and services at runtime.

A few OSGi component frameworks (there are others) are

- Declarative Services (a.k.a. **DS**, OSGi Spec) (**recommended**)
- Blueprint (OSGi Spec)
- Apache Felix DM
- IPojo

Liferay prefers **DS** (using DS annotations) as a component framework and so
most Liferay examples take this form. It's the most concise way of building
complex component models and it's the simplest to use.

However, Liferay portlets can also be designed using the raw OSGi API or using
Blueprint. Please see the []() and []() tutorials for more information.

## Build System

Liferay provides examples that demonstrate how to create OSGi applications for
Liferay 7. There are examples using several different build systems. Although
the examples are similar, it helps to choose examples which suit your preferred
build system. Complete projects are available in the
[BLADE](https://github.com/rotty3000/blade) project. 

Currently there are projects using:

- Maven
- Bndtools
- Gradle
- Liferay Plugins SDK (coming soon)

## The Portlet Class

Regardless of your preferred build system, you need to create at least a simple
Java project with a portlet class. Consider the following example portlet class:

    package my.bundle;

    import java.io.IOException;

    import javax.portlet.GenericPortlet;
    import javax.portlet.PortletException;
    import javax.portlet.RenderRequest;
    import javax.portlet.RenderResponse;

    public class MyPortlet extends GenericPortlet {
            @Override
            protected void doView(
                            RenderRequest request, RenderResponse response)
                    throws PortletException, IOException {

                    response.getWriter().print("Hello World!");
            }
    }

This is an overly simple portlet. However from the point of view of the portlet,
there are no differences with respect to how portlets operate in Liferay via
OSGi and how to operate without OSGi. The level of complexity of the portlet is
irrelevant for this example. We're going to focus only on the service aspects of
the portlet.

### DS Annotations

**DS**, while fundamentally an XML based component framework, provides a
convenient way to define components via its build time annotations. These
annotations are supported by bnd or by any build tool which uses
[bnd](http://bnd.bndtools.org/) under the covers (like the Apache Felix
`maven-bundle-plugin`, the Gradle `osgi` plugin, the bnd `ant` tasks, or
[Bndtools IDE](http://bndtools.org/)).

You can add annotations to your portlet which result in your portlet being
registered as an OSGi service. 

Add the following directly to the portlet class:

    import org.osgi.service.component.annotations.Component;

    @Component(
            immediate = true,
            property = {
                    "com.liferay.portlet.display-category=category.sample",
                    "com.liferay.portlet.instanceable=true",
                    "javax.portlet.display-name=My DS Portlet",
                    "javax.portlet.security-role-ref=power-user,user"
            },
            service = Portlet.class
    )

The complete class looks like this:

    package my.bundle;

    import java.io.IOException;

    import javax.portlet.GenericPortlet;
    import javax.portlet.Portlet;
    import javax.portlet.PortletException;
    import javax.portlet.RenderRequest;
    import javax.portlet.RenderResponse;

    import org.osgi.service.component.annotations.Component;

    @Component(
            immediate = true,
            property = {
                    "com.liferay.portlet.display-category=category.sample",
                    "com.liferay.portlet.instanceable=true",
                    "javax.portlet.display-name=My DS Portlet",
                    "javax.portlet.security-role-ref=power-user,user"
            },
            service = Portlet.class
    )
    public class MyPortlet extends GenericPortlet {
            @Override
            protected void doView(
                            RenderRequest request, RenderResponse response)
                    throws PortletException, IOException {

                    response.getWriter().print("Hello World!");
            }
    }

That's it! 

Build and deploy your bundle, access Liferay's Gogo shell, and run the *lb*
command. Confirm that your bundle has been installed and is active:

![image]()

Add your portlet to a Liferay page and look for something like this:

![image]()

**Note:** DS annotations are class annotations. As such, they do not result in
any extra runtime dependencies.
