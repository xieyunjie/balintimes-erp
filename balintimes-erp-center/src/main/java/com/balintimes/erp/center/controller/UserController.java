package com.balintimes.erp.center.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.balintimes.erp.center.base.BaseController;
import com.balintimes.erp.center.model.Post;
import com.balintimes.erp.center.model.PostTree;
import com.balintimes.erp.center.model.User;
import com.balintimes.erp.center.service.PostService;
import com.balintimes.erp.center.service.UserService;
import com.balintimes.erp.center.util.JsonUtil;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {
	@Resource
	private UserService userService;
	@Resource
	private PostService postService;

	@Value("#{settingProperties['tempavatarsurl']}")
	private String tempavatarsurl;
	@Value("#{settingProperties['avatarsurl']}")
	private String avatarsurl;

	@RequestMapping(value = "getuserbyempname", method = RequestMethod.POST)
	@ResponseBody
	public String GetUserListByEmpName(String empname) {
		if (empname != null && !empname.equals("")) {
			List<User> list = this.userService.GetUserByEmpName(empname);
			return JsonUtil.ResponseSuccessfulMessage(list);
		} else {
			return JsonUtil.ResponseSuccessfulMessage(new ArrayList<User>());
		}
	}

	@RequestMapping("list")
	@ResponseBody
	public String UserList() throws InterruptedException {
		// Thread.sleep(1 * 1000);

		// System.out.println(LocalMsg("name"));

		List<User> list = new ArrayList<User>();

		list = this.userService.GetUserList();

		return JsonUtil.ResponseSuccessfulMessage(list);
	}

	@RequestMapping("listbypage")
	@ResponseBody
	public String UserListforPage(String username, String employeename, String usertype, String isenable, int page, int pageSize) {
		if (isenable != null) {
			isenable = isenable.equals("-1") ? "" : isenable;
		}

		com.balintimes.erp.center.tuples.TuplePage<List<User>, Integer> result = this.userService.GetUserList(username, employeename, usertype, isenable, page, pageSize);

		return JsonUtil.ResponseSuccessfulMessage(result.objectList, result.objectTotalCount);
	}

	@RequestMapping("getuser/{uid}")
	@ResponseBody
	public String GetUser(@PathVariable String uid) {
		User user;
		if (uid.equals("0")) {
			user = new User();
		} else {
			user = this.userService.getUser(uid);
		}

		return JsonUtil.ResponseSuccessfulMessage(user);
	}

	@RequestMapping("getuser")
	@ResponseBody
	public String GetSomnUser() {
		User user;
		user = new User();

		return JsonUtil.ResponseSuccessfulMessage(user);
	}

	@RequestMapping(value = "create")
	@ResponseBody
	public String createUser(User user) {
		if (user.getStillpass() == false) {
			// 相同用户名
			if (this.userService.ExistsUserName(user.getUsername()) == true) {
				return JsonUtil.ResponseFailureMessage("已经存在相同用户名!");
			}
		}
		
		user.setUid(UUID.randomUUID().toString());
		user.setCreateby(webUsrUtil.CurrentUser().getEmployeeName());
		user.setCreatetime(new Date());

		this.userService.create(user);

		return JsonUtil.ResponseSuccessfulMessage("保存成功");
	}

	@RequestMapping(value = "update")
	@ResponseBody
	public String updateUser(User user) {
		User userFromDB = this.userService.ExistsUserUid(user.getUid());

		if (user.getStillpass() == false) {
			if (!userFromDB.getUsername().equalsIgnoreCase(user.getUsername())) {
				// 相同用户名
				if (this.userService.ExistsUserName(user.getUsername(), user.getUid()) == true) {
					return JsonUtil.ResponseFailureMessage("已经存在相同用户名!");
				}
			}

			if (!userFromDB.getEmployeename().equalsIgnoreCase(user.getEmployeename())) {
				// 相同员工名
				if (this.userService.ExistsEmployeeName(user.getEmployeename()) == true) {
					return JsonUtil.ResponseFailureMessage("已经存在相同员工名!");
				}
			}
		}

		user.setEditby(webUsrUtil.CurrentUser().getEmployeeName());
		user.setEdittime(new Date());
		this.userService.updateUser(user);
		this.UpdateUserPost(user.getUid(), user.getPostuid());
		return JsonUtil.ResponseSuccessfulMessage("修改成功");
	}

	@RequestMapping(value = "resetpassword")
	@ResponseBody
	public String ResetPassword(String UID) {
		this.userService.UpdatePassword(UID, "1");

		return JsonUtil.ResponseSuccessfulMessage("重置成功");
	}

	@RequestMapping(value = "updatepassword", method = RequestMethod.POST)
	@ResponseBody
	public String UpdateUserPassword(String oldpassword, String newpassword) {

		try {
			String username = this.userService.UpdatePassword(webUsrUtil.CurrentUser().getUid(), oldpassword, newpassword);
			if (username.equals("")) {
				return JsonUtil.ResponseFailureMessage("修改密码错误！");
			}
			com.balintimes.erp.center.shiro.Utils.logout();
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username, newpassword);
			subject.login(token);

			return JsonUtil.ResponseSuccessfulMessage("修改密码成功！");

		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtil.ResponseFailureMessage("修改密码异常!!" + e.getMessage());
		}

	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteUser(String UID) {
		System.out.println("*******" + UID);
		this.userService.deleteUser(UID, webUsrUtil.CurrentUser().getEmployeeName());

		return JsonUtil.ResponseSuccessfulMessage("删除成功");
	}

	@RequestMapping("getnulluser")
	@ResponseBody
	public String GetNullUser() {
		return JsonUtil.ResponseSuccessfulMessage("获取了一个 null 的 user");
	}

	@RequestMapping("getresourcepermission")
	@ResponseBody
	public String GetResourcePermission(String state) {

		try {
			Thread.sleep(2 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (state.equals("auth/user")) {
			return JsonUtil.ResponsePermissionMessage(false, "无权限");
		} else {
			return JsonUtil.ResponsePermissionMessage(true, "");
		}
	}

	@RequestMapping("tree")
	@ResponseBody
	public String GetUserTreeList() {
		List<User> listUsers = new ArrayList<User>();
		listUsers = userService.GetUserTreeList();

		List<Post> posts = new ArrayList<Post>();
		posts = postService.GetPostList();

		List<Post> postListWithUsers = new ArrayList<Post>();
		postListWithUsers = FillPostListWithUsers(posts, listUsers);

		List<PostTree> trees = InitPostTree(postListWithUsers);

		return JsonUtil.ResponseSuccessfulMessage(trees);
	}

	// @RequestMapping(value = "tree/{employeeName}", method =
	// RequestMethod.GET)
	// @ResponseBody
	// public String GetUserTreeSet(@PathVariable String employeeName) {
	// List<User> listUsersSet = userService.GetUserTreeSet(employeeName);
	// List<UserTree> treesSet = InitUserTree(listUsersSet);
	// return JsonUtil.ResponseSuccessfulMessage(treesSet);
	// }

	@RequestMapping(value = "querytree", method = RequestMethod.POST)
	@ResponseBody
	public String GetUserTreeListByCondition(String username, String employeename, String postuid, String postname, String organizationuid, String orgnizationname) {
		if ((username == null || username.equalsIgnoreCase("")) && (employeename == null || employeename.equalsIgnoreCase("")) && (postuid == null || postuid.equalsIgnoreCase("")) && (organizationuid == null || organizationuid.equalsIgnoreCase("")))
			return this.GetUserTreeList();

		List<PostTree> trees = new ArrayList<PostTree>(1000);
		List<User> listUsers = userService.GetUserTreeListByCondition(username, employeename, postuid, organizationuid);// 符合条件的User

		List<User> allUsers = userService.GetUserTreeList();
		List<Post> posts = new ArrayList<Post>();
		List<Post> tempPosts = new ArrayList<Post>();
		boolean isFind = false;
		if (listUsers.size() > 0) {
			for (int k = 0; k < listUsers.size(); k++) {
				postuid = listUsers.get(k).getPostuid();
				if (organizationuid == null)
					organizationuid = "";

				tempPosts = postService.GetPostParent(postuid, organizationuid);
				if (k == 0) {
					posts = tempPosts;
					continue;
				}

				for (Post tmpPost : tempPosts) {
					for (Post post : posts) {
						if (tmpPost.getUid().equalsIgnoreCase(post.getUid())) {
							isFind = true;
							break;
						}
					}
					if (isFind == true) {
						isFind = false;
						continue;
					} else {
						posts.add(tmpPost);
					}
				}
			}

			List<Post> postListWithUsers = new ArrayList<Post>();
			postListWithUsers = FillPostListWithUsers(posts, allUsers);

			for (Post item : postListWithUsers) {
				List<User> restUsers = new ArrayList<User>();
				for (User user : item.getUsers()) {
					for (User listUserItem : listUsers) {
						// 职位、ID和筛选结果相同的，才添加
						if (listUserItem.getPostuid().equalsIgnoreCase(user.getPostuid()) && listUserItem.getUid().equalsIgnoreCase(user.getUid())) {
							restUsers.add(user);
						}
					}
				}
				// 上级员工不用添加
				if (restUsers.size() != 0)
					item.setUsers(restUsers);
			}

			trees = InitPostTree(postListWithUsers);
		} else {
			return JsonUtil.ResponseSuccessfulMessage("");
		}

		return JsonUtil.ResponseSuccessfulMessage(trees);
	}

	// ----------------------------------------------------------------------------------------------------
	private List<Post> FillPostListWithUsers(List<Post> postList, List<User> userList) {
		List<Post> resultPosts = new ArrayList<Post>();
		for (Post post : postList) {
			List<User> tmpUsers = new ArrayList<User>();
			for (User user : userList) {
				if (user.getPostuid().equalsIgnoreCase(post.getUid())) {
					tmpUsers.add(user);
				}
			}
			post.setName(post.getName() + "(暂无员工)");
			post.setUsers(tmpUsers);
		}
		resultPosts = postList;
		return resultPosts;
	}

	private List<PostTree> InitPostTree(List<Post> list) {
		List<PostTree> trees = new ArrayList<PostTree>();
		if (list == null) {
			return trees;
		}
		if (list.size() < 1) {
			return trees;
		}

		String rootUID = "";
		PostTree rootPost = null;
		rootUID = "00000000-0000-0000-0000-000000000001";
		for (Post Post : list) {
			if (Post.getUid().equalsIgnoreCase(rootUID)) {
				rootPost = new PostTree(Post);
				rootPost.setChildren(this.GetPostChildren(list, rootPost.getUid(), rootPost.getName()));
				trees.add(rootPost);
				break;
			}
		}

		return trees;
	}

	private List<PostTree> GetPostChildren(List<Post> list, String parentUID, String parentname) {

		List<PostTree> tree = new ArrayList<PostTree>();

		for (Post post : list) {
			if (post.getParentuid().equalsIgnoreCase(parentUID)) {

				PostTree node = new PostTree(post);
				node.setParentname(parentname);
				node.setChildren(this.GetPostChildren(list, post.getUid(), node.getName()));
				tree.add(node);
			}
		}

		return tree;
	}

	@RequestMapping(value = "getoneuser", method = RequestMethod.POST)
	@ResponseBody
	public String GetOneUser(String uid, String postuid) {
		User oneUser = new User();
		if (!uid.equalsIgnoreCase(null) && !uid.equalsIgnoreCase("")) {
			oneUser = userService.getUser(uid);
			List<Post> posts = postService.GetOneUserPosts(uid);
			String tmpPostNameString = "", tmpPostUidString = "";
			for (Post post : posts) {
				tmpPostNameString += post.getName() + ",";
				tmpPostUidString += post.getUid() + ",";
			}
			oneUser.setPostuid(tmpPostUidString);
			oneUser.setPostname(tmpPostNameString);
		} else {
			Post onePost = postService.GetOnePost(postuid);
			oneUser.setEmployeename(onePost.getName() + "(暂无员工)");
			oneUser.setUsername(onePost.getName() + "(暂无用户)");
			oneUser.setPostname(onePost.getName());
			oneUser.setPostuid(onePost.getUid());

		}

		return JsonUtil.ResponseSuccessfulMessage(oneUser);
	}

	@RequestMapping(value = "getoneuserparent/{parentuid}", method = RequestMethod.GET)
	@ResponseBody
	public String GetOneUserParent(@PathVariable String parentuid) {
		List<User> oneUserParent = userService.getUserParent(parentuid);
		User resultUser = new User();
		if (oneUserParent.size() > 0) {
			String tmpUserUid = "";
			String tmpUserName = "";
			String tmpEmployeeName = "";
			for (User user : oneUserParent) {
				tmpUserUid += user.getUid() + ",";
				tmpUserName += user.getUsername() + ",";
				tmpEmployeeName += user.getEmployeename() + ",";
			}
			tmpUserUid = tmpUserUid.substring(0, tmpUserUid.length() - 1);
			tmpUserName = tmpUserName.substring(0, tmpUserName.length() - 1);
			tmpEmployeeName = tmpEmployeeName.substring(0, tmpEmployeeName.length() - 1);
			resultUser.setUid(tmpUserUid);
			resultUser.setUsername(tmpUserName);
			resultUser.setEmployeename(tmpEmployeeName);
		} else {
			Post onePost = postService.GetOnePost(parentuid);
			resultUser.setEmployeename(onePost.getName() + "(暂无员工)");
			resultUser.setUsername(onePost.getName() + "(暂无用户)");
			resultUser.setPostname(onePost.getName());
			resultUser.setPostuid(onePost.getUid());
			resultUser.setUid("0");
		}
		return JsonUtil.ResponseSuccessfulMessage(resultUser);
	}



	@RequestMapping(value = "updatehead", method = RequestMethod.POST)
	@ResponseBody
	public String UpdateHead(HttpServletRequest request, String headurl) {
		if (headurl == "") {
			return JsonUtil.ResponseFailureMessage("请上传图片");
		}

		String url = request.getSession().getServletContext().getRealPath(this.avatarsurl);

		String oldUrl = request.getSession().getServletContext().getRealPath(this.tempavatarsurl);

		File file = new File(oldUrl, headurl);

		File headDir = new File(url, headurl);

		File u = new File(url);
		if (!u.exists()) {
			u.mkdir();
		}

		if (!headDir.exists()) {
			try {
				headDir.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				return JsonUtil.ResponseFailureMessage(e.getMessage());
			}
		} else {
			headDir.delete();
			try {
				headDir.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				return JsonUtil.ResponseFailureMessage(e.getMessage());
			}
		}

		InputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return JsonUtil.ResponseFailureMessage(e.getMessage());
		}
		OutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(headDir, true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return JsonUtil.ResponseFailureMessage(e.getMessage());
		}

		if (fileInputStream != null && fileOutputStream != null) {
			int temp = 0;
			try {
				while ((temp = fileInputStream.read()) != -1) {
					fileOutputStream.write(temp);
				}
				System.out.println("复制完成");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println("复制失败");
				return JsonUtil.ResponseFailureMessage(e.getMessage());
			} finally {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					return JsonUtil.ResponseFailureMessage(e.getMessage());
				}
				try {
					fileOutputStream.flush();
					fileOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					return JsonUtil.ResponseFailureMessage(e.getMessage());
				}
			}

		}

		file.delete();

		this.userService.UpdateHeadByUser(this.webUsrUtil.CurrentUser().getUid(), headurl);
		return JsonUtil.ResponseSuccessfulMessage("保存成功");
	}

	@RequestMapping(value = "updateuserpost", method = RequestMethod.POST)
	@ResponseBody
	public String UpdateUserPost(String useruid, String postuid) {
		String postUidAry[] = new String[5];
		postUidAry = postuid.split(",");
		if (postUidAry.length > 0) {
			this.userService.DeleteUserPost(useruid);
		}
		for (String uid : postUidAry) {
			if (!uid.equalsIgnoreCase("")) {
				User user = new User();
				user.setUid(useruid);
				user.setPostuid(uid);
				this.userService.CreateUserPost(user);
			}
		}

		return JsonUtil.ResponseSuccessfulMessage("更新用户的职位成功");
	}

	// @Resource
	// public AuthorityService authorityService;

	// @RequestMapping(value = "teste", method = RequestMethod.GET)
	// @ResponseBody
	// public String TestEmployee(){
	// com.balintimes.erp.center.model.authority.Employee
	// employee=authorityService.GetEmployee("qq");
	// return JsonUtil.ResponseSuccessfulMessage(employee);
	// }

	// @RequestMapping(value = "tests", method = RequestMethod.GET)
	// @ResponseBody
	// public String TestSuperiors() {
	// List<com.balintimes.erp.center.model.authority.Employee> employees =
	// authorityService.GetSuperiors("qq");
	// return JsonUtil.ResponseSuccessfulMessage(employees);
	// }

	// @RequestMapping(value = "testsub", method = RequestMethod.GET)
	// @ResponseBody
	// public String TestSubordinates() {
	// List<com.balintimes.erp.center.model.authority.Employee> employees =
	// authorityService.GetSubordinates("admin");
	// return JsonUtil.ResponseSuccessfulMessage(employees);
	// }
}
