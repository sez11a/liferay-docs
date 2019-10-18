package com.liferay.docs.headless.guestbook.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.docs.headless.guestbook.client.dto.v1_0.Guestbook;
import com.liferay.docs.headless.guestbook.client.http.HttpInvoker;
import com.liferay.docs.headless.guestbook.client.pagination.Page;
import com.liferay.docs.headless.guestbook.client.pagination.Pagination;
import com.liferay.docs.headless.guestbook.client.resource.v1_0.GuestbookResource;
import com.liferay.docs.headless.guestbook.client.serdes.v1_0.GuestbookSerDes;
import com.liferay.petra.function.UnsafeTriConsumer;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.test.log.CaptureAppender;
import com.liferay.portal.test.log.Log4JLoggerTestUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.vulcan.resource.EntityModelResource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Generated;

import javax.ws.rs.core.MultivaluedHashMap;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Level;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Rich Sezov
 * @generated
 */
@Generated("")
public abstract class BaseGuestbookResourceTestCase {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");
	}

	@Before
	public void setUp() throws Exception {
		irrelevantGroup = GroupTestUtil.addGroup();
		testGroup = GroupTestUtil.addGroup();

		testCompany = CompanyLocalServiceUtil.getCompany(
			testGroup.getCompanyId());

		_guestbookResource.setContextCompany(testCompany);

		GuestbookResource.Builder builder = GuestbookResource.builder();

		guestbookResource = builder.locale(
			LocaleUtil.getDefault()
		).build();
	}

	@After
	public void tearDown() throws Exception {
		GroupTestUtil.deleteGroup(irrelevantGroup);
		GroupTestUtil.deleteGroup(testGroup);
	}

	@Test
	public void testClientSerDesToDTO() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				enable(SerializationFeature.INDENT_OUTPUT);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		Guestbook guestbook1 = randomGuestbook();

		String json = objectMapper.writeValueAsString(guestbook1);

		Guestbook guestbook2 = GuestbookSerDes.toDTO(json);

		Assert.assertTrue(equals(guestbook1, guestbook2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		Guestbook guestbook = randomGuestbook();

		String json1 = objectMapper.writeValueAsString(guestbook);
		String json2 = GuestbookSerDes.toJSON(guestbook);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		Guestbook guestbook = randomGuestbook();

		guestbook.setId(regex);
		guestbook.setName(regex);

		String json = GuestbookSerDes.toJSON(guestbook);

		Assert.assertFalse(json.contains(regex));

		guestbook = GuestbookSerDes.toDTO(json);

		Assert.assertEquals(regex, guestbook.getId());
		Assert.assertEquals(regex, guestbook.getName());
	}

	@Test
	public void testGetGuestbooksPage() throws Exception {
		Page<Guestbook> page = guestbookResource.getGuestbooksPage(
			RandomTestUtil.randomString(), null, Pagination.of(1, 2), null);

		Assert.assertEquals(0, page.getTotalCount());

		Guestbook guestbook1 = testGetGuestbooksPage_addGuestbook(
			randomGuestbook());

		Guestbook guestbook2 = testGetGuestbooksPage_addGuestbook(
			randomGuestbook());

		page = guestbookResource.getGuestbooksPage(
			null, null, Pagination.of(1, 2), null);

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(guestbook1, guestbook2),
			(List<Guestbook>)page.getItems());
		assertValid(page);

		guestbookResource.deleteGuestbook(guestbook1.getId());

		guestbookResource.deleteGuestbook(guestbook2.getId());
	}

	@Test
	public void testGetGuestbooksPageWithFilterDateTimeEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DATE_TIME);

		if (entityFields.isEmpty()) {
			return;
		}

		Guestbook guestbook1 = randomGuestbook();

		guestbook1 = testGetGuestbooksPage_addGuestbook(guestbook1);

		for (EntityField entityField : entityFields) {
			Page<Guestbook> page = guestbookResource.getGuestbooksPage(
				null, getFilterString(entityField, "between", guestbook1),
				Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(guestbook1),
				(List<Guestbook>)page.getItems());
		}
	}

	@Test
	public void testGetGuestbooksPageWithFilterStringEquals() throws Exception {
		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.STRING);

		if (entityFields.isEmpty()) {
			return;
		}

		Guestbook guestbook1 = testGetGuestbooksPage_addGuestbook(
			randomGuestbook());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		Guestbook guestbook2 = testGetGuestbooksPage_addGuestbook(
			randomGuestbook());

		for (EntityField entityField : entityFields) {
			Page<Guestbook> page = guestbookResource.getGuestbooksPage(
				null, getFilterString(entityField, "eq", guestbook1),
				Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(guestbook1),
				(List<Guestbook>)page.getItems());
		}
	}

	@Test
	public void testGetGuestbooksPageWithPagination() throws Exception {
		Guestbook guestbook1 = testGetGuestbooksPage_addGuestbook(
			randomGuestbook());

		Guestbook guestbook2 = testGetGuestbooksPage_addGuestbook(
			randomGuestbook());

		Guestbook guestbook3 = testGetGuestbooksPage_addGuestbook(
			randomGuestbook());

		Page<Guestbook> page1 = guestbookResource.getGuestbooksPage(
			null, null, Pagination.of(1, 2), null);

		List<Guestbook> guestbooks1 = (List<Guestbook>)page1.getItems();

		Assert.assertEquals(guestbooks1.toString(), 2, guestbooks1.size());

		Page<Guestbook> page2 = guestbookResource.getGuestbooksPage(
			null, null, Pagination.of(2, 2), null);

		Assert.assertEquals(3, page2.getTotalCount());

		List<Guestbook> guestbooks2 = (List<Guestbook>)page2.getItems();

		Assert.assertEquals(guestbooks2.toString(), 1, guestbooks2.size());

		Page<Guestbook> page3 = guestbookResource.getGuestbooksPage(
			null, null, Pagination.of(1, 3), null);

		assertEqualsIgnoringOrder(
			Arrays.asList(guestbook1, guestbook2, guestbook3),
			(List<Guestbook>)page3.getItems());
	}

	@Test
	public void testGetGuestbooksPageWithSortDateTime() throws Exception {
		testGetGuestbooksPageWithSort(
			EntityField.Type.DATE_TIME,
			(entityField, guestbook1, guestbook2) -> {
				BeanUtils.setProperty(
					guestbook1, entityField.getName(),
					DateUtils.addMinutes(new Date(), -2));
			});
	}

	@Test
	public void testGetGuestbooksPageWithSortInteger() throws Exception {
		testGetGuestbooksPageWithSort(
			EntityField.Type.INTEGER,
			(entityField, guestbook1, guestbook2) -> {
				BeanUtils.setProperty(guestbook1, entityField.getName(), 0);
				BeanUtils.setProperty(guestbook2, entityField.getName(), 1);
			});
	}

	@Test
	public void testGetGuestbooksPageWithSortString() throws Exception {
		testGetGuestbooksPageWithSort(
			EntityField.Type.STRING,
			(entityField, guestbook1, guestbook2) -> {
				Class<?> clazz = guestbook1.getClass();

				Method method = clazz.getMethod(
					"get" +
						StringUtil.upperCaseFirstLetter(entityField.getName()));

				Class<?> returnType = method.getReturnType();

				if (returnType.isAssignableFrom(Map.class)) {
					BeanUtils.setProperty(
						guestbook1, entityField.getName(),
						Collections.singletonMap("Aaa", "Aaa"));
					BeanUtils.setProperty(
						guestbook2, entityField.getName(),
						Collections.singletonMap("Bbb", "Bbb"));
				}
				else {
					BeanUtils.setProperty(
						guestbook1, entityField.getName(), "Aaa");
					BeanUtils.setProperty(
						guestbook2, entityField.getName(), "Bbb");
				}
			});
	}

	protected void testGetGuestbooksPageWithSort(
			EntityField.Type type,
			UnsafeTriConsumer<EntityField, Guestbook, Guestbook, Exception>
				unsafeTriConsumer)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		Guestbook guestbook1 = randomGuestbook();
		Guestbook guestbook2 = randomGuestbook();

		for (EntityField entityField : entityFields) {
			unsafeTriConsumer.accept(entityField, guestbook1, guestbook2);
		}

		guestbook1 = testGetGuestbooksPage_addGuestbook(guestbook1);

		guestbook2 = testGetGuestbooksPage_addGuestbook(guestbook2);

		for (EntityField entityField : entityFields) {
			Page<Guestbook> ascPage = guestbookResource.getGuestbooksPage(
				null, null, Pagination.of(1, 2),
				entityField.getName() + ":asc");

			assertEquals(
				Arrays.asList(guestbook1, guestbook2),
				(List<Guestbook>)ascPage.getItems());

			Page<Guestbook> descPage = guestbookResource.getGuestbooksPage(
				null, null, Pagination.of(1, 2),
				entityField.getName() + ":desc");

			assertEquals(
				Arrays.asList(guestbook2, guestbook1),
				(List<Guestbook>)descPage.getItems());
		}
	}

	protected Guestbook testGetGuestbooksPage_addGuestbook(Guestbook guestbook)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetGuestbooksPage() throws Exception {
		List<GraphQLField> graphQLFields = new ArrayList<>();

		List<GraphQLField> itemsGraphQLFields = getGraphQLFields();

		graphQLFields.add(
			new GraphQLField(
				"items", itemsGraphQLFields.toArray(new GraphQLField[0])));

		graphQLFields.add(new GraphQLField("page"));
		graphQLFields.add(new GraphQLField("totalCount"));

		GraphQLField graphQLField = new GraphQLField(
			"query",
			new GraphQLField(
				"guestbooks",
				new HashMap<String, Object>() {
					{
						put("page", 1);
						put("pageSize", 2);
					}
				},
				graphQLFields.toArray(new GraphQLField[0])));

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			invoke(graphQLField.toString()));

		JSONObject dataJSONObject = jsonObject.getJSONObject("data");

		JSONObject guestbooksJSONObject = dataJSONObject.getJSONObject(
			"guestbooks");

		Assert.assertEquals(0, guestbooksJSONObject.get("totalCount"));

		Guestbook guestbook1 = testGraphQLGuestbook_addGuestbook();
		Guestbook guestbook2 = testGraphQLGuestbook_addGuestbook();

		jsonObject = JSONFactoryUtil.createJSONObject(
			invoke(graphQLField.toString()));

		dataJSONObject = jsonObject.getJSONObject("data");

		guestbooksJSONObject = dataJSONObject.getJSONObject("guestbooks");

		Assert.assertEquals(2, guestbooksJSONObject.get("totalCount"));

		assertEqualsJSONArray(
			Arrays.asList(guestbook1, guestbook2),
			guestbooksJSONObject.getJSONArray("items"));
	}

	@Test
	public void testPostGuestbook() throws Exception {
		Guestbook randomGuestbook = randomGuestbook();

		Guestbook postGuestbook = testPostGuestbook_addGuestbook(
			randomGuestbook);

		assertEquals(randomGuestbook, postGuestbook);
		assertValid(postGuestbook);
	}

	protected Guestbook testPostGuestbook_addGuestbook(Guestbook guestbook)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testDeleteGuestbook() throws Exception {
		Guestbook guestbook = testDeleteGuestbook_addGuestbook();

		assertHttpResponseStatusCode(
			204,
			guestbookResource.deleteGuestbookHttpResponse(guestbook.getId()));

		assertHttpResponseStatusCode(
			404, guestbookResource.getGuestbookHttpResponse(guestbook.getId()));

		assertHttpResponseStatusCode(
			404, guestbookResource.getGuestbookHttpResponse(""));
	}

	protected Guestbook testDeleteGuestbook_addGuestbook() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLDeleteGuestbook() throws Exception {
		Guestbook guestbook = testGraphQLGuestbook_addGuestbook();

		GraphQLField graphQLField = new GraphQLField(
			"mutation",
			new GraphQLField(
				"deleteGuestbook",
				new HashMap<String, Object>() {
					{
						put("guestbookId", guestbook.getId());
					}
				}));

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			invoke(graphQLField.toString()));

		JSONObject dataJSONObject = jsonObject.getJSONObject("data");

		Assert.assertTrue(dataJSONObject.getBoolean("deleteGuestbook"));

		try (CaptureAppender captureAppender =
				Log4JLoggerTestUtil.configureLog4JLogger(
					"graphql.execution.SimpleDataFetcherExceptionHandler",
					Level.WARN)) {

			graphQLField = new GraphQLField(
				"query",
				new GraphQLField(
					"guestbook",
					new HashMap<String, Object>() {
						{
							put("guestbookId", guestbook.getId());
						}
					},
					new GraphQLField("id")));

			jsonObject = JSONFactoryUtil.createJSONObject(
				invoke(graphQLField.toString()));

			JSONArray errorsJSONArray = jsonObject.getJSONArray("errors");

			Assert.assertTrue(errorsJSONArray.length() > 0);
		}
	}

	@Test
	public void testGetGuestbook() throws Exception {
		Guestbook postGuestbook = testGetGuestbook_addGuestbook();

		Guestbook getGuestbook = guestbookResource.getGuestbook(
			postGuestbook.getId());

		assertEquals(postGuestbook, getGuestbook);
		assertValid(getGuestbook);
	}

	protected Guestbook testGetGuestbook_addGuestbook() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetGuestbook() throws Exception {
		Guestbook guestbook = testGraphQLGuestbook_addGuestbook();

		List<GraphQLField> graphQLFields = getGraphQLFields();

		GraphQLField graphQLField = new GraphQLField(
			"query",
			new GraphQLField(
				"guestbook",
				new HashMap<String, Object>() {
					{
						put("guestbookId", guestbook.getId());
					}
				},
				graphQLFields.toArray(new GraphQLField[0])));

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			invoke(graphQLField.toString()));

		JSONObject dataJSONObject = jsonObject.getJSONObject("data");

		Assert.assertTrue(
			equalsJSONObject(
				guestbook, dataJSONObject.getJSONObject("guestbook")));
	}

	@Test
	public void testPatchGuestbook() throws Exception {
		Guestbook postGuestbook = testPatchGuestbook_addGuestbook();

		Guestbook randomPatchGuestbook = randomPatchGuestbook();

		Guestbook patchGuestbook = guestbookResource.patchGuestbook(
			postGuestbook.getId(), randomPatchGuestbook);

		Guestbook expectedPatchGuestbook = (Guestbook)BeanUtils.cloneBean(
			postGuestbook);

		_beanUtilsBean.copyProperties(
			expectedPatchGuestbook, randomPatchGuestbook);

		Guestbook getGuestbook = guestbookResource.getGuestbook(
			patchGuestbook.getId());

		assertEquals(expectedPatchGuestbook, getGuestbook);
		assertValid(getGuestbook);
	}

	protected Guestbook testPatchGuestbook_addGuestbook() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testPutGuestbook() throws Exception {
		Guestbook postGuestbook = testPutGuestbook_addGuestbook();

		Guestbook randomGuestbook = randomGuestbook();

		Guestbook putGuestbook = guestbookResource.putGuestbook(
			postGuestbook.getId(), randomGuestbook);

		assertEquals(randomGuestbook, putGuestbook);
		assertValid(putGuestbook);

		Guestbook getGuestbook = guestbookResource.getGuestbook(
			putGuestbook.getId());

		assertEquals(randomGuestbook, getGuestbook);
		assertValid(getGuestbook);
	}

	protected Guestbook testPutGuestbook_addGuestbook() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Guestbook testGraphQLGuestbook_addGuestbook() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(Guestbook guestbook1, Guestbook guestbook2) {
		Assert.assertTrue(
			guestbook1 + " does not equal " + guestbook2,
			equals(guestbook1, guestbook2));
	}

	protected void assertEquals(
		List<Guestbook> guestbooks1, List<Guestbook> guestbooks2) {

		Assert.assertEquals(guestbooks1.size(), guestbooks2.size());

		for (int i = 0; i < guestbooks1.size(); i++) {
			Guestbook guestbook1 = guestbooks1.get(i);
			Guestbook guestbook2 = guestbooks2.get(i);

			assertEquals(guestbook1, guestbook2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<Guestbook> guestbooks1, List<Guestbook> guestbooks2) {

		Assert.assertEquals(guestbooks1.size(), guestbooks2.size());

		for (Guestbook guestbook1 : guestbooks1) {
			boolean contains = false;

			for (Guestbook guestbook2 : guestbooks2) {
				if (equals(guestbook1, guestbook2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				guestbooks2 + " does not contain " + guestbook1, contains);
		}
	}

	protected void assertEqualsJSONArray(
		List<Guestbook> guestbooks, JSONArray jsonArray) {

		for (Guestbook guestbook : guestbooks) {
			boolean contains = false;

			for (Object object : jsonArray) {
				if (equalsJSONObject(guestbook, (JSONObject)object)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				jsonArray + " does not contain " + guestbook, contains);
		}
	}

	protected void assertValid(Guestbook guestbook) {
		boolean valid = true;

		if (guestbook.getId() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("creator", additionalAssertFieldName)) {
				if (guestbook.getCreator() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (guestbook.getName() == null) {
					valid = false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		Assert.assertTrue(valid);
	}

	protected void assertValid(Page<Guestbook> page) {
		boolean valid = false;

		java.util.Collection<Guestbook> guestbooks = page.getItems();

		int size = guestbooks.size();

		if ((page.getLastPage() > 0) && (page.getPage() > 0) &&
			(page.getPageSize() > 0) && (page.getTotalCount() > 0) &&
			(size > 0)) {

			valid = true;
		}

		Assert.assertTrue(valid);
	}

	protected String[] getAdditionalAssertFieldNames() {
		return new String[0];
	}

	protected List<GraphQLField> getGraphQLFields() {
		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			graphQLFields.add(new GraphQLField(additionalAssertFieldName));
		}

		return graphQLFields;
	}

	protected String[] getIgnoredEntityFieldNames() {
		return new String[0];
	}

	protected boolean equals(Guestbook guestbook1, Guestbook guestbook2) {
		if (guestbook1 == guestbook2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("creator", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						guestbook1.getCreator(), guestbook2.getCreator())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("id", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						guestbook1.getId(), guestbook2.getId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						guestbook1.getName(), guestbook2.getName())) {

					return false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		return true;
	}

	protected boolean equalsJSONObject(
		Guestbook guestbook, JSONObject jsonObject) {

		for (String fieldName : getAdditionalAssertFieldNames()) {
			if (Objects.equals("id", fieldName)) {
				if (!Objects.deepEquals(
						guestbook.getId(), jsonObject.getString("id"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("name", fieldName)) {
				if (!Objects.deepEquals(
						guestbook.getName(), jsonObject.getString("name"))) {

					return false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid field name " + fieldName);
		}

		return true;
	}

	protected java.util.Collection<EntityField> getEntityFields()
		throws Exception {

		if (!(_guestbookResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_guestbookResource;

		EntityModel entityModel = entityModelResource.getEntityModel(
			new MultivaluedHashMap());

		Map<String, EntityField> entityFieldsMap =
			entityModel.getEntityFieldsMap();

		return entityFieldsMap.values();
	}

	protected List<EntityField> getEntityFields(EntityField.Type type)
		throws Exception {

		java.util.Collection<EntityField> entityFields = getEntityFields();

		Stream<EntityField> stream = entityFields.stream();

		return stream.filter(
			entityField ->
				Objects.equals(entityField.getType(), type) &&
				!ArrayUtil.contains(
					getIgnoredEntityFieldNames(), entityField.getName())
		).collect(
			Collectors.toList()
		);
	}

	protected String getFilterString(
		EntityField entityField, String operator, Guestbook guestbook) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("creator")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("id")) {
			sb.append("'");
			sb.append(String.valueOf(guestbook.getId()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("name")) {
			sb.append("'");
			sb.append(String.valueOf(guestbook.getName()));
			sb.append("'");

			return sb.toString();
		}

		throw new IllegalArgumentException(
			"Invalid entity field " + entityFieldName);
	}

	protected String invoke(String query) throws Exception {
		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.body(
			JSONUtil.put(
				"query", query
			).toString(),
			"application/json");
		httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);
		httpInvoker.path("http://localhost:8080/o/graphql");
		httpInvoker.userNameAndPassword("test@liferay.com:test");

		HttpInvoker.HttpResponse httpResponse = httpInvoker.invoke();

		return httpResponse.getContent();
	}

	protected Guestbook randomGuestbook() throws Exception {
		return new Guestbook() {
			{
				id = RandomTestUtil.randomString();
				name = RandomTestUtil.randomString();
			}
		};
	}

	protected Guestbook randomIrrelevantGuestbook() throws Exception {
		Guestbook randomIrrelevantGuestbook = randomGuestbook();

		return randomIrrelevantGuestbook;
	}

	protected Guestbook randomPatchGuestbook() throws Exception {
		return randomGuestbook();
	}

	protected GuestbookResource guestbookResource;
	protected Group irrelevantGroup;
	protected Company testCompany;
	protected Group testGroup;

	protected class GraphQLField {

		public GraphQLField(String key, GraphQLField... graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			GraphQLField... graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = graphQLFields;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(_key);

			if (!_parameterMap.isEmpty()) {
				sb.append("(");

				for (Map.Entry<String, Object> entry :
						_parameterMap.entrySet()) {

					sb.append(entry.getKey());
					sb.append(":");
					sb.append(entry.getValue());
					sb.append(",");
				}

				sb.setLength(sb.length() - 1);

				sb.append(")");
			}

			if (_graphQLFields.length > 0) {
				sb.append("{");

				for (GraphQLField graphQLField : _graphQLFields) {
					sb.append(graphQLField.toString());
					sb.append(",");
				}

				sb.setLength(sb.length() - 1);

				sb.append("}");
			}

			return sb.toString();
		}

		private final GraphQLField[] _graphQLFields;
		private final String _key;
		private final Map<String, Object> _parameterMap;

	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseGuestbookResourceTestCase.class);

	private static BeanUtilsBean _beanUtilsBean = new BeanUtilsBean() {

		@Override
		public void copyProperty(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {

			if (value != null) {
				super.copyProperty(bean, name, value);
			}
		}

	};
	private static DateFormat _dateFormat;

	@Inject
	private com.liferay.docs.headless.guestbook.resource.v1_0.GuestbookResource
		_guestbookResource;

}