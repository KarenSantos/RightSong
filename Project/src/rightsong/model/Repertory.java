package rightsong.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Repertory class.
 * 
 * @author Karen Santos
 * @author Jolayemioluwa Ilori
 * @version November 2014
 *
 */
public class Repertory implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String eventName;
	private Calendar date;
	private String description;
	private List<Song> songs;

	/**
	 * Creates a new repertory with an ID, an event name, a date and a
	 * description. A repertory also has a list of songs that can be added or
	 * deleted.
	 * 
	 * @param id
	 *            The ID of the repertory.
	 * @param event
	 *            The name of the repertory event.
	 * @param date
	 *            The date of the repertory event.
	 * @param description
	 *            The description of the repertory.
	 */
	public Repertory(String id, String event, Calendar date, String description) {
		this.id = id;
		this.eventName = event;
		this.date = date;
		this.description = description;
		this.songs = new ArrayList<Song>();
	}

	/**
	 * Returns the ID of the repertory.
	 * 
	 * @return The ID of the repertory.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets a new ID for the repertory.
	 * 
	 * @param repertoryID
	 *            The new ID of the repertory.
	 */
	public void setId(String repertoryID) {
		this.id = repertoryID;
	}

	/**
	 * Returns the name of the repertory event.
	 * 
	 * @return The name of the repertory event.
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * Sets a new name for the repertory event.
	 * 
	 * @param eventName
	 *            The new name of the repertory event.
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	/**
	 * Returns the date of the repertory.
	 * 
	 * @return The date of the repertory.
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * Sets a new date for the repertory.
	 * 
	 * @param date
	 *            The new date of the repertory.
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}

	/**
	 * Returns the description of the repertory.
	 * 
	 * @return The description of the repertory.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets a new description for the repertory.
	 * 
	 * @param description
	 *            The new description of the repertory.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the list of songs of the repertory.
	 * 
	 * @return The list of songs of the repertory.
	 */
	public List<Song> getSongs() {
		return songs;
	}

	/**
	 * Adds a song to the list of songs of the repertory.
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
	 * Deletes a song from the list of songs of the repertory.
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
	 * Returns the serialVersionUID number of the repertory.
	 * 
	 * @return The serialVersionUID number of the repertory.
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * A repertory is equal to another if it has the same ID.
	 */
	@Override
	public boolean equals(Object obj) {
		boolean answer = false;
		if (obj instanceof Repertory) {
			if (getId().equals(((Repertory) obj).getId())) {
				answer = true;
			}
		}
		return answer;
	}

	/**
	 * Returns a string with the repertory date and name.
	 */
	@Override
	public String toString() {
		return getDate() + " - " + getEventName();
	}
}
