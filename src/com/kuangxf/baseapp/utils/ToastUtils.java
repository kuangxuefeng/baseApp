package com.kuangxf.baseapp.utils;

import com.kuangxf.baseapp.MyApplication;
import com.kuangxf.baseapp.R;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ToastUtils {
	private static long lastToastTime;
	private static String lastToast = "";
	
	public static void showToast(String message){
		showToast(MyApplication.getInstance(), message, Toast.LENGTH_LONG, 0, Gravity.BOTTOM);
	}

	public static void showToast(Context context, String message, int duration,
			int icon, int gravity) {
		LogUtil.d("showToast(String message, int duration, int icon, int gravity) message="
				+ message);
		if (message != null && !message.equalsIgnoreCase("")) {
			long time = System.currentTimeMillis();
			// || Math.abs(time - lastToastTime) > 2000
			if (!message.equalsIgnoreCase(lastToast)
					|| Math.abs(time - lastToastTime) > 2000) {
				View view = LayoutInflater.from(context).inflate(
						R.layout.view_toast, null);
				((TextView) view.findViewById(R.id.title_tv)).setText(message);
				if (icon != 0) {
					((ImageView) view.findViewById(R.id.icon_iv))
							.setImageResource(icon);
					((ImageView) view.findViewById(R.id.icon_iv))
							.setVisibility(View.VISIBLE);
				}
				Toast toast = new Toast(context);
				toast.setView(view);
				if (gravity == Gravity.CENTER) {
					toast.setGravity(gravity, 0, 0);
				} else {
					toast.setGravity(gravity, 0, 35);
				}

				toast.setDuration(duration);
				toast.show();
				lastToast = message;
				lastToastTime = System.currentTimeMillis();
			}
		}
	}
}
