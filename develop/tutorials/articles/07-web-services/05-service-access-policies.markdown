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

You can do this integration on two levels: 

1. The portal level, via the 
   [package `com.liferay.portal.kernel.security.service.access.policy`](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/security/service/access/policy/package-summary.html). 
   This package provides classes for basic access to policies. For example, you 
   can use the 
   [singleton `ServiceAccessPolicyManagerUtil`](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/security/service/access/policy/ServiceAccessPolicyManagerUtil.html) 
   to obtain Service Access Policies configured in the system. You can also use 
   the 
   [`ServiceAccessPolicyThreadLocal` class](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/security/service/access/policy/ServiceAccessPolicyThreadLocal.html) 
   to set and obtain Service Access Policies granted to current request thread. 
   You may now be thinking, "That sounds great, but what's the advantage of 
   getting or setting these policies? What's the practical application?" This is 
   a fantastic question! Getting a list of the configured policies in a Liferay 
   instance lets remote apps/clients choose the policy to use to access 
   services. Also, apps like OAuth can offer a list of available policies during 
   the authorization step in the OAuth workflow, and allow the user to choose 
   the policy to assign to the remote application. You can also grant a policy 
   to a current request thread. When a remote client accesses an API, something 
   must tell the Liferay instance which policies are assigned to this call. This 
   something is in most cases an 
   [`AuthVerifier` implementation](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/security/auth/verifier/AuthVerifier.html). 
   For example, in the case of the OAuth app, an `AuthVerifier` implementation 
   assigns the policy choosen by the user in the authorization step. 

2. The OSGi module level, via the following service access profile modules:

    - `com.liferay.portal.security.service.access.policy.api.jar`
    - `com.liferay.portal.security.service.access.policy.service.jar`
    - `com.liferay.portal.security.service.access.policy.web.jar`

    These OSGi modules can be used to manage Service Access Policies 
    programmatically. You can find their source code here in 
    [GitHub](https://github.com/liferay/liferay-portal/tree/master/modules/apps/foundation/portal-security). 
    Each module publishes a list of packages and services that can be consumed 
    by other OSGi modules. 

You can use either approach to develop a custom token verification module. A 
custom token verification module is an OSGi module that implements custom 
security token verification for use in authorizing remote clients. For example, 
such a module may contain a JSON Web Token implementation for Liferay's remote 
API. A custom token verification module must integrate with the Liferay instance 
during the remote API/web service call, to grant the associated policy during 
the request. The module: 

- should use `ServiceAccessPolicyThreadLocal#addActiveServiceAccessPolicyName()` 
  to grant the associated policy during web service request. 

- can use `com.liferay.portal.security.service.access.policy.api.jar` and 
  `com.liferay.portal.security.service.access.policy.service.jar` to create 
  policies programmatically. 

- can use `ServiceAccessPolicyManagerUtil` to display list of supported policies 
  when authorizing the remote application, to associate the token with an 
  existing policy. 

Nice! Now you know how to integrate your apps with the Service Access Policies 
in a Liferay instance. 

**Related Topics**

[Service Access Policies](/discover/deployment/-/knowledge_base/7-0/service-access-policies)

[Service Builder Web Services](/develop/tutorials/-/knowledge_base/7-0/service-builder-web-services)

[Application Security](/develop/tutorials/-/knowledge_base/7-0/application-security)
