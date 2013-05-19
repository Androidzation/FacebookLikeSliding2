package com.androidzation.facebook;

import java.util.Date;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.androidzation.slideview.MyHorizontalScrollView;
import com.androidzation.slideview.MyHorizontalScrollView.SizeCallback;

public class MainActivity extends FragmentActivity {

	private LinearLayout detailsFragment;
	private FrameLayout menuFragment;
	private FrameLayout detailsFragmentContainer;
	private Button btnSlide;
	private Button backBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		MyHorizontalScrollView scrollView = (MyHorizontalScrollView) findViewById(R.id.myScrollViewhome);

		LayoutInflater inflater = LayoutInflater.from(this);

		menuFragment = (FrameLayout) inflater.inflate(
				R.layout.framelayout_menu, null);

		detailsFragment = (LinearLayout) inflater.inflate(
				R.layout.framelayout_detail, null);

		detailsFragmentContainer = (FrameLayout) detailsFragment
				.findViewById(R.id.fragment_details);

		btnSlide = (Button) detailsFragment.findViewById(R.id.btnSlide);
		backBtn = (Button) detailsFragment.findViewById(R.id.btnBack);
		backBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				onBackPressed();
			}
		});

		final View[] children = new View[] { menuFragment, detailsFragment };

		int scrollToViewIdx = 1;

		scrollView.initViews(children, scrollToViewIdx,
				new SizeCallbackForMenu(btnSlide));

		btnSlide.setOnClickListener(new ClickListenerForScrolling(scrollView,
				menuFragment));
		FragmentManager childFragmentManager = getSupportFragmentManager();
		FragmentTransaction childTransaction = childFragmentManager
				.beginTransaction();

		childTransaction.add(R.id.fragment_menu, new MenuFragment());
		childTransaction.commit();

		FragmentManager childFragmentManager1 = getSupportFragmentManager();
		FragmentTransaction childTransaction1 = childFragmentManager1
				.beginTransaction();

		childTransaction1.add(R.id.fragment_details, new DetailFragment());
		childTransaction1.commit();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	
	// /

	static class ClickListenerForScrolling implements OnClickListener {
		HorizontalScrollView scrollView;
		View menu;
		/**
		 * Menu must NOT be out/shown to start with.
		 */
		boolean menuOut = false;

		public ClickListenerForScrolling(HorizontalScrollView scrollView,
				View menu) {

			super();
			this.scrollView = scrollView;
			this.menu = menu;
		}

		@Override
		public void onClick(View v) {

			// Context context = menu.getContext();
			String msg = "Slide " + new Date();
			System.out.println(msg);
			int menuWidth = menu.getMeasuredWidth();
			menu.setVisibility(View.VISIBLE);

			if (!menuOut) {
				// Scroll to 0 to reveal menu
				int left = 0;
				scrollView.smoothScrollTo(left, 0);
			} else {
				// Scroll to menuWidth so menu isn't on screen.
				int left = menuWidth;
				scrollView.smoothScrollTo(left, 0);
			}
			menuOut = !menuOut;
		}
	}

	/**
	 * Helper that remembers the width of the 'slide' button, so that the
	 * 'slide' button remains in view, even when the menu is showing.
	 */

	static class SizeCallbackForMenu implements SizeCallback {
		int btnWidth;
		View btnSlide;

		public SizeCallbackForMenu(View btnSlide) {
			super();
			this.btnSlide = btnSlide;
		}

		@Override
		public void onGlobalLayout() {
			btnWidth = btnSlide.getMeasuredWidth();
		}

		@Override
		public void getViewSize(int idx, int w, int h, int[] dims) {
			dims[0] = w;
			dims[1] = h;
			final int menuIdx = 0;
			if (idx == menuIdx) {
				dims[0] = w - btnWidth - 10;
			}
		}
	}

	@Override
	public void onBackPressed() {

		if (getSupportFragmentManager().findFragmentById(R.id.fragment_details)
				.getChildFragmentManager()
				.findFragmentById(R.id.realtabcontent)
				.getChildFragmentManager().getBackStackEntryCount() > 0) {

			(getSupportFragmentManager()
					.findFragmentById(R.id.fragment_details))
					.getChildFragmentManager()
					.findFragmentById(R.id.realtabcontent)
					.getChildFragmentManager().popBackStack();
		} else {

			super.onBackPressed();
		}
	}

	public Button getBtnSlide() {
		return btnSlide;
	}

	public Button getBackButton() {
		return backBtn;
	}

}
