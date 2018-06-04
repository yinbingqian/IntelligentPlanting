package com.lnpdit.woofarm.page.activity.farmingmanagement;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.db.DBHelper;
import com.lnpdit.woofarm.entity.FarmList;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.page.adapter.FarmListAdapter;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ZuowuInfoActivity extends BaseActivity implements OnClickListener {
    /** Called when the activity is first created. */

    private TextView tv_back;
    private TextView submit_tv;
    private TextView tv_title;
    private TextView cropname_tv;
    private TextView cropstandard_tv;
    private TextView cropenvironment_tv;
    Context context;
    private String cropName = "";
    private String cropStandard = "";
    private String cropEnvironment = "";
    private String cropid = "";
    //环境参数
    private TextView dayBrightnessMini_tv; // 白天光照度下限(单位:Lux)
    private TextView dayBrightnessMax_tv; // 白天光照度上限(单位:Lux)
    private TextView dayAirTemperatureMax_tv; // 白天空气温度上限(单位:℃)
    private TextView dayAirTemperatureMini_tv; // 白天空气温度下限(单位:℃)
    private TextView dayAirHumidityMax_tv; // 白天空气湿度上限(单位:%rh)
    private TextView dayAirHumidityMini_tv;// 白天空气湿度下限(单位:%rh)
    private TextView dayCo2Concentrations_tv;  // 白天CO2浓度(单位:ppm)
    private TextView daySoilTemperatureMini_tv; // 白天土壤温度下限(单位:℃)
    private TextView daySoilTemperatureMax_tv; // 白天土壤温度上限(单位:℃)
    private TextView daySoilHumidityMini_tv;// 白天土壤湿度下限(单位:%rh)
    private TextView daySoilHumidityMax_tv; // 白天土壤湿度上限(单位:%rh)
    private TextView nightBrightnessMax_tv;  // 夜间光照度上限(单位:Lux)
    private TextView nightBrightnessMini_tv; // 夜间光照度下限(单位:Lux)
    private TextView nightAirTemperatureMax_tv; // 夜间空气温度上限(单位:℃)
    private TextView nightAirTemperatureMini_tv; // 夜间空气温度下限(单位:℃)
    private TextView nightAirHumidityMax_tv; // 夜间空气湿度上限(单位:%rh)
    private TextView nightAirHumidityMini_tv; // 夜间空气湿度下限(单位:%rh)
    private TextView nightCo2Concentrations_tv;// 夜间CO2浓度(单位:ppm)
    private TextView nightSoilTemperatureMax_tv;// 夜间土壤温度上限(单位:℃)
    private TextView nightSoilTemperatureMini_tv;  // 夜间土壤温度下限(单位:℃)
    private TextView nightSoilHumidityMini_tv;// 夜间土壤湿度下限(单位:%rh)
    private TextView nightSoilHumidityMax_tv;// 夜间土壤湿度上限(单位:%rh)
    
    private String dayBrightnessMini_str; // 白天光照度下限(单位:Lux)
    private String dayBrightnessMax_str; // 白天光照度上限(单位:Lux)
    private String dayAirTemperatureMax_str; // 白天空气温度上限(单位:℃)
    private String dayAirTemperatureMini_str; // 白天空气温度下限(单位:℃)
    private String dayAirHumidityMax_str; // 白天空气湿度上限(单位:%rh)
    private String dayAirHumidityMini_str;// 白天空气湿度下限(单位:%rh)
    private String dayCo2Concentrations_str;  // 白天CO2浓度(单位:ppm)
    private String daySoilTemperatureMini_str; // 白天土壤温度下限(单位:℃)
    private String daySoilTemperatureMax_str; // 白天土壤温度上限(单位:℃)
    private String daySoilHumidityMini_str;// 白天土壤湿度下限(单位:%rh)
    private String daySoilHumidityMax_str; // 白天土壤湿度上限(单位:%rh)
    private String nightBrightnessMax_str;  // 夜间光照度上限(单位:Lux)
    private String nightBrightnessMini_str; // 夜间光照度下限(单位:Lux)
    private String nightAirTemperatureMax_str; // 夜间空气温度上限(单位:℃)
    private String nightAirTemperatureMini_str; // 夜间空气温度下限(单位:℃)
    private String nightAirHumidityMax_str; // 夜间空气湿度上限(单位:%rh)
    private String nightAirHumidityMini_str; // 夜间空气湿度下限(单位:%rh)
    private String nightCo2Concentrations_str;// 夜间CO2浓度(单位:ppm)
    private String nightSoilTemperatureMax_str;// 夜间土壤温度上限(单位:℃)
    private String nightSoilTemperatureMini_str;  // 夜间土壤温度下限(单位:℃)
    private String nightSoilHumidityMini_str;// 夜间土壤湿度下限(单位:%rh)
    private String nightSoilHumidityMax_str;// 夜间土壤湿度上限(单位:%rh)
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_zuowuinfo);

        Intent intent = getIntent();
        cropName = intent.getStringExtra("cropName");
        cropStandard = intent.getStringExtra("cropStandard");
        cropEnvironment = intent.getStringExtra("cropEnvironment");
        cropid = intent.getStringExtra("cropid"); 
        
        initView();
        initData();
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setClickable(true);
        tv_back.setOnClickListener(this);
        submit_tv = (TextView) findViewById(R.id.submit_tv);
        submit_tv.setClickable(true);
        submit_tv.setOnClickListener(this);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(cropName);
        cropname_tv = (TextView) findViewById(R.id.cropname_tv);
        cropname_tv.setText(cropName);

        cropstandard_tv = (TextView) findViewById(R.id.cropstandard_tv);
        cropstandard_tv.setText(cropStandard);
        cropenvironment_tv = (TextView) findViewById(R.id.cropenvironment_tv);
        cropenvironment_tv.setText(cropEnvironment);
        

        dayBrightnessMini_tv = (TextView) findViewById(R.id.dayBrightnessMini_tv);
        dayBrightnessMax_tv = (TextView) findViewById(R.id.dayBrightnessMax_tv);
        dayAirTemperatureMax_tv = (TextView) findViewById(R.id.dayAirTemperatureMax_tv);
        dayAirTemperatureMini_tv = (TextView) findViewById(R.id.dayAirTemperatureMini_tv);
        dayAirHumidityMax_tv = (TextView) findViewById(R.id.dayAirHumidityMax_tv);
        dayAirHumidityMini_tv = (TextView) findViewById(R.id.dayAirHumidityMini_tv);
        dayCo2Concentrations_tv = (TextView) findViewById(R.id.dayCo2Concentrations_tv);
        daySoilTemperatureMini_tv = (TextView) findViewById(R.id.daySoilTemperatureMini_tv);
        daySoilTemperatureMax_tv = (TextView) findViewById(R.id.daySoilTemperatureMax_tv);
        daySoilHumidityMini_tv = (TextView) findViewById(R.id.daySoilHumidityMini_tv);
        daySoilHumidityMax_tv = (TextView) findViewById(R.id.daySoilHumidityMax_tv);
        nightBrightnessMax_tv = (TextView) findViewById(R.id.nightBrightnessMax_tv);
        nightBrightnessMini_tv = (TextView) findViewById(R.id.nightBrightnessMini_tv);
        nightBrightnessMini_tv = (TextView) findViewById(R.id.nightBrightnessMini_tv);
        nightAirTemperatureMax_tv = (TextView) findViewById(R.id.nightAirTemperatureMax_tv);
        nightAirTemperatureMini_tv = (TextView) findViewById(R.id.nightAirTemperatureMini_tv);
        nightAirHumidityMax_tv = (TextView) findViewById(R.id.nightAirHumidityMax_tv);
        nightAirHumidityMini_tv = (TextView) findViewById(R.id.nightAirHumidityMini_tv);
        nightCo2Concentrations_tv = (TextView) findViewById(R.id.nightCo2Concentrations_tv);
        nightSoilTemperatureMax_tv = (TextView) findViewById(R.id.nightSoilTemperatureMax_tv);
        nightSoilTemperatureMini_tv = (TextView) findViewById(R.id.nightSoilTemperatureMini_tv);
        nightSoilHumidityMini_tv = (TextView) findViewById(R.id.nightSoilHumidityMini_tv);
        nightSoilHumidityMax_tv = (TextView) findViewById(R.id.nightSoilHumidityMax_tv);

    }

    private void initData() {

        
//        SharedPreferences sharedPreferences = getSharedPreferences("userinfo",Activity.MODE_PRIVATE);  
//        String userCode = sharedPreferences.getString("userCode","");  
        String[] property_va = new String[] {cropid };
        soapService.getCropInfo(property_va);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.tv_back:
            finish();
            break;
        case R.id.submit_tv:
            finish();
            break;
        default:
            break;
        }
    }
    
    
   @Override
   public void onEvent(Object obj) {
       // TODO Auto-generated method stub
       super.onEvent(obj);
       SoapRes res = (SoapRes) obj;
       if (res.getCode().equals(SOAP_UTILS.METHOD.CROPINFO)) {
           if (res.getObj() != null) {
               try {
               JSONObject json_objs = new JSONObject(res.getObj().toString());
      
               if (json_objs.get("result").toString().equals("true")) {
                   JSONObject json_obj = json_objs.getJSONObject("data");

                  dayBrightnessMini_str = json_obj.getString("dayBrightnessMini") ; // 白天光照度下限(单位:Lux)
                    dayBrightnessMax_str = json_obj.getString("dayBrightnessMax"); // 白天光照度上限(单位:Lux)
                    dayAirTemperatureMax_str = json_obj.getString("dayAirTemperatureMax"); // 白天空气温度上限(单位:℃)
                    dayAirTemperatureMini_str = json_obj.getString("dayAirTemperatureMini"); // 白天空气温度下限(单位:℃)
                    dayAirHumidityMax_str = json_obj.getString("dayAirHumidityMax"); // 白天空气湿度上限(单位:%rh)
                    dayAirHumidityMini_str = json_obj.getString("dayAirHumidityMini");// 白天空气湿度下限(单位:%rh)
                    dayCo2Concentrations_str = json_obj.getString("dayCo2Concentrations");  // 白天CO2浓度(单位:ppm)
                    daySoilTemperatureMini_str = json_obj.getString("daySoilTemperatureMini"); // 白天土壤温度下限(单位:℃)
                    daySoilTemperatureMax_str = json_obj.getString("daySoilTemperatureMax"); // 白天土壤温度上限(单位:℃)
                    daySoilHumidityMini_str = json_obj.getString("daySoilHumidityMini");// 白天土壤湿度下限(单位:%rh)
                    daySoilHumidityMax_str = json_obj.getString("daySoilHumidityMax"); // 白天土壤湿度上限(单位:%rh)
                    nightBrightnessMax_str = json_obj.getString("nightBrightnessMax");  // 夜间光照度上限(单位:Lux)
                    nightBrightnessMini_str = json_obj.getString("nightBrightnessMini"); // 夜间光照度下限(单位:Lux)
                    nightAirTemperatureMax_str = json_obj.getString("nightAirTemperatureMax"); // 夜间空气温度上限(单位:℃)
                    nightAirTemperatureMini_str = json_obj.getString("nightAirTemperatureMini"); // 夜间空气温度下限(单位:℃)
                    nightAirHumidityMax_str = json_obj.getString("nightAirHumidityMax"); // 夜间空气湿度上限(单位:%rh)
                    nightAirHumidityMini_str = json_obj.getString("nightAirHumidityMini"); // 夜间空气湿度下限(单位:%rh)
                    nightCo2Concentrations_str = json_obj.getString("nightCo2Concentrations");// 夜间CO2浓度(单位:ppm)
                    nightSoilTemperatureMax_str = json_obj.getString("nightSoilTemperatureMax");// 夜间土壤温度上限(单位:℃)
                    nightSoilTemperatureMini_str = json_obj.getString("nightSoilTemperatureMini");  // 夜间土壤温度下限(单位:℃)
                    nightSoilHumidityMini_str = json_obj.getString("nightSoilHumidityMini");// 夜间土壤湿度下限(单位:%rh)
                    nightSoilHumidityMax_str = json_obj.getString("nightSoilHumidityMax");// 夜间土壤湿度上限(单位:%rh)

                    
                    dayBrightnessMini_tv.setText(dayBrightnessMini_str + "Lux"); // 白天光照度下限(单位:Lux)
                    dayBrightnessMax_tv.setText(dayBrightnessMax_str + "Lux"); // 白天光照度上限(单位:Lux)
                    dayAirTemperatureMax_tv.setText(dayAirTemperatureMax_str + "℃"); // 白天空气温度上限(单位:℃)
                    dayAirTemperatureMini_tv.setText(dayAirTemperatureMini_str + "℃"); // 白天空气温度下限(单位:℃)
                    dayAirHumidityMax_tv.setText(dayAirHumidityMax_str + "%rh"); // 白天空气湿度上限(单位:%rh)
                    dayAirHumidityMini_tv.setText(dayAirHumidityMini_str + "%rh");// 白天空气湿度下限(单位:%rh)
                    dayCo2Concentrations_tv.setText(dayCo2Concentrations_str + "ppm");  // 白天CO2浓度(单位:ppm)
                    daySoilTemperatureMini_tv.setText(daySoilTemperatureMini_str + "℃"); // 白天土壤温度下限(单位:℃)
                    daySoilTemperatureMax_tv.setText(daySoilTemperatureMax_str + "℃"); // 白天土壤温度上限(单位:℃)
                    daySoilHumidityMini_tv.setText(daySoilHumidityMini_str + "%rh");// 白天土壤湿度下限(单位:%rh)
                    daySoilHumidityMax_tv.setText(daySoilHumidityMax_str + "%rh"); // 白天土壤湿度上限(单位:%rh)
                    nightBrightnessMax_tv.setText(nightBrightnessMax_str + "Lux");  // 夜间光照度上限(单位:Lux)
                    nightBrightnessMini_tv.setText(nightBrightnessMini_str + "Lux"); // 夜间光照度下限(单位:Lux)
                    nightAirTemperatureMax_tv.setText(nightAirTemperatureMax_str + "℃"); // 夜间空气温度上限(单位:℃)
                    nightAirTemperatureMini_tv.setText(nightAirTemperatureMini_str + "℃"); // 夜间空气温度下限(单位:℃)
                    nightAirHumidityMax_tv.setText(nightAirHumidityMax_str + "%rh"); // 夜间空气湿度上限(单位:%rh)
                    nightAirHumidityMini_tv.setText(nightAirHumidityMini_str + "%rh"); // 夜间空气湿度下限(单位:%rh)
                    nightCo2Concentrations_tv.setText(nightCo2Concentrations_str + "ppm");// 夜间CO2浓度(单位:ppm)
                    nightSoilTemperatureMax_tv.setText(nightSoilTemperatureMax_str + "℃");// 夜间土壤温度上限(单位:℃)
                    nightSoilTemperatureMini_tv.setText(nightSoilTemperatureMini_str + "℃");  // 夜间土壤温度下限(单位:℃)
                    nightSoilHumidityMini_tv.setText(nightSoilHumidityMini_str + "%rh");// 夜间土壤湿度下限(单位:%rh)
                    nightSoilHumidityMax_tv.setText(nightSoilHumidityMax_str + "%rh");// 夜间土壤湿度上限(单位:%rh)
                    
                }
               } catch (JSONException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
               }

           }else{
               Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
           }
       }
   }
}