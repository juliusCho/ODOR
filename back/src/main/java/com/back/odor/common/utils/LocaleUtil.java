package com.back.odor.common.utils;

import com.back.odor.common.etc.GlobalConst;

public class LocaleUtil {

    private static String currentLocale;

    public static void setLocale(String locale) {
        if (GlobalConst.LOCALE_LIST[0].equals(locale)) {
            LocaleUtil.currentLocale = locale;
        } else {
            LocaleUtil.currentLocale = GlobalConst.LOCALE_LIST[1];
        }
    }

    public static String getLocale() {
        return LocaleUtil.currentLocale;
    }

}
