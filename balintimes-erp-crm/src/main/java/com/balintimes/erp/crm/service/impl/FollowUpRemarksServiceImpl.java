package com.balintimes.erp.crm.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.balintimes.erp.crm.dao.FollowUpRemarksDao;
import com.balintimes.erp.crm.dao.RegRemarksDao;
import com.balintimes.erp.crm.model.FollowUpRemarks;
import com.balintimes.erp.crm.model.RegRemarks;
import com.balintimes.erp.crm.model.RemarksInfo;
import com.balintimes.erp.crm.service.FollowUpRemarksService;
import com.balintimes.erp.util.common.DateUtil;

@Service
public class FollowUpRemarksServiceImpl implements FollowUpRemarksService {

	@Resource
	private RegRemarksDao regRemarksDao;
	@Resource
	private FollowUpRemarksDao followUpRemarksDao;

	public RemarksInfo getRemark(int uid, boolean isReg) {
		// TODO Auto-generated method stub
		RemarksInfo remarks = new RemarksInfo();
		if (isReg) {
			FollowUpRemarks fur = this.followUpRemarksDao
					.getFollowUpRemarks(uid);
			remarks.setReg(true);
			remarks.setUid(fur.getUid());
			remarks.setFollowUpUid(fur.getFollowUpUid());
			remarks.setCustomerUid(fur.getCustomerUid());
			remarks.setUserUid(fur.getUserUid());
			remarks.setContract(fur.getContract());
			remarks.setMannerUid(fur.getMannerUid());
			remarks.setMannerName(fur.getMannerName());
			remarks.setPhone(fur.getPhone());
			remarks.setRemarks(fur.getRemarks());
			remarks.setFollowUpDate(DateUtil.dateToString(fur.getFollowUpDate()));
			remarks.setSummary(fur.getSummary());
			remarks.setPersons(fur.getPersons());
			remarks.setDeleted(fur.isDeleted());
			remarks.setCreateBy(fur.getCreateBy());
			remarks.setCreateTime(fur.getCreateTime());
			remarks.setEditBy(fur.getEditBy());
			remarks.setEditTime(fur.getEditTime());
		} else {
			RegRemarks rr = this.regRemarksDao.getRegRemarks(uid);
			remarks.setReg(false);
			remarks.setUid(rr.getUid());
			remarks.setCustomerUid(rr.getCustomerUid());
			remarks.setUserUid(rr.getUserUid());
			remarks.setContract(rr.getContract());
			remarks.setMannerUid(rr.getMannerUid());
			remarks.setMannerName(rr.getMannerName());
			remarks.setPhone(rr.getPhone());
			remarks.setRemarks(rr.getRemarks());
			remarks.setFollowUpDate(DateUtil.dateToString(rr.getFollowUpDate()));
			remarks.setSummary(rr.getSummary());
			remarks.setPersons(rr.getPersons());
			remarks.setDeleted(rr.isDeleted());
			remarks.setCreateBy(rr.getCreateBy());
			remarks.setCreateTime(rr.getCreateTime());
			remarks.setEditBy(rr.getEditBy());
			remarks.setEditTime(rr.getEditTime());
		}
		return remarks;
	}

	public void createRemarks(RemarksInfo remarks) {
		// TODO Auto-generated method stub
		Date d = DateUtil.stringToDate(remarks.getFollowUpDate());

		if (remarks.isReg()) {
			FollowUpRemarks fup = new FollowUpRemarks();
			fup.setFollowUpUid(remarks.getFollowUpUid());
			fup.setCustomerUid(remarks.getCustomerUid());
			fup.setUserUid(remarks.getUserUid());
			fup.setContract(remarks.getContract());
			fup.setMannerUid(remarks.getMannerUid());
			fup.setMannerName(remarks.getMannerName());
			fup.setPhone(remarks.getPhone());
			fup.setRemarks(remarks.getRemarks());
			fup.setFollowUpDate(d);
			fup.setSummary(remarks.getSummary());
			fup.setPersons(remarks.getPersons());
			fup.setDeleted(remarks.isDeleted());
			fup.setCreateBy(remarks.getCreateBy());

			this.followUpRemarksDao.createFollowUpRemarks(fup);
		} else {
			RegRemarks rr = new RegRemarks();
			rr.setCustomerUid(remarks.getCustomerUid());
			rr.setUserUid(remarks.getUserUid());
			rr.setContract(remarks.getContract());
			rr.setMannerUid(remarks.getMannerUid());
			rr.setMannerName(remarks.getMannerName());
			rr.setPhone(remarks.getPhone());
			rr.setRemarks(remarks.getRemarks());
			rr.setFollowUpDate(d);
			rr.setSummary(remarks.getSummary());
			rr.setPersons(remarks.getPersons());
			rr.setDeleted(remarks.isDeleted());
			rr.setCreateBy(remarks.getCreateBy());

			this.regRemarksDao.createRegRemarks(rr);
		}
	}

	public void deleteRemark(int uid, boolean isReg) {
		// TODO Auto-generated method stub
		if (isReg) {
			this.followUpRemarksDao.updateFollowUpRemarksByDel(uid);
		} else {
			this.regRemarksDao.updateRegRemarksByDel(uid);
		}
	}

	public void updateRemark(RemarksInfo remarks) {
		// TODO Auto-generated method stub
		Date d = DateUtil.stringToDate(remarks.getFollowUpDate());
		if (remarks.isReg()) {
			FollowUpRemarks fup = new FollowUpRemarks();
			fup.setUid(remarks.getUid());
			fup.setContract(remarks.getContract());
			fup.setMannerUid(remarks.getMannerUid());
			fup.setMannerName(remarks.getMannerName());
			fup.setPhone(remarks.getPhone());
			fup.setRemarks(remarks.getRemarks());
			fup.setFollowUpDate(d);
			fup.setSummary(remarks.getSummary());
			fup.setPersons(remarks.getPersons());
			fup.setEditBy(remarks.getEditBy());

			this.followUpRemarksDao.updateFollowUpRemarks(fup);
		} else {
			RegRemarks rr = new RegRemarks();
			rr.setUid(remarks.getUid());
			rr.setContract(remarks.getContract());
			rr.setMannerUid(remarks.getMannerUid());
			rr.setMannerName(remarks.getMannerName());
			rr.setPhone(remarks.getPhone());
			rr.setRemarks(remarks.getRemarks());
			rr.setFollowUpDate(d);
			rr.setSummary(remarks.getSummary());
			rr.setPersons(remarks.getPersons());
			rr.setEditBy(remarks.getEditBy());

			this.regRemarksDao.updateRegRemarks(rr);
		}
	}

}
