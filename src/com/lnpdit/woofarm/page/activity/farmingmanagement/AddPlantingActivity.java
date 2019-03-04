package com.lnpdit.woofarm.page.activity.farmingmanagement;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.entity.BaseList;
import com.lnpdit.woofarm.entity.ZuowukuList;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.page.adapter.ZuowukuListAdapter;
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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddPlantingActivity extends BaseActivity implements OnClickListener {
    /** Called when the activity is first created. */

    private Spinner base_sp,cropcategory_sp,cropvariety_sp;
    private EditText cropname_et,croparea_et,remarks_et,cropunit_et;
    private TextView date_tv;
    private TextView enddate_tv;
    private TextView save_tv;
    private TextView tv_back;
    Context context;
    private String cropcategory_str = "";
    private String basename_str = "";
    private String baseid_str = "";
    private String cropvariety_str = "";
    private String cropvarietycode_str = "";
//    private String edu_str = "";
    String userCode = "";

    private int times_date = 0;//保存时间选择器上次选的值
    private int times_enddate = 0;//保存时间选择器上次选的值
    private List<BaseList> baseList = new ArrayList<BaseList>();
    //下拉菜单作物品种
    private List<ZuowukuList> zuowukuList = new ArrayList<ZuowukuList>();
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_addplanting);
         
        initView();
        initData();

        testSpinnercropcategory1();//作物类别
    }

    private void initView() {
        
        date_tv = (TextView) findViewById(R.id.date_tv);
        date_tv.setClickable(true);
        date_tv.setOnClickListener(this);
        enddate_tv = (TextView) findViewById(R.id.enddate_tv);
        enddate_tv.setClickable(true);
        enddate_tv.setOnClickListener(this);
        base_sp = (Spinner) findViewById(R.id.base_sp);
        cropcategory_sp = (Spinner) findViewById(R.id.cropcategory_sp);
        cropvariety_sp = (Spinner) findViewById(R.id.cropvariety_sp);
        cropname_et = (EditText) findViewById(R.id.cropname_et);
        croparea_et = (EditText) findViewById(R.id.croparea_et);
        remarks_et = (EditText) findViewById(R.id.remarks_et);
        cropunit_et = (EditText) findViewById(R.id.cropunit_et);
        tv_back = (TextView) findViewById(R.id.tv_back);
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

            String[] property_va = new String[] {userCode};//获取基地列表
            soapService.getBaseList(property_va);
        
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
        case R.id.enddate_tv:
            time(1,times_enddate);
            break;
        case R.id.save_tv:
            if(baseid_str.equals("")){

                Toast.makeText(context, "请选择基地名称", Toast.LENGTH_SHORT).show();
            }else if(cropcategory_str.equals("")){

                Toast.makeText(context, "请选择作物类别", Toast.LENGTH_SHORT).show();
            }else if(cropvarietycode_str.equals("")){

                Toast.makeText(context, "请选择作物品种", Toast.LENGTH_SHORT).show();
            }else if(cropname_et.getText().toString().equals("")){

                Toast.makeText(context, "请输入作物名称", Toast.LENGTH_SHORT).show();
            }else if(croparea_et.getText().toString().equals("")){

                Toast.makeText(context, "请输入种植面积", Toast.LENGTH_SHORT).show();
            }else if(cropunit_et.getText().toString().equals("")){

                Toast.makeText(context, "请输入种植单位", Toast.LENGTH_SHORT).show();
            }else if(date_tv.getText().toString().equals("")){

                Toast.makeText(context, "请选择开始时间", Toast.LENGTH_SHORT).show();
            }else if(enddate_tv.getText().toString().equals("")){
                Toast.makeText(context, "请选择结束时间", Toast.LENGTH_SHORT).show();
            }else{

                String[] property_va = new String[] {baseid_str,croparea_et.getText().toString(),cropname_et.getText().toString(),
                        cropvarietycode_str,date_tv.getText().toString(),cropcategory_str,remarks_et.getText().toString(),cropunit_et.getText().toString()
                        ,enddate_tv.getText().toString()};
                soapService.savePlantingInfo(property_va);
            }
            break;
        default:
            break;
        }
    }

    /**
     * 测试：加载数据列，监听选择 作物类别
     * */
    private void testSpinnercropcategory1(){
        
            final String[] spinnerItems = {"蔬菜菌类","生鲜瓜果","水产渔业","肉禽蛋奶","花卉药材","粮油饲料"};
        //简单的string数组适配器：样式res，数组
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_dropdowm_item, spinnerItems);
        //绑定 Adapter到控件
        cropcategory_sp.setAdapter(spinnerAdapter);
        //选择监听
        cropcategory_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                cropcategory_str = pos +"";
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
    private void testSpinnerbase2(){
        
            final String[] spinnerItems = new String[baseList.size() ];
            final String[] spinnerItems_id = new String[baseList.size() ];
            for(int i = 0;i<baseList.size();i++){
                spinnerItems[i] = baseList.get(i).getPlotName();
                spinnerItems_id[i] = baseList.get(i).getPlotId();
            }
      
        //简单的string数组适配器：样式res，数组
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_dropdowm_item, spinnerItems);
        //绑定 Adapter到控件
        base_sp.setAdapter(spinnerAdapter);
        //选择监听
        base_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
