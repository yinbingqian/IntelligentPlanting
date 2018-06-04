package com.lnpdit.woofarm.page.activity.farm;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.entity.Environment;
import com.lnpdit.woofarm.entity.Equipment;
import com.lnpdit.woofarm.entity.PengAlarm;
import com.lnpdit.woofarm.entity.Planting;
import com.lnpdit.woofarm.entity.Scenes;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.instance.Instance;
import com.lnpdit.woofarm.page.adapter.EquipmentListAdapter;
import com.lnpdit.woofarm.page.adapter.ScenesListAdapter;
import com.lnpdit.woofarm.utils.SOAP_UTILS;
import com.lnpdit.woofarm.widget.CustomDialog;
import com.lnpdit.woofarm.widget.LineViewAlarm;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class GreenHouseInfoActivity extends BaseActivity implements OnClickListener {
    /** Called when the activity is first created. */

    private List<Planting> plantingList  = new ArrayList<Planting>();
    private List<PengAlarm> pengAlarmList  = new ArrayList<PengAlarm>();
    //场景
    private List<Scenes> scenesList  = new ArrayList<Scenes>();
    private ListView listview_sceneslist;
    private ScenesListAdapter scenesListAdapter;
    //控制面板
    private List<Equipment> equipmentList  = new ArrayList<Equipment>();
    private ListView listview_equipmentlist;
    private EquipmentListAdapter equipmentListAdapter;
    private LinearLayout none_layout,linechart_layout,linechart_layout2,scenes_layout;
    
    private TextView tv_back;
    private TextView add_tv;
    private ScrollView greenhouse_layout;
    private LinearLayout unlogin_layout;
    private ImageView crop_headpic;
    private TextView crop_name;
    private TextView begintime_tv;
    private TextView endtime_tv;
    private TextView plantdate_tv;
    private TextView title_tv;
    private TextView grey_line_view;
    private TextView orange_line_view;//大棚信息进度线条
    private TextView alarmcount_tv;//报警次数
    private TextView alarmpercent_tv;
    private ImageView alarmupdown_img;
    private TextView endplant_tv;//结束种植
    private TextView label_tv1,label_tv2,label_tv3,label_tv4;//温度  湿度 光照 co2
    private TextView fengji1_label1,fengji1_label2,fengji1_label3,
                     fengji2_label1,fengji2_label2,fengji2_label3,
                     diancifa1_label1,diancifa1_label2,diancifa1_label3,
                     buguangdeng1_label1,buguangdeng1_label2,buguangdeng1_label3;
    
    Context context;
    private String farmname = "";
    private String pengcode = "";
    private String farmcode = "";
    private String pengname = "";
    private String todayCount = "";
    private String changeRate = "";
    private String dinishplantid = "";
    

    //折线图
//    private LineViewAlarm xyview05;
    private LineViewAlarm xyview06;

    private LineChartView lineChart;
    private LineChartView lineChart2;
    
    String[] date = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};//X轴的标注
    int[] score= {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};//图表的数据

//    String[] date2 = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};//X轴的标注
//    float[] score2=  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};//图表的数据    

    String[] date2 = {"00","00","00","00","01","01","01","01","02","02","02","02","03","03","03","03","04","04","04","04","05","05","05","05",
            "06","06","06","06","07","07","07","07","08","08","08","08","09","09","09","09","10","10","10","10","11","11","11","11",
            "12","12","12","12","13","13","13","13","14","14","14","14","15","15","15","15","16","16","16","16","17","17","17","17",
            "18","18","18","18","19","19","19","19","20","20","20","20","21","21","21","21","22","22","22","22","23","23","23","23"};//X轴的标注
    float[] score2=  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};//图表的数据    
