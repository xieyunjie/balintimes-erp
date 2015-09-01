/**
 * Created by AlexXie on 2015/9/1.
 */
module.exports = {
    serverurl: {
        bmms: "http://www.balintimes-web.net:9090/bmms",
        crm: "http://www.balintimes-web.net:9090/crm",
        ucenter: "http://172.16.0.250:8080/oaucenter"
    },
    redis: {
        host: "172.16.0.250",
        port: "6379",
        username: "",
        password: ""
    },
    sessionexpire: 10 * 60 * 60,
    redisKey: {
        redissessionid: "redissessionid",
        webuser: "ucenter-WebUser-",
        permissions: "ucenter-USERPERMISSION-",
        roles: "ucenter-Roles-"
    },
    authurl: "http://172.16.0.250:8080/oaucenter/ws/authority/userauthentication",

    requestHeader: {
        contentType: {
            text: "Content-Type",
            value: "application/x-www-form-urlencoded"
        },
        ajaxHead: {
            text: "X-Requested-With",
            value: "XMLHttpRequest"
        }
    }

};