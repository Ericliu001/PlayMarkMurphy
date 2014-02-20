package com.playmarkmurphy.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.playmarkmurphy.R;

public class FauxSenderTestActivity extends Activity {
	EditText etSubject, etText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_faux_sender_test);
		etSubject = (EditText) findViewById(R.id.etSubject);
		etText = (EditText) findViewById(R.id.etText);
	}
	
	
	public void sendIt(View view){
		Intent i = new Intent(Intent.ACTION_SEND);
		
		i.setType("text/plain");
		i.putExtra(Intent.EXTRA_TEXT, etText.getText().toString());
		i.putExtra(Intent.EXTRA_SUBJECT, etSubject.getText().toString());
		
		startActivity(Intent.createChooser(i, "Choose the things"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.faux_sender_test, menu);
		return true;
	}

}
