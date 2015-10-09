package com.balintimes.erp.crm.dao.batisimpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.balintimes.erp.crm.dao.CustomerProcDao;
import com.balintimes.erp.crm.mappers.CustomerProcMapper;
import com.balintimes.erp.crm.mappers.RemarksProcMapper;
import com.balintimes.erp.crm.model.EmpCustomer;
import com.balintimes.erp.crm.model.RemarksInfo;
import com.balintimes.erp.util.tuples.TuplePage;

@Repository
public class CustomerProcDaoImpl implements CustomerProcDao {

	@Resource
	private CustomerProcMapper customerProcMapper;
	@Resource
	private RemarksProcMapper remarksProcMapper;

	public TuplePage<List<EmpCustomer>, Integer> getCustomerByEmp(String name,
			String userUids, String businessTypeUid, int isReg, String brand,
			int pageSize, int currPage) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("userUids", userUids);
		map.put("businessTypeUid", businessTypeUid);
		map.put("isReg", isReg);
		map.put("brand", brand);
		map.put("pageSize", pageSize);
		map.put("currPage", currPage);

		List<EmpCustomer> list = this.customerProcMapper.getCustomerByEmp(map);
		return new TuplePage<List<EmpCustomer>, Integer>(list,
				(int) map.get("totalCount"));
	}

	public TuplePage<List<RemarksInfo>, Integer> getRemarksByEmp(
			String customerName, String brand, String userUids,
			Integer mannerUid, Date beginDate, Date endDate, int pageSize,
			int currPage) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("customerName", customerName);
		map.put("brand", brand);
		map.put("userUids", userUids);
		map.put("mannerUid", mannerUid == null ? -1 : mannerUid);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		map.put("pageSize", pageSize);
		map.put("currPage", currPage);

		List<RemarksInfo> list = this.remarksProcMapper.getRemarksByEmp(map);
		int totalcount = 0;
		if (map.get("totalCount") != null) {
			totalcount = (int) map.get("totalCount");
		}
		return new TuplePage<List<RemarksInfo>, Integer>(list, totalcount);
	}

}
