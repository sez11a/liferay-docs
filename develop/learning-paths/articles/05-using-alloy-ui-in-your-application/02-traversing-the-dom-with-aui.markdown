# Traversing the DOM with AlloyUI [](id=traversing-the-dom-with-alloyui)

In the last portion of the learning path you learned how AlloyUI functions and 
can be applied to your application. In this section we'll show you how you can 
use AlloyUI to manipulate HTML elements in the DOM(Document Object Model). We'll 
explore this subject through a fun addition to our current Guestbook application. 
We'll create a silly phrase generator using the aui:forms and aui:buttons that 
were covered in the last section. For now our silly phrase will display in our
page, we'll persist the phrase to the database in a later section.

First we'll walk through familiar territory: creating the form.

## Creating the form

1. Open the edit_entry.jsp and add `<aui:form>` tags to the bottom.

     Next we need to create the input fields for the form. If you're familiar 
     with the children's madlibs game, then you'll know the basic idea of what 
     our form will be. We're going to generate a silly phrase that will be made 
     up of different parts of speech. As we did with our other input fields, 
     we'll add `aui:validator` tags to the fields. Since we'll be grabbing these 
     input elements later, each one needs to be given a unique id as well.

2. Add the input fields below to the form:

            <aui:input name="adjective" id="adj" type="text">
            	<aui:validator name="required"/>
            </aui:input>
            <aui:input name="verb" id="verb" type="text">
            	<aui:validator name="required"/>
            </aui:input>
            <aui:input name="adverb" id="adv" type="text">
            	<aui:validator name="required"/>
            </aui:input>
            <aui:input name="animal" id="ani" type="text">
            	<aui:validator name="required"/>
            </aui:input>
            <aui:input name="location" id="loc" type="text">
            	<aui:validator name="required"/>
            </aui:input>
            
    We've added an additional type attribute of text for each of our input 
    fields, so that they are visible. The entry bean that is used in the
    guestbook form configures the `<aui:input>` fields automatically. Since we
    are not adding our silly phrase form fields to the bean, we have to force
    type to text so that they appear.

3. Add a aui:button to the form to generate the silly phrase:

        <button class="btn btn-primary" id="submit">Generate</button>

    Now that we have our form created we need to add the html element for our 
    silly phrase to display in:

4. add the html element just above the `<aui:form>` opening tag we just made:

        <html>
          <div id="message"></div>
        </html>

For now a empty `<div>` has been created with a unique id. We'll append an HTML 
element that will display our silly phrase later on. Now that we have the 
foundation layer, we can add the logic to make it work.

## Adding Logic to the Silly Phrase Generator

The first thing we need to do is grab our aui:button element so that we can 
attach a click event to it:

1. Add this aui script just below the `aui-char-counter` script we created in 
the first part of our learning path:

        <aui:script use="event, node">
          var btnSubmit = A.one("#submit");
        </aui:script>

    We've given the `use` attribute of our `<aui:script>` tag the value of the 
    `event` and `node` packages so that we have access to them for our click 
    event and input nodes later on. We use the `A.one` selector method to grab 
    our button node by passing it's id as the argument. Now that we have a 
    variable for our button element and our aui packages selected, we can 
    declare our variables.

2. Declare these variables just below the btnSubmit variable:

        var verb = A.one("#<portlet:namespace />verb");
        var adj = A.one("#<portlet:namespace />adj");
        var mam = A.one("#<portlet:namespace />ani"); 
        var adv = A.one("#<portlet:namespace />adv");
        var loc = A.one("#<portlet:namespace />loc");

    Each id for the input fields is preceeded by `<portlet:namespace/>` tag to 
    avoid namespacing issues between portlets. In addition to the input field 
    and button variables we'll need to declare a variable for an element to 
    display our silly phrase inside. We'll grab the div element we created 
    earlier for this:

        var container = A.one("#message");

    All of our variables have been declared, so now we can create our click 
    event.

3. Add the function just below our container variable:

        btnSubmit.on('click', function(event){

	    )};    

    Next, we need to declare variables for the value of our input fields.

4. Add these variables just inside of the function we just created:

        ver = (verb.get('value'));
        adje = (adj.get('value'));
        adve = (adv.get('value'));
        mama = (mam.get('value'));
        loca = (loc.get('value'));

    Next we'll add an html element to display our silly phrase. To do this, 
    we'll append a html `<p>` element to our container `<div>` and give it a 
    unique id to grab in the steps to follow.

