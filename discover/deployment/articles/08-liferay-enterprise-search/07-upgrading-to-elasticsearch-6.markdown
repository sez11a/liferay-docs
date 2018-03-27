# Upgrading to Elasticsearch 6 [](id=upgrading-to-elasticsearch-6)

Elasticsearch 6 is supported for Digital Enterprise subscribers running Fix Pack
42 or later, and for Community Edition users running 7.0 CE GA 6 or greater. If
you're not already running a remote Elasticsearch 2.x server, follow the [installation guide](/discover/deployment/-/knowledge_base/7-0/installing-elasticsearch) to install
Elasticsearch 6 and the [configuration guide](/discover/deployment/-/knowledge_base/7-0/configuring-elasticsearch-for-liferay-0) to configure the Elasticsearch
adapter. In this article, learn to upgrade an existing Elasticsearch 2.x
server (or cluster) to Elasticsearch 6.1.x. The general steps are as follows:

1.  Install and configure Elasticsearch 6.1.x.
<!-- 2.  [Upgrade the Elasticsearch 2.4 indexes](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/setup-upgrade.html) to 6.1. -->
3.  Download the Elasticsearch 6 adapter from [Liferay Marketplace](LINK WHEN
    RELEASED)
4.  Stop the default Elasticsearch adapter.
5.  Stop the Elasticsearch 2.4 server.
6.  Start Elasticsearch 6.
7.  Install and configure the Elasticsearch 6 adapter.
8.  Reindex all search indexes.

+$$$

**Before Proceeding:** Back up your existing data before upgrading
Elasticsearch. If something goes wrong during or after the upgrade, roll
back to 2.x using the uncorrupted index snapshots. See
[here](https://dev.liferay.com/discover/deployment/-/knowledge_base/7-0/backing-up-elasticsearch)
for more information.

$$$

+$$$

**Key Changes:** This list compiles known changes within Elasticsearch that are
likely to affect @product@ users. For a more complete reckoning of what's
changed in Elasticsearch, see the [breaking changes for Elasticsearch
6](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/breaking-changes.html),
and [Elasticsearch
5](https://www.elastic.co/guide/en/elasticsearch/reference/5.0/breaking-changes-5.0.html),
since the breaking changes from 2.x to 5.x also apply to 2.x to 6.1.

*Custom Mappings*

- Aggregations: Aggregating on analyzed `String` fields was possible in
    Elasticsearch 2.x, but aggregating on analyzed `text` fields is not advised
    in Elasticsearch 6.1. Instead, use a `keyword` field or add [field
    data](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/fielddata.html).
    Beware, `fielddata` is disabled by default because it is memory-intensive.

*Spell Check and Query Suggestions*

- Due to Elastic's [removal of mapping
    types](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/removal-of-types.html)
    from search documents, spell check indexes and suggestion dictionaries now
    use a single document type. If your installation was leveraging either
    functionality, you must reindex all spell check indexes (form Control Panel
    &rarr; Configuration &rarr; Server Administration).

$$$

## Installing Elasticsearch 6.1 [](id=installing-elasticsearch-6-1)

Download [Elasticsearch 6.1.x](https://www.elastic.co/downloads/past-releases)
and unzip it wherever you please.

Name the cluster by configuring the `cluster.name` property
in `elasticsearch.yml`:

    cluster.name: LiferayElasticsearchCluster

Install the following required Elasticsearch plugins:

-  `analysis-icu`
-  `analysis-kuromoji`
-  `analysis-smartcn`
-  `analysis-stempel`

To install these plugins, navigate to Elasticsearch Home and enter

    ./bin/elasticsearch-plugin install [plugin-name]

Replace *[plugin-name]* with the Elasticsearch plugin's name.

Once installed, start Elasticsearch 6 by running

    ./bin/elasticsearch

from Elasticsearch Home.

<!-- ## Upgrade the Elasticsearch 2.4 Indexes

This is likely the trickiest part of the upgrade process. Fortunately.  [Elastic
extensively documents the
process](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/setup-upgrade.html).
Consider [upgrading from a remote
cluster](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/reindex-upgrade-remote.html)
as that will allow you to upgrade indexes without interrupting service. Once you
have upgraded indexes ready to use with @product@ and your new Elasticsearch 6.1
server, come back here and continue with this guide.
-->

## Download the Elasticsearch 6 Adapter [](id=download-the-elasticsearch-6-adapter)

Download the [Elasticsearch 6 adapter LPKG from Liferay Marketplace](LINK WHEN
RELEASED).

## Stop the Elasticsearch Adapter and Elasticsearch 2.x [](id=stop-the-elasticsearch-adapter-and-elasticsearch-2-x)

Before installing the Elasticsearch 6 adapter, you must stop the running
Elasticsearch adapter that ships with @product@. Use the App Manager: 

- Navigate to Control Panel &rarr; Apps &rarr; App Manager.
- Search for *elasticsearch*. Find the Liferay Portal Search Elasticsearch
    module and click the edit ((![Edit](../../images/icon-edit.png))) button.
    Choose the Deactivate option. This leaves the bundle installed, but stops it
    in the OSGi runtime.

Then stop Elasticsearch 2.x. If you're wondering whether your log should be
complaining vociferously at this point, the answer is a definitive *yes*. You'll
resolve that in the next step.

## Install and Configure the Elasticsearch 6 Adapter [](id=install-and-configure-the-elasticsearch-6-adapter)

Once the default adapter is stopped, install the Elasticsearch 6 adapter (the
LPKG you donwloaded) by placing it in your Liferay Home folder's `deploy`
directory.
See[here](https://dev.liferay.com/discover/portal/-/knowledge_base/7-0/installing-apps-manually#using-your-file-system-to-install-apps)
for more information.

<!--It starts automatically with log messages like this:

Add when possible -->
Now configure the adapter to find your Elasticsearch 6.1.x cluster by specifying
the correct *Cluster Name* and Setting *Operation Mode* to *REMOTE*. Make sure
the *Transport Address* matches the one Elasticsearch is using. If testing
locally with Elasticsearch's default settings, the default value in the adapter
works fine (*localhost:9300*).

## Reindex [](id=reindex)

Once the Elasticsearch adapter is installed and talking to the Elasticsearch
cluster, navigate to Control Panel &rarr; Configuration &rarr; Server
Administration, and click *Execute* for the *Reindex all search indexes* entry.

You should also reindex the spell check indexes while you're here.

## Reverting to Elasticsearch 2 [](id=reverting-to-elasticsearch-2)

Stuff happens. If that stuff involves an unrecoverable failure during the
upgrade to Elasticsearch 6, roll back to Elasticsearch 2 and regroup.

Since Elasticsearch 2 and Elasticsearch 6 are two separate installations, this
procedure is straightforward:

1.  Stop and remove the Elasticsearch 6 adapter from @product@.
2.  Reinstall the Elasticsearch 2 adapter.
3.  Make sure that `elasticsearch.yml` and the Elasticsearch 2 adapter's configuration
    are both agreed upon where the search engine is running (port 9200 by
    default).

Learn more about configuring Elasticsearch in [this article](/discover/deployment/-/knowledge_base/7-0/configuring-elasticsearch-for-liferay-0).
