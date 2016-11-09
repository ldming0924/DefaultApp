package com.kawakp.demingliu.defaultapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kawakp.demingliu.defaultapp.R;
import com.kawakp.demingliu.defaultapp.widget.systembar.StatusBarUtil;

import butterknife.ButterKnife;

/**
 * Created by deming.liu on 2016/10/10.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置布局内容
        setContentView(setContentViewId());
        setStatusBar(); //状态栏
        //初始化黄油刀控件
        ButterKnife.bind(this);
        //初始化控件
        initViews(savedInstanceState);
    }
    public abstract int setContentViewId();

    public abstract void initViews(Bundle savedInstanceState);

    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);

    }


    /**
     * activity的跳转带动画
     * 0   淡入淡出效果
     * 1  放大淡出效果
     * 2  转动淡出效果
     * 3  转动淡出效果2
     * 4  左上角展开淡出效果
     * 5  压缩变小淡出
     * 6  右往左推出
     * 7  下往上推出
     * 8  左右交错
     * 9  放大淡出效果
     * 10  缩小效果
     * 11  上下交错
     * 12   从左往右
     * 13  从上往下
     **/
    public void startAnimToActivity(Context context, Class<?> cls, int type){
        Intent intent=new Intent();
        intent.setClass(context, cls);
        startActivity(intent);
        switch (type) {
            case 0:
                overridePendingTransition(R.anim.fade, R.anim.hold);
                break;
            case 1:
                overridePendingTransition(R.anim.my_scale_action,
                        R.anim.my_alpha_action);
                break;
            case 2:
                overridePendingTransition(R.anim.scale_rotate,
                        R.anim.my_alpha_action);
                break;
            case 3:
                overridePendingTransition(R.anim.scale_translate_rotate,
                        R.anim.my_alpha_action);
                break;
            case 4:
                overridePendingTransition(R.anim.scale_translate,
                        R.anim.my_alpha_action);
                break;
            case 5:
                overridePendingTransition(R.anim.hyperspace_in,
                        R.anim.hyperspace_out);
                break;
            case 6:
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case 7:
                overridePendingTransition(R.anim.push_up_in,
                        R.anim.push_up_out);
                break;
            case 8:
                overridePendingTransition(R.anim.slide_left,
                        R.anim.slide_right);
                break;
            case 9:
                overridePendingTransition(R.anim.wave_scale,
                        R.anim.my_alpha_action);
                break;
            case 10:
                overridePendingTransition(R.anim.zoom_enter,
                        R.anim.zoom_exit);
                break;
            case 11:
                overridePendingTransition(R.anim.slide_up_in,
                        R.anim.slide_down_out);
                break;
            case 12:
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            case 13:
                overridePendingTransition(R.anim.push_up_in,
                        R.anim.push_bottom_out);
                break;
        }
    }

}
