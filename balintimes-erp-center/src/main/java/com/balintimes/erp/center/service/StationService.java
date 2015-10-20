package com.balintimes.erp.center.service;

import java.util.List;
import java.util.Map;

import com.balintimes.erp.center.model.Station;

public interface StationService {
	List<Station> GetStationList();

	List<Station> GetStationListByCondition(Map<String, Object> params);

	Station GetOneStation(String uid);

	String GetMaxKid();

	void UpdateStation(Station station);
	
	void DeleteStation(String uid);
	
	boolean CreateStation(Station station);
}
