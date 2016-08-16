package com.health.measurement;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.c102c.app.bluetooth.BluetoothListActivity;
import com.c102c.app.bluetooth.BluetoothService;
import com.c102c.app.commu.Basesence;
import com.c102c.finalJKYTJ.R;
import com.health.util.ConnectState;
import com.health.util.MyProgressDialog;
import com.health.util.T;

/***
 * ��ϸ������
 * 
 * @author jiqunpeng
 * 
 *         ����ʱ�䣺2014-3-20 ����11:01:19
 */
public class MeasureWbc extends Activity {
	private static MyProgressDialog mDialog;
	private static Context context;
	private static EditText wbcEt;
	private static BluetoothService bluetoothService = null;
	private String btName = "";
	private static ImageView imageview;
	private static TextView statusView;
	private static WbcHandler wbcHandler;
	private static final int UPLOAD_RESULT = 0x100189;
	private static final int NO_DATA = 0x100190;
	private static final String TAG = "MeasureWbc";
	private static String btMac = "";
	private List<Byte> receiveBytes = new ArrayList<Byte>();
	private static final byte[] WBCSS = { 87, 66, 67, 13, 10 };
	private Float value = null;
	private String unit = null;
	private boolean hasGetData = false;// �Ƿ��Ѿ���ȡ������
	private Button uploadButton;
	private static String grxh;
	private static Cache cache;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.measure_wbc);
		context = this;
		cache = new Cache(context);
		initView();

		wbcHandler = new WbcHandler();
		mDialog = new MyProgressDialog(context);
		bluetoothService = BluetoothService.getService(wbcHandler, true);
	}

	private void initView() {
		imageview = (ImageView) findViewById(R.id.hp_image);
		wbcEt = (EditText) findViewById(R.id.wbc_et);
		statusView = (TextView) findViewById(R.id.connect_status);
		uploadButton = (Button) findViewById(R.id.upload_button);
	}

	public void uploadWbc(View view) {
		String wbc = wbcEt.getText().toString();

		if (wbc != null) {
			Intent intent = getIntent();
			intent.putExtra("wbc", wbc);
			setResult(Basesence.MEASURE_RESULT_WBC, intent);
		}
		finish();

	}

	private class WbcHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case BluetoothService.MESSAGE_STATE_CHANGE:
				switch (msg.arg1) {
				case BluetoothService.STATE_CONNECTED:
					statusView.setText(btName);
					Toast.makeText(context, "�����ӵ�" + btName, Toast.LENGTH_LONG)
							.show();
					// uploadButton.setEnabled(true);
					break;
				case BluetoothService.STATE_CONNECTING:
					statusView.setText(R.string.connecting);
					break;
				case BluetoothService.STATE_NONE:
					statusView.setText(R.string.unconnect);
					break;
				}
				break;

			case BluetoothService.MESSAGE_WRITE:
				// byte[] writeBuf = (byte[]) msg.obj;
				// new String(writeBuf);
				break;
			case BluetoothService.MESSAGE_READ:
				byte[] readBuf = (byte[]) msg.obj;
				if (readBuf == null)
					return;
				if (hasGetData == false) {// û�л������
					hasGetData = check(readBuf);// ����һ��
					if (hasGetData) {// ��������ݼ���ʾ
						// wbcEt.setText(value + " (" +
						// unit + ")");
						wbcEt.setText(value + "");
					}
				}
				break;
			case BluetoothService.MESSAGE_TOAST:
				Toast.makeText(context,
						msg.getData().getString(BluetoothService.TOAST),
						Toast.LENGTH_SHORT).show();
				break;
			case BluetoothService.MESSAGE_DEVICE:
				btName = msg.getData().getString(BluetoothService.DEVICE_NAME);
				String address = msg.getData().getString(
						BluetoothService.DEVICE_ADDRESS);
				cache.saveDeviceAddress(Cache.GMPUA, address);// �����ַ,�Ա��´��Դ�����
				break;
			// case UploadHelper.UPLOADJKCLINFO:// �ϴ�����������Ϣ�ķ���ֵ
			// Log.i("msg.arg1", msg.arg1 + "");
			// switch (msg.arg1) {
			//
			// case 0:// ����0����ת��Ϊascii��48
			// T.showShort(context, "�ϴ��ɹ���");
			// break;
			// default:
			// T.showShort(context, "�ϴ�ʧ��,�ѱ��浽���ݿ�");
			// // ����ϴ�ʧ�ܣ����浽�ϴ����� ��unUploadTable table
			// String xml = (String) msg.obj;
			// DataBaseManger dbManger = new DataBaseManger(context);
			// ContentValues cv = new ContentValues();
			// cv.put(TABLE.XML_STRING, xml);
			// long i = dbManger.insert(Tables.UNUPLOADJKCLTABLE, cv);
			// Log.i("Tables.UNUPLOADJKCLTABLE +i", i + "");
			// }
			// break;
			}
		}
	};

	/***
	 * �����յ������Ƿ��в�����ֵ��
	 * 
	 * @param readBuf
	 */
	private boolean check(byte[] readBuf) {
		for (byte b : readBuf) {
			receiveBytes.add(b);
		}
		int index = contains(receiveBytes, WBCSS);
		if (index != -1) {// ���õ�wbcss
			if (receiveBytes.size() >= index + 13) {
				List<Byte> sub = receiveBytes.subList(index, index + 13);
				byte[] subArray = new byte[sub.size()];
				for (int i = 0; i < sub.size(); i++)
					subArray[i] = sub.get(i);
				String result = new String(subArray);
				// System.out.println(result);
				String[] results = result.split("\\*");
				if (results.length < 2)
					return false;
				value = Float.valueOf(results[0].trim());
				unit = results[1].trim();
				return true;
			}
		}
		return false;
	}

	private int contains(List<Byte> bytes, byte[] buf) {
		int i = 0;
		int j = 0;
		while (i < bytes.size() && j < buf.length) {
			if (bytes.get(i).equals(Byte.valueOf(buf[j]))) {
				i++;
				j++;
			} else {
				i++;
				j = 0;// ����
			}
		}
		if (j == buf.length)
			return i;
		else
			return -1;
	}

	/**
	 * �������������豸��activity
	 */
	public void startDeviceListActivity(View view) {
		Intent serverIntent = new Intent(this, BluetoothListActivity.class);
		startActivityForResult(serverIntent,
				BluetoothListActivity.REQUEST_CONNECT_DEVICE);
	}

	/**
	 * �����������û�ָ�������豸�����ؽ�������
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.i(TAG, "onActivityResult " + resultCode);
		switch (requestCode) {
		case BluetoothListActivity.REQUEST_CONNECT_DEVICE:
			if (resultCode == Activity.RESULT_OK) {
				String address = data.getExtras().getString(
						BluetoothListActivity.EXTRA_DEVICE_ADDRESS);
				connectWbc(address);
			}
			break;
		}
	}

	/**
	 * ������������PC300�豸
	 * 
	 * @param address
	 */
	private void connectWbc(String address) {
		btMac = address;
		BluetoothDevice device = bluetoothService
				.getRemoteDeviceByAddress(address);
		bluetoothService.connect(device);
		HealthDevice.PersistWriter persistWriter = new HealthDevice.PersistWriter(
				bluetoothService, PC300.COMMAND_BETTERY, 3000);
		persistWriter.start();// ��������
	}

}
