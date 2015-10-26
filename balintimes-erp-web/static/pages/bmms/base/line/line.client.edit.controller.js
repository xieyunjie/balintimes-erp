/**
 * Created by AlexXie on 2015/8/21.
 */
'use strict';

angular.module('BMMS_Line_Edit_Module', []).controller('BMMS_Line_Edit_Controller',
    ['$scope', '$state', '$timeout', 'BMMS_Line_Service', 'CRM_BaseData_Service', function ($scope, $state, $timeout, BMMS_Line_Service, CRM_BaseData_Service) {
        var vm = $scope.vm = {
            title: 'Edit',
            line: {},
            availableColors: ['Red', 'Green', 'Blue', 'Yellow', 'Magenta', 'Maroon', 'Umbra', 'Turquoise'],
            colors: [],

            businesstypelist: [],
            selectedTypes: [],
            selectedType: ""
        };

        $scope.initEdit = function () {
            //if ($state.params.uid == "0") {
            //    line.uid = "0"
            //}
            //else {
            //
            //    BMMS_Line_Service.getLine({uid: $state.params.uid}).then(function (res) {
            //        vm.line = res.data;
            //    });
            //}
            $timeout(function () {
                vm.selectedType = "72cf48dc-55f3-11e5-999d-c86000a05d5f";
                vm.selectedTypes = [{
                    "uid": "72cf453f-55f3-11e5-999d-c86000a05d5f",
                    "name": "饮料",
                    "code": "1",
                    "priority": 1,
                    "comment": ""
                }, {
                    "uid": "72cf45c0-55f3-11e5-999d-c86000a05d5f",
                    "name": "活动/会展类",
                    "code": "1",
                    "priority": 1,
                    "comment": ""
                }];
            });

            CRM_BaseData_Service.businessTypeList().then(function (res) {

                vm.businesstypelist = res.data;
            });
        };
        $scope.saveLine = function (picFile,xlsFile) {
            var l = angular.copy(vm.line);
            if(picFile) l.picFile = picFile;
            if(xlsFile) l.xlsFile = xlsFile;

            BMMS_Line_Service.uploadLine(l).then(function(res){
                console.log(res);
            });
        };
        $scope.setBusiness = function () {
            vm.selectedType = "72cf419e-55f3-11e5-999d-c86000a05d5f";
        }

    }]);