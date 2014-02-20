package com.playmarkmurphy.service;

import com.playmarkmurphy.R;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class DownloadFragment extends Fragment implements OnClickListener {
	
	private Button b = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View result = inflater.inflate(R.layout.fragment_download, container, false);
		b = (Button) result.findViewById(R.id.btStart);
		b.setOnClickListener(this);
		
		return result;
	}

	@Override
	public void onClick(View v) {
		b.setEnabled(false);
		
		Intent i = new Intent(getActivity(), Downloader.class);
		i.setData(Uri.parse("http://commonsware.com/Android/excerpt.pdf"));
		getActivity().startService(i);
	}

	
	
	@Override
	public void onStart() {
		
		super.onStart();
		IntentFilter filter = new IntentFilter(Downloader.ACTION_COMPLETE);
		getActivity().registerReceiver(onEvent, filter);
		
	}
	
	
	@Override
	public void onStop() {
		
		super.onStop();
		getActivity().unregisterReceiver(onEvent);
	}
	
	private BroadcastReceiver onEvent = new BroadcastReceiver() {
		
		
		@Override
		public void onReceive(Context context, Intent intent) {
		b.setEnabled(true);
		
		Toast.makeText(getActivity(), "Download completed", Toast.LENGTH_LONG).show();
		}
	};
}
