# Enabling Hypermedia REST APIs [](id=enabling-hypermedia-rest-apis)

Liferay's hypermedia REST APIs are included in Liferay CE Portal 7.1 GA2 and 
Liferay DXP 7.1 Fix Pack 1. To use these APIs in earlier 7.1 releases, you must 
install them manually: 

1.  Remove the Pre-installed APIs
2.  Install the Latest Version of the APIs
3.  Enable Access to the APIs

The following sections walk you through these steps. 

## Remove the Pre-installed APIs [](id=remove-the-pre-installed-apis)

You must first remove the older, pre-installed version of the APIs: 

1.  Navigate to `[Liferay Home]/osgi/marketplace`. The 
    [Liferay Home](/discover/deployment/-/knowledge_base/7-1/installing-liferay#liferay-home) 
    folder is typically the application server's parent folder. 

2.  Delete the APIO packages. For example, here are the APIO packages for 
    Liferay Portal: 

        Liferay CE Foundation - Liferay CE APIO Architect - API.lpkg 
        Liferay CE Foundation - Liferay CE APIO Architect - Impl.lpkg

    If the portal is running, you should see in the console that the modules in 
    those packages have been stopped: 

        2018-07-16 12:42:22.186 INFO  [fileinstall-$LIFERAY_HOME/osgi/marketplace][BundleStartStopLogger:38] STOPPED Liferay CE Foundation - Liferay CE Apio Architect - API_1.0.0 [387]
        2018-07-16 12:42:22.199 INFO  [fileinstall-$LIFERAY_HOME/osgi/marketplace][BundleStartStopLogger:38] STOPPED com.liferay.apio.architect.api_1.0.2 [388]
        2018-07-16 12:42:22.216 INFO  [Refresh Thread: Equinox Container: c0a2f090-f388-0018-1c45-fc3bc84c1049][BundleStartStopLogger:38] STOPPED com.liferay.apio.architect.uri.mapper.impl_1.0.0 [715]
        2018-07-16 12:42:22.323 INFO  [Refresh Thread: Equinox Container: c0a2f090-f388-0018-1c45-fc3bc84c1049][BundleStartStopLogger:38] STOPPED com.liferay.apio.architect.impl_1.0.1 [714]
        2018-07-16 12:42:22.335 INFO  [Refresh Thread: Equinox Container: c0a2f090-f388-0018-1c45-fc3bc84c1049][BundleStartStopLogger:38] STOPPED com.liferay.apio.architect.exception.mapper.impl_1.0.1 [713]
        2018-07-16 12:42:22.347 INFO  [fileinstall-$LIFERAY_HOME/osgi/marketplace][BundleStartStopLogger:38] STOPPED Liferay CE Foundation - Liferay CE Apio Architect - Impl_1.0.0 [712]

3.  Use the 
    [Felix Gogo shell](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell) 
    to verify that the bundles have been removed. To do so, run this command in 
    the Gogo shell: 

        lb apio

    If this returns results for the `*apio*` bundles, then you must delete them
    via the Gogo shell. Take note of each bundle's ID and run `uninstall
    [BUNDLE_ID]` for each. For example, if the bundle IDs were 388, 713, 714,
    and 715, then you would run these commands in the Gogo shell: 

        uninstall 388
        uninstall 713
        uninstall 714
        uninstall 715

4.  Finally, remove the `*apio*` configuration file in 
    `[Liferay Home]/osgi/configs`. 

## Install the Latest Version of the APIs [](id=install-the-latest-version-of-the-apis)

Now you must download and install the latest version of the APIs: 

1.  Download the updated API modules by clicking the link for each: 

    -   [`com.liferay.apio.architect.impl-1.0.6.jar`](http://central.maven.org/maven2/com/liferay/com.liferay.apio.architect.impl/1.0.6/com.liferay.apio.architect.impl-1.0.6.jar)
    -   [`com.liferay.apio.architect.api-1.3.0.jar`](http://central.maven.org/maven2/com/liferay/com.liferay.apio.architect.api/1.3.0/com.liferay.apio.architect.api-1.3.0.jar)
    -   [`com.liferay.apio.architect.uri.mapper.impl-1.0.1.jar`](http://central.maven.org/maven2/com/liferay/com.liferay.apio.architect.uri.mapper.impl/1.0.1/com.liferay.apio.architect.uri.mapper.impl-1.0.1.jar)
    -   [`com.liferay.apio.architect.exception.mapper.impl-1.0.3.jar`](http://central.maven.org/maven2/com/liferay/com.liferay.apio.architect.exception.mapper.impl/1.0.3/com.liferay.apio.architect.exception.mapper.impl-1.0.3.jar) 

2.  Deploy these files to the `[Liferay Home]/deploy` folder. The console should 
    show that the modules are starting: 

        2018-07-16 13:01:26.477 INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:261] Processing com.liferay.apio.architect.api-1.3.0.jar
        2018-07-16 13:01:26.483 INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:261] Processing com.liferay.apio.architect.impl-1.0.6.jar
        2018-07-16 13:01:26.484 INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:261] Processing com.liferay.apio.architect.exception.mapper.impl-1.0.3.jar
        2018-07-16 13:01:26.484 INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:261] Processing com.liferay.apio.architect.uri.mapper.impl-1.0.1.jar
        2018-07-16 13:01:31.818 INFO  [fileinstall-$LIFERAY_HOME/osgi/modules][BundleStartStopLogger:35] STARTED com.liferay.apio.architect.exception.mapper.impl_1.0.3 [948]
        2018-07-16 13:01:31.898 INFO  [fileinstall-$LIFERAY_HOME/osgi/modules][BundleStartStopLogger:35] STARTED com.liferay.apio.architect.impl_1.0.6 [949]
        2018-07-16 13:01:32.831 INFO  [fileinstall-$LIFERAY_HOME/osgi/modules][BundleStartStopLogger:35] STARTED com.liferay.apio.architect.api_1.3.0 [947]
        2018-07-16 13:01:32.839 INFO  [fileinstall-$LIFERAY_HOME/osgi/modules][BundleStartStopLogger:35] STARTED com.liferay.apio.architect.uri.mapper.impl_1.0.1 [950]

    The modules should then appear in `[Liferay Home]/osgi/modules`. 

## Enable Access to the APIs [](id=enable-access-to-the-apis)

By default, security restricts access to the APIs. To enable access, you must 
add a specific configuration file: 

1.  Create the file 
    `com.liferay.apio.architect.impl.application.ApioApplication-default.config` 
    with these contents: 

        auth.verifier.auth.verifier.BasicAuthHeaderAuthVerifier.urls.includes="*"
        auth.verifier.auth.verifier.OAuth2RestAuthVerifier.urls.includes="*"
        auth.verifier.guest.allowed="true"
        oauth2.scopechecker.type="none"

2.  Deploy the file to `[Liferay Home]/osgi/configs`. 

3.  Once the OSGi container loads the configuration, you can make a request to
    the API's root URL:

        curl http://localhost:8080/o/api

    This should give you an empty root response: 

        {
            "@id":"http://localhost:8080/o/api",
            "@type":"EntryPoint",
            "@context":[  
            {  
                "@vocab":"http://schema.org/"
            },
            "https://www.w3.org/ns/hydra/core#"
            ]
        }

    If you instead receive a permissions error, then the configuration didn't 
    load. In this case, try restarting the portal. 

## Related Topics [](id=related-topics)

[Consuming Web Services](/develop/tutorials/-/knowledge_base/7-1/consuming-web-services)
