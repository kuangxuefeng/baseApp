package com.kuangxf.baseapp.base;

import com.kuangxf.baseapp.AppConfig;
import com.kuangxf.baseapp.utils.LogUtil;

import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class BaseActivity extends Activity {

	private String tag = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		tag = this.getPackageName() + "." + this.getLocalClassName();
		tag = "do in " + tag + AppConfig.BASE_ACTIVITY_LOG_INFO_STRING;
		LogUtil.e(tag);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		LogUtil.e(tag);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		LogUtil.e(tag);
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		LogUtil.e(tag);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		LogUtil.e(tag);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		LogUtil.e(tag);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		LogUtil.e(tag);
	}
	
	@Override
	public void finish() {
		super.finish();
		LogUtil.e(tag);
	}
}
