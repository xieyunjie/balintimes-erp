/**
 * Created by AlexXie on 2015/8/18.
 */
var express = require('express'),
    router = express.Router(),
    request = require("superagent"),
    crmurl = require("../../config/settings").serverurl.crm;
var AuthCtrl = require('../authentication/authentication.server.controller');
var util = require("../util");


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
                var e = new Error(resObj.responseMsg);
                e.status = resObj.httpStatus;
                next(e);

            } else {
                res.send(response.text);
            }
        }
    };

    if (req.method == "GET") {
        util.request(req, crmurl, ajaxRes)
    }
    else {
        util.request(req, crmurl, req.body, ajaxRes)
    }

});

module.exports = router;