package com.kawakp.demingliu.defaultapp.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by deming.liu on 2016/10/10.
 */
public abstract class BaseFragment extends Fragment {

    protected View mRootView;

    protected Activity activity;

    private LayoutInflater inflater;

    // 标志位 标志已经初始化完成。
    protected boolean isPrepared;

    //标志位 fragment是否可见
    protected boolean isVisible;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        mRootView = inflater.inflate(setContentViewId(), container, false);
        ButterKnife.bind(this, mRootView);
        initView();

        return mRootView;
    }

    protected abstract int setContentViewId();
    protected abstract void initView();




    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }



    protected LayoutInflater getLayoutInflater()
    {

        return inflater;
    }

    protected View getParentView()
    {

        return mRootView;
    }

    /**
     * Fragment数据的懒加载
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {

        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint())
        {
            isVisible = true;
            onVisible();
        } else
        {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible()
    {

        lazyLoad();
    }

    protected abstract void lazyLoad();

    protected void onInvisible()
    {

    }

    public <T extends View> T $(int id)
    {

        return (T) mRootView.findViewById(id);
    }
}
