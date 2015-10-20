/**
 * Created by Administrator on 2015/10/8.
 */
/**
 *
 */
"use strict"
define(['angularAMD', 'balintimesConstant', 'ui-bootstrap', 'angular-messages','angular-treetable'], function (angularAMD, balintimesConstant) {
    var app = angular.module("lineModule", ['ui.router', 'ui.bootstrap', 'ngMessages','ngTreetable']);

    var mainState = {
        name: 'org/line',
        url: '/org/line',
        templateUrl: balintimesConstant.rootpath + '/views/org/line/list.html',
        controller: 'lineListController'
    };
    var editState = {
        name: 'org/line/edit',
        url: '/org/line/edit/:uid',
        templateUrl: balintimesConstant.rootpath + '/views/org/line/edit.html',
        controller: 'lineEditController',
        resolve: {
            lineData: function (AjaxRequest, $stateParams) {
                return AjaxRequest.Post("/line/getoneline",{uid :$stateParams.uid});
            }
        }
    };



    app.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
        $stateProvider.state(mainState).state(editState);
    }]);



    app.factory("lineServices", function (AjaxRequest) {
        return {
            listlineByPage: function (params) {
                return AjaxRequest.Post("/line/listbypage",params);
            },
            deleteline: function (UID) {
                return AjaxRequest.Post("/line/delete", {uid: UID});
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

    app.controller("lineListController", function ($scope, $state, AjaxRequest, DlgMsg, NgUtil,  lineServices,$location,cityServices) {    	
        $scope.lines = {};
        $scope.lineTypes = [];
        $scope.searchParams = "";
        var params={ cityuid:"",name:"" };    
        $scope.totalItems = 1;

        $scope.resetForm = function () {
            lineServices.listlineByPage(params).then(function (rsBody) {
                $scope.lines = rsBody.data;
            });
        };
        $scope.init = function () {        	
            $scope.resetForm();
        };
        $scope.init();

        $scope.loadPage = function () {
            lineServices.listlineByPage($scope.searchParams).then(function (rsBody) {
                $scope.lines = rsBody.data;
                $scope.searchParams.total = rsBody.total;
            })
        };

        $scope.searchline = function () {
            lineServices.listlineByPage($scope.searchParams).then(function (rsBody) {
                $scope.lines = rsBody.data;
            })
        };

		$scope.deleteline = function(UID) {
			DlgMsg.confirm('系统提示', '是否删除该线路').result.then(function(btn) {
				if (btn == "ok") {
					lineServices.deleteline(UID).then(function(rsBody) {
						if (rsBody.success == 'true') {
							$scope.init();
						}
					})
				}
			});
		};

        cityServices.cityData().then(function(rs){          	
            $scope.cities=rs.data;                                  
        });
		

				

    }).controller("lineEditController", function ($scope, $state, lineData,  AjaxRequest, TreeSelectModal,cityServices) {
            $scope.line = lineData.data;
            var original = angular.copy(lineData.data);

            $scope.saveline = function () {
                var url = "/line/update"
                if (angular.isUndefined($scope.line.uid) == true || $scope.line.uid == "0") {
                    url = "/line/create"
                }

                AjaxRequest.Post(url, $scope.line).then(function (rsBody) {
                    if (rsBody.success == 'true') {
                        $state.go('org/line');
                    }
                })
            };
            $scope.revert = function () {
                $scope.line = angular.copy(original);
                $scope.editForm.$setPristine();
            };
            $scope.ShowTreeModal = function () {
                TreeSelectModal.show().result.then(function (node) {
                    console.info(node);
                });

            };
        
        cityServices.cityData().then(function(rs){          	
            $scope.cities=rs.data;                                  
        });
        
    });



    return {
        mainState: mainState,
        module: app
    };
})