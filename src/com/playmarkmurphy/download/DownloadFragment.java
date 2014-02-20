package com.playmarkmurphy.download;

import com.playmarkmurphy.R;

import android.app.DownloadManager;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class DownloadFragment extends Fragment implements View.OnClickListener {
	private DownloadManager mgr = null;

	// Why use View here instead of Buttons?
	private View btQuery = null;
	private View btStart = null;

	private long lastDownload = -1L;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mgr = (DownloadManager) getActivity().getSystemService(
				Context.DOWNLOAD_SERVICE);

		View result = inflater.inflate(R.layout.fragment_download, container,
				false);

		btQuery = result.findViewById(R.id.btQuery);
		btQuery.setOnClickListener(this);
		btStart = result.findViewById(R.id.btStart);
		btStart.setOnClickListener(this);

		result.findViewById(R.id.btView).setOnClickListener(this);
		return result;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btQuery:
			queryStatus(v);
			break;
		case R.id.btStart:
			startDownload(v);
			break;

		default:
			((DownloadActivity) getActivity()).viewLog();
			break;
		}
	}

	private void startDownload(View view) {
		Uri uri = Uri.parse("http://commonsware.com/misc/test.mp4");

		Environment.getExternalStoragePublicDirectory(
				Environment.DIRECTORY_DOWNLOADS).mkdirs();

		DownloadManager.Request req = new DownloadManager.Request(uri);

		req.setAllowedNetworkTypes(
				DownloadManager.Request.NETWORK_WIFI
						| DownloadManager.Request.NETWORK_MOBILE)
				.setAllowedOverRoaming(false)
				.setTitle("Demo")
				.setDescription("Something useful. No, really.")
				.setDestinationInExternalPublicDir(
						Environment.DIRECTORY_DOWNLOADS, "test.mp4");

		lastDownload = mgr.enqueue(req);

		view.setEnabled(false);
		btQuery.setEnabled(true);
	}

	private void queryStatus(View view) {
		Cursor cursor = mgr.query(new DownloadManager.Query()
				.setFilterById(lastDownload));

		if (cursor == null) {
			Toast.makeText(getActivity(), "Download not found",
					Toast.LENGTH_LONG).show();
		} else {
			cursor.moveToFirst();
			Log.d("eric",
					"COLUMN_ID: "
							+ cursor.getLong(cursor
									.getColumnIndex(DownloadManager.COLUMN_ID)));

			Log.d("eric",
					"COLUMN_BYTES_DOWNLOADED_SO_FAR: "
							+ cursor.getLong(cursor
									.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR)));
			cursor.close();
		}

	}
	
	

	BroadcastReceiver onEvent = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (DownloadManager.ACTION_NOTIFICATION_CLICKED.equals(intent
					.getAction())) {
				Toast.makeText(getActivity(), "Hi", Toast.LENGTH_LONG).show();
			} else {
				btStart.setEnabled(true);
			}
		}
	};
	
	
	@Override
	public void onStart() {
		
		super.onStart();
		IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
		filter.addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED);
		getActivity().registerReceiver(onEvent, filter);
	}
	
	@Override
	public void onStop() {
		
		super.onStop();
		getActivity().unregisterReceiver(onEvent);
	}

}
