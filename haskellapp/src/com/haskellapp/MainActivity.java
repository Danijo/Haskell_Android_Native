package com.haskellapp;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import com.haskellapp.R;

import android.annotation.SuppressLint;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnKeyListener;
import android.support.v7.app.AppCompatActivity;

//extends   Activity

// @SuppressLint("ClickableViewAccessibility")
public class MainActivity extends AppCompatActivity{

	static {
		System.loadLibrary("runhugsApp");
		Log.v("HaskellApp", "Loaded Libraries");
	}
	
	public native String evalHugsExpr(String x, int y,int z);
	public native String cleanStack(String z);
	public int OUTPUT_LIMIT = 750;
	private Timer timer_asynctask;
	private Worker worker_asynctask;
	String text_view_message;
	int DONOTWRITE = 0;
	public int TWO_SECONDS = 2000;
	int plus = 0;
	int REDUCTION_LIMIT = 100000;  
	int linhasdetextview = 100;
	int first = 1;
	String[] myStringArray = new String[10000];
	
	//private static final View view = null;
	//EditText editText = (EditText) findViewById(R.id.edit_message);
	//private Worker mTask3;
	//int flag = 0;
	//int x = 0;
	
	
	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		
		loading_interface (savedInstanceState);
	

		
		//TextView textview = (TextView) findViewById(R.id.text_id);

		/*
		TextView tv = (TextView) findViewById(R.id.text_id);
		tv.setTextIsSelectable(true);
		String stringYouExtracted = tv.getText().toString();
		int startIndex = tv.getSelectionStart();
		int endIndex = tv.getSelectionEnd();
		stringYouExtracted = stringYouExtracted.substring(startIndex, endIndex);
		ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
		clipboard.setText(stringYouExtracted);
		*/
		/*
		
		textview.setOnTouchListener(new OnTouchListener () {
		
			public boolean onTouch(View view, MotionEvent event) {
				  EditText editText = (EditText) findViewById(R.id.edit_message);
				  editText.setText("HERE...");
			    if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
			      Log.d("TouchTest", "Touch down");
			    } else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
			      Log.d("TouchTest", "Touch up");
			    }
			    return false;
			  }
		});
		
		textview.setOnClickListener(new View.OnClickListener() {

		    @Override
		    public void onClick(View v) {
		        // TODO Auto-generated method stub
		    	EditText editText = (EditText) findViewById(R.id.edit_message);
				editText.setText("HERE...");
		    	Log.v("AS", "Clicked");
		    }
		});
		
		textview.setTextIsSelectable(true);
		*/
		
		/*
		setContentView(R.layout.initial_image);
		View w = (ImageView)findViewById(R.id.initial_image2);
		w.setVisibility(View.VISIBLE);
		w.forceLayout();
		onStart(savedInstanceState);
		Log.v("HaskellApp", "Ready");
		SystemClock.sleep(5000);
		*/
		
		/*
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        startActivity(intent);
		editText.setText(">");
		*/
		
		/*
		((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
				.toggleSoftInput(InputMethodManager.SHOW_FORCED,
						InputMethodManager.HIDE_IMPLICIT_ONLY);
		 */
			
		/*
		Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
	    setSupportActionBar(myToolbar);
		*/
		
		/*
	    class CancelButtonListener implements View.OnClickListener {

	        public void onClick(View v) {
	            _initTask.cancel(true);
	        }
	    }
	    */

		/*
		try {
			Runtime.getRuntime().exec("su root chmod -R 777 " + "hugs");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
	}
	/*
	protected void onPause() {
		// TODO Auto-generated method stub
		/*super.onPause();
		SharedPreferences myPrefs = getSharedPreferences("HUGS", Context.MODE_PRIVATE);
	    SharedPreferences.Editor prefsEditor = myPrefs.edit();
	    TextView textview = (TextView) findViewById(R.id.text_id);
	    String convert = (String) textview.getText();
	    prefsEditor.putString("myvalue", convert);

	    prefsEditor.commit();
	    
	    
	}

		@Override
	protected void onResume() {
		    // TODO Auto-generated method stub
			/*
		    super.onResume();
		    SharedPreferences myPrefs1 = getSharedPreferences("HUGS", Context.MODE_PRIVATE);
		    String prefName1 = myPrefs1.getString("myvalue", "");
		    TextView textview = (TextView) findViewById(R.id.text_id);
		    textview.setText(prefName1);
}
		    */
	
	
	
