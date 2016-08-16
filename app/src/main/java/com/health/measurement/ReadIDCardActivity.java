package com.health.measurement;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.c102c.app.base.AppDB;
import com.c102c.app.commu.Basesence;
import com.c102c.app.commu.Fetch;
import com.c102c.app.health_record_activity.UserDetailActivity;
import com.c102c.app.health_record_activity.UsersListActivity;
import com.c102c.app.model.DiabetesFlup_down;
import com.c102c.app.model.DiabetesSpecial_down;
import com.c102c.app.model.HealthExamination;
import com.c102c.app.model.HealthRecord_down;
import com.c102c.app.model.HypertensionFlup_down;
import com.c102c.app.model.HypertensionSpecial_down;
import com.c102c.app.utils.Util;
import com.c102c.finalJKYTJ.R;
import com.ivsign.android.IDCReader.IDCReaderSDK;

public class ReadIDCardActivity extends Activity {

	BluetoothAdapter myBluetoothAdapter = null;
	BluetoothServerSocket mBThServer = null;
	BluetoothSocket mBTHSocket = null;
	InputStream mmInStream = null;
	OutputStream mmOutStream = null;
	int Readflage = -99;
	private Context context;
	private List<Object> temp = null;
	byte[] cmd_SAM = { (byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0x96,
			0x69, 0x00, 0x03, 0x12, (byte) 0xFF, (byte) 0xEE };
	byte[] cmd_find = { (byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0x96,
			0x69, 0x00, 0x03, 0x20, 0x01, 0x22 };
	byte[] cmd_selt = { (byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0x96,
			0x69, 0x00, 0x03, 0x20, 0x02, 0x21 };
	byte[] cmd_read = { (byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0x96,
			0x69, 0x00, 0x03, 0x30, 0x01, 0x32 };
	byte[] cmd_sleep = { (byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0x96,
			0x69, 0x00, 0x02, 0x00, 0x02 };
	byte[] cmd_weak = { (byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0x96,
			0x69, 0x00, 0x02, 0x01, 0x03 };
	byte[] recData = new byte[1500];

	String DEVICE_NAME1 = "CVR-100B";
	String DEVICE_NAME2 = "IDCReader";
	String DEVICE_NAME3 = "COM2";
	String DEVICE_NAME4 = "BOLUTEK";

	UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
	String[] decodeInfo = new String[10];

	private static final String TAG = "IDCReaderSDK";
	private String token;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.read_id_card);

		Button btconn = (Button) findViewById(R.id.btn_find_id_reader);
		btconn.getBackground().setAlpha(160);
		Button btread = (Button) findViewById(R.id.btnInit);
		btread.getBackground().setAlpha(160);
		Button btclose = (Button) findViewById(R.id.btn_off);
		btclose.getBackground().setAlpha(160);
		// final EditText ett = (EditText)findViewById(R.id.editText1);
		final TextView ett = (TextView) findViewById(R.id.tv_info);
		final ImageView image = (ImageView) findViewById(R.id.ivImageview);
		// image.getBackground().setAlpha(50);
		token = getIntent().getExtras().getString("extra");
		context = this;
		btconn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
				Set<BluetoothDevice> pairedDevices = myBluetoothAdapter
						.getBondedDevices();
				if (pairedDevices.size() > 0) {
					for (Iterator<BluetoothDevice> iterator = pairedDevices
							.iterator(); iterator.hasNext();) {
						BluetoothDevice device = (BluetoothDevice) iterator
								.next();
						if (DEVICE_NAME1.equals(device.getName())
								|| DEVICE_NAME2.equals(device.getName())
								|| DEVICE_NAME3.equals(device.getName())
								|| DEVICE_NAME4.equals(device.getName())) {
							try {
								myBluetoothAdapter.enable();
								Intent discoverableIntent = new Intent(
										BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);// 使得蓝牙处于可发现模式，持续时间150s
								discoverableIntent
										.putExtra(
												BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,
												150);
								mBTHSocket = device
										.createRfcommSocketToServiceRecord(MY_UUID);

								int sdk = Integer.parseInt(Build.VERSION.SDK);
								if (sdk >= 10) {
									mBTHSocket = device
											.createInsecureRfcommSocketToServiceRecord(MY_UUID);
								} else {
									mBTHSocket = device
											.createRfcommSocketToServiceRecord(MY_UUID);
								}

								mBThServer = myBluetoothAdapter
										.listenUsingRfcommWithServiceRecord(
												"myServerSocket", MY_UUID);
								mBTHSocket.connect();
								mmInStream = mBTHSocket.getInputStream();
								mmOutStream = mBTHSocket.getOutputStream();

							} catch (IOException e) {
								ett.setText("设备连接异常！");
							}
							if ((mmInStream != null) && (mmInStream != null)) {
								ett.setText("设备连接成功！");
							} else {
								ett.setText("设备连接失败！");
							}
							break;
						}
					}
				}
			}
		});

		btread.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
				Boolean get = false;
				int readcount = 15;
				try {
					while (readcount > 1) {
						ReadCard();
						readcount = readcount - 1;
						if (Readflage > 0) {
							readcount = 0;
							if (token != null) {
								if (token.equals("shuaka")) {
									try {
										close();
										// Log.i("decodeInfo[5]",
										// decodeInfo[5]);
										AppDB.getInstance(context);
										temp = AppDB
												.get(new HealthRecord_down());
										for (Object object : temp) {
											HealthRecord_down healthRecord_down = (HealthRecord_down) object;
											if (healthRecord_down.getIdCard()
													.equals(decodeInfo[5])) {
												Basesence
														.setTempHealthRecord_down(healthRecord_down);
												get = true;
												break;
											}
										}
										if (get) {
											Intent intent = new Intent(context,
													UserDetailActivity.class);
											startActivity(intent);
										} else {
											Toast.makeText(context,
													"该用户不存在从后台获取",
													Toast.LENGTH_SHORT).show();
											String cont = "<requestParams pageStart=\"1\" pageSize=\"500\"><healthRecord>"
													+ "<householdRegisterCode>"
													+ Basesence
															.getAdminAreaCode()
													+ "</householdRegisterCode><name>"
													+ decodeInfo[0].trim()
													+ "</name>"
													+ "</healthRecord></requestParams>";
											Fetch.communicate("M0100020101",
													ReadIDCardActivity.this,
													handler, cont);
										}
									} catch (Exception e) {
										e.printStackTrace();
									}
								} else {
									close();
									Intent intent = getIntent();
									Bundle bundle = new Bundle();
									bundle.putString("name",
											decodeInfo[0].trim());
									bundle.putString("sex",
											decodeInfo[1].trim());
									bundle.putString("nation",
											decodeInfo[2].trim());
									bundle.putString(
											"birthday",
											BirthdayFormat(decodeInfo[3].trim()));
									bundle.putString("address",
											decodeInfo[4].trim());
									bundle.putString("IDNo",
											decodeInfo[5].trim());
									intent.putExtras(bundle);
									setResult(0x86, intent);
									finish();
								}
							}

							if (Readflage == 1) {
								FileInputStream fis = new FileInputStream(
										Environment
												.getExternalStorageDirectory()
												+ "/wltlib/zp.bmp");
								Bitmap bmp = BitmapFactory.decodeStream(fis);
								fis.close();
								image.setImageBitmap(bmp);
							} else {
								ett.append("照片解码失败，请检查路径"
										+ Environment
												.getExternalStorageDirectory()
										+ "/wltlib/");
								// image.setImageBitmap(BitmapFactory.decodeResource(getResources(),
								// R.drawable.face));
							}
						} else {
							// image.setImageBitmap(BitmapFactory.decodeResource(getResources(),
							// R.drawable.face));
							if (Readflage == -2) {
								ett.setText("蓝牙连接异常");
							}
							if (Readflage == -3) {
								if (decodeInfo[0] != null
										&& decodeInfo[0].length() > 0)
									ett.setText(decodeInfo[0]);
							}
							if (Readflage == -4) {
								if (decodeInfo[0] != null
										&& decodeInfo[0].length() > 0)
									ett.setText(decodeInfo[0]);
							}
							if (Readflage == -5) {
								ett.setText("读卡失败");
							}
							if (Readflage == -99) {
								ett.setText("操作异常");
							}
						}
						Thread.sleep(100);
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					ett.setText("读取数据异常！");
					// image.setImageBitmap(BitmapFactory.decodeResource(getResources(),
					// R.drawable.face));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					ett.setText("读取数据异常！");
					// image.setImageBitmap(BitmapFactory.decodeResource(getResources(),
					// R.drawable.face));
				}
			}
		});

