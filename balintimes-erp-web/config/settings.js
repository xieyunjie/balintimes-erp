/**
 * Created by AlexXie on 2015/9/1.
 */
module.exports = {

    server: {
        bmms: {
            url: "http://www.balintimes-web.net:9090/bmms"
        },
        crm: {
            url: "http://www.balintimes-web.net:9090/crm"
        },
        ucenter: {
            url: "http://www.balintimes-web.net:9090/oaucenter",
            authurl: "/ws/authority/userauthentication",
            userapps: "/ws/authority/userapps"
        }
    },
    redis: {
        host: "172.16.0.250",
        port: "6379",
        username: "",
        password: ""
    },
    sessionexpire: 10 * 60 * 60,
    redisKey: {
        redisToken: "redisToken",
        webuser: "webuser",
        permissions: "permissions",
        roles: "roles",
        menus: "menus",
        apps: "apps"
    },

    requestHeader: {
        contentType: {
            text: "Content-Type",
            value: "application/x-www-form-urlencoded"
        },
        ajaxHead: {
            text: "X-Requested-With",
            value: "XMLHttpRequest"
        },
        appToken: {
            text: "applicationToken",
            value: "_iloP^2nql8~"
        }
    }

};