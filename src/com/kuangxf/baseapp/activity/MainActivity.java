package com.kuangxf.baseapp.activity;

import android.content.Context;
import android.os.Bundle;

import com.kuangxf.baseapp.R;
import com.kuangxf.baseapp.base.BaseActivity;
import com.kuangxf.baseapp.excel.ExcelUtils;
import com.kuangxf.baseapp.excel.ReportExcel;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.MobclickAgent.EScenarioType;

public class MainActivity extends BaseActivity {

	private Context mContext;
    private final String mPageName = "AnalyticsHome";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mContext = this;
        MobclickAgent.setDebugMode(true);
        // SDK在统计Fragment时，需要关闭Activity自带的页面统计，
        // 然后在每个页面中重新集成页面统计的代码(包括调用了 onResume 和 onPause 的Activity)。
        MobclickAgent.openActivityDurationTrack(false);
        // MobclickAgent.setAutoLocation(true);
        // MobclickAgent.setSessionContinueMillis(1000);
        // MobclickAgent.startWithConfigure(
        // new UMAnalyticsConfig(mContext, "4f83c5d852701564c0000011", "Umeng",
        // EScenarioType.E_UM_NORMAL));
        MobclickAgent.setScenarioType(mContext, EScenarioType.E_UM_NORMAL);
		
        MobclickAgent.onEvent(mContext, "MainActivityCreate");
        
		test();
	}
	
	@Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(mPageName);
        MobclickAgent.onResume(mContext);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(mPageName);
        MobclickAgent.onPause(mContext);
    }

	private void test() {
		ExcelUtils.writeReportExcel(new ReportExcel());
	}
}
