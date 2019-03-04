package com.lnpdit.woofarm.page.activity.farminformation;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.application.DemoApplication;
import com.lnpdit.woofarm.db.DBHelper;
import com.lnpdit.woofarm.entity.FarmNewsList;
import com.lnpdit.woofarm.http.ISoapService;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.http.SoapService;
import com.lnpdit.woofarm.page.adapter.FarmFruitListAdapter;
import com.lnpdit.woofarm.page.adapter.FarmNewsListAdapter;
import com.lnpdit.woofarm.page.adapter.FarmVegetablesListAdapter;
import com.lnpdit.woofarm.pulltorefresh.library.PullToRefreshBase;
import com.lnpdit.woofarm.pulltorefresh.library.PullToRefreshListView;
import com.lnpdit.woofarm.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.lnpdit.woofarm.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.lnpdit.woofarm.utils.EventCache;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class FarmFruitFragment extends Fragment{

	Context context;
	View view;
	/** soapService **/
	public ISoapService soapService = new SoapService();

    private PullToRefreshListView listView_farmnewslist;
    private ListView listView;
    private LinearLayout vegetables_layout;
    private LinearLayout fruit_layout;
    private LinearLayout grain_layout;
    private LinearLayout seedling_layout;
    private LinearLayout more_layout;
	private int pageIndex = 1;

	private DBHelper dbh;
	private FarmFruitListAdapter adapter;
	private List<FarmNewsList> list;
//	private FarmNewsList farmnews;
	public DemoApplication myApplication;
//	private static final String TAG = "SU-JPush";

	public FarmFruitFragment() {
		super();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		System.out.println("onAttach");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		myApplication = DemoApplication.getInstance();
		EventCache.commandActivity.unregister(this);
		EventCache.commandActivity.register(this);
		EventCache.errorHttp.unregister(this);
		EventCache.errorHttp.register(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		System.out.println("onCreateView");

		context = this.getActivity().getApplicationContext();

		view = inflater.inflate(R.layout.fragment_informationitem_newstype, container, false);

	    initView();
	    initData();
	    setListeners();
		return view;
	}

private void initView() {
    listView_farmnewslist = (PullToRefreshListView) view.findViewById(R.id.listView_farmnewslist);
    listView = listView_farmnewslist.getRefreshableView();
  
    vegetables_layout = (LinearLayout) view.findViewById(R.id.vegetables_layout);
    vegetables_layout.setClickable(true);
    vegetables_layout.setOnClickListener(new OnClickListener() {  
        @Override  
        public void onClick(View v) {  
            
            String[] property_va = new String[] {"3", pageIndex + "","10","蔬菜"};
            soapService.getNewsList3(property_va, false);
        }  
            });  
    fruit_layout = (LinearLayout) view.findViewById(R.id.fruit_layout);
    fruit_layout.setClickable(true);
    fruit_layout.setOnClickListener(new OnClickListener() {  
        @Override  
        public void onClick(View v) {  
            
            String[] property_va = new String[] {"3", pageIndex + "","10","水果"};
            soapService.getNewsList3(property_va, false);
        }  
            });
    grain_layout = (LinearLayout) view.findViewById(R.id.grain_layout);
    grain_layout.setClickable(true);
    grain_layout.setOnClickListener(new OnClickListener() {  
        @Override  
        public void onClick(View v) {  
            
            String[] property_va = new String[] {"3", pageIndex + "","10","粮油作物"};
            soapService.getNewsList3(property_va, false);
        }  
            });
    seedling_layout = (LinearLayout) view.findViewById(R.id.seedling_layout);
    seedling_layout.setClickable(true); 
    seedling_layout.setOnClickListener(new OnClickListener() {  
        @Override  
        public void onClick(View v) {  
            
            String[] property_va = new String[] {"3", pageIndex + "","10","种苗"};
            soapService.getNewsList3(property_va, false);
        }  
            });
    more_layout = (LinearLayout) view.findViewById(R.id.more_layout);
    more_layout.setClickable(true);
    
}

private void initData() {
    list = new ArrayList<FarmNewsList>();
//    farmnews = new FarmNewsList();
    if(list.size() != 0){
        adapter = new FarmFruitListAdapter(context, list);
        listView.setAdapter(adapter);
        }
    String[] property_va = new String[] {"3", pageIndex + "","10"};
    soapService.getNewsList3(property_va, false);

}


private void setListeners() {
    listView_farmnewslist.setOnItemClickListener(new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            System.out.println("Click Towns");

            Intent intent = new Intent();
            intent.putExtra("newsurl", list.get(position-1).getUrl()); //将计算的值回传回去
            intent.putExtra("newsid", list.get(position-1).getNewsId()); //将计算的值回传回去
            intent.setClass(context, NewsInfo_Activity.class);
            startActivity(intent);

        }
    });
    listView_farmnewslist.setOnRefreshListener(new OnRefreshListener<ListView>() {

        @Override
        public void onRefresh(PullToRefreshBase<ListView> refreshView) {

            pageIndex = 1;
            String[] property_va = new String[] {"3", pageIndex + "","10"};
            soapService.getNewsList3(property_va, false);
        }

    });
    
    // end of list
    listView_farmnewslist.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

        @Override
        public void onLastItemVisible() {

                String[] property_va = new String[] { "3", ++pageIndex + "","10"};
                soapService.getNewsList3(property_va, true);

        }
    });
}

public void onEvent(SoapRes obj) {
   // TODO Auto-generated method stub
   if (obj.getCode().equals(SOAP_UTILS.METHOD.NEWSLIST3)) {
       listView_farmnewslist.onRefreshComplete();
       if (obj.getObj() != null) {
           try {
           JSONObject json_objs = new JSONObject(obj.getObj().toString());
  
           if (json_objs.get("result").toString().equals("true")) {
//               list.clear();
               JSONObject json_objdata = json_objs.getJSONObject("data");
               Integer pageNo = Integer.parseInt(json_objdata.getString("pageNo"));
               Integer last =  Integer.parseInt(json_objdata.getString("last"));

               if(pageNo == 1){
                   list.clear();
               }
               if(pageNo <= last){
               
               JSONArray json_arr = json_objdata.getJSONArray("list");
               for (int i = 0; i < json_arr.length(); i++) {
                   JSONObject json_obj= (JSONObject) json_arr.get(i);
                   FarmNewsList farm = new FarmNewsList();
                   farm.setNewsId(json_obj.getString("id"));
                   farm.setNewsName(json_obj.getString("title"));
                   farm.setUrl(json_obj.getString("url"));
                   farm.setType(json_obj.getString("type"));
                   farm.setContent(json_obj.getString("content"));
                   farm.setSource(json_obj.getString("source"));
                   farm.setViewCount(json_obj.getString("viewCount"));
                   farm.setHoursAgo(json_obj.getString("hoursAgo"));
                   farm.setImgUrl(json_obj.getString("imgUrl"));
                   
                   list.add(farm);
               }

               if (obj.isPage()) {
                   adapter.notifyDataSetChanged();
               } else {
                    if (list.size() != 0) {
                   
                        adapter = new FarmFruitListAdapter(context, list);
                        listView.setAdapter(adapter);
                    pageIndex = 1;
                    }else {
                           listView.setAdapter(null);
                       }
               }    
               }
            }
           } catch (JSONException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }

       }else{
           Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
       }
   }
}

}