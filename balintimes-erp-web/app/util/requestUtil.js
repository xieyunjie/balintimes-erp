/**
 * Created by AlexXie on 2015/9/8.
 */

"use strict";
var request = require("superagent"),
    agent = require("agentkeepalive"),
    settings = require("../../config/settings"),
    formidable = require('formidable'),
    lodash = require('lodash-node'),
    fs = require("fs");
var cloneFn = require('lodash-node/modern/lang/clone');
var logger = require("./log4jsUtil").logReq();

var keepaliveAgent = new agent({
    keepAlive: true,
    maxSockets: 100,
    maxFreeSockets: 10,
    timeout: 30000,
    keepAliveTimeout: 2000 // free socket keepalive for 30 seconds
});
var Util = {};
module.exports = Util;

/*
 config =
 {
 method
 url
 redisToken
 params
 callback
 }*/

var clientRequest = function (config) {

    var logMsg = [config.method, config.url, config.redisToken, JSON.stringify(config.params)];

    var call = {};
    var deleteFiles = [];
    switch (config.method) {
        case "GET":
            call = request.get(config.url);
            break;
        case "POST":
            call = request.post(config.url).set(settings.requestHeader.contentType.text, settings.requestHeader.contentType.value).send(config.params);
            break;
        case "PUT":
            call = request.put(config.url).set(settings.requestHeader.contentType.text, settings.requestHeader.contentType.value).send(config.params);
            break;
        case "DELETE":
            call = request.del(config.url);
            break;
        case "HEAD":
            call = request.head(config.url);
            break;
        case "OPTIONS":
            call = request.options(config.url);
            break;
        case "multipart":
            call = request.post(config.url).set(settings.requestHeader.multipartContentType.text, settings.requestHeader.multipartContentType.value);
            lodash.forEach(config.fields, function (value, key) {
                call.field(key, value);
            });
            lodash.forEach(config.files, function (file, key) {
                call.attach(key, file.path, file.name);
                deleteFiles.push(file.path);
            });
            break;
    }
    if (config.headers) call.set(config.headers);
    if (config.redisToken)  call.set(settings.redisKey.redisToken, config.redisToken);

    call.set(settings.requestHeader.ajaxHead.text, settings.requestHeader.ajaxHead.value)
        .set(settings.requestHeader.appToken.text, settings.requestHeader.appToken.value)
        //.set("Accept","application/json")
        .agent(keepaliveAgent)// keyalive貌似没什么作用
        .end(function (err, response) {

            if (lodash.isEmpty(deleteFiles) == false && settings.fileupload.deleteFileAfterComplete == 1) {
                lodash.forEach(deleteFiles, function (f) {
                    fs.unlinkSync(f)
                });
            }
            if (err) {
                logMsg.splice(0, 0, err.status);
                logMsg.push(err);
                logger.error(logMsg.join(" "));
            }
            else {
                logMsg.splice(0, 0, response.status);
                logger.debug(logMsg.join(" "));
            }
            config.callback(err, response)
        });

};

Util.transmit = function (config) {

    var req = config.req;

    if (lodash.isEmpty(req.header("content-type")) == false && req.header("content-type").indexOf("multipart/form-data") >= 0) {
        var form = new formidable.IncomingForm();

        form.encoding = "utf-8";
        form.uploadDir = settings.fileupload.folder;
        form.keepExtensions = true;
        // 只表示单个表单域能够申请到的最大内存值，不是限定文件的大小
        form.maxFieldsSize = settings.fileupload.fieldsSize;

        if (!fs.existsSync(settings.fileupload.folder)) {
            fs.mkdirSync(settings.fileupload.folder);
        }

        form.parse(req, function (err, fields, files) {

            if (err) {
                err.status = 9990;
                config.callback(err, null);
            }
            else {
                clientRequest({
                    method: "multipart",
                    redisToken: req.session.redisToken,
                    url: config.baseUrl + req.url,
                    //params: req.body,
                    fields: fields,
                    files: files,
                    callback: config.callback
                });
            }
        });
    }
    else {
        clientRequest({
            method: req.method,
            redisToken: req.session.redisToken,
            url: config.baseUrl + req.url,
            params: req.body,
            callback: config.callback
        });
    }
};

Util.request = function (method, url, params, callback, redisToken) {
    clientRequest({
        method: method,
        redisToken: redisToken,
        url: url,
        params: params,
        callback: callback
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
