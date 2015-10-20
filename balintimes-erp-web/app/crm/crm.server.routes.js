/**
 * Created by AlexXie on 2015/8/18.
 */
var express = require('express'),
    router = express.Router();
var crmServer = require("../../config/settings").server.crm,
    AuthCtrl = require('../authentication/authentication.server.controller'),
    requestUtil = require("../util/requestUtil"),
    formidable = require('formidable'),
    fs = require('fs'),
    util = require('util'),
    rest = require('restler'),
    logger = require("../util/log4jsUtil").logReq();


router.post("/contract/uploadcard", AuthCtrl.IsAuth, function (req, res, next) {

    var files = [], fields = [], filePaths = [];
    var url = crmServer.url + req.url;

    var form = new formidable.IncomingForm();   //创建上传表单
    form.encoding = 'utf-8';		//设置编辑
    form.uploadDir = 'tempupload';	 //设置上传目录
    form.keepExtensions = true;	 //保留后缀
    form.maxFieldsSize = 2 * 1024 * 1024;   //文件大小

    if (!fs.existsSync(form.uploadDir)) {
        fs.mkdirSync(form.uploadDir);
    }

    form
        .on('field', function (field, value) {
            //console.log(field, value);
            fields.push([field, value]);
        })
        .on('file', function (field, file) {
            //console.log(field, file);
            files.push([field, file]);
            filePaths.push(file.path);
        })
        .on('end', function () {
            var d = {};
            for (var i = 0; i < files.length; i++) {
                d[i] = rest.file(filePaths[i]);
            }

            rest.post(url, {
                multipart: true,
                data: d,
            }).on('complete', function (data) {
                //console.log('-> upload done');
                files = [];
                fields = [];
                filePaths = [];

                res.writeHead(200, {'content-type': 'text/plain'});
                res.write(JSON.stringify(data));
                res.end();
            });
        });
    form.parse(req);
});

router.post("/attachment/uploadatts", AuthCtrl.IsAuth, function (req, res, next) {

    var files = [], fields = [], filePaths = [];
    var url = crmServer.url + req.url;

    var form = new formidable.IncomingForm();   //创建上传表单
    form.encoding = 'utf-8';		//设置编辑
    form.uploadDir = 'tempupload';	 //设置上传目录
    form.keepExtensions = true;	 //保留后缀
    form.maxFieldsSize = 5 * 1024 * 1024;   //文件大小

    if (!fs.existsSync(form.uploadDir)) {
        fs.mkdirSync(form.uploadDir);
    }

    form
        .on('field', function (field, value) {
            //console.log(field, value);
            fields.push([field, value]);
        })
        .on('file', function (field, file) {
            //console.log(field, file);
            var f = {
                filename: file.name,
                path: file.path
            };
            files.push([field, file]);
            filePaths.push(f);
        })
        .on('end', function () {
            var d = {};
            var n = {};
            for (var i = 0; i < files.length; i++) {
                n[i] = filePaths[i].filename;
                d[i] = rest.file(filePaths[i].path);
            }

            rest.post(url, {
                multipart: true,
                data: d,
            }).on('complete', function (data) {
                //console.log('-> upload done');
                files = [];
                fields = [];
                filePaths = [];

                res.writeHead(200, {'content-type': 'text/plain'});
                res.write(JSON.stringify(data));
                res.end();
            });
        });
    form.parse(req);
});

router.all("*", AuthCtrl.IsAuth, function (req, res, next) {

    var ajaxRes = function (err, response) {
        if (err) {
            if (err) next(err);
        } else {
            var resObj = response.body;

            if (resObj.success == "false" && resObj.httpStatus != 200) {

                var logMsg = [resObj.httpStatus, response.req.method, response.req.path, JSON.stringify(resObj)];
                logger.debug(logMsg.join(" "));

                var e = new Error(resObj.responseMsg);
                e.status = resObj.httpStatus;
                next(e);

            } else {
                res.send(response.text);
            }
        }
    };

    requestUtil.transmit({
        req: req,
        baseUrl: crmServer.url,
        callback: ajaxRes
    });

});

module.exports = router;