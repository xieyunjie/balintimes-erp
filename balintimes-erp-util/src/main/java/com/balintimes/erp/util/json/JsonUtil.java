package com.balintimes.erp.util.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class JsonUtil {
    private static ObjectMapper objectMapper;

    private final static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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


}
