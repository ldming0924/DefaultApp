package com.kawakp.demingliu.defaultapp.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.TextViewCompat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.kawakp.demingliu.defaultapp.R;
import com.kawakp.demingliu.defaultapp.bean.LvMenuItem;

import java.util.List;

/**
 * Created by deming.liu on 2016/9/22.
 */
public class MenuItemAdapter extends BaseAdapter {

    private  int mIconSize;
    private LayoutInflater mInflater;
    private Context mContext;
    private List<LvMenuItem> mItems;

    public MenuItemAdapter(Context context, List<LvMenuItem> mItems) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mItems = mItems;
        mIconSize = context.getResources().getDimensionPixelSize(R.dimen.drawer_icon_size);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).type;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LvMenuItem item = mItems.get(i);
        switch (item.type) {
            case LvMenuItem.TYPE_NORMAL:
                if (view == null) {
                    view = mInflater.inflate(R.layout.design_drawer_item, viewGroup,
                            false);
                }
                TextView itemView = (TextView) view;
                itemView.setText(item.name);
                Drawable icon = mContext.getResources().getDrawable(item.icon);
                setIconColor(icon);
                if (icon != null) {
                    icon.setBounds(0, 0, mIconSize, mIconSize);
                    TextViewCompat.setCompoundDrawablesRelative(itemView, icon, null, null, null);
                }

                break;
            case LvMenuItem.TYPE_NO_ICON:
                if (view == null) {
                    view = mInflater.inflate(R.layout.design_drawer_item_subheader,
                            viewGroup, false);
                }
                TextView subHeader = (TextView) view;
                subHeader.setText(item.name);
                break;
            case LvMenuItem.TYPE_SEPARATOR:
                if (view == null) {
                    view = mInflater.inflate(R.layout.design_drawer_item_separator,
                            viewGroup, false);
                }
                break;
        }

        return view;
    }

    public void setIconColor(Drawable icon) {
        int textColorSecondary = android.R.attr.textColorSecondary;
        TypedValue value = new TypedValue();
        if (!mContext.getTheme().resolveAttribute(textColorSecondary, value, true)) {
            return;
        }
        int baseColor = mContext.getResources().getColor(value.resourceId);
        icon.setColorFilter(baseColor, PorterDuff.Mode.MULTIPLY);
    }
}
