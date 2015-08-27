/**
 * Created by AlexXie on 2015/8/27.
 */


angular.module("app")
    .directive("tdMouseoverToggle", function () {
        return {
            restrict: 'A',
            scope: {},
            link: function (scope, element, attrs) {
                var tr = angular.element(element.parents('tr')[0]);
                var el = element;
                angular.element(el).children().hide();
                tr.bind('mouseover', function () {
                    angular.element(el).children().show();
                });
                tr.bind('mouseout', function () {
                    angular.element(el).children().hide();
                });
            }
        }
    });


