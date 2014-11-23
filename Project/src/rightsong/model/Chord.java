package models;

public class Chord {
	
	private static final long serialVersionUID = 1L;
	
	private String chordID;
	private MusicalNote note;
	private ChordType type;
	private String alteration;
	
	public Chord(String chordID, MusicalNote note, ChordType type, String alteration){
		this.chordID = chordID;
		this.note = note;
		this.type = type;
		this.alteration = alteration;
	}

	public String getChordID() {
		return chordID;
	}

	public void setChordID(String chordID) {
		this.chordID = chordID;
	}

	public MusicalNote getNote() {
		return note;
	}

	public void setNote(MusicalNote note) {
		this.note = note;
	}

	public ChordType getType() {
		return type;
	}

	public void setType(ChordType type) {
		this.type = type;
	}

	public String getAlteration() {
		return alteration;
	}

	public void setAlteration(String alteration) {
		this.alteration = alteration;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean answer = false;
		if (obj instanceof Chord){
			if (getChordID().equals(((Chord)obj).getChordID())){
				answer = true;
			}
		}
		return answer;
	}
	
	@Override
	public String toString() {
		String chord = getNote().getNotation();
		
		if (getType() == ChordType.MINOR){
			chord += "m";
		} else if (getType() == ChordType.DIMINISHED){
			chord += "°";
		}
		chord += getAlteration();
		
		return chord;
	}
}
