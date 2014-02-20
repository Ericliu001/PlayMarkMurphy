package com.playmarkmurphy.fragment;

import com.playmarkmurphy.R;
import com.playmarkmurphy.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class RotationFragmentActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (getFragmentManager().findFragmentById(android.R.id.content) == null ) {
			getFragmentManager().beginTransaction().add(android.R.id.content, new RotationFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rotation, menu);
		return true;
	}

}
