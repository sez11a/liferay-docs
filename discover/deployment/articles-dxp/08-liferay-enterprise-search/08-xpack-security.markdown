# Securing Elasticsearch 6 with X-Pack [](id=securing-elasticsearch-6-with-x-pack)

X-Pack is an [Elastic
extension](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/setup-xpack.html)
for securing and monitoring Elasticsearch clusters. If you're using
Elasticsearch as @product@'s search engine, it's advisable to secure it with
X-Pack. The security features of X-Pack include authenticating access to the
Elasticsearch cluster's data and encrypting Elasticsearch's internal and
external communications. These are necessary security features for most
production systems. An Enterprise Search-Standard subscription is necessary for
this integration.
<!--Need confirmation of subscription-->

Here's the generalized process for using X-Pack to secure the @product@ data
indexed in Elasticsearch:

1.  Get an Enterprise Search subscription.
2.  [Install X-Pack into Elasticsearch](https://www.elastic.co/guide/en/x-pack/6.1/installing-xpack.html) and configure it to require authentication and [encryption](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/configuring-tls.html#configuring-tls).
3.  Download and install the [Liferay Connector for X-Pack Security](MP LINK)
4.  Configure the X-Pack connector with the proper credentials and encryption
    information.
5.  Restart Elasticsearch. These steps require a full cluster restart.

Following these instructions gives you a basic working installation of
Elasticsearch, communicating freely with @product@, but read Elastic's
documentation to learn about additional configuration options, features, and the
architecture of
[X-Pack](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/configuring-security.html). 

## Installing X-Pack [](id=installing-x-pack)

1.  To [install
    X-Pack](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/installing-xpack-es.html)
    and automatically grant it the required permissions (recommended), run

        bin/elasticsearch-plugin install x-pack --batch

    on each cluster node. The `--batch` option bypasses installation prompts for
    granting permissions to X-Pack. 

    You'll see log output detailing the permissions granted, finishing with
    `Installed x-pack`:

        -> Downloading x-pack from elastic
        [=================================================] 100%   
        @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        @     WARNING: plugin requires additional permissions     @
        @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        * java.io.FilePermission \\.\pipe\* read,write
        * java.lang.RuntimePermission accessClassInPackage.com.sun.activation.registries
        * java.lang.RuntimePermission getClassLoader
        * java.lang.RuntimePermission setContextClassLoader
        * java.lang.RuntimePermission setFactory
        * java.net.SocketPermission * connect,accept,resolve
        * java.security.SecurityPermission createPolicy.JavaPolicy
        * java.security.SecurityPermission getPolicy
        * java.security.SecurityPermission putProviderProperty.BC
        * java.security.SecurityPermission setPolicy
        * java.util.PropertyPermission * read,write
        See http://docs.oracle.com/javase/8/docs/technotes/guides/security/permissions.html
        for descriptions of what these permissions allow and the associated risks.
        @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        @        WARNING: plugin forks a native controller        @
        @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        This plugin launches a native controller that is not subject to the Java
        security manager nor to system call filters.
        Elasticsearch keystore is required by plugin [x-pack], creating...
        -> Installed x-pack

    See more about the permissions X-Pack needs
    [here](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/installing-xpack-es.html)

2.  Make sure Elasticsearch is allowing the automatic creation of indexes. If
   you're unsure, check `elasticsearch.yml` for this property:

        action.auto_create_index: false

    This property is `true` by default, so if you don't see it in
    `elasticsearch.yml`, there's nothing to worry about. See [Elastic's
    documentation](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/docs-index_.html#index-creation) for more information on automatic index creation.

Once X-Pack is installed, configure its built-in user passwords.

## Setting Up X-Pack Users [](id=setting-up-x-pack-users)

In a system using X-Pack Security and X-Pack Monitoring, two of the built-in
X-Pack users are important: `kibana` and `elastic`.

Set the passwords for all X-Pack's [built-in
users](https://www.elastic.co/guide/en/x-pack/6.1/setting-up-authentication.html#built-in-users).
The `setup-passwords` command is the simplest method to set the built-in users'
passwords for the first time. It's only valid for the first use. To update a
password subsequently, use Kibana's UI or the [Change Password
API](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/security-api-change-password.html).

You can even let X-Pack randomly generate passwords for all the built-in users
by passing in the `auto` argument:

    ./bin/x-pack/setup-passwords auto
        Changed password for user kibana
        PASSWORD kibana = Y?v1Jv^0AO*SKXveriGr

        Changed password for user logstash_system
        PASSWORD logstash_system = U^zLGC9$N6%6KhUHz^qb

        Changed password for user elastic
        PASSWORD elastic = GqhoaEUyTM@tp1*wQd~F

