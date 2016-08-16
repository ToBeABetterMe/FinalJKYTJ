package com.health.measurement;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.c102c.app.commu.Basesence;
import com.c102c.finalJKYTJ.R;

public class ETempActivity extends Activity implements OnClickListener {

	private static String btName = "AET-R161E";// 蓝牙名称
	private static String btMac = null;// 蓝牙mac
	public static final String TAG = "ETempActivity";

	private BluetoothServerSocket mBThServer = null;
	private BluetoothSocket mBTHSocket = null;
	private InputStream mmInStream = null;
	private OutputStream mmOutStream = null;
	private UUID MY_UUID = UUID
			.fromString("00001101-0000-1000-8000-00805F9B34FB");

	private TextView mConnectStatus;
	private EditText mETempEditText;
	private Button mFindDevice;
	private Button mFinishMeasure;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.etemp_activity_layout);
		mConnectStatus = (TextView) findViewById(R.id.connect_status);
		mETempEditText = (EditText) findViewById(R.id.e_tmp);
		mFindDevice = (Button) findViewById(R.id.find_device);
		mFinishMeasure = (Button) findViewById(R.id.upload_button);

		mFindDevice.setOnClickListener(this);
		mFinishMeasure.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.find_device: // Toast.makeText(this, "单击查找设备",
			int i = 0;

			BluetoothAdapter myBluetoothAdapter = BluetoothAdapter
					.getDefaultAdapter();
			if (myBluetoothAdapter.getState() != BluetoothAdapter.STATE_ON) {
				myBluetoothAdapter.enable();
			}
			Set<BluetoothDevice> pairedDevices = myBluetoothAdapter
					.getBondedDevices();
			if (pairedDevices.size() > 0) {
				for (Iterator<BluetoothDevice> iterator = pairedDevices
						.iterator(); iterator.hasNext();) {
					BluetoothDevice device = (BluetoothDevice) iterator.next();
					if (btName.equals(device.getName())) {
						try {
							// myBluetoothAdapter.en able();

							Intent discoverableIntent = new Intent(
									BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);// 使得蓝牙处于可发现模式，持续时间150s

							discoverableIntent
									.putExtra(
											BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,
											250);

							mBTHSocket = device
									.createInsecureRfcommSocketToServiceRecord(MY_UUID);

							mBThServer = myBluetoothAdapter
									.listenUsingRfcommWithServiceRecord(
											"myServerSocket", MY_UUID);

							while (i < 5) {
								mBTHSocket.connect();
								mmInStream = mBTHSocket.getInputStream();
								mmOutStream = mBTHSocket.getOutputStream();
								if (mmInStream != null)
									break;
								i++;
							}

						} catch (IOException e) {
							try {
								mBTHSocket.connect();
								mmInStream = mBTHSocket.getInputStream();
								mmOutStream = mBTHSocket.getOutputStream();
							} catch (IOException e1) {
								// TODO Auto-generated catch block

								e1.printStackTrace();
							}

						}
						if ((mmOutStream != null) && (mmInStream != null)
								&& mBTHSocket != null) {

							Toast.makeText(ETempActivity.this, "设备连接成功！",
									Toast.LENGTH_SHORT).show();
							mConnectStatus.setText("已连接：" + "AET-R161E");

							new Thread() {
								public void run() {

									while (true) {

										byte[] buff = new byte[1024];

										String data = "";

										try {
											int len = mmInStream.read(buff);
											//Log.i(TAG, Arrays.toString(buff));
											//Log.i(TAG, "len= " + len);
											int i = len;
											for (; i >= 0; i--)
												if (buff[i] == (byte) 0xfb
														&& buff[i + 1] == (byte) 0x01) {
													int t1 = buff[i + 2] & 0xff;
													int t2 = buff[i + 3] & 0xff;

													int tt = (int) ((t1 * 256 + t2) / 10);
													double nowTemp = ((double) tt * 10) / 100;
													//Log.i("#####", nowTemp + "");

													data = nowTemp + "";

												}
											if (!TextUtils.isEmpty(data)) {
												//Log.i(TAG, data);
												handler.obtainMessage(8, data)
														.sendToTarget();
											}

										} catch (IOException e) {
											// TODO Auto-generated catch block

											// //Log.i(TAG, "adasdas");
											handler.obtainMessage(9)
													.sendToTarget();
										} catch (Exception e) {
											// TODO: handle exception

											e.printStackTrace();
										}

									}

								};
							}.start();

						} else {
							mConnectStatus.setText("未连接");
							Toast.makeText(ETempActivity.this, "设备连接失败！",
									Toast.LENGTH_SHORT).show();
						}
						break;
					}
				}
			}
			break;

		case R.id.upload_button:
			Intent intent = new Intent();
			intent.putExtra(TAG, mETempEditText.getText().toString());
			setResult(Basesence.MEASURE_RESULT_ETMP, intent);
			finish();
			break;

		default:
			break;
		}
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 8:
				mETempEditText.setText((String) msg.obj);
				break;

			default:
				break;
			}
		};
	};

}
