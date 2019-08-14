---
header-id: configuring-office-365-integration
---

# Configuring Office 365™ Integration

[TOC levels=1-4]

Before you can use Office 365&trade; to create and edit Documents and Media 
files, you must configure @product@ to connect with an application in the 
[Azure portal](https://portal.azure.com/). 

| **Note:** You must be an administrator to complete these steps. 

## Register an application with the Microsoft identity platform

First, you must configure your application with Microsoft identity platform&trade; 
following the steps described in https://docs.microsoft.com/en-gb/graph/auth-register-app-v2. 

## Configuring the Portal

Now that you have your application set up for use with @product@, 
you must connect your installation to that project. You can do this at two scopes: 

1.  Globally, for all instances in your @product@ installation.
2.  At the instance scope, for one or more instances in your @product@ 
    installation. 

You can override the global configuration for one or more instances by 
configuring those instances separately. Similarly, you can configure only the 
instances you want to connect to your application and leave the global 
configuration empty. 

Follow these steps to configure your @product@ installation to connect to your 
application: 

1.  Note that the configuration options are the same in the global and 
    instance-level configurations. 

    To access the global configuration, go to *Control Panel* &rarr; 
    *Configuration* &rarr; *System Settings* &rarr; *Documents and Media*. 

    To access the instance-level configuration, go to *Control Panel* &rarr; 
    *Configuration* &rarr; *Instance Settings* &rarr; *Documents and Media*. 

2.  Under *VIRTUAL INSTANCE SCOPE*, select *OneDrive*. 

3.  Enter your applications's OAuth 2 client ID and client secret into the 
    *Client ID* and *Client Secret* fields. 

4.  Enter your tenant into the *Tenant* field. 

4.  Click *Save*. 

| **Note:** To turn this feature off, delete the client ID, client secret and tenant
| values from the form. 

![Figure 1: Enter your application's OAuth 2 client ID and client secret.](../../../../images/onedrive-system-settings.png)
