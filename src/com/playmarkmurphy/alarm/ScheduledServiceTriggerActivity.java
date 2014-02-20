package com.playmarkmurphy.alarm;

import com.playmarkmurphy.R;
import com.playmarkmurphy.R.layout;
import com.playmarkmurphy.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

public class ScheduledServiceTriggerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		PollReceiver.scheduleAlarms(this);
		
		Toast.makeText(this, "Start scheduled service now!", Toast.LENGTH_SHORT).show();
	
		
		finish();
	}


}
