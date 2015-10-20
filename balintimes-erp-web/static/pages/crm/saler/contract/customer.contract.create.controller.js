'use strict';
angular.module('CRM_Contract_Create_Module', []).controller('CRM_Contract_Create_Controller',
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
                title: '新建联系人',
                customer: {},
                contract: {},

                isReg: false,
                followUid: "",
                customerUid: "",

                sexs: [
                    {
                        value: true,
                        name: "男"
                    }, {
                        value: false,
                        name: "女"
                    }
                ]

            }

            $scope.currImgUrl = "";
            //var headUrl = "";
            $scope.tempImg = {};

            $scope.saveContract = function () {
                vm.contract.reg = vm.isReg;
                vm.contract.customerUid = vm.customerUid;
                vm.contract.followUpUid = vm.followUid;
                if (vm.contract.birthdayByDateTime != undefined && vm.contract.birthdayByDateTime != "") {
                    vm.contract.birthday = vm.contract.birthdayByDateTime.format("yyyy-MM-dd hh:mm:ss");
                }
                vm.contract.cardUrl = $scope.tempImg.fileFullName;

                CRM_Contract_Service.createContract(vm.contract).then(function (res) {
                    $state.go("crm.saler.customer_list");
                });
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

            function getCustomer() {
                vm.customerUid = $state.params.customeruid;
                vm.followUid = $state.params.followupuid;
                vm.isReg = eval($state.params.isreg);

                var params = {
                    uid: vm.isReg ? vm.followUid : vm.customerUid,
                    isreg: vm.isReg
                };
                CRM_Customer_Service.getCustomer(params).then(function (res) {
                    vm.customer = res.data;
                })
            }

            function init() {
                getCustomer();
            }

            init();

        }]);