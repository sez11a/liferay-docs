---
header-id: configuring-cache-in-a-module
---

# Configuring Cache in a Module

Configuring module class cache for single VM and multi-VM environments is
straightforward. 

1.  For single VM cache, create a file called `module-single-vm.xml` in your 
    `src/main/resources/META-INF` folder. 

2.  For multi-VM cache, create a file called `module-multi-vm.xml` in your 
    `src/main/resources/META-INF` folder. 

3.  Refer to the
    [Ehcache documentation](http://www.ehcache.org/documentation/2.8/configuration/index.html)
    and the
    [ehcache.xsd](http://www.ehcache.org/ehcache.xsd)
    to correctly specify class cache in your `module-single-vm.xml` and
    `module-multi-vm.xml` files. 

For example, the @product@ Web Experience suite's `com.liferay.journal.service`
module uses the following
[`module-multi-vm.xml`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/journal/journal-service/src/main/resources/META-INF/module-multi-vm.xml)
to specify cache for its `com.liferay.journal.util.JournalContent` class.

    <ehcache
        dynamicConfig="true"
        monitoring="off"
        name="module-multi-vm"
        updateCheck="false"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:noNamespaceSchemaLocation="http://www.ehcache.org/ehcache.xsd"
    >
        <cache
            eternal="false"
            maxElementsInMemory="10000"
            name="com.liferay.journal.util.JournalContent"
            overflowToDisk="false"
            timeToIdleSeconds="600"
        >
        </cache>
    </ehcache>

Congratulations! You configured cache in your module. 

## Related Topics

[Configuring Cache in a Portlet WAR](/developer/frameworks/-/knowledge_base/7-2/configuring-cache-in-a-portlet-war)
