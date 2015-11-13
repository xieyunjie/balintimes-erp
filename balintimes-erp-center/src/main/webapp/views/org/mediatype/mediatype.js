/**
 * Created by Administrator on 2015/10/26.
 */
"use strict"
define(['angularAMD', 'balintimesConstant', 'ui-bootstrap', 'angular-messages','angular-treetable'],function(angularAMD, balintimesConstant){
        var app=angular.module("mediatypemodule",['ui.router', 'ui.bootstrap', 'ngMessages','ngTreetable']);

        var mainState = {
            name: 'org/mediatype',
            url: '/org/mediatype',
            templateUrl: balintimesConstant.rootpath + '/views/org/mediatype/list.html',
            controller: 'mediatypeListController'
        };
        var editState = {
            name: 'org/mediatype/edit',
            url: '/org/mediatype/edit/:uid',
            templateUrl: balintimesConstant.rootpath + '/views/org/mediatype/edit.html',
            controller: 'mediatypeEditController',
            resolve: {
                mediatypeData: function (AjaxRequest, $stateParams) {
                    if($stateParams.uid=="0"){
                        return {data:{
                            uid:"0",
                            kid:"",
                            name:"",
                            value:""
                        }};
                    }else{
                        return AjaxRequest.Post("/mediatype/getone",{uid :$stateParams.uid});
                    }
                }
            }
        };

        app.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            $stateProvider.state(mainState).state(editState);
        }]);

        app.factory("mediatypeServices", function (AjaxRequest) {
            return {
                listByPage: function (params) {
                    return AjaxRequest.Post("/mediatype/listbypage",params);
                },
                deletemediatype: function (UID) {
                    return AjaxRequest.Post("/mediatype/delete", {uid: UID});
                }
            }

        });

        app.factory("mediatypecategoryServices", function (AjaxRequest) {
            return {
                mediaTypeCategroyData: function (params) {
                    return AjaxRequest.Post("/mediatypecategory/listbypage",params);
                }
            }
        });

        app.controller("mediatypeListController",function($scope, $state, $stateParams, AjaxRequest, DlgMsg, NgUtil,mediatypeServices){
            $scope.mediatypes = {};
            $scope.mediatypeTypes = [];
            $scope.searchParams = "";
            var params={ name:"" };

            $scope.resetForm = function () {
                $scope.searchParams = NgUtil.initPageParams();
                mediatypeServices.listByPage($scope.searchParams).then(function (rsBody) {
                    $scope.mediatypes = rsBody.data;
                    $scope.searchParams.total = rsBody.total;
                });
            };
            $scope.init = function () {
                $scope.resetForm();
            };
            $scope.init();

            $scope.loadPage = function () {
                mediatypeServices.listByPage($scope.searchParams).then(function (rsBody) {
                    $scope.mediatypes = rsBody.data;
                    $scope.searchParams.total = rsBody.total;
                })
            };

            $scope.searchmediatype = function () {
                mediatypeServices.listByPage($scope.searchParams).then(function (rsBody) {
                    $scope.mediatypes = rsBody.data;
                    $scope.searchParams.total = rsBody.total;
                })
            };

            $scope.deletemediatype = function(UID) {
                DlgMsg.confirm('系统提示', '是否删除该类型').result.then(function(btn) {
                    if (btn == "ok") {
                        mediatypeServices.deletemediatype(UID).then(function(rsBody) {
                            if (rsBody.success == 'true') {
                                $scope.init();
                            }
                        })
                    }
                });
            };
        }).controller("mediatypeEditController",function($scope,$state,$stateParams,AjaxRequest,DlgMsg,NgUtil,mediatypeServices,mediatypeData,mediatypecategoryServices){
            $scope.mediatype = mediatypeData.data;
            var original = angular.copy(mediatypeData.data);

            $scope.savemediatype = function () {
                var url = "/mediatype/update"
                if (angular.isUndefined($scope.mediatype.uid) == true || $scope.mediatype.uid == "0") {
                    url = "/mediatype/create"
                }

                AjaxRequest.Post(url, $scope.mediatype).then(function (rsBody) {
                    if (rsBody.success == 'true') {
                        $state.go('org/mediatype');
                    }
                })
            };
            $scope.revert = function () {
                $scope.mediatype = angular.copy(original);
                $scope.editForm.$setPristine();
            };

            mediatypecategoryServices.mediaTypeCategroyData({name:""}).then(function(rsBody){
                $scope.mediatypecategorys=rsBody.data;
            });
        });

        return{
            mainState: mainState,
            module: app
        };

    }
)