package com.liferay.docs.examplejsonserializationportlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.docs.examplejsonserializationportlet.model.Pet;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONSerializer;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=category.sample",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=Example JSON Serialization Portlet",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class ExampleJSONSerializationPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest request, RenderResponse response)
			throws IOException, PortletException {

		// manual serialization
		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
		String exampleInfo = "example information";
		jsonObj.put("exampleInfo", exampleInfo);
		long exampleCompanyId = 12345;
		jsonObj.put("exampleCompanyId", exampleCompanyId);
		
		String jsonString = jsonObj.toString();
		request.setAttribute("JSON_STRING", jsonString);
		
		// loose serialization
		Pet pet1 = new Pet();
		pet1.setName("Rover");
		pet1.setSpecies("dog");
		List<String> foodList1 = new ArrayList<String>();
		foodList1.add("steak");
		foodList1.add("eggs");
		pet1.setFavoriteFoods(foodList1);
		
		Pet pet2 = new Pet();
		pet2.setName("Spot");
		pet2.setSpecies("dog");
		List<String> foodList2 = new ArrayList<String>();
		foodList2.add("cheese");
		foodList2.add("crackers");
		pet2.setFavoriteFoods(foodList2);
		
		Pet pet3 = new Pet();
		pet3.setName("Sparky");
		pet3.setSpecies("cat");
		List<String> foodList3 = new ArrayList<String>();
		foodList3.add("fish");
		foodList3.add("catnip");
		pet3.setFavoriteFoods(foodList3);
		
		String pet1String = JSONFactoryUtil.looseSerialize(pet1);
		request.setAttribute("PET1_STRING", pet1String);

		String pet2String = JSONFactoryUtil.looseSerialize(pet2);
		request.setAttribute("PET2_STRING", pet2String);
		
		String pet3String = JSONFactoryUtil.looseSerialize(pet3);
		request.setAttribute("PET3_STRING", pet3String);
		
		// demonstrate 'include'
		JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
		jsonSerializer.include("favoriteFoods");
		String pet1StringIncludeFoods= jsonSerializer.serialize(pet1);
		
		// This following (commented out) line is a shortcut for the lines above
        // String pet1StringIncludeFoods = JSONFactoryUtil.looseSerialize(pet1, "favoriteFoods");

		request.setAttribute("PET1_STRING_INCLUDE_FOODS", pet1StringIncludeFoods);
		
		super.render(request, response);
	}

}
