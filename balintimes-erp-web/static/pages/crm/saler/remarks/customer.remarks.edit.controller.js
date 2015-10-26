'use strict';
angular.module('CRM_Customer_Remarks_Edit_Module', []).controller('CRM_Customer_Remarks_Edit_Controller',
    ['$scope', '$timeout', 'NgTableUtil', 'CRM_Follow_Remarks_Service', 'CRM_Customer_Service', 'CRM_BaseData_Service',
        'CRM_base_Service', 'AlertMsg', 'DlgMsg', 'NgTableParams', 'UserStgService', '$state', '$modal',
        function ($scope, $timeout, NgTableUtil, CRM_Follow_Remarks_Service, CRM_Customer_Service, CRM_BaseData_Service,
                  CRM_base_Service, AlertMsg, DlgMsg, NgTableParams, UserStgService, $state, $modal) {

            var vm = $scope.vm = {
                title: '跟进列表',
                remarks: {
                    followUid: "",
                    customerUid: "",
                    isReg: false,
                    mannerUid: 1,
                    contract: "",
                    phone: "",
                    persons: "",
                    followUpDate: "",
                    followUpDateByDateTime: new Date(),
                    summary: "",
                    remarks: ""
                },
                customer: {},
                isReg: false,
                followUid: "",
                customerUid: "",

                mannerList: [],
                maxDate: new Date().format("yyyy-MM-dd")
            };

            $scope.saveRemarks = function () {
                vm.remarks.reg = vm.isReg;
                vm.remarks.followUpUid = vm.followUid;
                vm.remarks.customerUid = vm.customerUid;
                vm.remarks.followUpDate = vm.remarks.followUpDateByDateTime.toString("yyyy-MM-dd 00:00:00");
                CRM_Follow_Remarks_Service.createRemarks(vm.remarks).then(function (res) {
                    $state.go("crm.saler.customer_list");
                });
            };

            $scope.openmanner = function () {

                $modal.open({
                    animation: true,
                    templateUrl: "templateId",
                    controller: function ($scope, CRM_base_Service, $modalInstance) {
                        $scope.mannerModel = {
                            id: 0,
                            name: ""
                        };
                        $scope.saveManner = function () {
                            CRM_base_Service.createManner($scope.mannerModel).then(function (res) {
                                getMannerList();
                                $modalInstance.dismiss('cancel');
                            });
                        }

                        $scope.closeWin = function () {
                            $modalInstance.dismiss('cancel');
                        }
                    }
                })
            };

            function getCustomer() {
                vm.customerUid = $state.params.customeruid;
                vm.followUid = $state.params.followuid;
                vm.isReg = eval($state.params.isreg);

                var params = {
                    uid: vm.isReg ? vm.followUid : vm.customerUid,
                    isreg: vm.isReg
                };
                CRM_Customer_Service.getCustomer(params).then(function (res) {
                    vm.customer = res.data;
                })
            }

            function getMannerList() {
                CRM_base_Service.getMannerList().then(function (res) {
                    vm.mannerList = res.data;
                })
            }

            function init() {
                getMannerList();
                getCustomer();
            }

            init();

        }]);
