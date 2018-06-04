package com.lnpdit.woofarm.page.activity.tabhost.item;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.page.activity.farmingmanagement.PlantingManagementListActivity;
import com.lnpdit.woofarm.page.activity.farmingmanagement.ZuowukuListActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class FarmingManagementActivity extends BaseActivity {
    /** Called when the activity is first created. */
    Context context;
    private LinearLayout planting_layout;
    private LinearLayout zuowuku_layout;
    private LinearLayout plan_layout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_farmingmanagement);
        
        initView();
    }

    private void initView() {
        this.isParentActivity = false;

        planting_layout = (LinearLayout) findViewById(R.id.planting_layout);
        planting_layout.setOnClickListener(this);
        zuowuku_layout = (LinearLayout) findViewById(R.id.zuowuku_layout);
        zuowuku_layout.setOnClickListener(this);
        plan_layout = (LinearLayout) findViewById(R.id.plan_layout);
        plan_layout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.zuowuku_layout:

            Intent intent_order = new Intent();
            intent_order.setClass(context, ZuowukuListActivity.class);
            startActivity(intent_order);
            break;
        case R.id.planting_layout:

            Intent intent = new Intent();
            intent.setClass(context, PlantingManagementListActivity.class);
            startActivity(intent);
            break;
        case R.id.plan_layout:

//            intent = new Intent();
//            intent.setClass(context, OldPasswordActivity.class);
//            startActivity(intent);
            break;

        default:
            break;
        }

    }

    
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