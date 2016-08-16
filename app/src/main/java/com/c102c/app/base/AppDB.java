package com.c102c.app.base;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.c102c.app.model.Area_down;
import com.c102c.app.model.Area_downDao;
import com.c102c.app.model.ChildSpecial_down;
import com.c102c.app.model.ChildSpecial_downDao;
import com.c102c.app.model.DaoMaster;
import com.c102c.app.model.DaoMaster.DevOpenHelper;
import com.c102c.app.model.DaoSession;
import com.c102c.app.model.DiabetesFlup_down;
import com.c102c.app.model.DiabetesFlup_downDao;
import com.c102c.app.model.DiabetesFlup_upload;
import com.c102c.app.model.DiabetesFlup_uploadDao;
import com.c102c.app.model.DiabetesSpecial_down;
import com.c102c.app.model.DiabetesSpecial_downDao;
import com.c102c.app.model.Dictionary_down;
import com.c102c.app.model.Dictionary_downDao;
import com.c102c.app.model.HealthExamination;
import com.c102c.app.model.HealthExaminationDao;
import com.c102c.app.model.HealthRecord_Status;
import com.c102c.app.model.HealthRecord_StatusDao;
import com.c102c.app.model.HealthRecord_down;
import com.c102c.app.model.HealthRecord_downDao;
import com.c102c.app.model.HealthRecord_downDao.Properties;
import com.c102c.app.model.HypertensionFlup_down;
import com.c102c.app.model.HypertensionFlup_downDao;
import com.c102c.app.model.HypertensionFlup_upload;
import com.c102c.app.model.HypertensionFlup_uploadDao;
import com.c102c.app.model.HypertensionScreen_upload;
import com.c102c.app.model.HypertensionScreen_uploadDao;
import com.c102c.app.model.HypertensionSpecial_down;
import com.c102c.app.model.HypertensionSpecial_downDao;
import com.c102c.app.model.LocalJieZhen;
import com.c102c.app.model.LocalJieZhenDao;
import com.c102c.app.model.LocalMeasurementBaiXiBao;
import com.c102c.app.model.LocalMeasurementBaiXiBaoDao;
import com.c102c.app.model.LocalMeasurementEWen;
import com.c102c.app.model.LocalMeasurementEWenDao;
import com.c102c.app.model.LocalMeasurementNiaoYe;
import com.c102c.app.model.LocalMeasurementNiaoYeDao;
import com.c102c.app.model.LocalMeasurementXinDian;
import com.c102c.app.model.LocalMeasurementXinDianDao;
import com.c102c.app.model.LocalMeasurementXueTang;
import com.c102c.app.model.LocalMeasurementXueTangDao;
import com.c102c.app.model.LocalMeasurementXueYa;
import com.c102c.app.model.LocalMeasurementXueYaDao;
import com.c102c.app.model.LocalMeasurementXueYang;
import com.c102c.app.model.LocalMeasurementXueYangDao;
import com.c102c.app.model.LocalZhuanZhen;
import com.c102c.app.model.LocalZhuanZhenDao;
import com.c102c.app.model.NeonatalVisit_upload;
import com.c102c.app.model.NeonatalVisit_uploadDao;
import com.c102c.app.model.Org_down;
import com.c102c.app.model.Org_downDao;
import com.c102c.app.model.PastHistory_down;
import com.c102c.app.model.PastHistory_downDao;
import com.c102c.app.model.Person_down;
import com.c102c.app.model.Person_downDao;
import com.c102c.app.model.Postpartum42Visit_upload;
import com.c102c.app.model.Postpartum42Visit_uploadDao;
import com.c102c.app.model.PostpartumVisit_upload;
import com.c102c.app.model.PostpartumVisit_uploadDao;
import com.c102c.app.model.PregnantFirstCheck_upload;
import com.c102c.app.model.PregnantFirstCheck_uploadDao;
import com.c102c.app.model.PregnantRecheck_upload;
import com.c102c.app.model.PregnantRecheck_uploadDao;
import com.c102c.app.model.PregnantSpecial_down;
import com.c102c.app.model.PregnantSpecial_downDao;
import com.c102c.app.model.RequestMessage;
import com.c102c.app.model.RequestMessageDao;
import com.c102c.app.model.User_down;
import com.c102c.app.model.User_downDao;
import com.c102c.app.utils.Tools;

import de.greenrobot.dao.query.QueryBuilder;

