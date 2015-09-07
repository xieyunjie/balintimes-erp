/**
 * Created by AlexXie on 2015/8/19.
 */
var request = require("superagent"),
    agent = require("agentkeepalive"),
    settings = require("../config/settings");
var cloneFn = require('lodash-node/modern/lang/clone');

var keepaliveAgent = new agent({
    keepAlive: true,
    maxSockets: 100,
    maxFreeSockets: 10,
    timeout: 30000,
    keepAliveTimeout: 2000 // free socket keepalive for 30 seconds
});
var Util = {};
module.exports = Util;

Util.request = function (req, baseUrl, params, callback) {

    if (typeof params == 'function') {
        callback = params;
        params = {};
    }

    var call = {};
    switch (req.method) {
        case "GET":
            call = request.get(baseUrl + req.path);
            break;
        case "POST":
            call = request.post(baseUrl + req.path).set(settings.requestHeader.contentType.text, settings.requestHeader.contentType.value).send(params);
            break;
        case "PUT":
            call = request.put(baseUrl + req.path).set(settings.requestHeader.contentType.text, settings.requestHeader.contentType.value).send(params);
            break;
        case "DELETE":
            call = request.del(baseUrl + req.path);
            break;
        case "HEAD":
            call = request.head(baseUrl + req.path);
            break;
        case "OPTIONS":
            call = request.options(baseUrl + req.path);
            break;
    }

    call.set(settings.redisKey.redissessionid, req.session.ruid)
        .set(settings.requestHeader.ajaxHead.text, settings.requestHeader.ajaxHead.value)
        //.set("Accept","application/json")
        .agent(keepaliveAgent)// keyalive貌似没什么作用
        .end(function (err, response) {
            callback(err, response)
        });
};

Util.errResponseMessage = {
    success: false,
    status: "",
    path: "",
    responseMsg: ""
};

Util.Error404Msg = function (msg) {
    var msg = msg == undefined ? "请求错误(404)，请联系管理员" : msg;
    var resMsg = cloneFn(Util.errResponseMessage, true);
    resMsg.status = 404;
    resMsg.responseMsg = msg;
    return resMsg;
};
Util.Error500Msg = function (msg) {
    var msg = msg == undefined ? "请求错误(500)，请联系管理员" : msg;
    var resMsg = cloneFn(Util.errResponseMessage, true);
    resMsg.status = 404;
    resMsg.responseMsg = msg;
    return resMsg;
};

Util.ErrorMsg = function (msg) {
    var msg = msg == undefined ? "请求错误(未知代码)，请联系管理员" : msg;
    var resMsg = cloneFn(Util.errResponseMessage, true);
    resMsg.status = 404;
    resMsg.responseMsg = msg;
    return resMsg;
};


Util.Error40001Msg = function (msg) {
    var resMsg = cloneFn(Util.errResponseMessage, true);
    resMsg.status = 40001;
    resMsg.responseMsg = msg;
    return resMsg;
};
Util.Error70001Msg = function (msg) {
    var resMsg = cloneFn(Util.errResponseMessage, true);
    resMsg.status = 70001;
    resMsg.responseMsg = msg;
    return resMsg;
};