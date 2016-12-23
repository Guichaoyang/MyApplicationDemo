package com.yf.myapplicationdemo.xutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.yf.myapplicationdemo.R;

import org.xutils.http.RequestParams;
import org.xutils.x;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

public class XUTILActivity extends AppCompatActivity {
    @DataInject("com.yf.myapplicationdemo.xutils.Data")
    RequestParams requestParams;
    private static String address = "http://appapi.yaochufa.com/v2/ProductPackage/GetCommonPackagesByLinkIdV424?linkId=115829&machineCode=ffffffff-a50f-4cc6-ffff-ffffbd649aff&version=4.4.0&checkOutDate=2015-12-22&system=android&checkInDate=2015-11-17&channel=360mobile&isSelected=false";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xutil);
        EventBus.getDefault().register(this);
        RequestParams params = new RequestParams(address);
        x.http().get(params,new MyCommonCallStringRequest1(new Data()));

    }
    @Subscribe
    public void getData(Data data){
        Log.e("eventbus",""+ data.getSatausCode());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
