package com.liferay.docs.headless.guestbook.internal.resource.v1_0;

import com.liferay.docs.headless.guestbook.dto.v1_0.Guestbook;
import com.liferay.docs.headless.guestbook.resource.v1_0.GuestbookResource;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.util.TransformUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

import java.util.Collections;
import java.util.List;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.validation.constraints.NotNull;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * @author Rich Sezov
 * @generated
 */
@Generated("")
@Path("/v1.0")
public abstract class BaseGuestbookResourceImpl implements GuestbookResource {

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/headless-guestbook/v1.0/guestbooks/'  -u 'test@liferay.com:test'
	 */
	@Override
	@GET
	@Operation(description = "Retrieves the list of Guestbooks.")
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "search"),
			@Parameter(in = ParameterIn.QUERY, name = "filter"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize"),
			@Parameter(in = ParameterIn.QUERY, name = "sort")
		}
	)
	@Path("/guestbooks/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Guestbook")})
	public Page<Guestbook> getGuestbooksPage(
			@Parameter(hidden = true) @QueryParam("search") String search,
			@Context Filter filter, @Context Pagination pagination,
			@Context Sort[] sorts)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'POST' 'http://localhost:8080/o/headless-guestbook/v1.0/guestbooks/' -d $'{"creator": ___, "id": ___, "name": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Override
	@Consumes({"application/json", "application/xml"})
	@Operation(description = "Create a new Guestbook.")
	@POST
	@Path("/guestbooks/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Guestbook")})
	public Guestbook postGuestbook(Guestbook guestbook) throws Exception {
		return new Guestbook();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'DELETE' 'http://localhost:8080/o/headless-guestbook/v1.0/guestbooks/{guestbookId}'  -u 'test@liferay.com:test'
	 */
	@Override
	@DELETE
	@Operation(
		description = "Deletes the guestbook and returns a 204 if the operation succeeds."
	)
	@Parameters(
		value = {@Parameter(in = ParameterIn.PATH, name = "guestbookId")}
	)
	@Path("/guestbooks/{guestbookId}")
	@Produces("application/json")
	@Tags(value = {@Tag(name = "Guestbook")})
	public void deleteGuestbook(
			@NotNull @Parameter(hidden = true) @PathParam("guestbookId") String
				guestbookId)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/headless-guestbook/v1.0/guestbooks/{guestbookId}'  -u 'test@liferay.com:test'
	 */
	@Override
	@GET
	@Operation(description = "Retrieves the guestbook via its ID.")
	@Parameters(
		value = {@Parameter(in = ParameterIn.PATH, name = "guestbookId")}
	)
	@Path("/guestbooks/{guestbookId}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Guestbook")})
	public Guestbook getGuestbook(
			@NotNull @Parameter(hidden = true) @PathParam("guestbookId") String
				guestbookId)
		throws Exception {

		return new Guestbook();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'PATCH' 'http://localhost:8080/o/headless-guestbook/v1.0/guestbooks/{guestbookId}' -d $'{"creator": ___, "id": ___, "name": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Override
	@Consumes({"application/json", "application/xml"})
	@Operation(
		description = "Replaces the guestbook with the information sent in the request body. Any missing fields are deleted, unless they are required."
	)
	@PATCH
	@Parameters(
		value = {@Parameter(in = ParameterIn.PATH, name = "guestbookId")}
	)
	@Path("/guestbooks/{guestbookId}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Guestbook")})
	public Guestbook patchGuestbook(
			@NotNull @Parameter(hidden = true) @PathParam("guestbookId") String
				guestbookId,
			Guestbook guestbook)
		throws Exception {

		Guestbook existingGuestbook = getGuestbook(guestbookId);

		if (guestbook.getName() != null) {
			existingGuestbook.setName(guestbook.getName());
		}

		preparePatch(guestbook, existingGuestbook);

		return putGuestbook(guestbookId, existingGuestbook);
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'PUT' 'http://localhost:8080/o/headless-guestbook/v1.0/guestbooks/{guestbookId}' -d $'{"creator": ___, "id": ___, "name": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Override
	@Consumes({"application/json", "application/xml"})
	@Operation(
		description = "Replaces the guestbook with the information sent in the request body. Any missing fields are deleted, unless they are required."
	)
	@PUT
	@Parameters(
		value = {@Parameter(in = ParameterIn.PATH, name = "guestbookId")}
	)
	@Path("/guestbooks/{guestbookId}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Guestbook")})
	public Guestbook putGuestbook(
			@NotNull @Parameter(hidden = true) @PathParam("guestbookId") String
				guestbookId,
			Guestbook guestbook)
		throws Exception {

		return new Guestbook();
	}

	public void setContextAcceptLanguage(AcceptLanguage contextAcceptLanguage) {
		this.contextAcceptLanguage = contextAcceptLanguage;
	}

	public void setContextCompany(Company contextCompany) {
		this.contextCompany = contextCompany;
	}

	public void setContextHttpServletRequest(
		HttpServletRequest contextHttpServletRequest) {

		this.contextHttpServletRequest = contextHttpServletRequest;
	}

	public void setContextHttpServletResponse(
		HttpServletResponse contextHttpServletResponse) {

		this.contextHttpServletResponse = contextHttpServletResponse;
	}

	public void setContextUriInfo(UriInfo contextUriInfo) {
		this.contextUriInfo = contextUriInfo;
	}

	public void setContextUser(User contextUser) {
		this.contextUser = contextUser;
	}

	protected void preparePatch(
		Guestbook guestbook, Guestbook existingGuestbook) {
	}

	protected <T, R> List<R> transform(
		java.util.Collection<T> collection,
		UnsafeFunction<T, R, Exception> unsafeFunction) {

		return TransformUtil.transform(collection, unsafeFunction);
	}

	protected <T, R> R[] transform(
		T[] array, UnsafeFunction<T, R, Exception> unsafeFunction,
		Class<?> clazz) {

		return TransformUtil.transform(array, unsafeFunction, clazz);
	}

	protected <T, R> R[] transformToArray(
		java.util.Collection<T> collection,
		UnsafeFunction<T, R, Exception> unsafeFunction, Class<?> clazz) {

		return TransformUtil.transformToArray(
			collection, unsafeFunction, clazz);
	}

	protected <T, R> List<R> transformToList(
		T[] array, UnsafeFunction<T, R, Exception> unsafeFunction) {

		return TransformUtil.transformToList(array, unsafeFunction);
	}

	protected AcceptLanguage contextAcceptLanguage;
	protected Company contextCompany;
	protected HttpServletRequest contextHttpServletRequest;
	protected HttpServletResponse contextHttpServletResponse;
	protected UriInfo contextUriInfo;
	protected User contextUser;

}