public class AppDB {
	private static DevOpenHelper mHelper;
	private static SQLiteDatabase mDatabase;
	private static DaoMaster mDaoMaster;
	private static DaoSession mDaoSession;
	private static AppDB sAppDB = null;

	// ****************************���ݲ���*************************************************
	private static Area_downDao mArea_downDao;
	private static ChildSpecial_downDao mChildSpecial_downDao;
	private static DiabetesFlup_downDao mDiabetesFlup_downDao;
	private static DiabetesFlup_uploadDao mDiabetesFlup_uploadDao;
	private static DiabetesSpecial_downDao mDiabetesSpecial_downDao;
	private static Dictionary_downDao mDictionary_downDao;
	private static HealthExaminationDao mHealthExaminationDao;
	private static HealthRecord_downDao mHealthRecord_downDao;
	private static HealthRecord_StatusDao mHealthRecord_StatusDao;
	private static HypertensionFlup_downDao mHypertensionFlup_downDao;
	private static HypertensionFlup_uploadDao mHypertensionFlup_uploadDao;
	private static HypertensionScreen_uploadDao mHypertensionScreen_uploadDao;
	private static HypertensionSpecial_downDao mHypertensionSpecial_downDao;
	private static NeonatalVisit_uploadDao mNeonatalVisit_uploadDao;
	private static Org_downDao mOrg_downDao;
	private static PastHistory_downDao mPastHistory_downDao;
	private static Person_downDao mPerson_downDao;
	private static Postpartum42Visit_uploadDao mPostpartum42Visit_uploadDao;
	private static PostpartumVisit_uploadDao mPostpartumVisit_uploadDao;
	private static PregnantFirstCheck_uploadDao mPregnantFirstCheck_uploadDao;
	private static PregnantRecheck_uploadDao mPregnantRecheck_uploadDao;
	private static PregnantSpecial_downDao mPregnantSpecial_downDao;
	private static RequestMessageDao mRequestMessageDao;
	private static User_downDao mUser_downDao;

	private static LocalMeasurementEWenDao mLocalMeasurementEWenDao;
	private static LocalMeasurementNiaoYeDao mLocalMeasurementNiaoYeDao;
	private static LocalMeasurementXinDianDao mLocalMeasurementXinDianDao;
	private static LocalMeasurementXueTangDao mLocalMeasurementXueTangDao;
	private static LocalMeasurementXueYangDao mLocalMeasurementXueYangDao;
	private static LocalMeasurementXueYaDao mLocalMeasurementXueYaDao;
	private static LocalMeasurementBaiXiBaoDao mLocalMeasurementBaiXiBaoDao;

	private static LocalZhuanZhenDao mLocalZhuanZhenDao;
	private static LocalJieZhenDao mLocalJieZhenDao;

