# Installing Liferay Maven Artifacts [](id=installing-liferay-maven-artifacts)

To create Liferay modules using Maven, you'll need the archives required by
Liferay (e.g., JAR and WAR files). This won't be a problem--Liferay provides
them as Maven artifacts. 

So how do you get Liferay artifacts? There are several different ways you can
obtain them. The most popular way to retrieve Liferay Maven artifacts is to
install them from a remote repository. Alternatively, you can download a
Liferay-provided Zip file that lets you install artifacts to local intranets and
repositories without relying on public network accessibility. If you're unsure
about which repository is best for you, visit the
[Understanding Maven Repositories](/develop/tutorials/-/knowledge_base/7-0/managing-liferay-maven-projects#understanding-maven-repositories)
section of the *Managing Liferay Maven Projects* tutorial.

First, you'll consider the installation process using remote repositories.

## Installing Artifacts from a Remote Repository [](id=installing-artifacts-from-a-remote-repository)

There are two main ways of installing Liferay's Maven artifacts from a remote
repository: Central Repository and Liferay Repository. To use these
repositories, you must have access to a public network. You'll learn more about
each option and how to configure it for your project next.

### Central Repository [](id=central-repository)

Using the Central Repository to install Liferay Maven artifacts is very
straightforward and only requires that you specify your module's dependencies
via its `pom.xml` file. In fact, the first time you use Maven to build a
Liferay Maven module project, Maven automatically downloads the required
artifacts from the Central Repository into your local `.m2` repository, if
they're not found in your `.m2` repository or any of your configured repository
servers. You'll see it happen when you package your Liferay modules. This
automation puts more emphasis on the development of your Liferay project and
less on managing artifacts.

When packaging your module, the automatic Maven artifact installation process
only downloads the artifacts necessary for that particular module. That module's
dependencies determines the artifacts that are installed into your local `.m2`
repository. The `pom.xml` file of your project is where you specify your
project's dependencies. You'll learn more about the POM throughout the Maven
tutorials.

You can view the published Liferay Maven artifacts on the Central Repository by
searching for *liferay maven* in the repo's Search bar. For convenience, you can
reference the available artifacts at
[http://search.maven.org/#search|ga|1|liferay maven](http://search.maven.org/#search|ga|1|liferay%20maven).
You can use the Latest Version column as a guide to see what is available for
the intended version of @product@ for which you're developing.

Sometimes, the Liferay Maven artifacts for a release are not immediately
available on Maven's Central Repository. In some cases, the Central Repository
might still be synced to a previous release of Liferay. If this occurs, download
the Liferay Maven artifacts from Liferay's repository. You'll learn how to do
this next.

### Liferay Repository [](id=liferay-repository)

If you'd like to access Liferay's latest Maven artifacts, you can configure
Maven to automatically download and install them from
[Liferay's Nexus repository](https://repository.liferay.com). Once configured, 
Maven automatically downloads the required artifacts from the Liferay Maven
repository into your local `.m2` repository, if they're not found in your `.m2`
repository or any of your configured repository servers. You'll see it happen
when you package your Liferay modules. 

In order to access artifacts from the Liferay Maven repository, you'll need to
configure Maven to look for them there. First, specify the Liferay repository's
credentials in your project's parent `pom.xml` file:

    <repositories>
        <repository>
            <id>liferay-public-releases</id>
            <name>Liferay Public Releases</name>
            <url>https://repository.liferay.com/nexus/content/repositories/liferay-public-releases</url>
        </repository>
    </repositories>
	  
	<pluginRepositories>
        <pluginRepository>
            <id>liferay-public-releases</id>
            <url>https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/</url>
        </pluginRepository>
    </pluginRepositories>

The above configuration retrieves artifacts from Liferay's release repository.

+$$$

**Note:** Liferay also provides a
[snapshot repository](https://repository.liferay.com/nexus/content/repositories/liferay-public-snapshots/),
which you can access by modifying the `<id>`, `<name>`, and `<url>` tags to
point to that repo. This repository should only be used in special cases. You'll
also need to enable accessing the snapshot artifacts:

    <snapshots>
        <enabled>true</enabled>
    </snapshots>

$$$

<!-- When the Liferay repository is configured in your `settings.xml` file,
archetypes are generated based on that repository's contents. See the
[Generating New Projects Using Archetypes]() tutorial for details on using Maven
archetypes for Liferay development.
-->
<!--
The above should be added once the archetype tutorial is written. -Cody
-->

If you've configured the Liferay Nexus repository to access Liferay
Maven artifacts, and you've already been syncing from the Central Repository,
you may need to clear out parts of your local repository to force Maven to
redownload the newer artifacts. Also, do not leave the Liferay repository
configured when publishing artifacts to Maven Central. You must comment out the
Liferay Repository credentials when publishing your artifacts.

The Liferay Maven repository offers a good alternative for those who want the
most up-to-date Maven artifacts produced by Liferay. With a few simple
configurations, you can download and install Liferay Maven artifacts from
Liferay's remote repository.

Finally, you'll learn how to install Liferay Maven artifacts from a local
repository.

## Installing Artifacts from a Local Repository [](id=installing-artifacts-from-a-local-repository)

For those who don't have access to a public network or just prefer to install
Liferay's Maven artifacts locally without relying on a remote repository, you
can download Liferay's Maven artifacts as a Zip file. Well, sort of. The
downloadable Zip doesn't actually contain Liferay's Maven artifacts; it contains
scripts that let you download all of Liferay's artifacts and install them to a
Maven repository of your choice.

You can download the Liferay artifacts from SourceForge.

1.  Navigate to the [Liferay Portal](https://sourceforge.net/projects/lportal/files/Liferay%20Portal/)
    project page in SourceForge and select the version of Liferay for which you
    want Maven artifacts. Then select the
    `liferay-ce-portal-maven-[version].zip` link to download the @product@
    Maven Zip.

    ![Figure 1: After selecting the Liferay version, select the Liferay Portal Maven Zip file to download.](../../../images/maven-select-download.png)

2.  Unzip the file and navigate to it in your command prompt. Then run the Ant
    command without a target.
    
        ant
    
    You can verify Liferay's artifacts are being installed by reading the output
    messages in your command prompt. The artifacts are downloaded from Liferay's
    Nexus repository.

    By default, all of Liferay's Maven artifacts are installed in your
    `[USER_HOME]/.m2` repository. You can change where the artifacts are
    installed by creating a `build.[USER_NAME}.properties` file in the Liferay
    Portal Maven folder and setting the `local.repository.path` property to the
    folder you want your artifacts to reside in.

Now you have all of Liferay's Maven artifacts locally and can delegate them to
any repository or folder you choose.

Congratulations! You've downloaded the Liferay artifacts and installed them to
your chosen repository.
