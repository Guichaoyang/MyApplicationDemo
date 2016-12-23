package com.yf.myapplicationdemo.mvp.model;

import java.util.List;

/**
 * 用于获取数据的模型层
 * Created by yangfei on 2016/12/21.
 */

public interface MvpModel {
    //在这里专门用于获取数据
    void getData(OnLoadCompleteListener onLoadCompleteListener);//获取数据的方法(上网,异步的一般需要通过接口回调传回来)

    public interface OnLoadCompleteListener{
        void onLoadComplete(List<String> list);//这个是请求完成的回调,携带数据回来
    }
}
