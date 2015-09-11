package com.balintimes.erp.center.controller;

//import java.awt.List;

//import com.balintimes.erp.center.annotation.CustomerTransactional;
//import java.com.balintimes.erp.center.util.ArrayList;
//import java.com.balintimes.erp.center.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
//import javax.swing.JApplet;

import com.balintimes.erp.center.model.City;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.balintimes.erp.center.service.CityService;
import com.balintimes.erp.center.util.JsonUtil;
import com.balintimes.erp.center.base.BaseController;
//import java.com.balintimes.erp.center.util.jar.JarEntry;

/**
 * Created by AlexXie on 2015/7/14.
 */
@Controller
@RequestMapping("city")
public class CityController extends BaseController {

    @Resource
    private CityService cityService;

    @RequestMapping("list")
    @ResponseBody
    public String CityList() {        
    	List<City> resultList=this.cityService.GetCityList();    	
        return JsonUtil.ResponseSuccessfulMessage(resultList,resultList.size());

    }
    
    @RequestMapping(value = "listByPage", method = RequestMethod.POST)
    @ResponseBody
    public String GetCityListByPage(String cityName,int page,int pageSize){    	    	
    	City searchCity=new City();
    	searchCity.setName(cityName);
    	com.balintimes.erp.center.tuples.TuplePage<List<City>, Integer> listCity = this.cityService.GetCityListByPage(searchCity, 1, 100);
    	return JsonUtil.ResponseSuccessfulMessage(listCity.objectList,listCity.objectTotalCount);
    }
    
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public String UpdateCity(City city){    	
    	try {
    		this.cityService.UpdateCity(city);
    		return JsonUtil.ResponseSuccessfulMessage("保存成功");
		} catch (Exception e) {
			// TODO: handle com.balintimes.erp.center.exception
			return JsonUtil.ResponseSuccessfulMessage("保存失败");
		}
        
    }
    
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public String DeleteCity(String UID)
    {    	
    	try {
    		this.cityService.DeleteCity(UID);
    		return JsonUtil.ResponseSuccessfulMessage("删除成功");
		} catch (Exception e) {
			// TODO: handle com.balintimes.erp.center.exception
			return JsonUtil.ResponseSuccessfulMessage("删除失败");
		}
    	
    }
    
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public String CreateCity(City city){    	
    	City insertCityModel=city;
    	insertCityModel.setUid(UUID.randomUUID().toString());   
    	try {
    		boolean isSuccess=this.cityService.CreateCity(city);
    		if(isSuccess)
    			return JsonUtil.ResponseSuccessfulMessage("添加成功");
    		else {				
    			return JsonUtil.ResponseSuccessfulMessage("添加失败");
			}
		} catch (Exception e) {
			// TODO: handle com.balintimes.erp.center.exception
			return JsonUtil.ResponseSuccessfulMessage("添加失败，出错请检查");
		}
    	
    	   	
    }

	@RequestMapping(value = "/getCity/{uid}")
	@ResponseBody
	public String GetCity(@PathVariable String uid) {
		City city = new City();
		city.setUid(uid);
		com.balintimes.erp.center.tuples.TuplePage<List<City>, Integer> listCity = this.cityService.GetCityListByPage(city, 1, 100);
		City resultCity = new City();
		if (uid.equals("0")) {
			resultCity = new City();
		} else {
			resultCity = listCity.objectList.get(0);
		}
    	     	
        return JsonUtil.ResponseSuccessfulMessage(resultCity);
    }
}
