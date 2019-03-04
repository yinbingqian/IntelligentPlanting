package com.lnpdit.woofarm.page.activity.farmservice;


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
 * 病虫害详情
 * 
 *
 */
public class PesticidesInfoActivity extends BaseActivity {
    Context context;
	private TextView tv_back,tv_title;
	private ProgressWebView webView;
	private String urldetails;
    private String title;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_pesticidesinfo);
		super.onCreate(savedInstanceState);
		this.isParentActivity = false;
		context = this;
		intent = getIntent();
		urldetails = intent.getStringExtra("urldetails");
        title = intent.getStringExtra("title");
		
		initView();
		setListeners();
		setUI();
		
		
		
//		JC jzVideoPlayerStandard = (JZVideoPlayerStandard) findViewById(R.id.videoplayer);
//		jzVideoPlayerStandard.setUp("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4", 
//		                            JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, 
//		                            "饺子闭眼睛");
//		jzVideoPlayerStandard.thumbImageView.setImage("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640");
	}

	private void initView() {
	    tv_back = (TextView) findViewById(R.id.tv_back);
	    tv_title =  (TextView) findViewById(R.id.tv_title);
	    tv_title.setText(title);
		webView = (ProgressWebView) findViewById(R.id.webView);

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
         return false;
         }
         });

	    webView.loadUrl("http://source.nongguanjia.com/F0A0051.mp4");
//        webView.loadUrl("http://192.168.1.196:8980/zhzz/static/yingshi/demo.html");
	    
//		webView.loadData(getHtmlData(urldetails), "text/html", "UTF-8");
//        webView.loadData(urldetails, "text/html", "UTF-8");
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

}
