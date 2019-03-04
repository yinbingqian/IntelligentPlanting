package com.lnpdit.woofarm.page.activity.farmingmanagement;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.entity.PickList;
import com.lnpdit.woofarm.entity.ZuowukuList;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.page.adapter.PickListAdapter;
import com.lnpdit.woofarm.page.adapter.PlantingManagementListAdapter;
import com.lnpdit.woofarm.page.adapter.ZuowukuListAdapter;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PickListActivity extends BaseActivity implements OnClickListener {
    /** Called when the activity is first created. */

    private List<PickList> pickList = new ArrayList<PickList>();
    private ListView listview_picklist;
    private PickListAdapter pickListAdapter;
    private TextView tv_back;
    private TextView tv_title;
    private TextView add_tv;
    Context context;
    private String farmname = "";
    private String farmcode = "";
    String userCode = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_picklist);

        initView();
        initData();
        setListeners();
    }

    private void initView() {
        listview_picklist = (ListView) findViewById(R.id.listview_picklist);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setClickable(true);
        tv_back.setOnClickListener(this);
        add_tv = (TextView) findViewById(R.id.add_tv);
        add_tv.setClickable(true);
        add_tv.setOnClickListener(this);
    }

    private void initData() {

        SharedPreferences sharedPreferences = getSharedPreferences("userinfo",Activity.MODE_PRIVATE);  
        userCode = sharedPreferences.getString("userCode","");  
        
        if(!userCode.equals("")&&!userCode.equals("null")){
            
        String[] property_va = new String[] {userCode};
        soapService.getPickList(property_va);
        
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
            intents.setClass(context, AddPickActivity.class);
            startActivity(intents);
              break;
        default:
            break;
        }
    }
    
    private void setListeners() {
        listview_picklist.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Click Towns");
                
//                Intent intent = new Intent();
//                intent.putExtra("cropName", pickList.get(position).getCropName());
//                intent.putExtra("cropcode", pickList.get(position).getCropCode());
//                intent.setClass(context, ZuowuInfoActivity.class);
//                startActivity(intent);
            }
        });
    }
   @Override
   public void onEvent(Object obj) {
       // TODO Auto-generated method stub
       super.onEvent(obj);
       SoapRes res = (SoapRes) obj;
       if (res.getCode().equals(SOAP_UTILS.METHOD.GETPICKLIST)) {
           if (res.getObj() != null) {
               try {
               JSONObject json_objs = new JSONObject(res.getObj().toString());
      
               if (json_objs.get("result").toString().equals("true")) {
                   pickList.clear();
                   JSONArray json_arr = json_objs.getJSONArray("data");
                   for (int i = 0; i < json_arr.length(); i++) {
                       JSONObject json_obj= (JSONObject) json_arr.get(i);
                       PickList farm = new PickList();
                       farm.setCropName(json_obj.getString("cropName"));
                       farm.setPlotName(json_obj.getString("plotName"));
                       farm.setPicker(json_obj.getString("picker"));
                       farm.setPickQuantity(json_obj.getString("pickQuantity"));
                       farm.setPickDate(json_obj.getString("pickDate"));
                       farm.setPickPhoto(json_obj.getString("pickPhoto"));
                       
                       pickList.add(farm);
                   }

                   if(pickList.size()!=0){

                       pickListAdapter = new PickListAdapter(context, pickList);
                       listview_picklist.setAdapter(pickListAdapter);
                   }else{
                       listview_picklist.setAdapter(null); 
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