See Elastic's documentation on the [setup-passwords
command](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/setup-passwords.html) for additional options.

Since you're securing Elasticsearch, make sure you keep track of the password
set for the `elastic` user. 

Enabling transport layer security on each node is highly recommended.

## Enabling Transport Layer Security [](id=enabling-transport-layer-security)

The following instructions for enabling TLS use `liferay` as the password
whenever one is needed. Customize these as appropriate for your installation. To
enable TLS/SSL in Elasticsearch:

1.  [Generate a node
    certificate](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/configuring-tls.html#node-certificates)
    for each node. You can, of course, use a Certificate Authority of your
    choosing to obtain node certificates.

    - Create a certificate authority, using [X-Pack's
     certutil](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/certutil.html)
     command:

        ./bin/x-pack/certutil ca --pem --ca-dn CN=localhost

        +$$$

        **Note:** The `certutil` command defaults to using the *PKSC#12* format
        for certificate generation. Kibana does not work with PKSC#12
        certificates, so the `--pem` option (to generate the certificate in PEM
        format) is important if you're using X-Pack monnitoring.

        $$$

        This generate a ZIP file. Unzip the contents somewhere safe.

    - Generate X.509 certificates and private keys using the new CA. For example:

        ./bin/x-pack/certutil cert --pem --ca-cert /path/to/ca.crt --ca-key /path/to/ca.key --dns localhost --ip 127.0.0.1

        This generates another ZIP file. Extract the contents somewhere in the
        `Elasticsearch Home/config` folder.

2.  [Enable TLS](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/configuring-tls.html#enable-ssl) on each node via its `elasticsearch.yml`.

- Add the certificate, key, and certificate authority paths to each node's
    `elasticsearch.yml`:

        xpack.ssl.certificate: /path/to/[Elasticsearch Home]/config/instance.crt
        xpack.ssl.key: /path/to/[Elasticsearch Home]/config/instance.key
        xpack.ssl.certificate_authorities: /path/to/ca.crt

    The example paths above assume you added the certificate to `Elasticsearch
    Home/config/x-pack/`. The `certutil` output includes the certificate
    authority certificate inside the `.p12` file, so you can use the same file
    for the keystore and truststore.

-  Enable transport layer TLS with these settings in `elasticsearch.yml`:

        xpack.security.transport.ssl.enabled: true
        xpack.security.transport.ssl.verification_mode: certificate

- Enable TLS on the HTTP layer to encrypt client communication:

        xpack.security.http.ssl.enabled: true

After X-Pack is installed and TLS is enabled, configure the X-Pack Security
adapter in @product@.

## Configuring the X-Pack Security Adapter [](id=configuring-the-x-pack-security-adapter)

If you'd like to configure the X-Pack adapter in the @product@ UI, navigate to
Control Panel &rarr; Configuration &rarr; System Settings. Find the Foundation
category, and click on the X-Pack Security entry. You can enter the property
values here, but it's more common to use a [configuration
file](/discover/portal/-/knowledge_base/7-1/understanding-system-configuration-files)
deployed to `Liferay Home/osgi/configs`. For the X-Pack adapter, create a file
called

    com.liferay.portal.search.elasticsearch6.xpack.security.internal.configuration.XPackSecurityConfiguration.config

The exact contents of the file depend on your X-Pack setup. To configure the adapter
according to the Elasticsearch setup documented here, populate the file with
these contents:

    sslKeyPath="/path/to/[Elasticsearch Home]/config/instance.key"
    sslCertificatePath="/path/to/[Elasticsearch Home]/config/instance.crt"
    certificateFormat="PEM"
    requiresAuthentication=B"true"
    username="elastic"
    password="GqhoaEUyTM@tp1*wQd~F"
    sslCertificateAuthoritiesPaths="/path/to/[Elasticsearch Home]/config/ca.crt"
    transportSSLVerificationMode="certificate"
    transportSSLEnabled=B"true"

Enable authentication by setting authentication to `required` and providing the
credentials for the Elasticsearch user. For SSL, enable transport SSL, set the
certificate verification mode and certificate format, and provide path to the
certificate, key, and certificate authority. Of course, the exact values will
differ if you configured X-Pack differently.

Here's the complete list of configuration options for the X-Pack Connector:

- `sslKeyPath`
- `sslCertificatePath`
- `sslCertificateAuthoritiesPaths`
- `certificateFormat`
- `requiresAuthentication`
- `username`
- `password`
- `transportSSLVerificationMode`
- `transportSSLEnabled`
- `sslKeystorePath`
- `sslKeyStorePassword`
- `sslTruststorePath`
- `sslTruststorePassword`

Once completed the , restart Elasticsearch. These steps require a full cluster
restart.
