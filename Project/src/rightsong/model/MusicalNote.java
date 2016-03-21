package rightsong.model;

/**
 * Musical note enumeration class.
 * 
 * @author Karen Santos
 * @author Jolayemioluwa Ilori
 * @version November 2014
 *
 */
public enum MusicalNote {

	DO("C"), DO_SHARP("C#"), RE_FLAT("Db"), RE("D"), RE_SHARP("D#"), MI_FLAT(
			"Eb"), MI("E"), FA("F"), FA_SHARP("F#"), SOL_FLAT("Gb"), SOL("G"), SOL_SHARP(
			"G#"), LA_FLAT("Ab"), LA("A"), LA_SHARP("A#"), TI_FLAT("Bb"), TI(
			"B");

	private String note;

	/**
	 * Creates a new musical note that can be one of the 7 musical notes, flat
	 * or sharp.
	 * 
	 * @param note
	 *            The new musical note.
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
