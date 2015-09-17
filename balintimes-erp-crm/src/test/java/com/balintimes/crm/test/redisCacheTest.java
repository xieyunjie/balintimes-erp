package com.balintimes.crm.test;

import com.balintimes.erp.util.json.JsonUtil;
import com.balintimes.erp.util.mvc.model.UserApp;
import com.balintimes.erp.util.mvc.model.WebUser;
import com.balintimes.erp.util.redis.RedisUserUtil;
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
//
//    @Resource
//    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private RedisUserUtil redisUserUtil;


    @Test
    public void InsetStrs() {

//        List<String> strs = new ArrayList<String>(10);
//
//        strs.add("organization:view");
//        strs.add("organization:edit");
//        strs.add("organization:delete");
//
//        strs.add("user:delete");
//        strs.add("user:delete");
//        strs.add("user:delete");
//
//        redisTemplate.opsForValue().set("webuserwebuser", JsonUtil.ToJson(strs));

    }

    @Test
    public void GetStrs() {

//        List<String> strs = new ArrayList<String>(10);
//
//
//        strs = JsonUtil.ToObject(redisTemplate.opsForValue().get("webuserwebuser"), List.class);
//
//        for (String str : strs) {
//            System.out.println(str);
//        }

    }
    @Test
    public void InsertW() {

        WebUser user = new WebUser();
        user.setUsername("ALEX");
        user.setUid("411-110-112-120-119");

        redisTemplate.opsForHash().put("webusers","user", JsonUtil.ToJson(user));
//        redisUserUtil.SetRedisWebUser(token, user);
    }


    @Test
    public void InsertWebuser() {

        WebUser user = new WebUser();
        user.setUsername("ALEX");
        user.setUid("411-110-112-120-119");

        String token = RedisUserUtil.GenToken();
        System.out.println(token);

        redisUserUtil.SetRedisWebUser(token, user);
    }

    @Test
    public void GetWebuser() {

        WebUser u = this.redisUserUtil.GetRedisWebUser("rXx3MjG0Qb4inBpwCOr+fA==", WebUser.class);

        System.out.println(u);

    }

    @Test
    public void InsertApp() {

        List<UserApp> apps = new ArrayList<UserApp>(10);

        UserApp app = new UserApp();
        app.setName("aaaa");
        app.setUid("kdjkfjdkdjksaldal;");
        app.setCode("123456");
        apps.add(app);

        redisUserUtil.SetRedisUserApps("rXx3MjG0Qb4inBpwCOr+fA==", apps);
    }

    @Test
    public void GetApp() {
        List<UserApp> apps = redisUserUtil.GetRedisUserApps("rXx3MjG0Qb4inBpwCOr+fA==", UserApp.class);

        for (UserApp app : apps) {

            System.out.println(app.toString());
        }
    }
}
