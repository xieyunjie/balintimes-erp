/**
 * Created by AlexXie on 2015/9/1.
 */

var ajaxHead = require("../../config/settings").requestHeader.ajaxHead;
var requestUtil = require("./requestUtil");

module.exports = function (err, req, res, next) {

    if (req.header(ajaxHead.text) == ajaxHead.value) {
        if (err) {
            if (err.status == 404) {
                res.status(404).send(util.Error404Msg());
            }
            else if (err.status == 500) {
                res.status(500).send(util.Error500Msg());
            }
            else if (err.status == 40001) {
                res.status(err.status).send(requestUtil.Error40001Msg(err.message));
            }
            else if (err.status == 70001) {
                res.status(err.status).send(requestUtil.Error70001Msg(err.message));
            }
            else {
                res.status(err.status).send(requestUtil.ErrorMsg());
            }
        }
    } else {
        next(err);
    }
};