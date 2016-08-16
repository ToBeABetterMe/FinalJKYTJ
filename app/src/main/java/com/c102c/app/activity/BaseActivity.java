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
	/** 初始化view */
	protected abstract void initViews();

	/** 给所有的bean找到id然后显示出来。 */
	protected void setAllText() {

	}

	/*** 从id力获取数据，设置给bean类 */
	protected Object getAllText() {
		return null;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initViews();
		setAllText();
	}

	/** 通过id来设置显示Text文本内容 */
	public void setTextByid(String content, int id) {
		if (!TextUtils.isEmpty(content)) {
			EditText et = (EditText) findViewById(id);
			et.setText(content);
		}
	}

	/** 给editText设置要选择的数组 */
	public void setChoiceEditText(int et_Id, int arrayId) {
		if (arrayId == -1) {
			((ChoiceEditText) findViewById(et_Id)).setFixItems(null);
		} else
			((ChoiceEditText) findViewById(et_Id)).setFixItems(getResources()
					.getStringArray(arrayId));
	}

	// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓曾德森添加↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	/** 设置EditText为空时的提示消息 */
	public boolean setEditTextNullMessage(int etId, String message) {
		String str = "不能为空";
		if (TextUtils.isEmpty(((EditText) findViewById(etId)).getText()
				.toString().trim())) {
			Toast.makeText(BaseActivity.this, message + str, Toast.LENGTH_SHORT)
					.show();
			return true;
		}
		return false;
	}

	// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑曾德森添加↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
}
