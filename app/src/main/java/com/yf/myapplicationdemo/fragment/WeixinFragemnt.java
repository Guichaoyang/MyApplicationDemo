package com.yf.myapplicationdemo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yf.myapplicationdemo.R;

/**
 * Created by yangfei on 2016/12/14.
 */

public class WeixinFragemnt extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weixin,container,false);
        return view;
    }
}
