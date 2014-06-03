package shiftscope.model;

public class Song {
	private int idSong;
	private String name;
	private String artist;
	private String composer;
	/**
	 * @return the idSong
	 */
	public int getIdSong() {
		return idSong;
	}
	/**
	 * @param idSong the idSong to set
	 */
	public void setIdSong(int idSong) {
		this.idSong = idSong;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}
	/**
	 * @param artist the artist to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}
	/**
	 * @return the composer
	 */
	public String getComposer() {
		return composer;
	}
	/**
	 * @param composer the composer to set
	 */
	public void setComposer(String composer) {
		this.composer = composer;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name + ", " + artist + ", " + composer;
	}
	
	
	
}
