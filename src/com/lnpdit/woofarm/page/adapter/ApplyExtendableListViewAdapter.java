package com.lnpdit.woofarm.page.adapter;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.entity.Equipment;
import com.lnpdit.woofarm.http.ISoapService;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.http.SoapService;
import com.lnpdit.woofarm.page.activity.farm.ShuifeiInfoActivity;
import com.lnpdit.woofarm.page.activity.farm.ShuifeijianceInfoActivity;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ApplyExtendableListViewAdapter extends BaseExpandableListAdapter {
    private Context mcontext;
//    public String[] groupString = {"21", "23", "34", "45"};
//    public String[][] childString = { {"12", "23", "34", "45"}, {"56", "67", "78", "89"}, {"90", "87", "67", "43"}, {"32", "21", "34", "34"} };

    public ISoapService soapService = new SoapService();
    private List<Equipment> equipmentList  = new ArrayList<Equipment>();
    private String state03 = "05";
    
    private LayoutInflater mInflater;
    public ApplyExtendableListViewAdapter(Context context, List<Equipment> equiplist) {
        equipmentList = equiplist;
        mcontext = context;
        mInflater = (LayoutInflater) mcontext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override // 获取分组的个数
    public int getGroupCount() {
        return equipmentList.size();
    } //获取指定分组中的子选项的个数
    @Override
    public int getChildrenCount(int groupPosition) {
        return equipmentList.get(groupPosition).get_List().size();
    } //        获取指定的分组数据
    @Override public Object getGroup(int groupPosition) {
        return equipmentList.get(groupPosition);
    }  //获取指定分组中的指定子选项数据
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return equipmentList.get(groupPosition).get_List();
    } //获取指定分组的ID, 这个ID必须是唯一的
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    } //获取子选项的ID, 这个ID必须是唯一的
    @Override public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }  //分组和子选项是否持有稳定的ID, 就是说底层数据的改变会不会影响到它们
    @Override public boolean hasStableIds() {
        return true;
    }
    /**
 *
         * 获取显示指定组的视图对象
 *
         * @param groupPosition 组位置
 * @param isExpanded 该组是展开状态还是伸缩状态
 * @param convertView 重用已有的视图对象
 * @param parent 返回的视图对象始终依附于的视图组
  */ // 获取显示指定分组的视图
   @Override
   public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
       GroupViewHolder groupViewHolder;
       if (convertView == null){
           convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_parentequipmentlist,parent,false);
 
           groupViewHolder = new GroupViewHolder();
           groupViewHolder.tvTitle = (TextView)convertView.findViewById(R.id.equipname_tv);
           groupViewHolder.equipname_img = (ImageView)convertView.findViewById(R.id.equipname_img);
           convertView.setTag(groupViewHolder);
       }else {
           groupViewHolder = (GroupViewHolder)convertView.getTag();
       }
       groupViewHolder.tvTitle.setText(equipmentList.get(groupPosition).getEquipName().toString());
       return convertView;
   }
 
       /**
          *
          * 获取一个视图对象，显示指定组中的指定子元素数据。
            *
            * @param groupPosition 组位置
     * @param childPosition 子元素位置
     * @param isLastChild 子元素是否处于组中的最后一个
     * @param convertView 重用已有的视图(View)对象
     * @param parent 返回的视图(View)对象始终依附于的视图组
     * @return
             * @see android.widget.ExpandableListAdapter#getChildView(int, int, boolean, android.view.View,
     *      android.view.ViewGroup)
     */ //取得显示给定分组给定子位置的数据用的视图
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_childequipmentlist,parent,false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.tvTitle = (TextView)convertView.findViewById(R.id.channel_tv);
            childViewHolder.status_tv1 = (TextView)convertView.findViewById(R.id.status_tv1);
            childViewHolder.status_tv2 = (TextView)convertView.findViewById(R.id.status_tv2);
            childViewHolder.status_tv3 = (TextView)convertView.findViewById(R.id.status_tv3);
            convertView.setTag(childViewHolder);
        }else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        childViewHolder.tvTitle.setText(equipmentList.get(groupPosition).get_List().get(childPosition).getChannelName().toString());
        String channeltype = equipmentList.get(groupPosition).get_List().get(childPosition).getChannelType().toString();
        String channelstatus = equipmentList.get(groupPosition).get_List().get(childPosition).getChannelStatus().toString();
        if(channeltype.equals("01")){//01是开关 按钮两个（开 关） 02是卷帘机 按钮三个（升起 下降 停止）
            childViewHolder.status_tv3.setVisibility(8);
            childViewHolder.status_tv1.setText("打开");
            childViewHolder.status_tv2.setText("关闭");
            if(channelstatus.equals("04")){
                childViewHolder.status_tv1.setBackgroundResource(R.drawable.broder_orange);
                childViewHolder.status_tv2.setBackgroundResource(R.drawable.broder_graylabel);
                childViewHolder.status_tv1.setTextColor(mcontext.getResources().getColor(R.color.white));
                childViewHolder.status_tv2.setTextColor(mcontext.getResources().getColor(R.color.item_graytext));
            }else if(channelstatus.equals("05")){

                childViewHolder.status_tv1.setBackgroundResource(R.drawable.broder_graylabel);
                childViewHolder.status_tv2.setBackgroundResource(R.drawable.broder_orange);
                childViewHolder.status_tv1.setTextColor(mcontext.getResources().getColor(R.color.item_graytext));
                childViewHolder.status_tv2.setTextColor(mcontext.getResources().getColor(R.color.white));
            }else if(channelstatus.equals("00")){
                childViewHolder.status_tv1.setBackgroundResource(R.drawable.broder_graylabel);
                childViewHolder.status_tv2.setBackgroundResource(R.drawable.broder_graylabel);
                childViewHolder.status_tv1.setTextColor(mcontext.getResources().getColor(R.color.item_graytext));
                childViewHolder.status_tv2.setTextColor(mcontext.getResources().getColor(R.color.item_graytext));
            }
        }else if(channeltype.equals("02")){
            childViewHolder.status_tv3.setVisibility(1);
            childViewHolder.status_tv1.setText("升起");
            childViewHolder.status_tv2.setText("降落");
            if(channelstatus.equals("01")){
                childViewHolder.status_tv1.setBackgroundResource(R.drawable.broder_orange);
                childViewHolder.status_tv2.setBackgroundResource(R.drawable.broder_graylabel);
                childViewHolder.status_tv3.setBackgroundResource(R.drawable.broder_graylabel);
                childViewHolder.status_tv1.setTextColor(mcontext.getResources().getColor(R.color.white));
                childViewHolder.status_tv2.setTextColor(mcontext.getResources().getColor(R.color.item_graytext));
                childViewHolder.status_tv3.setTextColor(mcontext.getResources().getColor(R.color.item_graytext));
            }else if(channelstatus.equals("02")){

                childViewHolder.status_tv1.setBackgroundResource(R.drawable.broder_graylabel);
                childViewHolder.status_tv2.setBackgroundResource(R.drawable.broder_orange);
                childViewHolder.status_tv3.setBackgroundResource(R.drawable.broder_graylabel);
                childViewHolder.status_tv1.setTextColor(mcontext.getResources().getColor(R.color.item_graytext));
                childViewHolder.status_tv2.setTextColor(mcontext.getResources().getColor(R.color.white));
                childViewHolder.status_tv3.setTextColor(mcontext.getResources().getColor(R.color.item_graytext));
            }else if(channelstatus.equals("03")){

                childViewHolder.status_tv1.setBackgroundResource(R.drawable.broder_graylabel);
                childViewHolder.status_tv2.setBackgroundResource(R.drawable.broder_graylabel);
                childViewHolder.status_tv3.setBackgroundResource(R.drawable.broder_orange);
                childViewHolder.status_tv1.setTextColor(mcontext.getResources().getColor(R.color.item_graytext));
                childViewHolder.status_tv2.setTextColor(mcontext.getResources().getColor(R.color.item_graytext));
                childViewHolder.status_tv3.setTextColor(mcontext.getResources().getColor(R.color.white));
            }else if(channelstatus.equals("00")){

                childViewHolder.status_tv1.setBackgroundResource(R.drawable.broder_graylabel);
                childViewHolder.status_tv2.setBackgroundResource(R.drawable.broder_graylabel);
                childViewHolder.status_tv3.setBackgroundResource(R.drawable.broder_graylabel);
                childViewHolder.status_tv1.setTextColor(mcontext.getResources().getColor(R.color.item_graytext));
                childViewHolder.status_tv2.setTextColor(mcontext.getResources().getColor(R.color.item_graytext));
                childViewHolder.status_tv3.setTextColor(mcontext.getResources().getColor(R.color.item_graytext));
            }
        }else if(channeltype.equals("03")){//01是开关 按钮两个（开 关） 02是卷帘机 按钮三个（升起 下降 停止） 03是水肥机 按钮两个（开 关）
            childViewHolder.status_tv3.setVisibility(8);
            childViewHolder.status_tv1.setText("打开");
            childViewHolder.status_tv2.setText("关闭");
            if(state03.equals("04")){
                childViewHolder.status_tv1.setBackgroundResource(R.drawable.broder_orange);
                childViewHolder.status_tv2.setBackgroundResource(R.drawable.broder_graylabel);
                childViewHolder.status_tv1.setTextColor(mcontext.getResources().getColor(R.color.white));
                childViewHolder.status_tv2.setTextColor(mcontext.getResources().getColor(R.color.item_graytext));
            }else if(state03.equals("05")){

                childViewHolder.status_tv1.setBackgroundResource(R.drawable.broder_graylabel);
                childViewHolder.status_tv2.setBackgroundResource(R.drawable.broder_orange);
                childViewHolder.status_tv1.setTextColor(mcontext.getResources().getColor(R.color.item_graytext));
                childViewHolder.status_tv2.setTextColor(mcontext.getResources().getColor(R.color.white));
            }else if(state03.equals("00")){
                childViewHolder.status_tv1.setBackgroundResource(R.drawable.broder_graylabel);
                childViewHolder.status_tv2.setBackgroundResource(R.drawable.broder_graylabel);
                childViewHolder.status_tv1.setTextColor(mcontext.getResources().getColor(R.color.item_graytext));
                childViewHolder.status_tv2.setTextColor(mcontext.getResources().getColor(R.color.item_graytext));
            }
        }
            childViewHolder.status_tv1.setClickable(true);
            childViewHolder.status_tv1.setOnClickListener(new ButtonListener_a(groupPosition,childPosition, childViewHolder.status_tv1, childViewHolder.status_tv2, childViewHolder.status_tv3));

            childViewHolder.status_tv2.setClickable(true);
            childViewHolder.status_tv2.setOnClickListener(new ButtonListener_b(groupPosition,childPosition, childViewHolder.status_tv1, childViewHolder.status_tv2, childViewHolder.status_tv3));

            childViewHolder.status_tv3.setClickable(true);
            childViewHolder.status_tv3.setOnClickListener(new ButtonListener_c(groupPosition,childPosition, childViewHolder.status_tv1, childViewHolder.status_tv2, childViewHolder.status_tv3));
       
        return convertView;
    } //指定位置上的子元素是否可选中
    @Override public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
 
    static class GroupViewHolder {
        TextView tvTitle;
        ImageView equipname_img;
    }
    static class ChildViewHolder {
        TextView tvTitle;
        TextView status_tv1;
        TextView status_tv2;
        TextView status_tv3;
    }
    class ButtonListener_a implements OnClickListener {
        private int grouppos;
        private int childpos;
        private TextView tv1;
        private TextView tv2;
        private TextView tv3;

        public ButtonListener_a(int _grouppos,int _childpos,TextView _tv1,TextView _tv2,TextView _tv3) {
            // TODO Auto-generated constructor stub
            grouppos = _grouppos;
            childpos = _childpos;
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

            tv1.setTextColor(mcontext.getResources().getColor(R.color.white));
            tv2.setTextColor(mcontext.getResources().getColor(R.color.item_graytext));
            tv3.setTextColor(mcontext.getResources().getColor(R.color.item_graytext));
            

                if(equipmentList.get(grouppos).get_List().get(childpos).getChannelType().equals("01")){

                    String[] property_vaa = new String[] {equipmentList.get(grouppos).getEquipCode(), equipmentList.get(grouppos).get_List().get(childpos).getChannelNo()
                            ,"04"};
                    soapService.equipmentState(property_vaa);  
                }else if(equipmentList.get(grouppos).get_List().get(childpos).getChannelType().equals("02")){

                    String[] property_vaa = new String[] {equipmentList.get(grouppos).getEquipCode(), equipmentList.get(grouppos).get_List().get(childpos).getChannelNo()
                            ,"01"};
                    soapService.equipmentState(property_vaa);  
             
                }else if(equipmentList.get(grouppos).get_List().get(childpos).getChannelType().equals("03")){
                   if(state03.equals("04")||state03.equals("06")){//04 打开 05关闭 06施肥中

                        state03 = "06"; 
                        tv1.setText("施肥中");
                        Intent in_info = new Intent();
                        in_info.setClass(mcontext, ShuifeiInfoActivity.class);
                        mcontext.startActivity(in_info);
                    }else if(state03.equals("05")){
                        state03 = "04"; 
                        tv1.setText("施肥中");
                        Intent in_alarminfo = new Intent();
                        in_alarminfo.setClass(mcontext, ShuifeijianceInfoActivity.class);
                        mcontext.startActivity(in_alarminfo);
                    }
               
                }
           


    }
            
    }
    
    class ButtonListener_b implements OnClickListener {
        private int grouppos;
        private int childpos;
        private TextView tv1;
        private TextView tv2;
        private TextView tv3;

        public ButtonListener_b(int _grouppos,int _childpos,TextView _tv1,TextView _tv2,TextView _tv3) {
            // TODO Auto-generated constructor stub
            grouppos = _grouppos;
            childpos = _childpos;
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

            tv1.setTextColor(mcontext.getResources().getColor(R.color.item_graytext));
            tv2.setTextColor(mcontext.getResources().getColor(R.color.white));
            tv3.setTextColor(mcontext.getResources().getColor(R.color.item_graytext));


            if(equipmentList.get(grouppos).get_List().get(childpos).getChannelType().equals("01")){

                String[] property_vaa = new String[] {equipmentList.get(grouppos).getEquipCode(), equipmentList.get(grouppos).get_List().get(childpos).getChannelNo()
                        ,"05"};
                soapService.equipmentState(property_vaa);  
            }else if(equipmentList.get(grouppos).get_List().get(childpos).getChannelType().equals("02")){

                String[] property_vaa = new String[] {equipmentList.get(grouppos).getEquipCode(), equipmentList.get(grouppos).get_List().get(childpos).getChannelNo()
                        ,"02"};
                soapService.equipmentState(property_vaa);  
            }else if(equipmentList.get(grouppos).get_List().get(childpos).getChannelType().equals("03")){

                    state03 = "05"; 
                    tv1.setText("打开");
                 Toast.makeText(mcontext, "已关闭设备", Toast.LENGTH_SHORT).show();
           
            }
    }
    }
    
    class ButtonListener_c implements OnClickListener {
        private int grouppos;
        private int childpos;
        private TextView tv1;
        private TextView tv2;
        private TextView tv3;

        public ButtonListener_c(int _grouppos,int _childpos,TextView _tv1,TextView _tv2,TextView _tv3) {
            // TODO Auto-generated constructor stub
            grouppos = _grouppos;
            childpos = _childpos;
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
            tv1.setTextColor(mcontext.getResources().getColor(R.color.item_graytext));
            tv2.setTextColor(mcontext.getResources().getColor(R.color.item_graytext));
            tv3.setTextColor(mcontext.getResources().getColor(R.color.white));

                String[] property_vaa = new String[] {equipmentList.get(grouppos).getEquipCode(), equipmentList.get(grouppos).get_List().get(childpos).getChannelNo()
                        ,"03"};
                soapService.equipmentState(property_vaa);  
    }
            
    }
    
}
