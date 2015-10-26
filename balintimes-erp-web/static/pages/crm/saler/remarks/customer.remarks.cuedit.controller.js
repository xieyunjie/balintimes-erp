'use strict';
angular.module('CRM_Customer_Remarks_CU_Edit_Module', []).controller('CRM_Customer_Remarks_CU_Edit_Controller',
    ['$scope', '$timeout', 'NgTableUtil', 'CRM_Follow_Remarks_Service', 'CRM_Customer_Service', 'CRM_BaseData_Service',
        'CRM_base_Service', 'AlertMsg', 'DlgMsg', 'NgTableParams', 'UserStgService', '$state', '$modal',
        function ($scope, $timeout, NgTableUtil, CRM_Follow_Remarks_Service, CRM_Customer_Service, CRM_BaseData_Service,
                  CRM_base_Service, AlertMsg, DlgMsg, NgTableParams, UserStgService, $state, $modal) {
            Date.prototype.format = function (fmt) {
                var o = {
                    "M+": this.getMonth() + 1,                 //月份
                    "d+": this.getDate(),                    //日
                    "h+": this.getHours(),                   //小时
                    "m+": this.getMinutes(),                 //分
                    "s+": this.getSeconds(),                 //秒
                    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                    "S": this.getMilliseconds()             //毫秒
                };
                if (/(y+)/.test(fmt))
                    fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
                for (var k in o)
                    if (new RegExp("(" + k + ")").test(fmt))
                        fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                return fmt;
            }

            var vm = $scope.vm = {
                isReadOnly: eval($state.params.isreadonly),
                title: eval($state.params.isreadonly) ? "浏览日志详细" : "编辑日志",
                remarks: {},
                customerList: [],
                mannerList: [],
                ic: $state.params.ic,
                seachCusotmer: {},
                maxDate: new Date().format("yyyy-MM-dd"),
                isUpdate: $state.params.uid == -1 ? false : true
            }

            $scope.saveRemarks = function () {
                vm.remarks.followUpDate = vm.remarks.followUpDateByDateTime.toLocaleDateString() + " 00:00:00 ";
                if ($state.params.uid == -1) {
                    vm.remarks.customerUid = vm.seachCusotmer.uid;
                    vm.remarks.followUpUid = vm.seachCusotmer.followUid;
                    vm.remarks.customerName = vm.seachCusotmer.name;
                    vm.remarks.reg = vm.seachCusotmer.reg;

                    CRM_Follow_Remarks_Service.createRemarks(vm.remarks).then(function (res) {
                        $state.go("crm.saler.remarks_list");
                    })
                } else {
                    CRM_Follow_Remarks_Service.updateRemark(vm.remarks).then(function (res) {
                        $state.go("crm.saler.remarks_list");
                    })
                }
            }

            $scope.go = function () {
                if (vm.ic == "1") {
                    $state.go("crm.saler.remarks_list");
                } else {
                    $state.go("crm.saler.remarks_customerlist", {
                        customeruid: vm.remarks.customerUid,
                        followupuid: vm.remarks.followUpUid,
                        isreg: vm.remarks.reg
                    });
                }
            }

            function getRemark() {
                if ($state.params.uid == -1)
                    return;

                var p = {
                    uid: $state.params.uid,
                    isreg: $state.params.isreg
                };
                CRM_Follow_Remarks_Service.getRemark(p).then(function (res) {
                    vm.remarks = res.data;
                    vm.remarks.followUpDateByDateTime = new Date(vm.remarks.followUpDate);

                    var uid = vm.remarks.customerUid;
                    if (vm.remarks.reg == true) {
                        uid = vm.remarks.followUpUid;
                    }
                    getCustomer(uid, vm.remarks.reg);
                })
            }

            function getMannerList() {
                CRM_base_Service.getMannerList().then(function (res) {
                    vm.mannerList = res.data;
                })
            }

            function getCustomerList() {
                var p = {
                    name: "",
                    businesstype: null,
                    isreg: null,
                    brand: null,
                    pagesize: 100000,
                    page: 1,
                    isshowdown: false
                };

                CRM_Customer_Service.listByEmp(p).then(function (res) {
                    vm.customerList = res.data;
                })
            }

            function getCustomer(uid, isreg) {
                var p = {
                    uid: uid,
                    isreg: isreg
                };

                CRM_Customer_Service.getCustomer(p).then(function (res) {
                    vm.seachCusotmer = res.data;
                });
            }

            function init() {
                getCustomerList();
                getMannerList();
                getRemark();
            }

            init();
        }]);