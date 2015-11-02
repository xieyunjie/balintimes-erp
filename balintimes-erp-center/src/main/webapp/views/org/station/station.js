/**
 * Created by Administrator on 2015/10/14.
 */
"use strict"
define(['angularAMD', 'balintimesConstant', 'ui-bootstrap', 'angular-messages','angular-treetable'], function (angularAMD, balintimesConstant) {
    var app = angular.module("stationModule", ['ui.router', 'ui.bootstrap', 'ngMessages','ngTreetable']);
   
    var mainState = {
        name: 'org/station',
        url: '/org/station/:uid/:cityuid/:lineuid',
        templateUrl: balintimesConstant.rootpath + '/views/org/station/list.html',
        controller: 'stationListController'
    };
    var editState = {
        name: 'org/station/edit',
        url: '/org/station/edit/:uid/:cityuid/:lineuid',
        templateUrl: balintimesConstant.rootpath + '/views/org/station/edit.html',
        controller: 'stationEditController',
        resolve: {
            stationData: function (AjaxRequest, $stateParams) {
                return AjaxRequest.Post("/station/getonestation",{uid :$stateParams.uid});
            }
        }
    };
    
    app.config([ '$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
		$stateProvider.state(mainState).state(editState);
	} ]);

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

    app.factory("levelService",function(AjaxRequest,$stateParams){
       return{
           levelData:function(){
               return AjaxRequest.Post("/level/listbypage",{name:""});
           }
       }
    });

    app.controller("stationListController",function($scope,$state,AjaxRequest,DlgMsg, NgUtil,stationServices,TreeSelectModal, $stateParams){        		
            $scope.resetForm=function(params){
                stationServices.listStationByPage(params).then(function(rs){
                    $scope.stations=rs.data;
                })
            };

            $scope.init= function () {
            	
            	var params={
            			lineuid:$stateParams.uid
            	};
                $scope.resetForm(params);
            }

            $scope.init();



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
            };

        }
    ).controller("stationEditController",function($scope,$state,AjaxRequest,DlgMsg,NgUtil,stationData, $stateParams,levelService){
            $scope.station = stationData.data;
            var original = angular.copy(stationData.data);
            var params={ cityuid:"",name:"" };

            $scope.re={
                lineuid:"",
                cityuid:""
            };
            $scope.re.lineuid=$stateParams.lineuid;
            $scope.re.cityuid=$stateParams.cityuid;


            $scope.savestation = function () {
                var url = "/station/update"
                if (angular.isUndefined($scope.station.uid) == true || $scope.station.uid == "0") {
                    url = "/station/create"
                }

                AjaxRequest.Post(url, $scope.station).then(function (rsBody) {
                    if (rsBody.success == 'true') {
                        $state.go('org/station',{
                            uid:$stateParams.lineuid,
                            cityuid:$stateParams.cityuid
                        });
                    }
                })
            };
            $scope.revert = function () {
                $scope.station = angular.copy(original);
                $scope.editForm.$setPristine();
            };
            $scope.ShowTreeModal = function () {
                TreeSelectModal.show().result.then(function (node) {
                    console.info(node);
                });

            };

            params.cityuid=$stateParams.cityuid;
            AjaxRequest.Post('/line/listbypage', params).then(function (rsBody) {
                if (rsBody.success == 'true') {
                    $scope.lines=rsBody.data;
                    $scope.lines[0].uid=$stateParams.lineuid;
                }
            });

            levelService.levelData().then(function(rs){
                    $scope.levels=rs.data;
                }
            );

        }
    );

    return {
        mainState: mainState,
        module: app
    };
})


