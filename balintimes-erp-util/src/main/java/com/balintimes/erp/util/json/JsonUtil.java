package com.balintimes.erp.util.json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

public class JsonUtil {
	private static ObjectMapper objectMapper;

	private final static DateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static <T> T ToObject(String content, Class<T> valueType) {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();

			objectMapper.setDateFormat(dateFormat);

		}

		try {
			return objectMapper.readValue(content, valueType);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String ToJson(Object object) {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
			objectMapper.setDateFormat(dateFormat);

		}
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings("unused")
	public static Map<String, Object> ToMap(String content) {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
			objectMapper.setDateFormat(dateFormat);

		}

		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> map =(Map<String,Object>) objectMapper
					.readValue(content, Map.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
