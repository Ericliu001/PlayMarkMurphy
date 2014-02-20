package com.playmarkmurphy.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;

public class Downloader extends IntentService {

	public static final String ACTION_COMPLETE = "com.commonsware.android.downloader.action.COMPLETE";

	public Downloader() {
		
		super("Downloader");
		
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		try {
		File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
		
		root.mkdirs();
		
		File output = new File(root, intent.getData().getLastPathSegment());
		
		if (output.exists()) {
			output.delete();
		}
		
		URL url = new URL(intent.getData().toString());
		
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setReadTimeout(15000);
			conn.connect();
			
			FileOutputStream fos = new FileOutputStream(output.getPath());
			BufferedOutputStream out = new BufferedOutputStream(fos);
			try{
			InputStream in = conn.getInputStream();
			byte[] buffer = new byte[8192];
			int len = 0;
			
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			
			out.flush();
			}finally{
				fos.getFD().sync();
				out.close();
			}
			sendBroadcast(new Intent(ACTION_COMPLETE));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
