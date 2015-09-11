package com.balintimes.erp.util.webapi;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpApiUtil {

	public static String get(String path, Map<String, Object> params,
			UrlFormatEnum format) throws ClientProtocolException, IOException {
		String url = path;
		if (params.size() > 0) {
			if (format == UrlFormatEnum.REST) {
				for (String item : params.keySet()) {
					Object obj = params.get(item);
					url += "/" + obj.toString();
				}
			} else if (format == UrlFormatEnum.PARAM) {
				Iterator<Entry<String, Object>> entries = params.entrySet()
						.iterator();
				while (entries.hasNext()) {
					Map.Entry<String, Object> entry = (Map.Entry<String, Object>) entries
							.next();
					String key = entry.getKey();
					String value = entry.getValue().toString();
					if (url.indexOf("?") == -1) {
						url += "?" + key + "=" + value;
					} else {
						url += "&" + key + "=" + value;
					}
				}
			}
		}
		return get(url);
	}

	public static String get(String url) throws ClientProtocolException,
			IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpgets = new HttpGet(url);
		HttpResponse response = httpclient.execute(httpgets);
		HttpEntity entity = response.getEntity();

		String str = null;
		if (entity != null) {
			str = EntityUtils.toString(entity, "utf-8");
			httpgets.abort();
		}
		httpclient.close();
		return str;
	}

	public static String post(String url, List<NameValuePair> params)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		httppost.setEntity(new UrlEncodedFormEntity(params));
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();
		String str = "";
		if (entity != null) {
			str = EntityUtils.toString(entity, "utf-8");
		}

		httppost.releaseConnection();
		httpclient.close();
		return str;
	}

}
