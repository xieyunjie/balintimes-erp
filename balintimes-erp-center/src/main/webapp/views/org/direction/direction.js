/**
 * Created by Administrator on 2015/10/26.
 */
"use strict"
define(['angularAMD', 'balintimesConstant', 'ui-bootstrap', 'angular-messages','angular-treetable'],function(angularAMD, balintimesConstant){
        var app=angular.module("directionmodule",['ui.router', 'ui.bootstrap', 'ngMessages','ngTreetable']);

        var mainState = {
            name: 'org/direction',
            url: '/org/direction',
            templateUrl: balintimesConstant.rootpath + '/views/org/direction/list.html',
            controller: 'directionlistController'
        };
        var editState = {
            name: 'org/direction/edit',
            url: '/org/direction/edit/:uid/:cityuid',
            templateUrl: balintimesConstant.rootpath + '/views/org/direction/edit.html',
            controller: 'directionEditController',
            resolve: {
                directionData: function (AjaxRequest, $stateParams) {
                    if($stateParams.uid=="0"){
                        return {data:{
                            uid:"0",
                            kid:"",
                            name:"",
                            value:""
                        }};
                    }else{
                        return AjaxRequest.Post("/direction/getonedirection",{uid :$stateParams.uid});
                    }
                }
            }
        };

        app.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            $stateProvider.state(mainState).state(editState);
        }]);

        app.factory("directionServices", function (AjaxRequest) {
            return {
                listByPage: function (params) {
                    return AjaxRequest.Post("/direction/listbypage",params);
                },
                deletedirection: function (UID) {
                    return AjaxRequest.Post("/direction/delete", {uid: UID});
                }
            }

        });

        app.factory("lineServices", function (AjaxRequest) {
            return {
                lineData: function (params) {
                    return AjaxRequest.Post("/line/listbypage",params);
                }
            }
        });

        app.factory("cityServices",function(AjaxRequest){
            return{
                cityData:function(){
                    return AjaxRequest.Post("/city/list");
                }
            }
        });

        app.factory("postypeServices",function(AjaxRequest){
            return{
                postypeData:function(){
                    return AjaxRequest.Post("/postype/listbypage",{name:""});
                }
            }
        });

        app.controller("directionlistController",function($scope, $state, $stateParams, AjaxRequest, DlgMsg, NgUtil,directionServices,lineServices,cityServices,postypeServices){
            $scope.directions = {};
            $scope.directionTypes = [];
            $scope.searchParams = {};
            var lineParams={ cityuid:"",name:"" };

            $scope.resetForm = function () {
                $scope.searchParams = NgUtil.initPageParams();
                directionServices.listByPage($scope.searchParams).then(function (rsBody) {
                    $scope.directions = rsBody.data;
                });
            };
            $scope.init = function () {
                $scope.resetForm();
            };
            $scope.init();

            $scope.loadPage = function () {
                directionServices.listByPage($scope.searchParams).then(function (rsBody) {
                    $scope.directions = rsBody.data;
                    $scope.searchParams.total = rsBody.total;
                })
            };

            $scope.searchDirection = function () {
                directionServices.listByPage( $scope.searchParams).then(function (rsBody) {
                    $scope.directions = rsBody.data;
                    $scope.searchParams.total = rsBody.total;
                })
            };

            $scope.deleteDirection = function(UID) {
                DlgMsg.confirm('系统提示', '是否删除该方向').result.then(function(btn) {
                    if (btn == "ok") {
                        directionServices.deletedirection(UID).then(function(rsBody) {
                            if (rsBody.success == 'true') {
                                $scope.init();
                            }
                        })
                    }
                });
            };

            cityServices.cityData().then(function(rsBody){
                $scope.cities=rsBody.data;
                $scope.searchParams.cityuid=rsBody.data[3].uid;
            });

            $scope.$watch('searchParams.cityuid', function(cityuid) {
                lineParams.cityuid=cityuid;
                lineServices.lineData(lineParams).then(function(rsBody){
                    $scope.lines=rsBody.data;
                });
            });

            postypeServices.postypeData().then(function(rsBody){
                $scope.postypes=rsBody.data;
            });

        }).controller("directionEditController",function($scope,$state,$stateParams,AjaxRequest,DlgMsg,NgUtil,directionServices,directionData,lineServices,cityServices,postypeServices){
            $scope.direction = directionData.data;
            var original = angular.copy(directionData.data);
            var lineParams={ cityuid:"",name:"" };

            $scope.savedirection = function () {
                var url = "/direction/update"
                if (angular.isUndefined($scope.direction.uid) == true || $scope.direction.uid == "0") {
                    url = "/direction/create"
                }

                AjaxRequest.Post(url, $scope.direction).then(function (rsBody) {
                    if (rsBody.success == 'true') {
                        $state.go('org/direction');
                    }
                })
            };
            $scope.revert = function () {
                $scope.direction = angular.copy(original);
                $scope.editForm.$setPristine();
            };

            cityServices.cityData().then(function(rsBody){
                $scope.cities=rsBody.data;
                $scope.direction.cityuid=$stateParams.cityuid;
            });

            $scope.$watch('direction.cityuid', function(cityuid) {
                lineParams.cityuid=cityuid;
                lineServices.lineData(lineParams).then(function(rsBody){
                    $scope.lines=rsBody.data;
                });
            });

            postypeServices.postypeData().then(function(rsBody){
                $scope.postypes=rsBody.data;
            });
        });

        return{
            mainState: mainState,
            module: app
        };

    }
)