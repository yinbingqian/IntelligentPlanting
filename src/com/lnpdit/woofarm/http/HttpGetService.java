package com.lnpdit.woofarm.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.lnpdit.woofarm.utils.SOAP_UTILS;


public class HttpGetService {

	@SuppressWarnings("deprecation")
	public static Object data(String url1, String method) {
		 String url=SOAP_UTILS.URL+url1;
//		String url = "http://192.168.1.122/api/app/" + url1;
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
			String result = reader.readLine();

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
