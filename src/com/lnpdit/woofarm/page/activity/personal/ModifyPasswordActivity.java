package com.lnpdit.woofarm.page.activity.personal;

import org.json.JSONObject;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.md5.MD5Plus;
import com.lnpdit.woofarm.page.activity.login.LoginActivity;
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

public class ModifyPasswordActivity extends BaseActivity
        implements OnClickListener {
    Context context;
    private TextView tv_back;
    private EditText newpwd_edit;
    private EditText renewpwd_edit;
    private Button next_btn;
    private String sessionid = "";
    private String userCode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);

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
        newpwd_edit = (EditText) findViewById(R.id.newpwd_edit);
        renewpwd_edit = (EditText) findViewById(R.id.renewpwd_edit);
        next_btn = (Button) findViewById(R.id.next_btn);
        next_btn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.tv_back:
            finish();
            break;
        case R.id.next_btn:
            if ( newpwd_edit.getText().toString().trim().equals("")
                    || renewpwd_edit.getText().toString().trim().equals("")) {
                Toast.makeText(context, R.string.Password_cannot_be_empty, Toast.LENGTH_SHORT).show();
                break;
            }
            if (newpwd_edit.getText().toString().trim().length() > 15 || newpwd_edit.getText().toString().trim().length() < 6) {

                Toast.makeText(context, R.string.input_password, Toast.LENGTH_SHORT).show();
                break;
            }
            if (!newpwd_edit.getText().toString().trim().equals(renewpwd_edit.getText().toString().trim())) {

                Toast.makeText(context, R.string.Two_input_password, Toast.LENGTH_SHORT).show();
                break;
            }
            String[] property_va = new String[] { userCode,
                    newpwd_edit.getText().toString().trim(),"json", sessionid };
            soapService.updatePassword(property_va);
            break;
        default:
            break;
        }

    }

    public void onEvent(SoapRes obj) {
        if (obj.getCode().equals(SOAP_UTILS.METHOD.UPDATEPWD)) {
            if (obj.getObj() != null) {
                try {
                    JSONObject json_obj = new JSONObject(
                            obj.getObj().toString());

                    String result = json_obj.get("result").toString();
                    String message = json_obj.get("message").toString();
                    if (result.equals("true")) {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT)
                                .show();
//                        dbh.clearUserInfoData();
                        intent = new Intent();
                        intent.setClass(context, LoginActivity.class);
                        intent.putExtra("type", "out");
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
