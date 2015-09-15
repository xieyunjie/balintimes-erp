/**
 * Created by AlexXie on 2015/8/18.
 */
var express = require('express'),
    router = express.Router();
var crmServer = require("../../config/settings").server.crm,
    AuthCtrl = require('../authentication/authentication.server.controller'),
    requestUtil = require("../util/requestUtil"),
    logger = require("../util/log4jsUtil").logReq();

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