	private AppDB(Context context) {
		// TODO Auto-generated constructor stub
		mHelper = new DaoMaster.DevOpenHelper(context, "App-DB", null);
		mDatabase = mHelper.getWritableDatabase();
		mDaoMaster = new DaoMaster(mDatabase);
		mDaoSession = mDaoMaster.newSession();

		mArea_downDao = mDaoSession.getArea_downDao();
		mChildSpecial_downDao = mDaoSession.getChildSpecial_downDao();
		mDiabetesFlup_downDao = mDaoSession.getDiabetesFlup_downDao();
		mDiabetesFlup_uploadDao = mDaoSession.getDiabetesFlup_uploadDao();
		mDiabetesSpecial_downDao = mDaoSession.getDiabetesSpecial_downDao();
		mDictionary_downDao = mDaoSession.getDictionary_downDao();
		mHealthExaminationDao = mDaoSession.getHealthExaminationDao();
		mHealthRecord_downDao = mDaoSession.getHealthRecord_downDao();
		mHealthRecord_StatusDao = mDaoSession.getHealthRecord_StatusDao();
		mHypertensionFlup_downDao = mDaoSession.getHypertensionFlup_downDao();
		mHypertensionFlup_uploadDao = mDaoSession
				.getHypertensionFlup_uploadDao();
		mHypertensionScreen_uploadDao = mDaoSession
				.getHypertensionScreen_uploadDao();
		mHypertensionSpecial_downDao = mDaoSession
				.getHypertensionSpecial_downDao();
		mNeonatalVisit_uploadDao = mDaoSession.getNeonatalVisit_uploadDao();
		mOrg_downDao = mDaoSession.getOrg_downDao();
		mPastHistory_downDao = mDaoSession.getPastHistory_downDao();
		mPerson_downDao = mDaoSession.getPerson_downDao();
		mPostpartum42Visit_uploadDao = mDaoSession
				.getPostpartum42Visit_uploadDao();
		mPostpartumVisit_uploadDao = mDaoSession.getPostpartumVisit_uploadDao();
		mPregnantFirstCheck_uploadDao = mDaoSession
				.getPregnantFirstCheck_uploadDao();
		mPregnantRecheck_uploadDao = mDaoSession.getPregnantRecheck_uploadDao();
		mPregnantSpecial_downDao = mDaoSession.getPregnantSpecial_downDao();
		mRequestMessageDao = mDaoSession.getRequestMessageDao();
		mUser_downDao = mDaoSession.getUser_downDao();

		mLocalMeasurementEWenDao = mDaoSession.getLocalMeasurementEWenDao();
		mLocalMeasurementNiaoYeDao = mDaoSession.getLocalMeasurementNiaoYeDao();
		mLocalMeasurementXinDianDao = mDaoSession
				.getLocalMeasurementXinDianDao();
		mLocalMeasurementXueTangDao = mDaoSession
				.getLocalMeasurementXueTangDao();
		mLocalMeasurementXueYangDao = mDaoSession
				.getLocalMeasurementXueYangDao();
		mLocalMeasurementXueYaDao = mDaoSession.getLocalMeasurementXueYaDao();
		mLocalMeasurementBaiXiBaoDao = mDaoSession
				.getLocalMeasurementBaiXiBaoDao();

		mLocalJieZhenDao = mDaoSession.getLocalJieZhenDao();
		mLocalZhuanZhenDao = mDaoSession.getLocalZhuanZhenDao();
	}

	public static synchronized AppDB getInstance(Context context) {
		if (sAppDB == null) {
			sAppDB = new AppDB(context);
		}
		return sAppDB;
	}

