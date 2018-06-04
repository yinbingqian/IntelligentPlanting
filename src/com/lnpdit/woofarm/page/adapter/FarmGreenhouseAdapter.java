package com.lnpdit.woofarm.page.adapter;

import java.util.List;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.entity.Cart;
import com.lnpdit.woofarm.entity.FarmGreenHouseList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FarmGreenhouseAdapter extends BaseAdapter {
    private class buttonViewHolder {
        ImageView state_img;
        TextView farm_name;
        TextView area_text;
        TextView state_tv;
        TextView monitor_tv;
        TextView control_tv;
    }

    private FarmGreenHouseList appInfo;
    private List<FarmGreenHouseList> mAppList;
    private LayoutInflater mInflater;
    private Context mContext;
    private buttonViewHolder holder;

    public FarmGreenhouseAdapter(Context context, List<FarmGreenHouseList> appList) {
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

            convertView = mInflater.inflate(R.layout.item_greenhouse, parent,
                    false);
            holder = new buttonViewHolder();
            holder.state_img = (ImageView) convertView
                    .findViewById(R.id.state_img);
            holder.farm_name = (TextView) convertView
                    .findViewById(R.id.farm_name);
            holder.area_text = (TextView) convertView
                    .findViewById(R.id.area_text);
            holder.state_tv = (TextView) convertView
                    .findViewById(R.id.state_tv);
            holder.monitor_tv = (TextView) convertView
                    .findViewById(R.id.monitor_tv);
            holder.control_tv = (TextView) convertView
                    .findViewById(R.id.control_tv);
            convertView.setTag(holder);
            
        } else {

            holder = (buttonViewHolder) convertView.getTag();

        }

        appInfo = mAppList.get(position);

        holder.farm_name.setText(appInfo.getPengName());
        holder.area_text.setText(appInfo.getLandarea());
        holder.monitor_tv.setText("监测设备：" + appInfo.getSensorCount());
        holder.control_tv.setText("控制设施：" + appInfo.getControlCount());
        String state = appInfo.getPlanting();
        if(state.equals("false")){
            holder.state_img.setBackgroundResource(R.drawable.xianzhi);
            holder.state_tv.setText("闲置中");
            holder.state_tv.setTextColor(mContext.getResources().getColor(R.color.item_orangetext));
        }else{

            holder.state_img.setBackgroundResource(R.drawable.ing);
            holder.state_tv.setText("使用中");
            holder.state_tv.setTextColor(mContext.getResources().getColor(R.color.item_greentext));
        }
//        int value1 = 0;
//        Class<R.drawable> cls = R.drawable.class;
//        try {
//            value1 = cls.getDeclaredField(appInfo.getThumb()).getInt(null);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        holder.state_img.setImageResource(value1);

        return convertView;
    }

}