package com.lnpdit.woofarm.page.adapter;

import java.util.List;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.entity.PlantingList;
import com.lnpdit.woofarm.http.ISoapService;
import com.lnpdit.woofarm.http.SoapService;
import com.lnpdit.woofarm.instance.Instance;
import com.lnpdit.woofarm.utils.SOAP_UTILS;
import com.lnpdit.woofarm.widget.CustomDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PlantingManagementListAdapter extends BaseAdapter {
    private class buttonViewHolder {
        TextView pengname_tv;
        TextView endplant_tv;
        TextView plant_tv;
        TextView datetime_tv;
        ImageView photo_img;
        TextView grey_line_view;
        TextView orange_line_view;
        TextView date_tv;
        TextView uncrop_tv;
        TextView enddatetime_tv;
        
        LinearLayout crop_layout;
    }

    public ISoapService soapService = new SoapService();
    private PlantingList appInfo;
    private List<PlantingList> mAppList;
    private LayoutInflater mInflater;
    private Context mContext;
    private buttonViewHolder holder;
    private String plantId = "";

    public PlantingManagementListAdapter(Context context, List<PlantingList> appList) {
        mAppList = appList;
        mContext = context;
        mInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mAppList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAppList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void removeItem(int positon) {
        mAppList.remove(positon);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // buttonViewHolder holder = null;

        // 无convertView，需要new出各个控件
        if (convertView == null) {
            // 按当前所需的样式，确定new的布局

            convertView = mInflater.inflate(R.layout.item_planting, parent,
                    false);
            holder = new buttonViewHolder();
            holder.pengname_tv = (TextView) convertView
                    .findViewById(R.id.pengname_tv);
            holder.endplant_tv = (TextView) convertView
                    .findViewById(R.id.endplant_tv);
            holder.plant_tv = (TextView) convertView
                    .findViewById(R.id.plant_tv);
            holder.datetime_tv = (TextView) convertView
                    .findViewById(R.id.datetime_tv);
            holder.enddatetime_tv = (TextView) convertView
                    .findViewById(R.id.enddatetime_tv);
            holder.photo_img = (ImageView) convertView
                    .findViewById(R.id.photo_img);
            holder.grey_line_view = (TextView) convertView
                    .findViewById(R.id.grey_line_view);
            holder.orange_line_view = (TextView) convertView
                    .findViewById(R.id.orange_line_view);
            holder.date_tv = (TextView) convertView
                    .findViewById(R.id.date_tv);

            holder.crop_layout = (LinearLayout) convertView
                    .findViewById(R.id.crop_layout);
            holder.uncrop_tv = (TextView) convertView
                    .findViewById(R.id.uncrop_tv);
            
            convertView.setTag(holder);
            
        } else {

            holder = (buttonViewHolder) convertView.getTag();

        }

        appInfo = mAppList.get(position);
        plantId = appInfo.getPlantId();
        String plantingstate = appInfo.getPlantState().toString();
        if(plantingstate.equals("0")){

            holder.crop_layout.setVisibility(1);
            holder.uncrop_tv.setVisibility(8);
            
            holder.pengname_tv.setText(appInfo.getPlotName());
            holder.endplant_tv.setVisibility(1);
            holder.endplant_tv.setText("结束种植"); 
            holder.plant_tv.setText(appInfo.getVarietyName());
            holder.datetime_tv.setText(appInfo.getBeginTime());
            holder.enddatetime_tv.setText(appInfo.getEndTime());
            holder.date_tv.setText("已定值" +appInfo.getPlantDays() + "天");

            String headPath = SOAP_UTILS.URL + appInfo.getPlantImg();
            Instance.imageLoader.displayImage(headPath, holder.photo_img, Instance.zuowu_s_options);

              String plantschedule =appInfo.getPlantSchedule();
            if(!plantschedule.equals("")&&!plantschedule.equals("null")){
                
                Float status_f = Float.parseFloat(plantschedule);
                Float width_f = holder.grey_line_view.getMeasuredWidth()*status_f;
                int width_int = floatToInt(width_f);
                if(width_int!=0){

                    holder.orange_line_view.setVisibility(1);
                    holder.orange_line_view.getLayoutParams().width = width_int;
                }else{

                    holder.orange_line_view.setVisibility(8);
                }

                }
            
        }else{
            holder.endplant_tv.setText("开始种植"); 
            holder.endplant_tv.setVisibility(8);
            holder.crop_layout.setVisibility(8);
            holder.uncrop_tv.setVisibility(1);
        }
       
        holder.endplant_tv.setClickable(true);
        holder.endplant_tv.setOnClickListener(new ButtonListener(position, holder.endplant_tv));
        
        return convertView;
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

    class ButtonListener implements OnClickListener {
        private int position;
        private TextView tv;

        public ButtonListener(int pos,TextView _tv) {
            // TODO Auto-generated constructor stub
            position = pos;
            tv = _tv;
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            
            showDialog();

    }
            
    }


    public void showDialog() {
        CustomDialog.Builder builder = new CustomDialog.Builder(mContext);
        builder.setMessage("");
        builder.setTitle("是否确定结束种植");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                // 设置你的操作事项

              String[] property_vas = new String[] {plantId };
              soapService.finishplant(property_vas);
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
