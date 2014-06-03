package shiftscope.views;

import shiftscope.controllers.ConnectionController;
import shiftscope.controllers.SongController;
import shiftscope.main.R;
import shiftscope.utils.SongAdapter;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class Search extends Activity implements OnItemClickListener {

	private static ListView listView;

	public static Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
		}
	};

	private class UIUpdater extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... arg0) {
			ConnectionController.requestTrackList();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			SongAdapter adapter = new SongAdapter(
					Search.this.getApplicationContext(),
					SongController.getAllSongs());
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(Search.this);

		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_search);
		listView = (ListView) findViewById(R.id.listView);
		new UIUpdater().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
		View v = view;
		TextView textView = (TextView) v.findViewById(R.id.title);
		ConnectionController
				.request("PLAY:"
						+ SongController.getSongIdByName(textView.getText()
								.toString()));
	}

}
