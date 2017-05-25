package com.kuangxf.baseapp.utils.codes;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.text.TextUtils;

import com.kuangxf.baseapp.utils.LogUtil;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

public class BCDHelper {
	/** 
	    * @Title: StrToBCD
	    * @Description: TODO(用BCD码压缩数字字符串)
	    *
	    *  @param  str
	    *  @return 
	    *  @return :byte[]
	    *
	    *  @version : v1.0.0
	    *  @author : WANJJ
	    * @date: 2011-11-17
	    * 
	    * Modification History:messageHeader 
	    * Date  Author  Version  Description
	    * ---------------------------------------------------------*
	    * 2011-11-17  wanjj v1.0.0   修改原因
	     */
		
	    //如�?9’转�?9
		private static char ConvertHexChar(char ch) {
			if ((ch >= '0') && (ch <= '9'))
				return (char)(ch - 0x30);
			else if ((ch >= 'A') && (ch <= 'F'))
				return (char)(ch - 'A' + 10);
			else if ((ch >= 'a') && (ch <= 'f'))
				return (char)(ch - 'a' + 10);
			else
				return (char)(-1);
		}
		
		//byte值转成INT�?
		public static   int     byte2int(byte val){
			return (val >= 0 ? val : (val + 256));
		}
		
	    public static   byte [] StrToBCD(String str) {
	        return  StrToBCD(str, str.length());
	    }

	    public static   byte [] StrToBCD(String str, int  numlen) {
	        if  (numlen % 2 != 0)
	            numlen++;

	        while  (str.length() < numlen) {
	            str = "0" + str;  //前导补�?00�?
	        }

	        byte [] bStr = new  byte [str.length() / 2];
	        char [] cs = str.toCharArray();
	        int  i     = 0;
	        int  iNum  = 0;
	        for  (i = 0; i < cs.length; i += 2) {
	        	//TODO: 过滤空格
	            int  iTemp = 0;
	            if  (cs[i] >= '0' && cs[i] <= '9') {
	                iTemp = (cs[i] - '0') << 4;
	            } else  {
	                //  判断是否为a~f 
	                if  (cs[i] >= 'a' && cs[i] <= 'f') {
	                    cs[i] -= 32;
	                }
	                iTemp = (cs[i] - '0' - 7) << 4;
	            }
	            //  处理低位 
	            if  (cs[i + 1] >= '0' && cs[i + 1] <= '9') {
	                iTemp += cs[i + 1] - '0';
	            } else  {
	                //  判断是否为a~f 
	                if  (cs[i + 1] >= 'a' && cs[i + 1] <= 'f') {
	                    cs[i + 1] -= 32;
	                }
	                iTemp += cs[i + 1] - '0' - 7;
	            }
	            bStr[iNum] = (byte ) iTemp;
	            iNum++;
	        }
	        return  bStr;
	    }
	    
	    public static byte[] stringToBcd(String src, int numlen)
	    {
	    	int inum=0;
	    	if ((numlen % 2)>0) return null;
	    	byte[] dst = new byte[numlen/2];
	    	
	    	for (int i=0; i<numlen; ){
	    		//TODO: 过滤空格
	    		char hghch = ConvertHexChar(src.charAt(i));
	    		char lowch = ConvertHexChar(src.charAt(i+1));
	    		
	    		dst[inum++] = (byte) (hghch * 16 + lowch);
	    		i+=2;
	    	}
	    	return dst;
	    }
	    
