package com.lnpdit.woofarm.page.activity.farmingmanagement;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.entity.PlantingList;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.page.activity.farm.FarmListActivity;
import com.lnpdit.woofarm.page.adapter.PlantingManagementListAdapter;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PlantingManagementListActivity extends BaseActivity implements OnClickListener {
    /** Called when the activity is first created. */

    private List<PlantingList> plantingList = new ArrayList<PlantingList>();
    private ListView listview_plantinglist;
    private PlantingManagementListAdapter plantingListAdapter;
    private TextView tv_back;
//    private ImageView changefarm_img;
//    private TextView farm_tv;
    private TextView add_tv;
    Context context;
    private String farmcode = "";
    private String farmname = "";
    private String userCode = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_plantingmanagement);
       
//        SharedPreferences sp = getSharedPreferences("farm", Context.MODE_PRIVATE); 
//        farmcode =sp.getString("farmcode", "");
//        farmname =sp.getString("farmname", "");

                
        initView();
        initData();
        setListeners();
    }

    private void initView() {
        listview_plantinglist = (ListView) findViewById(R.id.listview_plantinglist);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setClickable(true);
        tv_back.setOnClickListener(this);

      add_tv = (TextView) findViewById(R.id.add_tv);
      add_tv.setClickable(true);
      add_tv.setOnClickListener(this);
//        changefarm_img = (ImageView) findViewById(R.id.changefarm_img);
//        changefarm_img.setClickable(true);
//        changefarm_img.setOnClickListener(this);
//        farm_tv = (TextView) findViewById(R.id.farm_tv);
//        farm_tv.setText(farmname);
//        farm_tv.setClickable(true);
//        farm_tv.setOnClickListener(this);
    }

    private void initData() {

//        plantingListAdapter = new PlantingManagementListAdapter(context, plantingList);
//        listview_plantinglist.setAdapter(plantingListAdapter);
        
        SharedPreferences sharedPreferences = getSharedPreferences("userinfo",Activity.MODE_PRIVATE);  
        userCode = sharedPreferences.getString("userCode","");  
        if(!userCode.equals("")&&!userCode.equals("null")){
            
        String[] property_va = new String[] {userCode};
        soapService.getPlantingList(property_va);
        
        }else{
            Toast.makeText(context, "请登录后查看", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.tv_back:
            finish();
            break;
        case R.id.add_tv:

          Intent intents = new Intent();
          intents.setClass(context, AddPlantingActivity.class);
          startActivity(intents);
            break;
//        case R.id.changefarm_img:
//        case R.id.farm_tv:
//
////            Intent intents = new Intent();
////            intents.setClass(context, FarmListActivity.class);
////            startActivity(intents);
//            SharedPreferences sharedPreferences = getSharedPreferences("userinfo",Activity.MODE_PRIVATE);  
//            String userCode = sharedPreferences.getString("userCode","");  
//            if(!userCode.equals("")&&!userCode.equals("null")){
//                
//            Intent intents = new Intent();
//            intents.setClass(context, FarmListActivity.class);
//            startActivityForResult(intents, 4);
//            }else{
//
//                Toast.makeText(context, "请前往我的农场登录后查看", Toast.LENGTH_SHORT).show();
//            }
//            break;
        default:
            break;
        }
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        initData();

    };   
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode,
//            Intent data) {
//        // TODO Auto-generated method stub
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==4&&resultCode==3){
//            farmname = data.getStringExtra("farmname");
//            farmcode = data.getStringExtra("farmcode");
//      if(!farmname.equals("")&&!farmname.equals("null")){
//
////          farm_tv.setText(farmname);
//
//          String[] property_va = new String[] {farmcode};
//          soapService.getPlantingList(property_va);
//      }else{
//
////          farm_tv.setText("未登录");
//      }  
//        }
//    }
    
    private void setListeners() {
        listview_plantinglist.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Click Towns");

//                Intent intent = new Intent();
////                intent.putExtra("cropName", zuowukuList.get(position).getCropName());
////                intent.putExtra("cropcode", zuowukuList.get(position).getCropCode());
//                intent.setClass(context, AddPlantingActivity.class);
//                startActivity(intent);
            }
        });
    }
   @Override
   public void onEvent(Object obj) {
       // TODO Auto-generated method stub
       super.onEvent(obj);
       SoapRes res = (SoapRes) obj;
       if (res.getCode().equals(SOAP_UTILS.METHOD.PLANTINGLIST)) {
           if (res.getObj() != null) {
               try {
               JSONObject json_objs = new JSONObject(res.getObj().toString());
      
               if (json_objs.get("result").toString().equals("true")) {
                   plantingList.clear();
                   JSONArray json_arr = json_objs.getJSONArray("data");
                   for (int i = 0; i < json_arr.length(); i++) {
                       JSONObject json_obj= (JSONObject) json_arr.get(i);
                       PlantingList farm = new PlantingList();
                       farm.setPlantId(json_obj.getString("plantId"));
                       farm.setBeginTime(json_obj.getString("beginTime"));
                       farm.setEndTime(json_obj.getString("endTime"));
                       farm.setPlantDays(json_obj.getString("plantDays"));
                       farm.setPlantImg(json_obj.getString("plantImg"));
                       farm.setPlantState(json_obj.getString("plantStatus"));
                       farm.setPlantSchedule(json_obj.getString("plantSchedule"));
                       farm.setVarietyName(json_obj.getString("varietyName"));
                       String planting = json_obj.getString("plantStatus");
                       if(planting.equals("0")){
                           
                           JSONObject json_plist= (JSONObject) json_obj.get("plot");
                           farm.setPlotName(json_plist.getString("plotName"));
                           
                          }else{
                              farm.setPlantDays("1");
                              farm.setBeginTime("1");
                              farm.setEndTime("1"); 
                              farm.setPlantId("1");
                              farm.setPlantImg("1");
                              
                              farm.setPlantSchedule("1");
                              farm.setPlantState("1");
                              farm.setPlotName("1");
                              farm.setVarietyName("1");
                           }
                       plantingList.add(farm);
                   }


                   if(plantingList.size()!=0){

//                       plantingListAdapter.notifyDataSetChanged();

                       plantingListAdapter = new PlantingManagementListAdapter(context, plantingList);
                       listview_plantinglist.setAdapter(plantingListAdapter);
                   }else{
                       listview_plantinglist.setAdapter(null); 
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