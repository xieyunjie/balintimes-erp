/**
 * Created by AlexXie on 2015/9/8.
 */
"use strict";


var log4js = require("log4js");
log4js.configure({
    appenders: [
        {type: 'console'},
        {
            type: 'file',
            filename: __dirname + '../../../logs/log.log',
            pattern: "_yyyy-MM-dd",
            maxLogSize: 2048,
            backups: 3,
            category: 'request'
        }
    ],
    levels: {
        "request": "DEBUG"
    }
});

module.exports.logReq = function () {
    var logger = log4js.getLogger("request");
    return logger;
};

