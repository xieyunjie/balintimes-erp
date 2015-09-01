package com.balintimes.erp.crm.dao;

import java.util.List;

import com.balintimes.erp.crm.model.Area;

public interface AreaDao {

	List<Area> getAreaInfoList(String name, String code);

	List<Area> getAreaInfoListByParnetUid(String parentUid);

	Area getAreaInfo(String uid);

	void createAreaInfo(Area areaInfo);

	void updateAreaInfo(Area areaInfo);

	void deleteAreaInfo(String uid);
}
