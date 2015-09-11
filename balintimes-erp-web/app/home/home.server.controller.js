/**
 * Created by AlexXie on 2015/8/28.
 */

"use strict";

var passport = require("passport"),
    settings = require("../../config/settings"),
    async = require("async");
var redis = require("redis"),
    redisClient = redis.createClient(settings.redis.port, settings.redis.host);
var requestUtil = require("../util/requestUtil");

var HomeController = {};
module.exports = HomeController;

HomeController.initUser = function (req, res) {
    var user = req.user;
    res.json(user);
};

HomeController.initUserAuthority = function (req, res) {
    var params = {
        redisToken: req.session.redisToken,
        username: req.user.username
    };
    var pUrl = settings.serverurl.ucenter + "/ws/authority/userpermissions",
        mUrl = settings.serverurl.ucenter + "/ws/authority/usermenus";

    async.parallel({
        permissions: function (callback) {
            requestUtil.request("POST", pUrl, params, function (err, response) {
                if (err) {
                    callback(err, null);
                }
                else {
                    callback(null, JSON.parse(response.text));
                }
            })
        },
        menus: function (callback) {
            requestUtil.request("POST", mUrl, params, function (err, response) {
                if (err) {
                    callback(err, null);
                }
                else {
                    callback(null, JSON.parse(response.text));
                }
            })
        }
    }, function (err, results) {
        if (err) {
            res.send(err);
        }
        else {
            res.send(results);
        }
    });
};

HomeController.initMenus = function (req, res) {
    var success = {success: true};
    res.json(success);
};

HomeController.login = function (req, res) {
    res.render("login", {errormsg: req.flash("error")});
};

HomeController.getSettings = function (req, res) {
    var sysSettings = {
        avatarsurl: settings.serverurl.ucenter
    };

    res.json(sysSettings);

};

HomeController.loginSubmit = passport.authenticate('local', {
    successRedirect: '/',
    failureRedirect: '/login',
    failureFlash: true
});

HomeController.logout = function (req, res) {
    var redisToken = req.session.redisToken;

    redisClient.del(settings.redisKey.webuser + redisToken, redis.print);
    redisClient.del(settings.redisKey.permissions + redisToken, redis.print);
    redisClient.del(settings.redisKey.roles + redisToken, redis.print);
    req.logout();

    res.redirect("/login");

};