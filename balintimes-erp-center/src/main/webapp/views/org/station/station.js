/**
 * Created by Administrator on 2015/10/14.
 */
"use strict"
define(['angularAMD', 'balintimesConstant', 'ui-bootstrap', 'angular-messages','angular-treetable'], function (angularAMD, balintimesConstant) {
    var app = angular.module("stationModule", ['ui.router', 'ui.bootstrap', 'ngMessages','ngTreetable']);
   
    var mainState = {
        name: 'org/station',
        url: '/org/station',
        templateUrl: balintimesConstant.rootpath + '/views/org/station/list.html',
        controller: 'stationListController'
    };
    var editState = {
        name: 'org/station/edit',
        url: '/org/station/edit/:uid',
        templateUrl: balintimesConstant.rootpath + '/views/org/station/edit.html',
        controller: 'stationEditController',
        resolve: {
            stationData: function (AjaxRequest, $stateParams) {
                //return AjaxRequest.Post("/station/getonestation",{uid :$stateParams.uid});
            }
        }
    };
    
    app.config([ '$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
		$stateProvider.state(mainState).state(editState);
	} ]);

    app.factory("stationServices", function (AjaxRequest) {
        return {
            listStationByPage: function (params) {
                return AjaxRequest.Post("/station/listbypage",params);
            },
            deletestation: function (UID) {
                return AjaxRequest.Post("/station/delete", {uid: UID});
            }
        }

    });

    app.controller("stationListController",function($scope,$state,AjaxRequest,DlgMsg, NgUtil,stationServices,TreeSelectModal){    	              
            $scope.resetForm=function(params){
                stationServices.listStationByPage(params).then(function(rs){
                    $scope.stations=rs.data;
                })
            };

            $scope.init= function () {
                $scope.resetForm($scope.searchParams);
            }

            $scope.init();

//            $scope.savestation = function () {
//                var url = "/station/update"
//                if (angular.isUndefined($scope.station.uid) == true || $scope.station.uid == "0") {
//                    url = "/station/create"
//                }
//
//                AjaxRequest.Post(url, $scope.station).then(function (rsBody) {
//                    if (rsBody.success == 'true') {
//                        $state.go('org/station');
//                    }
//                })
//            };
//            $scope.revert = function () {
//                $scope.station = angular.copy(original);
//                $scope.editForm.$setPristine();
//            };
//            $scope.ShowTreeModal = function () {
//                TreeSelectModal.show().result.then(function (node) {
//                    console.info(node);
//                });
//
//            };
        }
    ).controller("stationEditController",function($scope,$state,AjaxRequest,DlgMsg,NgUtil,stationData){
//            $scope.station = stationData.data;
//            var original = angular.copy(stationData.data);
//
//            $scope.savestation = function () {
//                var url = "/station/update"
//                if (angular.isUndefined($scope.station.uid) == true || $scope.station.uid == "0") {
//                    url = "/station/create"
//                }
//
//                AjaxRequest.Post(url, $scope.station).then(function (rsBody) {
//                    if (rsBody.success == 'true') {
//                        $state.go('org/station');
//                    }
//                })
//            };
//            $scope.revert = function () {
//                $scope.station = angular.copy(original);
//                $scope.editForm.$setPristine();
//            };
//            $scope.ShowTreeModal = function () {
//                TreeSelectModal.show().result.then(function (node) {
//                    console.info(node);
//                });
//
//            };
//
//            cityServices.cityData().then(function(rs){
//                $scope.cities=rs.data;
//            });
        }
    );

    return {
        mainState: mainState,
        module: app
    };
})


