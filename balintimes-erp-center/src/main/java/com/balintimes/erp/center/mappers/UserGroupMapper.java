package com.balintimes.erp.center.mappers;

import java.util.List;

import com.balintimes.erp.center.model.UserGroup;

public interface UserGroupMapper {
	List<UserGroup> GetUserGroupList(String name);

	UserGroup GetUserGroup(String uid);

	void CreateUserGroup(UserGroup userGroup);

	void UpdateUserGroup(UserGroup userGroup);

	void DeleteUserGroup(String uid);
}
