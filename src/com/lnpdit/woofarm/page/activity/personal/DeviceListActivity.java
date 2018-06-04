package com.lnpdit.woofarm.page.activity.personal;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.db.DBHelper;
import com.lnpdit.woofarm.entity.DeviceList;
import com.lnpdit.woofarm.entity.FarmList;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.page.adapter.DeviceListAdapter;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DeviceListActivity extends BaseActivity implements OnClickListener {
    /** Called when the activity is first created. */

    private DBHelper dbh;
    private DeviceList device;
    private List<DeviceList> deviceList = new ArrayList<DeviceList>();
    private ListView listview_devicelist;
    private DeviceListAdapter deviceListAdapter;
    private TextView tv_back;
    Context context;
    private String farmname = "";
    private String farmcode = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_devicelist);

        initView();
        initData();
        setListeners();
    }

    private void initView() {
        listview_devicelist = (ListView) findViewById(R.id.listview_devicelist);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setClickable(true);
        tv_back.setOnClickListener(this);

    }

    private void initData() {

        SharedPreferences sharedPreferences = getSharedPreferences("userinfo",Activity.MODE_PRIVATE);  
        String userCode = sharedPreferences.getString("userCode","");  
        String[] property_va = new String[] {userCode };
        soapService.getDeviceList(property_va);
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
        listview_devicelist.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Click Towns");
//                farmname = farmList.get(position).getFarmName();
//                farmcode = farmList.get(position).getFarmCode();
////                SharedPreferences sp = getSharedPreferences("farm", Context.MODE_PRIVATE); 
////                Editor editor = sp.edit();
////                editor.putString("farmname", farmname);
////                editor.putString("farmcode", farmcode);
////                editor.commit();
//                Intent intent = new Intent();
//                intent.putExtra("farmname", farmname);
//                intent.putExtra("farmcode", farmcode); //将计算的值回传回去
//                ((Activity) context).setResult(3,intent);
//                finish();
            }
        });
    }
    
   @Override
   public void onEvent(Object obj) {
       // TODO Auto-generated method stub
       super.onEvent(obj);
       SoapRes res = (SoapRes) obj;
       if (res.getCode().equals(SOAP_UTILS.METHOD.DEVICELIST)) {
           if (res.getObj() != null) {
               try {
               JSONObject json_objs = new JSONObject(res.getObj().toString());
      
               if (json_objs.get("result").toString().equals("true")) {
                   deviceList.clear();
                   JSONArray json_arr = json_objs.getJSONArray("data");
                   for (int i = 0; i < json_arr.length(); i++) {
                       JSONObject json_obj= (JSONObject) json_arr.get(i);
                       DeviceList farm = new DeviceList();
                       farm.setId(json_obj.getString("id"));
                       farm.setEquipCode(json_obj.getString("equipCode"));
                       farm.setEquipName(json_obj.getString("equipName"));
                       farm.setEquipStatus(json_obj.getString("equipStatus"));
                       farm.setPengName(json_obj.getString("pengName"));
                       farm.setFarmName(json_obj.getString("farmName"));
                       deviceList.add(farm);
                   }

                   if(deviceList.size()!=0){

                       deviceListAdapter = new DeviceListAdapter(context, deviceList);
                       listview_devicelist.setAdapter(deviceListAdapter);
                   }else{
                       listview_devicelist.setAdapter(null); 
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