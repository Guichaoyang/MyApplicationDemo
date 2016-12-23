package com.yf.myapplicationdemo.bluetooth;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothProfile.ServiceListener;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.util.Log;

public class BluetoothConnnecte {

	private final static String TAG = "BluetoothConnnecte";
	
	private BluetoothAdapter adapter;
	private Context context;
	private Handler handler;
//	BluetoothHeadset mBluetoothHeadset;
	BluetoothA2dp mBluetoothA2dp;
	BluetoothProfile proxyls;
	BluetootnReceiver bluetootnReceiver;

	public BluetoothConnnecte(Context context, Handler handler) {
		super();
		this.context = context;
		this.handler = handler;
		registerBluetoothReceiver();
	}

	public boolean isSupportBluetooth() {
		if (adapter == null) {
			adapter = BluetoothAdapter.getDefaultAdapter();
		}
		if (adapter == null) {
			return false;
		}
		return true;
	}

	public void openBluetooth() {
		// 打开蓝牙
		if (!adapter.isEnabled()) {
			// Intent intent = new
			// Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			// // 设置蓝牙可见性，最多300秒
			// intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,
			// 300);
			// context.startActivity(intent);
			adapter.enable();

			while (!adapter.isEnabled()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		adapter.getProfileProxy(context, new ServiceListener() {

			@Override
			public void onServiceDisconnected(int profile) {
//				if (profile == BluetoothProfile.HEADSET) {
//					mBluetoothHeadset = null;
//				}
				if (profile == BluetoothProfile.A2DP) {
					mBluetoothA2dp = null;
				}
			}

			@Override
			public void onServiceConnected(int profile, BluetoothProfile proxy) {
				if (profile == BluetoothProfile.A2DP) {
					proxyls = proxy;
					mBluetoothA2dp = (BluetoothA2dp) proxy;
					Log.e(TAG, mBluetoothA2dp.toString());
					// 等蓝牙打开之后进行搜索
					startSerchDevice();
				}
//				if (profile == BluetoothProfile.HEADSET) {
//					proxyls = proxy;
//					mBluetoothHeadset = (BluetoothHeadset) proxy;
//					Log.e(TAG, mBluetoothHeadset.toString());
//					// 等蓝牙打开之后进行搜索
//					startSerchDevice();
//				}
			}
//		}, BluetoothProfile.HEADSET);
		}, BluetoothProfile.A2DP);
	}

	/**
	 * 开始搜索
	 */
	public void startSerchDevice() {
		//

		if (adapter != null) {
//			bluetootnReceiver.setBluetoothDevice(adapter, mBluetoothHeadset);
			bluetootnReceiver.setBluetoothDevice(adapter, mBluetoothA2dp);
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					adapter.startDiscovery();
				}
			}, 500);
		}

	}

	/**
	 * 注册广播
	 * 
	 */
	private void registerBluetoothReceiver() {
		bluetootnReceiver = new BluetootnReceiver(handler);
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
		intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
		intentFilter.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
		intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
		intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
		intentFilter.addAction(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED);
		intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
		intentFilter.addAction(BluetoothAdapter.ACTION_LOCAL_NAME_CHANGED);
		intentFilter.addAction(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
		intentFilter.addAction(BluetoothAdapter.ACTION_REQUEST_ENABLE);
		intentFilter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
		intentFilter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
		intentFilter.addAction(BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED);
		context.registerReceiver(bluetootnReceiver, intentFilter);
	}

	/**
	 * 判断蓝牙是否打开
	 * 
	 * @return
	 */
	public boolean bluetoothIsOpened() {
		if (adapter != null) {
			if (!adapter.isEnabled()) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	/**
	 * 关闭蓝牙
	 */
	public void closeBluetooth() {
		if (adapter != null) {
			if (adapter.isEnabled()) {
				 bluetootnReceiver.unpairDevice();
				adapter.closeProfileProxy(BluetoothProfile.A2DP, proxyls);
//				adapter.closeProfileProxy(BluetoothProfile.HEADSET, proxyls);
				adapter.disable();
				// adapter = null;
			}
		}
	}

	// 退出时注销广播
	public void destroy() {
		context.unregisterReceiver(bluetootnReceiver);
	}
}
