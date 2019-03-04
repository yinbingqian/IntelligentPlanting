package com.lnpdit.woofarm.page.activity.farmingmanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;
import org.json.JSONObject;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class OriginsScoreActivity extends BaseActivity implements OnClickListener {

	Context context;
	private Button cancel_bt;
	private Button send_bt;
    private ImageView star_click_img1,star_click_img2,star_click_img3,star_click_img4,star_click_img5;

    private String plantId = ""; 
    
	ArrayList<HashMap<String, Object>> remoteWindowItem = null;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		isParentActivity = false;

		setContentView(R.layout.activity_addnewscomment);
		
		 Intent intent = getIntent();
	     plantId = intent.getStringExtra("plantId");
	        
		WindowManager m = getWindowManager();
		Display d = m.getDefaultDisplay(); // 为获取屏幕宽、高

		LayoutParams p = getWindow().getAttributes(); // 获取对话框当前的参数值
		// p.height = (int) (d.getHeight() * 0.5); // 高度设置为屏幕的1.0
		p.height = (int) (d.getHeight() * 1.0); // 高度设置为屏幕的1.0
		p.width = (int) (d.getWidth() * 1.0); // 宽度设置为屏幕的0.8
		p.alpha = 1.0f; // 设置本身透明度
		p.dimAmount = 0.0f; // 设置黑暗度

		getWindow().setAttributes(p); // 设置生效
		getWindow().setGravity(Gravity.RIGHT);

		InitView();

		// 定时器
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				InputMethodManager m = (InputMethodManager) getWindow().getContext()
						.getSystemService(Context.INPUT_METHOD_SERVICE);
				m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
			}
		}, 300);

	}

	void InitView() {
		cancel_bt = (Button) this.findViewById(R.id.cancel_bt);
		send_bt = (Button) this.findViewById(R.id.send_bt);
		send_bt.setOnClickListener(this);

        cancel_bt = (Button) this.findViewById(R.id.cancel_bt);
        star_click_img1 = (ImageView) findViewById(R.id.star_click_img1);
        star_click_img1.setClickable(true);
        star_click_img2 = (ImageView) findViewById(R.id.star_click_img2);
        star_click_img2.setClickable(true);
        star_click_img3 = (ImageView) findViewById(R.id.star_click_img3);
        star_click_img3.setClickable(true);
        star_click_img4 = (ImageView) findViewById(R.id.star_click_img4);
        star_click_img4.setClickable(true);
        star_click_img5 = (ImageView) findViewById(R.id.star_click_img5);
        star_click_img5.setClickable(true);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

        case R.id.star_click_img1:
//            star_click_img1.setBackground();
            break;
		case R.id.cancel_bt:
			finish();
			break;
		case R.id.send_bt:

            String[] property_va = new String[] {plantId,"android","4"};
            soapService.getOriginsScore(property_va);

			break;
		default:
			break;
		}
		super.onClick(v);
	}
	
	 @Override
	   public void onEvent(Object obj) {
	       // TODO Auto-generated method stub
	       super.onEvent(obj);
	       SoapRes res = (SoapRes) obj;
	       if (res.getCode().equals(SOAP_UTILS.METHOD.ORIGINSSCORE)) {//溯源评分
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
