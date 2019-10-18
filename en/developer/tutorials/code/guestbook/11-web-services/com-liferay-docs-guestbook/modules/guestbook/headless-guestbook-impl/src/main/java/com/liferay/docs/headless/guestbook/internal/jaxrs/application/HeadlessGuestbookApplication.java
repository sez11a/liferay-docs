package com.liferay.docs.headless.guestbook.internal.jaxrs.application;

import javax.annotation.Generated;

import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;

/**
 * @author Rich Sezov
 * @generated
 */
@Component(
	property = {
		"osgi.jaxrs.application.base=/headless-guestbook",
		"osgi.jaxrs.extension.select=(osgi.jaxrs.name=Liferay.Vulcan)",
		"osgi.jaxrs.name=liferay.Headless.Guestbook"
	},
	service = Application.class
)
@Generated("")
public class HeadlessGuestbookApplication extends Application {
}