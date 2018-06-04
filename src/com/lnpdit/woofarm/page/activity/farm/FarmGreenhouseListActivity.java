package com.lnpdit.woofarm.page.activity.farm;

import java.util.ArrayList;
import java.util.List;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.db.DBHelper;
import com.lnpdit.woofarm.entity.Cart;
import com.lnpdit.woofarm.page.adapter.FarmListAdapter;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class FarmGreenhouseListActivity extends BaseActivity{
    /** Called when the activity is first created. */

    private DBHelper dbh;
    Context context;
    private Cart farm;
    private ListView farmListView;
    private List<Cart> farmList;
    private FarmListAdapter farmlistAdapter;
    
    private TextView farmname_tv;
    private TextView location_tv;
    private TextView temperature_tv;
    private TextView wind_tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_farmgreenhouselist);

        initView();
        initData();
    }

    private void initView() {
        farmListView = (ListView) findViewById(R.id.listview_farmlist);
        farmname_tv = (TextView) findViewById(R.id.farmname_tv);
        location_tv = (TextView) findViewById(R.id.location_tv);
        temperature_tv = (TextView) findViewById(R.id.temperature_tv);
        wind_tv = (TextView) findViewById(R.id.wind_tv);
        location_tv.setOnClickListener(this);

    }

    private void initData() {

        dbh = new DBHelper(this);
        farm = new Cart();
        farmList = new ArrayList<Cart>();

        farmList = dbh.queryCart();

//        farmlistAdapter = new FarmListAdapter(context, farmList);
//        farmListView.setAdapter(farmlistAdapter);
    }

}