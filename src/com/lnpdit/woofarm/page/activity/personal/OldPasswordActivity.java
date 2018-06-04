package com.lnpdit.woofarm.page.activity.personal;

import org.json.JSONObject;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.md5.MD5Plus;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OldPasswordActivity extends BaseActivity implements OnClickListener {
    Context context;
    private TextView tv_back;
    private EditText oldpwd_edit;
    private Button next_btn;
    private String sessionid = "";
    private String userCode = "";
    private String password = "";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatepwd);

        context = this;

        SharedPreferences sharedPreferences = getSharedPreferences("userinfo",
                MODE_PRIVATE);
        sessionid = sharedPreferences.getString("sessionid", "");
        userCode = sharedPreferences.getString("userCode", "");

        initView();

    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setClickable(true);
        tv_back.setOnClickListener(this);
        oldpwd_edit = (EditText) findViewById(R.id.oldpwd_edit);
        next_btn = (Button) findViewById(R.id.next_btn);
        next_btn.setOnClickListener(this);

    }

    public String getStringData(int id) {
        return getResources().getString(id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.tv_back:
            finish();
            break;
        case R.id.next_btn:
            
            if (userCode.equals("") || userCode.equals(null)) {

                Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
            } else {
                password = oldpwd_edit.getText().toString();
                if (password.equals("") || password.equals(null)) {

                    Toast.makeText(this, "请输入旧密码", Toast.LENGTH_SHORT).show();
                } else {

                    String[] property_va = new String[] { userCode,
                            password,"json", sessionid};
                    soapService.validatePassword(property_va);

                }
            }

            break;

        default:
            break;
        }

    }
    public void onEvent(SoapRes obj) {
        if (obj.getCode().equals(SOAP_UTILS.METHOD.VALIDATEPWD)) {
            if (obj.getObj() != null) {
                try {
                    JSONObject json_obj = new JSONObject(
                            obj.getObj().toString());

                    String result = json_obj.get("result").toString();
                    String message = json_obj.get("message").toString();
                    if (result.equals("true")) {
                         Toast.makeText(context, message, Toast.LENGTH_SHORT)
                         .show();

                        Intent intent = new Intent();
//                        intent.putExtra("type", "modify");
                        intent.setClass(context, ModifyPasswordActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT)
                                .show();
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
            }
        }
    }
  
}
