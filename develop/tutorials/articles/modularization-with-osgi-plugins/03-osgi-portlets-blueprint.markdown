# Blueprint

Blueprint is an OSGi component framework that's inspired by Spring's XML bean
configurations. It can be used in a similar fashion to Declarative Services.

If you want to create a Blueprint project, you need to create a Blueprint XML
file. Its location in your bundle must default to `OSGi-INF/blueprint/*.xml`.
(You can override this location by using OSGi headers.)

As an example, suppose that you have a portlet class called `MyPortlet` in your
`my.bundle` package. To configure your project in this case, you could create a
`OSGi-INF/blueprint/blueprint.xml` file with the following contents:

    <?xml version="1.0" encoding="UTF-8"?>
    <blueprint xmlns="http://www.osgi.org/xmlns/blueprint/v1.0.0">
        <service id="my.bundle.MyPortlet.service" interface="javax.portlet.Portlet" ref="my.bundle.MyPortlet">
            <service-properties>
                <entry key="com.liferay.portlet.display-category" value="category.sample" />
                <entry key="com.liferay.portlet.instanceable" value="true" />
                <entry key="javax.portlet.display-name" value="My Blueprint Portlet" /> 
                <entry key="javax.portlet.security-role-ref" value="power-user,user" />
            </service-properties>
        </service>
        <bean id="my.bundle.MyPortlet" class="my.bundle.MyPortlet" />
    </blueprint> 

Build and deploy your bundle, access Liferay's Gogo shell, and run the *lb*
command. To confirm that your bundle has been installed and is active, look for
a line like the following:

    112|Active     |    1|My Blueprint Portlet (1.0.0.SNAPSHOT)

Check that you can add your portlet to a page and that it's working correctly.
An easy way to make sure that it's working is to check that its `doView` method
is invoked.

![In the example above, the portlet was added to the *category.sample* category. When added to a Liferay page, the portlet displays the text *My Blueprint Portlet - Hello World*.](../../images/my-blueprint-portlet.png)

You can find a complete example portlet built via Blueprint in the BLADE project
on Github. It's called *Portlet Blueprint*. Remember that the BLADE project
examples are available for several different build systems, such as Maven,
Gradle, and Bndtools. For example, the Maven version of the Portlet Blueprint
portlet is available here:
[Maven Portlet Blueprint](https://github.com/rotty3000/blade/tree/master/maven/blade.portlet.blueprint).
