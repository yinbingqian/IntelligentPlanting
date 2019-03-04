package com.lnpdit.woofarm.page.adapter;

import java.util.List;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.entity.FarmPlanList;
import com.lnpdit.woofarm.http.ISoapService;
import com.lnpdit.woofarm.http.SoapService;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FarmPlanFinishedListAdapter extends BaseAdapter {
    private class buttonViewHolder {
        TextView pengname_tv;
        TextView creatdate_tv;
        TextView planname_tv;
        TextView operation_tv;
        TextView finished_tv;
        TextView finish_tv;
    }

    private FarmPlanList appInfo;
    private List<FarmPlanList> mAppList;
    private LayoutInflater mInflater;
    private Context mContext;
    private buttonViewHolder holder;

    public ISoapService soapService = new SoapService();
    
    public FarmPlanFinishedListAdapter(Context context, List<FarmPlanList> appList) {
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

            convertView = mInflater.inflate(R.layout.item_farmplanfinished, parent,
                    false);
            holder = new buttonViewHolder();
            holder.pengname_tv = (TextView) convertView
                    .findViewById(R.id.pengname_tv);
            holder.creatdate_tv = (TextView) convertView
                    .findViewById(R.id.creatdate_tv);
            holder.planname_tv = (TextView) convertView
                    .findViewById(R.id.planname_tv);
            holder.operation_tv = (TextView) convertView
                    .findViewById(R.id.operation_tv);
            holder.finished_tv = (TextView) convertView
                    .findViewById(R.id.finished_tv);
//            holder.finish_tv = (TextView) convertView
//                    .findViewById(R.id.finish_tv);
            convertView.setTag(holder);
            
        } else {

            holder = (buttonViewHolder) convertView.getTag();

        }

        appInfo = mAppList.get(position);

        holder.pengname_tv.setText(appInfo.getPengName());
        holder.creatdate_tv.setText(appInfo.getBeginDate() + "-" +appInfo.getEndDate());
        holder.planname_tv.setText(appInfo.getPlanName());
        holder.operation_tv.setText(appInfo.getPlanType());
        
//        holder.finish_tv.setOnClickListener(new finishButtonListener(position));
        
        return convertView;
    }
    
//    class finishButtonListener implements OnClickListener {
//        private int position;
//
//        public finishButtonListener(int pos) {
//            // TODO Auto-generated constructor stub
//            position = pos;
//
//        }
//
//        @Override
//        public void onClick(View v) {
//            
//            String[] property_vas = new String[] { mAppList.get(position).getId() };
//            soapService.finishPlan(property_vas);
//            
//            holder.finished_tv.setVisibility(1);
//            holder.finish_tv.setVisibility(8);
//        }
//    }

}