package com.liferay.docs.headless.guestbook.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.docs.headless.guestbook.client.dto.v1_0.GuestbookEntry;
import com.liferay.docs.headless.guestbook.client.http.HttpInvoker;
import com.liferay.docs.headless.guestbook.client.pagination.Page;
import com.liferay.docs.headless.guestbook.client.pagination.Pagination;
import com.liferay.docs.headless.guestbook.client.resource.v1_0.GuestbookEntryResource;
import com.liferay.docs.headless.guestbook.client.serdes.v1_0.GuestbookEntrySerDes;
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
public abstract class BaseGuestbookEntryResourceTestCase {

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

		_guestbookEntryResource.setContextCompany(testCompany);

		GuestbookEntryResource.Builder builder =
			GuestbookEntryResource.builder();

		guestbookEntryResource = builder.locale(
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

		GuestbookEntry guestbookEntry1 = randomGuestbookEntry();

		String json = objectMapper.writeValueAsString(guestbookEntry1);

		GuestbookEntry guestbookEntry2 = GuestbookEntrySerDes.toDTO(json);

		Assert.assertTrue(equals(guestbookEntry1, guestbookEntry2));
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

		GuestbookEntry guestbookEntry = randomGuestbookEntry();

		String json1 = objectMapper.writeValueAsString(guestbookEntry);
		String json2 = GuestbookEntrySerDes.toJSON(guestbookEntry);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		GuestbookEntry guestbookEntry = randomGuestbookEntry();

		guestbookEntry.setEmail(regex);
		guestbookEntry.setId(regex);
		guestbookEntry.setMessage(regex);
		guestbookEntry.setName(regex);

		String json = GuestbookEntrySerDes.toJSON(guestbookEntry);

		Assert.assertFalse(json.contains(regex));

		guestbookEntry = GuestbookEntrySerDes.toDTO(json);

		Assert.assertEquals(regex, guestbookEntry.getEmail());
		Assert.assertEquals(regex, guestbookEntry.getId());
		Assert.assertEquals(regex, guestbookEntry.getMessage());
		Assert.assertEquals(regex, guestbookEntry.getName());
	}