5. Append the element just below our value variables:

        container.append('<p id="phrase"></p>');

    Now that we have our HTML element created we need to assign it a variable 
    that we can call upon later.

6. Add the variable below our appended element:

        message= A.one("#phrase");

    Alright! Our framework is set, now we need to write the code for the message. 
    To do this, we'll set the HTML of our `<p>` element which we just set to the 
    message variable.

7. Add this code below the message variable:

        message.html('Your silly phrase of the day is:<br>' + '"' + ver + ' your ' + adje + ' ' + mama + ' ' + adve + ' in the ' + loca + '."');
    
We use the `html()` method to set the inner HTML of the message element.

There you have it! redeploy your application and goto the add entry page of the 
guestbook to see our finished silly phrase generator. With the form filled out 
like the one below, your silly phrase should read: "Walk your left-footed 
platypus gingerly in the warehouse."

![Figure 1: You can generate some silly phrases with the silly phrase generator.](../../images/guestbook-silly-phrase.png)

Everything is in working order, but there are a few things left that we can 
do to make this better.

## Finishing Touches

The silly phrase generator is complete! Congrats! there are a few finishing 
touches that will hit it out of the park though. First of all, we can style the 
silly phrase so that it sticks out from the rest of the form. Second, we can 
safeguard against generating the silly phrase if the entire form is not filled 
out. Next, we can add an option to hide the silly phrase generator when it's not 
being used. Finally, we'll add a tool tip using Alloy's aui-tooltip module so 
that our guest knows that they have the option to create a silly phrase.

### Styling the Silly Phrase

The first thing we can do is add a bit of style to it, so that it stands out 
from our forms.

1. Add the style code just below the message code we just wrote:

        message.setStyle('fontSize', '200%');
        message.setStyle('lineHeight', '120%');

    We style our silly phrase by grabbing it's id, which we've set to the 
    variable `message` and then we call the `setStyle()` method on it and give 
    it two arguments: 1.) the CSS style we want to use; 2.) the value for the 
    style. In this case, we set the font size to double the original size. We've 
    also set the lineHeight to a little wider than normal to account for word 
    wrapping so that the words don't overlap when the page is resized to a 
    smaller width. One final thing we can do to give our silly phrase form a bit 
    of separation is to add a title.

2. Add the following `<p>` element just inside of our `<html>` tag:

        <p style="font-size:35px;line-height:120%;" id="title">Silly Phrase Generator</p>

    We've assigned our title some default styles inside of the `<p>` tag.  We've 
    given it a font-size to make it stand out as a title, and as with our silly 
    phrase, we've given it a larger than normal line height to account for word 
    wrapping. Now we can assign it a variable so that we can access it later.
    
3. Declare the title variable just below our container variable:

        var title = A.one("#title");

Our silly phrase now stands out from our form. Next we'll prevent the silly 
phrase from being generated if all the fields are not filled out. 
    
### Safeguarding the Silly Phrase Generator
    
We'll create an alert that will notify the guest that they need to fill out all 
the fields. We'll create an if statement to do this. First we need a condition 
to test. We want to make sure all the fields are filled out before the silly 
phrase is generated, so we can test if any of the fields are left empty when the 
generate button is clicked. 

1. Create the if statement just below our styles with the following conditions:

        if (ver == '' ||adje == '' ||adve == '' ||mama == '' ||loca == '') {
				
	    }

    Our if statement test if any of our fields are blank, by testing if the 
    corresponding variable equals nothing(''). Each condition is separated by 
    the `||`(or) operator, meaning that if any of these conditions are true the 
    statement is true. Now we can write what we want to happen if the condition 
    is true. Right now if the silly phrase is generated and any of the fields 
    are left blank, the phrase still displays the fields that were filled out. 
    To prevent this, we need to set the html for the message to nothing if the 
    condition is true.

2. Add this code to the condition:

        message.html('');

    Now if the silly phrase is generated with any blank fields, the message is 
    blank. Now we need to alert the guest that they need to fill out all the 
    fields. This is done with the `alert()` function.

3. Add the following alert to the if statement to complete the silly phrase 
generator:

        alert('You need to fill out the entire form to generate a silly phrase');

Next we'll setup the logic to hide the phrase when the form is not being used.

### Hiding the Silly Phrase Generator

