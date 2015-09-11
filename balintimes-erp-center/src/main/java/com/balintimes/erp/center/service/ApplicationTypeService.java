package com.balintimes.erp.center.service;

import java.util.List;

import com.balintimes.erp.center.model.ApplicationType;

public interface ApplicationTypeService {
	List<ApplicationType> getApplicationTypeList(String name);
	
	ApplicationType getApplicationType(String uid);
	
	void createApplicationType(ApplicationType applicationType);
	
	void updateApplicationType(ApplicationType applicationType);
	
	void deleteApplicationType(String uid);
}
