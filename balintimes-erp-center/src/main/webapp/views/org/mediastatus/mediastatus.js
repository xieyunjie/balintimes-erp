/**
 * Created by Administrator on 2015/10/26.
 */
"use strict"
define(['angularAMD', 'balintimesConstant', 'ui-bootstrap', 'angular-messages','angular-treetable'],function(angularAMD, balintimesConstant){
        var app=angular.module("mediastatusmodule",['ui.router', 'ui.bootstrap', 'ngMessages','ngTreetable']);

        var mainState = {
            name: 'org/mediastatus',
            url: '/org/mediastatus',
            templateUrl: balintimesConstant.rootpath + '/views/org/mediastatus/list.html',
            controller: 'mediastatusListController'
        };
        var editState = {
            name: 'org/mediastatus/edit',
            url: '/org/mediastatus/edit/:uid',
            templateUrl: balintimesConstant.rootpath + '/views/org/mediastatus/edit.html',
            controller: 'mediastatusEditController',
            resolve: {
                mediastatusData: function (AjaxRequest, $stateParams) {
                    if($stateParams.uid=="0"){
                        return {data:{
                            uid:"0",
                            kid:"",
                            name:"",
                            value:""
                        }};
                    }else{
                        return AjaxRequest.Post("/mediastatus/getonemediastatus",{uid :$stateParams.uid});
                    }
                }
            }
        };

        app.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            $stateProvider.state(mainState).state(editState);
        }]);

        app.factory("mediastatusServices", function (AjaxRequest) {
            return {
                listByPage: function (params) {
                    return AjaxRequest.Post("/mediastatus/listbypage",params);
                },
                deletemediastatus: function (UID) {
                    return AjaxRequest.Post("/mediastatus/delete", {uid: UID});
                }
            }

        });

        app.controller("mediastatusListController",function($scope, $state, $stateParams, AjaxRequest, DlgMsg, NgUtil,mediastatusServices){
            $scope.mediastatuss = {};
            $scope.mediastatusTypes = [];
            $scope.searchParams = "";
            var params={ name:"" };
            $scope.totalItems = 1;

            $scope.resetForm = function () {
                mediastatusServices.listByPage(params).then(function (rsBody) {
                    $scope.mediastatuss = rsBody.data;
                });
            };
            $scope.init = function () {
                $scope.resetForm();
            };
            $scope.init();

            $scope.loadPage = function () {
                mediastatusServices.listByPage($scope.searchParams).then(function (rsBody) {
                    $scope.mediastatuss = rsBody.data;
                    $scope.searchParams.total = rsBody.total;
                })
            };

            $scope.searchmediastatus = function () {
                params.name=$scope.searchParams.name;
                mediastatusServices.listByPage(params).then(function (rsBody) {
                    $scope.mediastatuss = rsBody.data;
                })
            };

            $scope.deletemediastatus = function(UID) {
                DlgMsg.confirm('系统提示', '是否删除该状态').result.then(function(btn) {
                    if (btn == "ok") {
                        mediastatusServices.deletemediastatus(UID).then(function(rsBody) {
                            if (rsBody.success == 'true') {
                                $scope.init();
                            }
                        })
                    }
                });
            };
        }).controller("mediastatusEditController",function($scope,$state,$stateParams,AjaxRequest,DlgMsg,NgUtil,mediastatusServices,mediastatusData){
            $scope.mediastatus = mediastatusData.data;
            var original = angular.copy(mediastatusData.data);

            $scope.savemediastatus = function () {
                var url = "/mediastatus/update"
                if (angular.isUndefined($scope.mediastatus.uid) == true || $scope.mediastatus.uid == "0") {
                    url = "/mediastatus/create"
                }

                AjaxRequest.Post(url, $scope.mediastatus).then(function (rsBody) {
                    if (rsBody.success == 'true') {
                        $state.go('org/mediastatus');
                    }
                })
            };
            $scope.revert = function () {
                $scope.mediastatus = angular.copy(original);
                $scope.editForm.$setPristine();
            };

        });

        return{
            mainState: mainState,
            module: app
        };

    }
)