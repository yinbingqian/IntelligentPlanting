package com.lnpdit.woofarm.page.activity.farm;

import org.json.JSONException;
import org.json.JSONObject;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.page.activity.ztiany2011.simplezxing.CaptureActivity;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FarmAddAdviceSubmitActivity extends BaseActivity implements OnClickListener {
    /** Called when the activity is first created. */

//    private DBHelper dbh;
//    private FarmList farm;
//    private List<FarmList> farmList = new ArrayList<FarmList>();
//    private ListView listview_farmlist;
//    private FarmListAdapter farmListAdapter;
    private TextView tv_back,adddevice_tv;
    private EditText devicenumber_et;
    Context context;
    private String equipCode = "";
    private String equipName = "";
    private String pengCode = "";
    private String sensors = "";
    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_adddevicesubmit);

        initView();

        Intent intent = getIntent();
        equipCode = intent.getStringExtra("equipCode");
        pengCode = intent.getStringExtra("pengCode");
        
//        initData();
//        setListeners();
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setClickable(true);
        tv_back.setOnClickListener(this);
        devicenumber_et = (EditText) findViewById(R.id.devicenumber_et);//输入设备编号
        adddevice_tv = (TextView) findViewById(R.id.adddevice_tv);//添加设备按钮
        adddevice_tv.setClickable(true);
        adddevice_tv.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.tv_back:
            finish();
            break;
        case R.id.adddevice_tv:

            String[] property_va = new String[] {equipCode,devicenumber_et.getText().toString(),pengCode,"01;02;03;04;05" };
            soapService.equipmentBind(property_va);
            
            break;
        default:
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String result = data.getStringExtra("Strings");
            devicenumber_et.setText(result);
        }
    }

   @Override
   public void onEvent(Object obj) {
       // TODO Auto-generated method stub
       super.onEvent(obj);
       SoapRes res = (SoapRes) obj;
       if (res.getCode().equals(SOAP_UTILS.METHOD.EQUIPMENTBIND)) {//我的农场传感器数据
    if (res.getObj() != null) {
        try {

            JSONObject json_objs = new JSONObject(res.getObj().toString());
            if (json_objs.get("result").toString().equals("true")) {
            String message = json_objs.get("message").toString();
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                finish();
                
            }else{

                String message = json_objs.get("message").toString();
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
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