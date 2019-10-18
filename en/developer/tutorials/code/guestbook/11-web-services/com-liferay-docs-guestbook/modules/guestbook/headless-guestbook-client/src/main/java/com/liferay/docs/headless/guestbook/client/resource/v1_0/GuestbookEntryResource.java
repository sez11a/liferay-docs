package com.liferay.docs.headless.guestbook.client.resource.v1_0;

import com.liferay.docs.headless.guestbook.client.dto.v1_0.GuestbookEntry;
import com.liferay.docs.headless.guestbook.client.http.HttpInvoker;
import com.liferay.docs.headless.guestbook.client.pagination.Page;
import com.liferay.docs.headless.guestbook.client.pagination.Pagination;
import com.liferay.docs.headless.guestbook.client.serdes.v1_0.GuestbookEntrySerDes;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Generated;

/**
 * @author Rich Sezov
 * @generated
 */
@Generated("")
public interface GuestbookEntryResource {

	public static Builder builder() {
		return new Builder();
	}

	public Page<GuestbookEntry> getGuestbookEntriesPage(
			Long guestbookId, String search, Pagination pagination,
			String sortString)
		throws Exception;

	public HttpInvoker.HttpResponse getGuestbookEntriesPageHttpResponse(
			Long guestbookId, String search, Pagination pagination,
			String sortString)
		throws Exception;

	public GuestbookEntry postGuestbookEntry(
			Long guestbookId, GuestbookEntry guestbookEntry)
		throws Exception;

	public HttpInvoker.HttpResponse postGuestbookEntryHttpResponse(
			Long guestbookId, GuestbookEntry guestbookEntry)
		throws Exception;

	public void deleteGuestbookEntryGuestbookEntry(
			Long guestbookId, String guestbookEntryId)
		throws Exception;

	public HttpInvoker.HttpResponse
			deleteGuestbookEntryGuestbookEntryHttpResponse(
				Long guestbookId, String guestbookEntryId)
		throws Exception;

	public GuestbookEntry getGuestbookEntryGuestbookEntry(
			String guestbookEntryId, Long guestbookId)
		throws Exception;

	public HttpInvoker.HttpResponse getGuestbookEntryGuestbookEntryHttpResponse(
			String guestbookEntryId, Long guestbookId)
		throws Exception;

	public GuestbookEntry patchGuestbookEntryGuestbookEntry(
			Long guestbookId, String guestbookEntryId,
			GuestbookEntry guestbookEntry)
		throws Exception;

	public HttpInvoker.HttpResponse
			patchGuestbookEntryGuestbookEntryHttpResponse(
				Long guestbookId, String guestbookEntryId,
				GuestbookEntry guestbookEntry)
		throws Exception;

	public GuestbookEntry putGuestbookEntryGuestbookEntry(
			Long guestbookId, String guestbookEntryId,
			GuestbookEntry guestbookEntry)
		throws Exception;

	public HttpInvoker.HttpResponse putGuestbookEntryGuestbookEntryHttpResponse(
			Long guestbookId, String guestbookEntryId,
			GuestbookEntry guestbookEntry)
		throws Exception;

	public static class Builder {

		public Builder authentication(String login, String password) {
			_login = login;
			_password = password;

			return this;
		}

		public GuestbookEntryResource build() {
			return new GuestbookEntryResourceImpl(this);
		}

		public Builder endpoint(String host, int port, String scheme) {
			_host = host;
			_port = port;
			_scheme = scheme;

			return this;
		}

		public Builder header(String key, String value) {
			_headers.put(key, value);

			return this;
		}

		public Builder locale(Locale locale) {
			_locale = locale;

			return this;
		}

		public Builder parameter(String key, String value) {
			_parameters.put(key, value);

			return this;
		}

		private Builder() {
		}

		private Map<String, String> _headers = new LinkedHashMap<>();
		private String _host = "localhost";
		private Locale _locale;
		private String _login = "test@liferay.com";
		private String _password = "test";
		private Map<String, String> _parameters = new LinkedHashMap<>();
		private int _port = 8080;
		private String _scheme = "http";

	}

