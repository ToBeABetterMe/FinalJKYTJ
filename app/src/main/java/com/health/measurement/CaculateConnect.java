/**
 * @author ImJosMR
 * 
 */
package com.health.measurement;



public class CaculateConnect {
	public static final int BUF_SIZE = 20;
	public static final int TIME_OUT = 750;

	// int FindR(ECGDATA data);
	// ECGDATA CEcgAna::Getthreshold(void);
	// void SetThreshold(ECGDATA thre);

	private int m_iBufCnt;
	private int m_iRRnumber;
	private int[] m_CntBuf = new int[BUF_SIZE];
	private int m_lSum;
	private int m_iFix;
	public int m_iFixBuf[] = { 0, 0, 0 };
	private int m_Threshold;
	private int m_RIndex;
	private int m_TimeOut;// R波检测超时计数
	private int m_preRRnumber;// 上一次检测到的RR间期
	private int m_nSetThreshold;
	public  CaculateConnect(){
		int j;
		for(j = 0; j < BUF_SIZE; j++)
		{
			m_CntBuf[j] = 0;
		}
		m_iBufCnt = 0;
		m_iRRnumber = 0;
		m_iFixBuf[0] = 0;
		m_iFixBuf[1] = 0;
		m_iFixBuf[2] = 0;
		m_iFix = 0;
		m_nSetThreshold = 300;
		//m_Threshold = m_nSetThreshold;//
		m_RIndex = 0;
		m_lSum = 0;
		m_TimeOut = 0;
		m_preRRnumber = 0;
	}
	
	public int findR2(int data){
		int j;
		int res = 0;
		m_TimeOut++;//超时计数
		data -= 2000;
	    if (m_iBufCnt < BUF_SIZE)//未填充满缓冲区
	    {
	         m_CntBuf[m_iBufCnt] = data;//填充缓冲区
	         m_lSum += data;//缓冲区求和
	         ++m_iBufCnt;
	    }
	    if (m_iBufCnt == BUF_SIZE)//已经填充满缓冲区
	    {
	         m_iFix = m_CntBuf[BUF_SIZE/2-1]*BUF_SIZE - m_lSum;//计算差值
	         m_lSum = m_lSum - m_CntBuf[0];//更新缓冲区之和

	         if ((m_iFix >= m_Threshold && ((m_CntBuf[BUF_SIZE/2-2]<m_CntBuf[BUF_SIZE/2-1]&&m_CntBuf[BUF_SIZE/2-1]>=m_CntBuf[BUF_SIZE/2])||(m_CntBuf[BUF_SIZE/2-1]<m_CntBuf[BUF_SIZE/2]&&m_CntBuf[BUF_SIZE/2]>=m_CntBuf[BUF_SIZE/2+1])))
	            ||(m_iFix <= -m_Threshold && ((m_CntBuf[BUF_SIZE/2-2]>=m_CntBuf[BUF_SIZE/2-1]&&m_CntBuf[BUF_SIZE/2-1]<m_CntBuf[BUF_SIZE/2]) ||(m_CntBuf[BUF_SIZE/2-1]>=m_CntBuf[BUF_SIZE/2]&&m_CntBuf[BUF_SIZE/2]<m_CntBuf[BUF_SIZE/2+1]))))
	          {//将差值和阈值做判断，同时判断波形的最高点或最低点
	            m_RIndex++;//找到R波
	            m_iFixBuf[0]=m_iFixBuf[1];
	            m_iFixBuf[1]=m_iFixBuf[2];
	            if(m_iFix<0) m_iFix = 0 - m_iFix;
	            m_iFixBuf[2] = m_iFix;
	            if (m_iFixBuf[0] != 0)//更新阈值
	            {
	                m_Threshold = (m_iFixBuf[0]+m_iFixBuf[1]+m_iFixBuf[2])*21/100;
	            }
	          }

	         for(j = 1; j < BUF_SIZE; j++)//移动更新缓冲区
	         {
	            m_CntBuf[j-1] = m_CntBuf[j];
	         }
	         m_iBufCnt -= 1;//
	    }
	    if((m_RIndex > 0) && (m_RIndex < 3))//找到一个R波后计数采样点
		{
	         m_iRRnumber++;
		}
		if(m_TimeOut >= TIME_OUT)//检测超时，将所有变量清零
		{
			m_TimeOut = 0;
			m_iBufCnt = 0;
			m_lSum = 0;
			m_Threshold = m_nSetThreshold;
			m_iFixBuf[0] = 0;
			m_iFixBuf[1] = 0;
			m_iFixBuf[2] = 0;
			m_RIndex = 0;
			m_iRRnumber = 0;
			m_preRRnumber = 0;
			res = 2;//3秒内没找到R波，可以认为是导联脱落
			return res;
		}
	    if(m_RIndex == 2)//找到两个R波
	    {
	         if(m_iRRnumber < 45) //RR不应期
			 {
				 res = 0;
			 } 
			 else
			 {
				 res = 1;
				 m_TimeOut = 0;
			 }
			 m_RIndex = m_RIndex - 1;
			 m_iRRnumber = 0;
	     }
		else
		{
			res = 0;
		}
		return res;
	}
	void SetThreshold(int thre)
	{
		m_nSetThreshold = thre;
		m_Threshold = m_nSetThreshold;
	}
}
