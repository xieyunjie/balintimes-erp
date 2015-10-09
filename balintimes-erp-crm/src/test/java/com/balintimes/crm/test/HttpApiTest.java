package com.balintimes.crm.test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import com.balintimes.erp.model.crm.BusinessType;
import com.balintimes.erp.util.json.JsonUtil;
import com.balintimes.erp.util.json.ResponseMessage;
import com.balintimes.erp.util.webapi.HttpApiUtil;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.balintimes.erp.util.json.AjaxResponse;
import com.balintimes.erp.util.json.JsonUtil;
import com.balintimes.erp.util.webapi.HttpApiUtil;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;


//import com.balintimes.erp.util.webapi.HttpApiUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class HttpApiTest {

	
	@Test
	public void getTest(){
		String url = "http://localhost:8080/balintimes-erp-center/ws/basedata/businesstypelist";
		try {
			String str =(new HttpApiUtil()).get(url);
			System.out.println("----------------------------------------------------------------------------------------------------------------");
			System.out.println(str);
			List<BusinessType> bus=new ArrayList<BusinessType>();
			AjaxResponse aj=JsonUtil.ToObject(str, AjaxResponse.class);
			
			List<LinkedHashMap<String, Object>> list=(List<LinkedHashMap<String, Object>>)aj.getData();
			
			for (LinkedHashMap<String, Object> item : list) {
				Iterator<Entry<String, Object>> entries = item.entrySet()
						.iterator();
				BusinessType bt=new BusinessType();
				while (entries.hasNext()) {
					Map.Entry<String, Object> entry = (Map.Entry<String, Object>) entries
							.next();
					String key = entry.getKey();
					Object value = entry.getValue();
					//System.out.println(key+":"+value.toString());
					
					Field[] fields=bt.getClass().getDeclaredFields();
					for (Field it : fields) {
						it.setAccessible(true);
						if(it.getName().equals(key)){
							try {
								it.set(bt, value);
							} catch (IllegalArgumentException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
				bus.add(bt);
			}
			
			for (BusinessType b : bus) {
				System.out.println(b.getName()+":"+b.getCode());
			}
			System.out.println("----------------------------------------------------------------------------------------------------------------");
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    @Resource
    private HttpApiUtil ucenterWebApi;

    @Value("#{webapiUrlProperties['oaucenter.businesstype']}")
    private String businesstypelistUrl;

    @Test
    public void getBusinessType() {
//		String url = "http://172.16.0.250:8080/oaucenter/ws/basedata/businesstypelist";
        try {
//			String str = HttpApiUtil.get(url);

            String str = ucenterWebApi.get(businesstypelistUrl);
//			System.out.println(str);
			String data = "[{\"uid\":\"72cf3a1f-55f3-11e5-999d-c86000a05d5f\",\"name\":\"日用品\",\"code\":\"1\",\"priority\":1,\"comment\":\"\"},{\"uid\":\"72cf3d08-55f3-11e5-999d-c86000a05d5f\",\"name\":\"电脑及办公用品\",\"code\":\"1\",\"priority\":1,\"comment\":\"\"},{\"uid\":\"72cf3e04-55f3-11e5-999d-c86000a05d5f\",\"name\":\"商业服务及流通\",\"code\":\"1\",\"priority\":1,\"comment\":\"\"},{\"uid\":\"72cf3ea4-55f3-11e5-999d-c86000a05d5f\",\"name\":\"文教科卫\",\"code\":\"1\",\"priority\":1,\"comment\":\"\"},{\"uid\":\"72cf3f4e-55f3-11e5-999d-c86000a05d5f\",\"name\":\"网站\",\"code\":\"1\",\"priority\":1,\"comment\":\"\"},{\"uid\":\"72cf3ff4-55f3-11e5-999d-c86000a05d5f\",\"name\":\"食品\",\"code\":\"1\",\"priority\":1,\"comment\":\"\"},{\"uid\":\"72cf407d-55f3-11e5-999d-c86000a05d5f\",\"name\":\"汽车交通类\",\"code\":\"1\",\"priority\":1,\"comment\":\"\"},{\"uid\":\"72cf4108-55f3-11e5-999d-c86000a05d5f\",\"name\":\"家居建材\",\"code\":\"1\",\"priority\":1,\"comment\":\"\"},{\"uid\":\"72cf419e-55f3-11e5-999d-c86000a05d5f\",\"name\":\"公益类\",\"code\":\"1\",\"priority\":1,\"comment\":\"\"},{\"uid\":\"72cf4222-55f3-11e5-999d-c86000a05d5f\",\"name\":\"电讯服务\",\"code\":\"1\",\"priority\":1,\"comment\":\"\"},{\"uid\":\"72cf4303-55f3-11e5-999d-c86000a05d5f\",\"name\":\"通讯产品\",\"code\":\"1\",\"priority\":1,\"comment\":\"\"},{\"uid\":\"72cf43b4-55f3-11e5-999d-c86000a05d5f\",\"name\":\"娱乐休闲\",\"code\":\"1\",\"priority\":1,\"comment\":\"\"},{\"uid\":\"72cf4439-55f3-11e5-999d-c86000a05d5f\",\"name\":\"药品\",\"code\":\"1\",\"priority\":1,\"comment\":\"\"},{\"uid\":\"72cf44ba-55f3-11e5-999d-c86000a05d5f\",\"name\":\"金融\",\"code\":\"1\",\"priority\":1,\"comment\":\"\"},{\"uid\":\"72cf453f-55f3-11e5-999d-c86000a05d5f\",\"name\":\"饮料\",\"code\":\"1\",\"priority\":1,\"comment\":\"\"},{\"uid\":\"72cf45c0-55f3-11e5-999d-c86000a05d5f\",\"name\":\"活动/会展类\",\"code\":\"1\",\"priority\":1,\"comment\":\"\"},{\"uid\":\"72cf4645-55f3-11e5-999d-c86000a05d5f\",\"name\":\"保健品 \",\"code\":\"1\",\"priority\":1,\"comment\":\"\"},{\"uid\":\"72cf46cd-55f3-11e5-999d-c86000a05d5f\",\"name\":\"家用电器 \",\"code\":\"1\",\"priority\":1,\"comment\":\"\"},{\"uid\":\"72cf474e-55f3-11e5-999d-c86000a05d5f\",\"name\":\"保险\",\"code\":\"1\",\"priority\":1,\"comment\":\"\"},{\"uid\":\"72cf47cf-55f3-11e5-999d-c86000a05d5f\",\"name\":\"房地产\",\"code\":\"1\",\"priority\":1,\"comment\":\"\"},{\"uid\":\"72cf4854-55f3-11e5-999d-c86000a05d5f\",\"name\":\"物流及邮政类\",\"code\":\"1\",\"priority\":1,\"comment\":\"\"},{\"uid\":\"72cf48dc-55f3-11e5-999d-c86000a05d5f\",\"name\":\"数码产品\",\"code\":\"1\",\"priority\":1,\"comment\":\"\"},{\"uid\":\"72cf4961-55f3-11e5-999d-c86000a05d5f\",\"name\":\"体育用品及服务\",\"code\":\"1\",\"priority\":1,\"comment\":\"\"},{\"uid\":\"72cf49e5-55f3-11e5-999d-c86000a05d5f\",\"name\":\"工业用品\",\"code\":\"1\",\"priority\":1,\"comment\":\"\"},{\"uid\":\"72cf4a6a-55f3-11e5-999d-c86000a05d5f\",\"name\":\"化妆品\",\"code\":\"1\",\"priority\":1,\"comment\":\"\"},{\"uid\":\"72cf4ae8-55f3-11e5-999d-c86000a05d5f\",\"name\":\"奶类产品\",\"code\":\"1\",\"priority\":1,\"comment\":\"\"},{\"uid\":\"72cf4b69-55f3-11e5-999d-c86000a05d5f\",\"name\":\"农业\",\"code\":\"1\",\"priority\":1,\"comment\":\"\"},{\"uid\":\"72cf4be7-55f3-11e5-999d-c86000a05d5f\",\"name\":\"烟酒类\",\"code\":\"1\",\"priority\":1,\"comment\":\"\"},{\"uid\":\"72cf4c68-55f3-11e5-999d-c86000a05d5f\",\"name\":\"服装服饰\",\"code\":\"1\",\"priority\":1,\"comment\":\"\"}]";

//			Map<String,Object> res = JsonUtil.ToMap(str);
//
//			System.out.println(res.get("data").toString());

//            String data = JsonUtil.GetField(str, "data");
//            System.out.println(data);

//            List<BusinessType> list = JsonUtil.ToList(data, BusinessType.class);

//            List<BusinessType> list = ResponseMessage.toObjectList(str,BusinessType.class);

            ObjectMapper objectMapper = new ObjectMapper();
            List<BusinessType> list = objectMapper.readValue(data, new TypeReference<List<BusinessType>>() {});

            for (BusinessType businessType : list) {
                System.out.println(businessType);
            }
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
