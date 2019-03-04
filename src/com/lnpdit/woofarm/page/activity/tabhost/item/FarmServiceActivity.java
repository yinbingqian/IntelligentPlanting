package com.lnpdit.woofarm.page.activity.tabhost.item;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.page.activity.farmservice.CropListActivity;
import com.lnpdit.woofarm.page.activity.farmservice.FarmServiceListActivity2;
import com.lnpdit.woofarm.page.activity.farmservice.FarmServiceListActivity3;
import com.lnpdit.woofarm.page.activity.farmservice.FarmServiceListActivity4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class FarmServiceActivity extends BaseActivity {
    /** Called when the activity is first created. */
    Context context;

    private LinearLayout askexpert_layout;
    private RelativeLayout cropdisease_layout;
    private RelativeLayout pesticide_layout;
    private RelativeLayout soil_layout;
    private RelativeLayout plant_layout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_farmservice);
        
        
        initView();
    }

    private void initView() {
        this.isParentActivity = false;
//
        askexpert_layout = (LinearLayout) findViewById(R.id.askexpert_layout);
        askexpert_layout.setClickable(true);
        askexpert_layout.setOnClickListener(this);
        pesticide_layout = (RelativeLayout) findViewById(R.id.pesticide_layout);
        pesticide_layout.setClickable(true);
        pesticide_layout.setOnClickListener(this);
        
        soil_layout = (RelativeLayout) findViewById(R.id.soil_layout);
        soil_layout.setClickable(true);
        soil_layout.setOnClickListener(this);

        plant_layout = (RelativeLayout) findViewById(R.id.plant_layout);
        plant_layout.setClickable(true);
        plant_layout.setOnClickListener(this);

        cropdisease_layout = (RelativeLayout) findViewById(R.id.cropdisease_layout);
        cropdisease_layout.setClickable(true);
        cropdisease_layout.setOnClickListener(this);
//        myaddress_layout = (LinearLayout) findViewById(R.id.myaddress_layout);
//        myaddress_layout.setOnClickListener(this);
//        myorder_layout = (LinearLayout) findViewById(R.id.myorder_layout);
//        myorder_layout.setOnClickListener(this);
//        password_layout = (LinearLayout) findViewById(R.id.password_layout);
//        password_layout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.askexpert_layout:
            
//            Intent intent_askexpert = new Intent();
//            intent_askexpert.setClass(context, BasicInfoActivity.class);
//            startActivity(intent_askexpert);
            break;
        case R.id.cropdisease_layout:

            Intent intent_crop = new Intent();
            intent_crop.setClass(context, CropListActivity.class);
            startActivity(intent_crop);
            break;
        case R.id.pesticide_layout:
            
            Intent intent_pesticide = new Intent();
            intent_pesticide.setClass(context, FarmServiceListActivity2.class);
            startActivity(intent_pesticide);
            break;
        case R.id.soil_layout:
            
            Intent intent_soil = new Intent();
            intent_soil.setClass(context, FarmServiceListActivity3.class);
            startActivity(intent_soil);
            break;
        case R.id.plant_layout:
      
      Intent intent_plant = new Intent();
      intent_plant.setClass(context, FarmServiceListActivity4.class);
      startActivity(intent_plant);
      break;
        default:
            break;
        }

    }

//    @Override
//    public void onEvent(Object obj) {
//        // TODO Auto-generated method stub
//        super.onEvent(obj);
//        SoapRes res = (SoapRes) obj;
//        if (res.getCode().equals(SOAP_UTILS.METHOD.LOGOUT)) {
//            if (res.getObj() != null) {
//                try {
//                JSONObject json_obj = new JSONObject(res.getObj().toString());
//               
//                if (json_obj.get("result").toString().equals("true")) {
//
//                  Toast.makeText(this, json_obj.get("message").toString(), Toast.LENGTH_SHORT).show(); 
//                  
//                  SharedPreferences sp = context
//                          .getSharedPreferences("userinfo", MODE_PRIVATE);
//                          Editor editor = sp.edit();
//                          editor.putString("userCode","");
//                          editor.putString("loginCode", "");
//                          editor.putString("userlogo", "");
//                          editor.commit();
//
//                          intent = new Intent();
//                          intent.setClass(context, FarmLoginActivity.class);
//                          startActivity(intent);
//                  finish();
//                 }else{
//
//                     Toast.makeText(this, json_obj.get("message").toString(), Toast.LENGTH_SHORT).show(); 
//                 }
//                } catch (JSONException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//
//            }else{
//                Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
    
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}