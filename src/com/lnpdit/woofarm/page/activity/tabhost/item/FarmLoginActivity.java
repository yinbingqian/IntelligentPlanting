package com.lnpdit.woofarm.page.activity.tabhost.item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.db.DBHelper;
import com.lnpdit.woofarm.entity.FarmGreenHouseList;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.page.activity.farm.FarmListActivity;
import com.lnpdit.woofarm.page.activity.farm.GreenHouseInfoActivity;
import com.lnpdit.woofarm.page.activity.login.LoginActivity;
import com.lnpdit.woofarm.page.adapter.FarmGreenhouseAdapter;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FarmLoginActivity extends BaseActivity implements OnClickListener {
    /** Called when the activity is first created. */

    private DBHelper dbh;
    static Context context;
    private FarmGreenHouseList cart;
    private List<FarmGreenHouseList> farmgreenhouseList = new ArrayList<FarmGreenHouseList>();
    private ListView listview_farmgreenhouselist;
    private FarmGreenhouseAdapter farmgreenhouseListAdapter;
    private TextView location_tv;
    private TextView farm_tv;
    private TextView wind_tv;
    private TextView temperature_tv;
    private TextView temperature_img;
//    private ImageView temperature_img;
    private Button login_bt;
    private LinearLayout unlogin_layout;
    private ImageView changefarm_img;
    
    private String farmname = "";
    private String farmCode = "";
    private String city="";
//    private boolean isFirst = true;
    
    //定位获取城市
    private LocationManager locationManager;
    private double latitude = 0;  
    private double longitude = 0;
    private String latLongString = "";
    Double a, b;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_farmlogin);

        initView();
        
        farmgreenhouseListAdapter = new FarmGreenhouseAdapter(context, farmgreenhouseList);
        listview_farmgreenhouselist.setAdapter(farmgreenhouseListAdapter);
        
        initData();
        setListeners();
        //定位获取当前城市
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);  

    }

    private void initView() {
        listview_farmgreenhouselist = (ListView) findViewById(R.id.listview_farmlist);
        temperature_img = (TextView) findViewById(R.id.temperature_img);
        temperature_img.setText("");
        temperature_tv = (TextView) findViewById(R.id.temperature_tv);
        temperature_tv.setText("");
        login_bt = (Button) findViewById(R.id.login_bt);
        login_bt.setOnClickListener(this);
        location_tv = (TextView) findViewById(R.id.location_tv);
        location_tv.setText("未知");
        location_tv.setClickable(true);
        location_tv.setOnClickListener(this);
        farm_tv = (TextView) findViewById(R.id.farm_tv);
        farm_tv.setClickable(true);
        farm_tv.setOnClickListener(this);
        wind_tv = (TextView) findViewById(R.id.wind_tv);
        wind_tv.setText("");
        unlogin_layout = (LinearLayout) findViewById(R.id.unlogin_layout);
        changefarm_img = (ImageView) findViewById(R.id.changefarm_img);
        changefarm_img.setClickable(true);
        changefarm_img.setOnClickListener(this);

    }

    private void initData() {
       
        SharedPreferences sharedPreferences = getSharedPreferences("userinfo",Activity.MODE_PRIVATE);  
        String userCode = sharedPreferences.getString("userCode","");  
        if(!userCode.equals("")&&!userCode.equals("null")){
            listview_farmgreenhouselist.setVisibility(1);
            unlogin_layout.setVisibility(8);
            
            String[] property_va = new String[] {userCode };
            soapService.farmListDataFirst(property_va);
            

//            String[] property_vas = new String[] {"991187136638402560" };
//            soapService.greenhouseListData(property_vas);
            
        }else{
            listview_farmgreenhouselist.setVisibility(8);
            unlogin_layout.setVisibility(1);

            farm_tv.setText("未登录");
        }

    }

    
    private void setListeners() {
        listview_farmgreenhouselist.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                farmname = farmList.get(position).getFarmName();
//                SharedPreferences sp = getSharedPreferences("farm", Context.MODE_PRIVATE); 
//                Editor editor = sp.edit();
//                editor.putString("farmname", farmname);
//                editor.putString("farmcode", farmcode);
//                editor.commit();
//                finish();
                if(farmgreenhouseList.get(position).getPlanting().equals("true")){

                    Intent intent = new Intent();
                    intent.putExtra("farmcode", farmCode);
                    intent.putExtra("pengcode", farmgreenhouseList.get(position).getPengCode());
                    intent.putExtra("pengname", farmgreenhouseList.get(position).getPengName());
                    intent.setClass(context, GreenHouseInfoActivity.class);
                    startActivity(intent); 
                }
            }
        });
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.login_bt:

            Intent intent = new Intent();
            intent.putExtra("type", "in");
            intent.setClass(context, LoginActivity.class);
            startActivityForResult(intent, 2);
            break;
        case R.id.location_tv:
            new Thread() {  
                @Override  
                public void run() {  
                    Location location = locationManager  
                            .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);  
                    if (location != null) {  
                        latitude = location.getLatitude(); // 经度  
                        longitude = location.getLongitude(); // 纬度  
                        double[] data = { latitude, longitude };  
                        Message msg = handler.obtainMessage();  
                        msg.obj = data;  
                        handler.sendMessage(msg);  
                    }  
                }  
            }.start();  
           
            break;
            
        case R.id.changefarm_img:
        case R.id.farm_tv:

