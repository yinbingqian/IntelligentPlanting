package com.lnpdit.woofarm.page.adapter;

import java.util.List;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.entity.PickList;
import com.lnpdit.woofarm.instance.Instance;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PickListAdapter extends BaseAdapter {
    private class buttonViewHolder {
        TextView cropname_tv;
        TextView basename_tv;
        TextView pickname_tv;
        TextView pickcount_tv;
        TextView pickdate_tv;
        ImageView pic_img;
    }

    private PickList appInfo;
    private List<PickList> mAppList;
    private LayoutInflater mInflater;
    private Context mContext;
    private buttonViewHolder holder;

    public PickListAdapter(Context context, List<PickList> appList) {
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

            convertView = mInflater.inflate(R.layout.item_picklist, parent,
                    false);
            holder = new buttonViewHolder();
            holder.cropname_tv = (TextView) convertView
                    .findViewById(R.id.cropname_tv);
            holder.basename_tv = (TextView) convertView
                    .findViewById(R.id.basename_tv);
            holder.pickname_tv = (TextView) convertView
                    .findViewById(R.id.pickname_tv);
            holder.pickcount_tv = (TextView) convertView
                    .findViewById(R.id.pickcount_tv);
            holder.pickdate_tv = (TextView) convertView
                    .findViewById(R.id.pickdate_tv);
            holder.pic_img = (ImageView) convertView
                    .findViewById(R.id.pic_img);
            convertView.setTag(holder);

        } else {

            holder = (buttonViewHolder) convertView.getTag();

        }

        appInfo = mAppList.get(position);

        holder.cropname_tv.setText(appInfo.getCropName());
        holder.basename_tv.setText("基地名称："+appInfo.getPlotName());
        holder.pickname_tv.setText("采摘人员："+appInfo.getPicker());
        holder.pickcount_tv.setText("采摘数量："+appInfo.getPickQuantity());
        holder.pickdate_tv.setText("采摘时间："+appInfo.getPickDate());

        String headPath = SOAP_UTILS.PIC_PATH + appInfo.getPickPhoto();
        Instance.imageLoader.displayImage(headPath, holder.pic_img, Instance.new_s_options);
        return convertView;
    }

}