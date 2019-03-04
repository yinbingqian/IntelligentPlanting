package com.lnpdit.woofarm.page.activity.farmingmanagement;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.db.DBHelper;
import com.lnpdit.woofarm.entity.FarmPlanList;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.page.adapter.FarmPlanFinishListAdapter;
import com.lnpdit.woofarm.page.adapter.FarmPlanFinishedListAdapter;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FarmPlanListActivity extends BaseActivity implements OnClickListener  {
    /** Called when the activity is first created. */

    private DBHelper dbh;
    private FarmPlanList farm;
    private List<FarmPlanList> farmList = new ArrayList<FarmPlanList>();
    private List<FarmPlanList> farmplanfinishedList = new ArrayList<FarmPlanList>();
    private ListView listview_finish;
    private ListView listview_finished;
    private FarmPlanFinishListAdapter farmListAdapter;
    private FarmPlanFinishedListAdapter farmplanfinishedAdapter;
    View headerView = null;
    View footView = null;
    private RelativeLayout finished_layout;
    private ImageView finished_img;
    private TextView tv_back;
    Context context;
    private String farmname = "";
    private String farmcode = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
            setContentView(R.layout.activity_farmplan);

            initView();
            initData();
            setListeners();
        }

        private void initView() {
            
            headerView = LayoutInflater.from(this).inflate(R.layout.activity_farmplanhead, null);
            footView = LayoutInflater.from(this).inflate(R.layout.activity_farmplanfoot, null);
            
            listview_finish = (ListView) findViewById(R.id.listview_finish);
            listview_finish.setFocusable(false);
            
            listview_finished = (ListView) footView.findViewById(R.id.listview_finished);
            listview_finished.setFocusable(false);

            tv_back = (TextView)headerView.findViewById(R.id.tv_back);//待完成
            tv_back.setClickable(true);
            tv_back.setOnClickListener(this);
//            listview_finish = (ListView) findViewById(R.id.listview_finish);
            finished_layout = (RelativeLayout)footView. findViewById(R.id.finished_layout);
//            finished_img = (ImageView)footView. findViewById(R.id.finished_img);
//            listview_finish.setFocusable(false);

//            headerView = LayoutInflater.from(this).inflate(R.layout.activity_farmplanhead, null);
//            listview_finish = (ListView) headerView.findViewById(R.id.listview_finish);
//            finish_layout = (RelativeLayout) headerView.findViewById(R.id.finish_layout);
//            finish_img = (ImageView) headerView.findViewById(R.id.finish_img);
//            listview_finish.setFocusable(false);
            listview_finish.addHeaderView(headerView);
            
            listview_finish.addFooterView(footView);
        }

        private void initData() {

            dbh = new DBHelper(this);
            
            SharedPreferences sharedPreferences = getSharedPreferences("userinfo",Activity.MODE_PRIVATE);  
            String userCode = sharedPreferences.getString("userCode","");  
            String[] property_va = new String[] {userCode };
            soapService.getPlanlist(property_va);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            default:
                break;
            }
        }
        
        private void setListeners() {
            listview_finish.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    System.out.println("Click Towns");

//                    Intent in_camera1 = new Intent();
//                    in_camera1.putExtra("cameraId", cameraId1);
//                    in_camera1.setClass(GreenHouseInfoActivity.this, CameraInfo_Activity.class);
//                    startActivity(in_camera1);
                }
            });
        }
       @Override
       public void onEvent(Object obj) {
           // TODO Auto-generated method stub
           super.onEvent(obj);
           SoapRes res = (SoapRes) obj;
           if (res.getCode().equals(SOAP_UTILS.METHOD.PLANLIST)) {//农事计划列表
               if (res.getObj() != null) {
                   try {
                   JSONObject json_objs = new JSONObject(res.getObj().toString());
          
                   if (json_objs.get("result").toString().equals("true")) {
                       JSONObject json_obj = json_objs.getJSONObject("data");
                       
                        //待完成
                       farmList.clear();
                       JSONArray json_arrplaning = json_obj.getJSONArray("planning");
                       for (int i = 0; i < json_arrplaning.length(); i++) {
                           JSONObject json_planing= (JSONObject) json_arrplaning.get(i);
                           FarmPlanList farm = new FarmPlanList();
                           farm.setId(json_planing.getString("id"));
                           farm.setPlantId(json_planing.getString("plantId"));
                           farm.setPlanName(json_planing.getString("planName"));
                           farm.setBeginDate(json_planing.getString("beginDate"));
                           farm.setEndDate(json_planing.getString("endDate"));
                           farm.setUserItem(json_planing.getString("userItem"));
                           farm.setPlanType(json_planing.getString("planType"));
                           farm.setPlanStatus(json_planing.getString("planStatus"));
                           farm.setPengName(json_planing.getString("pengName"));
                           farmList.add(farm);
                       }
                       if(farmList.size()!=0){

                           farmListAdapter = new FarmPlanFinishListAdapter(context, farmList);
                           listview_finish.setAdapter(farmListAdapter);
                       }else{
                           listview_finish.setAdapter(null); 
                       }

//                       setListViewHeightBasedOnChildren(listview_finish);//解决ScrollView中嵌套ListView时显示异常的问题
                       //已完成
                       farmplanfinishedList.clear();
                       JSONArray json_arrplanned = json_obj.getJSONArray("planned");
                       for (int i = 0; i < json_arrplanned.length(); i++) {
                           JSONObject json_planed= (JSONObject) json_arrplanned.get(i);
                           FarmPlanList farm = new FarmPlanList();
                           farm.setId(json_planed.getString("id"));
                           farm.setPlantId(json_planed.getString("plantId"));
                           farm.setPlanName(json_planed.getString("planName"));
                           farm.setBeginDate(json_planed.getString("beginDate"));
                           farm.setEndDate(json_planed.getString("endDate"));
                           farm.setUserItem(json_planed.getString("userItem"));
                           farm.setPlanType(json_planed.getString("planType"));
                           farm.setPlanStatus(json_planed.getString("planStatus"));
                           farm.setPengName(json_planed.getString("pengName"));
                           farmplanfinishedList.add(farm);
                       }
                       
                       if(farmplanfinishedList.size()!=0){

                           farmplanfinishedAdapter = new FarmPlanFinishedListAdapter(context, farmplanfinishedList);
                           listview_finished.setAdapter(farmplanfinishedAdapter);
                           setListViewHeightBasedOnChildren(listview_finished);//解决ScrollView中嵌套ListView时显示异常的问题
                       }else{
                           listview_finished.setAdapter(null); 
                       }

                    }
                   } catch (JSONException e) {
                       // TODO Auto-generated catch block
                       e.printStackTrace();
                   }

               }else{
                   Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
               }
           }else  if (res.getCode().equals(SOAP_UTILS.METHOD.PLANFINISH)) {//农事计划结束计划
               if (res.getObj() != null) {
                   try {

                       JSONObject json_objs = new JSONObject(res.getObj().toString());
                       if (json_objs.get("result").toString().equals("true")) {
                       String message = json_objs.get("message").toString();
                           Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

                       }else{

                           String message = json_objs.get("message").toString();
                           Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
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
       // 设置listview高度 解决ScrollView中嵌套ListView时显示异常的问题
       public static void setListViewHeightBasedOnChildren(ListView listView) {
           BaseAdapter listAdapter = (BaseAdapter) listView.getAdapter();
           if (listAdapter == null) {
               return;
           }

           int totalHeight = 0;
           for (int i = 0; i < listAdapter.getCount(); i++) {
               View listItem = listAdapter.getView(i, null, listView);
               listItem.measure(0, 0);
               totalHeight += listItem.getMeasuredHeight();
           }

           ViewGroup.LayoutParams params = listView.getLayoutParams();
           int divideHeight = (int) (0.5 * (listAdapter.getCount() - 1));
           params.height = totalHeight + divideHeight;
           listView.setLayoutParams(params);
       }
}