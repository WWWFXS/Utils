package com.example.imageloader.load;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;

/**
 * 图片下载
 * 
 * @author fxs
 * 
 */
public class ImageLoader {

	ExecutorService executors = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	LruCache<String, Bitmap> lruCache = null;

	private static ImageLoader instance;

	private ImageLoader() {
		initLoader();
	}

	public static ImageLoader getInstance() {
		if (instance == null) {
			synchronized (ImageLoader.class) {
				if (instance == null) {
					instance = new ImageLoader();
				}
			}
		}
		return instance;
	}

	private void initLoader() {
		final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
		final int cacheSize = maxMemory / 4;
		lruCache = new LruCache<String, Bitmap>(cacheSize) {
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
			}

			@Override
			protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
				super.entryRemoved(evicted, key, oldValue, newValue);
				Log.v("tag", "hard cache is full , push to soft cache");
			}
		};
	}

	public void displayImage(final String url, final ImageView imageView) {
		imageView.setTag(url);
		executors.submit(new Runnable() {
			@Override
			public void run() {
				Bitmap bitmap = lruCache.get(url);
				if (bitmap != null) {
					imageView.setImageBitmap(bitmap);
				} else {
					bitmap = downloadImage(url);
					imageView.setImageBitmap(bitmap);
					lruCache.put(url, bitmap);
				}
			}
		});

	}

	public Bitmap downloadImage(String imageUrl) {
		Bitmap bitmap = null;
		try {
			URL url = new URL(imageUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			bitmap = BitmapFactory.decodeStream(conn.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bitmap;
	}

}
