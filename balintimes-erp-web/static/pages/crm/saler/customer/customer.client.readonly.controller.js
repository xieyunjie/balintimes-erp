'use strict';
angular.module('CRM_Customer_Detail_Module', []).controller('CRM_Customer_Detail_Controller',
    ['$scope', '$timeout', 'NgTableUtil', 'CRM_Customer_Service', 'CRM_BaseData_Service', 'AlertMsg', 'DlgMsg', 'NgTableParams', 'UserStgService', '$state',
        function ($scope, $timeout, NgTableUtil, CRM_Customer_Service, CRM_BaseData_Service, AlertMsg, DlgMsg, NgTableParams, UserStgService, $state) {
            var vm = $scope.vm = {
                title: "详细列表",
                data:{
                    customerUid:$state.params.customeruid,
                    followUpUid:$state.params.followupuid,
                    isReg:eval($state.params.isreg),
                    isReadOnly:false
                }
            }
        }])