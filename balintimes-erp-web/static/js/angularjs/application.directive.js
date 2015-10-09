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
    })
    .directive("hasPermissions", ["UserStgService", function (UserStgService) {


        var OR_OPERATOR = " | ", AND_OPERATOR = " & ", NOT_OPERATOR = "!";

        var isPermitted = function (permission) {
            var userPermissions = getUserPermissions(permission);
            if (_.indexOf(userPermissions, permission) >= 0) return true;
            return false;
        };

        var isPermittedWithNotOperator = function (permission) {

            if (_.startsWith(permission, NOT_OPERATOR)) {
                var permission = permission.substr(1, permission.length - 1);
                return !isPermitted(permission);
            }
            else {
                return isPermitted(permission);
            }
        };

        var getUserPermissions = function (permission) {
            var root = permission.substr(0, permission.indexOf(":")),
                userPermissions = [];
            angular.forEach(UserStgService.getApps(), function (a) {
                if (a.code == root) {
                    userPermissions = a.permissions;
                    return false;
                }
            });
            return userPermissions;
        };

        return {
            restrict: 'A',
            scope: {},
            link: function (scope, element, attrs) {
                var haspermissions = attrs.hasPermissions;
                var permissions = [];

                if (haspermissions.indexOf(OR_OPERATOR) >= 0) {
                    permissions = haspermissions.split(OR_OPERATOR);
                    var bln_show = false;
                    angular.forEach(permissions, function (p) {
                        if (isPermittedWithNotOperator(p)) {
                            bln_show = true;
                            return false;
                        }
                    });
                    if (bln_show == false) element.css({display: "none"});

                }
                else if (haspermissions.indexOf(AND_OPERATOR) >= 0) {
                    permissions = haspermissions.split(AND_OPERATOR);
                    angular.forEach(permissions, function (p) {
                        if (!isPermittedWithNotOperator(p)) {
                            element.css({display: "none"});
                            return false;
                        }
                    });
                }
                else {
                    if (isPermittedWithNotOperator(haspermissions) == false) element.css({display: "none"});
                }

            }
        }

    }]);


