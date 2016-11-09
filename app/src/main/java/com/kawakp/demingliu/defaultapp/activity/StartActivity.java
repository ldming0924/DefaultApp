package com.kawakp.demingliu.defaultapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;


import com.kawakp.demingliu.defaultapp.R;
import com.kawakp.demingliu.defaultapp.http.OkHttpHelper;
import com.kawakp.demingliu.defaultapp.http.SpotsCallBack;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Response;

/**
 * Created by deming.liu on 2016/9/26.
 */
public class StartActivity extends Activity {

    private OkHttpHelper httpHelper;

    private ImageView btn;
    int scaleTime = 0;
    boolean isFlash;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        public void run() {
            handler.postDelayed(this, 400);
            if (!isFlash){
                scaleTime++;
                if(scaleTime%2==1){
                    scaleBlg(btn,400);
                }else{
                    scaleSmall(btn,400);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_start);
       // StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));

        btn = (ImageView) findViewById(R.id.btn);


        httpHelper = OkHttpHelper.getInstance(this);

        flash(true);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flash(false);
                isFlash = true;

                String url = "http://kawakp.chinclouds.com:60034/userconsle/login";
                String userName = "jnadmin";
                String pwd = "chin2016";
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("username",userName);
                map.put("password",pwd);
                httpHelper.post(url, map, new SpotsCallBack<String>(StartActivity.this) {

                    @Override
                    public void onSuccess(Response response, String s) {
                      //  Log.d("TAG",s);
                        startActivity(new Intent(StartActivity.this,MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onError(Response response, int code, Exception e) {
                        //  LogUtils.e(code +"　　"+ e.toString());
                        dismissDialog();
                    }
                });
            }
        });

      /*  new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(StartActivity.this,MainActivity.class));
                finish();
            }
        },2000);*/
    }

    public void flash(boolean flash){
        if (flash){
            handler.removeCallbacks(runnable);
            handler.postDelayed(runnable, 400);
        }
    }

    private void scaleSmall(ImageView view,int time){
        ScaleAnimation sa = new ScaleAnimation(1.0f, 0.97f, 1.0f, 0.9f,
                // 设置锚点
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        // 显示时间
        sa.setDuration(time);
        // 界面停留在结束状态
        sa.setFillAfter(true);
        view.startAnimation(sa);
    }
    private void scaleBlg(ImageView view, int time){
        ScaleAnimation sa = new ScaleAnimation(0.97f, 1.0f, 0.9f, 1.0f,
                // 设置锚点
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        // 显示时间
        sa.setDuration(time);
        // 界面停留在结束状态
        sa.setFillAfter(true);
        view.startAnimation(sa);
    }
}
