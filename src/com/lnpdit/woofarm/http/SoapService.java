package com.lnpdit.woofarm.http;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lnpdit.woofarm.entity.IndexAdvertise;
import com.lnpdit.woofarm.entity.IndexProduct;
import com.lnpdit.woofarm.entity.ProductByClass;
import com.lnpdit.woofarm.entity.ProductByMonth;
import com.lnpdit.woofarm.entity.ProductClass;
import com.lnpdit.woofarm.http.AsyncPostTaskBase.HttpPostObjectResult;
import com.lnpdit.woofarm.utils.EventCache;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

public class SoapService implements ISoapService {
	private AsyncTaskBase asynTaskBase = new AsyncTaskBase();
    private AsyncPostTaskBase asynPostTaskBase = new AsyncPostTaskBase();
	private SoapRes soapRes = new SoapRes();

    @Override
    public void userLogin(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"phone","password"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.LOGIN);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.LOGIN);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.LOGIN);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }

    @Override
    public void getCodeByPhone(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"phone"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.GETCODEBYPHONE);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                
                try {
                    JSONObject json_obj = new JSONObject(obj.toString());
                    String result = json_obj.get("status").toString();
                    String message = json_obj.get("msg").toString();
                    if (result.equals("true")) {
                        message = "true";
                    }
                    
                soapRes.setObj(message);
                soapRes.setCode(SOAP_UTILS.METHOD.GETCODEBYPHONE);
                EventCache.commandActivity.post(soapRes);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETCODEBYPHONE);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    
    @Override
    public void memberReg(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"phone","code","nickname","password"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.MEMBERREG);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                try {
                    JSONObject json_obj = new JSONObject(obj.toString());
                    String result = json_obj.get("status").toString();
                    String message = json_obj.get("msg").toString();
                    if (result.equals("true")) {
                        message = "true";
                    }
                soapRes.setObj(message);
                soapRes.setCode(SOAP_UTILS.METHOD.MEMBERREG);
                
                EventCache.commandActivity.post(soapRes);
                
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.MEMBERREG);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }

  //幻灯片数组
    @Override
    public void getIndexAdvertise(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.GETINDEXADVERTISE);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                try {
                    JSONObject json_obj = new JSONObject(obj.toString());
                    String result = json_obj.get("status").toString();
                 
                    JSONArray message_array = json_obj.getJSONArray("msg");
                    
                    List<IndexAdvertise> message_list = new ArrayList<IndexAdvertise>();

                    for (int i = 0; i < message_array.length(); i++) {
                        JSONObject json_news = (JSONObject) message_array.get(i);
                        IndexAdvertise hpn = new IndexAdvertise();
                        hpn.setImage(json_news.get("image").toString());
                        hpn.setLinkaddress(json_news.get("linkaddress").toString());

                        message_list.add(hpn);
                    }
                    
                    soapRes.setObj(message_list);
                    soapRes.setCode(SOAP_UTILS.METHOD.GETINDEXADVERTISE);
                    EventCache.commandActivity.post(soapRes);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    soapRes.setObj(null);
                    soapRes.setCode(SOAP_UTILS.METHOD.GETINDEXADVERTISE);
                    EventCache.commandActivity.post(soapRes);
                }
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETINDEXADVERTISE);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }

  //新品商品数组
    @Override
    public void getIndexProduct(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.GETINDEXPRODUCT);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                try {
                    JSONObject json_obj = new JSONObject(obj.toString());
                    String result = json_obj.get("status").toString();
                    
                    JSONArray message_array = json_obj.getJSONArray("msg");
                    
                    List<IndexProduct> message_list = new ArrayList<IndexProduct>();

                    for (int i = 0; i < message_array.length(); i++) {
                        JSONObject json_news = (JSONObject) message_array.get(i);
                        IndexProduct hpn = new IndexProduct();
                        hpn.setImage(json_news.get("image").toString());
                        hpn.setId(json_news.get("id").toString());
                        hpn.setPrice(json_news.get("price").toString());
                        hpn.setName(json_news.get("name").toString());

                        message_list.add(hpn);
                    }
                    
                    soapRes.setObj(message_list);
                    soapRes.setCode(SOAP_UTILS.METHOD.GETINDEXPRODUCT);
                    EventCache.commandActivity.post(soapRes);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    soapRes.setObj(null);
                    soapRes.setCode(SOAP_UTILS.METHOD.GETINDEXPRODUCT);
                    EventCache.commandActivity.post(soapRes);
                }
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETINDEXPRODUCT);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }

  //预订商品数组
    @Override
    public void getIndexPreProduct(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.GETINDEXPREPRODUCT);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                try {
                    JSONObject json_obj = new JSONObject(obj.toString());
                    String result = json_obj.get("status").toString();
                    
                    JSONArray message_array = json_obj.getJSONArray("msg");
                    
                    List<IndexProduct> message_list = new ArrayList<IndexProduct>();

                    for (int i = 0; i < message_array.length(); i++) {
                        JSONObject json_news = (JSONObject) message_array.get(i);
                        IndexProduct hpn = new IndexProduct();
                        hpn.setImage(json_news.get("image").toString());
                        hpn.setId(json_news.get("id").toString());
                        hpn.setPrice(json_news.get("price").toString());
                        hpn.setName(json_news.get("name").toString());

                        message_list.add(hpn);
                    }
                    
                    soapRes.setObj(message_list);
                    soapRes.setCode(SOAP_UTILS.METHOD.GETINDEXPREPRODUCT);
                    EventCache.commandActivity.post(soapRes);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    soapRes.setObj(null);
                    soapRes.setCode(SOAP_UTILS.METHOD.GETINDEXPREPRODUCT);
                    EventCache.commandActivity.post(soapRes);
                }
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETINDEXPREPRODUCT);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }

  //分类列表获取
    @Override
    public void getProductClass(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.GETPRODUCTCLASS);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                try {
                    JSONObject json_obj = new JSONObject(obj.toString());
                    String result = json_obj.get("status").toString();
                   
                    JSONArray message_array = json_obj.getJSONArray("msg");
                    
                    List<ProductClass> message_list = new ArrayList<ProductClass>();

                    for (int i = 0; i < message_array.length(); i++) {
                        JSONObject json_news = (JSONObject) message_array.get(i);
                        ProductClass hpn = new ProductClass();
                        hpn.setId(json_news.get("id").toString());
                        hpn.setName(json_news.get("name").toString());

                        message_list.add(hpn);
                    }
                  
                    soapRes.setObj(message_list);
                    soapRes.setCode(SOAP_UTILS.METHOD.GETPRODUCTCLASS);
                    EventCache.commandActivity.post(soapRes);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    soapRes.setObj(null);
                    soapRes.setCode(SOAP_UTILS.METHOD.GETPRODUCTCLASS);
                    EventCache.commandActivity.post(soapRes);
                }
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETPRODUCTCLASS);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }

  //分类内容列表获取
    @Override
    public void getProductByClass(Object[] property_va, final boolean isPage) {
        // TODO Auto-generated method stub
        String[] property_nm = {"classid","pagesize","pageindex"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.GETPRODUCTBYCLASS);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                try {
                    JSONObject json_obj = new JSONObject(obj.toString());
                    String result = json_obj.get("status").toString();
                    
                    JSONArray message_array = json_obj.getJSONArray("msg");
                    
                    List<ProductByClass> message_list = new ArrayList<ProductByClass>();

                    for (int i = 0; i < message_array.length(); i++) {
                        JSONObject json_news = (JSONObject) message_array.get(i);
                        ProductByClass hpn = new ProductByClass();
                        hpn.setImage(json_news.get("image").toString());
                        hpn.setId(json_news.get("id").toString());
                        hpn.setName(json_news.get("name").toString());
                        hpn.setPrice(json_news.get("price").toString());

                        message_list.add(hpn);
                    }
                    
                    soapRes.setObj(message_list);
                    soapRes.setCode(SOAP_UTILS.METHOD.GETPRODUCTBYCLASS);
                    EventCache.commandActivity.post(soapRes);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    soapRes.setObj(null);
                    soapRes.setCode(SOAP_UTILS.METHOD.GETPRODUCTBYCLASS);
                    EventCache.commandActivity.post(soapRes);
                }
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETPRODUCTBYCLASS);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }

  //新品更多获取、预订更多获取
    @Override
    public void getProductBySellType(Object[] property_va, final boolean isPage) {
        // TODO Auto-generated method stub
        //(presell:预售,sell:现有商品) selltype
        String[] property_nm = {"selltype","pagesize","pageindex"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.GETPRODUCTBYSELLTYPE);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                try {
                    JSONObject json_obj = new JSONObject(obj.toString());
                    String result = json_obj.get("status").toString();
                    if(result.equals("true")){
                  
                    JSONArray message_array = json_obj.getJSONArray("msg");
                    
                    List<ProductByClass> message_list = new ArrayList<ProductByClass>();

                    for (int i = 0; i < message_array.length(); i++) {
                        JSONObject json_news = (JSONObject) message_array.get(i);
                        ProductByClass hpn = new ProductByClass();
                        hpn.setImage(json_news.get("image").toString());
                        hpn.setId(json_news.get("id").toString());
                        hpn.setName(json_news.get("name").toString());
                        hpn.setPrice(json_news.get("price").toString());

                        message_list.add(hpn);
                    }
                    soapRes.setObj(message_list);
                    }else{
                        soapRes.setObj(result);
                    }
                    soapRes.setCode(SOAP_UTILS.METHOD.GETPRODUCTBYSELLTYPE);
                    EventCache.commandActivity.post(soapRes);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    soapRes.setObj(null);
                    soapRes.setCode(SOAP_UTILS.METHOD.GETPRODUCTBYSELLTYPE);
                    EventCache.commandActivity.post(soapRes);
                }
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETPRODUCTBYSELLTYPE);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }

  //预订月份查询
    @Override
    public void getPreProductByMonth(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"year","month"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.GETPREPRODUCTBYMONTH);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                try {
                    JSONObject json_obj = new JSONObject(obj.toString());
                    String result = json_obj.get("status").toString();
                    
                    JSONArray message_array = json_obj.getJSONArray("msg");
                    
                    List<ProductByMonth> message_list = new ArrayList<ProductByMonth>();

                    for (int i = 0; i < message_array.length(); i++) {
                        JSONObject json_news = (JSONObject) message_array.get(i);
                        ProductByMonth hpn = new ProductByMonth();
                        hpn.setDate(json_news.get("date").toString());
                        hpn.setName(json_news.get("name").toString());

                        message_list.add(hpn);
                    }
                    
                    soapRes.setObj(message_list);
                    soapRes.setCode(SOAP_UTILS.METHOD.GETPREPRODUCTBYMONTH);
                    EventCache.commandActivity.post(soapRes);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    soapRes.setObj(null);
                    soapRes.setCode(SOAP_UTILS.METHOD.GETPREPRODUCTBYMONTH);
                    EventCache.commandActivity.post(soapRes);
                }
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETPREPRODUCTBYMONTH);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
}
