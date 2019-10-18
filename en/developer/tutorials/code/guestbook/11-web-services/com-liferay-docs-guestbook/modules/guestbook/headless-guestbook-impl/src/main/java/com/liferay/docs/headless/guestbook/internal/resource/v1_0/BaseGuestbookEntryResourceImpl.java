package com.liferay.docs.headless.guestbook.internal.resource.v1_0;

import com.liferay.docs.headless.guestbook.dto.v1_0.GuestbookEntry;
import com.liferay.docs.headless.guestbook.resource.v1_0.GuestbookEntryResource;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Sort;
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
public abstract class BaseGuestbookEntryResourceImpl
	implements GuestbookEntryResource {

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/headless-guestbook/v1.0/guestbooks/{guestbookId}/entries/'  -u 'test@liferay.com:test'
	 */
	@Override
	@GET
	@Operation(description = "Get entries that belong to a Guestbook")
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "guestbookId"),
			@Parameter(in = ParameterIn.QUERY, name = "search"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize"),
			@Parameter(in = ParameterIn.QUERY, name = "sort")
		}
	)
	@Path("/guestbooks/{guestbookId}/entries/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "GuestbookEntry")})
	public Page<GuestbookEntry> getGuestbookEntriesPage(
			@NotNull @Parameter(hidden = true) @PathParam("guestbookId") Long
				guestbookId,
			@Parameter(hidden = true) @QueryParam("search") String search,
			@Context Pagination pagination, @Context Sort[] sorts)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'POST' 'http://localhost:8080/o/headless-guestbook/v1.0/guestbooks/{guestbookId}/entries/' -d $'{"creator": ___, "email": ___, "guestbook": ___, "id": ___, "message": ___, "name": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Override
	@Consumes({"application/json", "application/xml"})
	@Operation(description = "Create a new Guestbook Entry.")
	@POST
	@Parameters(
		value = {@Parameter(in = ParameterIn.PATH, name = "guestbookId")}
	)
	@Path("/guestbooks/{guestbookId}/entries/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "GuestbookEntry")})
	public GuestbookEntry postGuestbookEntry(
			@NotNull @Parameter(hidden = true) @PathParam("guestbookId") Long
				guestbookId,
			GuestbookEntry guestbookEntry)
		throws Exception {

		return new GuestbookEntry();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'DELETE' 'http://localhost:8080/o/headless-guestbook/v1.0/guestbooks/{guestbookId}/entries/{guestbookEntryId}'  -u 'test@liferay.com:test'
	 */
	@Override
	@DELETE
	@Operation(
		description = "Deletes the guestbook entry and returns a 204 if the operation succeeds."
	)
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "guestbookId"),
			@Parameter(in = ParameterIn.PATH, name = "guestbookEntryId")
		}
	)
	@Path("/guestbooks/{guestbookId}/entries/{guestbookEntryId}")
	@Produces("application/json")
	@Tags(value = {@Tag(name = "GuestbookEntry")})
	public void deleteGuestbookEntryGuestbookEntry(
			@NotNull @Parameter(hidden = true) @PathParam("guestbookId") Long
				guestbookId,
			@NotNull @Parameter(hidden = true) @PathParam("guestbookEntryId")
				String guestbookEntryId)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/headless-guestbook/v1.0/guestbooks/{guestbookId}/entries/{guestbookEntryId}'  -u 'test@liferay.com:test'
	 */
	@Override
	@GET
	@Operation(description = "Retrieves the guestbook entry via its ID.")
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "guestbookEntryId"),
			@Parameter(in = ParameterIn.PATH, name = "guestbookId")
		}
	)
	@Path("/guestbooks/{guestbookId}/entries/{guestbookEntryId}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "GuestbookEntry")})
	public GuestbookEntry getGuestbookEntryGuestbookEntry(
			@NotNull @Parameter(hidden = true) @PathParam("guestbookEntryId")
				String guestbookEntryId,
			@NotNull @Parameter(hidden = true) @PathParam("guestbookId") Long
				guestbookId)
		throws Exception {

		return new GuestbookEntry();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'PATCH' 'http://localhost:8080/o/headless-guestbook/v1.0/guestbooks/{guestbookId}/entries/{guestbookEntryId}' -d $'{"creator": ___, "email": ___, "guestbook": ___, "id": ___, "message": ___, "name": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Override
	@Consumes({"application/json", "application/xml"})
	@Operation(
		description = "Replaces the guestbook entry with the information sent in the request body. Any missing fields are deleted, unless they are required."
	)
	@PATCH
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "guestbookId"),
			@Parameter(in = ParameterIn.PATH, name = "guestbookEntryId")
		}
	)
	@Path("/guestbooks/{guestbookId}/entries/{guestbookEntryId}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "GuestbookEntry")})
	public GuestbookEntry patchGuestbookEntryGuestbookEntry(
			@NotNull @Parameter(hidden = true) @PathParam("guestbookId") Long
				guestbookId,
			@NotNull @Parameter(hidden = true) @PathParam("guestbookEntryId")
				String guestbookEntryId,
			GuestbookEntry guestbookEntry)
		throws Exception {

		GuestbookEntry existingGuestbookEntry = getGuestbookEntry(guestbookId);

		if (guestbookEntry.getEmail() != null) {
			existingGuestbookEntry.setEmail(guestbookEntry.getEmail());
		}

		if (guestbookEntry.getMessage() != null) {
			existingGuestbookEntry.setMessage(guestbookEntry.getMessage());
		}

		if (guestbookEntry.getName() != null) {
			existingGuestbookEntry.setName(guestbookEntry.getName());
		}

		preparePatch(guestbookEntry, existingGuestbookEntry);

		return putGuestbookEntry(guestbookId, existingGuestbookEntry);
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'PUT' 'http://localhost:8080/o/headless-guestbook/v1.0/guestbooks/{guestbookId}/entries/{guestbookEntryId}' -d $'{"creator": ___, "email": ___, "guestbook": ___, "id": ___, "message": ___, "name": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Override
	@Consumes({"application/json", "application/xml"})
	@Operation(
		description = "Replaces the guestbook entry with the information sent in the request body. Any missing fields are deleted, unless they are required."
	)
	@PUT
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "guestbookId"),
			@Parameter(in = ParameterIn.PATH, name = "guestbookEntryId")
		}
	)
	@Path("/guestbooks/{guestbookId}/entries/{guestbookEntryId}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "GuestbookEntry")})
	public GuestbookEntry putGuestbookEntryGuestbookEntry(
			@NotNull @Parameter(hidden = true) @PathParam("guestbookId") Long
				guestbookId,
			@NotNull @Parameter(hidden = true) @PathParam("guestbookEntryId")
				String guestbookEntryId,
			GuestbookEntry guestbookEntry)
		throws Exception {

		return new GuestbookEntry();
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
		GuestbookEntry guestbookEntry, GuestbookEntry existingGuestbookEntry) {
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