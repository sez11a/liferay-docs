package com.liferay.docs.headless.guestbook.resource.v1_0;

import com.liferay.docs.headless.guestbook.dto.v1_0.Guestbook;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.UriInfo;

import org.osgi.annotation.versioning.ProviderType;

/**
 * To access this resource, run:
 *
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/headless-guestbook/v1.0
 *
 * @author Rich Sezov
 * @generated
 */
@Generated("")
@ProviderType
public interface GuestbookResource {

	public Page<Guestbook> getGuestbooksPage(
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception;

	public Guestbook postGuestbook(Guestbook guestbook) throws Exception;

	public void deleteGuestbook(String guestbookId) throws Exception;

	public Guestbook getGuestbook(String guestbookId) throws Exception;

	public Guestbook patchGuestbook(String guestbookId, Guestbook guestbook)
		throws Exception;

	public Guestbook putGuestbook(String guestbookId, Guestbook guestbook)
		throws Exception;

	public default void setContextAcceptLanguage(
		AcceptLanguage contextAcceptLanguage) {
	}

	public void setContextCompany(Company contextCompany);

	public default void setContextHttpServletRequest(
		HttpServletRequest contextHttpServletRequest) {
	}

	public default void setContextHttpServletResponse(
		HttpServletResponse contextHttpServletResponse) {
	}

	public default void setContextUriInfo(UriInfo contextUriInfo) {
	}

	public void setContextUser(User contextUser);

}