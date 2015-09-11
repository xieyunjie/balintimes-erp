package com.balintimes.erp.center.util;

import com.balintimes.erp.center.model.Application;
import com.balintimes.erp.center.model.Role;
import com.balintimes.erp.center.model.authority.Employee;
import com.balintimes.erp.center.model.authority.Menu;
import com.balintimes.erp.center.model.authority.Permission;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.balintimes.erp.center.service.AuthorityService;
import com.balintimes.erp.center.shiro.WebUser;

import javax.annotation.Resource;
import java.util.*;

@Component
public class WebUserUtil {
    private final String WEBUSER_KEY = "WEBUSER";
    private final String USERMENUS_KEY = "USERMENUS";
    private final String APPUID = "05bd7806-3026-11e5-8396-c86000a05d5f";
    @Value("#{settingProperties['avatarsurl']}")
    private String employeeAvatarsurl;

    @Resource
    private AuthorityService authorityService;

    public WebUser InitUser(String username) {
        WebUser webUser = initWebUser(username);
        return webUser;
    }

    public WebUser CurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isRemembered() == false && subject.isAuthenticated() == false) {
            return null;
        }

        WebUser webUser = (WebUser) subject.getSession().getAttribute(WEBUSER_KEY);
        if (webUser == null) {
            webUser = initWebUser(SecurityUtils.getSubject().getPrincipal().toString());
            subject.getSession().setAttribute(WEBUSER_KEY, webUser);
        }
        return webUser;
    }

    public List<Menu> GetUserMenuTree(String username) {

        List<Menu> list = new ArrayList<>();

        List<com.balintimes.erp.center.model.Resource> list_resources = this.authorityService.GetUserMenu(username, APPUID);

        list = this.GenMenuTree("00000000-0000-0000-0000-000000000000", list_resources);

        Collections.sort(list);

        return list;
    }

    public List<Menu> GetUserMenuTree() {
        List<Menu> list = new ArrayList<>();
        Subject subject = SecurityUtils.getSubject();
        if (subject.isRemembered() == false && subject.isAuthenticated() == false) {
            return list;
        }

        list = (List<Menu>) subject.getSession().getAttribute(USERMENUS_KEY);

        if (list == null) {
            list = this.GetUserMenuTree(SecurityUtils.getSubject().getPrincipal().toString());

            subject.getSession().setAttribute(USERMENUS_KEY, list);
        }
        return list;

    }

    public List<Menu> GetUserMenus(String username) {
        List<Menu> list = new ArrayList<>();
        List<com.balintimes.erp.center.model.Resource> list_resources = this.authorityService.GetUserMenu(username, APPUID);

        for (com.balintimes.erp.center.model.Resource resource : list_resources) {
            Menu menu = new Menu(resource.getUid(), resource.getName(), resource.getState(), resource.getIconClass(), resource.getUrl(), resource.getPriority());
            list.add(menu);
        }
        Collections.sort(list);
        return list;
    }

    public List<Menu> GetUserDisableMenus(String username) {
        List<Menu> list = new ArrayList<>();
        List<com.balintimes.erp.center.model.Resource> list_resources = this.authorityService.GetUserDisableMenus(username, APPUID);

        for (com.balintimes.erp.center.model.Resource resource : list_resources) {
            Menu menu = new Menu(resource.getUid(), resource.getName(), resource.getState(), resource.getIconClass(), resource.getUrl(), resource.getPriority(),false);
            list.add(menu);
        }
        Collections.sort(list);
        return list;
    }

    public List<Menu> GetUserMenus() {
        List<Menu> list = new ArrayList<>();
        Subject subject = SecurityUtils.getSubject();
        if (subject.isRemembered() == false && subject.isAuthenticated() == false) {
            return list;
        }

        return GetUserMenus(SecurityUtils.getSubject().getPrincipal().toString());
    }

    public List<Permission> GetUserPermission() {

        List<Permission> list = new ArrayList<>();
        Subject subject = SecurityUtils.getSubject();
        if (subject.isRemembered() == false && subject.isAuthenticated() == false) {
            return list;
        }

        List<com.balintimes.erp.center.model.Resource> list_resources = this.authorityService.GetUserPermissions(SecurityUtils.getSubject().getPrincipal().toString(), APPUID);
        list = new ArrayList<>(list_resources.size());
        for (com.balintimes.erp.center.model.Resource resource : list_resources) {
            Permission permission = new Permission(resource.getUid(), resource.getName(), resource.getAuthorityCode(), resource.getParentUid());
            list.add(permission);
        }
        return list;
    }

    public Set<String> GetUserAllPermissions(String username) {
        List<Application> applications = authorityService.GetUserApplications(username);

        Set<String> userPermissions = new HashSet<String>();

        for (Application application : applications) {
            Set<String> p = this.GetUserPermissions(username, application.getUid());
            userPermissions.addAll(p);
        }
        return userPermissions;
    }

    public Set<String> GetUserPermissions(String username, String appUID) {

        List<com.balintimes.erp.center.model.Resource> resourceList = this.authorityService.GetUserPermissions(username, appUID);
        Set<String> permission = new HashSet<String>(resourceList.size());
        for (com.balintimes.erp.center.model.Resource resource : resourceList) {
            permission.add(resource.getAuthorityCode());
        }
        return permission;
    }

    public Set<String> GetUserPermissions() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isRemembered() == false && subject.isAuthenticated() == false) {
            return null;
        }
        return GetUserPermissions(SecurityUtils.getSubject().getPrincipal().toString(), APPUID);
    }

    public Set<String> GetUserRoles() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isRemembered() == false && subject.isAuthenticated() == false) {
            return null;
        }

        List<Role> roles = this.authorityService.GetUserRoles(SecurityUtils.getSubject().getPrincipal().toString());
        Set<String> roleStr = new HashSet<>(roles.size());

        for (Role role : roles) {
            roleStr.add(role.getName());
        }

        return roleStr;
    }

    private List<Menu> GenMenuTree(String parentuid, List<com.balintimes.erp.center.model.Resource> list_resources) {

        List<Menu> list_menu = new ArrayList<>();

        for (com.balintimes.erp.center.model.Resource resource : list_resources) {

            if (resource.getParentUid().equalsIgnoreCase(parentuid)) {
                Menu menu = new Menu(resource.getUid(), resource.getName(), resource.getState(), resource.getIconClass(), resource.getUrl(), resource.getPriority());
                menu.setChildren(this.GenMenuTree(menu.getUid(), list_resources));
                Collections.sort(menu.getChildren());
                list_menu.add(menu);
            }
        }
        return list_menu;
    }

    private WebUser initWebUser(String username) {
        Employee employee = authorityService.GetEmployee(username);

        WebUser webUser = new WebUser();
        webUser.setUid(employee.getUid());
        webUser.setEmployeeName(employee.getEmployeename());
        webUser.setUsername(employee.getUsername());
        webUser.setEmail(employee.getUsername());
        webUser.setUsername(employee.getUsername());
        webUser.setAdmin(employee.getIsadmin());
        webUser.setEmail(employee.getEmail());
        webUser.setUsertypename(employee.getUsertypename());
        webUser.setLastLogin(employee.getLastlogin());
        int v = (int) (1 + Math.random() * (100 - 1 + 1));
        webUser.setAvatarUrl(this.employeeAvatarsurl + "/" + employee.getAvatarurl() + "?v=" + v);
        webUser.setPostList(employee.getPosts());

        return webUser;
    }
}

