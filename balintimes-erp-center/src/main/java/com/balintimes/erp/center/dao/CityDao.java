package com.balintimes.erp.center.dao;

import java.util.List;

import com.balintimes.erp.center.model.City;
import com.balintimes.erp.center.tuples.TuplePage;

/**
 * Created by AlexXie on 2015/7/14.
 */
public interface CityDao {

    List<City> GetCityList();
    
    TuplePage<List<City>, Integer> GetCityListByPage(City city, int page, int pageSize);
    
    void UpdateCity(City city);

    void DeleteCity(String uid);
    
    boolean CreateCity(City city);
}
