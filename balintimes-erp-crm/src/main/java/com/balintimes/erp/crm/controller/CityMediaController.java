package com.balintimes.erp.crm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.balintimes.erp.crm.model.CityMedia;
import com.balintimes.erp.crm.service.CityMediaService;
import com.balintimes.erp.util.json.AjaxResponse;
import com.balintimes.erp.util.json.JsonUtil;
import com.balintimes.erp.util.json.ResponseMessage;

@Controller
@RequestMapping("citymedia")
public class CityMediaController extends BaseController {

	@Resource
	private CityMediaService cityMediaService;

	@RequestMapping("getlist/{cityuid}")
	@ResponseBody
	public AjaxResponse getCityMediaListByCity(@PathVariable String cityuid) {
		List<CityMedia> list = this.cityMediaService
				.getCityMediaListByCity(cityuid);
		return ResponseMessage.successful(list);
	}

	@RequestMapping("getcitymedia/{uid}")
	@ResponseBody
	public AjaxResponse getCityMedia(@PathVariable String uid) {
		CityMedia model = this.cityMediaService.getCityMedia(uid);
		return ResponseMessage.successful(model);
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse createCityMedia(String json) {
		CityMedia cityMedia = JsonUtil.ToObject(json, CityMedia.class);
		this.cityMediaService.createCityMedia(cityMedia);
		return ResponseMessage.successful("success");
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse deleteCityMedia(String uid) {
		this.cityMediaService.deleteCityMedia(uid);
		return ResponseMessage.successful("success");
	}

	@RequestMapping(value = "deletebycity", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse deleteCityMediaByCity(String cityuid) {
		this.cityMediaService.deleteCityMediaByCity(cityuid);
		return ResponseMessage.successful("success");
	}
}