	public static boolean add(Object object) {
		boolean tag = true;
		if ("com.c102c.app.model.Area_down".equals(object.getClass().getName())) {
			mArea_downDao.insertOrReplace((Area_down) object);
		} else if ("com.c102c.app.model.ChildSpecial_down".equals(object
				.getClass().getName())) {
			mChildSpecial_downDao.insertOrReplace((ChildSpecial_down) object);
		} else if ("com.c102c.app.model.DiabetesFlup_down".equals(object
				.getClass().getName())) {
			mDiabetesFlup_downDao.insertOrReplace((DiabetesFlup_down) object);
		} else if ("com.c102c.app.model.DiabetesFlup_upload".equals(object
				.getClass().getName())) {
			mDiabetesFlup_uploadDao
					.insertOrReplace((DiabetesFlup_upload) object);
		} else if ("com.c102c.app.model.DiabetesSpecial_down".equals(object
				.getClass().getName())) {
			mDiabetesSpecial_downDao
					.insertOrReplace((DiabetesSpecial_down) object);
		} else if ("com.c102c.app.model.Dictionary_down".equals(object
				.getClass().getName())) {
			mDictionary_downDao.insertOrReplace((Dictionary_down) object);
		} else if ("com.c102c.app.model.HealthExamination".equals(object
				.getClass().getName())) {
			mHealthExaminationDao.insertOrReplace((HealthExamination) object);
		} else if ("com.c102c.app.model.HealthRecord_down".equals(object
				.getClass().getName())) {
			mHealthRecord_downDao.insertOrReplace((HealthRecord_down) object);
		} else if ("com.c102c.app.model.HealthRecord_Status".equals(object
				.getClass().getName())) {
			mHealthRecord_StatusDao
					.insertOrReplace((HealthRecord_Status) object);
		} else if ("com.c102c.app.model.HypertensionFlup_down".equals(object
				.getClass().getName())) {
			mHypertensionFlup_downDao
					.insertOrReplace((HypertensionFlup_down) object);
		} else if ("com.c102c.app.model.HypertensionFlup_upload".equals(object
				.getClass().getName())) {
			mHypertensionFlup_uploadDao
					.insertOrReplace((HypertensionFlup_upload) object);
		} else if ("com.c102c.app.model.HypertensionScreen_upload"
				.equals(object.getClass().getName())) {
			mHypertensionScreen_uploadDao
					.insertOrReplace((HypertensionScreen_upload) object);
		} else if ("com.c102c.app.model.HypertensionSpecial_down".equals(object
				.getClass().getName())) {
			mHypertensionSpecial_downDao
					.insertOrReplace((HypertensionSpecial_down) object);
		} else if ("com.c102c.app.model.NeonatalVisit_upload".equals(object
				.getClass().getName())) {
			mNeonatalVisit_uploadDao
					.insertOrReplace((NeonatalVisit_upload) object);
		} else if ("com.c102c.app.model.Org_down".equals(object.getClass()
				.getName())) {
			mOrg_downDao.insertOrReplace((Org_down) object);
		} else if ("com.c102c.app.model.PastHistory_down".equals(object
				.getClass().getName())) {
			mPastHistory_downDao.insertOrReplace((PastHistory_down) object);
		} else if ("com.c102c.app.model.Person_down".equals(object.getClass()
				.getName())) {
			mPerson_downDao.insertOrReplace((Person_down) object);
		} else if ("com.c102c.app.model.Postpartum42Visit_upload".equals(object
				.getClass().getName())) {
			mPostpartum42Visit_uploadDao
					.insertOrReplace((Postpartum42Visit_upload) object);
		} else if ("com.c102c.app.model.PostpartumVisit_upload".equals(object
				.getClass().getName())) {
			mPostpartumVisit_uploadDao
					.insertOrReplace((PostpartumVisit_upload) object);
		} else if ("com.c102c.app.model.PregnantFirstCheck_upload"
				.equals(object.getClass().getName())) {
			mPregnantFirstCheck_uploadDao
					.insertOrReplace((PregnantFirstCheck_upload) object);
		} else if ("com.c102c.app.model.PregnantRecheck_upload".equals(object
				.getClass().getName())) {
			mPregnantRecheck_uploadDao
					.insertOrReplace((PregnantRecheck_upload) object);
		} else if ("com.c102c.app.model.PregnantSpecial_down".equals(object
				.getClass().getName())) {
			mPregnantSpecial_downDao
					.insertOrReplace((PregnantSpecial_down) object);
		} else if ("com.c102c.app.model.RequestMessage".equals(object
				.getClass().getName())) {
			mRequestMessageDao.insertOrReplace((RequestMessage) object);
		} else if ("com.c102c.app.model.User_down".equals(object.getClass()
				.getName())) {
			mUser_downDao.insertOrReplace((User_down) object);
		} else if ("com.c102c.app.model.LocalMeasurementEWen".equals(object
				.getClass().getName())) {
			mLocalMeasurementEWenDao
					.insertOrReplace((LocalMeasurementEWen) object);
		} else if ("com.c102c.app.model.LocalMeasurementNiaoYe".equals(object
				.getClass().getName())) {
			mLocalMeasurementNiaoYeDao
					.insertOrReplace((LocalMeasurementNiaoYe) object);
		} else if ("com.c102c.app.model.LocalMeasurementXinDian".equals(object
				.getClass().getName())) {
			mLocalMeasurementXinDianDao
					.insertOrReplace((LocalMeasurementXinDian) object);
		} else if ("com.c102c.app.model.LocalMeasurementXueTang".equals(object
				.getClass().getName())) {
			mLocalMeasurementXueTangDao
					.insertOrReplace((LocalMeasurementXueTang) object);
		} else if ("com.c102c.app.model.LocalMeasurementXueYang".equals(object
				.getClass().getName())) {
			mLocalMeasurementXueYangDao
					.insertOrReplace((LocalMeasurementXueYang) object);
		} else if ("com.c102c.app.model.LocalMeasurementXueYa".equals(object
				.getClass().getName())) {
			mLocalMeasurementXueYaDao
					.insertOrReplace((LocalMeasurementXueYa) object);
		} else if ("com.c102c.app.model.LocalMeasurementBaiXiBao".equals(object
				.getClass().getName())) {
			mLocalMeasurementBaiXiBaoDao
					.insertOrReplace((LocalMeasurementBaiXiBao) object);
		} else if ("com.c102c.app.model.LocalZhuanZhen".equals(object
				.getClass().getName())) {
			mLocalZhuanZhenDao.insertOrReplace((LocalZhuanZhen) object);
		} else if ("com.c102c.app.model.LocalJieZhen".equals(object.getClass()
				.getName())) {
			mLocalJieZhenDao.insertOrReplace((LocalJieZhen) object);
		} else {
			tag = false;
		}
		return tag;
	}

