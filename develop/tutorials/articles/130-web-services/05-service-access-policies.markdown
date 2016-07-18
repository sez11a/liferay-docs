# Service Access Policies [](id=service-access-policies)

Service Access Policies are an additional layer of web service security, 
defining services or service methods that can be invoked remotely. For more 
detail and information on managing Service Access Policies, see 
[the user guide article on Service Access Policies](/discover/deployment/-/knowledge_base/7-0/service-access-policies). 

There may be cases, however, when your application needs to integrate with a
Liferay instance's Service Access Policies. For example, you may have an app
that 

- uses custom remote API authentication (tokens) and requires certain Liferay 
  services to be available for clients using the tokens. 

- requires its services be made available to guest users, with no authentication 
  necessary. 

- contains a remote service authorization layer that needs to drive access to 
  remote services based on granted privileges. 

You can do this integration on two levels: 

1. The portal level, using the 
   [package `com.liferay.portal.kernel.security.service.access.policy`](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/security/service/access/policy/package-summary.html). 
   This package provides classes for basic access to policies. For example, you 
   can use the 
   [singleton `ServiceAccessPolicyManagerUtil`](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/security/service/access/policy/ServiceAccessPolicyManagerUtil.html) 
   to obtain Service Access Policies configured in the system. You can also use 
   the 
   [`ServiceAccessPolicyThreadLocal` class](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/security/service/access/policy/ServiceAccessPolicyThreadLocal.html) 
   to set and obtain Service Access Policies granted to the current request 
   thread. You may now be thinking, "That sounds great, but what's the advantage 
   of getting or setting these policies? What's the practical application?" This 
   is a fantastic question! Getting a list of the configured policies in a 
   Liferay instance lets remote apps/clients choose the policy to use to access 
   services. Also, apps like OAuth can offer a list of available policies during 
   the authorization step in the OAuth workflow and allow the user to choose 
   the policy to assign to the remote application. You can also grant a policy 
   to a current request thread. When a remote client accesses an API, something 
   must tell the Liferay instance which policies are assigned to this call. This 
   something is in most cases an 
   [`AuthVerifier` implementation](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/security/auth/verifier/AuthVerifier.html). 
   For example, in the case of the OAuth app, an `AuthVerifier` implementation 
   assigns the policy chosen by the user in the authorization step. 

2. The OSGi module level, using the following service access policy modules:

    - `com.liferay.portal.security.service.access.policy.api.jar`
    - `com.liferay.portal.security.service.access.policy.service.jar`
    - `com.liferay.portal.security.service.access.policy.web.jar`

    These OSGi modules are active in Liferay by default and you can use them to 
    manage Service Access Policies programmatically. You can find their source 
    code 
    [here in GitHub](https://github.com/liferay/liferay-portal/tree/master/modules/apps/foundation/portal-security). 
    Each module publishes a list of packages and services that can be consumed 
    by other OSGi modules. 

You can use either approach to develop a custom token verification module. A 
custom token verification module is an OSGi module that implements custom 
security token verification for use in authorizing remote clients. For example, 
such a module may contain a JSON Web Token implementation for Liferay's remote 
API. A custom token verification module must integrate with the Liferay instance 
during the remote API/web service call to grant the associated policy during the 
request. The module: 

- can use `com.liferay.portal.security.service.access.policy.api.jar` and 
  `com.liferay.portal.security.service.access.policy.service.jar` to create 
  policies programmatically. 

- should use the method 
  `ServiceAccessPolicyThreadLocal.addActiveServiceAccessPolicyName()` to grant 
  the associated policy during a web service request. 

- can use `ServiceAccessPolicyManagerUtil` to display list of supported policies 
  when authorizing the remote application, to associate the token with an 
  existing policy. 

[Liferay Sync's](https://www.liferay.com/supporting-products/liferay-sync) 
`sync-security` module is an example of such a module. It uses 
`com.liferay.portal.security.service.access.policy.service` to create the 
`SYNC_DEFAULT` and `SYNC_TOKEN` policies programmatically. For service calls to 
Sync's remote API, these policies grant access to Sync's 
`com.liferay.sync.service.SyncDLObjectService#getSyncContext` and 
`com.liferay.sync.service.*`, respectively. Here's the code in the 
`sync-security` module that defines and creates these policies:

    @Component(immediate = true)
    public class SyncSAPEntryActivator {

        // Define the policies
        public static final Object[][] SAP_ENTRY_OBJECT_ARRAYS = new Object[][] {
            {
                "SYNC_DEFAULT",
                "com.liferay.sync.service.SyncDLObjectService#getSyncContext", true
            },
            {"SYNC_TOKEN", "com.liferay.sync.service.*", false}
        };

        ...

        // Create the policies
        protected void addSAPEntry(long companyId) throws PortalException {
                for (Object[] sapEntryObjectArray : SAP_ENTRY_OBJECT_ARRAYS) {
                    String name = String.valueOf(sapEntryObjectArray[0]);
                    String allowedServiceSignatures = String.valueOf(
                        sapEntryObjectArray[1]);
                    boolean defaultSAPEntry = GetterUtil.getBoolean(
                        sapEntryObjectArray[2]);

                    SAPEntry sapEntry = _sapEntryLocalService.fetchSAPEntry(
                        companyId, name);

                    if (sapEntry != null) {
                        continue;
                    }

                    Map<Locale, String> map = new HashMap<>();

                    map.put(LocaleUtil.getDefault(), name);

                    _sapEntryLocalService.addSAPEntry(
                        _userLocalService.getDefaultUserId(companyId),
                        allowedServiceSignatures, defaultSAPEntry, true, name, map,
                        new ServiceContext());
                }
        }

        ...

    }

[Click here](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/sync/sync-security/src/main/java/com/liferay/sync/security/service/access/policy/SyncSAPEntryActivator.java) 
to see the entire `SyncSAPEntryActivator` class. This class creates the policies 
when the module starts. Note that this module is included and enabled in Liferay 
by default. You can access these and other policies in your Liferay instance at 
*Control Panel* &rarr; *Configuration* &rarr; *Service Access Policy*. 

The `sync-security` module must then grant the appropriate policy when needed. 
Since every authenticated call to Liferay Sync's remote API requires access to 
`com.liferay.sync.service.*`, the module must grant the `SYNC_TOKEN` policy to 
such calls. The module does this with the method 
`ServiceAccessPolicyThreadLocal.addActiveServiceAccessPolicyName`, as shown in 
this code snippet: 

    if ((permissionChecker != null) && permissionChecker.isSignedIn()) {
        ServiceAccessPolicyThreadLocal.addActiveServiceAccessPolicyName(
            String.valueOf(
                SyncSAPEntryActivator.SAP_ENTRY_OBJECT_ARRAYS[1][0]));
    }

Now every authenticated call to Sync's remote API, regardless of authentication 
method, will have access to `com.liferay.sync.service.*`. To see the full code 
example, 
[click here](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/sync/sync-security/src/main/java/com/liferay/sync/security/servlet/filter/SyncAuthFilter.java).

Nice! Now you know how to integrate your apps with the Service Access Policies 
in a Liferay instance. 

## Related Topics

[Service Access Policies](/discover/deployment/-/knowledge_base/7-0/service-access-policies)

[Service Builder Web Services](/develop/tutorials/-/knowledge_base/7-0/service-builder-web-services)

[Application Security](/develop/tutorials/-/knowledge_base/7-0/application-security)
