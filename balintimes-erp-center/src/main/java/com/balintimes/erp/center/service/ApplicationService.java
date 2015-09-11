package com.balintimes.erp.center.service;

import java.util.List;

import com.balintimes.erp.center.model.Application;

public interface ApplicationService {
	List<Application> GetApplicationList(String name, String typeUid, int showInMenu, int forbidden);
	
	List<Application> GetApplicationListByNotForbidden();
	
	Application GetApplication(String uid);
	
	void CreateApplication(Application application);
	
	void UpdateApplication(Application application);
	
	void DeleteApplication(String uid);
}
