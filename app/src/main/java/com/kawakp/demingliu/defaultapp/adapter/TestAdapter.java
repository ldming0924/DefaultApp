package com.kawakp.demingliu.defaultapp.adapter;

import android.content.Context;

import com.kawakp.demingliu.defaultapp.R;
import com.kawakp.demingliu.defaultapp.bean.WarmBean;

import java.util.List;

/**
 * Created by deming.liu on 2016/10/9.
 */
public class TestAdapter extends SimpleAdapter<WarmBean> {


    public TestAdapter(Context context,  List<WarmBean> datas) {
        super(context, R.layout.material_adapter_item, datas);
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, WarmBean item) {
        viewHoder.getTextView(R.id.textView4).setText(item.getCreateDate());
        viewHoder.getTextView(R.id.textView5).setText(item.getDisplayName());
    }
}
