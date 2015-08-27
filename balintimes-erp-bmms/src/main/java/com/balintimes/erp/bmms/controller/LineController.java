package com.balintimes.erp.bmms.controller;

import com.balintimes.erp.util.json.AjaxResponse;
import com.balintimes.erp.util.json.ResponseMessage;
import com.balintimes.erp.bmms.model.Line;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by AlexXie on 2015/8/26.
 */
@Controller
@RequestMapping("line")
public class LineController extends BaseController {

    static List<Line> list = null;

    public LineController() {

        list = new ArrayList<Line>(54);
        Line line;
        for (int i = 0; i < 54; i++) {
            line = new Line();
            line.setUid(UUID.randomUUID().toString());
            line.setName(i + "AB水");
            line.setCreatetime(new Date());

            list.add(line);
        }
    }

    @RequestMapping(value = "get/{uid}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResponse listAll(@PathVariable String uid) {

        Line l = null;
        for (Line line : list) {
            if (uid.equalsIgnoreCase(line.getUid())) {
                l = line;
                break;
            }
        }

        return ResponseMessage.successful(l);
    }

    @RequestMapping(value = "query/{pagesize}/{page}/{query}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResponse getLine(@PathVariable String query, @PathVariable int pagesize, @PathVariable int page) {

        int start = (page - 1) * pagesize;
        int take = 0;

        List<Line> sublist = new ArrayList<Line>(pagesize);
        for (int i = start; i < this.list.size() && take < pagesize; i++, take++) {
            sublist.add(list.get(i));
        }

        return ResponseMessage.successful(sublist, 54);
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse SaveLine(Line line) {
        System.out.println(line.toString());
        if (line.getUid().equals("0")) {

        } else {

        }

        return ResponseMessage.successful("保存成功");
    }
}
