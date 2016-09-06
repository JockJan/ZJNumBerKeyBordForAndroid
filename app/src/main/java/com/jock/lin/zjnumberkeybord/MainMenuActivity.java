package com.jock.lin.zjnumberkeybord;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.pc.ioc.inject.InjectBinder;
import com.android.pc.ioc.inject.InjectInit;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.inject.InjectView;
import com.android.pc.ioc.view.listener.OnClick;


/**
 * Author: Jock.Lin on 2016-08-09 14:48:09
 * Email：Jock_Jan@yeah.net
 */
@InjectLayer(R.layout.activity_menu)
public class MainMenuActivity extends Activity {

    @InjectView(binders = @InjectBinder(method = "onclick", listeners = {OnClick.class}))
    TextView demo1,demo2;

    @InjectInit
    private void init(){

    }

    private void onclick(View v){
        switch (v.getId()){
            case R.id.demo1:
                startActivity(new Intent(MainMenuActivity.this, MainActivity.class));
                break;
            case R.id.demo2:
                startActivity(new Intent(MainMenuActivity.this, ZjNumberKeybordOnListenerDemoActivity.class));
                break;
        }
    }
}
