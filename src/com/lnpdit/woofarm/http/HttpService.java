package com.lnpdit.woofarm.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.lnpdit.woofarm.utils.SOAP_UTILS;

public class HttpService {
    /**
     * 
     * @param method
     *            SOAP_UTILS
     * @param property_nm
     * @param property_va
     * @return
     */
    public static Object data(String method, String[] property_nm,
            Object[] property_va) {
        String url = "";
        if(method.equals("weatherInfo")){
            url = SOAP_UTILS.WEATHER_IP + method + "?";
        }else{

            url = SOAP_UTILS.URL + method + "?";
        }
        for (int i = 0; i < property_va.length; i++) {
            url += property_nm[i] + "=" + property_va[i] + "&";
        }
        url = url.substring(0, url.length() - 1);

        // String url = SOAP_UTILS.URL + method;
        // for (int i = 0; i < property_va.length; i++) {
        // url = url + "/" + property_va[i];
        // }
        HttpGet httpGet = new HttpGet(url);
        HttpClient httpClient = new DefaultHttpClient();
        // 发送请求
        try {
            HttpResponse response = httpClient.execute(httpGet);
            String result = EntityUtils.toString(response.getEntity(), "UTF_8");

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