	    public static byte[] stringToBcd(String src)
	    {
	    	int inum=0;
	    	int numlen = src.length();
	    	if ((numlen % 2)>0) return null;
	    	byte[] dst = new byte[numlen/2];
	    	
	    	for (int i=0; i<numlen; ){
	    		//TODO: 过滤空格
	    		char hghch = ConvertHexChar(src.charAt(i));
	    		char lowch = ConvertHexChar(src.charAt(i+1));
	    		
	    		dst[inum++] = (byte) (hghch * 16 + lowch);
	    		i+=2;
	    	}
	    	return dst;
	    }
	    
	    
	    public static char[] asciiToBcd(String src)
	    {
	    	int inum=0;
	    	
	    	String str = src.trim().replaceAll(" ", "");
	    	
	    	int numlen=str.length();
	    	if ((numlen % 2)>0) return null;
	    	char[] dst = new char[numlen/2];
	    	
	    	for (int i=0; i<numlen; ){
	    		//TODO: 过滤空格
	    		char hghch = ConvertHexChar(str.charAt(i));
	    		char lowch = ConvertHexChar(str.charAt(i+1));
	    		
	    		dst[inum++] = (char) (hghch * 16 + lowch);
	    		i+=2;
	    	}
	    	return dst;
	    }
	    
	    
	    
	    
	    /**
	     * 将BCD码串转成ASCII码串，如 hex("\x21\x31\x24") 转成 "213124"
	     * @param bcdNum	是代表BCD码串
	     * @param offset	是代表从第几个BCD码字节开始转�?
	     * @param numlen  	是代表BCD码字节数
	     * @return
	     */
	    public static  String bcdToString(byte [] bcdNum, int  offset, int  numlen) {
	        int  len = numlen;
	        //if  (numlen % 2 != 0) {
	        //    len++;
	        //}

	        StringBuffer sb = new  StringBuffer();
	        for  (int  i = 0; i < len; i++) {        	
	            sb.append(Integer.toHexString((bcdNum[i + offset] & 0xF0) >> 4));
	            sb.append(Integer.toHexString( bcdNum[i + offset] & 0x0F));
	        }
	        return  sb.toString().toUpperCase();
	    }
	    
	    public static  String bcdToString(byte [] bcdNum) {
	        int  len = bcdNum.length;

	        StringBuffer sb = new  StringBuffer();
	        for  (int  i = 0; i < len; i++) {        	
	            sb.append(Integer.toHexString((bcdNum[i] & 0xF0) >> 4));
	            sb.append(Integer.toHexString( bcdNum[i] & 0x0F));
	        }
	        return  sb.toString().toUpperCase();
	    }
	    
	    /**
	     * 把数组转换为十六进制字符串格式显�?
	     * @param bts
	     * @param offset
	     * @param count
	     * @return
	     */
		//    public static   String BytesToHexString(byte [] bts, int  offset, int  count) {
		//        StringBuilder sb = new  StringBuilder(bts.length * 2);
		//        for  (int  i = 0; i < count; i++) {
		//            sb.append(Integer.toHexString(bts[i + offset]));
		//        }
		//        return  sb.toString();
		//    }
	    

		/** 用于调试
		  * 16进制数组转化成调试用字符�?大写字母)，比如[0x03][0x3f]转化�?03 3F"  
		  * @param b  
		  * @return  
		  */	  
		public static String hex2DebugHexString(byte[] b, int len) {
		     int[] x = new int[len];     
		     String[] y = new String[len];     
		     StringBuilder str = new StringBuilder();     
		     // 转换成Int数组,然后转换成String数组     
		     int j = 0;     
		     for (; j < len; j++) {         
		     	x[j] = b[j] & 0xff;         
		     	y[j] = Integer.toHexString(x[j]);         
		     	while (y[j].length() < 2) {
		     		y[j] = "0" + y[j];
		     	}         
		     	str.append(y[j]);         
		     	str.append(" ");     
		     }     
		     return new String(str).toUpperCase();
		     //toUpperCase()方法  转化成大�?
		}
		/** 用于调试
		 * 16进制数组转化成调试用字符�?大写字母)，比如[0x03][0x3f]转化�?03 3F"
		 * @param
		 * @return
		 *  wangb 2016 06 12 添加以下
		 */

		public static byte[] asciiByteArray2BcdArray(byte[] data) {
			return hexString2ByteArray(asciiByteArray2String1(data));
		}

		public static String asciiByteArray2String(byte[] data) {
			if(data == null) {
				return "";
			} else {
				StringBuffer tStringBuf = new StringBuffer();
				char[] tChars = new char[data.length];
				int end = 0;

				for(int i = 0; i < data.length; ++i) {
					if(data[i] == 32) {
						end = i;
						break;
					}

					end = data.length;
					tChars[i] = (char)data[i];
				}

				tStringBuf.append(tChars, 0, end);
				return tStringBuf.toString();
			}
		}

