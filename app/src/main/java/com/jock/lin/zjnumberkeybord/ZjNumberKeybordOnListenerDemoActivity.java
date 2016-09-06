package com.jock.lin.zjnumberkeybord;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.android.pc.ioc.inject.InjectInit;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.inject.InjectView;

/**
 * Author: Jock.Lin on 2016-08-09 14:47:56
 * Email：Jock_Jan@yeah.net
 */
@InjectLayer(R.layout.activity_onlistener_keybord)
public class ZjNumberKeybordOnListenerDemoActivity extends Activity  implements View.OnClickListener{

    @InjectView
    TextView title;

    @InjectView
    EditText editText;


    Dialog keybord;
    NumberBoardDialogUtils numberBoardDialogUtils;

    @InjectInit
    private void init(){
        title.setText("键盘监听回调");
        numberBoardDialogUtils = NumberBoardDialogUtils.getInstance(this);
        numberBoardDialogUtils.setInputEditText(editText);
        keybord = numberBoardDialogUtils.getShareDialog(this);
        numberBoardDialogUtils.setOnNumberBoardClickListener(onNumberBoardClickListener);
        editText.setOnClickListener(this);
        keybord.show();
        numberBoardDialogUtils.setDialogWindows(keybord);
    }

    /**
     * 监听输入回调
     */
    private NumberBoardDialogUtils.OnNumberBoardClickListener onNumberBoardClickListener = new NumberBoardDialogUtils.OnNumberBoardClickListener() {
        @Override
        public void onClick(String NumberStr) {
            editText.setText(NumberStr);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.editText:
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(),0);
                keybord.show();
                numberBoardDialogUtils.setDialogWindows(keybord);
                break;
        }
    }
}
