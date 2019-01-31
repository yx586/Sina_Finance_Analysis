package com.java.utils;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;

/**
 * Created by yangfei on 18/1/29.
 */
public class StrUtils {

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    /**
     * 根据身份证获取年龄
     *
     * @param idNum
     * @return
     */
    public static int getAgeByIdNum(String idNum) {
        int age = 0;
        GregorianCalendar calendar = new GregorianCalendar(TimeZone.getDefault());//获取系统当前时间
        int currentYear = calendar.get(Calendar.YEAR);
        if (idNum.matches("^\\d{15}$|^\\d{17}[\\dxX]$")) {
            if (idNum.length() == 18) {
                Pattern pattern = Pattern.compile("\\d{6}(\\d{4})\\d{6}(\\d{1})[\\dxX]{1}");
                Matcher matcher = pattern.matcher(idNum);
                if (matcher.matches()) {
                    age = currentYear - Integer.parseInt(matcher.group(1));
                }
            } else if (idNum.length() == 15) {
                Pattern p = Pattern.compile("\\d{6}(\\d{2})\\d{5}(\\d{1})\\d{1}");
                Matcher m = p.matcher(idNum);
                if (m.matches()) {
                    int year = Integer.parseInt(m.group(1));
                    year = 2000 + year;
                    if (year > 2020) {
                        year = year - 100;
                    }
                    age = currentYear - year;
                }
            }
        }
        return age;
    }

    /**
     * 是否是相同的手机号段
     *
     * @param mobile1
     * @param mobile2
     * @return
     */
    public static boolean isSameMobileSection(String mobile1, String mobile2) {
        if (StrUtils.isBlank(mobile1) || StrUtils.isBlank(mobile2)) {
            return false;
        }
        if (NumberTag(mobile1).equals(NumberTag(mobile2))) {
            return true;
        }
        return false;
    }

    /**
     * 获取手机号码前七位 作为号段
     *
     * @param mobile
     * @return
     */
    public static String NumberTag(String mobile) {
        if (isBlank(mobile)) {
            return mobile;
        }
        String numberTag = mobile.substring(0, 6);
        return numberTag;
    }

    public static byte[] getCompressValue(String str) throws Exception {

        InputStream in = null;
        BufferedReader brf = null;
        ByteArrayOutputStream out = null;
        GZIPOutputStream gzip = null;
        byte[] bt;
        try {
            in = IOUtils.toInputStream(str);
            brf = new BufferedReader(new InputStreamReader(in));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = brf.readLine()) != null) {
                buffer.append(line);
            }
            String value = buffer.toString();

            out = new ByteArrayOutputStream();

            gzip = new GZIPOutputStream(out);
            gzip.write(value.getBytes("utf-8"));
            gzip.close();
            bt = out.toByteArray();
        } catch (Exception e) {
            throw new Exception("CompressValue zip error: " + e.getMessage());
        } finally {

            try {
                if (gzip != null) {
                    gzip.close();
                }
            } catch (Exception e1) {
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e1) {
            }
            try {
                if (brf != null) {
                    brf.close();
                }
            } catch (Exception e1) {
            }
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e1) {
            }
        }

        return bt;

    }

    public static String unicodeToCn(String unicode) {
        /** 以 \ u 分割，因为java注释也能识别unicode，因此中间加了一个空格*/
        String[] strs = unicode.split("\\\\u");
        String returnStr = "";
        // 由于unicode字符串以 \ u 开头，因此分割出的第一个字符是""。
        for (int i = 1; i < strs.length; i++) {
            returnStr += (char) Integer.valueOf(strs[i], 16).intValue();
        }
        return returnStr;
    }

    public static String cnToUnicode(String cn) {
        char[] chars = cn.toCharArray();
        String returnStr = "";
        for (int i = 0; i < chars.length; i++) {
            returnStr += "\\u" + Integer.toString(chars[i], 16);
        }
        return returnStr;
    }

    public static String formatChineseData(String data) {

        return data.replace("年","-").replace("月","-").replace("日","");
    }
}
