package com.lnpdit.woofarm.page.activity.login;

import org.json.JSONException;
import org.json.JSONObject;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.db.DBHelper;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.utils.SOAP_UTILS;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends BaseActivity implements OnClickListener {
    Context context;
    EditText username_edit;
    EditText password_edit;
    Button login_bt;
    private TextView tvRigister;
    private TextView tvForget;

    private String type;
    private DBHelper dbh;
    private PushAgent mPushAgent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;
        
        type = this.getIntent().getStringExtra("type");
        if (type.equals("in")) {
            isParentActivity = false;
        } else {
            isParentActivity = true;
        }

        dbh = new DBHelper(this);
        initView();
        
//        mPushAgent = PushAgent.getInstance(this);
//        String device_token = mPushAgent.getRegistrationId();//device token是【友盟+】消息推送生成的用于标识设备的id
        PushAgent.getInstance(context).onAppStart();//统计应用启动数据
    }

    private void initView() {
        tvRigister = (TextView) findViewById(R.id.tv_register);
        tvRigister.setOnClickListener(this);
        tvForget = (TextView) findViewById(R.id.tv_forget);
        tvForget.setOnClickListener(this);
        username_edit = (EditText) findViewById(R.id.username_edit);
        password_edit = (EditText) findViewById(R.id.password_edit);

        // 保存登陆用户名
        SharedPreferences sharedPreferences = getSharedPreferences("userinfo",
                MODE_PRIVATE);
        // 使用getString方法获得value，注意第2个参数是value的默认值
        String Id = sharedPreferences.getString("Id", "");
        String serialNum = sharedPreferences.getString("serialNum", "");
        String passWd = sharedPreferences.getString("passWd", "");
        String sex = sharedPreferences.getString("sex", "");
        String zone = sharedPreferences.getString("zone", "");
        String crTime = sharedPreferences.getString("crTime", "");
        // 使用toast信息提示框显示信息

        username_edit.setText(serialNum);
        password_edit.setText(passWd);

        login_bt = (Button) findViewById(R.id.login_bt);
        login_bt.setOnClickListener(this);

        /**
         * 限制只能输入字母和数字，默认弹出英文输入法
         */
        password_edit.setKeyListener(new DigitsKeyListener() {
            @Override
            public int getInputType() {
                return InputType.TYPE_TEXT_VARIATION_PASSWORD;
            }

            @Override
            protected char[] getAcceptedChars() {
                char[] data = getStringData(R.string.login_only_can_input)
                        .toCharArray();
                return data;
            }
        });
        username_edit.setKeyListener(new DigitsKeyListener() {
            @Override
            public int getInputType() {
                return InputType.TYPE_TEXT_VARIATION_PASSWORD;
            }

            @Override
            protected char[] getAcceptedChars() {
                char[] data = getStringData(R.string.login_only_can_input)
                        .toCharArray();
                return data;
            }
        });

    }

    public String getStringData(int id) {
        return getResources().getString(id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.login_bt:
            login_validate(username_edit.getText().toString().trim(), password_edit.getText().toString().trim());
            break;
        case R.id.tv_register:

            Intent intent = new Intent();
            intent.setClass(context, RegisterActivity.class);
            startActivity(intent);
            break;
        case R.id.tv_forget:

            intent = new Intent();
            intent.setClass(context, ForgetActivity.class);
            startActivity(intent);
            break;

        default:
            break;
        }

    }

    /**
     * 用户登录
     * 
     * @param username
     * @param password
     */
    private void login_validate(String username, String password) {
        if (username == null || username.equals("")) {
            Toast.makeText(this, "用户名为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password == null || password.equals("")) {
            Toast.makeText(this, "密码为空", Toast.LENGTH_SHORT).show();
            return;
        }
         Object[] property_va = {"true","json", username_edit.getText().toString(),
         password_edit.getText().toString() };
         soapService.userLogin(property_va);
        
//        String[] property_va = new String[] {"true","json","admin","admin" };
//        soapService.userLogin(property_va);

//        String[] property_va = new String[] {"true","json","13704016776","123456" };
//        soapService.userLogin(property_va);
    }

    /**
     * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
     * 
     * @param context
     * @return true 表示开启
     */
    public static final boolean gPSIsOPen(final Context context) {
        LocationManager locationManager = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        boolean gps = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
        boolean network = locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (gps || network) {
            return true;
        }
        return false;
    }

    @Override
    public void onEvent(Object obj) {
        // TODO Auto-generated method stub
        super.onEvent(obj);
        SoapRes res = (SoapRes) obj;
        if (res.getCode().equals(SOAP_UTILS.METHOD.LOGIN)) {
            if (res.getObj() != null) {
                try {
                JSONObject json_obj = new JSONObject(res.getObj().toString());
               
                if (json_obj.get("result").toString().equals("true")) {

                  Toast.makeText(this, "登录成功！", Toast.LENGTH_SHORT).show(); 
                  JSONObject json_user = json_obj.getJSONObject("user");
                  String id= json_user.get("id").toString();
                  String userCode= json_user.get("userCode").toString();
                  String loginCode= json_user.get("loginCode").toString();
                  String userlogo= json_user.get("avatarUrl").toString();
                  String userName= json_user.get("userName").toString();
                  
                  String sessionid= json_obj.get("sessionid").toString();
//                  UserInfo loginUser = (UserInfo) json_user.;
//                          userId = loginUser.getid();
//                          dbh.clearUserInfoData();
//                          dbh.insUserInfo(loginUser);
                 SharedPreferences sp = context
                 .getSharedPreferences("userinfo", MODE_PRIVATE);
                 Editor editor = sp.edit();
                 editor.putString("id",id);
                 editor.putString("userCode",userCode);
                 editor.putString("userName",userName);
                 editor.putString("loginCode", loginCode);
                 editor.putString("userlogo", userlogo);
                 editor.putString("sessionid", sessionid);
                 editor.commit();
//                 //友盟推送
//                 mPushAgent.addAlias(id, "liankeyounong", new UTrack.ICallBack() {
//                     @Override
//                     public void onMessage(boolean isSuccess, String message) {
//                    
//                     }
//                  });
                 
                 Intent in=new Intent();
                 setResult(2,in);
                 finish();
                 }else{

                   Toast.makeText(this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
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
