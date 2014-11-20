package models;

public enum ChordType {
	
	MINOR(1), MAJOR(2), DIMINISHED(0);
	 
	private int type;
 
	/**
	 * Creates a new chord type.
	 * 
	 * @param type
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
