package com.kawakp.demingliu.defaultapp.fragment;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kawakp.demingliu.defaultapp.R;

/**
 * Created by deming.liu on 2016/10/10.
 */
public class HomePageFragment extends BaseFragment {

    public static HomePageFragment newInstance() {

        return new HomePageFragment();
    }


    @Override
    protected int setContentViewId() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void lazyLoad() {

    }
}
