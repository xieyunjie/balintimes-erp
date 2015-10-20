package com.balintimes.erp.crm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.balintimes.erp.crm.dao.ContractDao;
import com.balintimes.erp.crm.dao.RegContractDao;
import com.balintimes.erp.crm.model.Contract;
import com.balintimes.erp.crm.model.ContractInfo;
import com.balintimes.erp.crm.model.RegContract;
import com.balintimes.erp.crm.service.ContractService;
import com.balintimes.erp.util.common.DateUtil;

@Service
public class ContractServiceImpl implements ContractService {

	@Resource
	private RegContractDao regContractDao;
	@Resource
	private ContractDao contractDao;

	public List<ContractInfo> getContractListByObj(int objUid, boolean isReg) {
		// TODO Auto-generated method stub
		List<ContractInfo> list = new ArrayList<ContractInfo>();
		if (isReg) {
			List<Contract> contracts = this.contractDao
					.getContractByFollowUp(objUid);
			for (Contract contract : contracts) {
				ContractInfo c = new ContractInfo();
				String bd = null;
				if (contract.getBirthday() != null) {
					bd = DateUtil.dateToString(contract.getBirthday());
				}
				c.setUid(contract.getUid());
				c.setFollowUpUid(contract.getFollowUpUid());
				c.setCustomerUid(contract.getCustomerUid());
				c.setCustomerName(contract.getCustomerName());
				c.setName(contract.getName());
				c.setSex(contract.isSex());
				c.setPhone(contract.getPhone());
				c.setQq(contract.getQq());
				c.setEmail(contract.getEmail());
				c.setAddress(contract.getAddress());
				c.setCardUrl(contract.getCardUrl());
				c.setDeleted(contract.isDeleted());
				c.setCreateBy(contract.getCreateBy());
				c.setCreateTime(contract.getCreateTime());
				c.setEditBy(contract.getEditBy());
				c.setEditTime(contract.getEditTime());
				c.setBirthday(bd);
				c.setBirthdayByDateTime(contract.getBirthday());
				c.setSocialUrl(contract.getSocialUrl());
				c.setRemarks(contract.getRemarks());
				c.setReg(isReg);

				list.add(c);
			}
		} else {
			List<RegContract> regContracts = this.regContractDao
					.getRegContractByCustomer(objUid);
			for (RegContract contract : regContracts) {
				String bd = null;
				if (contract.getBirthday() != null) {
					bd = DateUtil.dateToString(contract.getBirthday());
				}
				ContractInfo c = new ContractInfo();

				c.setUid(contract.getUid());
				c.setCustomerUid(contract.getCustomerUid());
				c.setCustomerName(contract.getCustomerName());
				c.setName(contract.getName());
				c.setSex(contract.isSex());
				c.setPhone(contract.getPhone());
				c.setQq(contract.getQq());
				c.setEmail(contract.getEmail());
				c.setAddress(contract.getAddress());
				c.setCardUrl(contract.getCardUrl());
				c.setDeleted(contract.isDeleted());
				c.setCreateBy(contract.getCreateBy());
				c.setCreateTime(contract.getCreateTime());
				c.setEditBy(contract.getEditBy());
				c.setEditTime(contract.getEditTime());
				c.setBirthday(bd);
				c.setBirthdayByDateTime(contract.getBirthday());
				c.setSocialUrl(contract.getSocialUrl());
				c.setRemarks(contract.getRemarks());
				c.setReg(isReg);

				list.add(c);
			}

		}
		return list;
	}

	public ContractInfo getContractInfo(int uid, boolean isReg) {
		// TODO Auto-generated method stub
		ContractInfo c = new ContractInfo();

		if (isReg) {
			Contract contract = this.contractDao.getContract(uid);
			String bd = null;
			if (contract.getBirthday() != null) {
				bd = DateUtil.dateToString(contract.getBirthday());
			}

			c.setUid(contract.getUid());
			c.setFollowUpUid(contract.getFollowUpUid());
			c.setCustomerUid(contract.getCustomerUid());
			c.setCustomerName(contract.getCustomerName());
			c.setName(contract.getName());
			c.setSex(contract.isSex());
			c.setPhone(contract.getPhone());
			c.setQq(contract.getQq());
			c.setEmail(contract.getEmail());
			c.setAddress(contract.getAddress());
			c.setCardUrl(contract.getCardUrl());
			c.setDeleted(contract.isDeleted());
			c.setCreateBy(contract.getCreateBy());
			c.setCreateTime(contract.getCreateTime());
			c.setEditBy(contract.getEditBy());
			c.setEditTime(contract.getEditTime());
			c.setBirthday(bd);
			c.setBirthdayByDateTime(contract.getBirthday());
			c.setSocialUrl(contract.getSocialUrl());
			c.setRemarks(contract.getRemarks());
			c.setReg(isReg);

		} else {
			RegContract contract = this.regContractDao.getRegContract(uid);
			String bd = null;
			if (contract.getBirthday() != null) {
				bd = DateUtil.dateToString(contract.getBirthday());
			}

			c.setUid(contract.getUid());
			c.setCustomerUid(contract.getCustomerUid());
			c.setCustomerName(contract.getCustomerName());
			c.setName(contract.getName());
			c.setSex(contract.isSex());
			c.setPhone(contract.getPhone());
			c.setQq(contract.getQq());
			c.setEmail(contract.getEmail());
			c.setAddress(contract.getAddress());
			c.setCardUrl(contract.getCardUrl());
			c.setDeleted(contract.isDeleted());
			c.setCreateBy(contract.getCreateBy());
			c.setCreateTime(contract.getCreateTime());
			c.setEditBy(contract.getEditBy());
			c.setEditTime(contract.getEditTime());
			c.setBirthday(bd);
			c.setBirthdayByDateTime(contract.getBirthday());
			c.setSocialUrl(contract.getSocialUrl());
			c.setRemarks(contract.getRemarks());
			c.setReg(isReg);

		}
		return c;
	}

