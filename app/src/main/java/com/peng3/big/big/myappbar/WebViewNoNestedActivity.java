package com.peng3.big.big.myappbar;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class WebViewNoNestedActivity extends AppCompatActivity{

    private Toolbar toolbar;
    private TextView mTitle;

    private MyWebView mWebView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web_view_no_nested);

        mTitle = (TextView) findViewById(R.id.toolText);

        mWebView = (MyWebView) findViewById(R.id.web_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.collapseActionView ();


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
                Toast.makeText(WebViewNoNestedActivity.this, "请检查您的网络设置", Toast.LENGTH_SHORT).show();
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

                full(false);
//                toolbar.animate().translationY(0).setInterpolator(new AccelerateDecelerateInterpolator());
                //toolbar.animate().translationY(0);

            }

            @Override
            public void onDown() {
//                toolbar.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateDecelerateInterpolator());

                //toolbar.animate().translationY(-toolbar.getHeight());

                full(true);

            }
        });


        mWebView.loadUrl("http://www.baidu.com");

    }


    /**
     * @param enable false 显示，true 隐藏
     */
    private void full(boolean enable) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            WindowManager.LayoutParams p = this.getWindow().getAttributes();
            if (enable) {
                p.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;//|=：或等于，取其一
            } else {
                p.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);//&=：与等于，取其二同时满足， ~ ： 取反
            }

            getWindow().setAttributes(p);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

    }

}
