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

    app.factory("stationServices", function (AjaxRequest, $stateParams) {
        return {
            listStationByPage: function (params) {
                return AjaxRequest.Post("/station/listbypage",params);
            },
            deletestation: function (UID) {
                return AjaxRequest.Post("/station/delete", {uid: UID});
            }
        }
    });

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

    app.controller("lineListController", function ($scope, $state, $modal,$stateParams, AjaxRequest, DlgMsg, NgUtil,  lineServices,$location,cityServices,stationServices) {
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


        //--------------------------------------站点设置----------------------------------------------------------------
         /*$scope.loadStationList=function(params){
            stationServices.listStationByPage(params).then(function(rs){
                $scope.stations=rs.data;
            })
        };

        $scope.showOneStationModal=function(parameters){
            var stationData={};
            if(parameters.uid=="0"){
                stationData={
                    uid:"0",
                    lineuid:parameters.lineuid
                };
                stationConfirm(stationData,parameters).result.then(function(isSuccess){
                    if(isSuccess==1){
                        var params={
                            lineuid:parameters.lineuid,
                            name:""
                        };

                        $scope.loadStationList(params);
                    }
                });
            }
            else{
                AjaxRequest.Post("/station/getonestation",{uid :parameters.uid}).then(function(rs){
                    stationData=rs.data;
                    stationConfirm(stationData,parameters).result.then(function(isSuccess){
                        if(isSuccess==1){
                            var params={
                                lineuid:parameters.lineuid,
                                name:""
                            };

                            $scope.loadStationList(params);
                        }
                    });
                });
            }


        };

        var stationConfirm=function(stationData,parameters){
            return $modal.open({
                animation:true,
                templateUrl:"editStationTpl",
                controller:function($scope,$modalInstance,stationData,AjaxRequest,lineServices,parameters){
                    $scope.station = stationData;
                    var original = angular.copy(stationData);

                    $scope.closeStationWin=function(){
                        $modalInstance.dismiss('cancel');
                    }

                    $scope.savestation=function(){
                        var isSuccess=0;
                        var url = "/station/update"
                        if (angular.isUndefined($scope.station.uid) == true || $scope.station.uid == "0") {
                            url = "/station/create"
                        }


                        AjaxRequest.Post(url, $scope.station).then(function (rsBody) {
                            if (rsBody.success == 'true') {
                                isSuccess=1;
                                $modalInstance.close(isSuccess);
                            }
                        })
                    }

                    $scope.revert=function(){
                        $scope.station=angular.copy(original);
                        $scope.editForm.$setPristine();
                    };

                    params.cityuid=parameters.cityuid;
                    AjaxRequest.Post('/line/listbypage', params).then(function (rsBody) {
                        if (rsBody.success == 'true') {
                            $scope.lines=rsBody.data;
                            $scope.lines[0].uid=parameters.lineuid;
                        }
                    })
                },
                resolve:{
                    stationData:function(){
                        return stationData;
                    },
                    parameters:function(){
                        return parameters;
                    }
                }
            })
        };

        $scope.deletestation= function (uid,name) {
            DlgMsg.confirm("警告","是否删除该站点"+name+"?").result.then(function(btn){
                if(btn=="ok"){
                    stationServices.deletestation(uid).then(function(rs){
                        if (rs.success == 'true') {
                            $scope.init();
                        }
                    })
                }
            })
        };*/

    }).controller("lineEditController", function ($scope, $state, lineData,  AjaxRequest, TreeSelectModal,cityServices,stationServices) {
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

        stationServices.listStationByPage({lineuid:$scope.line.uid}).then(function(rs){
            $scope.stations=rs.data;
        });
    });




    return {
        mainState: mainState,
        module: app
    };
})