package rightsong.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rightsong.model.*;

/**
 * Controller class for the RightSong client.
 * 
 * @author karensantos
 * @version November 2014
 *
 */
public class ClientController {

	final private int SONGS = 0;
	final private int TAGS = 1;
	final private int GENRES = 2;
	final private int REPERTORIES = 3;
	final private int ARTISTS = 4;
	final private int CHORD_SHEETS = 5;
	final private int CHORDS = 6;

	private Client client;

	private List<Song> songs;
	private List<Song> mySongs;
	private List<Tag> tags;
	private List<Genre> genres;
	private List<SongSpeed> speeds;
	private List<Repertory> myRepertories;
	private List<Artist> artists;
	private List<ChordSheet> chordSheets;
	private List<Chord> chords;

	/**
	 * Creates a client controller with the lists of all the data in the model.
	 */
	public ClientController() {
		songs = new ArrayList<Song>();
		mySongs = new ArrayList<Song>();
		tags = new ArrayList<Tag>();
		genres = new ArrayList<Genre>();
		myRepertories = new ArrayList<Repertory>();
		artists = new ArrayList<Artist>();
		chordSheets = new ArrayList<ChordSheet>();
		chords = new ArrayList<Chord>();

		setSpeeds();
	}

	/**
	 * Sets the client for this client controller.
	 * 
	 * @param client
	 *            The client to be set.
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * Sets all the data in the client controller with the data received from
	 * the server.
	 * 
	 * @param data
	 *            The data received from the server.
	 * @return Returns true if the data was set.
	 */
	@SuppressWarnings("unchecked")
	public boolean setData(Object data) {

		List<List<?>> lists = (List<List<?>>) data;

		setSongs((List<Song>) lists.get(SONGS));
		setMySongs();
		setTags((List<Tag>) lists.get(TAGS));
		setGenres((List<Genre>) lists.get(GENRES));
		setMyRepertories((List<Repertory>) lists.get(REPERTORIES));
		setArtists((List<Artist>) lists.get(ARTISTS));
		setChordSheets((List<ChordSheet>) lists.get(CHORD_SHEETS));
		setChords((List<Chord>) lists.get(CHORDS));

		return true;
	}

	/**
	 * Returns all the songs from the client controller.
	 * 
	 * @return All the songs from the client controller.
	 */
	public List<Song> getSongs() {
		Collections.sort(songs);
		return songs;
	}

	/**
	 * Returns all the songs that are from this client.
	 * 
	 * @return All the songs that are from this client.
	 */
	public List<Song> getMySongs() {
		Collections.sort(mySongs);
		return mySongs;
	}

	/**
	 * Sets all the songs from the client controller.
	 * 
	 * @param songs
	 *            The song list that will be set.
	 */
	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	/**
	 * Returns all the tags from the client controller.
	 * 
	 * @return All the tags from the client controller.
	 */
	public List<Tag> getTags() {
		return tags;
	}

	/**
	 * Sets all the tags from the client controller.
	 * 
	 * @param tags
	 *            The tag list that will be set.
	 */
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	/**
	 * Returns all the genres from the client controller.
	 * 
	 * @return All the genres from the client controller.
	 */
	public List<Genre> getGenres() {
		return genres;
	}

	/**
	 * Sets all the genres from the client controller.
	 * 
	 * @param genres
	 *            The genre list that will be set.
	 */
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	/**
	 * Returns all the song speeds from the client controller.
	 * 
	 * @return All the song speeds from the client controller.
	 */
	public List<SongSpeed> getSpeeds() {
		return speeds;
	}

	/**
	 * Returns all the repertories from this client.
	 * 
	 * @return All the repertories from this client.
	 */
	public List<Repertory> getMyRepertories() {
		return myRepertories;
	}

	/**
	 * Sets all the repertories from the client controller.
	 * 
	 * @param repertories
	 *            The repertories list that will be set.
	 */
	public void setMyRepertories(List<Repertory> repertories) {
		this.myRepertories = repertories;
	}

	/**
	 * Returns all the artists from the client controller.
	 * 
	 * @return All the artists from the client controller.
	 */
	public List<Artist> getArtists() {
		return artists;
	}

	/**
	 * Sets all the artists from the client controller.
	 * 
	 * @param artists
	 *            The artists list that will be set.
	 */
	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}

	/**
	 * Returns all the chord sheets from the client controller.
	 * 
	 * @return All the chord sheets from the client controller.
	 */
	public List<ChordSheet> getChordSheets() {
		return chordSheets;
	}

	/**
	 * Sets all the chord sheets from the client controller.
	 * 
	 * @param chordSheets
	 *            The chord sheet list that will be set.
	 */
	public void setChordSheets(List<ChordSheet> chordSheets) {
		this.chordSheets = chordSheets;
	}

	/**
	 * Returns all the chords from the client controller.
	 * 
	 * @return All the chords from the client controller.
	 */
	public List<Chord> getChords() {
		return chords;
	}

	/**
	 * Sets all the chords from the client controller.
	 * 
	 * @param chords
	 *            The chords list that will be set.
	 */
	public void setChords(List<Chord> chords) {
		this.chords = chords;
	}

	/**
	 * Sets all the song speeds from the client controller.
	 */
	private void setSpeeds() {
		speeds = new ArrayList<SongSpeed>();
		speeds.add(SongSpeed.VERY_FAST);
		speeds.add(SongSpeed.FAST);
		speeds.add(SongSpeed.MODERATE);
		speeds.add(SongSpeed.SLOW);
		speeds.add(SongSpeed.VERY_SLOW);
	}

	/**
	 * Sets the list of songs of the client controller with all the songs that
	 * are from this client.
	 */
	private void setMySongs() {
		for (Song s : getSongs()) {
			if (s.getUser().getEmail().equals(client.getEmail())) {
				mySongs.add(s);
			}
		}
	}
}
