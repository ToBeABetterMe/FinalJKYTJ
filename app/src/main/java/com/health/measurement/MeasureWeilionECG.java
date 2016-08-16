package com.health.measurement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.c102c.app.bluetooth.BluetoothListActivity;
import com.c102c.app.commu.Basesence;
import com.c102c.app.utils.Util;
import com.c102c.finalJKYTJ.R;
import com.health.util.T;

public class MeasureWeilionECG extends Activity implements OnClickListener {
	private Button startBtn;
	private Button anaBtn;
	private Button uploadBtn;
	private Button searchBtn;

	private TextView connectTV;
	private SurfaceView sfv;

	private Context context;

	public static boolean startFlag = false;
	private static String deviceName = "WeiLion";
	static OnClickListener clickListener;
	private static final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
	private BluetoothAdapter btAdapt;
	public static BluetoothSocket btSocket;
	private static ControlView clv;

	public static double m_plus = 1.0;
	public static double m_rate = 2.5;
	public static float m_HR = 0;

	private static Spinner sp_zousu;
	private static Spinner sp_gain;

	private static ArrayAdapter<String> zousu_SpAdapter;
	private static ArrayAdapter<String> gain_SpAdapter;
	private static ArrayList packEcgData = new ArrayList();
	private static File file = null;
	private static String result = null;
	private static WeiLionHandler handler = null;
	static EditText et_xinlv = null;
	private static ImageView icon;
	private static String token = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.weilion_ecg12);
		context = this;

		btAdapt = BluetoothAdapter.getDefaultAdapter();// 初始化本机蓝牙功能
		handler = new WeiLionHandler();
		initView();
		anaBtn.setEnabled(false);
		token = getIntent().getStringExtra("token");
		if (token != null) {
			uploadBtn.setText("测量结束");
		}
		deleteFile();
	}

	private void initView() {
		startBtn = (Button) findViewById(R.id.start_btn);
		anaBtn = (Button) findViewById(R.id.analysis_btn);
		uploadBtn = (Button) findViewById(R.id.upload_button);
		searchBtn = (Button) findViewById(R.id.find_device);
		connectTV = (TextView) findViewById(R.id.connect_status);
		et_xinlv = (EditText) findViewById(R.id.et_xinlv);
		icon = (ImageView) findViewById(R.id.xinlv_image);
		sfv = (SurfaceView) findViewById(R.id.sfv);
		sp_gain = (Spinner) findViewById(R.id.sp_gain);
		sp_zousu = (Spinner) findViewById(R.id.sp_sudu);
		zousu_SpAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, getResources()
						.getStringArray(R.array.select_zousu));
		zousu_SpAdapter.setDropDownViewResource(R.layout.main_spinner_dropdown);
		gain_SpAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, getResources()
						.getStringArray(R.array.select_gain));
		gain_SpAdapter.setDropDownViewResource(R.layout.main_spinner_dropdown);
		sp_gain.setAdapter(gain_SpAdapter);
		sp_zousu.setAdapter(zousu_SpAdapter);
		sp_gain.setSelection(1);
		sp_zousu.setSelection(1);
		sp_gain.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				switch (arg2) {
				case 0:
					m_plus = 0.5;
					break;
				case 1:
					m_plus = 1.0;
					break;
				case 2:
					m_plus = 2.0;
					break;

				default:
					break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				m_plus = 1.0;
			}
		});

		sp_zousu.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				switch (arg2) {
				case 0:
					m_rate = 5;
					break;
				case 1:
					m_rate = 2.5;
					break;
				case 2:
					m_rate = 1.25;
					break;

				default:
					break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				m_rate = 2.5;
			}
		});
		searchBtn.setOnClickListener(this);
		startBtn.setOnClickListener(this);
		anaBtn.setOnClickListener(this);
		uploadBtn.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.start_btn:
			// 开始心电
			try {
				if (btSocket != null && btSocket.getInputStream() != null) {
					startFlag = !startFlag;
					anaBtn.setEnabled(!startFlag);
				} else {
					T.showLong(context, "请先连接蓝牙设备！");
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (startFlag) {
				startBtn.setText("停止测量");
				try {
					deleteFile();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (btSocket != null && btSocket.getInputStream() != null) {
						clv = null;
						clv = new ControlView(context, sfv,
								btSocket.getInputStream(), handler);
						clv.start();
					} else {
						T.showLong(context, "请先连接蓝牙设备！");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (!startFlag) {
				startBtn.setText("开始测量");
				connectTV.setText("未连接");
				try {
					if (btSocket != null && btSocket.getInputStream() != null
							&& clv != null) {
						clv.stop();
						btSocket.getInputStream().close();
						btSocket.close();
						btSocket = null;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			break;
		case R.id.analysis_btn:
			// 分析数据
			anaBtn.setEnabled(false);
			try {
				String ecgpath = Environment.getExternalStorageDirectory()
						.getPath();
				String name = ecgpath + "/ecg.ecg";
				String path = ecgpath + "/";
				//system.out.println(ecgpath);
				try {
					stringFromJNI(name, path);
				} catch (Exception e) {
					//Log.i("!!!", "!!!");
					e.printStackTrace();
				}
				file = new File(ecgpath + "/001.txt");
				if (file.exists()) {
					//Log.i("001.txt", "exist");
					ReadThread readThread = new ReadThread(handler);
					readThread.run();
				} else {
					//Log.i("001.txt", "not exist");
					T.showShort(context, "分析结果文件不存在！");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case R.id.upload_button:
			// 完成测量
			Intent intent = new Intent();
			String[] values = result.split("\n");
			String[] ecgResult = values[10].split("：");
			intent.putExtra("xdjg", Arrays.toString(ecgResult));
			byte[] data = clv.getXinDianData();
			// //Log.i("心电数据", Util.printHexString(data));
			intent.putExtra("xdsj", Util.printHexString(data));
			setResult(Basesence.MEASURE_RESULT_XIN_DIAN, intent);
			finish();
			break;
		case R.id.find_device:
			// 查找设备//system.out.println("##################");
			Intent serverIntent = new Intent(MeasureWeilionECG.this,
					BluetoothListActivity.class);
			startActivityForResult(serverIntent,
					BluetoothListActivity.REQUEST_CONNECT_DEVICE);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		deleteFile();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		deleteFile();
	}

	/**
	 * 查找蓝牙后，用户指定连接设备，返回进行连接
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case BluetoothListActivity.REQUEST_CONNECT_DEVICE:
			if (resultCode == Activity.RESULT_OK) {
				String address = data.getExtras().getString(
						BluetoothListActivity.EXTRA_DEVICE_ADDRESS);
				//Log.i("address", address);
				UUID uuid = UUID.fromString(SPP_UUID);
				try {
					BluetoothDevice btDev = btAdapt.getRemoteDevice(address);
					Method m = btDev.getClass().getMethod("createRfcommSocket",
							new Class[] { int.class });
					btSocket = (BluetoothSocket) m.invoke(btDev, 1);
					// btSocket = btDev.createRfcommSocketToServiceRecord(uuid);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//Log.i("CHEN", "Low: Connection failed.", e);
					Toast.makeText(context, "连接失败,请重新尝试连接...",
							Toast.LENGTH_SHORT).show();
				}
				try {
					btSocket.connect();
					Toast.makeText(context, "连接成功", Toast.LENGTH_SHORT).show();
					connectTV.setText(deviceName);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(context, "连接失败,请重新尝试连接...",
							Toast.LENGTH_SHORT).show();
				}
			}
			break;
		}
	}

	public native String stringFromJNI(String name, String path);

	/*
	 * this is used to load the 'helloneon' library on application startup. The
	 * library has already been unpacked into
	 * /data/data/com.example.neon/lib/libhelloneon.so at installation time by
	 * the package manager.
	 */
	static {
		// System.loadLibrary("EcgPreprocess");
		System.loadLibrary("stlport_shared");
		System.loadLibrary("wla");
	}

	private String getTxtContent() {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		InputStreamReader inputStreamReader = null;
		try {
			inputStreamReader = new InputStreamReader(inputStream, "gbk");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(inputStreamReader);
		StringBuffer sb = new StringBuffer("");
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	private class WeiLionHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {

			case 0003:
				int xinlv = (int) (msg.obj);
				et_xinlv.setText(xinlv + "");
				if (xinlv > 160 || xinlv < 40)
					icon.setImageResource(R.drawable.light_red);
				else
					icon.setImageResource(R.drawable.light_blue);
				break;
			case 0013:
				// //Log.i("s","== = = = = ");
				int mm = (int) msg.obj;
				// Toast.makeText(MeasureWeilionECG.context, "第"+mm+"号可能脱落",
				// Toast.LENGTH_SHORT);
				T.showShort(context, "导联线可能脱落");
				// //Log.i("s","== = = = = ");
				break;
			case 0004:
				//Log.i("jkytj", "get a message which what is 0004");
				if (msg.arg1 == 1) {
					result = (String) msg.obj;
					String[] values = result.split("\n");
					String[] ecgResult = values[10].split("：");
					//Log.i("ecgResult", Arrays.toString(ecgResult));
					int drawable = R.drawable.light_blue;
					if (!ecgResult[1].trim().equals("窦性心律，")) {
						T.showShort(context, "心电图异常，请进一步检查！");
						drawable = R.drawable.light_red;
					}
					Dialog alertDialog = new AlertDialog.Builder(context)
							.setTitle("分析结果").setIcon(drawable)
							.setMessage(result).create();
					alertDialog.show();
					uploadBtn.setEnabled(true);
				} else if (msg.arg1 == 2) {
					Dialog adialog = new AlertDialog.Builder(context)
							.setTitle("answer").setMessage("amessage").create();
					adialog.show();
				} else {
					T.showShort(context, "未生成分析文件！");
				}
			}
		}
	}

	private class ReadThread implements Runnable {
		private Handler handler;

		public ReadThread(Handler handler) {
			this.handler = handler;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			String result = getTxtContent();
			if (result != null && result.length() > 0) {
				handler.obtainMessage(0004, 1, 0, result).sendToTarget();
			} else {
				handler.obtainMessage(0004, 2).sendToTarget();
			}
		}
	}

	private void deleteFile() {
		String ecgpath = Environment.getExternalStorageDirectory().getPath();
		File[] files = new File[] { new File(ecgpath + "/001.fil"),
				new File(ecgpath + "/001.pmg"), new File(ecgpath + "/001.qrs"),
				new File(ecgpath + "/001.tqrs"),
				new File(ecgpath + "/001.stseg"),
				new File(ecgpath + "/001.sum"), new File(ecgpath + "/001.trd"),
				new File(ecgpath + "/001.ecg"), new File(ecgpath + "/001.txt"),
				new File(ecgpath + "/ecg.ecg") };
		for (int i = 0; i < files.length; i++) {
			if (files[i].exists())
				files[i].delete();
		}
	}

}
