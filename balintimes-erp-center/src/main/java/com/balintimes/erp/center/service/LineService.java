package com.balintimes.erp.center.service;

import java.util.List;
import java.util.Map;

import com.balintimes.erp.center.model.Line;

public interface LineService {
	List<Line> GetLineList();

	void UpdateLine(Line Line);

	void DeleteLine(String uid);

	boolean CreateLine(Line Line);
	
	List<Line> GetLineListByCondition(Map<String, Object> parameters);
	
	Line GetOneLine(String uid);
}
