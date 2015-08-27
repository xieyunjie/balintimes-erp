/**
 * Created by AlexXie on 2015/8/26.
 */
'use strict';

angular.module('app').constant("ERPROUTERS", {
    bmms_line_list: {
        state: 'bmms_line_list',
        controllername: 'BMMS_Line_List_Controller',
        url: 'bmms/line/line.client.list',

        script: ["BMM_Line_Service"]
    },
    bmms_line_edit: {
        state: 'bmms_line_edit',
        params: ["uid"],
        controllername: 'BMMS_Line_Edit_Controller',
        url: 'bmms/line/line.client.edit',

        script: ["BMM_Line_Service"]
    }
}).config(['$stateProvider', '$urlRouterProvider', 'ERPROUTERS',

    function ($stateProvider, $urlRouterProvider, erpRouters) {


        $urlRouterProvider.otherwise('/app/index');

        $stateProvider.state("app", {
            abstract: true,
            url: '/app',
            templateUrl: '/pages/app.html'
        });
        $stateProvider.state("app.index", {
            url: '/index',
            templateUrl: '/pages/index.html'
        });

        angular.forEach(erpRouters, function (router) {
            var u = "";

            if (angular.isArray(router.params)) {
                u = "/" + router.state.replace(/_/g, "/") + "/:" + router.params.join("/:");
            }
            else {
                u = "/" + router.state.replace(/_/g, "/");
            }
            var state = {
                url: u,
                templateUrl: "/pages/" + router.url + ".view.html",
                controller: router.controllername
            };
            state.resolve = {
                deps: ['$ocLazyLoad',
                    function ($ocLazyLoad) {
                        var sq = angular.isArray(router.script) ? angular.copy(router.script) : [];
                        sq.push("/pages/" + router.url + ".controller.js");
                        return $ocLazyLoad.load(sq);
                    }
                ]
            };
            //
            //if (angular.isArray(router.script)) {
            //    state.resolve = {
            //        deps: ['$ocLazyLoad',
            //            function ($ocLazyLoad) {
            //                return $ocLazyLoad.load(router.script).then(function () {
            //                    return $ocLazyLoad.load(["/pages/" + router.url + ".controller.js"]);
            //                })
            //            }
            //        ]
            //    }
            //}
            //else {
            //    state.resolve = {
            //        deps: ['$ocLazyLoad',
            //            function ($ocLazyLoad) {
            //                return $ocLazyLoad.load(["/pages/" + router.url + ".controller.js"]);
            //            }
            //        ]
            //    }
            //}
            $stateProvider.state("app." + router.state, state);
        });

    }]);