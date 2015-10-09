/**
 * Created by AlexXie on 2015/8/28.
 */
'use strict';
angular.module('CRM_Customer_List_Module', []).controller('CRM_Customer_List_Controller',
    ['$scope', '$timeout', 'NgTableUtil', 'CRM_Customer_Service', 'CRM_BaseData_Service', 'AlertMsg', 'DlgMsg', 'NgTableParams', 'UserStgService',
        function ($scope, $timeout, NgTableUtil, CRM_Customer_Service, CRM_BaseData_Service, AlertMsg, DlgMsg, NgTableParams, UserStgService) {
            var vm = $scope.vm = {
                title: '客户列表',
                params: {
                    name: "",
                    brand: "",
                    isreg: -1,
                    isshowdown: false,
                    pagesize: 20,
                    page: 1,
                    businesstype: ""
                },

                currUser: {},

                tableParams: NgTableUtil.initNgTableParams(function (exparam, tbparam) {
                    return CRM_Customer_Service.listByEmp(exparam).then(function (res) {
                        tbparam.total(res.total);
                        return res.data;
                    })
                }),

                businessTypeList: [],
                customerCategoryList: [],
                regTypeList: [
                    {
                        id: -1,
                        name: '全部'
                    }, {
                        id: 0,
                        name: '登记'
                    }, {
                        id: 1,
                        name: '注册'
                    }
                ]
            };


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

            function setUser() {
                vm.currUser = UserStgService.getWebuser();
            }

            getBusinessTypeList();
            getCustomerCategoryList();
            setUser();

            $scope.reloadFirstPage = function () {
                NgTableUtil.reloadNgTable(vm.tableParams, vm.params, 1);
            };

            $scope.reloadOtherPage = function () {
                //console.log(vm.params.currPage);
                NgTableUtil.reloadNgTable(vm.tableParams, vm.params, vm.page);
            };

            $scope.deleteCustomer = function (uid, followUid, isReg) {
                var params = {
                    uid: isReg ? followUid : uid,
                    isReg: isReg
                };
                CRM_Customer_Service.deleteCustomer(params).then(function (res) {
                    $scope.reloadOtherPage();
                })
            }

        }])
;