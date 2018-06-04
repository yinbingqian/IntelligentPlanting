package com.lnpdit.woofarm.page.adapter;

import java.util.List;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.entity.FarmNewsList;
import com.lnpdit.woofarm.instance.Instance;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FarmGrainListAdapter extends BaseAdapter {
    private class buttonViewHolder {
        TextView news_tv;
        TextView count_tv;
        TextView time_tv;
        ImageView photo_img;
    }

    private FarmNewsList appInfo;
    private List<FarmNewsList> mAppList;
    private LayoutInflater mInflater;
    private Context mContext;
    private buttonViewHolder holder;

    private FarmNewsList farmnews;

    public FarmGrainListAdapter(Context context, List<FarmNewsList> appList) {
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

            convertView = mInflater.inflate(R.layout.item_farmnews, parent,
                    false);
            holder = new buttonViewHolder();
            holder.news_tv = (TextView) convertView
                    .findViewById(R.id.news_tv);
            holder.count_tv = (TextView) convertView
                    .findViewById(R.id.count_tv);
            holder.time_tv = (TextView) convertView
                    .findViewById(R.id.time_tv);
            holder.photo_img = (ImageView) convertView
                    .findViewById(R.id.photo_img);
            
            convertView.setTag(holder);
            
        } else {

            holder = (buttonViewHolder) convertView.getTag();

        }

        appInfo = mAppList.get(position); 

        holder.news_tv.setText(appInfo.getNewsName());
        holder.time_tv.setText(appInfo.getHoursAgo());
        holder.count_tv.setText(appInfo.getViewCount());

        String headPath = appInfo.getImgUrl();
        Instance.imageLoader.displayImage(headPath, holder.photo_img, Instance.news_s_options);
       
        return convertView;
    }

}