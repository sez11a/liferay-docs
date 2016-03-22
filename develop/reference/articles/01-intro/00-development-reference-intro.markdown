# Development Reference [](id=development-reference)

<p style="margin-left: 40px;">
Here you'll find Liferay's reference documentation for Java, tag libraries,
JavaScript and CSS, and deployment descriptors.
</p>

<p style="margin-left: 40px;">
Liferay Portal 7 Community Edition's Portal Core reference documentation is
available online  at <a href="https://docs.liferay.com/portal/7.0"
target="_blank">https://docs.liferay.com/portal/<span
class="opens-new-window-accessible"> (Opens New Window)</span></a>
and available for download as a ZIP file from Liferay's Downloads
<a href="https://www.liferay.com/downloads" target="_blank">Liferay's Downloads<span
class="opens-new-window-accessible"> (Opens New Window)</span></a>. 
</p>

<p style="margin-left: 40px;">
Liferay Portal CE module reference documentation JAR files accompany each
module's class artifacts in the <a
href="https://repository.liferay.com/nexus/content/repositories/liferay-releases-ce/com/liferay/"
target="_blank">Liferay Nexus repository<span
class="opens-new-window-accessible"> (Opens New Window)</span></a>. The reference
documentation includes Javadoc and tag library (taglib) docs.
</p>

<h3><span style="font-size: 22px;">Java APIs</span></h3>

<p style="margin-left: 40px;">
A module's Javadoc describes its Java API. You can download a newly migrated module's Javadoc
JAR file from <a
href="https://repository.liferay.com/nexus/content/repositories/liferay-releases-ce/com/liferay/"
target="_blank">Liferay Nexus repository<span
class="opens-new-window-accessible"> (Opens New Window)</span></a> and extract
it to a local folder. The extracted <code>index.html</code> file is the gateway
to exploring the module's Java API.
</p>

<p style="margin-left: 40px;">
Here are the Java packages common to newly migrated API modules:
<ul style="margin-left: 60px;">
<li>
<code>com.liferay.[component].constants</code> - Classes that specify module
specific constant values, such as web keys
</li>
<li>
<code>com.liferay.[component].exception</code> - Module specific exception classes
</li>
<li>
<code>com.liferay.[component].model</code> - Model entity interfaces, entity wrapper classes, and SOAP classes
</li>
<li>
<code>com.liferay.[component].service</code> - Local and remote service interfaces
</li>
<li>
<code>com.liferay.[component].service.persistence</code> - Entity persistence interfaces, entity finder interfaces, and static utilities
</li>
<li>
<code>com.liferay.[component].util</code> - Utility classes
</li>
</ul>
</p>

<p style="margin-left: 40px;">
Not all components have been migrated from the portal-service API. Their API
modules have proxies to classes in portal-service and have Javadoc available at
<a href="https://docs.liferay.com/portal/7.0/javadocs/portal-service/"  
target="_blank">https://docs.liferay.com/portal/<span
class="opens-new-window-accessible"> (Opens New Window)</span></a>. Their
package structure includes <code>kernel</code> after the component name. The
common structure looks like this:
<ul style="margin-left: 60px;">
<li>
<code>com.liferay.[component].kernel.constants</code> - Classes that specify module specific constant values, such as web keys
</li>
<li>
<code>com.liferay.[component].kernel.exception</code> - Module specific exception classes
</li>
<li>
<code>com.liferay.[component].kernel.model</code> - Model entity interfaces, entity wrapper classes, and SOAP classes
</li>
<li>
<code>com.liferay.[component].kernel.service</code> - Local and remote service interfaces
</li>
<li>
<code>com.liferay.[component].kernel.service.persistence</code> - Entity persistence interfaces, entity finder interfaces, and static utilities
</li>
<li>
<code>com.liferay.[component].kernel.util</code> - Utility classes
</li>
</ul>
</p>

