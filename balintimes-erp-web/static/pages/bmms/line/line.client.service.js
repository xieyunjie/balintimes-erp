/**
 * Created by AlexXie on 2015/8/27.
 */
'use strict';
angular.module("app").factory('BMMS_Line_Service', ['AjaxRequest', function (AjaxRequest) {

    return {
        queryLine: function (params) {
            return AjaxRequest.get("/crm/line/query", params).then(function (req) {
                return req;
            });
        },

        getLine: function (params) {
            return AjaxRequest.get("/crm/line/get", params).then(function (req) {
                return req;
            });
        },
        saveLine: function (line) {
            return AjaxRequest.post("/crm/line/save", line).then(function (req) {
                return req;
            });
        },
        deleteLine:function(params){
            return AjaxRequest.delete("/crm/line/delete",params).then(function(req){
                return req;
            })
        },
        showload: function () {
            return AjaxRequest.get("/crm/line/file").then(function (req) {
                return req;
            });
        }
    };
}]);
