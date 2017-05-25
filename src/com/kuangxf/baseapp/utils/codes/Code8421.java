package com.kuangxf.baseapp.utils.codes;

public class Code8421 {
	public static String Str8ToHexStr(String str8){
		if(null == str8 || str8.length() != 8){
			return "";
		}
		return Str8421ToStr(str8.substring(0, 4))+Str8421ToStr(str8.substring(4));
	}
	
	public static String Str8421ToHexStr(String str8){
		if(null == str8 || str8.length()%8 != 0 || str8.length() == 0){
			return "";
		}
		int i = str8.length()/8;
		String s = "";
		for(int j = 0 ; j < i ; j++){
			s = s + Str8ToHexStr(str8.substring(0+8*j,8*(j+1)));
		}
		return s;
	}
	
	public static String HexStrToStr8421(String str){
		if(null == str || str.length() < 1){
			return "";
		}
		char[] cs = str.toCharArray();
		String s = "";
		for(char c : cs){
			s = s + StrToStr8421(c);
		}
		return s;
	}
	
	public static String StrToStr8421(char c){
		int i = BCDHelper.hex2Dec(c);
		String str8421;
		str8421 = (i/8)+"";
		i=i%8;
		str8421 = str8421 + (i/4);
		i=i%4;
		str8421 = str8421 + (i/2);
		i=i%2;
		str8421 = str8421 + i;
		return str8421;
	}
	
	public static String Str8421ToStr(String str8421){
		if(null == str8421 || str8421.length() != 4){
			return "";
		}
		int sum = 0;
		str8421 = new StringBuffer(str8421).reverse().toString();
		for(int i = 0;i<str8421.length();i++){
			int j = Integer.parseInt(str8421.substring(i, i+1));
			sum = (int) (sum + j*Math.pow(2,i));
		}
		return HexString.hexString.charAt(sum)+"";
	}
}
