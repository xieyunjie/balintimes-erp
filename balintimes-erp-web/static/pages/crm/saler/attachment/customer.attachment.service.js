'use strict';
angular.module("app").factory('CRM_Attachment_Service', ['AjaxRequest', function (AjaxRequest) {
    return {
        createAttachmentInfo: function (params) {
            return AjaxRequest.post("/crm/attachment/create", params).then(function (req) {
                return req;
            })
        },

        getAttachmentList: function (params) {
            return AjaxRequest.restGet("/crm/attachment/getattachmentlist", params).then(function (req) {
                return req;
            })
        },

        deleteAttachment: function (params) {
            return AjaxRequest.post("/crm/attachment/delete", params).then(function (req) {
                return req;
            })
        }
    }
}]);