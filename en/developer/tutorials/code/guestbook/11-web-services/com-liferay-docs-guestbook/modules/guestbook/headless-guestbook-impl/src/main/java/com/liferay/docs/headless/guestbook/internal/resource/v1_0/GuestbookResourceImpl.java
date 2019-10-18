package com.liferay.docs.headless.guestbook.internal.resource.v1_0;

import com.liferay.docs.headless.guestbook.resource.v1_0.GuestbookResource;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Rich Sezov
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/guestbook.properties",
	scope = ServiceScope.PROTOTYPE, service = GuestbookResource.class
)
public class GuestbookResourceImpl extends BaseGuestbookResourceImpl {
}