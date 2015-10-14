package com.balintimes.erp.crm.service;

import java.util.List;

import com.balintimes.erp.crm.model.Area;

public interface AreaService {
	List<Area> getAreaInfoList(String name, String code);

	List<Area> getAreaInfoListByParnetUid(String parentUid);

	Area getAreaInfo(String uid);

	void createAreaInfo(Area areaInfo);

	void updateAreaInfo(Area areaInfo);

	void deleteAreaInfo(String uid);

	void updateAndaudit();
}
