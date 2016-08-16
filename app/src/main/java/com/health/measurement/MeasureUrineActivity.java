package com.health.measurement;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.c102c.app.bluetooth.BluetoothListActivity;
import com.c102c.app.bluetooth.BluetoothService;
import com.c102c.app.commu.Basesence;
import com.c102c.finalJKYTJ.R;
import com.health.measurement.GmpUa.UaRecord;

/**
 * 尿液分析仪,可以测量 白细胞 亚硝酸盐 尿胆原 蛋白质 pH值 潜血 比重 酮体 胆红素 葡萄糖 维生素C 12个项目
 * 
 * @author jiqunpeng
 * 
 *         创建时间：2013-10-30 上午10:39:37
 */
public class MeasureUrineActivity extends Activity {

	private static TextView statusView = null;// 蓝牙连接状态
	private static String btName = "EMP-UI";// 蓝牙名称
	private static String btMac;// 蓝牙mac
	private static UAHandler handler = null;
	private static Context context;
	private static Button homeButton;// 返回主界面按钮
	private static Button returnButton;// 返回上一步按钮
	private static Button uploadButton;// 上次数据按钮
	private static Button getDataButton;// 获取最新数据按钮
	private static Button findButton;// 查找设备按钮
	private static EditText leuEditText;
	private static EditText nitEditText;
	private static EditText ubgEditText;
	private static EditText proEditText;
	private static EditText phEditText;
	private static EditText sgEditText;
	private static EditText bldEditText;
	private static EditText ketEditText;
	private static EditText bilEditText;
	private static EditText gluEditText;
	private static EditText vcEditText;
	private static EditText dateEditText;

	private static ImageView imageView1;
	private static ImageView imageView2;
	private static ImageView imageView3;
	private String dataStr;
	private static final String TAG = "MeasureUrineActivity";
	// private static final int UPLOADE_RESULT = 0x10245;
	private static BluetoothService bluetoothService = null;
	OnClickListener clickListener;
	EditText user;
	String username;
	int userid;

	private static Cache cache;
	private static String grxh;

	static String nit = "";
	static String ubg = "";
	static String pro = "";
	static String ph = "";
	static String sg = "";
	static String bld = "";
	static String ket = "";
	static String bil = "";
	static String uglu = "";
	static String vc = "";
	static String leu = "";
	static String time = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.measure_urine);
		context = this;
		cache = new Cache(context);

		if (handler == null)
			handler = new UAHandler();
		findViewid();
		setOnClickListener();// 设置监听器
		bluetoothService = BluetoothService.getService(handler, false);
		// connectGmpUa();
		setConnectState();
