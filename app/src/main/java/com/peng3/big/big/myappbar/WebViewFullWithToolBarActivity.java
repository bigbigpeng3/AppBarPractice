package com.peng3.big.big.myappbar;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class WebViewFullWithToolBarActivity extends AppCompatActivity{

    private Toolbar toolbar;
    private TextView mTitle;

    private WebView mWebView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_full_tool);

//        StatusbarUtils.from(this)
//                //沉浸状态栏
//                .setTransparentStatusbar(true)
//                //白底黑字状态栏
//                .setLightStatusBar(true)
//                //设置toolbar,actionbar等view
//                .setActionbarView(mNavigationBar)
//                .process();


        mTitle = (TextView) findViewById(R.id.toolText);

        mWebView = (WebView) findViewById(R.id.web_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        //添加一个后退的按钮,太棒了,不过要在manifest中设置一下
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//
//        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
//
//        appBarLayout.addOnOffsetChangedListener(this);



//        CollapsingToolbarLayout collapsingToolbar =
//                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//
//        collapsingToolbar.setTitle("WebView");



        // 设置缓存模式
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDefaultTextEncodingName("UTF-8");


        //mWebView.setBackgroundColor(BaseApplication.mAppContext.getResources().getColor(R.color.color_app_top));
        //设置为中等的字体大小,注意!需要从sharedPreference里面获取到值
        //setTextSize();

        //mWebView.addJavascriptInterface(new JavaScriptObject(this), "myObj");
        //载入js
        //mWebView.addJavascriptInterface(new JavascriptInterface(this), "imagelistner");

        mWebView.setWebViewClient(new WebViewClient() {
            // 网页开始加载
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                //view.getSettings().setJavaScriptEnabled(true);

                super.onPageStarted(view, url, favicon);
                //pbLoading.setVisibility(View.VISIBLE);
            }

            // 网页跳转
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;

            }

            // 网页加载结束
            @Override
            public void onPageFinished(WebView view, String url) {

                //view.getSettings().setJavaScriptEnabled(true);

                super.onPageFinished(view, url);

                // html加载完成之后，添加监听图片的点击js函数
                //addImageClickListner();

                //pbLoading.setVisibility(View.GONE);
            }


            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                //出现页面错误的时候，不让webView显示了。同时跳出一个错误Toast
                mWebView.setVisibility(View.INVISIBLE);
                Toast.makeText(WebViewFullWithToolBarActivity.this, "请检查您的网络设置", Toast.LENGTH_SHORT).show();
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            // 加载进度回调
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }

            // 网页标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);

            }
        });

        mWebView.loadUrl("http://www.baidu.com");

    }

}
