package com.kevin.common.utils;

import org.apache.commons.lang.math.NumberUtils;

import java.math.BigDecimal;

/**
 * Created by kevin on 2018/8/15.
 */
public class NumberUtil {

    public static double formatDouble(double value) {
        BigDecimal b = new BigDecimal(value);
        return b.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double formatDouble(double value, int scale) {
        BigDecimal b = new BigDecimal(value);
        return b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static boolean isNumeric(final String str) {

        return NumberUtils.isDigits(str);

    }

}
