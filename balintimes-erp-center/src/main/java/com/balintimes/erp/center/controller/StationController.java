package com.balintimes.erp.center.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class StationController extends BaseController{
	@Resource
	private StationService stationService;
	@Resource
	private LineService lineService;
	
	@RequestMapping(value="listbypage",method=RequestMethod.POST)
	@ResponseBody
	public String GetStationListByCondition(String lineuid,String name) {
		Map<String,Object> parameters=new HashMap<String, Object>(2);
		parameters.put("name",name);
		parameters.put("lineuid",lineuid);
		List<Station> stations=stationService.GetStationListByCondition(parameters);
		return JsonUtil.ResponseSuccessfulMessage(stations);
	}

	
//	public Station GetOneStation(String uid) {		
//		return stationDao.GetOneStation(uid);
//	}
//	
//	public String GetMaxKid() {		
//		return stationDao.GetMaxKid();
//	}
//	
//	public void UpdateStation(Station station) {
//		// TODO Auto-generated method stub
//		stationDao.UpdateStation(station);		
//	}
//
//	
//	public void DeleteStation(String uid) {
//		// TODO Auto-generated method stub
//		stationDao.DeleteStation(uid);
//	}
//	
//	public boolean CreateStation(Station station) {
//		// TODO Auto-generated method stub
//		return stationDao.CreateStation(station);
//	}

}
