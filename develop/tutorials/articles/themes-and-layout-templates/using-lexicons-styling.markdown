#Using Lexicons Framework for your Portal's UI

Lexicon is Liferay's Experience Language, which provides markup for your 
elements and components in Portal. Lexicon is an extension of Bootstrap's 
existing framework. When you create a new theme based on Liferay's `_styled` 
theme it will inherit Lexicon's base styles. Once the theme is created, you can 
use the markup to create your application's UI.

As an example, take a look at Lexicon's [Form Elements](http://liferay.github.io/lexicon/content/form-elements/). 

1. Add the following markup to create a form using Lexicon:

        <form>
            <div class="form-group">
                <label>Default text input</label>
                <input class="form-control" placeholder="Placeholder" type="text">
            </div>

            <div class="form-group">
                <label>Address</label>
                <input class="form-control" placeholder="1428 Elm Street" type="text">
            </div>

            <div class="form-group">
                <label>Feedback</label>
                <textarea class="form-control"></textarea>
            </div>
        </form>

The form should look like the following figure:

![Figure 1: Lexicon provides a robust solution for your UI needs.](../../images/form.png)

As you can see, Lexicon provides a robust framework for you to use to quickly 
and easily create a consistent UI for your Portal and applications, freeing you 
up to concentrate on other areas. This tutorial merely skims the surface of what 
Lexicon has to offer. You can view all of the options that are available to you 
on the [Lexicon](http://liferay.github.io/lexicon/) website.

#Related Topics

[Dynamic Applications](/develop/tutorials/-/knowledge_base/7-0/dynamic-applications)

[Creating a Portlet Application](/develop/tutorials/-/knowledge_base/7-0/creating-a-portlet-application)

