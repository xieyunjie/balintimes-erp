package com.balintimes.erp.center.mappers;

import java.util.List;
import java.util.Map;

import com.balintimes.erp.center.model.Station;

public interface StationMapper {
	List<Station> GetStationList();

	List<Station> GetStationListByCondition(Map<String, Object> params);

	Station GetOneStation(String uid);

	String GetMaxKid();

	void UpdateStation(Station station);
	
	void DeleteStation(String uid);
	
	void DeleteLineStation(Map<String, Object> params);
	
	boolean InsertLineStation(Station station);
	
	boolean CreateStation(Station station);
}
