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
                  
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.MEMBERREG);
                EventCache.commandActivity.post(soapRes);
             
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
        String[] property_nm = {"plantId"};
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
    //种植管理
    @Override
    public void getPlantingList(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"userCode"};
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
        String[] property_nm = {"equipCode","sensorType"};
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
    public void collection3(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"equipCode","sensorType"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.COLLECTIONLIST3);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.COLLECTIONLIST3);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.COLLECTIONLIST3);
                EventCache.commandActivity.post(soapRes);
            }
        });
    } 
    
    //大棚温度
    @Override
    public void collection4(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"equipCode","sensorType"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.COLLECTIONLIST4);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.COLLECTIONLIST4);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.COLLECTIONLIST4);
                EventCache.commandActivity.post(soapRes);
            }
        });
    } 
    
    //大棚曲线 土壤湿度温度
    @Override
    public void collection5(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"equipCode","sensorType"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.COLLECTIONLIST5);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.COLLECTIONLIST5);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.COLLECTIONLIST5);
                EventCache.commandActivity.post(soapRes);
            }
        });
    } 

    //大棚曲线 土壤湿度
    @Override
    public void collection6(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"equipCode","sensorType"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.COLLECTIONLIST6);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.COLLECTIONLIST6);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.COLLECTIONLIST6);
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
        String[] property_nm = {"plotId"};
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
        String[] property_nm = {"equipCode","channelNo","channelStatus"};
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
        String[] property_nm = {"type","pageNo","pageSize","label"};
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
    
    
    //我的农场传感器数据
    @Override
    public void getSensorData(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"equipCode"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.SENSORDATALIST);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.SENSORDATALIST);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.SENSORDATALIST);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    
    //网关列表
    @Override
    public void getGatewayList(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"pengCode"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.GATEWATLIST);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.GATEWATLIST);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GATEWATLIST);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    
    //我的农场绑定设备
    @Override
    public void equipmentBind(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"equipCode","equipName","pengCode","sensors"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.EQUIPMENTBIND);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.EQUIPMENTBIND);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.EQUIPMENTBIND);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }  
    
    //摄像头列表
    @Override
    public void getCameraList(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"pengCode"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.CAMERALIST);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.CAMERALIST);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.CAMERALIST);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    
    //摄像头列表
    @Override
    public void getCropDiseaseList(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"cropCode"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.CROPDISEASE);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.CROPDISEASE);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.CROPDISEASE);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }

    //作物库
    @Override
    public void getCropList(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.CROPLIST);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.CROPLIST);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.CROPLIST);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }

    //农产品溯源列表
    @Override
    public void getOriginsList(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"userCode"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.ORIGINSLIST);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.ORIGINSLIST);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.ORIGINSLIST);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    
    //溯源详情
    @Override
    public void getOriginsInfo(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"plantId"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.ORIGINSINFO);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.ORIGINSINFO);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.ORIGINSINFO);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    
    //溯源详情打分
    @Override
    public void getOriginsScore(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"plantId","client","stars"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.ORIGINSSCORE);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.ORIGINSSCORE);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.ORIGINSSCORE);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }  
    
    //农技服务视频
    @Override
    public void getVideolist(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"type"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.FARMSERVICEVIDEOLIST);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.FARMSERVICEVIDEOLIST);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.FARMSERVICEVIDEOLIST);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    
    //农事计划
    @Override
    public void getPlanlist(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"userCode"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.PLANLIST);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.PLANLIST);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.PLANLIST);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }   
    
    //农事计划-完成计划
    @Override
    public void finishPlan(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"id"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.PLANFINISH);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.PLANFINISH);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.PLANFINISH);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }  
    
    //报警详情
    @Override
    public void getAlarmInfo(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"pengCode"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.ALARMINFO);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.ALARMINFO);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.ALARMINFO);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }  
    
    //获取大棚阈值
    @Override
    public void getPengLimit(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"pengCode"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.GETPENGLIMIT);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.GETPENGLIMIT);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETPENGLIMIT);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }  

    //大棚阈值更新
    @Override
    public void updatePengLimit(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"pengCode","brightnessMini","brightnessMax","airTemperatureMini","airTemperatureMax",
                "airHumidityMini","airHumidityMax","soilTemperatureMini","soilTemperatureMax","soilHumidityMini","soilHumidityMax"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.UPDATEPENGLIMIT);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.UPDATEPENGLIMIT);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.UPDATEPENGLIMIT);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }  

    //新增定植--plotId地块id|yield产量|varietyName作物名称|cropCode作物id（作物库获取）|beginTime2019-01-01|cropVariety作物类别0-5
    @Override
    public void savePlantingInfo(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"plotId","yield","varietyName","cropCode","beginTime","cropVariety","remarks","unit","endTime"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.SAVEPLANTINGINFO);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.SAVEPLANTINGINFO);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.SAVEPLANTINGINFO);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }  
    
    //新增定植页基地 
    @Override
    public void getBaseList(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"userCode"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.GETBASELIST);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.GETBASELIST);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETBASELIST);
                EventCache.commandActivity.post(soapRes);
            }
        });
    } 

    //新增定植中获取作物品种列表
    @Override
    public void getZuowukuVarietyList(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.ZUOWUKU2);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.ZUOWUKU2);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.ZUOWUKU2);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    
    //新增定植页基地 
    @Override
    public void getGrowList(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"userCode"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.GETGROWLIST);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.GETGROWLIST);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETGROWLIST);
                EventCache.commandActivity.post(soapRes);
            }
        });
    } 
    
    //新增定植页基地 
    @Override
    public void getGrowInfo(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"plantId"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.GETGROWINFO);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.GETGROWINFO);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETGROWINFO);
                EventCache.commandActivity.post(soapRes);
            }
        });
    } 
    
    //采摘列表
    @Override
    public void getPickList(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"userCode"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.GETPICKLIST);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.GETPICKLIST);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETPICKLIST);
                EventCache.commandActivity.post(soapRes);
            }
        });
    } 
    
    //新增定植--plantId采摘作物|userCode采摘人员|pickDate采摘时间|pickUnit采摘规格|traceType|remarks备注|pickQuantity采摘数量
    @Override
    public void savePickInfo(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"plantId","userCode","pickDate","pickUnit","traceType","remarks","pickQuantity"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.SAVEPICKINFO);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.SAVEPICKINFO);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.SAVEPICKINFO);
                EventCache.commandActivity.post(soapRes);
            }
        });
    } 
    
    //新增采摘中采摘作物列表
    @Override
    public void getPickCropList(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"userCode"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.PICKCROPLIST);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.PICKCROPLIST);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.PICKCROPLIST);
                EventCache.commandActivity.post(soapRes);
            }
        });
    } 


    //新增采摘中采摘作物列表
    @Override
    public void getPickUserList(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"userCode"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.PICKCROPLIST);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.PICKCROPLIST);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.PICKCROPLIST);
                EventCache.commandActivity.post(soapRes);
            }
        });
    } 
    //首页种植列表
    @Override
    public void getPlantingLists(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"userCode"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.PLANTINGLISTS);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new HttpObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.PLANTINGLISTS);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.PLANTINGLISTS);
                EventCache.commandActivity.post(soapRes);
            }
        });
    } 

    
}
