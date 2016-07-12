package com.peng3.big.big.myappbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SystembarActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnhide;
    private Button btnshow;
    private View decorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_systembar);


        btnhide = (Button) findViewById(R.id.btn_hide);
        btnshow = (Button) findViewById(R.id.btn_show);


        decorView = getWindow().getDecorView();
// Hide the status bar.

// Remember that you should never show the action bar if the
// status bar is hidden, so hide that too if necessary.


    }

    @Override
    public void onClick(View view) {

        if (view == btnhide) {

            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);

        } else if (view == btnshow) {
            int uiOptions = View.SYSTEM_UI_FLAG_VISIBLE;
            decorView.setSystemUiVisibility(uiOptions);

        }

    }
}
