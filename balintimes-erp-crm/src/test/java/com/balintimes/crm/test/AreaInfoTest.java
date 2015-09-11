package com.balintimes.crm.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.balintimes.erp.crm.model.Area;
import com.balintimes.erp.crm.service.AreaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class AreaInfoTest {

	@Resource
	private AreaService areaInfoService;

	@Test
	public void GetList() {
		List<Area> list = this.areaInfoService.getAreaInfoList("", "020");
		for (Area item : list) {
			System.out.println(item.getName());
		}
	}
}
