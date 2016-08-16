package com.c102c.app.health_record_activity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.c102c.app.activity.BaseActivity;
import com.c102c.app.base.AppDB;
import com.c102c.app.commu.Basesence;
import com.c102c.app.commu.Fetch;
import com.c102c.app.commu.Fetch_by_li;
import com.c102c.app.ggws_activity.ETZXDAActivity;
import com.c102c.app.ggws_activity.XSEJTFSJL_uploadActivity;
import com.c102c.app.ggws_activity.YCFActivity;
import com.c102c.app.ggws_activity.YCFCH42FSJL_uploadActivity;
import com.c102c.app.ggws_activity.YCFCHFSJL_uploadActivity;
import com.c102c.app.ggws_activity.YCFCJJL_uploadActivity;
import com.c102c.app.ggws_activity.YCFFJJL_uploadActivity;
import com.c102c.app.ggws_activity.YCFZXDA_downloadActivity;
import com.c102c.app.mbgy_activity.GXYSFJLActivity;
import com.c102c.app.mbgy_activity.GXYZXDAActivity;
import com.c102c.app.mbgy_activity.TNBSFJLActivity;
import com.c102c.app.mbgy_activity.TNBZXDAActivity;
import com.c102c.app.model.ChildSpecial_down;
import com.c102c.app.model.DiabetesFlup_down;
import com.c102c.app.model.DiabetesSpecial_down;
import com.c102c.app.model.HealthExamination;
import com.c102c.app.model.HealthRecord_down;
import com.c102c.app.model.HypertensionFlup_down;
import com.c102c.app.model.HypertensionSpecial_down;
import com.c102c.app.model.PregnantSpecial_down;
import com.c102c.app.model.RequestMessage;
import com.c102c.app.utils.ThreeColmunAdapter;
import com.c102c.app.utils.Tools;
import com.c102c.app.utils.Util;
import com.c102c.app.utils.WebTool;
import com.c102c.finalJKYTJ.R;
import com.health.measurement.ReadIDCardActivity;
import com.health.util.T;

@SuppressLint("HandlerLeak")
public class UsersListActivity extends BaseActivity {

	private ListView mUsersList;
	private ProgressBar mProgressBar;
	private LinearLayout mlinerlayout;
	private Button mUpdataDB;
	private Button mAdduser;
	private Button mUserslist_btn_shuaka;
	private EditText mSearchEditText;
	private TextView third;
	private TextView second;
	private TextView first;
	private List<String[]> datas = null;
	private ThreeColmunAdapter mAdapter;
	private List<Object> temp = null;
	private RelativeLayout mLayout;
	private HealthExamination hex;
	private Comparator comp;
	private String[] method = { "M0100020101", "M0100040101", "M0100050101",
			"M0100060101", "M0100070101", "M0100060102", "M0100070102",
			"M0100030101" };
	// 0健康档案，1孕产妇，2儿童，3高血压，4糖尿病 ,5高血压随访，6糖尿病随访 ,7健康体检下载,8离线上传

	public static final int HEALTH_ARCHIVE = 0; // 健康档案
	public static final int PREGNENT = 1; // 孕产妇
	public static final int CHILD = 2; // 儿童
	public static final int HYPERTENSION = 3; // 高血压
	public static final int DIABETES = 4; // 糖尿病
	public static final int HYPERTENSION_RECORD = 5;// 高血压随访记录
	public static final int DIABETES_RECORD = 6; // 糖尿病随访记录
	public static final int HEALTH_EXAMINATION = 7; // 健康体检下载

