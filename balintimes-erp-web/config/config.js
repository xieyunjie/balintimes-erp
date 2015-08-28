/**
 * Created by AlexXie on 2015/8/11.
 */
module.exports = {
    redis: {
        host: "172.16.0.250",
        port: "6379",
        username: "",
        password: ""
    },
    sessionexpire: 10 * 60 * 60,
    rkey: {
        ruid: "nodejs-sessionid",
        webuser: "ucenter-WebUser-",
        permissions: "ucenter-USERPERMISSION-",
        roles: "ucenter-Roles-"
    },
    authurl: "http://172.16.0.250:8080/oaucenter/ws/authority/userauthentication"
};