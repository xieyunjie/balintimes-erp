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
    'ngTable'
]).run(['$rootScope', '$state', '$stateParams',
    function ($rootScope, $state, $stateParams) {
        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;
    }
]);