/**
 * Created by AlexXie on 2015/8/18.
 */
var express = require('express'),
    router = express.Router(),
    request = require("superagent"),
    crmurl = require("../erpserver").url.crm;
var AuthCtrl = require('../authentication/authentication.server.controller');

router.get("/",AuthCtrl.IsAuth,function(req,res,next){

    res.render('crm/crm.html');

});
router.all("*", AuthCtrl.IsAuth, function (req, res, next) {

    if (req.method == "GET") {
        request.get(crmurl + req.path).set('nodejs-sessionid', req.session.ruid).end(function (err, response) {
            if (err) {
                res.send(err);
            }
            else {
                res.send(response.text);
            }
        });
    } else if (req.method == "POST") {
        request.post(crmurl + req.path).set('nodejs-sessionid', req.session.ruid).set('Content-Type', 'application/x-www-form-urlencoded').send(req.body).end(function (err, response) {
            if (err) {
                console.log(err);
                res.status(err.response.statusCode).send(err.response.text);
            }
            else {
                res.send(response.text);
            }
        });
    }

});
module.exports = router;