	@Test
	public void testGetGuestbookEntriesPage() throws Exception {
		Page<GuestbookEntry> page =
			guestbookEntryResource.getGuestbookEntriesPage(
				testGetGuestbookEntriesPage_getGuestbookId(),
				RandomTestUtil.randomString(), Pagination.of(1, 2), null);

		Assert.assertEquals(0, page.getTotalCount());

		Long guestbookId = testGetGuestbookEntriesPage_getGuestbookId();
		Long irrelevantGuestbookId =
			testGetGuestbookEntriesPage_getIrrelevantGuestbookId();

		if ((irrelevantGuestbookId != null)) {
			GuestbookEntry irrelevantGuestbookEntry =
				testGetGuestbookEntriesPage_addGuestbookEntry(
					irrelevantGuestbookId, randomIrrelevantGuestbookEntry());

			page = guestbookEntryResource.getGuestbookEntriesPage(
				irrelevantGuestbookId, null, Pagination.of(1, 2), null);

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantGuestbookEntry),
				(List<GuestbookEntry>)page.getItems());
			assertValid(page);
		}

		GuestbookEntry guestbookEntry1 =
			testGetGuestbookEntriesPage_addGuestbookEntry(
				guestbookId, randomGuestbookEntry());

		GuestbookEntry guestbookEntry2 =
			testGetGuestbookEntriesPage_addGuestbookEntry(
				guestbookId, randomGuestbookEntry());

		page = guestbookEntryResource.getGuestbookEntriesPage(
			guestbookId, null, Pagination.of(1, 2), null);

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(guestbookEntry1, guestbookEntry2),
			(List<GuestbookEntry>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetGuestbookEntriesPageWithPagination() throws Exception {
		Long guestbookId = testGetGuestbookEntriesPage_getGuestbookId();

		GuestbookEntry guestbookEntry1 =
			testGetGuestbookEntriesPage_addGuestbookEntry(
				guestbookId, randomGuestbookEntry());

		GuestbookEntry guestbookEntry2 =
			testGetGuestbookEntriesPage_addGuestbookEntry(
				guestbookId, randomGuestbookEntry());

		GuestbookEntry guestbookEntry3 =
			testGetGuestbookEntriesPage_addGuestbookEntry(
				guestbookId, randomGuestbookEntry());

		Page<GuestbookEntry> page1 =
			guestbookEntryResource.getGuestbookEntriesPage(
				guestbookId, null, Pagination.of(1, 2), null);

		List<GuestbookEntry> guestbookEntries1 =
			(List<GuestbookEntry>)page1.getItems();

		Assert.assertEquals(
			guestbookEntries1.toString(), 2, guestbookEntries1.size());

		Page<GuestbookEntry> page2 =
			guestbookEntryResource.getGuestbookEntriesPage(
				guestbookId, null, Pagination.of(2, 2), null);

		Assert.assertEquals(3, page2.getTotalCount());

		List<GuestbookEntry> guestbookEntries2 =
			(List<GuestbookEntry>)page2.getItems();

		Assert.assertEquals(
			guestbookEntries2.toString(), 1, guestbookEntries2.size());

		Page<GuestbookEntry> page3 =
			guestbookEntryResource.getGuestbookEntriesPage(
				guestbookId, null, Pagination.of(1, 3), null);

		assertEqualsIgnoringOrder(
			Arrays.asList(guestbookEntry1, guestbookEntry2, guestbookEntry3),
			(List<GuestbookEntry>)page3.getItems());
	}

	@Test
	public void testGetGuestbookEntriesPageWithSortDateTime() throws Exception {
		testGetGuestbookEntriesPageWithSort(
			EntityField.Type.DATE_TIME,
			(entityField, guestbookEntry1, guestbookEntry2) -> {
				BeanUtils.setProperty(
					guestbookEntry1, entityField.getName(),
					DateUtils.addMinutes(new Date(), -2));
			});
	}

	@Test
	public void testGetGuestbookEntriesPageWithSortInteger() throws Exception {
		testGetGuestbookEntriesPageWithSort(
			EntityField.Type.INTEGER,
			(entityField, guestbookEntry1, guestbookEntry2) -> {
				BeanUtils.setProperty(
					guestbookEntry1, entityField.getName(), 0);
				BeanUtils.setProperty(
					guestbookEntry2, entityField.getName(), 1);
			});
	}

	@Test
	public void testGetGuestbookEntriesPageWithSortString() throws Exception {
		testGetGuestbookEntriesPageWithSort(
			EntityField.Type.STRING,
			(entityField, guestbookEntry1, guestbookEntry2) -> {
				Class<?> clazz = guestbookEntry1.getClass();

				Method method = clazz.getMethod(
					"get" +
						StringUtil.upperCaseFirstLetter(entityField.getName()));

				Class<?> returnType = method.getReturnType();

				if (returnType.isAssignableFrom(Map.class)) {
					BeanUtils.setProperty(
						guestbookEntry1, entityField.getName(),
						Collections.singletonMap("Aaa", "Aaa"));
					BeanUtils.setProperty(
						guestbookEntry2, entityField.getName(),
						Collections.singletonMap("Bbb", "Bbb"));
				}
				else {
					BeanUtils.setProperty(
						guestbookEntry1, entityField.getName(), "Aaa");
					BeanUtils.setProperty(
						guestbookEntry2, entityField.getName(), "Bbb");
				}
			});
	}

	protected void testGetGuestbookEntriesPageWithSort(
			EntityField.Type type,
			UnsafeTriConsumer
				<EntityField, GuestbookEntry, GuestbookEntry, Exception>
					unsafeTriConsumer)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		Long guestbookId = testGetGuestbookEntriesPage_getGuestbookId();

		GuestbookEntry guestbookEntry1 = randomGuestbookEntry();
		GuestbookEntry guestbookEntry2 = randomGuestbookEntry();

		for (EntityField entityField : entityFields) {
			unsafeTriConsumer.accept(
				entityField, guestbookEntry1, guestbookEntry2);
		}

		guestbookEntry1 = testGetGuestbookEntriesPage_addGuestbookEntry(
			guestbookId, guestbookEntry1);

		guestbookEntry2 = testGetGuestbookEntriesPage_addGuestbookEntry(
			guestbookId, guestbookEntry2);

		for (EntityField entityField : entityFields) {
			Page<GuestbookEntry> ascPage =
				guestbookEntryResource.getGuestbookEntriesPage(
					guestbookId, null, Pagination.of(1, 2),
					entityField.getName() + ":asc");

			assertEquals(
				Arrays.asList(guestbookEntry1, guestbookEntry2),
				(List<GuestbookEntry>)ascPage.getItems());

			Page<GuestbookEntry> descPage =
				guestbookEntryResource.getGuestbookEntriesPage(
					guestbookId, null, Pagination.of(1, 2),
					entityField.getName() + ":desc");

			assertEquals(
				Arrays.asList(guestbookEntry2, guestbookEntry1),
				(List<GuestbookEntry>)descPage.getItems());
		}
	}

	protected GuestbookEntry testGetGuestbookEntriesPage_addGuestbookEntry(
			Long guestbookId, GuestbookEntry guestbookEntry)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetGuestbookEntriesPage_getGuestbookId()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetGuestbookEntriesPage_getIrrelevantGuestbookId()
		throws Exception {

		return null;
	}

	@Test
	public void testGraphQLGetGuestbookEntriesPage() throws Exception {
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
				"guestbookEntries",
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

		JSONObject guestbookEntriesJSONObject = dataJSONObject.getJSONObject(
			"guestbookEntries");

		Assert.assertEquals(0, guestbookEntriesJSONObject.get("totalCount"));

		GuestbookEntry guestbookEntry1 =
			testGraphQLGuestbookEntry_addGuestbookEntry();
		GuestbookEntry guestbookEntry2 =
			testGraphQLGuestbookEntry_addGuestbookEntry();

		jsonObject = JSONFactoryUtil.createJSONObject(
			invoke(graphQLField.toString()));

		dataJSONObject = jsonObject.getJSONObject("data");

		guestbookEntriesJSONObject = dataJSONObject.getJSONObject(
			"guestbookEntries");

		Assert.assertEquals(2, guestbookEntriesJSONObject.get("totalCount"));

		assertEqualsJSONArray(
			Arrays.asList(guestbookEntry1, guestbookEntry2),
			guestbookEntriesJSONObject.getJSONArray("items"));
	}

	@Test
	public void testPostGuestbookEntry() throws Exception {
		GuestbookEntry randomGuestbookEntry = randomGuestbookEntry();

		GuestbookEntry postGuestbookEntry =
			testPostGuestbookEntry_addGuestbookEntry(randomGuestbookEntry);

		assertEquals(randomGuestbookEntry, postGuestbookEntry);
		assertValid(postGuestbookEntry);
	}

	protected GuestbookEntry testPostGuestbookEntry_addGuestbookEntry(
			GuestbookEntry guestbookEntry)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testDeleteGuestbookEntryGuestbookEntry() throws Exception {
		GuestbookEntry guestbookEntry =
			testDeleteGuestbookEntryGuestbookEntry_addGuestbookEntry();

		assertHttpResponseStatusCode(
			204,
			guestbookEntryResource.
				deleteGuestbookEntryGuestbookEntryHttpResponse(
					null, guestbookEntry.getId()));

		assertHttpResponseStatusCode(
			404,
			guestbookEntryResource.getGuestbookEntryGuestbookEntryHttpResponse(
				null, guestbookEntry.getId()));

		assertHttpResponseStatusCode(
			404,
			guestbookEntryResource.getGuestbookEntryGuestbookEntryHttpResponse(
				null, ""));
	}

	protected GuestbookEntry
			testDeleteGuestbookEntryGuestbookEntry_addGuestbookEntry()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetGuestbookEntryGuestbookEntry() throws Exception {
		GuestbookEntry postGuestbookEntry =
			testGetGuestbookEntryGuestbookEntry_addGuestbookEntry();

		GuestbookEntry getGuestbookEntry =
			guestbookEntryResource.getGuestbookEntryGuestbookEntry(
				postGuestbookEntry.getId(),
				postGuestbookEntry.getGuestbookId());

		assertEquals(postGuestbookEntry, getGuestbookEntry);
		assertValid(getGuestbookEntry);
	}

	protected GuestbookEntry
			testGetGuestbookEntryGuestbookEntry_addGuestbookEntry()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetGuestbookEntryGuestbookEntry() throws Exception {
		GuestbookEntry guestbookEntry =
			testGraphQLGuestbookEntry_addGuestbookEntry();

		List<GraphQLField> graphQLFields = getGraphQLFields();

		GraphQLField graphQLField = new GraphQLField(
			"query",
			new GraphQLField(
				"guestbookEntryGuestbookEntry",
				new HashMap<String, Object>() {
					{
						put("guestbookEntryId", guestbookEntry.getId());
						put("guestbookId", guestbookEntry.getGuestbookId());
					}
				},
				graphQLFields.toArray(new GraphQLField[0])));

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			invoke(graphQLField.toString()));

		JSONObject dataJSONObject = jsonObject.getJSONObject("data");

		Assert.assertTrue(
			equalsJSONObject(
				guestbookEntry,
				dataJSONObject.getJSONObject("guestbookEntryGuestbookEntry")));
	}

	@Test
	public void testPatchGuestbookEntryGuestbookEntry() throws Exception {
		GuestbookEntry postGuestbookEntry =
			testPatchGuestbookEntryGuestbookEntry_addGuestbookEntry();

		GuestbookEntry randomPatchGuestbookEntry = randomPatchGuestbookEntry();

		GuestbookEntry patchGuestbookEntry =
			guestbookEntryResource.patchGuestbookEntryGuestbookEntry(
				postGuestbookEntry.getId(), randomPatchGuestbookEntry);

		GuestbookEntry expectedPatchGuestbookEntry =
			(GuestbookEntry)BeanUtils.cloneBean(postGuestbookEntry);

		_beanUtilsBean.copyProperties(
			expectedPatchGuestbookEntry, randomPatchGuestbookEntry);

		GuestbookEntry getGuestbookEntry =
			guestbookEntryResource.getGuestbookEntry(
				patchGuestbookEntry.getId());

		assertEquals(expectedPatchGuestbookEntry, getGuestbookEntry);
		assertValid(getGuestbookEntry);
	}

	protected GuestbookEntry
			testPatchGuestbookEntryGuestbookEntry_addGuestbookEntry()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testPutGuestbookEntryGuestbookEntry() throws Exception {
		GuestbookEntry postGuestbookEntry =
			testPutGuestbookEntryGuestbookEntry_addGuestbookEntry();

		GuestbookEntry randomGuestbookEntry = randomGuestbookEntry();

		GuestbookEntry putGuestbookEntry =
			guestbookEntryResource.putGuestbookEntry(
				postGuestbookEntry.getId(), randomGuestbookEntry);

		assertEquals(randomGuestbookEntry, putGuestbookEntry);
		assertValid(putGuestbookEntry);

		GuestbookEntry getGuestbookEntry =
			guestbookEntryResource.getGuestbookEntry(putGuestbookEntry.getId());

		assertEquals(randomGuestbookEntry, getGuestbookEntry);
		assertValid(getGuestbookEntry);
	}

	protected GuestbookEntry
			testPutGuestbookEntryGuestbookEntry_addGuestbookEntry()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected GuestbookEntry testGraphQLGuestbookEntry_addGuestbookEntry()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(
		GuestbookEntry guestbookEntry1, GuestbookEntry guestbookEntry2) {

		Assert.assertTrue(
			guestbookEntry1 + " does not equal " + guestbookEntry2,
			equals(guestbookEntry1, guestbookEntry2));
	}

	protected void assertEquals(
		List<GuestbookEntry> guestbookEntries1,
		List<GuestbookEntry> guestbookEntries2) {

		Assert.assertEquals(guestbookEntries1.size(), guestbookEntries2.size());

		for (int i = 0; i < guestbookEntries1.size(); i++) {
			GuestbookEntry guestbookEntry1 = guestbookEntries1.get(i);
			GuestbookEntry guestbookEntry2 = guestbookEntries2.get(i);

			assertEquals(guestbookEntry1, guestbookEntry2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<GuestbookEntry> guestbookEntries1,
		List<GuestbookEntry> guestbookEntries2) {

		Assert.assertEquals(guestbookEntries1.size(), guestbookEntries2.size());

		for (GuestbookEntry guestbookEntry1 : guestbookEntries1) {
			boolean contains = false;

			for (GuestbookEntry guestbookEntry2 : guestbookEntries2) {
				if (equals(guestbookEntry1, guestbookEntry2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				guestbookEntries2 + " does not contain " + guestbookEntry1,
				contains);
		}
	}

	protected void assertEqualsJSONArray(
		List<GuestbookEntry> guestbookEntries, JSONArray jsonArray) {

		for (GuestbookEntry guestbookEntry : guestbookEntries) {
			boolean contains = false;

			for (Object object : jsonArray) {
				if (equalsJSONObject(guestbookEntry, (JSONObject)object)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				jsonArray + " does not contain " + guestbookEntry, contains);
		}
	}

	protected void assertValid(GuestbookEntry guestbookEntry) {
		boolean valid = true;

		if (guestbookEntry.getId() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("creator", additionalAssertFieldName)) {
				if (guestbookEntry.getCreator() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("email", additionalAssertFieldName)) {
				if (guestbookEntry.getEmail() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("guestbook", additionalAssertFieldName)) {
				if (guestbookEntry.getGuestbook() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("message", additionalAssertFieldName)) {
				if (guestbookEntry.getMessage() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (guestbookEntry.getName() == null) {
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

	protected void assertValid(Page<GuestbookEntry> page) {
		boolean valid = false;

		java.util.Collection<GuestbookEntry> guestbookEntries = page.getItems();

		int size = guestbookEntries.size();

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

	protected boolean equals(
		GuestbookEntry guestbookEntry1, GuestbookEntry guestbookEntry2) {

		if (guestbookEntry1 == guestbookEntry2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("creator", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						guestbookEntry1.getCreator(),
						guestbookEntry2.getCreator())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("email", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						guestbookEntry1.getEmail(),
						guestbookEntry2.getEmail())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("guestbook", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						guestbookEntry1.getGuestbook(),
						guestbookEntry2.getGuestbook())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("id", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						guestbookEntry1.getId(), guestbookEntry2.getId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("message", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						guestbookEntry1.getMessage(),
						guestbookEntry2.getMessage())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						guestbookEntry1.getName(), guestbookEntry2.getName())) {

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
		GuestbookEntry guestbookEntry, JSONObject jsonObject) {

		for (String fieldName : getAdditionalAssertFieldNames()) {
			if (Objects.equals("email", fieldName)) {
				if (!Objects.deepEquals(
						guestbookEntry.getEmail(),
						jsonObject.getString("email"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("id", fieldName)) {
				if (!Objects.deepEquals(
						guestbookEntry.getId(), jsonObject.getString("id"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("message", fieldName)) {
				if (!Objects.deepEquals(
						guestbookEntry.getMessage(),
						jsonObject.getString("message"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("name", fieldName)) {
				if (!Objects.deepEquals(
						guestbookEntry.getName(),
						jsonObject.getString("name"))) {

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

		if (!(_guestbookEntryResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_guestbookEntryResource;

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
		EntityField entityField, String operator,
		GuestbookEntry guestbookEntry) {

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

		if (entityFieldName.equals("email")) {
			sb.append("'");
			sb.append(String.valueOf(guestbookEntry.getEmail()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("guestbook")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("id")) {
			sb.append("'");
			sb.append(String.valueOf(guestbookEntry.getId()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("message")) {
			sb.append("'");
			sb.append(String.valueOf(guestbookEntry.getMessage()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("name")) {
			sb.append("'");
			sb.append(String.valueOf(guestbookEntry.getName()));
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

	protected GuestbookEntry randomGuestbookEntry() throws Exception {
		return new GuestbookEntry() {
			{
				email = RandomTestUtil.randomString();
				id = RandomTestUtil.randomString();
				message = RandomTestUtil.randomString();
				name = RandomTestUtil.randomString();
			}
		};
	}

	protected GuestbookEntry randomIrrelevantGuestbookEntry() throws Exception {
		GuestbookEntry randomIrrelevantGuestbookEntry = randomGuestbookEntry();

		return randomIrrelevantGuestbookEntry;
	}

	protected GuestbookEntry randomPatchGuestbookEntry() throws Exception {
		return randomGuestbookEntry();
	}

	protected GuestbookEntryResource guestbookEntryResource;
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
		BaseGuestbookEntryResourceTestCase.class);

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
	private
		com.liferay.docs.headless.guestbook.resource.v1_0.GuestbookEntryResource
			_guestbookEntryResource;

}