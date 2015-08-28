/**
 * Created by AlexXie on 2015/8/11.
 */
'use strict';
var passport = require('passport'),
    LocalStrategy = require('passport-local').Strategy,
    request = require("superagent");
var config = require("./config");

var redis = require("redis"),
    redisClient = redis.createClient(config.redis.port, config.redis.host);

module.exports = function () {
    passport.use(new LocalStrategy({
        usernameField: 'name',
        passwordField: 'password',
        passReqToCallback: true
    }, function (req, username, password, done) {

        request.post(config.authurl).set('Content-Type', 'application/x-www-form-urlencoded').send({
            username: username,
            password: password,
        }).end(function (err, response) {
            if (err) return done(err, null);
            var resObj = JSON.parse(response.text)
            if (resObj.success == 'true') {
                var ruid = resObj.responseMsg;

                req.session.ruid = ruid;
                return done(null, ruid);
            }
            else {
                return done({err: 'error'}, null);
            }
        });

    }));

    passport.serializeUser(function (ruid, done) {
        done(null, ruid);
    });

    passport.deserializeUser(function (ruid, done) {

        redisClient.get(config.rkey.webuser + ruid, function (err, data) {

            if (err) return done(err, null);

            return done(null, JSON.parse(data));
        });
    });

};

