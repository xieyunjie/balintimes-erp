/**
 * Created by AlexXie on 2015/8/18.
 */
var express = require('express'),
    router = express.Router();
var crmurl = require("../../config/settings").serverurl.crm,
    AuthCtrl = require('../authentication/authentication.server.controller'),
    requestUtil = require("../util/requestUtil"),
    logger = require("../util/log4jsUtil").logReq();


router.get("/line/showload", function (req, res) {
    var NotAuth = {
        success: true,
        status: 40001,
        path: req.path
    };

    res.setTimeout(5000, function () {
        res.send(NotAuth);
    });
});

router.get("/", AuthCtrl.IsAuth, function (req, res) {

    res.render('crm/crm.html');

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

    if (req.method == "GET") {
        requestUtil.request(req, crmurl, ajaxRes)
    }
    else {
        requestUtil.request(req, crmurl, req.body, ajaxRes)
    }

});

module.exports = router;