package com.lnpdit.woofarm.page.activity.farmingmanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.customview.LoadingPage;
import com.lnpdit.woofarm.customview.LoadingPage.ILoadingDo;
import com.lnpdit.woofarm.entity.AreaHouseList;
import com.lnpdit.woofarm.http.ISoapService;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.http.SoapService;
import com.lnpdit.woofarm.utils.EventCache;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

public class FarmPlanListActivityOld extends ExpandableListActivity {
    /** Called when the activity is first created. */
    /** soapService **/
    public ISoapService soapService = new SoapService();
    private TextView tv_title;
    Context context;
    /** loading **/
    private LoadingPage loading;
    
    private List<AreaHouseList> list;
    String id;
    String plantId;
    String planName;
    String beginDate;
    String endDate;
    String userItem;
    String planType;
    String planStatus;
    String pengName;
  
    String userCode;
    // ExpandableListView expandableListView;

    // 创建一级条目容器
    List<Map<String, String>> gruops = new ArrayList<Map<String, String>>();
    // 存放内容, 以便显示在列表中
    List<List<Map<String, String>>> childs = new ArrayList<List<Map<String, String>>>();

    Map<String, String> title_1 = new HashMap<String, String>();
    // Map<String, String> title_1_content_1 = new HashMap<String, String>();
    List<Map<String, String>> childs_1 = new ArrayList<Map<String, String>>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_farmplanold);

        EventCache.commandActivity.unregister(this);
        EventCache.commandActivity.register(this);
        EventCache.errorHttp.unregister(this);
        EventCache.errorHttp.register(this);

      SharedPreferences sharedPreferences = getSharedPreferences("userinfo",Activity.MODE_PRIVATE);  
      userCode = sharedPreferences.getString("userCode","");  
        
        initView();
        // setUI();
        setListData();

    }

    private void initView() {

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("首页");
    }

    /**
     * 设置列表内容
     */
    public void setListData() {
        String[] property_va = new String[] {userCode};
        soapService.getPlanlist(property_va);

    }

    /**
     * 列表内容按下
     */
    @Override
    public boolean onChildClick(ExpandableListView parent, View v,
            int groupPosition, int childPosition, long id) {
        // Toast.makeText( MainActivity.this, "您选择了" +
        // gruops.get(groupPosition).toString()
        // + "子编号"+ childs.get(groupPosition).get(childPosition)
        // .toString(), Toast.LENGTH_SHORT).show();
//        
//        String areanumber = childs.get(groupPosition).get(childPosition)
//                .get("areanumber").toString();
//        String houseserial = childs.get(groupPosition).get(childPosition)
//                .get("houseserial").toString();
//        Intent intent_main = new Intent();
//        intent_main.putExtra("areanumber", areanumber);
//        intent_main.putExtra("houseserial", houseserial);
//        intent_main.setClass(this, MainDPActivity.class);
//        startActivity(intent_main);
        return super.onChildClick(parent, v, groupPosition, childPosition, id);
    }

    /**
     * 二级标题按下
     */
    @Override
    public boolean setSelectedChild(int groupPosition, int childPosition,
            boolean shouldExpandGroup) {
        return super.setSelectedChild(groupPosition, childPosition,
                shouldExpandGroup);
    }

    /**
     * 一级标题按下
     */
    @Override
    public void setSelectedGroup(int groupPosition) {
        super.setSelectedGroup(groupPosition);
    }

    // public void setUI() {
    // webview.loadUrl("http://192.168.1.114:8090/jfarm/a/login");
    // }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventCache.commandActivity.unregister(this);
        EventCache.errorHttp.unregister(this);
    }
    public void onEvent(Object obj) {
        SoapRes res = (SoapRes) obj;
        if (res.getCode().equals(SOAP_UTILS.METHOD.PLANLIST)) {
            if (res.getObj() != null) {
                try {
                    JSONArray  json_obj = new JSONArray(res.getObj().toString());
                                        for (int j = 0; j < json_obj.length(); j++) {
                                            JSONObject json_objs = (JSONObject) json_obj.get(j);
//                                            areaname = json_objs.get("areaname").toString();
                                            JSONArray areahouse_array = json_objs
                                                    .getJSONArray("houseList");
                                            childs_1 = new ArrayList<Map<String, String>>();
                                            for (int i = 0; i < areahouse_array.length(); i++) {
                                                JSONObject json_news = (JSONObject) areahouse_array
                                                        .get(i);
                                                id = json_news.get("id")
                                                        .toString();
                                                plantId = json_news.get("plantId")
                                                        .toString();
                                                planName = json_news.get("planName")
                                                        .toString();
                                                beginDate = json_news.get("beginDate").toString();
                                                endDate = json_news.get("endDate").toString();
                                                userItem = json_news.get("userItem").toString();
                                                planType = json_news.get("planType").toString();
                                                planStatus = json_news.get("planStatus").toString();
                                                pengName = json_news.get("pengName").toString();
                                                // list.add(hpn);
                                         
                                                // 创建二级条目内容
                                                // 内容一
                                                Map<String, String> title_1_content_1 = new HashMap<String, String>();
                                                title_1_content_1.put("id", id);
                                                title_1_content_1.put("plantId", plantId);
                                                title_1_content_1.put("planName", planName);
                                                title_1_content_1.put("beginDate", beginDate);
                                                title_1_content_1.put("endDate", endDate);
                                                title_1_content_1.put("userItem", userItem);
                                                title_1_content_1.put("planType", planType);
                                                title_1_content_1.put("planStatus", planStatus);
                                                title_1_content_1.put("pengName", pengName);
                                                childs_1.add(title_1_content_1);
                                            }
                                            title_1 = new HashMap<String, String>();
                                            title_1.put("group", "待完成");
                                            title_1.put("group", "已完成");
                                            gruops.add(title_1);
                    
                                            childs.add(childs_1);
                                        }
                    
                                        /**
                                         * 创建ExpandableList的Adapter容器 参数: 1.上下文 2.一级集合 3.一级样式文件 4.
                                         * 一级条目键值 5.一级显示控件名 6. 二级集合 7. 二级样式 8.二级条目键值 9.二级显示控件名
                                         */
//                                        SimpleExpandableListAdapter sela = new SimpleExpandableListAdapter(
//                                                FarmPlanListActivityOld.this, gruops, R.layout.activity_groups,
//                                                new String[] { "group" },
//                                                new int[] { R.id.textgroup }, childs,
//                                                R.layout.activity_childs, new String[] { "child" },
//                                                new int[] { R.id.textchild1,R.id.textchild2,R.id.textchild3,R.id.textchild4 });
                                        // 加入列表
//                                        setListAdapter(sela);
                    
                                } catch (JSONException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                
            }

        }
    }
    /**
     * 添加loading
     */
    public void addLoading() {
        loading = new LoadingPage(this, new ILoadingDo() {
            
            @Override
            public void soapFail(String methodName) {
                EventCache.errorHttp.post(methodName);
            }
        });
        
        addContentView(loading, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    /**
     * 移除 loading
     */
    public void removeLoading() {
        if (loading != null) {
            ViewGroup parent = (ViewGroup) loading.ll_bg.getParent();
            parent.removeView(loading.ll_bg);
            loading = null;
        }
    }

}