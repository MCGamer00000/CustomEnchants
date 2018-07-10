package me.mcgamer00000.customenchants.utils;

public class Enchant {

	private final String name;
	private final int level;
	
	public Enchant(String name, int level) {
		this.name = name;
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}
	
}
