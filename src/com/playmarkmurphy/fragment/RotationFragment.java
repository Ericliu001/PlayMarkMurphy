package com.playmarkmurphy.fragment;

import com.playmarkmurphy.R;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class RotationFragment extends Fragment implements OnClickListener {
	static final int PICK_REQUEST = 1337;
	Uri contact = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setRetainInstance(true);

		View result = inflater.inflate(R.layout.fragment_rotation, container,
				false);
		result.findViewById(R.id.btPick).setOnClickListener(this);

		Button btView = (Button) result.findViewById(R.id.btView);
		btView.setOnClickListener(this);
		btView.setEnabled(contact != null);

		return result;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		
		if (requestCode == PICK_REQUEST && resultCode == Activity.RESULT_OK) {
			contact = data.getData();
			getView().findViewById(R.id.btView).setEnabled(true);
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btPick:
			pickContact(v);
			break;

		case R.id.btView:
			viewContact(v);
			break;

		default:
			break;
		}
	}

	private void pickContact(View v) {
		// TODO Auto-generated method stub
		Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
		startActivityForResult(i, PICK_REQUEST);
	}

	private void viewContact(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(Intent.ACTION_VIEW, contact));
		
	}
}