	public static class GuestbookEntryResourceImpl
		implements GuestbookEntryResource {

		public Page<GuestbookEntry> getGuestbookEntriesPage(
				Long guestbookId, String search, Pagination pagination,
				String sortString)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getGuestbookEntriesPageHttpResponse(
					guestbookId, search, pagination, sortString);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			return Page.of(content, GuestbookEntrySerDes::toDTO);
		}

		public HttpInvoker.HttpResponse getGuestbookEntriesPageHttpResponse(
				Long guestbookId, String search, Pagination pagination,
				String sortString)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (search != null) {
				httpInvoker.parameter("search", String.valueOf(search));
			}

			if (pagination != null) {
				httpInvoker.parameter(
					"page", String.valueOf(pagination.getPage()));
				httpInvoker.parameter(
					"pageSize", String.valueOf(pagination.getPageSize()));
			}

			if (sortString != null) {
				httpInvoker.parameter("sort", sortString);
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/headless-guestbook/v1.0/guestbooks/{guestbookId}/entries/",
				guestbookId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public GuestbookEntry postGuestbookEntry(
				Long guestbookId, GuestbookEntry guestbookEntry)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				postGuestbookEntryHttpResponse(guestbookId, guestbookEntry);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return GuestbookEntrySerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw e;
			}
		}

		public HttpInvoker.HttpResponse postGuestbookEntryHttpResponse(
				Long guestbookId, GuestbookEntry guestbookEntry)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(guestbookEntry.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/headless-guestbook/v1.0/guestbooks/{guestbookId}/entries/",
				guestbookId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteGuestbookEntryGuestbookEntry(
				Long guestbookId, String guestbookEntryId)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteGuestbookEntryGuestbookEntryHttpResponse(
					guestbookId, guestbookEntryId);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse
				deleteGuestbookEntryGuestbookEntryHttpResponse(
					Long guestbookId, String guestbookEntryId)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/headless-guestbook/v1.0/guestbooks/{guestbookId}/entries/{guestbookEntryId}",
				guestbookId, guestbookEntryId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public GuestbookEntry getGuestbookEntryGuestbookEntry(
				String guestbookEntryId, Long guestbookId)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getGuestbookEntryGuestbookEntryHttpResponse(
					guestbookEntryId, guestbookId);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return GuestbookEntrySerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw e;
			}
		}

		public HttpInvoker.HttpResponse
				getGuestbookEntryGuestbookEntryHttpResponse(
					String guestbookEntryId, Long guestbookId)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/headless-guestbook/v1.0/guestbooks/{guestbookId}/entries/{guestbookEntryId}",
				guestbookEntryId, guestbookId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public GuestbookEntry patchGuestbookEntryGuestbookEntry(
				Long guestbookId, String guestbookEntryId,
				GuestbookEntry guestbookEntry)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				patchGuestbookEntryGuestbookEntryHttpResponse(
					guestbookId, guestbookEntryId, guestbookEntry);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return GuestbookEntrySerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw e;
			}
		}

		public HttpInvoker.HttpResponse
				patchGuestbookEntryGuestbookEntryHttpResponse(
					Long guestbookId, String guestbookEntryId,
					GuestbookEntry guestbookEntry)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(guestbookEntry.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PATCH);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/headless-guestbook/v1.0/guestbooks/{guestbookId}/entries/{guestbookEntryId}",
				guestbookId, guestbookEntryId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public GuestbookEntry putGuestbookEntryGuestbookEntry(
				Long guestbookId, String guestbookEntryId,
				GuestbookEntry guestbookEntry)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putGuestbookEntryGuestbookEntryHttpResponse(
					guestbookId, guestbookEntryId, guestbookEntry);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return GuestbookEntrySerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw e;
			}
		}

		public HttpInvoker.HttpResponse
				putGuestbookEntryGuestbookEntryHttpResponse(
					Long guestbookId, String guestbookEntryId,
					GuestbookEntry guestbookEntry)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(guestbookEntry.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/headless-guestbook/v1.0/guestbooks/{guestbookId}/entries/{guestbookEntryId}",
				guestbookId, guestbookEntryId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		private GuestbookEntryResourceImpl(Builder builder) {
			_builder = builder;
		}

		private static final Logger _logger = Logger.getLogger(
			GuestbookEntryResource.class.getName());

		private Builder _builder;

	}

}