## Drools

Liferay Portal Enterprise Edition provides an implementation of a JSR-94 compliant rules engine.  This rules engine is provided as a Web Plugin and is based on the popular open source Drools project. 

In most applications, complex rule processing often takes the form of nested if-else blocks of code which can be very difficult to decipher and to maintain.  A rules engine provides a means to separate the rules or logic of an application from the remaining code.  Separating these rules allows for changes to be made to the rules without affecting the rest of the application.  A rule engine allows for a more declarative style of programming where the rules define what is happening, without describing how it is happening.  

### Installation

The Drools Web Plugin is available to Liferay Enterprise Edition customers through the customer portal.  In can also be downloaded and installed through the built-in plugin repository.  The name is `drools-web`{.western}, and you'll find it in the list of web plugins. 


### Working with the Drools Web Plugin

The Drools Web Plugin provides a rules engine implementation, but by itself it does not provide any observable changes to the portal user interface or any additional functionality.  To see the rules engine in action, you can download and install the Sample Drools Portlet.  The Sample Drools Portlet contains two rule definitions that illustrate how to leverage the rules engine in your custom code.   


#### Configuring the Sample Drools Portlet

After installing the Sample Drools Portlet, as an administrative user, log into the portal and navigate to the Control Panel.   Once in the Control Panel, add a new Web Content entry to the default liferay.com community. Before publishing the Web Content entry, tag the article with *west coast symposium*.  While still in the control panel, navigate to *My Account* and select the Address link on the right side of the screen. Enter a Canadian, Mexican, or US based address and save the record.  Navigate back to the liferay.com community and add the Sample Drools Portlet to the page.  The Web Content should be displayed in the Sample Web Content 

The default rule that is applied will display a list of assets based on the current user's address.  For example, if the current user's country is set to Canada, Mexico, or the United States, the Sample Drools Portlet will display a list of assets that have been tagged with the *west coast symposium* tag. 

The Sample Drools Portlet plugin also contains a second rule that evaluates a custom field attached to a user.

After installing the Sample Drools Portlet

#### Rules Definitions

Rule definitions are written using Drools' declarative language.  Rule files often have a .drl extension and can contain multiple rules.  To see an example of a rules definition file, access the Sample Drools Portlet