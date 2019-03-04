package com.lnpdit.woofarm.page.activity.tabhost.item;

import org.json.JSONException;
import org.json.JSONObject;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.application.DemoApplication;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.customview.FocusedtrueTV;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.page.activity.personal.AboutUsActivity;
import com.lnpdit.woofarm.page.activity.personal.BasicInfoActivity;
import com.lnpdit.woofarm.page.activity.personal.DeviceListActivity;
import com.lnpdit.woofarm.page.activity.tabhost.MainTabHostActivity;
import com.lnpdit.woofarm.page.activity.tabhost.TabHostAbstractActivity;
import com.lnpdit.woofarm.utils.SOAP_UTILS;
import com.pgyersdk.feedback.PgyFeedbackShakeManager;
import com.pgyersdk.update.PgyUpdateManager;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalActivity extends BaseActivity {
    /** Called when the activity is first created. */
    private FocusedtrueTV tv_title;
    Context context;
    private LinearLayout logout_layout;
    private LinearLayout basicinfo_layout;
    private LinearLayout eqiupment_layout;
    private LinearLayout aboutus_layout;
    private LinearLayout function_layout;
    private LinearLayout veision_layout;
    private TextView username_tv;
    private String sessionid = "";
    private String userName= "";
    private PushAgent mPushAgent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_personal);
        
        SharedPreferences sharedPreferences = getSharedPreferences("userinfo",Activity.MODE_PRIVATE);  
        sessionid = sharedPreferences.getString("sessionid",""); 
        userName = sharedPreferences.getString("userName",""); 
        
        initView();
        mPushAgent = DemoApplication.mPushAgentS();
    }

    private void initView() {
        this.isParentActivity = false;

        logout_layout = (LinearLayout) findViewById(R.id.logout_layout);
        logout_layout.setClickable(true);
        logout_layout.setOnClickListener(this);
        basicinfo_layout = (LinearLayout) findViewById(R.id.basicinfo_layout);
        basicinfo_layout.setClickable(true);
        basicinfo_layout.setOnClickListener(this);
        eqiupment_layout = (LinearLayout) findViewById(R.id.eqiupment_layout);
        eqiupment_layout.setClickable(true);
        eqiupment_layout.setOnClickListener(this);

        aboutus_layout = (LinearLayout) findViewById(R.id.aboutus_layout);
        aboutus_layout.setClickable(true);
        aboutus_layout.setOnClickListener(this);
        function_layout = (LinearLayout) findViewById(R.id.function_layout);
        function_layout.setClickable(true);
        function_layout.setOnClickListener(this);
        veision_layout = (LinearLayout) findViewById(R.id.veision_layout);
        veision_layout.setClickable(true);
        veision_layout.setOnClickListener(this);
        username_tv = (TextView) findViewById(R.id.username_tv);
        if(userName.isEmpty()){

            username_tv.setText("");
        }else{

            username_tv.setText(userName);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.basicinfo_layout:

            Intent intent_basicinfo = new Intent();
            intent_basicinfo.setClass(context, BasicInfoActivity.class);
            startActivity(intent_basicinfo);
            break;

        case R.id.aboutus_layout:

            Intent intent_aboutus = new Intent();
            intent_aboutus.setClass(context, AboutUsActivity.class);
            startActivity(intent_aboutus);
            break;
        case R.id.eqiupment_layout:

          Intent intent_device = new Intent();
          intent_device.setClass(context, DeviceListActivity.class);
          startActivity(intent_device);
          break;
        case R.id.function_layout:

//          intent = new Intent();
//          intent.setClass(context, OldPasswordActivity.class);
//          startActivity(intent);
          break;
        case R.id.veision_layout:
            PgyUpdateManager.register(this);//更新
            // 自定义摇一摇的灵敏度，默认为950，数值越小灵敏度越高。
            PgyFeedbackShakeManager.setShakingThreshold(700);

            // 以对话框的形式弹出
            PgyFeedbackShakeManager.register(PersonalActivity.this);

            // 以Activity的形式打开，这种情况下必须在AndroidManifest.xml配置FeedbackActivity
            // 打开沉浸式,默认为false
            // FeedbackActivity.setBarImmersive(true);
//          PgyFeedbackShakeManager.register(CooperativesActivity.this, false);

//          intent = new Intent();
//          intent.setClass(context, OldPasswordActivity.class);
//          startActivity(intent);
          break;
        case R.id.logout_layout:

            String[] property_va = new String[] {"json",sessionid };
            soapService.logout(property_va);

            break;

        default:
            break;
        }

    }

    @Override
    public void onEvent(Object obj) {
        // TODO Auto-generated method stub
        super.onEvent(obj);
        SoapRes res = (SoapRes) obj;
        if (res.getCode().equals(SOAP_UTILS.METHOD.LOGOUT)) {
            if (res.getObj() != null) {
                try {
                JSONObject json_obj = new JSONObject(res.getObj().toString());
               
                if (json_obj.get("result").toString().equals("true")) {

                  Toast.makeText(this, json_obj.get("message").toString(), Toast.LENGTH_SHORT).show(); 

                  SharedPreferences sharedPreferences = getSharedPreferences("userinfo",Activity.MODE_PRIVATE);  
                  String id = sharedPreferences.getString("id","");  
                  
                 //友盟 若要使用新的alias，请先调用deleteAlias接口移除掉旧的alias，再调用addAlias添加新的alias
                  mPushAgent.deleteAlias(id, "lecyon", new UTrack.ICallBack() {
                      @Override
                      public void onMessage(boolean isSuccess, String message) {
                          
                      }
                   });
                  
                  SharedPreferences sp = context
                          .getSharedPreferences("userinfo", MODE_PRIVATE);
                          Editor editor = sp.edit();
                          editor.putString("userCode","");
                          editor.putString("loginCode", "");
                          editor.putString("userlogo", "");
                          editor.putString("id", "");
                          editor.commit();

                          username_tv.setText("");
                          
                          
//                          intent = new Intent();
//                          intent.putExtra("locationCur", "2");
//                          intent.setClass(context, MainTabHostActivity.class);
//                          startActivity(intent);
                          TabHostAbstractActivity.setCurrentTab(2);
                          MainTabHostActivity.restore(4);
                          MainTabHostActivity.myList.get(2).setBackgroundResource(R.drawable.farm);
//                          finish();
                 }else{

                     Toast.makeText(this, json_obj.get("message").toString(), Toast.LENGTH_SHORT).show(); 
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
    
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("userinfo",Activity.MODE_PRIVATE);  
        userName = sharedPreferences.getString("userName",""); 
        if(userName.isEmpty()){

            username_tv.setText("");
        }else{

            username_tv.setText(userName);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        PgyFeedbackShakeManager.unregister();
    }
 
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}