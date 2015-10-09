'use strict';
angular.module('CRM_Customer_Create_Edit_Module', []).controller('CRM_Customer_Create_Edit_Controller',
    ['$scope', 'CRM_Customer_Service', 'CRM_BaseData_Service', 'CRM_base_Service', 'AlertMsg', 'DlgMsg', '$state',
        function ($scope, CRM_Customer_Service, CRM_BaseData_Service, CRM_base_Service, AlertMsg, DlgMsg, $state) {
            var vm = $scope.vm = {
                title: "新建客户信息",
                step1Title: '请查询并选择客户，如没有相关数据请点击新增',
                step2Title: '请录入相关的客户数据',
                customer: null,
                customerName: "",
                customerList: [],

                businessTypeList: [],
                customerCategoryList: [],
                province: "",
                provinceList: [],
                areaList: []
            }

            $scope.customerChange = function () {
                if (vm.customerName == undefined || vm.customerName == null || vm.customerName == "")
                    return [];

                if (vm.customerName.length < 4)
                    return [];
                var params = {
                    name: vm.customerName,
                    page: 1,
                    pagesize: 10000
                };
                CRM_Customer_Service.customerList(params).then(function (res) {
                    vm.customerList = res.data;
                });
            };

            $scope.changeCustomer = function (item) {
                vm.customer = item;
                vm.customer.businessTypeUid = item.businessType;
                vm.customer.reg = true;
                vm.customer.followUid = vm.customer.uid;
                var areauid = vm.customer.areaUid;
                CRM_base_Service.getArea({uid: areauid}).then(function (res) {
                    if (res.data != undefined && res.data != null) {
                        vm.province = res.data.parentUid;

                        $scope.getArea();
                    }
                });
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

            $scope.pre = function () {
                if (vm.customer.uid == -1) {
                    vm.customer = null;
                }
            }

            $scope.createReg = function () {
                vm.customer = {
                    uid: -1,
                    isReg: false
                };
                vm.customerName = "";
                vm.customerList = [];
                vm.province = "";
                vm.areaList = [];
            }

            $scope.saveData = function () {
                CRM_Customer_Service.createCustomer(vm.customer).then(function (res) {
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

            function getProvince() {
                CRM_base_Service.getTopPareas().then(function (res) {
                    vm.provinceList = res.data;
                })
            }

            function init() {
                getBusinessTypeList();
                getCustomerCategoryList();
                getProvince();
            }

            init();
        }]);