# Traversing the DOM with AlloyUI [](id=traversing-the-dom-with-alloyui)

In the last portion of the learning path, you learned how AlloyUI can be applied
to your application. In this section, you'll learn how you can use AlloyUI to
manipulate HTML elements in the DOM (Document Object Model). You'll explore this
subject through a fun addition to your Guestbook application: you'll
create a silly phrase generator using the `<aui:forms>` and `<aui:buttons>` that
were covered in the last section. For now, the silly phrase is generated on the
fly; you'll persist the phrase to the database in a later section.

First you'll walk through familiar territory: creating the form.

## Creating the form

<!-- The past learning paths used Liferay Developer Studio/IDE's snippets to do
most of the work. These instructions should be revised to use them. -Rich -->

1.  Open the `edit_entry.jsp` and add `<aui:form>` tags to the bottom.

    Next, you need to create the input fields for the form. If you've seen 
    the children's game *Madlibs*, then this will be familiar to you. You'll
    generate a silly phrase that will be made up of different parts of speech. As
    you did with the other input fields, you'll add `aui:validator` tags to the
    fields.  Since you'll be grabbing these input elements later, each one needs to
    be given a unique id as well.

2.  Add the input fields below to the form:

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
            
    You added an additional type attribute of `text` for each input 
    field to make them visible. The entry bean that is used in the
    guestbook form defines the bean for the `<aui:input>` fields automatically.
    Since you are not adding silly phrase form fields to the bean, you have to
    force the `type` to text so that they appear.

3.  Add an `<aui:button>` to the form to generate the silly phrase:

        <button class="btn btn-primary" id="submit">Generate</button>

<!-- The above snippet is not an <aui:button>. -Rich -->

    Now that the form is done, you need to add the `<html>` element for our 
    silly phrase to display in:

4. Add the html element just above the `<aui:form>` opening tag we just made:

        <html>
          <div id="message"></div>
        </html>

<!-- I have never seen an <html> element used outside the beginning and ending
tags of a full page. This, of course, should never be done in a portlet
application, because it's the root element of the page, and a portlet runs in a
portion of a page. Are you sure this is the proper way to do things? -Rich -->

For now an empty `<div>` has been created with a unique id. You'll append an HTML 
element that will display our silly phrase later on. Now that you have the 
foundation layer, you can add the logic to make it work.

## Adding Logic to the Silly Phrase Generator

The first thing you need to do is grab the `<aui:button>` element so that you can 
attach a click event to it:

1.  Add this script just below the `<aui-char-counter>` script you created in 
    the first part of the learning path:

        <aui:script use="event, node">
          var btnSubmit = A.one("#submit");
        </aui:script>

    You give the `use` attribute of the `<aui:script>` tag the value of the 
    `event` and `node` packages so that you have access to them for our click 
    event and input nodes later on. You then use the `A.one` selector method to grab 
    the button node by passing its `id` as the argument. Now that you have a 
    variable for the button element and the aui packages selected, you can 
    declare variables.

<!-- Here, you're using the A variable, where before you were using the Y
variable (and I added a comment asking why). We should be consistent and use the
AlloyUI constructs everywhere. -Rich -->

2.  Declare these variables just below the `btnSubmit` variable:

        var verb = A.one("#<portlet:namespace />verb");
        var adj = A.one("#<portlet:namespace />adj");
        var mam = A.one("#<portlet:namespace />ani"); 
        var adv = A.one("#<portlet:namespace />adv");
        var loc = A.one("#<portlet:namespace />loc");

    Each id for the input fields is preceeded by `<portlet:namespace/>` tag to 
    avoid namespacing issues between portlets. In addition to the input field 
    and button variables, you must declare a variable for an element to 
    display the silly phrase inside. You'll grab the `<div>` element you created 
    earlier for this:

        var container = A.one("#message");

    All variables have been declared, so now you can create the click 
    event.

3.  Add the function just below the container variable:

        btnSubmit.on('click', function(event){

	    )};    

    Next, you need to declare variables for the value of your input fields.

4. Add these variables just inside the function you just created:

        ver = (verb.get('value'));
        adje = (adj.get('value'));
        adve = (adv.get('value'));
        mama = (mam.get('value'));
        loca = (loc.get('value'));

    Next, you'll add an element to display our silly phrase. To do this, 
    you'll append a `<p>` element to the container `<div>` and give it a 
    unique id to grab in the steps to follow.

