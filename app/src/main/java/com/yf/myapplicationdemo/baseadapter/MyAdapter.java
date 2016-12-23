package com.yf.myapplicationdemo.baseadapter;

import android.content.Context;
import android.widget.TextView;

import com.yf.myapplicationdemo.R;

import java.util.List;

/**
 * Created by yangfei on 2016/12/21.
 */

public class MyAdapter extends MyBaseAdapter<String> {

    private List<String> strings;
    public MyAdapter(List<String> list, Context context, int resId) {
        super(list, context, resId);
        this.strings = list;//数据源赋值,一定要接收数据 否则下面调用的时候就崩了
    }

    @Override
    public void setData(ViewHolder viewHolder, int position) {
        ((TextView) viewHolder.findViewById(R.id.tv)).setText("这是第"+position);
    }
}
