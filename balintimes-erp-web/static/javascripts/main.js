/**
 * Created by AlexXie on 2015/8/26.
 */
'use strict';

angular.module('app').controller('AppController', ['$scope', '$q', '$localStorage', '$window', 'AjaxRequest',
    function ($scope, $q, $localStorage, $window, AjaxRequest) {
        // add 'ie' classes to html
        var isIE = !!navigator.userAgent.match(/MSIE/i);
        isIE && angular.element($window.document.body).addClass('ie');
        isSmartDevice($window) && angular.element($window.document.body).addClass('smart');

        // config
        var app = $scope.app = {
            name: 'BalintimesERP',
            version: '0.0.1',
            // for chart colors
            color: {
                primary: '#7266ba',
                info: '#23b7e5',
                success: '#27c24c',
                warning: '#fad733',
                danger: '#f05050',
                light: '#e8eff0',
                dark: '#3a3f51',
                black: '#1c2b36'
            },
            settings: {
                themeID: 1,
                navbarHeaderColor: 'bg-black',
                navbarCollapseColor: 'bg-white-only',
                asideColor: 'bg-black',
                headerFixed: true,
                asideFixed: false,
                asideFolded: false,
                asideDock: false,
                container: false
            },
            webUser: {},
            menus: {}
        };

        // save settings to local storage
        if (angular.isDefined($localStorage.settings)) {
            $scope.app.settings = $localStorage.settings;
        } else {
            $localStorage.settings = $scope.app.settings;
        }
        $scope.$watch('app.settings', function () {
            if ($scope.app.settings.asideDock && $scope.app.settings.asideFixed) {
                // aside dock and fixed must set the header fixed.
                $scope.app.settings.headerFixed = true;
            }
            // save to local storage
            $localStorage.settings = $scope.app.settings;
        }, true);

        /*            // angular translate
         $scope.lang = {isopen: false};
         $scope.langs = {en: 'English', de_DE: 'German', it_IT: 'Italian'};
         $scope.selectLang = $scope.langs[$translate.proposedLanguage()] || "English";
         $scope.setLang = function (langKey, $event) {
         // set the current lang
         $scope.selectLang = $scope.langs[langKey];
         // You can change the language during runtime
         $translate.use(langKey);
         $scope.lang.isopen = !$scope.lang.isopen;
         };*/

        function isSmartDevice($window) {
            // Adapted from http://www.detectmobilebrowsers.com
            var ua = $window['navigator']['userAgent'] || $window['navigator']['vendor'] || $window['opera'];
            // Checks for iOs, Android, Blackberry, Opera Mini, and Windows mobile devices
            return (/iPhone|iPod|iPad|Silk|Android|BlackBerry|Opera Mini|IEMobile/).test(ua);
        };

        $scope.initAppCtrl = function () {
            var webuserPromise = AjaxRequest.get("/home/inituser"),
                menusPromise = AjaxRequest.get("/home/initmenus");
            $q.all({user: webuserPromise, menus: menusPromise}).then(function (results) {
                app.webUser = results.user;
                app.menus = results.menus;
            });

        };

    }]);