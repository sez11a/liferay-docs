# Configuring Cache in a Module [](id=configuring-cache-in-a-module)

Configuring module class cache for single VM and multi VM environments is
straightforward. 

1.  For single VM cache, create a file called `module-single-vm.xml` in your 
    module's `src/main/resources/META-INF` folder. 

2.  For multi VM cache, create a file called `module-multi-vm.xml` in your 
    module's `src/main/resources/META-INF` folder. 

3.  Refer to the
    [Ehcache documentation](http://www.ehcache.org/documentation/2.8/configuration/index.html)
    and the
    [ehcache.xsd](http://www.ehcache.org/ehcache.xsd)
    to correctly specify class cache in your `module-single-vm.xml` and
    `module-multi-vm.xml` files. 

For example, the @product@ Web Experience suite's `com.liferay.journal.service`
module specifies cache for its `com.liferay.journal.util.JournalContent` class
using this
[`module-multi-vm.xml`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/journal/journal-service/src/main/resources/META-INF/module-multi-vm.xml)
file content:

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

Congratulations! You set cache for classes in your module. 

## Related Topics [](id=related-topics)

[Configuring Cache in a Portlet WAR](/developer/frameworks/-/knowledge_base/7-2/configuring-cache-in-a-portlet-war)
