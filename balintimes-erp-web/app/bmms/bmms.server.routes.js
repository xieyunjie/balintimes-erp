/**
 * Created by AlexXie on 2015/8/18.
 */
var express = require('express'),
    router = express.Router(),
    request = require("superagent"),
    bmmurl = require("../../config/settings").serverurl.bmms;
var AuthCtrl = require('../authentication/authentication.server.controller');

router.get("/", AuthCtrl.IsAuth, function (req, res, next) {

    res.render('bmms/bmms.html');

});

router.all("*", AuthCtrl.IsAuth, function (req, res, next) {

    if (req.method == "GET") {
        util.request(req, bmmurl, function (err, response) {
            if (err) {
                res.send(err);
            }
            else {
                res.send(response.text);
            }
        })
    }
    else {
        util.request(req, bmmurl, req.body, function (err, response) {
            if (err) {
                res.send(err);
            }
            else {
                res.send(response.text);
            }
        })
    }
});

module.exports = router;



