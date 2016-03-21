package rightsong.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User class.
 * 
 * @author Karen Santos
 * @author Jolayemioluwa Ilori
 * @version November 2014
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String username;
	private String email;
	private String password;
	private UserType type;

	private List<Song> songs;
	private List<ChordSheet> chordSheets;
	private List<Repertory> repertories;

	/**
	 * Creates a new user with an ID, a username, an email and a password. A
	 * user can also have a list of songs and a list of chords that he has
	 * uploaded, and a list of repertories.
	 * 
	 * @param id
	 *            The ID of the user.
	 * @param username
	 *            The username of the user.
	 * @param email
	 *            The email of the user.
	 * @param password
	 *            The password of the user.
	 */
	public User(String id, String username, String email, String password) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;

		this.type = UserType.REGULAR;
		this.songs = new ArrayList<Song>();
		this.chordSheets = new ArrayList<ChordSheet>();
		this.repertories = new ArrayList<Repertory>();

	}

	/**
	 * Returns the ID of the user.
	 * 
	 * @return The ID of the user.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets a new ID for the user.
	 * 
	 * @param id
	 *            The new ID of the user.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Returns the username of the user.
	 * 
	 * @return The username of the user.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets a new username for the user.
	 * 
	 * @param name
	 *            The new username of the user.
	 */
	public void setName(String username) {
		this.username = username;
	}

	/**
	 * Returns the email of the user.
	 * 
	 * @return The email of the user.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets a new email for the user.
	 * 
	 * @param email
	 *            The new email of the user.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Returns the password of the user.
	 * 
	 * @return The password of the user.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets a new password for the user.
	 * 
	 * @param password
	 *            The new password of the user.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Returns the type of the user.
	 * 
	 * @return The type of the user.
	 */
	public UserType getType() {
		return type;
	}

	/**
	 * Sets a new type for the user.
	 * 
	 * @param type
	 *            The new type of the user.
	 */
	public void setType(UserType type) {
		this.type = type;
	}

	/**
	 * Returns the list of songs uploaded by the user.
	 * 
	 * @return The list of songs uploaded by the user.
	 */
	public List<Song> getSongs() {
		return songs;
	}

	/**
	 * Adds a song to the list of songs of the user.
	 * 
	 * @param song
	 *            The song to be added to the list of songs.
	 */
	public void addSong(Song song) {
		if (!songs.contains(song)) {
			songs.add(song);
			song.setUser(this);
		}
	}

	/**
	 * Adds a tag to the song uploaded by this user.
	 * 
	 * @param song
	 *            The song the tag will be added to.
	 * @param tag
	 *            The tag to be added to the song.
	 */
	public void addTagToSong(Song song, Tag tag) {
		if (isMySong(song) || getEmail().equals("admin")) {
			song.addTag(tag);
			tag.addSong(song);
		}
	}

	/**
	 * Deletes a tag from the song uploaded by this user.
	 * 
	 * @param song
	 *            The song the tag will be deleted from.
	 * @param tag
	 *            The tag to be removed from the song tags.
	 */
	public void deleteTagFromSong(Song song, Tag tag) {
		if (isMySong(song)) {
			song.deleteTag(tag);
			tag.deleteSong(song);
		}
	}

	/**
	 * Adds a genre to the song uploaded by this user.
	 * 
	 * @param song
	 *            The song the genre will be added to.
	 * @param genre
	 *            The genre to be added to the song.
	 */
	public void addGenreToSong(Song song, Genre genre) {
		if (isMySong(song) || getEmail().equals("admin")) {
			song.addGenre(genre);
			genre.addSong(song);
		}
	}

	/**
	 * Returns the list of chord sheets uploaded by this user.
	 * 
	 * @return The list of chord sheets uploaded by this user.
	 */
	public List<ChordSheet> getChordSheets() {
		return chordSheets;
	}

	/**
	 * Adds a chord sheet to the list of chord sheets of the user.
	 * 
	 * @param chordSheet
	 *            The chord sheet to be added.
	 */
	public void addChordSheet(ChordSheet chordSheet) {
		if (!chordSheets.contains(chordSheet)) {
			chordSheets.add(chordSheet);
		}
	}

	/**
	 * Deletes a chord sheet from the list of chord sheets of the user.
	 * 
	 * @param chordSheet
	 *            The chord sheet to be deleted.
	 */
	public void deleteChordSheet(ChordSheet chordSheet) {
		if (chordSheets.contains(chordSheet)) {
			chordSheets.remove(chordSheet);
		}
	}

	/**
	 * Returns the list of repertories of the user.
	 * 
	 * @return The list of repertories of the user.
	 */
	public List<Repertory> getRepertories() {
		return repertories;
	}

	/**
	 * Adds a repertory to the list of repertories of the user.
	 * 
	 * @param repertory
	 *            The repertory to be added.
	 */
	public void addRepertory(Repertory repertory) {
		if (!repertories.contains(repertory)) {
			repertories.add(repertory);
		}
	}

	/**
	 * Deletes a repertory from the list of repertories of the user.
	 * 
	 * @param repertory
	 *            The repertory to be deleted.
	 */
	public void deleteRepertory(Repertory repertory) {
		if (repertories.contains(repertory)) {
			repertories.remove(repertory);
		}
	}

	/**
	 * Returns if a song was uploaded by the user or not.
	 * 
	 * @param song
	 *            The song to be checked.
	 * @return True if the song was uploaded by the user, false otherwise.
	 */
	public boolean isMySong(Song song) {
		boolean answer = false;
		if (getSongs().contains(song)) {
			answer = true;
		}
		return answer;
	}

	/**
	 * Returns the serialVersionUID number of the user.
	 * 
	 * @return The serialVersionUID number of the user.
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * A user is equal to another if it has the same ID.
	 */
	@Override
	public boolean equals(Object obj) {
		boolean answer = false;
		if (obj instanceof User) {
			if (getId().equals(((User) obj).getId())) {
				answer = true;
			}
		}
		return answer;
	}

	/**
	 * Returns a string with the username of the user.
	 */
	@Override
	public String toString() {
		return getUsername();
	}

}
