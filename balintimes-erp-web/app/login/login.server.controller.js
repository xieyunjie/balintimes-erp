/**
 * Created by AlexXie on 2015/8/11.
 */
'use strict';

var LoginController = {};
module.exports = LoginController;

LoginController.ToLoginView = function (req, res) {
    res.render("login", {errormsg: req.flash("error")});
};