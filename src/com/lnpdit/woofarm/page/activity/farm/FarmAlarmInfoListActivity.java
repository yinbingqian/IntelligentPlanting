package com.lnpdit.woofarm.page.activity.farm;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.application.DemoApplication;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.db.DBHelper;
import com.lnpdit.woofarm.entity.FarmAlarmInfoList;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.page.adapter.FarmAlarmInfoListAdapter;
import com.lnpdit.woofarm.utils.SOAP_UTILS;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FarmAlarmInfoListActivity extends BaseActivity implements OnClickListener {
    /** Called when the activity is first created. */

    private DBHelper dbh;
    private FarmAlarmInfoList farm;
    private List<FarmAlarmInfoList> farmList = new ArrayList<FarmAlarmInfoList>();
    private ListView listview_farmlist;
    private FarmAlarmInfoListAdapter farmListAdapter;
    private TextView tv_back;
    Context context;
    private String farmname = "";
    private String farmcode = "";
    private String pengCode = "";
    public PushAgent mPushAgent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_farmalarminfolist);

        Intent intent = getIntent();
        pengCode = intent.getStringExtra("pengCode");
        initView();
        initData();
        setListeners();

        mPushAgent = DemoApplication.mPushAgentS();
//        mPushAgent.setNotificationClickHandler(notificationClickHandler);
    }

    private void initView() {
        listview_farmlist = (ListView) findViewById(R.id.listview_farmlist);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setClickable(true);
        tv_back.setOnClickListener(this);

    }

    private void initData() {

        String[] property_va = new String[] {pengCode };
        soapService.getAlarmInfo(property_va);
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

            }
        });
    }
   @Override
   public void onEvent(Object obj) {
       // TODO Auto-generated method stub
       super.onEvent(obj);
       SoapRes res = (SoapRes) obj;
       if (res.getCode().equals(SOAP_UTILS.METHOD.ALARMINFO)) {
           if (res.getObj() != null) {
               try {
               JSONObject json_objs = new JSONObject(res.getObj().toString());
      
               if (json_objs.get("result").toString().equals("true")) {
                   farmList.clear();
                   JSONArray json_arr = json_objs.getJSONArray("data");
                   for (int i = 0; i < json_arr.length(); i++) {
                       JSONObject json_obj= (JSONObject) json_arr.get(i);
                       FarmAlarmInfoList farm = new FarmAlarmInfoList();
                       farm.setCreateDate(json_obj.getString("createDate"));
                       farm.setPengName(json_obj.getString("pengName"));
                       farm.setEquipName(json_obj.getString("equipName"));
                       farm.setAlarmInfo(json_obj.getString("alarmInfo"));
                       farm.setAlarmType(json_obj.getString("alarmType"));
                       farmList.add(farm);
                   }

                   if(farmList.size()!=0){

                       farmListAdapter = new FarmAlarmInfoListAdapter(context, farmList);
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
   
//   //友盟
// UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
// 
//         @Override
//         public void dealWithCustomAction(Context context, UMessage msg) {
//             Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
////             Intent[] intents = new Intent[2];
////             Intent intent_main = new Intent(getApplicationContext(), MainTabHostActivity.class);
//             Intent intent_target = new Intent(getApplicationContext(), FarmAlarmInfoListActivity.class);
//             intent_target.putExtra("pengCode", intent_target);
////             intents[0] = intent_main;
////             intents[1] = intent_target;
//             intent_target.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//             startActivity(intent_target);
//         }
//     };
}