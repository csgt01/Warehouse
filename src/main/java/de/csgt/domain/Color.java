package de.csgt.domain;

public enum Color {

	BLUE("Blau"), RED("Rot"), WHITE("Weiß"), GREEN("Grün");
	
	private Color(String name) {
		this.name = name;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
