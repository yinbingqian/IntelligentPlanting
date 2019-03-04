package com.lnpdit.woofarm.page.activity.farmingmanagement;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.entity.CropInfoList;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.instance.Instance;
import com.lnpdit.woofarm.page.adapter.CropInfoListAdapter;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ZuowuInfoActivity extends BaseActivity implements OnClickListener {
    /** Called when the activity is first created. */

    private TextView tv_back;
    private TextView tv_title;
    private TextView cropname_tv;
    private TextView cropinfo_tv;
    private ImageView pic_img;
    Context context;
    private String cropName = "";
    private String cropcode = "";

    View home_front = null;
    private List<CropInfoList> cropList = new ArrayList<CropInfoList>();
    private ListView listview_croplist;
    private CropInfoListAdapter cropListAdapter;
    
    //环境参数
    private TextView light_tv; // 光照度(单位:Lux)
    private TextView air_temperature_tv; // 空气温度(单位:℃)
    private TextView air_humidity_tv; // 空气湿度(单位:%rh)
    private TextView co2_tv;  // CO2浓度(单位:ppm)
    private TextView soil_temperature_tv; // 土壤温度(单位:℃)
    private TextView soil_humidity_tv;// 土壤湿度(单位:%rh)
    private TextView soilph_tv;// 土壤PH值
    private TextView ec_tv;  // EC值
    private TextView night_tv;
    private TextView day_tv;
    
    private String dayBrightnessMini_str; // 白天光照度下限(单位:Lux)
    private String dayBrightnessMax_str; // 白天光照度上限(单位:Lux)
    private String dayAirTemperatureMax_str; // 白天空气温度上限(单位:℃)
    private String dayAirTemperatureMini_str; // 白天空气温度下限(单位:℃)
    private String dayAirHumidityMax_str; // 白天空气湿度上限(单位:%rh)
    private String dayAirHumidityMini_str;// 白天空气湿度下限(单位:%rh)
    private String dayco2Mini_str;  // 白天CO2浓度(单位:ppm)
    private String dayco2Max_str;  // 白天CO2浓度(单位:ppm)
    private String daySoilTemperatureMini_str; // 白天土壤温度下限(单位:℃)
    private String daySoilTemperatureMax_str; // 白天土壤温度上限(单位:℃)
    private String daySoilHumidityMini_str;// 白天土壤湿度下限(单位:%rh)
    private String daySoilHumidityMax_str; // 白天土壤湿度上限(单位:%rh)
    private String daysoilPh_str;  // 土壤PH值
    private String daysoilEcMin_str; // EC值
    private String daysoilEcMax_str; // EC值

    private String nightBrightnessMini_str; // 夜晚光照度下限(单位:Lux)
    private String nightBrightnessMax_str; // 白天光照度上限(单位:Lux)
    private String nightAirTemperatureMax_str; // 白天空气温度上限(单位:℃)
    private String nightAirTemperatureMini_str; // 白天空气温度下限(单位:℃)
    private String nightAirHumidityMax_str; // 白天空气湿度上限(单位:%rh)
    private String nightAirHumidityMini_str;// 白天空气湿度下限(单位:%rh)
    private String nightco2Mini_str;  // 白天CO2浓度(单位:ppm)
    private String nightco2Max_str;  // 白天CO2浓度(单位:ppm)
    private String nightSoilTemperatureMini_str; // 白天土壤温度下限(单位:℃)
    private String nightSoilTemperatureMax_str; // 白天土壤温度上限(单位:℃)
    private String nightSoilHumidityMini_str;// 白天土壤湿度下限(单位:%rh)
    private String nightSoilHumidityMax_str; // 白天土壤湿度上限(单位:%rh)
    private String nightsoilPh_str;  // 土壤PH值
    private String nightsoilEcMin_str; // EC值
    private String nightsoilEcMax_str; // EC值
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_zuowuinfo);

        Intent intent = getIntent();
        cropName = intent.getStringExtra("cropName");
        cropcode = intent.getStringExtra("cropcode"); 
        
        initView();
        initData();
    }

    private void initView() {

        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setClickable(true);
        tv_back.setOnClickListener(this);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(cropName);
        
        home_front = LayoutInflater.from(this).inflate(R.layout.activity_zuowuinfoheader, null);
        
        cropname_tv = (TextView) home_front.findViewById(R.id.cropname_tv);
        cropname_tv.setText(cropName);
        cropinfo_tv = (TextView) home_front.findViewById(R.id.cropinfo_tv);
        cropinfo_tv.setText(cropName);
        pic_img = (ImageView) home_front.findViewById(R.id.pic_img);
        

        light_tv = (TextView) home_front.findViewById(R.id.light_tv);
        air_temperature_tv = (TextView) home_front.findViewById(R.id.air_temperature_tv);
        air_humidity_tv = (TextView) home_front.findViewById(R.id.air_humidity_tv);
        co2_tv = (TextView) home_front.findViewById(R.id.co2_tv);
        soil_temperature_tv = (TextView) home_front.findViewById(R.id.soil_temperature_tv);
        soil_humidity_tv = (TextView) home_front.findViewById(R.id.soil_humidity_tv);
        soilph_tv = (TextView) home_front.findViewById(R.id.soilph_tv);
        ec_tv = (TextView) home_front.findViewById(R.id.ec_tv);
        day_tv = (TextView) home_front.findViewById(R.id.day_tv);
        day_tv.setClickable(true);
        day_tv.setOnClickListener(this);
        night_tv = (TextView) home_front.findViewById(R.id.night_tv);
        night_tv.setClickable(true);
        night_tv.setOnClickListener(this);

        listview_croplist = (ListView) findViewById(R.id.listview_croplist);
        listview_croplist.addHeaderView(home_front);
    }

    private void initData() {

          
        String[] property_va = new String[] {cropcode };
        soapService.getCropInfo(property_va);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.tv_back:
            finish();
            break;
        case R.id.day_tv:
            day_tv.setTextColor(getResources().getColor(R.color.white));
            day_tv.setBackground(getResources().getDrawable(R.drawable.green_icon));
            night_tv.setTextColor(getResources().getColor(R.color.item_graytext4));
            night_tv.setBackground(getResources().getDrawable(R.drawable.gray_icon));

            light_tv.setText(dayBrightnessMini_str +"-"+ dayBrightnessMax_str + "Lux"); // 白天光照度范围(单位:Lux)
            air_temperature_tv.setText(dayAirTemperatureMini_str + "-" + dayAirTemperatureMax_str + "℃"); // 白天空气温度上限(单位:℃)
            air_humidity_tv.setText(dayAirHumidityMini_str+"-"+dayAirHumidityMax_str + "%rh"); // 白天空气湿度上限(单位:%rh)
            co2_tv.setText(dayco2Mini_str + "-" +dayco2Max_str + "ppm");  // CO2浓度(单位:ppm)
            soil_temperature_tv.setText(daySoilTemperatureMini_str +"-"+ daySoilTemperatureMax_str + "℃"); // 白天土壤温度下限(单位:℃)
            soil_humidity_tv.setText(daySoilHumidityMini_str+"-"+ daySoilHumidityMax_str + "%rh");// 白天土壤湿度下限(单位:%rh)
            soilph_tv.setText(daysoilPh_str);  // 土壤PH值
            ec_tv.setText(daysoilEcMin_str + "-" + daysoilEcMax_str ); // EC值
            break;
        case R.id.night_tv:

            night_tv.setTextColor(getResources().getColor(R.color.white));
            night_tv.setBackground(getResources().getDrawable(R.drawable.green_icon));
            day_tv.setTextColor(getResources().getColor(R.color.item_graytext4));
            day_tv.setBackground(getResources().getDrawable(R.drawable.gray_icon));

            light_tv.setText(nightBrightnessMini_str +"-"+ nightBrightnessMax_str + "Lux"); // 白天光照度范围(单位:Lux)
            air_temperature_tv.setText(nightAirTemperatureMini_str + "-" + nightAirTemperatureMax_str + "℃"); // 白天空气温度上限(单位:℃)
            air_humidity_tv.setText(nightAirHumidityMini_str+"-"+nightAirHumidityMax_str + "%rh"); // 白天空气湿度上限(单位:%rh)
            co2_tv.setText(nightco2Mini_str + "-" +nightco2Max_str + "ppm");  // CO2浓度(单位:ppm)
            soil_temperature_tv.setText(nightSoilTemperatureMini_str +"-"+ nightSoilTemperatureMax_str + "℃"); // 白天土壤温度下限(单位:℃)
            soil_humidity_tv.setText(nightSoilHumidityMini_str+"-"+ nightSoilHumidityMax_str + "%rh");// 白天土壤湿度下限(单位:%rh)
            soilph_tv.setText(nightsoilPh_str);  // 土壤PH值
            ec_tv.setText(nightsoilEcMin_str + "-" + nightsoilEcMax_str ); // EC值
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
                   String desc = json_obj.getString("desc");
                   cropinfo_tv.setText(desc);
                   String pic_path = SOAP_UTILS.PIC_PATH + json_obj.getString("avatar");
                   Instance.imageLoader.displayImage(pic_path, pic_img, Instance.new_s_options);
                   
                   JSONArray json_arr = json_obj.getJSONArray("cropEnvironmentList");
                   for (int i = 0; i < json_arr.length(); i++) {
                       JSONObject json_objday= (JSONObject) json_arr.get(i);
                   String dayOrNight = json_objday.getString("dayOrNight");
                   if(dayOrNight.equals("0")){
                     
                    dayBrightnessMini_str = json_objday.getString("brightnessMini") ; // 白天光照度下限(单位:Lux)
                    dayBrightnessMax_str = json_objday.getString("brightnessMax"); // 白天光照度上限(单位:Lux)
                    dayAirTemperatureMax_str = json_objday.getString("airTemperatureMax"); // 白天空气温度上限(单位:℃)
                    dayAirTemperatureMini_str = json_objday.getString("airTemperatureMini"); // 白天空气温度下限(单位:℃)
                    dayAirHumidityMax_str = json_objday.getString("airHumidityMax"); // 白天空气湿度上限(单位:%rh)
                    dayAirHumidityMini_str = json_objday.getString("airHumidityMini");// 白天空气湿度下限(单位:%rh)
                    dayco2Mini_str = json_objday.getString("co2Mini");  // 白天CO2浓度(单位:ppm)
                    dayco2Max_str = json_objday.getString("co2Max");  // 白天CO2浓度(单位:ppm)
                    daySoilTemperatureMini_str = json_objday.getString("soilTemperatureMini"); // 白天土壤温度下限(单位:℃)
                    daySoilTemperatureMax_str = json_objday.getString("soilTemperatureMax"); // 白天土壤温度上限(单位:℃)
                    daySoilHumidityMini_str = json_objday.getString("soilHumidityMini");// 白天土壤湿度下限(单位:%rh)
                    daySoilHumidityMax_str = json_objday.getString("soilHumidityMax"); // 白天土壤湿度上限(单位:%rh)
                    daysoilEcMin_str = json_objday.getString("soilEcMin");  // 夜间光照度上限(单位:Lux)
                    daysoilEcMax_str = json_objday.getString("soilEcMax"); // 夜间光照度下限(单位:Lux)
                    daysoilPh_str = json_objday.getString("soilPh"); // 夜间空气温度上限(单位:℃)
                    
                   }else if(dayOrNight.equals("1")){
                       
                       nightBrightnessMini_str = json_objday.getString("brightnessMini") ; // 白天光照度下限(单位:Lux)
                       nightBrightnessMax_str = json_objday.getString("brightnessMax"); // 白天光照度上限(单位:Lux)
                       nightAirTemperatureMax_str = json_objday.getString("airTemperatureMax"); // 白天空气温度上限(单位:℃)
                       nightAirTemperatureMini_str = json_objday.getString("airTemperatureMini"); // 白天空气温度下限(单位:℃)
                       nightAirHumidityMax_str = json_objday.getString("airHumidityMax"); // 白天空气湿度上限(单位:%rh)
                       nightAirHumidityMini_str = json_objday.getString("airHumidityMini");// 白天空气湿度下限(单位:%rh)
                       nightco2Mini_str = json_objday.getString("co2Mini");  // 白天CO2浓度(单位:ppm)
                       nightco2Max_str = json_objday.getString("co2Max");  // 白天CO2浓度(单位:ppm)
                       nightSoilTemperatureMini_str = json_objday.getString("soilTemperatureMini"); // 白天土壤温度下限(单位:℃)
                       nightSoilTemperatureMax_str = json_objday.getString("soilTemperatureMax"); // 白天土壤温度上限(单位:℃)
                       nightSoilHumidityMini_str = json_objday.getString("soilHumidityMini");// 白天土壤湿度下限(单位:%rh)
                       nightSoilHumidityMax_str = json_objday.getString("soilHumidityMax"); // 白天土壤湿度上限(单位:%rh)
                       nightsoilEcMin_str = json_objday.getString("soilEcMin");  // 夜间光照度上限(单位:Lux)
                       nightsoilEcMax_str = json_objday.getString("soilEcMax"); // 夜间光照度下限(单位:Lux)
                       nightsoilPh_str = json_objday.getString("soilPh"); // 夜间空气温度上限(单位:℃)
                       
                      }
                   }

                   light_tv.setText(dayBrightnessMini_str +"-"+ dayBrightnessMax_str + "Lux"); // 白天光照度范围(单位:Lux)
                   air_temperature_tv.setText(dayAirTemperatureMini_str + "-" + dayAirTemperatureMax_str + "℃"); // 白天空气温度上限(单位:℃)
                   air_humidity_tv.setText(dayAirHumidityMini_str+"-"+dayAirHumidityMax_str + "%rh"); // 白天空气湿度上限(单位:%rh)
                   co2_tv.setText(dayco2Mini_str + "-" +dayco2Max_str + "ppm");  // CO2浓度(单位:ppm)
                   soil_temperature_tv.setText(daySoilTemperatureMini_str +"-"+ daySoilTemperatureMax_str + "℃"); // 白天土壤温度下限(单位:℃)
                   soil_humidity_tv.setText(daySoilHumidityMini_str+"-"+ daySoilHumidityMax_str + "%rh");// 白天土壤湿度下限(单位:%rh)
                   soilph_tv.setText(daysoilPh_str);  // 土壤PH值
                   ec_tv.setText(daysoilEcMin_str + "-" + daysoilEcMax_str ); // EC值


                   cropList.clear();
                   JSONArray json_arrcrop = json_obj.getJSONArray("cropLivespanList");
                   for (int i = 0; i < json_arrcrop.length(); i++) {
                       JSONObject json_objcrop= (JSONObject) json_arrcrop.get(i);
                       CropInfoList farm = new CropInfoList();
                       farm.setCropname(json_objcrop.getString("cycle"));
                       farm.setCropdetails(json_objcrop.getString("details"));
                       farm.setPlantdays(json_objcrop.getString("days"));
                       cropList.add(farm);
                   }

                   if(cropList.size()!=0){

                       cropListAdapter = new CropInfoListAdapter(context, cropList);
                       listview_croplist.setAdapter(cropListAdapter);
                   }else{
                       listview_croplist.setAdapter(null); 
                   }
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