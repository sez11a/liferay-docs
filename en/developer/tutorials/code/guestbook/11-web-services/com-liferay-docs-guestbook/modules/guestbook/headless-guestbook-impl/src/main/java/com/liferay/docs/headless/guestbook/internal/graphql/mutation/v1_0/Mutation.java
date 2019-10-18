package com.liferay.docs.headless.guestbook.internal.graphql.mutation.v1_0;

import com.liferay.docs.headless.guestbook.dto.v1_0.Guestbook;
import com.liferay.docs.headless.guestbook.dto.v1_0.GuestbookEntry;
import com.liferay.docs.headless.guestbook.resource.v1_0.GuestbookEntryResource;
import com.liferay.docs.headless.guestbook.resource.v1_0.GuestbookResource;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author Rich Sezov
 * @generated
 */
@Generated("")
public class Mutation {

	public static void setGuestbookResourceComponentServiceObjects(
		ComponentServiceObjects<GuestbookResource>
			guestbookResourceComponentServiceObjects) {

		_guestbookResourceComponentServiceObjects =
			guestbookResourceComponentServiceObjects;
	}

	public static void setGuestbookEntryResourceComponentServiceObjects(
		ComponentServiceObjects<GuestbookEntryResource>
			guestbookEntryResourceComponentServiceObjects) {

		_guestbookEntryResourceComponentServiceObjects =
			guestbookEntryResourceComponentServiceObjects;
	}

	@GraphQLField
	public Guestbook createGuestbook(
			@GraphQLName("guestbook") Guestbook guestbook)
		throws Exception {

		return _applyComponentServiceObjects(
			_guestbookResourceComponentServiceObjects,
			this::_populateResourceContext,
			guestbookResource -> guestbookResource.postGuestbook(guestbook));
	}

	@GraphQLField
	public boolean deleteGuestbook(
			@GraphQLName("guestbookId") String guestbookId)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_guestbookResourceComponentServiceObjects,
			this::_populateResourceContext,
			guestbookResource -> guestbookResource.deleteGuestbook(
				guestbookId));

		return true;
	}

	@GraphQLField
	public Guestbook patchGuestbook(
			@GraphQLName("guestbookId") String guestbookId,
			@GraphQLName("guestbook") Guestbook guestbook)
		throws Exception {

		return _applyComponentServiceObjects(
			_guestbookResourceComponentServiceObjects,
			this::_populateResourceContext,
			guestbookResource -> guestbookResource.patchGuestbook(
				guestbookId, guestbook));
	}

	@GraphQLField
	public Guestbook updateGuestbook(
			@GraphQLName("guestbookId") String guestbookId,
			@GraphQLName("guestbook") Guestbook guestbook)
		throws Exception {

		return _applyComponentServiceObjects(
			_guestbookResourceComponentServiceObjects,
			this::_populateResourceContext,
			guestbookResource -> guestbookResource.putGuestbook(
				guestbookId, guestbook));
	}

	@GraphQLField
	public GuestbookEntry createGuestbookEntry(
			@GraphQLName("guestbookId") Long guestbookId,
			@GraphQLName("guestbookEntry") GuestbookEntry guestbookEntry)
		throws Exception {

		return _applyComponentServiceObjects(
			_guestbookEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			guestbookEntryResource -> guestbookEntryResource.postGuestbookEntry(
				guestbookId, guestbookEntry));
	}

	@GraphQLField
	public boolean deleteGuestbookEntryGuestbookEntry(
			@GraphQLName("guestbookId") Long guestbookId,
			@GraphQLName("guestbookEntryId") String guestbookEntryId)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_guestbookEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			guestbookEntryResource ->
				guestbookEntryResource.deleteGuestbookEntryGuestbookEntry(
					guestbookId, guestbookEntryId));

		return true;
	}

	@GraphQLField
	public GuestbookEntry patchGuestbookEntryGuestbookEntry(
			@GraphQLName("guestbookId") Long guestbookId,
			@GraphQLName("guestbookEntryId") String guestbookEntryId,
			@GraphQLName("guestbookEntry") GuestbookEntry guestbookEntry)
		throws Exception {

		return _applyComponentServiceObjects(
			_guestbookEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			guestbookEntryResource ->
				guestbookEntryResource.patchGuestbookEntryGuestbookEntry(
					guestbookId, guestbookEntryId, guestbookEntry));
	}

	@GraphQLField
	public GuestbookEntry updateGuestbookEntryGuestbookEntry(
			@GraphQLName("guestbookId") Long guestbookId,
			@GraphQLName("guestbookEntryId") String guestbookEntryId,
			@GraphQLName("guestbookEntry") GuestbookEntry guestbookEntry)
		throws Exception {

		return _applyComponentServiceObjects(
			_guestbookEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			guestbookEntryResource ->
				guestbookEntryResource.putGuestbookEntryGuestbookEntry(
					guestbookId, guestbookEntryId, guestbookEntry));
	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private <T, E1 extends Throwable, E2 extends Throwable> void
			_applyVoidComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeConsumer<T, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			unsafeFunction.accept(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(GuestbookResource guestbookResource)
		throws Exception {

		guestbookResource.setContextAcceptLanguage(_acceptLanguage);
		guestbookResource.setContextCompany(_company);
		guestbookResource.setContextHttpServletRequest(_httpServletRequest);
		guestbookResource.setContextHttpServletResponse(_httpServletResponse);
		guestbookResource.setContextUriInfo(_uriInfo);
		guestbookResource.setContextUser(_user);
	}

	private void _populateResourceContext(
			GuestbookEntryResource guestbookEntryResource)
		throws Exception {

		guestbookEntryResource.setContextAcceptLanguage(_acceptLanguage);
		guestbookEntryResource.setContextCompany(_company);
		guestbookEntryResource.setContextHttpServletRequest(
			_httpServletRequest);
		guestbookEntryResource.setContextHttpServletResponse(
			_httpServletResponse);
		guestbookEntryResource.setContextUriInfo(_uriInfo);
		guestbookEntryResource.setContextUser(_user);
	}

	private static ComponentServiceObjects<GuestbookResource>
		_guestbookResourceComponentServiceObjects;
	private static ComponentServiceObjects<GuestbookEntryResource>
		_guestbookEntryResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private Company _company;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private UriInfo _uriInfo;
	private User _user;

}