package com.lnpdit.woofarm.page.adapter;

import java.util.List;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.entity.OriginsList;
import com.lnpdit.woofarm.instance.Instance;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class OriginsListAdapter extends BaseAdapter {
    private class buttonViewHolder {
        TextView crop_name;
//        TextView biaozhun_tv;
//        TextView huanjing_tv;
        ImageView zuowu_img;
    }

    private OriginsList appInfo;
    private List<OriginsList> mAppList;
    private LayoutInflater mInflater;
    private Context mContext;
    private buttonViewHolder holder;

    public OriginsListAdapter(Context context, List<OriginsList> appList) {
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

            convertView = mInflater.inflate(R.layout.item_origins, parent,
                    false);
            holder = new buttonViewHolder();
            holder.crop_name = (TextView) convertView
                    .findViewById(R.id.crop_name);
            holder.zuowu_img = (ImageView) convertView
                    .findViewById(R.id.zuowu_img);
            convertView.setTag(holder);
            
        } else {

            holder = (buttonViewHolder) convertView.getTag();

        }

        appInfo = mAppList.get(position);

        holder.crop_name.setText(appInfo.getCcropName());
        String headPath = SOAP_UTILS.URL + appInfo.getCavatar();
        Instance.imageLoader.displayImage(headPath, holder.zuowu_img, Instance.zuowu_s_options);

        return convertView;
    }

}