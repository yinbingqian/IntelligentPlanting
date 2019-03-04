/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lnpdit.woofarm.page.activity.farmservice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.lnpdit.IntelligentPlanting.R;
import com.lnpdit.woofarm.base.component.BaseActivity;
import com.lnpdit.woofarm.domain.User;
import com.lnpdit.woofarm.page.adapter.ContactAdapter;
import com.lnpdit.woofarm.widget.Sidebar;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * 联系人列表页
 * 
 */
public class ActivityFarmserviceSidebarlist extends BaseActivity {
	private ContactAdapter adapter;
	private List<User> contactList;
	private ListView listView;
	private boolean hidden;
	private Sidebar sidebar;
    Context context;
	private InputMethodManager inputMethodManager;

	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        context = this;
	        setContentView(R.layout.activity_farmerservice_sidebarlist);

	        initView();
//	        initData();
//	        setListeners();
	    }

	    private void initView() {
	    
	        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
	        listView = (ListView) findViewById(R.id.list);
	        sidebar = (Sidebar) findViewById(R.id.sidebar);
	        sidebar.setListView(listView);
	        contactList = new ArrayList<User>();
	        // 获取设置contactlist
	        getContactList();
	        // 设置adapter
	        adapter = new ContactAdapter(context, R.layout.row_contact, contactList, sidebar);
	        listView.setAdapter(adapter);
	        listView.setOnItemClickListener(new OnItemClickListener() {

	            @Override
	            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	                String username = adapter.getItem(position).getUsername();

	            }
	        });
	        listView.setOnTouchListener(new OnTouchListener() {

	            @Override
	            public boolean onTouch(View v, MotionEvent event) {
	                // 隐藏软键盘
	                if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
	                    if (getCurrentFocus() != null)
	                        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
	                                InputMethodManager.HIDE_NOT_ALWAYS);
	                }
	                return false;
	            }
	        });

	        registerForContextMenu(listView);

	    }


	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		// 长按前两个不弹menu
		if (((AdapterContextMenuInfo) menuInfo).position > 2) {
			getMenuInflater().inflate(R.menu.context_contact_list, menu);
		}
	}


	@Override
	public void onResume() {
		super.onResume();
		if (!hidden) {
			refresh();
		}
	}

	// 刷新ui
	public void refresh() {
		try {
			// 可能会在子线程中调到这方法
			runOnUiThread(new Runnable() {
				public void run() {
					getContactList();
					adapter.notifyDataSetChanged();

				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getContactList() {
		contactList.clear();

		// 排序
		Collections.sort(contactList, new Comparator<User>() {

			@Override
			public int compare(User lhs, User rhs) {
				return lhs.getUsername().compareTo(rhs.getUsername());
			}
		});
	}
}
