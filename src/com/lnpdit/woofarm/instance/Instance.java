package com.lnpdit.woofarm.instance;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.lnpdit.IntelligentPlanting.R;

public class Instance {

	public static ImageLoader imageLoader = ImageLoader.getInstance();
	public static DisplayImageOptions user_options = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.carloong_radar_item_default)
			.showImageForEmptyUri(R.drawable.carloong_radar_item_default)
			.showImageOnFail(R.drawable.carloong_radar_item_default).cacheInMemory(true).cacheOnDisc(true).build();
	public static DisplayImageOptions new_s_options = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.default_bg).showImageForEmptyUri(R.drawable.default_bg)
			.showImageOnFail(R.drawable.default_bg).cacheInMemory(true).cacheOnDisc(true).build();
	public static DisplayImageOptions zuowu_s_options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.zuowuimg).showImageForEmptyUri(R.drawable.zuowuimg)
            .showImageOnFail(R.drawable.zuowuimg).cacheInMemory(true).cacheOnDisc(true).build();
	public static DisplayImageOptions news_s_options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.newsicon).showImageForEmptyUri(R.drawable.newsicon)
            .showImageOnFail(R.drawable.newsicon).cacheInMemory(true).cacheOnDisc(true).build();
	public static DisplayImageOptions user_s_options = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.user_none).showImageForEmptyUri(R.drawable.user_none)
			.showImageOnFail(R.drawable.user_none).cacheInMemory(true).cacheOnDisc(true).build();
	public static DisplayImageOptions advert_options = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.adt_bg).showImageForEmptyUri(R.drawable.adt_bg)
			.showImageOnFail(R.drawable.adt_bg).cacheInMemory(true).cacheOnDisc(true).build();
	
}
