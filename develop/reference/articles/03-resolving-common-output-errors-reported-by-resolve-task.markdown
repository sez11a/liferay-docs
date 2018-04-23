# Resolving Common Output Errors Reported by the resolve Task

The `resolve` Gradle task is provided by a
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace)
to
[validates modules](/develop/tutorials/-/knowledge_base/7-1/resolving-your-modules).
This is very useful for finding issues and reporting them as output before
deployment. For more information on running this task from Liferay Workspace,
see the
[Validating Modules Against the Target Platform](/develop/tutorials/-/knowledge_base/7-1/validating-modules-against-the-target-platform)
tutorial section. For general help with OSGi related issues, visit the
[Troubleshooting FAQ](/develop/tutorials/-/knowledge_base/7-1/troubleshooting)
tutorial section.

For help interpreting the `resolve` task's output, see the list below for common
output errors, what they mean, and how to fix them.

## Missing Import Error

This type of error is thrown when your module is referring to an import that is
not available. For example, suppose you have a module `test-service` that
depends on the `com.google.common.base` package. If that package is not
available to reference, the following error is thrown:

    Resolution exception in project 'modules:test-service': Unresolved requirements in root project 'modules:test-service':
        Mandatory:
            [osgi.wiring.package ] com.google.common.base; version=[23.0.0,24.0.0)
            [osgi.identity       ] test.service

This kind of error can also occur when separate modules require different
versions of another module. For example, suppose you have *module A* requiring
*module Test version 1* and *module B* requiring *module Test version 4*.
Without running the resolver, both modules A and B would compile successfully,
but when they were deployed, one would fail in @product@'s OSGi runtime because
both dependencies cannot be satisfied. These types of scenarios are difficult to
diagnose, but with the `resolve` task, can be found with ease.

To fix missing import errors, you may need to adjust the
[export](/develop/tutorials/-/knowledge_base/7-1/exporting-packages) and/or
[import](/develop/tutorials/-/knowledge_base/7-1/importing-packages)
configuration of your modules. Also, see the
[Resolving Third Party Library Package Dependencies](/develop/tutorials/-/knowledge_base/7-1/adding-third-party-libraries-to-a-module)
tutorial for more information on resolving import errors. Sometimes, this kind
of error can be solved by editing the `resolve` task's list of capabilities. See
the
[Depending on Third Party Libraries Not Included in @product@](/develop/tutorials/-/knowledge_base/7-1/depending-on-third-party-libraries-not-included-in-product)
section to learn how to do this.

## Missing Service Reference

If your module is referencing a service that does not exist, an error is thrown.
This is very helpful because service reference issues are hard to diagnose
during deployment without using the
[Gogo Shell](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell).

For example, if your module `test-portlet` is referencing a service (e.g.,
`test.api.TestApi`) it does not have access to, the following error is thrown:

    Resolution exception in project 'modules:test-portlet': Unresolved requirements in project 'modules:test-portlet':
        Mandatory:
            [osgi.identity ] test.portlet
            [osgi.service  ] objectClass=test.api.TestApi

To fix this, you must make the service available to your module. If you're
expecting the service to be provided by your target platform, check to make sure
it's being provided. If it's a service provided by a custom module, you should
check that service provider module and ensure it's correctly providing that
service to your module. To check the target platform for available services,
follow the steps below:

1.  Start your target platform instance.

2.  Start the Gogo shell from a local telnet session (e.g., `telnet localhost
    11311`).

3.  You can list all services containing a keyword by running `services | grep
    "SERVICE_NAME"`. It's easiest to do this rather than listing all services
    since there are usually too many to sift through.

4.  You can also list services provided by a component. Run `lb -s` to list all
    provided bundles by their bundle symbolic name (BSN). Find the BSN for the
    desired component and then run `scr:info <BSN>`.

If you're unable to track down your missing service, it may be provided by a
customized @product@ core feature or an external @product@ feature. If this is
the case, it will not be included in the target platform's default capabilities.
You can make the custom service capability available to reference by
[generating a new custom distro JAR](/develop/reference/-/knowledge_base/7-1/modifying-the-target-platforms-capabilities#depending-on-a-customized-distrobution-of-product).

## Missing Fragment Host

Referring to a fragment host that does not exist throws an error. For example,
if your `test.login` fragment is configured to modify a fragment host named
`com.liferay.login.web` that cannot be referenced, the following error is
thrown:

    Resolution exception in project 'modules:test.login': Unresolved requirements in project 'modules:test-login':
        Mandatory:
            [osgi.identity    ] test.login
            [osgi.wiring.host ] com.liferay.login.web; version=1.0.10

Configuring a fragment host in your module is typically done with the
`Fragment-Host` header in the `bnd.bnd` file:

    Fragment-Host: com.liferay.login.web;bundle-version="[1.0.0,1.0.1)"

To fix this, you should inspect your target platform to ensure it includes the
JAR you're attempting to add a fragment for. Your fragment host header may be
referencing an incorrect bundle symbolic name (BSN) or version. The easiest way
to check this is by using the
[Gogo Shell](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell).
Follow the steps below to find the bundle symbolic name:

1.  Start your target platform instance.

2.  Start the Gogo shell from a local telnet session (e.g., `telnet localhost
    11311`).

3.  List all installed bundles by BSN with the command `lb -s`. You can search
    through the output to find the BSN. If you already know the BSN and want to
    check the version, run `lb -s | grep "<BSN>"`.

Once you know the correct BSN/version to reference, update your `Fragment-Host`
header to resolve the error.

For more information on fragments, see the
[JSP Overrides Using OSGi Fragments](/develop/tutorials/-/knowledge_base/7-0/overriding-a-modules-jsps)
tutorial.