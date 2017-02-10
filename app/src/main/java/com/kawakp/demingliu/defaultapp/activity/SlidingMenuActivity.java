package com.kawakp.demingliu.defaultapp.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kawakp.demingliu.defaultapp.R;
import com.kawakp.demingliu.defaultapp.adapter.MenuItemAdapter;
import com.kawakp.demingliu.defaultapp.bean.LvMenuItem;
import com.kawakp.demingliu.defaultapp.fragment.AttentionPeopleFragment;
import com.kawakp.demingliu.defaultapp.fragment.ConsumeHistoryFragment;
import com.kawakp.demingliu.defaultapp.fragment.DataStatisticsFragment;
import com.kawakp.demingliu.defaultapp.fragment.HomePageFragment;
import com.kawakp.demingliu.defaultapp.fragment.IFavoritesFragment;
import com.kawakp.demingliu.defaultapp.fragment.SettingFragment;
import com.kawakp.demingliu.defaultapp.widget.CircleImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by deming.liu on 2016/10/10.
 */
public class SlidingMenuActivity extends AppCompatActivity {

    @Bind(R.id.id_toolbar)
    Toolbar mToolbar;

    @Bind(R.id.container)
    FrameLayout container;
    @Bind(R.id.id_lv_left_menu)
    ListView mLvLeftMenu;

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;


    private CircleImageView mUserAcatarView;

    private TextView mUserName;

    private TextView mUserSign;

    private Fragment[] fragments;

    private int currentTabIndex;

    private int index;

    private boolean isShowMenu = false;

    private Random random;

    private long exitTime;


    private static final String SWITCH_MODE_KEY = "mode_key";

    private List<LvMenuItem> mItems;

    private FragmentManager manager;
    private FragmentTransaction transaction;

    private ImageView mSwitchMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.qact);
        //初始化黄油刀控件绑定框架
        ButterKnife.bind(this);

        initData();
        //初始化Fragment
        initFragments();

        //初始化ToolBar
        initToolBar();
    }

    /**
     * 初始化Fragments
     */
    private void initFragments()
    {

        HomePageFragment mHomePageFragment = HomePageFragment.newInstance();
        IFavoritesFragment mFavoritesFragment = IFavoritesFragment.newInstance();
        DataStatisticsFragment mHistoryFragment = DataStatisticsFragment.newInstance();
        AttentionPeopleFragment mAttentionPeopleFragment = AttentionPeopleFragment.newInstance();
        ConsumeHistoryFragment mConsumeHistoryFragment = ConsumeHistoryFragment.newInstance();
        SettingFragment mSettingFragment = SettingFragment.newInstance();

        fragments = new Fragment[]{
                mHomePageFragment,
                mFavoritesFragment,
                mHistoryFragment,
                mAttentionPeopleFragment,
                mConsumeHistoryFragment,
                mSettingFragment
        };

        // 添加显示第一个fragment
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, mHomePageFragment)
                .show(mHomePageFragment).commit();
        mToolbar.setTitle(mItems.get(0).name);
        mToolbar.setTitleTextColor(Color.WHITE);
        //setFragment(fragments[0]);
    }

    private void initData() {
        mItems  = new ArrayList<LvMenuItem>( Arrays.asList(
                new LvMenuItem(R.drawable.ic_launcher, "主页"),
                new LvMenuItem(R.drawable.ic_launcher, "Messages"),
                new LvMenuItem(R.drawable.ic_launcher, "数据统计图表"),
                new LvMenuItem(R.drawable.ic_launcher, "Discussion"),
                new LvMenuItem(R.drawable.ic_launcher, "Sub Item 1"),
                new LvMenuItem(R.drawable.ic_launcher, "Sub Item 2")));


        setUpDrawer();
    }

    private void setUpDrawer() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.layout_navigation_header,null);

        mSwitchMode = (ImageView) view.findViewById(R.id.iv_head_switch_mode);//夜间模式按钮

        mLvLeftMenu.addHeaderView(view);
        MenuItemAdapter adapter = new MenuItemAdapter(this,mItems);
        mLvLeftMenu.setAdapter(adapter);



        mLvLeftMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SlidingMenuActivity.this, position + "  "+mItems.get(position-1).name, Toast.LENGTH_SHORT).show();
                index = position -1;
                switchFragment(fragments[position -1]);
                //setFragment(fragments[position - 1]);
                mToolbar.setTitle(mItems.get(position -1).name);
                mToolbar.setTitleTextColor(Color.WHITE);
                mDrawerLayout.closeDrawers();

            }
        });


    }



 /*   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }*/




    /**
     * Fragment切换
     *
     * @param fragment
     */
    private void switchFragment(Fragment fragment)
    {

        FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
        trx.hide(fragments[currentTabIndex]);
        if (!fragments[index].isAdded())
        {
            trx.add(R.id.container, fragments[index]);
        }
        trx.show(fragments[index]).commit();
        currentTabIndex = index;
    }





    /**
     * 解决App重启后导致Fragment重叠的问题
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        //super.onSaveInstanceState(outState);
    }

    public void initToolBar() {
        // mToolbar.setLogo(R.drawable.ic_bili_logo_white);

        setSupportActionBar(mToolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nav_list_view, menu);
        return true;
    }*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);  //设置点击弹出侧滑菜单
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
