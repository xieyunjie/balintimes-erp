/**
 * Created by Administrator on 2015/10/26.
 */
"use strict"
define(['angularAMD', 'balintimesConstant', 'ui-bootstrap', 'angular-messages','angular-treetable'],function(angularAMD, balintimesConstant){
        var app=angular.module("mediatypecategorymodule",['ui.router', 'ui.bootstrap', 'ngMessages','ngTreetable']);

        var mainState = {
            name: 'org/mediatypecategory',
            url: '/org/mediatypecategory',
            templateUrl: balintimesConstant.rootpath + '/views/org/mediatypecategory/list.html',
            controller: 'mediatypecategoryListController'
        };
        var editState = {
            name: 'org/mediatypecategory/edit',
            url: '/org/mediatypecategory/edit/:uid',
            templateUrl: balintimesConstant.rootpath + '/views/org/mediatypecategory/edit.html',
            controller: 'mediatypecategoryEditController',
            resolve: {
                mediatypecategoryData: function (AjaxRequest, $stateParams) {
                    if($stateParams.uid=="0"){
                        return {data:{
                            uid:"0",
                            kid:"",
                            name:"",
                            value:""
                        }};
                    }else{
                        return AjaxRequest.Post("/mediatypecategory/getonemediatypecategory",{uid :$stateParams.uid});
                    }
                }
            }
        };

        app.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            $stateProvider.state(mainState).state(editState);
        }]);

        app.factory("mediatypecategoryServices", function (AjaxRequest) {
            return {
                listByPage: function (params) {
                    return AjaxRequest.Post("/mediatypecategory/listbypage",params);
                },
                deletemediatypecategory: function (UID) {
                    return AjaxRequest.Post("/mediatypecategory/delete", {uid: UID});
                }
            }

        });

        app.controller("mediatypecategoryListController",function($scope, $state, $stateParams, AjaxRequest, DlgMsg, NgUtil,mediatypecategoryServices){
            $scope.mediatypecategorys = {};
            $scope.mediatypecategoryTypes = [];
            $scope.searchParams = "";
            var params={ name:"" };
            $scope.totalItems = 1;

            $scope.resetForm = function () {
                mediatypecategoryServices.listByPage(params).then(function (rsBody) {
                    $scope.mediatypecategorys = rsBody.data;
                });
            };
            $scope.init = function () {
                $scope.resetForm();
            };
            $scope.init();

            $scope.loadPage = function () {
                mediatypecategoryServices.listByPage($scope.searchParams).then(function (rsBody) {
                    $scope.mediatypecategorys = rsBody.data;
                    $scope.searchParams.total = rsBody.total;
                })
            };

            $scope.searchmediatypecategory = function () {
                params.name=$scope.searchParams.name;
                mediatypecategoryServices.listByPage(params).then(function (rsBody) {
                    $scope.mediatypecategorys = rsBody.data;
                })
            };

            $scope.deletemediatypecategory = function(UID) {
                DlgMsg.confirm('系统提示', '是否删除该分类').result.then(function(btn) {
                    if (btn == "ok") {
                        mediatypecategoryServices.deletemediatypecategory(UID).then(function(rsBody) {
                            if (rsBody.success == 'true') {
                                $scope.init();
                            }
                        })
                    }
                });
            };
        }).controller("mediatypecategoryEditController",function($scope,$state,$stateParams,AjaxRequest,DlgMsg,NgUtil,mediatypecategoryServices,mediatypecategoryData){
            $scope.mediatypecategory = mediatypecategoryData.data;
            var original = angular.copy(mediatypecategoryData.data);

            $scope.savemediatypecategory = function () {
                var url = "/mediatypecategory/update"
                if (angular.isUndefined($scope.mediatypecategory.uid) == true || $scope.mediatypecategory.uid == "0") {
                    url = "/mediatypecategory/create"
                }

                AjaxRequest.Post(url, $scope.mediatypecategory).then(function (rsBody) {
                    if (rsBody.success == 'true') {
                        $state.go('org/mediatypecategory');
                    }
                })
            };
            $scope.revert = function () {
                $scope.mediatypecategory = angular.copy(original);
                $scope.editForm.$setPristine();
            };

        });

        return{
            mainState: mainState,
            module: app
        };

    }
)