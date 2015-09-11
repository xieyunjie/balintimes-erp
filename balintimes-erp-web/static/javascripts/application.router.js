/**
 * Created by AlexXie on 2015/8/26.
 */
'use strict';

angular.module('app')
    .config(['$stateProvider', '$urlRouterProvider', 'BMMROUTER', 'CRMROUTER',
        function ($stateProvider, $urlRouterProvider, BmmsRouter, CrmRouter) {

            var RetRouters = function (routers) {

                var results = [];
                var abstractState = "";
                angular.forEach(routers, function (router) {
                    var u = "";

                    if (angular.isArray(router.params)) {
                        u = "/" + router.state.replace(/_/g, "/") + "/:" + router.params.join("/:");
                    }
                    else {
                        u = "/" + router.state.replace(/_/g, "/");
                    }
                    var abstract = false;
                    if (angular.isDefined(router.abstract)) {
                        abstract = router.abstract;
                        if (router.abstract == true) {
                            abstractState = router.state;
                        }
                    }
                    var state = {
                        abstract: abstract,
                        url: u,
                        templateUrl: "/pages/" + router.url + ".view.html",
                        controller: router.controllername
                    };
                    if (angular.isDefined(router.controllername)) {
                        state.resolve = {
                            deps: ['$ocLazyLoad', 'UserMenuAuth', '$q',
                                function ($ocLazyLoad, UserMenuAuth, $q) {

                                    //var deferred = $q.defer();
                                    //var promise = deferred.promise;
                                    //
                                    //if (UserMenuAuth.check(abstractState + "." + router.state) == false) {
                                    //    promise.then(function(data){
                                    //        console.log(data);
                                    //    });
                                    //
                                    //    deferred.reject("$UnAuthState");
                                    //}
                                    //else {
                                    //    var sq = angular.isArray(router.script) ? angular.copy(router.script) : [];
                                    //    sq.push("/pages/" + router.url + ".controller.js");
                                    //    $ocLazyLoad.load(sq).then(function(data){
                                    //        deferred.resolve(data);
                                    //    });
                                    //}
                                    //return promise;
                                    console.log("resolveresolveresolveresolveresolveresolve");

                                    if (UserMenuAuth.check(abstractState + "." + router.state) == false) {
                                        return $q.reject("$UnAuthState");
                                    }


                                    var sq = angular.isArray(router.script) ? angular.copy(router.script) : [];
                                    sq.push("/pages/" + router.url + ".controller.js");
                                    return $ocLazyLoad.load(sq);
                                }
                            ]
                        }
                    }
                    if (abstract == true) {
                        $stateProvider.state(abstractState, state);

                    }
                    else {
                        $stateProvider.state(abstractState + "." + router.state, state);
                        results.push({
                            statename: abstractState + "." + router.state,
                            statevalue: state
                        })
                    }

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

            var rs = RetRouters(BmmsRouter);
            angular.forEach(rs, function (r) {
                $stateProvider.state(r.statename, r.statevalue);
            });
            rs = RetRouters(CrmRouter);
            angular.forEach(rs, function (r) {
                $stateProvider.state(r.statename, r.statevalue);
            });
        }]);