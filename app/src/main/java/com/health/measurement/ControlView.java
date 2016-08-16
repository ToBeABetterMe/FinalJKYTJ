package com.health.measurement;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceView;

public class ControlView {

	private SurfaceView sfv;
	private Canvas canvas;
	private Paint mPaint;
	public static boolean flag = true;
	private Context context;
	public DrawThread drawThread;
	private InputStream btInput = null;// 蓝牙数据输入流
	public static boolean isNeed = false;
	public static boolean isStop = true; // 控制线程
	private ArrayList<int[]> content;
	private byte[] head = null;
	private byte[] tail = null;
	
	private byte[] data = null;

	private ArrayList<Integer> usedlistV1;
	private ArrayList<Integer> usedlistV2;
	private ArrayList<Integer> usedlistV3;
	private ArrayList<Integer> usedlistV4;
	private ArrayList<Integer> usedlistV5;
	private ArrayList<Integer> usedlistV6;
	private ArrayList<Integer> usedlistI;
	private ArrayList<Integer> usedlistII;
	private ArrayList<Integer> usedlistIII;
	private ArrayList<Integer> usedlistaVR;
	private ArrayList<Integer> usedlistaVL;
	private ArrayList<Integer> usedlistaVF;
	private ArrayList<int[]> tmpValues;
	private ArrayList<Integer> usedlist;
	private int count;
	private static ArrayList<Byte> packEcgData;
	private static int points;
	private static int SIZE;
	private static Handler handler;

