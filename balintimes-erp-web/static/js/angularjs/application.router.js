/**
 * Created by AlexXie on 2015/8/26.
 */
'use strict';

angular.module('app')
    .config(['$stateProvider', '$urlRouterProvider', 'BMMROUTER', 'CRMROUTER',
        function ($stateProvider, $urlRouterProvider, BmmsRouter, CrmRouter) {

            var regRouter = function (router) {
                var abstract = false;
                if (angular.isDefined(router.abstract)) {
                    abstract = router.abstract;
                }
                var url = router.url;
                if (angular.isArray(router.params)) {
                    url +=  "/:" + router.params.join("/:");
                }
                var state = {
                    abstract: abstract,
                    url: url,
                    templateUrl: "/pages/" + router.filepath + ".view.html",
                    controller: router.controllername
                };

                if (angular.isDefined(router.controllername)) {
                    state.resolve = {
                        deps: ['$ocLazyLoad','UserStgService', '$q',
                            function ($ocLazyLoad, UserStgService, $q) {
                                if(UserStgService.checkMenuAuth(router.state) == false){
                                    return $q.reject("$UnAuthState");
                                }

                                var sq = angular.isArray(router.script) ? angular.copy(router.script) : [];
                                sq.push("/pages/" + router.filepath + ".controller.js");
                                return $ocLazyLoad.load(sq);
                            }]
                    }
                }
                $stateProvider.state(router.state, state);
            };

            var recursionRouter = function (routers) {
                angular.forEach(routers, function (router) {
                    regRouter(router);
                });
            };


            $urlRouterProvider.otherwise('/app/index');

            $stateProvider.state("app", {
                abstract: true,
                url: '/app',
                templateUrl: '/pages/app.view.html'
            });
            $stateProvider.state("app.index", {
                url: '/index',
                templateUrl: '/pages/index.view.html'
            });
            $stateProvider.state("app.notauth", {
                url: '/noauth',
                templateUrl: '/pages/error/NotAuth.html'
            });


            recursionRouter(BmmsRouter);
            recursionRouter(CrmRouter);
        }
    ]);