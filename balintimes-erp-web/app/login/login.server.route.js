/**
 * Created by AlexXie on 2015/8/11.
 */

var express = require('express'),
    router = express.Router(),
    passport = require('passport'),
    loginCtrl = require("./login.server.controller");

module.exports = router;

router.get("/", loginCtrl.ToLoginView);

router.post("/", passport.authenticate('local', {
    successRedirect: '/',
    failureRedirect: '/login',
    failureFlash: true
}));

