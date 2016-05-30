package com.peng3.big.big.myappbar;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{

    private Toolbar toolbar;
    private TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mTitle = (TextView) findViewById(R.id.toolText);

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);

        appBarLayout.addOnOffsetChangedListener(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //添加一个后退的按钮,太棒了,不过要在manifest中设置一下
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        //collapsingToolbar.setTitle("zhangpeng");


    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

        if (toolbar.getHeight() < -verticalOffset) {
            mTitle.setText("大于标题高度");
        } else {
            mTitle.setText("小于标题高度");
        }

//        if (-verticalOffset > (appBarLayout.getHeight()-2*mToolbar.getHeight())) {
//            ViewCompat.setAlpha(mLayout, 1);
//        }else {
//            ViewCompat.setAlpha(mLayout, 0);
//        }


//        ViewCompat.setAlpha(mLayout, (-verticalOffset + mToolbar.getHeight()) / (float) appBarLayout.getHeight());

//        System.out.println(verticalOffset);
//        System.out.println(appBarLayout.getHeight());
//        System.out.println(mToolbar.getHeight());
    }
}
