package com.balintimes.erp.center.dao;

import java.util.List;

import com.balintimes.erp.center.model.Line;

public interface LineDao {
	List<Line> GetLineList();

	void UpdateLine(Line Line);

	void DeleteLine(String uid);

	boolean CreateLine(Line Line);
	
	List<Line> GetLineListByCondition(String name);
	
	Line GetOneLine(String uid);
}
