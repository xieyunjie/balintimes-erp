/**
 * Created by AlexXie on 2015/7/9.
 */
"use strict"
define([ 'angularAMD', 'balintimesConstant', 'ui-bootstrap',
		'angular-messages', 'angular-file-upload' ], function(angularAMD,
		balintimesConstant) {
	var app = angular.module("userModule", [ 'ui.router', 'ui.bootstrap',
			'ngMessages', 'angular-file-upload' ]);

	var mainState = {
		name : 'profile',
		url : '/profile',
		templateUrl : balintimesConstant.rootpath + '/views/profile.html',
		controller : 'ProfileController',
	};
	app.config([ '$stateProvider', '$urlRouterProvider',
			function($stateProvider, $urlRouterProvider) {
				$stateProvider.state(mainState);
			} ]);

	app.controller("ProfileController", function($rootScope, $scope, $state,
			AjaxRequest, FileUploader) {

		$scope.activeTabIndex = 1;
		$scope.activeTab = function(index) {
			$scope.activeTabIndex = index;
		};

		/* 个人资料 */
		$scope.email = "";
		$scope.SaveUserInfo = function() {
			console.info($scope.userInfoForm);
			// AjaxRequest.Post("/user/updatepassword", {oldpassword:
			// $scope.oldpassword, newpassword: $scope.newpassword});
		}

		/* 个人密码修改功能 */
		$scope.userpassword = {
			oldpassword : "",
			newpassword : ""
		};
		$scope.confirmpassword = "";
		$scope.SavePassword = function() {
			$scope.passwordForm.$setPristine();
			return;
			AjaxRequest.Post("/user/updatepassword", $scope.userpassword).then(
					function(res) {
						$rootScope.init();
						$scope.confirmpassword = "";
						$scope.userpassword = {
							oldpassword : "",
							newpassword : ""
						};

					})
		};

		$scope.currImgUrl = "";
		var headUrl="";
		var uploader = $scope.uploader = new FileUploader({
			url : balintimesConstant.rootpath + '/user/userheadupload'
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
		uploader.onSuccessItem = function(fileItem, response, status, headers) {
			//console.info('onSuccessItem', fileItem, response, status, headers);
			var urls = response.data;
			if (urls.length > 0) {
				var imgUrl = urls[0];
				headUrl=imgUrl;
				$scope.currImgUrl = balintimesConstant.rootpath
						+ "/tempupload/" + imgUrl;
			}

		};
		
        uploader.onAfterAddingFile = function(fileItem) {
            var l=uploader.queue.length;
            if(l>1){
            	uploader.queue[0].remove();
            }
        };

		$scope.removeImg=function(item){
			item.remove();
			$scope.currImgUrl = "";
			headUrl="";
		};
		
		$scope.saveHead=function(){
			AjaxRequest.Post("/user/updatehead", {headurl:headUrl}).then(
					function(res) {
						
					})
		}

	})

	return {
		mainState : mainState,
		module : app
	};
})