		public static String asciiByteArray2String1(byte[] data) {
			StringBuffer tStringBuf = new StringBuffer();
			char[] tChars = new char[data.length];
			int end = 0;

			for(int i = 0; i < data.length; ++i) {
				end = data.length;
				tChars[i] = (char)data[i];
			}

			tStringBuf.append(tChars, 0, end);
			return tStringBuf.toString();
		}

		public static byte[] string2ASCIIByteArray(String str) {
			byte[] data = null;

			try {
				data = str.getBytes("US-ASCII");
			} catch (UnsupportedEncodingException var3) {
				LogUtil.e("字符串转换为ASCII码Byte数组错误");
				var3.printStackTrace();
			}

			return data;
		}

		public static byte[] int2BCDByteArray(int dd) {
			if(dd <= 9999 && dd >= 0) {
				StringBuffer hexStr = new StringBuffer(dd + "");
				int strLen = hexStr.length();
				if(strLen != 4) {
					for(int i = 0; i < 4 - strLen; ++i) {
						hexStr.insert(0, '0');
					}
				}

				return hexString2ByteArray(hexStr.toString());
			} else {
				return new byte[]{(byte)0, (byte)0};
			}
		}

		public static byte[] hexString2ByteArray(String hexStr) {
			if(hexStr != null && !hexStr.equals("")) {
				if(hexStr.length() % 2 != 0) {
					hexStr = hexStr + "0";
				}

				byte[] data = new byte[hexStr.length() / 2];

				for(int i = 0; i < hexStr.length() / 2; ++i) {
					char hc = hexStr.charAt(2 * i);
					char lc = hexStr.charAt(2 * i + 1);
					byte hb = hexChar2Byte(hc);
					byte lb = hexChar2Byte(lc);
					if(hb < 0 || lb < 0) {
						return null;
					}

					int n = hb << 4;
					data[i] = (byte)(n + lb);
				}

				return data;
			} else {
				return null;
			}
		}

		public static byte hexString2Byte(String hexStr) {
			if(TextUtils.isEmpty(hexStr)) {
				return (byte)0;
			} else {
				char hc = hexStr.charAt(0);
				char lc = hexStr.charAt(1);
				byte hb = hexChar2Byte(hc);
				byte lb = hexChar2Byte(lc);
				if(hb >= 0 && lb >= 0) {
					int n = hb << 4;
					byte data = (byte)(n + lb);
					return data;
				} else {
					return (byte)0;
				}
			}
		}

		public static short[] StringAndShorttoShortArray(short[] args1, String args2) {
			byte[] tmps = hexString2ByteArray(args2.replaceAll(" ", ""));
			short[] result;
			int length;
			if(args1 != null) {
				length = args1.length;
				result = new short[length + tmps.length];
				System.arraycopy(args1, 0, result, 0, length);
			} else {
				length = 0;
				result = new short[tmps.length];
			}

			for(int i = 0; i < tmps.length; ++i) {
				result[length + i] = (short)tmps[i];
			}

			return result;
		}

		public static short[] Bytes2ShortArray(byte[] data) {
			short[] result = null;
			if(data != null) {
				result = new short[data.length];

				for(int i = 0; i < data.length; ++i) {
					result[i] = (short)data[i];
				}
			}

			return result;
		}

		public static byte hexChar2Byte(char c) {
			return c >= 48 && c <= 57?(byte)(c - 48):(c >= 97 && c <= 102?(byte)(c - 97 + 10):(c >= 65 && c <= 70?(byte)(c - 65 + 10):-1));
		}

		public static String byteArray2HexString(byte[] arr) {
			if(arr != null && arr.length != 0) {
				StringBuilder sbd = new StringBuilder();
				byte[] arr$ = arr;
				int len$ = arr.length;

				for(int i$ = 0; i$ < len$; ++i$) {
					byte b = arr$[i$];
					String tmp = Integer.toHexString(255 & b);
					if(tmp.length() < 2) {
						tmp = "0" + tmp;
					}

					sbd.append(tmp);
				}

				return sbd.toString();
			} else {
				return "";
			}
		}

