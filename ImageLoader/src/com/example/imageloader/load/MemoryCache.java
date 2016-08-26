package com.example.imageloader.load;

import android.graphics.Bitmap;

public interface MemoryCache {
	Bitmap get(String url);
	void set(String url, Bitmap bitmap);
}
