package com.balintimes.crm.test;

import com.balintimes.erp.util.json.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexXie on 2015/9/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class redisCacheTest {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void InsetStrs() {

        List<String> strs = new ArrayList<String>(10);

        strs.add("organization:view");
        strs.add("organization:edit");
        strs.add("organization:delete");

        strs.add("user:delete");
        strs.add("user:delete");
        strs.add("user:delete");

        redisTemplate.opsForValue().set("webuserwebuser", JsonUtil.ToJson(strs));

    }

    @Test
    public void GetStrs() {

        List<String> strs = new ArrayList<String>(10);


        strs = JsonUtil.ToObject(redisTemplate.opsForValue().get("webuserwebuser"), List.class);

        for (String str : strs) {
            System.out.println(str);
        }

    }
}
