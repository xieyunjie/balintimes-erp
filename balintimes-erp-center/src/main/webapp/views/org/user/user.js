/**
 * kang.wu 2015-8
 */
"use strict"
define([ 'angularAMD', 'balintimesConstant', 'ui-bootstrap', 'angular-messages', 'angular-treetable' ], function(angularAMD, balintimesConstant) {

	var app = angular.module("userModule", [ 'ui.router', 'ui.bootstrap', 'ngMessages', 'ngTreetable' ]);

	var mainState = {
		name : 'org/user',
		url : '/org/user',
		templateUrl : balintimesConstant.rootpath + '/views/org/user/list.html',
		controller : 'userListController'
	};

	var editState = {
		name : 'org/user/edit',
		url : '/org/user/edit/:uid/:parentuid/:parentname/:postuid',
		templateUrl : balintimesConstant.rootpath + '/views/org/user/edit.html',
		controller : 'userEditController',
		resolve : {
			userData : function(AjaxRequest, $stateParams) {
				if ($stateParams.uid == "0") {
					return {
						data : {
							uid : 0,
							parentuid : $stateParams.parentuid,
							parentname : $stateParams.parentname
						}
					}
				} else {
					var params = {};
					params = {
						uid : $stateParams.uid,
						postuid : $stateParams.postuid
					}
					return AjaxRequest.Post("/user/getoneuser/", params);
				}
			},
			userParentData : function(AjaxRequest, $stateParams) {				
				return AjaxRequest.Get("/user/getoneuserparent/" + $stateParams.parentuid);
			}
		}
	};

	var editByPostState = {
		name : 'org/post/editbypost',
		url : '/org/post/editbypost/:uid/:parentuid',
		templateUrl : balintimesConstant.rootpath + '/views/org/post/editbypost.html',
		controller : 'PostEditGroupController',
		resolve : {

		}
	};

	app.config([ '$stateProvider', '$urlRouterProvider', '$httpProvider', function($stateProvider, $urlRouterProvider, $httpProvider) {
		$httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
		$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
		$stateProvider.state(mainState).state(editState).state(editByPostState);
	} ]);

	app.factory("userTypeData", function(AjaxRequest) {
		return AjaxRequest.Get("/usertype/list");
	});

	app.factory("postData", function(AjaxRequest) {
		return AjaxRequest.Get("/post/tree");
	});

	app.factory("orgData", function(AjaxRequest) {
		return AjaxRequest.Get("/organization/tree");
	});

	app.factory("postTreeServices", function(AjaxRequest) {
		return {
			getCheckedChildren : function(root, ary) {
				for (var k = 0; k < root.children.length; k++) {
					var c = root.children[k];
					var d = {
						uid : "",
						name : ""
					};
					
					if (c.checked) {
						d.uid = c.uid;
						d.name = c.name;
						ary.push(d);
					}
					this.getCheckedChildren(c, ary);
				}
			}
		}
	});

	app.controller("userListController", function($scope, $state, $location, AjaxRequest, DlgMsg, AlertMsg, ngTreetableParams, userTypeData, postData, orgData, $modal, NgUtil) {

		var treeData = [];
		$scope.search_username = "";
		$scope.searchParams = {};

		var post2 = [];
		var org = [];
		postData.then(function(rs) {
			$scope.post2 = rs.data;
		});
		orgData.then(function(rs) {
			$scope.org = rs.data;
		});

		$scope.queryUserTree = function() {
			return AjaxRequest.Post("/user/querytree", $scope.searchParams).then(function(rs) {
				treeData = rs.data;
				$scope.expanded_params.refresh();
			});
		}

		$scope.inituserTree = function(name) {
			userTypeData.then(function(res) {
				$scope.userTypes = res.data;
			})

			var param = {};
			if (name != "" && name != null) {
				param = {
					username : name

				}
			}
			return AjaxRequest.Get("/user/tree", param).then(function(rs) {
				treeData = rs.data;
				if (treeData.length > 0) {
					$scope.expanded_params.refresh();
				} else {
					DlgMsg.alert("系统提示", "没有查找的员工信息！");
				}

			});
		};
		$scope.Deleteuser = function(users) {
			if (users.length > 1) {
				delConfirm("请选择要删除的员工", users).result.then(function(result) {
					for (var i = 0; i < result.length; i++) {
						if (result[i].checked != undefined && result[i].checked != null && result[i].checked == true) {

							AjaxRequest.Post("/user/delete", {
								UID : result[i].uid
							}).then(function() {
								$scope.inituserTree();
							})
						}
					}

				})
			} else {
				DlgMsg.confirm("系统提示", "注意！！是否确认删除此员工？").result.then(function(btn) {
					if (btn == "ok") {
						AjaxRequest.Post("/user/delete", {
							UID : uid
						}).then(function() {
							$scope.inituserTree();
						})
					}
				})
			}

		}

		var delConfirm = function(title, content, size) {
			var s = '';
			if (size)
				s = size;
			return $modal.open({
				animation : true,
				size : s,
				templateUrl : "delUserTpl",
				controller : function($scope, $modalInstance, viewContent) {
					$scope.viewContent = viewContent;
					$scope.btnClick = function(btn) {
						if (btn == "ok") {
							$modalInstance.close($scope.viewContent.content);
						} else {
							$modalInstance.close({});
						}

					};
					$scope.cancel = function() {
						$modalInstance.dismiss('cancel');
					}
				},
				resolve : {
					viewContent : function() {
						return {
							title : title,
							content : content
						};
					}
				}
			});
		}

		$scope.expanded_params = new ngTreetableParams({
			getNodes : function(parent) {
				return parent ? parent.children : treeData;
			},
			getTemplate : function(node) {
				return 'tree_node';
			},
			options : {
				initialState : 'expanded'
			}
		});

		$scope.SelectTreePost2 = function(node) {
			$scope.searchParams.postuid = node.uid;
			$scope.searchParams.postname = node.name;
			$scope.postDropDown = false;
		};

		$scope.SelectTreeOrg = function(node) {
			$scope.searchParams.organizationuid = node.uid;
			$scope.searchParams.orgnizationname = node.name;
			$scope.orgDropDown = false;
		}

		$scope.resetForm = function() {
			$scope.searchParams = NgUtil.initPageParams();
			// return AjaxRequest.Post("/user/querytree",
			// $scope.searchParams).then(function(rs) {
			// treeData = rs.data;
			// $scope.expanded_params.refresh();
			// });
		};
		

		
		//-----------------------------------------------------------			
		// 弹出用户组树窗口
		$scope.SelectUserGroupTreeModal = function(useruid) {		
			var userGroupTreeData = [];
			var params = {
					name : "",
					useruid : useruid
				};
				var url = "/usergroup/getusergrouproletype";
				AjaxRequest.Post(url, params).then(function(rs) {
					if (rs.success == 'true') {						
						userGroupTreeData=rs.data;
						userGroupFun(userGroupTreeData,useruid).result.then(function(ary) {							
							var url = "/usergroup/saveusergroupdetail";
							var params = {
								useruid : useruid,
								json : JSON.stringify(ary)
							};

							AjaxRequest.Post(url, params).then(function(rs) {
								if (rs.success == 'true') {
									
								}
							});
						});
					}
				});			
			
		};
		
		var userGroupFun = function(userGroupData) {				
			return $modal.open({
				animation : true,
				templateUrl : "editByUserTpl",
				controller : function($scope, AjaxRequest, $modalInstance, userGroupData) {							
					$scope.userGroupTreeData = userGroupData;
					
					$scope.closeUserGroupWin = function() {
						$modalInstance.dismiss('cancel');
					}
					$scope.saveUserGroupChoose = function() {						
						var ary = new Array();
						for (var i = 0; i < $scope.userGroupTreeData.length; i++) {
							var g = $scope.userGroupTreeData[i];
							var d = {
								groupuid : g.uid,
								roletypes : new Array()
							};

							for (var k = 0; k < g.children.length; k++) {
								var c = g.children[k];
								if (c.checked) {
									d.roletypes.push(c.id);
								}
							}
							ary.push(d);							
						}
						$modalInstance.close(ary);
					}
				},
				resolve : {
					userGroupData : function() {
						return userGroupData;
					}
				}
			});
		}
		
		
		$scope.inituserTree();

	}).controller("userEditController", function($scope, $state, $location, AjaxRequest, TreeSelectModal, DlgMsg, userData, userParentData, userTypeData, $stateParams, $modal, postTreeServices) {
		$scope.go = function(uid, name) {
			$state.go("org/post/editbypost", {
				uid : $stateParams.uid,
				parentuid : $stateParams.parentuid
			});
		}
				
		$scope.userStatus = [ {
			text : '启用',
			value : 1
		}, {
			text : '禁用',
			value : 0
		} ];

		var original = angular.copy($scope.user);
		var originalPost = angular.copy($scope.post);
		var originalUserParent = angular.copy($scope.userParent);

		$scope.user = userData.data;

		$scope.userDropDown = false;
		$scope.treeData = [];
		$scope.post = [];
		$scope.userParent = userParentData.data;

		AjaxRequest.Get("/user/tree").then(function(res) {
			$scope.treeData = res.data;
		})

		AjaxRequest.Get("/post/tree").then(function(res) {
			$scope.post = res.data;
		})

		userTypeData.then(function(res) {
			$scope.userTypes = res.data;
		})

		$scope.SaveUser = function() {
			if ($scope.user.uid == "0") {
				AjaxRequest.Post("/user/create", $scope.user).then(function(res) {
					if (res.responseMsg == "保存成功") {
						$state.go("org/user");
					} else
						DlgMsg.confirm("系统提示", res.responseMsg + "是否继续保存？").result.then(function(btn) {
							if (btn == "ok") {
								$scope.user.stillpass = true;
								AjaxRequest.Post("/user/create", $scope.user).then(function(res) {

								})
							}
						});
				})
			} else {
				AjaxRequest.Post("/user/update", $scope.user).then(function(res) {
					if (res.responseMsg == "修改成功") {
						$state.go("org/user");
					} else {
						DlgMsg.confirm("系统提示", res.responseMsg + "是否继续保存？").result.then(function(btn) {
							if (btn == "ok") {
								$scope.user.stillpass = true;
								AjaxRequest.Post("/user/update", $scope.user).then(function(res) {

								})
							}
						})
					}
				})
			}
		};
		$scope.Revert = function() {
			$scope.user = angular.copy(original);
			$scope.editForm.$setPristine();
		};

		$scope.SelectTreeuser = function(node) {
			if ($scope.userParent != null) {
				if ($scope.userParent.uid == node.uid) {
					DlgMsg.alert("系统提示", "上级员工不能与当前员工一致，请重新选择。")
					return;
				}
			}

			$scope.userParent.uid = node.uid;
			if (node.users.length > 1) {
				var tmpEmpName = "";
				for (var i = 0; i < node.users.length; i++) {
					tmpEmpName += node.users[i].employeename + ",";
				}
				$scope.userParent.employeename = tmpEmpName;
			} else if (node.users.length == 1) {
				$scope.userParent.employeename = node.users[0].employeename;
			} else if (node.users.length <= 0) {
				$scope.userParent.employeename = node.name;
			}
			$scope.userDropDown = false;
		};

		// 弹出职位树窗口
		var postTreeGroupData = [];
		if ($stateParams.uid == "0") {
			AjaxRequest.Get("/post/tree").then(function(res) {
				postTreeGroupData = res.data;
			})
		} else {
			var params = {};
			params = {
				useruid : $stateParams.uid,
				postuid : $stateParams.postuid
			};

			var url = "/post/getpostgroup";
			AjaxRequest.Post(url, params).then(function(rs) {
				if (rs.success == 'true') {
					postTreeGroupData = rs.data;
				}
			});
		}

		$scope.SelectTreePostModal = function() {
			postFun(postTreeGroupData).result.then(function(t) {
				$scope.user.postuid = t.postuid;
				$scope.user.postname = t.postname;
			});
		};

		var postFun = function(postData) {
			
			return $modal.open({
				animation : true,
				templateUrl : "postGroupTpl",
				controller : function($scope, AjaxRequest, $modalInstance, postData) {
					$scope.postTreeGroupData = postData;

					$scope.closeWin = function() {
						$modalInstance.dismiss('cancel');
					}
					$scope.saveChoose = function() {
						var postname = "";
						var postuid = "";
						var checkedChildren = new Array();
						for (var i = 0; i < $scope.postTreeGroupData.length; i++) {
							var g = $scope.postTreeGroupData[i];
							var d = {
								uid : "",
								name : ""
							};

							if (g.checked) {
								d.uid = g.uid;
								d.name = g.name;
								checkedChildren.push(d);
							}
							postTreeServices.getCheckedChildren(g, checkedChildren);

						}

						for (var j = 0; j < checkedChildren.length; j++) {
							postname = checkedChildren[j].name + "," + postname;
							postuid = checkedChildren[j].uid + "," + postuid;
						}
						$modalInstance.close({
							postname : postname,
							postuid : postuid
						});
					}

				},
				resolve : {
					postData : function() {
						return postData;
					}
				}
			});
		}

	})

	app.controller('PostEditGroupController', function($scope, $state, $stateParams, AjaxRequest, DlgMsg, AlertMsg) {
		$scope.postTreeData = [];
		$scope.loadData = function() {
			var params = {};
			params = {
				useruid : $stateParams.uid
			};

			var url = "/post/getpostgroup";
			AjaxRequest.Post(url, params).then(function(rs) {
				if (rs.success == 'true') {
					$scope.postTreeData = rs.data;

				}
			});
		};

		$scope.back = function() {

			$state.go("org/user/edit", {
				uid : $stateParams.uid,
				parentuid : $stateParams.parentuid
			});
		}

		$scope.save = function() {
			var checkedChildren = new Array();
			for (var i = 0; i < $scope.postTreeData.length; i++) {
				var g = $scope.postTreeData[i];
				var d = {
					uid : "",
					name : ""
				};

				if (g.checked) {
					d.uid = g.uid;
					d.name = g.name;
					checkedChildren.push(d);
				}
				getCheckedChildren(g, checkedChildren);
			}

			var postname = "";
			var postuid = "";

			for (var j = 0; j < checkedChildren.length; j++) {
				postname = checkedChildren[j].name + "," + postname;
				postuid = checkedChildren[j].uid + "," + postuid;
			}

			var params = {};
			params = {
				useruid : $stateParams.uid,
				postuid : postuid
			};
			var url = "/user/updateuserpost";
			AjaxRequest.Post(url, params).then(function(rs) {
				if (rs.success == 'true') {
					$state.go("org/user/edit", {
						uid : $stateParams.uid,
						parentuid : $stateParams.parentuid
					});
				}
			});

		};

		var getCheckedChildren = function(root, ary) {
			for (var k = 0; k < root.children.length; k++) {
				var c = root.children[k];
				var d = {
					uid : "",
					name : ""
				};
				if (c.checked) {
					d.uid = c.uid;
					d.name = c.name;
					ary.push(d);
				}
				getCheckedChildren(c, ary);
			}
		}

		$scope.loadData();
	});

})