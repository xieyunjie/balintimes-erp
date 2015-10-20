package com.balintimes.erp.center.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.balintimes.erp.center.dao.StationDao;
import com.balintimes.erp.center.mappers.StationMapper;
import com.balintimes.erp.center.model.Station;

@Repository("stationdao")
public class StationDaoImpl implements StationDao {
    @Resource 
    private StationMapper stationMapper;
		
	public List<Station> GetStationList() {		
		return stationMapper.GetStationList();
	}


	public List<Station> GetStationListByCondition(Map<String, Object> params) {
		return this.stationMapper.GetStationListByCondition(params);
	}


	public Station GetOneStation(String uid) {
		// TODO Auto-generated method stub
		return stationMapper.GetOneStation(uid);
	}
	
	public String GetMaxKid() {
		// TODO Auto-generated method stub
		return stationMapper.GetMaxKid();
	}
	
	public void UpdateStation(Station station) {
		// TODO Auto-generated method stub
		stationMapper.UpdateStation(station);	
		Map<String, Object> parameters=new HashMap<String,Object>(2);
		parameters.put("lineuid",station.getLineuid());
		parameters.put("stationuid", station.getUid());
		stationMapper.DeleteLineStation(parameters);
		stationMapper.InsertLineStation(station);
	}
	
	public void DeleteStation(String uid) {
		// TODO Auto-generated method stub
		stationMapper.DeleteStation(uid);
		
	}
	
		
	public boolean CreateStation(Station station) {
		// TODO Auto-generated method stub
		try {
			stationMapper.CreateStation(station);
			stationMapper.InsertLineStation(station);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

}
