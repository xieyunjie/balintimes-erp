package com.balintimes.erp.util.mvc.util.impl;

import com.balintimes.erp.util.json.JsonUtil;
import com.balintimes.erp.util.mvc.model.Ruid;
import com.balintimes.erp.util.mvc.model.WebUser;
import com.balintimes.erp.util.mvc.util.WebUserUtil;
import com.balintimes.erp.util.redis.RedisUserUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by AlexXie on 2015/9/2.
 */
public class RedisWebUserUtil implements WebUserUtil {

    @Autowired(required = false)
    private HttpServletRequest httpRequest;

    private RedisUserUtil redisUserUtil;

    public WebUser getWebUser() {
        if (this.httpRequest != null) {

            String userStr = redisUserUtil.GetRedisWebUser(httpRequest.getHeader("redissessionid"));
            if (userStr == null) {
                return null;
            }
            WebUser webUser = JsonUtil.ToObject(userStr, WebUser.class);
            return webUser;
        }
        return null;
    }

    public Ruid getUniqueID() {
        if (this.httpRequest != null) {

            String id = httpRequest.getHeader("redissessionid");
            Ruid ruid = new Ruid();
            ruid.setRuid(id);
            return ruid;
        }
        return null;
    }


    public RedisUserUtil getRedisUserUtil() {
        return redisUserUtil;
    }

    public void setRedisUserUtil(RedisUserUtil redisUserUtil) {
        this.redisUserUtil = redisUserUtil;
    }
}
