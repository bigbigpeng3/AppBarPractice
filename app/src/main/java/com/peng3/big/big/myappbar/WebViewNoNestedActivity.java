package com.peng3.big.big.myappbar;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class WebViewNoNestedActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView mTitle;

    private MyWebView mWebView;

    private NestedScrollView nestedview;

    private View mDecorView;
    private SystemBarTintManager tintManager;
    private boolean hasHideSystemUi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web_view_no_nested);

        mTitle = (TextView) findViewById(R.id.toolText);

        mWebView = (MyWebView) findViewById(R.id.web_view);

        nestedview = (NestedScrollView) findViewById(R.id.nestedview);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//        mDecorView = getWindow().getDecorView();
        initWindow();

//        toolbar.collapseActionView();

        nestedview.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY - oldScrollY > 0) {//下
                    //full(true);
//                    hideToolbarAndSystemUI();
                    hideToolbarAndSystemUi();
                } else {//上
//                    full(false);
//                    showSystemUI();
//                    showToolbarAndSystemUI();
                    showToolbarAndSystemUi();
                }
            }
        });

        //添加一个后退的按钮,太棒了,不过要在manifest中设置一下
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // 设置缓存模式
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDefaultTextEncodingName("UTF-8");

        mWebView.setWebViewClient(new WebViewClient() {
            // 网页开始加载
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                super.onPageStarted(view, url, favicon);
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
                super.onPageFinished(view, url);
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


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebView.setNestedScrollingEnabled(true);
        }

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

                //full(false);
//                toolbar.animate().translationY(0).setInterpolator(new AccelerateDecelerateInterpolator());
                //toolbar.animate().translationY(0);

                //showSystemUI();
                //showToolbarAndSystemUI();

            }

            @Override
            public void onDown() {
//                toolbar.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateDecelerateInterpolator());

                //toolbar.animate().translationY(-toolbar.getHeight());

                //full(true);
                //hideToolbarAndSystemUI();
                //hideToolbarAndSystemUI();
            }
        });

        mWebView.loadUrl("http://www.baidu.com");


    }

    @TargetApi(19)
    private void initWindow(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintColor(getResources().getColor(R.color.colorPrimary));
            tintManager.setStatusBarTintEnabled(true);
        }
        mDecorView = getWindow().getDecorView();
    }

    @TargetApi(19)
    public void hideSystemUI() {
        hasHideSystemUi = true;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (mDecorView != null){
            mDecorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                            | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }

        if (tintManager != null){
            tintManager.setStatusBarTintEnabled(false);
        }
    }

    @TargetApi(16)
    public void showSystemUI() {
        hasHideSystemUi = false;
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (mDecorView != null){
            mDecorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (tintManager != null){
            tintManager.setStatusBarTintEnabled(true);
        }
    }

    public boolean hasNavBar(){
        if (tintManager == null)
            return false;
        SystemBarTintManager.SystemBarConfig config = tintManager.getConfig();
        return config.hasNavigtionBar();
    }


    /**
     * @param enable false 显示，true 隐藏
     */
    private void full(boolean enable) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            WindowManager.LayoutParams p = this.getWindow().getAttributes();

            if (enable) {

                p.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;//|=：或等于，取其一
//                p.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;//|=：或等于，取其一

//                var uiOpts = uiOpts = SystemUiFlags.LayoutStable
//                        | SystemUiFlags.LayoutHideNavigation
//                        | SystemUiFlags.LayoutFullscreen
//                        | SystemUiFlags.Fullscreen
//                        | SystemUiFlags.HideNavigation
//                        | SystemUiFlags.Immersive;
//                Window.DecorView.SystemUiVisibility = uiOpts;

            } else {

                p.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);//&=：与等于，取其二同时满足， ~ ： 取反

            }

            getWindow().setAttributes(p);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

    }

    // This snippet hides the system bars.
    private void hideToolbarAndSystemUI1() {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        if (toolbar == null)
            return;

        ViewPropertyAnimator.animate(toolbar).translationY(-toolbar.getHeight())
                .setInterpolator(new AccelerateInterpolator(1))
                .setListener(new com.nineoldandroids.animation.Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(com.nineoldandroids.animation.Animator animation) {
                        hideSystemUI();
                    }

                    @Override
                    public void onAnimationEnd(com.nineoldandroids.animation.Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(com.nineoldandroids.animation.Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(com.nineoldandroids.animation.Animator animation) {

                    }
                });

    }

    // This snippet shows the system bars. It does this by removing all the flags
// except for the ones that make the content appear under the system bars.

    private void showToolbarAndSystemUI1() {

        if (toolbar == null) {
            return;
        }

        ViewPropertyAnimator.animate(toolbar).translationY(0)
                .setInterpolator(new DecelerateInterpolator(1))
                .setListener(new com.nineoldandroids.animation.Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(com.nineoldandroids.animation.Animator animation) {
                        showSystemUI();
                    }

                    @Override
                    public void onAnimationEnd(com.nineoldandroids.animation.Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(com.nineoldandroids.animation.Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(com.nineoldandroids.animation.Animator animation) {

                    }
                });



    }


    private void showSystemUI1() {

        mDecorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }


    private void hideSystemUI1() {

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mDecorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                            | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                            | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }


//        if (mDecorView != null){
//            mDecorView.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
//                            | View.SYSTEM_UI_FLAG_IMMERSIVE);
//        }

    }

    private void hideToolbarAndSystemUi() {
        if (toolbar == null)
            return;

        ViewPropertyAnimator.animate(toolbar).translationY(-toolbar.getHeight())
                .setInterpolator(new AccelerateInterpolator(1))
                .setListener(new SimpleAnimatorListener(){
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        hideSystemUI();
                    }
                });

    }

    private void showToolbarAndSystemUi() {
        if (toolbar == null)
            return;
        ViewPropertyAnimator.animate(toolbar).translationY(0)
                .setInterpolator(new DecelerateInterpolator(1))
                .setListener(new SimpleAnimatorListener(){
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        showSystemUI();
                    }
                });
    }


    public static class SimpleAnimatorListener implements Animator.AnimatorListener{
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {

        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }

}
