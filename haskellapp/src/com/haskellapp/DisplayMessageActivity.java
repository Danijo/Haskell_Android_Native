package com.haskellapp;

import com.haskellapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayMessageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		// Get the message from the intent

		setContentView(R.layout.initial_image);
		ImageView w = (ImageView)findViewById(R.id.initial_image2);
		w.setVisibility(View.VISIBLE);
		
			}
		/*
	
	// Create the text view
		TextView textView = new TextView(this);
		textView.setTextSize(40);
		Log.v("HaskellApp", "BeforeJNI");
		textView.setText(stringFromJNI(message));
		Log.v("HaskellApp", "AfterJNI");
		setContentView(textView);



	public native String stringFromJNI(String x);

	static {
		System.loadLibrary("runhugsApp");
		Log.v("HaskellApp", "Loaded Libraries");
	}


	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(1, container, false);
			return rootView;
		}
	}
	*/
}