	public void createContractInfo(ContractInfo contract) {
		// TODO Auto-generated method stub
		Date bd = null;
		if (contract.getBirthday() != null) {
			bd = DateUtil.stringToDate(contract.getBirthday());
		}
		if (contract.isReg()) {
			Contract c = new Contract();

			c.setFollowUpUid(contract.getFollowUpUid());
			c.setCustomerUid(contract.getCustomerUid());
			c.setCustomerName(contract.getCustomerName());
			c.setName(contract.getName());
			c.setSex(contract.isSex());
			c.setPhone(contract.getPhone());
			c.setQq(contract.getQq());
			c.setEmail(contract.getEmail());
			c.setAddress(contract.getAddress());
			c.setCardUrl(contract.getCardUrl());
			c.setDeleted(contract.isDeleted());
			c.setCreateBy(contract.getCreateBy());
			c.setBirthday(bd);
			c.setSocialUrl(contract.getSocialUrl());
			c.setRemarks(contract.getRemarks());

			this.contractDao.createContract(c);
		} else {
			RegContract c = new RegContract();

			c.setCustomerUid(contract.getCustomerUid());
			c.setCustomerName(contract.getCustomerName());
			c.setName(contract.getName());
			c.setSex(contract.isSex());
			c.setPhone(contract.getPhone());
			c.setQq(contract.getQq());
			c.setEmail(contract.getEmail());
			c.setAddress(contract.getAddress());
			c.setCardUrl(contract.getCardUrl());
			c.setDeleted(contract.isDeleted());
			c.setCreateBy(contract.getCreateBy());
			c.setBirthday(bd);
			c.setSocialUrl(contract.getSocialUrl());
			c.setRemarks(contract.getRemarks());

			this.regContractDao.createRegContract(c);
		}
	}

	public void updateContractInfo(ContractInfo contract) {
		// TODO Auto-generated method stub
		Date bd = null;
		if (contract.getBirthday() != null) {
			bd = DateUtil.stringToDate(contract.getBirthday());
		}
		if (contract.isReg()) {
			Contract c = new Contract();
			
			c.setUid(contract.getUid());
			c.setName(contract.getName());
			c.setSex(contract.isSex());
			c.setPhone(contract.getPhone());
			c.setQq(contract.getQq());
			c.setEmail(contract.getEmail());
			c.setAddress(contract.getAddress());
			c.setCardUrl(contract.getCardUrl());
			c.setEditBy(contract.getEditBy());
			c.setBirthday(bd);
			c.setSocialUrl(contract.getSocialUrl());
			c.setRemarks(contract.getRemarks());

			this.contractDao.updateContract(c);
		} else {
			RegContract c = new RegContract();
			
			c.setUid(contract.getUid());
			c.setName(contract.getName());
			c.setSex(contract.isSex());
			c.setPhone(contract.getPhone());
			c.setQq(contract.getQq());
			c.setEmail(contract.getEmail());
			c.setAddress(contract.getAddress());
			c.setCardUrl(contract.getCardUrl());
			c.setEditBy(contract.getEditBy());
			c.setBirthday(bd);
			c.setSocialUrl(contract.getSocialUrl());
			c.setRemarks(contract.getRemarks());

			this.regContractDao.updateRegContract(c);
		}
	}

	public void deleteContractInfo(int uid, boolean isReg) {
		// TODO Auto-generated method stub
		if (isReg) {
			this.contractDao.updateContractByDel(uid);
		} else {
			this.regContractDao.updateRegContractByDel(uid);
		}
	}

}
