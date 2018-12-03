# Blogs Entry Display Screenlet for Android [](id=blogs-entry-display-screenlet-for-android)

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

Blogs Entry Display Screenlet displays a single blog entry. Image Display 
Screenlet renders any header image the blogs entry may have. 

## JSON Services Used [](id=json-services-used)

Screenlets in Liferay Screens call JSON web services in the portal. This 
Screenlet calls the following services and methods.

| Service | Method | Notes |
| ------- | ------ | ----- |
| `ScreensassetentryService` (Screens compatibility plugin) | `getAssetEntry` | With `entryId` |
| `ScreensassetentryService` (Screens compatibility plugin) | `getAssetEntry` | With `classPK` and `className` |
| `ScreensassetentryService` (Screens compatibility plugin) | `getAssetEntries` | With `entryQuery` |
| `ScreensassetentryService` (Screens compatibility plugin) | `getAssetEntries` | With `companyId`, `groupId`, and `portletItemName` |

## Module [](id=module)

- None

## Views [](id=views)

- Default

The Default View uses different components to show a blogs entry (`BlogsEntry`). 
For example, it uses an Android `TextView` to show the blog's text, and 
[User Portrait Screenlet](/develop/reference/-/knowledge_base/7-1/userportraitscreenlet-for-android) 
to show the profile picture of the Liferay user who posted it. Note that other 
custom Views may use different components. 

![Figure 1: Blogs Entry Display Screenlet using the Default View.](../../../images/screens-android-blogsentrydisplay.png)

## Offline [](id=offline)

This Screenlet supports offline mode so it can function without a network 
connection. For more information on how offline mode works, see the 
[tutorial on its architecture](/develop/tutorials/-/knowledge_base/7-1/architecture-of-offline-mode-in-liferay-screens). 
Here are the offline mode policies that you can use with this Screenlet: 

| Policy | What happens | When to use |
|--------|--------------|-------------|
| `REMOTE_ONLY` | The Screenlet loads the data from the Liferay instance. If a connection issue occurs, the Screenlet uses the listener to notify the developer about the error. If the Screenlet successfully loads the data, it stores it in the local cache for later use. | Use this policy when you always need to show updated data, and show nothing when there's no connection. |
| `CACHE_ONLY` | The Screenlet loads the data from the local cache. If the data isn't there, the Screenlet uses the listener to notify the developer about the error. | Use this policy when you always need to show local data, without retrieving remote data under any circumstance. |
| `REMOTE_FIRST` | The Screenlet loads the data from the Liferay instance. If this succeeds, the Screenlet shows the data to the user and stores it in the local cache for later use. If a connection issue occurs, the Screenlet retrieves the data from the local cache. If the data doesn't exist there, the Screenlet uses the listener to notify the developer about the error. | Use this policy to show the most recent version of the data when connected, but show an outdated version when there's no connection. |
| `CACHE_FIRST` | The Screenlet loads the data from the local cache. If the data isn't there, the Screenlet requests it from the Liferay instance and notifies the developer about any errors that occur (including connectivity errors). | Use this policy to save bandwidth and loading time in case you have local (but probably outdated) data. |

## Required Attributes [](id=required-attributes)

- `entryId`

If you don't use `entryId`, you must use both of the following attributes: 

- `className`
- `classPK`

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------|
| `layoutId` | `@layout` | The layout to use to show the View.|
| `autoLoad` | `boolean` | Whether the blog entry automatically loads when the Screenlet appears in the app's UI. The default value is `true`. |
| `entryId` | `number` | The primary key of the blog entry (`BlogsEntry`). | 
| `className` | `string` | The `BlogsEntry` object's fully qualified class name. This is `com.liferay.blogs.model.BlogsEntry`. If you don't use `entryId`, the `className` and `classPK` attributes are required to instantiate the Screenlet. |
| `classPK` | `number` | The `BlogsEntry` object's unique identifier. If you don't use `entryId`, the `className` and `classPK` attributes are required to instantiate the Screenlet. |
| `cachePolicy` | `string` | The offline mode setting. See [the Offline section](/develop/reference/-/knowledge_base/7-1/blogs-entry-display-screenlet-for-android#offline) for details. |

## Listener [](id=listener)

Because a blog entry is an asset, Blogs Entry Display Screenlet delegates its 
events to a class that implements `AssetDisplayListener`. This interface lets 
you implement the following method: 

- `onRetrieveAssetSuccess(AssetEntry assetEntry)`: Called when the Screenlet 
  successfully loads the blog entry. 

- `error(Exception e, String userAction)`: Called when an error occurs in the 
  process. The `userAction` argument distinguishes the specific action in which 
  the error occurred. 
