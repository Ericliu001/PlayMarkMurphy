package com.playmarkmurphy.alarm;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class ScheduledService extends IntentService {

	public ScheduledService() {
		
		super("ScheduledService");
		
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		Log.d("eric", "I'm running");
	}

}