5.  Append the element just below our value variables:

        container.append('<p id="phrase"></p>');

    Now that you have the HTML element created, you must assign it a variable 
    that you can call upon later.

6.  Add the variable below the appended element:

        message= A.one("#phrase");

    All right! The framework is set; now you need to write the code for the message. 
    To do this, you'll set the HTML of our `<p>` element which you just assigned to the 
    message variable.

7.  Add this code below the message variable:

        message.html('Your silly phrase of the day is:<br>' + '"' + ver + ' your ' + adje + ' ' + mama + ' ' + adve + ' in the ' + loca + '."');
 
You use the `html()` method to set the inner HTML of the message element.

There you have it! Redeploy your application and go to the Guestbook's Add Entry
page to see the finished silly phrase generator. With the form filled out like
the one below, your silly phrase should read: "Walk your left-footed platypus
gingerly in the warehouse."

![Figure 1: You can generate some silly phrases with the silly phrase generator.](../../images/guestbook-silly-phrase.png)

Everything is in working order, but there are a few things left that you can 
do to make this better.

## Finishing Touches

The silly phrase generator is complete! Congrats! There are a few finishing 
touches that will hit it out of the park, though. First of all, you can style the 
silly phrase so that it sticks out from the rest of the form. Second, you can 
safeguard against generating the silly phrase if the entire form is not filled 
out. Next, you can add an option to hide the silly phrase generator when it's not 
being used. Finally, you'll add a tool-tip using AlloyUI's `aui-tooltip` module so 
that users know that they have the option to create a silly phrase.

### Styling the Silly Phrase

The first thing you can do is add a bit of style to the phrase, so that it
stands out from the form.

1.  Add the style code just below the message code you just wrote:

        message.setStyle('fontSize', '200%');
        message.setStyle('lineHeight', '120%');

    You style our silly phrase by grabbing its `id`, which you set to the 
    variable `message`. Then you call the `setStyle()` method on it and give 
    it two arguments: 1.) the CSS style you want to use; 2.) the value for the 
    style. In this case, you set the font size to double the original size. You've 
    also set the `lineHeight` a little wider than normal so that the words don't
    overlap when the page is resized to a smaller width. One final thing you can do
    to give the silly phrase form a bit of separation from the rest of the page
    is to add a title.

2.  Add the following `<p>` element just inside of our `<html>` tag:

        <p style="font-size:35px;line-height:120%;" id="title">Silly Phrase Generator</p>

    You've assigned the title some default styles inside the `<p>` tag.  You've 
    given it a `font-size` to make it stand out as a title, and as with the silly 
    phrase, you've given it a larger than normal line height to account for word 
    wrapping. Now you can assign it a variable so that we can access it later.
 
3.  Declare the title variable just below the container variable:

        var title = A.one("#title");

The silly phrase now stands out from the form. Next, you'll prevent the silly 
phrase from being generated if all the fields are not filled out. 

<!-- I don't believe this represents best practices. Styles should not be placed
in the code; they should go in the main.css file that's generated in the
docroot/css folder when the project is created. -Rich -->
 
### Safeguarding the Silly Phrase Generator
 
Next, you'll create an alert that notifies users that they need to fill out all 
the fields. You'll create an `if` statement to do this. First, you need a condition 
to test. You want to make sure all the fields are filled out before the silly 
phrase is generated, so you can test if any of the fields are left empty when the 
Generate button is clicked. 

1.  Create the `if` statement just below the styles:

        if (ver == '' ||adje == '' ||adve == '' ||mama == '' ||loca == '') {
				
	    }

    The if statement tests if any fields are blank, by checking if the 
    corresponding variable equals nothing (''). Each condition is separated by 
    the `||`(or) operator, meaning that if any of these conditions are true, the 
    statement is true. Now we can write what we want to happen if the condition 
    is true. Right now if the silly phrase is generated and any of the fields 
    are left blank, the phrase still shows the fields that were filled out. 
    To prevent this, you need to set the HTML for the message to nothing if the 
    condition is true.

2.  Add this code to the condition:

        message.html('');

    Now if the silly phrase is generated with any blank fields, the message is 
    blank. You need to alert the guest that they need to fill out all the 
    fields. This is done with the `alert()` function.

3.  Add the following alert to the if statement to complete the silly phrase 
    generator:

        alert('You need to fill out the entire form to generate a silly phrase');

Next, you'll create the logic to hide the phrase when the form is not being used.

<!-- All this is basic JavaScript stuff, and has no bearing on AlloyUI. Alert
boxes in particular are not best practices for working code anymore. Can this be
reimplemented using AlloyUI modules? -Rich -->

