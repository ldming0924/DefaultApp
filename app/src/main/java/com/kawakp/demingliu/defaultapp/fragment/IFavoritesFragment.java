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
public class IFavoritesFragment extends Fragment {

    public static IFavoritesFragment newInstance() {

        return new IFavoritesFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage,null);
        return view;
    }
}
