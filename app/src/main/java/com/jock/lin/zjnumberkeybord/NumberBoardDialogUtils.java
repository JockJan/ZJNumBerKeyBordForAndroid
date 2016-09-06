package com.jock.lin.zjnumberkeybord;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * 自定义键盘
 *
 * @author 
 *		Jock.Lin
 * @since
 * 		2016-7-20 下午8:28:38
 */
public class NumberBoardDialogUtils {
	
	private OnNumberBoardClickListener onNumberClickListener;
	private static NumberBoardDialogUtils numberBoardDialogUtils;
	private EditText inputEditText;
	private StringBuffer buffer = new StringBuffer();
	private View dialogView ;
	
	/**
	 * 按键监听器 （多个输入框需要输入的时候用这个比较合适）
	 *
	 * @author 
	 *		Jock.Lin
	 * @since
	 * 		2016-7-20 下午8:28:31
	 */
	public static interface OnNumberBoardClickListener {
		void onClick(String NumberStr);
	}

	/**
	 * 设置按键监听器
	 *
	 * @author
	 * 		Jock.Lin
	 * @since
	 * 		2016-7-20 下午8:28:10
	 * @param onNumberBoardClickListener
	 */
	public void setOnNumberBoardClickListener(OnNumberBoardClickListener onNumberBoardClickListener) {
		onNumberClickListener = onNumberBoardClickListener;
	}
	
	/**
	 * 设置需要输入的EdiText，可随时动态设置
	 *
	 * @author
	 * 		Jock.Lin
	 * @since
	 * 		2016-7-20 下午8:36:10
	 * @param inputEditText
	 */
	public void setInputEditText(EditText inputEditText){
		this.inputEditText = inputEditText;
	}
	
	/**
	 * 获取键盘管理器
	 *
	 * @author
	 * 		Jock.Lin
	 * @since
	 * 		2016-7-20 下午8:27:55
	 * @param activityContext
	 * @return
	 */
	public static NumberBoardDialogUtils getInstance(Activity activityContext) {
		if (numberBoardDialogUtils == null) {
			numberBoardDialogUtils = new NumberBoardDialogUtils();
		} 
		return numberBoardDialogUtils;
	}

	/**
	 * 获取键盘视图
	 *
	 * @author
	 * 		Jock.Lin
	 * @since
	 * 		2016-7-20 下午8:27:19
	 * @param activityContext
	 * @return
	 */
	@SuppressLint("InflateParams") 
	public Dialog getShareDialog(Activity activityContext) {
		Dialog alertDialog = new Dialog(activityContext, R.style.WinDialog); 
		dialogView = LayoutInflater.from(activityContext).inflate( R.layout.dialog_number_keyboard, null);
		setBtnOnClickListener(R.id.btn_0);
		setBtnOnClickListener(R.id.btn_1);
		setBtnOnClickListener(R.id.btn_2);
		setBtnOnClickListener(R.id.btn_3);
		setBtnOnClickListener(R.id.btn_4);
		setBtnOnClickListener(R.id.btn_5);
		setBtnOnClickListener(R.id.btn_6);
		setBtnOnClickListener(R.id.btn_7);
		setBtnOnClickListener(R.id.btn_8);
		setBtnOnClickListener(R.id.btn_9);
		setBtnOnClickListener(R.id.btn_t);
		((ImageButton) dialogView.findViewById(R.id.btn_d)).setOnClickListener(onClickListener);
		alertDialog.setContentView(dialogView);
		return alertDialog;
	}
	
	/**
	 * 为数字按键和删除键添加点击事件
	 *
	 * @author
	 * 		Jock.Lin
	 * @since
	 * 		2016-7-20 下午8:23:37
	 * @param id
	 */
	private void setBtnOnClickListener(int id){
		((Button) dialogView.findViewById(id)).setOnClickListener(onClickListener);
	}
	
	
	/**
	 * 为EditText键入值
	 *
	 * @author
	 * 		Jock.Lin
	 * @since
	 * 		2016-7-20 下午8:40:18
	 * @param value
	 */
	private void setEditTextValue(String value){
		if (inputEditText != null) {
			inputEditText.append(value);
		} else {
			Log.e("NumberBoardDialog error", "未设置需要输入的EditText");
		}
	}
	
	/**
	 * 为StringBuffer键入值
	 *
	 * @author
	 * 		Jock.Lin
	 * @since
	 * 		2016-7-21 上午9:24:04
	 * @param value
	 */
	private void setStringBufferValue(String value){
		if (buffer != null) {
			buffer.append(value);
		} else {
			Log.e("keyboard buffer error ", "buffer is null");
		}
	}
	
