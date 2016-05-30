package com.peng3.big.big.myappbar;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebView;

/**
 * Created by zp on 16/5/28.
 */
public class MyWebView extends WebView {


    public OnMyScrollChangeListener listener;


    public MyWebView(Context context) {
        super(context);
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {//t就是x轴,l是y轴

        super.onScrollChanged(l, t, oldl, oldt);

        float webcontent = getContentHeight() * getScale();// webview的高度
        float webnow = getHeight() + getScrollY();// 当前webview的高度
        //Log.i("TAG1", "webview.getScrollY()====>>" + getScrollY());
        if (Math.abs(webcontent - webnow) < 1) {
            // 已经处于底端
             Log.i("TAG1", "已经处于底端");
            listener.onPageEnd(l, t, oldl, oldt);
        } else if (getScrollY() == 0) {
             Log.i("TAG1", "已经处于顶端");
            listener.onPageTop(l, t, oldl, oldt);
        } else {
            listener.onScrollChanged(l, t, oldl, oldt);
        }

//        if (l - oldl > 0){
//            Log.i("TAG1", "l - oldl > 0 ....." );
//        }


        //这是X轴
        if (t - oldt > 0){
            Log.i("TAG1", "t - oldt > 0 ....." );
            listener.onDown();
        } else {
            listener.onUp();
        }

    }

    public void setOnScrollChangeListener(OnMyScrollChangeListener listener) {

        this.listener = listener;

    }

    public interface OnMyScrollChangeListener {
        public void onPageEnd(int l, int t, int oldl, int oldt);
        public void onPageTop(int l, int t, int oldl, int oldt);
        public void onScrollChanged(int l, int t, int oldl, int oldt);

        public void onUp();
        public void onDown();

    }


}
