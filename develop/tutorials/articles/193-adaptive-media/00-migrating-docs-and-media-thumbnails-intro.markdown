# Migrating Documents and Media Thumbnails to Adaptive Media

Users can take advantage of existing Documents and Media thumbnails as if they were adaptive images in just a few steps. To do this, the Portal Administrator must add new Image Resolutions whose maximum height and maximum width values are the same as the values specified in the following portal properties:

    dl.file.entry.thumbnail.max.height

    dl.file.entry.thumbnail.max.width

    dl.file.entry.thumbnail.custom1.max.height

    dl.file.entry.thumbnail.custom1.max.width

    dl.file.entry.thumbnail.custom2.max.height

    dl.file.entry.thumbnail.custom2.max.width

Note: Keep in mind that not all the properties might be enabled. The Portal Administrator only needs to create Image Resolutions for the properties that are enabled.

Once the new Image Resolutions are added, you can convert the Documents and Media thumbnails into Adaptive Media images. First, you must deploy the module adaptive-media-document-library-thumbnails. 

This module redirects the thumbnails to Adaptive Media, so instead of using Documents and Media thumbnails it will use Adaptive Media images. **Once this module is deployed, all the thumbnails in Documents and Media will no longer be displayed until the thumbnails are migrated to Adaptive Media.**** **Deploying the module does not delete any data itself. If you want to keep the behavior of Documents and Media thumbnails you can undeploy the  adaptive-media-document-library-thumbnails** **module.

Next, the Portal Administrator needs to create Adaptive Media images for the image thumbnails. There are two different approaches you can take:

* Adapt the images for the thumbnail image resolution

* Execute an upgrade process that reuses the existing thumbnails

The first approach is covered in more detail in the "Generating Missing Image Resolutions" section of the [User Guide](https://docs.google.com/document/d/1nAkNxzoHeEc2upPiLtRQ8lSBOvj6isXvCXc6wSaulLg/edit#heading=h.8kvvlmd852i), however this process requires that all the existing thumbnails be scaled to the image resolution values, and can take some time depending on the number of images. This approach is only recommended when there isn’t a large number of thumbnails to process, or if you prefer to generate your images from scratch.

The second approach reuses the existing thumbnails, so it’s better performance wise because it avoids the expensive scaling operation. This approach is, in general, the recommended way to convert your thumbnail images to Adaptive Media. The steps to run this upgrade are described next.

# Upgrading Documents and Media Thumbnails to Adaptive Media

The upgrade process is available in the adaptive-media-document-library-thumbnails  module as a GoGo console set of commands that the administrator must execute. You can learn more about using the GoGo console in the [Felix GoGo Shell](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell) tutorial. Follow these steps to upgrade your thumbnails:

1. From the GoGo console run the thumbnails:check command. This will list, for each company, how many thumbnails are pending migration and have not been deleted. The count returned is only updated after `thumbnails:cleanUp` is run.

2. Next, run the thumbnails:migrate command. This will execute the migration process itself. Depending on the number of images this command might take a while to finish.

3. Finally, once all thumbnails are migrated successfully, run the thumbnails:cleanUp command. **This ****will remove ****all Documents and Media thumbnails**** for images****. This operation should only be done after running the migrate command and ensuring that the migration ran successfully and no images are pending upgrade.**