### Hiding the Silly Phrase Generator

The first thing you need to do is create a node that can have an event attached 
to it to hide and show the silly phrase generator. A check box works well for 
this, because of its two possible states: checked and unchecked. This option 
should be visible on the Guestbook form, so you'll add it there.

1.  Inside the `edit_entry.jsp`, add the following code to the form,
    just before the closing `</aui:fieldset>` tag: 

        <aui:input id="show" 
            type="checkbox" 
            name="include" 
            label="Show silly phrase" 
            checked="false"
        />

    Now that the check box is there, you can assign its node a variable.
 
2.  Declare this variable just below the title variable:

        var checkbox = A.one("#<portlet:namespace/>showCheckbox");
    
    Just as before, you use the node's `id` to reference it. One thing that you 
    probably notice is the added 'Checkbox' appended to the end of the show `id`. 
    It's important to note that when an `<aui:input>` is created of type `checkbox`, 
    'Checkbox' is automatically appended to the end of the `id`. Now that you have
    the `checkbox` variable attached to the node, you can create the function
    that hides the silly phrase generator.
 
3.  Add this function just below the `buttonSubmit.on('click'...` function:

        checkbox.on('change', function(event){

        });

    This is a function that listens for an 'onChange' event on the
    `checkbox` node. If there is any change in its state, this function is
    triggered. Now you can define the conditions for the `checkbox`. Since there are
    two states to a `checkbox` element (checked and unchecked), you'll  naturally have
    two conditions. First you need a way to check the state of the `checkbox`. 
    In order to do that you'll use the `attr()` method to check the value of the
    `checkbox`'s `checked` attribute. We'll declare a private variable to determine if
    the `checkbox` is checked.
 
4.  Declare the `checked` variable at the top of the `checkbox` function:

        var checked = checkbox.attr('checked');
    
    The checked variable is now equal to the `checked` attribute of the
    `checkbox` node. The `checked` attribute holds a boolean value depending on
    the state of the checkbox: true for `checked` and false for `unchecked`. Now
    you can write conditional statements to test for these two states.
 
5. Add the following conditional statements to the `checkbox` function:

        if(checked){
    
        }
        else if(!checked){
    
        }
    
    The two conditionals are now written: one that checks if the checked
    attribute is true, and one that checks if it is false. The `!`(not) operator
    placed before the checked condition in the `elseif` statement tests whether the
    checked attribute is not true, (false). Now you can add the functionality.
You'll use the `show()` and `hide()` methods to show and hide the nodes, depending on
the state of the `checkbox`.  First you need to determine the nodes to
show and hide. The main element to be hidden is the silly phrase generator
form. As of right now there is no variable created for it, so you must create
one.. If you were to try to hide the form element itself, the input fields
would still appear. Instead, you'll need to hide the `<aui:fieldset>` element
that holds the input fields and validator tags. First you'll add an `id` to
the `<aui:fieldset>` element so you can grab the node. 

6.  Add the id `silly` to the `<aui:fieldset>` tag that holds the silly phrase
    form:

        <aui:fieldset id="silly" style="display:none;">

    In addition to the `id`, you also gave the `<aui:fieldset>` a display style
    of `none`.  This will be explained later. Now that you have an `id`, you need
    to add a variable for the node.
 
7.  Declare the following variable below the `checkbox` variable:

        var silly = A.one("#<portlet:namespace/>silly");
    
    Now that the `<aui:fieldset>` element is assigned to a variable, you can use it
    to hide and show the silly phrase form. Before you go back to the conditional
    statement, however, you must add some styles to the other elements that
should be hidden. You added a display style of `none` to the silly
`<aui:fieldset>` because the form must be hidden until the check box is checked.
Likewise, the same styling must be added to the  message 
    `<div>`, title `<p>`, and generate `<aui:button>`. 
    After you've added the styles to those elements, you can return back to the 
    conditional statement. At this point it's pretty straightforward. If the 
    check box is checked, you want to show the silly phrase form, and if it's 
    unchecked you want to hide the form.

<!-- Never say something is straightforward, self-explanatory, common sense, or
anything else like that. See
https://github.com/liferay/liferay-docs/blob/master/guidelines/writers-guidelines.markdown#common-documentation-and-phraseology-issues
-Rich -->
 
8.  Your conditional statement should look like the following code:

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
 
You can now hide and show the form! Next, you'll add an informational 
tooltip to explain what the silly phrase generator is.
 
