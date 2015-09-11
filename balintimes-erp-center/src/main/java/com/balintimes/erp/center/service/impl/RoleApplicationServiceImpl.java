package com.balintimes.erp.center.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.balintimes.erp.center.annotation.CustomerTransactional;
import com.balintimes.erp.center.dao.RoleApplicationDao;
import com.balintimes.erp.center.model.RoleApplication;
import com.balintimes.erp.center.service.RoleApplicationService;

@Service
public class RoleApplicationServiceImpl implements RoleApplicationService {

	@Resource
	private RoleApplicationDao roleApplicationDao;

	public List<RoleApplication> GetRoleApplicationListByRole(String roleUid) {
		// TODO Auto-generated method stub
		return this.roleApplicationDao.GetRoleApplicationListByRole(roleUid);
	}

	@CustomerTransactional
	public void SaveRoleApplicationSetting(String roleUid, String appUid,
			boolean checked) {
		// TODO Auto-generated method stub		
		if(checked==true){
			RoleApplication ra=new RoleApplication();
			ra.setUid(UUID.randomUUID().toString());
			ra.setAppUid(appUid);
			ra.setRoleUid(roleUid);
			
			this.roleApplicationDao.CreateRoleApplication(ra);
		}else{
			this.roleApplicationDao.DeleteRoleApplicationByRoleAndApp(roleUid, appUid);
		}
	}

}
