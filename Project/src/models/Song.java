package models;

import java.io.Serializable;

public class Song {

	private static final long serialVersionUID = 1L;

	private String songID;
	private String title;
	private String lyric;
	private String youtubeLink;
	private SongSpeed speed;

	public Song(String songID, String title, String lyric, String youtubeLink,
			SongSpeed speed) {
		this.songID = songID;
		this.title = title;
		this.lyric = lyric;
		this.youtubeLink = youtubeLink;
		this.speed = speed;
	}

	public String getSongID() {
		return songID;
	}

	public void setSongID(String songID) {
		this.songID = songID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLyric() {
		return lyric;
	}

	public void setLyric(String lyric) {
		this.lyric = lyric;
	}

	public String getYoutubeLink() {
		return youtubeLink;
	}

	public void setYoutubeLink(String youtubeLink) {
		this.youtubeLink = youtubeLink;
	}

	public SongSpeed getSpeed() {
		return speed;
	}

	public void setSpeed(SongSpeed speed) {
		this.speed = speed;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean answer = false;
		if (obj instanceof Song){
			if(getSongID().equals(((Song) obj).getSongID())){
				answer = true;
			}
		}
		return answer;
	}
	
	@Override
	public String toString() {
		return getTitle();
	}

}
