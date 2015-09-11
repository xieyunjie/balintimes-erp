package com.balintimes.erp.crm.mappers;

import java.util.List;

import com.balintimes.erp.crm.model.GroupCityMediaSetting;

public interface GroupCityMediaSettingMapper {
	List<GroupCityMediaSetting> getGroupCityMediaList(String groupUid);

	void createGroupCityMedia(GroupCityMediaSetting groupCityMediaSetting);

	void deleteGroupCityMedia(String uid);

	void deleteGroupCityMediaByGroup(String groupUid);
}