	@SuppressLint("ResourceAsColor")
	protected void Start(Bundle savedInstanceState){
		
		SharedPreferences sharedPreferences = getSharedPreferences("HUGS", Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		
		SharedPreferences prefs = getSharedPreferences("HUGS", MODE_PRIVATE);
		int idName = prefs.getInt("First_Time", 0);
		Log.v("HaskellApp", String.valueOf(idName));
		
		if (idName == 0){
			copyFileOrDir("hugs");
			Log.v("HaskellApp", "Copied hugs Folder");
		}
		
		String startjni = evalHugsExpr("a", 100,100000);
		Log.v("HaskellApp", "Hugs Started");
		
		editor.putInt("First_Time", 1);
		editor.commit();
	}
	
	
	
	
	public void loading_interface (Bundle savedInstanceState){
		
		setContentView(R.layout.activity_main);
		
		createtoolbar();
		
		//findViewById(R.id.loadingPanel).setVisibility(View.GONE);
		TextView textview = (TextView) findViewById(R.id.text_id);
		textview.setBackgroundResource(R.drawable.hugs);
		//textview.setTextIsSelectable(true);
		
		
		EditText editText = (EditText) findViewById(R.id.edit_message);
		editText.setText("LOADING...");
		
		findViewById(R.id.button_cancel).setVisibility(View.GONE);
		
		findViewById(R.id.button_send).setVisibility(View.GONE);
		
		new Loading(savedInstanceState,editText,textview).execute();
		
		Log.v("HaskellApp", "All Done until here");

	}
	
	
	public void main_function(View view) throws ExecutionException, TimeoutException, InterruptedException {
		
		TextView textview = (TextView) findViewById(R.id.text_id);
		textview.setMovementMethod(new ScrollingMovementMethod());
		
		
		if (first == 1){
		
		Layout layout = textview.getLayout();
        int height = textview.getHeight();
        int scrollY = textview.getScrollY();
        int firstVisibleLineNumber = layout.getLineForVertical(scrollY);
		int lastVisibleLineNumber = layout.getLineForVertical(height+scrollY);
		int size = lastVisibleLineNumber - firstVisibleLineNumber; 
		Log.v("Screensize", ""+size);
		
		textview.setText(null);
		textview.setScrollY(0);
		
		
			for (int i = 0; i < size; i++){
				textview.append("" + '\n');
			}
		first = 0;
		linhasdetextview = size;
		}
		
		EditText editText = (EditText) findViewById(R.id.edit_message);
		final String final_message = cleanning_message(editText);

		Log.v("HaskellApp", "Begin JNI");
		Log.v("HaskellApp",final_message);
		Log.v("HaskellApp",String.valueOf(OUTPUT_LIMIT));
		
		findViewById(R.id.button_cancel).setVisibility(View.GONE);
		
		findViewById(R.id.button_send).setVisibility(View.GONE);
		
		findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);

		worker_asynctask = new Worker(final_message, editText,OUTPUT_LIMIT,textview,REDUCTION_LIMIT);
		//timer_asynctask = new Timer(final_message,editText,worker_asynctask,textview);
		StartAsyncTaskInParallel_worker(worker_asynctask);
		//StartAsyncTaskInParallel_timer(timer_asynctask);

		Log.v("HaskellApp", "Done waiting");
		
		/*
		cleanStack("clean");
		Log.v("HaskellApp",String.valueOf(mTask2.getStatus()));
		*/
		
		//text2.setSelected(true);
		
		/*
		
		editText.setKeyListener(new KeyListener()
		{
		    public boolean onKey(View v, int keyCode, KeyEvent event)
		    {
		        if (event.getAction() == KeyEvent.ACTION_DOWN)
		        {
		            switch (keyCode)
		            {
		                case KeyEvent.KEYCODE_ENTER:
		                	Log.v("HaskellApp", "X");
		                    return true;
		                default:
		                    break;
		            }
		        }
		        return false;
		    }

			@Override
			public void clearMetaKeyState(View view, Editable content,
					int states) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public int getInputType() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public boolean onKeyDown(View view, Editable text, int keyCode,
					KeyEvent event) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean onKeyOther(View view, Editable text, KeyEvent event) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean onKeyUp(View view, Editable text, int keyCode,
					KeyEvent event) {
				// TODO Auto-generated method stub
				return false;
			}
		});

		*/
		
		/*
		EditText editText2 = (EditText) findViewById(R.id.max);
		
		String message2 = editText2.getText().toString();		
		history[0] = message;
		end = 1;
		*/
		
		/*
		if (editText2.getText().toString().trim().length() != 0) {
			n = Integer.parseInt(message2);
		} else {
			editText2.setText("750");
		}
		*/
		
		//text2.setText(mes);
		
		/*
		editText.setText(evalHugsExpr(message1, n));
		if (mTask.isCancelled()){mTask2 = new Sync(message, message1, editText, n).execute();}
		else{mTask = new Sync(message, message1, editText, n).execute();}
		*/
		
		/*
		if (flag == 0){
			//mTask.cancel(true);
			mTask2 = new Sync(message, message1, editText, n).execute();
		}
		if (flag == 0){
			//mTask2.cancel(true);
			mTask = new Sync("1+1", "1+1", editText, n).execute();
		}
		
		Log.v("HaskellApp", "Done JNI");
		*/
		
		/*
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);	
		LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(
                10,
                LayoutParams.WRAP_CONTENT);
		*/
		
		/*
		param.weight = (float) x;
		param2.weight = (float) 1 - x;
				text2.setLayoutParams(param);
		 
		//param2.height = (int) 10.0f;
		Log.v("HaskellApp", String.valueOf(param.weight));
		Log.v("HaskellApp", String.valueOf(param.height));
		Log.v("HaskellApp", String.valueOf(param.width));		
		text2.setLayoutParams(param);
		//editText.setLayoutParams(param2);
		
		//text2.setLayoutParams(param);
		//editText.setLayoutParams(param2);
		//x = (float) (x + 0.05);
		*/
		
		/*
		mTask3 = new Sync(mes, "1+1", editText,n,text2,mes);
		StartAsyncTaskInParallel(mTask3);
		*/
		
		//mTask3 = new Sync2(editText).execute();
		
		
		//text2.setVerticalScrollBarEnabled(true);
	    
		
		//text2.setSelection(editText.getText().length());
		
		/*
		for (int i = 0; i < 3000; i++){
			 //Thread.sleep(4000);
			editText.setText(i + "");
			/*
			if (i < 999){
				editText.setText(".");
			}
			if (i > 998 || i < 1999){
				editText.setText("..");
			}
			if (i > 1998 || i < 2999){
				editText.setText("...");
			}
			
		}
		*/
		//editText.setText("passed for");
		
		
		/*
		for (int i = 0; i < 3000; i++){
			 //Thread.sleep(4000);
			editText.setText(i + "");
		}
		*/
		/*
		if (mTask2.isCancelled()){
			editText.setText("cancelled");
		}
		*/
		//mTask = new Sync("1+1", "1+1", editText, n).execute();		
		
		/*
		if (mTask.getStatus() == AsyncTask.Status.RUNNING){
			mTask.cancel(true);
		}
		*/
		
		//mTask2 = new Sync2(mTask);
			
		//mTask.cancel(true);
		//editText.setText("...");
	
		//mTask.get(2, TimeUnit.SECONDS);
		
		//editText.setText("hello");
		
		//mTask = new Sync(message, message1, editText, n).execute();

	}
	
