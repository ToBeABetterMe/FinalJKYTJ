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
	private int m_TimeOut;// R����ⳬʱ����
	private int m_preRRnumber;// ��һ�μ�⵽��RR����
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
		m_TimeOut++;//��ʱ����
		data -= 2000;
	    if (m_iBufCnt < BUF_SIZE)//δ�����������
	    {
	         m_CntBuf[m_iBufCnt] = data;//��仺����
	         m_lSum += data;//���������
	         ++m_iBufCnt;
	    }
	    if (m_iBufCnt == BUF_SIZE)//�Ѿ������������
	    {
	         m_iFix = m_CntBuf[BUF_SIZE/2-1]*BUF_SIZE - m_lSum;//�����ֵ
	         m_lSum = m_lSum - m_CntBuf[0];//���»�����֮��

	         if ((m_iFix >= m_Threshold && ((m_CntBuf[BUF_SIZE/2-2]<m_CntBuf[BUF_SIZE/2-1]&&m_CntBuf[BUF_SIZE/2-1]>=m_CntBuf[BUF_SIZE/2])||(m_CntBuf[BUF_SIZE/2-1]<m_CntBuf[BUF_SIZE/2]&&m_CntBuf[BUF_SIZE/2]>=m_CntBuf[BUF_SIZE/2+1])))
	            ||(m_iFix <= -m_Threshold && ((m_CntBuf[BUF_SIZE/2-2]>=m_CntBuf[BUF_SIZE/2-1]&&m_CntBuf[BUF_SIZE/2-1]<m_CntBuf[BUF_SIZE/2]) ||(m_CntBuf[BUF_SIZE/2-1]>=m_CntBuf[BUF_SIZE/2]&&m_CntBuf[BUF_SIZE/2]<m_CntBuf[BUF_SIZE/2+1]))))
	          {//����ֵ����ֵ���жϣ�ͬʱ�жϲ��ε���ߵ����͵�
	            m_RIndex++;//�ҵ�R��
	            m_iFixBuf[0]=m_iFixBuf[1];
	            m_iFixBuf[1]=m_iFixBuf[2];
	            if(m_iFix<0) m_iFix = 0 - m_iFix;
	            m_iFixBuf[2] = m_iFix;
	            if (m_iFixBuf[0] != 0)//������ֵ
	            {
	                m_Threshold = (m_iFixBuf[0]+m_iFixBuf[1]+m_iFixBuf[2])*21/100;
	            }
	          }

	         for(j = 1; j < BUF_SIZE; j++)//�ƶ����»�����
	         {
	            m_CntBuf[j-1] = m_CntBuf[j];
	         }
	         m_iBufCnt -= 1;//
	    }
	    if((m_RIndex > 0) && (m_RIndex < 3))//�ҵ�һ��R�������������
		{
	         m_iRRnumber++;
		}
		if(m_TimeOut >= TIME_OUT)//��ⳬʱ�������б�������
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
			res = 2;//3����û�ҵ�R����������Ϊ�ǵ�������
			return res;
		}
	    if(m_RIndex == 2)//�ҵ�����R��
	    {
	         if(m_iRRnumber < 45) //RR��Ӧ��
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
