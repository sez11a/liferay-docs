<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>

<portlet:defineObjects />

<%
String jsonString = GetterUtil.getString(renderRequest.getAttribute("JSON_STRING"));

String pet1String = GetterUtil.getString(renderRequest.getAttribute("PET1_STRING"));

String pet2String = GetterUtil.getString(renderRequest.getAttribute("PET2_STRING"));

String pet3String = GetterUtil.getString(renderRequest.getAttribute("PET3_STRING"));

String pet1StringIncludeFoods = GetterUtil.getString(renderRequest.getAttribute("PET1_STRING_INCLUDE_FOODS"));
%>

<p>Example JSON string:</p>

<p><%= jsonString %></p>

<p>Example pet1 string:</p>

<p><%= pet1String %></p>

<p>Example pet2 string:</p>

<p><%= pet2String %></p>

<p>Example pet3 string:</p>

<p><%= pet3String %></p>

<p>Example pet1 string including favorite foods:</p>

<p><%= pet1StringIncludeFoods %></p>