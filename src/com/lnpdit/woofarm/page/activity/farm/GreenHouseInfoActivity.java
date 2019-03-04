package com.lnpdit.woofarm.page.activity.farm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.entity.Equipment;
import com.lnpdit.woofarm.entity.EquipmentChannel;
import com.lnpdit.woofarm.entity.PengAlarm;
import com.lnpdit.woofarm.entity.Planting;
import com.lnpdit.woofarm.entity.Scenes;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.instance.Instance;
import com.lnpdit.woofarm.page.adapter.ApplyExtendableListViewAdapter;
import com.lnpdit.woofarm.page.adapter.ScenesListAdapter;
import com.lnpdit.woofarm.utils.NestedExpandaleListView;
import com.lnpdit.woofarm.utils.SOAP_UTILS;
import com.lnpdit.woofarm.widget.CustomDialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import lecho.lib.hellocharts.formatter.SimpleLineChartValueFormatter;
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
//    private ListView listview_equipmentlist;
//    private EquipmentListAdapter equipmentListAdapter;
    NestedExpandaleListView listview_equipmentlist;
    ApplyExtendableListViewAdapter expandableAdapter;
    
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
    private TextView alarminfoset_tv;//报警详情
    private TextView alarmpercent_tv;
    private ImageView alarmupdown_img;
    private TextView endplant_tv;//结束种植
    private TextView label_tv1,label_tv2,label_tv3,label_tv4,label_tv5,namelabel_tv;//温度  湿度 光照 co2 图表左侧标签
    private TextView fengji1_label1,fengji1_label2,fengji1_label3,
                     fengji2_label1,fengji2_label2,fengji2_label3,
                     diancifa1_label1,diancifa1_label2,diancifa1_label3,
                     buguangdeng1_label1,buguangdeng1_label2,buguangdeng1_label3;
    private TextView gateway1_tv,gateway2_tv,gateway3_tv,gateway4_tv,gateway5_tv,gateway6_tv;//网关
    private TextView shipin_tv1,shipin_tv2,shipin_tv3,shipin_tv4;//摄像头
    private RelativeLayout camera1_layout,camera2_layout,camera3_layout,camera4_layout;//摄像头
    private TextView air_temperature_tv,air_humidity_tv,light_tv,soil_temperature_tv,soil_humidity_tv,co2_tv,soilph_tv;//传感器数据
    private ImageView video_img;//视频监控
    private ImageView air_temperature_btn,air_humidity_btn,light_btn,soil_temperature_btn,soil_humidity_btn,co2_btn,soilph_btn;

    private View red_line;
    private View green_line1;
    private View green_line2;
    private View green_line3;
    Context context;
    private String plantId = "";
    private String farmname = "";
    private String pengcode = "";
    private String farmcode = "";
    private String pengname = "";
    private String todayCount = "";
    private String changeRate = "";
    private String dinishplantid = "";
    String stri = "";
    //网关设备
    String equipCode1 ="";
    String equipName1 ="";
    String equipCode2 ="";
    String equipName2 ="";
    String equipCode3 ="";
    String equipName3 ="";
    String equipCode4 ="";
    String equipName4 ="";
    String equipCode5 ="";
    String equipName5 ="";
    String equipCode6 ="";
    String equipName6 ="";
    //设备传感器数据
    String air_temperature ="";
    String air_humidity ="";
    String brightness ="";
    String soil_temperature ="";
    String soil_humidity ="";
    String co2 ="";
    String soil_ph ="";

    //摄像头列表
    String cameraCode1 ="";
    String cameraCode2 ="";
    String cameraCode3 ="";
    String cameraCode4 ="";
    String cameraId1 ="";
    String cameraId2 ="";
    String cameraId3 ="";
    String cameraId4 ="";
    String m3u81 ="";
    String m3u82 ="";
    String m3u83 ="";
    String m3u84 ="";
    
    //控制设备
    private String channelStatus ="";
    private String channelType ="";
    private String channelName ="";
    private String channelNo ="";
    private String equipName_str="";
    private String equipCode_str = "";

    // 创建一级条目容器
    List<Map<String, String>> gruops = new ArrayList<Map<String, String>>();
    // 存放内容, 以便显示在列表中
    List<List<Map<String, String>>> childs = new ArrayList<List<Map<String, String>>>();

    Map<String, String> title_1 = new HashMap<String, String>();
    // Map<String, String> title_1_content_1 = new HashMap<String, String>();
    List<Map<String, String>> childs_1 = new ArrayList<Map<String, String>>();

//还原报警曲线
    private LineChartView lineChart;
    String[] date = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};//X轴的标注
    int[] score= {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};//图表的数据

    private List<PointValue> mPointValues_restore = new ArrayList<PointValue>();
    private List<AxisValue> mAxisXValues_restore = new ArrayList<AxisValue>();
    private ProgressDialog progressDialog;
    
    //报警折线图
    
    /* =========== 控件相关 ========== */
    private LineChartView mLineChartView_alarm; // 线性图表控件

    /* =========== 数据相关 ========== */
    private LineChartData mLineData_alarm; // 图表数据
    private int pointCount_alarm;
    String[] xData_alarm = { "0", "", "2", "", "4","","6","","8","","10"
            ,"","12","","14","","16","","18","","20","","22","","24"};// X轴的标注

    String[]  yData_alarm= new String[5];// y轴的标注
    String[] xLineData_alarm;// 点的横坐标
    String[] yLineData_alarm;// 点的纵坐标
    private List<AxisValue> mAxisXValues_alarm = new ArrayList<AxisValue>();
    private List<AxisValue> mAxisYValues_alarm = new ArrayList<AxisValue>();
    float[] myPoints_alarm; // 将线上的点放在一个数组中
    int numberOfMyPoints_alarm;
    
    //新折线图-环境曲线
    /* =========== 控件相关 ========== */
    private LineChartView mLineChartView; // 线性图表控件

    /* =========== 数据相关 ========== */
    private LineChartData mLineData; // 图表数据
    private int numberOfLines = 1; // 图上折线/曲线的显示条数
    // private int maxNumberOfLines = 4; // 图上折线/曲线的最多条数
    // private int numberOfPoints = 12; // 图上的节点数
    private int pointCount;
    String[] xData = { "0", "", "2", "", "4","","6","","8","","10"
            ,"","12","","14","","16","","18","","20","","22","","24"};// X轴的标注

    String[]   yData= new String[5];// y轴的标注
//    String[] yData = { "-4.00", "-2.67", "-1.33", "0", "1.33", "2.67", "4.00" };// y轴的标注
    String[] xLineData;// 点的横坐标
    String[] yLineData;// 点的纵坐标
    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();
    private List<AxisValue> mAxisYValues = new ArrayList<AxisValue>();

    /* =========== 状态相关 ========== */
    private boolean isHasAxes = true; // 是否显示坐标轴
    private boolean isHasAxesNames = false; // 是否显示坐标轴名称
    private boolean isHasLines = true; // 是否显示折线/曲线
    private boolean isHasPoints = true; // 是否显示线上的节点
    private boolean isFilled = false; // 是否填充线下方区域
    private boolean isHasPointsLabels = false; // 是否显示节点上的标签信息
    private boolean isCubic = false; // 是否是立体的
    private boolean isPointsHasSelected = false; // 设置节点点击后效果(消失/显示标签)
    private boolean isPointsHaveDifferentColor; // 节点是否有不同的颜色

    /* =========== 其他相关 ========== */
    private ValueShape pointsShape = ValueShape.CIRCLE; // 点的形状(圆/方/菱形)
    float[] myPoints; // 将线上的点放在一个数组中
    int numberOfMyPoints;
    double TopStringZ = 1;
    double TopStringF = 1;
    int linetype = 1;//环境曲线类型 1空气温度 2空气湿度 3光照强度
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_greenhouseinfo);

        Intent intent = getIntent();
