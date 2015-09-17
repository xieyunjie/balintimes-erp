package com.balintimes.erp.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by AlexXie on 2015/9/15.
 */
public class test {

    @Test
    public void TestJson() {
        String data = "[{\"name\":\"bmms\",\"permission\":[\"1111\",\"22222\",\"9999\"]},{\"name\":\"crm\",\"permission\":[\"aaaa\",\"bbbb\",\"zzz\"]},{\"name\":\"apk\",\"permission\":[\"eeeee\",\"qqq\",\"popo\"]}]";

        ObjectMapper mapper = new ObjectMapper();

        try {
            System.out.println(mapper.readTree(data).size());
//
//            List<JsonNode> jsonNodeList = mapper.readTree(data).findValues("permission");
//
//
//            for (JsonNode jsonNode : jsonNodeList) {
//                System.out.println(jsonNode);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
