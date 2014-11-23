package rightsong.model;

/**
 * User type enumeration class.
 * 
 * @author Karen Santos
 * @version November 2014
 *
 */
public enum UserType {

	REGULAR(1), PREMIUM(2), ADMINISTRATOR(0);

	private int type;

	/**
	 * Creates a new user type that can be Regular(1), Premium(2) or
	 * Administrator(0).
	 * 
	 * @param type
	 *            The new user type.
	 */
	private UserType(int type) {
		this.type = type;
	}

	/**
	 * Returns the value of the user type.
	 * 
	 * @return The value of the user type.
	 */
	public int getValue() {
		return type;
	}

}
