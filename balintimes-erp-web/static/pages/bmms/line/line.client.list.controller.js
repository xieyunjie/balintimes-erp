/**
 * Created by AlexXie on 2015/8/21.
 */
'use strict';

angular.module('BMMS_Line_List_Module', [])
    .controller('BMMS_Line_List_Controller', ['$scope', 'AjaxRequest', 'NgTableUtil', 'BMMS_Line_Service',
        function ($scope, AjaxRequest, NgTableUtil, BMMS_Line_Service) {
            var vm = $scope.vm = {
                title: '线路列表',
                tableParams: NgTableUtil.initNgTableParams(function (exparam, tbparam) {
                    var params = {
                        pagesize: exparam.pagesize,
                        page: exparam.page,
                        query: "lhms"
                    };
                    if (exparam.query) params.query = exparam.query;

                    return BMMS_Line_Service.queryLine(params).then(function (res) {
                        tbparam.total(res.total);
                        return res.data;
                    })
                })
            };

            $scope.reloadFirstPage = function () {

                var par = {query: "lhms"};

                NgTableUtil.reloadNgTable(vm.tableParams, par, 1);
            };

        }]);