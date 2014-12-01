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
	 * Returns all the data from the domain model stored in the server
	 * controller.
	 * 
	 * @param email
	 *            The email of the user the data will be from.
	 * @return All the data stored in this server controller.
	 */
	public List<List<?>> getData(String email) {
		List<List<?>> data = new ArrayList<List<?>>();

		data.add(getSongs());
		data.add(getTags());
		data.add(getGenres());
		data.add(getUserRepertories(email));
		data.add(getArtists());
		data.add(getChordSheets());
		data.add(getChords());
		return data;
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
	 * Adds a song to a users and the system database.
	 * 
	 * @param email
	 *            The email of the user the song will be added to.
	 * @param song
	 *            The song to be added.
	 * @return True if the song was added.
	 */
	public boolean addSong(String email, Song song) {
		String id = "song" + getSongs().size() + 1;
		Song newSong = new Song(id, song.getTitle(), song.getLyrics(),
				song.getSpeed());

		if (!song.getArtists().isEmpty()) {
			for (Artist a : song.getArtists()) {
				newSong.addArtist(createArtist(a.getName()));
			}
		}

		for (Genre g : song.getGenres()) {
			newSong.addGenre(g);
		}
		for (Tag t : song.getTags()) {
			newSong.addTag(t);
		}

		User user = getUserByEmail(email);
		user.addSong(newSong);
		songs.add(newSong);
		return true;
	}

	/**
	 * Returns all the tags from this server controller.
	 * 
	 * @return All the tags from this server controller.
	 */
	public List<Tag> getTags() {
		return tags;
	}

	/**
	 * Creates a new tag.
	 * 
	 * @param name
	 *            The name of the tag to be created.
	 * @return The tag that was created or that already existed with the same
	 *         name.
	 */
	public Tag createTag(String name) {
		Tag tag = null;
		for (Tag t : getTags()) {
			if (t.getName().equals(name)) {
				tag = t;
				break;
			}
		}
		if (tag == null) {
			String id = "tag" + getTags().size() + 1;
			tag = new Tag(id, name);
		}
		tags.add(tag);
		return tag;
	}

	/**
	 * Adds a tag to the song of the specified user.
	 * 
	 * @param user
	 *            The user that uploaded the song.
	 * @param song
	 *            The song uploaded by this user.
	 * @param tag
	 *            The tag to be added to the song.
	 */
	public void addTagToSong(User user, Song song, Tag tag) {
		user.addTagToSong(song, tag);
	}

	/**
	 * Returns all the genres from this server controller.
	 * 
	 * @return All the genres from this server controller.
	 */
	public List<Genre> getGenres() {
		return genres;
	}

	/**
	 * Creates a new genre.
	 * 
	 * @param name
	 *            The name of the genre to be created.
	 * @return The genre that was created or that already existed with the same
	 *         name.
	 */
	public Genre createGenre(String name) {
		Genre genre = null;
		for (Genre g : getGenres()) {
			if (g.getName().equals(name)) {
				genre = g;
				break;
			}
		}
		if (genre == null) {
			String id = "genre" + getGenres().size() + 1;
			genre = new Genre(id, name);
		}
		genres.add(genre);
		return genre;
	}

	/**
	 * Adds a genre to the song of the specified user.
	 * 
	 * @param user
	 *            The user that uploaded the song.
	 * @param song
	 *            The song uploaded by this user.
	 * @param genre
	 *            The genre to be added to the song.
	 */
	public void addGenreToSong(User user, Song song, Genre genre) {
		user.addGenreToSong(song, genre);
	}

	/**
	 * Returns all the repertories from this server controller.
	 * 
	 * @return All the repertories from this server controller.
	 */
	public List<Repertory> getRepertories() {
		return repertories;
	}

	/**
	 * Returns all the repertories from the user with the specified email.
	 * 
	 * @return All the repertories from the user with the specified email.
	 */
	public List<Repertory> getUserRepertories(String email) {
		return getUserByEmail(email).getRepertories();
	}

	/**
	 * Returns all the artists from this server controller.
	 * 
	 * @return All the artists from this server controller.
	 */
	public List<Artist> getArtists() {
		return artists;
	}

	/**
	 * Creates a new artist.
	 * 
	 * @param name
	 *            The name of the artist to be created.
	 * @return The artist that was created or that already existed with the same
	 *         name.
	 */
	public Artist createArtist(String name) {
		Artist artist = null;
		for (Artist a : getArtists()) {
			if (a.getName().equals(name)) {
				artist = a;
				break;
			}
		}
		if (artist == null) {
			String id = "artist" + getArtists().size() + 1;
			artist = new Artist(id, name);
		}
		artists.add(artist);
		return artist;
	}

	/**
	 * Returns all the chord sheets from this server controller.
	 * 
	 * @return All the chord sheets from this server controller.
	 */
	public List<ChordSheet> getChordSheets() {
		return chordSheets;
	}

	/**
	 * Returns all the chords from this server controller.
	 * 
	 * @return All the chords from this server controller.
	 */
	public List<Chord> getChords() {
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
		if (user != null) {
			if (user.getPassword().equals(password)) {
				answer = true;
			}
		}
		return answer;
	}

	/**
	 * Returns if the email is the administrator's email.
	 * 
	 * @param email
	 *            The email to be checked.
	 * @return True if the email is the administrator's email.
	 */
	public boolean isAdmin(String email) {
		return email.equals(admin.getEmail());
	}

	/**
	 * Initializes a sample database.
	 */
	private void initializeSampleDataBase() {

		String email = "firstUser@email.com";
		addUser(email, "FirstUser", "1234");

		List<String> lyrics = new ArrayList<String>();
		lyrics.add("First line of the song");
		lyrics.add("This will be the second line of music");

		Song song = new Song("id", "My First Song", lyrics, SongSpeed.MODERATE);

		song.addTag(createTag("Romantic"));
		song.addTag(createTag("Chill"));
		song.addTag(createTag("Instrumental"));
		song.addTag(createTag("Happy"));

		song.addGenre(createGenre("Rock"));
		song.addGenre(createGenre("Jazz"));

		addSong(email, song);
	}
}
