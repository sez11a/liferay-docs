package com.liferay.docs.headless.guestbook.client.serdes.v1_0;

import com.liferay.docs.headless.guestbook.client.dto.v1_0.GuestbookEntry;
import com.liferay.docs.headless.guestbook.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Rich Sezov
 * @generated
 */
@Generated("")
public class GuestbookEntrySerDes {

	public static GuestbookEntry toDTO(String json) {
		GuestbookEntryJSONParser guestbookEntryJSONParser =
			new GuestbookEntryJSONParser();

		return guestbookEntryJSONParser.parseToDTO(json);
	}

	public static GuestbookEntry[] toDTOs(String json) {
		GuestbookEntryJSONParser guestbookEntryJSONParser =
			new GuestbookEntryJSONParser();

		return guestbookEntryJSONParser.parseToDTOs(json);
	}

	public static String toJSON(GuestbookEntry guestbookEntry) {
		if (guestbookEntry == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (guestbookEntry.getCreator() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"creator\": ");

			sb.append(String.valueOf(guestbookEntry.getCreator()));
		}

		if (guestbookEntry.getEmail() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"email\": ");

			sb.append("\"");

			sb.append(_escape(guestbookEntry.getEmail()));

			sb.append("\"");
		}

		if (guestbookEntry.getGuestbook() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"guestbook\": ");

			sb.append(String.valueOf(guestbookEntry.getGuestbook()));
		}

		if (guestbookEntry.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append("\"");

			sb.append(_escape(guestbookEntry.getId()));

			sb.append("\"");
		}

		if (guestbookEntry.getMessage() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"message\": ");

			sb.append("\"");

			sb.append(_escape(guestbookEntry.getMessage()));

			sb.append("\"");
		}

		if (guestbookEntry.getName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(guestbookEntry.getName()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		GuestbookEntryJSONParser guestbookEntryJSONParser =
			new GuestbookEntryJSONParser();

		return guestbookEntryJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(GuestbookEntry guestbookEntry) {
		if (guestbookEntry == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (guestbookEntry.getCreator() == null) {
			map.put("creator", null);
		}
		else {
			map.put("creator", String.valueOf(guestbookEntry.getCreator()));
		}

		if (guestbookEntry.getEmail() == null) {
			map.put("email", null);
		}
		else {
			map.put("email", String.valueOf(guestbookEntry.getEmail()));
		}

		if (guestbookEntry.getGuestbook() == null) {
			map.put("guestbook", null);
		}
		else {
			map.put("guestbook", String.valueOf(guestbookEntry.getGuestbook()));
		}

		if (guestbookEntry.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(guestbookEntry.getId()));
		}

		if (guestbookEntry.getMessage() == null) {
			map.put("message", null);
		}
		else {
			map.put("message", String.valueOf(guestbookEntry.getMessage()));
		}

		if (guestbookEntry.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(guestbookEntry.getName()));
		}

		return map;
	}

	public static class GuestbookEntryJSONParser
		extends BaseJSONParser<GuestbookEntry> {

		@Override
		protected GuestbookEntry createDTO() {
			return new GuestbookEntry();
		}

		@Override
		protected GuestbookEntry[] createDTOArray(int size) {
			return new GuestbookEntry[size];
		}

		@Override
		protected void setField(
			GuestbookEntry guestbookEntry, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "creator")) {
				if (jsonParserFieldValue != null) {
					guestbookEntry.setCreator(
						CreatorSerDes.toDTO((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "email")) {
				if (jsonParserFieldValue != null) {
					guestbookEntry.setEmail((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "guestbook")) {
				if (jsonParserFieldValue != null) {
					guestbookEntry.setGuestbook(
						GuestbookSerDes.toDTO((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					guestbookEntry.setId((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "message")) {
				if (jsonParserFieldValue != null) {
					guestbookEntry.setMessage((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					guestbookEntry.setName((String)jsonParserFieldValue);
				}
			}
			else {
				throw new IllegalArgumentException(
					"Unsupported field name " + jsonParserFieldName);
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		string = string.replace("\\", "\\\\");

		return string.replace("\"", "\\\"");
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\":");

			Object value = entry.getValue();

			Class<?> valueClass = value.getClass();

			if (value instanceof Map) {
				sb.append(_toJSON((Map)value));
			}
			else if (valueClass.isArray()) {
				Object[] values = (Object[])value;

				sb.append("[");

				for (int i = 0; i < values.length; i++) {
					sb.append("\"");
					sb.append(_escape(values[i]));
					sb.append("\"");

					if ((i + 1) < values.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else {
				sb.append("\"");
				sb.append(_escape(entry.getValue()));
				sb.append("\"");
			}

			if (iterator.hasNext()) {
				sb.append(",");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}