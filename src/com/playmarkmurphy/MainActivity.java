package com.playmarkmurphy;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	
	String[] ActivityNames = {
			""
			,"fragment.RotationFragmentActivity"
			,"async.AsyncActivity"
			,"file.FilesDemoActivity"
			,"pref.PrefFragmentActivity"
			,"pref.EditPrefsActivity"
			,"intent.FauxSenderTestActivity"
			,"broadcast.BatteryMonitorActivity"
			,"download.DownloadActivity"
			,"service.FakePlayerActivity"
			,"service.DemoDownloaderActivity"
			,"alarm.SimpleAlarmActivity"
			,"alarm.ScheduledServiceTriggerActivity"
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ActivityNames);
		setListAdapter(adapter);
	}
	
	@Override
	protected void onListItemClick(ListView list, View view, int position, long id) {
		String activityName = ActivityNames[position];
		try {
			Class targetActivity = Class.forName("com.playmarkmurphy." + activityName);
			Intent intent = new Intent(this, targetActivity);
			startActivity(intent);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	

}