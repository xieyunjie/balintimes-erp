'use strict';
angular.module('CRM_Customer_Remarks_List_Module', []).controller('CRM_Customer_Remarks_List_Controller',
    ['$scope', '$timeout', 'NgTableUtil', 'CRM_Follow_Remarks_Service', 'CRM_BaseData_Service', 'CRM_base_Service', 'AlertMsg', 'DlgMsg', 'NgTableParams', 'UserStgService',
        function ($scope, $timeout, NgTableUtil, CRM_Follow_Remarks_Service, CRM_BaseData_Service, CRM_base_Service, AlertMsg, DlgMsg, NgTableParams, UserStgService) {
            var date = new Date();
            var vm = $scope.vm = {
                title: '跟进列表',
                params: {
                    customername: "",
                    brand: "",
                    manneruid: null,
                    begindate: new Date(date.getFullYear(), date.getMonth(), 1),
                    enddate: new Date(date.getFullYear(), date.getMonth() + 1, 0),
                    isshowdown: false,
                    pagesize: 20,
                    page: 1
                },

                currUser: {},
                mannerList: [],

                tableParams: NgTableUtil.initNgTableParams(function (exparam, tbparam) {
                    if (exparam.begindate == undefined || exparam.begindate == "") {
                        exparam.begindate = vm.params.begindate.toLocaleDateString() + " 00:00:00 ";
                    }
                    if (exparam.enddate == undefined || exparam.enddate == "") {
                        exparam.enddate = vm.params.enddate.toLocaleDateString() + " 00:00:00 ";
                    }
                    if (exparam.isshowdown == undefined || exparam.isshowdown == "") {
                        exparam.isshowdown = false;
                    }
                    return CRM_Follow_Remarks_Service.getRemarksByEmp(exparam).then(function (res) {
                        tbparam.total(res.total);
                        return res.data;
                    })
                })
            }

            $scope.reloadFirstPage = function () {
                var p = jQuery.extend({}, vm.params);
                p.begindate = vm.params.begindate.toLocaleDateString() + " 00:00:00 ";
                p.enddate = vm.params.enddate.toLocaleDateString() + " 00:00:00 ";
                NgTableUtil.reloadNgTable(vm.tableParams, p, 1);
            };

            $scope.reloadOtherPage = function () {
                var p = jQuery.extend({}, vm.params);
                p.begindate = vm.params.begindate.toLocaleDateString() + " 00:00:00 ";
                p.enddate = vm.params.enddate.toLocaleDateString() + " 00:00:00 ";
                NgTableUtil.reloadNgTable(vm.tableParams, p, vm.page);
            };

            $scope.deleteRemark = function (uid, reg) {
                var p = {
                    uid: uid,
                    isreg: reg
                };

                CRM_Follow_Remarks_Service.deleteRemark(p).then(function (res) {
                    $scope.reloadOtherPage();
                });
            }

            function getMannerList() {
                CRM_base_Service.getMannerList().then(function (res) {
                    vm.mannerList = res.data;
                })
            }

            function setUser() {
                vm.currUser = UserStgService.getWebuser();
            }

            function init() {
                getMannerList();
                setUser();
            }

            init();
        }]);
