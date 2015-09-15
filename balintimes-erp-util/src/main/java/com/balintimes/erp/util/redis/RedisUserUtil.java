package com.balintimes.erp.util.redis;

import com.balintimes.erp.util.security.TokenProcessor;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Created by AlexXie on 2015/8/25.
 */
public class RedisUserUtil {


    private final static String RedisKey = "redisToken";
    private final static String WebUserKey = "webuser:";
    private final static String PerssiomsKey = "permissions:";
    private final static String MenusKey = "menus:";
    private final static String RolesID = "roles:";
    private final static String AppsID = "apps:";

    private RedisTemplate<String, String> redisTemplate;
    private final int expireHour = 10;

    public static String GetRedisTokenName() {
        return RedisKey;
    }


    public String SetRedisWebUser(String data) {

        String redistoken = TokenProcessor.getInstance().generateTokeCode();

        redisTemplate.opsForValue().set(WebUserKey + redistoken, data, expireHour, TimeUnit.HOURS);

        return redistoken;
    }

    public String GetRedisWebUser(String redistoken) {

        String data = redisTemplate.opsForValue().get(WebUserKey + redistoken);

        return data;
    }

    public boolean SetRedisUserPermissions(String redistoken, String permissionData) {

        if (redisTemplate.opsForValue().size(PerssiomsKey + redistoken) == 0) {

            redisTemplate.opsForValue().set(PerssiomsKey + redistoken, permissionData, expireHour, TimeUnit.HOURS);

            return true;
        }

        return false;
    }


    public String GetRedisUserPermissions(String redistoken) {

        String data = redisTemplate.opsForValue().get(PerssiomsKey + redistoken);

        return data;
    }


    public boolean SetRedisUserMenus(String redistoken, String menusData) {

        if (redisTemplate.opsForValue().size(MenusKey + redistoken) == 0) {
            redisTemplate.opsForValue().set(MenusKey + redistoken, menusData, expireHour, TimeUnit.HOURS);

            return true;
        }
        return false;
    }

    public boolean SetRedisUserApps(String redistoken, String data) {

        if (redisTemplate.opsForValue().size(AppsID + redistoken) == 0) {

            redisTemplate.opsForValue().set(AppsID + redistoken, data, expireHour, TimeUnit.HOURS);

            return true;
        }

        return false;
    }

    public String GetRedisUserApps(String redistoken) {

        return redisTemplate.opsForValue().get(AppsID + redistoken);
    }


    public RedisTemplate<String, String> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
