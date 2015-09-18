/**
 * Created by AlexXie on 2015/8/28.
 */
'use strict';
angular.module('CRM_Customer_Edit_Module', []).controller('CRM_Customer_Edit_Controller', ['$scope', 'CRM_Customer_Service', 'CRM_BaseData_Service', 'AlertMsg', 'DlgMsg',
    function ($scope) {
        var vm = $scope.vm = {
            title: '编辑客户信息',
            customer:{}
        };
    }]);