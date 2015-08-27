package com.balintimes.erp.util.redis;

/**
 * Created by AlexXie on 2015/8/25.
 */
public class RedisKeyUtil {

    private final static String RedisUID = "nodejs-sessionid";
    private final static String WebUserID = "ucenter-WebUser-";
    private final static String PerssiomsID = "ucenter-USERPERMISSION-";
    private final static String RolesID = "ucenter-Roles-";

    public static String getRedisUID() {
        return RedisUID;
    }

    public static String getWebUserID(String uid) {
        return WebUserID + uid;
    }

    public static String getPerssiomsID(String uid) {
        return PerssiomsID + uid;
    }

    public static String getRolsID(String uid) {
        return RolesID + uid;
    }
}