	//Start AsyncTaks in Parallel (Get it from the net)

	 private void StartAsyncTaskInParallel_worker(Worker worker) {
	     if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
	         worker.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
	     else
	         worker.execute();
	 }
	
	 private void StartAsyncTaskInParallel_timer(Timer timer) {
	     if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
	         timer.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
	     else
	         timer.execute();
	 }
	 
	private class Worker extends AsyncTask<String, Void, String> {
		
		String user_input;
		EditText editText;
		TextView textview;
		int output_limit;
		int reduction_limit;

		Worker(String m, EditText e, int n, TextView t,int r) {
			
			this.user_input = m;
			this.editText = e;
			this.output_limit = n;	
			this.textview = t;
			this.reduction_limit = r;
		}

		protected void onPreExecute(){
			Log.v("HaskellApp", "Begining");
		}
		
		protected String doInBackground(String... param) {

			Log.v("HaskellApp", "Passed here " + user_input);
		
			String result =  evalHugsExpr(user_input, output_limit,reduction_limit);
			
			Log.v("HaskellApp", "Passed there " + result);
			
			//return "loop";
			//if (mTask.isCancelled()) {return "loop";}
			
			return result;
			
			// exit()
		}
		
		protected void onProgressUpdate(){
				//editText.setText("looping");
		}		
		
