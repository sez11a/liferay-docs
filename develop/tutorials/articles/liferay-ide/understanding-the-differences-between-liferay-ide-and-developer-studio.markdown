# Understanding the Differences Between Liferay IDE and Liferay Developer Studio [](id=understanding-the-differences-between-liferay-ide-and-liferay-developer-stu)

If you're interested in Liferay development, the first step is choosing the
development environment that offers the necessary tools for a productive
experience. Liferay offers two supported development environments that you can
choose, based on your needs: Liferay IDE and Liferay Developer Studio. However,
what are the differences between the two? 

In this tutorial, you'll explore what both development environments have to
offer, and be able to make a choice between which environment you should use to
meet your Liferay development goals. 

## Comparing IDE and Developer Studio [](id=comparing-ide-and-developer-studio)

Liferay IDE is a set of Eclipse plugins that support Liferay development. It is
shipped as one single Eclipse feature (a set of plugins) that can be installed
in any existing Eclipse install (3.6.x, 3.7.x, 4.2.x). 

Liferay Developer Studio is another set of Eclipse plugins, but is a superset of
Liferay IDE. This means Developer Studio contains all of the Liferay IDE plugins
with some additional plugins that support EE developers. Developer Studio is
also available as an all-in-one bundle, which is a shrink-wrapped copy of
Eclipse (3.6.x) that has all of the Developer Studio Eclipse plugins
pre-installed (which subsequently includes all of the Liferay IDE plugins). 

Now that you know the physical differences between the two development
environments, you may be curious about their monetary and support differences. 

Liferay IDE is free and open source and always will be. You can think of IDE as
the *Community Edition* of Liferay developer tooling. Thus, it is targeted for
use by Liferay Portal Community Edition developers. Since IDE is open source, it
is community supported in the Liferay IDE forums. 

Liferay Developer Studio is only for EE customers (or EE trial users). Since
Developer Studio users are EE customers, they also have an email based support
system in addition to community support in the Liferay IDE forums. 

Lastly, you'll compare IDE and Developer Studio's server capabilities. 

In Developer Studio there is a new type of Liferay Tomcat server that is
available for developers: Liferay Tomcat Server EE. This is an upgraded version
of the CE server that is specifically used for the EE edition of Liferay. If you
are using Liferay IDE, you can use the Liferay Server CE version to develop
against Liferay Portal EE, but you may miss EE specific features in the future. 
For Developer Studio users, they can also use the CE version of Liferay, but
they must have a properly licensed EE server also configured in Studio. 

Next, you'll learn about the specific plugins only offered for Developer Studio.

## Learning About Developer Studio's Additional Plugins [](id=learning-about-developer-studios-additional-plugins)

In the previous section, you learned that Developer Studio is simply a superset
of Liferay IDE, containing additional plugins that support EE developers. What
are the additional plugins? A comprehensive list is given below: 

- Eclipse all-in-one bundle packaging
- Bundled Portal Server EE 
- Bundled Plugins SDK EE 
- Example Project wizard for bundled example projects 
- WebSphere 6.x/7.0 Server development support 
- Kaleo Designer for Java

Excellent! You now have a better understanding of the differences between
Liferay IDE and Developer Studio! 

## Related Topics [](id=related-topics)

[Developing Applications with Liferay IDE](/develop/learning-paths/-/knowledge_base/6-2/developing-apps-with-liferay-ide)

[Developing Applications with Liferay Developer Studio](/develop/learning-paths/-/knowledge_base/6-2/developing-applications-with-liferay-developer-stu)

[Creating Projects Using the New Project Wizard](/develop/tutorials/-/knowledge_base/6-2/creating-projects-using-the-new-project-wizard)
