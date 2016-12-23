package com.yf.myapplicationdemo.mvp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangfei on 2016/12/21.
 */

public class MainModel2 implements MvpModel {
    @Override
    public void getData(OnLoadCompleteListener onLoadCompleteListener) {
        //这里方式变了,伪装数据发生变化
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("这是更新后的数据第" + i + "条");
        }
        onLoadCompleteListener.onLoadComplete(list);
    }
}
