/**
 * Created by AlexXie on 2015/8/26.
 */
'use strict';
angular.module('app')
    .config(['$httpProvider', function ($httpProvider) {

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
    }])
    .config(function ($translateProvider) {
        $translateProvider.useLocalStorage();
        $translateProvider.useSanitizeValueStrategy(null);
        $translateProvider.useStaticFilesLoader({
            files: [{
                prefix: './i18n/locale-',
                suffix: '.json'
            }]
        });
        $translateProvider.registerAvailableLanguageKeys(['en', 'zh'], {
            'en_US': 'en',
            'en_UK': 'en',
            'zh_CN': 'zh'
        });
        $translateProvider.determinePreferredLanguage();
        $translateProvider.fallbackLanguage('zh');
    })
    .config(function (blockUIConfig) {
        blockUIConfig.message = '正在努力加载中...';
        blockUIConfig.autoInjectBodyBlock = false;
        blockUIConfig.blockBrowserNavigation = true;
        blockUIConfig.template = '<div class=\"block-ui-overlay\"></div><div class=\"block-ui-message-container\" aria-live=\"assertive\" aria-atomic=\"true\">' +
            '<div class=\"block-ui-message\" ng-class=\"$_blockUiMessageClass\">' +
            '<i class="fa fa-spinner fa-pulse"></i> {{ state.message }}</div></div>';

        blockUIConfig.requestFilter = function (config) {
            var message;
            switch (config.method) {
                case 'GET':
                    message = '正在获取数据 ......';
                    break;
                default:
                    message = '正在提交数据 ......';
                    break;
            }
            return message;
        };
    });