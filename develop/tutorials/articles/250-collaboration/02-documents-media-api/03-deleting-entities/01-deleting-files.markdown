# Deleting Files [](id=deleting-files)

Deleting files (`FileEntry` entities) via 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
is straightforward. There are two methods you can use to delete files. Click 
each method to see its Javadoc: 

-   [`deleteFileEntry(long fileEntryId)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#deleteFileEntry-long-)

-   [`deleteFileEntryByTitle(long repositoryId, long folderId, String title)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#deleteFileEntryByTitle-long-long-java.lang.String-)

These methods differ only in how they identify a file for deletion. The 
combination of the `folderId` and `title` parameters in `deleteFileEntryByTitle` 
uniquely identify a file because it's impossible for two files in the same 
folder to share a name. 

The following example comes from @product@'s 
[`EditFileEntryMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFileEntryMVCActionCommand.java) 
class. This class implements almost all the `FileEntry` actions that the 
Documents and Media UI supports. It contains its own `deleteFileEntry` method, 
which calls the `DLAppService` method `deleteFileEntry(fileEntryId)`. 

The `EditFileEntryMVCActionCommand` class's `deleteFileEntry` method gets the 
file's ID from the request. If the `moveToTrash` flag is `false`, the file 
should be deleted instead of moved to the 
[Recycle Bin](/discover/portal/-/knowledge_base/7-1/restoring-deleted-assets). 
To delete the file, the `DLAppService` method `deleteFileEntry` is called with 
the file's ID: 

    protected void deleteFileEntry(
                    ActionRequest actionRequest, boolean moveToTrash)
            throws Exception {

            long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");

            if (fileEntryId == 0) {
                return;
            }

            ...

            if (!moveToTrash) {
                    _dlAppService.deleteFileEntry(fileEntryId);

                    return;
            }

            ...
    }

Note that you don't have to support the Recycle Bin in your delete operations. 
If you want to, however, see the tutorial on 
[moving entities to the Recycle Bin](/develop/tutorials/-/knowledge_base/7-1/moving-entities-to-the-recycle-bin). 

## Related Topics [](id=related-topics)

[Moving Entities to the Recycle Bin](/develop/tutorials/-/knowledge_base/7-1/moving-entities-to-the-recycle-bin)

[Creating Files](/develop/tutorials/-/knowledge_base/7-1/creating-files)

[Updating Files](/develop/tutorials/-/knowledge_base/7-1/updating-files)

[Moving Folders and Files](/develop/tutorials/-/knowledge_base/7-1/moving-folders-and-files)