//		uploadButton.setEnabled(false);
	}

	/**
	 * 设置监听器
	 */
	private void setOnClickListener() {
		clickListener = new OnClickListener() {
			@Override
			public void onClick(View view) {

				if (view == homeButton) {
					MeasureUrineActivity.this.setResult(RESULT_OK);
					MeasureUrineActivity.this.finish();
				} else if (view == returnButton) {
					MeasureUrineActivity.this.finish();
				} else if (view == getDataButton) {
					sendCommd(GmpUa.COMMAND_SINGLE_DATA);
//					uploadButton.setEnabled(true);// 获取数据后才能上传数据

				} else if (view == findButton) {
					startDeviceListActivity();// 开启查找蓝牙activity
				} else if (view == uploadButton) {
					pro = proEditText.getText().toString();
					nit = nitEditText.getText().toString();
					ubg = ubgEditText.getText().toString();
					ph = phEditText.getText().toString();
					sg = sgEditText.getText().toString();
					bld = bldEditText.getText().toString();
					ket = ketEditText.getText().toString();
					bil = bilEditText.getText().toString();
					uglu = gluEditText.getText().toString();
					vc = vcEditText.getText().toString();
					leu = leuEditText.getText().toString();
					time = dateEditText.getText().toString();
//					uploadButton.setEnabled(false);// 按钮不可用，避免多次上传{
					Intent intent = new Intent();
					intent.putExtra("ndb", pro);
					intent.putExtra("nqx", bld);
					intent.putExtra("nt", uglu);
					intent.putExtra("ntt", ket);

					intent.putExtra("nqt", "nit:" + nit + "," + "ubg:" + ubg
							+ "," + "ph:" + ph + "," + "sg:" + sg + ","
							+ "bil:" + bil + "," + "vc:" + vc + "," + "leu:"
							+ leu + "," + "time:" + time);
					setResult(Basesence.MEASURE_RESULT_URINE, intent);
					finish();
				}
			}
		};
		// homeButton.setOnClickListener(clickListener);
		// returnButton.setOnClickListener(clickListener);
		uploadButton.setOnClickListener(clickListener);
		// returnButton.setOnClickListener(clickListener);
		getDataButton.setOnClickListener(clickListener);
		findButton.setOnClickListener(clickListener);
	}

	protected void upload() {
		// ExecutorService exec =
		// Executors.newSingleThreadExecutor();// 单线程池
		leu = leuEditText.getText().toString();
		if (leu.length() > 0) {
			StringBuilder builder = new StringBuilder();
			nit = nitEditText.getText().toString();
			ubg = ubgEditText.getText().toString();
			pro = proEditText.getText().toString();
			ph = phEditText.getText().toString();
			sg = sgEditText.getText().toString();
			bld = bldEditText.getText().toString();
			ket = ketEditText.getText().toString();
			bil = bilEditText.getText().toString();
			uglu = gluEditText.getText().toString();
			vc = vcEditText.getText().toString();
			time = dateEditText.getText().toString();
			builder.append("亚硝酸盐(nit):" + nit + "\t");
			builder.append("尿胆原(ubg):" + ubg + "\t");
			builder.append("蛋白质(pro):" + pro + "\t");
			builder.append("pH值(ph):" + ph + "\t");
			builder.append("潜血(bld):" + bld + "\t");
			builder.append("比重(sg):" + sg + "\t");
			builder.append("酮体(ket):" + ket + "\t");
			builder.append("胆红素(bil):" + bil + "\t");
			builder.append("血糖(glu):" + uglu + "\t");
			builder.append("维生素C(vc):" + vc + "\t");
			dataStr = builder.toString();

		}
	}

	/**
	 * 初始化控件
	 */
	private void findViewid() {
		imageView1 = (ImageView) findViewById(R.id.urine_status_image1);
		imageView2 = (ImageView) findViewById(R.id.urine_status_image2);
		imageView3 = (ImageView) findViewById(R.id.urine_status_image3);
		// homeButton = (Button) findViewById(R.id.to_home_button);
		// returnButton = (Button) findViewById(R.id.return_button);
		uploadButton = (Button) findViewById(R.id.upload_button);
		getDataButton = (Button) findViewById(R.id.get_data_button);
		leuEditText = (EditText) findViewById(R.id.leu);
		nitEditText = (EditText) findViewById(R.id.nit);
		ubgEditText = (EditText) findViewById(R.id.ubg);
		proEditText = (EditText) findViewById(R.id.pro);
		phEditText = (EditText) findViewById(R.id.pH);
		sgEditText = (EditText) findViewById(R.id.sg);
		bldEditText = (EditText) findViewById(R.id.bld);
		ketEditText = (EditText) findViewById(R.id.ket);
		bilEditText = (EditText) findViewById(R.id.bil);
		gluEditText = (EditText) findViewById(R.id.glu);
		vcEditText = (EditText) findViewById(R.id.vc);
		dateEditText = (EditText) findViewById(R.id.measure_time);
		statusView = (TextView) findViewById(R.id.connect_status);
		findButton = (Button) findViewById(R.id.find_device);
	}

	/**
	 * 设置连接状态
	 */
	private void setConnectState() {
		setVisibility();
		if (bluetoothService == null) {
			statusView.setText(R.string.unconnect);
			return;
		}
		switch (bluetoothService.getState()) {
		case BluetoothService.STATE_CONNECTING:
			statusView.setText(R.string.connecting);
			break;
		case BluetoothService.STATE_CONNECTED:
			statusView.setText(btName);
			break;
		case BluetoothService.STATE_NONE:
			statusView.setText(R.string.unconnect);
			break;
		}
	}

	/**
	 * 开启查找蓝牙设备的activity
	 */
	private void startDeviceListActivity() {
		Intent serverIntent = new Intent(this, BluetoothListActivity.class);
		startActivityForResult(serverIntent,
				BluetoothListActivity.REQUEST_CONNECT_DEVICE);
	}

	/**
	 * 连接尿液分析仪设备
	 */
	private void connectGmpUa() {
		if (bluetoothService.getState() == BluetoothService.STATE_NONE) {
			String address = cache.getDeviceAddress(Cache.GMPUA);
			btMac = address;
			BluetoothDevice device = bluetoothService
					.getBondedDeviceByAddress(address);
			if (device != null) {
				bluetoothService.connect(device);// 连接设备
				HealthDevice.PersistWriter persistWriter = new HealthDevice.PersistWriter(
						bluetoothService, GmpUa.CONFIRM, 10000);
				if (!persistWriter.isAlive())
					persistWriter.start();// 持续握手
			} else {
				Toast.makeText(context, "无已经绑定设备，试试查找设备...", Toast.LENGTH_LONG)
						.show();// 没有配对过得设备，启动查找
			}
		}
	}

	/**
	 * 连接搜索到的尿液分析仪设备
	 * 
	 * @param address
	 */
	private void connectGmpUa(String address) {
		// Log.i("address", address);
		btMac = address;
		BluetoothDevice device = bluetoothService
				.getRemoteDeviceByAddress(address);
		bluetoothService.connect(device);
		HealthDevice.PersistWriter persistWriter = new HealthDevice.PersistWriter(
				bluetoothService, GmpUa.CONFIRM, 10000);
		persistWriter.start();// 持续握手
	}

	private static class UAHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case BluetoothService.MESSAGE_STATE_CHANGE:
				switch (msg.arg1) {
				case BluetoothService.STATE_CONNECTED:
					statusView.setText(btName);
					Toast.makeText(context, "已连接到" + btName, Toast.LENGTH_LONG)
							.show();
					setVisibility();// 设置测量按钮可见
					break;
				case BluetoothService.STATE_CONNECTING:
					statusView.setText(R.string.connecting);
					setVisibility();
					break;
				case BluetoothService.STATE_NONE:
					statusView.setText(R.string.unconnect);
					setVisibility();
					break;
				}
				break;

			case BluetoothService.MESSAGE_WRITE:
				byte[] writeBuf = (byte[]) msg.obj;
				new String(writeBuf);
				// Toast.makeText(context,
				// writeMessage,
				// Toast.LENGTH_LONG).show();
				break;
			case BluetoothService.MESSAGE_READ:
				byte[] readBuf = (byte[]) msg.obj;
				// Log.i(TAG, "get:" + Arrays.toString(readBuf));
				SparseArray<List<byte[]>> map = GmpUa
						.getLegalPatternsFromBuffer(readBuf);
				int dataSize = map.size();
				for (int i = 0; i < dataSize; i++) {
					int token = map.keyAt(i);// 获取token
					List<byte[]> datas = map.get(token);
					switch (token) {
					case GmpUa.TOKEN_SINGLE_DATA:
						GmpUa.UaRecord record = GmpUa.parseRecord(datas
								.get(datas.size() - 1));
						if (null == record) {
							Toast.makeText(context, "无存储数据", Toast.LENGTH_LONG)
									.show();
						} else
							showRecord(record);// 显示记录
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
				cache.saveDeviceAddress(Cache.GMPUA, address);// 保存地址,以便下次自带连接
				break;
			/*
			 * case UploadHelper.UPLOADJKCLINFO:// 上传健康测量信息的返回值
			 * //Log.i("msg.arg1", msg.arg1 + ""); switch (msg.arg1) {
			 * 
			 * case 0:// 整形0向上转型为ascii码48 T.showShort(context, "上传成功！"); if (leu
			 * != null && leu.length() > 0) { setImageView(imageView1, 0);
			 * setImageView(imageView2, 0); setImageView(imageView3, 0); }
			 * break; default: T.showShort(context, "上传失败,已保存到数据库"); //
			 * 如果上传失败，保存到上传表中 即unUploadTable table String xml = (String)
			 * msg.obj; DataBaseManger dbManger = new DataBaseManger(context);
			 * ContentValues cv = new ContentValues(); cv.put(TABLE.XML_STRING,
			 * xml); long i = dbManger.insert(Tables.UNUPLOADJKCLTABLE, cv);
			 * //Log.i("Tables.UNUPLOADJKCLTABLE +i", i + ""); }
			 */
			// break;
			}
		}
	};

	/**
	 * 设置几个button的显示与隐藏
	 * 
	 * @param status
	 */
	private static void setVisibility() {
		int status;
		if (bluetoothService != null
				&& bluetoothService.getState() == BluetoothService.STATE_CONNECTED) {
			status = View.VISIBLE;// 连接时设置可见
		} else {
			status = View.INVISIBLE;// 未连接时设置不可见
		}
		getDataButton.setVisibility(status);
	}

	/**
	 * 发送命令
	 * 
	 * @param command
	 */
	private static void sendCommd(byte[] command) {
		// Log.i(TAG, "send:" + Arrays.toString(command));
		if (bluetoothService.getState() == BluetoothService.STATE_CONNECTED
				&& command != null)
			bluetoothService.write(command);
	}

	/**
	 * 显示记录
	 * 
	 * @param record
	 */
	public static void showRecord(UaRecord record) {
		leuEditText.setText(record.leu);
		nitEditText.setText(record.nit);
		ubgEditText.setText(record.ubg);
		proEditText.setText(record.pro);
		phEditText.setText(record.ph);
		sgEditText.setText(record.sg);
		bldEditText.setText(record.bld);
		ketEditText.setText(record.ket);
		bilEditText.setText(record.bil);
		gluEditText.setText(record.glu);
		vcEditText.setText(record.vc);
		dateEditText.setText(record.date);
	}

	private static void setImageView(ImageView imageview, int status) {
		if (status == 0)
			imageview.setImageResource(R.drawable.light_greed);
		else if (status == -1)
			imageview.setImageResource(R.drawable.light_blue);
		else
			imageview.setImageResource(R.drawable.light_red);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case BluetoothListActivity.REQUEST_CONNECT_DEVICE:
			if (resultCode == Activity.RESULT_OK) {
				String address = data.getExtras().getString(
						BluetoothListActivity.EXTRA_DEVICE_ADDRESS);
				connectGmpUa(address);
			}
			break;
		}
	}
}
