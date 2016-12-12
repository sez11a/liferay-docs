# Activating Your DXP Server [](id=registering-your-dxp-server)

@product@ server instances are activated through Liferay Connected Services. Please proceed to [this](//TODO: link) section if you are installing a local workstation instance.

## Introduction to Liferay Connected Services [](id=lcs-introduction)

Liferay Connected Services (LCS) is a set of online tools and services that lets 
you manage and monitor your Liferay DXP instances. LCS can automatically install 
the fix packs that you choose, monitor your DXP instances' performance, activate 
your DXP instances, and help you manage your DXP subscriptions. This section in the documentation will help you to activate your @product@ installation, the other LCS features are described in the Maintainance sections. //TODO: Link

You can find more information about LCS on its 
[official product page](http://www.liferay.com/products/liferay-connected-services). 

Before going any further, you should take note of a few key terms used 
throughout this guide: 

- *Project*: Represents a group of users belonging to a company or organization. 
  For example, a project can consist of all the users from a project team or 
  business unit, or it can include the entire company.
- *Environment*: Represents a physical cluster of servers or a virtual or 
  logical aggregation of servers.
- *Server*: Describes a concrete DXP instance. It can be a standalone server 
  or a cluster node.

You'll get started with a few configuration steps that are required to use LCS 
with your Liferay DXP instances.

## Downloading the LCS Client App

The LCS client app is part of Liferay DXP 
bundles and autodeploys when the bundle starts. The LCS client app in the DXP 
bundle, however, may be outdated. You should therefore download and install 
the latest version of the LCS client app. You can download the LCS client app 
[here in the Liferay Marketplace](https://web.liferay.com/marketplace/-/mp/application/71774947). 
For instructions on using Marketplace to download and install apps, see 
[this user guide article](/discover/portal/-/knowledge_base/7-0/using-the-liferay-marketplace). 
Also note that as improvements are made to LCS, older versions of the LCS 
client app may not work. You should therefore ensure that your DXP instance 
is always running the latest version of the client. When upgrading the 
client, you may also need to regenerate the 
[environment token](/discover/deployment/-/knowledge_base/7-0/using-lcs#using-environment-tokens) 
that you use to connect. 

## Configuring the LCS Client

The LCS Client needs to connect to Liferay's servers to work.
If your server connects to the Internet through a proxy, you must 
preconfigure the LCS client app before it deploys to your DXP instance. [This 
article shows you how to do this](/discover/deployment/-/knowledge_base/7-0/preconfiguring-the-lcs-client-to-connect-through-a-proxy).

If a proxy is not available and you are behind a firewall, please read [this page](/discover/deployment/-/knowledge_base/7-0/preconfiguring-the-lcs-client-to-connect-through-a-proxy). //TODO: link

## Activating Your DXP Server with LCS [](id=registering-your-dxp-server-with-lcs)

To use LCS, you must have an account at [Liferay.com](http://www.liferay.com/) which should be registered to your company's subscription project. 

Once the LCS client app is deployed, you're ready to activate your DXP server 
with LCS. You'll use an 
[LCS environment token](/discover/deployment/-/knowledge_base/7-0/using-lcs#using-environment-tokens) 
to do this: 

1. Log in to 
   [lcs.liferay.com](https://lcs.liferay.com). This takes you to your company's 
   LCS project. If your company has multiple projects, select the one you want 
   to activate this server under from the user menu at the top right.

    ![Figure 1: Your company's LCS projects are shown under *Manage Projects* in your user menu.](../../../../images-dxp/lcs-user-menu-manage-projects.png)


+$$$

**Note**: If you don't see the company's project, you can either request access from your colleagues who have are already members or from Liferay Support.

$$$

2. Ensure that an environment exists to activate your server under. If you have 
   sufficient permissions in your company's project, you can create a new 
   environment by selecting the *Add Environment* tab. Note that you must 
   activate a clustered server in a clustered environment. LCS environments can 
   only be designated as clustered when they are created. To create a clustered 
   environment, click *Add Environment* and select the *Cluster* checkbox when 
   filling out the environment's information. Clustering must be enabled on the servers, please follow the configuration section for it. //TODO:Link

    ![Figure 2: You must activate your DXP server in an LCS environment. The red box in this screenshot highlights an environment.](../../../../images-dxp/lcs-registration-select-environment.png)

3. Select the *Subscriptions* tab, and check the *Assign Subscription Type* 
   table to ensure that the environment you want to activate your server in has 
   a subscription type. If the environment doesn't have a subscription type, you 
   can assign it one by selecting the *No Subscriptions* link and then choosing 
   a subscription type. You should **use caution** when setting an environment's 
   subscription type. All the servers in an environment **must be shut down** to 
   assign that environment's subscription type. Also, **once set, you can't 
   change an environment's subscription type**. 
   [Click here](/discover/deployment/-/knowledge_base/7-0/using-lcs#managing-liferay-dxp-subscriptions) 
   for more information on using subscriptions in LCS. 

    ![Figure 3: The Environment Subscriptions table shows the subscription types assigned to your environments, and lets you make such assignments.](../../../../images-dxp/lcs-environment-subscriptions.png)

4. Select the environment you want to activate the server under, and then select 
   the *Registration* tab. This tab is where you can download and manage the 
   environment's token. In the Registration tab's *Services* section, first 
   select the LCS services that you want to use with all DXP servers that 
   connect to this environment. All services are selected by default. Your 
   selections of LCS services here are embedded in the token file. 

    ![Figure 4: An environment's Registration tab lets you manage the token file used to connect DXP instances to the environment.](../../../../images-dxp/lcs-registration.png) 

5. Now you must use the Registration tab to generate, regenerate, and/or 
   download the token file. The actions you take here depend on two things: what 
   you did in the previous step and whether the environment has a token file 
   already. Note that if the environment already has a token file, the Generate 
   Token button is replaced by the Download Token button: 

    - **No existing token file:** Generate the token by clicking *Generate 
      Token*. 
    - **Existing token file, and changed selections of LCS services:** 
      Regenerate the token by clicking *Regenerate Token*. Note that if you 
      regenerate the token, all DXP servers that currently use it will be 
      disconnected from LCS and won't be able to reconnect until receiving the 
      new token. 
    - **Existing token file, and no changes to selections of LCS services:** No 
      token generation or regeneration is required.

    Once you've taken the appropriate action, download the token file by 
    clicking the *Download Token* button at the bottom of the screen. 
    [Click here](/discover/deployment/-/knowledge_base/7-0/using-lcs#using-environment-tokens) 
    for more information on environment tokens.

6. Shut down your DXP instance if it's running. Place the token file in your 
   instance's `[Liferay_Home]/data` folder, and then start the instance. On 
   startup, the LCS client app uses the environment token to activate your DXP 
   instance in the LCS environment that corresponds to the token. 

7. Celebrate! Your DXP server is activated and connected to LCS. 

In your DXP instance, you can view your LCS connection status in the LCS client 
app. Access the client by clicking *Control Panel* &rarr; *Configuration* &rarr; 
*Liferay Connected Services*. Note that you can change which LCS services are 
enabled for your DXP instance by clicking the *Configure Services* link. 

Here's a full description of what a connected LCS client app displays: 

- **Connection Uptime:** The duration of the client's connection with LCS.
- **Last Message Received:** The time the latest message was received from LCS.
- **Services:** The LCS services enabled for this DXP instance. Note that all 
  DXP instances that connect to the same LCS environment must use the same set 
  of LCS services. LCS services can't be enabled on an instance-by-instance 
  basis. 
- **Project Home:** This link takes you to this server's LCS project. 
  The project home in LCS is also called the *dashboard*. 
- **Environment:** This link takes you to this server's LCS environment.
- **Server Dashboard:** This link takes you to the server on LCS. 

![Figure 5: The server is connected to LCS.](../../../../images-dxp/lcs-server-connected.png)

Awesome! Now you know how to use environment tokens in LCS to activate your DXP 
server. 

For information on using the other features of LCS, see 
[the next article](/discover/deployment/-/knowledge_base/7-0/using-lcs). 

## Activating a Developer Instance

The steps above show how to activate DXP instances for use in production 
environments. To activate DXP on a local workstation for testing or development 
purposes, you don't need to use LCS. Instead, create a ticket in 
[LESA](https://web.liferay.com/group/customer/support/-/support/ticket) 
to request an activation key. When creating this ticket, select *Activation Key* 
in the *Select a component* field. When you receive your key, place it in your 
local DXP instance's `deploy` folder. 

