package com.androidzation.facebook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public  class TabContainer extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View convertView = inflater.inflate(R.layout.fragment_tabcontainer,
				container, false);

		return convertView;
	}

	@Override
	public void onStart() {
		super.onStart();

		Fragment frag = getChildFragmentManager().findFragmentById(
				R.id.realtabcontent);

		if (frag != null) {

			FragmentManager childFragmentManager = getChildFragmentManager();
			FragmentTransaction childTransaction = childFragmentManager
					.beginTransaction();

			childTransaction.attach(frag);
			childTransaction.commit();
		} else {

			launchFragment();
		}

	}

	 public void launchFragment(){
		
	}
}
