package com.balintimes.erp.crm.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.balintimes.erp.crm.dao.AreaDao;
import com.balintimes.erp.crm.model.Area;
import com.balintimes.erp.crm.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService {

	@Resource
	private AreaDao areaInfoDao;

	public List<Area> getAreaInfoList(String name, String code) {
		// TODO Auto-generated method stub
		return this.areaInfoDao.getAreaInfoList(name, code);
	}

	public List<Area> getAreaInfoListByParnetUid(String parentUid) {
		// TODO Auto-generated method stub
		return this.areaInfoDao.getAreaInfoListByParnetUid(parentUid);
	}

	public Area getAreaInfo(String uid) {
		// TODO Auto-generated method stub
		return this.areaInfoDao.getAreaInfo(uid);
	}

	public void createAreaInfo(Area areaInfo) {
		// TODO Auto-generated method stub
		areaInfo.setUid(UUID.randomUUID().toString());
		this.areaInfoDao.createAreaInfo(areaInfo);
	}

	public void updateAreaInfo(Area areaInfo) {
		// TODO Auto-generated method stub
		this.areaInfoDao.updateAreaInfo(areaInfo);
	}

	public void deleteAreaInfo(String uid) {
		// TODO Auto-generated method stub
		this.areaInfoDao.deleteAreaInfo(uid);
	}

}
