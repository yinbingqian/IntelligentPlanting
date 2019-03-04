package com.lnpdit.woofarm.page.activity.farmingmanagement;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.entity.BaseList;
import com.lnpdit.woofarm.entity.PickUserList;
import com.lnpdit.woofarm.entity.PlantingList;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddPickActivity extends BaseActivity implements OnClickListener {
    /** Called when the activity is first created. */

    private Spinner pickcrop_sp,pickuser_sp;
    private EditText pickcount_et,pickformat_et,remarks_et;
    private TextView date_tv;
    private TextView save_tv;
    private ImageView tv_back;
    Context context;
    private String cropname_str = "";
    private String cropid_str = "";
    private String pickuser_str = "";
    private String pickusercode_str = "";
    String userCode = "";

    private int times_date = 0;//保存时间选择器上次选的值
//    private List<PickCropList> baseList = new ArrayList<PickCropList>();
    private List<PlantingList> plantingList = new ArrayList<PlantingList>();
    //下拉菜单作物品种
    private List<PickUserList> pickuserList = new ArrayList<PickUserList>();
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_addpick);
         
        initView();
        initData();
        
    }

    private void initView() {
        
        date_tv = (TextView) findViewById(R.id.date_tv);
        date_tv.setClickable(true);
        date_tv.setOnClickListener(this);
        pickcrop_sp = (Spinner) findViewById(R.id.pickcrop_sp);
        pickuser_sp = (Spinner) findViewById(R.id.pickuser_sp);
        pickcount_et = (EditText) findViewById(R.id.pickcount_et);
        pickformat_et = (EditText) findViewById(R.id.pickformat_et);
        remarks_et = (EditText) findViewById(R.id.remarks_et);
        tv_back = (ImageView) findViewById(R.id.tv_back);
        tv_back.setClickable(true);
        tv_back.setOnClickListener(this);
        save_tv = (TextView) findViewById(R.id.save_tv);
        save_tv.setClickable(true);
        save_tv.setOnClickListener(this);

        
    }

    private void initData() {
        SharedPreferences sharedPreferences = getSharedPreferences("userinfo",Activity.MODE_PRIVATE);  
        userCode = sharedPreferences.getString("userCode","");  
        if(!userCode.equals("")&&!userCode.equals("null")){
            
        String[] property_va = new String[] {userCode};
        soapService.getPlantingList(property_va);
        
        }else{
            Toast.makeText(context, "请登录后查看", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.tv_back:
            finish();
            break;
        case R.id.date_tv:
            time(0,times_date);
            break;
        case R.id.save_tv:
            
            String[] property_va = new String[] {cropid_str,pickusercode_str,date_tv.getText().toString(),pickformat_et.getText().toString(),
                    "0",remarks_et.getText().toString(),pickcount_et.getText().toString()};
            soapService.savePickInfo(property_va);
            break;
        default:
            break;
        }
    }

    /**
     * 测试：加载数据列，监听选择  基地
     * */
    private void testSpinnercrop1(){
        
            final String[] spinnerItems = new String[plantingList.size() ];
            final String[] spinnerItems_id = new String[plantingList.size() ];
            for(int i = 0;i<plantingList.size();i++){
                spinnerItems[i] = plantingList.get(i).getVarietyName();
                spinnerItems_id[i] = plantingList.get(i).getPlantId();
            }
      
        //简单的string数组适配器：样式res，数组
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_dropdowm_item, spinnerItems);
        //绑定 Adapter到控件
        pickcrop_sp.setAdapter(spinnerAdapter);
        //选择监听
        pickcrop_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
//                Toast.makeText(context, "选择了["+spinnerItems[pos]+"]", Toast.LENGTH_SHORT).show();
                cropname_str = spinnerItems[pos];
                cropid_str = spinnerItems_id[pos];
                //设置spinner内的填充文字居中
                ((TextView)view).setGravity(Gravity.CENTER);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }

    /**
     * 测试：加载数据列，监听选择  基地
     * */
    private void testSpinneruser2(){
        
            final String[] spinnerItems = new String[pickuserList.size() ];
            final String[] spinnerItems_id = new String[pickuserList.size() ];
            for(int i = 0;i<pickuserList.size();i++){
                spinnerItems[i] = pickuserList.get(i).getUserName();
                spinnerItems_id[i] = pickuserList.get(i).getUserCode();
            }
      
        //简单的string数组适配器：样式res，数组
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_dropdowm_item, spinnerItems);
        //绑定 Adapter到控件
        pickuser_sp.setAdapter(spinnerAdapter);
        //选择监听
        pickuser_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
