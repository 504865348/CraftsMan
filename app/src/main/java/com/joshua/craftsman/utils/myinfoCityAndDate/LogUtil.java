package com.joshua.craftsman.utils.myinfoCityAndDate;

import android.util.Log;

/**
 * Created by nzz on 2017/7/5.
 */

public class LogUtil {

    public static void i(String str) {
        Log.i("选择所在地标记", str);
    }

    public static void i(String tag, String str) {
        Log.i(tag, str);
    }

    public static void iFor(int forNumber, String str) {
        for (int i = 0; i < forNumber; i++) {
            Log.i("选择所在地标记", str);
        }
    }
}
