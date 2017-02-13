package com.kawakp.demingliu.defaultapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kawakp.demingliu.defaultapp.R;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {




    @Bind(R.id.button1)
    Button b1;

    @Bind(R.id.button2)
    Button b2;

    @Bind(R.id.button3)
    Button b3;
    @Bind(R.id.button4)
    Button b4;
    @Bind(R.id.button5)
    Button b5;
    @Bind(R.id.button6)
    Button b6;



    @Override
    public int setContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {


        init();
    }


    private void init() {
    }
    @OnClick({R.id.button1,R.id.button2,R.id.button3,R.id.button4,R.id.button5,R.id.button6,R.id.button7,R.id.button8,R.id.button9,R.id.button10,R.id.button11})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.button1:
                startActivity(new Intent(MainActivity.this,SlidingMenuActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(MainActivity.this,WXactivity.class));
                break;
            case R.id.button3:
                startActivity(new Intent(MainActivity.this,TreeMenuActivity.class));
                break;
            case R.id.button4:
                startActivity(new Intent(MainActivity.this,CoordinatorLayoutActivity.class));
                break;
            case R.id.button5:
                startActivity(new Intent(MainActivity.this,ReactFloatActivity.class));
                break;
            case R.id.button6:
                startActivity(new Intent(MainActivity.this,AnimaActivity.class));
                break;
            case R.id.button7:
                startActivity(new Intent(MainActivity.this,TestPanningViewActivity.class));
                break;
            case R.id.button8:
                startActivity(new Intent(MainActivity.this,FlowLayoutActivity.class));
                break;
            case R.id.button9:
                startActivity(new Intent(MainActivity.this,SpringScrollViewActivity.class));
                break;
            case R.id.button10:
                startActivity(new Intent(MainActivity.this,TestOkhttpActivity.class));
                break;
            case R.id.button11:
                startActivity(new Intent(MainActivity.this,ArcMenuActivity.class));
                break;
        }
    }





}
