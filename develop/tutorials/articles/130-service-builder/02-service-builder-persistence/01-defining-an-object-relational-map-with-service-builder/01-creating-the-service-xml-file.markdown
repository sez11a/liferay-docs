# Creating the service.xml File [](id=creating-the-service-xml-file)

To define a service for your portlet project, you must create a `service.xml`
file. The DTD (Document Type Declaration) file
[http://www.liferay.com/dtd/liferay-service-builder_7_1_0.dtd](http://www.liferay.com/dtd/liferay-service-builder_7_1_0.dtd)
specifies the format and requirements of the XML to use. You can create your
`service.xml` file manually, following the DTD, or you can use Liferay @ide@.
@ide@ helps you build the `service.xml` file piece-by-piece, taking the
guesswork out of creating XML that adheres to the DTD.

If a default `service.xml` file already exists in your `*-service` module's root
folder, make sure it has an `<entity />` element named *Foo*. If it has the Foo
entity, remove the entire `<entity name="Foo" ...> ... </entity>` element. The
Liferay @ide@ project wizard creates the Foo entity as an example. It has no
practical use for you. 

If you don't already have a `service.xml` file, create one in your `*-service`
module's root folder and open the file. Liferay @ide@ provides a Diagram mode
and a Source mode to give you different perspectives of the service information
in your `service.xml` file.

- **Diagram mode** facilitates creating and visualizing relationships between 
service entities.
- **Source mode** brings up the `service.xml` file's raw XML content in the 
editor.

You can switch between these modes.

Next, you'll specify your service's global information. 
