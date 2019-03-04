package com.lnpdit.woofarm.page.adapter;

import java.util.List;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.entity.ZuowukuList;
import com.lnpdit.woofarm.instance.Instance;
import com.lnpdit.woofarm.page.activity.farmservice.CropDiseaseListActivity;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CropListAdapter extends BaseAdapter {
    private class buttonViewHolder {
        TextView cropdisease_name;
        ImageView cropdisease_img;
    }

    private ZuowukuList appInfo;
    private List<ZuowukuList> mAppList;
    private LayoutInflater mInflater;
    private Context mContext;
    private buttonViewHolder holder;
    private String cropCode = "";

    public CropListAdapter(Context context, List<ZuowukuList> appList) {
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

            convertView = mInflater.inflate(R.layout.item_croplist, parent,
                    false);
            holder = new buttonViewHolder();
            holder.cropdisease_name = (TextView) convertView
                    .findViewById(R.id.cropdisease_name);
            holder.cropdisease_img = (ImageView) convertView
                    .findViewById(R.id.cropdisease_img);
            holder.cropdisease_img.setClickable(true);
            
            convertView.setTag(holder);
            
        } else {

            holder = (buttonViewHolder) convertView.getTag();

        }

        appInfo = mAppList.get(position);

        holder.cropdisease_name.setText(appInfo.getCropName());
        Instance.imageLoader.displayImage(SOAP_UTILS.IP + appInfo.getAvatar(), holder.cropdisease_img, Instance.new_s_options);
        holder.cropdisease_img.setClickable(true);
        
        cropCode = appInfo.getCropCode();

        holder.cropdisease_img.setOnClickListener(new ButtonListener(position));
     
        
        
        return convertView;
    }
    class ButtonListener implements OnClickListener {
        private int position;

        public ButtonListener(int pos) {
            // TODO Auto-generated constructor stub
            position = pos;

        }

        @Override
        public void onClick(View v) {
            
            Intent intent = new Intent();
            intent.putExtra("cropCode", mAppList.get(position).getCropCode());
            intent.setClass(mContext, CropDiseaseListActivity.class);
            mContext.startActivity(intent);
        }
    }
}