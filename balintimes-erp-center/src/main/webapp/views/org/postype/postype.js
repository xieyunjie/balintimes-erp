/**
 * Created by Administrator on 2015/10/28.
 */
"use strict"
define(['angularAMD', 'balintimesConstant', 'ui-bootstrap', 'angular-messages','angular-treetable'],function(angularAMD, balintimesConstant){
        var app=angular.module("postypemodule",['ui.router', 'ui.bootstrap', 'ngMessages','ngTreetable']);

        var mainState = {
            name: 'org/postype',
            url: '/org/postype',
            templateUrl: balintimesConstant.rootpath + '/views/org/postype/list.html',
            controller: 'postypeistController'
        };
        var editState = {
            name: 'org/postype/edit',
            url: '/org/postype/edit/:uid',
            templateUrl: balintimesConstant.rootpath + '/views/org/postype/edit.html',
            controller: 'postypeEditController',
            resolve: {
                postypeData: function (AjaxRequest, $stateParams) {
                    if($stateParams.uid=="0"){
                        return {data:{
                            uid:"0",
                            kid:"",
                            name:"",
                            value:""
                        }};
                    }else{
                        return AjaxRequest.Post("/postype/getonepostype",{uid :$stateParams.uid});
                    }
                }
            }
        };

        app.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            $stateProvider.state(mainState).state(editState);
        }]);

        app.factory("postypeServices", function (AjaxRequest) {
            return {
                listByPage: function (params) {
                    return AjaxRequest.Post("/postype/listbypage",params);
                },
                deletepostype: function (UID) {
                    return AjaxRequest.Post("/postype/delete", {uid: UID});
                }
            }

        });

        app.controller("postypeistController",function($scope, $state, $stateParams, AjaxRequest, DlgMsg, NgUtil,postypeServices){
            $scope.postypes = {};
            $scope.postypeTypes = [];
            $scope.searchParams = "";
            var params={ name:"" };
            $scope.totalItems = 1;

            $scope.resetForm = function () {
                postypeServices.listByPage(params).then(function (rsBody) {
                    $scope.postypes = rsBody.data;
                });
            };
            $scope.init = function () {
                $scope.resetForm();
            };
            $scope.init();

            $scope.loadPage = function () {
                postypeServices.listByPage($scope.searchParams).then(function (rsBody) {
                    $scope.postypes = rsBody.data;
                    $scope.searchParams.total = rsBody.total;
                })
            };

            $scope.searchpostype = function () {
                params.name=$scope.searchParams.name;
                postypeServices.listByPage(params).then(function (rsBody) {
                    $scope.postypes = rsBody.data;
                })
            };

            $scope.deletepostype = function(UID) {
                DlgMsg.confirm('系统提示', '是否删除该级别').result.then(function(btn) {
                    if (btn == "ok") {
                        postypeServices.deletepostype(UID).then(function(rsBody) {
                            if (rsBody.success == 'true') {
                                $scope.init();
                            }
                        })
                    }
                });
            };
        }).controller("postypeEditController",function($scope,$state,$stateParams,AjaxRequest,DlgMsg,NgUtil,postypeServices,postypeData){
            $scope.postype = postypeData.data;
            var original = angular.copy(postypeData.data);

            $scope.savepostype = function () {
                var url = "/postype/update"
                if (angular.isUndefined($scope.postype.uid) == true || $scope.postype.uid == "0") {
                    url = "/postype/create"
                }

                AjaxRequest.Post(url, $scope.postype).then(function (rsBody) {
                    if (rsBody.success == 'true') {
                        $state.go('org/postype');
                    }
                })
            };
            $scope.revert = function () {
                $scope.postype = angular.copy(original);
                $scope.editForm.$setPristine();
            };

        });

        return{
            mainState: mainState,
            module: app
        };

    }
)