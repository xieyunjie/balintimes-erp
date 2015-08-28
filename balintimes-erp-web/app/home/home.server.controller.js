/**
 * Created by AlexXie on 2015/8/28.
 */

"use strict";

var passport = require('passport');
var rkey = {
    ruid: "nodejs-sessionid",
    webuser: "ucenter-WebUser-",
    permissions: "ucenter-USERPERMISSION-",
    roles: "ucenter-Roles-"
};
var redis = require("redis"),
    redisClient = redis.createClient(6379, "172.16.0.250");

var HomeController = {};
module.exports = HomeController;

HomeController.initUser = function (req, res) {
    var user = req.user;
    res.json(user);
};

HomeController.initMenus = function (req, res) {
    var success = {success: true};
    res.json(success);
};

HomeController.login = function (req, res) {
    res.render("login", {errormsg: req.flash("error")});
};

HomeController.loginSubmit = passport.authenticate('local', {
    successRedirect: '/',
    failureRedirect: '/login',
    failureFlash: true
});

HomeController.logout = function (req, res) {
    var ruid = req.session.ruid;
    redisClient.del(rkey.webuser + ruid, redis.print);
    redisClient.del(rkey.permissions + ruid, redis.print);
    redisClient.del(rkey.roles + ruid, redis.print);
    req.logout();

    res.redirect("/login");

};