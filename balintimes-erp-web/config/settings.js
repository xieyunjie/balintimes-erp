/**
 * Created by AlexXie on 2015/9/1.
 */
module.exports = {
    serverurl: {
        bmms: "http://www.balintimes-web.net:9090/bmms",
        crm: "http://www.balintimes-web.net:9090/crm",
        ucenter: "http://www.balintimes-web.net:9090/oaucenter"
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
        webuser: "webuser:",
        permissions: "permissions:",
        roles: "roles:",
        menus:"menus:"
    },
    authurl: "/ws/authority/userauthentication",

    requestHeader: {
        contentType: {
            text: "Content-Type",
            value: "application/x-www-form-urlencoded"
        },
        ajaxHead: {
            text: "X-Requested-With",
            value: "XMLHttpRequest"
        },
        appToken:{
            text:"applicationToken",
            value:"_iloP^2nql8~"
        }

    }

};