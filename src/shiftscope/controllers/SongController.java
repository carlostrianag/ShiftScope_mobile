package shiftscope.controllers;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import shiftscope.dao.SongDAO;
import shiftscope.model.Song;

public class SongController {
	
	
	private static void create(Song newSong){
			SongDAO.addSong(newSong);
	}
	
	public static ArrayList<String> parseJSON(JSONArray array){
		ArrayList<String> songNames = new ArrayList<String>();
		for (int i = 0; i < array.length(); i++) {
		    try {
				JSONObject object = array.getJSONObject(i);
				Song newSong = new Song();
				newSong.setIdSong(Integer.parseInt(object.getString("idSong")));
				newSong.setArtist(object.getString("artist"));
				newSong.setComposer(object.getString("composer"));
				newSong.setName(object.getString("name"));
				songNames.add(newSong.getName());
				create(newSong);
			} catch (JSONException e) {
				e.printStackTrace();
			}		    
		}
		return songNames;
	}
	
	public static ArrayList<Song> getAllSongs(){
		return SongDAO.getSongs();
	}
	
	public static int getSongIdByName(String name){
		return SongDAO.getSongIdByName(name);
		
	}
	
	public static void init(){
		SongDAO.init();
	}
}
