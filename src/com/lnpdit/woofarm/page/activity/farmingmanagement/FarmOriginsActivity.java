package com.lnpdit.woofarm.page.activity.farmingmanagement;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.db.DBHelper;
import com.lnpdit.woofarm.entity.ADInfo;
import com.lnpdit.woofarm.entity.Originsinfo;
import com.lnpdit.woofarm.entity.ZuowukuList;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.instance.Instance;
import com.lnpdit.woofarm.utils.SOAP_UTILS;
import com.lnpdit.woofarm.utils.advert.ImageCycleView;
import com.lnpdit.woofarm.utils.advert.ImageCycleView.ImageCycleViewListener;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FarmOriginsActivity extends BaseActivity implements OnClickListener {
    /** Called when the activity is first created. */

    private DBHelper dbh;
//    private FarmList farm;
    private List<Originsinfo> originsinfoList = new ArrayList<Originsinfo>();
    private TextView tv_back;
    private TextView company_tv;
    private TextView address_tv;
    private TextView manager_tv;
    private TextView tel_tv;
    private TextView plantid_tv;
    private TextView cropname_tv;
    private TextView storagemethod_tv;
    private TextView harvesttime_tv;
    private TextView score_tv;//评分
    
    Context context;
    private String farmname = "";
    private String farmcode = "";
    private String plantId = "";    
    
    private ImageCycleView mAdView;
    private ArrayList<ADInfo> infos = new ArrayList<ADInfo>();
    boolean isRefreshing;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_origins);

        Intent intent = getIntent();
        plantId = intent.getStringExtra("plantId");
        initView();
        initData();
    }

    private void initView() {
//        listview_farmlist = (ListView) findViewById(R.id.listview_farmlist);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setClickable(true);
        tv_back.setOnClickListener(this);


        company_tv = (TextView) findViewById(R.id.company_tv);
        address_tv = (TextView) findViewById(R.id.address_tv);
        manager_tv = (TextView) findViewById(R.id.manager_tv);
        tel_tv = (TextView) findViewById(R.id.tel_tv);
        plantid_tv = (TextView) findViewById(R.id.plantid_tv);
        cropname_tv = (TextView) findViewById(R.id.cropname_tv);
        storagemethod_tv = (TextView) findViewById(R.id.storagemethod_tv);
        harvesttime_tv = (TextView) findViewById(R.id.harvesttime_tv);
        score_tv = (TextView) findViewById(R.id.score_tv);//评分
        score_tv.setClickable(true);
   
        // 广告轮播
        mAdView = (ImageCycleView) findViewById(R.id.ad_view);

    }

    private void initData() {

        dbh = new DBHelper(this);
        
        String[] property_va = new String[] { plantId };
        soapService.getOriginsInfo(property_va);
    }
    private void getDBData() {

//        infos = dbh.queryAdtInfo();
//
//  
//        if (infos.size() != 0) {
//            mAdView.setImageResources(infos, mAdCycleViewListener);
//        }
   

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.tv_back:
            finish();
            break;
        case R.id.score_tv:
            Intent in_camera3 = new Intent();
            in_camera3.putExtra("plantId", plantId);
            in_camera3.setClass(FarmOriginsActivity.this, OriginsScoreActivity.class);
            startActivity(in_camera3);
            break;
        default:
            break;
        }
    }
    

    @Override
    protected void onResume() {
        super.onResume();
        mAdView.startImageCycle();
        isRefreshing = true;
    };

    @Override
    protected void onPause() {
        super.onPause();
        mAdView.pushImageCycle();
        isRefreshing = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdView.pushImageCycle();
        isRefreshing = false;
    }
   @Override
   public void onEvent(Object obj) {
       // TODO Auto-generated method stub
       super.onEvent(obj);
       SoapRes res = (SoapRes) obj;
       if (res.getCode().equals(SOAP_UTILS.METHOD.ORIGINSINFO)) {
           if (res.getObj() != null) {
               try {
               JSONObject json_objs = new JSONObject(res.getObj().toString());
      
               if (json_objs.get("result").toString().equals("true")) {
                       JSONObject json_obj= (JSONObject) json_objs.get("data");

                       JSONObject json_farm= (JSONObject) json_obj.get("farm");
                       JSONObject json_plant= (JSONObject) json_obj.get("plant");
                       
                       Originsinfo originsinfo = new Originsinfo();
                       originsinfo.setCompany(json_farm.getString("company"));
                       originsinfo.setAddress(json_farm.getString("address"));
                       originsinfo.setManager(json_farm.getString("manager"));
                       originsinfo.setTel(json_farm.getString("tel"));
                       
                       originsinfo.setPlantid(json_plant.getString("id"));
                       originsinfo.setCropName(json_plant.getString("cropName"));
                       originsinfo.setStorageMethod(json_plant.getString("storageMethod"));
                       originsinfo.setHarvestTime(json_plant.getString("harvestTime"));
                       originsinfoList.add(originsinfo);
                       
                     company_tv.setText(json_farm.getString("company"));
                     address_tv.setText(json_farm.getString("address"));
                     manager_tv.setText(json_farm.getString("manager"));
                     tel_tv.setText(json_farm.getString("tel"));
                     plantid_tv.setText(json_plant.getString("id"));
                     cropname_tv.setText(json_plant.getString("cropName"));
                     storagemethod_tv.setText(json_plant.getString("storageMethod"));
                     harvesttime_tv.setText(json_plant.getString("harvestTime"));
                     
//                     infos = "[/userfiles/fileupload/201808/08/1032539596906049536.jpg]";
                     JSONArray json_arr = (JSONArray) json_obj.getJSONArray("urls");
                     for (int i = 0; i < json_arr.length(); i++) {
                         JSONObject json_urls= (JSONObject) json_arr.get(i);
                         ADInfo adinfo = new ADInfo();
                         adinfo.setImgUrl(SOAP_UTILS.IP + json_urls.getString("imgUrl"));
                         adinfo.setPlantDays(json_urls.getString("plantDays"));
                         
                         infos.add(adinfo);
                     }

                     
                   if (originsinfoList.size() != 0) {
                   mAdView.setImageResources(infos, mAdCycleViewListener);
               }
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

   private ImageCycleViewListener mAdCycleViewListener = new ImageCycleViewListener() {

       @Override
       public void onImageClick(ADInfo info, int position, View imageView) {
//           Intent intent = new Intent();
//           intent.putExtra("wap_url", SOAP_UTILS.HTTP_ADVERTINFO_PATH + info.getId());
//           intent.setClass(context, Wap_Activity.class);
//           startActivity(intent);
       }

       @Override
       public void displayImage(String imageURL, ImageView imageView) {
           // 使用ImageLoader对图片进行加装！
           Instance.imageLoader.displayImage(imageURL, imageView, Instance.advert_options);// 使用ImageLoader对图片进行加装！
       }
   };

   
}