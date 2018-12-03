# Sign Up Screenlet for iOS [](id=signupscreenlet-for-ios)

## Requirements [](id=requirements)

- Xcode 9.3 or above
- iOS 11 SDK
- Liferay Portal 6.2 CE/EE, Liferay CE Portal 7.0/7.1, Liferay DXP

## Compatibility [](id=compatibility)

- iOS 9 and above

## Xamarin Requirements [](id=xamarin-requirements)

- Visual Studio 7.2
- Mono .NET framework 5.4.1.6

## Features [](id=features)

The Sign Up Screenlet creates a new user in your Liferay instance: a new user of
your app can become a new user in your portal. You can also use this Screenlet
to save the credentials of the new user in their keychain. This enables auto
login for future sessions. The Screenlet also supports navigation of form fields
from the keyboard of the user's device. 

## JSON Services Used [](id=json-services-used)

Screenlets in Liferay Screens call JSON web services in the portal. This 
Screenlet calls the following services and methods.

| Service | Method | Notes | 
| ------- | ------ | ----- |
| `UserService` | `addUser` |  |

## Module [](id=module)

- Auth

## Themes [](id=themes)

- Default (`default`)
- Flat7 (`flat7`)

![The Sign Up Screenlet with the Default and Flat7 Themes.](../../../images/screens-ios-signup.png)

## Portal Configuration [](id=portal-configuration)

Sign Up Screenlet's corresponding configuration in the Liferay instance can be 
set in the Control Panel by selecting *Configuration* &rarr; *Instance 
Settings*, and then selecting the *Authentication* section. 

![The Liferay instance's authentication settings.](../../../images/screens-portal-signup.png)

For more details, see the 
[Authentication](/discover/portal/-/knowledge_base/7-1/authentication) 
section of the User Guide. 

## Anonymous Request [](id=anonymous-request)

Anonymous requests are unauthenticated requests. Authentication is needed,
however, to call the API. To allow this operation, the portal administrator
should create a specific user with minimal permissions.

## Offline [](id=offline)

This Screenlet doesn't support offline mode. It requires network connectivity.

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `anonymousApiUserName` | `string` | The user name, email address, or user ID (depending on the portal's authentication method) to use for authenticating the request. |
| `anoymousApiPassword` | `string` | The password for use in authenticating the request. |
| `companyId` | `number` | When set, authentication is done for a user in the specified company. If the value is `0`, the company specified in `LiferayServerContext` is used. |
| `autoLogin` | `boolean` | Whether the user is logged in automatically after a successful sign up. |
| `saveCredentials` | `boolean` | Sets whether or not the user's credentials and attributes are stored in the keychain after a successful log in. This attribute is ignored if `autologin` is disabled. |

## Delegate [](id=delegate)

The Sign Up Screenlet delegates some events to an object that conforms to the 
`SignUpScreenletDelegate` protocol. If the `autologin` attribute is enabled, 
login events are delegated to an object conforming to the 
`LoginScreenletDelegate` protocol. Refer to the [`LoginScreenlet` documentation](LoginScreenlet.md) 
for more details.

The `SignUpScreenletDelegate` protocol lets you implement the following methods:

- `- screenlet:onSignUpResponseUserAttributes:`: Called when sign up 
  successfully completes. The user attributes are passed as a dictionary of keys 
  (`String` or `NSStrings`) and values (`AnyObject` or `NSObject`). The 
  supported keys are the same as the attributes in the 
  [portal's `User` entity](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/User.html).

- `- screenlet:onSignUpError:`: Called when an error occurs in the process. The 
  `NSError` object describes the error.
