/**
 * Created by AlexXie on 2015/8/21.
 */
'use strict';

angular.module('BMMS_Line_List_Module', [])
    .controller('BMMS_Line_List_Controller', ['$scope', '$timeout', 'NgTableUtil', 'BMMS_Line_Service', 'AlertMsg', 'DlgMsg',
        function ($scope, $timeout, NgTableUtil, BMMS_Line_Service, AlertMsg, DlgMsg) {
            var vm = $scope.vm = {
                title: '线路列表',
                tableParams: NgTableUtil.initNgTableParams(),

                getData: function (exparam, tbparam) {
                    return BMMS_Line_Service.queryLine(exparam).then(function (res) {
                        tbparam.total(res.total);
                        return res.data;
                    })
                }
            };

            $scope.initLineListCtrl = function () {

            };

            $scope.reloadFirstPage = function () {

                var par = {query: "aaaa"};

                NgTableUtil.setNgTableFn(vm.tableParams, vm.getData);
                NgTableUtil.reloadNgTable(vm.tableParams, par, 1);
            };
            $scope.reloadOtherPage = function () {

                var par = {query: "zzz"};
                NgTableUtil.reloadNgTable(vm.tableParams, par, 1);
            };
            $scope.deleteLine = function (uid) {
                BMMS_Line_Service.deleteLine({uid: uid}).then(function (res) {
                    console.log(res);
                })
            };

            $scope.showload = function () {
                DlgMsg.confirm("系统提示", "确认要访问吗？", 'sm').result.then(function (btn) {
                    if (btn == "ok") {
                        BMMS_Line_Service.showload().then(function (res) {
                            AlertMsg.info("保存成功", "系统提示。。。" + btn);
                            AlertMsg.success("保存成功", "系统提示");
                            AlertMsg.failtrue("保存成功", "系统提示");
                            AlertMsg.warn("保存成功", "系统提示");

                        });
                    }

                });
            };

            // ui-select

        }]);