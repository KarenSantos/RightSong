package models;

public class ChordSheet {
	
	private static final long serialVersionUID = 1L;
	
	private String chordSheetID;
	private MusicalNote key;
	
	public ChordSheet(String id, MusicalNote key){
		this.chordSheetID = id;
		this.key = key;
	}

	public String getChordSheetID() {
		return chordSheetID;
	}

	public void setChordSheetID(String chordSheetID) {
		this.chordSheetID = chordSheetID;
	}

	public MusicalNote getKey() {
		return key;
	}

	public void setKey(MusicalNote key) {
		this.key = key;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean answer = false;
		if (obj instanceof ChordSheet){
			if (getChordSheetID().equals(((ChordSheet)obj).getChordSheetID())){
				answer = true;
			}
		}
		return answer;
	}
	
	@Override
	public String toString() {
		return "Chord sheet in " + getKey();
	}

}
