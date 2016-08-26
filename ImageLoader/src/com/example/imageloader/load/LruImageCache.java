package com.example.imageloader.load;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class LruImageCache implements MemoryCache {

	LruCache<String, Bitmap> lruCache;
	
	public LruImageCache() {
		init();
	}
	
	private void init() {
		final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
		final int cacheSize = maxMemory / 4;
		lruCache = new LruCache<String, Bitmap>(cacheSize) {
			@Override
			protected int sizeOf(String key, Bitmap value) {
				// TODO Auto-generated method stub
				return value.getRowBytes() * value.getHeight() / 1024;
			}
		};
	}

	@Override
	public Bitmap get(String url) {
		Bitmap bitmap = lruCache.get(url);
		return bitmap;
	}

	@Override
	public void set(String url, Bitmap bitmap) {
		lruCache.put(url, bitmap);
	}
}