//    float[] scorex2=  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};//图表的数据
//    float[] score2=  new float[96];//图表的数据    
//    float[] scorex2=  new float[96];//图表的数据
//    int [] score = {};//图表的数据
    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();

    private List<PointValue> mPointValues2 = new ArrayList<PointValue>();
    private List<AxisValue> mAxisXValues2 = new ArrayList<AxisValue>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_greenhouseinfo);

        Intent intent = getIntent();
        farmcode = intent.getStringExtra("farmcode");
        pengname = intent.getStringExtra("pengname");
        pengcode = intent.getStringExtra("pengcode");
        
        initView();
        initData();
        setListeners();
    }

    private void initView() {
        //场景
        listview_sceneslist = (ListView) findViewById(R.id.listview_sceneslist);
        listview_sceneslist.setFocusable(false);
        //控制面板
        none_layout = (LinearLayout) findViewById(R.id.none_layout); 
        listview_equipmentlist = (ListView) findViewById(R.id.listview_equipmentlist);
        listview_equipmentlist.setFocusable(false);
        
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setClickable(true);
        tv_back.setOnClickListener(this);
        add_tv = (TextView) findViewById(R.id.add_tv);
        add_tv.setClickable(true);
        add_tv.setOnClickListener(this);
        greenhouse_layout = (ScrollView) findViewById(R.id.greenhouse_layout);
        unlogin_layout = (LinearLayout) findViewById(R.id.unlogin_layout);
        crop_headpic = (ImageView) findViewById(R.id.crop_headpic);
        crop_name = (TextView) findViewById(R.id.crop_name);
        begintime_tv = (TextView) findViewById(R.id.begintime_tv);
        endtime_tv = (TextView) findViewById(R.id.endtime_tv);
        plantdate_tv = (TextView) findViewById(R.id.plantdate_tv);
        title_tv = (TextView) findViewById(R.id.title_tv);
        title_tv.setText(pengname);

        grey_line_view = (TextView)findViewById(R.id.grey_line_view);
        orange_line_view = (TextView) findViewById(R.id.orange_line_view);
        
        endplant_tv= (TextView) findViewById(R.id.endplant_tv);
        endplant_tv.setClickable(true);
        endplant_tv.setOnClickListener(this);
        alarmcount_tv = (TextView) findViewById(R.id.alarmcount_tv);
        alarmpercent_tv = (TextView) findViewById(R.id.alarmpercent_tv);
        alarmupdown_img = (ImageView) findViewById(R.id.alarmupdown_img);
        
       //温度湿度光照co2浓度
        label_tv1 = (TextView) findViewById(R.id.label_tv1);
        label_tv2 = (TextView) findViewById(R.id.label_tv2);
        label_tv3 = (TextView) findViewById(R.id.label_tv3);
        label_tv4 = (TextView) findViewById(R.id.label_tv4);
        label_tv1.setClickable(true);
        label_tv2.setClickable(true);
        label_tv3.setClickable(true);
        label_tv4.setClickable(true);
        label_tv1.setOnClickListener(this);
        label_tv2.setOnClickListener(this);
        label_tv3.setOnClickListener(this);
        label_tv4.setOnClickListener(this);
        //折线图
        lineChart = (LineChartView)findViewById(R.id.line_chart);
        lineChart2 = (LineChartView)findViewById(R.id.line_chart_ev);
        linechart_layout = (LinearLayout)findViewById(R.id.linechart_layout);
        linechart_layout2 = (LinearLayout)findViewById(R.id.linechart_layout2);
        
        scenes_layout = (LinearLayout)findViewById(R.id.scenes_layout);
    }

    private void initData() {

        //场景模式
        scenesListAdapter = new ScenesListAdapter(context, scenesList);
        listview_sceneslist.setAdapter(scenesListAdapter);
        //控制面板
        equipmentListAdapter= new EquipmentListAdapter(context, equipmentList);
        listview_equipmentlist.setAdapter(equipmentListAdapter);
//        SharedPreferences sharedPreferences = getSharedPreferences("userinfo",Activity.MODE_PRIVATE);  
//        String userCode = sharedPreferences.getString("userCode","");  

        String[] property_vas = new String[] {farmcode };
        soapService.planting(property_vas);
       
    }
  
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.tv_back:
            finish();
            break;
        case R.id.add_tv:
            
            break;
        case R.id.label_tv1:
            label_tv1.setBackgroundResource(R.drawable.broder_orange);
            label_tv2.setBackgroundResource(R.drawable.broder_graylabel);
            label_tv3.setBackgroundResource(R.drawable.broder_graylabel);
            label_tv4.setBackgroundResource(R.drawable.broder_graylabel);

            label_tv1.setTextColor(getResources().getColor(R.color.white));
            label_tv2.setTextColor(getResources().getColor(R.color.item_graytext));
            label_tv3.setTextColor(getResources().getColor(R.color.item_graytext));
            label_tv4.setTextColor(getResources().getColor(R.color.item_graytext));
            
            String[] property_va1 = new String[] {pengcode,"02" };//equipType = 02是温度  03湿度  07光照  08二氧化碳
            soapService.collection(property_va1);
            break;
        case R.id.label_tv2:
            label_tv1.setBackgroundResource(R.drawable.broder_graylabel);
            label_tv2.setBackgroundResource(R.drawable.broder_orange);
            label_tv3.setBackgroundResource(R.drawable.broder_graylabel);
            label_tv4.setBackgroundResource(R.drawable.broder_graylabel);

            label_tv1.setTextColor(getResources().getColor(R.color.item_graytext));
            label_tv2.setTextColor(getResources().getColor(R.color.white));
            label_tv3.setTextColor(getResources().getColor(R.color.item_graytext));
            label_tv4.setTextColor(getResources().getColor(R.color.item_graytext));
            
