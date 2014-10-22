# OSGi Portlets by Example

Liferay 7.0 (as of yet released) offers the capability of deploying portlets as OSGi services. What are the benefits of doing this?

* Deploy, Redeploy, Uninstall, start and stop portlets at any time using OSGi’s management tools. That means no longer depending on specific app server tools which are often not as reliable or flexible. It will probably mean a faster startup as well.
* Define the portlets with annotations instead of XML files. Both for the standard characteristics (portlet.xml not needed anymore except for some advanced options) and the ones that are specific to Liferay (liferay-portlet.xml and liferay-display.xml)
* Declared dependencies: when developing an OSGi module the developer needs to declare (with the help of the bnd tool) the APIs that the module’s code depends on. Over time this will help evolve the portlet to be more maintainable.
* Detect breaking changes sooner: If one of the dependencies have changed in an incompatible way you will be notified instead of getting Java errors during runtime later on.
* Improved extensibility: by making your portlets OSGi bundles it will be dead easy to make them extensible as well using OSGi extension points (which are annotation driven!)
* Much more: OSGi modules tend to be easier to test since there is a higher emphasis on APIs, the remoting capabilities of API will increase the scalability since the portlet can be distributed in several processes, etc.

The key to harnessing the power of OSGi is choosing a *component framework* to work with. A component framework is synonymous with frameworks which provide **DI** (dependency injection), **SoC** (separation of concerns), and **IoC** (Inversion of Control).

Some popular (non-OSGi) component frameworks (there are others) are:

* Spring
* Google Guice
* CDI

OSGi component frameworks, in addition to providing the features above, offer awareness of the dynamic nature of the OSGi runtime supporting well behaved arrival and departure of bundles and services at runtime.

A few are (there are others):

* Declarative Services (a.k.a. **DS**, OSGi Spec) (**recommended**)
* Blueprint (OSGi Spec)
* Apache Felix DM
* IPojo

Liferay will prefer **DS** (using DS annotations) as a component framework and so most examples will take this form. It’s the most concise way of building complex component models and simplest to use.

However, this document also demonstrates designing portlets using the raw OSGi API as well as Blueprint.

## Build system

Though the examples are similar it helps choose the samples which will suit your preferred buildsystem. Complete projects are available in the blade project. 

Currently there are projects using:

* Maven
* Bndtools
* Gradle (coming soon)
* Liferay Plugins SDK (coming soon)
* Others? (if there is significant interest we can add any other build system)

## The portlet class

Let’s start with the following portlet class:

```java
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
```

This is an overly simple portlet. However from the point of view of the portlet, there are no differences with respect to how portlets operate in Liferay via OSGi and so the level of complexity of the portlet is irrelevant for this document. We’re going to focus on the service aspects only.

### DS Annotations

**DS**, while fundamentally an XML based component framework, provides a convenient way to define components via it’s build time annotations. These annotations are supported by bnd or by any build tool which uses [bnd](http://bnd.bndtools.org/) under the covers (like the Apache Felix `maven-bundle-plugin`, the gradle `osgi` plugin, the bnd `ant` tasks, or [Bndtools IDE](http://bndtools.org/)).

Let’s add the annotations which will result in the portlet being registered as an OSGi service. 

Add the following directly to the portlet class:

```java
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
```

The complete class looks like this:

```java
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
```

That’s it! 

Build and deploy the bundle, refresh the page and we should see:

[image]

After adding it to the page we have:

[image]

>**Note:** DS annotations are class annotation and so do not result in any extra runtime dependencies.
