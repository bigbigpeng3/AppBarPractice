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

public class WebViewFullScreenActivity extends AppCompatActivity{

    private Toolbar toolbar;
    private TextView mTitle;

    private MyWebView mWebView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_full);

        mWebView = (MyWebView) findViewById(R.id.web_view);




        // 设置缓存模式
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDefaultTextEncodingName("UTF-8");



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
                Toast.makeText(WebViewFullScreenActivity.this, "请检查您的网络设置", Toast.LENGTH_SHORT).show();
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

        //这个类不需要监听

        mWebView.setOnScrollChangeListener(new MyWebView.OnMyScrollChangeListener() {
            @Override
            public void onPageEnd(int l, int t, int oldl, int oldt) {

            }

            @Override
            public void onPageTop(int l, int t, int oldl, int oldt) {

            }

            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {

            }

            @Override
            public void onUp() {

            }

            @Override
            public void onDown() {

            }
        });

        mWebView.loadUrl("http://www.baidu.com");

    }

}
