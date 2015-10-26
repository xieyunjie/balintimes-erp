angular.module("app").directive("attachmentlist",
    function () {
        return {
            restrict: 'EA',
            templateUrl: '/pages/crm/directives/tpls/customer.attachment.view.html',
            transclude: true,
            replace: true,
            scope: {
                params: '=data',
            },

            controller: ['$scope', 'CRM_Customer_Service', 'CRM_Attachment_Service', 'AlertMsg', 'DlgMsg', '$state', 'FileUploader',
                function ($scope, CRM_Customer_Service, CRM_Attachment_Service, AlertMsg, DlgMsg, $state, FileUploader) {
                    var vm = $scope.vm = {
                        title: "附件列表",
                        customerUid: $scope.params.customerUid,
                        followUpUid: $scope.params.followUpUid,
                        isReg: $scope.params.isReg,
                        isReadOnly: $scope.params.isReadOnly,
                        attachmentList: [],

                        customer: {},

                        modalShown: false
                    }

                    $scope.logClose = function () {
                        vm.modalShown = !vm.modalShown;
                    }

                    $scope.openWin = function () {
                        getCustomer();
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
                        var p = {
                            objuid: $scope.params.isReg ? $scope.params.followUpUid : $scope.params.customerUid,
                            isreg: $scope.params.isReg
                        };

                        CRM_Attachment_Service.getAttachmentList(p).then(function (res) {
                            vm.attachmentList = res.data;
                        })
                    }

                    function getCustomer() {
                        var p = {
                            uid: $scope.params.isReg ? $scope.params.followUpUid : $scope.params.customerUid,
                            isreg: $scope.params.isReg
                        };
                        CRM_Customer_Service.getCustomer(p).then(function (res) {
                            vm.customer = res.data;
                        })
                    }

                    function init() {
                        getAttachmentList();
                    }

                    init();
                }
            ]
        }
    });