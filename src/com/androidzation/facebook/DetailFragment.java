package com.androidzation.facebook;

import com.androidzation.tabs.Tab1;
import com.androidzation.tabs.Tab2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class DetailFragment extends Fragment {

	private FragmentTabHost mTabHost;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View convertView = inflater.inflate(R.layout.fragment_details,
				container, false);

		mTabHost = (FragmentTabHost) convertView
				.findViewById(android.R.id.tabhost);
		mTabHost.setup(getActivity(), getChildFragmentManager(),
				R.id.realtabcontent);
		addTab("Tab1", R.drawable.ic_launcher, Tab1.class);
		addTab("Tab2", R.drawable.ic_launcher, Tab2.class);

		return convertView;
	}

	private void addTab(String labelId, int drawableId, Class<?> c) {
		TabHost tabHost = mTabHost;
		TabHost.TabSpec spec = tabHost.newTabSpec("tab" + labelId);

		View tabIndicator = LayoutInflater.from(getActivity()).inflate(
				R.layout.tab_indicator, tabHost.getTabWidget(), false);
		TextView title = (TextView) tabIndicator.findViewById(R.id.title);
		title.setText(labelId);
		ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
		icon.setImageResource(drawableId);

		spec.setIndicator(tabIndicator);
		mTabHost.addTab(spec, c, null);

	}

}
