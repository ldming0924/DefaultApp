package com.kawakp.demingliu.defaultapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.kawakp.demingliu.defaultapp.R;
import com.kawakp.demingliu.defaultapp.widget.panningview.PanningView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by deming.liu on 2017/2/10.
 */

public class TestPanningViewActivity extends Activity {
    @Bind(R.id.iv_login_bg)
    PanningView m_ivLoginBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pan);
        ButterKnife.bind(this);
    }



    @Override
    protected void onResume() {
        super.onResume();
        m_ivLoginBg.startPanning();
    }

    @Override
    protected void onPause() {
        super.onPause();
        m_ivLoginBg.stopPanning();
    }
}
