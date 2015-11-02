/**
 * Created by Administrator on 2015/10/26.
 */
"use strict"
define(['angularAMD', 'balintimesConstant', 'ui-bootstrap', 'angular-messages','angular-treetable'],function(angularAMD, balintimesConstant){
        var app=angular.module("levelmodule",['ui.router', 'ui.bootstrap', 'ngMessages','ngTreetable']);

        var mainState = {
            name: 'org/level',
            url: '/org/level',
            templateUrl: balintimesConstant.rootpath + '/views/org/level/list.html',
            controller: 'levelistController'
        };
        var editState = {
            name: 'org/level/edit',
            url: '/org/level/edit/:uid',
            templateUrl: balintimesConstant.rootpath + '/views/org/level/edit.html',
            controller: 'levelEditController',
            resolve: {
                levelData: function (AjaxRequest, $stateParams) {
                    if($stateParams.uid=="0"){
                        return {data:{
                            uid:"0",
                            kid:"",
                            name:"",
                            value:""
                        }};
                    }else{
                        return AjaxRequest.Post("/level/getonelevel",{uid :$stateParams.uid});
                    }
                }
            }
        };

        app.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            $stateProvider.state(mainState).state(editState);
        }]);

        app.factory("levelServices", function (AjaxRequest) {
            return {
                listByPage: function (params) {
                    return AjaxRequest.Post("/level/listbypage",params);
                },
                deletelevel: function (UID) {
                    return AjaxRequest.Post("/level/delete", {uid: UID});
                }
            }

        });

        app.controller("levelistController",function($scope, $state, $stateParams, AjaxRequest, DlgMsg, NgUtil,levelServices){
            $scope.levels = {};
            $scope.levelTypes = [];
            $scope.searchParams = "";
            var params={ name:"" };
            $scope.totalItems = 1;

            $scope.resetForm = function () {
                levelServices.listByPage(params).then(function (rsBody) {
                    $scope.levels = rsBody.data;
                });
            };
            $scope.init = function () {
                $scope.resetForm();
            };
            $scope.init();

            $scope.loadPage = function () {
                levelServices.listByPage($scope.searchParams).then(function (rsBody) {
                    $scope.levels = rsBody.data;
                    $scope.searchParams.total = rsBody.total;
                })
            };

            $scope.searchlevel = function () {
                params.name=$scope.searchParams.name;
                levelServices.listByPage(params).then(function (rsBody) {
                    $scope.levels = rsBody.data;
                })
            };

            $scope.deletelevel = function(UID) {
                DlgMsg.confirm('系统提示', '是否删除该级别').result.then(function(btn) {
                    if (btn == "ok") {
                        levelServices.deletelevel(UID).then(function(rsBody) {
                            if (rsBody.success == 'true') {
                                $scope.init();
                            }
                        })
                    }
                });
            };
        }).controller("levelEditController",function($scope,$state,$stateParams,AjaxRequest,DlgMsg,NgUtil,levelServices,levelData){
            $scope.level = levelData.data;
            var original = angular.copy(levelData.data);

            $scope.savelevel = function () {
                var url = "/level/update"
                if (angular.isUndefined($scope.level.uid) == true || $scope.level.uid == "0") {
                    url = "/level/create"
                }

                AjaxRequest.Post(url, $scope.level).then(function (rsBody) {
                    if (rsBody.success == 'true') {
                        $state.go('org/level');
                    }
                })
            };
            $scope.revert = function () {
                $scope.level = angular.copy(original);
                $scope.editForm.$setPristine();
            };

        });

        return{
            mainState: mainState,
            module: app
        };

    }
)