	/**
	 * 删除按键事件处理
	 *
	 * @author
	 * 		Jock.Lin
	 * @since
	 * 		2016-7-20 下午8:48:20
	 */
	private void deleteText(){
		if (inputEditText != null) {
			if (inputEditText.length() > 0) {
					
				String inputText = inputEditText.getText().toString();
				StringBuffer buffers = new StringBuffer(inputText);
				buffers		 .delete(buffers.length() - 1, buffers.length());
				inputEditText.setText(buffers.toString());
			} else {
				inputEditText.setText("");
			}
		} else {
			Log.e("NumberBoardDialog error", "未设置需要输入的EditText");
		}
	}
	
	/**
	 * 删除按键事件处理（有onNumberClickListener回调的时候用到此方法）
	 *
	 * @author
	 * 		Jock.Lin
	 * @since
	 * 		2016-7-21 上午9:35:27
	 */
	private void deleteBufferText(){
		if (buffer != null) {
			if (buffer.length() > 0) {
				buffer .delete(buffer.length() - 1, buffer.length());
			}
		} else {
			Log.e("keyboard buffer error ", "buffer is null");
		}
	}
	
	/**
	 * 点击事件监听器，回调onNumberClickListener监听
	 */
	private OnClickListener onClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if (onNumberClickListener == null) {
				if (inputEditText != null) {
					switch (v.getId()) {
					case R.id.btn_0:
						setEditTextValue("0");
						break;
					case R.id.btn_1:

						setEditTextValue("1");
						break;
					case R.id.btn_2:

						setEditTextValue("2");
						break;
					case R.id.btn_3:

						setEditTextValue("3");
						break;
					case R.id.btn_4:

						setEditTextValue("4");
						break;
					case R.id.btn_5:

						setEditTextValue("5");
						break;
					case R.id.btn_6:

						setEditTextValue("6");
						break;
					case R.id.btn_7:

						setEditTextValue("7");
						break;
					case R.id.btn_8:

						setEditTextValue("8");
						break;
					case R.id.btn_9:

						setEditTextValue("9");
						break;
					case R.id.btn_d: //---删除
						deleteText();
					break;
					case R.id.btn_t: //---小数点

//						setEditTextValue("."); 
						break;
					}
					
				} else {
					Log.e("NumberBoardDialog error", "未设置需要输入的EditText");
				}
			} else {
				switch (v.getId()) {
				case R.id.btn_0:
					
					setStringBufferValue("0");
					break;
				case R.id.btn_1:

					setStringBufferValue("1");
					break;
				case R.id.btn_2:

					setStringBufferValue("2");
					break;
				case R.id.btn_3:

					setStringBufferValue("3");
					break;
				case R.id.btn_4:

					setStringBufferValue("4");
					break;
				case R.id.btn_5:

					setStringBufferValue("5");
					break;
				case R.id.btn_6:

					setStringBufferValue("6");
					break;
				case R.id.btn_7:

					setStringBufferValue("7");
					break;
				case R.id.btn_8:

					setStringBufferValue("8");
					break;
				case R.id.btn_9:

					setStringBufferValue("9");
					break;
				case R.id.btn_d: //---删除
					deleteBufferText();
				break;
				case R.id.btn_t: //---小数点

//					setEditTextValue("."); 
					break;
				}
				onNumberClickListener.onClick(buffer.toString());
			}
		}
	};

	/**
	 * 调整dialog窗口全屏
	 *
	 * @author
	 * 		Jock.Lin
	 * @since
	 * 		2016-7-20 下午8:29:03
	 * @param shareDialog
	 */
	public static void setDialogWindows(Dialog shareDialog) {
		Window win = shareDialog.getWindow();
		win.setGravity(Gravity.BOTTOM);
		android.view.WindowManager.LayoutParams params = win.getAttributes();
		params.width = android.view.WindowManager.LayoutParams.MATCH_PARENT;// 如果不设置,可能部分机型出现左右有空隙,也就是产生margin的感觉
		params.height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
		params.flags = android.view.WindowManager.LayoutParams.FLAG_DIM_BEHIND;// 就是这个属性导致不能获取焦点,默认的是FLAG_NOT_FOCUSABLE,故名思义不能获取输入焦点,
		params.dimAmount = 0.5f;// 设置对话框的透明程度背景(非布局的透明度)
		win.setAttributes(params);
	}
}
