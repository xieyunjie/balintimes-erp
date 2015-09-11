package com.balintimes.erp.util.mvc.util;

import com.balintimes.erp.util.mvc.model.RedisToken;
import com.balintimes.erp.util.mvc.model.WebUser;

/**
 * Created by AlexXie on 2015/9/2.
 */
public interface WebUserUtil {

    WebUser getWebUser();

    RedisToken getUniqueID();
}
