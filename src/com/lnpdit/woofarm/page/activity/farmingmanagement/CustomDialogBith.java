package com.lnpdit.woofarm.page.activity.farmingmanagement;

/**
 * Created by Administrator on 2018\11\24 0024.
 */

import java.lang.reflect.Field;

import com.lnpdit.IntelligentPlanting.R;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;


public class CustomDialogBith extends Dialog{

    static String sex="";

    static String mYear = "2018";
    static String mmoon = "01";
    static String mday = "01";
    static String[] city = new String[100];
    static String[] citym = new String[12];
    static String[] cityd = new String[31];

    public CustomDialogBith(Context context) {
        super(context);
    }

    public CustomDialogBith(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context;
        private int times;
        private String title;
        private String message;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;
        private newDialoglistener positiveButtonClickListener;
        private DialogInterface.OnClickListener negativeButtonClickListener;

        public Builder(Context context,int times) {
            this.context = context;
            this.times=times;
        }

        public Builder setMessage(String message) {
            this.message = message;

            return this;
        }

        /**
         * Set the Dialog message from resource
         *
         * @param
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
        public Builder setPositiveButton(int positiveButtonText, newDialoglistener listener) {
            this.positiveButtonText = (String) context.getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText, newDialoglistener listener) {
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

        public interface newDialoglistener{
            public void onClick(DialogInterface dialpg,int tyoe,String date);
        }

        private TextColorNumberPicker mYearSpinner;
        private TextColorNumberPicker mmoonSpinner;
        private TextColorNumberPicker mdaySpinner;

        private OnValueChangeListener mOnYearChangedListener = new OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//				mDate.add(Calendar.DAY_OF_MONTH, newVal - oldVal);
                mYear =city[mYearSpinner.getValue()] ;
//				Updatefarmcoop.setmYear(mYear);
//				updateDateControl();
            }
        };
        private OnValueChangeListener mOnmoonChangedListener = new OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//				mDate.add(Calendar.DAY_OF_MONTH, newVal - oldVal);
                mmoon =citym[mmoonSpinner.getValue()] ;
//				Updatefarmcoop.setmYear(mYear);
//				updateDateControl();
            }
        };
        private OnValueChangeListener mOndayChangedListener = new OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//				mDate.add(Calendar.DAY_OF_MONTH, newVal - oldVal);
                mday =cityd[mdaySpinner.getValue()];
//				Updatefarmcoop.setmYear(mYear);
//				updateDateControl();
            }
        };

        private void setNumberPickerDividerColor(NumberPicker numberPicker) {//修改滚动框的分割线颜色
            NumberPicker picker = numberPicker;
            Field[] pickerFields = NumberPicker.class.getDeclaredFields();
            for (Field pf : pickerFields) {
                if (pf.getName().equals("mSelectionDivider")) {
                    pf.setAccessible(true);
                    try {
                        //设置分割线的颜色值 透明
                        pf.set(picker, new ColorDrawable(0xffd5d5d5));
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (Resources.NotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }


        public CustomDialogBith create() {
//            SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy");
//            String date = sDateFormat.format(new java.util.Date());
            if(times==0){
                times=20181111;
            }
            String t=times+"";
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final CustomDialogBith dialog = new CustomDialogBith(context, R.style.MyDialogStyle);
            final View layout = inflater.inflate(R.layout.dialog_biths, null);
            int ty=0;
            for(int i=2016;i<2116;i++){
                city[i-2016]=i+"";
                if (t.substring(0,4).equals(i+"")){
                    ty=i-2016;
                    mYear=t.substring(0,4);
                }
            }
            mYearSpinner = (TextColorNumberPicker) layout.findViewById(R.id.np_year);

            mYearSpinner.setDisplayedValues(city);
            mYearSpinner.setMinValue(0);
            mYearSpinner.setMaxValue(city.length - 1);
            mYearSpinner.setValue(ty);
            mYearSpinner.getChildAt(0).setFocusable(false);//取消进入时显示输入效果禁止点击手动输入时间
            mYearSpinner.setOnValueChangedListener(mOnYearChangedListener);
//            SelectionDividerField.set(mYearSpinner, new ColorDrawable(0xff5d5d5d));
            setNumberPickerDividerColor(mYearSpinner);
            int cy=0;
            for(int i=0;i<12;i++){
                if(i<9) {
                    citym[i]="0"+(i+1)+"";
                }else {
                    citym[i]=(i+1)+"";
                }

                if (t.substring(4,6).equals(citym[i])){
                    cy=i;
                    mmoon=t.substring(4,6);
                }
            }
            mmoonSpinner=(TextColorNumberPicker) layout.findViewById(R.id.np_moon);
            mmoonSpinner.setDisplayedValues(citym);
            mmoonSpinner.setMinValue(0);
            mmoonSpinner.setMaxValue(citym.length - 1);
            mmoonSpinner.setValue(cy);
            mmoonSpinner.getChildAt(0).setFocusable(false);//取消进入时显示输入效果禁止点击手动输入时间
            mmoonSpinner.setOnValueChangedListener(mOnmoonChangedListener);
            setNumberPickerDividerColor(mmoonSpinner);
            int dy=0;
            for(int i=0;i<31;i++){
                if(i<9) {
                    cityd[i] = "0"+(i + 1) + "";
                }else {
                    cityd[i] = (i + 1) + "";
                }

                if (t.substring(6).equals(cityd[i])){
                    dy=i;
                    mday=t.substring(6);
                }
            }
            mdaySpinner=(TextColorNumberPicker) layout.findViewById(R.id.np_day);
            mdaySpinner.setDisplayedValues(cityd);
            mdaySpinner.setMinValue(0);
            mdaySpinner.setMaxValue(cityd.length - 1);
            mdaySpinner.setValue(dy);
            mdaySpinner.getChildAt(0).setFocusable(false);//取消进入时显示输入效果禁止点击手动输入时间
            mdaySpinner.setOnValueChangedListener(mOndayChangedListener);
            setNumberPickerDividerColor(mdaySpinner);

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

            if (positiveButtonText != null) {
                ((TextView) layout.findViewById(R.id.positiveButton)).setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
                    ((TextView) layout.findViewById(R.id.positiveButton))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
//                                    Entityclass.setBith(mYear+"-"+mmoon+"-"+mday);
                                    String ss=mYear+""+mmoon+""+mday;

                                    positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE,ss);
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
            if (message != null) {
                ((TextView) layout.findViewById(R.id.message)).setText(message);
            } else if (contentView != null) {
                // if no message set
                // add the contentView to the dialog body
//                ((LinearLayout) layout.findViewById(R.id.content)).removeAllViews();
//                ((LinearLayout) layout.findViewById(R.id.content)).addView(contentView,
//                        new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
            }
            dialog.setContentView(layout);
            return dialog;
        }
    }

}