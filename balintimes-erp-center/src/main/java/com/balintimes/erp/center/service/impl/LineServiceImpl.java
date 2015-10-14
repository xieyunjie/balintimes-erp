/**
 * 
 */
package com.balintimes.erp.center.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.balintimes.erp.center.annotation.CustomerTransactional;
import com.balintimes.erp.center.dao.LineDao;
import com.balintimes.erp.center.model.Line;
import com.balintimes.erp.center.service.LineService;

/**
 * @author Administrator
 *
 */
@Service("lineservice")
public class LineServiceImpl implements LineService {
    @Resource
	private LineDao lineDao;
	/* (non-Javadoc)
	 * @see com.balintimes.erp.center.service.LineService#GetLineList()
	 */	
	public List<Line> GetLineList() {
		// TODO Auto-generated method stub
		return this.lineDao.GetLineList();
	}

	/* (non-Javadoc)
	 * @see com.balintimes.erp.center.service.LineService#UpdateLine(com.balintimes.erp.center.model.Line)
	 */
	@CustomerTransactional
	public void UpdateLine(Line Line) {
		// TODO Auto-generated method stub
		this.lineDao.UpdateLine(Line);
	}

	/* (non-Javadoc)
	 * @see com.balintimes.erp.center.service.LineService#DeleteLine(java.lang.String)
	 */
	@CustomerTransactional
	public void DeleteLine(String uid) {
		// TODO Auto-generated method stub
		this.lineDao.DeleteLine(uid);
	}

	/* (non-Javadoc)
	 * @see com.balintimes.erp.center.service.LineService#CreateLine(com.balintimes.erp.center.model.Line)
	 */
	@CustomerTransactional
	public boolean CreateLine(Line Line) {
		// TODO Auto-generated method stub
		return this.lineDao.CreateLine(Line);
	}

	
	public List<Line> GetLineListByCondition(String name) {
		// TODO Auto-generated method stub
		return this.lineDao.GetLineListByCondition(name);
	}
	
	public Line GetOneLine(String uid) {
		// TODO Auto-generated method stub
		return lineDao.GetOneLine(uid);
	}

}
