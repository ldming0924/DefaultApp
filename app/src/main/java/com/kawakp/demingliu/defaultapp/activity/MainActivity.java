package com.kawakp.demingliu.defaultapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.kawakp.demingliu.defaultapp.R;
import com.kawakp.demingliu.defaultapp.adapter.TestAdapter;
import com.kawakp.demingliu.defaultapp.bean.MateralBean;
import com.kawakp.demingliu.defaultapp.bean.WarmBean;
import com.kawakp.demingliu.defaultapp.decoration.DividerItemDecoration;
import com.kawakp.demingliu.defaultapp.http.OkHttpHelper;
import com.kawakp.demingliu.defaultapp.http.SimpleCallback;
import com.kawakp.demingliu.defaultapp.http.SpotsCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Response;

public class MainActivity extends BaseActivity {


    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;


    @Bind(R.id.materialRefreshLayout)
    MaterialRefreshLayout mRefreshLaout;

    @Bind(R.id.button1)
    Button b1;

    @Bind(R.id.button2)
    Button b2;

    @Bind(R.id.button3)
    Button b3;

    private OkHttpHelper httpHelper;

    private String url = "http://kawakp.chinclouds.com:60034/userconsle/deviceAlarms?";
    private int page = 1;
    private int pageSize = 10;
    private int status = 1;

    private int totalPage = 1;

    private static final int STATE_NOMAL = 0; //正常加载
    private static final int STATE_REFRESH = 1; //下拉刷新
    private static final int STATE_MORE = 2; //上拉加载

    private int state = STATE_NOMAL;


    private List<WarmBean> totallist = new ArrayList<WarmBean>();
    private TestAdapter adapter;



    @Override
    public int setContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        httpHelper = OkHttpHelper.getInstance(this);

        init();
    }

    private void init() {
        initRefreshLayout();
        requestWares();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,QActivity.class));
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,WXactivity.class));
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,DeviceListActivity.class));
            }
        });

    }



    private  void initRefreshLayout(){

        mRefreshLaout.setLoadMore(true);
        mRefreshLaout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {

                refreshData();

            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {

                if(page <=totalPage)
                    loadMoreData();
                else{
//                    Toast.makeText()
                    mRefreshLaout.finishRefreshLoadMore();
                }
            }
        });
    }


    private  void refreshData(){

        page =1;

        state=STATE_REFRESH;
        requestWares();

    }

    private void loadMoreData(){

        page = ++page;
        state = STATE_MORE;
        requestWares();

    }


    private void requestWares(){

        String s = url + "pageNum=" + page + "&pageSize=" + pageSize + "&status=" + status;

        httpHelper.get(s, new SpotsCallBack<MateralBean<WarmBean>>(MainActivity.this) {

            @Override
            public void onSuccess(Response response, MateralBean<WarmBean> warmBeanMateralBean) {

                totalPage =warmBeanMateralBean.getPages();

                totallist = warmBeanMateralBean.getList();
                showWaresData(totallist);
            }



            @Override
            public void onError(Response response, int code, Exception e) {

            }


        });

    }


    private  void showWaresData(List<WarmBean> wares){
        switch (state){

            case  STATE_NOMAL:

                if(adapter ==null) {
                    adapter = new TestAdapter(MainActivity.this, wares);
               /*     mWaresAdatper.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Wares wares = mWaresAdatper.getItem(position);

                            Intent intent = new Intent(getActivity(), WareDetailActivity.class);

                            intent.putExtra(Contants.WARE,wares);
                            startActivity(intent);

                        }
                    });*/

                    recyclerView.setAdapter(adapter);

                    //recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    //recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
//                    mRecyclerviewWares.addItemDecoration(new DividerGridItemDecoration(getContext()));
                }
                else{
                    adapter.clear();
                    adapter.addData(wares);
                }




                break;

            case STATE_REFRESH:
                adapter.clear();
                adapter.addData(wares);

                recyclerView.scrollToPosition(0);
                mRefreshLaout.finishRefresh();
                break;

            case STATE_MORE:
                adapter.addData(adapter.getDatas().size(),wares);
                recyclerView.scrollToPosition(adapter.getDatas().size());
                mRefreshLaout.finishRefreshLoadMore();
                break;


        }



    }

}
