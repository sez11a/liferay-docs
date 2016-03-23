# Portal Configuration of JSON Web Services

JSON web services are enabled on Liferay Portal by default. If you need to
disable them, specify this portal property setting: 

    json.web.service.enabled=false

Next, let's look at strict HTTP methods. 

## Discoverability

By default, JSON web services are discoverable through the API page. Set the
following property to disable access to JSON web services:

    jsonws.web.service.api.discoverable=false

The JSON web services API page uses the path `/api/jsonws`. If you're running
Liferay locally, you can access it at `localhost:8080/api/jsonws`.

## Disabling HTTP Methods

When strict HTTP method mode is enabled, you can filter web service access based
on HTTP methods used by the services. For example, you can set the portal JSON
web services to work in read-only mode by disabling HTTP methods other than GET.
For example: 

    jsonws.web.service.invalid.http.methods=DELETE,POST,PUT

Now all requests that use HTTP methods from the list above are ignored.

Next, we'll show you how to restrict public access to exposed JSON APIs. 

## Strict HTTP Methods

All JSON web services are mapped to either GET or POST HTTP methods. If a
service method name starts with `get`, `is` or `has`, the service is assumed to
be read-only and is bound to the GET method; otherwise it's bound to POST. 

By default, Liferay Portal doesn't check HTTP methods when invoking a service
call; it works in *non-strict http method* mode, where services may be invoked
using any HTTP method. If you need the strict mode, you can set it with this
portal property: 

    jsonws.web.service.strict.http.method=true

When using strict mode, you must use the correct HTTP methods in calling service
methods. 

When strict HTTP mode is enabled, you still might have need to disable HTTP
methods. We'll show you how next. 

## Controlling Public Access

Each service method knows whether or not a given user has permission to invoke
the chosen action. If you're concerned about security, you can restrict access
to exposed JSON APIs by explicitly permitting or restricting certain JSON web
service paths.

The property `jsonws.web.service.paths.includes` denotes patterns for JSON web
service action paths that allowed. Set a blank pattern to allow any service
action path.

The property `jsonws.web.service.paths.excludes` denotes patterns for JSON web
service action paths that are not allowed even if they match one of the
patterns set in `jsonws.web.service.paths.includes`.

These properties support wildcards, so if you specify `get*,has*,is*` on the
right hand side of the `=` symbol for the `jsonws.web.service.paths.includes`
property, all read-only JSON methods will be publicly accessible. All other
JSON methods will be secured. To disable access to *all* exposed methods, you
can leave the right side of the `=` symbol empty; to enable access to all
exposed methods, specify `*`. Remember that if a path is matches both the
`jsonws.web.service.paths.includes` property and the
`jsonws.web.service.paths.excludes` property, the
`jsonws.web.service.paths.excludes` property takes precedence.
