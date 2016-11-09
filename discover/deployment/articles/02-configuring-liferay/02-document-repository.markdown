# Document Repository Configuration

By default, @product@ uses a document library store option called Simple File
Store to store documents and media files on the file system (local or mounted)
of the server @product@'s running on. The store's default root directory is
`[Liferay Home]/data/document_library`. You can specify a different root
directory from within [System Settings](/discover/portal/-/knowledge_base/7-0/system-settings).
To access System Settings, open the *Menu*
(![Menu](../../images/icon-menu.png)) and navigate to *Control Panel &rarr;
Configuration &rarr; System Settings*. From *System Settings*, navigate to
*Platform* and then search for and select the entry *Simple File System Store*.
For the store's *Root dir* value, specify a path relative to the [Liferay Home](/discover/deployment/-/knowledge_base/7-0/installing-liferay-portal#liferay-home)
or an absolute path; then click the *Update* button. The document library store
switches immediately to the new root dir.

You can also use an entirely different method for storing documents and media
files with changing the value of the `dl.store.impl` property in the
`portal-ext.properties` file.

The following paragraphs describe the property file settings and the
properties related to document library stores that have been moved
from `portal-ext.properties` to OSGI configuration files.

**Simple File System Store**

Uses the file system (local or a mounted share) to store files.

Portal property to `portal-ext.properties` file: `dl.store.impl=com.liferay.portal.store.file.system.FileSystemStore`

The name of the OSGi configuration file to `osgi/configs`: `com.liferay.portal.store.file.system.configuration.FileSystemStoreConfiguration.cfg`

***OSGi configuration entries***

Property | Default | Required
---------|---------|---------
`rootDir` | `data/document_library` | `false`

**Advanced File System Store**

In addition to using the file system (local or a
mounted share) to store files, Advanced File System Store nests the files into
more directories by version for faster performance and to store more files.

Portal property to `portal-ext.properties` file: `dl.store.impl=com.liferay.portal.store.file.system.AdvancedFileSystemStore`

The name of the OSGi configuration file to `osgi/configs`: `com.liferay.portal.store.file.system.configuration.AdvancedFileSystemStoreConfiguration.cfg`

***OSGi configuration entries***

Property | Default | Required
---------|---------|---------
`rootDir` | `data/document_library` | `false`

**CMIS Store (Content Management Interoperability Services)**

Uses a system separate from Liferay to store files.

Portal property to `portal-ext.properties` file: `dl.store.impl=com.liferay.portal.store.cmis.CMISStore`

The name of the OSGi configuration file to `osgi/configs`: `com.liferay.portal.store.cmis.configuration.CMISStoreConfiguration.cfg`

***OSGi configuration entries***

Property | Default | Required
---------|---------|---------
`repositoryUrl` | `http://localhost:8080/alfresco/service/api/cmis` | `true`
`credentialsUsername` | none | `true`
`credentialsPassword` | none | `true`
`systemRootDir` | Liferay Home | `true`

**DBStore (Database Storage)**

Stores files in the @product@ database.

***OSGi configuration entries***

There is no additional OSGi configuration.

**JCRStore (Java Content Repository)**

Stores files to a JSR-170 compliant
document repository. Any JCR client can be used to access the files. The files
are stored to the server's file system by default. Optionally the
JCRStore can be configured to store files in a database.

Portal property to `portal-ext.properties` file: `dl.store.impl=com.liferay.portal.store.jcr.JCRStore`

The name of the OSGi configuration file to `osgi/configs`: `com.liferay.portal.store.jcr.configuration.JCRStoreConfiguration.cfg`

***OSGi configuration entries***

Property | Default | Required
---------|---------|---------
`initializeOnStartup` | `false`| `true`
`wrapSession` | `true` | `true`
`moveVersionLabels` | `false` | `true`
`workspaceName` | `liferay` | `true`
`nodeDocumentlibrary` | `documentlibrary` | `true`
`jackrabbitRepositoryRoot` | `data/jackrabbit` | `true`
`jackrabbitConfigFilePath` | `repository.xml` | `true`
`jackrabbitRepositoryHome` | `home` | `true`
`jackrabbitCredentialsUsername` | none | `true`
`jackrabbitCredentialsPassword` | none | `true`

**S3Store (Amazon Simple Storage)**

Uses Amazon's cloud-based storage solution.

Portal property to `portal-ext.properties` file: `dl.store.impl=com.liferay.portal.store.s3.S3Store`

The name of the OSGi configuration file to `osgi/configs`: `com.liferay.portal.store.s3.configuration.S3StoreConfiguration.cfg`

***OSGi configuration entries***

Property | Default | Required
---------|---------|---------
`accessKey` | | `false`
`secretKey` | | `false`
`s3Region` | `us-east-1` | `false`
`bucketName` | | `true`
`s3StorageClass` | STANDARD | `false`
`httpClientMaxConnections` | `50` | `false`
`cacheDirCleanUpExpunge` | `7` | `false`
`cacheDirCleanUpFrequency` | `100` | `false`

+$$$

**Note:** Please refer to the [Document Library property reference](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Document%20Library%20Portlet)
for a complete list of supported configuration options. You can configure features such
as the maximum allowed size of documents and media files, the list of allowed
file extensions, which types of files should be indexed, and more.

$$$

+$$$

**Warning:** If a database transaction rollback occurs in a Document Library
that uses a file system based store, file system changes that have occurred
since the start of the transaction won't be reversed. Inconsistencies between
Document Library files and those in the file system store can occur and may
require manual synchronization. All stores except DBStore are vulnerable to this
limitation.

$$$

## Changing the storage on an existing installation [](id=changing-storage)
If file were already uploaded to @product@, it is not enough to change the `dl.store.impl`
property in the `portal-ext.properties`. The steps to follow in this case are
the followings:
1. Open the *Menu*
(![Menu](../../images/icon-menu.png)) and navigate to *Control Panel &rarr;
 Configuration &rarr; Server Administration &rarr; Data Migration*,
2. Change the value of the `dl.store.impl` dropdown to the desired storage type,
3. Click on Execute,
4. Stop the portal application or the servlet container after the migration
process has been finished,
5. Change the `dl.store.impl` property in the `portal-ext.properties` to the
storage type which has been chosen at step #2,
6. Start the portal application or the servlet container.
