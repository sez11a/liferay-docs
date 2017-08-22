# Upgrading Data in Development

As you develop modules, you might need to iterate through several database
schema changes. Before you release new module versions with your finalized
schema changes, you must create a formal
[data upgrade process](/develop/tutorials/-/knowledge_base/7-0/creating-an-upgrade-process-for-your-app).
But until then, you can use the Build Auto Upgrade feature to test schema
changes on the fly.

+$$$

**Note**:  The `service.properties` file's property `build.auto.upgrade` in
Liferay Portal 6.x portlets that use Service Builder applies Liferay Service
schema changes on rebuilding the services and redeploying the portlets.

Liferay Digital Enterprise 7.0 SP5 and Liferay CE Portal 7.0.4 GA5 reintroduces
this feature as this tutorial describes. 

$$$

Setting global property `schema.module.build.auto.upgrade` in
`[Liferay_Home]/portal-developer.properties` to `true` applies the module's
schema changes with every module build version increment. The feature executes
schema changes without massaging existing data. It leaves data empty for columns
you create, drops data from columns you delete or rename, and orphans data from
tables you delete or rename. 

Although Build Auto Upgrade updates databases quickly and automatically, it
doesn't guarantee proper data upgrade--that's for you to implement via
[data upgrade processes](/develop/tutorials/-/knowledge_base/7-0/creating-an-upgrade-process-for-your-app).
Build Auto Upgrade is for development purposes only. 

+$$$

**WARNING**: DO NOT USE the Build Auto Upgrade feature in production. Liferay DOES NOT support Build Auto Upgrade in production.

The Build Auto Upgrade feature is for development purposes only.  Enabling Build
Auto Upgrade can result in data loss and improper data upgrade. In production
environments, leave property `schema.module.build.auto.upgrade` in
`portal-developer.properties` set to `false`. 

$$$

By default, `schema.module.build.auto.upgrade` is set to `false`. On any
module's first deployment, its tables are generated regardless of
`schema.module.build.auto.upgrade`'s value.

The following table summarizes what Build Auto Upgrade does with schema changes: 

Schema Change | Result | 
:----------------- | :----------- | 
Add column | Create a new empty column. |
Rename column | Drop the existing column and delete all its data. Create a new empty column. |
Delete column | Drop the existing column and delete all its data. |
Create or rename a table in @product@'s built-in data source. | Orphan the existing table and all its data. Create the new table. |

You can now add the Build Auto Upgrade developer feature to your toolbox. 

## Related Topics

[Creating Data Upgrade Process for Modules](/develop/tutorials/-/knowledge_base/7-0/creating-an-upgrade-process-for-your-app)