	private static int m_iBufCnt;
	private static int m_iRRnumber;
	private static int m_iFix;
	private static int m_Threshold;// 根据实际情况进行调整，波形幅度大就将值调大，反之调小
	private static int m_RIndex;
	private static int m_lSum;
	private static int m_TimeOut;
	private static int m_preRRnumber;
	private static final int BUF_SIZE = 16;
	private static final int TIME_OUT = 750;
	private static int[] m_iFixBuf = { 0, 0, 0 };
	private static int[] m_CntBuf = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0 };
	public CaculateConnect[] listconnect = new CaculateConnect[8];

	public ControlView(Context context, SurfaceView sfv, InputStream btInput,
			Handler handler) {
		// TODO Auto-generated constructor stub
		// listconnect = new CaculateConnect[8];
		for (int mm = 0; mm < 8; mm++) {
			listconnect[mm] = new CaculateConnect();
			listconnect[mm].SetThreshold(600);
		}
		flag = true;
		this.sfv = sfv;
		this.btInput = btInput;
		this.context = context;
		content = new ArrayList<int[]>();
		mPaint = new Paint();
		this.handler = handler;
		mPaint.setColor(Color.rgb(0, 128, 64));
		count = 0;
		points = 0;
		SIZE = 10;
		m_iBufCnt = 0;
		m_iRRnumber = 0;
		m_iFix = 0;
		m_Threshold = 300;// 根据实际情况进行调整，波形幅度大就将值调大，反之调小
		m_RIndex = 0;
		m_lSum = 0;
		m_TimeOut = 0;
		m_preRRnumber = 0;
		// BUF_SIZE = 16;
		// TIME_OUT = 750;
		for (int i = 0; i < m_iFixBuf.length; i++) {
			m_iFixBuf[i] = 0;
		}
		for (int i = 0; i < m_CntBuf.length; i++) {
			m_CntBuf[i] = 0;
		}
		head = null;
		tail = null;
		usedlistV1 = new ArrayList<Integer>();
		usedlistV2 = new ArrayList<Integer>();
		usedlistV3 = new ArrayList<Integer>();
		usedlistV4 = new ArrayList<Integer>();
		usedlistV5 = new ArrayList<Integer>();
		usedlistV6 = new ArrayList<Integer>();
		usedlistI = new ArrayList<Integer>();
		usedlistII = new ArrayList<Integer>();
		usedlistIII = new ArrayList<Integer>();
		usedlistaVR = new ArrayList<Integer>();
		usedlistaVL = new ArrayList<Integer>();
		usedlistaVF = new ArrayList<Integer>();
		tmpValues = new ArrayList<int[]>();
		usedlist = new ArrayList<Integer>();
		packEcgData = new ArrayList<Byte>();
		try {
			drawThread = new DrawThread();
			drawThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 负责绘制data中的数据
	 * 
	 * @author DC
	 * 
	 */
	class DrawThread extends Thread {
		// private Canvas canvas;
		private long wait = 100;

		// public CaculateConnect[] listconnect= new CaculateConnect[8];
		@Override
		public void run() {
			byte[] tmpbuf = new byte[20];
			int extlenth = 0;
			byte[] buffer = new byte[1024];
			int bytes = 0;
			// byte[] m = new byte[1050]
			while (flag) {

				byte[] readBuf = null;
				int count = 0;
				try {
					count = btInput.available();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (count == 0) {
					if (isStop) {
						isNeed = false;
					}
				} else {
					try {
						bytes = btInput.read(buffer);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// byte[] m = buffer;
				// buffer= ArrayUtils.addAll(tmpbuf,buffer);
				int tmpint = bytes + extlenth;
				// bytes=bytes+extlenth;
				// byte[] m = new byte [bytes];
				// readBuf = new byte[bytes];
				readBuf = new byte[tmpint];
				System.arraycopy(buffer, 0, readBuf, extlenth, bytes);
				System.arraycopy(tmpbuf, 0, readBuf, 0, extlenth);
				extlenth = 0;
				// readBuf = new byte[bytes];
				// System.arraycopy(buffer, 0, readBuf, 0, readBuf.length);
				//system.out.println("长度=================" + readBuf.length);
				// //Log.i("shuju",Utils.printHexString(readBuf));
				data = readBuf;

				if (isNeed) {
					try {
						// tmpbuf = null;
						int index = 0;
						index = covert2Useful(readBuf);
						extlenth = readBuf.length - index;
						// //Log.i("extlenth=",extlenth+"");
						System.arraycopy(readBuf, index, tmpbuf, 0, extlenth);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	private int covert2Useful(byte[] values) {
		// //system.out.println("READ 原始:  " + Utils.printHexString1(values));
		int i = 0;
		while (i < values.length) {
			if (((i + 1) < values.length) && values[i] == 85
					&& values[i + 1] == -86 && (i + 18 <= values.length)) {
				byte[] tmp = new byte[18];
				System.arraycopy(values, i, tmp, 0, 18);
				// //system.out.println(Utils.printHexString1(tmp));
				int[] datas = new int[12];
				datas[0] = ((values[i + 2] << 8 & 0x0ff00) + (values[i + 3] & 0x000ff));// II
				datas[1] = ((values[i + 4] << 8 & 0x0ff00) + (values[i + 5] & 0x000ff));// III
				datas[2] = ((values[i + 6] << 8 & 0x0ff00) + (values[i + 7] & 0x000ff));
				datas[3] = ((values[i + 8] << 8 & 0x0ff00) + (values[i + 9] & 0x000ff));
				datas[4] = ((values[i + 10] << 8 & 0x0ff00) + (values[i + 11] & 0x000ff));
				datas[5] = ((values[i + 12] << 8 & 0x0ff00) + (values[i + 13] & 0x000ff));
				datas[6] = ((values[i + 14] << 8 & 0x0ff00) + (values[i + 15] & 0x000ff));
				datas[7] = ((values[i + 16] << 8 & 0x0ff00) + (values[i + 17] & 0x000ff));
				datas[8] = datas[0] - datas[1] + 2000;// I
				datas[9] = (datas[1] - 2000) / 2 - (datas[0] - 2000) + 2000;// aVR
				datas[10] = (datas[0] - 2000) / 2 - (datas[1] - 2000) + 2000;// aVL
				datas[11] = (datas[0] + datas[1]) / 2;// aVF
				// //Log.i("datas", Arrays.toString(datas));
				FindR(datas[0]);
				for (int ii = 0; ii < 8; ii++) {
					//Log.i("fl", ii + "");
					if (listconnect[ii].findR2(datas[ii]) == 2)
						handler.obtainMessage(0013, ii).sendToTarget();
				}
				content.add(datas);
				if (content.size() >= SIZE * MeasureWeilionECG.m_rate) {
					draw(content);
					content.clear();
					// drawTest();
				}
				i += 18;
			} else {
				if (i + 18 > values.length) {
					//Log.i("return at i+18", i + "");
					return i;
				}
				i++;
			}
		}
		//Log.i("return at last", i + "");
		return i;
	}

	// private Handler mhandler = new Handler(){
	// @Override
	// public void handleMessage(Message msg){
	// switch (msg.what) {
	// case 1:
	// break;
	// default:
	// break;
	// }
	// };
	// };
	private void draw(final ArrayList<int[]> content) {
		try {
			// 开启一个线程去写数据；
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() { // TODO Auto-generated method stub
					for (int k = 0; k < content.size(); k++) {
						convert2ECGDataType(content.get(k));
					}
				}
			});
			// Thread athread = new Thread(new Runnable() {
			//
			// @Override
			// public void run() {
			// // TODO Auto-generated method stub
			//
			// }
			// });
			thread.start();
			canvas = sfv.getHolder().lockCanvas();// 关键:获取画布
			if (canvas != null) {
				//Log.i("canvas!=null", "canvas!=null??????????????????");
				drawBackGround(canvas);
				for (int i = 0; i < content.size(); i += MeasureWeilionECG.m_rate) {
					// 收集到10个点打印一次；
					if (points < SIZE) {
						tmpValues.add(content.get(i));
						points++;
					} else {
						points = 0;
						drawValue(tmpValues, canvas);
						tmpValues.clear();
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (canvas != null) {
				sfv.getHolder().unlockCanvasAndPost(canvas);// 解锁画布，提交画好的图像
			}
		}
	}

	private void drawValue(ArrayList<int[]> values, Canvas canvas) {
		/********** 画心电图线 **********/
		// //Log.i("int[] values", Arrays.toString(values));
		int[] tmpI = new int[SIZE];
		int[] tmpII = new int[SIZE];
		int[] tmpIII = new int[SIZE];
		int[] tmpaVR = new int[SIZE];
		int[] tmpaVL = new int[SIZE];
		int[] tmpaVF = new int[SIZE];
		int[] tmpV1 = new int[SIZE];
		int[] tmpV2 = new int[SIZE];
		int[] tmpV3 = new int[SIZE];
		int[] tmpV4 = new int[SIZE];
		int[] tmpV5 = new int[SIZE];
		int[] tmpV6 = new int[SIZE];

		for (int i = 0; i < SIZE; i++) {
			int[] tmp = values.get(i);
			tmpI[i] = tmp[8];
			tmpII[i] = tmp[0];
			tmpIII[i] = tmp[1];
			tmpaVR[i] = tmp[9];
			tmpaVL[i] = tmp[10];
			tmpaVF[i] = tmp[11];
			tmpV1[i] = tmp[2];
			tmpV2[i] = tmp[3];
			tmpV3[i] = tmp[4];
			tmpV4[i] = tmp[5];
			tmpV5[i] = tmp[6];
			tmpV6[i] = tmp[7];

		}

		drawECGGraph(75, tmpI, 1); // I
		drawECGGraph(155, tmpII, 2); // II
		drawECGGraph(235, tmpIII, 3); // III
		drawECGGraph(315, tmpaVR, 4); // aVR
		drawECGGraph(395, tmpaVL, 5); // aVL
		drawECGGraph(475, tmpaVF, 6); // aVF
		drawECGGraph(75, tmpV1, 7); // v1
		drawECGGraph(155, tmpV2, 8); // v2
		drawECGGraph(235, tmpV3, 9); // v3
		drawECGGraph(315, tmpV4, 10); // v4
		drawECGGraph(395, tmpV5, 11); // v5
		drawECGGraph(475, tmpV6, 12); // v6

	}

	private void drawBackGround(Canvas canvas) {
		/********** 画网格 **********/
		canvas.drawColor(Color.WHITE);
		mPaint.setColor(Color.GREEN);
		int height = canvas.getHeight();
		int width = canvas.getWidth();
		for (int u = 0; u < (width + 6); u += 6) {
			if (u % 30 == 0) {
				canvas.drawLine(u, 0, u, height, mPaint); // 纵轴方向 每隔30px即5mm
			} else {
				// 纵轴方向每隔6px打点即1mm
				for (int i = 0; i < (height + 6); i += 6)
					canvas.drawPoint(u, i, mPaint);
			}
		}
		for (int v = 0; v < (height + 30) / 30; v++) {
			canvas.drawLine(0, v * 30, width, v * 30, mPaint);
		}
		/********** 添加说明 **********/
		mPaint.setColor(Color.RED);
		int topMargin = 10, bottomMargin = 30, leftMargin = 20, rightMargin = 20;
		int stepWidth = (550 - topMargin - bottomMargin) / 6;
		int baseHeight = topMargin + stepWidth / 2;
		int baseWidth = leftMargin + (width - leftMargin - rightMargin) / 2;
		canvas.drawText("I", leftMargin, baseHeight, mPaint);
		canvas.drawText("II", leftMargin, baseHeight + stepWidth, mPaint);
		canvas.drawText("III", leftMargin, baseHeight + stepWidth * 2, mPaint);
		canvas.drawText("aVR", leftMargin, baseHeight + stepWidth * 3, mPaint);
		canvas.drawText("aVL", leftMargin, baseHeight + stepWidth * 4, mPaint);
		canvas.drawText("aVF", leftMargin, baseHeight + stepWidth * 5, mPaint);
		canvas.drawText("V1", baseWidth, baseHeight, mPaint);
		canvas.drawText("V2", baseWidth, baseHeight + stepWidth, mPaint);
		canvas.drawText("V3", baseWidth, baseHeight + stepWidth * 2, mPaint);
		canvas.drawText("V4", baseWidth, baseHeight + stepWidth * 3, mPaint);
		canvas.drawText("V5", baseWidth, baseHeight + stepWidth * 4, mPaint);
		canvas.drawText("V6", baseWidth, baseHeight + stepWidth * 5, mPaint);
	}

	private void drawECGGraph(int base, int[] values, int tag) {

		mPaint.setColor(Color.BLACK);
		usedlist = selectList(tag);
		if (usedlist.size() >= 360) {
			drawBackGround(canvas);
			usedlist.clear();
		}

		if (tag < 7) {
			for (int i = 0; i < values.length; i++) {
				int y = convertY2Pix(base, values[i]);
				usedlist.add(y);
			}
			for (int j = 0; j < usedlist.size() - 1; j++) {
				canvas.drawLine((float) (j * 1.5 + 60),
						(float) usedlist.get(j), (float) ((j + 1) * 1.5 + 60),
						(float) usedlist.get(j + 1), mPaint);
			}
		} else {
			for (int i = 0; i < values.length; i++) {
				int y = convertY2Pix(base, values[i]);
				usedlist.add(y);
			}
			for (int j = 0; j < usedlist.size() - 1; j++) {
				canvas.drawLine((float) (j * 1.5 + 660),
						(float) usedlist.get(j), (float) ((j + 1) * 1.5 + 660),
						(float) usedlist.get(j + 1), mPaint);
			}
		}
	}

	private ArrayList<Integer> selectList(int tag) {
		switch (tag) {
		case 1:
			return usedlistI;
		case 2:
			return usedlistII;
		case 3:
			return usedlistIII;
		case 4:
			return usedlistaVR;
		case 5:
			return usedlistaVL;
		case 6:
			return usedlistaVF;
		case 7:
			return usedlistV1;
		case 8:
			return usedlistV2;
		case 9:
			return usedlistV3;
		case 10:
			return usedlistV4;
		case 11:
			return usedlistV5;
		case 12:
			return usedlistV6;
		default:
			return null;
		}
	}

	private int convertY2Pix(int base, int y) {
		// 60/160=0.375
		double newValue = base
				+ (-(y - 2000) * 0.375 * MeasureWeilionECG.m_plus);
		int value = newValue >= 0 ? (int) (newValue + 0.5)
				: (int) (newValue - 0.5);
		// //Log.i("value", "base =" + base + " " + "value =" + "" + value);
		return value;
	}

	private byte[] ECG_DataPackage(int[] rawBuf) {
		byte[] dataBuf = new byte[13];
		dataBuf[0] = (byte) rawBuf[0]; // II低8BIT

		dataBuf[1] = (byte) (rawBuf[0] >> 8); // II[11-8]为低4位
		dataBuf[1] += ((rawBuf[6] >> 4) & 0xf0);// V5[11-8]为高4位

		dataBuf[2] = (byte) rawBuf[1]; // III低8BIT

		dataBuf[3] = (byte) (rawBuf[1] >> 8); // III[11-8]为低4位
		dataBuf[3] += (rawBuf[6] & 0xf0); // V5[7-4]为高4位

		dataBuf[4] = (byte) rawBuf[2]; // V1低8BIT

		dataBuf[5] = (byte) (rawBuf[2] >> 8); // V1[11-8]为低4位
		dataBuf[5] += ((rawBuf[6] << 4) & 0xf0);// V5[3-0]为高4位

		dataBuf[6] = (byte) rawBuf[3]; // V2低8BIT

		dataBuf[7] = (byte) (rawBuf[3] >> 8); // V2[11-8]为低4位
		dataBuf[7] += ((rawBuf[7] >> 4) & 0xf0);// V6[11-8]为高4位

		dataBuf[8] = (byte) rawBuf[4]; // V3低8BIT

		dataBuf[9] = (byte) (rawBuf[4] >> 8); // V3[11-8]为低4位
		dataBuf[9] += (rawBuf[7] & 0xf0); // V6[7-4]为高4位

		dataBuf[10] = (byte) rawBuf[5]; // V4低8BIT

		dataBuf[11] = (byte) (rawBuf[5] >> 8); // V4[11-8]为低4位
		dataBuf[11] += ((rawBuf[7] << 4) & 0xf0);// V6[3-0]为高4位
		dataBuf[12] = 0; // 起搏信号
		return dataBuf;
	}

	private void write2EcgFile(List list) {
		try {
			String ecgpath = Environment.getExternalStorageDirectory()
					.getPath();
			String name = ecgpath + "/ecg.ecg";
			FileOutputStream fout = new FileOutputStream(name, true);
			byte[] bytes = new byte[list.size()];
			for (int i = 0; i < list.size(); i++) {
				bytes[i] = (byte) list.get(i);
			}
			fout.write(bytes);
			fout.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void convert2ECGDataType(int[] rawBuf) {
		byte[] dataBytes = ECG_DataPackage(rawBuf);
		if (count == 0) {
			byte[] time = readTime();
			for (int i = 0; i < time.length; i++) {
				packEcgData.add(time[i]);
			}
		}
		if (count < 39) {
			for (int i = 0; i < dataBytes.length; i++) {
				packEcgData.add(dataBytes[i]);
			}
			count++;
		}
		if (count == 39) {
			count = 0;
			write2EcgFile(packEcgData);
			packEcgData.clear();
		}
	}

	private byte[] readTime() {
		int[] time = new int[6];
		byte[] DataBuff = new byte[5];
		Calendar c = Calendar.getInstance();
		time[0] = c.get(Calendar.SECOND);
		time[1] = c.get(Calendar.MINUTE);
		time[2] = c.get(Calendar.HOUR_OF_DAY);
		time[3] = c.get(Calendar.DAY_OF_MONTH);
		time[4] = c.get(Calendar.MONTH);
		time[5] = c.get(Calendar.YEAR) - 2000;
		DataBuff[0] = (byte) (time[5] & 0xff); // 年
		DataBuff[1] = (byte) (time[3] & 0xff); // 日
		DataBuff[1] += ((time[4] << 2) & 0xe0); // 月高3位保存到日的高3位上
		DataBuff[2] = (byte) (time[2] & 0xff); // 时
		DataBuff[2] += (((byte) (time[4] & 0xff) << 5) & 0xe0); // 月低3位保存到时的高3位上
		DataBuff[3] = (byte) time[1]; // 分
		DataBuff[4] = (byte) time[0]; // 秒
		//Log.i("Time", Arrays.toString(DataBuff));
		return DataBuff;
	}

	// 1 finded 0 not find
	// data为采样数据，一般为II导联或V5导联的数据
	// m_HR 为计算的心率值
	private boolean FindR(int data) {
		int j;
		m_TimeOut++;// 超时计数
		// data -= 2000;
		if (m_iBufCnt < BUF_SIZE)// 未填充满缓冲区
		{
			m_CntBuf[m_iBufCnt] = data;// 填充缓冲区
			m_lSum += data;// 缓冲区求和
			++m_iBufCnt;
		}
		if (m_iBufCnt == BUF_SIZE)// 已经填充满缓冲区
		{
			// m_iFix = m_CntBuf[BUF_SIZE/2-1]*BUF_SIZE - m_lSum;
			// m_lSum = m_lSum - m_CntBuf[0];//更新缓冲区之和
			//
			// if ((m_iFix >= m_Threshold &&
			// ((m_CntBuf[BUF_SIZE/2-2]<m_CntBuf[BUF_SIZE/2-1]&&m_CntBuf[BUF_SIZE/2-1]>=m_CntBuf[BUF_SIZE/2])||(m_CntBuf[BUF_SIZE/2-1]<m_CntBuf[BUF_SIZE/2]&&m_CntBuf[BUF_SIZE/2]>=m_CntBuf[BUF_SIZE/2+1])))
			// ||(m_iFix <= -m_Threshold &&
			// ((m_CntBuf[BUF_SIZE/2-2]>=m_CntBuf[BUF_SIZE/2-1]&&m_CntBuf[BUF_SIZE/2-1]<m_CntBuf[BUF_SIZE/2])
			// ||(m_CntBuf[BUF_SIZE/2-1]>=m_CntBuf[BUF_SIZE/2]&&m_CntBuf[BUF_SIZE/2]<m_CntBuf[BUF_SIZE/2+1]))))
			// {//将差值和阈值做判断，同时判断波形的最高点或最低点
			// m_RIndex++;//找到R波
			// m_iFixBuf[0]=m_iFixBuf[1];
			// m_iFixBuf[1]=m_iFixBuf[2];
			// if(m_iFix<0) m_iFix = 0 - m_iFix;
			// m_iFixBuf[2] = m_iFix;
			// if (m_iFixBuf[0] != 0)//更新阈值
			// {
			// m_Threshold = (m_iFixBuf[0]+m_iFixBuf[1]+m_iFixBuf[2])*21/100;
			// }
			// }
			//
			// for(j = 1; j < BUF_SIZE; j++)//移动更新缓冲区
			// {
			// m_CntBuf[j-1] = m_CntBuf[j];
			// }
			// m_iBufCnt -= 1;//
			m_iFix = m_CntBuf[7] * BUF_SIZE - m_lSum;// 计算差值
			m_lSum = m_lSum - m_CntBuf[0];// 更新缓冲区之和

			if ((m_iFix >= m_Threshold && ((m_CntBuf[6] < m_CntBuf[7] && m_CntBuf[7] >= m_CntBuf[8]) || (m_CntBuf[7] < m_CntBuf[8] && m_CntBuf[8] >= m_CntBuf[9])))
					|| (m_iFix <= -m_Threshold && ((m_CntBuf[6] >= m_CntBuf[7] && m_CntBuf[7] < m_CntBuf[8]) || (m_CntBuf[7] >= m_CntBuf[8] && m_CntBuf[8] < m_CntBuf[9])))) {// 将差值和阈值做判断，同时判断波形的最高点或最低点
				m_RIndex++;// 找到R波
				m_iFixBuf[0] = m_iFixBuf[1];
				m_iFixBuf[1] = m_iFixBuf[2];
				if (m_iFix < 0)
					m_iFix = 0 - m_iFix;
				m_iFixBuf[2] = m_iFix;
				if (m_iFixBuf[0] != 0)// 更新阈值
				{
					m_Threshold = (m_iFixBuf[0] + m_iFixBuf[1] + m_iFixBuf[2]) * 21 / 100;
				}
			}

			for (j = 1; j < BUF_SIZE; j++)// 移动更新缓冲区
			{
				m_CntBuf[j - 1] = m_CntBuf[j];
			}
			m_iBufCnt -= 1;//
		}
		if ((m_RIndex > 0) && (m_RIndex < 3))// 找到一个R波后计数采样点
		{
			m_iRRnumber++;
		}
		if (m_TimeOut >= TIME_OUT)// 检测超时，将所有变量清零
		{
			m_TimeOut = 0;
			m_iBufCnt = 0;
			m_lSum = 0;
			m_Threshold = 300;
			for (int i = 0; i < m_iFixBuf.length; i++) {
				m_iFixBuf[i] = 0;
			}
			for (int i = 0; i < m_CntBuf.length; i++) {
				m_CntBuf[i] = 0;
			}
			// m_iFixBuf[0] = 0;
			// m_iFixBuf[1] = 0;
			// m_iFixBuf[2] = 0;
			m_RIndex = 0;
			m_iRRnumber = 0;
			m_preRRnumber = 0;
			return false;
		}
		if (m_RIndex == 2)// 找到两个R波
		{
			// //Log.i("f","i will return true");
			if (m_iRRnumber < 65) // RR不应期，继续计数,心率大于230：（60000/230/4ms）时，认为是干扰
			{
				m_RIndex = m_RIndex - 1;
				return true;
			} else// 找到两个R波，计算心率。
			{
				m_TimeOut = 0;// 找到R波，重新开始超时计数
				int m_HR = 15000 / m_iRRnumber + 1;// 通过采样点来计算心率
				//Log.i("m_HR", m_HR + "");
				m_HR /= 2;
				handler.obtainMessage(0003, m_HR).sendToTarget();
				m_RIndex = 1; // 公式：60/(dot * (1/采样率))
				m_preRRnumber = m_iRRnumber;
				m_iRRnumber = 0;
				return true;
			}
		}
		return true;
	}

	public void start() {
		flag = true;
		isNeed = true;
		isStop = false;
	}

	public void stop() {
		flag = false;
		isStop = true;
		isNeed = false;
	}

	
	public byte[] getXinDianData () {
		return data;
	}
}
