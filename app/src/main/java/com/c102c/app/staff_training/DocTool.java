package com.c102c.app.staff_training;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;

public class DocTool extends Activity {
	private final String mAppname = "全科医生";
	private String packageName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getInstallInfo();
		openApp();
	}

	private void openApp() {
		Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(
				packageName);
		startActivity(LaunchIntent); // 按钮事件
		finish();
	}

	private void getInstallInfo() {
		List<PackageInfo> packages = getPackageManager()
				.getInstalledPackages(0);
		for (int j = 0; j < packages.size(); j++) {
			PackageInfo packageInfo = packages.get(j);
			if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
				String APPname = packageInfo.applicationInfo.loadLabel(
						getPackageManager()).toString();
				if (mAppname.equals(APPname)) {
					packageName = packageInfo.packageName;
				}
			}
		}

	}
}
