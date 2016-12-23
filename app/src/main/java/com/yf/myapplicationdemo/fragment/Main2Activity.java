package com.yf.myapplicationdemo.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.yf.myapplicationdemo.R;

public class Main2Activity extends Activity implements View.OnClickListener {

    private LinearLayout mTabWeixin;
    private LinearLayout mTabFriend;

    private WeixinFragemnt mWeixin;
    private FriendFragemnt mFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // 初始化控件和声明事件
        mTabWeixin = (LinearLayout) findViewById(R.id.id_weixin_botoombar);
        mTabFriend = (LinearLayout) findViewById(R.id.id_friend_botoombar);
        mTabWeixin.setOnClickListener(this);
        mTabFriend.setOnClickListener(this);

        //设置默认的Fragemnt
        setDefultFragemnt();
    }

    private void setDefultFragemnt() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        mWeixin = new WeixinFragemnt();
        ft.replace(R.id.ly_content,mWeixin);
        ft.commit();
    }

    @Override
    public void onClick(View view) {
        FragmentManager fm = getFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (view.getId())
        {
            case R.id.id_weixin_botoombar:
                if (mWeixin == null)
                {
                    mWeixin = new WeixinFragemnt();
                }
                // 使用当前Fragment的布局替代id_content的控件
                transaction.replace(R.id.ly_content, mWeixin);
                break;
            case R.id.id_friend_botoombar:
                if (mFriend == null)
                {
                    mFriend = new FriendFragemnt();
                }
                transaction.replace(R.id.ly_content, mFriend);
                break;
        }
        // transaction.addToBackStack();
        // 事务提交
        transaction.commit();
    }
}
