package rightsong.model;

import java.io.Serializable;

/**
 * Artist class.
 * 
 * @author Karen Santos
 * @version November 2014
 *
 */
public class Artist implements Serializable {

	private static final long serialVersionUID = 1L;

	private String artistID;
	private String name;

	/**
	 * Creates a new artist with an ID and a name.
	 * 
	 * @param artistID
	 *            The id of the artist.
	 * @param name
	 *            The name of the artist.
	 */
	public Artist(String artistID, String name) {
		this.artistID = artistID;
		this.name = name;
	}

	/**
	 * Returns the ID of the artist.
	 * 
	 * @return The ID of the artist.
	 */
	public String getArtistID() {
		return artistID;
	}

	/**
	 * Sets a new ID for the artist.
	 * 
	 * @param artistID
	 *            The new ID of the artist.
	 */
	public void setArtistID(String artistID) {
		this.artistID = artistID;
	}

	/**
	 * Returns the name of the artist.
	 * 
	 * @return The name of the artist.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets a new name for the artist.
	 * 
	 * @param name
	 *            The new name of the artist.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the serialVersionUID number of the artist.
	 * 
	 * @return The serialVersionUID number of the artist.
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * An artist is equal to another if the artist ID is the same.
	 */
	@Override
	public boolean equals(Object obj) {
		boolean answer = false;
		if (obj instanceof Artist) {
			if (getArtistID().equals(((Artist) obj).getArtistID())) {
				answer = true;
			}
		}
		return answer;
	}

	/**
	 * Artist toString containing the artist name.
	 */
	@Override
	public String toString() {
		return getName();
	}

}