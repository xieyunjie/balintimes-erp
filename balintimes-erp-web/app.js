'use strict';

var express = require("./config/express"),
    passport = require("./config/passport");

var app = express();

var passport = passport();



module.exports = app;
