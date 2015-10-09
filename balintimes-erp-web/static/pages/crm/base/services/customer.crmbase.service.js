angular.module("app").factory('CRM_base_Service', ['AjaxRequest', function (AjaxRequest) {
    return {
        getTopPareas: function () {
            return AjaxRequest.restGet("/crm/area/gettoppareas").then(function (req) {
                return req;
            });
        },

        getAreaBypParent: function (params) {
            return AjaxRequest.restGet("/crm/area/getareabyparent", params).then(function (req) {
                return req;
            });
        },

        getArea: function (params) {
            return AjaxRequest.restGet("/crm/area/getarea", params).then(function (req) {
                return req;
            });
        },

        getMannerList: function () {
            return AjaxRequest.get("/crm/manner/getlist").then(function (req) {
                return req;
            })
        },

        createManner: function (params) {
            return AjaxRequest.post("/crm/manner/create", params).then(function (req) {
                return req;
            });
        }
    }
}]);