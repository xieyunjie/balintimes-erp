package com.balintimes.erp.crm.dao.batisimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.balintimes.erp.crm.dao.CustomerProcDao;
import com.balintimes.erp.crm.mappers.CustomerProcMapper;
import com.balintimes.erp.crm.model.EmpCustomer;
import com.balintimes.erp.util.tuples.TuplePage;

@Repository
public class CustomerProcDaoImpl implements CustomerProcDao {

	@Resource
	private CustomerProcMapper customerProcMapper;

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

}
