package com.playmarkmurphy.async;

import java.util.ArrayList;

import android.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class AsyncFragment extends ListFragment {
	private static final String[] items = {
		"May the force"
		,"Republic of Korea"
		,"God bless America"
		,"Australian Made"
		,"Made in China"
		,"Gingerbread"
		,"I love garlic"
	};
	
	private ArrayList<String> model = null;
	private ArrayAdapter<String> adapter = null;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setRetainInstance(true);
		
		if (model == null) {
			model = new ArrayList<String>();
			new AddStringTask().execute();
		}
		
		adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, model);
		getListView().setScrollbarFadingEnabled(false);
		setListAdapter(adapter);
	}
	
	
	private class AddStringTask extends AsyncTask<Void, String, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			for (String item: items) {
				publishProgress(item);
				SystemClock.sleep(500);
			}
			return null;
		}
		
		@Override
		protected void onProgressUpdate(String... values) {
			adapter.add(values[0]);
		}
		
		@Override
		protected void onPostExecute(Void result) {
			Toast.makeText(getActivity(), "Job Done!", Toast.LENGTH_SHORT).show();
		}
		
	}

}
