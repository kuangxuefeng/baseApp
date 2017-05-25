package com.kuangxf.baseapp;

import java.io.File;
import java.io.IOException;

import com.kuangxf.baseapp.utils.LogUtil;

import android.os.Environment;

public class AppConfig {
	public static final String BASE_ACTIVITY_LOG_INFO_STRING = "  run...(send by base)";
	
	
	//----------------------------数据库--------------------------
    public static final String DB_NAME = "MyDB.db";//数据库名称
    public static final int Db_Version = 1;//数据库名称
    public static final int number = 10;//db 分页
	
	public static String getSDPath() {
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory();
		}
		String dir = sdDir.toString() + "/baseAppWorks";
		File file = new File(dir);
		makeDir(file);
		dir = dir + "/";
		LogUtil.e("dir="+dir);
		return dir;
	}
	
	public static void makeDir(File dir) {
		LogUtil.e("dir="+dir);
        if (!dir.getParentFile().exists()) {
        	LogUtil.e("dir.getParentFile()="+dir.getParentFile());
            makeDir(dir.getParentFile());
        }
        dir.mkdir();
    }
}
