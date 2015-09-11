package com.balintimes.erp.center.controller;

import com.balintimes.erp.center.base.BaseController;
import com.balintimes.erp.center.model.Post;
import com.balintimes.erp.center.model.User;
import com.balintimes.erp.center.model.UserTree;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import com.balintimes.erp.center.service.PostService;
import com.balintimes.erp.center.service.UserService;
import com.balintimes.erp.center.util.JsonUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

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
    public String UserListforPage(String username, String employeename,
                                  String usertype, String isenable, int page, int pageSize) {
        if (isenable != null) {
            isenable = isenable.equals("-1") ? "" : isenable;
        }

        com.balintimes.erp.center.tuples.TuplePage<List<User>, Integer> result = this.userService
                .GetUserList(username, employeename, usertype, isenable, page,
                        pageSize);

        return JsonUtil.ResponseSuccessfulMessage(result.objectList,
                result.objectTotalCount);
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
        // 相同用户名
        if (this.userService.ExistsUserName(user.getUsername()) == true) {
            return JsonUtil.ResponseFailureMessage("已经存在相同用户名，请重新录入。");
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
        // 相同用户名
        if (this.userService.ExistsUserName(user.getUsername(), user.getUid()) == true) {
            return JsonUtil.ResponseFailureMessage("已经存在相同用户名，请重新录入。");
        }

        if (this.userService.ExistsEmployeeName(user.getEmployeename()) == true) {
            return JsonUtil.ResponseFailureMessage("已经存在相同员工名，请重新录入。");
        }
        user.setEditby(webUsrUtil.CurrentUser().getEmployeeName());
        user.setEdittime(new Date());
        this.userService.updateUser(user);
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
            String username = this.userService.UpdatePassword(webUsrUtil
                    .CurrentUser().getUid(), oldpassword, newpassword);
            if (username.equals("")) {
                return JsonUtil.ResponseFailureMessage("修改密码错误！");
            }
            com.balintimes.erp.center.shiro.Utils.logout();
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username,
                    newpassword);
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
        this.userService.deleteUser(UID, webUsrUtil.CurrentUser()
                .getEmployeeName());

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

    @SuppressWarnings("unchecked")
    @RequestMapping("tree")
    @ResponseBody
    public String GetUserTreeList() {
        List<User> listUsers = new ArrayList<User>();
        listUsers = userService.GetUserTreeList();

        List<Post> posts = new ArrayList<Post>();
        posts = postService.GetPostList();

        List<User> users = new ArrayList<User>();
        users = this.FillUserListWithVacantPost(listUsers, posts);
        Collections.sort(users);

        List<UserTree> trees = InitUserTree(users);
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

    @SuppressWarnings("unchecked")
    @RequestMapping("querytree")
    @ResponseBody
    public String GetUserTreeListByCondition(String username,
                                             String employeename, String postuid, String postname,
                                             String organizationuid, String orgnizationname) {
        List<UserTree> trees = new ArrayList<UserTree>(1000);
        List<User> listUsers = userService.GetUserTreeListByCondition(username,
                employeename, postuid, organizationuid);

        if ((username == null || username.equalsIgnoreCase(""))
                && (employeename == null || employeename.equalsIgnoreCase(""))
                && (postuid == null || postuid.equalsIgnoreCase(""))
                && (organizationuid == null || organizationuid
                .equalsIgnoreCase("")))
            return this.GetUserTreeList();

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

            List<User> users = this.FillUserListWithVacantPost(allUsers, posts);
            Collections.sort(users);

            trees = InitUserTree(users);
        } else {
            return JsonUtil.ResponseSuccessfulMessage("");
        }

        return JsonUtil.ResponseSuccessfulMessage(trees);
    }

    // 根据职位树生成用户树，没有用户的职位同样显示
    private List<User> FillUserListWithVacantPost(List<User> list,
                                                  List<Post> posts) {
        boolean isFind = false;
        List<User> resultUsers = new ArrayList<User>();

        for (Post post : posts) {
            for (User user : list) {
                if (user.getPostuid().equalsIgnoreCase(post.getUid())) {
                    User itemUser = user;
                    itemUser.setPostname(post.getName());
                    itemUser.setParentuid(post.getParentuid());
                    itemUser.setParentname(post.getParentname());
                    itemUser.setOrganizationuid(post.getOrganizationuid());
                    itemUser.setOrganizationname(post.getOrganizationname());
                    resultUsers.add(itemUser);
                    isFind = true;
                }
            }
            if (isFind == true) {
                isFind = false;
                continue;
            } else {
                User tempUser = new User();
                tempUser.setUid("0");
                tempUser.setPostuid(post.getUid());
                tempUser.setPostname(post.getName());
                tempUser.setParentuid(post.getParentuid());
                tempUser.setUsername("暂无用户");
                tempUser.setEmployeename(post.getName() + "(暂无员工)");
                tempUser.setParentuid(post.getParentuid());
                tempUser.setOrganizationuid(post.getOrganizationuid());
                tempUser.setOrganizationname(post.getOrganizationname());
                resultUsers.add(tempUser);
            }

        }
        return resultUsers;
    }

    private List<UserTree> InitUserTree(List<User> list) {
        List<UserTree> trees = new ArrayList<UserTree>();
        if (list == null) {
            return trees;
        }
        if (list.size() < 1) {
            return trees;
        }

        String rootUID = "";
        UserTree rootUser = null;
        rootUID = "00000000-0000-0000-0000-000000000001";
        for (User user : list) {
            if (user.getPostuid().equalsIgnoreCase(rootUID)) {
                rootUser = new UserTree(user);
                rootUser.setChildren(this.GetChildren(list,
                        rootUser.getPostuid(), rootUser.getPostname()));
                trees.add(rootUser);
                break;
            }
        }

        return trees;
    }

    private List<UserTree> GetChildren(List<User> list, String parentUID,
                                       String parentname) {

        List<UserTree> tree = new ArrayList<UserTree>();

        for (User user : list) {
            if (user.getParentuid().equalsIgnoreCase(parentUID)) {

                UserTree node = new UserTree(user);
                node.setParentname(parentname);
                node.setChildren(this.GetChildren(list, user.getPostuid(),
                        node.getPostname()));
                tree.add(node);
            }
        }

        return tree;
    }

    @RequestMapping(value = "getoneuser/{uid}", method = RequestMethod.GET)
    @ResponseBody
    public String GetOneUser(@PathVariable String uid) {
        User oneUser = userService.GetOneUser(uid);
        return JsonUtil.ResponseSuccessfulMessage(oneUser);
    }

    @RequestMapping(value = "getoneuserparent/{parentuid}", method = RequestMethod.GET)
    @ResponseBody
    public String GetOneUserParent(@PathVariable String parentuid) {
        User oneUserParent = userService.GetOneUserParent(parentuid);
        return JsonUtil.ResponseSuccessfulMessage(oneUserParent);
    }

    // @RequestMapping(value = "upload", method = RequestMethod.POST)
    // @ResponseBody
    // public String UploadFile(UserUpload userUpload){
    //
    // @SuppressWarnings("unused")
    // UserUpload aUpload=userUpload;
    // return JsonUtil.ResponseSuccessfulMessage("上传成功");
    // }

    @RequestMapping(value = "upload")
    public String upload(
            @RequestParam(value = "file", required = false) MultipartFile file,
            HttpServletRequest request, ModelMap model) {

        System.out.println("开始");
        String path = request.getSession().getServletContext()
                .getRealPath("upload");
        String fileName = file.getOriginalFilename();
        System.out.println(path);
        File targetFile = new File(path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        // // 保存
        // try {
        // file.transferTo(targetFile);
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        // com.balintimes.erp.center.model.addAttribute("fileUrl", request.getContextPath() + "/upload/" +
        // fileName);

        return "result";
    }

    @RequestMapping(value = "userheadupload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadImg(HttpServletRequest request,
                            HttpServletResponse response) throws IllegalStateException,
            IOException {

        List<String> filenames = new ArrayList<String>();

        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                // 取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    // 取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (myFileName.trim() != "") {
                        // System.out.println(myFileName);
                        // 重命名上传后的文件名
                        int index = myFileName.lastIndexOf(".");
                        String prx = myFileName.substring(index);
                        // String fileName = file.getOriginalFilename();
                        String fileName = this.webUsrUtil.CurrentUser()
                                .getUid() + prx;
                        // 定义上传路径

                        String url = request.getSession().getServletContext()
                                .getRealPath(this.tempavatarsurl);
                        File targetFile = new File(url, fileName);
                        if (!targetFile.exists()) {
                            targetFile.mkdirs();
                        }

                        filenames.add(fileName);

                        // String path = "H:/" + fileName;
                        // File localFile = new File(path);
                        file.transferTo(targetFile);
                    }
                }
            }

        }
        return JsonUtil.ResponseSuccessfulMessage(filenames);
    }

    @RequestMapping(value = "updatehead", method = RequestMethod.POST)
    @ResponseBody
    public String UpdateHead(HttpServletRequest request, String headurl) {
        if (headurl == "") {
            return JsonUtil.ResponseFailureMessage("请上传图片");
        }

        String url = request.getSession().getServletContext()
                .getRealPath(this.avatarsurl);

        String oldUrl = request.getSession().getServletContext()
                .getRealPath(this.tempavatarsurl);

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
                //e.printStackTrace();
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
            //e.printStackTrace();
            return JsonUtil.ResponseFailureMessage(e.getMessage());
        }
        OutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(headDir, true);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
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
                //e.printStackTrace();
                System.out.println("复制失败");
                return JsonUtil.ResponseFailureMessage(e.getMessage());
            } finally {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    //e.printStackTrace();
                    return JsonUtil.ResponseFailureMessage(e.getMessage());
                }
                try {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    //e.printStackTrace();
                    return JsonUtil.ResponseFailureMessage(e.getMessage());
                }
            }

        }

        file.delete();


        this.userService.UpdateHeadByUser(this.webUsrUtil.CurrentUser().getUid(), headurl);
        return JsonUtil.ResponseSuccessfulMessage("保存成功");
    }

    // @Resource
    // public AuthorityService authorityService;

    // @RequestMapping(value = "teste", method = RequestMethod.GET)
    // @ResponseBody
    // public String TestEmployee(){
    // com.balintimes.erp.center.model.authority.Employee employee=authorityService.GetEmployee("qq");
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
