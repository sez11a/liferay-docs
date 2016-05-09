# Service Access Policies

Service Access Policies are an additional layer of web service security, 
defining services or service methods that can be invoked remotely. For more 
detail, and information on managing Service Access Policies, see 
[the user guide article on Service Access Policies](/discover/deployment/-/knowledge_base/7-0/service-access-policies). 

There may be cases, however, where your app needs to integrate with a Liferay 
instance's Service Access Policies. For example, you may have an app that: 

- uses custom remote API authentication (tokens), and requires certain Liferay 
  services to be available for clients using the tokens. 

- requires its services be made available to guest users, with no authentication 
  necessary. 

- contains a remote service authorization layer that needs to drive access to 
  remote services based on granted privileges. 

This kind of integration can be done on two levels. 

<!-- What's the other level, besides portal? 

Two levels:
1, integration with portal package com.liferay.portal.kernel.security.service.access.policy

2, integration with osgi modules:
- com.liferay.service.access.policy.api.jar`
- com.liferay.service.access.policy.service.jar`
- com.liferay.service.access.policy.web.jar`

-->

On the portal level, the 
[package `com.liferay.portal.kernel.security.service.access.policy`](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/security/service/access/policy/package-summary.html) 
provides classes for basic access to policies. For example, you can use the 
[singleton `ServiceAccessPolicyManagerUtil`](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/security/service/access/policy/ServiceAccessPolicyManagerUtil.html) 
to obtain Service Access Policies configured in the system. You can also use the 
[`ServiceAccessPolicyThreadLocal` class](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/security/service/access/policy/ServiceAccessPolicyThreadLocal.html) 
to set and obtain Service Access Policies granted to current request thread. 

<!-- What is accessing/setting policies used for? 

There is a list of policies configured in DB. Either by portal admin manually using UI or created via API by installed applications.

1, Accessing list of policies using portal API
Non-osgi plugins can see list of configured policies. This can help remote apps/clients to choose which one to use to access the services.

Also a plugins like OAuth can during authorization step in the OAuth workflow offer list of available policies and allow user to choose which policy should be assigned to the remote application.

2. Granting a policy to current request thread

When a remote client access API, "something" needs to tell portal which policies are assigned/granted to this call. This "something" is in most cases an AuthVerifier implementation that:
1, in Sync example always assigns SYNC_POLICY
2, in OAuth plugin example assigns the policy choosed by the user in authorization


-->

Furthermore, Liferay's Service Access Policy API and implementation is provided 
via the following OSGi modules:

- `com.liferay.service.access.policy.api.jar`
- `com.liferay.service.access.policy.service.jar`
- `com.liferay.service.access.policy.web.jar`

These OSGi modules can be used to manage service access policies 
programmatically.
<!-- 
How? 

Each module publishes a list of packages and services that can be consumed by other OSGi modules. Please see some OSGi guide on how to import packages and use OSGi services.



What is meant by "manage service access policies automatically"? 

Not automatically but programmatically, that means not via UI but using API.

-->

A custom token verification module: 
<!-- What's a custom token verification module, and how does it fit in with this tutorial?

Example of OSGi module implementing a custom security token verification for remote clients authorization. Can be for example someone's JWT implementation for Liferay remote API. 

For it to work, the developer must
1, Create a way for remote applications to obtain token - out of scope of this article
- may need list of existing SAC profiles
- may need to create custom SAC profile

2, Can offer UI for users to choose a policy - out of scope of this article
- may need list of existing SAC profiles

3, Need to integrate with portal during remote API/web service call
- need to grant the associated policy during web service request

-->

- should use `ServiceAccessPolicyThreadLocal#addActiveServiceAccessPolicyName()` 
  to grant the associated policy during web service request.

- can use `com.liferay.service.access.policy.api.jar` and  
  `com.liferay.service.access.policy.service.jar` to create policies 
  programmatically

- can use `ServiceAccessPolicyManagerUtil` to display list of supported policies 
  when authorizing remote application to associate token with an existing 
  policy. 
