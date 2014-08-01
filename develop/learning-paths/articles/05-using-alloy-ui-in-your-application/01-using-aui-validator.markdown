# Using AlloyUI to Validate Forms in Your Application

In the previous learning paths, you setup a basic form for the user to fill out
to populate the guestbook. It serves as a good starting point and does exactly 
what it needs to do. However, one thing that has not been addressed up to this 
point is form validation. Before submitting your form, you want to make sure
that the user has filled it out exactly as you had intended. You need to let
them know which fields are required, if there is a limit on the number of
characters, if they left any fields empty etc. AlloyUI makes form validation a
straight forward and easy process.

We'll break down this process into two sections: using the AlloyUI validator tag
and using the AlloyUI Character Counter module. Go ahead and get started with
the AlloyUI validator tag.

## Using the AUI:Validator Tag

This learning path starts with the development environment and application you 
created in the previous learning paths. If you haven't followed those paths, 
you'll need to create that application first, or download it from [here](https://github.com/liferay/liferay-docs/tree/master/develop/learning-paths/code/02-writing-a-data-driven-application/sb-sdk). 
From here on, we'll assume you have Liferay IDE and your application all set up 
and ready to go. 

Most of the work is already done for us at this point. We just need to modify
the form input fields a bit.

1. Open the  `edit_entry.jsp`.

    To keep things simple, replace the contents between the `<aui:fieldset>` and
    `<aui:fieldset/>` tags with the following:
    
    ```
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
    
    ```

    The AUI validator tag is a very quick and easy solution to validating your 
    fields. The first thing to note is that the `<aui:validator/>` tag is placed 
    inside of the `<aui:input>` tags of the field you wish to validate. The next 
    step is to give the `name` attribute a value of the type of validation you 
    want to use on the field. All of the fields are marked with a `required` 
    validation. In order to assure that a legit email is entered, we've added a 
    `email` validation to the email field. The message field has been modified 
    and configured into a textarea input field to allow for a larger input 
    field. It has been given a `style` attribute with a `resize:none` value to 
    disable resizing for the field. The textarea has been marked as a required 
    field like the others. The last thing to note is the added `errormessage` 
    attribute to the message field. The `errormessage` attribute replaces the 
    default error message for the type of validation it is included with. In our 
    code, we replaced the default "this field is required" message for the 
    message field with our custom "Leave a message please" message.

2. Save the changes you made and redeploy your app.

3. Add a new entry to a guestbook and save it with all the fields left empty.
    
    You should see all of your hard work come to fruition. All of the default
    error messages, along with our custom error message are displayed. You will
    also notice that all of the input fields now have a added "Required" label.
    Your add entry page should reflect the figure below: 

![Figure 1: With the input fields left empty and the form submitted your form should look like this one.](../../images/guestbook-form-validation.png)

Now that we have added some validation to our form using AUI's validator tag,
we're going to add a little more validation to our form to keep everyone's
message to a reasonable length.

## Using the AUI Character Counter Module

You've seen what you can do with the AUI Validator tag. Now you're going to
learn how to use AUI modules. They provide a much more powerful set of tools to
work with. There are many different modules to choose from, depending on the
particular problem you need to solve. In our case we want to limit the amount of 
characters a user can put for their message. We can do this with the AUI
Validator tag, but then we depend on the user to know how many characters they
have typed. The only way they could check how many characters are left is to
guess and check to see if they get an error message. As you can guess, this is
not a good route to go. A much better option is the `aui-char-counter` module;
not only does it allow us to limit the amount of characters to a number we
specify, it also is capable of notifying the user how may characters are left. 
Let's get started. 

1. Open your `edit_entry.jsp` and add `<aui:script>` tags just beneath the 
`</portlet:actionURL>` end tag. We'll be placing all of our code inside these
tags.

    The first thing we need to do is load the `aui-char-counter` module.

2. Inside of the `<aui:script>` tag, add the code below to the top:

    ```
    YUI().use(
      'aui-char-counter',
      function(Y) {
  
      }
    );
    
    ```    
    Now that we've loaded the character counter module, we need to create a new
    instance of the character counter to use.
    
3. Add the code below inside of the `function(Y)` brackets to create an instance 
of the character counter:

    ```
    new Y.CharCounter(
      {
           
      }
    );
        
    ```
    Now that we've created an instance of the module we loaded, we're going to
    configure the character counter by setting values for the counter's
    attributes.
    
4. Add the attributes below inside of the character counter instance you just
created:

    ```
    counter: '#counter',
    input: '#<portlet:namespace />message',
    maxLength: 70

    ```
    
    Now we have a fully functioning character counter. The values above set the
    counter to display in the element with the id `counter`. The input attribute 
    is set to the message input element we created with the `message` id. To 
    ensure that there are no naming conflicts with other elements or existing 
    portlets on the page, a <portlet:namespace/> tag is added before the 
    `message` id. Finally, the number of characters allowed in the input field 
    are limited to 70.
    
    Don't be so fast to redeploy just yet though. Although, we have configured
    the character counter module, we still have one last step to make our effort
    worth it: create a element to display the counter. We set the counter
    attribute of the character counter to a element with the id `counter`, but
    we don't currently have any elements with that id, so we'll need to create
    one.
    
5. Create a `<span>` element just above the closing `</aui:fieldset>` tag that
looks like the following:

    ```
    <span id="counter"></span> character(s) remaining
   
    ```
   
    There you have it! Now you can redeploy and test out the guestbook entry
    form. You'll now see your fancy new character counter keeps track of how many
    characters you have left in your message. To make our form a little more 
    visually appealing, we'll nest the `<span>` tag inside a div and style it,  
    so that the counter text is not so far away from our message input field.
    
6. Nest your `<span>` tag in between two `<div>` tags so that your code looks
like this:

   ```
    <div style="margin-top: -30px">
        <span id="counter"></span> character(s) remaining
    </div>
    
   ``` 
    With updated visuals, your form should look like the one below:
   
![Figure 2: With our new character counter your form should look like this one.](../../images/guestbook-char-counter.png)
   
    With all the additions, here's what your `edit_entry.jsp` should look like:
   
   ```
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
    
   ```
Your form validation is done! Save your changes and deploy your application. You
can rest assured that your form will be filled out as you had intended.
