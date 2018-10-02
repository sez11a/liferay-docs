# Updating Folders [](id=updating-folders)

The Documents and Media API lets you 
[copy or move](/develop/tutorials/-/knowledge_base/7-1/copying-and-moving-entities) 
folders to a different location. Options for in-place folder updates, however, 
are limited. You can only update a folder's name and description. You can do 
this with the 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
method `updateFolder`: 

    updateFolder(long folderId, String name, String description, ServiceContext serviceContext)

All parameters except the description are mandatory. For a full description of 
this method and its parameters, see its 
[Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#updateFolder-long-java.lang.String-java.lang.String-com.liferay.portal.kernel.service.ServiceContext-). 

The following example comes from @product@'s 
[`EditFolderMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFolderMVCActionCommand.java) 
class. This class implements almost all the `Folder` actions that the Documents 
and Media UI supports. This class defines its own `updateFolder` method that 
contains logic to add and update folders. Note that this is the same method from 
the example in the 
[tutorial on creating folders](/develop/tutorials/-/knowledge_base/7-1/creating-folders). 
The example here, however, focuses on the folder update. 

This method uses the request to get the data it needs to add or update a folder. 
It creates a new folder if there's no existing folder (`folderId <= 0`). If 
there's an existing folder, then the `DLAppService` method `updateFolder` 
updates it: 

    protected void updateFolder(ActionRequest actionRequest) throws Exception {
            long folderId = ParamUtil.getLong(actionRequest, "folderId");

            long repositoryId = ParamUtil.getLong(actionRequest, "repositoryId");
            long parentFolderId = ParamUtil.getLong(actionRequest, "parentFolderId");
            String name = ParamUtil.getString(actionRequest, "name");
            String description = ParamUtil.getString(actionRequest, "description");

            ServiceContext serviceContext = ServiceContextFactory.getInstance(
                    DLFolder.class.getName(), actionRequest);

            if (folderId <= 0) {
                    // Add folder
            }
            else {

                    // Update folder
                    _dlAppService.updateFolder(
                            folderId, name, description, serviceContext);
            }
    }

## Related Topics [](id=related-topics)

[Creating Folders](/develop/tutorials/-/knowledge_base/7-1/creating-folders)

[Deleting Folders](/develop/tutorials/-/knowledge_base/7-1/deleting-folders)

[Copying Folders](/develop/tutorials/-/knowledge_base/7-1/copying-folders)

[Moving Folders and Files](/develop/tutorials/-/knowledge_base/7-1/moving-folders-and-files)
