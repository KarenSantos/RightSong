package rightsong.model;

import java.io.Serializable;
import java.util.List;

/**
 * Artist class.
 * 
 * @author Karen Santos
 * @version November 2014
 *
 */
public class Artist implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private List<Song> songs;

	/**
	 * Creates a new artist with an ID and a name.
	 * 
	 * @param id
	 *            The id of the artist.
	 * @param name
	 *            The name of the artist.
	 */
	public Artist(String id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * Returns the ID of the artist.
	 * 
	 * @return The ID of the artist.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets a new ID for the artist.
	 * 
	 * @param id
	 *            The new ID of the artist.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Returns the name of the artist.
	 * 
	 * @return The name of the artist.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets a new name for the artist.
	 * 
	 * @param name
	 *            The new name of the artist.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the songs by this artist.
	 * 
	 * @return The songs by this artist.
	 */
	public List<Song> getSongs() {
		return songs;
	}

	/**
	 * Adds a song to the list of songs by this artist.
	 * 
	 * @param song
	 *            The song to be added to the list of songs.
	 */
	public void addSong(Song song) {
		if (!songs.contains(song)) {
			songs.add(song);
		}
	}
	
	/**
	 * Removes a song from the list of songs by this artist.
	 * 
	 * @param song
	 *            The song to be removed from the list of songs.
	 */
	public void removeSong(Song song) {
		if (songs.contains(song)) {
			songs.remove(song);
		}
	}

	/**
	 * Returns the serialVersionUID number of the artist.
	 * 
	 * @return The serialVersionUID number of the artist.
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * An artist is equal to another if the artist ID is the same.
	 */
	@Override
	public boolean equals(Object obj) {
		boolean answer = false;
		if (obj instanceof Artist) {
			if (getId().equals(((Artist) obj).getId())) {
				answer = true;
			}
		}
		return answer;
	}

	/**
	 * Artist toString containing the artist name.
	 */
	@Override
	public String toString() {
		return getName();
	}

}