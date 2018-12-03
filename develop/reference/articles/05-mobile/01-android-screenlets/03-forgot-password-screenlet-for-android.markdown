# Forgot Password Screenlet for Android [](id=forgotpasswordscreenlet-for-android)

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

The Forgot Password Screenlet sends an email to registered users with their 
new passwords or password reset links, depending on the server configuration. 
The available authentication methods are

- Email address
- Screen name
- User id

## JSON Services Used [](id=json-services-used)

Screenlets in Liferay Screens call JSON web services in the portal. This 
Screenlet calls the following services and methods.

| Service | Method | Notes |
| ------- | ------ | ----- |
| `UserService` | `sendPasswordByEmailAddress` |  |
| `UserService` | `sendPasswordByUserId` |  |
| `UserService` | `sendPasswordByScreenName` |  |

## Module [](id=module)

- Auth

## Views [](id=views)

- Default
- Material

![The Forgot Password Screenlet with the Default (left) and Material (right) Viewsets.](../../../images/screens-android-forgotpwd.png)

## Portal Configuration [](id=portal-configuration)

To use Forgot Password Screenlet, the portal must be configured to allow users 
to request new passwords. The below sections show you how to do this. 

### Authentication Method [](id=authentication-method)

The authentication method configured in the portal can be different from the one 
used by this Screenlet. For example, it's *perfectly fine* to use `screenName` 
for sign in authentication, but allow users to recover their password using the 
`email` authentication method.

### Password Reset [](id=password-reset)

You can set the Liferay instance's corresponding password reset options in the 
Control Panel by selecting *Configuration* &rarr; *Instance Settings*, and then 
selecting the *Authentication* section. The Screenlet's password functionality 
depends on the authentication settings in the portal: 

![Checkboxes for the password recovery features in your Liferay instance.](../../../images/screens-password-reset.png)

If these options are both unchecked, password recovery is disabled. If both
options are checked, an email containing a password reset link is sent when a
user requests it. If only the first option is checked, an email containing a new
password is sent when a user requests it.

For more details, see the 
[Authentication](/discover/portal/-/knowledge_base/7-1/authentication) 
section of the User Guide. 

### Anonymous Request [](id=anonymous-request)

An anonymous request can be made without the user being logged in. However, 
authentication is needed to call the API. To allow this operation, the portal
administrator should create a specific user with minimal permissions. 

## Offline [](id=offline)

This Screenlet doesn't support offline mode. It requires network connectivity.

## Required Attributes [](id=required-attributes)

- `anonymousApiUserName`
- `anonymousApiPassword`

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `layoutId` | `@layout` | The layout used to show the View. |
| `anonymousApiUserName` | `string` | The user name, email address, or `userId` to use for authenticating the request. This depends on the portal's authentication settings. |
| `anonymousApiPassword` | `string` | The password to use to authenticate the request. |
| `companyId` | `number` | When set, a user within the specified company is authenticated. If the value is set to `0`, the company specified in `LiferayServerContext` is used. |
| `basicAuthMethod` | `string` | The authentication method presented to the user. This can be `email`, `screenName`, or `userId`. The default value is `email`. |

## Listener [](id=listener)

The Forgot Password Screenlet delegates some events to an object that implements 
the `ForgotPasswordListener` interface. This interface lets you implement the 
following methods:

- `onForgotPasswordRequestSuccess(boolean passwordSent)`: Called when a password 
reset email is successfully sent. The boolean parameter determines whether the 
email contains the new password or a password reset link.

- `onForgotPasswordRequestFailure(Exception e)`: Called when an error occurs in 
the process.
