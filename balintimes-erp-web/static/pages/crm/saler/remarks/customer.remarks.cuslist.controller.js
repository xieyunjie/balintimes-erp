'use strict';
angular.module('CRM_Customer_Remarks_CustomerList_Module', []).controller('CRM_Customer_Remarks_CustomerList_Controller',
    ['$scope', '$timeout', 'NgTableUtil', 'CRM_Follow_Remarks_Service', 'CRM_BaseData_Service', 'CRM_base_Service', 'AlertMsg', 'DlgMsg', 'NgTableParams', 'UserStgService', '$state',
        function ($scope, $timeout, NgTableUtil, CRM_Follow_Remarks_Service, CRM_BaseData_Service, CRM_base_Service, AlertMsg, DlgMsg, NgTableParams, UserStgService, $state) {
            var vm = $scope.vm = {
                title: "日志列表",
                customerUid: "",
                followUpUid: "",
                isReg: false,

                remarksList: []
            }

            $scope.deleteRemark = function (uid, reg) {
                var p = {
                    uid: uid,
                    isreg: reg
                };

                CRM_Follow_Remarks_Service.deleteRemark(p).then(function (res) {
                    getRemarksList();
                });
            }

            function getRemarksList() {
                vm.customerUid = $state.params.customeruid;
                vm.followUpUid = $state.params.followupuid;
                vm.isReg = eval($state.params.isreg);

                var uid = vm.customerUid;
                if (vm.isReg) {
                    uid = vm.followUpUid;
                }
                var p = {
                    objuid: uid,
                    isreg: vm.isReg
                };
                CRM_Follow_Remarks_Service.getRemarksListByCustomer(p).then(function (res) {
                    vm.remarksList = res.data;
                })
            }

            function init() {
                getRemarksList();
            }

            init();

        }])