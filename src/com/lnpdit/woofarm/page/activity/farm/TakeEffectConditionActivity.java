package com.lnpdit.woofarm.page.activity.farm;

import java.util.ArrayList;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.page.activity.farminformation.FarmFruitFragment;
import com.lnpdit.woofarm.page.activity.farminformation.FarmGrainFragment;
import com.lnpdit.woofarm.page.activity.farminformation.FarmNewsFragment;
import com.lnpdit.woofarm.page.activity.farminformation.FarmVegetablesFragment;
import com.lnpdit.woofarm.page.activity.tabhost.item.custom.TabSwipPager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TakeEffectConditionActivity extends FragmentActivity implements OnClickListener  {
    /** Called when the activity is first created. */
    private LinearLayout llTabSwipPager;
    private TabSwipPager tabSwipPager;
    private TextView tv_back;
    private TextView submit_tv;
    
    private ArrayList<Fragment> fragmentsList;
    private String[] tags;
    boolean friendpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takeeffectcondition);

//        Intent intent = this.getIntent();
//        friendpage = intent.getBooleanExtra("friendpage", true);

        initView();
    }

    private void initView() {
        initData();

        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setClickable(true);
        tv_back.setOnClickListener(this);
        submit_tv = (TextView) findViewById(R.id.submit_tv);
        submit_tv.setClickable(true);
        submit_tv.setOnClickListener(this);

        llTabSwipPager = (LinearLayout) findViewById(R.id.llTabSwipPager);

        tabSwipPager = new TabSwipPager(getApplicationContext(), getSupportFragmentManager(), llTabSwipPager);
        if (!tabSwipPager.setFragmentList(fragmentsList, tags)) {
            System.out.println("初始化失败");
            finish();
        }
    }

    private void initData() {
        fragmentsList = new ArrayList<Fragment>();
        fragmentsList.add(new TimeConditionFragment());
        fragmentsList.add(new EnvironmentConditionFragment());

        tags = new String[] { "时间条件", "环境条件" };

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.tv_back:
            finish();
            break;
        case R.id.submit_tv:
            finish();
            break;
        default:
            break;
        }
    }
}