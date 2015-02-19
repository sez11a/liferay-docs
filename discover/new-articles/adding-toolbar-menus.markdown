# Adding menus to the portlet toolbar [](id=generating-your-service-layer-liferay-portal-6-2-dev-guide-04-en)

Since Liferay 7.0 developers can extend portlet toolbars by adding new menus in it so users can have common actions at hand all in the same single place. The appearance and position of this elements can be customized using a theme template for the file portlet.vm (or portlet.ftl).

The extensions are done in a non intrusive way using OSGI modules that can be deployed individually. This extension mechanism allows to have, at the same time, multiple modules extending the same portlet toolbar without any conflict among them. This extensions can be applied to both, your own developed portlets but also to Liferay portlets.

One of the cool things of these portlet toolbar extensions is that they can be applied (or not) depending on the portlet view or action. This makes it very powerful, because we may include a menu in the toolbar to add content when the user is in the main view but do not include it when the user is actually adding content. This, again, is fully customizable and it's entirely up to the developer.

##PortletToolbarContributor
The extensions to the portlet toolbar needs to implement the interface `PortletToolbarContributor`. The implementation of method `getPortletTitleMenus` will return the list of menus that will be rendered in the portlet toolbar. This method receives a `portletRequest` so we have access to the some objects like `themeDisplay` or `permissionChecker` and we can generate urls using the portletRequest.

Implementations of `PortletToolbarContributor` needs to be registed in OSGI so they can be used by the portal. The easier way of doing this it's using the OSGI annotation `@Component`.

`PortletToolbarContributorLocator` are responsible for finding the `PortletToolbarLocator` that will be applied for a particular portlet. Liferay provides a `StrutsPortletToolbarContributorLocator` that will return `PortletToolbarContributor` based on the struts action that is being rendered at that moment. Therefore, if you are extending a portlet toolbar that uses struts, you will just need to add 2 properties when registering the `PortletToolbarContributor` as an OSGI Component:
- `javax.portlet.name`: the portlet id of the portlet whose portlet toolbar is being extended
- `struts.action`: the struts action when the portlet toolbar will be extended. This property is optional. If it's not present, the extension will always be rendered (no matter the struts action). If it has an specific value, the extension will be rendered only when that specific struts action is being rendered. If the value is `-` the extension will be included only when the value of the struts action is empty (typically when the first view of the portlet is rendered)

This is an example of a `PortletToolbarContributor` implementation for the Blogs portlet that will include a edit action in the portlet toolbar only when the user is viewing a blog entry and he has permission to edit it.

```
@Component(
	property = {"javax.portlet.name=33", "struts.action=/blogs/view_entry"}
)
public class BlogsPortletToolbarContributor
	implements PortletToolbarContributor {

	@Override
	public List<Menu> getPortletTitleMenus(PortletRequest portletRequest) {
		List<Menu> menus = new ArrayList<>();

		menus.add(getEditEntryMenu(portletRequest));

		return menus;
	}

	protected Menu getEditEntryMenu(PortletRequest portletRequest) {
		MenuItem editEntryMenuItem = getEditEntryMenuItem(portletRequest);
		
		List<MenuItem> menuItems = new ArrayList<>();
		
		menuItems.add(editEntryMenuItem);

		Menu menu = new Menu();

		menu.setDirection("down");
		menu.setExtended(false);
		menu.setIcon("../aui/edit");
		menu.setMenuItems(menuItems);
		menu.setShowArrow(false);

		return menu;
	}

	protected MenuItem getEditEntryMenuItem(
		PortletRequest portletRequest) {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long entryId = ParamUtil.getLong(portletRequest, "entryId");

		if (!hasEditPermission(
				themeDisplay.getPermissionChecker(),
				themeDisplay.getScopeGroupId(), entryId)) {

			return null;
		}

		URLMenuItem urlMenuItem = new URLMenuItem();

		urlMenuItem.setIcon("icon-edit");

		PortletURL portletURL = PortletURLFactoryUtil.create(
			portletRequest, PortletKeys.BLOGS, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter("struts_action", "/blogs/edit_entry");

		portletURL.setParameter("entryId", String.valueOf(entryId));

		String currentURL = PortalUtil.getCurrentURL(portletRequest);

		portletURL.setParameter("redirect", currentURL);
		portletURL.setParameter("backURL", currentURL);

		urlMenuItem.setURL(portletURL.toString());

		return urlMenuItem;
	}
	
	protected boolean hasEditPermission(
		PermissionChecker permissionChecker, long scopeGroupId, long entryId) {

		try {
			_baseModelPermissionChecker.checkBaseModel(
				permissionChecker, scopeGroupId, entryId, ActionKeys.UPDATE);
			
			return true;
		}
		catch (PortalException e) {
			return false;
		}
	}

	@Reference(
		target =
			"(model.class.name=com.liferay.portlet.blogs.model.BlogsEntry)",
		unbind = "-"
	)
	protected void setBaseModelPermissionChecker(
		BaseModelPermissionChecker baseModelPermissionChecker) {

		_baseModelPermissionChecker = baseModelPermissionChecker;
	}

	private BaseModelPermissionChecker _baseModelPermissionChecker;

}
```