		btclose.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				try {
					if ((mmInStream == null) || (mmInStream == null)) {
						return;
					}
					mmOutStream.close();
					mmInStream.close();
					mBTHSocket.close();
					mBThServer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == Basesence.commusucc) {
				String a;
				a = (String) msg.obj;
				switch (msg.arg1) {
				case Basesence.M0100020101:// 健康档案下载

					if (a.contains("未查询到")) {
						Toast.makeText(context, "后台也不存在该用户", Toast.LENGTH_SHORT)
								.show();
					} else {
						List<HealthRecord_down> mHealthRecord_downList = new ArrayList<HealthRecord_down>(
								Util.getHealthRecord_down(a));
						for (HealthRecord_down h : mHealthRecord_downList) {
							AppDB.add(h);
						}
					}
					Intent intent = new Intent(ReadIDCardActivity.this,
							UsersListActivity.class);
					intent.putExtra("name", decodeInfo[0].trim());
					startActivity(intent);
					finish();
					break;
				default:
					break;
				}
			} else if (msg.what == Basesence.commufail) {
				if (msg.obj == null)
					Toast.makeText(ReadIDCardActivity.this, "接口调用失败",
							Toast.LENGTH_SHORT).show();
				else
					Toast.makeText(ReadIDCardActivity.this, "接口调用失败" + msg.obj,
							Toast.LENGTH_SHORT).show();
				// loadUsersList(mHealthRecord_downList);
			}
		}
	};

	private void close() {
		try {
			if ((mmInStream == null) || (mmInStream == null)) {
				return;
			}
			mmOutStream.close();
			mmInStream.close();
			mBTHSocket.close();
			mBThServer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void ReadCard() {
		try {
			if ((mmInStream == null) || (mmInStream == null)) {
				Readflage = -2;// 连接异常
				return;
			}
			mmOutStream.write(cmd_find);
			Thread.sleep(200);
			int datalen = mmInStream.read(recData);
			if (recData[9] == -97) {
				mmOutStream.write(cmd_selt);
				Thread.sleep(200);
				datalen = mmInStream.read(recData);
				if (recData[9] == -112) {
					mmOutStream.write(cmd_read);
					Thread.sleep(1000);
					byte[] tempData = new byte[1500];
					if (mmInStream.available() > 0) {
						datalen = mmInStream.read(tempData);
					} else {
						Thread.sleep(500);
						if (mmInStream.available() > 0) {
							datalen = mmInStream.read(tempData);
						}
					}
					int flag = 0;
					if (datalen < 1294) {
						for (int i = 0; i < datalen; i++, flag++) {
							recData[flag] = tempData[i];
						}
						Thread.sleep(1000);
						if (mmInStream.available() > 0) {
							datalen = mmInStream.read(tempData);
						} else {
							Thread.sleep(500);
							if (mmInStream.available() > 0) {
								datalen = mmInStream.read(tempData);
							}
						}
						for (int i = 0; i < datalen; i++, flag++) {
							recData[flag] = tempData[i];
						}

					} else {
						for (int i = 0; i < datalen; i++, flag++) {
							recData[flag] = tempData[i];
						}
					}
					tempData = null;
					if (flag == 1295) {
						if (recData[9] == -112) {

							byte[] dataBuf = new byte[256];
							for (int i = 0; i < 256; i++) {
								dataBuf[i] = recData[14 + i];
							}
							String TmpStr = new String(dataBuf, "UTF16-LE");
							TmpStr = new String(TmpStr.getBytes("UTF-8"));
							decodeInfo[0] = TmpStr.substring(0, 15);
							decodeInfo[1] = TmpStr.substring(15, 16);
							decodeInfo[2] = TmpStr.substring(16, 18);
							decodeInfo[3] = TmpStr.substring(18, 26);
							decodeInfo[4] = TmpStr.substring(26, 61);
							decodeInfo[5] = TmpStr.substring(61, 79);
							decodeInfo[6] = TmpStr.substring(79, 94);
							decodeInfo[7] = TmpStr.substring(94, 102);
							decodeInfo[8] = TmpStr.substring(102, 110);
							decodeInfo[9] = TmpStr.substring(110, 128);

							/*
							 * try { int code =
							 * Integer.parseInt(decodeInfo[2].toString());
							 * decodeInfo[2] = decodeNation(code); } catch
							 * (Exception e) { decodeInfo[2] = ""; }
							 */

							// 照片解码
							try {
								int ret = IDCReaderSDK.Init();
								if (ret == 0) {
									byte[] datawlt = new byte[1384];
									byte[] byLicData = { (byte) 0x05,
											(byte) 0x00, (byte) 0x01,
											(byte) 0x00, (byte) 0x5B,
											(byte) 0x03, (byte) 0x33,
											(byte) 0x01, (byte) 0x5A,
											(byte) 0xB3, (byte) 0x1E,
											(byte) 0x00 };
									for (int i = 0; i < 1295; i++) {
										datawlt[i] = recData[i];
									}
									int t = IDCReaderSDK.unpack(datawlt,
											byLicData);
									if (t == 1) {
										Readflage = 1;// 读卡成功
									} else {
										Readflage = 6;// 照片解码异常
									}
								} else {
									Readflage = 6;// 照片解码异常
								}
							} catch (Exception e) {
								Readflage = 6;// 照片解码异常
							}

						} else {
							Readflage = -5;// 读卡失败！
						}
					} else {
						Readflage = -5;// 读卡失败
					}
				} else {
					Readflage = -4;// 选卡失败
				}
			} else {
				Readflage = -3;// 寻卡失败
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			Readflage = -99;// 读取数据异常
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			Readflage = -99;// 读取数据异常
		}
	}

	private String BirthdayFormat(String substring) {
		// TODO Auto-generated method stub
		return substring.substring(0, 4) + "-" + substring.substring(4, 6)
				+ "-" + substring.substring(6);
	}

}
