package com.playmarkmurphy.alarm;

import com.playmarkmurphy.R;
import com.playmarkmurphy.R.layout;
import com.playmarkmurphy.R.menu;

import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.widget.Toast;

public class SimpleAlarmActivity extends Activity {

	private static final int ALARM_ID = 1334;
	private static final int PERIOD = 3000;
	private PendingIntent pi = null;
	private AlarmManager mgr = null;
	private Vibrator vibrator = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_alarm);
//
//		mgr = (AlarmManager) getSystemService(ALARM_SERVICE);
//		pi = createPendingResult(ALARM_ID, new Intent(), 0);
//		mgr.setRepeating(AlarmManager.ELAPSED_REALTIME,
//				SystemClock.elapsedRealtime() + PERIOD, PERIOD, pi);
//		
//		vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
	}
	
	@Override
	protected void onDestroy() {
		
		super.onDestroy();
		mgr.cancel(pi);
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == ALARM_ID) {
			Toast.makeText(this, "Awesome!", Toast.LENGTH_SHORT).show();
			vibrator.vibrate(500);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.simple_alarm, menu);
		return true;
	}

}
