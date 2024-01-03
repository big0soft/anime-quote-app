package com.big0soft.resource.helper;

import android.content.ContentResolver;
import android.os.Build;
import android.provider.Settings;

public class DeviceInfoHelper {
    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;

        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    public static String deviceSettingName(ContentResolver contentResolver) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            return Settings.Global.getString(contentResolver, Settings.Global.DEVICE_NAME);
        }
        return "";
    }

    public static String deviceUUID(ContentResolver contentResolver) {

        return Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID);
    }

    private static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }
}
