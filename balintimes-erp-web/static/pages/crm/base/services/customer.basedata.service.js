angular.module("app").factory('CRM_BaseData_Service', ['AjaxRequest', function (AjaxRequest) {
    return {
        businessTypeList: function () {
            return AjaxRequest.restGet("/center/ws/basedata/businesstypelist").then(function (req) {
                return req;
            });
        },

        customerCategoryList: function () {
            return AjaxRequest.restGet("/center/ws/basedata/customercategorylist").then(function (req) {
                return req;
            });
        }
    }
}]);