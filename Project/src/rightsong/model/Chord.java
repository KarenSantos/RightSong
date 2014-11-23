package rightsong.model;

import java.io.Serializable;

/**
 * Chord class.
 * 
 * @author Karen Santos
 * @version November 2014
 *
 */
public class Chord implements Serializable {

	private static final long serialVersionUID = 1L;

	private String chordID;
	private MusicalNote note;
	private ChordType type;
	private String alteration;

	/**
	 * Creates a chord with an ID, a musical note, a chord type and an
	 * alteration string for complement.
	 * 
	 * @param chordID
	 *            The ID of the chord.
	 * @param note
	 *            The musical note of the chord.
	 * @param type
	 *            The type of the chord.
	 * @param alteration
	 *            The alteration of the chord if necessary.
	 */
	public Chord(String chordID, MusicalNote note, ChordType type,
			String alteration) {
		this.chordID = chordID;
		this.note = note;
		this.type = type;
		this.alteration = alteration;
	}

	/**
	 * Returns the ID of the chord.
	 * 
	 * @return The ID of the chord.
	 */
	public String getChordID() {
		return chordID;
	}

	/**
	 * Sets a new ID for the chord.
	 * 
	 * @param chordID
	 *            The new ID of the chord.
	 */
	public void setChordID(String chordID) {
		this.chordID = chordID;
	}

	/**
	 * Returns the musical note of the chord.
	 * 
	 * @return The musical note of the chord.
	 */
	public MusicalNote getNote() {
		return note;
	}

	/**
	 * Sets a new musical note for the chord.
	 * 
	 * @param note
	 *            The new musical note of the chord.
	 */
	public void setNote(MusicalNote note) {
		this.note = note;
	}

	/**
	 * Returns the type of the chord.
	 * 
	 * @return The type of the chord.
	 */
	public ChordType getType() {
		return type;
	}

	/**
	 * Sets a new type for the chord.
	 * 
	 * @param type
	 *            The new type of the chord.
	 */
	public void setType(ChordType type) {
		this.type = type;
	}

	/**
	 * Returns the alteration of the chord or an empty string if it has no
	 * alteration.
	 * 
	 * @return The alteration of the chord.
	 */
	public String getAlteration() {
		return alteration;
	}

	/**
	 * Sets a new alteration for the chord.
	 * 
	 * @param alteration
	 *            The new alteration of the chord.
	 */
	public void setAlteration(String alteration) {
		this.alteration = alteration;
	}

	/**
	 * Returns the serialVersionUID number of the chord.
	 * 
	 * @return The serialVersionUID number of the chord.
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * A chord is equal do another if it has the same ID.
	 */
	@Override
	public boolean equals(Object obj) {
		boolean answer = false;
		if (obj instanceof Chord) {
			if (getChordID().equals(((Chord) obj).getChordID())) {
				answer = true;
			}
		}
		return answer;
	}

	/**
	 * Returns a string with the musical note, chord type and alteration.
	 */
	@Override
	public String toString() {
		String chord = getNote().getNotation();

		if (getType() == ChordType.MINOR) {
			chord += "m";
		} else if (getType() == ChordType.DIMINISHED) {
			chord += "°";
		}
		chord += getAlteration();

		return chord;
	}
}
