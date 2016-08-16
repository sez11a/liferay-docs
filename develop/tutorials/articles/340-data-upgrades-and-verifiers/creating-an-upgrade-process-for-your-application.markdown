# Creating An Upgrade Process for Your App [](id=creating-an-upgrade-process-for-your-app)

As changes are made to your app, it's very likely that your database schema
changes with it, and these changes bring with them the need for an upgrade
process to move your app from one version to the next. These upgrades not only
need to happen, but also they need to be registered in the database. The system
then records the current state of the schema so that if the upgrade fails, the
process can revert the app back to its previous version.

An app's upgrade process consists of the following components (from lowest to
highest level): 

-   **Upgrade Steps**: Classes responsible for updating the database for a new
    database schema version. Each upgrade step class focuses on a particular
    model (or cohesive set of models) with respect to a database schema version
    upgrade. 

-   **Registrations**: Calls to the app's registry that define all the upgrade
    step instances associated with moving an app from one database schema
    version to the next. 

-   **Upgrade Step Registrator**: Specifies the registrations for all of the
    app's database schema version upgrades.  

This tutorial demonstrates how to

- Create an upgrade process for your app using Liferay's new upgrade framework

Go ahead and jump right in to declaring your dependencies next.

## Declaring Your Dependencies [](id=declaring-your-dependencies)

In your application's dependency management file (e.g., Maven POM, Gradle build
file, or Ivy `ivy.xml` file), add a dependency on the [com.liferay.portal.upgrade](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.portal.upgrade/)
module. In a `build.gradle` file, the dependency would look like this line:

    compile group: "com.liferay", name: "com.liferay.portal.upgrade", version: "2.0.0"

Once your dependencies are declared, you can write your upgrade steps.

## Writing Upgrade Steps [](id=writing-upgrade-steps)

Upgrade steps are classes responsible for making changes
associated with each version of an app's database schema. They extend the
[`UpgradeProcess` base class](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/upgrade/UpgradeProcess.html),
which implements the [`UpgradeStep` interface](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/upgrade/UpgradeStep.html).

Each upgrade step must override the `doUpgrade` method to process its
database schema upgrade. For example, the [`UpgradeCalendarBooking` class](https://github.com/liferay/liferay-portal/blob/2960360870ae69360861a720136e082a06c5548f/modules/apps/forms-and-workflow/calendar/calendar-service/src/main/java/com/liferay/calendar/upgrade/v1_0_0/UpgradeCalendarBooking.java)
modifies a column in the calendar booking table: 

    public class UpgradeCalendarBooking extends UpgradeProcess {

            @Override
            protected void doUpgrade() throws Exception {
                    alter(
                            CalendarBookingTable.class,
                            new AlterColumnType("description", "TEXT null"));
            }

    }
    
Follow this same pattern to create upgrade step classes for your app's
upgrade process.

After creating upgrade steps, you can write the app's upgrade step registrator.

## Writing the Upgrade Step Registrator [](id=writing-the-upgrade-step-registrator)

The first thing you need to do is create a package called `upgrade` in your 
package namespace. In the `upgrade` package, create an OSGi Component of 
the service `UpgradeStepRegistrator` that implements the 
[`UpgradeStepRegistrator` interface](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/foundation/portal/com.liferay.portal.upgrade/com/liferay/portal/upgrade/registry/UpgradeStepRegistrator.html),
which is in the [com.liferay.portal.upgrade](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.portal.upgrade/)
module.

The interface declares a [`register` method](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/foundation/portal/com.liferay.portal.upgrade/com/liferay/portal/upgrade/registry/UpgradeStepRegistrator.html)
that you must override to handle the upgrade registration process.

    @Component(immediate = true, service = UpgradeStepRegistrator.class)
    public class CalendarServiceUpgrade implements UpgradeStepRegistrator {

        @Override
        public void register(Registry registry) {

        }
    }

The `register` method is where you'll specify upgrade registrations. Each schema
upgrade is represented by an upgrade registration. An upgrade registration is an
abstraction for all the changes you need to apply to the database from one
version to the next one.

To define a registration, you need to provide

- the bundle symbolic name of the module.
- the schema version to upgrade from (as a `String`)
- the schema version to upgrade to (as a `String`)
- a list of upgrade step instances

For example, here is the first registration in the `register` method of the
`com.liferay.calendar.service` module's upgrade step registrator:

	@Override
	public void register(Registry registry) {

		registry.register(
			"com.liferay.calendar.service", "0.0.1", "1.0.0",
			new com.liferay.calendar.upgrade.v1_0_0.UpgradeCalendarBooking()));

		...
	}