//            Intent intents = new Intent();
//            intents.setClass(context, FarmListActivity.class);
//            startActivity(intents);
            if(farm_tv.getText().toString().equals("未登录")){

                Intent intent_login = new Intent();
                intent_login.putExtra("type", "in");
                intent_login.setClass(context, LoginActivity.class);
                startActivityForResult(intent_login, 2);
            }else{
            Intent intents = new Intent();
            intents.setClass(context, FarmListActivity.class);
            startActivityForResult(intents, 3);
            }
            break;
        default:
            break;
        }
    }

    private Handler handler = new Handler() {  
        public void handleMessage(android.os.Message msg) {  
            double[] data = (double[]) msg.obj;  
//            showJW.setText("经度：" + data[0] + "\t纬度:" + data[1]);  
  
            List<Address> addList = null;  
            Geocoder ge = new Geocoder(getApplicationContext());  
            try {  
                addList = ge.getFromLocation(data[0], data[1], 1);  
            } catch (IOException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
            if (addList != null && addList.size() > 0) {  
                for (int i = 0; i < addList.size(); i++) {  
                    Address ad = addList.get(i);  
                    latLongString = ad.getLocality();  
                }  
            }  
            location_tv.setText(latLongString);  
            
            city =location_tv.getText().toString();
            if(!city.equals("")&&!city.equals("null")){
            String[] property_va = new String[] {"71b020fe0da38dbc9e1485b330ec1a55",city };
            soapService.getWeather(property_va);
            }
        }  
  
    };  
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
            Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==2){
            if(requestCode==2&&resultCode==2){
            initData();
        }
        if(requestCode==3&&resultCode==3){
            farmname = data.getStringExtra("farmname");
          farmCode = data.getStringExtra("farmcode");
      if(!farmname.equals("")&&!farmname.equals("null")){

          farm_tv.setText(farmname);

          String[] property_vas = new String[] {farmCode };
          soapService.greenhouseListData(property_vas);
      }else{

          farm_tv.setText("未登录");
      }  
        }
    }
    
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode,
//            Intent data) {
//        // TODO Auto-generated method stub
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==2){
////            if(requestCode==2&&resultCode==1){
//            initData();
//        }
//        
////        if(requestCode==3){
//        if (resultCode == 3) {
//            if(requestCode==3){
//                farmname = data.getStringExtra("farmname");
//                farmCode = data.getStringExtra("farmcode");
//            if(!farmname.equals("")&&!farmname.equals("null")){
//
//                farm_tv.setText(farmname);
//
//                String[] property_vas = new String[] {farmCode };
//                soapService.greenhouseListData(property_vas);
//            }else{
//
//                farm_tv.setText("未登录");
//            }  
//        }
//        }
//    }
    

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("userinfo",Activity.MODE_PRIVATE);  
        String userCode = sharedPreferences.getString("userCode","");  
        if(userCode.equals("")||userCode.equals("null")){
            listview_farmgreenhouselist.setVisibility(8);
            unlogin_layout.setVisibility(1);

            farm_tv.setText("未登录");
        }

    }
      
    
    @Override
    public void onEvent(Object obj) {
        // TODO Auto-generated method stub
        super.onEvent(obj);
        SoapRes res = (SoapRes) obj;
      if (res.getCode().equals(SOAP_UTILS.METHOD.FARMLISTDATAFIRST)) {
            if (res.getObj() != null) {
                try {
                JSONObject json_objs = new JSONObject(res.getObj().toString());
       
                if (json_objs.get("result").toString().equals("true")) {
                    JSONArray json_arr = json_objs.getJSONArray("data");
//                    for (int i = 0; i < json_arr.length(); i++) {
                        JSONObject json_obj= (JSONObject) json_arr.get(0);
                        farmCode = json_obj.getString("farmCode");
                        farmname = json_obj.getString("farmName");
//                    }
                      SharedPreferences sp = getSharedPreferences("farm", Context.MODE_PRIVATE); 
                      Editor editor = sp.edit();
                      editor.putString("farmname", farmname);
                      editor.putString("farmcode", farmCode);
                      editor.commit();
                      
                      farm_tv.setText(farmname);
                      
                        String[] property_vas = new String[] {farmCode };
                        soapService.greenhouseListData(property_vas);
//                        isFirst = false;
                 }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }else{
                Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
            }
        }else  if (res.getCode().equals(SOAP_UTILS.METHOD.GREENHOUSELISTDATA)) {
            if (res.getObj() != null) {
                try {
     
                    JSONObject json_objs = new JSONObject(res.getObj().toString());
       
                if (json_objs.get("result").toString().equals("true")) {
                    farmgreenhouseList.clear();
                    JSONArray json_arr = json_objs.getJSONArray("data");
                    for (int i = 0; i < json_arr.length(); i++) {
                        JSONObject json_obj= (JSONObject) json_arr.get(i);
                        FarmGreenHouseList farm = new FarmGreenHouseList();
                        farm.setId(json_obj.getString("id"));
                        farm.setFarmCode(json_obj.getString("farmCode"));
                        farm.setGpsPoint(json_obj.getString("gpsPoint"));
                        farm.setPlanting(json_obj.getString("planting"));
                        farm.setLandarea(json_obj.getString("landarea"));
                        farm.setManager(json_obj.getString("manager"));
                        farm.setPengCode(json_obj.getString("pengCode"));
                        farm.setPengName(json_obj.getString("pengName"));
                        farm.setPengType(json_obj.getString("pengType"));
                        farm.setPlantStatus(json_obj.getString("plantStatus"));
                        farm.setSensorCount(json_obj.getString("sensorCount"));
                        farm.setControlCount(json_obj.getString("controlCount"));
                        farmgreenhouseList.add(farm);
                    }

                    if(farmgreenhouseList.size()!=0){

                        farmgreenhouseListAdapter.notifyDataSetChanged();
//                        farmgreenhouseListAdapter = new FarmGreenhouseAdapter(context, farmgreenhouseList);
//                        listview_farmgreenhouselist.setAdapter(farmgreenhouseListAdapter);
                    }else{
                        listview_farmgreenhouselist.setAdapter(null);
                    }
                 }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }else{
                Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
            }
        }else if (res.getCode().equals(SOAP_UTILS.METHOD.GETWEATHER)) {
            if (res.getObj() != null) {
                try {
     
                    JSONObject json_objs = new JSONObject(res.getObj().toString());
       
                if (json_objs.get("info").toString().equals("OK")) {
                    JSONArray json_arr = json_objs.getJSONArray("lives");
                    for (int i = 0; i < json_arr.length(); i++) {
                        JSONObject json_obj= (JSONObject) json_arr.get(i);
                   
                        temperature_tv.setText(json_obj.getString("temperature") + "℃");
                        wind_tv.setText(json_obj.getString("winddirection") + "风" +json_obj.getString("windpower")+"级");
                        temperature_img.setText(json_obj.getString("weather"));
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