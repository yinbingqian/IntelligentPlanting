/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lnpdit.woofarm.base.application;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.lnpdit.woofarm.page.activity.farm.GreenHouseInfoActivity;
import com.lnpdit.woofarm.page.activity.tabhost.MainTabHostActivity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class DemoApplication extends Application {

    public static Context applicationContext;
    private static DemoApplication instance = null;
    // login user name
    public final String PREF_USERNAME = "username";
    public MainTabHostActivity mainTabHostActivity;
    public static PushAgent mPushAgent;
    /**
     * 当前用户nickname,为了苹果推送不是userid而是昵称
     */
    public static String currentUserNick = "";
    
    public synchronized static PushAgent mPushAgentS() {
             return mPushAgent;
    }
 
    public synchronized static DemoApplication getInstance() {
        if (null == instance) {
            instance = new DemoApplication();
        }
        return instance;
    }

    // 运用list来保存们每一个activity是关键
    private List<Activity> mList = new LinkedList<Activity>();

    // add Activity
    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    // 关闭每一个list内的activity
    public void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // finally {
        // System.exit(0);
        // }
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
//        instance = this;
        instance = getInstance();
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);
        
        //友盟初始化PushSDK
//        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE,"0408777def01a86ad24b2b03142c4744");
        /**
        * 初始化common库
        * 参数1:上下文，不能为空
        * 参数2:【友盟+】 AppKey
        * 参数3:【友盟+】 Channel
        * 参数4:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
        * 参数5:Push推送业务的secret
        */
        UMConfigure.setLogEnabled(true);
       //UMConfigure.init(this, "5b9b60b3f29d981314000087", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "20f2cd9f515ce078db58b1a9ccdbca68");
        mPushAgent = PushAgent.getInstance(this);
       
        //友盟注册推送服务
         
        //注册推送服务，每次调用register方法都会回调该接口
         mPushAgent.register(iucall);

//        String device_token = mPushAgent.getRegistrationId();//device token是【友盟+】消息推送生成的用于标识设备的id
         
         String phoneName = android.os.Build.MODEL ;

        String deviceToken  = mPushAgent.getRegistrationId();
//        TelephonyManager telephonyManager = ( TelephonyManager )getSystemService( Context.TELEPHONY_SERVICE );  
//        String device_token = telephonyManager.getDeviceId();
        Log.i("<<<<<<<<<<<<<<<<<<<<mytoken<<<<<<<<<<<<<<<",deviceToken);
      Log.i("<<<<<<<<<<<<<<<<<<<<mytoken<<<<<<<<<<<<<<<",deviceToken);
      Log.i("<<<<<<<<<<<<<<<<<<<<mytoken<<<<<<<<<<<<<<<",deviceToken);
      Log.d("<<<<<<<<<<<<<<<<<<<<mytoken<<<<<<<<<<<<<<<",deviceToken);
      Log.d("<<<<<<<<<<<<<<<<<<<<mytoken<<<<<<<<<<<<<<<",deviceToken);
      Log.d("<<<<<<<<<<<<<<<<<<<<mytoken<<<<<<<<<<<<<<<",deviceToken);
      Log.d("<<<<<<<<<<<<<<<<<<<<mytoken<<<<<<<<<<<<<<<",deviceToken);
      Log.d("<<<<<<<<<<<<<<<<<<<<mytoken<<<<<<<<<<<<<<<",deviceToken);
      Log.d("<<<<<<<<<<<<<<<<<<<<mytoken<<<<<<<<<<<<<<<",deviceToken);
        SharedPreferences sp = applicationContext.getSharedPreferences("device", MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString("device_token",deviceToken);
        editor.commit();
        
        mPushAgent.setNotificationClickHandler(notificationClickHandler);

    }
 
    
    IUmengRegisterCallback iucall=new IUmengRegisterCallback() {

        @Override
        public void onSuccess(String deviceToken) {
            //注册成功会返回device token
            String device_token = deviceToken;
//            
            Log.d("<<<<<<<<<<<<<<<<<<<<mytoken<<<<<<<<<<<<<<<",deviceToken);
            Log.d("<<<<<<<<<<<<<<<<<<<<mytoken<<<<<<<<<<<<<<<",deviceToken);
            Log.d("<<<<<<<<<<<<<<<<<<<<mytoken<<<<<<<<<<<<<<<",deviceToken);
            Log.d("<<<<<<<<<<<<<<<<<<<<mytoken<<<<<<<<<<<<<<<",deviceToken);
            Log.d("<<<<<<<<<<<<<<<<<<<<mytoken<<<<<<<<<<<<<<<",deviceToken);
            Log.d("<<<<<<<<<<<<<<<<<<<<mytoken<<<<<<<<<<<<<<<",deviceToken);
            Log.d("<<<<<<<<<<<<<<<<<<<<mytoken<<<<<<<<<<<<<<<",deviceToken);
            Log.d("<<<<<<<<<<<<<<<<<<<<mytoken<<<<<<<<<<<<<<<",deviceToken);
            Log.d("<<<<<<<<<<<<<<<<<<<<mytoken<<<<<<<<<<<<<<<",deviceToken);
            SharedPreferences sp = applicationContext.getSharedPreferences("device", MODE_PRIVATE);
            Editor editor = sp.edit();
            editor.putString("device_token",device_token);
            editor.commit();
        }

        @Override
        public void onFailure(String s, String s1) {

        }
    };

    public void exitApp() {
            if (mainTabHostActivity != null) {
                mainTabHostActivity.finish();
            }
          
    }
    
    
    //友盟
  UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
  
          @Override
          public void dealWithCustomAction(Context context, UMessage msg) {
//              Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
//              Intent[] intents = new Intent[2];
//              Intent intent_main = new Intent(getApplicationContext(), FarmAlarmInfoListActivity.class);
//              Intent intent_target = new Intent(getApplicationContext(), FarmAlarmInfoListActivity.class);
              
            try {
                Intent intent_target = new Intent(getApplicationContext(), GreenHouseInfoActivity.class);
                
                JSONObject json_obj = new JSONObject(msg.custom.toString());
                intent_target.putExtra("pengCode", json_obj.getString("pengCode"));
                intent_target.putExtra("pengName", json_obj.getString("pengName"));
                intent_target.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intents[0] = intent_main;
//                intents[1] = intent_target;
                startActivity(intent_target);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

//            //关键的地方
//              PendingIntent contentIntent = PendingIntent.getActivities(context, 0, intents, PendingIntent.FLAG_UPDATE_CURRENT);
//
//              NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//              NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
//                  //省略其他的一些设置
//                  .setContentIntent(contentIntent);
//                  //省略其他的一些设置
//
//              Notification notification = builder.build();
//              notification.flags = Notification.FLAG_AUTO_CANCEL;
//              mNotificationManager.notify((int) System.currentTimeMillis() / 1000, notification);
          }
      };
      
}
