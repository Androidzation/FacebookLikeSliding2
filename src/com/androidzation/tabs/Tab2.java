package com.androidzation.tabs;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.androidzation.facebook.R;
import com.androidzation.facebook.TabContainer;
import com.androidzation.fragments.Tab1Fragment;

public class Tab2 extends TabContainer {

	@Override
	public void launchFragment() {
		FragmentManager childFragmentManager = getChildFragmentManager();
		FragmentTransaction childTransaction = childFragmentManager
				.beginTransaction();

		childTransaction.replace(R.id.realtabcontent, new Tab1Fragment());
		childTransaction.commit();

	}

}
