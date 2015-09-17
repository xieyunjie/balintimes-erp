'use strict';


angular.module('app', [
    'ngAnimate',
    'ngCookies',
    'ngTouch',
    'ngStorage',
    'ui.router',
    'ui.bootstrap',
    'oc.lazyLoad',
    'ui.load',
    //'ui.jq',
    'ui.validate',
    'angular-lodash',
    'pascalprecht.translate',
    'ngSanitize',
    'ngTable',
    'blockUI',
    'gc.toaster'
]).run(['$rootScope', '$state', '$stateParams', '$timeout', '$window',
    function ($rootScope, $state, $stateParams, $timeout, $window) {
        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;

        $rootScope.$on("$stateChangeSuccess", function () {
            $timeout(function () {
                console.info("Local:" + $window.location.pathname + $window.location.hash);

            });
        });
        $rootScope.$on("$stateChangeError", function (event, toState, toParams, fromState, fromParams, err) {
            $timeout(function () {
                console.log(event);
                console.log(err);
                if (err === "$UnAuthState") {
                    $state.go("app.notauth");
                }
            });
        });

        $rootScope.$on("$stateChangeStart", function () {
            $timeout(function () {
                console.info("To:" + $window.location.pathname + $window.location.hash);
            });
        });
    }
]);