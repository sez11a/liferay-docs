# Migrating Documents and Media Thumbnails to Adaptive Media [](id=migrating-documents-and-media-thumbnails-to-adaptive-media)

Adaptive Media images can greatly improve the user experience across all devices. 
You can provide this same experience in Documents and Media by migrating 
existing Documents and Media thumbnails to adaptive images. 

This tutorial walks you through the process of migrating your Documents and 
Media thumbnails to adaptive images.

Go ahead and get started by creating the replacement image resolutions next. 

## Adding the Replacement Image Resolutions [](id=adding-the-replacement-image-resolutions)

To migrate the existing Documents and Media thumbnails, the Portal Administrator 
must add new Image Resolutions with maximum height and maximum width values that 
are the same as the values specified in the following portal properties: 

    dl.file.entry.thumbnail.max.height

    dl.file.entry.thumbnail.max.width

    dl.file.entry.thumbnail.custom1.max.height

    dl.file.entry.thumbnail.custom1.max.width

    dl.file.entry.thumbnail.custom2.max.height

    dl.file.entry.thumbnail.custom2.max.width

+$$$

**Note:** Keep in mind that not all the properties might be enabled. The Portal 
Administrator only needs to create Image Resolutions for the properties that are 
enabled.

$$$

Follow the instructions found in the [Adding Image Resolutions](/discover/portal/-/knowledge_base/7-0/publishing-files#adding-image-resolutions) 
section of the Adaptive Media User Guide to create the new Image Resolutions.

Next you can deploy the Document Library Thumbnails module.

## Deploying the Document Library Thumbnails Module [](id=deploying-the-document-library-thumbnails-module)

Once the new image resolutions are added, you can convert the Documents and 
Media thumbnails into Adaptive Media images. First, you must deploy the module [`adaptive-media-document-library-thumbnails` module](https://github.com/liferay/com-liferay-adaptive-media/tree/master/adaptive-media-document-library-thumbnails). 
This module redirects the Documents and Media thumbnails to use Adaptive Media 
images instead. **Once this module is deployed, all the thumbnails in Documents 
and Media will no longer be displayed until the thumbnails are migrated to 
Adaptive Media.** Deploying the module does not delete any data itself. To 
revert back to using Documents and Media thumbnails, simply undeploy the  
`adaptive-media-document-library-thumbnails` module. 

Next, the Portal Administrator needs to create Adaptive Media images for the 
image thumbnails. 

## Creating the Adaptive Media Images [](id=creating-the-adaptive-media-images)

There are two different approaches you can take when creating the Adaptive Media 
images:

- Adapt the images for the thumbnail image resolution

- Execute a migrate process that reuses the existing thumbnails

The first approach is covered in more detail in the 
[Generating Missing Image Resolutions](/discover/portal/-/knowledge_base/7-0/publishing-files#generating-missing-image-resolutions) 
section of the Adaptive Media User Guide, however this process requires that all 
the existing thumbnails be scaled to the image resolution values, and can take 
some time depending on the number of images. This approach is only recommended 
when there isn’t a large number of thumbnails to process, or if you prefer to 
generate your images from scratch.

The second approach reuses the existing thumbnails, so it’s better performance 
wise because it avoids the expensive scaling operation. This approach is, in 
general, the recommended way to convert your thumbnail images to adaptive images. 
The steps to run this upgrade are described next.

### Running the Migration Process [](id=running-the-migration-process)

The migration process is available in the 
`adaptive-media-document-library-thumbnails` module as a Gogo console set of 
commands that the administrator must execute. You can learn more about using the 
Gogo console in the [Felix Gogo Shell](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell) 
tutorial.

Follow these steps to migrate your thumbnails:

1.  From the GoGo console run the `thumbnails:check` command. This will list, 
    for each company, how many thumbnails are pending migration and have not 
    been deleted. The count returned is only updated after `thumbnails:cleanUp` 
    is run.

2.  Next, run the `thumbnails:migrate` command. This will execute the migration 
    process itself. Depending on the number of images this command might take a 
    while to finish.

3.  Finally, once all thumbnails are migrated successfully, run the 
    `thumbnails:cleanUp` command. **This removes all Documents and Media 
    thumbnails** for images. This operation should only be done after running 
    the migrate command and ensuring that the migration ran successfully and no 
    images are pending upgrade.
    
Now you know how to migrate your Documents and Media thumbnails to adaptive 
images!

## Related Topics [](id=related-topics)

<!-- Update link below with right path when User Guide is completed -->
[Adaptive Media User Guide](/discover/portal/-/knowledge_base/7-0/publishing-files)

[Item Selector Tutorials](/develop/tutorials/-/knowledge_base/7-0/item-selector)