	public static List<Object> get(Object object) {
		if ("com.c102c.app.model.Area_down".equals(object.getClass().getName())) {
			return new ArrayList<Object>(mArea_downDao.loadAll());
		} else if ("com.c102c.app.model.ChildSpecial_down".equals(object
				.getClass().getName())) {
			return new ArrayList<Object>(mChildSpecial_downDao.loadAll());
		} else if ("com.c102c.app.model.DiabetesFlup_down".equals(object
				.getClass().getName())) {
			return new ArrayList<Object>(mDiabetesFlup_downDao.loadAll());
		} else if ("com.c102c.app.model.DiabetesFlup_upload".equals(object
				.getClass().getName())) {
			return new ArrayList<Object>(mDiabetesFlup_uploadDao.loadAll());
		} else if ("com.c102c.app.model.DiabetesSpecial_down".equals(object
				.getClass().getName())) {
			return new ArrayList<Object>(mDiabetesSpecial_downDao.loadAll());
		} else if ("com.c102c.app.model.Dictionary_down".equals(object
				.getClass().getName())) {
			return new ArrayList<Object>(mDictionary_downDao.loadAll());
		} else if ("com.c102c.app.model.HealthExamination".equals(object
				.getClass().getName())) {
			return new ArrayList<Object>(mHealthExaminationDao.loadAll());
		} else if ("com.c102c.app.model.HealthRecord_down".equals(object
				.getClass().getName())) {
			return new ArrayList<Object>(mHealthRecord_downDao.loadAll());
		} else if ("com.c102c.app.model.HealthRecord_Status".equals(object
				.getClass().getName())) {
			return new ArrayList<Object>(mHealthRecord_StatusDao.loadAll());
		} else if ("com.c102c.app.model.HypertensionFlup_down".equals(object
				.getClass().getName())) {
			return new ArrayList<Object>(mHypertensionFlup_downDao.loadAll());
		} else if ("com.c102c.app.model.HypertensionFlup_upload".equals(object
				.getClass().getName())) {
			return new ArrayList<Object>(mHypertensionFlup_uploadDao.loadAll());
		} else if ("com.c102c.app.model.HypertensionScreen_upload"
				.equals(object.getClass().getName())) {
			return new ArrayList<Object>(
					mHypertensionScreen_uploadDao.loadAll());
		} else if ("com.c102c.app.model.HypertensionSpecial_down".equals(object
				.getClass().getName())) {
			return new ArrayList<Object>(mHypertensionSpecial_downDao.loadAll());
		} else if ("com.c102c.app.model.NeonatalVisit_upload".equals(object
				.getClass().getName())) {
			return new ArrayList<Object>(mNeonatalVisit_uploadDao.loadAll());
		} else if ("com.c102c.app.model.Org_down".equals(object.getClass()
				.getName())) {
			return new ArrayList<Object>(mOrg_downDao.loadAll());
		} else if ("com.c102c.app.model.PastHistory_down".equals(object
				.getClass().getName())) {
			return new ArrayList<Object>(mPastHistory_downDao.loadAll());
		} else if ("com.c102c.app.model.Person_down".equals(object.getClass()
				.getName())) {
			return new ArrayList<Object>(mPerson_downDao.loadAll());
		} else if ("com.c102c.app.model.Postpartum42Visit_upload".equals(object
				.getClass().getName())) {
			return new ArrayList<Object>(mPostpartum42Visit_uploadDao.loadAll());
		} else if ("com.c102c.app.model.PostpartumVisit_upload".equals(object
				.getClass().getName())) {
			return new ArrayList<Object>(mPostpartumVisit_uploadDao.loadAll());
		} else if ("com.c102c.app.model.PregnantFirstCheck_upload"
				.equals(object.getClass().getName())) {
			return new ArrayList<Object>(
					mPregnantFirstCheck_uploadDao.loadAll());
		} else if ("com.c102c.app.model.PregnantRecheck_upload".equals(object
				.getClass().getName())) {
			return new ArrayList<Object>(mPregnantRecheck_uploadDao.loadAll());
		} else if ("com.c102c.app.model.PregnantSpecial_down".equals(object
				.getClass().getName())) {
			return new ArrayList<Object>(mPregnantSpecial_downDao.loadAll());
		} else if ("com.c102c.app.model.RequestMessage".equals(object
				.getClass().getName())) {
			return new ArrayList<Object>(mRequestMessageDao.loadAll());
		} else if ("com.c102c.app.model.User_down".equals(object.getClass()
				.getName())) {
			return new ArrayList<Object>(mUser_downDao.loadAll());
		} else {
			return null;
		}
	}

