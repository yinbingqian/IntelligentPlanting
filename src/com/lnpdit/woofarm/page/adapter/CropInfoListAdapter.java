package com.lnpdit.woofarm.page.adapter;

import java.util.List;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.entity.CropInfoList;
import com.lnpdit.woofarm.instance.Instance;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CropInfoListAdapter extends BaseAdapter {
    private class buttonViewHolder {
        private TextView crop_name;
        private TextView details_tv;
        private TextView plantdays_tv;
    }

    private CropInfoList appInfo;
    private List<CropInfoList> mAppList;
    private LayoutInflater mInflater;
    private Context mContext;
    private buttonViewHolder holder;

    public CropInfoListAdapter(Context context, List<CropInfoList> appList) {
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

            convertView = mInflater.inflate(R.layout.item_cropinfo, parent,
                    false);
            holder = new buttonViewHolder();
            holder.crop_name = (TextView) convertView
                    .findViewById(R.id.crop_name);
            holder.details_tv = (TextView) convertView
                    .findViewById(R.id.details_tv);
            holder.plantdays_tv = (TextView) convertView
                    .findViewById(R.id.plantdays_tv);
            convertView.setTag(holder);
            
        } else {

            holder = (buttonViewHolder) convertView.getTag();

        }

        appInfo = mAppList.get(position);

        holder.crop_name.setText(appInfo.getCropname());
        holder.details_tv.setText(appInfo.getCropdetails());
        holder.plantdays_tv.setText(appInfo.getPlantdays() + "月");
        

        return convertView;
    }

}