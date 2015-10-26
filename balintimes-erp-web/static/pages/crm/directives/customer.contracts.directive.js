angular.module("app").directive("contractslists", function () {
    return {
        restrict: 'EA',
        templateUrl: '/pages/crm/directives/tpls/customer.contracts.view.html',
        transclude: true,
        replace: true,
        scope: {
            params: '=data',
        },

        controller: ['$scope', 'CRM_Customer_Service', 'CRM_BaseData_Service', 'CRM_base_Service', 'CRM_Contract_Service', 'AlertMsg', 'DlgMsg', '$state',
            function ($scope, CRM_Customer_Service, CRM_BaseData_Service, CRM_base_Service, CRM_Contract_Service, AlertMsg, DlgMsg, $state) {
                var vm = $scope.vm = {
                    title: '联系人列表',

                    customerUid: $scope.params.customerUid,
                    followUpUid: $scope.params.followUpUid,
                    isReg: $scope.params.isReg,
                    isReadOnly: $scope.params.isReadOnly,

                    contractList: []
                };

                function getContractList() {
                    var p = {
                        objuid: vm.isReg ? vm.followUpUid : vm.customerUid,
                        isreg: vm.isReg
                    };

                    CRM_Contract_Service.getListByCustomer(p).then(function (res) {
                        vm.contractList = res.data;
                    })
                }

                function init() {
                    getContractList();
                }

                init();
            }]
    }
})