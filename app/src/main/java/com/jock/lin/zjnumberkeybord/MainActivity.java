package com.jock.lin.zjnumberkeybord;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.pc.ioc.inject.InjectInit;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.inject.InjectView;

/**
 * Author: Jock.Lin on 2016-08-08 16:24:36
 * Email：Jock_Jan@yeah.net
 */
@InjectLayer(R.layout.activity_main)
public class MainActivity extends Activity  implements View.OnClickListener{

    @InjectView
    EditText hideEdit;

    @InjectView
    EditText word1;

    @InjectView
    EditText word2;

    @InjectView
    EditText word3;

    @InjectView
    EditText word4;

    @InjectView
    LinearLayout bodyLayout;

    @InjectView
    TextView result;

    Dialog keybord;
    NumberBoardDialogUtils numberBoardDialogUtils;
    private int screenWidth  = 0;

    @InjectInit
    private void init(){
        numberBoardDialogUtils = NumberBoardDialogUtils.getInstance(this);
        numberBoardDialogUtils.setInputEditText(hideEdit);
        keybord = numberBoardDialogUtils.getShareDialog(this);
        hideEdit.setOnClickListener(this);
        hideEdit.addTextChangedListener(watcher);

        screenWidth  = SystemUtils.getScreenWidth(this);
        int editWidth = (screenWidth - 50) / 4;
        LinearLayout.LayoutParams wordLp = new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT, (editWidth - 40));
        wordLp.rightMargin = 10;
        wordLp.weight = 1;
        word1.setLayoutParams(wordLp);
        word2.setLayoutParams(wordLp);
        word3.setLayoutParams(wordLp);
        word4.setLayoutParams(wordLp);
        keybord.show();
        numberBoardDialogUtils.setDialogWindows(keybord);
    }


    /**
     * 监听输入
     */
    TextWatcher watcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 4) {
                if (s.toString().equals("2233")) {
                    hideEdit.setText("");
                    result.setText("欢迎使用ZJNumberKeyBoard");
                    keybord.cancel();
                    bodyLayout.setVisibility(View.GONE);
                } else {
                    Toast.makeText(MainActivity.this,"密码： " + s.toString() + "错误！ 密码是：2233",Toast.LENGTH_SHORT).show();
                    initEditTextValue();
                    hideEdit.setText("");
                }
            } else {
                initEditTextValue();
                setEditTextValue(s.length());
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void initEditTextValue(){
        word1.setText("");
        word2.setText("");
        word3.setText("");
        word4.setText("");
    }

    private void setEditTextValue(int value){
        switch (value) {
            case 1:
                word1.setText("1");
                break;
            case 2:

                word1.setText("1");
                word2.setText("1");
                break;
            case 3:

                word1.setText("1");
                word2.setText("1");
                word3.setText("1");
                break;
            case 4:

                word1.setText("1");
                word2.setText("1");
                word3.setText("1");
                word4.setText("1");
                break;

        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.hideEdit:
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(hideEdit.getWindowToken(),0);
                keybord.show();
                numberBoardDialogUtils.setDialogWindows(keybord);
                break;
        }
    }
}
