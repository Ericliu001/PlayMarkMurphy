package com.playmarkmurphy.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PlayerService extends Service {
	
	
	
	public static final String EXTRA_PLAYLIST = null;
	public static final String EXTRA_SHUFFLE = null;
	private boolean isPlaying = false;
	
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		String playlist = intent.getStringExtra(EXTRA_PLAYLIST);
		boolean useShuffle = intent.getBooleanExtra(EXTRA_SHUFFLE, false);
		
		play(playlist, useShuffle);
		
		return START_NOT_STICKY;
	}
	
	
	@Override
	public void onDestroy() {
		
		super.onDestroy();
		stop();
	}

	private void stop() {
		if (isPlaying) {
			isPlaying = false;
		}
	}


	private void play(String playlist, boolean useShuffle) {
		if (! isPlaying) {
			isPlaying = true;
		}
	}

	@Override
	public IBinder onBind(Intent arg0) {

		return null;
	}

}
