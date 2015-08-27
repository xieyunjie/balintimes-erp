/**
 * Created by AlexXie on 2015/8/26.
 */
'use strict';
angular.module('app').config(['$httpProvider', function ($httpProvider) {

    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

    $httpProvider.defaults.transformRequest = [function (data) {
        if (data) {
            return $.param(data);
        }
        return data;
    }];

    $httpProvider.interceptors.push('securityInterceptor');
}]);