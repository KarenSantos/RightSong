package rightsong.client;

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
public class ClientController {
	
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
	
	public ClientController(){
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
	
	public void setClient(Client client){
		this.client = client;
	}

	@SuppressWarnings("unchecked")
	public void setData(List<?> list){
		
		if(list.get(0) instanceof Song){
			setSongs((List<Song>) list);
			setMySongs();
		}
		else if(list.get(0) instanceof Tag){
			setTags((List<Tag>) list);
		}
		else if(list.get(0) instanceof Genre){
			setGenres((List<Genre>) list);
		}
		else if(list.get(0) instanceof Repertory){
			setMyRepertories((List<Repertory>) list);
		}
		else if(list.get(0) instanceof Artist){
			setArtists((List<Artist>) list);
		}
		else if(list.get(0) instanceof ChordSheet){
			setChordSheets((List<ChordSheet>) list);
		}
		else if(list.get(0) instanceof Chord){
			setChords((List<Chord>) list);
		}
	}

	public List<Song> getSongs() {
		Collections.sort(songs);
		return songs;
	}
	
	public List<Song> getMySongs(){
		Collections.sort(mySongs);
		return mySongs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	
	public List<SongSpeed> getSpeeds(){
		return speeds;
	}

	public List<Repertory> getMyRepertories() {
		return myRepertories;
	}

	public void setMyRepertories(List<Repertory> repertories) {
		this.myRepertories = repertories;
	}

	public List<Artist> getArtists() {
		return artists;
	}

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}

	public List<ChordSheet> getChordSheets() {
		return chordSheets;
	}

	public void setChordSheets(List<ChordSheet> chordSheets) {
		this.chordSheets = chordSheets;
	}

	public List<Chord> getChords() {
		return chords;
	}

	public void setChords(List<Chord> chords) {
		this.chords = chords;
	}
	
	private void setSpeeds() {
		speeds = new ArrayList<SongSpeed>();
		speeds.add(SongSpeed.VERY_FAST);
		speeds.add(SongSpeed.FAST);
		speeds.add(SongSpeed.MODERATE);
		speeds.add(SongSpeed.SLOW);
		speeds.add(SongSpeed.VERY_SLOW);
	}
	
	private void setMySongs(){
		for (Song s : getSongs()){
			if (s.getUser().getEmail().equals(client.getEmail())){
				mySongs.add(s);
			}
		}
	}
}