//                Toast.makeText(context, "选择了["+spinnerItems[pos]+"]", Toast.LENGTH_SHORT).show();
                basename_str = spinnerItems[pos];
                baseid_str = spinnerItems_id[pos];
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
    private void testSpinnercrop3(){
        
            final String[] spinnerItems = new String[zuowukuList.size() ];
            final String[] spinnerItems_id = new String[zuowukuList.size() ];
            for(int i = 0;i<zuowukuList.size();i++){
                spinnerItems[i] = zuowukuList.get(i).getCropName();
                spinnerItems_id[i] = zuowukuList.get(i).getCropCode();
            }
      
        //简单的string数组适配器：样式res，数组
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_dropdowm_item, spinnerItems);
        //绑定 Adapter到控件
        cropvariety_sp.setAdapter(spinnerAdapter);
        //选择监听
        cropvariety_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
//                Toast.makeText(context, "选择了["+spinnerItems[pos]+"]", Toast.LENGTH_SHORT).show();
                cropvariety_str = spinnerItems[pos];
                cropvarietycode_str = spinnerItems_id[pos];
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
       if (res.getCode().equals(SOAP_UTILS.METHOD.SAVEPLANTINGINFO)) {
           if (res.getObj() != null) {
               try {
               JSONObject json_obj = new JSONObject(res.getObj().toString());
               if(json_obj.get("result").toString().equals("true")){
//                       img1_name = json_obj.get("Message").toString();
                   Toast.makeText(context, json_obj.get("message").toString(), Toast.LENGTH_SHORT).show();
                   finish();
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
       }else if (res.getCode().equals(SOAP_UTILS.METHOD.GETBASELIST)) {
           if (res.getObj() != null) {
               try {
               JSONObject json_objs = new JSONObject(res.getObj().toString());
      
               if (json_objs.get("result").toString().equals("true")) {
                 JSONArray json_arr = json_objs.getJSONArray("data");
                   baseList.clear();
                   for (int i = 0; i < json_arr.length(); i++) {
                       JSONObject json_obj= (JSONObject) json_arr.get(i);
                       BaseList farm = new BaseList();
                       farm.setPlotId(json_obj.getString("plotId"));
                       farm.setPlotName(json_obj.getString("plotName"));
                       baseList.add(farm);
                   }

                   testSpinnerbase2();

                }
               } catch (JSONException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
               }

               String[] property_va = new String[] {};
               soapService.getZuowukuVarietyList(property_va);
           }else{
               Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
           }
       }else if (res.getCode().equals(SOAP_UTILS.METHOD.ZUOWUKU2)) {
           if (res.getObj() != null) {
               try {
               JSONObject json_objs = new JSONObject(res.getObj().toString());
      
               if (json_objs.get("result").toString().equals("true")) {
                   zuowukuList.clear();
                   JSONArray json_arr = json_objs.getJSONArray("data");
                   for (int i = 0; i < json_arr.length(); i++) {
                       JSONObject json_obj= (JSONObject) json_arr.get(i);
                       ZuowukuList farm = new ZuowukuList();
                       farm.setId(json_obj.getString("id"));
                       farm.setCropCode(json_obj.getString("cropCode"));
                       farm.setCropName(json_obj.getString("cropName"));
                       farm.setCropVariety(json_obj.getString("cropCategory"));
                       farm.setAvatar(json_obj.getString("avatar"));
                       farm.setEnvironment(json_obj.getString("environment"));
                       farm.setStandard(json_obj.getString("standard"));
                       
                       zuowukuList.add(farm);
                   }
                   testSpinnercrop3();
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
   //时间选择器-开始时间 结束时间
   public void time(final int typ ,int times) {
       CustomDialogBith.Builder builder = new CustomDialogBith.Builder(context,times);
       builder.setPositiveButton("确认", new CustomDialogBith.Builder.newDialoglistener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i, String date) {
//               Toast.makeText(context, date, Toast.LENGTH_SHORT).show();
               if(typ==0){

                   date_tv.setText(date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6));
                   times_date=Integer.parseInt(date);
               }else{

                   enddate_tv.setText(date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6));
                   times_enddate=Integer.parseInt(date);   
               }
              
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