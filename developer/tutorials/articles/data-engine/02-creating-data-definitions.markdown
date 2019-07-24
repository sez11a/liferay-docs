# Creating Data Definitions with REST Calls

These example calls use cURL, a cross-platform tool for making HTTP calls. Feel
free to use any REST client you're familiar with.

### Adding Data Definitions

Get the `siteId` from the Site Menu &rarr; Configuration &rarr; Site Settings.

In this example, it's 20123.

```sh
curl -i -X POST \
  http://localhost:8080/o/data-engine/v1.0/sites/20123/data-definitions \
  -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' \
  -H 'Content-Type: application/json' \
  -d '{
    "dataDefinitionFields": [
        {
            "fieldType": "text",
            "name": "product"
        }
    ],
    "description": {
            "value": "Description"
    },
    "name": {
            "value": "Order"
    }
}'
```

Here you're specifying the endpoint for the data definitions API, passing in
the `siteId`. Then you're creating a request body that includes:

- `dataDefinitionFields`: The example is simple, with just a `fieldType` and a
    `name` element for one field. Here's the complete list of elements that each
    field can have:
    - `customProperties` <!-- DESCRIBE -->
    - `defaultValue`: provide a default value for a field. 
    - `id` <!-- is this a key that you can set yourself? Like the old
        DDMTemplateKey that could be configured in the UI? DESCRIBE --> 
    - `indexable` can be set to `true` (default)  or `false` to control whether the
        fields is indexed in the search engine.
    - `label`: set a human readable label for each of the custom properties the
        fields has.
    - `localizable`: can be set to `true` (default) or `false` to control
        whether the locale of the User is appended when he or she adds a data
        record.
    - `repeatable`: set to `true` or `false` to control whether the field
        accepts multiple values per data record.
    - `tip`: provide help text for the field's properties.
    - `name`: a required element that names the field. This defaults to `string`
        if left blank.
- `description`: use this optional element to describe the data definition.
- `name`: name the data definition.
- `dataDefinitionRules`: set validation rules for the data definition.
- `dateCreated` and `dateModified`: these are set by the system, so it's not
    necessary to do anything here.
- `siteId`: the ID of the site the data definition is scoped to.
- `storageType`: the default is `string`, which is how the JSON is stored in the
    database.
- `userId`: the ID of the User making the service call.

For a complete view of the JSON structure of a data definitions, see the
[`data-engine-rest-impl/rest-openapi.yaml`](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/data-engine/data-engine-rest-impl/rest-openapi.yaml)
file. For a more robust User Experience, copy the file contents into the [Swagger
Editor](https://editor.swagger.io/) and get auto-generated Open API docs. 

One your POST call is processed, a `DataDefinition` is returned in the body. You
will need its `id` to create data record collections for the definition's
records.

```sh
HTTP/1.1 200 
X-Content-Type-Options: nosniff
X-Frame-Options: SAMEORIGIN
X-XSS-Protection: 1
Set-Cookie: JSESSIONID=19FBDA81A72F8EA1F9668A153E9F2FBA; Path=/; HttpOnly
Expires: Thu, 01 Jan 1970 00:00:00 GMT
Cache-Control: private, no-cache, no-store, must-revalidate
Pragma: no-cache
Set-Cookie: COOKIE_SUPPORT=true; Max-Age=31536000; Expires=Fri, 22-May-2020 20:18:15 GMT; Path=/; HttpOnly
Set-Cookie: GUEST_LANGUAGE_ID=en_US; Max-Age=31536000; Expires=Fri, 22-May-2020 20:18:15 GMT; Path=/; HttpOnly
Date: Thu, 23 May 2019 20:18:16 GMT
Content-Type: application/json
Content-Length: 307

{
  "dataDefinitionFields" : [ {
    "fieldType" : "text",
    "indexable" : true,
    "localizable" : false,
    "name" : "product",
    "repeatable" : false
  } ],
  "dateCreated" : "2019-05-23T20:18:16Z",
  "dateModified" : "2019-05-23T20:18:16Z",
  "id" : 39108,
  "siteId" : 20123,
  "userId" : 20129
}
```

Here you can see that some information was added by the data engine itself:

- `id` <!-- is this a key that you can set yourself? Like the old
    DDMTemplateKey that coudl be configured in the UI? DESCRIBE --> 
- `indexable` is set to `true` by default, so the field is indexed in the search
    engine.
- `localizable` is set to `false` by default.
- `repeatable` is set to `false` by default, so submitting the same filed
    multiple times for the same data record is not allowed.
- `dateCreated` and `dateModified`: the data engine updates this metadata when
    the data definition is first created and when it's updated, respectively.
- `userId` is set to the numeric ID of the User that created the definition.

Before we move on, it's important to understand the relationship between the
data definition and the information you're going to collect from Users, as a
data record. A data record must conform to the fields defined in this data
definition creation step. Therefore, when you add a data record in a later step
your request will use a `dataRecordValues` structure like this:

```json
    "dataRecordValues": {

        "product": { 
            "en_US": "Bicycle"
        }
    }
```

### Retrieving Data Definitions

In addition to adding data definitions, you can make GET calls to the service.
Retrieve all the data definitions in a site by passing in the site ID (`20123`
here):

```sh
curl -i -X GET \
  http://localhost:8080/o/data-engine/v1.0/sites/20123/data-definitions \
  -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' \
  -H 'Content-Type: application/json' \
```

### Deleting Data Definitions

Data definitions can also be deleted with a DELETE request. You'll need the data
definition ID:

```sh
curl -i -X DELETE \
  http://localhost:8080/o/data-engine/v1.0/data-definitions/39108 \
  -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' \
  -H 'Content-Type: application/json' \
```

Deleting a data definition also deletes all associated Data Record Collections,
Data Records, and Data Layouts.
