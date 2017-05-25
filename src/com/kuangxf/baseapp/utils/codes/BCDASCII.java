package com.kuangxf.baseapp.utils.codes;

public class BCDASCII {

    private static final String TAG = "BCDASCII";
    public static final byte ALPHA_A_ASCII_VALUE = 65;
    public static final byte ALPHA_a_ASCII_VALUE = 97;
    public static final byte DIGITAL_0_ASCII_VALUE = 48;

    private BCDASCII() {
    }

    public static void fromBCDToASCII(byte[] bcdBuf, int bcdOffset, byte[] asciiBuf, int asciiOffset, int asciiLen, boolean rightAlignFlag) {
        int cnt;
        if((asciiLen & 1) == 1 && rightAlignFlag) {
            cnt = 1;
            ++asciiLen;
        } else {
            cnt = 0;
        }

        while(cnt < asciiLen) {
            asciiBuf[asciiOffset] = (byte)((cnt & 1) == 1?bcdBuf[bcdOffset++] & 15:bcdBuf[bcdOffset] >> 4 & 15);
            asciiBuf[asciiOffset] = (byte)(asciiBuf[asciiOffset] + (asciiBuf[asciiOffset] > 9?55:48));
            ++cnt;
            ++asciiOffset;
        }

    }

    public static byte[] fromBCDToASCII(byte[] bcdBuf, int bcdOffset, int asciiLen, boolean rightAlignFlag) {
        byte[] asciiBuf = new byte[asciiLen];
        fromBCDToASCII(bcdBuf, bcdOffset, asciiBuf, 0, asciiLen, rightAlignFlag);
        return asciiBuf;
    }

    public static String fromBCDToASCIIString(byte[] bcdBuf, int bcdOffset, int asciiLen, boolean rightAlignFlag) {
        try {
            return new String(fromBCDToASCII(bcdBuf, bcdOffset, asciiLen, rightAlignFlag), "GBK");
        } catch (Exception var5) {
            throw new RuntimeException(var5.getMessage(), var5);
        }
    }

    public static void fromASCIIToBCD(byte[] asciiBuf, int asciiOffset, int asciiLen, byte[] bcdBuf, int bcdOffset, boolean rightAlignFlag) {
        byte ch1;
        if((asciiLen & 1) == 1 && rightAlignFlag) {
            ch1 = 0;
        } else {
            ch1 = 85;
        }

        for(int cnt = 0; cnt < asciiLen; ++asciiOffset) {
            byte ch;
            if(asciiBuf[asciiOffset] >= 97) {
                ch = (byte)(asciiBuf[asciiOffset] - 97 + 10);
            } else if(asciiBuf[asciiOffset] >= 65) {
                ch = (byte)(asciiBuf[asciiOffset] - 65 + 10);
            } else if(asciiBuf[asciiOffset] >= 48) {
                ch = (byte)(asciiBuf[asciiOffset] - 48);
            } else {
                ch = 0;
            }

            if(ch1 == 85) {
                ch1 = ch;
            } else {
                bcdBuf[bcdOffset] = (byte)(ch1 << 4 | ch);
                ++bcdOffset;
                ch1 = 85;
            }

            ++cnt;
        }

        if(ch1 != 85) {
            bcdBuf[bcdOffset] = (byte)(ch1 << 4);
        }

    }

    public static void fromASCIIToBCD(String asciiStr, int asciiOffset, int asciiLen, byte[] bcdBuf, int bcdOffset, boolean rightAlignFlag) {
        try {
            byte[] e = asciiStr.getBytes("GBK");
            fromASCIIToBCD(e, asciiOffset, asciiLen, bcdBuf, bcdOffset, rightAlignFlag);
        } catch (Exception var7) {
            throw new RuntimeException(var7.getMessage(), var7);
        }
    }

    public static byte[] fromASCIIToBCD(byte[] asciiBuf, int asciiOffset, int asciiLen, boolean rightAlignFlag) {
        byte[] bcdBuf = new byte[(asciiLen + 1) / 2];
        fromASCIIToBCD((byte[])asciiBuf, asciiOffset, asciiLen, bcdBuf, 0, rightAlignFlag);
        return bcdBuf;
    }

    public static byte[] fromASCIIToBCD(String asciiStr, int asciiOffset, int asciiLen, boolean rightAlignFlag) {
        try {
            byte[] e = asciiStr.getBytes("GBK");
            return fromASCIIToBCD(e, asciiOffset, asciiLen, rightAlignFlag);
        } catch (Exception var5) {
            throw new RuntimeException(var5.getMessage(), var5);
        }
    }

    static int hexCharToInt(char c) {
        if(c >= 48 && c <= 57) {
            return c - 48;
        } else if(c >= 65 && c <= 70) {
            return c - 65 + 10;
        } else if(c >= 97 && c <= 102) {
            return c - 97 + 10;
        } else {
            throw new RuntimeException("invalid hex char \'" + c + "\'");
        }
    }

    public static byte[] hexStringToBytes(String s) {
        if(s == null) {
            return null;
        } else {
            int sz = s.length();

            int i;
            for(i = 0; i < sz; ++i) {
                char c = s.charAt(i);
                if((c < 48 || c > 57) && (c < 65 || c > 70) && (c < 97 || c > 102)) {
                    s = s.replaceAll("[^[0-9][A-F][a-f]]", "");
                    sz = s.length();
                    break;
                }
            }

            byte[] ret = new byte[sz / 2];

            for(i = 0; i < sz; i += 2) {
                ret[i / 2] = (byte)(hexCharToInt(s.charAt(i)) << 4 | hexCharToInt(s.charAt(i + 1)));
            }

            return ret;
        }
    }

    public static String bytesToHexString(byte[] bytes) {
        if(bytes == null) {
            return null;
        } else {
            StringBuilder ret = new StringBuilder(2 * bytes.length);

            for(int i = 0; i < bytes.length; ++i) {
                int b = 15 & bytes[i] >> 4;
                ret.append("0123456789abcdef".charAt(b));
                b = 15 & bytes[i];
                ret.append("0123456789abcdef".charAt(b));
            }

            return ret.toString().toUpperCase();
        }
    }

    public static String bytesToHexString(byte[] bytes, int len) {
        if(bytes == null) {
            return null;
        } else {
            StringBuilder ret = new StringBuilder(len * 2);

            for(int i = 0; i < len; ++i) {
                int b = 15 & bytes[i] >> 4;
                ret.append("0123456789abcdef".charAt(b));
                b = 15 & bytes[i];
                ret.append("0123456789abcdef".charAt(b));
            }

            return ret.toString().toUpperCase();
        }
    }
}
