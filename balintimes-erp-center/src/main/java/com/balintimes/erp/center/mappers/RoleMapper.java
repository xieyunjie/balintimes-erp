package com.balintimes.erp.center.mappers;

import java.util.List;

import com.balintimes.erp.center.model.Role;

public interface RoleMapper {
	List<Role> GetRoleList();
	
	List<Role> GetRoleListByNotForbidden();
	
	List<Role> GetUserRoles(String userName);
	
	Role GetRole(String uid);

	void CreateRole(Role role);

	void UpdateRole(Role role);

	void DeleteRole(String uid);

	void DeleteRoleByParentUid(String parentUid);
}
