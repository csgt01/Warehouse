package de.csgt.domain;

public enum Color {

	LIGHT_TURQUOISE("Helltürkis"), PINK2("Pink"), BLUE_PURPLE("Blau-Lila"), DARK_PINK("Dunkelpink"), 
	PINK("Rosa"), TURQUOISE("Türkis"), LEMON("Lemon"), PURPLE("Flieder"), MIDDLE_BLUE("Mittelblau"), 
	LIGHT_BLUE("BHellblau"), SHY_BLUE("Shyblau"), BLUE("Blau"), BABY_BLUE("Bybyblau"), 
	RED("Rot"), WHITE("Weiß"), GREEN("Grün");
	
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
