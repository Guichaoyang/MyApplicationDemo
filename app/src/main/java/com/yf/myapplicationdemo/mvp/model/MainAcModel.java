package com.yf.myapplicationdemo.mvp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 专门给MainActivity 获取数据的类
 * Created by yangfei on 2016/12/21.
 */

public class MainAcModel implements MvpModel {
    @Override
    public void getData(OnLoadCompleteListener onLoadCompleteListener) {
        //获取数据
        //伪装网络请求
        List<String> list = new ArrayList<>();//数据
        for (int i = 0; i < 100; i++) {
            list.add("这是第" + i + "条数据");
        }
        if (onLoadCompleteListener != null) {
            onLoadCompleteListener.onLoadComplete(list);
        }
    }
}