		protected void onCancelled() {
			Log.v("HaskellApp", "done Restarting");
			Log.v("HaskellApp", "Cancelled " + user_input);
			return;
			
		}
		
		protected void onPostExecute(String result) {
			
			Log.v("HaskellApp", "C part Done");
			
			//Log.v("HaskellApp", result);
			
			String result1 = result + " ";
			String[] lines =  result1.split("\r\n|\r|\n");
			plus = plus + lines.length;
			
			result = result.trim();
						
			textview.append('\n' + "> " + user_input + '\n' + result);
			
			int scroll_amount = 1;
			//Log.v("HaskellApp", String.valueOf(scroll_amount));
			textview.scrollBy(0, scroll_amount);
			
			clean_edittext(editText);

			findViewById(R.id.button_cancel).setVisibility(View.GONE);
			
			findViewById(R.id.button_send).setVisibility(View.VISIBLE);
			
			findViewById(R.id.loadingPanel).setVisibility(View.GONE);
			
			Log.v("HaskellApp", "All done by the Thread");
			
			//editText.setSelection(editText.getText().length());
		}
			
	}

	@SuppressWarnings("rawtypes")
	private class Timer extends AsyncTask<String, Void, AsyncTask> {
		
		EditText editText;
		TextView textview;
		AsyncTask worker;
		String user_input;
		
		Timer(String m,EditText e, AsyncTask<?, ?, ?> x,TextView t) {
		
			this.user_input = m;
			this.editText = e;
			this.worker = x;
			this.textview = t;
		}
		
		protected AsyncTask doInBackground(String... params) {
			
			Log.v("HaskellApp", "Sync2 in action");
			int n = 0;
			
			SystemClock.sleep(TWO_SECONDS); 
			Log.v("HaskellApp", "Done Sleeping");
			
			if (worker.getStatus() == AsyncTask.Status.RUNNING){ 
				Log.v("HaskellApp", "Canceled");	
				worker.cancel(true);
			}
			
			//while (worker.getStatus() == AsyncTask.Status.RUNNING){
				//worker.cancel(true);
				//worker.isCancelled();
				//n = n + 1;
				//worker = new Worker("1+1", editText,OUTPUT_LIMIT,textview).execute();
				//worker.cancel(true);
				//Log.v("HaskellApp", String.valueOf(n));
			//}
			//Log.v("HaskellApp", String.valueOf(worker.getStatus()));

					
			return worker;
			
		}		
		protected void onPostExecute(AsyncTask result) {

			
			
			
			//SystemClock.sleep(8000);
			//if (mTask2.isCancelled()){Log.v("HaskellApp", "yeah!");}
			
			
			Log.v("HaskellApp", "cleaning");

			if (worker.isCancelled()){
				//System.loadLibrary("runhugsApp");
				//Log.v("HaskellApp", "Loaded Libraries");
				if (DONOTWRITE == 0){
					textview.append('\n' + "> " + user_input + '\n' + "Evaluation Killed!");
				}


				//String resethugs = cleanStack("clean");
				//Log.v("HaskellApp", "Hugs cleaning");
				
				worker = null;
				Bundle b = null;
				Start(b);
				
				//String startjni = evalHugsExpr("1+1", 100);
				//Log.v("HaskellApp", "Hugs Started");
				//Log.v("HaskellApp", startjni);
				
				
						
				clean_edittext(editText);
				
				int scroll_amount = 1;
				textview.scrollBy(0, scroll_amount);
				
				findViewById(R.id.button_cancel).setVisibility(View.GONE);
				
				findViewById(R.id.button_send).setVisibility(View.VISIBLE);
				
				findViewById(R.id.loadingPanel).setVisibility(View.GONE);
				
				Log.v("HaskellApp", "Everything done");
			}
			DONOTWRITE = 0;
		}
		
	}