	public static HealthRecord_down testWithJWS() {
		QueryBuilder qb = mHealthRecord_downDao.queryBuilder();
		qb.where(Properties.PastHistoryList.notEq(""));
		List<HealthRecord_down> list = qb.list();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
		// mHealthRecord_downDao.

	}

	public void User_delete() {
		mUser_downDao.deleteAll();
	}

	public static void DeleteDataByPrimaryKey(String uuid) {
		if (uuid.contains(";")) {
			String type = uuid.split(";")[0];
			String primaryKey = Tools.getPrimaryKey(uuid);
			// ����
			if ("DiabetesFlup_upload".equals(type)) {
				mDiabetesFlup_uploadDao.deleteByKey(Long.parseLong(primaryKey));
			}
			// �������
			if ("HealthExamination".equals(type)) {
				mHealthExaminationDao.deleteByKey(primaryKey);
			}
			// ��������
			if ("HealthRecord_down".equals(type)) {
				mHealthRecord_downDao.deleteByKey(primaryKey);
			}
			// ��Ѫѹ��ü�¼�ϴ�
			if ("HypertensionFlup_upload".equals(type)) {
				mHypertensionFlup_uploadDao.deleteByKey(Long
						.parseLong(primaryKey));
			}
			// ��������ͥ���Ӽ�¼
			if ("com.c102c.app.model.NeonatalVisit_upload".equals(type)) {
				mNeonatalVisit_uploadDao
						.deleteByKey(Long.parseLong(primaryKey));
			}
			// �в���42����Ӽ�¼
			if ("com.c102c.app.model.Postpartum42Visit_upload".equals(type)) {
				mPostpartum42Visit_uploadDao.deleteByKey(Long
						.parseLong(primaryKey));

			}
			// �в���������Ӽ�¼�ϴ�
			if ("com.c102c.app.model.PostpartumVisit_upload".equals(type)) {
				mPostpartumVisit_uploadDao.deleteByKey(Long
						.parseLong(primaryKey));
			}
			// �в��������¼�ϴ�
			if ("com.c102c.app.model.PregnantFirstCheck_upload".equals(type)) {
				mPregnantFirstCheck_uploadDao.deleteByKey(Long
						.parseLong(primaryKey));
			}
			// �в��������¼�ϴ�
			if ("com.c102c.app.model.PregnantRecheck_upload".equals(type)) {
				mPregnantRecheck_uploadDao.deleteByKey(Long
						.parseLong(primaryKey));
			}
			mRequestMessageDao.deleteByKey(uuid);
		}
	}

	/** ��ȡ���е��Ѿ�RequestMessage�ļ��ϵ����� */
	public static List<RequestMessage> getAllRequestMessagesListOK() {
		return mRequestMessageDao.queryBuilder()
				.where(RequestMessageDao.Properties.State.eq("1")).list();
	}

