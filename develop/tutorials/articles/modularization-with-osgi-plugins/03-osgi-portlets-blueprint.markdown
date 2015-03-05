# Blueprint

Blueprint is a component framework derived from Spring’s XML bean
configurations.

We need to create a blueprint XML file whose location in the bundle must default
to `OSGi-INF/blueprint/*.xml`. (This can be overridden by OSGi headers which
we'll leave as an exercise for a later time.)

Therefore we'll create `OSGi-INF/blueprint/blueprint.xml` with the following
content:

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
        <bean id="my.bundle.MyPortlet" class="my.bundle.MyPortlet">
    </blueprint> 

Re-deploying the portlet, we should see:

![image]()

After adding it to the page we should have:

![image]()
