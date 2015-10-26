package com.balintimes.erp.center.controller;

import java.util.*;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.balintimes.erp.center.base.BaseController;
import com.balintimes.erp.center.model.Station;
import com.balintimes.erp.center.service.LineService;
import com.balintimes.erp.center.service.StationService;
import com.balintimes.erp.center.util.JsonUtil;

@Controller
@RequestMapping("station")
public class StationController extends BaseController {
	@Resource
	private StationService stationService;
	@Resource
	private LineService lineService;

	@RequestMapping(value = "listbypage", method = RequestMethod.POST)
	@ResponseBody
	public String GetStationListByCondition(String lineuid, String name) {
		Map<String, Object> parameters = new HashMap<String, Object>(2);
		parameters.put("name", name);
		parameters.put("lineuid", lineuid);
		List<Station> stations = stationService.GetStationListByCondition(parameters);
		return JsonUtil.ResponseSuccessfulMessage(stations);
	}

	@RequestMapping(value = "getonestation", method = RequestMethod.POST)
	@ResponseBody
	public String GetOneStation(String uid) {
		Station station = stationService.GetOneStation(uid);
		return JsonUtil.ResponseSuccessfulMessage(station);
	}

	public String GetMaxKid() {
		return stationService.GetMaxKid();
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String UpdateStation(Station station) {
		// TODO Auto-generated method stub
		stationService.UpdateStation(station);
		return JsonUtil.ResponseSuccessfulMessage("修改成功");
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public String DeleteStation(String uid) {
		// TODO Auto-generated method stub
		stationService.DeleteStation(uid);
		return JsonUtil.ResponseSuccessfulMessage("删除成功");
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public String CreateStation(Station station) {
		station.setUid(UUID.randomUUID().toString());
		station.setCreatorid(webUsrUtil.CurrentUser().getUid());
		station.setCreatorname(webUsrUtil.CurrentUser().getUsername());
		station.setCreatetime(new Date());
		station.setEdittime(new Date());
		station.setDeleted(false);
		boolean bl = stationService.CreateStation(station);
		if (bl)
			return JsonUtil.ResponseSuccessfulMessage("添加新站点成功");
		else
			return JsonUtil.ResponseSuccessfulMessage("添加失败");
	}

}
