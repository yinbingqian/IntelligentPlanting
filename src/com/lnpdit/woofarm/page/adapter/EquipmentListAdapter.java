package com.lnpdit.woofarm.page.adapter;

import java.util.List;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.entity.Cart;
import com.lnpdit.woofarm.entity.Equipment;
import com.lnpdit.woofarm.entity.FarmList;
import com.lnpdit.woofarm.http.ISoapService;
import com.lnpdit.woofarm.http.SoapService;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EquipmentListAdapter extends BaseAdapter {
    private class buttonViewHolder {
        TextView equipname_tv;
        ImageView equipname_img;
        TextView status_tv1;
        TextView status_tv2;
        TextView status_tv3;
    }

    private Equipment appInfo;
    private List<Equipment> mAppList;
    private LayoutInflater mInflater;
    private Context mContext;
    private buttonViewHolder holder;

    public ISoapService soapService = new SoapService();
    
    public EquipmentListAdapter(Context context, List<Equipment> appList) {
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

            convertView = mInflater.inflate(R.layout.item_equipmentlist, parent,
                    false);
            holder = new buttonViewHolder();
            holder.equipname_tv = (TextView) convertView
                    .findViewById(R.id.equipname_tv);
            holder.equipname_img = (ImageView) convertView
                    .findViewById(R.id.equipname_img);
            holder.status_tv1 = (TextView) convertView
                    .findViewById(R.id.status_tv1);
            holder.status_tv2 = (TextView) convertView
                    .findViewById(R.id.status_tv2);
            holder.status_tv3 = (TextView) convertView
                    .findViewById(R.id.status_tv3);
            convertView.setTag(holder);
        } else {

            holder = (buttonViewHolder) convertView.getTag();

        }

        appInfo = mAppList.get(position);
       
        String equipname= appInfo.getEquipName();
        holder.equipname_tv.setText(equipname);
        String status = appInfo.getEquipStatus().toString();
        String equipid= appInfo.getSwitchId();
        if(equipid.equals("1")||equipid.equals("4")){
            holder.status_tv3.setVisibility(1);
            if(status.equals("1")){
                holder.status_tv1.setBackgroundResource(R.drawable.broder_orange);
                holder.status_tv2.setBackgroundResource(R.drawable.broder_graylabel);
                holder.status_tv3.setBackgroundResource(R.drawable.broder_graylabel);
                holder.status_tv1.setTextColor(mContext.getResources().getColor(R.color.white));
                holder.status_tv2.setTextColor(mContext.getResources().getColor(R.color.item_graytext));
                holder.status_tv3.setTextColor(mContext.getResources().getColor(R.color.item_graytext));
            }else if(status.equals("2")){

                holder.status_tv1.setBackgroundResource(R.drawable.broder_graylabel);
                holder.status_tv2.setBackgroundResource(R.drawable.broder_orange);
                holder.status_tv3.setBackgroundResource(R.drawable.broder_graylabel);
                holder.status_tv1.setTextColor(mContext.getResources().getColor(R.color.item_graytext));
                holder.status_tv2.setTextColor(mContext.getResources().getColor(R.color.white));
                holder.status_tv3.setTextColor(mContext.getResources().getColor(R.color.item_graytext));
            }else if(status.equals("3")){

                holder.status_tv1.setBackgroundResource(R.drawable.broder_graylabel);
                holder.status_tv2.setBackgroundResource(R.drawable.broder_graylabel);
                holder.status_tv3.setBackgroundResource(R.drawable.broder_orange);
                holder.status_tv1.setTextColor(mContext.getResources().getColor(R.color.item_graytext));
                holder.status_tv2.setTextColor(mContext.getResources().getColor(R.color.item_graytext));
                holder.status_tv3.setTextColor(mContext.getResources().getColor(R.color.white));
            }
        }else{
            holder.status_tv3.setVisibility(8);
            if(status.equals("1")){
                holder.status_tv1.setBackgroundResource(R.drawable.broder_orange);
                holder.status_tv2.setBackgroundResource(R.drawable.broder_graylabel);
                holder.status_tv1.setTextColor(mContext.getResources().getColor(R.color.white));
                holder.status_tv2.setTextColor(mContext.getResources().getColor(R.color.item_graytext));
            }else if(status.equals("3")){

                holder.status_tv1.setBackgroundResource(R.drawable.broder_graylabel);
                holder.status_tv2.setBackgroundResource(R.drawable.broder_orange);
                holder.status_tv1.setTextColor(mContext.getResources().getColor(R.color.item_graytext));
                holder.status_tv2.setTextColor(mContext.getResources().getColor(R.color.white));
            }
        }
//        if(equipname.equals("风机")){
//
//            holder.equipname_img.setBackgroundResource(R.drawable.fengji);
//        }else if(equipname.equals("补光灯")){
//
//            holder.equipname_img.setBackgroundResource(R.drawable.iocopy);
//        }else if(equipname.equals("放风")){
//
//            holder.equipname_img.setBackgroundResource(R.drawable.io);
//        }else if(equipname.equals("井水")){
//
//            holder.equipname_img.setBackgroundResource(R.drawable.fengji);
//        }else if(equipname.equals("卷帘")){
//
//            holder.equipname_img.setBackgroundResource(R.drawable.fengji);
//        }else if(equipname.equals("照明")){
//
//            holder.equipname_img.setBackgroundResource(R.drawable.fengji);
//        }else if(equipname.equals("水泵")){
//
//            holder.equipname_img.setBackgroundResource(R.drawable.fengji);
//        }else if(equipname.equals("滴灌")){
//
//            holder.equipname_img.setBackgroundResource(R.drawable.ing);
//        }
       
        
        holder.status_tv1.setClickable(true);
        holder.status_tv1.setOnClickListener(new ButtonListener_a(position, holder.status_tv1, holder.status_tv2, holder.status_tv3));

        holder.status_tv2.setClickable(true);
        holder.status_tv2.setOnClickListener(new ButtonListener_b(position, holder.status_tv1, holder.status_tv2, holder.status_tv3));

        holder.status_tv3.setClickable(true);
        holder.status_tv3.setOnClickListener(new ButtonListener_c(position, holder.status_tv1, holder.status_tv2, holder.status_tv3));
        
        return convertView;
    }

    class ButtonListener_a implements OnClickListener {
        private int position;
        private TextView tv1;
        private TextView tv2;
        private TextView tv3;

        public ButtonListener_a(int pos,TextView _tv1,TextView _tv2,TextView _tv3) {
            // TODO Auto-generated constructor stub
            position = pos;
            tv1 = _tv1;
            tv2 = _tv2;
            tv3 = _tv3;
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            
            tv1.setBackgroundResource(R.drawable.broder_orange);
            tv2.setBackgroundResource(R.drawable.broder_graylabel);
            tv3.setBackgroundResource(R.drawable.broder_graylabel);

            tv1.setTextColor(mContext.getResources().getColor(R.color.white));
            tv2.setTextColor(mContext.getResources().getColor(R.color.item_graytext));
            tv3.setTextColor(mContext.getResources().getColor(R.color.item_graytext));
            
            String[] property_vaa = new String[] {mAppList.get(position).getSwitchId(), "1" ,mAppList.get(position).getEquipCode() };
            soapService.equipmentState(property_vaa); 
    }
            
    }
    
    class ButtonListener_b implements OnClickListener {
        private int position;
        private TextView tv1;
        private TextView tv2;
        private TextView tv3;

        public ButtonListener_b(int pos,TextView _tv1,TextView _tv2,TextView _tv3) {
            // TODO Auto-generated constructor stub
            position = pos;
            tv1 = _tv1;
            tv2 = _tv2;
            tv3 = _tv3;
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            
            tv1.setBackgroundResource(R.drawable.broder_graylabel);
            tv2.setBackgroundResource(R.drawable.broder_orange);
            tv3.setBackgroundResource(R.drawable.broder_graylabel);

            tv1.setTextColor(mContext.getResources().getColor(R.color.item_graytext));
            tv2.setTextColor(mContext.getResources().getColor(R.color.white));
            tv3.setTextColor(mContext.getResources().getColor(R.color.item_graytext));

            String switchid = mAppList.get(position).getSwitchId();
            if(switchid.equals("1")||switchid.equals("4")){

                String[] property_vab = new String[] {switchid, "2" ,mAppList.get(position).getEquipCode() };
                soapService.equipmentState(property_vab); 
            }else{

                String[] property_vab = new String[] {switchid, "3" ,mAppList.get(position).getEquipCode() };
                soapService.equipmentState(property_vab); 
            }
            
    }
    }
    
    class ButtonListener_c implements OnClickListener {
        private int position;
        private TextView tv1;
        private TextView tv2;
        private TextView tv3;

        public ButtonListener_c(int pos,TextView _tv1,TextView _tv2,TextView _tv3) {
            // TODO Auto-generated constructor stub
            position = pos;
            tv1 = _tv1;
            tv2 = _tv2;
            tv3 = _tv3;
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            
            tv1.setBackgroundResource(R.drawable.broder_graylabel);
            tv2.setBackgroundResource(R.drawable.broder_graylabel);
            tv3.setBackgroundResource(R.drawable.broder_orange);
            tv1.setTextColor(mContext.getResources().getColor(R.color.item_graytext));
            tv2.setTextColor(mContext.getResources().getColor(R.color.item_graytext));
            tv3.setTextColor(mContext.getResources().getColor(R.color.white));

            String[] property_vab = new String[] {mAppList.get(position).getSwitchId(), "3" ,mAppList.get(position).getEquipCode() };
            soapService.equipmentState(property_vab); 
    }
            
    }
}