package com.balintimes.erp.center.dao;

import java.util.List;

import com.balintimes.erp.center.model.UserRoles;

public interface UserRolesDao {
	List<UserRoles> GetUserRolesListByUser(String userUid);
	
	void CreateUserRoles(UserRoles userRoles);
	
	void DeleteUserRoleByUserAndRole(String userUid, String roleUid);
	
	void DeleteUserRoleByUser(String userUid);
	
	void DeleteUserRoleByRole(String roleUid);
}