//        farmcode = intent.getStringExtra("farmcode");
        pengname = intent.getStringExtra("plotName");
        pengcode = intent.getStringExtra("plotId");
        plantId = intent.getStringExtra("plantId");
        
        
        initView();
        //initLineChart2();
        initData();
        setListeners();
    }

    private void initView() {
        //场景
        listview_sceneslist = (ListView) findViewById(R.id.listview_sceneslist);
        listview_sceneslist.setFocusable(false);
        //控制面板
        none_layout = (LinearLayout) findViewById(R.id.none_layout); 
        listview_equipmentlist = (NestedExpandaleListView) findViewById(R.id.listview_equipmentlist);
        listview_equipmentlist.setFocusable(false);
        listview_equipmentlist.setChildDivider(new ColorDrawable(getResources().getColor(android.R.color.white)));
        
        video_img = (ImageView) findViewById(R.id.video_img);//视频监控
        video_img.setClickable(true);
        video_img.setOnClickListener(this);
        
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
        alarminfoset_tv = (TextView) findViewById(R.id.alarminfoset_tv);//报警详情
        alarminfoset_tv.setClickable(true);
        alarminfoset_tv.setOnClickListener(this);
        
       //温度湿度光照co2浓度
        label_tv1 = (TextView) findViewById(R.id.label_tv1);
        label_tv2 = (TextView) findViewById(R.id.label_tv2);
        label_tv3 = (TextView) findViewById(R.id.label_tv3);
        label_tv4 = (TextView) findViewById(R.id.label_tv4);
        label_tv5 = (TextView) findViewById(R.id.label_tv5);
        namelabel_tv = (TextView) findViewById(R.id.namelabel_tv);
        label_tv1.setClickable(true);
        label_tv2.setClickable(true);
        label_tv3.setClickable(true);
        label_tv4.setClickable(true);
        label_tv5.setClickable(true);
        label_tv1.setOnClickListener(this);
        label_tv2.setOnClickListener(this);
        label_tv3.setOnClickListener(this);
        label_tv4.setOnClickListener(this);
        label_tv5.setOnClickListener(this);
       //折线图
        lineChart = (LineChartView)findViewById(R.id.line_chart);//还原报警曲线

        red_line = (View) findViewById(R.id.red_line);//还原报警曲线红线
        red_line.setVisibility(8);
        
        linechart_layout = (LinearLayout)findViewById(R.id.linechart_layout);
        linechart_layout2 = (LinearLayout)findViewById(R.id.linechart_layout2);
        
        scenes_layout = (LinearLayout)findViewById(R.id.scenes_layout);
        
        //网关
        gateway1_tv = (TextView) findViewById(R.id.gateway1_tv);
        gateway2_tv = (TextView) findViewById(R.id.gateway2_tv);
        gateway3_tv = (TextView) findViewById(R.id.gateway3_tv);
        gateway4_tv = (TextView) findViewById(R.id.gateway4_tv);
        gateway5_tv = (TextView) findViewById(R.id.gateway5_tv);
        gateway6_tv = (TextView) findViewById(R.id.gateway6_tv);
        gateway1_tv.setClickable(true);
        gateway2_tv.setClickable(true);
        gateway3_tv.setClickable(true);
        gateway4_tv.setClickable(true);
        gateway5_tv.setClickable(true);
        gateway6_tv.setClickable(true);
        gateway1_tv.setOnClickListener(this);
        gateway2_tv.setOnClickListener(this);
        gateway3_tv.setOnClickListener(this);
        gateway4_tv.setOnClickListener(this);
        gateway5_tv.setOnClickListener(this);
        gateway6_tv.setOnClickListener(this);
        
        //摄像头列表
        shipin_tv1 = (TextView) findViewById(R.id.shipin_tv1);
        shipin_tv2 = (TextView) findViewById(R.id.shipin_tv2);
        shipin_tv3 = (TextView) findViewById(R.id.shipin_tv3);
        shipin_tv4 = (TextView) findViewById(R.id.shipin_tv4);
        
        camera1_layout = (RelativeLayout) findViewById(R.id.camera1_layout);
        camera2_layout = (RelativeLayout) findViewById(R.id.camera2_layout);
        camera3_layout = (RelativeLayout) findViewById(R.id.camera3_layout);
        camera4_layout = (RelativeLayout) findViewById(R.id.camera4_layout);
        camera1_layout.setClickable(true);
        camera2_layout.setClickable(true);
        camera3_layout.setClickable(true);
        camera4_layout.setClickable(true);
        camera1_layout.setOnClickListener(this);
        camera2_layout.setOnClickListener(this);
        camera3_layout.setOnClickListener(this);
        camera4_layout.setOnClickListener(this);
        
        //传感器数据
        air_temperature_tv = (TextView) findViewById(R.id.air_temperature_tv);
        air_humidity_tv = (TextView) findViewById(R.id.air_humidity_tv);
        light_tv = (TextView) findViewById(R.id.light_tv);
        soil_temperature_tv = (TextView) findViewById(R.id.soil_temperature_tv);
        soil_humidity_tv = (TextView) findViewById(R.id.soil_humidity_tv);
        co2_tv = (TextView) findViewById(R.id.co2_tv);
        soilph_tv = (TextView) findViewById(R.id.soilph_tv);
        

        air_temperature_btn = (ImageView) findViewById(R.id.air_temperature_btn);
        air_humidity_btn = (ImageView) findViewById(R.id.air_humidity_btn);
        light_btn = (ImageView) findViewById(R.id.light_btn);
        soil_temperature_btn = (ImageView) findViewById(R.id.soil_temperature_btn);
        soil_humidity_btn = (ImageView) findViewById(R.id.soil_humidity_btn);
        co2_btn = (ImageView) findViewById(R.id.co2_btn);
        soilph_btn = (ImageView) findViewById(R.id.soilph_btn);
        
//        red_line = (View) findViewById(R.id.red_line);
//        red_line.setVisibility(8);
        
        progressDialog = new ProgressDialog(this);
        stri = getResources().getString(R.string.Is_sending_a_request);
        progressDialog.setMessage(stri);
        progressDialog.setCanceledOnTouchOutside(false);
        
        //新折线图-环境曲线

        mLineChartView = (LineChartView) findViewById(R.id.lvc_main);
        /**
         * 禁用视图重新计算 主要用于图表在变化时动态更改，不是重新计算
         * 类似于ListView中数据变化时，只需notifyDataSetChanged()，而不用重新setAdapter() //
         */
        mLineChartView.setViewportCalculationEnabled(false);
        mLineChartView.setVisibility(View.VISIBLE);
//      //新折线图-报警
//
//        mLineChartView_alarm = (LineChartView) findViewById(R.id.line_chart);
//        /**
//         * 禁用视图重新计算 主要用于图表在变化时动态更改，不是重新计算
//         * 类似于ListView中数据变化时，只需notifyDataSetChanged()，而不用重新setAdapter() //
//         */
//        mLineChartView_alarm.setViewportCalculationEnabled(false);
//        mLineChartView_alarm.setVisibility(View.VISIBLE);
        
    }

    private void initData() {

//        //场景模式
//        scenesListAdapter = new ScenesListAdapter(context, scenesList);
//        listview_sceneslist.setAdapter(scenesListAdapter);
//        //控制面板
//        expandableAdapter= new ApplyExtendableListViewAdapter(context, equipmentList);
//        listview_equipmentlist.setAdapter(expandableAdapter);
//        SharedPreferences sharedPreferences = getSharedPreferences("userinfo",Activity.MODE_PRIVATE);  
//        String userCode = sharedPreferences.getString("userCode","");  
         if(pengcode.isEmpty()){

             greenhouse_layout.setVisibility(8);
             unlogin_layout.setVisibility(1);
             
         }else{

             greenhouse_layout.setVisibility(1);
             unlogin_layout.setVisibility(8);

             //种植信息
             String[] property_vas = new String[] {plantId };
             soapService.planting(property_vas);
         }

//       //网关列表
//        String[] property_va = new String[] {pengcode };
//        soapService.getGatewayList(property_va);

    }
  
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.tv_back:
            finish();
            break;
        case R.id.alarminfoset_tv:
            
            Intent in_alarminfo = new Intent();
            in_alarminfo.putExtra("pengCode", pengcode);
            in_alarminfo.setClass(GreenHouseInfoActivity.this, FarmAlarmInfoListActivity.class);
            startActivity(in_alarminfo);
            break;
        case R.id.add_tv:
            
            Intent in_adddevice = new Intent();
            in_adddevice.putExtra("pengCode", pengcode);
            in_adddevice.setClass(GreenHouseInfoActivity.this, FarmAddAdviceActivity.class);
            startActivity(in_adddevice);
            break;
        case R.id.label_tv1:
            label_tv1.setBackgroundResource(R.drawable.broder_orange);
            label_tv2.setBackgroundResource(R.drawable.broder_graylabel);
            label_tv3.setBackgroundResource(R.drawable.broder_graylabel);
            label_tv4.setBackgroundResource(R.drawable.broder_graylabel);
            label_tv5.setBackgroundResource(R.drawable.broder_graylabel);

            label_tv1.setTextColor(getResources().getColor(R.color.white));
            label_tv2.setTextColor(getResources().getColor(R.color.item_graytext));
            label_tv3.setTextColor(getResources().getColor(R.color.item_graytext));
            label_tv4.setTextColor(getResources().getColor(R.color.item_graytext));
            label_tv5.setTextColor(getResources().getColor(R.color.item_graytext));
            
            namelabel_tv.setText(R.string.air_temperature);
            String[] property_va1 = new String[] {equipCode1,"03" };//equipType = 03是温度  04湿度  07光照 08二氧化碳
            soapService.collection(property_va1);
            break;
        case R.id.label_tv2:
            label_tv1.setBackgroundResource(R.drawable.broder_graylabel);
            label_tv2.setBackgroundResource(R.drawable.broder_orange);
            label_tv3.setBackgroundResource(R.drawable.broder_graylabel);
            label_tv4.setBackgroundResource(R.drawable.broder_graylabel);
            label_tv5.setBackgroundResource(R.drawable.broder_graylabel);

            label_tv1.setTextColor(getResources().getColor(R.color.item_graytext));
            label_tv2.setTextColor(getResources().getColor(R.color.white));
            label_tv3.setTextColor(getResources().getColor(R.color.item_graytext));
            label_tv4.setTextColor(getResources().getColor(R.color.item_graytext));
            label_tv5.setTextColor(getResources().getColor(R.color.item_graytext));

            namelabel_tv.setText(R.string.air_humidity);
            
            String[] property_va2 = new String[] {equipCode1,"04" };//equipType = 03是温度  04湿度  07光照 08二氧化碳
            soapService.collection3(property_va2);
            break;
        case R.id.label_tv3:
            label_tv1.setBackgroundResource(R.drawable.broder_graylabel);
            label_tv2.setBackgroundResource(R.drawable.broder_graylabel);
            label_tv3.setBackgroundResource(R.drawable.broder_orange);
            label_tv4.setBackgroundResource(R.drawable.broder_graylabel);
            label_tv5.setBackgroundResource(R.drawable.broder_graylabel);
            label_tv1.setTextColor(getResources().getColor(R.color.item_graytext));
            label_tv2.setTextColor(getResources().getColor(R.color.item_graytext));
            label_tv3.setTextColor(getResources().getColor(R.color.white));
            label_tv4.setTextColor(getResources().getColor(R.color.item_graytext));
            label_tv5.setTextColor(getResources().getColor(R.color.item_graytext));
            namelabel_tv.setText(R.string.light);
            
            String[] property_va3 = new String[] {equipCode1,"07" };//equipType = 03是温度  04湿度  07光照 08二氧化碳
            soapService.collection4(property_va3);
            break;
        case R.id.label_tv4:
            label_tv1.setBackgroundResource(R.drawable.broder_graylabel);
            label_tv2.setBackgroundResource(R.drawable.broder_graylabel);
            label_tv3.setBackgroundResource(R.drawable.broder_graylabel);
            label_tv4.setBackgroundResource(R.drawable.broder_orange);
            label_tv5.setBackgroundResource(R.drawable.broder_graylabel);

            label_tv1.setTextColor(getResources().getColor(R.color.item_graytext));
            label_tv2.setTextColor(getResources().getColor(R.color.item_graytext));
            label_tv3.setTextColor(getResources().getColor(R.color.item_graytext));
            label_tv4.setTextColor(getResources().getColor(R.color.white));
            label_tv5.setTextColor(getResources().getColor(R.color.item_graytext));

            namelabel_tv.setText(R.string.soil_temperature);
            
            String[] property_va4 = new String[] {equipCode1,"01" };//equipType = 03是温度  04湿度  07光照 08二氧化碳 01土壤温度 02土壤湿度
            soapService.collection5(property_va4);
            break;
        case R.id.label_tv5:
            label_tv1.setBackgroundResource(R.drawable.broder_graylabel);
            label_tv2.setBackgroundResource(R.drawable.broder_graylabel);
            label_tv3.setBackgroundResource(R.drawable.broder_graylabel);
            label_tv4.setBackgroundResource(R.drawable.broder_graylabel);
            label_tv5.setBackgroundResource(R.drawable.broder_orange);

            label_tv1.setTextColor(getResources().getColor(R.color.item_graytext));
            label_tv2.setTextColor(getResources().getColor(R.color.item_graytext));
            label_tv3.setTextColor(getResources().getColor(R.color.item_graytext));
            label_tv4.setTextColor(getResources().getColor(R.color.item_graytext));
            label_tv5.setTextColor(getResources().getColor(R.color.white));

            namelabel_tv.setText(R.string.soil_humidity);
            
            String[] property_va7 = new String[] {equipCode1,"02" };//equipType = 03是温度  04湿度  07光照 08二氧化碳 01土壤温度 02土壤湿度
            soapService.collection6(property_va7);
            break;
        case R.id.endplant_tv:

            showDialog();
            break;
        case R.id.video_img:
            Intent intent = new Intent();
            intent.setClass(context, VideoMonitor_Activity.class);
            startActivity(intent); 
            break;
        case R.id.gateway1_tv:

            gateway1_tv.setBackgroundResource(R.drawable.broder_orange);
            gateway1_tv.setTextColor(getResources().getColor(R.color.white));
            
            gateway2_tv.setBackgroundResource(R.drawable.broder_gray2);
            gateway2_tv.setTextColor(getResources().getColor(R.color.item_graytext4));
            gateway3_tv.setBackgroundResource(R.drawable.broder_gray2);
            gateway3_tv.setTextColor(getResources().getColor(R.color.item_graytext4));
            gateway4_tv.setBackgroundResource(R.drawable.broder_gray2);
            gateway4_tv.setTextColor(getResources().getColor(R.color.item_graytext4));
            gateway5_tv.setBackgroundResource(R.drawable.broder_gray2);
            gateway5_tv.setTextColor(getResources().getColor(R.color.item_graytext4));
            gateway6_tv.setBackgroundResource(R.drawable.broder_gray2);
            gateway6_tv.setTextColor(getResources().getColor(R.color.item_graytext4));

            progressDialog.show();

        //我的农场传感器数据
        String[] property_va = new String[] {equipCode1 };
        soapService.getSensorData(property_va);
            break;
        case R.id.gateway2_tv:

            gateway2_tv.setBackgroundResource(R.drawable.broder_orange);
            gateway2_tv.setTextColor(getResources().getColor(R.color.white));

            gateway1_tv.setBackgroundResource(R.drawable.broder_gray2);
            gateway1_tv.setTextColor(getResources().getColor(R.color.item_graytext4));
            gateway3_tv.setBackgroundResource(R.drawable.broder_gray2);
            gateway3_tv.setTextColor(getResources().getColor(R.color.item_graytext4));
            gateway4_tv.setBackgroundResource(R.drawable.broder_gray2);
            gateway4_tv.setTextColor(getResources().getColor(R.color.item_graytext4));
            gateway5_tv.setBackgroundResource(R.drawable.broder_gray2);
            gateway5_tv.setTextColor(getResources().getColor(R.color.item_graytext4));
            gateway6_tv.setBackgroundResource(R.drawable.broder_gray2);
            gateway6_tv.setTextColor(getResources().getColor(R.color.item_graytext4));

            progressDialog.show();

        //我的农场传感器数据
            String[] property_vaSenso2 = new String[] {equipCode2 };
            soapService.getSensorData(property_vaSenso2);
            break;
        case R.id.gateway3_tv:

            gateway3_tv.setBackgroundResource(R.drawable.broder_orange);
            gateway3_tv.setTextColor(getResources().getColor(R.color.white));

            gateway1_tv.setBackgroundResource(R.drawable.broder_gray2);
            gateway1_tv.setTextColor(getResources().getColor(R.color.item_graytext4));
            gateway2_tv.setBackgroundResource(R.drawable.broder_gray2);
            gateway2_tv.setTextColor(getResources().getColor(R.color.item_graytext4));
            gateway4_tv.setBackgroundResource(R.drawable.broder_gray2);
            gateway4_tv.setTextColor(getResources().getColor(R.color.item_graytext4));
            gateway5_tv.setBackgroundResource(R.drawable.broder_gray2);
            gateway5_tv.setTextColor(getResources().getColor(R.color.item_graytext4));
            gateway6_tv.setBackgroundResource(R.drawable.broder_gray2);
            gateway6_tv.setTextColor(getResources().getColor(R.color.item_graytext4));

            progressDialog.show();

        //我的农场传感器数据
            String[] property_vaSensor3 = new String[] {equipCode3 };
            soapService.getSensorData(property_vaSensor3);
            break;
        case R.id.gateway4_tv:

            gateway4_tv.setBackgroundResource(R.drawable.broder_orange);
            gateway4_tv.setTextColor(getResources().getColor(R.color.white));

            gateway1_tv.setBackgroundResource(R.drawable.broder_gray2);
            gateway1_tv.setTextColor(getResources().getColor(R.color.item_graytext4));
            gateway2_tv.setBackgroundResource(R.drawable.broder_gray2);
            gateway2_tv.setTextColor(getResources().getColor(R.color.item_graytext4));
            gateway3_tv.setBackgroundResource(R.drawable.broder_gray2);
            gateway3_tv.setTextColor(getResources().getColor(R.color.item_graytext4));
            gateway5_tv.setBackgroundResource(R.drawable.broder_gray2);
            gateway5_tv.setTextColor(getResources().getColor(R.color.item_graytext4));
            gateway6_tv.setBackgroundResource(R.drawable.broder_gray2);
            gateway6_tv.setTextColor(getResources().getColor(R.color.item_graytext4));

            progressDialog.show();

        //我的农场传感器数据
            String[] property_vaSensor4 = new String[] {equipCode4 };
            soapService.getSensorData(property_vaSensor4);
            break;
        case R.id.gateway5_tv:

            gateway5_tv.setBackgroundResource(R.drawable.broder_orange);
            gateway5_tv.setTextColor(getResources().getColor(R.color.white));

            gateway1_tv.setBackgroundResource(R.drawable.broder_gray2);
            gateway1_tv.setTextColor(getResources().getColor(R.color.item_graytext4));
            gateway2_tv.setBackgroundResource(R.drawable.broder_gray2);
            gateway2_tv.setTextColor(getResources().getColor(R.color.item_graytext4));
            gateway3_tv.setBackgroundResource(R.drawable.broder_gray2);
            gateway3_tv.setTextColor(getResources().getColor(R.color.item_graytext4));
            gateway4_tv.setBackgroundResource(R.drawable.broder_gray2);
            gateway4_tv.setTextColor(getResources().getColor(R.color.item_graytext4));
            gateway6_tv.setBackgroundResource(R.drawable.broder_gray2);
            gateway6_tv.setTextColor(getResources().getColor(R.color.item_graytext4));

            progressDialog.show();

        //我的农场传感器数据
            String[] property_va5 = new String[] {equipCode5 };
            soapService.getSensorData(property_va5);
            break;
        case R.id.gateway6_tv:

            gateway6_tv.setBackgroundResource(R.drawable.broder_orange);
            gateway6_tv.setTextColor(getResources().getColor(R.color.white));

            gateway1_tv.setBackgroundResource(R.drawable.broder_gray2);
            gateway1_tv.setTextColor(getResources().getColor(R.color.item_graytext4));
            gateway2_tv.setBackgroundResource(R.drawable.broder_gray2);
            gateway2_tv.setTextColor(getResources().getColor(R.color.item_graytext4));
            gateway3_tv.setBackgroundResource(R.drawable.broder_gray2);
            gateway3_tv.setTextColor(getResources().getColor(R.color.item_graytext4));
            gateway4_tv.setBackgroundResource(R.drawable.broder_gray2);
            gateway4_tv.setTextColor(getResources().getColor(R.color.item_graytext4));
            gateway5_tv.setBackgroundResource(R.drawable.broder_gray2);
            gateway5_tv.setTextColor(getResources().getColor(R.color.item_graytext4));

            progressDialog.show();

         //我的农场传感器数据
            String[] property_va6 = new String[] {equipCode6 };
            soapService.getSensorData(property_va6);
            break;
        case R.id.camera1_layout:

            Intent in_camera1 = new Intent();
            in_camera1.putExtra("cameraId", cameraId1);
            in_camera1.putExtra("m3u8", m3u81);
            in_camera1.setClass(GreenHouseInfoActivity.this, CameraInfo_Activity.class);
            startActivity(in_camera1);
            break;
        case R.id.camera2_layout:

            Intent in_camera2 = new Intent();
            in_camera2.putExtra("cameraId", cameraId2);
            in_camera2.putExtra("m3u8", m3u82);
            in_camera2.setClass(GreenHouseInfoActivity.this, CameraInfo_Activity.class);
            startActivity(in_camera2);
            break;
        case R.id.camera3_layout:

            Intent in_camera3 = new Intent();
            in_camera3.putExtra("cameraId", cameraId3);
            in_camera3.putExtra("m3u8", m3u83);
            in_camera3.setClass(GreenHouseInfoActivity.this, CameraInfo_Activity.class);
            startActivity(in_camera3);
            break;
        case R.id.camera4_layout:

            Intent in_camera4 = new Intent();
            in_camera4.putExtra("cameraId", cameraId4);
            in_camera4.putExtra("m3u8", m3u84);
            in_camera4.setClass(GreenHouseInfoActivity.this, CameraInfo_Activity.class);
            startActivity(in_camera4);
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
       if (res.getCode().equals(SOAP_UTILS.METHOD.PLANTING)) {//种植信息
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
//                       planting.setPengCode(json_obj.getString("pengCode"));
                       planting.setBeginTime(json_obj.getString("beginTime"));
                       planting.setEndTime(json_obj.getString("endTime"));
                       planting.setCropVariety(json_obj.getString("cropVariety"));
                       planting.setVarietyName(json_obj.getString("varietyName"));
                       crop_name.setText(json_obj.getString("varietyName"));
                       planting.setPlantStandard(json_obj.getString("plantStandard"));
                       planting.setPlantStatus(json_obj.getString("plantStatus"));

                       String headPath = SOAP_UTILS.PIC_PATH + json_obj.getString("plantImg");
                       Instance.imageLoader.displayImage(headPath, crop_headpic, Instance.zuowu_s_options);
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

                       JSONObject json_peng= json_obj.getJSONObject("plot");
                       String pengName =json_peng.getString("plotName");
//                       String pengType =json_peng.getString("pengType");
//                       String planting_str =json_peng.getString("planting");
//                       String sensorCount =json_peng.getString("sensorCount");
//                       String controlCount =json_peng.getString("controlCount");

                       plantingList.add(planting);
                       
                   if(plantingList.size()!=0){ 
//                       farmListAdapter.notifyDataSetChanged();
                   
                   }else{
                       greenhouse_layout.setVisibility(8);
                       unlogin_layout.setVisibility(1);
                   }
                }else{

                    greenhouse_layout.setVisibility(8);
                    unlogin_layout.setVisibility(1);
                }

