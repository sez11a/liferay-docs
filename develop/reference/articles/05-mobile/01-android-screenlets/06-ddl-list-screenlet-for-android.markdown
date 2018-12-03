# DDL List Screenlet for Android [](id=ddllistscreenlet-for-android)

<iframe width="560" height="315" src="https://www.youtube.com/embed/A_QEZzkuGHg" frameborder="0" allowfullscreen></iframe>

## Requirements [](id=requirements)

- Android SDK 4.1 (API Level 16) or above
- Liferay Portal 6.2 CE/EE, Liferay CE Portal 7.0/7.1, Liferay DXP
- Liferay Screens Compatibility app
  ([CE](http://www.liferay.com/marketplace/-/mp/application/54365664) or 
  [EE/DXP](http://www.liferay.com/marketplace/-/mp/application/54369726)). 
  This app is preinstalled in Liferay CE Portal 7.0/7.1 and Liferay DXP. 

## Compatibility [](id=compatibility)

- Android SDK 4.1 (API Level 16) or above

## Xamarin Requirements [](id=xamarin-requirements)

- Visual Studio 7.2
- Mono .NET framework 5.4.1.6

## Features [](id=features)

The DDL List Screenlet has the following features:

- Shows a scrollable collection of Dynamic Data List (DDL) records.
- Implements [fluent pagination](http://www.iosnomad.com/blog/2014/4/21/fluent-pagination) 
  with configurable page size.
- Allows record filtering by creator.
- Supports i18n in record values.

## JSON Services Used [](id=json-services-used)

Screenlets in Liferay Screens call JSON web services in the portal. This 
Screenlet calls the following services and methods.

| Service | Method | Notes |
| ------- | ------ | ----- |
| `ScreensddlrecordService` (Screens compatibility plugin) | `getDdlRecords` | With `ddlRecordSetId`, or `ddlRecordSetId` and `userId` |
| `ScreensddlrecordService` (Screens compatibility plugin) | `getDdlRecordsCount` |  |

## Module [](id=module)

- DDL

## Views [](id=views)

- Default
- Material

The Default View uses a standard `RecyclerView` to show the scrollable list. 
Other Views may use a different component, such as `ViewPager` or others, to 
show the items. 

![The DDL List Screenlet using the Default and Material Views.](../../../images/screens-android-ddllist.png)

## Portal Configuration [](id=portal-configuration)

DDLs and Data Types should be configured in the portal before using
DDL List Screenlet. For more details, see the Liferay User Guide sections 
[Creating Data Definitions](/discover/portal/-/knowledge_base/7-1/creating-data-definitions) 
and 
[Creating Data Lists](/discover/portal/-/knowledge_base/7-1/creating-data-lists) . 

Also, to allow remote calls without the `userId`, the Liferay Screens 
Compatibility app must be installed in your Liferay instance. You can find this 
app on 
[Liferay Marketplace](https://web.liferay.com/marketplace). 

## Offline [](id=offline)

This Screenlet supports offline mode so it can function without a network 
connection. For more information on how offline mode works, see the 
[tutorial on its architecture](/develop/tutorials/-/knowledge_base/7-1/architecture-of-offline-mode-in-liferay-screens). 

| Policy | What happens | When to use |
|--------|--------------|-------------|
| `REMOTE_ONLY` | The Screenlet loads the list from the portal. If a connection issue occurs, the Screenlet uses the listener to notify the developer about the error. If the Screenlet successfully loads the list, it stores the data in the local cache for later use. | Use this policy when you always need to show updated data, and show nothing when there's no connection. |
| `CACHE_ONLY` | The Screenlet loads the list from the local cache. If the list isn't there, the Screenlet uses the listener to notify the developer about the error. | Use this policy when you always need to show local data, without retrieving remote information under any circumstance. |
| `REMOTE_FIRST` | The Screenlet loads the list from the portal. If this succeeds, the Screenlet shows the list to the user and stores it in the local cache for later use. If a connection issue occurs, the Screenlet retrieves the list from the local cache. If the list doesn't exist there, the Screenlet uses the listener to notify the developer about the error. | Use this policy to show the most recent version of the data when connected, but show an outdated version when there's no connection. |
| `CACHE_FIRST` | The Screenlet loads the list from the local cache. If the list isn't there, the Screenlet requests it from the portal and notifies the developer about any errors that occur (including connectivity errors). | Use this policy to save bandwidth and loading time in case you have local (but probably outdated) data. |

## Required Attributes [](id=required-attributes)

- `recordSetId`
- `labelFields`

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `layoutId` | `@layout` | The layout to use to show the View. |
| `autoLoad` | `boolean` | Defines whether the list should be loaded when it's presented on the screen. The default value is `true`. |
| `recordSetId` | `number` | The ID of the DDL being called. To find your DDLs' IDs, click *Admin* &rarr; *Content* from the Dockbar. Then click *Dynamic Data Lists* on the left. Each DDL's ID is in the ID column of the table. |
| `userId` | `number` | The ID of the user to filter records on. Records aren't filtered if the `userId` is `0`. The default value is `0`. |
| `cachePolicy` | `string` | The offline mode setting. See the [Offline section](/develop/reference/-/knowledge_base/7-1/ddllistscreenlet-for-android#offline) for details. |
| `firstPageSize` | `number` | The number of items to retrieve from the server for display on the first page. The default value is `50`. |
| `pageSize` | `number` | The number of items to retrieve from the server for display on the second and subsequent pages. The default value is `25`. |
| `labelFields` | `string` | The comma-separated names of the DDL fields to show. Refer to the list's data definition to find the field names. For more information on this, see [Creating Data Definitions](/discover/portal/-/knowledge_base/7-1/creating-data-definitions). Note that the appearance of these values in your app depends on the `layoutId` set. |
| `obcClassName` | `string` | The name of the `OrderByComparator` class to use to sort the results. Omit this property if you don't want to sort the results. [Click here](https://github.com/liferay/liferay-portal/tree/master/modules/apps/forms-and-workflow/dynamic-data-lists/dynamic-data-lists-api/src/main/java/com/liferay/dynamic/data/lists/util/comparator) to see some comparator classes. Note, however, that not all of these classes can be used with `obcClassName`. You can only use comparator classes that extend `OrderByComparator<DDLRecord>`. You can also create your own comparator classes that extend `OrderByComparator<DDLRecord>`. |

## Methods [](id=methods)

| Method | Return | Explanation |
|-----------|-----------|-------------| 
| `loadPage(pageNumber)` | `void` | Starts the request to load the specified page of records. The page is shown when the response is received. |

## Listener [](id=listener)

DDL List Screenlet delegates some events to an object or a class that implements 
[the `BaseListListener` interface](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListListener.java). 
This interface lets you implement the following methods: 

- `onListPageFailed(int startRow, Exception e)`: Called when the server call to 
  retrieve a page of items fails. This method's arguments include the 
  `Exception` generated when the server call fails. 

- `onListPageReceived(int startRow, int endRow, List<Record> records, int rowCount)`: 
  Called when the server call to retrieve a page of items succeeds. Note that 
  this method may be called more than once; once for each page received. Because 
  `startRow` and `endRow` change for each page, a `startRow` of `0` corresponds 
  to the first item on the first page. 

- `onListItemSelected(Record records, View view)`: Called when an item is 
  selected in the list. This method's arguments include the selected list item 
  (`Record`). 

- `error(Exception e, String userAction)`: Called when an error occurs in the 
  process. The `userAction` argument distinguishes the specific action in which 
  the error occurred. 
