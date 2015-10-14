package com.balintimes.erp.center.mappers;

import java.util.List;
import java.util.Map;

import com.balintimes.erp.center.model.Line;

public interface LineMapper {

	List<Line> GetLineList();

//	List<Line> GetLineListByPage(Map<String, Object> params);

	void UpdateLine(Line Line);

//	void UpdateLine(Map<String, Object> params);

	void DeleteLine(String uid);

	void CreateLine(Line Line);
	
	Line GetOneLine(String uid);
	
	List<Line> GetLineListByCondition(Map<String, Object> params);
	
	String GetMaxKid();
}
