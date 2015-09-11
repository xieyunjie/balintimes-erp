package com.balintimes.erp.crm.dao.batisimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.balintimes.erp.crm.dao.GroupCityMediaSettingDao;
import com.balintimes.erp.crm.mappers.GroupCityMediaSettingMapper;
import com.balintimes.erp.crm.model.GroupCityMediaSetting;

@Repository
public class GroupCityMediaSettingDaoImpl implements GroupCityMediaSettingDao {

	@Resource
	private GroupCityMediaSettingMapper groupCityMediaSettingMapper;

	public List<GroupCityMediaSetting> getGroupCityMediaList(String groupUid) {
		// TODO Auto-generated method stub
		return this.groupCityMediaSettingMapper.getGroupCityMediaList(groupUid);
	}

	public void createGroupCityMedia(GroupCityMediaSetting groupCityMediaSetting) {
		// TODO Auto-generated method stub
		this.groupCityMediaSettingMapper
				.createGroupCityMedia(groupCityMediaSetting);
	}

	public void deleteGroupCityMedia(String uid) {
		// TODO Auto-generated method stub
		this.groupCityMediaSettingMapper.deleteGroupCityMedia(uid);
	}

	public void deleteGroupCityMediaByGroup(String groupUid) {
		// TODO Auto-generated method stub
		this.groupCityMediaSettingMapper.deleteGroupCityMediaByGroup(groupUid);
	}

}
