package shiftscope.utils;

import java.util.ArrayList;

import shiftscope.main.R;
import shiftscope.model.Song;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SongAdapter extends ArrayAdapter<Song>{
	
	private Context c;
	private ArrayList<Song> songs;
	
	public SongAdapter(Context c, ArrayList<Song> songs){
		super(c,R.layout.single_row, R.id.title, songs);
		this.c = c;
		this.songs = songs;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row = inflater.inflate(R.layout.single_row, parent, false);
		Song song = songs.get(position);
		System.out.println(song);
		TextView title = (TextView) row.findViewById(R.id.title);
		title.setText(song.getName());
		TextView artist = (TextView) row.findViewById(R.id.artist);
		artist.setText(song.getArtist());
		ImageView image = (ImageView) row.findViewById(R.id.image);
		image.setImageResource(R.drawable.ic_launcher);
		
		return row;
	}
}