### Implementing Alloy's Tooltip Module

The first thing to do is write the script for the `aui-tooltip` module.

1.  Add this script just below the `aui-char-counter` script:

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

<!-- Again, you've got YUI().use and a function called Y. I suspect that it
should be AUI.use() and an A function, and we should consistently use that.
-Rich -->

    As you can see, the tooltip module has a few attributes that must be set.
    The `trigger` defines the `id` of the node that triggers the
    tooltip to pop up on mouse over. `Position` refers to the position the tooltip pops
    up relative to the trigger node. `cssClass` refers to the styling for the tooltip.
    `Opacity` refers to how transparent the tooltip should be. Finally, `visible`
    refers to whether or not the tooltip is visible by default. 

<!-- I suggest you reformat the above paragraph as definitions, like this: 

`trigger`: defines the `id` of the node that triggers the tooltip to pop up. 
`position`: defines the position of the tooltip relative to the trigger node.
(etc)

-Rich -->

    Before you define values for the attributes, you must create the node for the
    trigger. If you look at the Control Panel in Liferay Portal, you can see
    tooltips in action. Next to each link is a question mark icon that displays a
    tooltip on mouse over. You'll define the same icon, using the
    `<liferay-ui:icon>` tag to use the same icon that Liferay uses. Since the
    `aui-tooltip` module requires an `id` for the trigger attribute, you must nest
    the `<liferay-ui:icon>` tag inside a `<span>` tag.
 
2.  Add this `<span>` element below the `checkbox` element:

        <span id="help" 
            title="Check the box to create a silly phrase or uncheck it to 
            hide the form. Fill out the fields below with the correct parts of 
            speech to generate a unique and silly phrase.">

            <liferay-ui:icon image="help" message=""/>

        </span>
 
<!-- The above code was not formatted. Please format all your code so it is easy
to read. -Rich -->

    In addition to the `id` attribute, you also added a title attribute to the 
    `<span>` tag. The tooltip module uses the title attribute's value from  
    the trigger node for the tooltip text. The `<liferay-ui:icon>` has been 
    nested inside the `<span>` tag. Its image attribute has been set to the help
    icon. 

    +$$$

    **Note:** You can find a full list of the available icons in your 
    `/webapps/ROOT/html/themes/classic/images/common/` directory. 

    $$$
    
    The message attribute has been set to empty, so that no text is displayed 
    on mouse over of the icon: you want the AUI tooltip's text to display on 
    mouse over, and the two would conflict. Now you can go ahead and configure the
    tooltip.
 
3.  Set the tooltip's attributes to the following values:

        trigger: '#help',
        position: 'right',
        cssClass: 'tooltip-help',
        opacity: 1,
        visible: false
    
    The trigger attribute has been set to the `<span>` element. You've configured
    the tooltip to pop up on the right side of the help icon, set the opacity to
    fully opaque, and configured the tooltip to be hidden by default. If you
    redeploy the Guestbook portlet, you'll see your new tooltip is fully
    functional. Right now, however, the tooltip is below the check box, when it
    should be on the same line. In order to change that, you must add some styling to
    the checkbox and tooltip and organize them inside `<divs>`.
 
4.  Update your check box and tooltip to reflect the code below:

        <div>
            <div style="float:left;"><aui:input id="show" type="checkbox" name="include" label="Show silly phrase generator" checked="false"/></div>
            <div style="float:left; margin-left:10px;"><span id="help" title="Check the box to create a silly phrase or uncheck it to hide the form. Fill out the fields below with the correct parts of speech to generate a unique and silly phrase."><liferay-ui:icon image="help" message=""/></span></div>
        </div>
 
<!-- Please format the code above properly. -Rich -->

    What you've done is placed both the checkbox and tooltip inside their own
    `<div>`s and set them both to float left inside the larger `<div>`. The
    tooltip has been given an additional 10 pixel margin from the left, so that
    its `<div>` is on the right of the check box label. With this new
    organization and styles, your silly phrase generator is now complete! Give
    yourself a pat on the back. Your finished form should look like this:
 
![Figure 2: Go make some silly phrases.](../../images/guestbook-silly-phrase-finished.png)
 
<!-- You don't need to paste in the entire file, so I deleted it. It's better to
tell readers where to download the source code so they can examine it. -Rich -->

Congrats! Your silly phrase generator is complete, and now you have a basic 
understanding of how to traverse the DOM using AlloyUI.

## Next Steps

Add Link for JSF portlets here
