package com.balintimes.erp.util.redis;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by AlexXie on 2015/8/25.
 */
public class RedisUserUtil {


    private RedisTemplate<String, String> redisTemplate;
    private final int expireHour = 10;


    public String SetRedisWebUser(String data) {

        String uid = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(RedisKeyUtil.getWebUserID(uid), data, expireHour, TimeUnit.HOURS);

        return uid;
    }

    public String GetRedisWebUser(String uid) {

        String data = redisTemplate.opsForValue().get(RedisKeyUtil.getWebUserID(uid));

        return data;
    }

    public String SetRedisUserPermissions(String uid, String data) {

        redisTemplate.opsForValue().set(RedisKeyUtil.getPerssiomsID(uid), data, expireHour, TimeUnit.HOURS);

        return uid;
    }

    public String GetRedisUserPermissions(String uid) {

        String data = redisTemplate.opsForValue().get(RedisKeyUtil.getPerssiomsID(uid));

        return data;
    }

    public RedisTemplate<String, String> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
