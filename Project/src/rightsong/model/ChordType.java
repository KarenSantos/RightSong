package rightsong.model;

/**
 * Chord type enumeration class.
 * 
 * @author Karen Santos
 * @version November 2014
 *
 */
public enum ChordType {

	MINOR(1), MAJOR(2), DIMINISHED(0);

	private int type;

	/**
	 * Creates a new chord type that can be Minor(1), Major(2) or Diminished(0).
	 * 
	 * @param type
	 *            The chord type.
	 */
	private ChordType(int type) {
		this.type = type;
	}

	/**
	 * Returns the value of the chord type.
	 * 
	 * @return The value of the chord type.
	 */
	public int getValue() {
		return type;
	}

}
