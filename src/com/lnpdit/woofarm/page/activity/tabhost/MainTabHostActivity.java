package com.lnpdit.woofarm.page.activity.tabhost;

import java.util.ArrayList;
import java.util.List;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.application.DemoApplication;
import com.lnpdit.woofarm.page.activity.tabhost.item.FarmInformationActivity;
import com.lnpdit.woofarm.page.activity.tabhost.item.FarmLoginActivity;
import com.lnpdit.woofarm.page.activity.tabhost.item.FarmServiceActivity;
import com.lnpdit.woofarm.page.activity.tabhost.item.FarmingManagementActivity;
import com.lnpdit.woofarm.page.activity.tabhost.item.PersonalActivity;
import com.umeng.message.PushAgent;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainTabHostActivity extends TabHostAbstractActivity {

    public static List<TabHostItem> mItems;
    public static List<ImageView> myList = new ArrayList();
    private int locationCur;

    /**
     * 在初始化TabWidget前调用 和TabWidget有关的必须在这里初始化
     */
    @Override
    protected void prepare() {
        TabHostItem info = new TabHostItem(getString(R.string.tabHost_frist), // title
                R.drawable.information_h, // icon
                R.color.white, // background
                new Intent(this, FarmInformationActivity.class)); // intent

        TabHostItem service = new TabHostItem(getString(R.string.tabHost_second),
                R.drawable.service_h, R.color.white,
                new Intent(this, FarmServiceActivity.class));

        TabHostItem farm = new TabHostItem(
                getString(R.string.tabHost_thrid), R.drawable.farm,
                R.color.white,
                new Intent(this, FarmLoginActivity.class));
        TabHostItem manage = new TabHostItem(getString(R.string.tabHost_fourth),
                R.drawable.manage_h, R.color.white,
                new Intent(this, FarmingManagementActivity.class));
        TabHostItem myzone = new TabHostItem(getString(R.string.tabHost_five),
                R.drawable.personal_h, R.color.white,
                new Intent(this, PersonalActivity.class));

        mItems = new ArrayList<TabHostItem>();
        mItems.add(info);
        mItems.add(service);
        mItems.add(farm);
        mItems.add(manage);
        mItems.add(myzone);

        // 设置分割线
        // TabWidget tabWidget = getTabWidget();
        // tabWidget.setDividerDrawable(R.drawable.tabhost_divider);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DemoApplication myApplication = DemoApplication.getInstance();
        myApplication.mainTabHostActivity = this;

        PushAgent.getInstance(this).onAppStart();
        
        Intent intent = getIntent();
//        locationCur = intent.getIntExtra("locationCur", 0);
        locationCur=2;
        switch (locationCur) {
        case 1:
            restore(2);
            myList.get(2).setBackgroundResource(R.drawable.farm);
            setCurrentTab(2);
            break;
        case 0:
            setCurrentTab(2);
            break;
        default:
            setCurrentTab(2);
            break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 自定义点击三颜色切换，文字(textView)颜色变化写在XML中，背景(ll)颜色变化写在XML
     * 图片(imgTabItem)颜色变化写在代码中，position对应位置
     */
    @Override
    protected void setTabItemTextView(TextView textView,
            final ImageView imgTabItem, LinearLayout ll, final int position) {
        // 可调整图片太靠顶问题
//         textView.setPadding(3, 8, 3, 3);
        textView.setText(mItems.get(position).getTitle());
        textView.setTextSize(11);
        // textView.setBackgroundResource(mItems.get(position).getBg());
        ll.setBackgroundResource(mItems.get(position).getBg());
        // textView.setCompoundDrawablesWithIntrinsicBounds(0,
        // mItems.get(position).getIcon(), 0, 0);
        imgTabItem.setBackgroundResource(mItems.get(position).getIcon());
        myList.add(imgTabItem);
        switch (position) {
        case 0:
            ll.setOnTouchListener(new OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        imgTabItem
                                .setBackgroundResource(R.drawable.information_h);
                    } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                        imgTabItem
                                .setBackgroundResource(R.drawable.information_h);
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        for (int i = 0; i < myList.size(); i++) {
                            if (i != position) {
                                restore(i);
                            }
                        }
                        imgTabItem
                                .setBackgroundResource(R.drawable.information);
                        setCurrentTab(position);
                    }
                    return true;
                }
            });
            break;
        case 1:
            ll.setOnTouchListener(new OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        imgTabItem
                                .setBackgroundResource(R.drawable.service_h);
                    } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                        imgTabItem
                                .setBackgroundResource(R.drawable.service_h);
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        for (int i = 0; i < myList.size(); i++) {
                            if (i != position) {
                                restore(i);
                            }
                        }
                        imgTabItem
                                .setBackgroundResource(R.drawable.service);
                        setCurrentTab(1);
                    }
                    return true;
                }
            });
            break;
        case 2:
            ll.setOnTouchListener(new OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        imgTabItem
                                .setBackgroundResource(R.drawable.farm_h);
                    } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                        imgTabItem
                                .setBackgroundResource(R.drawable.farm_h);
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        for (int i = 0; i < myList.size(); i++) {
                            if (i != position) {
                                restore(i);
                            }
                        }
                        imgTabItem
                                .setBackgroundResource(R.drawable.farm);
                        setCurrentTab(position);
                    }
                    return true;
                }
            });
            break;
        case 3:
            ll.setOnTouchListener(new OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        imgTabItem
                                .setBackgroundResource(R.drawable.manage_h);
                    } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                        imgTabItem
                                .setBackgroundResource(R.drawable.manage_h);
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        for (int i = 0; i < myList.size(); i++) {
                            if (i != position) {
                                restore(i);
                            }
                        }
                        imgTabItem
                                .setBackgroundResource(R.drawable.manage);
                        setCurrentTab(position);
                    }
                    return true;
                }
            });
            break;
        case 4:
            ll.setOnTouchListener(new OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        imgTabItem
                                .setBackgroundResource(R.drawable.personal_h);
                    } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                        imgTabItem
                                .setBackgroundResource(R.drawable.personal_h);
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        for (int i = 0; i < myList.size(); i++) {
                            if (i != position) {
                                restore(i);
                            }
                        }
                        imgTabItem
                                .setBackgroundResource(R.drawable.personal);
                        setCurrentTab(position);
                    }
                    return true;
                }
            });
            break;
        default:
            break;
        }
    }

    /**
     * 重置颜色
     * 
     * @param position
     */
    public static void restore(int position) {
        switch (position) {
        case 0:
            myList.get(position).setBackgroundResource(R.drawable.information_h);
            break;
        case 1:
            myList.get(position).setBackgroundResource(R.drawable.service_h);
            break;
        case 2:
            myList.get(position).setBackgroundResource(R.drawable.farm_h);
            break;
        case 3:
            myList.get(position).setBackgroundResource(R.drawable.manage_h);
            break;
        case 4:
            myList.get(position).setBackgroundResource(R.drawable.personal_h);
            break;
        default:
            break;
        }
    }

    /** tab唯一的id */
    @Override
    protected String getTabItemId(int position) {
        return mItems.get(position).getTitle();
    }

    /** 点击tab时触发的事件 */
    @Override
    protected Intent getTabItemIntent(int position) {
        return mItems.get(position).getIntent();
    }

    @Override
    protected int getTabItemCount() {
        return mItems.size();
    }

}
