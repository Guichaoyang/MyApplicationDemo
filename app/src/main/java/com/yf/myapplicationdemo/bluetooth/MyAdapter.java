package com.yf.myapplicationdemo.bluetooth;

import java.util.List;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yf.myapplicationdemo.R;

public class MyAdapter extends BaseAdapter {

	private List<BluetoothDevice> mBluetoothDevices;
	private Context mContext;

	public MyAdapter(List<BluetoothDevice> mBluetoothDevices, Context mContext) {
		super();
		this.mBluetoothDevices = mBluetoothDevices;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mBluetoothDevices == null ? 0 : mBluetoothDevices.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_bluetooth, null);
		}
		BluetoothDevice mBluetoothDevice = mBluetoothDevices.get(position);
		TextView name = (TextView) convertView.findViewById(R.id.name);
		TextView address = (TextView) convertView.findViewById(R.id.address);
		name.setText(mBluetoothDevice.getName());// 设置蓝牙名
		address.setText(mBluetoothDevice.getAddress());// 设置蓝牙的地址
		return convertView;
	}

}
