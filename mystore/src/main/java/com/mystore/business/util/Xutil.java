package com.mystore.business.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Xutil {
    //去小数点，每隔三位加一个逗号
    public static String format4index(BigDecimal num) {
        if (num == null) {
            return "0.00";
        }
        String str = "0.00";
        java.text.DecimalFormat dFormat = new DecimalFormat("#,##0.##");
        str = dFormat.format(num);
        return str;
    }
    //去小数点，每隔三位加一个逗号
    public static String format4indexString(String num) {
        if (num == null) {
            return "0.00";
        }
        String str = "0.00";
        java.text.DecimalFormat dFormat = new DecimalFormat("#,##0.##");
        str = dFormat.format(new BigDecimal(num));
        return str;
    }
}
