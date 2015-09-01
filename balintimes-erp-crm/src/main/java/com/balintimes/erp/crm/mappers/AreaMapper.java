package com.balintimes.erp.crm.mappers;

import java.util.List;
import java.util.Map;

import com.balintimes.erp.crm.model.Area;

public interface AreaMapper {
	List<Area> getAreaInfoList(Map<String, Object> map);

	List<Area> getAreaInfoListByParnetUid(String parentUid);
	
	Area getAreaInfo(String uid);

	void createAreaInfo(Area areaInfo);

	void updateAreaInfo(Area areaInfo);

	void deleteAreaInfo(String uid);
}
