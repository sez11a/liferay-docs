# Deployment Helper

@product@ requires all plugins be deployed through its internal deployer because
the majority of plugins are deployed into the installation's OSGi container.
This presents a problem if you wish to leverage your application server's
administrative tools to manage deployments for plugin JARs. For example, many
system administrators use app server tools with clustered environments, which
can automatically deploy plugins across all nodes for you.

In previous versions of Liferay Portal, users could use app server tools to
deploy their WARs to their clustered environments. This is not possible with
OSGi JARs. Because of this, you need a way to package your JARs as a traditional
WAR so they're deployable by your app server.

To continue using your app server's tools to deploy your plugin JARs, you must
configure Liferay's Deployment Helper tool in your project. The Deployment
Helper wraps Liferay artifacts into a standard WAR file that can be leveraged by
any app server cluster deployment tool. When the wrapped WAR file is deployed,
your Liferay artifacts are deployed to the proper location. The wrapped WAR the
Deployment Helper tool produces is comparable to the EAR or fat JAR file
concepts.

The Deployment Helper supports the following file types:

- JARs
- WARs
- OSGi Configuration Files (e.g., `.config`)

This tutorial defines how to configure the Deployment Helper for three popular
build tools: Ant, Gradle, and Maven. Visit the appropriate section to learn how
to configure the Deployment Helper for that kind of project.

## Ant

To configure the Deployment Helper tool for an Ant project, insert the following
code into your `build.xml` file:

    <project>
        <path id="deployment.helper.classpath">
            <fileset dir="../../../../tools/sdk/dist" includes="com.liferay.deployment.helper-*.jar" />
        </path>

        <taskdef classpathref="deployment.helper.classpath" resource="com/liferay/deployment/helper/ant/taskdefs.properties" />

        <target name="build-deployment-helper">
            <build-deployment-helper
                deploymentFileNames="test.jar"
                outputFileName="samples.war"
            />
        </target>
    </project>

For the example code above, you've defined a `test.jar` file as a deployment
file, which will be wrapped in a `samples.war` file. The Deployment Helper
automatically deploys the WAR to your Liferay instance's `deploy` folder. See
the
[Deployment Helper property descriptions](/develop/reference/-/knowledge_base/7-0/deployment-helper-gradle-plugin#task-properties)
for more information on how these are set.

## Gradle

To configure the Deployment Helper tool for a Gradle project, follow the
[Deployment Helper Gradle Plugin](/develop/reference/-/knowledge_base/7-0/deployment-helper-gradle-plugin)
reference article.

## Maven

To configure the Deployment Helper tool for a Maven project, insert the
following code into your `pom.xml` file:

    <build>
        <plugins>
            <plugin>
                <groupId>com.liferay</groupId>
                <artifactId>com.liferay.deployment.helper</artifactId>
                <version>1.0.4</version>
                <configuration>
                    <deploymentFileNames>test.jar</deploymentFileNames>
                    <outputFileName>samples.war</outputFileName>
                </configuration>
            </plugin>
        </plugins>
    </build>

For the example code above, you've defined a `test.jar` file as a deployment
file, which will be wrapped in a `samples.war` file. The Deployment Helper
automatically deploys the WAR to your Liferay instance's `deploy` folder. See
the
[Deployment Helper property descriptions](/develop/reference/-/knowledge_base/7-0/deployment-helper-gradle-plugin#task-properties)
for more information on how these are set.