package com.balintimes.erp.crm.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.balintimes.erp.crm.model.ContractInfo;
import com.balintimes.erp.crm.service.ContractService;
import com.balintimes.erp.util.common.FileDetail;
import com.balintimes.erp.util.common.IoUtil;
import com.balintimes.erp.util.json.AjaxResponse;
import com.balintimes.erp.util.json.ResponseMessage;
import com.balintimes.erp.util.mvc.annon.MvcModel;
import com.balintimes.erp.util.mvc.model.WebUser;

@Controller
@RequestMapping("contract")
public class ContractController extends BaseController {

	@Resource
	private ContractService contractService;

	@Value("#{uploadpathProperties['upload.temppath']}")
	private String tempUrl;
	@Value("#{uploadpathProperties['upload.base']}")
	private String baseUrl;
	@Value("#{uploadpathProperties['upload.cardpath']}")
	private String cardUrl;

	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse createContractInfo(@MvcModel WebUser currUser,
			ContractInfo contract, HttpServletRequest request)
			throws FileNotFoundException, IOException, Exception {
		String oldPath = tempUrl + contract.getCardUrl();
		String newPath = cardUrl;

		String old = request.getSession().getServletContext()
				.getRealPath(oldPath);
		String newUrl = request.getSession().getServletContext()
				.getRealPath(newPath);

		IoUtil.cut(old, contract.getCardUrl(), newUrl);

		contract.setCreateBy(currUser.getEmployeeName());
		this.contractService.createContractInfo(contract);
		return ResponseMessage.successful("保存成功");
	}

	@RequestMapping(value = "uploadcard", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse uploadCard(HttpServletRequest request,
			HttpServletResponse response) throws IllegalStateException,
			IOException {
		String imgPath = baseUrl + tempUrl;
		String fileName = UUID.randomUUID().toString();
		List<FileDetail> fds = IoUtil.upload(request, response, tempUrl,
				fileName);
		for (FileDetail item : fds) {
			item.setBaseUrl(imgPath);
		}
		return ResponseMessage.successful(fds);
	}

	@RequestMapping("getlistbycustomer/{objuid}/{isreg}")
	@ResponseBody
	public AjaxResponse getContractInfoList(@PathVariable int objuid,
			@PathVariable boolean isreg) {
		List<ContractInfo> list = this.contractService.getContractListByObj(
				objuid, isreg);
		return ResponseMessage.successful(list);
	}

	@RequestMapping("getcontract/{uid}/{isreg}")
	@ResponseBody
	public AjaxResponse getContractInfo(@PathVariable int uid,
			@PathVariable boolean isreg) {
		ContractInfo contract = this.contractService
				.getContractInfo(uid, isreg);

		String url = baseUrl + cardUrl + contract.getCardUrl();
		contract.setCardUrl(url);
		return ResponseMessage.successful(contract);
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse updateContractInfo(@MvcModel WebUser currUser,
			HttpServletRequest request, ContractInfo contract)
			throws FileNotFoundException, IOException, Exception {
		
		if (!contract.getCardUrl().equals("")) {
			String oldPath = tempUrl + contract.getCardUrl();
			String newPath = cardUrl;

			String old = request.getSession().getServletContext()
					.getRealPath(oldPath);
			String newUrl = request.getSession().getServletContext()
					.getRealPath(newPath);

			IoUtil.cut(old, contract.getCardUrl(), newUrl);
		} else {

			ContractInfo c = this.contractService.getContractInfo(
					contract.getUid(), contract.isReg());
			contract.setCardUrl(c.getCardUrl());
		}
		
		contract.setEditBy(currUser.getEmployeeName());
		this.contractService.updateContractInfo(contract);

		return ResponseMessage.successful("保存成功");
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse deleteContractInfo(int uid, boolean isreg) {
		this.contractService.deleteContractInfo(uid, isreg);
		return ResponseMessage.successful("保存成功");
	}

}