		public static String byteArray2HexStringWithSpace(byte[] arr) {
			if(arr != null && arr.length != 0) {
				StringBuilder sbd = new StringBuilder();
				byte[] arr$ = arr;
				int len$ = arr.length;

				for(int i$ = 0; i$ < len$; ++i$) {
					byte b = arr$[i$];
					String tmp = Integer.toHexString(255 & b);
					if(tmp.length() < 2) {
						tmp = "0" + tmp;
					}

					sbd.append(tmp);
					sbd.append(" ");
				}

				return sbd.toString();
			} else {
				return "";
			}
		}

		public static String getBCDString(byte[] data, int start, int end) {
			byte[] t = new byte[end - start + 1];
			System.arraycopy(data, start, t, 0, t.length);
			return byteArray2HexString(t);
		}

		public static String getBCDString(String str) {
			byte[] tmp = hexString2ByteArray(str);
			return getBCDString(tmp, 0, tmp.length);
		}

		public static String getHexString(byte[] data, int start, int end) {
			byte[] t = new byte[end - start + 1];
			System.arraycopy(data, start, t, 0, t.length);
			return byteArray2HexStringWithSpace(t);
		}

		public static String shortArray2HexStringWithSpace(short[] arr) {
			StringBuilder sbd = new StringBuilder();
			short[] arr$ = arr;
			int len$ = arr.length;

			for(int i$ = 0; i$ < len$; ++i$) {
				short b = arr$[i$];
				String tmp = Integer.toHexString(255 & b);
				if(tmp.length() < 2) {
					tmp = "0" + tmp;
				}

				sbd.append(tmp);
			}

			return sbd.toString();
		}

		public static byte[] shortArray2bytes(short[] arr) {
			byte[] sbd = new byte[arr.length];

			for(int i = 0; i < arr.length; ++i) {
				sbd[i] = (byte)(255 & arr[i]);
			}

			return sbd;
		}

		public static byte[] shortArray2bytes2(short[] arr) {
			byte[] sbd = new byte[arr.length * 2];

			for(int i = 0; i < arr.length; ++i) {
				sbd[i * 2 + 1] = (byte)(arr[i] / 256);
				sbd[i * 2] = (byte)(255 & arr[i]);
			}

			return sbd;
		}

		public static byte[] short2ByteArrayLow(short arr) {
			byte[] sbd = new byte[]{(byte)(255 & arr), (byte)(arr / 256)};
			return sbd;
		}

		public static byte[] short2ByteArrayHigh(short arr) {
			byte[] sbd = new byte[]{(byte)(arr / 256), (byte)(255 & arr)};
			return sbd;
		}

		public static byte[] short2BcdByteArray(short arr) {
			byte len = 2;
			byte[] bcdByte = new byte[2];

			for(int i = 0; i < len; ++i) {
				byte tmp = (byte)(arr >> 8 * (len - i - 1) & 255);
				byte m = (byte)(tmp / 10);
				byte n = (byte)(tmp % 10);
				bcdByte[i] = (byte)((m << 4) + n);
			}

			return bcdByte;
		}

		public static int bcdByteArray2Int(byte arg0, byte arg1) {
			boolean tmp = false;
			byte data = 0;
			int tmp1;
			if((arg0 & 128) == 128) {
				tmp1 = arg0 + 256;
			} else {
				tmp1 = arg0;
			}

			byte m = (byte)(tmp1 / 16);
			byte n = (byte)(tmp1 % 16);
			int data1 = data + m * 1000 + n * 100;
			if((arg1 & 128) == 128) {
				tmp1 = arg1 + 256;
			} else {
				tmp1 = arg1;
			}

			m = (byte)(tmp1 / 16);
			n = (byte)(tmp1 % 16);
			data1 += m * 10 + n;
			return data1;
		}

