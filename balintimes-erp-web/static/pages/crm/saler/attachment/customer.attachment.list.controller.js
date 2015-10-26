'use strict';
angular.module('CRM_Attachment_List_Module', []).controller('CRM_Attachment_List_Controller',
    ['$scope', 'CRM_Attachment_Service', 'AlertMsg', 'DlgMsg', '$state',
        function ($scope, CRM_Attachment_Service, AlertMsg, DlgMsg, $state) {
            var vm = $scope.vm = {
                title: "附件列表",

                customerUid: "",
                followUpUid: "",
                isReg: false,

                attachmentList: []
            }

            $scope.deleteAtt = function (uid, isReg) {
                var p = {
                    uid: uid,
                    isreg: isReg
                };
                CRM_Attachment_Service.deleteAttachment(p).then(function (res) {
                    getAttachmentList();
                })
            }

            function getAttachmentList() {
                vm.customerUid = $state.params.customeruid;
                vm.followUpUid = $state.params.followupuid;
                vm.isReg = eval($state.params.isreg);
                var p = {
                    objuid: vm.isReg ? vm.followUpUid : vm.customerUid,
                    isreg: vm.isReg
                };

                CRM_Attachment_Service.getAttachmentList(p).then(function (res) {
                    vm.attachmentList = res.data;
                })
            }

            function init() {
                getAttachmentList();
            }

            init();
        }]);