	// �������
	/** ͨ���������жϲ�����һ������ */
	// �������
	/** ͨ���������жϲ�����һ������ */
	public static Object query(Object object) {
		// ����
		if ("com.c102c.app.model.DiabetesFlup_upload".equals(object.getClass()
				.getName())) {
			return mDiabetesFlup_uploadDao
					.queryBuilder()
					.where(DiabetesFlup_uploadDao.Properties.Id
							.eq(((DiabetesFlup_upload) object).getId() + ""))
					.list();
		}
		// �������
		if ("com.c102c.app.model.HealthExamination".equals(object.getClass()
				.getName())) {
			return mHealthExaminationDao
					.queryBuilder()
					.where(HealthExaminationDao.Properties.BusinessKey
							.eq(((HealthExamination) object).getBusinessKey()))
					.list();
		}
		// ��������
		if ("com.c102c.app.model.HealthRecord_down".equals(object.getClass()
				.getName())) {
			return mHealthRecord_downDao
					.queryBuilder()
					.where(HealthRecord_downDao.Properties.PersonId
							.eq(((HealthRecord_down) object).getPersonId() + ""))
					.list();
		}
		// ��Ѫѹ��ü�¼�ϴ�
		if ("com.c102c.app.model.HypertensionFlup_upload".equals(object
				.getClass().getName())) {
			return mHypertensionFlup_uploadDao
					.queryBuilder()
					.where(HypertensionFlup_uploadDao.Properties.Id
							.eq(((HypertensionFlup_upload) object).getId() + ""))
					.list();
		}
		// ��������ͥ���Ӽ�¼
		if ("com.c102c.app.model.NeonatalVisit_upload".equals(object.getClass()
				.getName())) {
			return mNeonatalVisit_uploadDao
					.queryBuilder()
					.where(NeonatalVisit_uploadDao.Properties.Id
							.eq(((NeonatalVisit_upload) object).getId() + ""))
					.list();
		}
		// �в���42����Ӽ�¼
		if ("com.c102c.app.model.Postpartum42Visit_upload".equals(object
				.getClass().getName())) {
			return mPostpartum42Visit_uploadDao
					.queryBuilder()
					.where(Postpartum42Visit_uploadDao.Properties.Id
							.eq(((Postpartum42Visit_upload) object).getId()
									+ "")).list();
		}
		// �в���������Ӽ�¼�ϴ�
		if ("com.c102c.app.model.PostpartumVisit_upload".equals(object
				.getClass().getName())) {
			return mPostpartumVisit_uploadDao
					.queryBuilder()
					.where(PostpartumVisit_uploadDao.Properties.Id
							.eq(((PostpartumVisit_upload) object).getId() + ""))
					.list();
		}
		// �в��������¼�ϴ�
		if ("com.c102c.app.model.PregnantFirstCheck_upload".equals(object
				.getClass().getName())) {
			return mPregnantFirstCheck_uploadDao
					.queryBuilder()
					.where(PregnantFirstCheck_uploadDao.Properties.Id
							.eq(((PregnantFirstCheck_upload) object).getId()
									+ "")).list();
		}
		// �в��������¼�ϴ�
		if ("com.c102c.app.model.PregnantRecheck_upload".equals(object
				.getClass().getName())) {
			return mPregnantRecheck_uploadDao
					.queryBuilder()
					.where(PregnantRecheck_uploadDao.Properties.Id
							.eq(((PregnantRecheck_upload) object).getId() + ""))
					.list();
		}
		// ���ݿ�״̬��ѯ
		if ("com.c102c.app.model.RequestMessage".equals(object.getClass()
				.getName())) {
			return mRequestMessageDao
					.queryBuilder()
					.where(RequestMessageDao.Properties.Uuid
							.eq(((RequestMessage) object).getUuid())).list();
		}
		return null;
	}

	/** ���õ�����¼Ϊ�Ѿ��ϴ� */
	public static void setRequestMessageStateOK(String uuid) {
		List<RequestMessage> messages = mRequestMessageDao.queryBuilder()
				.where(RequestMessageDao.Properties.Uuid.eq(uuid)).list();
		for (int i = 0; i < messages.size(); i++) {
			RequestMessage message = messages.get(i);
			message.setState("1");
			mRequestMessageDao.update(message);
		}
	}

	public static List<RequestMessage> getAllRequestMessagesListToUpload() {
		// TODO Auto-generated method stub
		return mRequestMessageDao.queryBuilder()
				.where(RequestMessageDao.Properties.State.eq("0")).list();
	}

	public static void deleteAll() {
		mArea_downDao.deleteAll();
		mChildSpecial_downDao.deleteAll();
		mDiabetesFlup_downDao.deleteAll();
		mDiabetesFlup_uploadDao.deleteAll();
		mDiabetesSpecial_downDao.deleteAll();
		mDictionary_downDao.deleteAll();
		mHealthExaminationDao.deleteAll();
		mHealthRecord_downDao.deleteAll();
		mHealthRecord_StatusDao.deleteAll();
		mHypertensionFlup_downDao.deleteAll();
		mHypertensionFlup_uploadDao.deleteAll();
		mHypertensionScreen_uploadDao.deleteAll();
		mHypertensionSpecial_downDao.deleteAll();
		mNeonatalVisit_uploadDao.deleteAll();
		mOrg_downDao.deleteAll();
		mPastHistory_downDao.deleteAll();
		mPerson_downDao.deleteAll();
		mPostpartum42Visit_uploadDao.deleteAll();
		mPostpartumVisit_uploadDao.deleteAll();
		mPregnantFirstCheck_uploadDao.deleteAll();
		mPregnantRecheck_uploadDao.deleteAll();
		mPregnantSpecial_downDao.deleteAll();
		mRequestMessageDao.deleteAll();
		mUser_downDao.deleteAll();
		mLocalMeasurementEWenDao.deleteAll();
		mLocalMeasurementNiaoYeDao.deleteAll();
		mLocalMeasurementXinDianDao.deleteAll();
		mLocalMeasurementXueTangDao.deleteAll();
		mLocalMeasurementXueYaDao.deleteAll();
		mLocalMeasurementXueYangDao.deleteAll();
		mLocalMeasurementBaiXiBaoDao.deleteAll();
		mLocalJieZhenDao.deleteAll();
		mLocalZhuanZhenDao.deleteAll();
	}

