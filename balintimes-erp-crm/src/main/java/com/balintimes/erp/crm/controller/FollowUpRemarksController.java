package com.balintimes.erp.crm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.balintimes.erp.crm.model.RemarksInfo;
import com.balintimes.erp.crm.service.CenterWebService;
import com.balintimes.erp.crm.service.CustomerProcService;
import com.balintimes.erp.crm.service.FollowUpRemarksService;
import com.balintimes.erp.model.crm.Employee;
import com.balintimes.erp.util.common.DateUtil;
import com.balintimes.erp.util.json.AjaxResponse;
import com.balintimes.erp.util.json.ResponseMessage;
import com.balintimes.erp.util.mvc.annon.MvcModel;
import com.balintimes.erp.util.mvc.model.WebUser;
import com.balintimes.erp.util.tuples.TuplePage;

@Controller
@RequestMapping("remarks")
public class FollowUpRemarksController extends BaseController {

	@Resource
	private FollowUpRemarksService followUpRemarksService;
	@Resource
	private CustomerProcService customerProcService;
	@Resource
	private CenterWebService centerWebService;

	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse createRemarks(@MvcModel WebUser currUser,
			RemarksInfo remarks) {
		remarks.setCreateBy(currUser.getEmployeeName());
		remarks.setUserUid(currUser.getUid());
		this.followUpRemarksService.createRemarks(remarks);
		return ResponseMessage.successful("保存成功");
	}

	@RequestMapping(value = "getremarksbyemp", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResponse getRemarksByEmp(@MvcModel WebUser currUser,
			String customername, String brand, Integer manneruid,
			String begindate, String enddate, Boolean isshowdown, int pagesize,
			int page) {

		String useruids = currUser.getUid();
		List<Employee> emps = new ArrayList<Employee>();
		Employee emp = new Employee();
		emp.setUid(currUser.getUid());
		emp.setUsername(currUser.getUsername());
		emp.setEmployeename(currUser.getEmployeeName());

		emps.add(emp);

		if (isshowdown != null && isshowdown == true) {
			emps = this.centerWebService.getSubordinatesByUser(currUser
					.getUsername());
			if (emps != null && emps.size() > 0) {
				for (Employee it : emps) {
					useruids += "," + it.getUid();
				}
			}
		}

		Date bd = DateUtil.stringToDate(begindate);
		Date ed = DateUtil.stringToDate(enddate);

		TuplePage<List<RemarksInfo>, Integer> tp = this.customerProcService
				.getRemarksByEmp(customername, brand, useruids, manneruid, bd,
						ed, pagesize, page);

		for (RemarksInfo item : tp.objectList) {
			boolean b = false;
			for (Employee it : emps) {
				if (it.getUid().equals(item.getUserUid())) {
					b = true;
					item.setUserName(it.getEmployeename());
				}
			}
			if (b)
				continue;
		}

		return ResponseMessage.successful(tp.objectList, tp.objectTotalCount);
	}

	@RequestMapping("getremark/{uid}/{isreg}")
	@ResponseBody
	public AjaxResponse getRemark(@PathVariable int uid,
			@PathVariable boolean isreg) {
		RemarksInfo remarks = this.followUpRemarksService.getRemark(uid, isreg);
		return ResponseMessage.successful(remarks);
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse deleteRemark(int uid, boolean isreg) {
		this.followUpRemarksService.deleteRemark(uid, isreg);
		return ResponseMessage.successful("删除成功");
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse updateRemarks(@MvcModel WebUser currUser,
			RemarksInfo remarks) {
		remarks.setEditBy(currUser.getEmployeeName());
		this.followUpRemarksService.updateRemark(remarks);
		return ResponseMessage.successful("保存成功");
	}

}
