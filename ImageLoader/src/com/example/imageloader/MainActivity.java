package com.example.imageloader;

import java.util.ArrayList;
import java.util.List;

import com.example.imageloader.load.ImageLoader;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private GridView gridView;
	List<String> list = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		for (int i = 0; i < 9; i++) {
			list.add("http://jbcdn2.b0.upaiyun.com/2015/10/9bd8b55be8303b3fef40816cdd46d0d1.gif");
		}

		gridView = (GridView) findViewById(R.id.gridView);

		gridView.setAdapter(new mAdapter());

	}

	class mAdapter extends BaseAdapter {

		@Override
		public int getCount() {

			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.image, null);
				holder.image = (ImageView) convertView.findViewById(R.id.image);
				convertView.setTag(holder);
			}
			holder = (ViewHolder) convertView.getTag();

			ImageLoader.getInstance().displayImage(list.get(position), holder.image);

			return convertView;
		}

		class ViewHolder {
			ImageView image;
		}

	}

}
