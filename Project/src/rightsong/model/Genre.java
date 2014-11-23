package rightsong.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Genre class.
 * 
 * @author Karen Santos
 * @version November 2014
 *
 */
public class Genre implements Serializable {

	private static final long serialVersionUID = 1L;

	private String genreID;
	private String name;
	private List<Song> songs;

	/**
	 * Creates a new genre with an ID and a name.
	 * 
	 * @param id
	 *            The id of the genre.
	 * @param name
	 *            The name of the genre.
	 */
	public Genre(String id, String name) {
		this.genreID = id;
		this.name = name;
		this.songs = new ArrayList<Song>();
	}

	/**
	 * Returns the ID of the genre.
	 * 
	 * @return The ID of the genre.
	 */
	public String getGenreID() {
		return genreID;
	}

	/**
	 * Sets a new ID for the genre.
	 * 
	 * @param genreID
	 *            The new ID of the genre.
	 */
	public void setTagID(String genreID) {
		this.genreID = genreID;
	}

	/**
	 * Returns the name of the genre.
	 * 
	 * @return The name of the genre.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets a new name for the genre.
	 * 
	 * @param name
	 *            The new name of the genre.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the list of songs that are from this genre.
	 * 
	 * @return The list of songs that are from this genre.
	 */
	public List<Song> getSongs() {
		return songs;
	}

	/**
	 * Adds a new song to the list of songs that are from this genre.
	 * 
	 * @param song
	 *            The new song to be added to the list of songs.
	 */
	public void addSong(Song song) {
		if (!songs.contains(song)) {
			songs.add(song);
		}
	}

	/**
	 * Deletes a song from the list of songs that are from this genre.
	 * 
	 * @param song
	 *            The song to be deleted from the list of songs.
	 */
	public void removeSong(Song song) {
		if (songs.contains(song)) {
			songs.remove(song);
		}
	}

	/**
	 * Returns the serialVersionUID number of the genre.
	 * 
	 * @return The serialVersionUID number of the genre.
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * A genre is equal to another if it has the same ID.
	 */
	@Override
	public boolean equals(Object obj) {
		boolean answer = false;
		if (obj instanceof Genre) {
			if (getGenreID().equals(((Genre) obj).getGenreID())) {
				answer = true;
			}
		}
		return answer;
	}

	/**
	 * Returns a string with the name of the genre.
	 */
	@Override
	public String toString() {
		return "Genre: " + getName();
	}

}