In this first registration, the `com.liferay.calendar.service` module's database
schema is upgraded from version 0.0.1 to version 1.0.0. The changes are produced
by a list of upgrade step instances, which in this example contains only one
instance:

    new com.liferay.calendar.upgrade.v1_0_0.UpgradeCalendarBooking());

For each database schema upgrade, you'll define a registration in parameters to
the local `Registry` instance's [`register` method](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/foundation/portal/com.liferay.portal.upgrade/com/liferay/portal/upgrade/registry/UpgradeStepRegistrator.Registry.html).

Here's the entire registrator's `register` method implementation from module
`com.liferay.calendar.service` version 2.0.5: 

    @Override
    public void register(Registry registry) {
        registry.register(
            "com.liferay.calendar.service", "0.0.1", "1.0.0",
            new com.liferay.calendar.upgrade.v1_0_0.UpgradeCalendarBooking());

        registry.register(
            "com.liferay.calendar.service", "1.0.0", "1.0.1",
            new com.liferay.calendar.upgrade.v1_0_1.UpgradeCalendarBooking());

        registry.register(
            "com.liferay.calendar.service", "1.0.1", "1.0.2",
            new UpgradeCalendar());

        registry.register(
            "com.liferay.calendar.service", "1.0.2", "1.0.3",
            new DummyUpgradeStep());

        registry.register(
            "com.liferay.calendar.service", "1.0.3", "1.0.4",
            new UpgradeClassNames());

        registry.register(
            "com.liferay.calendar.service", "1.0.4", "1.0.5",
            new UpgradeCalendarResource(
                _classNameLocalService, _companyLocalService,
                _userLocalService),
            new UpgradeCompanyId(), new UpgradeLastPublishDate());
    }

In this example, the `com.liferay.calendar.service` module is upgraded
incrementally from version 0.0.1 to 1.0.5. Only one upgrade step instance is
specified for each registration through version 1.0.4. Finally, the module is
upgraded from version 1.0.4 to version 1.0.5, using a list of three
upgrade steps consisting of a `UpgradeCalendarResource`, `UpgradeCompanyId`, and
`UpgradeLastPublishDate` instance.

+$$$

**Note:** Take care in using Upgrade Step classes that have the same name but
are in different packages. As a good example, see
`com.liferay.calendar.upgrade.v1_0_0.UpgradeCalendarBooking` and
`com.liferay.calendar.upgrade.v1_0_1.UpgradeCalendarBooking`.

$$$

In terms of data, each registration is represented by a row in the `Release_`
table on @product@'s database. You can check the database to verify the upgrades
that were executed for each module.

Next, you can learn how to specify differing module versions and schema versions
in your bundle.

## Specifying Schema Versions [](id=specifying-schema-versions)

If your database schema version is different from your module version, you
should specify the schema version in the `bnd.bnd` file. Add a
`Require-SchemaVersion` header if the file doesn't already have one and assign
it the database schema version value.

Here's an example schema version header:

    Require-SchemaVersion: 1.0.2

There you have it. Now you know how to create an upgrade process for your app!

## Related Topics [](id=related-topics)

[Upgrading Plugins to Liferay 7](/develop/tutorials/-/knowledge_base/7-0/upgrading-plugins-to-liferay-7)

[Application Configuration](/develop/tutorials/-/knowledge_base/7-0/application-configuration)

<!--[Creating a Verify Process for Your App](/develop/tutorials/-/knowledge_base/7-0/creating-a-verify-process-for-your-app)-->
