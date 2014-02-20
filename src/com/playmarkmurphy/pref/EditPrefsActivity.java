package com.playmarkmurphy.pref;

import java.util.List;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.view.Menu;

import com.playmarkmurphy.R;

public class EditPrefsActivity extends PreferenceActivity {

	@Override
	public void onBuildHeaders(List<Header> target) {
		// TODO Auto-generated method stub
		super.onBuildHeaders(target);
		loadHeadersFromResource(R.xml.preference_headers, target);
	}

	public static class First extends PreferenceFragment {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.preferences);
		}
	}

	public static class Second extends PreferenceFragment {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.preferences2);
		}
	}
	
	

}
