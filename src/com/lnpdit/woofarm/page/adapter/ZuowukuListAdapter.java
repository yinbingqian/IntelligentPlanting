package com.lnpdit.woofarm.page.adapter;

import java.util.List;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.entity.ZuowukuList;
import com.lnpdit.woofarm.instance.Instance;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ZuowukuListAdapter extends BaseAdapter {
    private class buttonViewHolder {
        TextView crop_name;
        TextView biaozhun_tv;
        TextView huanjing_tv;
        ImageView zuowu_img;
    }

    private ZuowukuList appInfo;
    private List<ZuowukuList> mAppList;
    private LayoutInflater mInflater;
    private Context mContext;
    private buttonViewHolder holder;

    public ZuowukuListAdapter(Context context, List<ZuowukuList> appList) {
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

            convertView = mInflater.inflate(R.layout.item_zuowuku, parent,
                    false);
            holder = new buttonViewHolder();
            holder.crop_name = (TextView) convertView
                    .findViewById(R.id.crop_name);
            holder.biaozhun_tv = (TextView) convertView
                    .findViewById(R.id.biaozhun_tv);
            holder.huanjing_tv = (TextView) convertView
                    .findViewById(R.id.huanjing_tv);
            holder.zuowu_img = (ImageView) convertView
                    .findViewById(R.id.zuowu_img);
            convertView.setTag(holder);
            
        } else {

            holder = (buttonViewHolder) convertView.getTag();

        }

        appInfo = mAppList.get(position);

        holder.crop_name.setText(appInfo.getCropName());
        holder.biaozhun_tv.setText(appInfo.getStandard());
        holder.huanjing_tv.setText(appInfo.getEnvironment());
        String headPath = SOAP_UTILS.URL + appInfo.getAvatar();
        Instance.imageLoader.displayImage(headPath, holder.zuowu_img, Instance.zuowu_s_options);

        return convertView;
    }

}