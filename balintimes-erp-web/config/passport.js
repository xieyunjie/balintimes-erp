/**
 * Created by AlexXie on 2015/8/11.
 */
'use strict';
var passport = require('passport'),
    LocalStrategy = require('passport-local').Strategy;

module.exports = function () {
    passport.use(new LocalStrategy({
        usernameField: 'name',
        passwordField: 'password'
    }, function (username, password, done) {

        if (username == password) {

            var user = {
                uid: '00000-00000-00000-000000-0000',
                username: username,
                dept: 'manage'
            };
            return done(null, user);
        }
        return done(null, false, {message: '错误用户或者密码!'})


    }));

    passport.serializeUser(function (user, done) {
        done(null, user.uid);
    });

    passport.deserializeUser(function (uid, done) {
        //User.findOne({
        //    _id: id
        //}, '-password -salt', function(err, user) {
        //    done(err, user);
        //});
        var user = {
            uid: '00000-00000-00000-000000-0000',
            username: "username",
            dept: 'manage'
        };
        done(null, user)
    });

};

