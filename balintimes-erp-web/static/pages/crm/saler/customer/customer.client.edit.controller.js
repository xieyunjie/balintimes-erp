/**
 * Created by AlexXie on 2015/8/28.
 */
'use strict';
angular.module('CRM_Customer_Edit_Module', []).controller('CRM_Customer_Edit_Controller',
    ['$scope', 'CRM_Customer_Service', 'CRM_BaseData_Service', 'CRM_base_Service', 'AlertMsg', 'DlgMsg', '$state',
        function ($scope, CRM_Customer_Service, CRM_BaseData_Service, CRM_base_Service, AlertMsg, DlgMsg, $state) {
            var vm = $scope.vm = {
                title: '编辑客户信息',
                customer: {},
                businessTypeList: [],
                customerCategoryList: [],
                province: "",
                provinceList: [],
                areaList: [],

                isReg: false
            };

            $scope.getArea = function () {
                if (vm.province != "") {
                    CRM_base_Service.getAreaBypParent({parentuid: vm.province}).then(function (res) {
                        vm.areaList = res.data;
                    });
                } else {
                    AlertMsg.warn("请选择所在省份", "警告");
                }
            }

            $scope.saveCustomer = function () {
                vm.customer.reg=vm.isReg;
                CRM_Customer_Service.updateCustomer(vm.customer).then(function (res) {
                    $state.go("crm.saler.customer_list");
                });
            }

            function getBusinessTypeList() {
                CRM_BaseData_Service.businessTypeList().then(function (res) {
                    vm.businessTypeList = res.data;
                })
            };

            function getCustomerCategoryList() {
                CRM_BaseData_Service.customerCategoryList().then(function (res) {
                    vm.customerCategoryList = res.data;
                })
            };

            function getCustomer() {
                var uid = 0;
                if ($state.params.uid == undefined || $state.params.uid == "-1") {
                    uid = "-1";
                } else {
                    uid = $state.params.uid;
                    var followUid = $state.params.followUid;
                    var isReg = eval($state.params.isReg);

                    vm.isReg = isReg

                    var params = {
                        uid: isReg ? followUid : uid,
                        isreg: isReg
                    };

                    CRM_Customer_Service.getCustomer(params).then(function (res) {
                        vm.customer = res.data;

                        var areauid = vm.customer.areaUid;
                        CRM_base_Service.getArea({uid: areauid}).then(function (res) {
                            if (res.data != undefined && res.data != null) {
                                vm.province = res.data.parentUid;

                                $scope.getArea();
                            }
                        });

                    });
                }
            }

            function getProvince() {
                CRM_base_Service.getTopPareas().then(function (res) {
                    vm.provinceList = res.data;
                })
            }

            function init() {
                getBusinessTypeList();
                getCustomerCategoryList();
                getProvince();
                getCustomer();
            }

            init();

        }])
;