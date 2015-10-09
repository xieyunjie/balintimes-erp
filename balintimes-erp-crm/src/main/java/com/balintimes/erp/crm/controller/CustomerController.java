package com.balintimes.erp.crm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.balintimes.erp.crm.model.Customer;
import com.balintimes.erp.crm.model.CustomerFollowUp;
import com.balintimes.erp.crm.model.EmpCustomer;
import com.balintimes.erp.crm.model.SalerCustomer;
import com.balintimes.erp.crm.service.CustomerProcService;
import com.balintimes.erp.crm.service.CustomerService;
import com.balintimes.erp.crm.service.CenterWebService;
import com.balintimes.erp.crm.service.SalerCustomerService;
import com.balintimes.erp.model.crm.BusinessType;
import com.balintimes.erp.model.crm.CustomerCategory;
import com.balintimes.erp.model.crm.Employee;
import com.balintimes.erp.util.json.AjaxResponse;
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
	@Resource
	private SalerCustomerService salerCustomerService;

	@RequestMapping(value = "listbyemp", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResponse getCustomerByEmp(@MvcModel WebUser currUser,
			String name, String businesstype, Integer isreg, String brand,
			Integer pagesize, Integer page, Boolean isshowdown) {
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

		if (isreg == null)
			isreg = -1;

		TuplePage<List<EmpCustomer>, Integer> tp = this.customerProcService
				.getCustomerByEmp(name, useruids, businesstype, isreg, brand,
						pagesize, page);

		for (EmpCustomer item : tp.objectList) {
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

	@RequestMapping(value = "customerlist", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResponse getCustomerByName(String name, int page, int pagesize) {
		TuplePage<List<Customer>, Integer> tp = this.customerService
				.getCustomerList(name, page, pagesize);
		return ResponseMessage.successful(tp.objectList, tp.objectTotalCount);
	}

	@RequestMapping("getcustomer/{uid}/{isreg}")
	@ResponseBody
	public AjaxResponse getCustomer(@PathVariable int uid,
			@PathVariable boolean isreg) {
		EmpCustomer ec = new EmpCustomer();
		if (isreg) {
			CustomerFollowUp fp = this.customerService.getCustomerFollowUp(uid);
			Customer c = this.customerService.getCustomer(fp.getCustomerUid());

			ec.setUid(c.getUid());
			ec.setAreaCode(c.getAreaCode());
			ec.setAreaName(c.getAreaName());
			ec.setAreaUid(c.getAreaUid());
			ec.setBrand(fp.getBrand());
			ec.setBusinessTypeUid(c.getBusinessType());
			ec.setCompanyAddress(c.getCompanyAddress());
			ec.setCustomerCategoryUid(fp.getCustomerCategoryUid());
			ec.setDeleted(c.isDeleted());
			ec.setFollowUid(fp.getUid());
			ec.setForbidden(fp.isForbidden());
			ec.setId(-1);
			ec.setName(c.getName());
			ec.setParentUid(c.getParentUid());
			ec.setPhone(c.getPhone());
			ec.setPostCode(c.getPostCode());
			ec.setProxyCompany(fp.getProxyCompany());
			ec.setReg(true);
			ec.setState(fp.getState());
			ec.setStateName(fp.getState() == 0 ? "登记" : "注册");
			ec.setUserUid(fp.getUserUid());

		} else {
			SalerCustomer salerCustomer = this.salerCustomerService
					.getSalerCustomer(uid);
			ec.setUid(salerCustomer.getUid());
			ec.setAreaCode(salerCustomer.getAreaCode());
			ec.setAreaName(salerCustomer.getAreaName());
			ec.setAreaUid(salerCustomer.getAreaUid());
			ec.setBrand(salerCustomer.getBrand());
			ec.setBusinessTypeUid(salerCustomer.getBusinessType());
			ec.setCompanyAddress(salerCustomer.getCompanyAddress());
			ec.setCustomerCategoryUid(salerCustomer.getCustomerCategory());
			ec.setDeleted(salerCustomer.isDeleted());
			ec.setFollowUid(-1);
			ec.setForbidden(salerCustomer.isForbidden());
			ec.setId(-1);
			ec.setName(salerCustomer.getName());
			ec.setParentUid(salerCustomer.getParentUid());
			ec.setPhone(salerCustomer.getPhone());
			ec.setPostCode(salerCustomer.getPostCode());
			ec.setProxyCompany(salerCustomer.getProxyCompany());
			ec.setReg(false);
			ec.setState(0);
			ec.setStateName("登记");
			ec.setUserUid(salerCustomer.getUserUid());
		}

		BusinessType bt = this.centerWebService.getBusinessType(ec
				.getBusinessTypeUid());
		ec.setBusinessTypeName(bt.getName());
		CustomerCategory cc = this.centerWebService.getCustomerCategory(ec
				.getCustomerCategoryUid());
		ec.setCustomerCategoryName(cc.getName());
		return ResponseMessage.successful(ec);
	}

	@RequestMapping(value = "updatecustomer", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse updateCustomer(@MvcModel WebUser currUser,
			EmpCustomer customer) {

		if (customer.isReg()) {
			CustomerFollowUp follow = new CustomerFollowUp();
			follow.setUid(customer.getFollowUid());
			follow.setBrand(customer.getBrand());
			follow.setCustomerCategoryUid(customer.getCustomerCategoryUid());
			follow.setProxyCompany(customer.getProxyCompany());

			CustomerCategory cc = this.centerWebService
					.getCustomerCategory(customer.getCustomerCategoryUid());
			follow.setCustomerCategoryName(cc.getName());

			this.customerService.updateCustomerFollowUp(follow);
		} else {
			SalerCustomer sc = new SalerCustomer();
			sc.setUid(customer.getUid());
			sc.setName(customer.getName());
			sc.setBusinessType(customer.getBusinessTypeUid());
			sc.setBrand(customer.getBrand());
			sc.setCustomerCategory(customer.getCustomerCategoryUid());
			sc.setProxyCompany(customer.getProxyCompany());
			sc.setAreaUid(customer.getAreaUid());
			sc.setAreaCode(customer.getAreaCode());
			sc.setPhone(customer.getPhone());
			sc.setCompanyAddress(customer.getCompanyAddress());
			sc.setPostCode(customer.getPostCode());
			sc.setForbidden(false);
			sc.setDeleted(false);
			sc.setEditBy(currUser.getEmployeeName());

			BusinessType bt = this.centerWebService.getBusinessType(customer
					.getBusinessTypeUid());
			sc.setBusinessTypeName(bt.getName());
			CustomerCategory cc = this.centerWebService
					.getCustomerCategory(customer.getCustomerCategoryUid());
			sc.setCustomerCategoryName(cc.getName());

			this.salerCustomerService.updateSalerCustomer(sc);
		}
		return ResponseMessage.successful("保存成功");

	}

	@RequestMapping(value = "createcustomer", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse createCustomer(@MvcModel WebUser currUser,
			EmpCustomer customer) {
		if (customer.isReg()) {
			CustomerFollowUp fp = new CustomerFollowUp();
			fp.setCustomerUid(customer.getFollowUid());
			fp.setCustomerCategoryUid(customer.getCustomerCategoryUid());
			fp.setProxyCompany(customer.getProxyCompany());
			fp.setBrand(customer.getBrand());
			fp.setState(0);
			fp.setUserUid(currUser.getUid());
			fp.setForbidden(false);
			fp.setDeleted(false);

			CustomerCategory cc = this.centerWebService
					.getCustomerCategory(customer.getCustomerCategoryUid());
			fp.setCustomerCategoryName(cc.getName());

			this.customerService.createCustomerFollowUp(fp);

		} else {
			SalerCustomer sc = new SalerCustomer();
			sc.setUid(customer.getUid());
			sc.setName(customer.getName());
			sc.setBusinessType(customer.getBusinessTypeUid());
			sc.setBrand(customer.getBrand());
			sc.setCustomerCategory(customer.getCustomerCategoryUid());
			sc.setProxyCompany(customer.getProxyCompany());
			sc.setAreaUid(customer.getAreaUid());
			sc.setAreaCode(customer.getAreaCode());
			sc.setPhone(customer.getPhone());
			sc.setCompanyAddress(customer.getCompanyAddress());
			sc.setPostCode(customer.getPostCode());
			sc.setForbidden(false);
			sc.setDeleted(false);
			sc.setUserUid(currUser.getUid());
			sc.setCreateBy(currUser.getEmployeeName());

			BusinessType bt = this.centerWebService.getBusinessType(customer
					.getBusinessTypeUid());
			sc.setBusinessTypeName(bt.getName());
			CustomerCategory cc = this.centerWebService
					.getCustomerCategory(customer.getCustomerCategoryUid());
			sc.setCustomerCategoryName(cc.getName());

			this.salerCustomerService.createSalerCustomer(sc);
		}
		return ResponseMessage.successful("保存成功");
	}

	@RequestMapping(value = "deletecustomer", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse deleteCustomer(int uid, boolean isReg) {
		if (isReg) {
			this.customerService.updateCustomerFollowUpByDel(uid);
		} else {
			this.salerCustomerService.updateSalerCustomerByDel(uid);
		}

		return ResponseMessage.successful("保存成功");
	}
}
