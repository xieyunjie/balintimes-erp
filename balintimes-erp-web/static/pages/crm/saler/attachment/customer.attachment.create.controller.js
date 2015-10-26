'use strict';
angular.module('CRM_Attachment_Create_Module', []).controller('CRM_Attachment_Create_Controller',
    ['$scope', 'CRM_Customer_Service', 'CRM_Attachment_Service', 'AlertMsg', 'DlgMsg', '$state', "FileUploader",
        function ($scope, CRM_Customer_Service, CRM_Attachment_Service, AlertMsg, DlgMsg, $state, FileUploader) {
            var vm = $scope.vm = {
                title: "上传附件",

                customerUid: "",
                followUpUid: -1,
                isReg: false,
                ic: $state.params.ic,
                customer: {},
                customerList: [],

                files: []
            }

            $scope.removeAtt = function (item) {
                var i = item.file.name.lastIndexOf(".");
                var s = item.file.name.substring(0, i);
                for (var i = 0; i < vm.files.length; i++) {
                    var it = vm.files[i];
                    if (it.filename == s) {
                        vm.files.remove(it);
                    }
                }
                item.remove();
            }

            $scope.removeAll = function () {
                uploader.clearQueue();
                vm.files = [];
            }

            $scope.saveAtt = function () {
                if (vm.files.length == 0) {
                    AlertMsg.warn("请上传附件", "警告");
                    return;
                }
                var ary = new Array();
                for (var i = 0; i < vm.files.length; i++) {
                    var item = vm.files[i];
                    var att = {
                        followUpUid: vm.customer.followUid == undefined ? -1 : vm.customer.followUid,
                        customerUid: vm.customer.uid,
                        reg: vm.customer.reg,
                        customerName: vm.customer.name,
                        remarks: "",
                        url: item.fileFullName,
                        fileName: item.fileName
                    };
                    ary.push(att);
                }
                var p = {
                    json: JSON.stringify(ary)
                };
                CRM_Attachment_Service.createAttachmentInfo(p).then(function (res) {
                    $state.go("crm.saler.customer_list");
                });
            }

            $scope.goBack = function () {
                if (vm.ic == "1") {
                    $state.go("crm.saler.customer_list");
                } else {
                    $state.go("crm.saler.attachment_list", {
                        customeruid: vm.customerUid,
                        followupuid: vm.followUpUid,
                        isreg: vm.isReg
                    });
                }
            }

            var uploader = $scope.uploader = new FileUploader({
                url: '/crm/attachment/uploadatts'
            });

            // CALLBACKS
            uploader.onCompleteItem = function (fileItem, response, status, headers) {
                var urls = response.data;
                if (urls.length > 0) {
                    vm.files.push(urls[0]);
                }
            };

            function getCustomerList() {
                var p = {
                    name: "",
                    businesstype: null,
                    isreg: null,
                    brand: null,
                    pagesize: 100000,
                    page: 1,
                    isshowdown: false
                };

                CRM_Customer_Service.listByEmp(p).then(function (res) {
                    vm.customerList = res.data;
                })
            }

            function getCustomer() {
                vm.customerUid = $state.params.customeruid;
                vm.followUpUid = $state.params.followupuid;
                vm.isReg = eval($state.params.isreg);

                var uid = vm.customerUid;
                if (vm.isReg) {
                    uid = vm.followUpUid;
                }

                var p = {
                    uid: uid,
                    isreg: vm.isReg
                };
                CRM_Customer_Service.getCustomer(p).then(function (res) {
                    vm.customer = res.data;
                })
            }

            function init() {
                getCustomerList();
                getCustomer();
            }

            init();

        }]);