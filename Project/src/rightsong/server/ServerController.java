package rightsong.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rightsong.model.*;

/**
 * Controller class for the RightSong server.
 * 
 * @author karensantos
 * @version November 2014
 *
 */
public class ServerController {

	private User admin;

	private List<User> users;
	private List<Song> songs;
	private List<Tag> tags;
	private List<Genre> genres;
	private List<Repertory> repertories;
	private List<Artist> artists;
	private List<ChordSheet> chordSheets;
	private List<Chord> chords;

	/**
	 * Creates a controller with a list of users, songs, repertories, artists,
	 * chordSheets and chords.
	 */
	public ServerController() {

		users = new ArrayList<User>();
		songs = new ArrayList<Song>();
		tags = new ArrayList<Tag>();
		genres = new ArrayList<Genre>();
		repertories = new ArrayList<Repertory>();
		artists = new ArrayList<Artist>();
		chordSheets = new ArrayList<ChordSheet>();
		chords = new ArrayList<Chord>();
		
		admin = addUser("admin", "admin", "admin");

		initializeSampleDataBase();
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
	public User addUser(String email, String username, String password) {
		String id = "user" + getUsers().size() + 1;
		User user = new User(id, username, email, password);
		users.add(user);
		return user;
	}

	/**
	 * Returns a user by its email.
	 * 
	 * @param email
	 *            The email of the user.
	 * @return The user that has the email or null if there is none.
	 */
	public User getUserByEmail(String email) {
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
	 * Returns a user by its username.
	 * 
	 * @param username
	 *            The username of the user.
	 * @return The user that has the username or null if there is none.
	 */
	public User getUserByUsername(String username) {
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
	 * Returns the list of songs of the system.
	 * 
	 * @return The list of songs of the system.
	 */
	public List<Song> getSongs() {
		Collections.sort(songs);
		return songs;
	}

	/**
	 * Adds a new song to the user and the system.
	 * 
	 * @param user
	 *            The user who uploaded the song.
	 * @param title
	 *            The title of the song.
	 * @param lyrics
	 *            The lyrics of the song.
	 * @param speed
	 *            The speed of the song.
	 * @return The song added by the user.
	 */
	public Song addSong(User user, String title, List<String> lyrics,
			SongSpeed speed) {
		String id = "song" + getSongs().size() + 1;
		Song song = new Song(id, title, lyrics, speed);
		user.addSong(song);
		songs.add(song);
		return song;

	}

	public List<Tag> getTags() {
		return tags;
	}

	public void addTagToSong(User user, Song song, Tag tag) {
		user.addTagToSong(song, tag);
	}

	public void addTagToSong(User user, Song song, String tagName) {
		// TODO check if the tag already exists
		String id = "tag" + getTags().size() + 1;
		Tag tag = new Tag(id, tagName);
		user.addTagToSong(song, tag);
		tags.add(tag);
	}
	
	public List<Genre> getGenres(){
		return genres;
	}

	public void addGenreToSong(User user, Song song, String genreName) {
		// TODO check if the tag already exists
		String id = "genre" + getGenres().size() + 1;
		Genre genre = new Genre(id, genreName);
		user.addGenreToSong(song, genre);
		genres.add(genre);
	}
	
	public List<Repertory> getRepertories(){
		return repertories;
	}
	
	public List<Repertory> getUserRepertories(String email){
		return getUserByEmail(email).getRepertories();
	}
	
	public List<Artist> getArtists(){
		return artists;
	}
	
	public List<ChordSheet> getChordSheets(){
		return chordSheets;
	}
	
	public List<Chord> getChords(){
		return chords;
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
		if (getUserByEmail(email) != null) {
			answer = "email";
		} else if (getUserByUsername(username) != null) {
			answer = "username";
		}
		return answer;
	}

	/**
	 * Returns if there is a user with this email and password.
	 * 
	 * @param email
	 *            The email of the user.
	 * @param password
	 *            The password of the user.
	 * @return True if the user exists, false otherwise.
	 */
	public boolean isUser(String email, String password) {
		boolean answer = false;
		User user = getUserByEmail(email);
		if (user != null){
			if (user.getPassword().equals(password)) {
				answer = true;
			}
		}
		return answer;
	}
	
	public boolean isAdmin(String email){
		return email.equals(admin.getEmail());
	}

	private void initializeSampleDataBase() {

		User user = addUser("firstUser@email.com", "FirstUser", "1234");

		String[] tagNames = new String[] { "Romantic", "Chill", "Instrumental",
				"Happy" };
		String[] genre = new String[] {"Rock", "Jazz" };
		
		List<String> lyrics = new ArrayList<String>();
		lyrics.add("First line of the song");
		lyrics.add("This will be the second line of music");
		
		Song song = addSong(user, "My First Song bla balbla bla", lyrics, SongSpeed.MODERATE);

		for (String tagName : tagNames) {
			addTagToSong(user, song, tagName);
		}
		
		for (String genreName : genre){
			addGenreToSong(user, song, genreName);
		}

	}
}
