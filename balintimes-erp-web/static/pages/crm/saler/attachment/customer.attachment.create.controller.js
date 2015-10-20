'use strict';
angular.module('CRM_Attachment_Create_Module', []).controller('CRM_Attachment_Create_Controller',
    ['$scope', 'CRM_Customer_Service', 'CRM_Attachment_Service', 'AlertMsg', 'DlgMsg', '$state', "FileUploader",
        function ($scope, CRM_Customer_Service, CRM_Attachment_Service, AlertMsg, DlgMsg, $state, FileUploader) {
            var vm = $scope.vm = {
                title: "上传附件",

                customerUid: "",
                followUpUid: "",
                isReg: false,

                attachmentList: [],

                customer: {},
                customerList: []

            }

            var uploader = $scope.uploader = new FileUploader({
                url: '/crm/attachment/uploadatts'
            });

            // CALLBACKS
            uploader.onSuccessItem = function (fileItem, response, status, headers) {
                //console.info('onSuccessItem', fileItem, response, status, headers);
                var urls = response.data;
                if (urls.length > 0) {
                    var imgUrl = urls[0];
                    //headUrl = imgUrl.fileFullName;
                    $scope.tempImg = imgUrl;
                    $scope.currImgUrl = imgUrl.baseUrl + imgUrl.fileFullName;
                }

            };

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
                getCustomer();
            }

            init();

        }]);