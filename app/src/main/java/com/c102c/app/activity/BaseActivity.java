package com.c102c.app.activity;

import com.c102c.app.commu.Basesence;
import com.c102c.app.model.User_down;
import com.health.util.ChoiceEditText;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

public abstract class BaseActivity extends Activity {
	/** ��ʼ��view */
	protected abstract void initViews();

	/** �����е�bean�ҵ�idȻ����ʾ������ */
	protected void setAllText() {

	}

	/*** ��id����ȡ���ݣ����ø�bean�� */
	protected Object getAllText() {
		return null;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initViews();
		setAllText();
	}

	/** ͨ��id��������ʾText�ı����� */
	public void setTextByid(String content, int id) {
		if (!TextUtils.isEmpty(content)) {
			EditText et = (EditText) findViewById(id);
			et.setText(content);
		}
	}

	/** ��editText����Ҫѡ������� */
	public void setChoiceEditText(int et_Id, int arrayId) {
		if (arrayId == -1) {
			((ChoiceEditText) findViewById(et_Id)).setFixItems(null);
		} else
			((ChoiceEditText) findViewById(et_Id)).setFixItems(getResources()
					.getStringArray(arrayId));
	}

	// ����������������������������������������������������������������������ɭ��ӡ�����������������������������������������������������������������
	/** ����EditTextΪ��ʱ����ʾ��Ϣ */
	public boolean setEditTextNullMessage(int etId, String message) {
		String str = "����Ϊ��";
		if (TextUtils.isEmpty(((EditText) findViewById(etId)).getText()
				.toString().trim())) {
			Toast.makeText(BaseActivity.this, message + str, Toast.LENGTH_SHORT)
					.show();
			return true;
		}
		return false;
	}

	// ����������������������������������������������������������������������ɭ��ӡ�����������������������������������������������������������������
}
