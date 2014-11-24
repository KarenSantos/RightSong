package rightsong.controller;

import java.util.ArrayList;
import java.util.List;

import rightsong.model.*;

/**
 * Controller class for the RightSong server.
 * 
 * @author karensantos
 * @version November 2014
 *
 */
public class Controller {

	private List<User> users;
	private List<Song> songs;
	private List<Repertory> repertories;
	private List<Artist> artists;
	private List<ChordSheet> chordSheets;
	private List<Chord> chords;

	/**
	 * Creates a controller with a list of users, songs, repertories, artists,
	 * chordSheets and chords.
	 */
	public Controller() {

		users = new ArrayList<User>();
		songs = new ArrayList<Song>();
		repertories = new ArrayList<Repertory>();
		artists = new ArrayList<Artist>();
		chordSheets = new ArrayList<ChordSheet>();
		chords = new ArrayList<Chord>();

	}

	/**
	 * Returns the users registered in the system.
	 * 
	 * @return The users registered in the system.
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * Adds a new user to the list of users of the system.
	 * 
	 * @param email
	 *            The email of the new user.
	 * @param username
	 *            The username of the new user.
	 * @param password
	 *            The password of the new user.
	 */
	public void addUser(String email, String username, String password) {
		String id = "user" + getUsers().size() + 1;
		User user = new User(id, username, email, password);
		users.add(user);
	}

	/**
	 * Finds a user by its email.
	 * 
	 * @param email
	 *            The email of the user.
	 * @return The user that has the email.
	 */
	public User findUserByEmail(String email) {
		User user = null;
		for (User u : users) {
			if (u.getEmail().equals(email)) {
				user = u;
				break;
			}
		}
		return user;
	}

	/**
	 * Finds a user by its username.
	 * 
	 * @param username
	 *            The username of the user.
	 * @return The user that has the username.
	 */
	public User findUserByUsername(String username) {
		User user = null;
		for (User u : users) {
			if (u.getUsername().equals(username)) {
				user = u;
				break;
			}
		}
		return user;
	}

	/**
	 * Returns if the email or username given are already registered by a user.
	 * 
	 * @param email
	 *            The email to be checked.
	 * @param username
	 *            The username to be checked.
	 * @return email if the email is already registered, username if the
	 *         username is already registered, or an empty string otherwise.
	 */
	public String isRegistered(String email, String username) {
		String answer = "";
		if (findUserByEmail(email) != null) {
			answer = "email";
		} else if (findUserByUsername(username) != null) {
			answer = "username";
		}
		return answer;
	}

}
