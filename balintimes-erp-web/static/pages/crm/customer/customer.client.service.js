
'use strict';
angular.module("app").factory('CRM_Customer_Service', ['AjaxRequest', function (AjaxRequest) {
    return{
        listByEmp:function(params){
            return AjaxRequest.get("/crm/customer/listbyemp", params).then(function (req) {
                return req;
            });
        },

        customerList:function(params){
            return AjaxRequest.get("/crm/customer/customerlist", params).then(function (req) {
                return req;
            });
        },

        getCustomer:function(params){
            return AjaxRequest.get("/crm/customer/getcustomer", params).then(function (req) {
                return req;
            });
        }
    };
}]);