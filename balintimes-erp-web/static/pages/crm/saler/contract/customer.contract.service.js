'use strict';
angular.module("app").factory('CRM_Contract_Service', ['AjaxRequest', function (AjaxRequest) {
    return {
        createContract: function (params) {
            return AjaxRequest.post("/crm/contract/create", params).then(function (req) {
                return req;
            })
        },

        getListByCustomer: function (params) {
            return AjaxRequest.restGet("/crm/contract/getlistbycustomer", params).then(function (req) {
                return req;
            })
        },

        getContract: function (params) {
            return AjaxRequest.restGet("/crm/contract/getcontract", params).then(function (req) {
                return req;
            })
        },

        deleteContract: function (params) {
            return AjaxRequest.post("/crm/contract/delete", params).then(function (req) {
                return req;
            })
        },

        updateContract: function (params) {
            return AjaxRequest.post("/crm/contract/update", params).then(function (req) {
                return req;
            })
        }
    }
}]);