	/*
	 * ���±��ؼ�¼��ѯ
	 */
	public static List<LocalMeasurementEWen> queryByPersonIdEWen(String personId) {
		return mLocalMeasurementEWenDao
				.queryBuilder()
				.where(LocalMeasurementEWenDao.Properties.PersonId.eq(personId))
				.list();
	}

	/*
	 * ��Һ���ؼ�¼��ѯ
	 */
	public static List<LocalMeasurementNiaoYe> queryByPersonIdNiaoYe(
			String personId) {
		return mLocalMeasurementNiaoYeDao
				.queryBuilder()
				.where(LocalMeasurementNiaoYeDao.Properties.PersonId
						.eq(personId)).list();
	}

	/*
	 * �ĵ籾�ؼ�¼��ѯ
	 */
	public static List<LocalMeasurementXinDian> queryByPersonIdXinDian(
			String personId) {
		return mLocalMeasurementXinDianDao
				.queryBuilder()
				.where(LocalMeasurementXinDianDao.Properties.PersonId
						.eq(personId)).list();
	}

	/*
	 * Ѫ�Ǳ��ؼ�¼��ѯ
	 */
	public static List<LocalMeasurementXueTang> queryByPersonIdXueTang(
			String personId) {
		return mLocalMeasurementXueTangDao
				.queryBuilder()
				.where(LocalMeasurementXueTangDao.Properties.PersonId
						.eq(personId)).list();
	}

	/*
	 * Ѫ�����ؼ�¼��ѯ
	 */
	public static List<LocalMeasurementXueYang> queryByPersonIdXueYang(
			String personId) {
		return mLocalMeasurementXueYangDao
				.queryBuilder()
				.where(LocalMeasurementXueYangDao.Properties.PersonId
						.eq(personId)).list();
	}

	/*
	 * Ѫѹ���ؼ�¼��ѯ
	 */
	public static List<LocalMeasurementXueYa> queryByPersonIdXueYa(
			String personId) {
		return mLocalMeasurementXueYaDao
				.queryBuilder()
				.where(LocalMeasurementXueYaDao.Properties.PersonId
						.eq(personId)).list();
	}

	/*
	 * ��ϸ�����ؼ�¼��ѯ
	 */
	public static List<LocalMeasurementBaiXiBao> queryByPersonIdBaiXiBao(
			String personId) {
		return mLocalMeasurementBaiXiBaoDao
				.queryBuilder()
				.where(LocalMeasurementBaiXiBaoDao.Properties.PersonId
						.eq(personId)).list();
	}

	/**
	 * �����¼��ѯ
	 */
	public static List<LocalJieZhen> queryByPersonIdJieZhen(String personId) {
		return mLocalJieZhenDao.queryBuilder()
				.where(LocalJieZhenDao.Properties.PersonId.eq(personId)).list();
	}

	/*
	 * ת���¼��ѯ
	 */
	public static List<LocalZhuanZhen> queryByPersonIdZhuanZhen(String personId) {
		return mLocalZhuanZhenDao.queryBuilder()
				.where(LocalZhuanZhenDao.Properties.PersonId.eq(personId))
				.list();
	}

	public static void deleteJieZhenByInt(int pos, String personId) {
		mLocalJieZhenDao.deleteByKey(queryByPersonIdJieZhen(personId).get(pos)
				.getId());
	}

	public static void deleteZhuanZhenByInt(int pos, String personId) {
		mLocalZhuanZhenDao.deleteByKey(queryByPersonIdZhuanZhen(personId).get(
				pos).getId());
	}

}
