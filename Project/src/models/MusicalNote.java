package models;

public enum MusicalNote {
	
	DO("C"),
	DO_SHARP("C#"),
	RE_FLAT("Db"),
	RE("D"),
	RE_SHARP("D#"),
	MI_FLAT("Eb"),
	MI("E"),
	FA("F"),
	FA_SHARP("F#"),
	SOL_FLAT("Gb"),
	SOL("G"),
	SOL_SHARP("G#"),
	LA_FLAT("Ab"),
	LA("A"),
	LA_SHARP("A#"),
	TI_FLAT("Bb"),
	TI("B"); 
	 
	private String note;
 
	/**
	 * Creates a new musical note.
	 * 
	 * @param note
	 */
	private MusicalNote(String note) {
		this.note = note;
	}

	/**
	 * Returns the notation of the musical note.
	 * 
	 * @return The notation of the musical note.
	 */
	public String getNotation() {
		return note;
	}

}
