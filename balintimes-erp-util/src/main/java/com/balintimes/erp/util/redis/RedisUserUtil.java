package com.balintimes.erp.util.redis;

import com.balintimes.erp.util.json.JsonUtil;
import com.balintimes.erp.util.security.TokenProcessor;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by AlexXie on 2015/8/25.
 */
public class RedisUserUtil {


    private final static String RedisKey = "redisToken";
    private final static String WebUserKey = "webuser";
    private final static String PerssiomsKey = "permissions";
    private final static String MenusKey = "menus";
    private final static String RolesID = "roles";
    private final static String AppsID = "apps";

    private RedisTemplate redisTemplate;
    private final int expireHour = 10;

    public static String GetRedisTokenName() {
        return RedisKey;
    }

    public static String GenToken() {
        return TokenProcessor.getInstance().generateTokeCode();
    }


    public boolean SetRedisWebUser(String redistoken, Object data) {

        if (redisTemplate.opsForHash().hasKey(redistoken, WebUserKey)) {
            return false;
        }
        redisTemplate.opsForHash().put(redistoken, WebUserKey, JsonUtil.ToJson(data));
        redisTemplate.expire(redistoken, expireHour, TimeUnit.HOURS);

        return true;
    }

    public String GetRedisWebUser(String redistoken) {

        Object data = redisTemplate.opsForHash().get(redistoken, WebUserKey);

        return data == null ? "" : data.toString();
    }

    public <T> T GetRedisWebUser(String redistoken, Class<T> c) {

        String data = this.GetRedisWebUser(redistoken);
        if ("".equals(data)) {
            return null;
        }
        T user = JsonUtil.ToObject(data, c);

        return user;
    }


    public boolean SetRedisUserApps(String redistoken, Object data) {

        if (redisTemplate.opsForHash().hasKey(redistoken, AppsID)) {
            return false;
        }
        redisTemplate.opsForHash().put(redistoken, AppsID, JsonUtil.ToJson(data));

        return true;
    }

    public String GetRedisUserApps(String redistoken) {
        Object data = redisTemplate.opsForHash().get(redistoken, AppsID);

        return data == null ? "" : data.toString();
    }

    public <T> List<T> GetRedisUserApps(String redistoken, Class<T> c) {

        String data = this.GetRedisUserApps(redistoken);

        return JsonUtil.ToList(data, c);
    }


    public boolean SetRedisUserRoles(String redistoken, Object data) {

        if (redisTemplate.opsForHash().hasKey(redistoken, RolesID)) {
            return false;
        }
        redisTemplate.opsForHash().put(redistoken, RolesID, JsonUtil.ToJson(data));

        return true;

    }

    public String GetRedisUserRoles(String redistoken) {
        Object data = redisTemplate.opsForHash().get(redistoken, RolesID).toString();

        return data == null ? "" : data.toString();
    }

    @Deprecated
    public boolean SetRedisUserPermissions(String redistoken, String permissionData) {

        if (redisTemplate.opsForValue().size(PerssiomsKey + redistoken) == 0) {

            redisTemplate.opsForValue().set(PerssiomsKey + redistoken, permissionData, expireHour, TimeUnit.HOURS);

            return true;
        }

        return false;
    }


    @Deprecated
    public String GetRedisUserPermissions(String redistoken) {

        String data = redisTemplate.opsForValue().get(PerssiomsKey + redistoken).toString();

        return data;
    }

    @Deprecated
    public boolean SetRedisUserMenus(String redistoken, String menusData) {

        if (redisTemplate.opsForValue().size(MenusKey + redistoken) == 0) {
            redisTemplate.opsForValue().set(MenusKey + redistoken, menusData, expireHour, TimeUnit.HOURS);

            return true;
        }
        return false;
    }


    public RedisTemplate<String, String> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
