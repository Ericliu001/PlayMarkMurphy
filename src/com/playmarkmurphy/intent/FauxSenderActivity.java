package com.playmarkmurphy.intent;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Menu;
import android.widget.Toast;

public class FauxSenderActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String msg = getIntent().getStringExtra(Intent.EXTRA_TEXT);
		
		if (TextUtils.isEmpty(msg)) {
			 msg = getIntent().getStringExtra(Intent.EXTRA_SUBJECT);
		}
		
		if (TextUtils.isEmpty(msg)) {
			Toast.makeText(this, "No message supplied.", Toast.LENGTH_LONG).show();
		}else {
			Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
		}
		
		finish();
	}


}
