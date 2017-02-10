package com.kawakp.demingliu.defaultapp.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.kawakp.demingliu.defaultapp.R;
import com.kawakp.demingliu.defaultapp.adapter.RecyclerViewAdapter;
import com.kawakp.demingliu.defaultapp.bean.MateralBean;
import com.kawakp.demingliu.defaultapp.bean.WarmBean;
import com.kawakp.demingliu.defaultapp.decoration.DividerItemDecoration;
import com.kawakp.demingliu.defaultapp.http.OkHttpHelper;
import com.kawakp.demingliu.defaultapp.http.SpotsCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import okhttp3.Response;

/**
 * Created by deming.liu on 2016/10/10.
 */
public class HomePageFragment extends BaseFragment {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;


    @Bind(R.id.materialRefreshLayout)
    MaterialRefreshLayout mRefreshLaout;
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
    private RecyclerViewAdapter adapter;

    public static HomePageFragment newInstance() {

        return new HomePageFragment();
    }


    @Override
    protected int setContentViewId() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void initView() {
        httpHelper = OkHttpHelper.getInstance(getActivity());
        initRefreshLayout();
        requestWares();
    }

    @Override
    protected void lazyLoad() {

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

        httpHelper.get(s, new SpotsCallBack<MateralBean<WarmBean>>(getActivity()) {

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
                    adapter = new RecyclerViewAdapter(getActivity(), wares);
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
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    //recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));
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
                //adapter.addData(adapter.getDatas().size(),wares);
                adapter.loadMoreData(wares);
                recyclerView.scrollToPosition(adapter.getDatas().size());
                mRefreshLaout.finishRefreshLoadMore();
                break;


        }



    }
}
