package models;

public enum UserType {
	
	REGULAR(1), PREMIUM(2);
	 
	private int type;
 
	/**
	 * Creates a new user type.
	 * 
	 * @param type
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
