package com.balintimes.erp.crm.mappers;

import java.util.List;
import java.util.Map;

import com.balintimes.erp.crm.model.RemarksInfo;

public interface RemarksProcMapper {
	List<RemarksInfo> getRemarksByEmp(Map<String, Object> map);
}
