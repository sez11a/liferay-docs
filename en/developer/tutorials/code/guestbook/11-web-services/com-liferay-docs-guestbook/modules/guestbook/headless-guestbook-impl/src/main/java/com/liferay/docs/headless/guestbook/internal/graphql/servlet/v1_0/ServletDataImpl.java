package com.liferay.docs.headless.guestbook.internal.graphql.servlet.v1_0;

import com.liferay.docs.headless.guestbook.internal.graphql.mutation.v1_0.Mutation;
import com.liferay.docs.headless.guestbook.internal.graphql.query.v1_0.Query;
import com.liferay.docs.headless.guestbook.resource.v1_0.GuestbookEntryResource;
import com.liferay.docs.headless.guestbook.resource.v1_0.GuestbookResource;
import com.liferay.portal.vulcan.graphql.servlet.ServletData;

import javax.annotation.Generated;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;

/**
 * @author Rich Sezov
 * @generated
 */
@Component(immediate = true, service = ServletData.class)
@Generated("")
public class ServletDataImpl implements ServletData {

	@Activate
	public void activate(BundleContext bundleContext) {
		Mutation.setGuestbookResourceComponentServiceObjects(
			_guestbookResourceComponentServiceObjects);
		Mutation.setGuestbookEntryResourceComponentServiceObjects(
			_guestbookEntryResourceComponentServiceObjects);

		Query.setGuestbookResourceComponentServiceObjects(
			_guestbookResourceComponentServiceObjects);
		Query.setGuestbookEntryResourceComponentServiceObjects(
			_guestbookEntryResourceComponentServiceObjects);
	}

	@Override
	public Mutation getMutation() {
		return new Mutation();
	}

	/**
	 * @deprecated
	 */
	@Override
	@Deprecated
	public String getPath() {
		return "/headless-guestbook-graphql/v1_0";
	}

	@Override
	public Query getQuery() {
		return new Query();
	}

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<GuestbookResource>
		_guestbookResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<GuestbookEntryResource>
		_guestbookEntryResourceComponentServiceObjects;

}