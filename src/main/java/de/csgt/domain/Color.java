package de.csgt.domain;

public enum Color {

	SEVERAL("Verschiedene Farben"), 
	LIGHT_TURQUOISE("Helltürkis"), 
	PINK2("Pink"), 
	BLUE_PURPLE("Blau-Lila"), 
	DARK_PINK("Dunkelpink"), 
	PINK("Rosa"), 
	TURQUOISE("Türkis"), 
	LEMON("Lemon"), 
	PURPLE("Flieder"), 
	MIDDLE_BLUE("Mittelblau"), 
	LIGHT_BLUE("Hellblau"), 
	YELLOW_GREEN("Gelb-grün"),
	SKY_BLUE("Skyblau"), 
	BLUE("Blau"), 
	BABY_BLUE("Babyblau"), 
	RED("Rot"), 
	WHITE("Weiß"), 
	GREEN("Grün"), 
	BABY_PINK("Babyrosa"), 
	WOOD("Holz"), 
	GREY("Grau"), 
	LIGHT_GREY("Hellgrau"), 
	GERANIE_PINK("Geraniepink"), 
	SILVER("Silber"), 
	BLACK("Schwarz"), 
	DARK_BLUE("Dunkelblau"), 
	NO_COLOR("Keine Auswahl");
	
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
