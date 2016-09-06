package com.jock.lin.zjnumberkeybord;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by Jock.Lin on 2016-08-08 15:50:48
 */
public class SystemUtils {

    public static int getScreenWidth(Context context){
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
       return  metrics.widthPixels;
    }
}
