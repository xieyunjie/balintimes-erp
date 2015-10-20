'use strict';
angular.module('CRM_Contract_List_Module', []).controller('CRM_Contract_List_Controller',
    ['$scope', 'CRM_Contract_Service', 'AlertMsg', 'DlgMsg', '$state',
        function ($scope, CRM_Contract_Service, AlertMsg, DlgMsg, $state) {
            var vm = $scope.vm = {
                title: '联系人列表',

                customerUid: "",
                followUpUid: "",
                isReg: false,

                contractList: []
            };

            function getContractList() {
                vm.customerUid = $state.params.customeruid;
                vm.followUpUid = $state.params.followupuid;
                vm.isReg = eval($state.params.isreg);

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
        }]);