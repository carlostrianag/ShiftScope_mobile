package shiftscope.views;

import shiftscope.controllers.ConnectionController;
import shiftscope.controllers.SongController;
import shiftscope.main.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer.TrackInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class Main extends Activity implements OnClickListener{
	
	private ImageView playBtn;
	private ImageView pauseBtn;
	private ImageView stopBtn;
	private ImageView nextBtn;
	private ImageView backBtn;
	private ImageView muteBtn;
	private ImageView trackListBtn;
	private static TextView textView;
	private static TextView songNameTextView;
	
	public static Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.getData().getString("currentsong") != null){
				songNameTextView.setText(msg.getData().getString("currentsong"));
				
			} else {
				textView.setText(msg.getData().getString("message"));
			}
			
		}
		
		
	};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		StrictMode.ThreadPolicy tp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(tp);
        setContentView(R.layout.activity_main);
        SongController.init();
        playBtn = (ImageView) findViewById(R.id.playButton);
        playBtn.setOnClickListener(this);
        pauseBtn = (ImageView) findViewById(R.id.pauseButton);
        pauseBtn.setOnClickListener(this);
        stopBtn = (ImageView) findViewById(R.id.stopButton);
        stopBtn.setOnClickListener(this);
        nextBtn = (ImageView) findViewById(R.id.nextButton);
        nextBtn.setOnClickListener(this);
        backBtn = (ImageView) findViewById(R.id.backButton);
        backBtn.setOnClickListener(this);
        trackListBtn = (ImageView) findViewById(R.id.trackList);
        trackListBtn.setOnClickListener(this);
        muteBtn = (ImageView) findViewById(R.id.muteButton);
        muteBtn.setOnClickListener(this);
        textView = (TextView) findViewById(R.id.textViewSearch);
        songNameTextView = (TextView) findViewById(R.id.songName);
        Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/DIGITAL.TTF");
        textView.setTypeface(typeFace);
        songNameTextView.setTypeface(typeFace);
        ConnectionController.automaticConnection();
    }

	@Override
	public void onClick(View view) {
		switch(view.getId()){
			case R.id.playButton:
				ConnectionController.request("PLAY");
				break;
			case R.id.backButton:
				ConnectionController.request("BACK");
				break;
			case R.id.nextButton:
				ConnectionController.request("NEXT");
				break;
			case R.id.pauseButton:
				ConnectionController.request("PAUSE");
				break;
			case R.id.stopButton:
				ConnectionController.request("STOP");
				break;
			case R.id.muteButton:
				ConnectionController.request("MUTE");
				break;
			case R.id.trackList:
				Intent intent = new Intent(this, Search.class);
				startActivity(intent);
				break;
		
		}
		
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
	}

	@Override
	protected void onPostResume() {
		// TODO Auto-generated method stub
		super.onPostResume();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	
	
	
	
    
}
