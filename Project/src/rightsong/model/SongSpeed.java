package rightsong.model;

/**
 * Song speed enumeration class.
 * 
 * @author Karen Santos
 * @version November 2014
 *
 */
public enum SongSpeed {

	VERY_SLOW(1), SLOW(2), MODERATE(3), FAST(4), VERY_FAST(5);

	private int speed;

	/**
	 * Creates a new song speed.
	 * 
	 * @param speed
	 */
	private SongSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * Returns the value of the song speed.
	 * 
	 * @return The value of the song speed.
	 */
	public int getValue() {
		return speed;
	}

}
