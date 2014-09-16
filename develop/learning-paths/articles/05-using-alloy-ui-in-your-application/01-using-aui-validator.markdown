# Using AlloyUI to Validate Forms in Your Application [](id=using-alloyui-to-validate-forms-in-your-application)

In the previous learning paths, you created a form for the user to fill out
to populate the guestbook. This serves as a good starting point for what you'll
do next. 

You probably noticed that you validated the entities on the back end, but did
not provide front end validation in the form itself. Before users submit your
form, you want to make sure they have filled it out exactly as you had
intended. You need to let them know which fields are required, if there is a
limit on the number of characters, or if they left any fields empty. AlloyUI
makes form validation a straightforward and easy process.

You'll learn how to do this in two steps: 1) using the AlloyUI validator tag
and 2) using the AlloyUI Character Counter module. The first step is to use 
the AlloyUI validator tag.

## Using the AUI:Validator Tag

Most of the work is already done at this point. You just need to modify the form
input fields a bit.

1.  Open `edit_entry.jsp`.

    To keep things simple, replace the contents between the `<aui:fieldset>` and
    `<aui:fieldset/>` tags with the following code:
    
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

    The only difference in this code is the presence of the `<aui:validator>`
    tag inside the `<aui:input>` tags. This tag is a quick and easy solution to
    validating your fields. The first thing to note is that the `<aui:validator/>`
    tag is placed inside the `<aui:input>` tags for the field you wish to
    validate. The next step is to give the `name` attribute a value of the type of
    validation you want to use on the field. All the fields are marked with a
    `required` attribute. For the email field, you've
    added a `email` validation in order to assure that a legitimate email is
    entered. 

    The message field has been modified into a textarea input field to give your
    users more space. It has been given a `style` attribute with a `resize:none`
    value to disable resizing for the field. The textarea has been marked as a
    required field like the others. The last thing to note is the `errormessage`
    attribute in the message field. The `errormessage` attribute replaces the
    default error message. In your code, you replaced the default "this field is
    required" message field with a custom "Leave a message please" message.

2.  Save the changes you made and redeploy your app.

3.  Add a new entry to a guestbook and save it with all the fields left empty.
    
    All the default error messages, along with your custom error message are
    displayed. You'll also notice that all the input fields now have an added
    "Required" label. Your add entry page should reflect the figure below: 

![Figure 1: With the input fields left empty and the form submitted your form should look like this one.](../../images/guestbook-form-validation.png)

Now that you've added validation to your form using AUI's validator tag,
you can do something a little more advanced: keep everyone's message to a
reasonable length.

## Using the AUI Character Counter Module

AUI modules are small, powerful building blocks of UI functionality. There are
many different modules to choose from, depending on the particular problem you
need to solve. In this case, you want to limit the amount of characters users
can type for their messages. You can do this with the AUI Validator tag, but then
users must know how many characters they have typed. As you can guess, this is
not a good solution. A much better option is the `aui-char-counter` module:
not only does it allow you to limit the amount of characters to a number you
specify, it also is capable of notifying the user how may characters are left.
Let's get started. 

1.  Open `edit_entry.jsp` and add `<aui:script>` tags just above the 
    `<%` opening tag for `long entryId = ...`. We'll be placing all 
    code inside these tags.

    The first thing you need to do is load the `aui-char-counter` module.

2.  Inside the `<aui:script>` tag, add the code below:

        YUI().use(
          'aui-char-counter',
          function(Y) {
  
          }
        );
 
<!-- Why are you using YUI().use() instead of AUI().use()? Based on the recent
announcement
(http://yahooeng.tumblr.com/post/96098168666/important-announcement-regarding-yui),
I think we should always use the AUI namespace wherever possible, in case our UI
team decides to either fork YUI or switch to something else. -Rich -->

    Now that you've loaded the character counter module, you need to create a new
    instance of the character counter to use.
 
3.  Add the code below inside the `function(Y)` brackets to create an instance 
    of the character counter:

        new Y.CharCounter(
          {
           
          }
        );

    Now that you've created an instance of the module you loaded, you'll next 
    configure the character counter by setting values for the counter's
    attributes.
 
4.  Add the attributes below inside the character counter instance you just
    created:

        counter: '#counter',
        input: '#<portlet:namespace />message',
        maxLength: 70
    
    Now you have a fully functioning character counter. The values above set the
    counter to display in the element with the id `counter`. The input attribute 
    is set to the message input element you created with the `message` id. To 
    ensure that there are no naming conflicts with other elements or existing 
    portlets on the page, a <portlet:namespace/> tag is added before the 
    `message` id. Finally, the number of characters allowed in the input field 
    are limited to 70.
    
    Don't be so fast to redeploy just yet though. Although, you've configured
    the character counter module, you still have one last step to make your effort
    worth it: create an element to display the counter. You set the counter
    attribute of the character counter to a element with the id `counter`, but
    you don't currently have any elements with that id, so you'll need to create
    one.
 
5.  Create a `<span>` element just above the closing `</aui:fieldset>` tag: 

        <span id="counter"></span> character(s) remaining
   
    There you have it! Now you can redeploy and test out the guestbook entry
    form. You'll now see your fancy new character counter keeps track of how many
    characters you have left in your message. To make your form a little more 
    visually appealing, you'll nest the `<span>` tag inside a div and style it,  
    so that the counter text is not so far away from our message input field.
 
6.  Nest your `<span>` tag in between two `<div>` tags so that your code looks
    like this:

        <div style="margin-top: -30px">
            <span id="counter"></span> character(s) remaining
        </div>
 
With updated visuals, your form should look like the one below:
 
![Figure 2: With your new character counter, your form should look like this one.](../../images/guestbook-char-counter.png)

With all the additions, here's what your `edit_entry.jsp` should look like:
    
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
            </aui:fieldset>

            <aui:button-row>

			    <aui:button type="submit"></aui:button>
			    <aui:button type="cancel" onClick="<%= viewURL %>"></aui:button>

            </aui:button-row>
    </aui:form>

Your front-end form validation is done! Save your changes and deploy your
application. 

## Next Steps

[Traversing the Dom with AlloyUI](/develop/learning-paths/-/knowledge_base/traversing-the-dom-with-alloyui)
