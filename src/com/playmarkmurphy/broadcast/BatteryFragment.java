package com.playmarkmurphy.broadcast;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.provider.VoicemailContract.Status;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.playmarkmurphy.R;

public class BatteryFragment extends Fragment {
	private ProgressBar pbBattery = null;
	private ImageView ivStatus = null;
	private TextView tvLevel = null;

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View result = inflater.inflate(R.layout.battery, container, false);
		
		pbBattery = (ProgressBar) result.findViewById(R.id.pbBattery);
		ivStatus = (ImageView) result.findViewById(R.id.ivStatus);
		tvLevel = (TextView) result.findViewById(R.id.tvLevel);
		
		
		return result;
	}
	
	
	@Override
	public void onStart() {
		
		super.onStart();
		
		IntentFilter f = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		
		getActivity().registerReceiver(onBattery, f);
	}
	
	@Override
	public void onStop() {
		
		super.onStop();
		getActivity().unregisterReceiver(onBattery);
	}
	
	
	private BroadcastReceiver onBattery = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			int pct = 100* intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 1)
					/ intent.getIntExtra(BatteryManager.EXTRA_SCALE, 1);
			
			pbBattery.setProgress(pct);
			tvLevel.setText(String.valueOf(pct));
			
			switch (intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)) {
			case BatteryManager.BATTERY_STATUS_CHARGING:
				ivStatus.setImageResource(R.drawable.charging);
				break;
				
			case BatteryManager.BATTERY_STATUS_FULL:
				int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
				
				if (plugged == BatteryManager.BATTERY_PLUGGED_AC || plugged == BatteryManager.BATTERY_PLUGGED_USB) {
					ivStatus.setImageResource(R.drawable.full);
				}else {
					ivStatus.setImageResource(R.drawable.unplugged);
				}
				
				ivStatus.setImageResource(R.drawable.full);
				break;
				
				

			default:
				ivStatus.setImageResource(R.drawable.unplugged);
				break;
			}
		}
	};
}