//            String[] property_va2 = new String[] {pengcode,"03" };//equipType = 02是温度  03湿度  07光照  08二氧化碳
//            soapService.collection(property_va2);
            break;
        case R.id.label_tv3:
            label_tv1.setBackgroundResource(R.drawable.broder_graylabel);
            label_tv2.setBackgroundResource(R.drawable.broder_graylabel);
            label_tv3.setBackgroundResource(R.drawable.broder_orange);
            label_tv4.setBackgroundResource(R.drawable.broder_graylabel);
            label_tv1.setTextColor(getResources().getColor(R.color.item_graytext));
            label_tv2.setTextColor(getResources().getColor(R.color.item_graytext));
            label_tv3.setTextColor(getResources().getColor(R.color.white));
            label_tv4.setTextColor(getResources().getColor(R.color.item_graytext));
//            String[] property_va3 = new String[] {pengcode,"07" };//equipType = 02是温度  03湿度  07光照  08二氧化碳
//            soapService.collection(property_va3);
            break;
        case R.id.label_tv4:
            label_tv1.setBackgroundResource(R.drawable.broder_graylabel);
            label_tv2.setBackgroundResource(R.drawable.broder_graylabel);
            label_tv3.setBackgroundResource(R.drawable.broder_graylabel);
            label_tv4.setBackgroundResource(R.drawable.broder_orange);

            label_tv1.setTextColor(getResources().getColor(R.color.item_graytext));
            label_tv2.setTextColor(getResources().getColor(R.color.item_graytext));
            label_tv3.setTextColor(getResources().getColor(R.color.item_graytext));
            label_tv4.setTextColor(getResources().getColor(R.color.white));
            
