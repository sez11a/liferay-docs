package com.liferay.docs.headless.guestbook.client.serdes.v1_0;

import com.liferay.docs.headless.guestbook.client.dto.v1_0.Guestbook;
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
public class GuestbookSerDes {

	public static Guestbook toDTO(String json) {
		GuestbookJSONParser guestbookJSONParser = new GuestbookJSONParser();

		return guestbookJSONParser.parseToDTO(json);
	}

	public static Guestbook[] toDTOs(String json) {
		GuestbookJSONParser guestbookJSONParser = new GuestbookJSONParser();

		return guestbookJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Guestbook guestbook) {
		if (guestbook == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (guestbook.getCreator() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"creator\": ");

			sb.append(String.valueOf(guestbook.getCreator()));
		}

		if (guestbook.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append("\"");

			sb.append(_escape(guestbook.getId()));

			sb.append("\"");
		}

		if (guestbook.getName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(guestbook.getName()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		GuestbookJSONParser guestbookJSONParser = new GuestbookJSONParser();

		return guestbookJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Guestbook guestbook) {
		if (guestbook == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (guestbook.getCreator() == null) {
			map.put("creator", null);
		}
		else {
			map.put("creator", String.valueOf(guestbook.getCreator()));
		}

		if (guestbook.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(guestbook.getId()));
		}

		if (guestbook.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(guestbook.getName()));
		}

		return map;
	}

	public static class GuestbookJSONParser extends BaseJSONParser<Guestbook> {

		@Override
		protected Guestbook createDTO() {
			return new Guestbook();
		}

		@Override
		protected Guestbook[] createDTOArray(int size) {
			return new Guestbook[size];
		}

		@Override
		protected void setField(
			Guestbook guestbook, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "creator")) {
				if (jsonParserFieldValue != null) {
					guestbook.setCreator(
						CreatorSerDes.toDTO((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					guestbook.setId((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					guestbook.setName((String)jsonParserFieldValue);
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