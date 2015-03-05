# Portlet Using the OSGi API

It's rather straightforward to use the
[OSGi](http://www.osgi.org/Specifications/HomePage) API to publish and manage
services. However, it's not recommended in the general case as it has some side
effects:

- it ties your code to OSGi apis
- it produces a significant amount of boilerplate
- it leaves the door open for common programmer error when unfamiliar with
  dynamic environments

Liferay recommends using DS whenever possible. However, many of Liferay's OSGi
support internals may directly use the OSGi APIs, so it's possible to come
across those from time to time while observing Liferay code.

The OSGi API provides access to the lifecycle a bundle when the bundle provides
(and declares) an implementation of the `BundleActivator` interface.

OSGi Bundles declare features by providing headers in the jar file's `MANIFEST.MF` file. Let's assume we have a bundle with the following `MANIFEST.MF`:

    Bundle-SymbolicName: my.bundle
    Bundle-Activator: my.bundle.Activator
    Import-Package: javax.portlet, org.osgi.framework

The `Activator` class looks like:

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

This class implements start and stop methods. As expected, these methods fire during the start and stop phases of the bundle lifecycle.

Let's create and register the portlet service using the OSGi API:

    bundleContext.registerService(Portlet.class, new MyPortlet(), null);

Pretty simple.

However, when we register a service in OSGi we become responsible for its proper
un-registration. When the service is registered a handle to the registered
service is returned and we can use this to clean up when our bundle is later
stopped, such as when shutting down the framework (or in our case, the portal). 

The full implementation looks something like this:

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

If we deploy the bundle into Liferay we should find the portlet in the **Add**
menu:

![image]()

As we've defined no metadata for our portlet it's display name has been
generated directly from the java object.

![image]()

Let's add a few more properties to get a general feel for it. 

We'll add a proper display name, 

    properties.put("javax.portlet.display-name", "My Bundle Portlet");

place it in a proper category, 

    properties.put("com.liferay.portlet.display-category", "category.sample");

and make it instanceable so we can add it to the page multiple times (for kicks).

    properties.put("com.liferay.portlet.instanceable", "true");

After re-deploying and a page refresh we should see:

![image]()

Note we can now add multiple instances to the page:

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

Though not extreme, it's plain there's more boiler plate code we'd rather avoid.
