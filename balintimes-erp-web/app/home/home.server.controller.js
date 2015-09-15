/**
 * Created by AlexXie on 2015/8/28.
 */

"use strict";

var passport = require("passport"),
    settings = require("../../config/settings"),
    async = require("async");
var redisUtil = require("../util/redisUtil");
//var redis = require("redis"),
//    redisClient = redis.createClient(settings.redis.port, settings.redis.host);

//var requestUtil = require("../util/requestUtil");

var HomeController = {};
module.exports = HomeController;

// ========== 登录 begin
HomeController.login = function (req, res) {
    res.render("login", {errormsg: req.flash("error")});
};

HomeController.loginSubmit = passport.authenticate('local', {
    successRedirect: '/',
    failureRedirect: '/login',
    failureFlash: true
});

HomeController.logout = function (req, res) {
    var redisToken = req.session.redisToken;

    redisClient.del(settings.redisKey.webuser + redisToken, redis.print);
    redisClient.del(settings.redisKey.apps + redisToken, redis.print);
    req.logout();

    res.redirect("/login");

};
// ========== 登录 end


// ========= Webuser begin
HomeController.initUser = function (req, res) {
    var user = req.user;
    res.json(user);
};

HomeController.initUserApps = function (req, res) {

    redisUtil.getApps(req.session.redisToken, function (err, data) {
        res.json(JSON.parse(data));
    });

    //var params = {
    //    redisToken: req.session.redisToken,
    //    username: req.user.username
    //};
    //var url = settings.server.ucenter.url + settings.server.ucenter.userapps;
    //
    //requestUtil.request("POST", url, params, function (err, response) {
    //    if (err) {
    //        if (err) next(err);
    //    }
    //    else {
    //        var resObj = JSON.parse( response.text);
    //
    //        if (resObj.success == "false" && resObj.httpStatus != 200) {
    //
    //            var logMsg = [resObj.httpStatus, response.req.method, response.req.path, JSON.stringify(resObj)];
    //            logger.debug(logMsg.join(" "));
    //
    //            var e = new Error(resObj.responseMsg);
    //            e.status = resObj.httpStatus;
    //            next(e);
    //
    //        } else {
    //            res.send(resObj.data);
    //        }
    //    }
    //})

};

HomeController.getSettings = function (req, res) {
    var sysSettings = {
        avatarsurl: settings.server.ucenter.url
    };

    res.json(sysSettings);
};
// ========= Webuser end

HomeController.initUserAuthority = function (req, res) {
    //var params = {
    //    redisToken: req.session.redisToken,
    //    username: req.user.username
    //};
    //var pUrl = settings.serverurl.ucenter + "/ws/authority/userpermissions",
    //    mUrl = settings.serverurl.ucenter + "/ws/authority/usermenus";
    //
    //async.parallel({
    //    permissions: function (callback) {
    //        requestUtil.request("POST", pUrl, params, function (err, response) {
    //            if (err) {
    //                callback(err, null);
    //            }
    //            else {
    //                callback(null, JSON.parse(response.text));
    //            }
    //        })
    //    },
    //    menus: function (callback) {
    //        requestUtil.request("POST", mUrl, params, function (err, response) {
    //            if (err) {
    //                callback(err, null);
    //            }
    //            else {
    //                callback(null, JSON.parse(response.text));
    //            }
    //        })
    //    }
    //}, function (err, results) {
    //    if (err) {
    //        res.send(err);
    //    }
    //    else {
    //        res.send(results);
    //    }
    //});
};

