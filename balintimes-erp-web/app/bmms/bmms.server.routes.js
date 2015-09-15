/**
 * Created by AlexXie on 2015/8/18.
 */
var express = require('express'),
    router = express.Router(),
    request = require("superagent"),
    bmmServer = require("../../config/settings").server.bmms;
var AuthCtrl = require('../authentication/authentication.server.controller'),
    requestUtil = require("../util/requestUtil");

router.get("/", AuthCtrl.IsAuth, function (req, res, next) {

    res.render('bmms/bmms.html');

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
        baseUrl:bmmServer.url,
        callback: ajaxRes
    });
});

module.exports = router;



