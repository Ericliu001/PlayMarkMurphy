package com.playmarkmurphy.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

public class PollReceiver extends BroadcastReceiver {

	private static final int PERIOD = 3500;

	@Override
	public void onReceive(Context context, Intent intent) {
		scheduleAlarms(context);
	}

	static void scheduleAlarms(Context context) {
		AlarmManager mgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		Intent i = new Intent(context, ScheduledService.class);
		PendingIntent pi = PendingIntent.getService(context, 0, i, 0);
		
		mgr.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + PERIOD, PERIOD, pi);
	}

}
