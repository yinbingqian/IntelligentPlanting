package com.lnpdit.woofarm.page.activity.farmservice;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.entity.CropDiseaseList;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.page.adapter.CropDiseaseListAdapter;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CropDiseaseListActivity extends BaseActivity implements OnClickListener {
    /** Called when the activity is first created. */

    private List<CropDiseaseList> farmList = new ArrayList<CropDiseaseList>();
    private ListView listview_cropdiseaselist;
    private CropDiseaseListAdapter farmListAdapter;
    private TextView tv_back;
    Context context;
    private String cropCode = "";
    private String title = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_cropdiseaselist);

        Intent intent=getIntent();
        cropCode = intent.getStringExtra("cropCode");
        
        initView();
        initData();
        setListeners();
    }

    private void initView() {
        listview_cropdiseaselist = (ListView) findViewById(R.id.listview_cropdiseaselist);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setClickable(true);
        tv_back.setOnClickListener(this);

    }

    private void initData() {

        String[] property_va = new String[] { cropCode };
        soapService.getCropDiseaseList(property_va);
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
        listview_cropdiseaselist.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Click Towns");

//                Intent intent = new Intent();
//                intent.putExtra("cropCode", farmList.get(position).getDetails());
//                intent.setClass(context, CropDiseaseInfoActivity.class);
//                startActivity(intent);
            }
        });
    }
   @Override
   public void onEvent(Object obj) {
       // TODO Auto-generated method stub
       super.onEvent(obj);
       SoapRes res = (SoapRes) obj;
       if (res.getCode().equals(SOAP_UTILS.METHOD.CROPDISEASE)) {
           if (res.getObj() != null) {
               try {
               JSONObject json_objs = new JSONObject(res.getObj().toString());

               String message = json_objs.get("message").toString();
               if (json_objs.get("result").toString().equals("true")) {
                   farmList.clear();
                   JSONArray json_arr = json_objs.getJSONArray("data");
                   for (int i = 0; i < json_arr.length(); i++) {
                       JSONObject json_obj= (JSONObject) json_arr.get(i);
                       CropDiseaseList farm = new CropDiseaseList();
                       farm.setCropCode(json_obj.getString("cropCode"));
                       farm.setDisName(json_obj.getString("disName"));
                       farm.setImgUrl(json_obj.getString("imgUrl"));
                       farm.setDetails(json_obj.getString("details"));
                       farmList.add(farm);
                   }

                   if(farmList.size()!=0){

                       farmListAdapter = new CropDiseaseListAdapter(context, farmList);
                       listview_cropdiseaselist.setAdapter(farmListAdapter);
                   }else{
                       listview_cropdiseaselist.setAdapter(null); 
                   }
                }else{
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
}