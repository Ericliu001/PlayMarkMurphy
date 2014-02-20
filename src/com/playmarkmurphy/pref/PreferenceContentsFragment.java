package com.playmarkmurphy.pref;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.playmarkmurphy.R;

public class PreferenceContentsFragment extends Fragment {
	private TextView tvCheckbox = null;
	private TextView tvRingtone = null;
	private TextView tvCheckbox2 = null;
	private TextView tvText = null;
	private TextView tvList = null;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View result = inflater.inflate(R.layout.content, container, false);
		
		tvCheckbox = (TextView) result.findViewById(R.id.tvCheckbox);
		tvRingtone = (TextView) result.findViewById(R.id.tvRingtone);
		tvCheckbox2 = (TextView) result.findViewById(R.id.tvCheckbox2);
		tvText = (TextView) result.findViewById(R.id.tvText);
		tvList = (TextView) result.findViewById(R.id.tvList);
		
		return result;
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
		
		tvCheckbox.setText(new Boolean(prefs.getBoolean("checkbox", false)).toString());
		tvRingtone.setText(prefs.getString("ringtone", "<unset>"));
		tvCheckbox2.setText(new Boolean(prefs.getBoolean("checkbox2", false)).toString());
		tvText.setText(prefs.getString("text", "<unset>"));
		tvList.setText(prefs.getString("list", "<unset>"));
	}

}
