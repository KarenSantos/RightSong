package models;

import java.io.Serializable;

public class Artist {
	
	private static final long serialVersionUID = 1L;
	
	private String artistID;
	private String name;
	
	public Artist(String artistID, String name){
		this.artistID = artistID;
		this.name = name;
	}

	public String getArtistID() {
		return artistID;
	}

	public void setArtistID(String artistID) {
		this.artistID = artistID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean answer = false;
		if (obj instanceof Artist){
			if (getArtistID().equals(((Artist)obj).getArtistID())){
				answer = true;
			}
		}
		return answer;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
}