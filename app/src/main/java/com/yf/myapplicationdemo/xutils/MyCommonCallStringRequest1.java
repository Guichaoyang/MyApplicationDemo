package com.yf.myapplicationdemo.xutils;

import com.google.gson.Gson;
import org.xutils.common.Callback;
import de.greenrobot.event.EventBus;

/**
 * Created by yangfei on 2016/12/22.
 */

public class MyCommonCallStringRequest1 implements Callback.CommonCallback<String> {
    private Object object;//用于解析json数据的对象，需要什么就传递什么

    public MyCommonCallStringRequest1(Object object) {
        this.object = object;
    }

    @Override
    public void onSuccess(String result) {
        object = new Gson().fromJson(result,object.getClass());
        EventBus.getDefault().post(object);
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {

    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }
}
