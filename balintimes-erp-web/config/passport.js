/**
 * Created by AlexXie on 2015/8/11.
 */
'use strict';
var passport = require('passport'),
    LocalStrategy = require('passport-local').Strategy,
    request = require("superagent");
var settings = require("./settings");

var redisUtil = require("../app/util/redisUtil");

module.exports = function () {
    passport.use(new LocalStrategy({
        usernameField: 'name',
        passwordField: 'password',
        passReqToCallback: true
    }, function (req, username, password, done) {

        var authurl = settings.server.ucenter.url + settings.server.ucenter.authurl;

        request.post(authurl).set(settings.requestHeader.contentType.text, settings.requestHeader.contentType.value).send({
            username: username,
            password: password
        }).end(function (err, response) {
            if (err) return done(err, null);
            var resObj = JSON.parse(response.text);
            if (resObj.success == 'true') {
                var redisToken = resObj.responseMsg;

                req.session.redisToken = redisToken;
                return done(null, redisToken);
            }
            else {
                //return done({error: resObj.responseMsg}, null);
                return done(null, false, {message: resObj.responseMsg});
            }
        });

    }));

    passport.serializeUser(function (redisToken, done) {
        done(null, redisToken);
    });

    passport.deserializeUser(function (redisToken, done) {

        redisUtil.getUser(redisToken, function (err, data) {
            if (err) return done(err, null);

            return done(null, JSON.parse(data));
        });
    });

};

