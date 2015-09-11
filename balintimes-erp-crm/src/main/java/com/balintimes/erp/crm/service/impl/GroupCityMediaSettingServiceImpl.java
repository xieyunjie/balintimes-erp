package com.balintimes.erp.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.balintimes.erp.crm.dao.GroupCityMediaSettingDao;
import com.balintimes.erp.crm.model.GroupCityMediaSetting;
import com.balintimes.erp.crm.service.GroupCityMediaSettingService;

@Service
public class GroupCityMediaSettingServiceImpl implements
		GroupCityMediaSettingService {

	@Resource
	private GroupCityMediaSettingDao groupCityMediaSettingDao;

	public List<GroupCityMediaSetting> getGroupCityMediaList(String groupUid) {
		// TODO Auto-generated method stub
		return this.groupCityMediaSettingDao.getGroupCityMediaList(groupUid);
	}

	public void createGroupCityMedia(GroupCityMediaSetting groupCityMediaSetting) {
		// TODO Auto-generated method stub
		this.groupCityMediaSettingDao
				.createGroupCityMedia(groupCityMediaSetting);
	}

	public void deleteGroupCityMedia(String uid) {
		// TODO Auto-generated method stub
		this.groupCityMediaSettingDao.deleteGroupCityMedia(uid);
	}

	public void deleteGroupCityMediaByGroup(String groupUid) {
		// TODO Auto-generated method stub
		this.groupCityMediaSettingDao.deleteGroupCityMediaByGroup(groupUid);
	}

}
