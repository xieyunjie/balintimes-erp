package com.balintimes.erp.crm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.balintimes.erp.crm.model.Media;
import com.balintimes.erp.crm.service.MediaService;
import com.balintimes.erp.util.json.AjaxResponse;
import com.balintimes.erp.util.json.ResponseMessage;

@Controller
@RequestMapping("media")
public class MediaController extends BaseController {

	@Resource
	private MediaService mediaService;

	@RequestMapping("getlist")
	@ResponseBody
	public AjaxResponse getMediaList() {
		List<Media> list = this.mediaService.getMediaList(null);
		return ResponseMessage.successful(list);
	}

	@RequestMapping("getmedia/{uid}")
	@ResponseBody
	public AjaxResponse getMedia(@PathVariable String uid) {
		Media model = this.mediaService.getMedia(uid);
		return ResponseMessage.successful(model);
	}
}
