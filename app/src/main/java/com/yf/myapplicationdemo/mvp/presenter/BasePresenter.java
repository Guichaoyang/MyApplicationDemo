package com.yf.myapplicationdemo.mvp.presenter;

import com.yf.myapplicationdemo.mvp.view.iview.MvpView;

import java.lang.ref.WeakReference;

/**
 * Created by yangfei on 2016/12/22.
 */

public class BasePresenter<V extends MvpView> {
    private WeakReference<V> weakReference;

    public void attch(V mvpView) {
        weakReference = new WeakReference(mvpView);
    }

    public void deAttch() {
        if (weakReference != null) {
            weakReference.clear();
            weakReference = null;
        }
    }

    public V getView() {
        return weakReference.get();
    }
}
