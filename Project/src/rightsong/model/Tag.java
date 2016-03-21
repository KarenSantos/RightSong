package rightsong.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Tag class.
 * 
 * @author Karen Santos
 * @author Jolayemioluwa Ilori
 * @version November 2014
 *
 */
public class Tag implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private List<Song> songs;

	/**
	 * Creates a new tag with an ID and a name.
	 * 
	 * @param id
	 *            The id of the tag.
	 * @param name
	 *            The name of the tag.
	 */
	public Tag(String id, String name) {
		this.id = id;
		this.name = name;
		this.songs = new ArrayList<Song>();
	}

	/**
	 * Returns the ID of the tag.
	 * 
	 * @return The ID of the tag.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets a new ID for the tag.
	 * 
	 * @param tagID
	 *            The new ID of the tag.
	 */
	public void setId(String tagID) {
		this.id = tagID;
	}

	/**
	 * Returns the name of the tag.
	 * 
	 * @return The name of the tag.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets a new name for the tag.
	 * 
	 * @param name
	 *            The new name of the tag.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the list of songs that have this tag.
	 * 
	 * @return The list of songs that have this tag.
	 */
	public List<Song> getSongs() {
		return songs;
	}

	/**
	 * Adds a new song to the list of songs that have this tag.
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
	 * Deletes a song from the list of songs that have this tag.
	 * 
	 * @param song
	 *            The song to be deleted from the list of songs.
	 */
	public void deleteSong(Song song) {
		if (songs.contains(song)) {
			songs.remove(song);
		}
	}

	/**
	 * Returns the serialVersionUID number of the tag.
	 * 
	 * @return The serialVersionUID number of the tag.
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * A tag is equal to another if it has the same ID.
	 */
	@Override
	public boolean equals(Object obj) {
		boolean answer = false;
		if (obj instanceof Tag) {
			if (getId().equals(((Tag) obj).getId())) {
				answer = true;
			}
		}
		return answer;
	}

	/**
	 * Returns a string with the name of the tag.
	 */
	@Override
	public String toString() {
		return getName();
	}

}
