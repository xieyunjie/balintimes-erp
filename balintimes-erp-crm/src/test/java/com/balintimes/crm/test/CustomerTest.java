package com.balintimes.crm.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.balintimes.erp.crm.dao.CustomerProcDao;
import com.balintimes.erp.crm.model.EmpCustomer;
import com.balintimes.erp.util.tuples.TuplePage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class CustomerTest {

	@Resource
	private CustomerProcDao customerProcDao;

	@Test
	public void test() {
		TuplePage<List<EmpCustomer>, Integer> page = this.customerProcDao
				.getCustomerByEmp("", "59e2be4d-23b7-11e5-970b-c86000a05d5f",
						"", -1, null, 20, 1);
		System.out.println(page.objectTotalCount);
		for (EmpCustomer item : page.objectList) {
			System.out.println(item.getName());
		}
	}
}
