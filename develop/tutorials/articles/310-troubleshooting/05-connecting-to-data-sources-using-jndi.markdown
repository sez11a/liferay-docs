# Connecting to Data Sources Using JNDI [](id=connecting-to-data-sources-using-jndi)

Your application server's Java Naming and Directory Interface (JNDI)
implementation lets you access data outside @product@'s data sources.
@product-ver@'s OSGi runtime requires using @product@'s class loader to load the
application server's JNDI classes for accessing its data sources. This tutorial
demonstrates how. 

## Using JNDI to Connect to a Data Source [](id=using-jndi-to-connect-to-a-data-source)

You can use an external JNDI data source from inside modules or traditional
plugins. The following code demonstrates this.

    Thread thread = Thread.currentThread();

    // Get the thread's class loader. You'll reinstate it after using
    // the data source you look up using JNDI

    ClassLoader origLoader = thread.getContextClassLoader();
    
    // Set Liferay's class loader on the thread
    
    thread.setContextClassLoader(PortalClassLoaderUtil.getClassLoader());

    try {

            // Look up the data source and connect to it

            InitialContext ctx = new InitialContext();
            DataSource datasource = (DataSource)
                ctx.lookup("java:comp/env/jdbc/TestDB");

            Connection connection = datasource.getConnection();
            Statement statement = connection.createStatement();

            // Execute SQL statements here ...

            connection.close();
    }
    catch (NamingException ne) {

        ne.printStackTrace();
    }
	catch (SQLException sqle) {

		sqle.printStackTrace();
	}
    finally {
           // Switch back to the original context class loader

           thread.setContextClassLoader(origLoader);
    }

Here are the class imports for the code above:

    import java.sql.Connection;
    import java.sql.SQLException;
    import java.sql.Statement;
    import javax.naming.InitialContext;
    import javax.naming.NamingException;
    import javax.sql.DataSource;
    import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

In traditional Java EE environments, you can access the application server's
JNDI API directly. In @product@'s OSGi environment, you must use @product@'s
classloader to access it. The example code sets @product@'s classloader on the
thread to access the JNDI APi. After working with the data source, the code
reinstates the thread's original classloader.

+$$$

**Note**: Attempts to use an application server's JNDI API from an OSGi bundle
without using @product@'s classloader results in
`java.lang.ClassNotFoundException`s. For example, here's an exception from
attempting to use Apache Tomcat's JNDI API without using @product@'s
classloader:

    javax.naming.NoInitialContextException: Cannot instantiate class:
    org.apache.naming.java.javaURLContextFactory [Root exception is
    java.lang.ClassNotFoundException:
    org.apache.naming.java.javaURLContextFactory]

$$$

You can use similar code to access a data source from your application. Make
sure to substitute `jdbc/TestDB` with your data source name and invoke SQL
statements as you desire.

How you define a JNDI data source depends on your application server. See your
application server's documentation for assistance. 

[@product@'s manual installation documentation](/develop/tutorials/-/knowledge_base/7-0/installing-liferay-manually#manual-configuration)
explains how to configure @product@ with an external data source. 

## Related Topics [](id=related-topics)

[Installing @product@ Manually](/discover/deployment/-/knowledge_base/7-0/installing-liferay-manually)
