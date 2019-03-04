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
import com.lnpdit.woofarm.entity.GrowCropList;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.page.adapter.GrowCropListAdapter;
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

public class GrowCropListActivity extends BaseActivity implements OnClickListener {
    /** Called when the activity is first created. */

    private DBHelper dbh;
    private FarmList farm;
    private List<GrowCropList> farmList = new ArrayList<GrowCropList>();
    private ListView listview_farmlist;
    private GrowCropListAdapter farmListAdapter;
    private TextView tv_back;
    Context context;
    private String plantName = "";
    private String plantId = "";
    private String userCode="";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_farmlist);

        initView();
        initData();
        setListeners();
    }

    private void initView() {
        listview_farmlist = (ListView) findViewById(R.id.listview_farmlist);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setClickable(true);
        tv_back.setOnClickListener(this);

    }

    private void initData() {

        SharedPreferences sharedPreferences = getSharedPreferences("userinfo",Activity.MODE_PRIVATE);  
        userCode = sharedPreferences.getString("userCode","");  

            String[] property_va = new String[] {userCode };
            soapService.getGrowList(property_va);
   
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
        listview_farmlist.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Click Towns");
                plantName = farmList.get(position).getPlantName();
                plantId = farmList.get(position).getPlantId();
                SharedPreferences sp = getSharedPreferences("grow", Context.MODE_PRIVATE); 
                Editor editor = sp.edit();
                editor.putString("plantName", plantName);
                editor.putString("plantId", plantId);
                editor.commit();
                Intent intent = new Intent();
                intent.putExtra("plantName", plantName);
                intent.putExtra("plantId", plantId); //将计算的值回传回去
                ((Activity) context).setResult(3,intent);
                finish();
            }
        });
    }
   @Override
   public void onEvent(Object obj) {
       // TODO Auto-generated method stub
       super.onEvent(obj);
       SoapRes res = (SoapRes) obj;
       if (res.getCode().equals(SOAP_UTILS.METHOD.GETGROWLIST)) {
           if (res.getObj() != null) {
               try {
               JSONObject json_objs = new JSONObject(res.getObj().toString());
      
               if (json_objs.get("result").toString().equals("true")) {
                   farmList.clear();
                   JSONArray json_arr = json_objs.getJSONArray("data");
                   for (int i = 0; i < json_arr.length(); i++) {
                       JSONObject json_obj= (JSONObject) json_arr.get(i);
                       GrowCropList farm = new GrowCropList();
                       farm.setPlantId(json_obj.getString("plantId"));
                       farm.setPlantName(json_obj.getString("plantName"));
                       farmList.add(farm);
                   }

                   if(farmList.size()!=0){

                       farmListAdapter = new GrowCropListAdapter(context, farmList);
                       listview_farmlist.setAdapter(farmListAdapter);
                   }else{
                       listview_farmlist.setAdapter(null); 
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