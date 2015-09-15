package com.balintimes.erp.center.webservice;

import com.balintimes.erp.center.model.Application;
import com.balintimes.erp.center.model.authority.UserApp;
import com.balintimes.erp.center.service.UserService;
import com.balintimes.erp.center.shiro.WebUser;
import com.balintimes.erp.center.util.JsonUtil;
import com.balintimes.erp.center.util.WebUserUtil;
import com.balintimes.erp.util.redis.RedisUserUtil;
import org.apache.shiro.authc.credential.PasswordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
            if (webUser == null) {
                return JsonUtil.ResponseFailureMessage("init user failture!");
            }

            String redisToken = redisUserUtil.SetRedisWebUser(JsonUtil.ToJson(webUser));

            /*初始化用户程序 权限*/
            List<UserApp> userApps = this.GenUserApps(username);
            redisUserUtil.SetRedisUserApps(redisToken, JsonUtil.ToJson(userApps));


            return JsonUtil.ResponseSuccessfulMessage(redisToken);
        }
        return JsonUtil.ResponseFailureMessage("password or user is invald");
    }

    private List<UserApp> GenUserApps(String username) {
        List<Application> apps = this.webUserUtil.GetUserApps(username);

        if (apps.size() < 1) {
            return new ArrayList<UserApp>();
        }

        List<UserApp> userApps = new ArrayList<UserApp>(apps.size());

        for (Application app : apps) {

            UserApp userApp = new UserApp();
            userApp.setUid(app.getUid());
            userApp.setCode(app.getComment());
            userApp.setName(app.getName());
            userApp.setUrl(app.getUrl());
            userApp.setIconClass(app.getIconUrl());

            userApp.addMenus(webUserUtil.GetUserMenus(username, app.getUid()));
            userApp.addMenus(webUserUtil.GetUserDisableMenus(username, app.getUid()));

            List<String> ps = new ArrayList<String>(webUserUtil.GetUserPermissions(username, app.getUid()));
            userApp.addPermissions(ps);

            userApp.addMenuTree(webUserUtil.GetUserMenuTree(username, app.getUid()));

            userApps.add(userApp);
        }
        return userApps;
    }


    @RequestMapping("/userapps")
    @ResponseBody
    // 这里是否可以考虑，用户认证成功后就初始化呢？
    public String GetUserApplications(String redisToken, String username) {

        List<UserApp> userApps = this.GenUserApps(username);

        redisUserUtil.SetRedisUserApps(redisToken, JsonUtil.ToJson(userApps));

        return JsonUtil.ResponseSuccessfulMessage(userApps);
    }


//    @RequestMapping("/userpermissions")
//    @ResponseBody
//    public String GetUserPermissions(String redisToken, String username) {
//
//        Set<String> userPerssmissions = webUserUtil.GetUserAllPermissions(username);
//
//        redisUserUtil.SetRedisUserPermissions(redisToken, JsonUtil.ToJson(userPerssmissions));
//
//        return JsonUtil.ResponseSuccessfulMessage(userPerssmissions);
//    }

//    @RequestMapping("/usermenus")
//    @ResponseBody
//    public String GetUserMenus(String redisToken, String username) {
//        List<Menu> userMenus = webUserUtil.GetUserMenus(username);
//        List<Menu> m = webUserUtil.GetUserDisableMenus(username);
//        if (m != null && m.size() > 0) {
//            userMenus.addAll(m);
//        }
//
//        redisUserUtil.SetRedisUserMenus(redisToken, JsonUtil.ToJson(userMenus));
//
//        return JsonUtil.ResponseSuccessfulMessage(userMenus);
//    }
//
//
//    @RequestMapping("menutree/{username}")
//    @ResponseBody
//    public String GetUserMenuTree(@PathVariable String username) {
//        return JsonUtil.ResponseSuccessfulMessage(webUserUtil.GetUserMenuTree(username));
//    }

}
