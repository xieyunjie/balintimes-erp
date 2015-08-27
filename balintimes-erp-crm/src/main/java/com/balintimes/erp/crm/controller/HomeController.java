package com.balintimes.erp.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by AlexXie on 2015/8/18.
 */
@RequestMapping("home")
@Controller
public class HomeController {

    @RequestMapping("erpname")
    @ResponseBody
    public String ErpName() {
        return "this is Erp CRM CRM Res";
    }

    @RequestMapping("erpmsg")
    @ResponseBody
    public String ErpMsg(String Msg) {
        return "CrM Msg is: " + Msg ;
    }
}
