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
import com.lnpdit.woofarm.http.AsyncTaskBase.HttpObjectResult;
import com.lnpdit.woofarm.utils.EventCache;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

public class SoapService implements ISoapService {
	private AsyncTaskBase asynTaskBase = new AsyncTaskBase();
    private AsyncPostTaskBase asynPostTaskBase = new AsyncPostTaskBase();
	private SoapRes soapRes = new SoapRes();

    @Override
    public void userLogin(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"_login","__ajax","username","password"};
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
        String[] property_nm = {"loginCode","password"};
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

    //农场列表
    @Override
    public void farmListData(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"userCode"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.FARMLISTDATA);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.FARMLISTDATA);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.FARMLISTDATA);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }

    //农场列表取第一条
    @Override
    public void farmListDataFirst(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"userCode"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.FARMLISTDATAFIRST);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.FARMLISTDATAFIRST);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.FARMLISTDATAFIRST);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }

    //大棚列表
    @Override
    public void greenhouseListData(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"farmCode"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.GREENHOUSELISTDATA);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.GREENHOUSELISTDATA);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GREENHOUSELISTDATA);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }

    //大棚信息
    @Override
    public void planting(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"farmCode"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.PLANTING);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.PLANTING);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.PLANTING);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }

    //登出
    @Override
    public void logout(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"__ajax","__sid"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.LOGOUT);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.LOGOUT);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.LOGOUT);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    
    //作物库
    @Override
    public void getZuowukuList(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.ZUOWUKU);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.ZUOWUKU);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.ZUOWUKU);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    
    //作物库
    @Override
    public void getWeather(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"key","city"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.GETWEATHER);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.GETWEATHER);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETWEATHER);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    //作物库
    @Override
    public void getPlantingList(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"farmCode"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.PLANTINGLIST);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.PLANTINGLIST);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.PLANTINGLIST);
                EventCache.commandActivity.post(soapRes);
            }
        });
    } 

    //作物库列表
    @Override
    public void getPengAlarm(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"pengCode"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.PENGALARM);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.PENGALARM);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.PENGALARM);
                EventCache.commandActivity.post(soapRes);
            }
        });
    } 

    //作物库详情
    @Override
    public void getCropInfo(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"id"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.CROPINFO);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.CROPINFO);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.CROPINFO);
                EventCache.commandActivity.post(soapRes);
            }
        });
    } 
    //结束种植
    @Override
    public void finishplant(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"id"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.FINISHPLANT);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.FINISHPLANT);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.FINISHPLANT);
                EventCache.commandActivity.post(soapRes);
            }
        });
    } 
    
    //大棚温度
    @Override
    public void collection(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"pengCode","equipType"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.COLLECTIONLIST);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.COLLECTIONLIST);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.COLLECTIONLIST);
                EventCache.commandActivity.post(soapRes);
            }
        });
    } 
    
    
    //大棚温度
    @Override
    public void scenesList(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"pengCode"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.SCENESLIST);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.SCENESLIST);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.SCENESLIST);
                EventCache.commandActivity.post(soapRes);
            }
        });
    } 
    
    
    //大棚温度
    @Override
    public void equipmentList(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"pengCode","equipKind"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.EQUIPMENTLIST);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.EQUIPMENTLIST);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.EQUIPMENTLIST);
                EventCache.commandActivity.post(soapRes);
            }
        });
    } 

    
    //大棚控制器开关
    @Override
    public void equipmentState(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"switchId","equipStatus","equipCode"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.EQUIPMENTSTATE);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.EQUIPMENTSTATE);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.EQUIPMENTSTATE);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }   
    
    //农情资讯列表
    @Override
    public void getNewsList1(Object[] property_va, final boolean isPage) {
        // TODO Auto-generated method stub
        String[] property_nm = {"type","pageNo","pageSize"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.NEWSLIST1);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setPage(isPage);
                soapRes.setCode(SOAP_UTILS.METHOD.NEWSLIST1);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.NEWSLIST1);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }  
    //农情资讯列表
    @Override
    public void getNewsList2(Object[] property_va, final boolean isPage) {
        // TODO Auto-generated method stub
        String[] property_nm = {"type","pageNo","pageSize"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.NEWSLIST2);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setPage(isPage);
                soapRes.setCode(SOAP_UTILS.METHOD.NEWSLIST2);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.NEWSLIST2);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }  
    //农情资讯列表
    @Override
    public void getNewsList3(Object[] property_va, final boolean isPage) {
        // TODO Auto-generated method stub
        String[] property_nm = {"type","pageNo","pageSize"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.NEWSLIST3);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setPage(isPage);
                soapRes.setCode(SOAP_UTILS.METHOD.NEWSLIST3);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.NEWSLIST3);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }  
    //农情资讯列表
    @Override
    public void getNewsList4(Object[] property_va, final boolean isPage) {
        // TODO Auto-generated method stub
        String[] property_nm = {"type","pageNo","pageSize"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.NEWSLIST4);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setPage(isPage);
                soapRes.setCode(SOAP_UTILS.METHOD.NEWSLIST4);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.NEWSLIST4);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }   

    //农情资讯更加点击次数
    @Override
    public void getNewsUpdatecount(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"id"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.NEWSLISTUPDATE);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.NEWSLISTUPDATE);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.NEWSLISTUPDATE);
                EventCache.commandActivity.post(soapRes);
            }
        });
    } 

    //修改密码-验证旧密码
    @Override
    public void validatePassword(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"userCode","oldPassword","__ajax","__sid"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.VALIDATEPWD);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.VALIDATEPWD);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.VALIDATEPWD);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    //修改密码
    @Override
    public void updatePassword(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"userCode","password","__ajax","__sid"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.UPDATEPWD);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.UPDATEPWD);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.UPDATEPWD);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    
    //农情资讯更加点击次数
    @Override
    public void getDeviceList(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"userCode"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.DEVICELIST);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.DEVICELIST);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.DEVICELIST);
                EventCache.commandActivity.post(soapRes);
            }
        });
    } 
}
