package com.example.imageloader.load;

import android.graphics.Bitmap;

public class DoubleCache implements MemoryCache {

	DiskCache diskCache;
	LruImageCache lruImageCache;

	public DoubleCache() {
		init();
	}

	private void init() {
		diskCache = new DiskCache();
		lruImageCache = new LruImageCache();
	}

	@Override
	public Bitmap get(String url) {
		 Bitmap bitmap = lruImageCache.get(url);
		if (bitmap != null) {
			return bitmap;
		}
		bitmap = diskCache.get(url);
		if (bitmap != null) {
			return bitmap;
		}
		return null;
	}

	@Override
	public void set(String url, Bitmap bitmap) {
		lruImageCache.set(url, bitmap);
		diskCache.set(url, bitmap);
	}

}
