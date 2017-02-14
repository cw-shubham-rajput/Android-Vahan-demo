package com.example.shubham.vahan_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by shubh on 1/31/2017.
 */

public class dialog_activity extends AppCompatActivity {

    private String msg;
    public static TextView tv1;
    private Button btn_dismiss;
    private RelativeLayout relativeLayout;
    public static boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_activity);
        this.setFinishOnTouchOutside(false);

        Intent intent = getIntent();
        msg = intent.getStringExtra("msg");

        tv1 = (TextView)findViewById(R.id.tv1);
        btn_dismiss = (Button)findViewById(R.id.btn_dismiss);
        relativeLayout = (RelativeLayout)findViewById(R.id.activity_dialog_activity);

        if(!flag){
            tv1.setText(msg);
            flag = true;
            //Toast.makeText(this, "Not Active", Toast.LENGTH_SHORT).show();
        }
        btn_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = false;
                finish();
            }
        });
    }
}
