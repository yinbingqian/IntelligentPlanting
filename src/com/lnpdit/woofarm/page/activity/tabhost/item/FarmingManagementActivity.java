package com.lnpdit.woofarm.page.activity.tabhost.item;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.page.activity.farmingmanagement.GrowListActivity;
import com.lnpdit.woofarm.page.activity.farmingmanagement.PickListActivity;
import com.lnpdit.woofarm.page.activity.farmingmanagement.PlantingManagementListActivity;
import com.lnpdit.woofarm.page.activity.farmingmanagement.ZuowukuListActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class FarmingManagementActivity extends BaseActivity {
    /** Called when the activity is first created. */
    Context context;
//    private LinearLayout planting_layout;
//    private LinearLayout zuowuku_layout;
//    private LinearLayout plan_layout;
//    private LinearLayout origin_layout;
    private RelativeLayout dingzhi_layout;
    private RelativeLayout shengzhang_layout;
    private RelativeLayout caishou_layout;
    private RelativeLayout jiancha_layout;
    private RelativeLayout xiaoshou_layout;
    private RelativeLayout dikuai_layout;
    private RelativeLayout renyuan_layout;
    private RelativeLayout zuowuku_layout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_farmingmanagementnew);
        
        initView();
    }

    private void initView() {
        this.isParentActivity = false;

        dingzhi_layout = (RelativeLayout) findViewById(R.id.dingzhi_layout);
        dingzhi_layout.setOnClickListener(this);
        shengzhang_layout = (RelativeLayout) findViewById(R.id.shengzhang_layout);
        shengzhang_layout.setOnClickListener(this);
        caishou_layout = (RelativeLayout) findViewById(R.id.caishou_layout);
        caishou_layout.setOnClickListener(this);
        jiancha_layout = (RelativeLayout) findViewById(R.id.jiancha_layout);
        jiancha_layout.setOnClickListener(this);
        xiaoshou_layout = (RelativeLayout) findViewById(R.id.xiaoshou_layout);
        xiaoshou_layout.setOnClickListener(this);
        dikuai_layout = (RelativeLayout) findViewById(R.id.dikuai_layout);
        dikuai_layout.setOnClickListener(this);
        renyuan_layout = (RelativeLayout) findViewById(R.id.renyuan_layout);
        renyuan_layout.setOnClickListener(this);
        zuowuku_layout = (RelativeLayout) findViewById(R.id.zuowuku_layout);
        zuowuku_layout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.zuowuku_layout:

            Intent intent_order = new Intent();
            intent_order.setClass(context, ZuowukuListActivity.class);
            startActivity(intent_order);
            break;
        case R.id.dingzhi_layout:

            Intent intent = new Intent();
            intent.setClass(context, PlantingManagementListActivity.class);
            startActivity(intent);
            break;
        case R.id.shengzhang_layout:

            Intent intent_grow = new Intent();
            intent_grow.setClass(context, GrowListActivity.class);
            startActivity(intent_grow);
            break;
        case R.id.caishou_layout:

            Intent intent_pick = new Intent();
            intent_pick.setClass(context, PickListActivity.class);
            startActivity(intent_pick);
          break;
        case R.id.jiancha_layout:

//            Intent intent_pick = new Intent();
//            intent_pick.setClass(context, PickListActivity.class);
//            startActivity(intent_pick);
            break;
        case R.id.xiaoshou_layout:

//          Intent intent_pick = new Intent();
//          intent_pick.setClass(context, PickListActivity.class);
//          startActivity(intent_pick);
            break;
        case R.id.dikuai_layout:

//          Intent intent_pick = new Intent();
//          intent_pick.setClass(context, PickListActivity.class);
//          startActivity(intent_pick);
            break;
        case R.id.renyuan_layout:

//          Intent intent_pick = new Intent();
//          intent_pick.setClass(context, PickListActivity.class);
//          startActivity(intent_pick);
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