package com.balintimes.erp.crm.dao.batisimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.balintimes.erp.crm.dao.CityMediaDao;
import com.balintimes.erp.crm.mappers.CityMediaMapper;
import com.balintimes.erp.crm.model.CityMedia;

@Repository
public class CityMediaDaoImpl implements CityMediaDao {

	@Resource
	private CityMediaMapper cityMediaMapper;

	public List<CityMedia> getCityMediaListByCity(String cityUid) {
		// TODO Auto-generated method stub
		return this.cityMediaMapper.getCityMediaListByCity(cityUid);
	}

	public CityMedia getCityMedia(String uid) {
		// TODO Auto-generated method stub
		return this.cityMediaMapper.getCityMedia(uid);
	}

	public void createCityMedia(CityMedia cityMedia) {
		// TODO Auto-generated method stub
		this.cityMediaMapper.createCityMedia(cityMedia);
	}

	public void deleteCityMedia(String uid) {
		// TODO Auto-generated method stub
		this.cityMediaMapper.deleteCityMedia(uid);
	}

	public void deleteCityMediaByCity(String cityUid) {
		// TODO Auto-generated method stub
		this.cityMediaMapper.deleteCityMediaByCity(cityUid);
	}

}
