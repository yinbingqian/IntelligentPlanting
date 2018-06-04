package com.lnpdit.woofarm.page.adapter;

import java.util.List;

import org.joda.time.field.AbstractPartialFieldProperty;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.entity.Scenes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ScenesListAdapter extends BaseAdapter {
    private class buttonViewHolder {
        TextView scenesname_tv;
        TextView scenesvalues_tv;
        TextView scenesaction_tv;
    }

    private Scenes appInfo;
    private List<Scenes> mAppList;
    private LayoutInflater mInflater;
    private Context mContext;
    private buttonViewHolder holder;

    public ScenesListAdapter(Context context, List<Scenes> appList) {
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

            convertView = mInflater.inflate(R.layout.item_sceneslist, parent,
                    false);
            holder = new buttonViewHolder();
            holder.scenesname_tv = (TextView) convertView
                    .findViewById(R.id.scenesname_tv);
            holder.scenesvalues_tv = (TextView) convertView
                    .findViewById(R.id.scenesvalues_tv);
            holder.scenesaction_tv = (TextView) convertView
                    .findViewById(R.id.scenesaction_tv);
            
            convertView.setTag(holder);
            
        } else {

            holder = (buttonViewHolder) convertView.getTag();

        }

        appInfo = mAppList.get(position);

        holder.scenesname_tv.setText(appInfo.getScenesName());
        holder.scenesvalues_tv.setText("当设备" + appInfo.getEquipCode() + "的" +appInfo.getParameter()+appInfo.getEffectCodition() +appInfo.getScenesValue() + "时");
        holder.scenesaction_tv.setText("设备" + appInfo.getEquipCode() + appInfo.getAction());

        return convertView;
    }

}