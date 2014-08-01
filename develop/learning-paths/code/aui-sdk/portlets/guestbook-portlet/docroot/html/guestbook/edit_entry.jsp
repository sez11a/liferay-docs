
<%@include file = "/html/init.jsp" %>

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/html/guestbook/view.jsp"></portlet:param>
</portlet:renderURL>

<portlet:actionURL name="addEntry" var="addEntryURL"></portlet:actionURL>
<aui:script>
YUI().use(
  'aui-char-counter',
  function(Y) {
    new Y.CharCounter(
      {
        counter: '#counter',
        input: '#<portlet:namespace />message',
        maxLength: 70
      }
    );
  }
);
</aui:script>
<aui:form action="<%= addEntryURL %>" name="<portlet:namespace />fm">

        <aui:fieldset>

            <aui:input name="name" >
            	<aui:validator name="required"/>
            </aui:input>
            <aui:input name="email" >
            	<aui:validator name="email"/>
            	<aui:validator name="required"/>
            </aui:input>
            <aui:input id="message" type="textarea" name="message" style="resize:none" >
            	<aui:validator name="required" errorMessage="Leave a message please." />
            </aui:input>
            
            <aui:input name='guestbookId' type='hidden' value='<%= ParamUtil.getString(renderRequest, "guestbookId") %>'/>
			<div style="margin-top: -30px">
            <span id="counter"></span> character(s) remaining
            </div>
        </aui:fieldset>

        <aui:button-row>

			<aui:button type="submit"></aui:button>
			<aui:button type="cancel" onClick="<%= viewURL %>"></aui:button>

        </aui:button-row>
</aui:form>

