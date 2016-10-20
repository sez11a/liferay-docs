#Liferay Public IP Addresses
##Description
Very often a Liferay Portal resides inside a private network and due to a company's security policy, while it can serve content to the public Internet, it cannot access it by default. In such cases, clould-based services, like Portal Activation and Liferay Connected Services may fail if traffic to the correct hostnames or IP addresses is not allowed.

##Resolution
In order to allow the traffic to those servers, the company's network administrator must configure the firewall to allow the server which runs the portal to allow inbound and outbound traffic to the following hostnames and IP addresses:

<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
    text-align: center;
}
caption {
    text-align: left;
}
</style>
 <table >

<tr>
<th>Liferay Hostname</th>
<th>Liferay Public IP Address</th>
</tr>

<tr>
<td>*.liferay.com</td>
<td>38.75.15.0/24 <br/> 173.196.212.0/25</td>
</tr>

<tr>
<td>lcs-gateway.liferay.com</td>
<td>54.235.184.179<br/> 54.243.115.146</td>
</tr>

<tr>
<td>lcs.liferay.com</td>
<td>50.17.211.155 <br/> 75.101.148.56 </td>
</tr>
</table>

##Additional Information
Because Liferay Connected Services is a brand new feature and under heavy development, it is subject to changes in short term. Therefore, the information listed here may not be up-to-date.

If you encounter issues while using the information above, consult the file portlet-ext.properties in the lcs-portlet/WEB-INF/classes folder for the following lines:

	osb.lcs.gateway.web.host.name=lcs-gateway.liferay.com
	osb.lcs.portlet.host.name=lcs.liferay.com

Resolve those hostnames using your operating system lookup tools (like dig, nslookup, etc.) to get their current IP addresses.