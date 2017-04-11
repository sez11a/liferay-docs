# Advanced Monitoring: APM Tools - Dynatrace
 Advanced performance monitoring tools like [Dynatrace's](https://www.dynatrace.com) are available for system administrators looking for more detailed information about how their @product@ servers and instances are performing. Using Dynatrace's web-based dashboards allows system administrators to view all information on various aspects such as memory leaks, garbage collection, CPU levels, and heap dumps analyses. Each dashboard is its own detailed report. One huge advantage of using Dynatrace is that Dynatrace offers a real time connection (compared to competitors who conduct sampling instead). System administrators can view very up to date information on @product@ servers' performance metrics down to the individual user. 
 
 To use Dynatrace's dashboards with @product@, three things are needed: a Dynatrace client which is the user interface (UI), a Dynatrace license, and the FastPack. The FastPack is a deployable agent on @product@. 
 
 Lastly, system administrators should also sign up for a Dynatrace account if they have not already done so; this way they can have access to Dynatrace's support teams.

## Download
The FastPack is available for download on the [Dynatrace site](https://community.dynatrace.com/community/display/DL/Liferay+Digital+Enterprise+FastPack). 


## Dashboards
The Dynatrace Fastpack offers multiple dashboards to best depict Liferay performances.


## Deployment
0. Install Java JDK 1.8.x
1. Install the Dynatrace environment on your local machine for evaluation purposes. Follow the steps outlined from the [Dynatrace Installation Step 1](https://community.compuwareapm.com/community/display/EVAL/Step+1+-+Download+and+install+dynaTrace). The installation files are available for Windows, Unix, and Linux systems.
2. Start the Dynatrace instance and deploy the Dynatrace trial license. (See below.)
3. Deploy the Dynatrace FastPack agent to the @product@ servers. The agent sits on top of existing infrastructure. 
4. Connect the @product@ with the Dynatrace agent to the Dynatrace instance. The steps are available on [Dynatrace Installation Step 3](https://community.dynatrace.com/community/display/EVAL/Step+3+-+Connect+Agent+to+Dynatrace). Click the Application Servers tab on the right. The example uses Apache Tomcat but according to Dynatrace, the steps are virtually the same for JBoss and WebSphere. 
5. After you import the fast pack, you will need to:    
    a. Select the Liferay profile as the the only active system profile in Dynatrace, and    
    b. Restart the application server.
6. Only then your Liferay agent will start sending data into the newly imported profile.


## Import Dynatrace license

Whenever you try to connect to Dynatrace, you will be asked to import a valid Dynatrace license. Either create a trial one on Dynatrace community pages or request one from your Dynatrace contact.

## Security
Dynatrace will mask confidential data. 

## Resources
1. [Dynatrace Community](https://community.compuwareapm.com)
2. [Dynatrace Installation Step 1](https://community.compuwareapm.com/community/display/EVAL/Step+1+-+Download+and+install+dynaTrace)
3. [Dynatrace Installation Step 2](https://community.compuwareapm.com/community/display/EVAL/Step+2+-+Activate+License+Key)
4. [Dynatrace Installation Step 3](https://community.dynatrace.com/community/display/EVAL/Step+3+-+Connect+Agent+to+Dynatrace) 