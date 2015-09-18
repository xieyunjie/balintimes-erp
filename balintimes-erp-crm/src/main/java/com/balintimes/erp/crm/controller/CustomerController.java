package com.balintimes.erp.crm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.balintimes.erp.crm.model.Customer;
import com.balintimes.erp.crm.model.EmpCustomer;
import com.balintimes.erp.crm.service.CustomerProcService;
import com.balintimes.erp.crm.service.CustomerService;
import com.balintimes.erp.crm.service.CenterWebService;
import com.balintimes.erp.model.crm.Employee;
import com.balintimes.erp.util.json.AjaxResponse;
import com.balintimes.erp.util.json.JsonUtil;
import com.balintimes.erp.util.json.ResponseMessage;
import com.balintimes.erp.util.mvc.annon.MvcModel;
import com.balintimes.erp.util.mvc.model.WebUser;
import com.balintimes.erp.util.tuples.TuplePage;

@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController {

	@Resource
	private CustomerProcService customerProcService;
	@Resource
	private CustomerService customerService;
	@Resource
	private CenterWebService centerWebService;

	@RequestMapping("listbyemp/{json}")
	@ResponseBody
	public AjaxResponse getCustomerByEmp(@MvcModel WebUser currUser,
			@PathVariable String json) {
		Map<String, Object> map = JsonUtil.ToMap(json);
		String useruids = currUser.getUid();
		List<Employee> emps = new ArrayList<Employee>();
		Employee emp = new Employee();
		emp.setUid(currUser.getUid());
		emp.setUsername(currUser.getUsername());
		emp.setEmployeename(currUser.getEmployeeName());

		emps.add(emp);

		if ((boolean) map.get("isShowDown")) {
			emps = this.centerWebService.getSubordinatesByUser(currUser
					.getUsername());
			if (emps != null && emps.size() > 0) {
				for (Employee it : emps) {
					useruids += "," + it.getUid();
				}
			}
		}

		TuplePage<List<EmpCustomer>, Integer> page = this.customerProcService
				.getCustomerByEmp(map.get("name").toString(), useruids, map
						.get("businessType").toString(), Integer.parseInt(map
						.get("isReg").toString()), map.get("brand").toString(),
						(int) map.get("pageSize"), (int) map.get("currPage"));

		for (EmpCustomer item : page.objectList) {
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

		return ResponseMessage.successful(page.objectList,
				page.objectTotalCount);
	}

	@RequestMapping("customerlist/{json}")
	@ResponseBody
	public AjaxResponse getCustomerByName(@PathVariable String json) {
		Map<String, Object> map = JsonUtil.ToMap(json);
		TuplePage<List<Customer>, Integer> page = this.customerService
				.getCustomerList(map.get("name").toString(),
						(int) map.get("currPage"), (int) map.get("pageSize"));
		return ResponseMessage.successful(page.objectList,
				page.objectTotalCount);
	}

	@RequestMapping("getcustomer/{uid}")
	@ResponseBody
	public AjaxResponse getCustomer(@PathVariable int uid) {
		return ResponseMessage
				.successful(this.customerService.getCustomer(uid));
	}
}
