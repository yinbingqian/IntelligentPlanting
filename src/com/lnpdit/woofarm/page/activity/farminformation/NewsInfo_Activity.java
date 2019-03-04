package com.lnpdit.woofarm.page.activity.farminformation;


import org.json.JSONException;
import org.json.JSONObject;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.customview.ProgressWebView;
import com.lnpdit.woofarm.http.SoapRes;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 新闻详情
 * 
 * @author huanyu 类名称：NewsInfo_Activity 创建时间:2014-10-29 下午3:18:47
 */
public class NewsInfo_Activity extends BaseActivity {
    Context context;
	private TextView tv_back;
	private ProgressWebView webView;
	private String newsurl = "";
    private String newsid = "";
    private String newscontent = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_newsinfo);
		super.onCreate(savedInstanceState);
		this.isParentActivity = false;
		context = this;
		intent = getIntent();
		newsurl = intent.getStringExtra("newsurl");
		newsid = intent.getStringExtra("newsid");
		
		
		initView();
		setListeners();
	
	}

	private void initView() {
	    tv_back = (TextView) findViewById(R.id.tv_back);
		webView = (ProgressWebView) findViewById(R.id.webView);
//		tv_title = (FocusedtrueTV) findViewById(R.id.tv_title);
//		tv_title.setText(newsList.getTitle());
		
	    String[] property_va = new String[] {newsid};
	    soapService.getNewsUpdatecount(property_va);
	}

	private void setListeners() {
	    tv_back.setOnClickListener(this);
	}

	public void setUI() {
//		webView.loadDataWithBaseURL(null, newsurl, "text/html", "utf-8", null);
//        webView.loadUrl("http://backlogin.guzhang.tv/agreement/index.html");
//		webView.loadUrl(newsurl); 
	    
	    
	    webView.getSettings().setBuiltInZoomControls(true);
	    webView.getSettings().setSupportZoom(true);
	    webView.getSettings().setUseWideViewPort(true);
	    webView.getSettings().setJavaScriptEnabled(true);
//	    webView.setDownloadListener(new DownloadListener() {
//            @Override
//            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,
//                    long contentLength) {
//                if (url != null && url.startsWith("http://"))
////                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.1.196:8980/zhzz/static/yingshi/demo.html")));
//            }
//        });
        
        //复写WebViewClient的shouldOverrideUrlLoading()的方法
        //如果需要事件处理返回false,否则返回true.这样就可以解决问题了(解决点击webview中的连接不用浏览器打开问题)
	    webView.setWebViewClient(new WebViewClient() {
         public boolean shouldOverrideUrlLoading(WebView view, String url) {
         view.loadUrl(url);
//         view.loadUrl("http://192.168.1.196:8980/zhzz/static/yingshi/demo.html");
         return false;
         }
         });

//	    webView.loadUrl(newsurl);
//        webView.loadUrl("http://192.168.1.196:8980/zhzz/static/yingshi/demo.html");
	    if(!newscontent.equals("")){

//	        webView.loadData(newscontent, "text/html", "UTF-8");
            webView.loadData(getHtmlData(newscontent), "text/html", "UTF-8");
	    }
	}

	private String getHtmlData(String bodyHTML) {
	    String head = "<head>" +
	            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
	            "<style>img{max-width: 100%; width:auto; height:auto;}</style>" +
	            "</head>";
	    return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_back:
			finish();
			break;
		default:
			break;
		}
	}

	public void onEvent(SoapRes obj) {
	    // TODO Auto-generated method stub
	    if (obj.getCode().equals(SOAP_UTILS.METHOD.NEWSLISTUPDATE)) {
	        if (obj.getObj() != null) {
	            try {
	            JSONObject json_objs = new JSONObject(obj.getObj().toString());
	   
	            if (json_objs.get("result").toString().equals("true")) {
//	                  list.clear();
	                  JSONObject json_objdata = json_objs.getJSONObject("data");
	                newscontent = json_objdata.getString("content");
	                setUI();
//	                Toast.makeText(context, "点击次数增加成功", Toast.LENGTH_SHORT).show();
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
	
	@Override
	protected void onResume() {
		super.onResume();
        webView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
        webView.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
