# OSGi Portlets Using the OSGi API

It's rather straightforward to use the
[OSGi](http://www.osgi.org/Specifications/HomePage) API to publish and manage
services. However, it's not recommended to manually publish and manage services
via the OSGi API in the general case. Doing so produces some side effects:

- It ties your code to OSGi APIs
- It produces a significant amount of boilerplate code
- Developers who are unfamiliar with dynamic environments are more likely to
  make errors

Liferay recommends using DS (declarative services) whenever possible. However,
many of Liferay's OSGi support internals may directly use the OSGi APIs. If
you're observing or debugging Liferay code, it's possible to come across these
from time to time.

The OSGi API provides access to the lifecycle of a bundle when the bundle
provides (and declares) an implementation of the `BundleActivator` interface.

OSGi bundles declare features by providing headers in the `MANIFEST.MF` files of
their JAR files. Suppose you have a bundle with the following `MANIFEST.MF`:

    Bundle-SymbolicName: my.bundle
    Bundle-Activator: my.bundle.Activator
    Import-Package: javax.portlet, org.osgi.framework

The `Activator` class looks like this:

    package my.bundle;

    import org.osgi.framework.BundleActivator;
    import org.osgi.framework.BundleContext;

    public class Activator implements BundleActivator {

            @Override
            public void start(BundleContext bundleContext) 
                    throws Exception {
            }

            @Override
            public void stop(BundleContext bundleContext) 
                    throws Exception {
            }

    }

This class implements start and stop methods. As expected, these methods fire
during the start and stop phases of the bundle lifecycle.

The following line creates and registers the portlet service using the OSGi API:

    bundleContext.registerService(Portlet.class, new MyPortlet(), null);

Pretty simple.

However, when you register a service in OSGi, you become responsible for its
proper un-registration. When the service is registered, a handle to the
registered service is returned. You can use this to clean up when your bundle is
later stopped, such as when shutting down the framework (in your case, when
shutting down the portal). 

A full implementation looks something like this:

    package my.bundle;

    import java.util.Dictionary;
    import java.util.Hashtable;

    import javax.portlet.Portlet;

    import org.osgi.framework.BundleActivator;
    import org.osgi.framework.BundleContext;
    import org.osgi.framework.ServiceRegistration;

    public class Activator implements BundleActivator {

            @Override
            public void start(BundleContext bundleContext) 
                    throws Exception {
                    
                    _serviceRegistration = bundleContext.registerService(
                            Portlet.class, new MyPortlet(), null);
            }

            @Override
            public void stop(BundleContext bundleContext) 
                    throws Exception {
                    
                    _serviceRegistration.unregister();
            }

            private ServiceRegistration<Portlet> _serviceRegistration;

    }

If you deploy the bundle into Liferay, you should find the portlet in the
**Add** menu:

![image]()

As you've defined no metadata for your portlet, its display name has been
generated directly from the Java object.

![image]()

Add a few more properties to get a general feel for how it works.

Add a proper display name:

    properties.put("javax.portlet.display-name", "My Bundle Portlet");

Place it in a proper category:

    properties.put("com.liferay.portlet.display-category", "category.sample");

Make it instanceable so that it can be added to the page multiple times (for
kicks):

    properties.put("com.liferay.portlet.instanceable", "true");

After re-deploying and a page refresh you should see:

![image]()

Note that you can now add multiple portlet instances to the page:

![image]()

Finally the entire class looks like:

    package my.bundle;

    import java.io.IOException;

    import java.util.Dictionary;
    import java.util.Hashtable;

    import javax.portlet.GenericPortlet;
    import javax.portlet.Portlet;
    import javax.portlet.PortletException;
    import javax.portlet.RenderRequest;
    import javax.portlet.RenderResponse;

    import org.osgi.framework.BundleActivator;
    import org.osgi.framework.BundleContext;
    import org.osgi.framework.ServiceRegistration;

    public class Activator implements BundleActivator {

            @Override
            public void start(BundleContext bundleContext) 
                    throws Exception {
                    
                    Dictionary<String, Object> properties = new Hashtable<String, Object>();

                    properties.put(
                            "com.liferay.portlet.display-category", "category.sample");
                    properties.put(
                            "com.liferay.portlet.instanceable", "true");
                    properties.put("javax.portlet.display-name", "My Bundle Portlet");
                    properties.put(
                            "javax.portlet.security-role-ref",
                            new String[] {"power-user", "user"});

                    _serviceRegistration = bundleContext.registerService(
                            Portlet.class, new MyPortlet(), properties);
            }

            @Override
            public void stop(BundleContext bundleContext) 
                    throws Exception {
                    
                    _serviceRegistration.unregister();
            }

            private ServiceRegistration<Portlet> _serviceRegistration;

    }

The amount of boilerplate code generated by using manually using the OSGi API to
manually publish and manage services is not extreme but it is significant.
Therefore, Liferay recommends using DS (declarative services) to publish and
manage services instead of manually using the OSGi API.

You can find a complete example portlet built via the OSGi API in the BLADE
project on Github. It's called *Portlet OSGi API*. Remember that the BLADE
project examples are available for several different build systems, such as
Maven, Gradle, and Bndtools. For example, the Maven version of the Portlet OSGi
API portlet is available here:
[Maven Portlet OSGi API](https://github.com/rotty3000/blade/tree/master/maven/blade.portlet.ds). Note that there are two classes in the `blade.portlet` package: `OSGiAPIPortlet` and `Activator`. The `start` method of the `Activator` class registers the `OSGiAPIPortlet` service with the following lines:

    _serviceRegistration = bundleContext.registerService(
        Portlet.class, new OSGiAPIPortlet(), properties);

The Maven bundle plugin is configured in the project's `pom.xml` file like this:

    <plugin>
        <groupId>org.apache.felix</groupId>
        <artifactId>maven-bundle-plugin</artifactId>
        <version>2.5.0</version>
        <extensions>true</extensions>
        <configuration>
            <instructions>
                <Bundle-Activator>blade.portlet.Activator</Bundle-Activator>
                <Private-Package>blade.portlet</Private-Package>
                <Import-Package>javax.portlet;version="[2.0,3)",org.osgi.framework;versi
                on="[1.7,2)",javax.servlet;version="[2.5,4.0.0)"</Import-Package>
            </instructions>
        </configuration>
    </plugin>

This Maven bundle plugin configuration generates a `META-INF/MANIFEST.MF` file
in the JAR file with the following line (among others):

    Bundle-Activator: sample.osgi.Activator

When the Portlet OSGi API portlet is deployed, the `MANIFEST.MF` file is read
and the `Activator` is invoked which registers the `OSGiAPIPortlet` service.