//            String[] property_va4 = new String[] {pengcode,"08" };//equipType = 02是温度  03湿度  07光照  08二氧化碳
//            soapService.collection(property_va4);
            break;
        case R.id.endplant_tv:

            showDialog();
            break;
        default:
            break;
        }
    }
    
    private void setListeners() {
//        listview_sceneslist.setOnItemClickListener(new OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                System.out.println("Click Towns");
//                farmname = farmList.get(position).getFarmName();
//                SharedPreferences sp = getSharedPreferences("farm", Context.MODE_PRIVATE); 
//                Editor editor = sp.edit();
//                editor.putString("farmname", farmname);
//                editor.putString("farmcode", farmcode);
//                editor.commit();
//                finish();
//            }
//        });
    }
    int floatToInt(float f){  
        int i = 0;  
        if(f>0) //正数  
          i = (int) ((f*10 + 5)/10);  
        else if(f<0) //负数  
          i = (int) ((f*10 - 5)/10);  
        else i = 0;  
      
        return i;  
      
    }
   @Override
   public void onEvent(Object obj) {
       // TODO Auto-generated method stub
       super.onEvent(obj);
       SoapRes res = (SoapRes) obj;
       if (res.getCode().equals(SOAP_UTILS.METHOD.PLANTING)) {
           if (res.getObj() != null) {
               try {
               JSONObject json_objs = new JSONObject(res.getObj().toString());
      
               if (json_objs.get("result").toString().equals("true")) {
                   plantingList.clear();
//                   JSONArray json_arr = json_objs.getJSONArray("data");

                 JSONObject json_obj=  json_objs.getJSONObject("data");
//                   if(json_arr.length()!=0){

                       greenhouse_layout.setVisibility(1);
                       unlogin_layout.setVisibility(8);
                       
//                   for (int i = 0; i < json_arr.length(); i++) {
//                       JSONObject json_obj= (JSONObject) json_arr.get(i);
                       
                       Planting planting = new Planting();
                       planting.setId(json_obj.getString("id"));
                       dinishplantid = json_obj.getString("id");
                       planting.setCropCode(json_obj.getString("cropCode"));
                       planting.setPengCode(json_obj.getString("pengCode"));
                       planting.setBeginTime(json_obj.getString("beginTime"));
                       planting.setEndTime(json_obj.getString("endTime"));
                       planting.setCropVariety(json_obj.getString("cropVariety"));
                       planting.setPlantStandard(json_obj.getString("plantStandard"));
                       planting.setPlantStatus(json_obj.getString("plantStatus"));
                       
                       String begintime = json_obj.getString("beginTime");
                       String endtime = json_obj.getString("endTime");
                       String plantdate = json_obj.getString("plantDays");
                       String plantschedule = json_obj.getString("plantSchedule");
                       begintime_tv.setText("开始时间:" + begintime);
                       endtime_tv.setText("结束时间:"+ endtime);
                       plantdate_tv.setText("已定植" + plantdate +"天");
                       if(!plantschedule.equals("")&&!plantschedule.equals("null")){
    
                       Float status_f = Float.parseFloat(plantschedule);
                       Float width_f = grey_line_view.getMeasuredWidth()*status_f;
                       int width_int = floatToInt(width_f);
                       if(width_int!=0){

                           orange_line_view.setVisibility(1);
                           orange_line_view.getLayoutParams().width = width_int;
                       }else{

                           orange_line_view.setVisibility(8);
                       }

                       }
                       
                       JSONObject json_crop= json_obj.getJSONObject("crop");
                       planting.setCropName(json_crop.getString("cropName"));
                       planting.setAvatar(json_crop.getString("avatar"));

                       String cropName = json_crop.getString("cropName");
                       crop_name.setText(cropName);
                       String headPath = SOAP_UTILS.URL + json_crop.getString("avatar");
                       Instance.imageLoader.displayImage(headPath, crop_headpic, Instance.zuowu_s_options);
                       
                       JSONObject json_peng= json_obj.getJSONObject("peng");
                       String pengName =json_peng.getString("pengName");
                       String pengType =json_peng.getString("pengType");
                       String planting_str =json_peng.getString("planting");
                       String sensorCount =json_peng.getString("sensorCount");
                       String controlCount =json_peng.getString("controlCount");

                       
                       plantingList.add(planting);
                       
                   if(plantingList.size()!=0){ 
//                       farmListAdapter.notifyDataSetChanged();
                   
                   }else{
                       greenhouse_layout.setVisibility(8);
                       unlogin_layout.setVisibility(1);
                   }
                }
               } catch (JSONException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
                   greenhouse_layout.setVisibility(8);
                   unlogin_layout.setVisibility(1);
               }

           }else{
               greenhouse_layout.setVisibility(8);
               unlogin_layout.setVisibility(1);
               Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
           }
           

           String[] property_va = new String[] {pengcode };
           soapService.getPengAlarm(property_va);
       }else  if (res.getCode().equals(SOAP_UTILS.METHOD.PENGALARM)) {
           if (res.getObj() != null) {
               try {

                   JSONObject json_objs = new JSONObject(res.getObj().toString());
                   if (json_objs.get("result").toString().equals("true")) {
                       pengAlarmList.clear();
                   JSONObject json_objdata = json_objs.getJSONObject("data");
                   if(json_objdata.length()!=0){
                       linechart_layout.setVisibility(1);
                    
                   todayCount = json_objdata.getString("todayCount");
                   String changeRate_str = json_objdata.getString("changeRate");

                   alarmcount_tv.setText(todayCount + "次");
                   if(changeRate_str.substring(0, 1).toString().equals("-")){
                       changeRate = changeRate_str.substring(1).toString();
//                       //小数转百分数
//                       NumberFormat nf = NumberFormat.getPercentInstance();
//                       nf.setMaximumFractionDigits(1);//这个1的意识是保存结果到小数点后几位，但是特别声明：这个结果已经先＊100了。
//                       changeRate =nf.format(changeRate_str);//自动四舍五入。

                       alarmpercent_tv.setText(changeRate + "%");
                       alarmupdown_img.setBackgroundResource(R.drawable.down);
                   }else{

//                       //小数转百分数
//                       NumberFormat nf = NumberFormat.getPercentInstance();
//                       nf.setMaximumFractionDigits(1);//这个1的意识是保存结果到小数点后几位，但是特别声明：这个结果已经先＊100了。
//                       changeRate =nf.format(changeRate_str);//自动四舍五入。
                       changeRate =changeRate_str;
                       alarmpercent_tv.setText(changeRate + "%");
                       alarmupdown_img.setBackgroundResource(R.drawable.up);
                   }

                   JSONArray json_arr = json_objdata.getJSONArray("alist");
                   
                   for (int i = 0; i < json_arr.length(); i++) {
                       JSONObject json_obj= (JSONObject) json_arr.get(i);
//                       PengAlarm farm = new PengAlarm();
//                       farm.setPengCode(json_obj.getString("pengCode"));
//                       farm.setAlarmCount(json_obj.getString("alarmCount"));
                       
                       String occtime = json_obj.getString("occTime");
                       int occtime_int = Integer.parseInt(occtime);
                       String alarmcount =json_obj.getString("alarmCount");
                       
                      score[occtime_int] = Integer.parseInt(alarmcount);
                       
                   }
                   getAxisXLables();//获取x轴的标注
                   getAxisPoints();//获取坐标点
                   initLineChart();//初始化
                   
                   }else{

                       linechart_layout.setVisibility(8); 
                   }
                }else{
                    linechart_layout.setVisibility(8);
//                    String msg =json_objs.get("message").toString();
//                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                }
               } catch (JSONException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
               }

           }else{
               Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
           }

           String[] property_vac = new String[] {pengcode,"02" };
           soapService.collection(property_vac);

       }else  if (res.getCode().equals(SOAP_UTILS.METHOD.COLLECTIONLIST)) {
           if (res.getObj() != null) {
               try {

                   JSONObject json_objs = new JSONObject(res.getObj().toString());
                   if (json_objs.get("result").toString().equals("true")) {
                       linechart_layout2.setVisibility(1);
                   JSONArray json_arr = json_objs.getJSONArray("data");
                   if(json_arr.length()!=0){
                   lineChart2.setVisibility(1);
                   for (int i = 0; i < json_arr.length(); i++) {
                       JSONObject json_obj= (JSONObject) json_arr.get(i);
//                       Environment environment = new Environment();
//                       environment.setCreateDate(json_obj.getString("createDate"));
//                       environment.setEquipCode(json_obj.getString("equipCode"));
//                       environment.setEquipType(json_obj.getString("equipType"));
//                       environment.setId(json_obj.getString("id"));
//                       environment.setPengCode(json_obj.getString("pengCode"));
//                       environment.setSensorData(json_obj.getString("sensorData"));
//                       environment.setSwitchId(json_obj.getString("switchId"));
                       

                       String alarmcount =json_obj.getString("sensorData");
                       String occtime_hour = json_obj.getString("createDate").substring(11,13);
//                       String occtime_min = json_obj.getString("createDate").substring(14);
//                       Float  occtime_min_result = Float.parseFloat(occtime_min)/60;
//                       Float  occtime_result = occtime_min_result +  Float.parseFloat(occtime_hour);
                       

                       int occtime_int = Integer.parseInt(occtime_hour);
//                       if(score2[occtime_int]==0){
//                       score2[occtime_int] = Float.parseFloat(alarmcount);
//                       }
                       
                       if(score2[occtime_int*4]==0){
                           score2[occtime_int*4] = Float.parseFloat(alarmcount);
                         }else if(score2[occtime_int*4+1]==0){

                             score2[occtime_int*4+1] = Float.parseFloat(alarmcount);
                         }else if(score2[occtime_int*4+2]==0){

                             score2[occtime_int*4+2] = Float.parseFloat(alarmcount);
                         }else if(score2[occtime_int*4+3]==0){

                             score2[occtime_int*4+3] = Float.parseFloat(alarmcount);
                         }
//                           scorex2[i] =  occtime_result;  
//                           score2[i] = Float.parseFloat(alarmcount);
                       
                   }
                   getAxisXLables2();//获取x轴的标注
                   getAxisPoints2();//获取坐标点
                   initLineChart2();//初始化
                   }else{
//                       String msg =json_objs.get("message").toString();
                       linechart_layout2.setVisibility(8);
//                       Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                   }
                }else{
                    linechart_layout2.setVisibility(8);
//                    String msg =json_objs.get("message").toString();
//                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                }
               } catch (JSONException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
               }

           }else{
               Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
           }

           String[] property_vas = new String[] {pengcode };
           soapService.scenesList(property_vas);
       }else  if (res.getCode().equals(SOAP_UTILS.METHOD.SCENESLIST)) {
           if (res.getObj() != null) {
               try {

                   JSONObject json_objs = new JSONObject(res.getObj().toString());
                   if (json_objs.get("result").toString().equals("true")) {
                       scenesList.clear();
                   JSONArray json_arr = json_objs.getJSONArray("data");
                   for (int i = 0; i < json_arr.length(); i++) {
                       JSONObject json_obj= (JSONObject) json_arr.get(i);
                       Scenes scenes = new Scenes();
                       scenes.setId(json_obj.getString("id"));
                       scenes.setParameter(json_obj.getString("parameter"));
                       scenes.setPengCode(json_obj.getString("pengCode"));
                       scenes.setScenesName(json_obj.getString("scenesName"));
                       scenes.setAction(json_obj.getString("action"));
                       scenes.setConditionType(json_obj.getString("conditionType"));
                       scenes.setEffectEndTime(json_obj.getString("effectEndTime"));
                       scenes.setEffectBeginTime(json_obj.getString("effectBeginTime"));
                       scenes.setEquipCode(json_obj.getString("equipCode"));
                       scenes.setScenesValue(json_obj.getString("scenesValue"));
                       scenes.setContinued(json_obj.getString("continued"));
                       scenes.setEffectCodition(json_obj.getString("effectCodition"));
                       
                       scenesList.add(scenes);
                   } 
                   if(scenesList.size()!=0){ 
                       scenesListAdapter.notifyDataSetChanged();

                       setListViewHeightBasedOnChildren(listview_sceneslist);
                   }else{
                       scenes_layout.setVisibility(8);
                   }
            
                   }
               } catch (JSONException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
               }

           }else{
               Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
           }

           String[] property_vae = new String[] {pengcode,"2" };
           soapService.equipmentList(property_vae);
       }else  if (res.getCode().equals(SOAP_UTILS.METHOD.EQUIPMENTLIST)) {
           if (res.getObj() != null) {
               try {

                   JSONObject json_objs = new JSONObject(res.getObj().toString());
                   if (json_objs.get("result").toString().equals("true")) {
                   JSONArray json_arr = json_objs.getJSONArray("data");
                   for (int i = 0; i < json_arr.length(); i++) {
                       JSONObject json_obj= (JSONObject) json_arr.get(i);
                       Equipment equipment = new Equipment();
                       equipment.setId(json_obj.getString("id"));
                       equipment.setCreateDate(json_obj.getString("createDate"));
                       equipment.setStatus(json_obj.getString("status"));
                       equipment.setEquipCode(json_obj.getString("equipCode"));
                       equipment.setEquipName(json_obj.getString("equipName"));
                       equipment.setEquipKind(json_obj.getString("equipKind"));
                       equipment.setEquipStatus(json_obj.getString("equipStatus"));
                       equipment.setEquipType(json_obj.getString("equipType"));
                       equipment.setPengCode(json_obj.getString("pengCode"));
                       equipment.setSwitchId(json_obj.getString("switchId"));
                       equipment.setSwitchType(json_obj.getString("switchType"));
                       equipment.setSwitchCount(json_obj.getString("switchCount"));
                       
                       equipmentList.add(equipment);
                   } 
                   
                   if(equipmentList.size()!=0){ 
                       equipmentListAdapter.notifyDataSetChanged();

                       setListViewHeightBasedOnChildren(listview_equipmentlist);
                   }else{
                       listview_equipmentlist.setVisibility(8);
                       none_layout.setVisibility(1);
                   }
            
                }
               } catch (JSONException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
               }

           }else{
               Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
           }

       }else  if (res.getCode().equals(SOAP_UTILS.METHOD.FINISHPLANT)) {
           if (res.getObj() != null) {
               try {

                   JSONObject json_objs = new JSONObject(res.getObj().toString());
                   if (json_objs.get("result").toString().equals("true")) {
                   String message = json_objs.get("message").toString();
                       Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                       finish();
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

       }else  if (res.getCode().equals(SOAP_UTILS.METHOD.EQUIPMENTSTATE)) {
           if (res.getObj() != null) {
               try {

                   JSONObject json_objs = new JSONObject(res.getObj().toString());
                   if (json_objs.get("result").toString().equals("true")) {
                   String message = json_objs.get("message").toString();
                       Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
//                       finish();
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
   
   /**
   * 动态设置ListView的高度
   * @param listView
   */
   public static void setListViewHeightBasedOnChildren(ListView listView) {
       if(listView == null) return;
       ListAdapter listAdapter = listView.getAdapter();
       if (listAdapter == null) {
           // pre-condition
           return;
       }
       int totalHeight = 0;
       for (int i = 0; i < listAdapter.getCount(); i++) {
           View listItem = listAdapter.getView(i, null, listView);
           listItem.measure(0, 0);
           totalHeight += listItem.getMeasuredHeight();
       }
       ViewGroup.LayoutParams params = listView.getLayoutParams();
       params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
       listView.setLayoutParams(params);
   }
   
//环境折线图
   
   /**
    * 初始化LineChart的一些设置
    */
   private void initLineChart2(){
       Line line = new Line(mPointValues2).setColor(Color.parseColor("#0cdb76"));  //折线的颜色绿色
       List<Line> lines = new ArrayList<Line>();    
       line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.SQUARE）
       
       line.setCubic(false);//曲线是否平滑
//     line.setStrokeWidth(3);//线条的粗细，默认是3
       line.setFilled(false);//是否填充曲线的面积
       line.setHasLabels(false);//曲线的数据坐标是否加上备注
//     line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
       line.setHasLines(true);//是否用直线显示。如果为false 则没有曲线只有点显示    
       line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示    
       line.setPointRadius(2);//坐标空心
       lines.add(line);  
       LineChartData data = new LineChartData();  
       data.setLines(lines);  
       
       //坐标轴  
       Axis axisX = new Axis(); //X轴  
       axisX.setHasTiltedLabels(false);  //X轴下面坐标轴字体是斜的显示还是直的，true是斜的显示 
//     axisX.setTextColor(Color.WHITE);  //设置字体颜色
       axisX.setTextColor(Color.parseColor("#abb3b9"));//灰色
       
//     axisX.setName("未来几天的天气");  //表格名称
       axisX.setTextSize(11);//设置字体大小
       axisX.setMaxLabelChars(7); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisValues.length
       axisX.setValues(mAxisXValues2);  //填充X轴的坐标名称
       data.setAxisXBottom(axisX); //x 轴在底部     
//     data.setAxisXTop(axisX);  //x 轴在顶部
       axisX.setHasLines(true); //x 轴分割线
       
       
       Axis axisY = new Axis();  //Y轴  
       axisY.setName("");//y轴标注
       axisY.setTextSize(12);//设置字体大小
       axisY.setHasLines(true); //y 轴分割线
       data.setAxisYLeft(axisY);  //Y轴设置在左边
     //data.setAxisYRight(axisY);  //y轴设置在右边
     //设置行为属性，支持缩放、滑动以及平移  
       lineChart2.setInteractive(true); 
       lineChart2.setZoomType(ZoomType.HORIZONTAL);  //缩放类型，水平
//       lineChart2.setMaxZoom((float) 3);//缩放比例
       lineChart2.setMaxZoom((float) 1);//缩放比例
       lineChart2.setLineChartData(data);  
       lineChart2.setVisibility(View.VISIBLE);

       /**注：下面的7，10只是代表一个数字去类比而已
        * 见（http://forum.xda-developers.com/tools/programming/library-hellocharts-charting-library-t2904456/page2）;
        * 下面几句可以设置X轴数据的显示个数（x轴0-7个数据），当数据点个数小于（29）的时候，缩小到极致hellochart默认的是所有显示。当数据点个数大于（29）的时候，
        * 若不设置axisX.setMaxLabelChars(int count)这句话,则会自动适配X轴所能显示的尽量合适的数据个数。
        * 若设置axisX.setMaxLabelChars(int count)这句话,
        * 33个数据点测试，若 axisX.setMaxLabelChars(10);里面的10大于v.right= 7; 里面的7，则
                        刚开始X轴显示7条数据，然后缩放的时候X轴的个数会保证大于7小于10
                若小于v.right= 7;中的7,反正我感觉是这两句都好像失效了的样子 - -!
        * 并且Y轴是根据数据的大小自动设置Y轴上限
        * 若这儿不设置 v.right= 7; 这句话，则图表刚开始就会尽可能的显示所有数据，交互性太差
        */
       Viewport v = new Viewport(lineChart2.getMaximumViewport()); 
         v.left = 0; 
         v.right= 7; 
         lineChart2.setCurrentViewport(v);
   }
   
   /**
    * X 轴的显示
    */
   private void getAxisXLables2(){
       for (int i = 0; i < date2.length; i++) {    
           mAxisXValues2.add(new AxisValue(i).setLabel(date2[i]));    
       }       
   }
   /**
    * 图表的每个点的显示
    */
   private void getAxisPoints2(){
       for (int i = 0; i < score2.length; i++) {    
           mPointValues2.add(new PointValue(i, score2[i]));    
//           mPointValues2.add(new PointValue(scorex2[i], score2[i]));       
       }       
   }
   
//折线图
   
   /**
    * 初始化LineChart的一些设置
    */
   private void initLineChart(){
       Line line = new Line(mPointValues).setColor(Color.parseColor("#db0c0c"));  //折线的颜色红色
       List<Line> lines = new ArrayList<Line>();    
       line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.SQUARE）
       
       line.setCubic(false);//曲线是否平滑
//     line.setStrokeWidth(3);//线条的粗细，默认是3
       line.setFilled(false);//是否填充曲线的面积
       line.setHasLabels(false);//曲线的数据坐标是否加上备注
//     line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
       line.setHasLines(true);//是否用直线显示。如果为false 则没有曲线只有点显示    
       line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示    
       line.setPointRadius(2);//坐标空心
       lines.add(line);  
       LineChartData data = new LineChartData();  
       data.setLines(lines);  
       
       //坐标轴  
       Axis axisX = new Axis(); //X轴  
       axisX.setHasTiltedLabels(false);  //X轴下面坐标轴字体是斜的显示还是直的，true是斜的显示 
//     axisX.setTextColor(Color.WHITE);  //设置字体颜色
       axisX.setTextColor(Color.parseColor("#abb3b9"));//灰色
       
//     axisX.setName("未来几天的天气");  //表格名称
       axisX.setTextSize(11);//设置字体大小
       axisX.setMaxLabelChars(7); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisValues.length
       axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
       data.setAxisXBottom(axisX); //x 轴在底部     
//     data.setAxisXTop(axisX);  //x 轴在顶部
       axisX.setHasLines(true); //x 轴分割线
       
       
       Axis axisY = new Axis();  //Y轴  
       axisY.setName("");//y轴标注
       axisY.setTextSize(12);//设置字体大小
       axisY.setHasLines(true); //y 轴分割线
       data.setAxisYLeft(axisY);  //Y轴设置在左边
     //data.setAxisYRight(axisY);  //y轴设置在右边
     //设置行为属性，支持缩放、滑动以及平移  
       lineChart.setInteractive(true); 
       lineChart.setZoomType(ZoomType.HORIZONTAL);  //缩放类型，水平
//       lineChart.setMaxZoom((float) 3);//缩放比例
       lineChart.setMaxZoom((float) 1);//缩放比例
       lineChart.setLineChartData(data);  
       lineChart.setVisibility(View.VISIBLE);

       /**注：下面的7，10只是代表一个数字去类比而已
        * 见（http://forum.xda-developers.com/tools/programming/library-hellocharts-charting-library-t2904456/page2）;
        * 下面几句可以设置X轴数据的显示个数（x轴0-7个数据），当数据点个数小于（29）的时候，缩小到极致hellochart默认的是所有显示。当数据点个数大于（29）的时候，
        * 若不设置axisX.setMaxLabelChars(int count)这句话,则会自动适配X轴所能显示的尽量合适的数据个数。
        * 若设置axisX.setMaxLabelChars(int count)这句话,
        * 33个数据点测试，若 axisX.setMaxLabelChars(10);里面的10大于v.right= 7; 里面的7，则
                        刚开始X轴显示7条数据，然后缩放的时候X轴的个数会保证大于7小于10
                若小于v.right= 7;中的7,反正我感觉是这两句都好像失效了的样子 - -!
        * 并且Y轴是根据数据的大小自动设置Y轴上限
        * 若这儿不设置 v.right= 7; 这句话，则图表刚开始就会尽可能的显示所有数据，交互性太差
        */
       Viewport v = new Viewport(lineChart.getMaximumViewport()); 
         v.left = 0; 
         v.right= 7; 
         lineChart.setCurrentViewport(v);
   }
   
   /**
    * X 轴的显示
    */
   private void getAxisXLables(){
       for (int i = 0; i < date.length; i++) {    
           mAxisXValues.add(new AxisValue(i).setLabel(date[i]));    
       }       
   }
   /**
    * 图表的每个点的显示
    */
   private void getAxisPoints(){
       for (int i = 0; i < score.length; i++) {    
           mPointValues.add(new PointValue(i, score[i]));      
       }       
   }
   

   public void showDialog() {
       CustomDialog.Builder builder = new CustomDialog.Builder(context);
       builder.setMessage("");
       builder.setTitle("是否确定结束种植");
       builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int which) {
               dialog.dismiss();
               // 设置你的操作事项

               String[] property_va = new String[] {dinishplantid };
               soapService.finishplant(property_va);
           }
       });

       builder.setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int which) {
               dialog.dismiss();
           }
       });

       builder.create().show();
   }
}