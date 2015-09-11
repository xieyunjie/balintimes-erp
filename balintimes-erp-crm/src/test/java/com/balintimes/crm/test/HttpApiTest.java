package com.balintimes.crm.test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.balintimes.erp.util.webapi.HttpApiUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class HttpApiTest {
	
	@Test
	public void getTest(){
		String url = "http://localhost:8080/oaucenter/ws/basedata/businesstypelist";
		try {
			String str = HttpApiUtil.get(url);
			System.out.println(str);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
