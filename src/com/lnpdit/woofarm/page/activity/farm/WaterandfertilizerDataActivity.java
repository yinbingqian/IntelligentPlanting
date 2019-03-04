package com.lnpdit.woofarm.page.activity.farm;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.db.DBHelper;
import com.lnpdit.woofarm.entity.FarmList;
import com.lnpdit.woofarm.entity.PengAlarm;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.page.adapter.FarmListAdapter;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemClickListener;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class WaterandfertilizerDataActivity extends BaseActivity implements OnClickListener {
    /** Called when the activity is first created. */

    private DBHelper dbh;
    private FarmList farm;
    private List<FarmList> farmList = new ArrayList<FarmList>();
    private ListView listview_farmlist;
    private FarmListAdapter farmListAdapter;
    private TextView tv_back;
    Context context;
    private String farmname = "";
    private String farmcode = "";

    private ImageView ec_btn,air_humidity_btn,wateryield_btn,watergage_btn,proportion_btn;//传感器图标
    private TextView ec_tv,air_humidity_tv,wateryield_tv,watergage_tv,proportion_tv;//传感器数据
    
    private ProgressDialog progressDialog;
    String stri = "";
    //设备传感器数据
    String ec ="";
    String air_humidity ="";
    String brightness ="";
    String watergage ="";
    String proportion ="";
    String co2 ="";
    String soil_ph ="";


  //还原报警曲线
      private LineChartView lineChart;
      String[] date = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};//X轴的标注
      int[] score= {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};//图表的数据

      private List<PointValue> mPointValues_restore = new ArrayList<PointValue>();
      private List<AxisValue> mAxisXValues_restore = new ArrayList<AxisValue>();

      private List<PengAlarm> pengAlarmList  = new ArrayList<PengAlarm>();
      private LinearLayout linechart_layout;

      private String todayCount = "";
      private String changeRate = "";
      
      private TextView alarmcount_tv;//报警次数
      private TextView alarminfoset_tv;//报警详情
      private TextView alarmpercent_tv;
      private ImageView alarmupdown_img;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_waterandfertilizer);

        initView();
        initData();
        setListeners();
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setClickable(true);
        tv_back.setOnClickListener(this);


        //传感器数据
        ec_tv = (TextView) findViewById(R.id.ec_tv);
        air_humidity_tv = (TextView) findViewById(R.id.air_humidity_tv);
        wateryield_tv = (TextView) findViewById(R.id.wateryield_tv);
        watergage_tv = (TextView) findViewById(R.id.watergage_tv);
        proportion_tv = (TextView) findViewById(R.id.proportion_tv);
        

        ec_btn = (ImageView) findViewById(R.id.ec_btn);
        air_humidity_btn = (ImageView) findViewById(R.id.air_humidity_btn);
        wateryield_btn = (ImageView) findViewById(R.id.wateryield_btn);
        watergage_btn = (ImageView) findViewById(R.id.watergage_btn);
        proportion_btn = (ImageView) findViewById(R.id.proportion_btn);

        progressDialog = new ProgressDialog(this);
        stri = getResources().getString(R.string.Is_sending_a_request);
        progressDialog.setMessage(stri);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        //折线图
         lineChart = (LineChartView)findViewById(R.id.line_chart);//还原报警曲线
         linechart_layout = (LinearLayout)findViewById(R.id.linechart_layout);

         alarmcount_tv = (TextView) findViewById(R.id.alarmcount_tv);
         alarmpercent_tv = (TextView) findViewById(R.id.alarmpercent_tv);
         alarmupdown_img = (ImageView) findViewById(R.id.alarmupdown_img);
         alarminfoset_tv = (TextView) findViewById(R.id.alarminfoset_tv);//报警详情
         alarminfoset_tv.setClickable(true);
         alarminfoset_tv.setOnClickListener(this);
    }

    private void initData() {

        dbh = new DBHelper(this);
        
        SharedPreferences sharedPreferences = getSharedPreferences("userinfo",Activity.MODE_PRIVATE);  
        String userCode = sharedPreferences.getString("userCode","");  
        String[] property_va = new String[] {userCode };
        soapService.farmListData(property_va);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.tv_back:
            finish();
            break;
        case R.id.alarminfoset_tv:
            
            Intent in_alarminfo = new Intent();
//            in_alarminfo.putExtra("pengCode", pengcode);
            in_alarminfo.setClass(WaterandfertilizerDataActivity.this, FarmAlarmInfoListActivity.class);
            startActivity(in_alarminfo);
            break;
        default:
            break;
        }
    }
    
    private void setListeners() {
        listview_farmlist.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Click Towns");

            }
        });
    }
   @Override
   public void onEvent(Object obj) {
       // TODO Auto-generated method stub
       super.onEvent(obj);
       SoapRes res = (SoapRes) obj;
       if (res.getCode().equals(SOAP_UTILS.METHOD.FARMLISTDATA)) {
           if (res.getObj() != null) {
               try {
               JSONObject json_objs = new JSONObject(res.getObj().toString());
      
               if (json_objs.get("result").toString().equals("true")) {
                   farmList.clear();
                   JSONArray json_arr = json_objs.getJSONArray("data");
                   for (int i = 0; i < json_arr.length(); i++) {
                       JSONObject json_obj= (JSONObject) json_arr.get(i);
                       FarmList farm = new FarmList();
                       farm.setId(json_obj.getString("id"));
                       farm.setFarmArea(json_obj.getString("farmArea"));
                       farm.setUserCode(json_obj.getString("userCode"));
                       farm.setFarmName(json_obj.getString("farmName"));
                       farm.setFarmCode(json_obj.getString("farmCode"));
                       farmList.add(farm);
                   }

                   if(farmList.size()!=0){

                       farmListAdapter = new FarmListAdapter(context, farmList);
                       listview_farmlist.setAdapter(farmListAdapter);
                   }else{
                       listview_farmlist.setAdapter(null); 
                   }
                }
               } catch (JSONException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
               }

           }else{
               Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
           }
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

//                           red_line.setVisibility(1);
                           for (int i = 0; i < 24; i++) {
                               int occtime_int = i;
                               String alarmcount = "0";
                               
                              score[occtime_int] = Integer.parseInt(alarmcount);
                               
                           }
                           
                               getAxisXLables_restore();//获取x轴的标注
                               getAxisPoints_restore();//获取坐标点
                               initLineChart_restore();//初始化
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
//           //网关列表
//           String[] property_va = new String[] {pengcode };
//           soapService.getGatewayList(property_va);

       }
   }



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