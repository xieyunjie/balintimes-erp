package com.balintimes.erp.center.webservice;

import com.balintimes.erp.center.model.authority.Menu;
import com.balintimes.erp.center.service.UserService;
import com.balintimes.erp.center.shiro.WebUser;
import com.balintimes.erp.center.util.JsonUtil;
import com.balintimes.erp.center.util.WebUserUtil;
import com.balintimes.erp.util.redis.RedisUserUtil;
import org.apache.shiro.authc.credential.PasswordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * Created by AlexXie on 2015/8/19.
 */
@Controller
@RequestMapping("ws/authority")
public class AuthorityController {

    @Resource
    private WebUserUtil webUserUtil;
    @Resource
    private RedisUserUtil redisUserUtil;
    @Resource
    private UserService userService = null;
    @Resource
    private PasswordService passwordService;

    @RequestMapping("userauthentication")
    @ResponseBody
    public String UserAuthentication(String username, String password) {

        String dbpsw = userService.getUserPassword(username);
        if ("".equals(dbpsw) || dbpsw == null) {
            return JsonUtil.ResponseFailureMessage("password or user is invald");
        }
        String inputpsw = passwordService.encryptPassword(password);

        if (inputpsw.equals(dbpsw)) {
            WebUser webUser = webUserUtil.InitUser(username);
//            Set<String> userPerssmissions = webUserUtil.GetUserAllPermissions(username);
            if (webUser == null) {
                return JsonUtil.ResponseFailureMessage("init user failture!");
            }

            String redisToken = redisUserUtil.SetRedisWebUser(JsonUtil.ToJson(webUser));

            return JsonUtil.ResponseSuccessfulMessage(redisToken);
        }
        return JsonUtil.ResponseFailureMessage("password or user is invald");
    }

    @RequestMapping("/userpermissions")
    @ResponseBody
    public String GetUserPermissions(String redisToken, String username) {

        Set<String> userPerssmissions = webUserUtil.GetUserAllPermissions(username);

        redisUserUtil.SetRedisUserPermissions(redisToken, JsonUtil.ToJson(userPerssmissions));

        return JsonUtil.ResponseSuccessfulMessage(userPerssmissions);
    }

    @RequestMapping("/usermenus")
    @ResponseBody
    public String GetUserMenus(String redisToken, String username) {
        List<Menu> userMenus = webUserUtil.GetUserMenus(username);
        List<Menu> m = webUserUtil.GetUserDisableMenus(username);
        if (m != null && m.size() > 0) {
            userMenus.addAll(m);
        }

        redisUserUtil.SetRedisUserMenus(redisToken, JsonUtil.ToJson(userMenus));

        return JsonUtil.ResponseSuccessfulMessage(userMenus);
    }


    @RequestMapping("menutree/{username}")
    @ResponseBody
    public String GetUserMenuTree(@PathVariable String username) {
        return JsonUtil.ResponseSuccessfulMessage(webUserUtil.GetUserMenuTree(username));
    }

}
