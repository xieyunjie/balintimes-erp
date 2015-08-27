/**
 * Created by AlexXie on 2015/8/26.
 */
'use strict';
angular.module('app')
    .factory('securityInterceptor', ['$q', function ($q) {
        var responseInterceptor = {
            response: function (response) {
                return response;
            },
            'responseError': function (response) {
                if (response.status == 403) {
                }
                else if (response.status == 401) {
                }
                return $q.reject(response);
            }
        };
        return responseInterceptor;
    }])
    .factory("AjaxRequest", ["$http", function ($http) {

        return {
            post: function (url, params) {
                console.log("post:" + url);
                return $http.post(url, params).then(function (response) {
                    return response.data;
                });
            },
            put: function (url, params) {
                console.log("put:" + url);
                return $http.put(url, params).then(function (response) {
                    return response.data;
                });
            },
            get: function (url, params) {
                var strParams = "";
                if (params) {
                    var values = [];
                    for (var i in params) {
                        values.push(params[i]);
                    }
                    strParams = values.join("/");

                    url = url + "/" + strParams;
                }
                console.log("get:" + url);
                return $http.get(url).then(function (response) {

                    return response.data;
                });
            },
            delete: function (url, params) {
                return "";
            },
            head: function (url, params) {

            },
            options: function (url, params) {

            }
        }
    }])
    .factory("NgTableUtil", ["NgTableParams", function (NgTableParams) {

        return {
            initNgTableParams: function (getDataService) {
                return new NgTableParams({
                    page: 1,
                    count: 20
                }, {
                    counts: [],
                    filterDelay: 0,
                    getData: function (params) {

                        var exparams = {};

                        if (params.parameters().extParams) exparams = angular.copy(params.parameters().extParams);
                        exparams.page = params.page();
                        exparams.pagesize = params.count();
                        exparams.sort = params.sorting();
                        exparams.filter = params.filter();

                        return getDataService(exparams, params);
                    }
                });

            },
            reloadNgTable: function (ngtable, extParams, page) {

                var param = {extParams: extParams};
                if (page) param.page = page;
                ngtable.parameters(param, false);
                ngtable.reload();

            }
        }

    }]);