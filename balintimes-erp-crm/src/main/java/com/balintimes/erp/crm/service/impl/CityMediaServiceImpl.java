package com.balintimes.erp.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.balintimes.erp.crm.dao.CityMediaDao;
import com.balintimes.erp.crm.model.CityMedia;
import com.balintimes.erp.crm.service.CityMediaService;

@Service
public class CityMediaServiceImpl implements CityMediaService {

	@Resource
	private CityMediaDao cityMediaDao;

	public List<CityMedia> getCityMediaListByCity(String cityUid) {
		// TODO Auto-generated method stub
		return this.cityMediaDao.getCityMediaListByCity(cityUid);
	}

	public CityMedia getCityMedia(String uid) {
		// TODO Auto-generated method stub
		return this.cityMediaDao.getCityMedia(uid);
	}

	public void createCityMedia(CityMedia cityMedia) {
		// TODO Auto-generated method stub
		this.cityMediaDao.createCityMedia(cityMedia);
	}

	public void deleteCityMedia(String uid) {
		// TODO Auto-generated method stub
		this.cityMediaDao.deleteCityMedia(uid);
	}

	public void deleteCityMediaByCity(String cityUid) {
		// TODO Auto-generated method stub
		this.cityMediaDao.deleteCityMediaByCity(cityUid);
	}

}
