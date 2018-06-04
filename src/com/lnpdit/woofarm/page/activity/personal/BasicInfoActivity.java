package com.lnpdit.woofarm.page.activity.personal;

import com.lnpdit.IntelligentPlanting.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BasicInfoActivity extends Activity implements OnClickListener {
    Context context;
    private TextView tv_back;
    private RelativeLayout modifypassword_layout;
    private TextView username_tv;
    private TextView phone_tv;
    private EditText email_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basicinfo);

        context = this;
        initView();

    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setClickable(true);
        tv_back.setOnClickListener(this);
        modifypassword_layout = (RelativeLayout) findViewById(R.id.modifypassword_layout);
        modifypassword_layout.setClickable(true);
        modifypassword_layout.setOnClickListener(this);
        username_tv = (TextView) findViewById(R.id.username_tv);
        phone_tv = (TextView) findViewById(R.id.phone_tv);
        email_edit = (EditText) findViewById(R.id.email_edit);

        SharedPreferences sharedPreferences = getSharedPreferences("userinfo",
                MODE_PRIVATE);
        String userCode = sharedPreferences.getString("userCode", "");
        username_tv.setText(userCode);
        phone_tv.setText(userCode);
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
        case R.id.modifypassword_layout:

            Intent intent= new Intent();
            intent.setClass(context, OldPasswordActivity.class);
            startActivity(intent);
            finish();
            break;

        default:
            break;
        }

    }

}
