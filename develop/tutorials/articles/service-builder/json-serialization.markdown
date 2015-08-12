# JSON Serialization

There are two JSON serialization mechanisms in Liferay Portal: *manual*
serialization and *loose* serialization. Please note that these names are not
strict or widely recognized. These terms are simply used here to differentiate
between the two serialization mechanisms. To see this tutorial's sample code in
the context of a deployable portlet project, please see the
[Example JSON Serialization Portlet](https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/liferay-plugins-sdk-7.0.0/portlets/example-json-serialization-portlet).

## Manual Serialization

*Manual serialization* begins with creation of a
`com.liferay.portal.kernel.json.JSONObject` or a
`com.liferay.portal.kernel.json.JSONArray` using the `createJSONObject` or
`createJSONArray` methods of `com.liferay.portal.kernel.json.JSONFactoryUtil`.
The `JSONObject` and `JSONArray` classes are wrappers for JSON maps and arrays.
After creating a `JSONObject` or `JSONArray`, you need to add map or array
elements to the created instance. Invoking the `toString()` method performs
the serialization and JSON string is returned. E.g.,

    JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
    String exampleInfo = "example information";
    jsonObj.put("exampleInfo", exampleInfo);
    long exampleCompanyId = 12345;
    jsonObj.put("exampleCompanyId", exampleCompanyId);
    ...
    String jsonString = jsonObj.toString();

The main advantage of using manual serialization is that it grants you complete
control over the properties and values that appear in the resulting JSON string.
However, this can also be a major drawback when serializing Java beans since
each property and value has to be added manually, as in the example above. Using
manual serialization can be error prone and can force developers to write a lot
of boiler plate code.

When using `JSONObject`s, there's another potential pitfall of which you should
be aware. Developers might be tempted to use `JSONObject`s as generic data
containers and write methods that return `JSONObject`s. This is not a good
practice. Methods should return concrete types when possible. Moreover, by
returning `JSONObject`s, the code becomes tightly-coupled with a non-common
type. Instead, methods should return one of the following items:

1. A concrete type where it make sense
2. A JSON serialized string
3. A map or list if the returned value will need further modifications

Remember that `JSONObject`s are just utility classes for JSON serialization.
They are not data containers. Their usage scope should be restricted to inside a
single method.

Liferay's manual serialization mechanism is built on top of the
[json.org](http://www.json.org/java/index.html) library and, therefore, it
shares the same features. There are some sharp corners about which users should
be aware. One is that inner maps are not serialized as JSON maps. Instead,
they're serialized as *double* maps: the first level contains information about
the map implementation type and the second level contains the actual map values.
Another important point is that the `class` property (from the `getClass()`
getter method) is serialized as `javaClass`, not as `class`.

## Loose Serialization

Loose serialization takes a different approach. The object to be serialized is
examined using reflection and then serialized automatically. The basic usage is
quite simple:

    String jsonString = JSONFactoryUtil.looseSerialize(object);

That's it! But this is just the beginning of the loose serialization features.
Loose serialization is based on the [Flexjson](http://flexjson.sourceforge.net)
library and shares all of its features.

One thing to remember is that, by default, all collections properties are *not*
(loosely) serialized. To include collections in the serialization, you need to
tell the serializer which collections properties to include. E.g., suppose that
you have a `Pet` model class that's designed to represent a pet. Suppose that
your `Pet` class contains a collection instance variable called `favoriteFoods`
and a getter method called `getFavoriteFoods`. You could use the following lines
to specify that the favorite food collection should be included in the
serialization of your pet object:

    JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
    jsonSerializer.include("favoriteFoods");
    String jsonString = jsonSerializer.serialize(pet);

The following serialization invocation is a shortcut that does the same thing as
the lines above:

    String jsonString = JSONFactoryUtil.looseSerialize(pet, "favoriteFoods");

Similarly, some properties can be excluded using the `exclude()` method (there
is no shortcut for this method). Including and excluding supports wildcards; a
common example is: `exclude("*.class")` to exclude class information from the
resulting JSON string.

As demonstrated above, loose serialization uses *hints* (what to include and
exclude) to control the serialization process. These hints are specified at the
point of serialization.

### Annotations

Another way of specifying what to include and exclude is by using the
`com.liferay.portal.kernel.json.JSON` annotation. Simply annotate a getter
method with `@JSON` or `@JSON(include = false)` to include or exclude that
property from JSON serialization. The `@JSON` annotation's `include` attribute
defaults to `true` so only `@JSON` and `@JSON(include = false)` need to be used
in practice. Furthermore, unless using strict mode (see below), you only need to
add the `@JSON` annotation to collection properties that need to be included in
the serialization (since collection properties are excluded by default).

### Strict Mode

Sometimes, a significant set of properties must be excluded from the
serialization. For example, suppose that only top level properties of some model
object need to be included while all others should be excluded. In this case,
it's convenient to use something called *strict* mode. In strict JSON
serialization mode, you must annotate all properties that need to be serialized.
Any property without an annotation is ignored. E.g.,

    @JSON(strict = true)
    public class UserModelImpl extends BaseModelImpl<User> implements UserModel {
    ....
    @JSON
    public String getUuid() {
    ...
    @JSON
    public long getUserId() {
    ...

All of the model objects in Liferay Portal are generated by Service Builder and
use strict mode. (To check this, open any of the `*ModelImpl` classes and look
for the `@JSON(strict = true)` annotation decorating the class declaration.)
Service Builder supports JSON loose serialization. The `json-enabled` attribute
of the `entity` tag in `service.xml` specifies whether or not the entity should
be annotated for JSON serialization. By default, if the `remote-service` value
is `true`, then the `json-enabled` value is also `true`. The `column` tag in
`service.xml` has the same attribute, `json-enabled`. It specifies whether or
not the column should be annotated for JSON serialization. If the `json-enabled`
value in an entity element is `true`, then the `json-enabled` value of each
column element defaults to `true`.

### JSONSerializable Interface

Alternatively, objects can implement the `JSONSerializable` interface that
contains a single method: `toJSONString()`. Developers can implement this method
and add any code that generates a custom JSON string from the current object
state. This option affords the most flexibility to developers since it grants
them complete control over the generated JSON string.