	@SuppressWarnings("rawtypes")
	private class Loading extends AsyncTask<String, Void, Bundle> {

		Bundle bundle;
		EditText editText;
		TextView textview;
		
		Loading(Bundle b,EditText e,TextView t){
			
			this.bundle = b;
			this.editText = e;
			this.textview = t;
			
		}
		
		@SuppressLint("ResourceAsColor")
		protected void onPostExecute(Bundle w) {
			
			Log.v("HaskellApp", "I get here");
			
			setContentView(R.layout.activity_main);
			
			createtoolbar();
			
			editText = (EditText) findViewById(R.id.edit_message);
			clean_edittext(editText);
			
			findViewById(R.id.loadingPanel).setVisibility(View.GONE); 
			
			textview = (TextView) findViewById(R.id.text_id);
			//textview.setBackgroundColor(R.color.black);
			//textview.setBackgroundResource(R.color.black);
			
			//textview.setGravity(Gravity.TOP);
						
			definescroll(textview);
			
			
			for (int i = 0; i < linhasdetextview; i++){
				textview.append("" + '\n');
			}
					
			
			findViewById(R.id.button_cancel).setVisibility(View.GONE);
			
			findViewById(R.id.button_send).setVisibility(View.VISIBLE);		
			
			turn_on_enterKey(editText);
			
			Log.v("HaskellApp", "finish loading");
			
			
		}

		@Override
		protected Bundle doInBackground(String... params) {
			
			Start(bundle);
			return bundle;
		}
		
	}
	
	
	//Não utilizo
	
	public void cancel_event(View view) throws ExecutionException, TimeoutException, InterruptedException {
		
		worker_asynctask.cancel(true);
		timer_asynctask.cancel(true);
	
		String resethugs = cleanStack("clean");
		Log.v("HaskellApp", "Hugs cleaning");

		String startjni = evalHugsExpr("1+1", 100,100000);
		Log.v("HaskellApp", "Hugs Started");
		Log.v("HaskellApp", startjni);

		worker_asynctask = null;
		Bundle b = null;
		
		Log.v("HaskellApp", "done Restarting");
		
		DONOTWRITE = 1; 
		
		TextView textview = (TextView) findViewById(R.id.text_id);
	    textview.setMovementMethod(new ScrollingMovementMethod());
	    
	    EditText editText = (EditText) findViewById(R.id.edit_message);
	    final String user_input = cleanning_message(editText);
	    clean_edittext(editText);
	  	
		textview.append('\n' + "> " + user_input + '\n' + "Evaluation Canceled!");
		
		int scroll_amount = 1;
		textview.scrollBy(0, scroll_amount);
		
		findViewById(R.id.button_cancel).setVisibility(View.GONE);
		
		findViewById(R.id.button_send).setVisibility(View.VISIBLE);
		
		findViewById(R.id.loadingPanel).setVisibility(View.GONE);
		
		
		//if (flag == 0){flag = 1;}
		//else {flag = 0;}
		//mTask.cancel(true);
		
		/*
		Intent i = getBaseContext().getPackageManager()
				.getLaunchIntentForPackage(getBaseContext().getPackageName());
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i);
		*/
		//if(mTask.getStatus() == AsyncTask.Status.RUNNING){
		    // My AsyncTask is currently doing work in doInBackground()
			//mTask.cancel(true);
			//mTask.cancel(true);
			//mTask = mTask2;
			//android.os.Process.killProcess(android.os.Process.myTid());
			//mTask.get();
			//Awake(mTask);
			//mTask = new Sync("hello","Hey",editText,n).execute();
		//
		//sendmessage(view);
		//android.os.Process.myTid();
		//android.os.Process.setThreadPriority(tid, priority);
	}
	/*
	public void Awake(AsyncTask t){
		if (t.isCancelled()){
			t.notify();
		}
	}
	*/
	
