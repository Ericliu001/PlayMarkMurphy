package com.playmarkmurphy.async;

import com.playmarkmurphy.R;
import com.playmarkmurphy.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AsyncActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (getFragmentManager().findFragmentById(android.R.id.content) == null ) {
			getFragmentManager().beginTransaction().add(android.R.id.content, new AsyncFragment()).commit();
		}
	}


}