The first thing we need to do is create a node that can have an event attached 
to it to hide and show our silly phrase generator. A checkbox works well for 
this, with it's two posiible states: checked and unchecked. We want this option 
to be visible on the guestbook form so we'll add it there.

1. Inside of the `edit_entry.jsp` add the following code to the guestbook form,
just before the closing `</aui:fieldset>` tag: 

        <aui:input id="show" type="checkbox" name="include" label="Show silly phrase" checked="false"/>

    Now that we have the checkbox created, we can assign it's node a variable.
    
2. Declare this variable just below the title variable:

        var checkbox = A.one("#<portlet:namespace/>showCheckbox");
    
    Just as before, we use the node's id to reference it. One thing that you 
    probably notice is the added 'Checkbox' appended to the end of our show id. 
    It's important to note that when a aui input is created of type checkbox, 
    'Checkbox' is automatically appended to the end of the id. Now that we have
    our checkbox variable attached to our node, we can create the function to
    hide our silly phrase generator.
    
3. Add this function just below our buttonSubmit.on('click'... function:

        checkbox.on('change', function(event){

        });

    We've created a function that listens for an 'onChange' event on the
    checkbox node. If there is any change in the state of the checkbox node,
    this function will be triggered. Now we can setup the conditions that we
    want for the checkbox. Seeing as there are two states to a checkbox: checked
    and unchecked, we will naturally have two conditions. First we will need a
    way to check what the state of the checkbox is. In order to do that we'll
    use the attr() method to check the value of the checkbox's `checked`
    attribute. We'll declare a private variable to determine if the checkbox is
    checked.
    
4. Declare the checked variable at the top of the checkbox function:

        var checked = checkbox.attr('checked');
    
    We've set the checked variable equal to the `checked` attribute of our
    checkbox node. The `checked` attribute holds a boolean value depending on
    the state of the checkbox: true for checked and false for unchecked. Now we
    can write our conditional statements to test for these two states.
    
5. Add the following conditional statements to the checkbox function:

        if(checked){
    
        }
        else if(!checked){
    
        }
    
    We have our two conditionals written: one that checks if the checked
    attribute is true, and one that checks if it is false. The `!`(not) operator
    placed before the checked condition in the elseif statement test for if the
    checked attribute is not true, i.e false. With our conditional statements 
    set up we can now add the functionality. We'll use the show() and hide() 
    methods to show and hide our nodes, depending on the state of the checkbox.
    First we need to determine what nodes we need to show and hide. The main
    element we need to hide is the silly phrase generator form. As of right now
    there is no variable created for it, so we'll go ahead and do that now. if 
    we were to try to hide the form element itself, the input fields would still
    appear. Instead, we'll need to hide the fieldset element that holds the
    input fields and validator tags. First we'll need to add an id to the
    fieldset element so we can grab the node in our conditions.

6. Add the id `silly` to the `<fieldset>` tag that holds our silly phrase form:

        <aui:fieldset id="silly" style="display:none;">

    In addition to the id, we've also given the fieldset a display style of none.
    We'll go over this in a little bit. Now that we have our id, we need to add 
    a variable for the node.
    
7. Declare the following variable below our checkbox variable:

        var silly = A.one("#<portlet:namespace/>silly");
    
    Now that we have our fieldset element assigned to a variable, we can use it
    to hide and show our silly phrase form. Before we go back to the conditional
    statement, we'll need to add some styles to the other elements we want to
    hide. We've added a display style of none to the silly fieldset because we 
    want to have the form hidden by default, until the checkbox is checked to 
    show it. Likewise, we will need to add the same styling to our message 
    `<div>`, title `<p>`, and generate `<aui:button>` for our silly phrase form. 
    After you've added the styles to those elements, you can return back to the 
    conditional statement. At this point it's pretty straight forward. If the 
    checkbox is checked we want to show our silly phrase form, and if it's 
    unchecked we want to hide the form.
    
8. Your conditional statement should look like the following:

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
    
We are now able to hide and show our form! Next we'll add a informational 
tooltip to explain what the silly phrase generator is exactly.
    
### Implementing Alloy's Tooltip Module

The first thing we'll do is write the script for the `aui-tooltip` module.

1. Add this script just below our `aui-char-counter` script:

        <aui:script>
        YUI().use(
          'aui-tooltip',
          function(Y) {
            new Y.Tooltip(
              {
                trigger:
                position:
                cssClass:
                opacity:
                visible:
              }
            ).render();
          }
        );
        </aui:script>

    As you can see, there are a few attributes that need to be configured for
    the tooltip module. the `trigger` attribute takes a value of the id of the
    node that triggers the tooltip to popup on mouse over. Position refers to
    the position the tooltip pops up relative to the trigger node. cssClass
    refers to the styling for the tooltip. Opacity refers to the opacity of the
    tooltip. Finally, visible refers to whether or not the tooltip is visible by
    default. Before we configure the attributes though, we need to create the 
    node for the trigger. If you take a look at the Control Panel in Liferay
    Portal you can see the tooltips in action. Next to each link is a question
    mark icon that displays a tooltip on mouse over. We'll use the same icon for
    our tooltip. We'll use `<liferay-ui:icon>` tag to use the same icon that
    Liferay uses. Since the `aui-tooltip` module requires an id for the trigger
    attribute we'll need to nest the `<liferay-ui:icon>` tag inside of a `<span>`
    tag.
    
2. Add this `<span>` element below our checkbox element:

        <span id="help" title="Check the box to create a silly phrase or uncheck it to hide the form. Fill out the fields below with the correct parts of speech to generate a unique and silly phrase."><liferay-ui:icon image="help" message=""/></span>
    
    In addition to the id attribute we've also added a title attribute to the 
    `<span>` tag. The tooltip module uses the value of the title attribute of 
    the trigger node for the tooltip text. The `<liferay-ui:icon>` has been 
    nested inside of the `<span>` tag. It's image attribute has been set to help, 
    which is the name of the icon we want to use. 
    
    ---

    ![Note](../../images/tip-pen-paper.png) **Note:** You can find a full list
    of the available icons in your 
    `/webapps/ROOT/html/themes/classic/images/common/` directory. 

    ---
    
    It's message attribute has been set to empty, so that no text is displayed 
    on mouse over of the icon; we want the AUI tooltip's text to display on 
    mouse over and the two would conflict. Now we can go ahead and configure the
    tooltip.
    
3. configure the tooltip's attributes to the following values:

        trigger: '#help',
        position: 'right',
        cssClass: 'tooltip-help',
        opacity: 1,
        visible: false
    
    The trigger attribute has been set to our `<span>` element. We've configured
    the tooltip to popup on the right side of our help icon, set the opacity to
    fully opaque, and configured the tooltip to be hidden by default. If you
    redeploy the guestbook portlet, you will see your new tooltip is fully
    functional. However, right now the tooltip is below our checkbox. We want
    the tooltip to be on the same line as the checkbox. In order to do that
    we'll add some styling to our checkbox and tooltip, and organize them inside
    of `<divs>`.
    
4. Update your checkbox and tooltip to reflect the code below:

        <div>
            <div style="float:left;"><aui:input id="show" type="checkbox" name="include" label="Show silly phrase generator" checked="false"/></div>
            <div style="float:left; margin-left:10px;"><span id="help" title="Check the box to create a silly phrase or uncheck it to hide the form. Fill out the fields below with the correct parts of speech to generate a unique and silly phrase."><liferay-ui:icon image="help" message=""/></span></div>
        </div>
    
    What we've done is placed both the checkbox and tooltip inside of their own
    `<div>`s and set them both to float left inside of the larger `<div>`. The
    tooltip has been given an additional 10 pixel margin from the left, so that
    it's `<div>` will be on the right of the checkbox label. With this new
    organization and styles, our silly phrase generator is now complete! Give
    yourself a pat on the back. Your finished form should look like this:
    
![Figure 2: Go make some silly phrases.](../../images/guestbook-silly-phrase-finished.png)
    
With the added silly phrase generator code your `edit_entry.jsp` should look 
like this:
    
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
    
    <%
    long entryId = ParamUtil.getLong(renderRequest, "entryId");

    Entry entry = null;

    if (entryId > 0) {
	
	    entry = EntryLocalServiceUtil.getEntry(entryId);
	
    }

    %>
    
    <aui:form action="<%= addEntryURL %>" name="<portlet:namespace />fm">

            <aui:fieldset>
            	<aui:model-context bean="<%= entry %>" model="<%= Entry.class %>" />
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
                <aui:input name="entryId" type="hidden" />
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

Congrats! Your silly phrase generator is complete, and now you have a basic 
understanding of how to traverse the DOM using AlloyUI.

## Next Steps

Add Link for JSF portlets here
