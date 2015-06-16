package com.tanghai.appframe.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;

/**
 * @author Gary
 *
 */
public class DigiImageLoader {
	private static DigiImageLoader instance;
	private static ImageLoader imageLoader;
	
	public static DigiImageLoader getInstance() {
		if (instance == null) {
			synchronized (ImageLoader.class) {
				if (instance == null) {
					instance = new DigiImageLoader();
					imageLoader = ImageLoader.getInstance();
				}
			}
		}
		return instance;
	}

	protected DigiImageLoader() {}
	
	public void loadImage(String uri, ImageView imageView, DisplayImageOptions options, Context context) {
		ImageViewAware imageAware = new ImageViewAware(imageView);
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		
		int height = imageAware.getHeight();
		if(height<=0) {
			height = displayMetrics.heightPixels;
		}
		int width = imageAware.getWidth();
		if(width<=0) {
			width = displayMetrics.widthPixels;
		}
		
		StringBuffer url = new StringBuffer();
		url.append(uri);
		url.append("@");
		url.append(""+height+ "h_1l");
//		url.append("" + width + "w_");
//		url.append("1e_1c");
		
		imageLoader.displayImage(url.toString(), imageView, options);
	}
	
}
