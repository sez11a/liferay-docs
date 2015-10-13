/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liferay.docs.exampleconfig.portlet;

import java.io.IOException;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

import com.liferay.docs.exampleconfig.configuration.ExampleConfiguration;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import aQute.bnd.annotation.metatype.Configurable;

@Component(
	configurationPid =
	"com.liferay.docs.exampleconfig.configuration.ExampleConfiguration",
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.resource-bundle=content.Language"
	},
	service = Portlet.class
)
public class ExampleConfigPortlet extends MVCPortlet {
	
	@Override
	public void doView(RenderRequest renderRequest,
		RenderResponse renderResponse) throws IOException, PortletException {

		renderRequest.setAttribute(
			ExampleConfiguration.class.getName(),
			_exampleConfiguration);

		super.doView(renderRequest, renderResponse);
	}
	
	public String getFavoriteColor(Map labels) {
		return (String) labels.get(_exampleConfiguration.favoriteColor());
	}

	@Activate
	@Modified
	protected void activate(Map<Object, Object> properties) {
		_exampleConfiguration = Configurable.createConfigurable(
			ExampleConfiguration.class, properties);
	}

	private volatile ExampleConfiguration _exampleConfiguration;

}