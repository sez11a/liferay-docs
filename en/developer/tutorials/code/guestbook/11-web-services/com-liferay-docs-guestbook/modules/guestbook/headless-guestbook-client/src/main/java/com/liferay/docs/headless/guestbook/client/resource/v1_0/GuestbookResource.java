package com.liferay.docs.headless.guestbook.client.resource.v1_0;

import com.liferay.docs.headless.guestbook.client.dto.v1_0.Guestbook;
import com.liferay.docs.headless.guestbook.client.http.HttpInvoker;
import com.liferay.docs.headless.guestbook.client.pagination.Page;
import com.liferay.docs.headless.guestbook.client.pagination.Pagination;
import com.liferay.docs.headless.guestbook.client.serdes.v1_0.GuestbookSerDes;

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
public interface GuestbookResource {

	public static Builder builder() {
		return new Builder();
	}

	public Page<Guestbook> getGuestbooksPage(
			String search, String filterString, Pagination pagination,
			String sortString)
		throws Exception;

	public HttpInvoker.HttpResponse getGuestbooksPageHttpResponse(
			String search, String filterString, Pagination pagination,
			String sortString)
		throws Exception;

	public Guestbook postGuestbook(Guestbook guestbook) throws Exception;

	public HttpInvoker.HttpResponse postGuestbookHttpResponse(
			Guestbook guestbook)
		throws Exception;

	public void deleteGuestbook(String guestbookId) throws Exception;

	public HttpInvoker.HttpResponse deleteGuestbookHttpResponse(
			String guestbookId)
		throws Exception;

	public Guestbook getGuestbook(String guestbookId) throws Exception;

	public HttpInvoker.HttpResponse getGuestbookHttpResponse(String guestbookId)
		throws Exception;

	public Guestbook patchGuestbook(String guestbookId, Guestbook guestbook)
		throws Exception;

	public HttpInvoker.HttpResponse patchGuestbookHttpResponse(
			String guestbookId, Guestbook guestbook)
		throws Exception;

	public Guestbook putGuestbook(String guestbookId, Guestbook guestbook)
		throws Exception;

	public HttpInvoker.HttpResponse putGuestbookHttpResponse(
			String guestbookId, Guestbook guestbook)
		throws Exception;

	public static class Builder {

		public Builder authentication(String login, String password) {
			_login = login;
			_password = password;

			return this;
		}

		public GuestbookResource build() {
			return new GuestbookResourceImpl(this);
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

	public static class GuestbookResourceImpl implements GuestbookResource {

		public Page<Guestbook> getGuestbooksPage(
				String search, String filterString, Pagination pagination,
				String sortString)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getGuestbooksPageHttpResponse(
					search, filterString, pagination, sortString);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			return Page.of(content, GuestbookSerDes::toDTO);
		}

		public HttpInvoker.HttpResponse getGuestbooksPageHttpResponse(
				String search, String filterString, Pagination pagination,
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

			if (filterString != null) {
				httpInvoker.parameter("filter", filterString);
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
					_builder._port + "/o/headless-guestbook/v1.0/guestbooks/");

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Guestbook postGuestbook(Guestbook guestbook) throws Exception {
			HttpInvoker.HttpResponse httpResponse = postGuestbookHttpResponse(
				guestbook);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return GuestbookSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw e;
			}
		}

		public HttpInvoker.HttpResponse postGuestbookHttpResponse(
				Guestbook guestbook)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(guestbook.toString(), "application/json");

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
					_builder._port + "/o/headless-guestbook/v1.0/guestbooks/");

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteGuestbook(String guestbookId) throws Exception {
			HttpInvoker.HttpResponse httpResponse = deleteGuestbookHttpResponse(
				guestbookId);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse deleteGuestbookHttpResponse(
				String guestbookId)
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
						"/o/headless-guestbook/v1.0/guestbooks/{guestbookId}",
				guestbookId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Guestbook getGuestbook(String guestbookId) throws Exception {
			HttpInvoker.HttpResponse httpResponse = getGuestbookHttpResponse(
				guestbookId);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return GuestbookSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw e;
			}
		}

		public HttpInvoker.HttpResponse getGuestbookHttpResponse(
				String guestbookId)
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
						"/o/headless-guestbook/v1.0/guestbooks/{guestbookId}",
				guestbookId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Guestbook patchGuestbook(String guestbookId, Guestbook guestbook)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse = patchGuestbookHttpResponse(
				guestbookId, guestbook);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return GuestbookSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw e;
			}
		}

		public HttpInvoker.HttpResponse patchGuestbookHttpResponse(
				String guestbookId, Guestbook guestbook)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(guestbook.toString(), "application/json");

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
						"/o/headless-guestbook/v1.0/guestbooks/{guestbookId}",
				guestbookId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Guestbook putGuestbook(String guestbookId, Guestbook guestbook)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse = putGuestbookHttpResponse(
				guestbookId, guestbook);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return GuestbookSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw e;
			}
		}

		public HttpInvoker.HttpResponse putGuestbookHttpResponse(
				String guestbookId, Guestbook guestbook)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(guestbook.toString(), "application/json");

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
						"/o/headless-guestbook/v1.0/guestbooks/{guestbookId}",
				guestbookId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		private GuestbookResourceImpl(Builder builder) {
			_builder = builder;
		}

		private static final Logger _logger = Logger.getLogger(
			GuestbookResource.class.getName());

		private Builder _builder;

	}

}