package com.playmarkmurphy.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.playmarkmurphy.R;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class EditorFragment extends Fragment {
	private static final String FILENAME = "notes.txt";
	private CheckBox cbExternal = null;
	private EditText etEditor = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setHasOptionsMenu(true);

		View result = inflater.inflate(R.layout.editor, container, false);
		etEditor = (EditText) result.findViewById(R.id.etEditor);

		return result;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.actions, menu);
		cbExternal = (CheckBox) menu.findItem(R.id.cbExternal);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.save:
			try {
				save(etEditor.getText().toString(), getTarget());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				boom(e);
			}
			break;

		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}

	private File getTarget() {
		File root = null;
		if (cbExternal != null && cbExternal.isChecked()) {
			root = getActivity().getExternalFilesDir(null);
		} else {
			root = getActivity().getFilesDir();
		}

		return new File(root, FILENAME);
	}

	private void save(String text, File target) throws IOException {
		FileOutputStream fos = new FileOutputStream(target);
		OutputStreamWriter out = new OutputStreamWriter(fos);

		out.write(text);
		out.flush();
		fos.getFD().sync();
		out.close();
	}

	private String load(File target) throws IOException {
		String result = "";
		try {
			InputStream in = new FileInputStream(target);
			if (in != null) {
				try {
					InputStreamReader tmp = new InputStreamReader(in);
					BufferedReader reader = new BufferedReader(tmp);
					String str;
					StringBuilder buf = new StringBuilder();

					while ((str = reader.readLine()) != null) {
						buf.append(str);
						buf.append("\n");
					}
					result = buf.toString();
				} finally {
					in.close();
				}
			}
		} catch (FileNotFoundException e) {
		}

		return result;
	}
	
	private class LoadTask extends AsyncTask<File, Void, String>{
		private Exception e = null;
		
		@Override
		protected String doInBackground(File... params) {
			// TODO Auto-generated method stub
			String result = "";
			
			try{
				result = load(params[0]);
			}catch(Exception e){
				this.e = e;
			}
			
			return result;
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			if (e == null) {
				etEditor.setText(result);
			}else{
				boom(e);
			}
		}
	}
	
	private class SaveTask extends AsyncTask<Void, Void, Void>{
		
		private Exception e = null;
		private String text;
		private File target;
		
		public SaveTask(String text, File target) {
			// TODO Auto-generated constructor stub
			this.text = text;
			this.target = target;
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try{
				save(text, target);
			}catch(Exception e){
				this.e = e;
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			if ( e != null) {
				boom(e);
			}
		}
		
	}
	

	public void boom(Exception e) {
		// TODO Auto-generated method stub
		Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
	}
}
