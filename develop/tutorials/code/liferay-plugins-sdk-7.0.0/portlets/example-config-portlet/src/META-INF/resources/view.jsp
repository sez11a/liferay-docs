<%@ include file="/init.jsp" %>

<p>Default language: <%= defaultLanguage %></p>

<p>Valid languages:

<%
for (String validLanguage : validLanguages) {
%>
	
<%= " " + validLanguage %>

<%
}
%>

</p>