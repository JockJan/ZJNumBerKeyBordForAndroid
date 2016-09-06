package com.jock.lin.zjnumberkeybord;

import android.app.Application;

import com.android.pc.ioc.app.Ioc;


/**
 * Author: Jock.Lin on 2016-08-08 15:47:56
 * Email：Jock_Jan@yeah.net
 */
public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Ioc.getIoc().init(this);

    }
}
