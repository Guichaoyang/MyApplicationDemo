package com.yf.myapplicationdemo.bluetooth;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.yf.myapplicationdemo.R;

public class MainActivity extends Activity {

	private Button button_bluetooth;
	private TextView textview_bluetoothState;
	private boolean isBluetoothOpen;

	private BluetoothConnnecte bluetoothConnnecte;

	// 更新蓝牙状态的显示
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				Log.i("BluetoothConnnecte", "====Ccccccc=====");
				 isBluetoothOpen = true;
				 button_bluetooth.setText("关闭蓝牙");
				 textview_bluetoothState.setText("连接成功");
				break;
			case 2:
				isBluetoothOpen = false;
				button_bluetooth.setText("打开蓝牙");
				textview_bluetoothState.setText("已关闭");
				break;

			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_bluetooth);
		button_bluetooth = (Button) findViewById(R.id.button_bluetooth);
		textview_bluetoothState = (TextView) findViewById(R.id.textview_showbluetoothstate);
		bluetoothConnnecte = new BluetoothConnnecte(this, handler);
		if (bluetoothConnnecte.isSupportBluetooth()) {
			if (bluetoothConnnecte.bluetoothIsOpened()) {
				textview_bluetoothState.setText("蓝牙已打开");
				isBluetoothOpen = true;
				button_bluetooth.setText("关闭蓝牙");
			} else {
				textview_bluetoothState.setText("蓝牙未打开");
			}
		} else {
			textview_bluetoothState.setText("该设备不支持蓝牙");
		}
		// 点击开关蓝牙
		button_bluetooth.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!isBluetoothOpen) {
					// 打开蓝牙
					if (bluetoothConnnecte.isSupportBluetooth()) {
						bluetoothConnnecte.openBluetooth();
//						isBluetoothOpen = true;
//						button_bluetooth.setText("关闭蓝牙");
					} else {
						textview_bluetoothState.setText("该设备不支持蓝牙");
					}
				} else {
					// 关闭蓝牙
					bluetoothConnnecte.closeBluetooth();
					handler.sendEmptyMessage(2);
				}
			}
		});
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		bluetoothConnnecte.destroy();
	}
}
