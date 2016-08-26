package com.example.imageloader.load;

import java.io.File;
import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.os.Environment;

public class DiskCache implements MemoryCache {
	FileOutputStream os;
	String path;
	String mpath = "com/image";
	public DiskCache() {
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			path = Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+mpath;
		}
	}
	@Override
	public Bitmap get(String url) {
	
		return null;
	}

	@Override
	public void set(String url, Bitmap bitmap) {
		try {
			os = new FileOutputStream(new File(path));	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}



	public String getMpath() {
		return mpath;
	}



	public void setMpath(String mpath) {
		this.mpath = mpath;
	}
	
	
	
}
