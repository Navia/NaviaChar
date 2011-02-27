package org.navia.java.plugins.mcandze;

public enum Gender {
	MALE("Male", "his"), 
	FEMALE("Female", "her"), 
	UNKNOWN("Unknown", "its");
	
	private final String niceName;
	private final String thirdPerson;
	
	

	Gender(String name, String thirdPerson){
		this.niceName = name;
		this.thirdPerson = thirdPerson;
	}

	public String getNiceName() {
		return niceName;
	}
	
	public String getThirdPerson() {
		return thirdPerson;
	}
	
}