//                Toast.makeText(context, "选择了["+spinnerItems[pos]+"]", Toast.LENGTH_SHORT).show();
                pickuser_str = spinnerItems[pos];
                pickusercode_str = spinnerItems_id[pos];
                //设置spinner内的填充文字居中
                ((TextView)view).setGravity(Gravity.CENTER);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }
   @Override
   public void onEvent(Object obj) {
       // TODO Auto-generated method stub
       super.onEvent(obj);
       SoapRes res = (SoapRes) obj;
       if (res.getCode().equals(SOAP_UTILS.METHOD.SAVEPICKINFO)) {
           if (res.getObj() != null) {
               try {
               JSONObject json_obj = new JSONObject(res.getObj().toString());
               if(json_obj.get("result").toString().equals("true")){
//                       img1_name = json_obj.get("Message").toString();
                   Toast.makeText(context, json_obj.get("message").toString(), Toast.LENGTH_SHORT).show();
               }else if(json_obj.get("result").toString().equals("false")){
                   Toast.makeText(context, json_obj.get("message").toString(), Toast.LENGTH_SHORT).show();
               }

               
               } catch (JSONException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
               }

           }else{
               Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
           }
       }else if (res.getCode().equals(SOAP_UTILS.METHOD.PICKCROPUSERLIST)) {
           if (res.getObj() != null) {
               try {
               JSONObject json_objs = new JSONObject(res.getObj().toString());
      
               if (json_objs.get("result").toString().equals("true")) {
                   JSONObject json_objdata = json_objs.getJSONObject("data");
                   pickuserList.clear();
                   JSONArray json_arr = json_objdata.getJSONArray("list");
                   for (int i = 0; i < json_arr.length(); i++) {
                       JSONObject json_obj= (JSONObject) json_arr.get(i);
                       PickUserList farm = new PickUserList();
                       farm.setUserCode(json_obj.getString("userCode"));
                       farm.setUserName(json_obj.getString("userName"));
                       pickuserList.add(farm);
                   }

                   testSpinneruser2();

                }
               } catch (JSONException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
               }
           }else{
               Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
           }
       }else if (res.getCode().equals(SOAP_UTILS.METHOD.PICKCROPLIST)) {
           if (res.getObj() != null) {
               try {
               JSONObject json_objs = new JSONObject(res.getObj().toString());
      
               if (json_objs.get("result").toString().equals("true")) {
                   plantingList.clear();
                   JSONArray json_arr = json_objs.getJSONArray("data");
                   for (int i = 0; i < json_arr.length(); i++) {
                       JSONObject json_obj= (JSONObject) json_arr.get(i);
                       PlantingList farm = new PlantingList();
                       farm.setPlantId(json_obj.getString("plantId"));
                       farm.setBeginTime(json_obj.getString("beginTime"));
                       farm.setEndTime(json_obj.getString("endTime"));
                       farm.setPlantDays(json_obj.getString("plantDays"));
                       farm.setPlantImg(json_obj.getString("plantImg"));
                       farm.setPlantState(json_obj.getString("plantStatus"));
                       farm.setPlantSchedule(json_obj.getString("plantSchedule"));
                       farm.setVarietyName(json_obj.getString("varietyName"));
                       String planting = json_obj.getString("plantStatus");
                       if(planting.equals("0")){
                           
                           JSONObject json_plist= (JSONObject) json_obj.get("plot");
                           farm.setPlotName(json_plist.getString("plotName"));
                           
                          }else{
                              farm.setPlantDays("1");
                              farm.setBeginTime("1");
                              farm.setEndTime("1"); 
                              farm.setPlantId("1");
                              farm.setPlantImg("1");
                              
                              farm.setPlantSchedule("1");
                              farm.setPlantState("1");
                              farm.setPlotName("1");
                              farm.setVarietyName("1");
                           }
                       plantingList.add(farm);
                   }

                   testSpinnercrop1();
                }
               } catch (JSONException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
               }
               SharedPreferences sharedPreferences = getSharedPreferences("userinfo",Activity.MODE_PRIVATE);  
               userCode = sharedPreferences.getString("userCode","");  
               if(!userCode.equals("")&&!userCode.equals("null")){
                   
               String[] property_va = new String[] {userCode};
               soapService.getPickUserList(property_va);
               
               }else{
                   Toast.makeText(context, "请登录后查看采摘人员列表", Toast.LENGTH_SHORT).show();
               }

           }else{
               Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
           }
                }
   }
   //时间选择器-出生日期 开始时间 年审时间
   public void time(final int typ ,int times) {
       CustomDialogBith.Builder builder = new CustomDialogBith.Builder(context,times);
       builder.setPositiveButton("确认", new CustomDialogBith.Builder.newDialoglistener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i, String date) {
//               Toast.makeText(context, date, Toast.LENGTH_SHORT).show();
               
                   date_tv.setText(date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6));
                   times_date=Integer.parseInt(date);
              
               dialogInterface.dismiss();
           }
       });
       builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {
               dialogInterface.dismiss();
           }
       });
       builder.create().show();
   }
 
}