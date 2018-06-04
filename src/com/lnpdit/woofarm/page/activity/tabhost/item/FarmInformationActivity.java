package com.lnpdit.woofarm.page.activity.tabhost.item;

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
import android.widget.LinearLayout;

public class FarmInformationActivity extends FragmentActivity {
    /** Called when the activity is first created. */
    private LinearLayout llTabSwipPager;
//    private ImageView img_back;
    private TabSwipPager tabSwipPager;

    private ArrayList<Fragment> fragmentsList;
    private String[] tags;
    boolean friendpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farminformation);

        Intent intent = this.getIntent();
        friendpage = intent.getBooleanExtra("friendpage", true);

        initView();
    }

    private void initView() {
        initData();

        llTabSwipPager = (LinearLayout) findViewById(R.id.llTabSwipPager);

        tabSwipPager = new TabSwipPager(getApplicationContext(), getSupportFragmentManager(), llTabSwipPager);
        if (!tabSwipPager.setFragmentList(fragmentsList, tags)) {
            System.out.println("初始化失败");
            finish();
        }
    }

    private void initData() {
        fragmentsList = new ArrayList<Fragment>();
        fragmentsList.add(new FarmNewsFragment());
        fragmentsList.add(new FarmVegetablesFragment());
        fragmentsList.add(new FarmFruitFragment());
        fragmentsList.add(new FarmGrainFragment());

        tags = new String[] { "头条新闻", "蔬菜菌类", "生鲜瓜果","粮油饲料" };

    }
}