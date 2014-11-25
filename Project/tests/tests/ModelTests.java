package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import rightsong.model.*;

public class ModelTests {
	
	private User user1;
	
	@Before
	public void setUp(){
		user1 = new User("id1", "username1", "email1", "password1");
	}
	
	@Test
	public void shouldBeAbleToCreateUsers() {
		User user = new User("id", "username", "email", "password");
		
		assertEquals("id", user.getId());
		assertEquals("username", user.getUsername());
		assertEquals("email", user.getEmail());
		assertEquals("password", user.getPassword());
		
	}
	
	@Test
	public void shouldBeAbleToCreateSongs(){
		
		List<String> lyrics = new ArrayList<String>();
		lyrics.add("first line of the lyrics");
		lyrics.add("second line of the lyrics");
		
		Song song = new Song("id", "this song", lyrics, SongSpeed.SLOW);
		
		assertEquals("id", song.getId());
		assertEquals("this song", song.getTitle());
		assertEquals("[first line of the lyrics, second line of the lyrics]", song.getLyrics().toString());
		assertEquals(SongSpeed.SLOW, song.getSpeed());
		assertEquals("username1", song.getUser().toString());
		
	}
	
	@Test
	public void shouldBeAbleToCreateArtist(){
		
		Artist artist = new Artist("artId", "artist name");
		
		assertEquals("artId", artist.getId());
		assertEquals("artist name", artist.getName());
	}
	
	@Test
	public void shouldBeAbleToCreateChords(){
		
		Chord chord1 = new Chord("id1", MusicalNote.RE, ChordType.MINOR, "");
		Chord chord2 = new Chord("id2", MusicalNote.SOL_SHARP, ChordType.MAJOR, "9");
		
		assertEquals("id1", chord1.getId());
		assertEquals("Dm", chord1.toString());
		assertEquals("id2", chord2.getId());
		assertEquals("G#9", chord2.toString());
	}
	
	@Test
	public void shouldBeAbleToCreateRepertories(){
		
		Calendar date = Calendar.getInstance();
		date.set(2014, 11, 22);
		
		Repertory rep = new Repertory("id", "saturday night", date, "romantic songs");
		
		assertEquals("id", rep.getId());
		assertEquals("saturday night", rep.getEventName());
//		assertEquals("", rep.getDate().);
		assertEquals("romantic songs", rep.getDescription());
	}
	
	@Test
	public void shouldBeAbleToCreateTags(){
		
		Tag tag1 = new Tag("id1", "Romantic");
		Tag tag2 = new Tag("id2", "Chill");
		
		assertEquals("id1", tag1.getId());
		assertEquals("Romantic", tag1.getName());
		assertEquals("id2", tag2.getId());
		assertEquals("Chill", tag2.getName());
	}
	
	@Test
	public void shouldBeAbleToCreateGenres(){
		
		Tag genre1 = new Tag("id1", "Classic");
		Tag genre2 = new Tag("id2", "Jazz");
		
		assertEquals("id1", genre1.getId());
		assertEquals("Classic", genre1.getName());
		assertEquals("id2", genre2.getId());
		assertEquals("Jazz", genre2.getName());
	}
	
	@Test
	public void userShouldBeAbleToAddSongs(){
		
		List<String> lyrics = new ArrayList<String>();
		lyrics.add("first line of the lyrics");
		lyrics.add("second line of the lyrics");
		
		Song song = new Song("id", "this song", lyrics, SongSpeed.SLOW);
		
		user1.addSong(song);
		
		assertEquals(1, user1.getSongs().size());
		assertEquals("username1", song.getUser().toString());
		
	}
	
	@Test
	public void userShouldBeAbleToTagHisSongs(){
		
		List<String> lyrics = new ArrayList<String>();
		lyrics.add("first line of the lyrics");
		lyrics.add("second line of the lyrics");
		
		Song song = new Song("id", "this song", lyrics, SongSpeed.SLOW);
		
		user1.addSong(song);
		
		Tag tag1 = new Tag("id1", "Romantic");
		Tag tag2 = new Tag("id2", "Chill");
		
		user1.addTagToSong(song, tag1);
		user1.addTagToSong(song, tag2);
		
		assertEquals(2, song.getTags().size());
		assertEquals(song, tag1.getSongs().get(0));
		assertEquals(song, tag2.getSongs().get(0));
		
	}

}
