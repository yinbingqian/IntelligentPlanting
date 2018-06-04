package com.lnpdit.woofarm.page.adapter;

import java.util.List;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.entity.DeviceList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DeviceListAdapter extends BaseAdapter {
    private class buttonViewHolder {
        TextView devicename_tv;
        TextView devicestate_tv;
        TextView pengname_tv;
    }

    private DeviceList appInfo;
    private List<DeviceList> mAppList;
    private LayoutInflater mInflater;
    private Context mContext;
    private buttonViewHolder holder;

    public DeviceListAdapter(Context context, List<DeviceList> appList) {
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

            convertView = mInflater.inflate(R.layout.item_devicelist, parent,
                    false);
            holder = new buttonViewHolder();
            holder.devicename_tv = (TextView) convertView
                    .findViewById(R.id.devicename_tv);
            holder.devicestate_tv = (TextView) convertView
                    .findViewById(R.id.devicestate_tv);
            holder.pengname_tv = (TextView) convertView
                    .findViewById(R.id.pengname_tv);
            convertView.setTag(holder);

        } else {

            holder = (buttonViewHolder) convertView.getTag();

        }

        appInfo = mAppList.get(position);

        holder.devicename_tv.setText(appInfo.getEquipName());
        holder.devicestate_tv.setText(appInfo.getEquipStatus());
        holder.pengname_tv.setText(appInfo.getPengName());

        return convertView;
    }

}