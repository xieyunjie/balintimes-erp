package com.balintimes.erp.crm.controller;

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

import com.balintimes.erp.crm.model.AttachmentInfo;
import com.balintimes.erp.crm.service.AttachmentService;
import com.balintimes.erp.util.common.FileDetail;
import com.balintimes.erp.util.common.IoUtil;
import com.balintimes.erp.util.json.AjaxResponse;
import com.balintimes.erp.util.json.ResponseMessage;

@Controller
@RequestMapping("attachment")
public class AttachmentController extends BaseController {

	@Resource
	private AttachmentService attachmentService;

	@Value("#{uploadpathProperties['upload.temppath']}")
	private String tempUrl;
	@Value("#{uploadpathProperties['upload.base']}")
	private String baseUrl;
	@Value("#{uploadpathProperties['upload.attpath']}")
	private String attsUrl;

	@RequestMapping("getattachmentlist/{objuid}/{isreg}")
	@ResponseBody
	public AjaxResponse getAttachmentList(@PathVariable int objuid,
			@PathVariable boolean isreg) {
		List<AttachmentInfo> list = this.attachmentService
				.getAttachmentInfoList(objuid, isreg);
		return ResponseMessage.successful(list);
	}

	@RequestMapping("getatt/{uid}/{isreg}")
	@ResponseBody
	public AjaxResponse getAttachment(@PathVariable int uid,
			@PathVariable boolean isreg) {
		AttachmentInfo att = this.attachmentService.getAttachmentInfo(uid,
				isreg);
		return ResponseMessage.successful(att);
	}

	@RequestMapping(value = "uploadatts", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse uploadAtts(HttpServletRequest request,
			HttpServletResponse response) throws IllegalStateException,
			IOException {
		String attPath = baseUrl + tempUrl;
		String fileName = UUID.randomUUID().toString();
		List<FileDetail> fds = IoUtil.upload(request, response, tempUrl,
				fileName);
		for (FileDetail item : fds) {
			item.setBaseUrl(attPath);
		}
		return ResponseMessage.successful(fds);
	}
}
