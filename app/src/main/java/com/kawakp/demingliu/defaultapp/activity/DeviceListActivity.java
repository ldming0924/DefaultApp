package com.kawakp.demingliu.defaultapp.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.kawakp.demingliu.defaultapp.R;
import com.kawakp.demingliu.defaultapp.bean.DeviceListBean;
import com.kawakp.demingliu.defaultapp.http.OkHttpHelper;
import com.kawakp.demingliu.defaultapp.http.SpotsCallBack;
import com.kawakp.demingliu.defaultapp.tree.bean.Bean;
import com.kawakp.demingliu.defaultapp.tree.bean.Node;
import com.kawakp.demingliu.defaultapp.tree.bean.TreeListViewAdapter;
import com.kawakp.demingliu.defaultapp.tree.tree_view.SimpleTreeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Response;

/**
 * Created by deming.liu on 2016/10/28.
 */
public class DeviceListActivity extends BaseActivity {

    @Bind(R.id.id_tree)
    ListView idTree;
    private TreeListViewAdapter mAdapter;
    private List<Bean> mDatas = new ArrayList<Bean>();
    private OkHttpHelper okHttpHelper;
    List<DeviceListBean> totallist = new ArrayList<>();

    @Override
    public int setContentViewId() {
        return R.layout.device;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

        initDatas();


    }
    private void initDatas() {
        /*mDatas.add(new Bean("1", "0", "根目录1"));
        mDatas.add(new Bean("2", "0", "根目录2"));
        mDatas.add(new Bean("3", "0", "根目录3"));
        mDatas.add(new Bean("4", "0", "根目录4"));
        mDatas.add(new Bean("5", "1", "子目录1-1"));
        mDatas.add(new Bean("6", "1", "子目录1-2"));

        mDatas.add(new Bean("7", "5", "子目录1-1-1"));
        mDatas.add(new Bean("8", "2", "子目录2-1"));

        mDatas.add(new Bean("9", "4", "子目录4-1"));
        mDatas.add(new Bean("10", "4", "子目录4-2"));

        mDatas.add(new Bean("11", "10", "子目录4-2-1"));
        mDatas.add(new Bean("12", "10", "子目录4-2-3"));
        mDatas.add(new Bean("13", "10", "子目录4-2-2"));
        mDatas.add(new Bean("14", "9", "子目录4-1-1"));
        mDatas.add(new Bean("15", "9", "子目录4-1-2"));
        mDatas.add(new Bean("16", "9", "子目录4-1-3"));
        mDatas.add(new Bean("16", "9", "子目录4-1-3"));*/


        okHttpHelper = OkHttpHelper.getInstance(DeviceListActivity.this);
        String url = "http://kawakp.chinclouds.com:60034/userconsle/orgs";
        okHttpHelper.get(url, new SpotsCallBack<String>(DeviceListActivity.this) {
                    @Override
                    public void onSuccess(Response response, String s) {

                        JSONArray array = JSON.parseArray(s);
                        List<DeviceListBean> list = JSON.parseArray(array.toString(),DeviceListBean.class);
                        //totallist.addAll(list);
                        for (int i = 0;i<list.size();i++){
                            mDatas.add(new Bean(list.get(i).getId(),list.get(i).getParentId(),list.get(i).getName()));
                            Log.d("TAG",list.get(i).getId()+" "+ list.get(i).getParentId()+"  "+list.get(i).getName()+"  "+mDatas.size());
                        }

                        try
                        {
                            Log.d("TAG",mDatas.size()+" aaaa");
                            mAdapter = new SimpleTreeAdapter<Bean>(idTree, DeviceListActivity.this, mDatas, 10);
                            //mAdapter = new SimpleTreeAdapter<FileBean>(mTree, this, mDatas2, 0); //传0表示不张开，10表示张开
                            mAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener()
                            {
                                @Override
                                public void onClick(Node node, int position)
                                {
                                    if (node.isLeaf())
                                    {
                                        Toast.makeText(getApplicationContext(), node.getName(),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }

                            });

                        } catch (Exception e)
                        {
                            e.printStackTrace();
                            Log.d("TAG",e.toString());
                        }


                        idTree.setAdapter(mAdapter);

                    }

                    @Override
                    public void onError(Response response, int code, Exception e) {
                        Log.e("ERR", e.toString());
                    }
                }
        );


    }

}
