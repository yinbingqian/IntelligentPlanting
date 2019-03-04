package com.lnpdit.woofarm.page.activity.farmingmanagement;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.entity.GrowInfoList;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.page.adapter.GrowCropListAdapter;
import com.lnpdit.woofarm.page.adapter.GrowInfoListAdapter;
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

public class GrowListActivity extends BaseActivity implements OnClickListener {
    /** Called when the activity is first created. */

    private List<GrowInfoList> plantingList = new ArrayList<GrowInfoList>();
    private ListView listview_plantinglist;
    private GrowInfoListAdapter plantingListAdapter;
    private TextView tv_back;
    private ImageView changefarm_img;
    private TextView tv_title;
    Context context;
    private String plantId = "";
    private String plantName = "";
    private String userCode = "";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_growlist);
       
        SharedPreferences sp = getSharedPreferences("grow", Context.MODE_PRIVATE); 
        plantId =sp.getString("plantId", "");
        plantName =sp.getString("plantName", "");

                
        initView();
        initData();
        setListeners();
    }

    private void initView() {
        listview_plantinglist = (ListView) findViewById(R.id.listview_plantinglist);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setClickable(true);
        tv_back.setOnClickListener(this);
        changefarm_img = (ImageView) findViewById(R.id.changefarm_img);
        changefarm_img.setClickable(true);
        changefarm_img.setOnClickListener(this);
        tv_title = (TextView) findViewById(R.id.tv_title);
        if(plantName.equals("")){

            tv_title.setText("请点击选择地块");
        }else{

            tv_title.setText(plantName);
        }
        tv_title.setClickable(true);
        tv_title.setOnClickListener(this);
    }

    private void initData() {

        plantingListAdapter = new GrowInfoListAdapter(context, plantingList);
        listview_plantinglist.setAdapter(plantingListAdapter);
        
        SharedPreferences sharedPreferences = getSharedPreferences("userinfo",Activity.MODE_PRIVATE);  
        userCode = sharedPreferences.getString("userCode","");  
        if(!userCode.equals("")&&!userCode.equals("null")){

            String[] property_va = new String[] {plantId};
            soapService.getGrowInfo(property_va);
        
        }else{
            Toast.makeText(context, "请登录后查看", Toast.LENGTH_SHORT).show();
            tv_title.setText("未登录");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.tv_back:
            finish();
            break;
        case R.id.changefarm_img:
        case R.id.tv_title:

            SharedPreferences sharedPreferences = getSharedPreferences("userinfo",Activity.MODE_PRIVATE);  
            String userCode = sharedPreferences.getString("userCode","");  
            if(!userCode.equals("")&&!userCode.equals("null")){
                
            Intent intents = new Intent();
            intents.setClass(context, GrowCropListActivity.class);
            startActivityForResult(intents, 4);
            }else{

                Toast.makeText(context, "请登录后查看", Toast.LENGTH_SHORT).show();
            }
            break;
        case R.id.add_tv:

//            Intent intent = new Intent();
////            intent.putExtra("cropName", zuowukuList.get(position).getCropName());
////            intent.putExtra("cropcode", zuowukuList.get(position).getCropCode());
//            intent.setClass(context, AddPlantingActivity.class);
//            startActivity(intent);
            break;
        default:
            break;
        }
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
            Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==4&&resultCode==3){
            plantName = data.getStringExtra("plantName");
            plantId = data.getStringExtra("plantId");
      if(!plantName.equals("")&&!plantName.equals("null")){

          tv_title.setText(plantName);

          String[] property_va = new String[] {plantId};
          soapService.getGrowInfo(property_va);
      }else{

          tv_title.setText("未登录");
      }  
        }
    }
    
    private void setListeners() {
        listview_plantinglist.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Click Towns");

            }
        });
    }
   @Override
   public void onEvent(Object obj) {
       // TODO Auto-generated method stub
       super.onEvent(obj);
       SoapRes res = (SoapRes) obj;
       if (res.getCode().equals(SOAP_UTILS.METHOD.GETGROWINFO)) {
           if (res.getObj() != null) {
               try {
               JSONObject json_objs = new JSONObject(res.getObj().toString());
      
               if (json_objs.get("result").toString().equals("true")) {
                   plantingList.clear();
                   JSONArray json_arr = json_objs.getJSONArray("data");
                   for (int i = 0; i < json_arr.length(); i++) {
                       JSONObject json_obj= (JSONObject) json_arr.get(i);
                       GrowInfoList farm = new GrowInfoList();
                       farm.setPlantName(json_obj.getString("workName"));
                       farm.setPlantId(json_obj.getString("plantId"));
                       farm.setLogAction(json_obj.getString("logAction"));
                       farm.setWorkContent(json_obj.getString("workContent"));
                       farm.setLogImg(json_obj.getString("logImg"));
                       plantingList.add(farm);
                   }

                   if(plantingList.size()!=0){

                       plantingListAdapter.notifyDataSetChanged();
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