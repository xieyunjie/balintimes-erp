package com.balintimes.erp.crm.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.balintimes.erp.crm.service.CenterWebService;
import com.balintimes.erp.model.crm.BusinessType;
import com.balintimes.erp.model.crm.CustomerCategory;
import com.balintimes.erp.model.crm.Employee;
import com.balintimes.erp.util.json.ResponseMessage;
import com.balintimes.erp.util.webapi.HttpApiUtil;
import com.balintimes.erp.util.webapi.UrlFormatEnum;

@Service
public class CenterWebServiceImpl implements CenterWebService {

	@Resource
	private HttpApiUtil httpApiUtil;

	@Value("#{webapiUrlProperties['oaucenter.businesstypelist']}")
	private String businesstypelistUrl;
	@Value("#{webapiUrlProperties['oaucenter.categorylist']}")
	private String customercategorylistUrl;
	@Value("#{webapiUrlProperties['oaucenter.getsubordinates']}")
	private String getsubordinatesUrl;
	@Value("#{webapiUrlProperties['oaucenter.businesstype']}")
	private String businesstypeUrl;
	@Value("#{webapiUrlProperties['oaucenter.category']}")
	private String customercategoryUrl;

	public List<BusinessType> getBusinessTypeList() {
		// TODO Auto-generated method stub
		try {
			String json = this.httpApiUtil.get(businesstypelistUrl);
			List<BusinessType> list = ResponseMessage.toObjectList(json,
					BusinessType.class);
			return list;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public BusinessType getBusinessType(String uid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		try {
			 String json = this.httpApiUtil.get(this.businesstypeUrl, map, UrlFormatEnum.REST);
			//String url = this.businesstypeUrl + "/" + uid;
			//String json = this.httpApiUtil.get(url);
			BusinessType bt = ResponseMessage
					.toObject(json, BusinessType.class);
			return bt;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<CustomerCategory> getCustomerCategoryList() {
		// TODO Auto-generated method stub
		try {
			String json = this.httpApiUtil.get(customercategorylistUrl);
			List<CustomerCategory> list = ResponseMessage.toObjectList(json,
					CustomerCategory.class);
			return list;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public CustomerCategory getCustomerCategory(String uid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		try {
			String json = this.httpApiUtil.get(this.customercategoryUrl, map,
					UrlFormatEnum.REST);
			CustomerCategory cc = ResponseMessage.toObject(json,
					CustomerCategory.class);
			return cc;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Employee> getSubordinatesByUser(String username) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		try {
			String json = this.httpApiUtil.get(this.getsubordinatesUrl, map,
					UrlFormatEnum.REST);
			List<Employee> list = ResponseMessage.toObjectList(json,
					Employee.class);
			return list;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
