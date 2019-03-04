package com.lnpdit.woofarm.page.adapter;

import java.util.List;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.entity.Cart;
import com.lnpdit.woofarm.entity.FarmList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AskExpertListAdapter extends BaseAdapter {
    private class buttonViewHolder {
        TextView name_tv;
        TextView type_tv;
        TextView info_tv;
        TextView ask_tv;
        ImageView iv_avatar;
    }

    private FarmList appInfo;
    private List<FarmList> mAppList;
    private LayoutInflater mInflater;
    private Context mContext;
    private buttonViewHolder holder;

    public AskExpertListAdapter(Context context, List<FarmList> appList) {
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

            convertView = mInflater.inflate(R.layout.item_askexpertlist, parent,
                    false);
            holder = new buttonViewHolder();
            holder.name_tv = (TextView) convertView
                    .findViewById(R.id.name_tv);
            holder.type_tv = (TextView) convertView
                    .findViewById(R.id.type_tv);
            holder.info_tv = (TextView) convertView
                    .findViewById(R.id.info_tv);
            holder.ask_tv = (TextView) convertView
                    .findViewById(R.id.ask_tv);
            holder.iv_avatar = (ImageView) convertView
                    .findViewById(R.id.iv_avatar);
            convertView.setTag(holder);
            
        } else {

            holder = (buttonViewHolder) convertView.getTag();

        }

        appInfo = mAppList.get(position);

        holder.name_tv.setText(appInfo.getFarmName());
        holder.type_tv.setText(appInfo.getFarmName());
        holder.info_tv.setText(appInfo.getFarmName());
        holder.ask_tv.setText(appInfo.getFarmName());

        return convertView;
    }

}