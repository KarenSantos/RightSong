package rightsong.model;

/**
 * Song speed enumeration class.
 * 
 * @author Karen Santos
 * @version November 2014
 *
 */
public enum SongSpeed {

	VERY_SLOW("Very slow"), SLOW("Slow"), MODERATE("Moderate"), FAST("Fast"), VERY_FAST("Very fast");

	private String speed;

	/**
	 * Creates a new song speed.
	 * 
	 * @param speed
	 */
	private SongSpeed(String speed) {
		this.speed = speed;
	}

	/**
	 * Returns the value of the song speed.
	 * 
	 * @return The value of the song speed.
	 */
	public String getName() {
		return speed;
	}

}
