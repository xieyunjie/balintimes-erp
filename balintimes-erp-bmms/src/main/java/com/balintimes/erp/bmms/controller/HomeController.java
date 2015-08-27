package com.balintimes.erp.bmms.controller;

import com.balintimes.erp.util.mvc.MvcExModel;
import com.balintimes.erp.util.mvc.MvcRUID;
import com.balintimes.erp.util.mvc.MvcWebUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by AlexXie on 2015/8/18.
 */
@RequestMapping("home")
@Controller
public class HomeController {

    //    @RequestMapping("erpname")
//    @ResponseBody
//    public String ErpName() {
//        return "this is Erp Bmms Res";
//    }
    @RequestMapping("erpname")
    @ResponseBody
    public String ErpName(@MvcExModel MvcRUID sessionID) {
        System.out.println("session id is:" + sessionID.getRuid());
        return "this is Erp Bmms Res";
    }

    @RequestMapping("erpmsg")
    @ResponseBody
    public String ErpMsg(@MvcExModel MvcWebUser webuser, String Msg) {

        return "response from java server:" + Msg + "#谢云杰#,current user is:" + webuser.getUid();

    }
}
