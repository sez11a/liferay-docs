# Using JNDI Resources with Hibernate [](id=using-jndi-resources-with-hibernate)

If you're using Hibernate to connect to JNDI resources on Tomcat (or an
application server that manages JNDI resources the same way), this tutorial is
for you! As background, Tomcat lets applications access JNDI resources via a
classloader. Although the DXP 7.1 application deploys on Tomcat, its portlets
are not---they're OSGi bundles installed to DXP's OSGi container. For a portlet
to access JNDI resources on Tomcat, the portlet must use DXP's classloader. This
tutorial demonstrates using this classloader to leverage a Hibernate
configuration's JNDI data source. 

+$$$

**Note:** The code demonstrated here works with Tomcat but is not guaranteed to 
work on other application servers. Refer to your application server's
documentation for how it manages JNDI resources. 

$$$

First in your Hibernate configuration descriptor, specify a session factory that uses a JNDI data source. Here's an abbreviated descriptor:

    <?xml version="1.0"?>
    <!DOCTYPE hibernate-configuration PUBLIC "-//Hibernate/Hibernate Configuration DTD 3.0//EN" "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">

    <hibernate-configuration>
        <session-factory>

            <property name="connection.datasource">java:comp/env/jdbc/mydb</property>

            <property name="current_session_context_class">thread</property>

            // Specify resources here ...
        </session-factory>
    </hibernate-configuration>

If you base your descriptor on the one above, make sure to replace the
`connection.datasource` property value with your JNDI data source name and
specify resources to use in your sessions. 

Next, the `HibernateUtil` class demonstrates creating and managing Hibernate
sessions that use DXP's classloader. Note, `// Customization START/END` comments
mark code that lets the caller access the Hibernate session factory configured
with the JNDI data source. 

    import com.liferay.portal.kernel.log.Log;
    import com.liferay.portal.kernel.log.LogFactoryUtil;

    import org.hibernate.HibernateException;
    import org.hibernate.Session;
    import org.hibernate.SessionFactory;
    import org.hibernate.cfg.Configuration;

    // Customization START
    import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
    import com.liferay.portal.kernel.util.AggregateClassLoader;
    // Customization END

    public class HibernateUtil {

        public static final String COUNT_COLUMN_NAME = "COUNT_VALUE";

        public static void closeSession(Session session) {
            try {
                if ((session != null) && session.isOpen()) {
                    session.close();
                }
            }
            catch (HibernateException he) {
                _log.error(he.getMessage());
            }
        }

        public static String getCountColumnName() {
            return COUNT_COLUMN_NAME;
        }

        public static SessionFactory getSessionFactory() {
            return _instance._sessionFactory;
        }

        public static Session openSession() throws HibernateException {
            return openSession(getSessionFactory());
        }

        public static Session openSession(SessionFactory sessionFactory)
            throws HibernateException {

            return sessionFactory.getCurrentSession();
        }

        private HibernateUtil() {
            // CUSTOMIZATION START

            Thread thread = Thread.currentThread();
            ClassLoader threadClassLoader = thread.getContextClassLoader();
            ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

            ClassLoader hibernateClassLoader =
                AggregateClassLoader.getAggregateClassLoader(
                    portalClassLoader, threadClassLoader);

            thread.setContextClassLoader(hibernateClassLoader);

            // CUSTOMIZATION END

            try {
                Configuration configuration = new Configuration();

                configuration = configuration.configure();

                _sessionFactory = configuration.buildSessionFactory();
            }
            catch (Exception e) {
                _log.error(e, e);
            }

            // CUSTOMIZATION START

            finally {
                thread.setContextClassLoader(threadClassLoader);
            }

            // CUSTOMIZATION END
        }

        private static Log _log = LogFactoryUtil.getLog(HibernateUtil.class);

        private static HibernateUtil _instance = new HibernateUtil();

        private SessionFactory _sessionFactory;

    }

Let's break down this class' logic. First, its static members are declared at
the bottom.

    private static Log _log = LogFactoryUtil.getLog(HibernateUtil.class);

    private static HibernateUtil _instance = new HibernateUtil();

    private SessionFactory _sessionFactory;

Here's a description for each:

-   `_log`: Logs messages for this class. 
-   `_instance`: the `HibernateUtil` instance.
-   `_sessionFactory`: manages sessions to the JNDI resources (e.g., the JNDI 
    data source. 

The `HibernateUtil` class uses these members. 

The constructor `HibernateUtil()` makes the "magic" happen:

1.  Get the current thread, its class loader, and DXP's class loader. 

        Thread thread = Thread.currentThread();
        ClassLoader threadClassLoader = thread.getContextClassLoader();
        ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

2.  Aggregate the classloaders as a convenience to use with Hibernate.   

        ClassLoader hibernateClassLoader =
            AggregateClassLoader.getAggregateClassLoader(
                portalClassLoader, threadClassLoader);

2.  Set the Hibernate classloader on the thread. 

        thread.setContextClassLoader(hibernateClassLoader);

3.  Create a session factory that uses the Hibernate configuration (the one 
    configured earlier that uses the JNDI data source). 

        try {
           Configuration configuration = new Configuration();

           configuration = configuration.configure();

           _sessionFactory = configuration.buildSessionFactory();
        }
        catch (Exception e) {
           _log.error(e, e);
        }

    At this point, the session factory services requests on the JNDI data
    source. 

4.  Restore the original classloader to the thread. 

        finally {
            thread.setContextClassLoader(threadClassLoader);
        }

`HibernateUtil`'s other methods access and act on the Hibernate session factory
and its sessions.

-  `getSessionFactory()`
-  `closeSession(Session)`
-  `openSession()`
-  `openSession(SessionFactory)`

Classes like `HibernateUtil` facilitate getting Hibernate sessions that use JNDI
resources, such as JNDI data sources. You can use a version of it in your
projects that use Hibernate. 

## Related Topics [](id=related-topics)

[Connecting to JNDI Data Resources](/develop/tutorials/-/knowledge_base/7-1/connecting-to-data-sources-using-jndi)

[Configuring Dependencies](/develop/tutorials/-/knowledge_base/7-1/configuring-dependencies)
