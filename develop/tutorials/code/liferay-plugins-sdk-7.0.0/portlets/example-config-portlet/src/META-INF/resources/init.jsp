<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@page import="com.liferay.docs.exampleconfigportlet.configuration.ExampleConfiguration" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
	ExampleConfiguration configuration = (ExampleConfiguration)
		request.getAttribute(ExampleConfiguration.class.getName());

	String defaultLanguage = null;
	String[] validLanguages = null;

	if (configuration != null) {
		defaultLanguage = portletPreferences.getValue(
            "defaultLanguage", configuration.defaultLanguage());

		validLanguages = configuration.validLanguages();
	}
%>