package com.kawakp.demingliu.defaultapp.bean;

import android.text.TextUtils;

/**
 * Created by deming.liu on 2016/9/22.
 */
public class LvMenuItem {
    public int type;
    public String name;
   public int icon;

    private static final int NO_ICON = 0;
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_NO_ICON = 1;
    public static final int TYPE_SEPARATOR = 2;


    //分类，有图片的
    public LvMenuItem(int icon, String name) {
        this.icon = icon;
        this.name = name;

        if (icon == NO_ICON && TextUtils.isEmpty(name)) {
            type = TYPE_SEPARATOR;
        } else if (icon == NO_ICON) {
            type = TYPE_NO_ICON;
        } else {
            type = TYPE_NORMAL;
        }

        if (type != TYPE_SEPARATOR && TextUtils.isEmpty(name)) {
            throw new IllegalArgumentException("you need set a name for a non-SEPARATOR item");
        }

       // L.e(type + "");


    }

    //没图片
    public LvMenuItem(String name) {
        this(NO_ICON, name);
    }

    //什么都没
    public LvMenuItem() {
        this(null);
    }
}
