package com.lnpdit.woofarm.page.activity.farmservice;

import java.util.ArrayList;
import java.util.List;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.db.DBHelper;
import com.lnpdit.woofarm.entity.FarmList;
import com.lnpdit.woofarm.page.adapter.AskExpertListAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class MyQandAListActivity extends BaseActivity implements OnClickListener {
    /** Called when the activity is first created. */

    private DBHelper dbh;
    private FarmList farm;
    private List<FarmList> farmList = new ArrayList<FarmList>();
    private ListView listview_askexpertlist;
    private AskExpertListAdapter askExpertListAdapter;
    private TextView tv_back;
    Context context;
    private String farmname = "";
    private String farmcode = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_askexpertlist);

        initView();
        initData();
        setListeners();
    }

    private void initView() {
        listview_askexpertlist = (ListView) findViewById(R.id.listview_askexpertlist);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setClickable(true);
        tv_back.setOnClickListener(this);

    }

    private void initData() {

        dbh = new DBHelper(this);
        
//        SharedPreferences sharedPreferences = getSharedPreferences("userinfo",Activity.MODE_PRIVATE);  
//        String userCode = sharedPreferences.getString("userCode","");  
//        String[] property_va = new String[] {userCode };
//        soapService.farmListData(property_va);
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
        listview_askexpertlist.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Click Towns");
                farmname = farmList.get(position).getFarmName();
                farmcode = farmList.get(position).getFarmCode();
//                SharedPreferences sp = getSharedPreferences("farm", Context.MODE_PRIVATE); 
//                Editor editor = sp.edit();
//                editor.putString("farmname", farmname);
//                editor.putString("farmcode", farmcode);
//                editor.commit();
                Intent intent = new Intent();
                intent.putExtra("farmname", farmname);
                intent.putExtra("farmcode", farmcode); //将计算的值回传回去
                ((Activity) context).setResult(3,intent);
                finish();
            }
        });
    }
    
    
//   @Override
//   public void onEvent(Object obj) {
//       // TODO Auto-generated method stub
//       super.onEvent(obj);
//       SoapRes res = (SoapRes) obj;
//       if (res.getCode().equals(SOAP_UTILS.METHOD.FARMLISTDATA)) {
//           if (res.getObj() != null) {
//               try {
//               JSONObject json_objs = new JSONObject(res.getObj().toString());
//      
//               if (json_objs.get("result").toString().equals("true")) {
//                   farmList.clear();
//                   JSONArray json_arr = json_objs.getJSONArray("data");
//                   for (int i = 0; i < json_arr.length(); i++) {
//                       JSONObject json_obj= (JSONObject) json_arr.get(i);
//                       FarmList farm = new FarmList();
//                       farm.setId(json_obj.getString("id"));
//                       farm.setFarmArea(json_obj.getString("farmArea"));
//                       farm.setUserCode(json_obj.getString("userCode"));
//                       farm.setFarmName(json_obj.getString("farmName"));
//                       farm.setFarmCode(json_obj.getString("farmCode"));
//                       farmList.add(farm);
//                   }
//
//                   if(farmList.size()!=0){
//
//                       askExpertListAdapter = new AskExpertListAdapter(context, farmList);
//                       listview_askexpertlist.setAdapter(askExpertListAdapter);
//                   }else{
//                       listview_askexpertlist.setAdapter(null); 
//                   }
//                }
//               } catch (JSONException e) {
//                   // TODO Auto-generated catch block
//                   e.printStackTrace();
//               }
//
//           }else{
//               Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
//           }
//       }
//   }
}