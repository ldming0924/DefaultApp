package com.kawakp.demingliu.defaultapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kawakp.demingliu.defaultapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {


    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("TAG","MessageFragment  onPause ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("TAG","MessageFragment  onResume ");
    }
}
