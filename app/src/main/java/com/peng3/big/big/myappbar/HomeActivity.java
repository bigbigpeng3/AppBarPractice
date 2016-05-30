package com.peng3.big.big.myappbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button buttonTest;

    private boolean isFull = false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        button1 = (Button) findViewById(R.id.btn_image1);
        button2 = (Button) findViewById(R.id.btn_image2);
        button3 = (Button) findViewById(R.id.btn_image3);
        button4 = (Button) findViewById(R.id.btn_image4);
        button5 = (Button) findViewById(R.id.btn_image5);
        buttonTest = (Button) findViewById(R.id.btn_test);


        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        buttonTest.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view == button1) {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (button2 == view) {
            Intent intent = new Intent(HomeActivity.this, Main2Activity.class);
            startActivity(intent);
        } else if (button3 == view) {
            Intent intent = new Intent(HomeActivity.this, WebViewActivity.class);
            startActivity(intent);
        } else if (button4 == view) {
            Intent intent = new Intent(HomeActivity.this, WebViewFullScreenActivity.class);
            startActivity(intent);

        } else if (button5 == view) {
            Intent intent = new Intent(HomeActivity.this, WebViewFullWithToolBarActivity.class);
            startActivity(intent);
        }else if (buttonTest == view) {

            Intent intent = new Intent(HomeActivity.this, WebViewNoNestedActivity.class);
            startActivity(intent);
        }
    }




}