		public static int bcdByteArray2Int(byte[] arr) {
			boolean tmp = false;
			boolean data = false;
			int tmp1;
			if((arr[0] & 128) == 128) {
				tmp1 = arr[0] + 256;
			} else {
				tmp1 = arr[0];
			}

			byte m = (byte)(tmp1 / 16);
			byte n = (byte)(tmp1 % 16);
			int data1 = m * 1000 + n * 100;
			if((arr[1] & 128) == 128) {
				tmp1 = arr[1] + 256;
			} else {
				tmp1 = arr[1];
			}

			m = (byte)(tmp1 / 16);
			n = (byte)(tmp1 % 16);
			data1 += m * 10 + n;
			return data1;
		}

		public static int byte2int(byte[] res, int offset, int length) {
			return res[1] & 255 | res[0] << 8 & '\uff00';
		}

		public static int byte2int(byte res0, byte res1) {
			return res1 & 255 | res0 << 8 & '\uff00';
		}

		public static byte[] int2ByteArray(int d) {
			byte[] data = new byte[]{(byte)(d >> 24 & 255), (byte)(d >> 16 & 255), (byte)(d >> 8 & 255), (byte)(d & 255)};
			return data;
		}

		public static String byte2BinaryString(byte bb) {
			String ZERO = "00000000";
			String s = Integer.toBinaryString(bb);
			if(s.length() > 8) {
				s = s.substring(s.length() - 8);
			} else if(s.length() < 8) {
				s = "00000000".substring(s.length()) + s;
			}

			return s;
		}

		public static byte getCrc(byte[] data, int start, int end) {
			byte crc = 0;

			for(int i = start; i <= end; ++i) {
				crc ^= data[i];
			}

			return crc;
		}
		public static String StringtoAscllString(String str_old) {
			int i=30;
			StringBuilder sb = new StringBuilder();
			for (char c : str_old.toCharArray()) { //String#toCharArray()就是将String转为char[]的方法
				sb.append(i + Integer.valueOf(String.valueOf(c)));
			}
			return sb.toString();
		}
		public static String StringToA(String content){
			String result = "";
			int max = content.length();
			for (int i=0; i<max; i++){
				char c = content.charAt(i);
				int b = (int)c / 16 * 10 + (int)c % 16;
				result = result + b ;
			}
			return result;
		}
		public static byte[] getTLVData(String tag, int length, byte[] data) {
			byte[] tmpData = new byte[1024];
			byte len = 0;
			byte[] tagData = hexString2ByteArray(tag);
			System.arraycopy(tagData, 0, tmpData, len, tagData.length);
			int var7 = len + tagData.length;
			if(length > 127 && length < 255) {
				tmpData[var7++] = -127;
			}

			tmpData[var7++] = (byte)length;
			System.arraycopy(data, 0, tmpData, var7, length);
			var7 += length;
			byte[] tlvData = new byte[var7];
			System.arraycopy(tmpData, 0, tlvData, 0, var7);
			return tlvData;
		}

		public static byte[] mergeByteArray(byte[] first, byte[] second) {
			if(null == first && null != second) {
				return second;
			} else if(null == first && null == second) {
				return new byte[0];
			} else if(null != first && null == second) {
				return first;
			} else {
				byte[] data = new byte[first.length + second.length];
				System.arraycopy(first, 0, data, 0, first.length);
				System.arraycopy(second, 0, data, first.length, second.length);
				return data;
			}
		}

		public static byte[] getSubByteArray(byte[] srcData, int srcPos, int len) {
			byte[] destData = new byte[len];

			try {
				System.arraycopy(srcData, srcPos, destData, 0, destData.length);
			} catch (Exception var5) {
				;
			}

			return destData;
		}

		public static byte[] byteAppendOne(byte[] byteArr, byte b) {
			byte[] bb = new byte[]{b};
			byte[] newArr = mergeByteArray(byteArr, bb);
			return newArr;
		}

		public static byte[] mergeByteArray(byte[][] twoDime) {
			byte[] temp = new byte[0];

			for(int i = 0; i < twoDime.length; ++i) {
				temp = mergeByteArray(temp, twoDime[i]);
			}

			return temp;
		}

