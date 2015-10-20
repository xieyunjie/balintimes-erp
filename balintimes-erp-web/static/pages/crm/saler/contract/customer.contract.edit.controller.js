'use strict';
angular.module('CRM_Contract_Edit_Module', []).controller('CRM_Contract_Edit_Controller',
    ['$scope', 'CRM_Customer_Service', 'CRM_Contract_Service', 'AlertMsg', 'DlgMsg', '$state', "FileUploader",
        function ($scope, CRM_Customer_Service, CRM_Contract_Service, AlertMsg, DlgMsg, $state, FileUploader) {
            Date.prototype.format = function (fmt) {
                var o = {
                    "M+": this.getMonth() + 1,                 //月份
                    "d+": this.getDate(),                    //日
                    "h+": this.getHours(),                   //小时
                    "m+": this.getMinutes(),                 //分
                    "s+": this.getSeconds(),                 //秒
                    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                    "S": this.getMilliseconds()             //毫秒
                };
                if (/(y+)/.test(fmt))
                    fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
                for (var k in o)
                    if (new RegExp("(" + k + ")").test(fmt))
                        fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                return fmt;
            }

            var vm = $scope.vm = {
                isReadOnly: eval($state.params.isreadonly),
                title: eval($state.params.isreadonly) ? "浏览联系人信息" : "联系人编辑",

                customer: {},
                customerList: [],
                contract: {},
                sexs: [
                    {
                        value: true,
                        name: "男"
                    }, {
                        value: false,
                        name: "女"
                    }
                ],

                isUpdate: $state.params.uid == -1 ? false : true
            }

            $scope.currImgUrl = "";
            $scope.tempImg = null;

            $scope.deleteContract = function (uid, reg) {
                var p = {
                    uid: uid,
                    isreg: reg
                };
                CRM_Contract_Service.deleteContract(p).then(function (res) {
                    getContract();
                });
            }

            $scope.saveContract = function () {
                if (vm.contract.birthdayByDateTime != undefined && vm.contract.birthdayByDateTime != null && vm.contract.birthdayByDateTime != "") {
                    vm.contract.birthday = vm.contract.birthdayByDateTime.toLocaleDateString() + " 00:00:00 ";
                }
                if ($state.params.uid == "-1") {
                    vm.contract.reg = vm.customer.reg;
                    vm.contract.customerUid = vm.customer.uid;
                    vm.contract.followUpUid = vm.customer.followUid;
                    vm.contract.cardUrl = $scope.tempImg.fileFullName;

                    vm.contract.birthdayByDateTime = null;
                    CRM_Contract_Service.createContract(vm.contract).then(function (res) {
                        $state.go("crm.saler.contract_list", {
                            customeruid: vm.customer.uid,
                            followupuid: vm.customer.followUid,
                            isreg: vm.customer.reg
                        });
                    });
                } else {
                    if ($scope.tempImg != null) {
                        vm.contract.cardUrl = $scope.tempImg.fileFullName;
                    } else {
                        vm.contract.cardUrl = "";
                    }
                    vm.contract.birthdayByDateTime = null;
                    CRM_Contract_Service.updateContract(vm.contract).then(function (res) {
                        $state.go("crm.saler.contract_list", {
                            customeruid: vm.customer.uid,
                            followupuid: vm.customer.followUid,
                            isreg: vm.customer.reg
                        });
                    });
                }
            }

            var uploader = $scope.uploader = new FileUploader({
                url: '/crm/contract/uploadcard'
            });

            uploader.filters.push({
                name: 'extensionFilter',
                fn: function (item, options) {
                    var filename = item.name;
                    var extension = filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();
                    if (extension == "jpg" || extension == "jpge" || extension == "png" ||
                        extension == "gif")
                        return true;
                    else {
                        return false;
                    }
                }
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

            uploader.onAfterAddingFile = function (fileItem) {
                var l = uploader.queue.length;
                if (l > 1) {
                    uploader.queue[0].remove();
                }
            };


            function getContract() {
                if ($state.params.uid == -1) {
                    var uid = $state.params.customeruid;
                    var reg = eval($state.params.isreg);
                    if (reg) {
                        uid = $state.params.followupuid;
                    }
                    getCustomer(uid, reg);
                    return;
                }
                var p = {
                    uid: $state.params.uid,
                    isreg: $state.params.isreg
                };

                CRM_Contract_Service.getContract(p).then(function (res) {
                        vm.contract = res.data;
                        if (vm.contract.birthday != null && vm.contract.birthday != "") {
                            vm.contract.birthdayByDateTime = new Date(vm.contract.birthday);
                        }
                        var uid = vm.contract.customerUid;
                        if (vm.contract.reg == true) {
                            uid = vm.contract.followUpUid;
                        }
                        $scope.currImgUrl = vm.contract.cardUrl;
                        getCustomer(uid, vm.contract.reg);
                    }
                )
            }

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

            function getCustomer(uid, isreg) {
                var p = {
                    uid: uid,
                    isreg: isreg
                };

                CRM_Customer_Service.getCustomer(p).then(function (res) {
                    vm.customer = res.data;
                });
            }

            function init() {
                getCustomerList();
                getContract();
            }

            init();
        }])
;