package com.balintimes.erp.crm.dao;

import java.util.List;

import com.balintimes.erp.crm.model.CityMedia;

public interface CityMediaDao {
	List<CityMedia> getCityMediaListByCity(String cityUid);

	CityMedia getCityMedia(String uid);

	void createCityMedia(CityMedia cityMedia);

	void deleteCityMedia(String uid);

	void deleteCityMediaByCity(String cityUid);
}
