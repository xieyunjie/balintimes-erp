/**
 * Created by Administrator on 2015/10/8.
 */
/**
 *
 */
"use strict"
define(['angularAMD', 'balintimesConstant', 'ui-bootstrap', 'angular-messages','angular-treetable'], function (angularAMD, balintimesConstant) {
    var app = angular.module("mediaModule", ['ui.router', 'ui.bootstrap', 'ngMessages','ngTreetable']);

    var mainState = {
        name: 'org/media',
        url: '/org/media',
        templateUrl: balintimesConstant.rootpath + '/views/org/media/list.html',
        controller: 'mediaListController'
    };
    var editState = {
        name: 'org/media/edit',
        url: '/org/media/edit/:uid',
        templateUrl: balintimesConstant.rootpath + '/views/org/media/edit.html',
        controller: 'mediaEditController',
        resolve: {
            mediaData: function (AjaxRequest, $stateParams) {
                return AjaxRequest.Post("/media/getone",{uid :$stateParams.uid});
            }
        }
    };



    app.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
        $stateProvider.state(mainState).state(editState);
    }]);



    app.factory("mediaServices", function (AjaxRequest) {
        return {
            listmediaByPage: function (params) {
                return AjaxRequest.Post("/media/listbypage",params);
            },
            deletemedia: function (UID) {
                return AjaxRequest.Post("/media/delete", {uid: UID});
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

    app.factory("lineServices", function (AjaxRequest) {
        return {
            lineData: function (params) {
                return AjaxRequest.Post("/line/listbypage",params);
            }
        }
    });

    app.factory("stationServices", function (AjaxRequest, $stateParams) {
        return {
            stationData: function (params) {
                return AjaxRequest.Post("/station/listbypage",params);
            }
        }
    });

    app.factory("mediatypeServices",function(AjaxRequest){
        return{
            mediatypeData:function(){
                return AjaxRequest.Post("/mediatype/listbypage",{
                    name:"",
                    page:1,
                    pageSize:20
                });
            }
        }
    });

    app.factory("trainServices", function (AjaxRequest) {
        return {
            trainData: function (params) {
                return AjaxRequest.Post("/train/listbypage",params);
            }
        }
    });

    app.factory("postypeServices", function (AjaxRequest) {
        return {
            postypeData: function (params) {
                return AjaxRequest.Post("/postype/listbypage",params);
            }
        }
    });

    app.factory("directionServices", function (AjaxRequest) {
        return {
            directionData: function (params) {
                return AjaxRequest.Post("/direction/listbypage",params);
            }
        }
    });

    app.controller("mediaListController", function ($scope, $state, $modal,$stateParams, AjaxRequest, DlgMsg, NgUtil,  mediaServices,$location,cityServices,stationServices) {
        $scope.medias = {};
        $scope.mediaTypes = [];
        $scope.searchParams = {};
        var params={ cityuid:"",name:"" };
        $scope.totalItems = 1;

        $scope.resetForm = function () {
            $scope.searchParams = NgUtil.initPageParams();
            mediaServices.listmediaByPage($scope.searchParams).then(function (rsBody) {
                $scope.medias = rsBody.data;
                $scope.searchParams.total = rsBody.total;
            });
        };
        $scope.init = function () {
            $scope.resetForm();
        };
        $scope.init();

        $scope.loadPage = function () {
            mediaServices.listmediaByPage($scope.searchParams).then(function (rsBody) {
                $scope.medias = rsBody.data;
                $scope.searchParams.total = rsBody.total;
            })
        };

        $scope.searchmedia = function () {
            mediaServices.listmediaByPage($scope.searchParams).then(function (rsBody) {
                $scope.medias = rsBody.data;
                $scope.searchParams.total = rsBody.total;

            })
        };

        $scope.deletemedia = function(UID) {
            DlgMsg.confirm('系统提示', '是否删除该线路').result.then(function(btn) {
                if (btn == "ok") {
                    mediaServices.deletemedia(UID).then(function(rsBody) {
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




    }).controller("mediaEditController", function ($scope, $state, mediaData,  AjaxRequest, TreeSelectModal,cityServices,stationServices,
                                                   lineServices,mediatypeServices,trainServices,postypeServices,directionServices) {
        $scope.media = mediaData.data;
        $scope.value1 = true;
        $scope.value2 = false;
        var original = angular.copy(mediaData.data);
        var lineParams={ cityuid:"",name:"" };
        var stationParams={ lineuid:"",name:"" };
        var trainParams={lineuid:"",name:"",cityuid:"",page:1,pageSize:500};
        var postypeParams={name:""};
        var directionParams={name:"",lineuid:"",postypeuid:"",page:1,pageSize:500};

        $scope.savemedia = function () {
            var url = "/media/update";
            if (angular.isUndefined($scope.media.uid) == true || $scope.media.uid == "0") {
                url = "/media/create"
            }

            AjaxRequest.Post(url, $scope.media).then(function (rsBody) {
                if (rsBody.success == 'true') {
                    $state.go('org/media');
                }
            })
        };
        $scope.revert = function () {
            $scope.media = angular.copy(original);
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

        $scope.$watch('media.cityuid', function(cityuid) {
            lineParams.cityuid=cityuid;
            lineServices.lineData(lineParams).then(function(rsBody){
                $scope.lines=rsBody.data;
            });
        });

        $scope.$watch('media.lineuid', function(lineuid) {
            stationParams.lineuid=lineuid;
            stationServices.stationData(stationParams).then(function(rsBody){
                $scope.stations=rsBody.data;
            });

            directionParams.lineuid=lineuid;
            directionServices.directionData(directionParams).then(function(rsBody){
                $scope.directions=rsBody.data;
            });
        });

        trainServices.trainData(trainParams).then(function(rsBody){
            $scope.trains=rsBody.data;
        });

        postypeServices.postypeData(postypeParams).then(function(rsBody){
            $scope.postypes=rsBody.data;
        });

        mediatypeServices.mediatypeData().then(function(rsBody){
            $scope.mediatypes=rsBody.data;
        });

        if($scope.media.stationuid!=null && $scope.media.stationuid!=""){
            $scope.value1 = true;
            $scope.value2 = false;
        }
        else if($scope.media.trainuid!=null && $scope.media.trainuid!=""){
            $scope.value1 = false;
            $scope.value2 = true;
        }


        var stationOriginal={
            stationuid:$scope.media.stationuid,
            directionuid:$scope.media.directionuid
        };
        var trainOriginal=$scope.media.trainuid;
        $scope.toggle=function(){
            $scope.value1 = !$scope.value1;
            $scope.value2 = !$scope.value2;

            if($scope.value1){
                trainOriginal=$scope.media.trainuid;
                $scope.media.trainuid="";
                $scope.media.stationuid=stationOriginal.stationuid;
                $scope.media.directionuid=stationOriginal.directionuid;
            }
            else if($scope.value2){
                stationOriginal={
                    stationuid:$scope.media.stationuid,
                    directionuid:$scope.media.directionuid
                };
                $scope.media.stationuid="";
                $scope.media.directionuid="";
                $scope.media.trainuid=trainOriginal;
            }
        };



    });

    app.controller('DatepickerDemoCtrl', ['$scope', function($scope) {
        $scope.today = function() {
            $scope.dt=new Date();
            $scope.dt2 = new Date();
        };
        $scope.today();

        $scope.clear = function () {
            $scope.dt=null;
            $scope.dt2 = null;
        };

        // Disable weekend selection
        $scope.disabled = function(date, mode) {
            return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
        };

        $scope.toggleMin = function() {
            $scope.minDate = $scope.minDate ? null : new Date();
        };
        $scope.toggleMin();

        $scope.open = function($event) {
            $event.preventDefault();
            $event.stopPropagation();

            if($scope.opened==false)
                $scope.opened = true;
            else
                $scope.opened=false;
        };

        $scope.dateOptions = {
            formatYear: 'yy',
            startingDay: 1,
            class: 'datepicker'
        };

        $scope.initDate = new Date('2016-15-20');
        $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
        $scope.format = $scope.formats[1];
    }])
    ;


    return {
        mainState: mainState,
        module: app
    };
})