##PortletToolbarContributorLocator
As mentioned previously, `PortletToolbarContributorLocator` are responsible for finding the PortletToolbarContributor so they can be included in the portlet toolbar. Liferay provides by default an implementation for struts portlet (`StrutsPortletToolbarContributorLocator`) that finds the `PortletToolbarContributor` for an specific struts action. However, this may be not enough for portlet that uses a different mvc pattern or for complex use cases. For those scenarios, you will need to implement your own `PortletToolbarContributorLocator` that adecquates to your needs.

You will need to create a class implementing the interface PortletToolbarContributorLocator and register it in OSGI so it can be used by the portal when searching for `PortletToolbarContributor`. The implementation will need to use the OSGI Registry to search for `PortletToolbarContributor` according to some criteria defined by the `PortletToolbarContributorLocator` that will need to be matched with the `PortletToolbarContributor`.

This is an example of a `PortletToolbarContributorLocator` that could be used to search for `PortletToolbarContributor` in portlets using Liferay's MVC pattern. The `mvcPath` parameter in the request define the jsp that will be rendered. `LiferayMVCPortletToolbarContributorLocator` will find `PortletToolbarContributor` based on `mvcPath` parameter which will allow to include different portletToolbar extensions based on the jsp that is being rendered at the moment. `PortletToolbarContributor` implementation for this kind of portlets will need to be registered in OSGI using the properties `javax.portlet.name` and `mvc.path` (this parameter is optional and follows the same rules as `struts.action` explained previously for `StrutsPortletToolbarContributorLocator`.

```
@Component(immediate = true)
public class LiferayMVCPortletToolbarContributorLocator
	implements PortletToolbarContributorLocator {

	@Override
	public List<PortletToolbarContributor> getPortletToolbarContributors(
		String portletId, PortletRequest portletRequest) {

		String mvcPath = ParamUtil.getString(
			portletRequest, "mvcPath", "-");

		List<PortletToolbarContributor> portletToolbarContributors =
			_serviceTrackerMap.getService(
				portletId.concat(StringPool.PERIOD).concat(mvcPath));

		if (ListUtil.isEmpty(portletToolbarContributors)) {
			portletToolbarContributors = _serviceTrackerMap.getService(
				portletId);
		}

		return portletToolbarContributors;
	}

	@Activate
	protected void activate() {
		_serviceTrackerMap = ServiceTrackerCollections.multiValueMap(
			PortletToolbarContributor.class, "(javax.portlet.name=*)",
			new ServiceReferenceMapper<String, PortletToolbarContributor>() {

				@Override
				public void map(
					ServiceReference<PortletToolbarContributor>
						serviceReference,
					Emitter<String> emitter) {

					String portletName = (String)serviceReference.getProperty(
						"javax.portlet.name");
					String mvcPath = (String)serviceReference.getProperty(
						"mvc.path");

					String key = portletName;

					if (mvcPath != null) {
						key += StringPool.PERIOD.concat(mvcPath);
					}

					emitter.emit(key);
				}

			});

		_serviceTrackerMap.open();
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private static ServiceTrackerMap<String, List<PortletToolbarContributor>>
		_serviceTrackerMap;

}
```
