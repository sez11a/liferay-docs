package com.liferay.docs.headless.guestbook.internal.graphql.query.v1_0;

import com.liferay.docs.headless.guestbook.dto.v1_0.Guestbook;
import com.liferay.docs.headless.guestbook.dto.v1_0.GuestbookEntry;
import com.liferay.docs.headless.guestbook.resource.v1_0.GuestbookEntryResource;
import com.liferay.docs.headless.guestbook.resource.v1_0.GuestbookResource;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLTypeExtension;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.function.BiFunction;

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
public class Query {

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

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {guestbooks(filter: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public GuestbookPage guestbooks(
			@GraphQLName("search") String search,
			@GraphQLName("filter") String filterString,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_guestbookResourceComponentServiceObjects,
			this::_populateResourceContext,
			guestbookResource -> new GuestbookPage(
				guestbookResource.getGuestbooksPage(
					search,
					_filterBiFunction.apply(guestbookResource, filterString),
					Pagination.of(page, pageSize),
					_sortsBiFunction.apply(guestbookResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {guestbook(guestbookId: ___){name, id, creator}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public Guestbook guestbook(@GraphQLName("guestbookId") String guestbookId)
		throws Exception {

		return _applyComponentServiceObjects(
			_guestbookResourceComponentServiceObjects,
			this::_populateResourceContext,
			guestbookResource -> guestbookResource.getGuestbook(guestbookId));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {guestbookEntries(guestbookId: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public GuestbookEntryPage guestbookEntries(
			@GraphQLName("guestbookId") Long guestbookId,
			@GraphQLName("search") String search,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_guestbookEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			guestbookEntryResource -> new GuestbookEntryPage(
				guestbookEntryResource.getGuestbookEntriesPage(
					guestbookId, search, Pagination.of(page, pageSize),
					_sortsBiFunction.apply(
						guestbookEntryResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {guestbookEntryGuestbookEntry(guestbookEntryId: ___, guestbookId: ___){name, id, email, message, guestbook, creator}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public GuestbookEntry guestbookEntryGuestbookEntry(
			@GraphQLName("guestbookEntryId") String guestbookEntryId,
			@GraphQLName("guestbookId") Long guestbookId)
		throws Exception {

		return _applyComponentServiceObjects(
			_guestbookEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			guestbookEntryResource ->
				guestbookEntryResource.getGuestbookEntryGuestbookEntry(
					guestbookEntryId, guestbookId));
	}

	@GraphQLTypeExtension(Guestbook.class)
	public class GetGuestbookEntriesPageTypeExtension {

		public GetGuestbookEntriesPageTypeExtension(Guestbook guestbook) {
			_guestbook = guestbook;
		}

		@GraphQLField
		public GuestbookEntryPage entries(
				@GraphQLName("search") String search,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page,
				@GraphQLName("sort") String sortsString)
			throws Exception {

			return _applyComponentServiceObjects(
				_guestbookEntryResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				guestbookEntryResource -> new GuestbookEntryPage(
					guestbookEntryResource.getGuestbookEntriesPage(
						_guestbook.getId(), search,
						Pagination.of(page, pageSize),
						_sortsBiFunction.apply(
							guestbookEntryResource, sortsString))));
		}

		private Guestbook _guestbook;

	}

	@GraphQLName("GuestbookPage")
	public class GuestbookPage {

		public GuestbookPage(Page guestbookPage) {
			items = guestbookPage.getItems();
			page = guestbookPage.getPage();
			pageSize = guestbookPage.getPageSize();
			totalCount = guestbookPage.getTotalCount();
		}

		@GraphQLField
		protected java.util.Collection<Guestbook> items;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("GuestbookEntryPage")
	public class GuestbookEntryPage {

		public GuestbookEntryPage(Page guestbookEntryPage) {
			items = guestbookEntryPage.getItems();
			page = guestbookEntryPage.getPage();
			pageSize = guestbookEntryPage.getPageSize();
			totalCount = guestbookEntryPage.getTotalCount();
		}

		@GraphQLField
		protected java.util.Collection<GuestbookEntry> items;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

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
	private BiFunction<Object, String, Filter> _filterBiFunction;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private Company _company;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private UriInfo _uriInfo;
	private User _user;

}