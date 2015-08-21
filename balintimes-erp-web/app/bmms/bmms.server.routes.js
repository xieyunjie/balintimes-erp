/**
 * Created by AlexXie on 2015/8/18.
 */
var express = require('express'),
    router = express.Router(),
    request = require("superagent"),
    bmmurl = require("../erpserver").url.bmms;
var AuthCtrl = require('../authentication/authentication.server.controller');

router.all("*", AuthCtrl.AuthAjax, function (req, res, next) {

    if (req.method == "GET") {
        request.get(bmmurl + req.path).end(function (err, response) {
            if (err) {
                res.send(err);
            }
            else {
                res.send(response.text);
            }
        });
    } else if (req.method == "POST") {
        request.post(bmmurl + req.path).set('Content-Type', 'application/x-www-form-urlencoded').send(req.body).end(function (err, response) {
            //if (err) next(err);
            if (err) {
                res.send(err);
            }
            else {
                res.send(response.text);
            }
        });
    }

});

module.exports = router;



