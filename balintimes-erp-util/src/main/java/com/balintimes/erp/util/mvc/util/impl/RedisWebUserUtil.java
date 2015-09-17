package com.balintimes.erp.util.mvc.util.impl;

import com.balintimes.erp.util.json.JsonUtil;
import com.balintimes.erp.util.mvc.model.RedisToken;
import com.balintimes.erp.util.mvc.model.WebUser;
import com.balintimes.erp.util.mvc.util.WebUserUtil;
import com.balintimes.erp.util.redis.RedisUserUtil;
import org.springframework.beans.factory.annotation.Autowired;

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

            WebUser webUser = redisUserUtil.GetRedisWebUser(httpRequest.getHeader(RedisUserUtil.GetRedisTokenName()),WebUser.class);

            return webUser;
        }
        return null;
    }

    public RedisToken getUniqueID() {
        if (this.httpRequest != null) {

            String id = httpRequest.getHeader(RedisUserUtil.GetRedisTokenName());
            RedisToken token = new RedisToken();
            token.setToken(id);
            return token;
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
