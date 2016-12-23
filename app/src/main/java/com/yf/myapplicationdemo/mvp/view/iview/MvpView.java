package com.yf.myapplicationdemo.mvp.view.iview;

import java.util.List;

/**
 * 更新视图的接口
 * Created by yangfei on 2016/12/22.
 */

public interface MvpView {
    void showView(List<String> list);//更新界面, 得有数据源
}
