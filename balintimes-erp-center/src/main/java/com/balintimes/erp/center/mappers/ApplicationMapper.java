package com.balintimes.erp.center.mappers;

import java.util.List;
import java.util.Map;

import com.balintimes.erp.center.model.Application;

public interface ApplicationMapper {
	List<Application> GetApplicationList(Map<String, Object> params);
	
	List<Application> GetApplicationListByNotForbidden();
	
	List<Application> GetUserApplications(String userName);
	
	Application GetApplication(String uid);
	
	void CreateApplication(Application application);
	
	void UpdateApplication(Application application);
	
	void DeleteApplication(String uid);
}
