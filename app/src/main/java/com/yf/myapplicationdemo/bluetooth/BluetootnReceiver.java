package com.yf.myapplicationdemo.bluetooth;

import java.io.IOException;
import java.lang.reflect.Method;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

public class BluetootnReceiver extends BroadcastReceiver {

	private final static String TAG = "BluetoothConnnecte";

	private Handler handler;
	private BluetoothAdapter adapter;
	private int connectState;// 蓝牙与目标设备的连接状态
	private BluetoothDevice device;
//	private BluetoothHeadset mBluetoothHeadset;
	private BluetoothA2dp mBluetoothA2dp;
	
	private boolean isConnected;

	public BluetootnReceiver(Handler handler) {
		super();
		this.handler = handler;
	}

	public void setBluetoothDevice(BluetoothAdapter adapter, BluetoothA2dp mBluetoothA2dp) {
		this.adapter = adapter;
		this.mBluetoothA2dp = mBluetoothA2dp;
		isConnected = false;
	}
//	public void setBluetoothDevice(BluetoothAdapter adapter, BluetoothHeadset mBluetoothHeadset) {
//		this.adapter = adapter;
//		this.mBluetoothHeadset = mBluetoothHeadset;
//		isConnected = false;
//	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String action = intent.getAction();
		if (BluetoothDevice.ACTION_FOUND.equals(action)) {
			device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
			// System.out.println(device.getName());
			if ("易关怀".equals(device.getName())) {
				// 搜索蓝牙设备的过程占用资源比较多，一旦找到需要连接的设备后需要及时关闭搜索
				adapter.cancelDiscovery();
				// 获取蓝牙设备的连接状态
				connectState = device.getBondState();
				checkDeviceState();
			}
		} else if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
			// 状态改变的广播
			device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
			if ("易关怀".equals(device.getName())) {
				// adapter.cancelDiscovery();// 停止搜索
				switch (device.getBondState()) {
				case BluetoothDevice.BOND_BONDING:// 正在配对

					break;
				case BluetoothDevice.BOND_BONDED:// 完成配对
					// 连接
					try {
						connect();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case BluetoothDevice.BOND_NONE:// 取消配对
					break;

				default:
					break;
				}
			}

		} else if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {
			if (adapter != null) {
				boolean isCovering = adapter.isDiscovering();
				Log.i(TAG, "------蓝牙设备搜索完毕----" + isCovering);
				if (isConnected) {
					isConnected = false;
					handler.sendEmptyMessage(1);
				}
			}
			System.gc();// 垃圾回收
		} else if (action.equals(BluetoothDevice.ACTION_ACL_CONNECTED)) {
			Log.i(TAG, "------ACTION_ACL_CONNECTED--------------");
			isConnected = true;
		} else if (action.equals(BluetoothDevice.ACTION_ACL_DISCONNECTED)) {
			// Log.i(TAG, "------ACTION_ACL_DISCONNECTED---------蓝牙断开-----");
		} else if (action.equals(BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED)) {

		} else if (action.equals(BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED)) {
			Log.i(TAG, "---BluetoothA2dp---ACTION_CONNECTION_STATE_CHANGED--------------");
		} else if (action.equals(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED)) {
			Log.i(TAG, "---BluetoothAdapter---ACTION_CONNECTION_STATE_CHANGED--------------");
			if (mBluetoothA2dp != null) {
				device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				int bluetoothState = mBluetoothA2dp.getConnectionState(device);
				switch (bluetoothState) {
				case BluetoothProfile.STATE_CONNECTED:
					Log.i(TAG, "------STATE_CONNECTED--------------");
					if (isConnected) {
						isConnected = false;
						handler.sendEmptyMessage(1);
					}
					break;
				case BluetoothProfile.STATE_DISCONNECTED:
					break;
				case BluetoothProfile.STATE_CONNECTING:
					break;
				case BluetoothProfile.STATE_DISCONNECTING:
					break;

				default:
					break;
				}
			}
//			if (mBluetoothHeadset != null) {
//				device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//				int bluetoothState = mBluetoothHeadset.getConnectionState(device);
//				switch (bluetoothState) {
//				case BluetoothProfile.STATE_CONNECTED:
//					Log.i(TAG, "------STATE_CONNECTED--------------");
//					if (isConnected) {
//						isConnected = false;
//						handler.sendEmptyMessage(1);
//					}
//					break;
//				case BluetoothProfile.STATE_DISCONNECTED:
//					break;
//				case BluetoothProfile.STATE_CONNECTING:
//					break;
//				case BluetoothProfile.STATE_DISCONNECTING:
//					break;
//					
//				default:
//					break;
//				}
//			}
		}
	}

	private void checkDeviceState() {
		// TODO Auto-generated method stub
		switch (connectState) {
		case BluetoothDevice.BOND_NONE:// 未配对
			Log.i(TAG, "未配对");
			// 配对
			try {
				Method createBondMethod = BluetoothDevice.class.getMethod("createBond");
				createBondMethod.invoke(device);
			} catch (Exception e) {
				Log.i(TAG, "配对---异常---" + e.getMessage());
				e.printStackTrace();
			}
			break;

		case BluetoothDevice.BOND_BONDED:// 已配对
			Log.i(TAG, "已配对");
			// 连接
			try {
				connect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		}
	}

	/**
	 * 连接设备
	 * 
	 * @throws IOException
	 */
	BluetoothSocket socket;

	private void connect() throws IOException {

		try {
			Method m = mBluetoothA2dp.getClass().getDeclaredMethod("connect", BluetoothDevice.class);
			m.setAccessible(true);
			m.invoke(mBluetoothA2dp, device);
			Log.i(TAG, "---------------蓝牙连接-----connect");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.i(TAG, "----Method---" + e.getMessage());
			e.printStackTrace();
		}
	}
//	private void connect() throws IOException {
//		
//		try {
//			Method m = mBluetoothHeadset.getClass().getDeclaredMethod("connect", BluetoothDevice.class);
//			m.setAccessible(true);
//			m.invoke(mBluetoothHeadset, device);
//			Log.i(TAG, "---------------蓝牙连接-----connect");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			Log.i(TAG, "----Method---" + e.getMessage());
//			e.printStackTrace();
//		}
//	}

	public void unpairDevice() {
		if (device != null) {
			try {
				Method m = device.getClass()
						.getMethod("removeBond", (Class[]) null);
				m.invoke(device, (Object[]) null);
			} catch (Exception e) {
				Log.e(TAG, e.getMessage());
			}
		}
	}
	
}
