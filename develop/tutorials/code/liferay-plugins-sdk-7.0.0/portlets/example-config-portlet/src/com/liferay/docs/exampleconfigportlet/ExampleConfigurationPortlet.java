package com.liferay.docs.exampleconfigportlet;

import java.io.IOException;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

import com.liferay.docs.exampleconfigportlet.configuration.ExampleConfiguration;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import aQute.bnd.annotation.metatype.Configurable;

@Component(
	configurationPid = "com.liferay.docs.exampleconfigportlet.configuration.ExampleConfiguration",
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Example Config Portlet",
		"javax.portlet.init-param.config-template=/configuration.jsp",
        "javax.portlet.init-param.template-path=/",
        "javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ExampleConfigurationPortlet extends MVCPortlet {
	
	public String getDefaultLanguageLabel(Map labels) {
		return (String) labels.get(_configuration.defaultLanguage());
	}
	
	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_configuration = Configurable.createConfigurable(
            ExampleConfiguration.class, properties);
	}
	
	@Override
	public void doView(RenderRequest request, RenderResponse response)
		throws PortletException, IOException {

			request.setAttribute(
                ExampleConfiguration.class.getName(), _configuration);
		
		super.doView(request, response);
	}
	
	private volatile ExampleConfiguration _configuration;

}
