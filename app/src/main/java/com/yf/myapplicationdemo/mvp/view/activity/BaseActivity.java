package com.yf.myapplicationdemo.mvp.view.activity;

import android.app.Activity;
import android.os.Bundle;

import com.yf.myapplicationdemo.R;
import com.yf.myapplicationdemo.mvp.presenter.BasePresenter;
import com.yf.myapplicationdemo.mvp.view.iview.MvpView;

public abstract  class BaseActivity<V extends MvpView,T extends BasePresenter<V>> extends Activity {

    public T basePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        basePresenter=getBasePresenter();
        basePresenter.attch((V) this);//最终的 this 代表的是具体的子类,子类再继承的时候就必须实现MVPview
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        basePresenter.deAttch();
    }

    public abstract T getBasePresenter();
}
