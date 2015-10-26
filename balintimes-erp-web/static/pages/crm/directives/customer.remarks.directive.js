angular.module("app").directive("remarkslist", function () {
    return {
        restrict: 'EA',
        templateUrl: '/pages/crm/directives/tpls/customer.remarks.view.html',
        transclude: true,
        replace: true,
        scope: {
            params: '=data',
        },

        controller: ['$scope', 'CRM_Customer_Service', 'CRM_BaseData_Service', 'CRM_base_Service', 'CRM_Follow_Remarks_Service', 'AlertMsg', 'DlgMsg', '$state',
            function ($scope, CRM_Customer_Service, CRM_BaseData_Service, CRM_base_Service, CRM_Follow_Remarks_Service, AlertMsg, DlgMsg, $state) {
                var vm = $scope.vm = {
                    title: "基础信息",
                    customerUid: $scope.params.customerUid,
                    followUpUid: $scope.params.followUpUid,
                    isReg: $scope.params.isReg,
                    isReadOnly: $scope.params.isReadOnly,

                    ir: false,
                    customer: {},
                    remarksList: [],
                    remarks: {},
                    modalShown: false,
                    mannerList: [],

                    maxDate: new Date().toString("yyyy-M-dd")
                }

                $scope.logClose = function () {
                    vm.modalShown = !vm.modalShown;
                }

                $scope.openWin = function () {
                    getCustomer();
                    vm.modalShown = true;
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

                function getCustomer() {
                    var p = {
                        uid: $scope.params.isReg ? $scope.params.followUpUid : $scope.params.customerUid,
                        isreg: $scope.params.isReg
                    };
                    CRM_Customer_Service.getCustomer(p).then(function (res) {
                        vm.customer = res.data;
                    })
                }

                $scope.getRemarks = function (uid, isReg, isReadOnly) {
                    if (vm.isReadOnly == false)
                        vm.ir = isReadOnly;
                    else
                        vm.ir=true;

                    getRemark(uid, isReg);
                }

                function getRemark(uid, isReg) {
                    var p = {
                        uid: uid,
                        isreg: isReg
                    };
                    CRM_Follow_Remarks_Service.getRemark(p).then(function (res) {
                        vm.remarks = res.data;
                        vm.remarks.followUpDateByDateTime = new Date(vm.remarks.followUpDate);

                        $scope.openWin();
                    })
                }

                function getRemarksList() {

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

                function getMannerList() {
                    CRM_base_Service.getMannerList().then(function (res) {
                        vm.mannerList = res.data;
                    })
                }

                function init() {
                    getMannerList();
                    getRemarksList();
                }

                init();

            }]
    }
})