<p style="margin-left: 40px;">
Liferay's Java API paired up with its taglibs facilitates showing off data
models and creating powerful UI components in your presentation layer.
</p>

<p style="margin-left: 40px;">&nbsp;</p>

<h3><span style="font-size: 22px;">Taglibs</span></h3>

<p style="margin-left: 40px;">
<span style="font-size:18px;">
<a href="http://docs.liferay.com/portal/7.0/taglibs/" target="_blank">
Portal Taglibs <span class="opens-new-window-accessible">(Opens New Window)</span>
</a>
</span>
</p>

<p style="margin-left: 40px;">
Tag libraries for <a href="http://alloyui.com/" target="_blank">AlloyUI <span
class="opens-new-window-accessible">(Opens New Window)</span></a>, Liferay UI,
Liferay themes, Liferay portlets, Liferay security, and standard portlets.
</p>

<p style="margin-left: 40px;">&nbsp;</p>

<p style="margin-left: 40px;">
<span style="font-size:18px;">
<a href="http://docs.liferay.com/faces/3.1/vdldoc/" target="_blank">
Faces Taglibs<span class="opens-new-window-accessible">(Opens New Window)</span>
</a>
</span>
</p>

<p style="margin-left: 40px;">
The latest version of Liferay Faces JSF tag documentation in View Declaration
Language (VDL) format. VDL docs for all versions of Liferay Faces are available
at <a href="http://docs.liferay.com/faces/"
target="_blank">http://docs.liferay.com/faces/ <span
class="opens-new-window-accessible">(Opens New Window)</span></a>.
</p>

<p style="margin-left: 40px;">&nbsp;</p>

<h3><span style="font-size: 22px;">JavaScript &amp; CSS</span></h3>

<p style="margin-left: 40px;">
<span style="font-size:18px;">
<a href="http://alloyui.com" target="_blank">
AlloyUI 3&nbsp;<span class="opens-new-window-accessible">(Opens New Window)</span>
</a>
</span>
</p>

<p style="margin-left: 40px;">
Liferay includes AlloyUI and all of its JavaScript APIs are available
within portlets, templates and themes.
</p>

<p style="margin-left: 40px;">&nbsp;</p>

<p style="margin-left: 40px;">
<span style="font-size:18px;">
<a href="http://getbootstrap.com/2.3.2" target="_blank">
Bootstrap 3&nbsp;<span class="opens-new-window-accessible">(Opens New Window)</span>
</a>
</span>
</p>

<p style="margin-left: 40px;">
Liferay uses Bootstrap natively and all of its CSS classes are available within
portlets, templates and themes.
</p>

<p style="margin-left: 40px;">&nbsp;</p>

<h3><span style="font-size: 22px;">Descriptor Definitions</span></h3>

<p style="margin-left: 40px;">
<span style="font-size:18px;">
<a href="http://docs.liferay.com/portal/7.0/definitions/" style="text-decoration: none;" target="_blank">
DTDs <span class="opens-new-window-accessible">(Opens New Window)</span>
</a>
</span>
</p>

<p style="margin-left: 40px;">
Describes the XML files used in configuring Liferay apps, Liferay plugins, and
Liferay Portal.
</p>

<p style="margin-left: 40px;">&nbsp;</p>

<p style="margin-left: 40px;">
<span style="font-size:18px;">
<a href="http://docs.liferay.com/portal/7.0/propertiesdoc/liferay-plugin-package_7_0_0.properties.html"
id="yui_patched_v3_11_0_1_1414746645512_1007" style="word-spacing: normal;
outline: 0px;" target="_blank">
liferay-plugin-package_7_0_0.properties&nbsp;<span class="opens-new-window-accessible">(Opens New Window)</span>
</a>
</span>
</p>

<p style="margin-left: 40px;">
Defines properties used for Liferay plugins. These properties describe the
the Liferay plugin, declare its resources, and specify its security related
parameters.  </p>


