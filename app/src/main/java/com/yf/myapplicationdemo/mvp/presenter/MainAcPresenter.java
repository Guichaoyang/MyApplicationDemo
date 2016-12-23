package com.yf.myapplicationdemo.mvp.presenter;

import com.yf.myapplicationdemo.mvp.model.MainAcModel;
import com.yf.myapplicationdemo.mvp.model.MainModel2;
import com.yf.myapplicationdemo.mvp.model.MvpModel;
import com.yf.myapplicationdemo.mvp.view.iview.MvpView;

import java.util.List;

/**
 * 用于协调View 和 Model 的中间类,这个是针对Main
 * Created by yangfei on 2016/12/22.
 */

public class MainAcPresenter extends BasePresenter<MvpView> {

    //private MvpView mvpView;//获得数据后需要更新视图的 view
    private MvpModel mvpModel;// 用于获取数据的 model

//    public MainAcPresenter(MvpView mvpView) {
//        this.mvpView = mvpView;
//        mvpModel = new MainAcModel();
//    }


    public MainAcPresenter() {
        mvpModel = new MainAcModel();
    }

    //    public MainAcPresenter setModel(MvpModel model) {
    //        this.mvpModel = model;
    //        return this;
    //    }
    public MainAcPresenter setModel(int model) {
        switch (model) {
            case 0:
                mvpModel = new MainAcModel();
                break;

            case 1:
                mvpModel = new MainModel2();
                break;
        }
        return this;
    }

    /**
     * 加载数据,内部通过 model去加载
     */
    public void load() {
        mvpModel.getData(new MvpModel.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(List<String> list) {
                //model 加载完成后数据会传递到这里来
                getView().showView(list);
            }
        });
    }
}
