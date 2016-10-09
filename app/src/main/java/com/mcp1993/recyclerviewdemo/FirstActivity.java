package com.mcp1993.recyclerviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by mcp1993 on 2016/10/9 0009.
 */

public class FirstActivity extends AppCompatActivity{
    private Button btn_1,btn_2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        btn_1= (Button) findViewById(R.id.btn_1);
        btn_2= (Button) findViewById(R.id.btn_2);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(FirstActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(FirstActivity.this,WaterFallActivity.class);
                startActivity(intent);
            }
        });

    }
}
