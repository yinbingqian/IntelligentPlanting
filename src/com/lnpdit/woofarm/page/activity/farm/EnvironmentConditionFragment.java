package com.lnpdit.woofarm.page.activity.farm;

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
import com.lnpdit.woofarm.utils.EventCache;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class EnvironmentConditionFragment extends Fragment {

	Context context;
	View view;
	/** soapService **/
	public ISoapService soapService = new SoapService();

	private EditText timestart_edit;
	private EditText timeend_edit;
    private EditText airtemmin_edit;
    private EditText airtemmax_edit;
    private EditText airhumiditymin_edit;
    private EditText airhumiditymmax_edit;
    private EditText soiltemmin_edit;
    private EditText soiltemmax_edit;
    private EditText soilhumiditymin_edit;
    private EditText soilhumiditymmax_edit;
    private EditText leaftemmin_edit;
    private EditText leaftemmax_edit;
    private EditText leafhumiditymin_edit;
    private EditText leafhumiditymmax_edit;
    private EditText soilecmin_edit;
    private EditText soilecmax_edit;
    private EditText co2concentrationmin_edit;
    private EditText co2concentrationmax_edit;
    private EditText illuminationmin_edit;
    private EditText illuminationmax_edit;
    private EditText parmin_edit;
    private EditText parmax_edit;
    private EditText soilphmin_edit;
    private EditText soilphmax_edit;

	private DBHelper dbh;
	public DemoApplication myApplication;
//	private static final String TAG = "SU-JPush";

	public EnvironmentConditionFragment() {
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

		view = inflater.inflate(R.layout.fragment_alarmday, container, false);

	    initView();
	    initData();
		return view;
	}

private void initView() {
    timestart_edit = (EditText) view.findViewById(R.id.timestart_edit);
    timeend_edit = (EditText) view.findViewById(R.id.timeend_edit);
    airtemmin_edit = (EditText) view.findViewById(R.id.airtemmin_edit);
    airtemmax_edit = (EditText) view.findViewById(R.id.airtemmax_edit);
    airhumiditymin_edit = (EditText) view.findViewById(R.id.airhumiditymin_edit);
    airhumiditymmax_edit = (EditText) view.findViewById(R.id.airhumiditymmax_edit);
    soiltemmin_edit = (EditText) view.findViewById(R.id.soiltemmin_edit);
    soiltemmax_edit = (EditText) view.findViewById(R.id.soiltemmax_edit);
    soilhumiditymin_edit = (EditText) view.findViewById(R.id.soilhumiditymin_edit);
    soilhumiditymmax_edit = (EditText) view.findViewById(R.id.soilhumiditymmax_edit);
    leaftemmin_edit = (EditText) view.findViewById(R.id.leaftemmin_edit);
    leaftemmax_edit = (EditText) view.findViewById(R.id.leaftemmax_edit);
    leafhumiditymin_edit = (EditText) view.findViewById(R.id.leafhumiditymin_edit);
    leafhumiditymmax_edit = (EditText) view.findViewById(R.id.leafhumiditymmax_edit);
    soilecmin_edit = (EditText) view.findViewById(R.id.soilecmin_edit);
    soilecmax_edit = (EditText) view.findViewById(R.id.soilecmax_edit);
    co2concentrationmin_edit = (EditText) view.findViewById(R.id.co2concentrationmin_edit);
    co2concentrationmax_edit = (EditText) view.findViewById(R.id.co2concentrationmax_edit);
    illuminationmin_edit = (EditText) view.findViewById(R.id.illuminationmin_edit);
    illuminationmax_edit = (EditText) view.findViewById(R.id.illuminationmax_edit);
    parmin_edit = (EditText) view.findViewById(R.id.parmin_edit);
    parmax_edit = (EditText) view.findViewById(R.id.parmax_edit);
    soilphmin_edit = (EditText) view.findViewById(R.id.soilphmin_edit);
    soilphmax_edit = (EditText) view.findViewById(R.id.soilphmax_edit);
  
}

private void initData() {
    
//    for(int i = 0;i<10;i++){
//        farmnews.setNewsName("互联网企业加快布局 科技农业商业应用提速");
//        farmnews.setNewsCount("143");
//        farmnews.setNewsTime("2天前");
//        farmnews.setAvatar("");
//        list.add(farmnews);
//    
//    }
   
//    SharedPreferences sharedPreferences = getSharedPreferences("userinfo",Activity.MODE_PRIVATE);  
//    String userCode = sharedPreferences.getString("userCode","");  
//    String[] property_va = new String[] {userCode };
//    soapService.farmListData(property_va);
}


//public void onEvent(SoapRes obj) {
//   // TODO Auto-generated method stub
//   if (obj.getCode().equals(SOAP_UTILS.METHOD.FARMLISTDATA)) {
//       if (obj.getObj() != null) {
//           try {
//           JSONObject json_objs = new JSONObject(obj.getObj().toString());
//  
//           if (json_objs.get("result").toString().equals("true")) {
//               list.clear();
//               JSONArray json_arr = json_objs.getJSONArray("data");
//               for (int i = 0; i < json_arr.length(); i++) {
//                   JSONObject json_obj= (JSONObject) json_arr.get(i);
//                   FarmNewsList farm = new FarmNewsList();
////                   farm.setId(json_obj.getString("id"));
////                   farm.setFarmArea(json_obj.getString("farmArea"));
////                   farm.setUserCode(json_obj.getString("userCode"));
////                   farm.setFarmName(json_obj.getString("farmName"));
////                   farm.setFarmCode(json_obj.getString("farmCode"));
//                   list.add(farm);
//               }
//
//               if(list.size()!=0){
//
//                   adapter = new FarmFruitListAdapter(context, list);
//                   listView_farmnewslist.setAdapter(adapter);
//               }else{
//                   listView_farmnewslist.setAdapter(null); 
//               }
//            }
//           } catch (JSONException e) {
//               // TODO Auto-generated catch block
//               e.printStackTrace();
//           }
//
//       }else{
//           Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
//       }
//   }
//}

}