	//Creates a menu in the toolbar (removed from the net)
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();

		inflater.inflate(R.menu.menu, menu);

		return true;

	}	

	//Options in toolbar
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.action_setting:
	        	EditText editText = (EditText) findViewById(R.id.edit_message);
	        	clean_edittext(editText);
	    
	        	TextView textview = (TextView) findViewById(R.id.text_id);
	        	textview.setMovementMethod(new ScrollingMovementMethod());
	    		textview.setText("");
				
	    		definescroll(textview);
	    
	    		for (int i = 0; i < linhasdetextview; i++){
					textview.append("" + '\n');
				}
	    		
	            return true;
	            
	        case R.id.menu_small:
	        	if (item.isChecked()) item.setChecked(false);
        		else item.setChecked(true);
	        	REDUCTION_LIMIT = 10000;
        			Log.v("HaskellApp",String.valueOf(REDUCTION_LIMIT));
        		return true;
	        	
	        case R.id.menu_normal:
	        	if (item.isChecked()) item.setChecked(false);
        		else item.setChecked(true);
	        	REDUCTION_LIMIT = 100000;
        			Log.v("HaskellApp",String.valueOf(REDUCTION_LIMIT));
        		return true;
        		
	        case R.id.menu_big:	
	        	if (item.isChecked()) item.setChecked(false);
        		else item.setChecked(true);
	        	REDUCTION_LIMIT = 1000000;
        			Log.v("HaskellApp",String.valueOf(REDUCTION_LIMIT));
        		return true;
	        
		    case R.id.menu_one:
		        		if (item.isChecked()) item.setChecked(false);
		        		else item.setChecked(true);
		        		OUTPUT_LIMIT = 150;
		        			Log.v("HaskellApp",String.valueOf(OUTPUT_LIMIT));
		        		return true;
		    
		    case R.id.menu_two:
		        		if (item.isChecked()) item.setChecked(false);
		        		else item.setChecked(true);
		        		OUTPUT_LIMIT = 400;
		        			Log.v("HaskellApp",String.valueOf(OUTPUT_LIMIT));
		        		return true;
		    
		    case R.id.menu_three:
		        		if (item.isChecked()) item.setChecked(false);
		        		else item.setChecked(true);
		        		OUTPUT_LIMIT = 750;
		        			Log.v("HaskellApp",String.valueOf(OUTPUT_LIMIT));
		        		return true;
		    
		    case R.id.menu_four:
		        		if (item.isChecked()) item.setChecked(false);
		        		else item.setChecked(true);
		        		OUTPUT_LIMIT = 999;
		        			//1000 gives error?
		        			Log.v("HaskellApp",String.valueOf(OUTPUT_LIMIT));
		        		return true;
		    
		    case R.id.action_favorite:
	        	Log.v("HaskellApp",String.valueOf(OUTPUT_LIMIT));
	       
	            
	        default:
	            return super.onOptionsItemSelected(item);

	    }
	}
	


	/**
	 * A placeholder fragment containing a simple view.
	 */
	
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(1, container, false);
			return rootView;
		}
	}
	
	public void clean_edittext(EditText editText){
		editText.setText("");
	}
	
	public void createtoolbar(){
		Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
		setSupportActionBar(myToolbar);
	}
	
	  //TextView textview = (TextView) findViewById(R.id.text_id);
	
	/*
	
	OnTouchListener listener = new OnTouchListener() {      
        @Override
	 
	public boolean onTouch(View v, MotionEvent event) {
		//int action = event.getAction();
		Log.d("DOWN",String.valueOf(event.getAction()));
		
	        switch (event.getAction()){
	        
	        case MotionEvent.ACTION_DOWN:
	            Log.d("DOWN","DOWN");
	        break;

	        case MotionEvent.ACTION_MOVE:
	            Log.d("MOVE","MOVE");
	        break;

	        case MotionEvent.ACTION_UP:
	            Log.d("UP","UP");
	            float X = event.getRawX();
	            float Y = event.getRawY();

	            Display display = getWindowManager().getDefaultDisplay(); 
	            float width=display.getWidth()/2;
	            float height=display.getHeight()/2;
	            Log.e("X", X+"");
	            Log.e("Y", Y+"");
	            Log.e("ScX", width+"");
	            Log.e("ScY", height+"");
	            if(X>width && Y>height){
	                Log.e("SUFI", "Event ho gyuaaaaaaa");
	            }

	        break;
	        }
	        return true;
	    }
	    
	};
	*/
	
	/*
	
	public void onClick(View view){


		TextView textview = (TextView) findViewById(R.id.text_id);
	
		
		EditText editText = (EditText) findViewById(R.id.edit_message);
		editText.setText("Hello");
	}
	*/
	
	/*
	public boolean onTouchEvent(MotionEvent event){ 
        
	    int action = MotionEventCompat.getActionMasked(event);
	        
	    switch(action) {
	        case (MotionEvent.ACTION_DOWN) :
	            Log.d("DEBUG_TAG","Action was DOWN");
	            return true;
	        case (MotionEvent.ACTION_MOVE) :
	            Log.d("DEBUG_TAG","Action was MOVE");
	            return true;
	        case (MotionEvent.ACTION_UP) :
	            Log.d("DEBUG_TAG","Action was UP");
	            return true;
	        case (MotionEvent.ACTION_CANCEL) :
	            Log.d("DEBUG_TAG","Action was CANCEL");
	            return true;
	        case (MotionEvent.ACTION_OUTSIDE) :
	            Log.d("DEBUG_TAG","Movement occurred outside bounds " +
	                    "of current screen element");
	            return true;      
	        default : 
	            return super.onTouchEvent(event);
	    }      
	}
	*/
	
	
	public void turn_on_enterKey(EditText editText){
		editText.setOnKeyListener(new OnKeyListener() {
	        public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
	            //If the keyevent is a key-down event on the "enter" button
	        	//text2.setText();
	            if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
	            	Log.v("HaskellApp", "Please...");
	            	try {
	            		main_function(view);
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (TimeoutException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                //...
	                // Perform your action on key press here
	                // ...    
	                return true;
	            }
	            return false;
	        }
	});	

	}
	

	//Copy Hugs (We get it on the net)

	private void copyFileOrDir(String path) {
	    AssetManager assetManager = this.getAssets();
	    String assets[] = null;
	    try {
	        assets = assetManager.list(path);
	        if (assets.length == 0) {
	            copyFile(path);
	        } else {
	            String fullPath = "/data/data/" + this.getPackageName() + "/" + path;
	            File dir = new File(fullPath);
	            if (!dir.exists())
	                dir.mkdir();
	            for (int i = 0; i < assets.length; ++i) {
	                copyFileOrDir(path + "/" + assets[i]);
	            }
	        }
	    } catch (IOException ex) {
	        Log.e("tag", "I/O Exception", ex);
	    }
	}

	private void copyFile(String filename) {
	    AssetManager assetManager = this.getAssets();

	    InputStream in = null;
	    OutputStream out = null;
	    try {
	        in = assetManager.open(filename);
	        String newFileName = "/data/data/" + this.getPackageName() + "/" + filename;
	        out = new FileOutputStream(newFileName);

	        byte[] buffer = new byte[1024];
	        int read;
	        while ((read = in.read(buffer)) != -1) {
	            out.write(buffer, 0, read);
	        }
	        in.close();
	        in = null;
	        out.flush();
	        out.close();
	        out = null;
	    } catch (Exception e) {
	        Log.e("tag", e.getMessage());
	    }

	}

	public String cleanning_message(EditText editText){
		
		String message = editText.getText().toString();
		int startIndex = message.lastIndexOf('\n');
		String final_message;

		if (startIndex != -1 && startIndex != message.length()) {
			final_message = message.substring(startIndex + 1);
		} else {
			final_message = message;
		}
		
		return final_message;
	}
	
	public void definescroll(final TextView textview){
		textview.setOnTouchListener(new View.OnTouchListener() {		
		    public boolean onTouch(View v, MotionEvent event) {
		    
		    	
		    	Layout layout = ((TextView) v).getLayout();
				Log.v("index", "hellloo");
				int end = layout.getLineEnd(textview.getLineCount() - 1);
		        Log.v("index", "thend"+end);
		    	
		    	//String text = textview.getText().toString();
		    	//textview.setGravity(Gravity.TOP);
				    	
		    	
		    	EditText editText = (EditText) findViewById(R.id.edit_message);
		    	String edittext = editText.getText().toString();
		    	
		        int x = (int)event.getX();
		        int y = (int)event.getY();
		        if (layout!=null){
		            int line = layout.getLineForVertical(y);
		            //int offset = layout.getOffsetForHorizontal(line, x);
		            Log.v("index", "x"+x);
		            Log.v("index", "y"+y);
		            Log.v("index", "l"+line);
		            
		            
		            int start = textview.getLayout().getLineStart(0);
		            

		            String displayed = textview.getText().toString();

		            Log.v("index", ""+displayed+"textview");
		            
		            String[] lines = displayed.split(System.getProperty("line.separator"));
		            
		            
		            Log.v("indexlinelength", ""+lines.length);
		            //Log.v("indexlinelength", ""+lines[lines.length-1]);

		            int height    = textview.getHeight();
		            int scrollY   = textview.getScrollY();
		            //layout = textview.getLayout();

		            int firstVisibleLineNumber = layout.getLineForVertical(scrollY);
		            int lastVisibleLineNumber  = layout.getLineForVertical(scrollY+height);
		           
		            
		            Log.v("index1", ""+ firstVisibleLineNumber);
		            Log.v("index2", ""+lastVisibleLineNumber);
		         
		            
		            
		            if (lines.length != 0){
		            	Log.v("index3", ""+lines[line+firstVisibleLineNumber]);
		            
		            	editText.setText(lines[line+firstVisibleLineNumber].replace("> ", ""));
		            }
		            /*
		            int start = 0;
			    	int end;
			    	
			    	for (int i=1; i<textview.getLineCount(); i++) {
		                end = layout.getLineEnd(i);
		                
		                if (line + plus == i){
		                	Log.v("index", ""+text.substring(start,end));
		                	Log.v("index", ""+i);
		                	Log.v("index", ""+plus);
		                	editText.setText(text.substring(start,end).replace("\n", "").replace("> ", ""));
		                }
		                start = end;
		            }
		            */
		            
		            
		            /*
		            Log.v("index", ""+offset);
		            layout = textview.getLayout();
		            String text = textview.getText().toString();
		            int start=0;
		            int end;
		            for (int i=0; i<textview.getLineCount(); i++) {
		                end = layout.getLineEnd(i);
		                Log.v("index", ""+text.substring(start,end));
		                Log.v("index", ""+i);
		                start = end;
		            }
		            */
		        }
		        //textview.setGravity(Gravity.BOTTOM);
		        return false;
		    }
		});

	}
	
}

/*
 * 
 * public void sendmessage(View view) { // Intent intent = new Intent(this,
 * DisplayMessageActivity.class); // EditText editText = (EditText)
 * findViewById(R.id.edit_message); // String message =
 * editText.getText().toString(); // intent.putExtra(EXTRA_MESSAGE, message); //
 * startActivity(intent);
 * 
 * EditText editText = (EditText) findViewById(R.id.edit_message); String
 * message = editText.getText().toString();
 * editText.setText(evalHugsExpr(message));
 * 
 * //TextView tv = new TextView(this); //tv.setText(message + "/n" +
 * evalHugsExpr()); //setContentView(tv);
 * 
 * 
 * }
 */