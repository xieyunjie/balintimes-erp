package com.balintimes.erp.center.model.authority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by AlexXie on 2015/9/14.
 */
public class UserApp {

    private String uid;
    private String code;
    private String name;
    private String url;
    private String iconClass;

    // 包含有效以及无效的菜单
    private List<Menu> menuList;
    // 权限列表
    private List<String> permissions;
    //有效菜单树
    private List<Menu> menuTree;

    public UserApp() {
        menuList = new ArrayList<Menu>();
        permissions = new ArrayList<String>();
        menuTree = new ArrayList<Menu>();
    }

    public void addMenus(List<Menu> menus){
        this.menuList.addAll(menus);
    }
    public void addPermissions(List<String> permissions){
        this.permissions.addAll(permissions);
    }
    public void addMenuTree(List<Menu> menus){
        this.menuTree.addAll(menus);
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public List<Menu> getMenuTree() {
        return menuTree;
    }

    public void setMenuTree(List<Menu> menuTree) {
        this.menuTree = menuTree;
    }


}
