package com.github.ad.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class CommonUtils {
    public static String md5(String val) {
        return DigestUtils.md5Hex(val).toUpperCase();
    }
}
