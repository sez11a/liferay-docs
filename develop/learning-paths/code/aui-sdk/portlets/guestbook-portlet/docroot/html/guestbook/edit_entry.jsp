

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
<aui:script>
YUI().use(
  'aui-tooltip',
  function(Y) {
    new Y.Tooltip(
      {
        trigger: '#help',
        position: 'right',
        cssClass: 'tooltip-help',
        opacity: 1,
        visible: false
      }
    ).render();
  }
);
</aui:script>
<aui:script use="event, node">
    var btnSubmit = A.one("#submit");
    var btnSave = A.one("#save");
    var verb = A.one("#<portlet:namespace />verb");
    var adj = A.one("#<portlet:namespace />adj");
	var mam = A.one("#<portlet:namespace />ani");
	var adv = A.one("#<portlet:namespace />adv");
	var loc = A.one("#<portlet:namespace />loc");
	var container = A.one("#message");
	var title = A.one("#title");
	var checkbox = A.one("#<portlet:namespace/>showCheckbox");
    var silly = A.one("#<portlet:namespace/>silly");
    
    
    btnSubmit.on('click', function(event){
        
        ver = (verb.get('value'));
        adje = (adj.get('value'));
        adve = (adv.get('value'));
        mama = (mam.get('value'));
        loca = (loc.get('value'));
        container.append('<p id="phrase"></p>');
        message= A.one("#phrase");
        
        
        message.html('Your silly phrase of the day is:<br>' + '"' + ver + ' your ' + adje + ' ' + mama + ' ' + adve + ' in the ' + loca + '."');
        message.setStyle('fontSize', '200%');
        message.setStyle('lineHeight', '120%');
        
        if (ver == '' ||adje == '' ||adve == '' ||mama == '' ||loca == '') {
			message.html('');
			alert('You need to fill out the entire form to generate a silly phrase');			
		}
		
});

checkbox.on('change', function(event){
var checked = checkbox.attr('checked');
if(checked){
	btnSubmit.show();
	container.show();
	silly.show();
	title.show();
}
else if(!checked){
	btnSubmit.hide();
	container.hide();
	silly.hide();
	title.hide();
}
	
});

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
            <div>
            <div style="float:left;"><aui:input id="show" type="checkbox" name="include" label="Show silly phrase generator" checked="false"/></div>
            <div style="float:left; margin-left:10px;"><span id="help" title="Check the box to create a silly phrase or uncheck it to hide the form. Fill out the fields below with the correct parts of speech to generate a unique and silly phrase."><liferay-ui:icon image="help" message=""/></span></div>
            </div>
        </aui:fieldset>

        <aui:button-row>

			<aui:button type="submit" id="save"></aui:button>
			<aui:button type="cancel" onClick="<%= viewURL %>"></aui:button>

        </aui:button-row>
</aui:form>
<html>
<p style="font-size:35px;line-height:120%;display:none;" id="title">Silly Phrase Generator</p>
<div id="message"></div>
</html>
<aui:form>

		<aui:fieldset id="silly" style="display:none;">
			<aui:input name="adjective" id="adj" >
            	<aui:validator name="required"/>
            </aui:input>
            <aui:input name="verb" id="verb" >
            	<aui:validator name="required"/>
            </aui:input>
            <aui:input name="adverb" id="adv" >
            	<aui:validator name="required"/>
            </aui:input>
            <aui:input name="animal" id="ani" >
            	<aui:validator name="required"/>
            </aui:input>
            <aui:input name="location" id="loc" >
            	<aui:validator name="required"/>
            </aui:input>
        </aui:fieldset>
            
            <aui:button class="btn btn-primary" id="submit" value="Generate" style="display:none;"></aui:button>
        
</aui:form>