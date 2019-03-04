package com.lnpdit.woofarm.page.activity.farm;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.page.activity.ztiany2011.simplezxing.CaptureActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class FarmAddAdviceActivity extends BaseActivity implements OnClickListener {
    /** Called when the activity is first created. */

//    private DBHelper dbh;
//    private FarmList farm;
//    private List<FarmList> farmList = new ArrayList<FarmList>();
//    private ListView listview_farmlist;
//    private FarmListAdapter farmListAdapter;
    private TextView tv_back,adddevice_tv;
    private ImageView scan_img;
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
        setContentView(R.layout.activity_adddevice);

        initView();

        Intent intent = getIntent();
        pengCode = intent.getStringExtra("pengCode");
        
//        initData();
//        setListeners();
    }

    private void initView() {
        scan_img = (ImageView) findViewById(R.id.scan_img);//扫一扫按钮
        scan_img.setClickable(true);
        scan_img.setOnClickListener(this);
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
        case R.id.scan_img:
            
            startActivityForResult(new Intent(FarmAddAdviceActivity.this, CaptureActivity.class), 1);
            
            break;
        case R.id.adddevice_tv:
            
            Intent intent = new Intent();
            intent.putExtra("pengCode", pengCode);
            intent.putExtra("equipCode", devicenumber_et.getText().toString().toUpperCase());//.toUpperCase()小写变大写
            intent.setClass(context, FarmAddAdviceSubmitActivity.class);
            startActivity(intent); 
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

}