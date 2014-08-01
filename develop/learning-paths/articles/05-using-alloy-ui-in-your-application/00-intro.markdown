# Using Alloy UI in your Application

When it comes to your application's UI components, AlloyUI is hands down the 
best choice. AlloyUI provides an easy to use framework with a uniform API, 
allowing you to keep a consistent look and feel throughout your application.

Before you submit a form, it is very important that all of the fields are filled
out the way you intended. In order to make that happen, you need to have some
form of validation to check your form before it is submitted. If you've followed 
the learning paths that preceeded, You learned how to get your application up 
and running, provided a database to hold all of your data, and implemented 
security using Liferay's permission system. In this learning path you'll learn 
how to use AlloyUI to implement form validation for your application.

AlloyUI gives you skinnable, scalable UI components, so you can provide a 
consistent look and feel for your application. It's a framework containing 
JavaScript extensions to Yahoo UI (YUI) that leverages all of YUI's modules and 
adds its own components to help you build terrific UIs.

A lot of people ask, why they should invest their time on AlloyUI? Or why not 
just use jQuery with their gigantic ecosystem of plugins? The thing is, DOM 
manipulation is just the tip of the iceberg when you are talking about modern 
and highly scalable applications. You'll probably need another library for 
templating (like Mustache/Handlebars), another for modular loading 
(like RequireJS/HeadJS), another for MVC structuring (like Backbone/Ember), 
another for UI components (like jQueryUI/ExtJS) and so on.

Well, AlloyUI comes with all of those things together. So there's no mess 
between different libraries, just a uniform API that makes your life easier. 
It's mantained by highly qualified engineers of Liferay, it's built on top of 
YUI3 (an awesome project made by Yahoo!) and uses Bootstrap 
(another cool project made by Twitter).

One of the most common ways to store data in an application is to use a
database. If you've followed the introductory learning path, you learned how to
write a Liferay application using only the simple API for portlet preferences.
While that method allows you to create some simple applications, it doesn't
scale well, and the amount of data you can store is limited. This learning path
picks up where that one left off: here, you'll learn how to turn the Guestbook
into a full-fledged, database-driven application. 

![The data-driven guestbook can store and display entries for many different guestbooks.](../../images/data-driven-guestbook-1.png)

