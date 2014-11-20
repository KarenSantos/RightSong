package models;

import java.io.Serializable;

public class User {
	
	private static final long serialVersionUID = 1L;

	private String userID;
	private String name;
	private String email;
	private String password;
	private UserType type;
	
	public User(String userID, String name, String email, String password){
		this.userID = userID;
		this.name = name;
		this.email = email;
		this.password = password;
		
		type = UserType.REGULAR;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean answer = false;
		if (obj instanceof User){
			if (getUserID().equals(((User) obj).getUserID())){
				answer = true;
			}
		}
		return answer;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
}
