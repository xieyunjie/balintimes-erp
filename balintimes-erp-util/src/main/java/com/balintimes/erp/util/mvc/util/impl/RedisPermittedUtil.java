package com.balintimes.erp.util.mvc.util.impl;

import com.balintimes.erp.util.json.JsonUtil;
import com.balintimes.erp.util.mvc.util.PermittedUtil;
import com.balintimes.erp.util.redis.RedisUserUtil;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by AlexXie on 2015/9/6.
 */
public class RedisPermittedUtil implements PermittedUtil {

    private RedisUserUtil redisUserUtil;
    private String appCode = "crm";

    private static final String OR_OPERATOR = " | ";
    private static final String AND_OPERATOR = " & ";
    private static final String NOT_OPERATOR = "!";

    public boolean isPermitted(HttpServletRequest request, HttpServletResponse response, String permissionValue) {

        HttpServletRequest httpRequest = request;
        Set<String> userPermissions;
        String data = redisUserUtil.GetRedisUserApps(httpRequest.getHeader(RedisUserUtil.GetRedisTokenName()));


        userPermissions = this.takeUserPermission(data);
        if (userPermissions.size() == 0) {
            return true;
        }

        if (permissionValue.contains(OR_OPERATOR)) {
            String[] permissions = permissionValue.split(OR_OPERATOR);
            for (String orPermission : permissions) {
                if (isPermittedWithNotOperator(userPermissions, orPermission)) {
                    return true;
                }
            }
            return false;
        } else if (permissionValue.contains(AND_OPERATOR)) {
            String[] permissions = permissionValue.split(AND_OPERATOR);
            for (String orPermission : permissions) {
                if (!isPermittedWithNotOperator(userPermissions, orPermission)) {
                    return false;
                }
            }
            return true;
        } else {
            return isPermittedWithNotOperator(userPermissions, permissionValue);
        }
    }

    private Set<String> takeUserPermission(String data) {
        Set<String> userPermissions = new HashSet<String>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(data);

            int treeNodeSize = root.size();

            for (int i = 0; i < treeNodeSize; i++) {
                JsonNode jsonNode = root.get(i);
                if (jsonNode.get("code").asText().equals(appCode)) {
                    String p = jsonNode.get("permissions").toString();
                    userPermissions = JsonUtil.ToObject(p, Set.class);

                    return userPermissions;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userPermissions;
    }

    private boolean isPermittedWithNotOperator(Set<String> userPermissions, String permission) {
        if (permission.startsWith(NOT_OPERATOR)) {
            return !isPermitted(userPermissions, permission.substring(NOT_OPERATOR.length()));
        } else {
            return isPermitted(userPermissions, permission);
        }
    }

    private boolean isPermitted(Set<String> userPermissions, String permission) {

        for (String userPermission : userPermissions) {
            if (userPermission.equalsIgnoreCase(permission)) return true;
        }

        return false;
    }

    public RedisUserUtil getRedisUserUtil() {
        return redisUserUtil;
    }

    public void setRedisUserUtil(RedisUserUtil redisUserUtil) {
        this.redisUserUtil = redisUserUtil;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }
}
