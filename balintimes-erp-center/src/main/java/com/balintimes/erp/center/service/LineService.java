package com.balintimes.erp.center.service;

import java.util.List;

import com.balintimes.erp.center.model.Line;

public interface LineService {
	List<Line> GetLineList();

	void UpdateLine(Line Line);

	void DeleteLine(String uid);

	boolean CreateLine(Line Line);
	
	List<Line> GetLineListByCondition(String name);
	
	Line GetOneLine(String uid);
}
