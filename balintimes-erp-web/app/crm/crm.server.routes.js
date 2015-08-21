/**
 * Created by AlexXie on 2015/8/18.
 */
var express = require('express'),
    router = express.Router(),
    request = require("superagent"),
    crmurl = require("../erpserver").url.crm;
var AuthCtrl = require('../authentication/authentication.server.controller');


router.all("*", AuthCtrl.AuthAjax, function (req, res, next) {

    if (req.method == "GET") {
        request.get(crmurl + req.path).end(function (err, response) {
            if (err) next(err);
            res.send(response.text);
        });
    } else if (req.method == "POST") {
        request.post(crmurl + req.path).set('Content-Type', 'application/x-www-form-urlencoded').send(req.body).end(function (err, response) {
            if (err) next(err);
            res.send(response.text);
        });
    }

});
module.exports = router;