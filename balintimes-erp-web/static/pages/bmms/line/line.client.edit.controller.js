/**
 * Created by AlexXie on 2015/8/21.
 */
'use strict';

angular.module('BMMS_Line_Edit_Module', []).controller('BMMS_Line_Edit_Controller', ['$scope', '$state', 'BMMS_Line_Service', function ($scope, $state, BMMS_Line_Service) {
    var vm = $scope.vm = {
        title: 'Edit',
        line: {}
    };

    $scope.initEdit = function () {
        if ($state.params.uid == "0") {
            line.uid = "0"
        }
        else {

            BMMS_Line_Service.getLine({uid: $state.params.uid}).then(function (res) {
                vm.line = res.data;
            });
        }
    };
    $scope.saveLine = function () {
        BMMS_Line_Service.saveLine(vm.line);
    }

}]);