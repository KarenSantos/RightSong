package rightsong.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Chord sheet class.
 * 
 * @author Karen Santos
 * @author Jolayemioluwa Ilori
 * @version November 2014
 *
 */
public class ChordSheet implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private MusicalNote key;
	private Map<String, int[][]> chords;
	private User user;

	/**
	 * Creates a chord sheet with an ID, a musical key, a map of chords and the
	 * user who uploaded the chord sheet.
	 * 
	 * @param id
	 *            The ID of the chord sheet.
	 * @param key
	 *            The musical key of the chord sheet.
	 * @param chords
	 *            The map of chords of the chord sheet.
	 * @param user
	 *            The user who uploaded the chord sheet.
	 */
	public ChordSheet(String id, MusicalNote key, Map<String, int[][]> chords,
			User user) {
		this.id = id;
		this.key = key;
		this.chords = new HashMap<String, int[][]>();
		this.user = user;
	}

	/**
	 * Returns the ID of the chord sheet.
	 * 
	 * @return The ID of the chord sheet.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets a new ID for the chord sheet.
	 * 
	 * @param chordSheetID
	 *            The new ID of the chord sheet.
	 */
	public void setId(String chordSheetID) {
		this.id = chordSheetID;
	}

	/**
	 * Returns the musical key of the chord sheet.
	 * 
	 * @return The musical key of the chord sheet.
	 */
	public MusicalNote getKey() {
		return key;
	}

	/**
	 * Sets a new musical key for the chord sheet.
	 * 
	 * @param key
	 *            The new musical key of the chord sheet.
	 */
	public void setKey(MusicalNote key) {
		this.key = key;
	}

	/**
	 * Returns the map of chords of the chord sheet.
	 * 
	 * @return The map of chords of the chord sheet.
	 */
	public Map<String, int[][]> getChords() {
		return chords;
	}

	/**
	 * Sets a new map of chords for the chord sheet.
	 * 
	 * @param chords
	 *            The new map of chords of the chord sheet.
	 */
	public void setChords(Map<String, int[][]> chords) {
		this.chords = chords;
	}

	/**
	 * Returns the user who uploaded the chord sheet.
	 * 
	 * @return The user who uploaded the chord sheet.
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the new user who uploaded the chord sheet.
	 * 
	 * @param user
	 *            The new user who uploaded the chord sheet.
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Returns the serialVersionUID number of the chord sheet.
	 * 
	 * @return The serialVersionUID number of the chord sheet.
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * A chord sheet is equal to another is it has the same ID.
	 */
	@Override
	public boolean equals(Object obj) {
		boolean answer = false;
		if (obj instanceof ChordSheet) {
			if (getId().equals(((ChordSheet) obj).getId())) {
				answer = true;
			}
		}
		return answer;
	}

	/**
	 * Returns a string with the key of the chord sheet and the user who upload
	 * it.
	 */
	@Override
	public String toString() {
		return "Chord sheet in " + getKey() + ". Uploaded by: " + getUser();
	}

}
