package com.balintimes.erp.center.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.balintimes.erp.center.dao.StationDao;
import com.balintimes.erp.center.model.Station;
import com.balintimes.erp.center.service.StationService;

@Service("stationservice")
public class StationServiceImpl implements StationService{

	@Resource
	private StationDao stationDao;
	
	public List<Station> GetStationList() {		
		return stationDao.GetStationList();
	}

	
	public List<Station> GetStationListByCondition(Map<String, Object> params) {		
		return stationDao.GetStationListByCondition(params);
	}

	
	public Station GetOneStation(String uid) {		
		return stationDao.GetOneStation(uid);
	}
	
	public String GetMaxKid() {		
		return stationDao.GetMaxKid();
	}

	@Transactional
	public void UpdateStation(Station station) {
		// TODO Auto-generated method stub
		stationDao.UpdateStation(station);		
	}

	@Transactional
	public void DeleteStation(String uid) {
		// TODO Auto-generated method stub
		stationDao.DeleteStation(uid);
	}

	@Transactional
	public boolean CreateStation(Station station) {
		// TODO Auto-generated method stub
		return stationDao.CreateStation(station);
	}

}