	private int from;
	private String token;

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		AppDB.getInstance(UsersListActivity.this);
		from = getIntent().getIntExtra("from", -1);
		token = getIntent().getStringExtra("name");
		super.onCreate(savedInstanceState);
		initViews();
		if (from != -1) {
			switch (from) {
			case 0:
				temp = AppDB.get(new HealthRecord_down());
				mLayout.setBackgroundResource(R.drawable.background_yhlb);
				break;
			case 1:
				temp = AppDB.get(new PregnantSpecial_down());
				mLayout.setBackgroundResource(R.drawable.backgrond_ycf);
				mAdduser.setBackgroundResource(R.drawable.ycftj_btn);
				mUpdataDB.setBackgroundResource(R.drawable.ycf_update_btn);
				third.setText("建册日期");
				break;
			case 2:
				temp = AppDB.get(new ChildSpecial_down());
				third.setText("出生年月");
				mLayout.setBackgroundResource(R.drawable.background_xse);
				break;
			case 3:
				third.setText("建档日期");
				temp = AppDB.get(new HypertensionSpecial_down());
				mLayout.setBackgroundResource(R.drawable.background_mbgy_list);
				break;
			case 4:
				third.setText("建档日期");
				temp = AppDB.get(new DiabetesSpecial_down());
				mLayout.setBackgroundResource(R.drawable.background_mbgy_list);
				break;
			case 5:
				third.setText("下次随访日期");
				temp = AppDB.get(new HypertensionFlup_down());
				mLayout.setBackgroundResource(R.drawable.background_mbgy_list);
				break;
			case 6:
				third.setText("下次随访日期");
				temp = AppDB.get(new DiabetesFlup_down());
				mLayout.setBackgroundResource(R.drawable.background_mbgy_list);
				break;
			case 7:
				first.setText("档案编号");
				third.setText("体检日期");
				mSearchEditText.setVisibility(View.INVISIBLE);
				mUserslist_btn_shuaka.setVisibility(View.INVISIBLE);
				temp = AppDB.get(new HealthExamination());
				temp = getThecurrentUserEaxmina(temp);
				break;
			case 8:
				first.setText("姓名");
				second.setText("档案类型");
				third.setText("上传状态");
				mLayout.setBackgroundResource(R.drawable.background_offline);
				mUpdataDB
						.setBackgroundResource(R.drawable.deleteuploaded_button);
				mAdduser.setBackgroundResource(R.drawable.autoupload);
				mSearchEditText.setVisibility(View.INVISIBLE);
				mUserslist_btn_shuaka.setVisibility(View.INVISIBLE);
				temp = AppDB.get(new RequestMessage());
				loadHealthRecord_downList(temp);
				break;
			case 10:

				temp = new ArrayList<Object>();
				List<Object> list = AppDB.get(new PregnantSpecial_down());
				PregnantSpecial_down pre;
				for (Object obj : list) {

					pre = (PregnantSpecial_down) obj;
					//system.out.println("======" + pre.getCheckStatus());
					if (pre.getCheckStatus().equals("0")) {
						temp.add(pre);
					}
				}
				third.setText("建册日期");
				break;
			// /////////////孕产妇复检
			case 11:

				temp = new ArrayList<Object>();
				List<Object> list1 = AppDB.get(new PregnantSpecial_down());
				PregnantSpecial_down pre1;
				for (Object obj : list1) {

					pre1 = (PregnantSpecial_down) obj;
					//system.out.println("======" + pre1.getCheckStatus());
					if (!pre1.getCheckStatus().equals("0")
							&& !pre1.getCheckStatus().equals("5")) {
						temp.add(pre1);
					}
				}
				third.setText("建册日期");
				break;
			// /////////////孕产妇产后初检
			case 12:

				temp = new ArrayList<Object>();
				List<Object> list2 = AppDB.get(new PregnantSpecial_down());
				PregnantSpecial_down pre2;
				for (Object obj : list2) {

					pre2 = (PregnantSpecial_down) obj;
					//system.out.println("======" + pre2.getCheckStatus());
					if (pre2.getVisitStatus().equals("2")) {
						temp.add(pre2);
					}
				}
				third.setText("建册日期");
				break;
			case 13:

				temp = new ArrayList<Object>();
				List<Object> list3 = AppDB.get(new PregnantSpecial_down());
				PregnantSpecial_down pre3;
				for (Object obj : list3) {

					pre3 = (PregnantSpecial_down) obj;
					//system.out.println("======" + pre3.getCheckStatus());
					if (pre3.getVisit42Status().equals("2")) {
						temp.add(pre3);
					}
				}
				third.setText("建册日期");
				break;

			default:
				break;
			}
			if (temp.size() != 0) {
				loadHealthRecord_downList(temp);
			} else {
				if (from == 10 || from == 11 || from == 12 || from == 13) {
					Toast.makeText(UsersListActivity.this, "没有相关人员需要操作", 0)
							.show();
					mProgressBar.setVisibility(View.INVISIBLE);
					finish();
					return;
				}
				if (from != -1 && from != 8)
					Fetch.communicate(method[from], UsersListActivity.this,
							handler);
			}
		}
		if (token != null) {
			from = 0;
			mlinerlayout.setVisibility(View.VISIBLE);
			temp = AppDB.get(new HealthRecord_down());
			mSearchEditText.setText(token.trim());
			textwatcher.afterTextChanged(null);
		}
	}

	private List<Object> getThecurrentUserEaxmina(List<Object> temp) {
		List<Object> answer = new ArrayList<Object>();
		HealthExamination aExam = new HealthExamination();
		String healthfealnum = Basesence.getTempHealthRecord_down()
				.getHealthFileNumber();
		for (Object obj : temp) {
			aExam = (HealthExamination) obj;
//			System.out.println(aExam.getHealthFileNumber());
//			System.out.println(healthfealnum);
			if (aExam.getHealthFileNumber().equals(healthfealnum)) {
				answer.add(aExam);
			}
		}
		return answer;
	}

	@SuppressWarnings("unchecked")
	private void loadHealthRecord_downList(final List<Object> objects) {
		// TODO Auto-generated method stub
		mProgressBar.setVisibility(View.INVISIBLE);
		mlinerlayout.setVisibility(View.VISIBLE);
		datas = new ArrayList<String[]>();
		String[] item;
		switch (from) {
		case 0:
			HealthRecord_down healthRecord_down;
			for (Object object : objects) {
				healthRecord_down = (HealthRecord_down) object;
				// mAppDB.add(healthRecord_down);
				item = new String[] { healthRecord_down.getPersonId(),
						healthRecord_down.getName(),
						healthRecord_down.getIdCard() };
				datas.add(item);
			}
			break;
		case 1:
			PregnantSpecial_down pregnantSpecial_down;
			for (Object object : objects) {
				pregnantSpecial_down = (PregnantSpecial_down) object;
				// mAppDB.add(healthRecord_down);
				item = new String[] { pregnantSpecial_down.getPersonId(),
						pregnantSpecial_down.getName(),
						pregnantSpecial_down.getRegisterDate() };
				datas.add(item);
			}
			break;
		case 2:
			ChildSpecial_down childSpecial_down;
			for (Object object : objects) {
				childSpecial_down = (ChildSpecial_down) object;
				// mAppDB.add(healthRecord_down);
				item = new String[] { childSpecial_down.getPersonId(),
						childSpecial_down.getName(),
						childSpecial_down.getBirthday() };
				datas.add(item);
			}
			break;
		case 3:
			HypertensionSpecial_down hysd;
			for (Object object : objects) {
				hysd = (HypertensionSpecial_down) object;
				item = new String[] { hysd.getPersonId(), hysd.getName(),
						hysd.getRegisterDate() };
				datas.add(item);
			}
			break;
		case 4:
			DiabetesSpecial_down dsd;
			for (Object object : objects) {
				dsd = (DiabetesSpecial_down) object;
				item = new String[] { dsd.getPersonId(), dsd.getName(),
						dsd.getRegisterDate() };
				datas.add(item);
			}
			break;
		case 5:
			Collections.sort(objects, comp);
			HypertensionFlup_down hyfd;
			for (Object object : objects) {
				hyfd = (HypertensionFlup_down) object;
				item = new String[] { hyfd.getPersonId(), hyfd.getName(),
						hyfd.getNextFlupDate() };
				datas.add(item);
			}
			break;
		case 6:
			Collections.sort(objects, comp);
			DiabetesFlup_down dfdn;
			for (Object object : objects) {
				dfdn = (DiabetesFlup_down) object;
				item = new String[] { dfdn.getPersonId(), dfdn.getName(),
						dfdn.getNextFlupDate() };
				datas.add(item);
			}
			break;
		case 7:
			for (Object object : objects) {
				hex = (HealthExamination) object;
				item = new String[] { hex.getHealthFileNumber(), hex.getName(),
						hex.getExaminationDate() };
				datas.add(item);
			}
			break;
		case 8:
			for (Object object : objects) {
				RequestMessage message = (RequestMessage) object;
				item = new String[] { message.getName(),
						Tools.getType(message.getUuid()),
						"0".equals(message.getState()) ? "未上传" : "已上传",
						message.getUuid() };
				datas.add(item);
			}
			mUsersList
					.setOnItemLongClickListener(new OnItemLongClickListener() {

						@Override
						public boolean onItemLongClick(AdapterView<?> parent,
								View view, final int position, long id) {
							// 使用对话框提示来删除数据
							AlertDialog.Builder builder = new Builder(
									UsersListActivity.this);
							builder.setMessage("确认删除吗？");
							builder.setTitle("提示");
							builder.setPositiveButton("确定",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											AppDB.DeleteDataByPrimaryKey(datas
													.get(position)[3]);
											datas.remove(position);
											mAdapter.notifyDataSetChanged();
										}
									});
							builder.setNegativeButton("取消",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											dialog.dismiss();
										}
									});
							// builder.setNeutralButton("上传", null);
							builder.create().show();
							return true;
						}
					});
			break;
		case 10:
			PregnantSpecial_down pregnantSpecial_down1;
			for (Object object : objects) {
				pregnantSpecial_down1 = (PregnantSpecial_down) object;
				// mAppDB.add(healthRecord_down);
				item = new String[] { pregnantSpecial_down1.getPersonId(),
						pregnantSpecial_down1.getName(),
						pregnantSpecial_down1.getRegisterDate() };
				datas.add(item);
			}
			break;
		case 11:
			PregnantSpecial_down pregnantSpecial_down2;
			for (Object object : objects) {
				pregnantSpecial_down2 = (PregnantSpecial_down) object;
				// mAppDB.add(healthRecord_down);
				item = new String[] { pregnantSpecial_down2.getPersonId(),
						pregnantSpecial_down2.getName(),
						pregnantSpecial_down2.getRegisterDate() };
				datas.add(item);
			}
			break;
		case 12:
			PregnantSpecial_down pregnantSpecial_down3;
			for (Object object : objects) {
				pregnantSpecial_down3 = (PregnantSpecial_down) object;
				// mAppDB.add(healthRecord_down);
				item = new String[] { pregnantSpecial_down3.getPersonId(),
						pregnantSpecial_down3.getName(),
						pregnantSpecial_down3.getRegisterDate() };
				datas.add(item);
			}
			break;
		case 13:
			PregnantSpecial_down pregnantSpecial_down4;
			for (Object object : objects) {
				pregnantSpecial_down4 = (PregnantSpecial_down) object;
				// mAppDB.add(healthRecord_down);
				item = new String[] { pregnantSpecial_down4.getPersonId(),
						pregnantSpecial_down4.getName(),
						pregnantSpecial_down4.getRegisterDate() };
				datas.add(item);
			}
			break;
		default:
			break;
		}
		mAdapter = new ThreeColmunAdapter(UsersListActivity.this, datas);
		mUsersList.setAdapter(mAdapter);
		mUsersList.setOnItemClickListener(new OnItemClickListener() {
			Intent intent = null;
			Bundle bundle = new Bundle();

			// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓跳转↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (from) {
				case 0:
					HealthRecord_down temp = (HealthRecord_down) objects
							.get(arg2);
					Basesence.setTempHealthRecord_down(temp);
					intent = new Intent(UsersListActivity.this,
							UserDetailActivity.class);
					break;
				case 1:
					PregnantSpecial_down pregnantSpecial_down = (PregnantSpecial_down) objects
							.get(arg2);
					Basesence.setPregnantSpecial_down(pregnantSpecial_down);
					// Toast.makeText(UsersListActivity.this,
					// pregnantSpecial_down.getName(), 0).show();
					intent = new Intent(UsersListActivity.this,
							YCFZXDA_downloadActivity.class);
					break;
				case 2:
					ChildSpecial_down childSpecial_down = (ChildSpecial_down) objects
							.get(arg2);
					Basesence.setChildSpecial_down(childSpecial_down);
					intent = new Intent(UsersListActivity.this,
							ETZXDAActivity.class);
					break;
				case 3:
					HypertensionSpecial_down tmphs = (HypertensionSpecial_down) objects
							.get(arg2);
					Basesence.setHypertensionspecial_down(tmphs);
					intent = new Intent(UsersListActivity.this,
							GXYZXDAActivity.class);
					break;
				case 4:
					DiabetesSpecial_down dsdn = (DiabetesSpecial_down) objects
							.get(arg2);
					Basesence.setDiabetesspecial_down(dsdn);
					intent = new Intent(UsersListActivity.this,
							TNBZXDAActivity.class);
					break;
				case 5:
					HypertensionFlup_down hyfd = (HypertensionFlup_down) objects
							.get(arg2);
					Basesence.sethypertensionflup_down(hyfd);
					intent = new Intent(UsersListActivity.this,
							GXYSFJLActivity.class);
					bundle = new Bundle();
					bundle.putString("from", "list");
					intent.putExtras(bundle);
					break;
				case 6:
					DiabetesFlup_down dfdn = (DiabetesFlup_down) objects
							.get(arg2);
					Basesence.setDiabetesFlup_down(dfdn);
					intent = new Intent(UsersListActivity.this,
							TNBSFJLActivity.class);
					bundle = new Bundle();
					bundle.putString("from", "list");
					intent.putExtras(bundle);
					break;
				case 7:
					hex = (HealthExamination) objects.get(arg2);
					Basesence.setHealthExamination(hex);
					intent = new Intent(UsersListActivity.this,
							JKTJActivity.class);
					bundle = new Bundle();
					HealthRecord_down temprecord = Basesence
							.getTempHealthRecord_down();
					bundle.putString("from", "list");
					bundle.putString("name", temprecord.getName());
					bundle.putString("personid", temprecord.getPersonId());
					bundle.putString("NO", temprecord.getHealthFileNumber());
					intent.putExtras(bundle);
					break;
				// 啟晖添加
				case 8:
					String[] data = (String[]) mAdapter.getItem(arg2);
					// 获取类名来判断类型。
					String[] strs = data[3].split(";");
					String type = "";
					if (strs.length > 0) {
						type = strs[0];
					}
					switch (type) {
					case "DiabetesFlup_upload":
						intent = new Intent(UsersListActivity.this,
								TNBSFJLActivity.class);
						intent.putExtra("from", "localHistory");
						break;
					case "HypertensionFlup_upload":
						intent = new Intent(UsersListActivity.this,
								GXYSFJLActivity.class);
						intent.putExtra("from", "localHistory");
						break;
					case "HealthRecord_down":
						intent = new Intent(UsersListActivity.this,
								JKDAActivity.class);
						intent.putExtra("extra", "adduser");
						intent.putExtra("from", "localHistory");
						break;
					case "HealthExamination":
						intent = new Intent(UsersListActivity.this,
								JKTJActivity.class);
						intent.putExtra("from", "localHistory");
						break;
					case "PregnantFirstCheck_upload":
						intent = new Intent(UsersListActivity.this,
								YCFCJJL_uploadActivity.class);
						break;
					case "PregnantRecheck_upload":
						intent = new Intent(UsersListActivity.this,
								YCFFJJL_uploadActivity.class);
						break;
					case "PostpartumVisit_upload":
						intent = new Intent(UsersListActivity.this,
								YCFCHFSJL_uploadActivity.class);
						break;
					case "Postpartum42Visit_upload":
						intent = new Intent(UsersListActivity.this,
								YCFCH42FSJL_uploadActivity.class);
						break;
					case "NeonatalVisit_upload":
						intent = new Intent(UsersListActivity.this,
								XSEJTFSJL_uploadActivity.class);
					default:
						break;
					}
					if (intent != null) {
						intent.putExtra("uuid", data[3]);
						intent.putExtra("from", "localHistory");
					}
					break;

				case 10:
					PregnantSpecial_down pregnantSpecial_down1 = (PregnantSpecial_down) objects
							.get(arg2);
					Basesence.setPregnantSpecial_down(pregnantSpecial_down1);
					// Toast.makeText(UsersListActivity.this,
					// pregnantSpecial_down.getName(), 0).show();
					intent = new Intent(UsersListActivity.this,
							YCFCJJL_uploadActivity.class);
					break;
				// //////////孕产妇复检检
				case 11:
					PregnantSpecial_down pregnantSpecial_down2 = (PregnantSpecial_down) objects
							.get(arg2);
					Basesence.setPregnantSpecial_down(pregnantSpecial_down2);
					// Toast.makeText(UsersListActivity.this,
					// pregnantSpecial_down.getName(), 0).show();
					intent = new Intent(UsersListActivity.this,
							YCFFJJL_uploadActivity.class);
					break;
				case 12:
					PregnantSpecial_down pregnantSpecial_down3 = (PregnantSpecial_down) objects
							.get(arg2);
					Basesence.setPregnantSpecial_down(pregnantSpecial_down3);
					// Toast.makeText(UsersListActivity.this,
					// pregnantSpecial_down.getName(), 0).show();
					intent = new Intent(UsersListActivity.this,
							YCFCHFSJL_uploadActivity.class);
					break;
				case 13:
					PregnantSpecial_down pregnantSpecial_down4 = (PregnantSpecial_down) objects
							.get(arg2);
					Basesence.setPregnantSpecial_down(pregnantSpecial_down4);
					// Toast.makeText(UsersListActivity.this,
					// pregnantSpecial_down.getName(), 0).show();
					intent = new Intent(UsersListActivity.this,
							YCFCH42FSJL_uploadActivity.class);
					break;
				default:
					break;
				}
				startActivity(intent);
			}
		});
		// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑跳转↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	}

	// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓健康档案↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	private void loadUsersList(final List<HealthRecord_down> users) {
		// TODO Auto-generated method stub

		for (HealthRecord_down healthRecord_down : users) {
			AppDB.add(healthRecord_down);
		}
		temp = AppDB.get(new HealthRecord_down());
		loadHealthRecord_downList(temp);
	}

	// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓高血压↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	private void loadUsersList2(final List<HypertensionSpecial_down> users) {
		// TODO Auto-generated method stub
		for (HypertensionSpecial_down Hy_down : users) {
			AppDB.add(Hy_down);
		}
		temp = AppDB.get(new HypertensionSpecial_down());
		loadHealthRecord_downList(temp);
	}

	// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓糖尿病↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	private void loadUsersList3(final List<DiabetesSpecial_down> users) {
		// TODO Auto-generated method stub
		for (DiabetesSpecial_down Hy_down : users) {
			AppDB.add(Hy_down);
		}
		temp = AppDB.get(new DiabetesSpecial_down());
		loadHealthRecord_downList(temp);
	}

	// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓高血压随访记录↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	private void loadUsersList4(final List<HypertensionFlup_down> users) {
		// TODO Auto-generated method stub
		for (HypertensionFlup_down Hy_down : users) {
			AppDB.add(Hy_down);
		}
		temp = AppDB.get(new HypertensionFlup_down());
		loadHealthRecord_downList(temp);
	}

	// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓糖尿病随访记录↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	private void loadUsersList5(final List<DiabetesFlup_down> users) {
		// TODO Auto-generated method stub
		for (DiabetesFlup_down Hy_down : users) {
			AppDB.add(Hy_down);
		}
		temp = AppDB.get(new DiabetesFlup_down());
		loadHealthRecord_downList(temp);
	}

	// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓健康体检下载↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	private void loadUsersList6(final List<HealthExamination> users) {
		// TODO Auto-generated method stub
		for (HealthExamination Hy_down : users) {
			AppDB.add(Hy_down);
		}
		temp = AppDB.get(new HealthExamination());
		temp = getThecurrentUserEaxmina(temp);
		loadHealthRecord_downList(temp);
	}

	// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓儿童档案下载↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	private void loadUsersList7(final List<ChildSpecial_down> users) {
		// TODO Auto-generated method stub
		for (ChildSpecial_down Hy_down : users) {
			AppDB.add(Hy_down);
		}
		temp = AppDB.get(new ChildSpecial_down());
		// temp = getThecurrentUserEaxmina(temp);
		loadHealthRecord_downList(temp);
	}

	// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓孕产妇案下载↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	private void loadUsersList8(final List<PregnantSpecial_down> users) {
		// TODO Auto-generated method stub
		for (PregnantSpecial_down Hy_down : users) {
			AppDB.add(Hy_down);
		}
		temp = AppDB.get(new PregnantSpecial_down());
		// temp = getThecurrentUserEaxmina(temp);
		loadHealthRecord_downList(temp);
	}

	private OnClickListener clicklistener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v == mUpdataDB) {
				mProgressBar.setVisibility(View.VISIBLE);
				mlinerlayout.setVisibility(View.INVISIBLE);
				// 啟晖添加
				if (from != 8) {
					Fetch.communicate(method[from], UsersListActivity.this,
							handler);
				} else if (from == 8) {
					List<RequestMessage> alloks = AppDB
							.getAllRequestMessagesListOK();
					for (int i = 0; i < alloks.size(); i++) {
						AppDB.DeleteDataByPrimaryKey(alloks.get(i).getUuid());
					}
					loadLocalHistoryData();
				}
			} else if (v == mAdduser) {
				if (from == 0) {
					Intent intent = new Intent(UsersListActivity.this,
							JKDAActivity.class);
					Bundle extras = new Bundle();
					extras.putString("extra", "adduser");
					intent.putExtras(extras);
					startActivity(intent);
				} else if (from == 7) {
					Intent intent = new Intent(UsersListActivity.this,
							JKTJActivity.class);
					HealthRecord_down ah = Basesence.getTempHealthRecord_down();
					Bundle extras = new Bundle();
					extras.putString("from", "new");
					extras.putString("name", ah.getName());
					extras.putString("personid", ah.getPersonId());
					extras.putString("NO", ah.getHealthFileNumber());
					intent.putExtras(extras);
					startActivity(intent);
				} else if (from == 8) {
					if (!WebTool.isNetConnected(UsersListActivity.this)) {
						T.showShort(UsersListActivity.this, "没有网络，请检查连接。");
						return;
					}
					if (datas.size() > 0) {
						// 自动上传全部数据
//						ProgressDialog MyDialog = ProgressDialog.show(
//								UsersListActivity.this, "提示", "数据正在上传，请等待...",
//								true);
//						MyDialog.show();
						// 执行上传数据的操作。
						List<RequestMessage> messages = AppDB
								.getAllRequestMessagesListToUpload();
						for (int i = 0; i < messages.size(); i++) {

//							String a = "\w{8}\-\w{4}\-\w{4}\-\w{16}";
							RequestMessage message = messages.get(i);
							String primaryKey = Tools.getPrimaryKey(message
									.getUuid());
							String xml = message.getXml();
							String uuid = message.getUuid();
							Fetch_by_li.communicate(uuid,
									UsersListActivity.this, handler, xml, true);
						}
//						MyDialog.dismiss();
						loadLocalHistoryData();
					}
					// MyDialog.show();
					// Fetch
				} else if (from == 1) {
					Intent intent = new Intent(UsersListActivity.this,
							YCFActivity.class);
					startActivity(intent);
				}
			} else if (v == mUserslist_btn_shuaka) {
				Intent intent = new Intent(UsersListActivity.this,
						ReadIDCardActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("extra", "shuaka");
				intent.putExtras(bundle);
				startActivity(intent);
				finish();
			}
		}
	};
	private TextWatcher textwatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub

		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			String aimName = mSearchEditText.getText().toString();
			boolean tag = false;
			HealthRecord_down temp_health = null;
			HypertensionSpecial_down temp_hsd = null;
			DiabetesSpecial_down temp_dsdn = null;
			HypertensionFlup_down temp_hfdn = null;
			DiabetesFlup_down temp_dfdm = null;
			ChildSpecial_down child = null;
			PregnantSpecial_down pre = null;
			ChildSpecial_down temp_chsd = null;
			PregnantSpecial_down temp_preg = null;
			List<Object> tmp = new ArrayList<Object>();
			switch (from) {
			case 0:
				for (Object healthRecord_down : temp) {
					temp_health = (HealthRecord_down) healthRecord_down;
					if (temp_health.getName().contains(aimName)) {
						tmp.add(temp_health);
						tag = true;
					}
				}
				break;
			case 1:
				for (Object object : temp) {
					pre = (PregnantSpecial_down) object;
					if (pre.getName().contains(aimName)) {
						tmp.add(pre);
						tag = true;
					}
				}
				break;
			case 2:
				for (Object object : temp) {
					child = (ChildSpecial_down) object;
					if (child.getName().contains(aimName)) {
						tmp.add(child);
						tag = true;
					}
				}
				break;
			case 3:
				for (Object object : temp) {
					temp_hsd = (HypertensionSpecial_down) object;
					if (temp_hsd.getName().contains(aimName)) {
						tmp.add(temp_hsd);
						tag = true;
					}
				}
				break;
			case 4:
				for (Object object : temp) {
					temp_dsdn = (DiabetesSpecial_down) object;
					if (temp_dsdn.getName().contains(aimName)) {
						tmp.add(temp_dsdn);
						tag = true;
					}
				}
				break;
			case 5:
				for (Object object : temp) {
					temp_hfdn = (HypertensionFlup_down) object;
					if (temp_hfdn.getName().contains(aimName)) {
						tmp.add(temp_dsdn);
						tag = true;
					}
				}
				break;
			case 6:
				for (Object object : temp) {
					temp_dfdm = (DiabetesFlup_down) object;
					if (temp_dfdm.getName().contains(aimName)) {
						tmp.add(temp_dsdn);
						tag = true;
					}
				}
				break;
			default:
				break;
			}

			if (tag == false) {

			}
			loadHealthRecord_downList(tmp);
		}
	};

	@Override
	protected void initViews() {
		comp = new Mycompare();
		setContentView(R.layout.users_list);
		mLayout = (RelativeLayout) findViewById(R.id.users_list_layout);
		mUpdataDB = (Button) findViewById(R.id.userslist_btn_update);
		mAdduser = (Button) findViewById(R.id.userslist_btn_add);
		first = (TextView) findViewById(R.id.tv_head_first);
		second = (TextView) findViewById(R.id.tv_head_second);
		third = (TextView) findViewById(R.id.tv_head_thrid);
		mUserslist_btn_shuaka = (Button) findViewById(R.id.userslist_btn_shuaka);
		mSearchEditText = (EditText) findViewById(R.id.userslist_et_search);
		mSearchEditText.addTextChangedListener(textwatcher);
		mUpdataDB.setOnClickListener(clicklistener);
		mAdduser.setOnClickListener(clicklistener);
		mUserslist_btn_shuaka.setOnClickListener(clicklistener);
		mlinerlayout = (LinearLayout) findViewById(R.id.listview_parent);
		mUsersList = (ListView) findViewById(R.id.userslist_lv_userlist);
		mProgressBar = (ProgressBar) findViewById(R.id.userslist_pb);

		// ///////////////曾德森// 0健康档案，1孕产妇，2儿童，3高血压，4糖尿病 ,5高血压随访，6糖尿病随访 ,7健康体检下载
		// 曾德森定义 10 孕产妇初检 11孕产妇复检 12孕产妇产后初检 13孕产妇产后42复检
		if (from == 2 || from == 10 || from == 11 || from == 12 || from == 13) {
			mAdduser.setVisibility(View.INVISIBLE);
			if (from == 10 || from == 11 || from == 12 || from == 13) {
				mUpdataDB.setVisibility(View.INVISIBLE);
			}
		}

	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == Basesence.commusucc) {
				String a;
				a = (String) msg.obj;
				switch (msg.arg1) {
				case Basesence.M0100020101:// 健康档案下载
					List<HealthRecord_down> mHealthRecord_downList = new ArrayList<HealthRecord_down>(
							Util.getHealthRecord_down(a));
					loadUsersList(mHealthRecord_downList);
					break;
				case Basesence.M0100060101:// 高血压下载
					List<HypertensionSpecial_down> mHyertensionSpecial_down = new ArrayList<HypertensionSpecial_down>(
							Util.getHypertensionSpecial_down(a));
					loadUsersList2(mHyertensionSpecial_down);
					break;
				case Basesence.M0100070101:// 糖尿病下载
					List<DiabetesSpecial_down> mDiabetesSpecial_down = new ArrayList<DiabetesSpecial_down>(
							Util.getDiabetesSpecial_down(a));
					loadUsersList3(mDiabetesSpecial_down);
					break;
				case Basesence.M0100060102:// 高血压随访记录下载
					List<HypertensionFlup_down> mHypertensionFlup_down = new ArrayList<HypertensionFlup_down>(
							Util.getHypertensionFlup_down(a));
					loadUsersList4(mHypertensionFlup_down);
					break;
				case Basesence.M0100070102:// 糖尿病随访记录下载
					List<DiabetesFlup_down> mDiabetesFlup_down = new ArrayList<DiabetesFlup_down>(
							Util.getDiabetesFlup_down(a));
					loadUsersList5(mDiabetesFlup_down);
					break;
				case Basesence.M0100030101:// 健康体检下载
					List<HealthExamination> hexlist = new ArrayList<HealthExamination>(
							Util.getHealthExaminationlist_byjiang(a));
					loadUsersList6(hexlist);
					// List<HealthExamination> hexlist = new
					// ArrayList<HealthExamination>(
					// Util.getHealthExamination(a));
					// loadUsersList5(mDiabetesFlup_down);
					break;
				case Basesence.M0100050101:
					List<ChildSpecial_down> chslist = new ArrayList<ChildSpecial_down>(
							Util.getChildSpecial_down(a));
					loadUsersList7(chslist);
					break;
				case Basesence.M0100040101:
					List<PregnantSpecial_down> prelist = new ArrayList<PregnantSpecial_down>(
							Util.getPregnantSpecial_down(a));
					loadUsersList8(prelist);
					break;
				default:
					break;
				}
			} else if (msg.what == Basesence.commufail) {
				if (msg.obj == null)
					Toast.makeText(UsersListActivity.this, "接口调用失败",
							Toast.LENGTH_SHORT).show();
				else
					Toast.makeText(UsersListActivity.this, "接口调用失败" + msg.obj,
							Toast.LENGTH_SHORT).show();
				mProgressBar.setVisibility(View.INVISIBLE);
				mlinerlayout.setVisibility(View.VISIBLE);
				// loadUsersList(mHealthRecord_downList);
			}
			// 啟晖添加
			if (msg.what == Basesence.commusucc) {

				if (msg.obj != null)
					Toast.makeText(UsersListActivity.this, "成功",
							Toast.LENGTH_SHORT).show();
				// 删除+ msg.obj
				else
					Toast.makeText(UsersListActivity.this, "成功",
							Toast.LENGTH_SHORT).show();
			}
			if (from == 8) {
				loadLocalHistoryData();
			}
		}
	};

	// 啟晖添加
	/*** 继续重新加载数据 */
	private void loadLocalHistoryData() {
		temp = AppDB.get(new RequestMessage());
		loadHealthRecord_downList(temp);
		mProgressBar.setVisibility(View.INVISIBLE);
		mlinerlayout.setVisibility(View.VISIBLE);
	}

}

class Mycompare implements Comparator {
	public int compare(Object o1, Object o2) {
		Field f = null;
		try {
			f = o1.getClass().getDeclaredField("nextFlupDate");
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String date1 = null;
		String date2 = null;
		try {
			f.setAccessible(true);
			date1 = (String) f.get(o1);
			date2 = (String) f.get(o2);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date1.compareTo(date2);
	}
}
