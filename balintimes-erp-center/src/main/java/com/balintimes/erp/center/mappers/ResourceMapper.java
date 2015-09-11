package com.balintimes.erp.center.mappers;

import java.util.List;
import java.util.Map;

import com.balintimes.erp.center.model.Resource;

public interface ResourceMapper {
	List<Resource> GetResourceList(String appUid);

	List<Resource> GetResourceListByNotForbidden(String appUid);

	List<Resource> GetUserMenu(Map<String, Object> map);

	List<Resource> GetUserPermissions(Map<String, Object> map);

	List<Resource> GetUserMenuPermissions(Map<String, Object> map);

	List<Resource> GetUserDisableMenus(Map<String, Object> map);

	int ExistsStateByResource(String state);

	Resource GetResource(String uid);

	void CreateResourceInfo(Resource resource);

	void UpdateResourceInfo(Resource resource);

	void DeleteResourceInfo(String uid);

	void DeleteResourceByParentUid(String parentUid);
}
