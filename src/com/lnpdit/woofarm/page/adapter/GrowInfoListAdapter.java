package com.lnpdit.woofarm.page.adapter;

import java.util.List;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.entity.GrowInfoList;
import com.lnpdit.woofarm.instance.Instance;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GrowInfoListAdapter extends BaseAdapter {
    private class buttonViewHolder {
        TextView info_tv;
        TextView name_tv;
        ImageView pic_img;
    }

    private GrowInfoList appInfo;
    private List<GrowInfoList> mAppList;
    private LayoutInflater mInflater;
    private Context mContext;
    private buttonViewHolder holder;

    public GrowInfoListAdapter(Context context, List<GrowInfoList> appList) {
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

            convertView = mInflater.inflate(R.layout.item_growlist, parent,
                    false);
            holder = new buttonViewHolder();
            holder.info_tv = (TextView) convertView
                    .findViewById(R.id.info_tv);
            holder.name_tv = (TextView) convertView
                    .findViewById(R.id.name_tv);
            holder.pic_img = (ImageView) convertView
                    .findViewById(R.id.pic_img);
            convertView.setTag(holder);
            
        } else {

            holder = (buttonViewHolder) convertView.getTag();

        }

        appInfo = mAppList.get(position);

        holder.name_tv.setText(appInfo.getLogAction());
        holder.info_tv.setText(appInfo.getWorkContent());

        String headPath = SOAP_UTILS.PIC_PATH + appInfo.getLogImg();
        Instance.imageLoader.displayImage(headPath, holder.pic_img, Instance.new_s_options);
        return convertView;
    }

}