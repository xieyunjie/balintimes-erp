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
            }, {
                name: 'CRM_Contract_Service',
                files: ["/pages/crm/saler/contract/customer.contract.service.js"]
            }, {
                name: "angular-file-upload",
                files: ["/bower_components/angular-file-upload/dist/angular-file-upload.min.js"]
            }, {
                name: "CRM_Attachment_Service",
                files: ["/pages/crm/saler/attachment/customer.attachment.service.js"]
            }, {
                name: "attachment_directive",
                files: [
                    "/pages/crm/saler/customer/customer.client.service.js",
                    "/bower_components/angular-file-upload/dist/angular-file-upload.min.js",
                    "/pages/crm/saler/attachment/customer.attachment.service.js",
                    "/pages/crm/directives/customer.attachment.directive.js"
                ]
            }, {
                name: "customerdetail_directive",
                files: [
                    "/pages/crm/saler/customer/customer.client.service.js",
                    "/pages/crm/directives/customer.detail.edit.directive.js",
                    "/pages/crm/base/services/customer.basedata.service.js",
                    "/pages/crm/base/services/customer.crmbase.service.js"
                ]
            }, {
                name: "remarks_directive",
                files: [
                    "/pages/crm/saler/customer/customer.client.service.js",
                    "/pages/crm/saler/remarks/customer.remarks.service.js",
                    "/pages/crm/directives/customer.remarks.directive.js",
                    "/pages/crm/base/services/customer.basedata.service.js",
                    "/pages/crm/base/services/customer.crmbase.service.js"
                ]
            }, {
                name: "contracts_directive",
                files: [
                    "/pages/crm/saler/customer/customer.client.service.js",
                    "/pages/crm/saler/contract/customer.contract.service.js",
                    "/pages/crm/directives/customer.contracts.directive.js",
                    "/pages/crm/base/services/customer.basedata.service.js",
                    "/pages/crm/base/services/customer.crmbase.service.js"
                ]
            }]
            //}, {
            //    name: "angular-ui-router-tabs",
            //    files: ["/bower_components/angular-ui-router-tabs/src/ui-router-tabs.js"]
            //}];

            var modules = plugins.concat(bmmsServices, crmServices);

            $ocLazyLoadProvider.config({
                debug: false,
                events: true,
                modules: modules
            });
        }]);