package com.balintimes.erp.center.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.balintimes.erp.center.dao.LineDao;
import com.balintimes.erp.center.mappers.LineMapper;
import com.balintimes.erp.center.model.Line;

@Repository("linedao")
public class LineDaoImpl implements LineDao {

	@Resource
	private LineMapper lineMapper;

	public List<Line> GetLineList() {		
		return this.lineMapper.GetLineList();
	}

	public void UpdateLine(Line Line) {
		this.lineMapper.UpdateLine(Line);

	}

	public void DeleteLine(String uid) {
		this.lineMapper.DeleteLine(uid);
	}

	public boolean CreateLine(Line line) {
		try {
			String kid=this.lineMapper.GetMaxKid();
			line.setKid(kid);
			this.lineMapper.CreateLine(line);
			return true;
		} catch (Exception e) {
			return false;
		}		
	}

	
	public List<Line> GetLineListByCondition(Map<String, Object> parameters) {
		Map<String, Object> params = new HashMap<String, Object>(1);
		params=parameters;
		return this.lineMapper.GetLineListByCondition(params);
	}

	
	public Line GetOneLine(String uid) {
		// TODO Auto-generated method stub
		return this.lineMapper.GetOneLine(uid);
	}

}
