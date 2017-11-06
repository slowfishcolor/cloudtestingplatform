package com.mist.cloudtestingplatform.util;

import java.util.UUID;

/**
 * Created by Prophet on 2017/11/5.
 */
public class IdUtils {

    public static String generateDeviceId() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(16);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(generateDeviceId());
        }
        System.out.println(generateDeviceId());
        System.out.println(System.currentTimeMillis());
    }
}
