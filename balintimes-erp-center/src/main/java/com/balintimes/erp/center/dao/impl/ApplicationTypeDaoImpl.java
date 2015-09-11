package com.balintimes.erp.center.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.balintimes.erp.center.mappers.ApplicationTypeMapper;
import com.balintimes.erp.center.model.ApplicationType;
import com.balintimes.erp.center.dao.ApplicationTypeDao;

@Repository("applicationTypeDao")
public class ApplicationTypeDaoImpl implements ApplicationTypeDao {
	
    @Resource
    private ApplicationTypeMapper applicationTypeMapper;

    public List<ApplicationType> getApplicationTypeList(String name){
    	if(name==null || name.equals(""))
    		name="";
    	String p="%"+name+"%";
    	return this.applicationTypeMapper.getApplicationTypeList(p);
    }
    
    public ApplicationType getApplicationType(String uid){
    	return this.applicationTypeMapper.getApplicationType(uid);
    }

	public void createApplicationType(ApplicationType applicationType) {
		// TODO Auto-generated method stub
		this.applicationTypeMapper.createApplicationType(applicationType);
	}

	public void updateApplicationType(ApplicationType applicationType) {
		// TODO Auto-generated method stub
		this.applicationTypeMapper.updateApplicationType(applicationType);
	}

	public void deleteApplicationType(String uid) {
		// TODO Auto-generated method stub
		this.applicationTypeMapper.deleteApplicationType(uid);
	}
}
