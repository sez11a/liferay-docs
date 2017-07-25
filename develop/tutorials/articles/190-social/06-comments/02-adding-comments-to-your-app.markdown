# Adding Comments to your App [](id=adding-comments-to-your-app)

Another key social feature you can add to your app is the ability to comment on 
an asset. Allowing users to leave comments and feedback on a user's post, or 
something you have created, is invaluable. It brings new life to your app, and 
lets it grow organically. You can add comments to your app by leveraging 
Liferay's asset framework. What's more, it can be implemented in just a few 
steps!

![Figure 1: Your JSP lets users comment on content in your portlet.](../../../images/social-comments-enabled.png)

In order to implement the comments feature on your custom entity, it must be [asset enabled](/develop/tutorials/-/knowledge_base/7-0/adding-updating-and-deleting-assets-for-custom-entities). 
This tutorial shows you how to add the comment feature for your application's
content. The Guestbook app from the MVC Learning Path is used as an example: 
What good are guestbook entries, if you can't reply to them! The completed 
Guestbook app code that uses this feature is on 
[Github](https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/osgi/modules/guestbook-social-features/guestbook).

Without any further ado, go ahead and get started enabling comments in your 
portlet!

You can display the comments component in your portlet's view 
or, if you've implemented 
[asset rendering](/develop/tutorials/-/knowledge_base/7-0/rendering-an-asset),
you can display it in the full content view in the Asset Publisher portlet. 

As an example, the Guestbook app's `guestbook-web` module's view entry JSP file
[`view_entry.jsp`](https://github.com/liferay/liferay-docs/develop/tutorials/code/osgi/modules/guestbook-social-features/guestbook/guestbook-web/src/main/resources/META-INF/resources/html/guestbookmvcportlet/view_entry.jsp)
shows an insult entity and this asset feature. 

In your entity's view, you can use `ParamUtil` to get the ID of the entity
from the render request. Then you can create an entity object using your
`-LocalServiceUtil` class. 

    <%
    long entryId = ParamUtil.getLong(renderRequest, "entryId");
    entry = EntryLocalServiceUtil.getEntry(entryId);
    %>

Next, you need to add the code that handles the discussion. First you'll create 
a collapsible panel for the comments, so the discussion area doesn't visually
take precedence over the content. This is done with the
`liferay-ui:panel-container` and `liferay-ui:panel` tags. Collapsible panels let
your users obscure any potentially long content on a page. Now that this panel
is in place, you need to create a URL for the discussion. This is done with the
`portlet:actionURL` tag. You also need to get the current URL from the `request`
object, so that your user can return to the JSP after adding a comment. This is
done using `PortalUtil.getCurrentURL((renderRequest))`. Last, but certainly not 
least, is the implementation of the discussion itself with the 
`liferay-ui:discussion` tag. The following block of code shows the collapsible 
panel, URLs, and the discussion:

    <liferay-ui:panel-container extended="<%=false%>"
      id="guestbookCollaborationPanelContainer" persistState="<%=true%>">
      <liferay-ui:panel collapsible="<%=true%>" extended="<%=true%>"
        id="guestbookCollaborationPanel" persistState="<%=true%>"
        title="Collaboration">

        <portlet:actionURL name="invokeTaglibDiscussion" var="discussionURL" />

        <liferay-ui:discussion className="<%=Entry.class.getName()%>"
          classPK="<%=entry.getEntryId()%>"
          formAction="<%=discussionURL%>" formName="fm2"
          ratingsEnabled="<%=true%>" redirect="<%=currentURL%>"
          userId="<%=entry.getUserId()%>" />

      </liferay-ui:panel>
    </liferay-ui:panel-container>

Awesome! Now you have a JSP that lets your users comment on content in your 
portlet.

If you haven't already connected your portlet's view to the JSP for your entity,
you can refer [here](/develop/tutorials/-/knowledge_base/7-0/relating-assets#creating-a-url-to-your-new-jsp)
to see how to connect your portlet's main view JSP to your entity's view JSP. 

Now redeploy your portlet and refresh the page so that your plugin's UI
reloads. The comments section appear at the bottom of the page.

Great! Now you know how to let users comment on content in your asset enabled 
portlets. 

Before moving on, another thing you might want to do is perform permissions 
checks to control who can access the discussion. For example, you can surround
the collapsible panel 
[`view_entry.jsp`](https://github.com/liferay/liferay-docs/develop/tutorials/code/osgi/modules/guestbook-social-features/guestbook/guestbook-web/src/main/resources/META-INF/resources/html/guestbookmvcportlet/view_entry.jsp)
with `c:if` tags that only reveal their contents to users that are signed in to
the portal:

    <c:if test="<%=themeDisplay.isSignedIn()%>">

        [Collapsible Panel Here]

    </c:if>

This is just one way of controlling access to the discussion. You could also 
perform more specific permissions checks as the Guestbook app does for the Add 
Guestbook and Permissions buttons in its 
[`guestbook_actions.jsp`](https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/osgi/modules/guestbook-social-features/guestbook/guestbook-web/src/main/resources/META-INF/resources/html/guestbookmvcportlet/guestbook_actions.jsp).
For more information on this, see the
[Adding Permissions to Resources](/develop/tutorials/-/knowledge_base/7-0/adding-permissions-to-resources) 
tutorial.

**Related Topics**

[Adding, Updating, and Deleting Assets for Custom Entities](/develop/tutorials/-/knowledge_base/7-0/adding-updating-and-deleting-assets-for-custom-entities)

[Rendering an Asset](/develop/tutorials/-/knowledge_base/7-0/rendering-an-asset)

[Using the Liferay UI Taglib](/develop/tutorials/-/knowledge_base/7-0/using-the-liferay-ui-taglib)

[Applying Social Bookmarks](/develop/tutorials/-/knowledge_base/7-0/applying-social-bookmarks)
