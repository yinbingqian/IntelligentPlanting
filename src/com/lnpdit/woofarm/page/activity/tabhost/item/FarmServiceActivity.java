package com.lnpdit.woofarm.page.activity.tabhost.item;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.component.BaseActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

public class FarmServiceActivity extends BaseActivity {
    /** Called when the activity is first created. */
    Context context;

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
//        logout_layout = (LinearLayout) findViewById(R.id.logout_layout);
//        logout_layout.setOnClickListener(this);
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