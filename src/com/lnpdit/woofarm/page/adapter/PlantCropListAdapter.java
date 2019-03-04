package com.lnpdit.woofarm.page.adapter;

import java.util.List;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.entity.PlantingLists;
import com.lnpdit.woofarm.instance.Instance;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PlantCropListAdapter extends BaseAdapter {
    private class buttonViewHolder {
        ImageView plant_img;
        TextView currentstage_tv;
        TextView peng_name;
        TextView plantdays_tv;
        TextView timetomarket_tv;
        TextView yield_tv;
    }

    private PlantingLists appInfo;
    private List<PlantingLists> mAppList;
    private LayoutInflater mInflater;
    private Context mContext;
    private buttonViewHolder holder;

    public PlantCropListAdapter(Context context, List<PlantingLists> appList) {
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

            convertView = mInflater.inflate(R.layout.item_plantcrop, parent,
                    false);
            holder = new buttonViewHolder();
            holder.plant_img =(ImageView) convertView
                    .findViewById(R.id.plant_img);
            holder.peng_name = (TextView) convertView
                    .findViewById(R.id.peng_name);
            holder.plantdays_tv = (TextView) convertView
                    .findViewById(R.id.plantdays_tv);
            holder.currentstage_tv = (TextView) convertView
                    .findViewById(R.id.currentstage_tv);
            holder.timetomarket_tv = (TextView) convertView
                    .findViewById(R.id.timetomarket_tv);
            holder.yield_tv = (TextView) convertView
                    .findViewById(R.id.yield_tv);
            convertView.setTag(holder);
            
        } else {

            holder = (buttonViewHolder) convertView.getTag();

        }

        appInfo = mAppList.get(position);
      
        holder.peng_name.setText(appInfo.getVarietyName());
//        holder.currentstage_tv.setText(appInfo.getCurrentStage());
//        if(appInfo.getCurrentStage().equals("幼苗期")){
//            holder.currentstage_tv.setTextColor(mContext.getResources().getColor(R.color.item_pinktext));
//        }else if(appInfo.getCurrentStage().equals("成熟期")){
//            
//            holder.currentstage_tv.setTextColor(mContext.getResources().getColor(R.color.item_greentext2));
//            
//        }else if(appInfo.getCurrentStage().equals("传粉期")){
//
//            holder.currentstage_tv.setTextColor(mContext.getResources().getColor(R.color.item_orangetext2));
//        }else{
//
//            holder.currentstage_tv.setTextColor(mContext.getResources().getColor(R.color.item_orangetext3));
//        }
        holder.plantdays_tv.setText(appInfo.getPlantDays() +"天");
//        holder.timetomarket_tv.setText( appInfo.getTimeToMarket());
//        if(appInfo.getTimeToMarket().equals("上市中")){
//            holder.timetomarket_tv.setTextColor(mContext.getResources().getColor(R.color.item_greentext2));
//        }else if(appInfo.getTimeToMarket().equals("已下架")){
//            
//            holder.timetomarket_tv.setTextColor(mContext.getResources().getColor(R.color.item_redtext));
//            
//        }
        holder.yield_tv.setText(appInfo.getYield() + appInfo.getUnit());
        Instance.imageLoader.displayImage(SOAP_UTILS.PIC_PATH + appInfo.getPlantImg(), holder.plant_img, Instance.new_s_options);
        return convertView;
    }

}