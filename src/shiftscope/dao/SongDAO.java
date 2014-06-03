package shiftscope.dao;
import java.util.ArrayList;

import shiftscope.model.Song;

public class SongDAO {
	private static ArrayList<Song> songs;

	public static void addSong(Song newSong){
		songs.add(newSong);
	}
	
	public static int getSongIdByName(String name){
		for (Song s : songs){
			if (name.equals(s.getName())){
				return s.getIdSong();
			}
		}
		return -1;
	}
	/**
	 * @return the songs
	 */
	public static ArrayList<Song> getSongs() {
		return songs;
	}
	
	public static void init(){
		songs = new ArrayList<Song>();
	}
	
	
}