		public static byte[] getTLVData(String tag, String value) {
			byte[] valueByteArr = string2ASCIIByteArray(value);
			byte[] data = null;

			try {
				data = getTLVData(tag, valueByteArr.length, valueByteArr);
			} catch (Exception var5) {
				var5.printStackTrace();
			}

			return data;
		}

		public static byte[] getTLVData(String tag, byte[] value) {
			byte[] data = null;

			try {
				data = getTLVData(tag, value.length, value);
			} catch (Exception var4) {
				var4.printStackTrace();
			}

			return data;
		}

		public static byte[] addLL2ByteArr(byte[] arr) {
			if(null != arr && 0 != arr.length) {
				byte[] arrLen = int2BCDByteArray(arr.length);
				byte[] newArr = mergeByteArray(arrLen, arr);
				return newArr;
			} else {
				return new byte[0];
			}
		}

		public static byte[] DoubleList2ByteArray(ArrayList<Double> result) {
			if(result == null) {
				return new byte[0];
			} else {
				byte[] r = new byte[result.size()];

				for(int j = 0; j < result.size(); ++j) {
					r[j] = (byte)(new Double(((Double)result.get(j)).doubleValue())).intValue();
				}

				return r;
			}
		}

		public static byte[] ByteArrayList2ByteArray(List<byte[]> list) {
			ArrayList result = new ArrayList();
			Iterator r = list.iterator();

			while(r.hasNext()) {
				byte[] j = (byte[])r.next();

				for(int i = 0; i < j.length; ++i) {
					result.add(Byte.valueOf(j[i]));
				}
			}

			byte[] var5 = new byte[result.size()];

			for(int var6 = 0; var6 < result.size(); ++var6) {
				var5[var6] = ((Byte)result.get(var6)).byteValue();
			}

			return var5;
		}

		public static String decodingTLV(String str, String tTag) {
			if(str != null && str.length() % 2 == 0) {
				String vv = "";
				int i = 0;

				while(i < str.length()) {
					try {
						String e = str.substring(i, i += 2);
						if((Integer.parseInt(e, 16) & 31) == 31) {
							e = e + str.substring(i, i += 2);
						}

						String len = str.substring(i, i += 2);
						int length = Integer.parseInt(len, 16);
						if(length > 128) {
							int value = length - 128;
							len = str.substring(i, i += value * 2);
							length = Integer.parseInt(len, 16);
						}

						length *= 2;
						String value1 = str.substring(i, i += length);
						System.out.println("tag:" + e + " len:" + len + " value:" + value1);
						if(tTag.equalsIgnoreCase(e)) {
							vv = value1;
							break;
						}
					} catch (NumberFormatException var8) {
						throw new RuntimeException("Error parsing number", var8);
					} catch (IndexOutOfBoundsException var9) {
						throw new RuntimeException("Error processing field", var9);
					}
				}

				return vv;
			} else {
				throw new RuntimeException("Invalid tlv, null or odd length");
			}
		}

		public static byte[] byteArrayBase64Encode(byte[] bytes) {
			BASE64Encoder encode = new BASE64Encoder();
			return encode.encode(bytes).getBytes();
		}

