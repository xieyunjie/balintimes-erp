/**
 * Created by AlexXie on 2015/8/28.
 */
'use strict';
angular.module('CRM_Customer_List_Module', []).controller('CRM_Customer_List_Controller',
    ['$scope', '$timeout', 'NgTableUtil', 'CRM_Customer_Service', 'CRM_BaseData_Service', 'AlertMsg', 'DlgMsg', 'NgTableParams',
        function ($scope, $timeout, NgTableUtil, CRM_Customer_Service, CRM_BaseData_Service, AlertMsg, DlgMsg, NgTableParams) {
            var vm = $scope.vm = {
                title: '客户列表',
                params: {
                    name: "",
                    brand: "",
                    userUids: "",
                    isReg: -1,
                    isShowDown: false,
                    pageSize: 20,
                    currPage: 1,
                    businessType: ""
                },
                tableParams: NgTableUtil.initNgTableParams(function (exparam, tbparam) {
                    var par={
                        name:angular.isUndefined(exparam.name) ? "" : exparam.name ,
                        brand: angular.isUndefined(exparam.brand) ? "" : exparam.brand ,
                        userUids: "",
                        isReg: angular.isUndefined(exparam.isReg) ? -1 : exparam.isReg,
                        isShowDown: angular.isUndefined(exparam.isReg) ? false : exparam.isShowDown,
                        pageSize: exparam.pagesize,
                        currPage: exparam.page,
                        businessType: angular.isUndefined(exparam.businessType) ? "" : exparam.businessType
                    };
                    if (exparam.name) par.name = exparam.name;
                    if (exparam.brand) par.brand = exparam.brand;
                    if (exparam.userUids) par.userUids = exparam.userUids;
                    if (exparam.isReg) par.isReg = exparam.isReg;
                    if (exparam.isShowDown) par.isShowDown = exparam.isShowDown;
                    if (exparam.businessType) par.businessType = exparam.businessType;

                    var p = {json: JSON.stringify(par)};
                    //console.log(p);
                    return CRM_Customer_Service.listByEmp(p).then(function (res) {
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

            getBusinessTypeList();
            getCustomerCategoryList();

            $scope.reloadFirstPage = function () {
                NgTableUtil.reloadNgTable(vm.tableParams, vm.params, 1);
            };

            $scope.reloadOtherPage = function () {
                console.log(vm.params.currPage);
                NgTableUtil.reloadNgTable(vm.tableParams, vm.params, 1);
            };

        }])
;