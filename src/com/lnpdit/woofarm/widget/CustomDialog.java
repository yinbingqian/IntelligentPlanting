package com.lnpdit.woofarm.widget;

import java.text.SimpleDateFormat;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.http.ISoapService;
import com.lnpdit.woofarm.http.SoapService;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

public class CustomDialog extends Dialog {

	static ISoapService soapService = new SoapService();
	static String sex="";
	
	static int mYear = 2017;

	public CustomDialog(Context context) {
		super(context);
	}

	public CustomDialog(Context context, int theme) {
		super(context, theme);
	}

	public static class Builder {
		private Context context;
		private String title;
		private String message;
		private String positiveButtonText;
		private String negativeButtonText;
		private View contentView;
		private DialogInterface.OnClickListener positiveButtonClickListener;
		private DialogInterface.OnClickListener negativeButtonClickListener;

		public Builder(Context context) {
			this.context = context;
		}

		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}

		/**
		 * Set the Dialog message from resource
		 * 
		 * @param title
		 * @return
		 */
		public Builder setMessage(int message) {
			this.message = (String) context.getText(message);
			return this;
		}

		/**
		 * Set the Dialog title from resource
		 * 
		 * @param title
		 * @return
		 */
		public Builder setTitle(int title) {
			this.title = (String) context.getText(title);
			return this;
		}

		/**
		 * Set the Dialog title from String
		 * 
		 * @param title
		 * @return
		 */

		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		public Builder setContentView(View v) {
			this.contentView = v;
			return this;
		}

		/**
		 * Set the positive button resource and it's listener
		 * 
		 * @param positiveButtonText
		 * @return
		 */
		public Builder setPositiveButton(int positiveButtonText, DialogInterface.OnClickListener listener) {
			this.positiveButtonText = (String) context.getText(positiveButtonText);
			this.positiveButtonClickListener = listener;
			return this;
		}

		public Builder setPositiveButton(String positiveButtonText, DialogInterface.OnClickListener listener) {
			this.positiveButtonText = positiveButtonText;
			this.positiveButtonClickListener = listener;
			return this;
		}

		public Builder setNegativeButton(int negativeButtonText, DialogInterface.OnClickListener listener) {
			this.negativeButtonText = (String) context.getText(negativeButtonText);
			this.negativeButtonClickListener = listener;
			return this;
		}

		public Builder setNegativeButton(String negativeButtonText, DialogInterface.OnClickListener listener) {
			this.negativeButtonText = negativeButtonText;
			this.negativeButtonClickListener = listener;
			return this;
		}

		private NumberPicker mYearSpinner;

		// private NumberPicker.OnValueChangeListener mOnYearChangedListener =
		// new OnValueChangeListener() {
		// @Override
		// public void onValueChange(NumberPicker picker, int oldVal, int
		// newVal) {
		// mDate.add(Calendar.DAY_OF_MONTH, newVal - oldVal);
		// mYear = mYearSpinner.getValue();
		// Updatefarmcoop.setmYear(mYear);
		// updateDateControl();activity_usersetting_sex
		// }
		// };
		

		public CustomDialog create() {
			SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy");
			String date = sDateFormat.format(new java.util.Date());
			
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			// instantiate the dialog with the custom Theme
			final CustomDialog dialog = new CustomDialog(context, R.style.Dialog);
			final View layout = inflater.inflate(R.layout.activity_usersetting_sex, null);

			// mYearSpinner = (NumberPicker) layout.findViewById(R.id.np_year);
			// mYearSpinner.setMinValue(2016);//填充可选时间
			// mYearSpinner.setMaxValue(2020);
			// mYearSpinner.setValue(Integer.parseInt(date));
			// Updatefarmcoop.setmYear(Integer.parseInt(date));//获取选中时间
			// mYearSpinner.getChildAt(0).setFocusable(false);//取消进入时显示输入效果禁止点击手动输入时间
			// mYearSpinner.setOnValueChangedListener(mOnYearChangedListener);

			dialog.addContentView(layout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			Window dialogWindow = dialog.getWindow();
			WindowManager.LayoutParams lp = dialogWindow.getAttributes();
			// dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);
			lp.width = LayoutParams.MATCH_PARENT;
			lp.height = LayoutParams.WRAP_CONTENT;
			lp.gravity = Gravity.BOTTOM;
			dialogWindow.setAttributes(lp);

			// set the dialog title
			((TextView) layout.findViewById(R.id.title)).setText(title);
			// set the confirm button
			if (positiveButtonClickListener != null) {
				((TextView) layout.findViewById(R.id.negativeButton)).setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
					}
				});
				
			}
			if (positiveButtonText != null) {
				((TextView) layout.findViewById(R.id.positiveButton)).setText(positiveButtonText);
				if (positiveButtonClickListener != null) {
					((TextView) layout.findViewById(R.id.positiveButton))
							.setOnClickListener(new View.OnClickListener() {
								public void onClick(View v) {
									positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
								}
							});
				}
			} else {
				// if no confirm button just set the visibility to GONE
				layout.findViewById(R.id.positiveButton).setVisibility(View.GONE);
			}
			// set the cancel button
			if (negativeButtonText != null) {
				((TextView) layout.findViewById(R.id.negativeButton)).setText(negativeButtonText);
				if (negativeButtonClickListener != null) {
					((TextView) layout.findViewById(R.id.negativeButton))
							.setOnClickListener(new View.OnClickListener() {
								public void onClick(View v) {
									negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
								}
							});
				}
			} else {
				// if no confirm button just set the visibility to GONE
				layout.findViewById(R.id.negativeButton).setVisibility(View.GONE);
			}
			// set the content message
//			if (message != null) {
//				((TextView) layout.findViewById(R.id.message)).setText(message);
//			} else if (contentView != null) {
//				// if no message set
//				// add the contentView to the dialog body
//				((LinearLayout) layout.findViewById(R.id.content)).removeAllViews();
//				((LinearLayout) layout.findViewById(R.id.content)).addView(contentView,
//						new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
//			}
			dialog.setContentView(layout);
			return dialog;
		}
	}
}
