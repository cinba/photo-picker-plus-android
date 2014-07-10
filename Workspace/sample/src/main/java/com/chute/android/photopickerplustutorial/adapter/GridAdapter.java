/**
 * The MIT License (MIT)

Copyright (c) 2013 Chute

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.chute.android.photopickerplustutorial.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.getchute.android.photopickerplus.models.enums.MediaType;
import com.chute.android.photopickerplustutorial.R;
import com.chute.sdk.v2.model.AssetModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class GridAdapter extends BaseAdapter {

	private static LayoutInflater inflater;
	private ArrayList<AssetModel> collection;

	public GridAdapter(final Activity context,
			final ArrayList<AssetModel> collection) {
		if (collection == null) {
			this.collection = new ArrayList<AssetModel>();
		} else {
			this.collection = collection;
		}
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return collection.size();
	}

	@Override
	public AssetModel getItem(int position) {
		return collection.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public static class ViewHolder {

		public ImageView imageView;
		public ImageView videoIcon;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
      convertView = inflater.inflate(R.layout.gc_grid_adapter_item, null);
			holder = new ViewHolder();
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.gcImageViewThumb);
			holder.videoIcon = (ImageView) convertView
					.findViewById(R.id.gcImageViewVideo);
      convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		AssetModel asset = getItem(position);
    Picasso.with(convertView.getContext()).load(asset.getThumbnail()).into(holder.imageView);
    if (asset.getType().equalsIgnoreCase(
				MediaType.VIDEO.name().toLowerCase())) {
			holder.videoIcon.setVisibility(View.VISIBLE);
		} else {
			holder.videoIcon.setVisibility(View.GONE);
		}
		return convertView;
	}

	public void changeData(ArrayList<AssetModel> collection) {
		this.collection = collection;
		notifyDataSetChanged();
	}

}
