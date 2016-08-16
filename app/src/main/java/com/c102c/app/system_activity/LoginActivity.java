package com.c102c.app.system_activity;

import java.util.ArrayList;
import java.util.List;

import com.c102c.app.activity.MainActivity;
import com.c102c.app.base.AppDB;
import com.c102c.app.commu.Basesence;
import com.c102c.app.commu.Fetch;
import com.c102c.app.model.User_down;
import com.c102c.app.utils.Util;
import com.c102c.finalJKYTJ.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	List<User_down> user_list;
   ListView listview;
   ProgressBar progressBar1;
   LayoutInflater flate;
   Dialog dialog;
   AppDB app;
   MyAdapter adapter;
   String from="-1";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		from=getIntent().getStringExtra("from");
		flate=LayoutInflater.from(this);
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		 app=AppDB.getInstance(this);
		 listview=(ListView)findViewById(R.id.user_list);
		listview.setVisibility(View.GONE);
		List<Object> list_user=app.get(new User_down());
		user_list=new ArrayList<User_down>();
		if(list_user.size()>0){
			for(Object user:list_user){
				user_list.add((User_down) user);
		}
			listview.setVisibility(View.VISIBLE);
		}
		adapter=new MyAdapter();
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(listener);
		Fetch.communicate("M0000010103", LoginActivity.this, handler);
		progressBar1=(ProgressBar)findViewById(R.id.progressBar1);
	}
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if(msg.what==Basesence.commusucc){
				switch (msg.arg1) {
			case Basesence.M0000010103:
				String a = (String) msg.obj;
				if(a.indexOf("xml")==-1){
					Toast.makeText(LoginActivity.this, a, 0);
					LoginActivity.this.finish();
					return;
				}
				//system.out.println(a);
				user_list = Util.getUser_down(a);
				if(user_list.size()<=0){
					Toast.makeText(LoginActivity.this, "该村没有医生", 0).show();
					return;
				}
				app.User_delete();
				for(int i=0;i<user_list.size();i++){
					app.add(user_list.get(i));
				}
				listview.setVisibility(View.VISIBLE);
				progressBar1.setVisibility(View.GONE);
				adapter.notifyDataSetChanged();
				//system.out.println("执行");
				break;
			}	
			}
			else{
				listview.setVisibility(View.VISIBLE);
				progressBar1.setVisibility(View.GONE);
				}
			super.handleMessage(msg);
		}
	};
	class MyAdapter extends BaseAdapter{
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return user_list.size();
		}
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return user_list.get(position);
		}
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
		@Override
		public View getView(int position, View view, ViewGroup parent) {
			// TODO Auto-generated method stub
			A a;
			if(view==null){
				view =flate.inflate(R.layout.user_item, null);
				a=new A();
				a.txt_name=(TextView) view.findViewById(R.id.txt_name);
				view.setTag(a);
			}
			else{
				a=(A) view.getTag();
			}
			a.txt_name.setText(user_list.get(position).getUserName());
			return view;
		}
		class A{
			private TextView txt_name;
		}
	}
	OnItemClickListener listener=new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			Toast.makeText(LoginActivity.this, user_list.get(position).getUserCode(), 0).show();
			showDialog(user_list.get(position).getUserName(),user_list.get(position));
		}
	};
	private void showDialog(String msg,final User_down user){
	AlertDialog.Builder buil=new AlertDialog.Builder(this).setMessage("确定选择"+msg+"医生？").setTitle("提示").setNegativeButton("确定", new Dialog.OnClickListener(){
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			Basesence.setUser(user);
			if(from!=null&&from.equals("1")){
					finish();
					return;
			}
			Intent intent=new Intent(LoginActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
		
		}
	}).setNeutralButton("取消", null);
	dialog=buil.create();
	dialog.show();
	}
	

}
