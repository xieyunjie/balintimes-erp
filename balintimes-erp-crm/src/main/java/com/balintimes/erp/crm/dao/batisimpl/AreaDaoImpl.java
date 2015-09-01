package com.balintimes.erp.crm.dao.batisimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.balintimes.erp.crm.dao.AreaDao;
import com.balintimes.erp.crm.mappers.AreaMapper;
import com.balintimes.erp.crm.model.Area;

@Repository
public class AreaDaoImpl implements AreaDao {

	@Resource
	private AreaMapper areaInfoMapper;

	public List<Area> getAreaInfoListByParnetUid(String parentUid) {
		// TODO Auto-generated method stub
		return this.areaInfoMapper.getAreaInfoListByParnetUid(parentUid);
	}

	public List<Area> getAreaInfoList(String name, String code) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("code", code);
		return this.areaInfoMapper.getAreaInfoList(map);
	}

	public Area getAreaInfo(String uid) {
		// TODO Auto-generated method stub
		return this.areaInfoMapper.getAreaInfo(uid);
	}

	public void createAreaInfo(Area areaInfo) {
		// TODO Auto-generated method stub
		this.areaInfoMapper.createAreaInfo(areaInfo);
	}

	public void updateAreaInfo(Area areaInfo) {
		// TODO Auto-generated method stub
		this.areaInfoMapper.updateAreaInfo(areaInfo);
	}

	public void deleteAreaInfo(String uid) {
		// TODO Auto-generated method stub
		this.areaInfoMapper.deleteAreaInfo(uid);
	}

}
