/**
 * Created by AlexXie on 2015/8/18.
 */
var express = require('express'),
    router = express.Router(),
    request = require("superagent"),
    preurl = require("../erpurl").crm;


router.all("*", function (req, res, next) {

    if (req.method == "GET") {
        request.get(preurl + req.path).end(function (err, response) {
            if (err) next(err);
            res.send(response.text);
        });
    } else if (req.method == "POST") {
        request.post(preurl + req.path).set('Content-Type', 'application/x-www-form-urlencoded').send(req.body).end(function (err, response) {
            if (err) next(err);
            res.send(response.text);
        });
    }

});
module.exports = router;