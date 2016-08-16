package com.health.measurement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import android.util.Log;

import com.c102c.app.bluetooth.BluetoothService;
import com.c102c.app.utils.MyArrays;

public class HealthDevice {
	/**
	 * ���յ��Ķ������ݸ���ͷ��־�ָ
	 * 
	 * @param buffer
	 * @return
	 */
	private static boolean state = true;
	private static PersistWriter lastInstance = null;

	public static List<byte[]> splitBufferData(byte[] buffer, byte[] head) {
		List<byte[]> datas = new ArrayList<byte[]>();
		// ÿ�����������ݵ�ǰ����ֽڴ�С�ǹ̶���,��̵�����Ϊ6���ֽ�
		int low = 0, high = buffer.length;// ��ʼ����ֻ��һ������

		for (int i = 2; i < buffer.length; i++) {
			if (buffer[i - 1] == head[0] && buffer[i] == head[1]) {
				high = i - 1;
				datas.add(MyArrays.copyOfRange(buffer, low, high));
				low = high;
			}
		}
		datas.add(MyArrays.copyOfRange(buffer, low, buffer.length));// ���һ������
		return datas;
	}

	public static PersistWriter getAuthority(BluetoothService bluetoothService,
			byte[] command, long sleepTime) {
		if (lastInstance != null) {
			if (lastInstance.bluetoothService == bluetoothService)
				return lastInstance;// ����֮ǰ������ʵ��
			else
				lastInstance.stop();// handler��ͬ��ʾҪ���Ӳ�ͬ���豸����Ҫ���ϴ����Ӷϵ�
		}
		lastInstance = new PersistWriter(bluetoothService, command, sleepTime);
		return lastInstance;
	}

	/**
	 * 
	 * ���������������������������
	 * 
	 */
	public static class PersistWriter extends Thread {
		private BluetoothService bluetoothService;
		private static final String TAG = "PC300.PersistWriter";
		private byte[] command;// ���ݵ�����
		private long sleepTime;// ���ʱ��

		public PersistWriter(BluetoothService bluetoothService, byte[] command,
				long sleepTime) {
			this.bluetoothService = bluetoothService;
			this.command = command;
			this.sleepTime = sleepTime;
		}

		@Override
		public void run() {
			while (state) {
				if (bluetoothService.getState() == BluetoothService.STATE_CONNECTED) {
					//Log.i(TAG, "send persist command");
					try {
						bluetoothService.write(command);// ��������
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				try {
					TimeUnit.MILLISECONDS.sleep(sleepTime);// ÿ��һ��ʱ�䷢��һ�ε�����ѯ����
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		public void close() {
			state = false;
		}

		public void mystart() {
			state = true;
		}
	}

}
