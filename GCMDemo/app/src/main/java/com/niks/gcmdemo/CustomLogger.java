package com.niks.gcmdemo;
import android.util.Log;

public class CustomLogger {
    final private static boolean enable_logs = true;

    public static void Log(String tag, String message) {
        if (enable_logs) {
            Log.d(tag, message);
        }
    }
}