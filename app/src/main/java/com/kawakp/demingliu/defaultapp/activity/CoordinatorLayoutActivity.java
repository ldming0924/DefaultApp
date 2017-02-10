package com.kawakp.demingliu.defaultapp.activity;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kawakp.demingliu.defaultapp.R;
import com.kawakp.demingliu.defaultapp.adapter.RecyclerViewAdapter;
import com.kawakp.demingliu.defaultapp.bean.WarmBean;
import com.kawakp.demingliu.defaultapp.decoration.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deming.liu on 2016/11/17.
 */

public class CoordinatorLayoutActivity extends Activity {

    RecyclerView listView;
    private List<WarmBean> list = new ArrayList<WarmBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        listView = (RecyclerView) findViewById(R.id.rc);
        for (int i=0;i<20;i++){
           WarmBean bean = new WarmBean();
            bean.setName(i+" aa");
            bean.setCreateDate("bb");
            list.add(bean);
        }
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(CoordinatorLayoutActivity.this,list);
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(CoordinatorLayoutActivity.this));
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        listView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
    }


}