//               //摄像头列表
//                String[] property_va_video = new String[] {pengcode };
//                soapService.getCameraList(property_va_video);
               } catch (JSONException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
                   greenhouse_layout.setVisibility(8);
                   unlogin_layout.setVisibility(1);

//                   //摄像头列表
//                    String[] property_va_video = new String[] {pengcode };
//                    soapService.getCameraList(property_va_video);
               }

           }else{
               greenhouse_layout.setVisibility(8);
               unlogin_layout.setVisibility(1);
               Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();

//               //摄像头列表
//                String[] property_va_video = new String[] {pengcode };
//                soapService.getCameraList(property_va_video);
           }
//         //摄像头列表
//          String[] property_va_video = new String[] {pengcode };
//          soapService.getCameraList(property_va_video);

        //控制面板
        String[] property_vae = new String[] {pengcode};
        soapService.equipmentList(property_vae);
          
       }else  if (res.getCode().equals(SOAP_UTILS.METHOD.PENGALARM)) {//24小时报警曲线
           if (res.getObj() != null) {
               try {

                   JSONObject json_objs = new JSONObject(res.getObj().toString());
                   if (json_objs.get("result").toString().equals("true")) {
                       linechart_layout.setVisibility(1);
                       pengAlarmList.clear();
                   JSONObject json_objdata = json_objs.getJSONObject("data");
                   if(json_objdata.length()!=0){
//                       lineChart.setVisibility(1);
                    
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
                   
                   if(json_arr.length()!=0){
                       
                       for (int i = 0; i < json_arr.length(); i++) {
                           JSONObject json_obj= (JSONObject) json_arr.get(i);
//                           PengAlarm farm = new PengAlarm();
//                           farm.setPengCode(json_obj.getString("pengCode"));
//                           farm.setAlarmCount(json_obj.getString("alarmCount"));
                           
                           String occtime = json_obj.getString("occTime");
                           int occtime_int = Integer.parseInt(occtime);
                           String alarmcount =json_obj.getString("alarmCount");
                           
                          score[occtime_int] = Integer.parseInt(alarmcount);
                           
                       }
                       getAxisXLables_restore();//获取x轴的标注
                       getAxisPoints_restore();//获取坐标点
                       initLineChart_restore();//初始化
                       }else{

                           red_line.setVisibility(1);
                           for (int i = 0; i < 24; i++) {
                               int occtime_int = i;
                               String alarmcount = "0";
                               
                              score[occtime_int] = Integer.parseInt(alarmcount);
                               
                           }
                           
                               getAxisXLables_restore();//获取x轴的标注
                               getAxisPoints_restore();//获取坐标点
                               initLineChart_restore();//初始化
                   }
//                   if(json_arr.length()!=0){
//                    
//                       String temp = "";
//                       pointCount_alarm=json_arr.length();
//                       String[] yLineDatatemp_alarm = new String[pointCount_alarm];
//                       String[] xLineDatatemp_alarm = new String[pointCount_alarm];
//                       
//                   for (int i = 0; i < json_arr.length(); i++) {
//                       JSONObject json_obj= (JSONObject) json_arr.get(i);
//
//                       String alarmcount =json_obj.getString("alarmCount");
//                       String occtime_hour = json_obj.getString("occTime");
//                       
//                       yLineDatatemp_alarm[i] = alarmcount;
//                       
//                       xLineDatatemp_alarm[i] = occtime_hour;
//                       
//                   }
//                   
//                   yData_alarm[0]="0";
//                   yData_alarm[1]="1";
//                   yData_alarm[2]="2";
//                   yData_alarm[3]="3";
//                   yData_alarm[4]="4";
//
//                   Integer lastPointString = Integer.parseInt(xLineDatatemp_alarm[pointCount_alarm-1]);//取最大点数的时间，此事件之前的所有点没有值的赋值0
//                   xLineData_alarm = new String[lastPointString + 1];//所有点的横坐标
//                   yLineData_alarm = new String[lastPointString + 1];//所有点的纵坐标
//                  
//                   for(int a = 0; a < lastPointString + 1; a++){
//                       xLineData_alarm[a]= "" + a ;
//                   }
//                   
//                   for (int j = 0; j < json_arr.length(); j++) {
//                       int pstr = Integer.parseInt(xLineDatatemp_alarm[j]);
//                       yLineData_alarm[pstr] = yLineDatatemp_alarm[j];
//                      
//                       }
//                   }else{
//                   xLineData_alarm = new String[24];//所有点的横坐标
//                   yLineData_alarm = new String[24];//所有点的纵坐标
//                       for (int i = 0; i < 24; i++) { 
//                           xLineData_alarm[i]="0";
//                           yLineData_alarm[i]="0";
//                       }
//                       
//                   }
//
//                   initChartAlarm();
                   }
                }else{
                }
               } catch (JSONException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
               }

           }else{
               Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
           }
           //网关列表
           String[] property_va = new String[] {pengcode };
           soapService.getGatewayList(property_va);
//           //环境走势
//           String[] property_vac = new String[] {equipCode1,"03" };
//           soapService.collection(property_vac);

       }else  if (res.getCode().equals(SOAP_UTILS.METHOD.COLLECTIONLIST)) {//环境走势
           if (res.getObj() != null) {
               try {

                   JSONObject json_objs = new JSONObject(res.getObj().toString());
                   if (json_objs.get("result").toString().equals("true")) {
                       linechart_layout2.setVisibility(1);
                   JSONArray json_arr = json_objs.getJSONArray("data");
             if(json_arr.length()!=0){
                       String temp = "";
                       pointCount=json_arr.length();
                       String[] yLineDatatemp = new String[pointCount];
                       String[] xLineDatatemp = new String[pointCount];
                       
                   for (int i = 0; i < json_arr.length(); i++) {
                       JSONObject json_obj= (JSONObject) json_arr.get(i);

                       String alarmcount =json_obj.getString("sensorData");
                       String occtime_hour = json_obj.getString("createDate");
                       
                       yLineDatatemp[i] = alarmcount;
                       
                       xLineDatatemp[i] = occtime_hour;
                       
                   }

                   for(int i=0;i<yData.length;i++){
                       yData[i]="";
                       }
                   yData[0]="-50";
                   yData[1]="-25";
                   yData[2]="0";
                   yData[3]="25";
                   yData[4]="50";

                   linetype = 1;//环境曲线类型 1空气温度 2空气湿度 3光照强度
                   
                   Integer lastPointString = Integer.parseInt(xLineDatatemp[pointCount-1]);//取最大点数的时间，此事件之前的所有点没有值的赋值0
                   xLineData = new String[lastPointString + 1];//所有点的横坐标
                   yLineData = new String[lastPointString + 1];//所有点的纵坐标
                  
                   for(int a = 0; a < lastPointString + 1; a++){
                       xLineData[a]= "" + a ;
                   }
                   
                   for (int j = 0; j < json_arr.length(); j++) {
                       int pstr = Integer.parseInt(xLineDatatemp[j]);
                       yLineData[pstr] = yLineDatatemp[j];
                      
                       }

                   TopStringZ = toStringZ(yLineData);
                   TopStringF = toStringF(yLineData);
                   
//                  //曲线纵坐标按照取的所有坐标值平分三份
//                   double top = keepTwo(TopStringF);
//                   double two = keepTwo(top / 3 * 2);
//                   double first = keepTwo(top / 3);
//                   yData[0] = "-" + TopStringF + "%";
//                   yData[1] = "-" + two + "%";
//                   yData[2] = "-" + first + "%";
//                   yData[3] = "0.00" + "%";
//                   yData[4] = "" + keepTwo(keepTwo(TopStringZ) / 3) + "%";
//                   yData[5] = "" + keepTwo(keepTwo(TopStringZ) / 3 * 2) + "%";
//                   yData[6] = TopStringZ + "%";
                   
                   initChart();
                   }else{ 
                       xLineData = new String[24];//所有点的横坐标
                       yLineData = new String[24];//所有点的纵坐标
                       for (int i = 0; i < 24; i++) {
                           xLineData[i]="0";
                           yLineData[i]="0";
                       }
                       
                   }
                }else{
                }
               } catch (JSONException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();

//                   //摄像头列表
//                    String[] property_va_video = new String[] {pengcode };
//                    soapService.getCameraList(property_va_video);
               }
//             //控制面板
//             String[] property_vae = new String[] {pengcode,"2" };
//             soapService.equipmentList(property_vae);
//               //环境走势
//               String[] property_vac = new String[] {equipCode1,"04" };
//               soapService.collection3(property_vac);
           }else{
               Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
           }

       }else  if (res.getCode().equals(SOAP_UTILS.METHOD.COLLECTIONLIST3)) {//环境走势--空气湿度
           if (res.getObj() != null) {
               try {

                   JSONObject json_objs = new JSONObject(res.getObj().toString());
                   if (json_objs.get("result").toString().equals("true")) {
                   JSONArray json_arr = json_objs.getJSONArray("data");
                   if(json_arr.length()!=0){
                       String temp = "";
                       pointCount=json_arr.length();
                       String[] yLineDatatemp = new String[pointCount];
                       String[] xLineDatatemp = new String[pointCount];
                       
                   for (int i = 0; i < json_arr.length(); i++) {
                       JSONObject json_obj= (JSONObject) json_arr.get(i);

                       String alarmcount =json_obj.getString("sensorData");
                       String occtime_hour = json_obj.getString("createDate");
                       
                       yLineDatatemp[i] = alarmcount;
                       
                       xLineDatatemp[i] = occtime_hour;
//                       Double topString =Double.valueOf(alarmcount);
//                       yData[i] ="" + topString;
                       
                   }
                   for(int i=0;i<yData.length;i++){
                       yData[i]="";
                       }
                   yData[0]="0";
                   yData[1]="25";
                   yData[2]="50";
                   yData[3]="75";
                   yData[4]="100";

                   linetype = 2;//环境曲线类型 1空气温度 2空气湿度 3光照强度
                   
                   Integer lastPointString = Integer.parseInt(xLineDatatemp[pointCount-1]);//取最大点数的时间，此事件之前的所有点没有值的赋值0
                   xLineData = new String[lastPointString + 1];//所有点的横坐标
                   yLineData = new String[lastPointString + 1];//所有点的纵坐标
                  
                   for(int a = 0; a < lastPointString + 1; a++){
                       xLineData[a]= "" + a ;
                   }
                   
                   for (int j = 0; j < json_arr.length(); j++) {
                       int pstr = Integer.parseInt(xLineDatatemp[j]);
                       yLineData[pstr] = yLineDatatemp[j];
                      
                       }
                   
                   TopStringZ = toStringZ(yLineData);
                   TopStringF = toStringF(yLineData);
                   
                   initChart();
                   }else{
                       xLineData = new String[24];//所有点的横坐标
                       yLineData = new String[24];//所有点的纵坐标
                       for (int i = 0; i < 24; i++) {
                           xLineData[i]="0";
                           yLineData[i]="0";
                       }
                       
                   }
                }
               } catch (JSONException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
               }
//               //环境走势
//               String[] property_vac = new String[] {equipCode1,"07" };
//               soapService.collection4(property_vac);
           }else{
               Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
           }
       }else  if (res.getCode().equals(SOAP_UTILS.METHOD.COLLECTIONLIST4)) {//环境走势--光照强度
           if (res.getObj() != null) {
               try {

                   JSONObject json_objs = new JSONObject(res.getObj().toString());
                   if (json_objs.get("result").toString().equals("true")) {
                   JSONArray json_arr = json_objs.getJSONArray("data");
                   if(json_arr.length()!=0){
                   String temp = "";
                   pointCount=json_arr.length();
                   String[] yLineDatatemp = new String[pointCount];
                   String[] xLineDatatemp = new String[pointCount];
                   
               for (int i = 0; i < json_arr.length(); i++) {
                   JSONObject json_obj= (JSONObject) json_arr.get(i);

                   String alarmcount =json_obj.getString("sensorData");
                   String occtime_hour = json_obj.getString("createDate");
                   
                   yLineDatatemp[i] = alarmcount;
                   
                   xLineDatatemp[i] = occtime_hour;
//                   Double topString =Double.valueOf(alarmcount);
//                   yData[i] ="" + topString;
                   
               }

               for(int i=0;i<yData.length;i++){
                   yData[i]="";
                   }
               yData[0]="0";
               yData[1]="25000";
               yData[2]="50000";
               yData[3]="75000";
               yData[4]="100000";

               linetype = 3;//环境曲线类型 1空气温度 2空气湿度 3光照强度
               
               Integer lastPointString = Integer.parseInt(xLineDatatemp[pointCount-1]);//取最大点数的时间，此事件之前的所有点没有值的赋值0
               xLineData = new String[lastPointString + 1];//所有点的横坐标
               yLineData = new String[lastPointString + 1];//所有点的纵坐标
              
               for(int a = 0; a < lastPointString + 1; a++){
                   xLineData[a]= "" + a ;
               }
               
               for (int j = 0; j < json_arr.length(); j++) {
                   int pstr = Integer.parseInt(xLineDatatemp[j]);
                   yLineData[pstr] = yLineDatatemp[j];
                  
                   }
               
               TopStringZ = toStringZ(yLineData);
               TopStringF = toStringF(yLineData);
               
               initChart();
               }else{
                   xLineData = new String[24];//所有点的横坐标
                   yLineData = new String[24];//所有点的纵坐标
                   for (int i = 0; i < 24; i++) {
                       xLineData[i]="0";
                       yLineData[i]="0";
                   }
                   
               }
            }
               } catch (JSONException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
               }

           }else{
               Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
           }
       }else  if (res.getCode().equals(SOAP_UTILS.METHOD.COLLECTIONLIST5)) {//环境走势-01土壤温度
           if (res.getObj() != null) {
               try {

                   JSONObject json_objs = new JSONObject(res.getObj().toString());
                   if (json_objs.get("result").toString().equals("true")) {
                       linechart_layout2.setVisibility(1);
                   JSONArray json_arr = json_objs.getJSONArray("data");
             if(json_arr.length()!=0){
                       String temp = "";
                       pointCount=json_arr.length();
                       String[] yLineDatatemp = new String[pointCount];
                       String[] xLineDatatemp = new String[pointCount];
                       
                   for (int i = 0; i < json_arr.length(); i++) {
                       JSONObject json_obj= (JSONObject) json_arr.get(i);

                       String alarmcount =json_obj.getString("sensorData");
                       String occtime_hour = json_obj.getString("createDate");
                       
                       yLineDatatemp[i] = alarmcount;
                       
                       xLineDatatemp[i] = occtime_hour;
                       
                   }

                   for(int i=0;i<yData.length;i++){
                       yData[i]="";
                       }
                   yData[0]="-50";
                   yData[1]="-25";
                   yData[2]="0";
                   yData[3]="25";
                   yData[4]="50";

                   linetype = 4;//环境曲线类型 1空气温度 2空气湿度 3光照强度4土壤温度5土壤湿度
                   
                   Integer lastPointString = Integer.parseInt(xLineDatatemp[pointCount-1]);//取最大点数的时间，此事件之前的所有点没有值的赋值0
                   xLineData = new String[lastPointString + 1];//所有点的横坐标
                   yLineData = new String[lastPointString + 1];//所有点的纵坐标
                  
                   for(int a = 0; a < lastPointString + 1; a++){
                       xLineData[a]= "" + a ;
                   }
                   
                   for (int j = 0; j < json_arr.length(); j++) {
                       int pstr = Integer.parseInt(xLineDatatemp[j]);
                       yLineData[pstr] = yLineDatatemp[j];
                      
                       }

                   TopStringZ = toStringZ(yLineData);
                   TopStringF = toStringF(yLineData);

                   
                   initChart();
                   }else{ 
                       xLineData = new String[24];//所有点的横坐标
                       yLineData = new String[24];//所有点的纵坐标
                       for (int i = 0; i < 24; i++) {
                           xLineData[i]="0";
                           yLineData[i]="0";
                       }
                       
                   }
                }else{
                }
               } catch (JSONException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();

               }

           }else{
               Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
           }

       }else  if (res.getCode().equals(SOAP_UTILS.METHOD.COLLECTIONLIST6)) {//环境走势--土壤湿度
           if (res.getObj() != null) {
               try {

                   JSONObject json_objs = new JSONObject(res.getObj().toString());
                   if (json_objs.get("result").toString().equals("true")) {
                   JSONArray json_arr = json_objs.getJSONArray("data");
                   if(json_arr.length()!=0){
                       String temp = "";
                       pointCount=json_arr.length();
                       String[] yLineDatatemp = new String[pointCount];
                       String[] xLineDatatemp = new String[pointCount];
                       
                   for (int i = 0; i < json_arr.length(); i++) {
                       JSONObject json_obj= (JSONObject) json_arr.get(i);

                       String alarmcount =json_obj.getString("sensorData");
                       String occtime_hour = json_obj.getString("createDate");
                       
                       yLineDatatemp[i] = alarmcount;
                       
                       xLineDatatemp[i] = occtime_hour;
                       
                   }
                   for(int i=0;i<yData.length;i++){
                       yData[i]="";
                       }
                   yData[0]="0";
                   yData[1]="25";
                   yData[2]="50";
                   yData[3]="75";
                   yData[4]="100";

                   linetype = 5;//环境曲线类型 1空气温度 2空气湿度 3光照强度4土壤温度5土壤湿度
                   
                   Integer lastPointString = Integer.parseInt(xLineDatatemp[pointCount-1]);//取最大点数的时间，此事件之前的所有点没有值的赋值0
                   xLineData = new String[lastPointString + 1];//所有点的横坐标
                   yLineData = new String[lastPointString + 1];//所有点的纵坐标
                  
                   for(int a = 0; a < lastPointString + 1; a++){
                       xLineData[a]= "" + a ;
                   }
                   
                   for (int j = 0; j < json_arr.length(); j++) {
                       int pstr = Integer.parseInt(xLineDatatemp[j]);
                       yLineData[pstr] = yLineDatatemp[j];
                      
                       }
                   
                   TopStringZ = toStringZ(yLineData);
                   TopStringF = toStringF(yLineData);
                   
                   initChart();
                   }else{
                       xLineData = new String[24];//所有点的横坐标
                       yLineData = new String[24];//所有点的纵坐标
                       for (int i = 0; i < 24; i++) {
                           xLineData[i]="0";
                           yLineData[i]="0";
                       }
                       
                   }
                }
               } catch (JSONException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
               }
//               //环境走势
//               String[] property_vac = new String[] {equipCode1,"07" };
//               soapService.collection4(property_vac);
           }else{
               Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
           }
       }else  if (res.getCode().equals(SOAP_UTILS.METHOD.SCENESLIST)) {//大棚场景列表
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

       }else  if (res.getCode().equals(SOAP_UTILS.METHOD.EQUIPMENTLIST)) {//设备控制
           if (res.getObj() != null) {
               try {

                   JSONObject json_objs = new JSONObject(res.getObj().toString());
                   if (json_objs.get("result").toString().equals("true")) {
                   JSONArray json_arr = json_objs.getJSONArray("data");
                   for (int j = 0; j < json_arr.length(); j++) {
                       JSONObject json_obj =(JSONObject) json_arr.get(j);

                       Equipment equipment = new Equipment();
//                       equipName = json_obj.get("equipName").toString();
                       equipment.setEquipCode(json_obj.getString("equipCode"));
                       equipment.setEquipName(json_obj.getString("equipName"));
                       JSONArray control_array = json_obj.getJSONArray("equipControlList");
                       List<EquipmentChannel> equipmentchannelList  = new ArrayList<EquipmentChannel>();
                       for (int i = 0; i < control_array.length(); i++) {
                           EquipmentChannel equipmentchannel = new EquipmentChannel();
                           JSONObject json_news = (JSONObject) control_array
                                   .get(i);
                           equipmentchannel.setChannelStatus(json_news.getString("channelStatus"));
                           equipmentchannel.setChannelName(json_news.getString("channelName"));
                           equipmentchannel.setChannelType(json_news.getString("channelType"));
                           equipmentchannel.setChannelNo(json_news.getString("channelNo"));
                           
                           equipmentchannelList.add(equipmentchannel);
                       }
                       equipment.set_List(equipmentchannelList);
                       equipmentList.add(equipment);
                       
                   }
                   
//                       equipmentListAdapter.notifyDataSetChanged();
//
//                       setListViewHeightBasedOnChildren(listview_equipmentlist);
//                   }else{
//                       listview_equipmentlist.setVisibility(8);
//                       none_layout.setVisibility(1);
//                   }
                   if(equipmentList.size()!=0){
                       expandableAdapter=new ApplyExtendableListViewAdapter(context,equipmentList);
                       listview_equipmentlist.setAdapter(expandableAdapter); //设置分组的监听
//                       setListViewHeight(expend_list);
//                       listview_equipmentlist.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//                           @Override
//                           public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
////                               Toast.makeText(context, ""+groupPosition, Toast.LENGTH_SHORT).show();
//                               expandableAdapter.notifyDataSetChanged();
////                               setListViewHeight(expend_list);
//                               return false;
//                           }
//                       }); //设置子项布局监听
                       listview_equipmentlist.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                           @Override
                           public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition,   long id) {
//                               Toast.makeText(context,groupPosition+""+childPosition, Toast.LENGTH_SHORT).show();
                               return true;
                           }
                       });
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


           //摄像头列表
            String[] property_va_video = new String[] {pengcode };
            soapService.getCameraList(property_va_video);
       }else  if (res.getCode().equals(SOAP_UTILS.METHOD.FINISHPLANT)) {//结束种植
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

       }else  if (res.getCode().equals(SOAP_UTILS.METHOD.GATEWATLIST)) {//网关列表
           if (res.getObj() != null) {
               try {

                   JSONObject json_objs = new JSONObject(res.getObj().toString());
                   if (json_objs.get("result").toString().equals("true")) {
                       JSONArray json_arr = json_objs.getJSONArray("data");
                       for (int i = 0; i < json_arr.length(); i++) {
                           JSONObject json_obj= (JSONObject) json_arr.get(i);
                           String equipCode = json_obj.getString("equipCode");
                           String equipName = json_obj.getString("equipName");
                           if(i==0){
                               equipCode1 = equipCode;
                               equipName1 = equipName;
                           }else if(i == 1){

                               equipCode2 = equipCode;
                               equipName2 = equipName;
                           }else if(i == 2){

                               equipCode3 = equipCode;
                               equipName3 = equipName;
                           }else if(i == 3){

                               equipCode4 = equipCode;
                               equipName4 = equipName;
                           }else if(i == 4){

                               equipCode5 = equipCode;
                               equipName5 = equipName;
                           }else if(i == 5){

                               equipCode6 = equipCode;
                               equipName6 = equipName;
                           }
                       } 
                       Integer equipcount = json_arr.length();
                       if(equipcount > 0){
                           gateway1_tv.setVisibility(1);
                           gateway1_tv.setBackgroundResource(R.drawable.broder_orange);
                           gateway1_tv.setTextColor(getResources().getColor(R.color.white));
                           gateway1_tv.setText(equipName1);
                           
                           //我的农场传感器数据
                           String[] property_va = new String[] {equipCode1 };
                           soapService.getSensorData(property_va);
                           if(equipcount > 1){

                               gateway2_tv.setVisibility(1);
                               gateway2_tv.setText(equipName2);
                               if(equipcount > 2){

                                   gateway3_tv.setVisibility(1);
                                   gateway3_tv.setText(equipName3);
                                   if(equipcount > 3){

                                       gateway4_tv.setVisibility(1);
                                       gateway4_tv.setText(equipName4);
                                       if(equipcount > 4){

                                           gateway5_tv.setVisibility(1);
                                           gateway5_tv.setText(equipName5);
                                           if(equipcount > 5){

                                               gateway6_tv.setVisibility(1);
                                               gateway6_tv.setText(equipName6);
                                           }else{

                                               gateway6_tv.setVisibility(8);  
                                           }
                                       }else{

                                           gateway5_tv.setVisibility(8);  
                                           gateway6_tv.setVisibility(8); 
                                       }
                                   }else{

                                       gateway4_tv.setVisibility(8);  
                                       gateway5_tv.setVisibility(8);  
                                       gateway6_tv.setVisibility(8); 
                                   }
                               }else{

                                   gateway3_tv.setVisibility(8); 
                                   gateway4_tv.setVisibility(8);  
                                   gateway5_tv.setVisibility(8);  
                                   gateway6_tv.setVisibility(8);  
                               }
                           }else{

                               gateway2_tv.setVisibility(8);  
                               gateway3_tv.setVisibility(8); 
                               gateway4_tv.setVisibility(8);  
                               gateway5_tv.setVisibility(8);  
                               gateway6_tv.setVisibility(8);  
                           }
                       }else{
                           gateway1_tv.setVisibility(8);
                           gateway2_tv.setVisibility(8);  
                           gateway3_tv.setVisibility(8); 
                           gateway4_tv.setVisibility(8);  
                           gateway5_tv.setVisibility(8);  
                           gateway6_tv.setVisibility(8);  
                       }
                       
                 
                      
                   }else{

                       String message = json_objs.get("message").toString();
                       Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                       gateway1_tv.setVisibility(8);
                       gateway2_tv.setVisibility(8);  
                       gateway3_tv.setVisibility(8); 
                       gateway4_tv.setVisibility(8);  
                       gateway5_tv.setVisibility(8);  
                       gateway6_tv.setVisibility(8);  
                   }
               } catch (JSONException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
               }

           }else{
               Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
           }

       }else  if (res.getCode().equals(SOAP_UTILS.METHOD.SENSORDATALIST)) {//我的农场传感器数据
           if (res.getObj() != null) {
               try {
                   progressDialog.dismiss();
                   JSONObject json_objs = new JSONObject(res.getObj().toString());
                   if (json_objs.get("result").toString().equals("true")) {
                           JSONObject json_obj= (JSONObject) json_objs.get("data");
                            air_temperature = json_obj.getString("air_temperature");
                            air_humidity = json_obj.getString("air_humidity");
                            brightness = json_obj.getString("brightness");
                            soil_temperature = json_obj.getString("soil_temperature");
                            soil_humidity = json_obj.getString("soil_humidity");
                            co2 = json_obj.getString("co2");
                            soil_ph = json_obj.getString("soil_ph");
                            
//                            air_temperature_tv.setText(air_temperature + "℃");//空气温度
//                            air_humidity_tv.setText(air_humidity + "%");//空气湿度
//                            light_tv.setText(brightness + " lux");//光照强度
//                            soil_temperature_tv.setText(soil_temperature + "℃");//土壤温度
//                            soil_humidity_tv.setText(soil_humidity + "%");//土壤湿度
//                            co2_tv.setText(co2 + " ppm");//co2浓度
//                            soilph_tv.setText(soil_ph);//PH值
                           //如果无值 数值显示暂无，图标变灰色
                             if(air_temperature.equals("暂无")){//空气温度

                                 air_temperature_tv.setText(air_temperature);  
                                 air_temperature_btn.setBackgroundResource(R.drawable.air_temperature_invalid_icon);
                             }else{

                                 air_temperature_tv.setText(air_temperature + "℃"); 
                                 air_temperature_btn.setBackgroundResource(R.drawable.air_temperature_icon); 
                             }
                             
                             if(air_humidity.equals("暂无")){//空气湿度

                                 air_humidity_tv.setText(air_humidity);  
                                 air_humidity_btn.setBackgroundResource(R.drawable.air_humidity_invalid_icon);
                             }else{

                                 air_humidity_tv.setText(air_humidity + "%"); 
                                 air_humidity_btn.setBackgroundResource(R.drawable.air_humidity_icon); 
                             }
                             
                             if(brightness.equals("暂无")){//光照强度

                                 light_tv.setText(brightness);  
                                 light_btn.setBackgroundResource(R.drawable.light_invalid_icon);
                             }else{

                                 light_tv.setText(brightness + " lux"); 
                                 light_btn.setBackgroundResource(R.drawable.light_icon); 
                             }
                             
                             if(soil_temperature.equals("暂无")){//土壤温度

                                 soil_temperature_tv.setText(soil_temperature);  
                                 soil_temperature_btn.setBackgroundResource(R.drawable.soil_temperature_invalid_icon);
                             }else{

                                 soil_temperature_tv.setText(soil_temperature + "℃"); 
                                 soil_temperature_btn.setBackgroundResource(R.drawable.soil_temperature_icon); 
                             }
                             
                             if(soil_humidity.equals("暂无")){//土壤湿度

                                 soil_humidity_tv.setText(soil_humidity);  
                                 soil_humidity_btn.setBackgroundResource(R.drawable.soil_humidity_invalid_icon);
                             }else{

                                 soil_humidity_tv.setText(soil_humidity + "%"); 
                                 soil_humidity_btn.setBackgroundResource(R.drawable.soil_humidity_icon); 
                             }
                             
                             if(co2.equals("暂无")){//co2

                                 co2_tv.setText(co2);  
                                 co2_btn.setBackgroundResource(R.drawable.co2_invalid_icon);
                             }else{

                                 co2_tv.setText(co2 + " ppm");
                                 co2_btn.setBackgroundResource(R.drawable.co2_icon); 
                             }

                             if(soil_ph.equals("暂无")){//PH值

                                 soilph_tv.setText(soil_ph);  
                                 soilph_btn.setBackgroundResource(R.drawable.soilph_invalid_icon);
                             }else{

                                 soilph_tv.setText(soil_ph);
                                 soilph_btn.setBackgroundResource(R.drawable.soilph_icon); 
                             }
                          
//                            //摄像头列表
//                             String[] property_va_video = new String[] {pengcode };
//                             soapService.getCameraList(property_va_video);
                   }else{

                       String message = json_objs.get("message").toString();
                       Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

//                       //摄像头列表
//                        String[] property_va_video = new String[] {pengcode };
//                        soapService.getCameraList(property_va_video);
                   }
               } catch (JSONException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
               }

//             //种植信息
//             String[] property_vas = new String[] {pengcode };
//             soapService.planting(property_vas);
//               //摄像头列表
//                String[] property_va_video = new String[] {pengcode };
//                soapService.getCameraList(property_va_video);
//             //24小时报警
//             String[] property_va = new String[] {pengcode };
//             soapService.getPengAlarm(property_va);
             //环境走势
             String[] property_vac = new String[] {equipCode1,"03" };
             soapService.collection(property_vac);
           }else{
               Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
           }

       }else  if (res.getCode().equals(SOAP_UTILS.METHOD.CAMERALIST)) {//摄像头列表
           if (res.getObj() != null) {
               try {

                   JSONObject json_objs = new JSONObject(res.getObj().toString());
                   if (json_objs.get("result").toString().equals("true")) {
                       JSONArray json_arr = json_objs.getJSONArray("data");
                       for (int i = 0; i < json_arr.length(); i++) {
                           JSONObject json_obj= (JSONObject) json_arr.get(i);
                           String id = json_obj.getString("id");
                           String status = json_obj.getString("status");
                           String cameraCode = json_obj.getString("cameraName");
                           String cameraType = json_obj.getString("cameraType");
                           String cameraStatus = json_obj.getString("cameraStatus");
                           String m3u8 = json_obj.getString("m3u8");
                           String pengCode = json_obj.getString("pengCode");
                          
                           if(i==0){
                               cameraCode1 = cameraCode;
                               cameraId1 = id;
                               m3u81 = m3u8;
                           }else if(i == 1){

                               cameraCode2 = cameraCode;
                               cameraId2 = id;
                               m3u82 = m3u8;
                           }else if(i == 2){

                               cameraCode3 = cameraCode;
                               cameraId3 = id;
                               m3u83 = m3u8;
                           }else if(i == 3){

                               cameraCode4 = cameraCode;
                               cameraId4 = id;
                               m3u84 = m3u8;
                           }
                       } 
                       Integer equipcount = json_arr.length();
                       if(equipcount > 0){
                           camera1_layout.setVisibility(1);
                           shipin_tv1.setText(cameraCode1);
                           
                           if(equipcount > 1){

                               camera2_layout.setVisibility(1);
                               shipin_tv2.setText(cameraCode2);
                               if(equipcount > 2){

                                   camera3_layout.setVisibility(1);
                                   shipin_tv3.setText(cameraCode3);
                                   if(equipcount > 3){

                                       camera4_layout.setVisibility(1);
                                       shipin_tv4.setText(cameraCode4);
                                    
                                   }else{

                                       camera4_layout.setVisibility(8);  
                                   }
                               }else{

                                   camera3_layout.setVisibility(8); 
                                   camera4_layout.setVisibility(8); 
                               }
                           }else{

                               camera2_layout.setVisibility(8);  
                               camera3_layout.setVisibility(8); 
                               camera4_layout.setVisibility(8);   
                           }
                       }else{
                           camera1_layout.setVisibility(8); 
                           camera2_layout.setVisibility(8);  
                           camera3_layout.setVisibility(8); 
                           camera4_layout.setVisibility(8);   
                       }

//                       //24小时报警
//                       String[] property_va = new String[] {pengcode };
//                       soapService.getPengAlarm(property_va);
                   }else{

                       String message = json_objs.get("message").toString();
//                       Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

                       shipin_tv1.setText("暂无");
                       shipin_tv2.setText("暂无");
                       shipin_tv3.setText("暂无");
                       shipin_tv4.setText("暂无");

//                       //24小时报警
//                       String[] property_va = new String[] {pengcode };
//                       soapService.getPengAlarm(property_va);
                   }
               } catch (JSONException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();

//                   //24小时报警
//                   String[] property_va = new String[] {pengcode };
//                   soapService.getPengAlarm(property_va);
               }

           }else{
               Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
           }


           //24小时报警
           String[] property_va = new String[] {pengcode };
           soapService.getPengAlarm(property_va);
//           //网关列表
//            String[] property_va = new String[] {pengcode };
//            soapService.getGatewayList(property_va);
//
//           //24小时报警
//           String[] property_va = new String[] {pengcode };
//           soapService.getPengAlarm(property_va);
//           //环境走势
//           String[] property_vac = new String[] {equipCode1,"03" };
//           soapService.collection(property_vac);
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
   
   //新曲线图

   // 曲线图

   public void initChart() {
       getAxisXLables();
       getAxisYLables();
       resetViewport();
       setPointsValues(); // 设置每条线的节点值
       setLinesDatas(); // 设置每条线的一些属性
       resetViewport(); // 计算并绘图
//       initEvent();//点击坐标点提示坐标值
   }

   /**
    * 设置X 轴的显示
    */
   private void getAxisXLables() {
       mAxisXValues.clear();
       for (int i = 0; i < xData.length; i++) {
           mAxisXValues.add(new AxisValue(i).setLabel(xData[i]));
       }
   }

   /**
    * 设置y 轴的显示
    */
   private void getAxisYLables() {
       mAxisYValues.clear();
       for (int i = 0; i < yData.length; i++) {
           mAxisYValues.add(new AxisValue(i).setLabel(yData[i]));
       }
   }

   private void resetViewport() {
       mLineChartView.setViewportCalculationEnabled(false);
     //创建一个图标视图 大小为控件的最大大小
       Viewport v = new Viewport(mLineChartView.getMaximumViewport());
       v.top = yData.length;//最高点为5
       v.bottom = 0;
       v.left = 0;//坐标原点在左下
       v.right = 25;//右边为点 坐标从0开始
//       v.right = pointCount;
       // mLineChartView.setZoomLevel(x, y, zoomLevel);
       mLineChartView.setZoomEnabled(false);
       mLineChartView.setMaximumViewport(v);
       mLineChartView.setCurrentViewport(v);
   }

   /**
    * 利用随机数设置每条线对应节点的值
    */
   private void setPointsValues() {

//       String[] myLines = myLine.split(",");
       numberOfMyPoints = yLineData.length;

       myPoints = new float[yLineData.length];

       for (int j = 0; j < numberOfMyPoints; ++j) {
           if (yLineData[j] == null || yLineData[j].equals("")) {
               continue;
           }
           Float temp;
           if (Float.valueOf(yLineData[j]) > 0) {
//               temp = (float) (Float.valueOf(yLineData[j])  * 3 / TopStringZ + 3);
               if(linetype==1){//linetype = 1;//环境曲线类型 1空气温度 2空气湿度 3光照强度4土壤温度5土壤湿度

                   temp = (float) (Float.valueOf(yLineData[j]) * 2 / 50 +2);
                   myPoints[j] = temp;
               }else if(linetype==2){

                   temp = (float) (Float.valueOf(yLineData[j]) * 4 / 100);
                   myPoints[j] = temp;
               }else if(linetype==3){

                   temp = (float) (Float.valueOf(yLineData[j]) * 4 / 100000);
                   myPoints[j] = temp;
               }else if(linetype==4){

                   temp = (float) (Float.valueOf(yLineData[j]) * 2 / 50 +2);
                   myPoints[j] = temp;
               }else if(linetype==5){

                   temp = (float) (Float.valueOf(yLineData[j]) * 4 / 100);
                   myPoints[j] = temp;
               }



           } else {
               temp = (float) (Float.valueOf(yLineData[j]) * 2 / 50);
//               temp = (float) (Float.valueOf(yLineData[j]) * 3 / TopStringF + 3);
               myPoints[j] = temp;
           }
//            temp = (float) (Float.valueOf(myLines[j]) );
     
//           myPoints[j] = temp;
       }

   }

   /**
    * 设置线的相关数据
    */
   private void setLinesDatas() {
       List<Line> lines = new ArrayList<Line>();
       // 循环将每条线都设置成对应的属性
//       for (int i = 0; i < numberOfLines; ++i) {
           // 节点的值
//       String[] myLines = {"1", "2", "0", "4.33"};
//       String[] myLines = myLine.split(",");
           List<PointValue> values = new ArrayList<PointValue>();

               for (int j = 0; j < yLineData.length; ++j) {
                   values.add(new PointValue(Integer.parseInt(xLineData[j]),myPoints[j]));//yLineData
//                   values.add(new PointValue(j, Float.parseFloat(yLineData[j])));//Integer.parseInt(xData[j])
               }
         

           /* ========== 设置线的一些属性 ========== */
           Line line = new Line(values); // 根据值来创建一条线
//           if (i == 0) {
               line.setColor(Color.parseColor("#62a8e6")); // 设置线的颜色
//           } else if (i == 1) {
//               line.setColor(this.getResources().getColor(R.color.blue_bgsmall)); // 设置线的颜色
//           }
           line.setShape(pointsShape); // 设置点的形状
           line.setHasLines(isHasLines); // 设置是否显示线
           line.setStrokeWidth(2);// 线的粗细 默认是3
           line.setHasPoints(isHasPoints); // 设置是否显示节点
           line.setCubic(isCubic); // 设置线是否立体或其他效果
           line.setFilled(isFilled); // 设置是否填充线下方区域
           line.setHasLabels(isHasPointsLabels); // 设置是否显示节点标签  
           line.setPointRadius(3);//坐标空心
           line.setFormatter(new SimpleLineChartValueFormatter(2));//解决坐标值是浮点数，曲线上显示为整数值问题。设置两位小数
           // 设置节点点击的效果
           line.setHasLabelsOnlyForSelected(isPointsHasSelected);
           // 如果节点与线有不同颜色 则设置不同颜色
//           if (isPointsHaveDifferentColor) {
//               line.setPointColor(ChartUtils.COLORS[(1) % ChartUtils.COLORS.length]);
//           }
           lines.add(line);
//       }

       mLineData = new LineChartData(lines); // 将所有的线加入线数据类中
       mLineData.setBaseValue(Float.NEGATIVE_INFINITY); // 设置基准数(大概是数据范围)
       /*
        * 其他的一些属性方法 可自行查看效果 mLineData.setValueLabelBackgroundAuto(true);
        * //设置数据背景是否跟随节点颜色 mLineData.setValueLabelBackgroundColor(Color.BLUE);
        * //设置数据背景颜色 mLineData.setValueLabelBackgroundEnabled(true);
        * //设置是否有数据背景 mLineData.setValueLabelsTextColor(Color.RED); //设置数据文字颜色
        * mLineData.setValueLabelTextSize(15); //设置数据文字大小
        * mLineData.setValueLabelTypeface(Typeface.MONOSPACE); //设置数据文字样式
        */

       // 如果显示坐标轴
       if (isHasAxes) {
           Axis axisX = new Axis().setHasLines(true); // X轴
           Axis axisY = new Axis().setHasLines(true); // Y轴
           axisX.setTextColor(getResources().getColor(R.color.item_graytext3)); // X轴灰色
           axisY.setTextColor(getResources().getColor(R.color.item_graytext3)); // Y轴灰色
           axisX.setLineColor(getResources().getColor(R.color.item_graytext3));
           axisY.setLineColor(getResources().getColor(R.color.item_graytext3));

           axisX.setValues(mAxisXValues); // 填充X轴的坐标名称
           axisX.setMaxLabelChars(0); //

           // 最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
           // axisX.setTextSize(5);
           //
           axisY.setHasSeparationLine(false);

           axisY.setMaxLabelChars(7);// y轴 显示字符长度
           axisY.setValues(mAxisYValues);
           // 如果显示名称
           if (isHasAxesNames) {
               axisX.setName("Axis X"); // 设置名称
               axisY.setName("Axis Y");
           }
           mLineData.setAxisXBottom(axisX); // 设置X轴位置 下方
           mLineData.setAxisYLeft(axisY); // 设置Y轴位置 左边
       } else {
           mLineData.setAxisXBottom(null);
           mLineData.setAxisYLeft(null);
       }

       mLineChartView.setLineChartData(mLineData); // 设置图表控件
   }

   public Double toStringZ(String[] sl) {// 正直
//       String[] sl = sh.split(",");
       Double topString = 0.00;
       if (sl[0].length() > 1) {
           if (sl[0].substring(0, 1).equals("-")) {
           } else {
               topString = Double.valueOf(sl[0]);
           }
       }

       for (int i = 1; i < sl.length; i++) {
           if (sl[i].length() > 1) {
               if (sl[i].substring(0, 1).equals("-")) {
               } else {
                   if (Double.valueOf(sl[i]) > topString) {
                       topString = Double.valueOf(sl[i]);
                   }
               }
           }
       }
       if (topString == 0) {
           topString = 1.00;
       }
       return topString;
   }
   public Double toStringF(String[] sl) {// 负值
//       String[] sl = sh.split(",");
       Double topString = 0.00;
       if (sl[0].length() > 1) {
           if (sl[0].substring(0, 1).equals("-")) {
               sl[0] = sl[0].substring(1);
               topString = Double.valueOf(sl[0]);
           }
       }
       for (int i = 1; i < sl.length; i++) {
           if (sl[i].length() > 1) {
               if (sl[i].substring(0, 1).equals("-")) {
                   sl[i] = sl[i].substring(1);
                   if (Double.valueOf(sl[i]) > topString) {
                       topString = Double.valueOf(sl[i]);
                   }
               }
           }
       }
       if (topString == 0) {
           topString = 1.00;
       }
       return topString;
   }

   // 报警次数曲线图

   public void initChartAlarm() {
       getAxisXLables_alarm();
       getAxisYLables_alarm();
       resetViewport_alarm();
       setPointsValues_alarm(); // 设置每条线的节点值
       setLinesDatas_alarm(); // 设置每条线的一些属性
       resetViewport_alarm(); // 计算并绘图
   }

   /**
    * 设置X 轴的显示
    */
   private void getAxisXLables_alarm() {
       for (int i = 0; i < xData_alarm.length; i++) {
           mAxisXValues_alarm.add(new AxisValue(i).setLabel(xData_alarm[i]));
       }
   }

   /**
    * 设置y 轴的显示
    */
   private void getAxisYLables_alarm() {
       for (int i = 0; i < yData_alarm.length; i++) {
           mAxisYValues_alarm.add(new AxisValue(i).setLabel(yData_alarm[i]));
       }
   }

   private void resetViewport_alarm() {
       mLineChartView_alarm.setViewportCalculationEnabled(false);
     //创建一个图标视图 大小为控件的最大大小
       Viewport v = new Viewport(mLineChartView_alarm.getMaximumViewport());
       v.top = yData_alarm.length;//最高点为5
       v.bottom = 0;
       v.left = 0;//坐标原点在左下
       v.right = 25;//右边为点 坐标从0开始
//       v.right = pointCount;
       // mLineChartView.setZoomLevel(x, y, zoomLevel);
       mLineChartView.setZoomEnabled(false);
       mLineChartView.setMaximumViewport(v);
       mLineChartView.setCurrentViewport(v);
   }

   /**
    * 利用随机数设置每条线对应节点的值
    */
   private void setPointsValues_alarm() {

//       String[] myLines = myLine.split(",");
       numberOfMyPoints_alarm = yLineData_alarm.length;

       myPoints_alarm = new float[yLineData_alarm.length];

       for (int j = 0; j < numberOfMyPoints_alarm; ++j) {
           if (yLineData_alarm[j] == null || yLineData_alarm[j].equals("")) {
               continue;
           }
           Float temp;

                   temp = (float) (Float.valueOf(yLineData_alarm[j]));
//                   temp = (float) (Float.valueOf(yLineData_alarm[j]) * 4 / 40);
                   myPoints_alarm[j] = temp;

       }

   }

   /**
    * 设置线的相关数据
    */
   private void setLinesDatas_alarm() {
       List<Line> lines = new ArrayList<Line>();
       // 循环将每条线都设置成对应的属性
           List<PointValue> values = new ArrayList<PointValue>();

               for (int j = 0; j < yLineData_alarm.length; ++j) {
                   values.add(new PointValue(Integer.parseInt(xLineData_alarm[j]),myPoints_alarm[j]));//yLineData
//                   values.add(new PointValue(j, Float.parseFloat(yLineData[j])));//Integer.parseInt(xData[j])
               }
         

           /* ========== 设置线的一些属性 ========== */
           Line line = new Line(values); // 根据值来创建一条线
           line.setColor(Color.parseColor("#62a8e6")); // 设置线的颜色
           line.setShape(pointsShape); // 设置点的形状
           line.setHasLines(isHasLines); // 设置是否显示线
           line.setStrokeWidth(2);// 线的粗细 默认是3
           line.setHasPoints(isHasPoints); // 设置是否显示节点
           line.setCubic(isCubic); // 设置线是否立体或其他效果
           line.setFilled(isFilled); // 设置是否填充线下方区域
           line.setHasLabels(isHasPointsLabels); // 设置是否显示节点标签  
           line.setPointRadius(3);//坐标空心
           
           // 设置节点点击的效果
           line.setHasLabelsOnlyForSelected(isPointsHasSelected);
           // 如果节点与线有不同颜色 则设置不同颜色
           lines.add(line);
//       }

       mLineData_alarm = new LineChartData(lines); // 将所有的线加入线数据类中
       mLineData_alarm.setBaseValue(Float.NEGATIVE_INFINITY); // 设置基准数(大概是数据范围)
       /*
        * 其他的一些属性方法 可自行查看效果 mLineData.setValueLabelBackgroundAuto(true);
        * //设置数据背景是否跟随节点颜色 mLineData.setValueLabelBackgroundColor(Color.BLUE);
        * //设置数据背景颜色 mLineData.setValueLabelBackgroundEnabled(true);
        * //设置是否有数据背景 mLineData.setValueLabelsTextColor(Color.RED); //设置数据文字颜色
        * mLineData.setValueLabelTextSize(15); //设置数据文字大小
        * mLineData.setValueLabelTypeface(Typeface.MONOSPACE); //设置数据文字样式
        */

       // 如果显示坐标轴
       if (isHasAxes) {
           Axis axisX = new Axis().setHasLines(true); // X轴
           Axis axisY = new Axis().setHasLines(true); // Y轴
           axisX.setTextColor(getResources().getColor(R.color.item_graytext3)); // X轴灰色
           axisY.setTextColor(getResources().getColor(R.color.item_graytext3)); // Y轴灰色
           axisX.setLineColor(getResources().getColor(R.color.item_graytext3));
           axisY.setLineColor(getResources().getColor(R.color.item_graytext3));

           axisX.setValues(mAxisXValues_alarm); // 填充X轴的坐标名称
           axisX.setMaxLabelChars(0); //

           // 最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
           // axisX.setTextSize(5);
           //
           axisY.setHasSeparationLine(false);

           axisY.setMaxLabelChars(7);// y轴 显示字符长度
           axisY.setValues(mAxisYValues_alarm);
           // 如果显示名称
           if (isHasAxesNames) {
               axisX.setName("Axis X"); // 设置名称
               axisY.setName("Axis Y");
           }
           mLineData_alarm.setAxisXBottom(axisX); // 设置X轴位置 下方
           mLineData_alarm.setAxisYLeft(axisY); // 设置Y轴位置 左边
       } else {
           mLineData_alarm.setAxisXBottom(null);
           mLineData_alarm.setAxisYLeft(null);
       }

       mLineChartView_alarm.setLineChartData(mLineData_alarm); // 设置图表控件
   }

   
//   private void initEvent() {
//       mLineChartView.setOnValueTouchListener(new ValueTouchListener());
//
//   }
//   /**
//    * 触摸监听类
//    */
//   private class ValueTouchListener implements LineChartOnValueSelectListener {
//
//       @Override
//       public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
//           Integer xvalue = (int)value.getX();
//           Toast.makeText(GreenHouseInfoActivity.this, xvalue + ":00 " + "空气温度: " + yLineData[xvalue] + "℃", Toast.LENGTH_SHORT).show();
//       }
//
//       @Override
//       public void onValueDeselected() {
//
//
//       }
//
//   }


   //还原报警折线图
      
      /**
       * 初始化LineChart的一些设置
       */
      private void initLineChart_restore(){
          Line line = new Line(mPointValues_restore).setColor(Color.parseColor("#db0c0c"));  //折线的颜色红色
          List<Line> lines = new ArrayList<Line>();    
          line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.SQUARE）
          
          line.setCubic(false);//曲线是否平滑
          line.setStrokeWidth(2);//线条的粗细，默认是3
          line.setFilled(false);//是否填充曲线的面积
//          line.setHasLabels(false);//曲线的数据坐标是否加上备注
        line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
          line.setHasLines(true);//是否用直线显示。如果为false 则没有曲线只有点显示    
          line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示    
          line.setPointRadius(3);//坐标空心
          lines.add(line);  
          LineChartData data = new LineChartData(); 
          
          data.setLines(lines);  

//          lineChart.destroyDrawingCache();//销毁缓存
          //坐标轴  
          Axis axisX = new Axis(); //X轴  
          axisX.setHasTiltedLabels(false);  //X轴下面坐标轴字体是斜的显示还是直的，true是斜的显示 
//        axisX.setTextColor(Color.WHITE);  //设置字体颜色
//          axisX.setTextColor(Color.parseColor("#abb3b9"));//灰色
          axisX.setTextColor(getResources().getColor(R.color.item_graytext3)); // Y轴灰色
          axisX.setLineColor(getResources().getColor(R.color.item_graytext3));
          
//        axisX.setName("未来几天的天气");  //表格名称
          axisX.setTextSize(11);//设置字体大小
          axisX.setMaxLabelChars(7); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisValues.length
          axisX.setValues(mAxisXValues_restore);  //填充X轴的坐标名称
          data.setAxisXBottom(axisX); //x 轴在底部     
//        data.setAxisXTop(axisX);  //x 轴在顶部
          axisX.setHasLines(true); //x 轴分割线
          
          
          Axis axisY = new Axis();  //Y轴  
          axisX.setName("");//y轴标注
//          axisY.setValues()
//          axisY.set
          axisY.setTextSize(12);//设置字体大小
          axisY.setHasLines(true); //y 轴分割线
          data.setAxisYLeft(axisY);  //Y轴设置在左边
          axisY.setTextColor(getResources().getColor(R.color.item_graytext3)); // Y轴灰色
          axisY.setLineColor(getResources().getColor(R.color.item_graytext3));
        //data.setAxisYRight(axisY);  //y轴设置在右边
          
        //设置行为属性，支持缩放、滑动以及平移  
          lineChart.setInteractive(true); 
          lineChart.setZoomType(ZoomType.HORIZONTAL);  //缩放类型，水平
//          lineChart.setMaxZoom((float) 3);//缩放比例
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
      private void getAxisXLables_restore(){
          for (int i = 0; i < date.length; i++) {    
              mAxisXValues_restore.add(new AxisValue(i).setLabel(date[i]));    
          }       
      }
      /**
       * 图表的每个点的显示
       */
      private void getAxisPoints_restore(){
          for (int i = 0; i < score.length; i++) {    
              mPointValues_restore.add(new PointValue(i, score[i]));      
          }       
      }
}