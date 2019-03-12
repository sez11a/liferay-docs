---
header-id: configuring-cache-in-a-portlet-war
---

# Configuring Cache in a Portlet WAR

Here you will configure cache for a portlet WAR classes in single VM and multi
VM environments. 

1.  In your portlet's application context (e.g., any path under `WEB-INF/src`), 
    create an Ehcache XML configuration file to specify cache for single VM
    environments. The file name is arbitrary. 

    +$$$

    Refer to the
    [Ehcache 2.8 documentation](http://www.ehcache.org/documentation/2.8/configuration/index.html)
    and the
    [ehcache.xsd](http://www.ehcache.org/ehcache.xsd)
    to correctly specify class cache. 

    $$$

2.  In your application context, create an Ehcache XML configuration file for 
    multi-VM environments. 

3.  In your `portlet.properties` file, specify the following properties for 
    your single and multi-VM cache configuration file paths. 

        ehcache.single.vm.config.location=path/to/file
        ehcache.multi.vm.config.location=path/to/file 

    The paths are relative to your application context root (e.g.,
    `WEB-INF/src`). Replace `path/to/file` with each environment's cache
    configuration file path. 

For example, here's the
[`test-cache-configuration-portlet`](https://github.com/liferay/liferay-plugins/blob/7.0.x/portlets/test-cache-configuration-portlet)
portlet WAR's structure:

-   `docroot/WEB-INF/src/`
    -   `ehcache/`
        -   [`liferay-single-vm-ext.xml`](https://github.com/liferay/liferay-plugins/blob/7.0.x/portlets/test-cache-configuration-portlet/docroot/WEB-INF/src/ehcache/liferay-single-vm-ext.xml)
        -   [`liferay-multi-vm-clustered-ext.xml`](https://github.com/liferay/liferay-plugins/blob/7.0.x/portlets/test-cache-configuration-portlet/docroot/WEB-INF/src/ehcache/liferay-multi-vm-clustered-ext.xml)
    -   `portlet.properties`

The `portlet.properties` file specifies these properties:

    ehcache.single.vm.config.location=ehcache/liferay-single-vm-ext.xml
    ehcache.multi.vm.config.location=ehcache/liferay-multi-vm-clustered-ext.xml

Congratulations! You configured cache in your portlet WAR. 

## Related Topics

[Configuring Cache in a Module](/developer/frameworks/-/knowledge_base/7-2/configuring-cache-in-a-module)
