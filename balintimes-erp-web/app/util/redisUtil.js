/**
 * Created by AlexXie on 2015/9/15.
 */
var redis = require("redis");
var settings = require("../../config/settings");
var redisClient = redis.createClient(settings.redis.port, settings.redis.host);

module.exports.instance = redisClient;

module.exports.getUser = function (token, fn) {
    redisClient.hget(token, settings.redisKey.webuser, fn);
};
module.exports.getApps = function (token, fn) {
    redisClient.hget(token, settings.redisKey.apps, fn);
};
module.exports.removeAll = function (token) {
    redisClient.del(token);
};

