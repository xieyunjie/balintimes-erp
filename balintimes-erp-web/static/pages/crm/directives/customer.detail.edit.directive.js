angular.module("app").directive("customerdetailview", function () {
    return {
        restrict: 'EA',
        templateUrl: '/pages/crm/directives/tpls/customer.detail.edit.view.html',
        transclude: true,
        replace: true,
        scope: {
            params: '=data',
        },

        controller: ['$scope', 'CRM_Customer_Service', 'CRM_BaseData_Service', 'CRM_base_Service', 'AlertMsg', 'DlgMsg', '$state',
            function ($scope, CRM_Customer_Service, CRM_BaseData_Service, CRM_base_Service, AlertMsg, DlgMsg, $state) {
                var vm = $scope.vm = {
                    title: "基础信息",
                    customerUid: $scope.params.customerUid,
                    followUpUid: $scope.params.followUpUid,
                    isReg: $scope.params.isReg,
                    isReadOnly: $scope.params.isReadOnly,

                    customer: {},

                    businessTypeList: [],
                    customerCategoryList: [],
                    province: "",
                    provinceList: [],
                    areaList: []
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

                function getCustomer() {
                    var p = {
                        uid: $scope.params.isReg ? $scope.params.followUpUid : $scope.params.customerUid,
                        isreg: $scope.params.isReg
                    };
                    CRM_Customer_Service.getCustomer(p).then(function (res) {
                        vm.customer = res.data;

                        var areauid = vm.customer.areaUid;
                        CRM_base_Service.getArea({uid: areauid}).then(function (res) {
                            if (res.data != undefined && res.data != null) {
                                vm.province = res.data.parentUid;

                                $scope.getArea();
                            }
                        });
                    })
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
                    getCustomer();
                }

                init();
            }]
    }
});