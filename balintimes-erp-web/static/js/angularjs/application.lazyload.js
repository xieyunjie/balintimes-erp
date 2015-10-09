/**
 * Created by AlexXie on 2015/8/26.
 */
'use strict';

angular.module('app')
    .constant('JQ_CONFIG', {
        "easyPieChart": ['/bower_components/bower-jquery-easyPieChart/dist/jquery.easypiechart.min.js']
    })
    .config(['$ocLazyLoadProvider',
        function ($ocLazyLoadProvider) {

            var plugins = [
                //    {
                //    name: "ui-select",
                //    files: ['/bower_components/ui-select/dist/select.min.js', '/bower_components/ui-select/dist/select.min.css']
                //}
            ];

            var bmmsServices = [{
                name: 'BMMS_Line_Service',
                files: ["/pages/bmms/base/line/line.client.service.js"]
            }];

            var crmServices = [{
                name: 'CRM_Customer_Service',
                files: ["/pages/crm/saler/customer/customer.client.service.js"]
            }, {
                name: 'CRM_BaseData_Service',
                files: ["/pages/crm/base/services/customer.basedata.service.js"]
            }, {
                name: 'CRM_base_Service',
                files: ["/pages/crm/base/services/customer.crmbase.service.js"]
            }, {
                name: 'CRM_Follow_Remarks_Service',
                files: ["/pages/crm/saler/remarks/customer.remarks.service.js"]
            }];

            var modules = plugins.concat(bmmsServices, crmServices);

            $ocLazyLoadProvider.config({
                debug: false,
                events: true,
                modules: modules
            });
        }]);