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
import com.lnpdit.woofarm.entity.ZuowukuList;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.page.adapter.FarmListAdapter;
import com.lnpdit.woofarm.page.adapter.ZuowukuListAdapter;
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

public class ZuowukuListActivity extends BaseActivity implements OnClickListener {
    /** Called when the activity is first created. */

    private List<ZuowukuList> zuowukuList = new ArrayList<ZuowukuList>();
    private ListView listview_zuowukulist;
    private ZuowukuListAdapter zuowukuListAdapter;
    private TextView tv_back;
    Context context;
    private String farmname = "";
    private String farmcode = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_farmingzuowukulist);

        initView();
        initData();
        setListeners();
    }

    private void initView() {
        listview_zuowukulist = (ListView) findViewById(R.id.listview_zuowukulist);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setClickable(true);
        tv_back.setOnClickListener(this);

    }

    private void initData() {

        
        String[] property_va = new String[] {};
        soapService.getZuowukuList(property_va);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.tv_back:
            finish();
            break;
        default:
            break;
        }
    }
    
    private void setListeners() {
        listview_zuowukulist.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Click Towns");
                
                Intent intent = new Intent();
                intent.putExtra("cropName", zuowukuList.get(position).getCropName());
                intent.putExtra("cropcode", zuowukuList.get(position).getCropCode());
                intent.setClass(context, ZuowuInfoActivity.class);
                startActivity(intent);
            }
        });
    }
   @Override
   public void onEvent(Object obj) {
       // TODO Auto-generated method stub
       super.onEvent(obj);
       SoapRes res = (SoapRes) obj;
       if (res.getCode().equals(SOAP_UTILS.METHOD.ZUOWUKU)) {
           if (res.getObj() != null) {
               try {
               JSONObject json_objs = new JSONObject(res.getObj().toString());
      
               if (json_objs.get("result").toString().equals("true")) {
                   zuowukuList.clear();
                   JSONArray json_arr = json_objs.getJSONArray("data");
                   for (int i = 0; i < json_arr.length(); i++) {
                       JSONObject json_obj= (JSONObject) json_arr.get(i);
                       ZuowukuList farm = new ZuowukuList();
                       farm.setId(json_obj.getString("id"));
                       farm.setCropCode(json_obj.getString("cropCode"));
                       farm.setCropName(json_obj.getString("cropName"));
                       farm.setCropVariety(json_obj.getString("cropCategory"));
                       farm.setAvatar(json_obj.getString("avatar"));
                       farm.setEnvironment(json_obj.getString("environment"));
                       farm.setStandard(json_obj.getString("standard"));
                       
                       zuowukuList.add(farm);
                   }

                   if(zuowukuList.size()!=0){

                       zuowukuListAdapter = new ZuowukuListAdapter(context, zuowukuList);
                       listview_zuowukulist.setAdapter(zuowukuListAdapter);
                   }else{
                       listview_zuowukulist.setAdapter(null); 
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