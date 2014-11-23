package rightsong.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Song class.
 * 
 * @author Karen Santos
 * @version November 2014
 *
 */
public class Song implements Serializable {

	private static final long serialVersionUID = 1L;

	private String songID;
	private String title;
	private List<String> lyrics;
	private SongSpeed speed;
	private User user;

	private List<Artist> artists;
	private List<ChordSheet> chordSheets;
	private String youtubeLink;
	private List<Tag> tags;
	private List<Genre> genres;

	/**
	 * Creates a new song with an ID, a title, lyrics, a speed and the user who
	 * uploaded the song. A song can also have a list of artists, chord sheets,
	 * tags and genres, and a youtube link.
	 * 
	 * @param songID
	 *            The ID of the song.
	 * @param title
	 *            The title of the song.
	 * @param lyrics
	 *            The lyrics of the song.
	 * @param speed
	 *            The speed of the song.
	 * @param user
	 *            The user who uploaded the song.
	 */
	public Song(String songID, String title, List<String> lyrics,
			SongSpeed speed, User user) {
		this.songID = songID;
		this.title = title;
		this.lyrics = lyrics;
		this.speed = speed;
		this.user = user;

		this.artists = new ArrayList<Artist>();
		this.chordSheets = new ArrayList<ChordSheet>();
		this.youtubeLink = "no link available";
		this.tags = new ArrayList<Tag>();
		this.genres = new ArrayList<Genre>();
	}

	/**
	 * Returns the ID of the song.
	 * 
	 * @return The ID of the song.
	 */
	public String getSongID() {
		return songID;
	}

	/**
	 * Sets a new ID for the song.
	 * 
	 * @param songID
	 *            The new ID of the song.
	 */
	public void setSongID(String songID) {
		this.songID = songID;
	}

	/**
	 * Returns the title of the song.
	 * 
	 * @return The title of the song.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets a new title for the song.
	 * 
	 * @param title
	 *            The new title of the song.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Returns the lyrics of the song.
	 * 
	 * @return The lyrics of the song.
	 */
	public List<String> getLyrics() {
		return lyrics;
	}

	/**
	 * Sets new lyrics for the song.
	 * 
	 * @param lyric
	 *            The new lyrics of the song.
	 */
	public void setLyrics(List<String> lyric) {
		this.lyrics = lyric;
	}

	/**
	 * Returns the speed of the song.
	 * 
	 * @return The speed of the song.
	 */
	public SongSpeed getSpeed() {
		return speed;
	}

	/**
	 * Sets a new speed for the song.
	 * 
	 * @param speed
	 *            The new speed of the song.
	 */
	public void setSpeed(SongSpeed speed) {
		this.speed = speed;
	}

	/**
	 * Returns the user who uploaded the song.
	 * 
	 * @return The user who uploaded the song.
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the new user who uploaded the song.
	 * 
	 * @param user
	 *            The new user who uploaded the song.
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Returns the list of artists of the song.
	 * 
	 * @return The list of artists of the song.
	 */
	public List<Artist> getArtists() {
		return artists;
	}

	/**
	 * Adds a new artist to the list of artists of the song.
	 * 
	 * @param artist
	 *            The new artist to be added to the list of artists.
	 */
	public void addArtist(Artist artist) {
		if (!artists.contains(artist)) {
			artists.add(artist);
		}
	}

	/**
	 * Deletes an artist from the list of artists of the song.
	 * 
	 * @param artist
	 *            The artist to be deleted.
	 */
	public void deleteArtist(Artist artist) {
		if (artists.contains(artist)) {
			artists.remove(artist);
		}
	}

	/**
	 * Returns the list of chord sheets of the song.
	 * 
	 * @return The list of chord sheets of the song.
	 */
	public List<ChordSheet> getChordSheets() {
		return chordSheets;
	}

	/**
	 * Adds a new chord sheet to the list of chord sheets of the song.
	 * 
	 * @param chords
	 *            The new chord sheet to be added to the list of chord sheets.
	 */
	public void addChordSheet(ChordSheet chords) {
		if (!chordSheets.contains(chords)) {
			chordSheets.add(chords);
		}
	}

	/**
	 * Deletes a chord sheet from the list of chord sheets of the song.
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
	 * Returns the youtube link of the song.
	 * 
	 * @return The youtube link of the song.
	 */
	public String getYoutubeLink() {
		return youtubeLink;
	}

	/**
	 * Sets a new youtube link for the song.
	 * 
	 * @param youtubeLink
	 *            The new youtube link of the song.
	 */
	public void setYoutubeLink(String youtubeLink) {
		this.youtubeLink = youtubeLink;
	}

	/**
	 * Returns the list of tags of the song.
	 * 
	 * @return The list of tags of the song.
	 */
	public List<Tag> getTags() {
		return tags;
	}

	/**
	 * Adds a new tag to the list of tags of the song.
	 * 
	 * @param tag
	 *            The new tag to be added to the list of tags.
	 */
	public void addTag(Tag tag) {
		if (!tags.contains(tag)) {
			tags.add(tag);
		}
	}

	/**
	 * Deletes a tag from the list of tags of the song.
	 * 
	 * @param tag
	 *            The tag to be deleted.
	 */
	public void deleteTag(Tag tag) {
		if (tags.contains(tag)) {
			tags.remove(tag);
		}
	}

	/**
	 * Returns the genres list of the song.
	 * 
	 * @return The genres list of the song.
	 */
	public List<Genre> getGenres() {
		return genres;
	}

	/**
	 * Adds a new genre to the list of genres of the song.
	 * 
	 * @param tag
	 *            The new genre to be added to the list of genres.
	 */
	public void addGenre(Genre genre) {
		if (!genres.contains(genre)) {
			genres.add(genre);
		}
	}

	/**
	 * Deletes a genre from the list of genres for the song.
	 * 
	 * @param genre
	 *            The genre to be deleted.
	 */
	public void deleteGenre(Genre genre) {
		if (genres.contains(genre)) {
			genres.remove(genre);
		}
	}

	/**
	 * Returns the serialVersionUID number of the song.
	 * 
	 * @return The serialVersionUID number of the song.
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * A song is equal to another if it has the same ID.
	 */
	@Override
	public boolean equals(Object obj) {
		boolean answer = false;
		if (obj instanceof Song) {
			if (getSongID().equals(((Song) obj).getSongID())) {
				answer = true;
			}
		}
		return answer;
	}

	/**
	 * Returns a string with the title and the artist of the song, and the user
	 * who uploaded the song.
	 */
	@Override
	public String toString() {
		return getTitle() + "by" + getArtists().toString() + ". Uploaded by: "
				+ getUser();
	}

}
