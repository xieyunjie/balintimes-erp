/**
 * Created by AlexXie on 2015/8/18.
 */
var express = require('express'),
    router = express.Router();
var ucenterServer = require("../../config/settings").server.ucenter,
    requestUtil = require("../util/requestUtil"),
    logger = require("../util/log4jsUtil").logReq();

router.all("*", function (req, res, next) {

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
        baseUrl:ucenterServer.url,
        callback: ajaxRes
    });
});

module.exports = router;