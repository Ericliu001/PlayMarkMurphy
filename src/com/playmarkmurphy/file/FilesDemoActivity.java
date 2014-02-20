package com.playmarkmurphy.file;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.playmarkmurphy.R;

public class FilesDemoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_files_demo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.files_demo, menu);
		return true;
	}

}
