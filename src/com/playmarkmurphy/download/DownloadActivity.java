package com.playmarkmurphy.download;

import com.playmarkmurphy.R;
import com.playmarkmurphy.R.layout;
import com.playmarkmurphy.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.view.Menu;

public class DownloadActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
			getFragmentManager().beginTransaction()
					.add(android.R.id.content, new DownloadFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.download, menu);
		return true;
	}

	public void viewLog() {
		startActivity(new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS));
	}

}
