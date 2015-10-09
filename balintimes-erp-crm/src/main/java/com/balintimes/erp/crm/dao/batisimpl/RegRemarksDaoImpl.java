package com.balintimes.erp.crm.dao.batisimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.balintimes.erp.crm.dao.RegRemarksDao;
import com.balintimes.erp.crm.mappers.RegRemarksMapper;
import com.balintimes.erp.crm.model.RegRemarks;

@Repository
public class RegRemarksDaoImpl implements RegRemarksDao {

	@Resource
	private RegRemarksMapper regRemarksMapper;

	public RegRemarks getRegRemarks(int uid) {
		// TODO Auto-generated method stub
		return this.regRemarksMapper.getRegRemarks(uid);
	}

	public void createRegRemarks(RegRemarks regRemarks) {
		// TODO Auto-generated method stub
		this.regRemarksMapper.createRegRemarks(regRemarks);
	}

	public void updateRegRemarksByDel(int uid) {
		// TODO Auto-generated method stub
		this.regRemarksMapper.updateRegRemarksByDel(uid);
	}

	public void updateRegRemarks(RegRemarks regRemarks) {
		// TODO Auto-generated method stub
		this.regRemarksMapper.updateRegRemarks(regRemarks);
	}

}
