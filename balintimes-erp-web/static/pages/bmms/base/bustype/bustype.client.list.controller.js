/**
 * Created by AlexXie on 2015/9/22.
 */
"use strict";

angular.module("BMMS_Base_BusType_List_Module", ['ui.grid.selection'])
    .controller("BMMS_Base_BusType_List_Controller",
    ["$scope", "BMMS_Line_Service", "UserStgService", function ($scope, BMMS_Line_Service, UserStgService) {
        var vm = $scope.vm = {
            title: "车型"
        };
        $scope.gridOption = {
            enableSorting: true,
            enableRowSelection: true, enableRowHeaderSelection: false,
            multiSelect: false,
            columnDefs: [
                {field: 'name', displayName: 'name'},
                {field: 'uid', displayName: 'uidw'},
                {field: 'createtime', displayName: 'createtime'}
            ]
        };

        $scope.initBusTypeCtrl = function () {

            BMMS_Line_Service.queryLine({query: 111, page: 1, pagesize: 20}).then(function (res) {

                $scope.gridOption.data = res.data;
            });

        };

        $scope.consoleWebuser = function () {
            console.log(UserStgService.getWebuser());
        };
    }]);