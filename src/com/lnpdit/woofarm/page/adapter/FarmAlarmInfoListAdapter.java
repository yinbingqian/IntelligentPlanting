package com.lnpdit.woofarm.page.adapter;

import java.util.List;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.entity.FarmAlarmInfoList;
import com.lnpdit.woofarm.http.ISoapService;
import com.lnpdit.woofarm.http.SoapService;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FarmAlarmInfoListAdapter extends BaseAdapter {
    private class buttonViewHolder {
        TextView creatdate_tv;
        TextView operation_tv;
    }

    private FarmAlarmInfoList appInfo;
    private List<FarmAlarmInfoList> mAppList;
    private LayoutInflater mInflater;
    private Context mContext;
    private buttonViewHolder holder;

    public ISoapService soapService = new SoapService();
    
    public FarmAlarmInfoListAdapter(Context context, List<FarmAlarmInfoList> appList) {
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

            convertView = mInflater.inflate(R.layout.item_farmalarminfolist, parent,
                    false);
            holder = new buttonViewHolder();
            holder.creatdate_tv = (TextView) convertView
                    .findViewById(R.id.creatdate_tv);
            holder.operation_tv = (TextView) convertView
                    .findViewById(R.id.operation_tv);
            convertView.setTag(holder);
            
        } else {

            holder = (buttonViewHolder) convertView.getTag();

        }

        appInfo = mAppList.get(position);

        holder.creatdate_tv.setText(appInfo.getCreateDate());//报警时间
        holder.operation_tv.setText(appInfo.getAlarmInfo());
        return convertView;
    }

}