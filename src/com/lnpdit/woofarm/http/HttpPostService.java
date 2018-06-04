package com.lnpdit.woofarm.http;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONException;
import org.json.JSONObject;

import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.util.Log;

public class HttpPostService {

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
        String url = SOAP_UTILS.URL + method;
        try {

            NewHttpClient httpClient = new NewHttpClient();

            HttpContext localContext = new BasicHttpContext();
            HttpPost httpPost = new HttpPost(url);

            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("User-Agent", "imgfornote");

            Map<String, String> params = new HashMap<String, String>();
            JSONObject jsonObject = new JSONObject();
            for (int i = 0; i < property_va.length; i++) {
                try {
                    jsonObject.put(property_nm[i], property_va[i]);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                params.put(property_nm[i], property_va[i].toString());
            }
            try {
                httpPost.setEntity(new StringEntity(jsonObject.toString(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            String response = httpClient.post(url, params);

            String responseFromServer = response.toString();

            return responseFromServer;
        } catch (Exception e) {
            Log.v("ImgPostService", "Some error came up");
            return null;

        }
    }
}
