package com.balintimes.erp.util.json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
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

    public static <T> List<T> ToList(String content, Class<T> valueType) {

        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(dateFormat);
        }

        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametrizedType(List.class, List.class, valueType);
            return objectMapper.readValue(content, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String GetFieldData(String data, String key) {

        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(dateFormat);
        }
        try {
            JsonNode root = objectMapper.readTree(data);

            String value = root.get("data").toString();
            return value;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
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

    public static Map<String, Object> ToMap(String content) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(dateFormat);

        }

        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) objectMapper
                    .readValue(content, Map.class);
            return map;
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
