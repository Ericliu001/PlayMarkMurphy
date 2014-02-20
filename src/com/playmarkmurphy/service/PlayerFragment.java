package com.playmarkmurphy.service;

import com.playmarkmurphy.R;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

public class PlayerFragment extends Fragment implements OnClickListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View result = inflater.inflate(R.layout.fragment_player, container,
				false);

		result.findViewById(R.id.btStart).setOnClickListener(this);
		result.findViewById(R.id.btStop).setOnClickListener(this);

		return result;
	}

	@Override
	public void onClick(View v) {
		Intent i = new Intent(getActivity(), PlayerService.class);

		switch (v.getId()) {
		case R.id.btStart:
			i.putExtra(PlayerService.EXTRA_PLAYLIST, "main");
			i.putExtra(PlayerService.EXTRA_SHUFFLE, true);
			getActivity().startService(i);
			return;

		case R.id.btStop:
			
			getActivity().stopService(i);
			return;

		default:
			break;
		}
		
		return;
	}

}
