'use strict';
angular.module("app").factory("CRM_Follow_Remarks_Service", ['AjaxRequest', function (AjaxRequest) {
    return {
        createRemarks: function (params) {
            return AjaxRequest.post("/crm/remarks/create", params).then(function (req) {
                return req;
            });
        },

        uploadRemarks:function(data){

            return AjaxRequest.upload("/crm/remarks/create",data).then(function(req){
               return req;
            });
        },

        getRemarksByEmp: function (params) {
            return AjaxRequest.get("/crm/remarks/getremarksbyemp", params).then(function (req) {
                return req;
            });
        },

        deleteRemark: function (params) {
            return AjaxRequest.post("/crm/remarks/delete", params).then(function (req) {
                return req;
            })
        },

        getRemark: function (params) {
            return AjaxRequest.restGet("/crm/remarks/getremark", params).then(function (req) {
                return req;
            })
        },

        updateRemark: function (params) {
            return AjaxRequest.post("/crm/remarks/update", params).then(function (req) {
                return req;
            })
        }

    }
}]);