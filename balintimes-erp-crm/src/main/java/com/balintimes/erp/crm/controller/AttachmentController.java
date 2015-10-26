package com.balintimes.erp.crm.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

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
import com.balintimes.erp.util.json.JsonUtil;
import com.balintimes.erp.util.json.ResponseMessage;
import com.balintimes.erp.util.mvc.annon.MvcModel;
import com.balintimes.erp.util.mvc.model.WebUser;

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
		for (AttachmentInfo item : list) {
			String path = this.baseUrl + this.attsUrl + item.getUrl();
			item.setUrl(path);
		}
		return ResponseMessage.successful(list);
	}

	@RequestMapping("getatt/{uid}/{isreg}")
	@ResponseBody
	public AjaxResponse getAttachment(@PathVariable int uid,
			@PathVariable boolean isreg) {
		AttachmentInfo att = this.attachmentService.getAttachmentInfo(uid,
				isreg);
		String path = this.baseUrl + this.attsUrl + att.getUrl();
		att.setUrl(path);
		return ResponseMessage.successful(att);
	}

	@RequestMapping(value = "uploadatts", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse uploadAtts(HttpServletRequest request,
			HttpServletResponse response) throws IllegalStateException,
			IOException {
		String attPath = baseUrl + tempUrl;
		List<FileDetail> fds = IoUtil.upload(request, response, tempUrl);
		for (FileDetail item : fds) {
			item.setBaseUrl(attPath);
		}
		return ResponseMessage.successful(fds);
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse createAttachmentInfo(@MvcModel WebUser currUser,
			HttpServletRequest request, String json)
			throws FileNotFoundException, IOException, Exception {
		List<AttachmentInfo> list = JsonUtil.ToList(json, AttachmentInfo.class);
		for (AttachmentInfo item : list) {
			String oldPath = tempUrl + item.getUrl();
			String newPath = attsUrl;

			String old = request.getSession().getServletContext()
					.getRealPath(oldPath);
			String newUrl = request.getSession().getServletContext()
					.getRealPath(newPath);

			IoUtil.cut(old, item.getUrl(), newUrl);

			item.setCreateBy(currUser.getEmployeeName());
			this.attachmentService.createAttachmentInfo(item);
		}
		return ResponseMessage.successful("保存成功");
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse deleteAttachmentInfo(int uid, boolean isreg) {
		this.attachmentService.deleteAttachmentInfo(uid, isreg);
		return ResponseMessage.successful("删除成功");
	}
}
