package com.kuangxf.baseapp.utils.codes;

public class BytesUtil {
	public BytesUtil() {
    }

    public static String bytes2HexString(byte[] data) {
        StringBuilder buffer = new StringBuilder();
        byte[] arrayOfByte = data;
        int j = data.length;

        for(int i = 0; i < j; ++i) {
            byte b = arrayOfByte[i];
            String hex = Integer.toHexString(b & 255);
            if(hex.length() == 1) {
                buffer.append('0');
            }

            buffer.append(hex);
        }

        return buffer.toString().toUpperCase();
    }

    public static byte[] hexString2Bytes(String data) {
        byte[] result = new byte[(data.length() + 1) / 2];
        if((data.length() & 1) == 1) {
            data = data + "0";
        }

        for(int i = 0; i < result.length; ++i) {
            result[i] = (byte)(hex2byte(data.charAt(i * 2 + 1)) | hex2byte(data.charAt(i * 2)) << 4);
        }

        return result;
    }

    public static byte hex2byte(char hex) {
        return hex <= 102 && hex >= 97?(byte)(hex - 97 + 10):(hex <= 70 && hex >= 65?(byte)(hex - 65 + 10):(hex <= 57 && hex >= 48?(byte)(hex - 48):0));
    }

    public static byte[] subBytes(byte[] data, int offset, int len) {
        if(offset >= 0 && data.length > offset) {
            if(len < 0 || data.length < offset + len) {
                len = data.length - offset;
            }

            byte[] ret = new byte[len];
            System.arraycopy(data, offset, ret, 0, len);
            return ret;
        } else {
            return null;
        }
    }

    public static byte[] merage(byte[][] data) {
        int len = 0;

        for(int newData = 0; newData < data.length; ++newData) {
            if(data[newData] == null) {
                throw new IllegalArgumentException("");
            }

            len += data[newData].length;
        }

        byte[] var7 = new byte[len];
        len = 0;
        byte[][] arrayOfByte = data;
        int j = data.length;

        for(int i = 0; i < j; ++i) {
            byte[] d = arrayOfByte[i];
            System.arraycopy(d, 0, var7, len, d.length);
            len += d.length;
        }

        return var7;
    }

    public static int bytesToInt(byte[] bytes) {
        if(bytes.length > 4) {
            return -1;
        } else {
            int lastIndex = bytes.length - 1;
            int result = 0;

            for(int i = 0; i < bytes.length; ++i) {
                result |= (bytes[i] & 255) << (lastIndex - i << 3);
            }

            return result;
        }
    }

    public static byte[] intToBytes(int intValue) {
        byte[] bytes = new byte[4];

        for(int i = 0; i < bytes.length; ++i) {
            bytes[i] = (byte)(intValue >> (3 - i << 3) & 255);
        }

        return bytes;
    }

    public static byte[] ascii2Bcd(String ascii) {
        if(ascii == null) {
            return null;
        } else {
            if((ascii.length() & 1) == 1) {
                ascii = "0" + ascii;
            }

            byte[] asc = ascii.getBytes();
            byte[] bcd = new byte[ascii.length() >> 1];

            for(int i = 0; i < bcd.length; ++i) {
                bcd[i] = (byte)(hex2byte((char)asc[2 * i]) << 4 | hex2byte((char)asc[2 * i + 1]));
            }

            return bcd;
        }
    }

    public static String bcd2Ascii(byte[] bcd) {
        if(bcd == null) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder(bcd.length << 1);
            byte[] var5 = bcd;
            int var4 = bcd.length;

            for(int var3 = 0; var3 < var4; ++var3) {
                byte ch = var5[var3];
                byte half = (byte)((ch & 255) >> 4);
                sb.append((char)(half + (half > 9?55:48)));
                half = (byte)(ch & 15);
                sb.append((char)(half + (half > 9?55:48)));
            }

            return sb.toString();
        }
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if(src != null && src.length > 0) {
            for(int i = 0; i < src.length; ++i) {
                int v = src[i] & 255;
                String hv = Integer.toHexString(v);
                if(hv.length() < 2) {
                    stringBuilder.append(0);
                }

                stringBuilder.append(hv);
            }

            return stringBuilder.toString();
        } else {
            return null;
        }
    }

    public static byte[] hexStringToBytes(String hexString) {
        if(hexString != null && !hexString.equals("")) {
            hexString = hexString.toUpperCase();
            int length = hexString.length() / 2;
            char[] hexChars = hexString.toCharArray();
            byte[] d = new byte[length];

            for(int i = 0; i < length; ++i) {
                int pos = i * 2;
                d[i] = (byte)(charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
            }

            return d;
        } else {
            return null;
        }
    }

    private static byte charToByte(char c) {
        return (byte)HexString.hexString.indexOf(c);
    }
}
