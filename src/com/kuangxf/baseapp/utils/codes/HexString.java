package com.kuangxf.baseapp.utils.codes;

import java.io.ByteArrayOutputStream;

public class HexString {
	public static final String hexString = "0123456789ABCDEF";

	public static String decode(String bytes){
		return decode(bytes,true);
	}
	
	public static String decode(String bytes,boolean isUpperCase) {
		if (bytes.length()%2!=0) {
			return null;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream(
				bytes.length() / 2);
		// 将每2位16进制整数组装成一个字节
		for (int i = 0; i < bytes.length(); i += 2)
			baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString
					.indexOf(bytes.charAt(i + 1))));
		if(isUpperCase){
			return new String(baos.toByteArray());
		}
		return new String(baos.toByteArray()).toLowerCase();
	}

	public static String encode(String str)
	{
		str = str.toUpperCase();

		// 根据默认编码获取字节数组

		byte[] bytes = str.getBytes();

		StringBuilder sb = new StringBuilder(bytes.length * 2);

		// 将字节数组中每个字节拆解成2位16进制整数

		for (int i = 0; i < bytes.length; i++)

		{

			sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));

			sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));

		}

		return sb.toString();

	}
}