		public static byte[] byteArrayBase64Decode(byte[] bytes) {
			 BASE64Decoder decode = new BASE64Decoder();
			try {
				return decode.decodeBuffer(new String(bytes));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "ERRO".getBytes();
		}

		public static byte[] bcd2Ascii(byte[] bytes) {
			byte[] temp = new byte[2];

			for(int i = 0; i < bytes.length; ++i) {
				if((bytes[i] & 240) >> 4 <= 9) {
					temp[i * 2] = (byte)((bytes[i] >> 4 & 15) + 48);
				} else {
					temp[i * 2] = (byte)(bytes[i] >> 4 & 70);
				}

				temp[i * 2 + 1] = (byte)((bytes[i] & 15) + 48);
			}

			System.out.println("temp:" + byteArray2HexStringWithSpace(temp));
			return temp;
		}

		public static String hexByteArray2BinaryStr(byte[] bArray) {
			String[] binaryArray = new String[]{"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
			String outStr = "";
			boolean pos = false;
			byte[] arr$ = bArray;
			int len$ = bArray.length;

			for(int i$ = 0; i$ < len$; ++i$) {
				byte b = arr$[i$];
				int var8 = (b & 240) >> 4;
				outStr = outStr + binaryArray[var8];
				var8 = b & 15;
				outStr = outStr + binaryArray[var8];
			}

			return outStr;
		}

		public static String binaryString2hexString(String bString) {
			if(bString != null && !bString.equals("") && bString.length() % 8 == 0) {
				StringBuffer tmp = new StringBuffer();
				boolean iTmp = false;

				for(int i = 0; i < bString.length(); i += 4) {
					int var5 = 0;

					for(int j = 0; j < 4; ++j) {
						var5 += Integer.parseInt(bString.substring(i + j, i + j + 1)) << 4 - j - 1;
					}

					tmp.append(Integer.toHexString(var5));
				}

				return tmp.toString();
			} else {
				return null;
			}
		}

		public static byte[] str2Bcd(String asc) {
			int len = asc.length();
			int mod = len % 2;
			if(mod != 0) {
				asc = "0" + asc;
				len = asc.length();
			}

			byte[] abt = new byte[len];
			if(len >= 2) {
				len /= 2;
			}

			byte[] bbt = new byte[len];
			abt = asc.getBytes();

			for(int p = 0; p < asc.length() / 2; ++p) {
				int j;
				if(abt[2 * p] >= 48 && abt[2 * p] <= 57) {
					j = abt[2 * p] - 48;
				} else if(abt[2 * p] >= 97 && abt[2 * p] <= 122) {
					j = abt[2 * p] - 97 + 10;
				} else {
					j = abt[2 * p] - 65 + 10;
				}

				int k;
				if(abt[2 * p + 1] >= 48 && abt[2 * p + 1] <= 57) {
					k = abt[2 * p + 1] - 48;
				} else if(abt[2 * p + 1] >= 97 && abt[2 * p + 1] <= 122) {
					k = abt[2 * p + 1] - 97 + 10;
				} else {
					k = abt[2 * p + 1] - 65 + 10;
				}

				int a = (j << 4) + k;
				byte b = (byte)a;
				bbt[p] = b;
			}

			return bbt;
		}
		
		public static String str2Bcdstr(String asc) {
			byte[] bbt = str2Bcd(asc);
			StringBuffer sb = null;
			if(null!=bbt&&bbt.length>0){
				sb = new StringBuffer();
				for(byte b : bbt){
					sb.append(b);
				}
			}
			return sb.toString();
		}

		public static byte[] intToBytes(int value) {
			byte[] src = new byte[]{(byte)(value & 255), (byte)(value >> 8 & 255), (byte)(value >> 16 & 255), (byte)(value >> 24 & 255)};
			return src;
		}

		public static byte[] intToBytes2(int value) {
			byte[] src = new byte[]{(byte)(value >> 24 & 255), (byte)(value >> 16 & 255), (byte)(value >> 8 & 255), (byte)(value & 255)};
			return src;
		}

		public static int bytesToInt(byte[] src, int offset) {
			int value = src[offset] & 255 | (src[offset + 1] & 255) << 8 | (src[offset + 2] & 255) << 16 | (src[offset + 3] & 255) << 24;
			return value;
		}

		public static int bytesToInt2(byte[] src, int offset) {
			int value = (src[offset] & 255) << 24 | (src[offset + 1] & 255) << 16 | (src[offset + 2] & 255) << 8 | src[offset + 3] & 255;
			return value;
		}

		public static boolean endOfBytesIsOneGBK(byte[] bytes) {
			if(bytes != null && bytes.length != 0) {
				boolean flag = true;
				if(bytes[bytes.length - 1] < -1) {
					int i;
					for(i = bytes.length - 1; i >= 0 && bytes[i] < 0; --i) {
						;
					}

					if(i == 0) {
						;
					}

					int cLen = bytes.length - i - 1;
					if(cLen % 2 == 0) {
						flag = false;
					}
				} else {
					flag = false;
				}

				return flag;
			} else {
				return false;
			}
		}

		public static String deUnicode(String content){//将16进制数转换为汉字
			String enUnicode=null;
			String deUnicode=null;
			for(int i=0;i<content.length();i++){
				if(enUnicode==null){
					enUnicode=String.valueOf(content.charAt(i));
				}else{
					enUnicode=enUnicode+content.charAt(i);
				}
				if(i%4==3){
					if(enUnicode!=null){
						if(deUnicode==null){
							deUnicode=String.valueOf((char)Integer.valueOf(enUnicode, 16).intValue());
						}else{
							deUnicode=deUnicode+String.valueOf((char)Integer.valueOf(enUnicode, 16).intValue());
						}
					}
					enUnicode=null;
				}

			}
			return deUnicode;
		}
		/*
	 * 将字符串编码成16进制数字,适用于所有字符（包括中文）
	 */
		public static String gbkcodehex(String str)
		{
			//根据默认编码获取字节数组
			byte[] bytes=str.getBytes();
			StringBuilder sb=new StringBuilder(bytes.length*2);
			//将字节数组中每个字节拆解成2位16进制整数
			for(int i=0;i<bytes.length;i++)
			{
				sb.append(hexString.charAt((bytes[i]&0xf0)>>4));
				sb.append(hexString.charAt((bytes[i]&0x0f)>>0));
			}
			return sb.toString();
		}
		private static String hexString="0123456789ABCDEF";
		public static String enUnicode(String content){//将汉字转换为16进制数
			String enUnicode=null;
			for(int i=0;i<content.length();i++){
				if(i==0){
					enUnicode=getHexString(Integer.toHexString(content.charAt(i)).toUpperCase());
				}else{
					enUnicode=enUnicode+getHexString(Integer.toHexString(content.charAt(i)).toUpperCase());
				}
			}
			return enUnicode;
		}
		private static String getHexString(String hexString){
			String hexStr="";
			for(int i=hexString.length();i<4;i++){
				if(i==hexString.length())
					hexStr="0";
				else
					hexStr=hexStr+"0";
			}
			return hexStr+hexString;
		}

		public static String hexgbkcode(String hexStr)
		{
			if( null==hexStr || "".equals(hexStr) || (hexStr.length())%2 != 0 )
			{
				return null;
			}

			int byteLength = hexStr.length() /2;
			byte[] bytes=new byte[ byteLength ];


			int temp=0;
			for(int i=0;i<byteLength;i++)
			{
				temp = hex2Dec(hexStr.charAt(2*i))*16+hex2Dec(hexStr.charAt(2*i+1));
				bytes[i]=(byte)( temp<128 ? temp : temp-256 ) ;
			}
			try {
				return new String(bytes , "gbk");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return hexStr;
		}

		public static char[] getChars (byte[] bytes) {
			Charset cs = Charset.forName ("UTF-8");
			ByteBuffer bb = ByteBuffer.allocate (bytes.length);
			bb.put (bytes);
			bb.flip ();
			CharBuffer cb = cs.decode (bb);

			return cb.array();
		}
		public static int hex2Dec(char ch)
		{
			if(ch == '0') return 0;
			if(ch == '1') return 1;
			if(ch == '2') return 2;
			if(ch == '3') return 3;
			if(ch == '4') return 4;
			if(ch == '5') return 5;
			if(ch == '6') return 6;
			if(ch == '7') return 7;
			if(ch == '8') return 8;
			if(ch == '9') return 9;
			if(ch == 'a') return 10;
			if(ch == 'A') return 10;
			if(ch == 'B') return 11;
			if(ch == 'b') return 11;
			if(ch == 'C') return 12;
			if(ch == 'c') return 12;
			if(ch == 'D') return 13;
			if(ch == 'd') return 13;
			if(ch == 'E') return 14;
			if(ch == 'e') return 14;
			if(ch == 'F') return 15;
			if(ch == 'f') return 15;
			else return -1;

		}

}
