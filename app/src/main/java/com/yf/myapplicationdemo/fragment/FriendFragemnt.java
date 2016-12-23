package com.yf.myapplicationdemo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yf.myapplicationdemo.R;

/**
 * Created by yangfei on 2016/12/14.
 */

public class FriendFragemnt extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_friend, container, false);
    }
}
